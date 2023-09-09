package com.starter.performance.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.starter.performance.controller.dto.CreatePerformanceScheduleRequestDto;
import com.starter.performance.controller.dto.CreatePerformanceScheduleResponseDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.service.PerformanceScheduleService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/performances")
@RequiredArgsConstructor
public class PerformanceScheduleController {

    private final PerformanceScheduleService performanceScheduleService;

    @PostMapping("/{performanceId}/schedules")
    public ResponseEntity<ResponseDto> create(@PathVariable Long performanceId,
        @Valid @RequestBody CreatePerformanceScheduleRequestDto request) {

        return ResponseEntity
            .status(CREATED.value())
            .body(ResponseDto.builder()
                .statusCode(CREATED.value())
                .body(CreatePerformanceScheduleResponseDto
                    .of(performanceScheduleService.create(performanceId, request.toServiceDto())))
                .build()
            );
    }
}
