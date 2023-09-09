package com.starter.performance.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.performance.SecurityConfig;
import com.starter.performance.controller.dto.CreateArtistRequestDto;
import com.starter.performance.exception.ClientErrorType;
import com.starter.performance.exception.CustomExceptionHandler;
import com.starter.performance.service.ArtistService;
import com.starter.performance.service.dto.CreateArtistRequestServiceDto;
import com.starter.performance.service.dto.CreateArtistResponseServiceDto;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArtistController.class)
@Import({SecurityConfig.class, CustomExceptionHandler.class})
public class ArtistControllerTest {

    @MockBean
    private ArtistService artistService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("아티스트 등록")
    public void createArtist_success() throws Exception {
        //given
        long performanceId = 1L;
        long artistId = 1L;

        given(artistService.create(anyLong(), any(CreateArtistRequestServiceDto.class)))
            .willReturn(CreateArtistResponseServiceDto.builder()
                .artistId(artistId)
                .build());

        CreateArtistRequestDto request = CreateArtistRequestDto.builder()
            .artistName("아티스트 이름")
            .build();

        String json = objectMapper.writeValueAsString(request);

        //when //then
        mockMvc.perform(post("/api/admin/performances/{performanceId}/artists", performanceId)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.statusCode").value(CREATED.value()))
            .andExpect(jsonPath("$.body.artistId").value(artistId));
    }

    @ParameterizedTest
    @MethodSource("requestProvider")
    @DisplayName("❌ 아티스트 등록시 validation 을 통과하지 못하면 예외응답으로 응답한다")
    public void exception_createArtist_invalidRequest(
        CreateArtistRequestDto request)
        throws Exception {
        long performanceId = 1L;
        long artistId = 1L;

        String json = objectMapper.writeValueAsString(request);

        given(artistService.create(anyLong(), any(CreateArtistRequestServiceDto.class)))
            .willReturn(CreateArtistResponseServiceDto.builder()
                .artistId(artistId)
                .build());

        mockMvc.perform(post("/api/admin/performances/{performanceId}/artists", performanceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400))
            .andExpect(jsonPath("$.data.errorType").value(ClientErrorType.INVALID_REQUEST_EXCEPTION.name()));

    }

    @Test
    @DisplayName("❌ 아티스트 등록시 공연이 존재하지 않으면 예외응답으로 응답한다")
    public void exception_createArtist_invalidPerformanceId()
        throws Exception {

        CreateArtistRequestDto request = CreateArtistRequestDto.builder()
            .artistName("아티스트 이름")
            .build();

        String json = objectMapper.writeValueAsString(request);

        doThrow(IllegalArgumentException.class).when(artistService)
            .create(anyLong(), any(CreateArtistRequestServiceDto.class));

        mockMvc.perform(post("/api/admin/performances/{performanceId}/artists", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
            ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.statusCode").value(400))
            .andExpect(jsonPath("$.data.errorType").value(ClientErrorType.INVALID_REQUEST_EXCEPTION.name()));

    }

    static Stream<CreateArtistRequestDto> requestProvider() {
        StringBuilder fiftyLengthText = new StringBuilder();
        IntStream.range(0, 51).forEach(value -> fiftyLengthText.append("-"));

        return Stream.of(
            CreateArtistRequestDto.builder()
                .build(),

            CreateArtistRequestDto.builder()
                .artistName(fiftyLengthText.toString())
                .build()
        );
    }
}
