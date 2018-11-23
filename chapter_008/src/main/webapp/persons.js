function validate() {
    var result = true;
    var fname = $('#fname').val();
    var lname = $('#lname').val();
    var desc = $('#desc').val();
    var sex = document.querySelectorAll('[name="sradio"]');
    var message = '';
    if (fname == '') {
        result = false;
        message += $('#fname').attr('placeholder') + '\n';
    }
    if (lname == '') {
        result = false;
        message += $('#lname').attr('placeholder') + '\n';
    }
    if (desc == '') {
        result = false;
        message += $('#desc').attr('placeholder') + '\n';
    }
    var counter = 0;
    for (var i = 0; i < sex.length; i++) {
        if (sex[i].checked) {
            break;
        } else {
            counter++;
        }
    }
    if (counter == sex.length) {
        result = false;
        message += 'Choose your gender';
    }
    if (!result) {
        alert(message);
    }

    return result;
}

function addRow() {
    var valid = validate();
    if(valid) {
        $('#table tr:last').after(
            '<tr><td>' + $('#fname').val() + '</td>'
            + '<td>' + $('#lname').val() + '</td>'
            + '<td>' + $('input[name="sradio"]:checked').val() + '</td>'
            + '<td style="word-break: break-word">' + $('#desc').val() + '</td></tr>'
        )
    }
}

function getPersons() {
    $.ajax({
        url: "json.do",
        method: "get",
        complete: function (data) {
            var result = "";
            var persons = JSON.parse(data.responseText);
            for (var i = 0; i < persons.length; i++) {
                result += "<tr>"
                    + "<td>" + persons[i].firstname + "</td>"
                    + "<td>" + persons[i].secondname + "</td>"
                    + "<td>" + persons[i].sex + "</td>"
                    + "<td style=\"word-break: break-word\">" + persons[i].description + "</td>"
                    +"</tr>"
            }
            var table = document.getElementById("personstable");
            table.innerHTML = result;
        }
    });
}
function sendPerson() {
    var valid = validate();
    if (valid) {
        var person = {
            "firstname": $('#fname').val(),
            "secondname": $('#lname').val(),
            "sex": $('input[name="sradio"]:checked').val(),
            "description": $('#desc').val()
        }


        $.ajax({
            url: "json.do",
            method: "post",
            data: JSON.stringify(person),
            contentType: "application/json",
            error: function (message) {
                console.log(message);
            },
            complete: function () {
                getPersons()
            },
            success: function (data) {
                console.log(data);
            }
        });
    }
}

$(document).ready(function () {
    getPersons();
})