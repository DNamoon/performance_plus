package com.starter.performance.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Builder
public class BookmarkResponseDto {
    private String performanceName;
    private LocalDateTime performanceDate;
}
