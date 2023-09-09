package com.starter.performance.controller;

import com.starter.performance.controller.dto.BookmarkRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity<ResponseDto> createBookmark(@RequestBody BookmarkRequestDto bookmarkRequestDto, Authentication auth){
        ResponseDto responseDto = bookmarkService.createBookmark(bookmarkRequestDto, auth);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/bookmark")
    public ResponseEntity<ResponseDto> deleteBookmark(@RequestBody BookmarkRequestDto bookmarkRequestDto, Authentication auth){
        return ResponseEntity.ok(bookmarkService.deleteBookmark(bookmarkRequestDto, auth));
    }

    @GetMapping("/bookmark")
    public ResponseEntity<ResponseDto> bookmarkList(Authentication auth){
        String email = auth.getName();
        return ResponseEntity.ok(bookmarkService.bookmarkList(email));
    }
}
