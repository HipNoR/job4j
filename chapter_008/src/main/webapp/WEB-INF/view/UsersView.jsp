<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users list page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" >
    <div class="page-header">
        <h3>List of all users.</h3>
    </div>
    <c:if test="${!empty result}">
        <div class="alert alert-success">
            <c:out value="${result}"/>
        </div>
    </c:if>
</div>
<div class="container">
    <table class="table table-striped" >
        <thead>
        <tr>
            <td colspan="6"></td>
            <td>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <form action="${pageContext.servletContext.contextPath}/create" method="get" style="display: inline-block">
                        <input type="submit" class="btn btn-success btn-sm" value="Create new user">
                    </form>
                </c:if>
                <form action="${pageContext.servletContext.contextPath}/signout" method="get" style="display: inline-block">
                    <input type="submit" class="btn btn-danger btn-sm" value="Sign out">
                </form>
            </td>
        </tr>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Location</th>
            <th>Create date</th>
            <th width="20%">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.country}"/> <c:out value=" ${user.city}"/></td>
                <td><c:out value="${user.createDate}"/></td>
                <td>
                    <c:if test="${sessionScope.role eq 'admin'}">
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get" style="display: inline-block">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="submit" class="btn btn-success btn-sm" value="Update user"></form>
                        <form action="${pageContext.servletContext.contextPath}/ustore" method="post" style="display: inline-block">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="submit" class="btn btn-danger btn-sm" value="Delete user"></form>
                    </c:if>
                    <c:if test="${sessionScope.role ne 'admin'}">
                        <c:if test="${sessionScope.uid eq user.id}">
                            <form action="${pageContext.servletContext.contextPath}/edit" method="get" style="display: inline-block">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                <input type="submit" class="btn btn-success btn-sm" value="Update user"></form>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="6"></td>
            <td>
                <c:if test="${sessionScope.role eq 'admin'}">
                    <form action="${pageContext.servletContext.contextPath}/create" method="get" style="display: inline-block">
                        <input type="submit" class="btn btn-success btn-sm" value="Create new user">
                    </form>
                </c:if>
                <form action="${pageContext.servletContext.contextPath}/signout" method="get" style="display: inline-block">
                    <input type="submit" class="btn btn-danger btn-sm" value="Sign out">
                </form>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
</body>
</html>
