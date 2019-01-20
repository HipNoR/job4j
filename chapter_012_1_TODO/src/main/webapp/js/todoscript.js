

function getTasks() {
    var sort = document.getElementById("done").checked;

    $.ajax({
        url: "todolist",
        method: "get",
        complete: function (data) {
            var result = "";
            var tasks = JSON.parse(data.responseText);
            for (var i = 0; i < tasks.length; i++) {
                if (!sort) {
                    result += showUndone(tasks[i]);
                } else {
                    result += showAll(tasks[i]);
                }
            }
            var table = document.getElementById("task-table");
            table.innerHTML = result;
        }
    });
}

function sendTask() {
    $.ajax({
        url: "todolist",
        method: "post",
        data: {action : "add", description : $('#desc').val()},
        complete: function () {
            getTasks();
            document.getElementById("desc").value = "";
        }
    });
}

function performTask(data) {
    $.ajax({
        url: "todolist",
        method: "post",
        data: {action : "perform", id : data},
        complete: function () {
            getTasks();
        }
    });
}

function showAll(task) {
    var result = "";
    result += "<tr>"
        + "<td>" + task.id + "</td>"
        + "<td>" + task.descript + "</td>"
        + "<td>" + task.created + "</td>"
        + "<td>" + showStatus(task.done) + "</td>";
    if (!task.done) {
        result += "<td><button type=\"button\" class=\"btn btn-primary\" onclick=\"performTask("
            + task.id + ")\" >Perform</button></td>";
    } else {
        result += "<td>Performed</td>"
    }
    result += "</tr>";
    return result;
}


function showUndone(task) {
    var result = "";
    if (!task.done) {
        result = "<tr>"
            + "<td>" + task.id + "</td>"
            + "<td>" + task.descript + "</td>"
            + "<td>" + task.created + "</td>"
            + "<td>" + showStatus(task.done) + "</td>"
            + "<td><button type=\"button\" class=\"btn btn-primary\" onclick=\"performTask("+ task.id +")\" >Perform</button></td>"
            + "</tr>";
    }
    return result;
}

function showStatus(status) {
    var result = "";
    if (status) {
        result += "<span class=\"badge badge-pill badge-success\">Done</span>";
    } else {
        result += "<span class=\"badge badge-pill badge-danger\">Undone</span>";
    }
    return result;
}

$(document).ready(function () {
     getTasks();
});