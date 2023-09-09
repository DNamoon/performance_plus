package com.starter.performance.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.starter.performance.controller.dto.CreateArtistRequestDto;
import com.starter.performance.controller.dto.CreateArtistResponseDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.service.ArtistService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/performances")
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping("/{performanceId}/artists")
    public ResponseEntity<ResponseDto> create(@PathVariable Long performanceId,
        @Valid @RequestBody CreateArtistRequestDto request) {

        return ResponseEntity
            .status(CREATED.value())
            .body(ResponseDto.builder()
                .statusCode(CREATED.value())
                .body(CreateArtistResponseDto.of(artistService.create(performanceId, request.toServiceDto())))
                .build()
            );
    }
}
