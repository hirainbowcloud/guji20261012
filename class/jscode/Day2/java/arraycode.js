var arr=[];
function addToArrayTail(element){
    arr.push(element);
}
function addToArrayHead(element){
    arr.unshift(element);
}
function showArray(){
    console.log(arr);
}
function addHead(){
    var element = prompt("請輸入要加入頭部的元素");
    addToArrayHead(element);
}
function addTail(){
    var element = prompt("請輸入要加入尾部的元素");
    addToArrayTail(element);
}   