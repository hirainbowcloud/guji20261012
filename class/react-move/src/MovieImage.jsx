import {useEffect, useState} from 'react'
import './css/myCss.css'
import Card from './components/Card.jsx'
import {Link} from 'react-router-dom'


export default function MovieImage(){

  const [page, setPage] = useState(1);
  const [size, setSize] = useState(3);
  const [error, setError] = useState('');
  const [pics, setPics]=  useState([]);

  useEffect(() => {
    
    //{{baseUrl}}/api/movie?page=1&size=10
    fetch(`http://localhost:8080/api/movie?page=${page}&size=${size}`)
    .then((res) =>{
      if(!res.ok)
      {
        throw new Error(`HTTP 錯誤：${res.status}`)
      }
      else
      {
        return res.json();
      }
    })
    .then((data) =>{
      //console.log(data.content)
      //  setPics(data.content.flatMap((movie) => movie.images));   
      //  console.log("pics:"+JSON.stringify(data.content.flatMap((movie) => movie.images)));   
       
       if (data?.content) 
        {
          setPics(data.content
            .flatMap((movie) => movie.images || [])
            .filter((img) => img.imageType === 'POSTER')
          );
        }

      })   
    .catch((err) => {
      setError(err.message);
    }) 
  },[page,size]);

 return (
    <div>
      <h2>電影海報牆</h2>
      {error && <p style={{ color: 'red' }}>錯誤：{error}</p>}
  
      {/* 使用 Card 組件呈現每張海報 */}
      <div className="card-grid">
        {pics.map((p) => (
          <Card key={p.imageId} title={p.imageType}>
            {/* 👇 夾在中間的這兩行，會直接被傳進 Card 的 children */}
            <img
              src={`http://localhost:8080${p.imageUrl}`}
              alt={p.imageType}
              className="card-img"
              onError={(e) => {
                e.target.onerror = null; // 避免備用圖片也載入失敗時造成無限迴圈
                e.target.src = 'https://via.placeholder.com/220x300?text=No+Image';
              }}
            />
            {/* <p className="card-url">{p.imageUrl}</p> */}
            <p className="card-url">
              <Link 
                to="/booking" 
                style={{ color: '#007bff', textDecoration: 'none' }}
              >
                前往訂票
              </Link>
            </p>
          </Card>
        ))}
      </div>
      {/* <table border="1">
        <thead>
          <tr>
            <th>圖片</th>
            <th>imageUrl</th>
            <th>imageType</th>
          </tr>
        </thead>
        <tbody>
          { pics.map((p) => (
            <tr key={p.imageId}>
              <td>
              <img
              src ={`http://localhost:8080${p.imageUrl}`}
              alt ={p.imageType}
              />
              </td>
              <td>{p.imageUrl}</td>
              <td>{p.imageType}</td>
            </tr>
          ))}
        </tbody>
      </table> */}

    </div>
  );
}

// JavaScript
// fetch("...")                          // 1. 發出請求（第一棒）
//   .then((res) => res.json())          // 2. 收到回應後，解析成 JSON（第二棒）
//   .then((data) => setPics(...))       // 3. 拿到解析後的資料，更新 State（第三棒）
//   .catch((err) => setError(...));     // 4. 接力途中任何一棒摔倒，全交給 catch 救場

// data?.content 是什麼？
// 這個符號 ?. 叫做 可選串連運算子（Optional Chaining）。
// 它的核心作用是：「先檢查前面是否存在，存在才繼續往後讀取屬性；不存在就直接停住並回傳 undefined，避免程式崩潰報錯」。
// let data = null; // 假設 API 請求失敗，data 可能是 null 或 undefined
// console.log(data.content); 
// // ❌ 瀏覽器直接爆錯：TypeError: Cannot read properties of null (reading 'content')

//setPics(data.content.flatMap((movie) => movie.images || []));
//並非每部電影都保證一定有圖片。如果某部電影後端回傳的 images 是 null 或 undefined，直接對它操作會報錯。

//movie.images || []：如果 movie.images 存在就使用它；如果是空的或找不到，就提供一個空的空陣列 [] 當備案，確保不會壞掉。

//---------------------------------------------------

// [
//   {
//     title: "阿凡達",
//     images: [
//       { imageId: 1, imageUrl: "a.jpg", imageType: "海報" },
//       { imageId: 2, imageUrl: "b.jpg", imageType: "劇照" }
//     ]
//   },
//   {
//     title: "鐵達尼號",
//     images: [
//       { imageId: 3, imageUrl: "c.jpg", imageType: "海報" }
//     ]
//   }
// ]

//data.content.map((movie) => movie.images)
//轉換出來的結果會是陣列包陣列（二維陣列）：
// [
//   [ { imageId: 1, ... }, { imageId: 2, ... } ], // 第一部電影的圖片陣列
//   [ { imageId: 3, ... } ]                        // 第二部電影的圖片陣列
// ]

//flatMap()：
//data.content.flatMap((movie) => movie.images)
//[
//   { imageId: 1, imageUrl: "a.jpg", imageType: "海報" },
//   { imageId: 2, imageUrl: "b.jpg", imageType: "劇照" },
//   { imageId: 3, imageUrl: "c.jpg", imageType: "海報" }
// ]
