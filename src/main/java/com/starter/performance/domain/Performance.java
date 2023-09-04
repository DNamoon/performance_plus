package com.starter.performance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String venue;
    private String detail;
    private String imageUrl;

    @Builder
    private Performance(Long id, String name, String venue, String detail, String imageUrl) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.detail = detail;
        this.imageUrl = imageUrl;
    }
}
