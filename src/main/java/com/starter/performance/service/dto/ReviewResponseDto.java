package com.starter.performance.service.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDto {

    private String performanceName;
    private String title;
    private String writer;
    private String reviewStatus;
    private LocalDateTime writingDate;
}
