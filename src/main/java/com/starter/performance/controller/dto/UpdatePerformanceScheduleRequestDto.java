package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.UpdatePerformanceScheduleRequestServiceDto;
import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePerformanceScheduleRequestDto {

    @NotNull
    @Future
    private LocalDateTime performanceDate;
    @Positive
    @Min(1)
    private int ticketQuantity;

    @Builder
    private UpdatePerformanceScheduleRequestDto(LocalDateTime performanceDate, int ticketQuantity) {
        this.performanceDate = performanceDate;
        this.ticketQuantity = ticketQuantity;
    }

    public UpdatePerformanceScheduleRequestServiceDto toServiceDto(Long performanceScheduleId) {
        return UpdatePerformanceScheduleRequestServiceDto.builder()
            .performanceScheduleId(performanceScheduleId)
            .performanceDate(this.performanceDate)
            .ticketQuantity(this.ticketQuantity)
            .build();

    }
}

