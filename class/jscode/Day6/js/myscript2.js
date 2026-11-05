
const changeText = () => {
    $("#p1").text("This is the new text for the first paragraph");
    $("#div1").text("This is the new text for the second paragraph");
    $("#span1").text("This is the new text for the span paragraph");
}
const changeHtml = () => {
    $("#p1").html("<b>This is the new text for the first paragraph</b>");
    $("#div1").html("<i>This is the new text for the second paragraph</i>");
    $("#span1").html("<p style='color: red;'>This is the new text for the span paragraph</p>");
}
const readp1 = () => {
    let p1Text = $("#p1").text();
    alert(p1Text);
}
const start = () => {
    console.log("Hello from JQuery");
    $("#bt1").click(changeText);
    $("#bt2").click(changeHtml);
    $("#bt3").click(readp1);
}
$(document).ready(start);