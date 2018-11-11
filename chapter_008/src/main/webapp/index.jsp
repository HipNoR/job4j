<%@ page import="ru.job4j.userstorage.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.userstorage.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="1" cellspacing="1">
    <%  String result = (String) request.getAttribute("result");
        List<User> users = ValidateService.getInstance().findAll();
        if (users.size() == 0) {%>
    <% if (result != null) {%>
    <tr>
        <td colspan="6" align="center"><%= result%></td>
    </tr>
    <%}%>
    <tr>
        <td>Storage is empty</td>
    </tr>
    <%} else {%>
    <tr>
        <td colspan="6" align="center"><%= result%></td>
    </tr>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Create date</th>
        <th>Actions</th>
    </tr>
    <% for (User user : users) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="ustore" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="Delete user"></form>
            <form method="get" action="edit">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="Update user"></form>
        </td>
    </tr>
    <%};%>
    <%}%>
    <tr>
        <td colspan="6" align="center">
            <form action="create" method="get" style="margin-bottom: 0">
                <input type="submit" value="Create new user">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
