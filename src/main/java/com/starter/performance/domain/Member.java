package com.starter.performance.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE member SET withdrawal_date = LocalDateTime.now() WHERE member_id = ?")
@Where(clause = "withdrawal_date=null")
@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

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
