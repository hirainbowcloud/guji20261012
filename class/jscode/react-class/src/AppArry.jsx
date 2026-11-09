const fruits = ["蘋果", "香蕉", "芒果"];

export function FruitList() {
  return (
    <ul>
      {fruits.map((fruit, index) => (
        // 純文字陣列沒有 id 可用時，暫時用 index（見下方「更好的做法」）
        <li key={index}>{fruit}</li>
      ))}
    </ul>
  );
}

// ✅ 更好的做法：資料有唯一 id 時，優先使用 id 當 key（資料增刪時才不會錯位）
const products = [
  { id: 1, name: "iPhone", price: 999 },
  { id: 2, name: "MacBook", price: 1999 },
];

export function ProductList() {
  return (
    <ul>
      {products.map(product => (
        <li key={product.id}>
          {product.name} — ${product.price}
        </li>
      ))}
    </ul>
  );
}

export function ProductTable() {
  return (
    <table border="1">
     <thead>
        <tr>
          <th>編號</th>
          <th>商品名稱</th>
          <th>價格</th>
        </tr> 
    </thead> 
    <tbody>
        {products.map(p => (
          <tr key={p.id}>{/*React 專屬的唯一識別屬性（Unique Key），沒給key值會出錯*/}
            <td>{p.id}</td>
            <td>{p.name}</td>
            <td>${p.price}</td>
          </tr>
        ))}
    </tbody>    
    </table>
  );
}
