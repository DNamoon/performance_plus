package com.starter.performance.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.starter.performance.controller.dto.CreatePerformanceScheduleRequestDto;
import com.starter.performance.controller.dto.CreatePerformanceScheduleResponseDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.controller.dto.UpdatePerformanceScheduleRequestDto;
import com.starter.performance.controller.dto.UpdatePerformanceScheduleResponseDto;
import com.starter.performance.service.PerformanceScheduleService;
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
public class PerformanceScheduleController {

    private final PerformanceScheduleService performanceScheduleService;

    @PostMapping("/admin/performances/{performanceId}/schedules")
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

    @PatchMapping("/admin/performances/schedules/{performanceScheduleId}")
    public ResponseEntity<ResponseDto> updatePerformanceSchedule(@PathVariable Long performanceScheduleId,
        @Valid @RequestBody UpdatePerformanceScheduleRequestDto request) {

        return ResponseEntity
            .status(OK.value())
            .body(ResponseDto.builder()
                .statusCode(OK.value())
                .body(
                    UpdatePerformanceScheduleResponseDto.of(
                        performanceScheduleService.updatePerformanceSchedule(
                            request.toServiceDto(performanceScheduleId))
                    )).build()
            );
    }
}

