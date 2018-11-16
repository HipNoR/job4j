<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="1" cellspacing="1">
        <tr>
            <td colspan="6" align="center"><c:out value="${result}" default="List of all users"/></td>
        </tr>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Create date</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.createDate}"/></td>
                <td>
                    <c:if test="${role eq 'admin'}">
                        <form action="${pageContext.servletContext.contextPath}/ustore" method="post" style="margin: 2px">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="submit" value="Delete user"></form>
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get" style="margin: 2px">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                            <input type="submit" value="Update user"></form>
                    </c:if>
                    <c:if test="${role ne 'admin'}">
                        <c:if test="${uid eq user.id}">
                            <form action="${pageContext.servletContext.contextPath}/edit" method="get" style="margin: 2px">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                <input type="submit" value="Update user"></form>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    <tr>
        <td colspan="6" align="center">
            <c:if test="${role eq 'admin'}">
                <form action="${pageContext.servletContext.contextPath}/create" method="get" style="margin: 2px; display: inline-block">
                    <input type="submit" value="Create new user">
                </form>
            </c:if>
            <form action="${pageContext.servletContext.contextPath}/signout" method="get" style="margin: 2px; display: inline-block">
                <input type="submit" value="Sign out">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
