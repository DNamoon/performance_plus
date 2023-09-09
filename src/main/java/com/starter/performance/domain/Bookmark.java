package com.starter.performance.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member",  referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "performance",  referencedColumnName = "id")
    private Performance performance;

    private String performanceName;

    private LocalDateTime performanceDate;
}