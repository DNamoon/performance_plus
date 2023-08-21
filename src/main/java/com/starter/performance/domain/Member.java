package com.starter.performance.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE member SET withdrawal_date = LocalDateTime.now() WHERE member_id = ?")
@FilterDef(name = "deletedAccountFilter", parameters = @ParamDef(name = "withdrawalDate", type = "timestamp"))
@Filter(name = "deletedAccountFilter", condition = "withdrawalDate = null")
@Entity
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  private String email;

  private String password;

  private String phoneNumber;

  private String nickname;

  @CreatedDate
  @Column(updatable = false,nullable = false)
  private LocalDateTime registeredDate;

  @LastModifiedDate
  private LocalDateTime modifiedDate;

  private LocalDateTime withdrawalDate;

  private String permission;

  private boolean emailAuth;

  private boolean sanctionWhether;
}
