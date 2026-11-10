import { useState, useEffect } from 'react';
export default function FindUser() {
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [user, setUser] = useState({});
    const [userId, setUserId] = useState(1); // 新增 state 來追蹤使用者 ID
    
    // https://jsonplaceholder.typicode.com/users/1
//  {
//   "id": 1,
//   "name": "Leanne Graham",
//   "username": "Bret",
//   "email": "Sincere@april.biz",
//   "address": {
//     "street": "Kulas Light",
//     "suite": "Apt. 556",
//     "city": "Gwenborough",
//     "zipcode": "92998-3874",
//     "geo": {
//       "lat": "-37.3159",
//       "lng": "81.1496"
//     }
//   },
//   "phone": "1-770-736-8031 x56442",
//   "website": "hildegard.org",
//   "company": {
//     "name": "Romaguera-Crona",
//     "catchPhrase": "Multi-layered client-server neural-net",
//     "bs": "harness real-time e-markets"
//   }
// }
    
    useEffect(() => {
        // 定義非同步函式（useEffect 的 callback 本身不能是 async）
        async function fetchUserById(userId) {
            try 
            {
                const response = await fetch(`https://jsonplaceholder.typicode.com/users/${userId}`);

                if (!response.ok) 
                {
                    throw new Error(`HTTP 錯誤：${response.status}`);
                }
                const data = await response.json();
                setUser(data);
            } 
            catch (err) 
            {
                setError(err.message);            } 
            finally 
            {
                setLoading(false);
            }
        }

        fetchUserById(userId);
    }, [userId]);
     if (loading) return <p>載入中...</p>;
     if (error) return <p>錯誤：{error}</p>;

     //onChange={(e) => setUserId(Number(e.target.value))}
     //e 代表 Event（事件物件）。
     //e.target：代表「觸發這個事件的 HTML 標籤本體」，在這裡就是你的 <input> 輸入框。
     //value：就是「使用者目前在輸入框裡輸入的內容」。
     //Number("2")：將拿到的字串 "2" 轉成真正的數字 2。
     //setUserId(...)：把轉換後的數字存回 React 的 State 中

// 完整流程圖解:

// 假設你在輸入框按鍵盤輸入了數字 5：

// 觸發事件：你打了 5，引發 onChange。

// 重要!!捕捉資料：e.target.value 抓到當前輸入框的值，此時是字串 "5"。

// 轉型數字：Number("5") 把字串轉換成數字 5。

// 更新狀態：呼叫 setUserId(5) 更新狀態。

// 連鎖反應：userId 變成了 5，觸發了 useEffect(..., [userId])，重新向伺服器發送請求抓取第 5 號使用者的資料。
    return (
        <div>
            <h1>使用者資料</h1>

            <label htmlFor="userId">使用者 ID：</label>
            <input
                id="userId"
                type="number"
                value={userId}
                onChange={(e) => setUserId(Number(e.target.value))}
            />
            <br/>
            <p>使用者 ID：{user.id}</p>
            <span>使用者名稱：{user.name}</span>

        </div>
    );
}

//e.target.value 是指 input、prompt、才會有e.target.value嗎?
//不是。 prompt 完全沒有 e.target.value，而除了 input 之外，其他常見的表單元素也都有 e.target.value。
// 1. prompt 為什麼沒有 e.target.value？
// prompt 是一個同步函式，不是事件。
// prompt("請輸入...") 執行後，瀏覽器會直接把使用者輸入的字串當作函式的回傳值交給你，它不依賴任何 Event 事件物件：
// prompt 是直接用變數接收回傳值，沒有事件 (e)
//let text = prompt("請輸入代辦事項");
//console.log(text); // 這裡拿到的就是輸入的字串，不是 e.target.value
//2. 哪些元素有 e.target.value？
//e.target.value 屬於 DOM 事件監聽機制，只要是 提供使用者輸入或選取值的 HTML 表單元素，在觸發事件（如 onChange、onInput）時，都可以透過 e.target.value 取得當前的值
//瀏覽器把網頁上的每個標籤都包裝成一個物件（即 DOM 節點）

// 一、事件監聽的三大核心要素
// 每次事件觸發，都包含這三個角色：

// 事件目標（Target）：被操作的 HTML 標籤（例如按鈕 <button>、輸入框 <input>、整個網頁視窗 window）。

// 事件類型（Event Type）：使用者做了什麼動作（例如點擊 click、輸入 change、按鍵 keydown）。

// 事件處理函式（Handler / Listener）：事件發生後要執行的動作，通常會接收一個代表事件詳細資訊的參數 e（即 Event 物件）。