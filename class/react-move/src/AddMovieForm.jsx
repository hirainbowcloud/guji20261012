import {useState, useEffect}  from 'react';

function AddMovieForm(){
 const [movieForm, setMovieForm] = useState({})
 const [selectedImages, setselectedImages] = useState([])
 const [trailer, setTrailer] = useState(null);
 const [loading, setLoading] = useState(false)
 const [error, setError] = useState()


  const handleInputChange = (e) => 
  {
    const { name, value, type, checked } = e.target
    setMovieForm((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
      //就是利用 [name] 的動態特性：
      //點擊片名輸入框（name="title"）時，更新的就是 title。
      //點擊導演輸入框（name="director"）時，更新的就是 director。
      //點擊核取方塊（name="isActive"）時，更新的就是 isActive。
      //一般輸入框（文字、數字、下拉選單等）：取用文字內容 value。
    }))    
   }

   const handleTrailerChanges = (e) =>
   {
        const files = Array.from(e.target.file)
        setTrailer(files)
   }

    const handleImageTypeChanges = (e) =>
    {
        const files = Array.from(e.target.files)

        const mappedImages = files.map((file, index) =>({
            file,
            imageType: index === 0 ? 'POSTER' : 'STILL'
        }))
        setselectedImages(mappedImages)
    }
    //我預設第一個files是poster，第二個以後就是still
    //files：這是一個檔案陣列（例如使用者一次選取了 3 張圖片，files 裡就有 3 個 File 物件）。
    //.map(...)：迴圈遍歷這個陣列，每遇到一個元素就執行一次括號裡的箭頭函式。
    //file（第 1 個參數）：當前正在處理的那一個元素（即單張圖片檔案 File 物件）。
    //index（第 2 個參數）：當前元素的索引編號（從 0 開始計數）。
    //第 1 張圖：index 是 0
    //第 2 張圖：index 是 1
    //第 3 張圖：index 是 2

      // 調整某張圖的類型 (例如 POSTER 或 STILL)
    const handleImageTypeChang = (index, newType) => {
        setSelectedImages((prev) =>
        prev.map((item, idx) => (idx === index ? { ...item, imageType: newType } : item))
        )
    }

    const handleSubmit = async (e) =>{
        e.preventDefault()
        setLoading(true)

        try
        {
            //new FormData() :是原生 JavaScript 提供的內建物件建構式，用來以鍵值對（Key-Value pairs）的形式封裝表單資料
            //multipart/form-data，最核心的用途是：處理包含「檔案上傳（圖片、文件）」的 HTTP 請求
            const formData = new FormData()
            //JSON.stringify() :將 JavaScript 物件（Object）或陣列（Array）轉換為符合 JSON 格式
            const movieJsonBlob = new Blob([JSON.stringify(movieForm)], {type: 'application/json'})
            //的第一個參數名稱，必須和後端的 @RequestPart("movie") 完全一致
            formData.append('movie', movieJsonBlob)

            if(selectedImages.length >0)
            {
                const imagesMeta = selectedImages.map ((img) =>({
                    imageType: img.imageType
                }))

                 const imagesJsonBlob = new Blob([JSON.stringify(imagesMeta)], {
                    type: 'application/json'
                })
                
                formData.append('files',imagesJsonBlob)
            }

            if(trailer.length >0)
            {
                
            }

            fetch(`http://localhost:8080/api/movie/add`,{
                method: 'POST',
                body: formData
            })
        }
        catch()
        {}
    }

}

export default AddMovieForm;