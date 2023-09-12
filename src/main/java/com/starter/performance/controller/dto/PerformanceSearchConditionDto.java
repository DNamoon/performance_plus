package com.starter.performance.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class PerformanceSearchConditionDto {

    private String performanceName;
    private String artistName;

}
