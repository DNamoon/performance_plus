package com.starter.performance.service;

import com.starter.performance.controller.dto.BookmarkRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import org.springframework.security.core.Authentication;

public interface BookmarkService {

    ResponseDto createBookmark(BookmarkRequestDto bookmarkRequest, Authentication auth);
    ResponseDto deleteBookmark(BookmarkRequestDto bookmarkRequest, Authentication auth);
}
