import { useState } from 'react';

// ===== 物件 State =====
export default function ProfileForm() {
  const [user, setUser] = useState({ name: "", email: "" });

  const handleNameChange = (e) => {
    // ✅ 用展開運算子保留其他欄位，只更新需要的
    setUser({ ...user, name: e.target.value });
    console.log(user);
    //e.target：引發事件的 HTML 元素（也就是 <input />）。
    //e.target.value：使用者目前在該輸入框內輸入的最新文字內容。
  };
  const handleEmailChange = (e) => {
    // ✅ 用展開運算子保留其他欄位，只更新需要的
    setUser({ ...user, email: e.target.value });
    console.log(user);
  };
  return (
    <div>
        <input value={user.name} onChange={handleNameChange} /> 
         <br/>
         <input value={user.email} onChange={handleEmailChange} />
     </div>
  );
}