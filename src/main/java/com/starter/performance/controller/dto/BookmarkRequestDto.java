package com.starter.performance.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookmarkRequestDto {
    private Long performance;
    private String performanceName;
    private LocalDateTime performanceDate;
}
