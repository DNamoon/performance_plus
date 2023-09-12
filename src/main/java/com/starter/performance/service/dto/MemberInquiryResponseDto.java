package com.starter.performance.service.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberInquiryResponseDto {
    private Long id;
    private String email;
    private String phoneNumber;
    private String nickname;
    private LocalDateTime registeredDate;
    private LocalDateTime withdrawalDate;
    private String permission;
    private boolean sanctionWhether;
    private String rating;
}
