<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<title>Hello, world!</title>
<script>
    $(function(){

        let lat = '<%= request.getParameter("lat") %>';
        let lnt = '<%= request.getParameter("lnt") %>';

        if(lat !== 'null' && lnt !== 'null'){
            $('#lat').val(lat);
            $('#lnt').val(lnt);
        }

        console.log(lat);
        console.log(lnt);
    });

    function getMyLocation() {

        // 위치 정보 획득
        navigator.geolocation.getCurrentPosition(function (pos){

            let latVal = pos.coords.latitude;
            let lntVal = pos.coords.longitude;

            // 획득한 위치 정보 값 세팅
            $('#lat').val(latVal);
            $('#lnt').val(lntVal);

            // DB에 위치 정보 값 추가
            $.ajax({
                method: 'GET',
                url: '/locationHistory',
                data: {
                    'lat' : latVal,
                    'lnt' : lntVal
                },
                success: function (data) {
                    console.log(data)
                }
            });
        });
    }

    function getAroundWifiInfo(){

        let latVal = $('#lat').val();
        let lntVal = $('#lnt').val();

        let url = "/getAroundWifiInfo";
        location.href = url + "?lat=" + latVal + "&lnt=" + lntVal;
    }

</script>
<style>
    table th{
        text-align: center;
    }

    body{
        padding : 10px;
    }

    .Div1, .Div2{
        margin-bottom: 15px;
    }
</style>
<body>
    <h1>와이파이 정보 구하기</h1>

    <div class="Div1">
        <a href="index.jsp">홈</a> |
        <a href="locationHistoryList">위치 히스토리 목록</a> |
        <a href="wifiInfo">Open API 와이파이 정보 가져오기</a>
    </div>

    <div class="Div2">
        LAT: <input id="lat" type="text" value="0.0">
        ,LNT: <input id = "lnt" type="text" value="0.0">
        <button onclick="getMyLocation()">내 위치 가져오기</button>
        <button onclick="getAroundWifiInfo()">근처 WIPI 정보 보기</button>
    </div>

    <table class="table table-striped table-bordered">
        <thead class="thead-light">
            <tr>
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류<th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach items="${list}" var="list">
                    <tr>
                        <td>${list.distance}</td>
                        <td>${list.mgrNo}</td>
                        <td>${list.wrdofc}</td>
                        <td>${list.mainNm}</td>
                        <td>${list.adres1}</td>
                        <td>${list.adres2}</td>
                        <td>${list.instlFloor}</td>
                        <td>${list.instlTy}</td>
                        <td>${list.instlMby}</td>
                        <td>${list.svcSe}</td>
                        <td>${list.cmcwr}</td>
                        <td></td>
                        <td>${list.cnstcYear}</td>
                        <td>${list.inoutDoor}</td>
                        <td>${list.remars3}</td>
                        <td>${list.lat}</td>
                        <td>${list.lnt}</td>
                        <td>${list.workDttm}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="18">
                        <div style="text-align: center;">
                            위치 정보를 입력한 후에 조회해 주세요.
                        </div>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>

        </tbody>
    </table>

</body>
</html>