package com.starter.performance.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class BookmarkRequestDto {
    private Long performanceId;
    private String performanceName;
    private LocalDateTime performanceDate;
}
