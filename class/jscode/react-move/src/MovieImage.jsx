import {useEffect, useState} from 'react'
import {useNavigate} from 'react-router-dom'


export default function MovieImage(){
  //const [movie, setMovies] = useState(null);
  const [page, setPage] = useState(1);
  const [size, setSize] = useState(3);
  const [error, setError] = useState('');
  const [pics, setPics]=  useState([]);
  const [img1, setImg1]=useState({imageUrl:"",imageType:""});
  useEffect(() => {
    
    //{{baseUrl}}/api/movie?page=1&size=10
    fetch(`http://localhost:8080/api/movie?${page}&size=${size}`)
    .then((res) =>{
      if(res.ok)
      {
        return res.json();
      }
      else
      {
        setError(res.status)
      }
    })
    .then((data) =>{
      console.log(data.content)
      
      data.content.forEach(movie => {
        console.log(movie.title)

        movie.images.forEach( img =>{
          console.log(img.imageUrl)
          //console.log(img.imageType)

          setPics(prevPics => [...prevPics, {...img1 , imageUrl:img.imageUrl,imageType:img.imageType}]);
           console.log("img1:"+JSON.stringify(img1));
        })
        
      });
     
      
    })
    .catch((err) => {
      setError(err.message);
    })
  },[page,size])

 return (
    <div>
      <h2>電影列表</h2>
      {error && <p style={{ color: 'red' }}>錯誤：{error}</p>}
      <p>test</p>
      <table border="1">
        <thead>
          <tr>
            <th>imageUrl</th>
            <th>imageType</th>
          </tr>
        </thead>
        <tbody>
          {/* pics.map(
            <tr {movie.imageId}>
            <td>{p.imageUrl}</td>
            <td>{p.imageType}</td>
            </tr>
          ))} */}
        </tbody>
      </table>
    </div>
  );
}
