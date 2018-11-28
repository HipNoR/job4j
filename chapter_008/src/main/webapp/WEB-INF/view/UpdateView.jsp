<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users update page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            var country = "<c:out value="${user.country}"/>";
            $.ajax({
                url: "loc",
                method: "get",
                complete: function (data) {
                    var result =  "<option name=\"countryradio\"></option>";
                    var countries = JSON.parse(data.responseText);
                    for (var i = 0; i < countries.length; i++) {
                        var selected = country == countries[i] ? "selected" : "";
                        result += "<option " + selected +" value=\""
                            + countries[i] + "\" name=\"countryradio\">" + countries[i] + "</option>"
                    }
                    document.getElementById("country").innerHTML = result;
                }
            });
            fillCity();
        });
        function fillCity() {
            var country = "<c:out value="${user.country}"/>";
            var city = "<c:out value="${user.city}"/>";
            $.ajax({
                url: "loc",
                method: "post",
                data: {"country" : country },
                complete: function (data) {
                    var result =  "<option name=\"citiradio\"></option>";
                    var cities = JSON.parse(data.responseText);
                    for (var i = 0; i < cities.length; i++) {
                        var selected = city == cities[i] ? "selected" : "";
                        result += "<option " + selected + " value=\""
                            + cities[i] + "\" name=\"citiradio\">" + cities[i] + "</option>";
                    }
                    document.getElementById("city").innerHTML = result;
                }
            });
        }
        function getCityByCountry() {
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
        <h3>Update user data.</h3>
    </div>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit" method="post">
        <input type="hidden" name="action" value="update">
        <div class="form-group">
            <label class="control-label col-sm-2" for="id">ID:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="id" placeholder="${user.id}" disabled>
                <input type="hidden" name="id" value="${user.id}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="Enter name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" placeholder="${user.login}" disabled>
                <input type="hidden" name="login" value="${user.login}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" name="password" value="${user.password}" placeholder="Enter password">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="Enter email">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Select country:</label>
            <div class="col-sm-10">
                <select class="form-control" id="country" name="country" onchange="getCityByCountry()" required>
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
                    <option ${user.role eq "user" ? "selected" : ""} value="user">User</option>
                    <c:if test="${sessionScope.role eq 'admin'}">
                        <option ${user.role eq "admin" ? "selected" : ""} value="admin">Admin</option>
                    </c:if>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-danger btn-block">Update user</button>
    </form><br>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/" method="get">
        <button type="submit" class="btn btn-success btn-block">Back</button>
    </form>
</div>
</body>
</html>
