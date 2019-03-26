<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>

</head>
<body>

<div class="container">
    <div class="page-header">
        <h3>Sign in, please.</h3>
    </div>

    <form class="form-inline" action="${pageContext.servletContext.contextPath}/signin" method="post">
        <c:if test="${!empty error}">
            <div class="alert alert-danger">
                <c:out value="${error}"/>
            </div>
        </c:if>

        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" name="login">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" name="password">
        </div>
        <button type="submit" class="btn btn-danger">SIGN IN</button>

        <button type="submit" class="btn btn-danger">SIGN IN</button>

    </form>
</div>
</body>
</html>
