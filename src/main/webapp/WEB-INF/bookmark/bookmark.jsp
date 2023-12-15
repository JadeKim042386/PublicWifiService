<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>즐겨찾기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link href="/resources/css/header.css" rel="stylesheet" type="text/css">
</head>
<header>
    <div class="container pb-4 pt-4">
        <div class="row align-items-start">
            <h1>즐겨찾기</h1>
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
        <table class="table table-bordered mt-4 text-center" style="width: 100%">
            <thead class="table-primary">
                <tr>
                    <th style="width: 10%">ID</th>
                    <th style="width: 30%">즐겨찾기 그룹 이름</th>
                    <th style="width: 30%">와이파이명</th>
                    <th style="width: 20%">등록일자</th>
                    <th style="width: 10%">삭제</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="bookmark" items="${bookmarks.content}">
                <tr>
                    <td>${bookmark.id}</td>
                    <td>${bookmark.groupName}</td>
                    <td>${bookmark.wifiName}</td>
                    <td>${bookmark.createdAt}</td>
                    <td><a class="delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-xs-center">
            <ul class="pagination justify-content-center">
                <!-- 이전 -->
                <c:choose>
                    <c:when test="${bookmarks.number <= 0}">
                        <li class="page-item"><a class="page-link disabled" href="/bookmark?page=${bookmarks.number - 1}">Prev</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/bookmark?page=${bookmarks.number - 1}">Prev</a></li>
                    </c:otherwise>
                </c:choose>
                <!-- 페이지 그룹 -->
                <c:forEach var="n" items="${paginationBarNumbers}">
                    <c:choose>
                        <c:when test="${bookmarks.number == n}">
                            <li class="page-item"><a class="page-link disabled" href="/bookmark?page=${n}">${n}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="/bookmark?page=${n}">${n}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <!-- 다음 -->
                <c:choose>
                    <c:when test="${bookmarks.totalPages <= 1 || bookmarks.number < bookmarks.totalPages - 1}">
                        <li class="page-item "><a class="page-link disabled" href="/bookmark/?page=${bookmarks.totalPages - 1}">Next</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item "><a class="page-link" href="/bookmark/?page=${bookmarks.totalPages - 1}">Next</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="/resources/js/requestUpdateAll.js"></script>
<script src="/resources/js/deleteTableRow.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    deleteTableRow("/bookmark/delete/");
</script>
</html>
