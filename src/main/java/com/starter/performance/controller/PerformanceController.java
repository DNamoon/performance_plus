package com.starter.performance.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.starter.performance.client.FileClient;
import com.starter.performance.controller.dto.CreatePerformanceRequestDto;
import com.starter.performance.controller.dto.CreatePerformanceResponseDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.service.PerformanceService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class PerformanceController {

    private final FileClient fileClient;
    private final PerformanceService performanceService;

    @PostMapping("/performances")
    public ResponseEntity<ResponseDto> create(
        @Valid @RequestPart CreatePerformanceRequestDto request,
        @RequestPart(required = false) MultipartFile performanceImage) {

        String imageUrl = null;

        if (performanceImage != null && !performanceImage.isEmpty()) {
            imageUrl = fileClient.upload(performanceImage);
        }

        return ResponseEntity
            .status(CREATED.value())
            .body(
                ResponseDto.builder()
                    .statusCode(CREATED.value())
                    .body(CreatePerformanceResponseDto.of(
                        performanceService.create(request.toServiceDto(imageUrl))
                    )).build());
    }

}
