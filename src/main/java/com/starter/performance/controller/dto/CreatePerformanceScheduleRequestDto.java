package com.starter.performance.controller.dto;

import com.starter.performance.service.dto.CreatePerformanceScheduleRequestServiceDto;
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
public class CreatePerformanceScheduleRequestDto {

    @NotNull
    @Future
    private LocalDateTime performanceDate;
    @Positive
    @Min(1)
    private int ticketQuantity;

    public CreatePerformanceScheduleRequestServiceDto toServiceDto() {
        return CreatePerformanceScheduleRequestServiceDto.builder()
            .performanceDate(performanceDate)
            .ticketQuantity(ticketQuantity)
            .build();
    }

    @Builder
    private CreatePerformanceScheduleRequestDto(LocalDateTime performanceDate, int ticketQuantity) {
        this.performanceDate = performanceDate;
        this.ticketQuantity = ticketQuantity;
    }


}
