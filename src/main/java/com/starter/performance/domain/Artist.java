package com.starter.performance.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance")
    private Performance performance;

    @Builder
    private Artist(Long id, String name, Performance performance) {
        this.id = id;
        this.name = name;
        this.performance = performance;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateArtist(String name){
        this.updateName(name);
    }
}
