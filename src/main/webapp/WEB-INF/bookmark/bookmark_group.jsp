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
    <link href="/resources/css/header.css" rel="stylesheet" type="text/css">
</head>
<header>
    <div class="container pb-4 pt-4">
        <div class="row align-items-start">
            <h1>즐겨찾기 그룹</h1>
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
    <button id="addBookmarkGroup" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addBookmarkGroupModal">즐겨찾기 그룹 추가</button>
    <div class="modal fade" id="addBookmarkGroupModal" tabindex="-1" aria-labelledby="addBookmarkGroupModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">즐겨찾기 그룹 추가</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form enctype="multipart/form-data" action="/bookmark_group/save" method="post">
                        <div class="row mb-3 justify-content-md-start ps-4 pe-4">
                            <input type="text" class="form-control" name="groupName" placeholder="그룹 이름">
                        </div>
                        <div class="text-end pe-3">
                            <button type="submit" class="btn btn-primary">저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger mt-4" role="alert">
                ${errorMessage}
        </div>
    </c:if>
    <table class="table table-bordered mt-4 text-center" style="width: 100%">
        <thead class="table-primary">
            <tr>
                <th style="width: 10%">ID</th>
                <th style="width: 25%">그룹 이름</th>
                <th style="width: 20%">등록일자</th>
                <th style="width: 20%">수정일자</th>
                <th style="width: 5%">수정</th>
                <th style="width: 5%">삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="group" items="${groups.content}">
                <tr>
                    <td>${group.id}</td>
                    <td>${group.groupName}</td>
                    <td>${group.createdAt}</td>
                    <td>${group.modifiedAt}</td>
                    <td><a role="button" id="editGroupName" data-bs-toggle="modal" data-bs-target="#editBookmarkGroupModal"><i class="material-icons">&#xE254;</i></a></td>
                    <td><a class="delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="modal fade" id="editBookmarkGroupModal" tabindex="-1" aria-labelledby="editBookmarkGroupModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">즐겨찾기 그룹 수정</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row mb-3 justify-content-md-start ps-4 pe-4">
                        <input type="hidden" class="form-control" id="groupId" name="groupId">
                        <input type="text" class="form-control" id="groupName" name="groupName">
                    </div>
                    <div class="text-end pe-3">
                        <button type="button" class="btn btn-primary edit-request">수정</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="text-xs-center">
        <ul class="pagination justify-content-center">
            <!-- 이전 -->
            <c:choose>
                <c:when test="${groups.number <= 0}">
                    <li class="page-item"><a class="page-link disabled" href="/bookmark_group?page=${groups.number - 1}">Prev</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/bookmark_group?page=${groups.number - 1}">Prev</a></li>
                </c:otherwise>
            </c:choose>
            <!-- 페이지 그룹 -->
            <c:forEach var="n" items="${paginationBarNumbers}">
                <c:choose>
                    <c:when test="${groups.number == n}">
                        <li class="page-item"><a class="page-link disabled" href="/bookmark_group?page=${n}">${n}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/bookmark_group?page=${n}">${n}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <!-- 다음 -->
            <c:choose>
                <c:when test="${groups.totalPages <= 1 || groups.number >= groups.totalPages - 1}">
                    <li class="page-item "><a class="page-link disabled" href="/bookmark_group/?page=${groups.number + 1}">Next</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item "><a class="page-link" href="/bookmark_group/?page=${groups.number + 1}">Next</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="/resources/js/requestUpdateAll.js"></script>
<script src="/resources/js/deleteTableRow.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    $(document).on("click", "#editGroupName", function (){
        $('#groupId').val($(this).parents("tr").find("td").eq(0).text());
        $('#groupName').val($(this).parents("tr").find("td").eq(1).text())
    })
    // -- 그룹 수정
    $(document).on("click", ".edit-request", function(){
        var tr = $(this).parents("tr");
        $.ajax({
            url: "/bookmark_group/update/?groupId=" + $('#groupId').val() + "&groupName=" + $('#groupName').val(),
            type: "GET",
            beforeSend: function () {
                $('html').css('cursor', 'wait');
            },
            success: function (result) {
                $('html').css('cursor', 'auto');
               for (var tr of $('tbody').find('tr')) {
                    if ($(tr).find("td").eq(0).text() === $('#groupId').val()) {
                        $(tr).find("td").eq(1).text(result.result.groupName);
                        $(tr).find("td").eq(2).text(result.result.createdAt);
                        $(tr).find("td").eq(3).text(result.result.modifiedAt);
                    }
                }
                $('#editBookmarkGroupModal').modal('hide');
                console.log(result.resultCode);
                successAlert("수정을 완료했습니다.")
            },
            error: function (result) {
                $('html').css('cursor', 'auto');
                console.log(result.resultCode);
                failAlert("수정에 실패했습니다.")
            }
        });
    });
    // -- 그룹 삭제
    deleteTableRow("/bookmark_group/delete/", "/bookmark_group");
</script>
</html>
