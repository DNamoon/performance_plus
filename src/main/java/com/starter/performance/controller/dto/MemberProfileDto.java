package com.starter.performance.controller.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileDto {
  private Long memberId;

  private String password;

  private String phoneNumber;

  private String nickname;

  private LocalDateTime modifiedDate;

  /*
  public static MemberProfileDto of(Member member) {

  }
  */

}
