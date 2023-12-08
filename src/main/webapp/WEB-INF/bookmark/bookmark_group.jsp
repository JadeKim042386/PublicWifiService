<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>즐겨찾기 그룹</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <style>
        a {
            text-decoration-line: none;
            color: black;
        }
        a:hover {
            color: blue;
            cursor: pointer;
        }
    </style>
</head>
<header>
    <div class="container pb-4 pt-4">
        <div class="row align-items-start">
            <h1>즐겨찾기 그룹</h1>
        </div>
        <a href="/" class="pe-3">홈</a>
        <a href="/wifi_log" class="pe-3">검색 기록 조회</a>
        <a href="#" onclick="requestUpdateAll()" class="pe-3">Open API 와이파이 정보 업데이트</a>
        <a href="#" class="pe-3">북마크 조회</a>
        <a href="/bookmark_group" class="pe-3">북마크 그룹 관리</a>
    </div>
</header>
<body>
<div class="container">
    <button id="addBookmarkGroup" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addBookmarkGroupModal">북마크 그룹 추가</button>
    <div class="modal fade" id="addBookmarkGroupModal" tabindex="-1" aria-labelledby="addBookmarkGroupModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">북마크 그룹 추가</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form enctype="multipart/form-data" action="/bookmark_group/save" method="post">
                        <div class="row mb-3 justify-content-md-start ps-4 pe-4">
                            <input type="text" class="form-control" id="groupName" name="groupName" placeholder="그룹 이름">
                        </div>
                        <div class="text-end pe-3">
                            <button type="submit" class="btn btn-primary">저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-bordered mt-4" style="width: 100%">
        <thead class="text-center table-primary">
        <tr>
            <th style="width: 10%">ID</th>
            <th style="width: 15%">그룹 이름</th>
            <th style="width: 10%">등록일자</th>
            <th style="width: 10%">수정일자</th>
            <th style="width: 10%">수정</th>
            <th style="width: 10%">삭제</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="group" items="${groups.content}">
                <tr>
                    <td>${group.id}</td>
                    <td>${group.groupName}</td>
                    <td>${group.createdAt}</td>
                    <td>${group.modifiedAt}</td>
                    <td><a class="edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a></td>
                    <td><a class="delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</html>
