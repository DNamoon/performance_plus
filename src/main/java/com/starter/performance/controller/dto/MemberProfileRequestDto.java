package com.starter.performance.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberProfileRequestDto {

  @NotNull
  private Long memberId;

  @NotNull
  private String password;

  @NotNull
  private String phoneNumber;

  @NotNull
  private String nickname;

  @Builder
  public MemberProfileRequestDto(Long memberId, String password, String phoneNumber, String nickname) {
    this.memberId = memberId;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.nickname = nickname;
  }
}
