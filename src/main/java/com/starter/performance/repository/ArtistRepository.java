package com.starter.performance.repository;

import com.starter.performance.domain.Artist;
import com.starter.performance.domain.Performance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findAllByPerformance(Performance performance);
}
