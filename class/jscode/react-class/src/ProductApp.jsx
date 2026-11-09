// 子元件：接收 props
export function ProductCard({ name, price, inStock }) {
  return (
    <div 
      className="card"
      style={{
        border: '1px solid black', // 邊框粗細、實線、顏色
        borderRadius: '8px',       // 圓角（選填）
        padding: '16px',           // 內距，文字才不會貼齊邊框
        margin: '10px 0'           // 卡片與卡片之間的間距
      }}
    >
      <h2>{name}</h2>
      <p>價格：${price}</p>
      <p>打9折後:{price * 0.9}</p>
      {inStock ? <span>有庫存</span> : <span>缺貨中</span>}
    </div>
  );
}

// 只要加上 {}，就代表告訴 React：「括號裡面的不是純文字，請當作 JavaScript 程式碼或變數來執行並取出它的值！
// 父元件：傳遞 props（像 HTML 屬性一樣）
function ProductApp() {
  return (
    <div>
      <ProductCard name="iPhone 16" price={999} inStock={true} />
      <ProductCard name="AirPods Pro" price={249} inStock={false} />
    </div>
  );
}

export default ProductApp;