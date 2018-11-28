<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users creation page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                url: "loc",
                method: "get",
                complete: function (data) {
                    var result =  "<option name=\"countryradio\"></option>";
                    var countries = JSON.parse(data.responseText);
                    for (var i = 0; i < countries.length; i++) {
                        result += "<option value=\""+ countries[i] + "\" name=\"countryradio\">" + countries[i] + "</option>";
                    }
                    document.getElementById("country").innerHTML = result;
                }
            });
        });
        function getCity() {
            $.ajax({
                url: "loc",
                method: "post",
                data: {"country" : $("#country").val()},
                complete: function (data) {
                    var result =  "<option name=\"citiradio\"></option>";
                    var cities = JSON.parse(data.responseText);
                    for (var i = 0; i < cities.length; i++) {
                        result += "<option value=\""+ cities[i] + "\" name=\"citiradio\">" + cities[i] + "</option>";
                    }
                    document.getElementById("city").innerHTML = result;
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Create new User.</h3>
    </div>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/create" method="post">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label class="control-label col-sm-2" for="id">ID:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="id" name="id" placeholder="Enter the ID or will be generated automatically">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" name="login" placeholder="Enter login" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Select country:</label>
            <div class="col-sm-10">
                <select class="form-control" id="country" name="country" onchange="getCity()" required>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Select city:</label>
            <div class="col-sm-10">
                <select class="form-control" id="city" name="city" required>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="role">Select role:</label>
            <div class="col-sm-10">
                <select class="form-control" id="role" name="role">
                    <option selected value="user" name="rolerad">User</option>
                    <option value="admin" name="rolerad">Admin</option>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-danger btn-block">Create new user</button>
    </form>
    <br>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/" method="get">
        <button type="submit" class="btn btn-success btn-block">Back</button>
    </form>
</div>
</body>
</html>
