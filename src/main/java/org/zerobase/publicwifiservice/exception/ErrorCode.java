package org.zerobase.publicwifiservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //Public WIFI
    PUBLIC_WIFI_CANT_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "와이파이 정보 업데이트에 실패했습니다."),
    PUBLIC_WIFI_NOT_FOUND_NEAREST(HttpStatus.NOT_FOUND, "가까운 와이파이 정보를 찾을 수 없습니다."),
    PUBLIC_WIFI_NOT_FOUND(HttpStatus.NOT_FOUND, "와이파이 정보를 찾을 수 없습니다."),
    //Public WIFI Log
    PUBLIC_WIFI_LOG_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "와이파이 검색 기록 저장에 실패했습니다."),
    PUBLIC_WIFI_LOG_SAVE_CONFLICT(HttpStatus.CONFLICT, "와이파이 검색 기록 저장 중 Optimistic Lock 충돌이 일어났습니다."),
    PUBLIC_WIFI_LOG_CANT_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "와이파이 검색 기록 삭제에 실패했습니다."),
    //Bookmark Group
    BOOKMARK_GROUP_SAVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "즐겨찾기 그룹 저장에 실패했습니다."),
    BOOKMARK_GROUP_SAVE_CONFLICT(HttpStatus.CONFLICT, "즐겨찾기 그룹 저장 중 Optimistic Lock 충돌이 일어났습니다."),
    BOOKMARK_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "즐겨찾기 그룹을 찾을 수 없습니다."),
    BOOKMARK_GROUP_CANT_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "즐겨찾기 그룹 삭제에 실패했습니다."),
    ;

    private HttpStatus status;
    private String message;
}
