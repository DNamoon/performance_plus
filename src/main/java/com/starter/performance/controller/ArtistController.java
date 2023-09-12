package com.starter.performance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.starter.performance.controller.dto.CreateArtistRequestDto;
import com.starter.performance.controller.dto.CreateArtistResponseDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.UpdateArtistRequestDto;
import com.starter.performance.controller.dto.UpdateArtistResponseDto;
import com.starter.performance.service.ArtistService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PatchMapping("/{performanceId}/artists/{artistId}")
    public ResponseEntity<ResponseDto> updateArtist(@PathVariable Long performanceId,
        @PathVariable Long artistId,
        @Valid @RequestBody UpdateArtistRequestDto request) {

        return ResponseEntity
            .status(OK.value())
            .body(
                ResponseDto.builder()
                    .statusCode(OK.value())
                    .body(UpdateArtistResponseDto.
                        of(artistService.updateArtist(request.toServiceDto(performanceId, artistId))))
                    .build()
            );
    }
}
