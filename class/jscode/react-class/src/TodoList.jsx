import { useState } from "react";

function TodoList(){
    const [todos, setTodos] = useState([
        {id:1, text:"咖啡"},
        {id:2, text:"奶茶"},
        {id:3, text:"紅茶"},
    ])


const removeTodo =(id) =>{
    setTodos(todos.filter((todo) => todo.id !== id))
};

//...todos：把原本 todos 陣列裡的所有舊待辦事項逐一攤開，放進新的中括號 [] 裡。
//, { id: Date.now(), text }：在這些舊項目的最後面，加上一筆新的待辦事項。
//這整行的意思就是：「複製原本所有的待辦事項，並在結尾新增一筆資料，組成一個全新的陣列傳給 setTodos」
// const addTodo = (text) =>{
//   setTodos([...todos, { id: Date.now(), text }]);
// }

const addTodo = ()=>{
  let text = prompt("輸入代辦事項: ")
  if (!text || text.trim() === "") return;
  setTodos([...todos,{id:Date.now(), text}])
}

 const updateTodo = (id) => {
    let newText = prompt("請輸入更新後的待辦事項");
    if (!text || text.trim() === "") return;
    setTodos(
      todos.map((todo) => (todo.id === id ? { ...todo, text: newText } : todo)) // ✅
    );
  };

//JSX 只能有一個根節點就是一個div的意思
return(
  <div>
   <button onClick={addTodo}>新增</button>
 
  
    <ul>
      {todos.map((todo) => (
        <li key={todo.id}>
          {todo.id}
          {todo.text}
          <button onClick={() => removeTodo(todo.id)}>刪除</button>
          <button onClick={() => updateTodo(todo.id)}>更新</button>
        </li>
      ))}
    </ul>
     
  </div>
  );

  }

  export default TodoList;