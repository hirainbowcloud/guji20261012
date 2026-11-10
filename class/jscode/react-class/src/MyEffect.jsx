import { useState, useEffect } from 'react';

// ===== 陣列 State（每筆資料帶唯一 id，讓 key 有穩定值）=====
function MyEffect() {
  const [userId, setUserId] = useState("A");

  // 切換使用者
  const changeUserId = () => {
    let newId = prompt("請輸入新的使用者 ID");
    if (!newId || newId.trim() === "") return;
    setUserId(newId.trim());
  };

  useEffect(() => {
    console.log(`userId 改變了，重新載入資料：${userId}`);
  }, [userId]); // 只有當 userId 改變（或初次掛載）時才執行

  return (
    <div>
      <p>目前的使用者 ID：<strong>{userId}</strong></p>
      <button onClick={changeUserId}>切換使用者</button>   
    </div>
  );
}

export default MyEffect;