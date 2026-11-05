const changeText = () => {
    let txt1Value = $("#txt1").val();
    alert(txt1Value);
    let newText = prompt("Please enter new text for the input box", txt1Value);
    //prompt("提示文字", "輸入框的預設值");
    if (newText !== null) { 
        //newText !== null 檢查使用者是否『沒有按下取消鍵』
        //newText === null 執行 $("#txt1").val(null)
        $("#txt1").val(newText);
    }
}
const changecolor = () => {
     $("#p1").css('color', 'red');
     $("#p1").addClass("big");
 
}
const toggleclass = () => {
    $("#p1").toggleClass("big"); 
}
const redclass = () => {
    $("#p1").addClass("red");
}
const start = () => {
    console.log("Hello from JQuery");
    $("#bt1").click(changeText);
    $("#bt2").click(changecolor);
    $("#bt3").click(toggleclass);
    $("#bt4").click(redclass);
    
}
$(document).ready(start);