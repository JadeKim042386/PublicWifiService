<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        a {
            text-decoration-line: none;
            color: black;
        }
        a:hover {
            color: blue;
        }
    </style>
</head>
<header>
    <div class="container pb-4 pt-4">
        <div class="row align-items-start">
            <h1>와이파이 정보 구하기</h1>
        </div>
        <a href="/" class="pe-3">홈</a>
        <a href="/wifi_log" class="pe-3">검색 기록 조회</a>
        <a href="#" onclick="requestUpdateAll()" class="pe-3">Open API 와이파이 정보 업데이트</a>
    </div>
</header>
<body>
<div class="container">
    <form enctype="multipart/form-data" action="/public_wifi/findNearestWifi" method="post">
        <div class="row align-items-start">
            <div class="d-flex justify-content-start flex-row align-items-start">
                <input type="text" class="form-control w-25 me-2" id="latitude" name="latitude" placeholder="위도" disabled="disabled" required>
                <input type="text" class="form-control w-25 me-2" id="longitude" name="longitude" placeholder="경도" disabled="disabled" required>
                <button type="button" class="btn btn-primary me-2" onclick="getUserLocation()">내 위치 확인</button>
                <button type="submit" class="btn btn-primary me-2" onclick="enableInputText()">근처 WIFI 정보 가져오기</button>
            </div>
        </div>
    </form>
    <table class="table table-bordered mt-4" style="width: 100%">
        <thead class="text-center table-primary">
            <tr>
                <th style="width: 10%">거리(km)</th>
                <th style="width: 15%">와이파이명</th>
                <th style="width: 10%">시도</th>
                <th style="width: 10%">시군구</th>
                <th style="width: 25%">상세주소</th>
                <th style="width: 10%">위도</th>
                <th style="width: 10%">경도</th>
                <th style="width: 10%">업데이트일자</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="wifi" items="${wifiList}">
                <tr>
                    <td>${wifi.distance}</td>
                    <td>
                        <a href="/public_wifi/detail/${wifi.id}?distance=${wifi.distance}" class="fw-bolder">${wifi.wifiName}</a>
                    </td>
                    <td>${wifi.addrState}</td>
                    <td>${wifi.addrCity}</td>
                    <td>${wifi.addrDetail}</td>
                    <td>${wifi.latitude}</td>
                    <td>${wifi.longitude}</td>
                    <td>${wifi.updatedAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="/resources/js/requestUpdateAll.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    function getUserLocation() {
        $('html').css('cursor', 'wait');
        navigator.geolocation.getCurrentPosition((position) => {
            $('#latitude').val(position.coords.latitude);
            $('#longitude').val(position.coords.longitude);
            $('html').css('cursor', 'auto');
        });
    }



    function enableInputText() {
        $('#latitude').removeAttr('disabled');
        $('#longitude').removeAttr('disabled');
    }
</script>
</html>
