package com.starter.performance.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  private String phoneNumber;

  private String nickname;

  private LocalDateTime registeredDate;

  private LocalDateTime modifiedDate;

  private LocalDateTime withdrawalDate;

  private String permission;

  private boolean emailAuth;

  private boolean sanctionWhether;
}
