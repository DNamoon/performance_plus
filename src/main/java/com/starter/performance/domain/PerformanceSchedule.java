package com.starter.performance.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@ToString
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceScheduleId;

    @ManyToOne
    @JoinColumn(name = "id",  referencedColumnName = "id")
    private Performance performanceId;

    private LocalDateTime performanceDate;

    private Integer ticket_quantity;

    private String performanceStatus;
}
