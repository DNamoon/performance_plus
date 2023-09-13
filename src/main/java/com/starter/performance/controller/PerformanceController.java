package com.starter.performance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.starter.performance.client.FileClient;
import com.starter.performance.controller.dto.CreatePerformanceRequestDto;
import com.starter.performance.controller.dto.CreatePerformanceResponseDto;
import com.starter.performance.controller.dto.FindPerformanceResponseDto;
import com.starter.performance.controller.dto.PerformanceSearchConditionDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.UpdatePerformanceRequestDto;
import com.starter.performance.controller.dto.UpdatePerformanceResponseDto;
import com.starter.performance.service.PerformanceService;
import com.starter.performance.service.dto.FindPerformanceRequestServiceDto;
import com.starter.performance.service.dto.UpdatePerformanceResponseServiceDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

    private final FileClient fileClient;
    private final PerformanceService performanceService;

    @PostMapping("/admin/performances")
    public ResponseEntity<ResponseDto> create(
        @Valid @RequestPart CreatePerformanceRequestDto request,
        @RequestPart(required = false) MultipartFile performanceImage) {

        String imageUrl = null;

        if (performanceImage != null && !performanceImage.isEmpty()) {
            imageUrl = fileClient.upload(performanceImage);
        }

        return ResponseEntity
            .status(CREATED.value())
            .body(ResponseDto.builder()
                .statusCode(CREATED.value())
                .body(CreatePerformanceResponseDto.of(performanceService.create(request.toServiceDto(imageUrl)))
                ).build()
            );
    }

    @GetMapping("/performances/{performanceId}")
    public ResponseEntity<ResponseDto> findPerformance(@PathVariable Long performanceId) {
        return ResponseEntity
            .status(OK.value())
            .body(
                ResponseDto.builder()
                    .statusCode(OK.value())
                    .body(FindPerformanceResponseDto.of(
                        performanceService.findPerformance(FindPerformanceRequestServiceDto.builder()
                            .performanceId(performanceId)
                            .build())
                    )).build()
            );
    }

    @GetMapping("/performances")
    public ResponseEntity<ResponseDto> findPerformances(PerformanceSearchConditionDto conditionDto,
        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity
            .status(OK.value())
            .body(ResponseDto.builder()
                .statusCode(OK.value())
                .body(performanceService.findPerformances(conditionDto, pageable)
                    .map(FindPerformanceResponseDto::of))
                .build()
            );
    }

    @PatchMapping("/admin/performances/{performanceId}")
    public ResponseEntity<ResponseDto> updatePerformance(@PathVariable Long performanceId,
        @Valid @RequestPart UpdatePerformanceRequestDto request,
        @RequestPart(required = false) MultipartFile performanceImage) {

        String imageUrl = null;

        if (performanceImage != null && !performanceImage.isEmpty()) {
            imageUrl = fileClient.upload(performanceImage);
        }

        try {
            UpdatePerformanceResponseServiceDto serviceDto = performanceService.updatePerformance(
                request.toServiceDto(performanceId, imageUrl));

            fileClient.delete(serviceDto.getOldImageUrl());

            return ResponseEntity.
                status(OK.value())
                .body(ResponseDto.builder()
                    .statusCode(OK.value())
                    .body(UpdatePerformanceResponseDto.of(serviceDto))
                    .build());

        } catch (Exception exception) {
            fileClient.delete(imageUrl);
            throw exception;
        }
    }
}
