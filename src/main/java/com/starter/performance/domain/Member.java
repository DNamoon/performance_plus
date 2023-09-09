package com.starter.performance.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "registered_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime registeredDate;

    private LocalDateTime modifiedDate;

    private LocalDateTime withdrawalDate;

    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private Permission permission;

    private boolean emailAuth;

    private boolean sanctionWhether;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating", referencedColumnName = "id")
    private Rating rating;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Member(String email, String password, String phoneNumber, String nickname) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.permission = Permission.ROLE_MEMBER;
        this.rating = new Rating(1);
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
