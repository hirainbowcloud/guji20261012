
/*const btnClick = () => {
    alert("Button clicked!");
};
const start = () => {
    console.log("Hello from JQuery");
    $("#bt1").click(btnClick);
    // 選取 id 為 em 的元素，綁定 click 事件，把背景改成綠色
    $('#em').click(function () {
        $('#em').css('background-color', 'green');
    });
    $('.yellowcolor').click(function () {
        $('.yellowcolor').css('background-color', 'yellow');
    });
};

$(document).ready(start);*/

const btnClick = () => {
    alert("Button clicked");
};

const start = () => {
    console.log("Hello from JQuery");
    $("#bt1").on("click", btnClick);

    $("#em").on("click", function () {
        $(this).css("background-color", "green");
    });

    $(".yellowcolor").on("click", function () {
        $(this).css("background-color", "yellow");
    });
};

// 讓 jQuery ready 直接呼叫 start
$(start);
