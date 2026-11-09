var carName;
function showMessage(s) {
    alert("Hello, " + s + "!");
    console.log("showMessage " + s + "!");
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
    console.log("a1 === a2: " + (a1 === a2));
    console.log("a1 == a2: " + (a1 == a2));
    console.log("a1 !== a2: " + (a1 !== a2));
    console.log("a1 != a2: " + (a1 != a2));
}
function ok(){
    console.log("ok function called");
    let ok=confirm("Are you sure?");
    if(ok){
        console.log("User confirmed OK");
    }else{
        console.log("User canceled OK");    
    }
}
//console.log("Globale carName: " + carName);
//showMessage("張三");