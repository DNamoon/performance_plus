package com.starter.performance.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity
@SQLDelete(sql = "UPDATE member SET withdrawal_date = CURRENT_TIMESTAMP WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"updatedDate"}, allowGetters = true)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String nickname;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime registeredDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    private LocalDateTime withdrawalDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Permission permission;

    @Column(nullable = false)
    private boolean emailAuth;

    @Column(nullable = false)
    private boolean sanctionWhether;

    @ManyToOne
    @JoinColumn(name = "rating")
    private Rating rating;

    @Builder
    public Member(String email, String password, String phoneNumber,
        String nickname, Permission permission) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.permission = Permission.MEMBER;
    }
}
