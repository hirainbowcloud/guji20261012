const name = "Alice";
const price = 99.9;
const isLoggedIn = false;

function App2() {  // 定義一個名為 App2 的 React 元件(function component)
  return (
    <div>
      {/* 插入變數 */}
      <h1>Hello, {name}!</h1>

      {/* 數學運算 */}
      <p>含稅價格：{price * 1.05} 元</p>

      {/* 呼叫函式 */}
      <p>{name.toUpperCase()}</p>

      {/* 三元運算子 */}
      <p>{isLoggedIn ? "已登入" : "請登入"}</p>
    </div>
  );
}

export default App2;