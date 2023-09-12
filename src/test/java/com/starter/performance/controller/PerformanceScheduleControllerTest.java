package com.starter.performance.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.performance.TestSecurityConfig;
import com.starter.performance.controller.dto.CreatePerformanceScheduleRequestDto;
import com.starter.performance.exception.ClientErrorType;
import com.starter.performance.exception.CustomExceptionHandler;
import com.starter.performance.service.PerformanceScheduleService;
import com.starter.performance.service.dto.CreatePerformanceScheduleRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceScheduleResponseServiceDto;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PerformanceScheduleController.class)
@Import({TestSecurityConfig.class, CustomExceptionHandler.class})
class PerformanceScheduleControllerTest {

    @MockBean
    private PerformanceScheduleService performanceScheduleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("공연 일정 등록")
    public void createPerformanceSchedule_success() throws Exception {
        //given
        CreatePerformanceScheduleRequestDto request =
            CreatePerformanceScheduleRequestDto.builder()
                .performanceDate(LocalDateTime.now().plusSeconds(10))
                .ticketQuantity(10)
                .build();

        given(performanceScheduleService.create(anyLong(), any(CreatePerformanceScheduleRequestServiceDto.class)))
            .willReturn(CreatePerformanceScheduleResponseServiceDto.builder()
                .performanceScheduleId(1L)
                .build());

        String json = objectMapper.writeValueAsString(request);

        //when //then
        mockMvc.perform(post("/api/admin/performances/{performanceId}/schedules", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            ).andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.statusCode").value(201))
            .andExpect(jsonPath("$.body.performanceScheduleId").value(1));
    }

    @ParameterizedTest
    @MethodSource("requestProvider")
    @DisplayName("❌ 공연 일정 등록시 validation 을 통과하지 못하면 예외응답으로 응답한다")
    public void exception_createPerformanceSchedule_invalidRequest(
        CreatePerformanceScheduleRequestDto request)
        throws Exception {

        String json = objectMapper.writeValueAsString(request);

        given(performanceScheduleService.create(anyLong(), any(CreatePerformanceScheduleRequestServiceDto.class)))
            .willReturn(CreatePerformanceScheduleResponseServiceDto.builder()
                .performanceScheduleId(1L)
                .build());

        mockMvc.perform(post("/api/admin/performances/{performanceId}/schedules", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400))
            .andExpect(jsonPath("$.data.errorType").value(ClientErrorType.INVALID_REQUEST_EXCEPTION.name()));

    }

    @Test
    @DisplayName("❌ 공연 일정 등록시 공연이 존재하지 않으면 예외응답으로 응답한다")
    public void exception_createPerformanceSchedule_invalidPerformanceId()
        throws Exception {

        CreatePerformanceScheduleRequestDto request =
            CreatePerformanceScheduleRequestDto.builder()
                .performanceDate(LocalDateTime.now().plusSeconds(10))
                .ticketQuantity(10)
                .build();

        String json = objectMapper.writeValueAsString(request);

        doThrow(IllegalArgumentException.class).when(performanceScheduleService)
            .create(anyLong(), any(CreatePerformanceScheduleRequestServiceDto.class));

        mockMvc.perform(post("/api/admin/performances/{performanceId}/schedules", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400))
            .andExpect(jsonPath("$.data.errorType").value(ClientErrorType.INVALID_REQUEST_EXCEPTION.name()));

    }

    static Stream<CreatePerformanceScheduleRequestDto> requestProvider() {
        return Stream.of(
            CreatePerformanceScheduleRequestDto.builder()
                .ticketQuantity(10)
                .build(),
            CreatePerformanceScheduleRequestDto.builder()
                .performanceDate(LocalDateTime.of(2022, 05, 23, 0, 0, 0))
                .ticketQuantity(10)
                .build(),
            CreatePerformanceScheduleRequestDto.builder()
                .performanceDate(LocalDateTime.now().plusSeconds(10))
                .ticketQuantity(-1)
                .build(),
            CreatePerformanceScheduleRequestDto.builder()
                .performanceDate(LocalDateTime.now().plusSeconds(10))
                .ticketQuantity(0)
                .build()
        );
    }

}