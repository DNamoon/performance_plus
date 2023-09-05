package com.starter.performance.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.starter.performance.domain.Performance;
import com.starter.performance.domain.PerformanceSchedule;
import com.starter.performance.domain.PerformanceStatus;
import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.repository.PerformanceScheduleRepository;
import com.starter.performance.service.dto.CreatePerformanceScheduleRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceScheduleResponseServiceDto;
import java.time.LocalDateTime;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceScheduleServiceImplTest {

    @Mock
    private PerformanceRepository performanceRepository;

    @Mock
    private PerformanceScheduleRepository performanceScheduleRepository;

    @InjectMocks
    private PerformanceScheduleServiceImpl performanceScheduleService;

    @Test
    @DisplayName("공연 일정 등록")
    public void createPerformanceSchedule_success() throws Exception {
        //given
        CreatePerformanceScheduleRequestServiceDto request = CreatePerformanceScheduleRequestServiceDto.builder()
            .performanceDate(LocalDateTime.of(2023, 05, 23, 10, 10))
            .ticketQuantity(10)
            .build();

        given(performanceRepository.findById(anyLong()))
            .willReturn(Optional.of(Performance.builder()
                .build()));

        given(performanceScheduleRepository.save(any(PerformanceSchedule.class)))
            .willReturn(PerformanceSchedule.builder()
                .id(1L)
                .build());

        //when
        CreatePerformanceScheduleResponseServiceDto response =
            performanceScheduleService.create(1L, request);

        //then
        Assertions.assertThat(response.getPerformanceScheduleId())
            .isEqualTo(1L);
    }

    @Test
    @DisplayName("공연일정 최초 등록시 공연일정 상태는 STAND_BY가 돼야 한다")
    public void performanceStatus_StandBy() throws Exception {
        //given
        CreatePerformanceScheduleRequestServiceDto request = CreatePerformanceScheduleRequestServiceDto.builder()
            .performanceDate(LocalDateTime.of(2023, 05, 23, 10, 10))
            .ticketQuantity(10)
            .build();
        ArgumentCaptor<PerformanceSchedule> captor = ArgumentCaptor.forClass(PerformanceSchedule.class);

        given(performanceRepository.findById(anyLong()))
            .willReturn(Optional.of(Performance.builder()
                .build()));

        given(performanceScheduleRepository.save(any(PerformanceSchedule.class)))
            .willReturn(PerformanceSchedule.builder()
                .id(1L)
                .build());

        //when
        performanceScheduleService.create(1L, request);

        //then
        verify(performanceScheduleRepository).save(captor.capture());
        Assertions.assertThat(captor.getValue().getPerformanceStatus())
            .isEqualTo(PerformanceStatus.STAND_BY);
    }

    @Test
    @DisplayName("❌ 공연 일정 등록시 공연이 존재하지 않으면 예외가 발생한다.")
    public void exception_createPerformanceSchedule_invalidPerformance() throws Exception {
        //given
        CreatePerformanceScheduleRequestServiceDto request = CreatePerformanceScheduleRequestServiceDto.builder()
            .performanceDate(LocalDateTime.of(2023, 05, 23, 10, 10))
            .ticketQuantity(10)
            .build();

        given(performanceRepository.findById(anyLong()))
            .willReturn(Optional.empty());

        //when //then
        assertThrows(IllegalArgumentException.class,
            () -> performanceScheduleService.create(1L, request));
    }
}