<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cinema hall</title>
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
    <!-- Custom styles -->
    <link rel="stylesheet" href="../../css/cinema.css">
    <!-- Custom scripts -->
    <script src="../../js/cinema.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="head">
                <h3>
                    Бронирование мест на сеанс
                </h3>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-9">
            <div id="alert-msg"></div>
            <div class="screen"> ЭКРАН</div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <form id="seatCheck" class="form-horizontal" action="${pageContext.servletContext.contextPath}/payment" method="get" onsubmit="return checkSelected()">
                <table class="table table-borderless">
                    <tbody>
                    <c:forEach items="${requestScope.seats}" var="row">
                        <tr>
                            <th>Ряд ${row.key}</th>
                            <c:forEach items="${row.value}" var="place">
                                <td>
                                    <input type="checkbox" name="seat" value="${place.row}.${place.seat}" id="${place.row}.${place.seat}">
                                    <label for="${place.row}.${place.seat}"><div class="seat">${place.seat}</div></label>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row float-right">
                    <button type="submit" class="btn btn-success">К оплате</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row pt-3">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <table class="table">
                <tbody>
                <tr>
                    <td>
                        <div class="seat-ex-fr"></div>
                    </td>
                    <td>
                        Свободное место
                    </td>
                    <td>
                        <div class="seat-ex-ch"></div>
                    </td>
                    <td>
                        Выбранное место
                    </td>
                    <td>
                        <div class="seat-ex-rs"></div>
                    </td>
                    <td>
                        Занятое место
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>
</div>

</body>
</html>
