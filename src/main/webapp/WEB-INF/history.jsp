<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script>
    function deleteLocationHistory(id){

    }
</script>
<style>
    table{
        text-align: center;
    }

    body{
        padding: 10px;
    }

    .Div1, .table{
        margin-top: 20px;
    }
</style>
<body>
    <h1>위치 히스토리 목록</h1>

    <div class="Div1">
        <a href="index.jsp">홈</a> |
        <a href="locationHistoryList">위치 히스토리 목록</a> |
        <a href="wifiInfo">Open API 와이파이 정보 가져오기</a>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="list">
            <tr>
                <td>${list.id}</td>
                <td>${list.x}</td>
                <td>${list.y}</td>
                <td>${list.inquiryTime}</td>
                <td>
                    <input type="button" onclick="deleteLocationHistory(${list.id});" value="삭제">
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</body>
</html>