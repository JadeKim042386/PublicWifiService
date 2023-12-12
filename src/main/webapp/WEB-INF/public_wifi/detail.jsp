<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>와이파이 정보 구하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link href="/resources/css/header.css" rel="stylesheet" type="text/css">
    <style>
        th {
            text-align: center;
            width: 30%;
            border-bottom: 1px solid white !important;
            border-collapse: collapse;
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
        <a onclick="requestUpdateAll()" class="pe-3">Open API 와이파이 정보 업데이트</a>
        <a href="/bookmark" class="pe-3">즐겨찾기 조회</a>
        <a href="/bookmark_group" class="pe-3">즐겨찾기 그룹 관리</a>
    </div>
</header>
<body>
    <div class="container">
        <form enctype="multipart/form-data" action="#" method="post">
            <div class="row align-items-start">
                <div class="d-flex justify-content-start flex-row align-items-start">
                    <select id="select-group" class="form-select w-25 me-2">
                        <option selected>즐겨찾기 그룹 선택</option>
                        <c:forEach var="group" items="${groups}">
                            <option value="${group.id}">${group.groupName}</option>
                        </c:forEach>
                    </select>
                    <button type="button" class="btn btn-primary me-2" onclick="saveBookmark()">즐겨찾기 추가</button>
                </div>
            </div>
        </form>
        <table class="table table-bordered mt-4" style="width: 100%">
            <tr>
                <th class="table-primary">거리(km)</th>
                <td>${publicWifi.distance}</td>
            </tr>
            <tr>
                <th class="table-primary">와이파이명</th>
                <td>${publicWifi.wifiName}</td>
            </tr>
            <tr>
                <th class="table-primary">시도</th>
                <td>${publicWifi.addrState}</td>
            </tr>
            <tr>
                <th class="table-primary">시군구</th>
                <td>${publicWifi.addrCity}</td>
            </tr>
            <tr>
                <th class="table-primary">상세주소</th>
                <td>${publicWifi.addrDetail}</td>
            </tr>
            <tr>
                <th class="table-primary">위도</th>
                <td>${publicWifi.latitude}</td>
            </tr>
            <tr>
                <th class="table-primary">경도</th>
                <td>${publicWifi.longitude}</td>
            </tr>
            <tr>
                <th class="table-primary">업데이트일자</th>
                <td>${publicWifi.updatedAt}</td>
            </tr>
        </table>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="/resources/js/requestUpdateAll.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    function saveBookmark() {
        $.ajax({
            url: "/bookmark/save?groupId=" + $('#select-group option:selected').val() + "&wifiId=" + window.location.pathname.split("/").pop(),
            type: "GET",
            beforeSend: function () {
                $('html').css('cursor', 'wait');
            },
            success: function (result) {
                $('html').css('cursor', 'auto');
                console.log(result.resultCode);
                successAlert("즐겨찾기를 추가했습니다.")
            },
            error: function (result) {
                $('html').css('cursor', 'auto');
                console.log(result.responseText);
                failAlert("즐겨찾기 추가에 실패했습니다.")
            }
        });
    }
</script>
</html>
