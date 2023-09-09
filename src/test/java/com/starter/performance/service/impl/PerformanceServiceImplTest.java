package com.starter.performance.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.starter.performance.domain.Performance;
import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceServiceImplTest {

    @Mock
    private PerformanceRepository performanceRepository;
    @InjectMocks
    private PerformanceServiceImpl performanceService;

    @Test
    @DisplayName("공연등록")
    public void createPerformance_success() throws Exception {
        //given
        CreatePerformanceRequestServiceDto serviceDto = CreatePerformanceRequestServiceDto.builder()
            .performanceName("공연이름")
            .venue("공연장소")
            .detail("공연 상세설명")
            .imageUrl("이미지 URL")
            .build();

        given(performanceRepository.save(any(Performance.class)))
            .willReturn(Performance.builder()
                .id(1L)
                .build());

        //when
        CreatePerformanceResponseServiceDto response = performanceService.create(serviceDto);

        //then
        Assertions.assertThat(response.getPerformanceId())
            .isEqualTo(1L);

    }
}