package com.starter.performance.exception;

import lombok.Getter;

@Getter
public enum BookmarkErrorCode {
    MISINFORMATION_EXCEPTION, // 잘못된 정보
    ALREADY_REGISTERED_BOOKMARK_EXCEPTION, // 이미 등록된 북마크
    IdNotFoundException // id 를 찾을 수 없음
}
