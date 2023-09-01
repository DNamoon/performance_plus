package com.starter.performance.domain;

import lombok.*;

import javax.persistence.*;
@ToString
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String venue;

    @Column
    private String detail;

    @Column
    private String imageUrl;
}
