package com.starter.performance.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.performance.TestSecurityConfig;
import com.starter.performance.client.FileClient;
import com.starter.performance.controller.dto.CreatePerformanceRequestDto;
import com.starter.performance.exception.ClientErrorType;
import com.starter.performance.exception.CustomExceptionHandler;
import com.starter.performance.service.PerformanceService;
import com.starter.performance.service.dto.CreatePerformanceRequestServiceDto;
import com.starter.performance.service.dto.CreatePerformanceResponseServiceDto;
import java.nio.charset.StandardCharsets;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

@WebMvcTest(controllers = PerformanceController.class)
@Import({TestSecurityConfig.class, CustomExceptionHandler.class})
class PerformanceControllerTest {

    @MockBean
    private PerformanceService performanceService;
    @MockBean
    private FileClient fileClient;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("공연 등록")
    public void createPerformance_success() throws Exception {
        //given
        CreatePerformanceRequestDto request = CreatePerformanceRequestDto.builder()
            .performanceName("공연 이름")
            .venue("공연 장소")
            .detail("상세 설명")
            .build();

        String json = objectMapper.writeValueAsString(request);

        given(performanceService.create(any(CreatePerformanceRequestServiceDto.class)))
            .willReturn(CreatePerformanceResponseServiceDto.builder()
                .performanceId(1L)
                .build());

        given(fileClient.upload(any(MultipartFile.class)))
            .willReturn("savedImageUrl");

        MockMultipartFile performanceImage =
            new MockMultipartFile("performanceImage", "test.jpg",
                MediaType.IMAGE_JPEG_VALUE, "image content".getBytes());

        MockMultipartFile jsonMultipart = new MockMultipartFile("request", "json",
            MediaType.APPLICATION_JSON_VALUE, json.getBytes(
            StandardCharsets.UTF_8));

        //when //then
        mockMvc.perform(multipart(HttpMethod.POST, "/admin/performances")
                .file(performanceImage)
                .file(jsonMultipart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
            ).andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.statusCode").value(201))
            .andExpect(jsonPath("$.body.performanceId").value(1L));
    }


    @ParameterizedTest
    @MethodSource("requestProvider")
    @DisplayName("❌ 공연 등록시 validation 을 통과하지 못하면 예외응답으로 응답한다")
    public void exception_createPerformance_invalidRequest(CreatePerformanceRequestDto request) throws Exception {

        String json = objectMapper.writeValueAsString(request);

        given(performanceService.create(any(CreatePerformanceRequestServiceDto.class)))
            .willReturn(CreatePerformanceResponseServiceDto.builder()
                .performanceId(1L)
                .build());

        given(fileClient.upload(any(MultipartFile.class)))
            .willReturn("savedImageUrl");

        MockMultipartFile jsonMultipart = new MockMultipartFile("request", "json",
            MediaType.APPLICATION_JSON_VALUE, json.getBytes(
            StandardCharsets.UTF_8));

        mockMvc.perform(multipart(HttpMethod.POST, "/admin/performances")
                .file(jsonMultipart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
            ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400))
            .andExpect(jsonPath("$.data.errorType").value(ClientErrorType.INVALID_REQUEST_EXCEPTION.name()));

    }

    static Stream<CreatePerformanceRequestDto> requestProvider() {

        StringBuilder twoHundredLengthText = new StringBuilder();
        IntStream.range(0, 201).forEach(value -> twoHundredLengthText.append("-"));

        StringBuilder thousandLengthText = new StringBuilder();
        IntStream.range(0, 1001).forEach(value -> thousandLengthText.append("-"));

        return Stream.of(CreatePerformanceRequestDto.builder()
                .venue("공연 장소")
                .detail("상세 설명")
                .build(),

            CreatePerformanceRequestDto.builder()
                .performanceName("공연명")
                .detail("상세 설명")
                .build(),

            CreatePerformanceRequestDto.builder()
                .performanceName(twoHundredLengthText.toString())
                .venue("공연 장소")
                .detail("상세 설명")
                .build(),

            CreatePerformanceRequestDto.builder()
                .performanceName("공연 ")
                .venue(twoHundredLengthText.toString())
                .detail("상세 설명")
                .build(),

            CreatePerformanceRequestDto.builder()
                .performanceName("공연명")
                .venue("공연 장소")
                .detail(thousandLengthText.toString())
                .build()
        );
    }
}
