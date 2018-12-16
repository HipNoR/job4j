<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>Order Info</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <!-- Custom style -->
    <link rel="stylesheet" href="../../css/cinema.css">
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <div class="head">
                <h3>
                    Информация о Вашем заказе.
                </h3>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${!empty requestScope.message}">
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <div class="alert alert-danger">
                        <c:out value="${requestScope.message}"/>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <div class="row pt-3">
                        <h5>
                            Вы выбрали и оплатили следующие места:
                        </h5>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th> Ряд </th>
                            <th> Место </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.reserved}" var="res">
                            <tr>
                                <c:set var="seat" value="${fn:split(res, '.' )}"/>
                                <td>${seat[0]}</td>
                                <td>${seat[1]}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </c:otherwise>
    </c:choose>
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <form action="${pageContext.servletContext.contextPath}/hall" method="get">
                <div class="row float-right">
                    <button type="submit" class="btn btn-secondary">К выбору мест</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
