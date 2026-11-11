// src/App.jsx
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'; // 👈 合併成這一行
import MemberPage from './MemberPage';
import Booking from './Booking';
import MovieImage from './MovieImage';

function App() {
  return (
    <BrowserRouter>
      {/* 導覽列：包含 首頁、會員列表、訂票選位、電影圖片 4 個連結 */}
      <nav style={{ padding: '15px', background: '#f5f5f5', borderBottom: '1px solid #ddd' }}>
        <ul style={{ display: 'flex', gap: '20px', listStyle: 'none', margin: 0, padding: 0 }}>
          <li>
            <Link to="/" style={{ textDecoration: 'none', color: '#007bff', fontWeight: 'bold' }}>首頁</Link>
          </li>
          <li>
            <Link to="/member" style={{ textDecoration: 'none', color: '#007bff', fontWeight: 'bold' }}>會員列表 (MemberPage)</Link>
          </li>
          <li>
            <Link to="/booking" style={{ textDecoration: 'none', color: '#007bff', fontWeight: 'bold' }}>訂票選位 (Booking)</Link>
          </li>
          <li>
            <Link to="/movieImage" style={{ textDecoration: 'none', color: '#007bff', fontWeight: 'bold' }}>電影圖片 (MovieImage)</Link>
          </li>
        </ul>
      </nav>

      <Routes>
        {/* 首頁顯示歡迎文字 */}
        <Route path="/" element={<h2>歡迎光臨！請從上方選單切換頁面。</h2>} />
        {/* 會員列表頁：對應網址 http://localhost:5173/member */}
        <Route path="/member" element={<MemberPage />} />
        <Route path="/booking" element={<Booking />} />
        <Route path="/movieImage" element={<MovieImage />} />
        {/* <Route path="/movieImage" element={<MovieImage item={{ image_url: '/images/movies/1/poster1.jpg' }} />} /> */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;