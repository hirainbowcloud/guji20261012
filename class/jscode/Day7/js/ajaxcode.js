const executeForm = (e) => {
    e.preventDefault();
    $.ajax({
        url: 'http://localhost:8080/api/getAll',
        type: 'get',
        dataType: 'json',                            // 伺服器回傳格式                
        data: '',
        statusCode: {
            200: function () { alert("List<Person> Data OK"); },
            204: function () { alert("NO Data"); },
            400: function () { alert("Bad Request"); },
            404: function () { alert("Page Not Found!"); },
            500: function (xhr, statusText, err) {
                alert(xhr.responseText);
            }
        },
        success: function (result) {
            let tbody = $("#personTB");
            tbody.attr("border", "1");
            tbody.empty();
            tbody.append("<tr><th>ID</th><th>Name</th><th>Email</th><th>OP</th></tr>");
            $.each(result, function (index, person) {
                tbody.append("<tr><td>" + person.id + "</td><td>" + person.name + "</td><td>" + person.email 
                    + "</td><td><button onclick='deletePerson(" + person.id + ")'>Delete</button></td><td><button onclick='updatePerson(" + person.id + ")'>Update</button></td></tr>");
            });
        }
    });

};
const updatePerson = (id) => {
     $.ajax({
        url: 'http://localhost:8080/api/find/'+id,
        type: 'get',
        dataType: 'json',                            // 伺服器回傳格式         
        data: '',
        statusCode: {
            200: function () { alert("Get Person ID OK"); },
            404: function () { alert("Person Not Found"); },
        },
        success: function (result) {
            console.log(JSON.stringify(result));
            $("#pid").val(result.id);
            $("#name").val(result.name);
            $("#email").val(result.email);
        }   
    });
};
const updateAndSavePerson = (e) => {
    e.preventDefault();
    $.ajax({
        url: 'http://localhost:8080/api/update/'+$("#pid").val(),
        type: 'put',    
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({id: $("#pid").val(), name: $("#name").val(), email: $("#email").val()}),
        statusCode: {
            200: function () { alert("Update Person OK"); },
            400: function () { alert("Bad Request"); },
            404: function () { alert("Person Not Found"); },
        },
        success: function (result) {
            console.log(JSON.stringify(result));
            executeForm(e); // Refresh the list after updating a person
        }
    });
};

const addPerson = (e) => {
    e.preventDefault();
    $.ajax({
        url: 'http://localhost:8080/api/add',
        type: 'post',
        dataType: 'text',                            // 伺服器回傳格式 
        contentType: 'application/json',                 // 送出資料格式               
        data: JSON.stringify({id: $("#pid").val(), name: $("#name").val(), email: $("#email").val()}),
        statusCode: {
            200: function () { alert("Add Person OK"); },
            400: function () { alert("Bad Request"); },
        },
        success: function (result) {
            console.log(result);
            executeForm(e); // Refresh the list after adding a person
        }   
    });
};
const start = () => {
    console.log("Hello from JQuery");
    $("#listBtn").click(executeForm);
    $("#addBtn").click(addPerson);
    $("#updateBtn").click(updateAndSavePerson); // Using a separate function for update
};

$(document).ready(start);
