// src/main.jsx
import { createRoot } from 'react-dom/client'
import { StrictMode } from 'react' //
//import App from './App.jsx'
//import App2 from './App2'
//import App3 from './App3'
//import ProductApp from './ProductApp.jsx'
import { FruitList, ProductList, ProductTable } from './AppArry'
import ProductApp from './ProductApp.jsx'
import TodoList from './TodoList.jsx'
import MyEffect from './MyEffect.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
  
  <MyEffect/>
  {/* <TodoList/> */}
  {/* <CardApp /> */}
  {/* <FruitList />
  <ProductList />
  <ProductTable />
  <ProductApp/> */}

  </StrictMode>
  //
  //<ProductApp/>
  //<App3 />
  //<App2 />
  //<App />
 
);
