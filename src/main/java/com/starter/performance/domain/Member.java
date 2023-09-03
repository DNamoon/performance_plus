package com.starter.performance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE member SET withdrawal_date = CURRENT_TIMESTAMP WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"updatedDate"}, allowGetters=true)
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  private String phoneNumber;

  @NotNull
  private String nickname;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime registeredDate;

  @LastModifiedDate
  private LocalDateTime modifiedDate;

  private LocalDateTime withdrawalDate;

  @NotNull
  private String permission;

  @NotNull
  private boolean emailAuth;

  @NotNull
  private boolean sanctionWhether;

  @ManyToOne
  @JoinColumn(name = "rating")
  private Rating rating;
}
