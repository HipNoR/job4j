<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    <table>
        <tr>
            <td>Login: </td><td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Password: </td><td>  <input type="password" name="password" ></td>
        </tr>
    </table>

    <input type="submit" value="SIGN IN"></form>

</body>
</html>
