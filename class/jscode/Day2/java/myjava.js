
var luckeyNumber;
function showMessage(s)
{
    alert("HI HI!"+s+"!");
    console.log("show message:"+s);
    
}
function foo(){
    let input = prompt("Enter car model name:");
    carName = input;
    console.log("Car name set to " + carName);
}
function displayTypes(){
    console.log("Type of carName: " + typeof carName);
    let score=80;
    console.log("Type of score: " + typeof score);
    let obj={};
    console.log("Type of obj: " + typeof obj);
    let arr = [];
    console.log("Type of arr: " + typeof arr);
    let isAvailable = true;
    console.log("Type of isAvailable: " + typeof isAvailable);
}
console.log("Globale carName: " + carName);
//showMessage("一二三");

function showValues()
{
    let v1 = "10";
    let v2 = "3.14";
    console.log("v1 Type: "+typeof v1);
    
    let n1 = parseInt(v1);
    let n2 = parseFloat(v2);
    console.log("v1 Type: "+ typeof n1);
}

function stringIf(){
    let str="false";
    if(str){
        console.log("The string is truthy");
    }else{
        console.log("The string is falsy");
    }
}

function operation(){
    let a = "10"; 
    let b = "4";
    console.log("a = " + a);
    console.log("b = " + b);
    console.log("a + b = " + (a + b));
    console.log("a - b = " + (a - b));
    console.log("a * b = " + (a * b));
    console.log("a / b = " + (a / b));
    console.log("a % b = " + (a % b));
    console.log("a ** b = " + (a ** b));
}

function guess(){
    let number = Math.floor(Math.random() * 100) + 1;
    let guess;
    do {
        guess = parseInt(prompt("Guess the number (1-100):"));
        if(guess < number){
            alert("Too low!");
        }else if(guess > number){
            alert("Too high!");
        }else{
            alert("Correct!");
        }
    } while(guess !== number);
}

function op2(){
    let a1=5;
    let a2="5";
    console.log("a1 === a2: " + (a1 === a2)); //false //型別相同 → 再比較值是否相等
    console.log("a1 !== a2: " + (a1 !== a2)); //true  //只要型別或數值任一項不同
    console.log("a1 == a2: " + (a1 == a2)); //true    //轉換型別後數值相等
    console.log("a1 != a2: " + (a1 != a2)); //false   //轉換型別後數值依然不相等
}

function ok()
{
    console.log("ok function called");

    let ok = confirm("Are you sure?")

    if(ok)
    {
        console.log("User confirmed ok")
    }
    else
    {
        console.log("User Canceled this ")
    }
}


