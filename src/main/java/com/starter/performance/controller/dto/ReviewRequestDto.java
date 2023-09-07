package com.starter.performance.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewRequestDto {

    @NotBlank(message = "제목은 필수 항목입니다.")
    private String reviewTitle;
    @NotBlank(message = "내용은 필수 항목입니다.")
    private String reviewContent;

}
