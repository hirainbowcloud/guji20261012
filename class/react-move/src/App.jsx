import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MemberPage from './memberPage';
import Booking from './booking';
// 假設原本的選位頁面叫 BookingPage：
// import BookingPage from './BookingPage.jsx';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* 首頁或選位頁 */}
        {/* <Route path="/booking/:showtimeId" element={<div>訂票選位頁面</div>} />*/}

        {/* 會員列表頁：對應網址 http://localhost:5173/member */}
        <Route path="/member" element={<MemberPage />} />
        <Route path="/booking" element={<Booking/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;