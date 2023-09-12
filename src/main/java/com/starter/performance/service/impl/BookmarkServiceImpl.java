package com.starter.performance.service.impl;

import com.starter.performance.controller.dto.BookmarkRequestDto;
import com.starter.performance.controller.dto.ResponseDto;
import com.starter.performance.domain.Bookmark;
import com.starter.performance.domain.Member;
import com.starter.performance.domain.Performance;
import com.starter.performance.domain.PerformanceSchedule;
import com.starter.performance.exception.impl.AlreadyRegisteredBookmarkException;
import com.starter.performance.exception.impl.IdNotFoundException;
import com.starter.performance.exception.impl.MisinformationException;
import com.starter.performance.repository.BookmarkRepository;
import com.starter.performance.repository.MemberRepository;
import com.starter.performance.repository.PerformanceRepository;
import com.starter.performance.repository.PerformanceScheduleRepository;
import com.starter.performance.service.BookmarkService;
import com.starter.performance.service.dto.BookmarkResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final PerformanceRepository performanceRepository;
    private final PerformanceScheduleRepository performanceScheduleRepository;

    @Override
    public ResponseDto createBookmark(BookmarkRequestDto bookmarkRequest, Authentication auth) {

        String email = auth.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MisinformationException());

        Performance performance =
            performanceRepository.findByIdAndName(bookmarkRequest.getPerformanceId(), bookmarkRequest.getPerformanceName())
                .orElseThrow(() -> new MisinformationException());

        PerformanceSchedule performanceSchedule =
            performanceScheduleRepository.findByPerformance(performance)
                .orElseThrow(() -> new MisinformationException());

        if (!bookmarkRequest.getPerformanceDate().equals(performanceSchedule.getPerformanceDate())) {
            throw new MisinformationException();
        }


        if (bookmarkRepository.existsByMemberIdAndPerformanceId(member.getId(), performance.getId())) {
            throw new AlreadyRegisteredBookmarkException();
        }

        Bookmark bookmark = Bookmark.builder()
            .member(member)
            .performance(performance)
            .performanceName(performance.getName())
            .performanceDate(performanceSchedule.getPerformanceDate())
            .build();

        bookmarkRepository.save(bookmark);

        return ResponseDto.builder()
            .message(bookmark.getPerformanceName() + "(이)가 북마크 되었습니다.")
            .statusCode(HttpStatus.OK.value())
            .body(BookmarkResponseDto.builder()
                .performanceDate(bookmark.getPerformanceDate())
                .performanceName(bookmark.getPerformanceName()).build())
            .build();
    }

    @Override
    public ResponseDto deleteBookmark(BookmarkRequestDto bookmarkRequest, Authentication auth) {

        String email = auth.getName();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new MisinformationException());

        bookmarkRepository.deleteByPerformanceIdAndMember(bookmarkRequest.getPerformanceId(), member);

        return ResponseDto.builder()
            .message("북마크가 취소 되었습니다.")
            .statusCode(HttpStatus.OK.value())
            .build();
    }

    @Override
    public ResponseDto bookmarkList(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MisinformationException::new);
        List<Bookmark> bookmarkList = bookmarkRepository.findAllByMember(member);

        List<BookmarkResponseDto> responseDtoList = new ArrayList<>();
        for (Bookmark bookmark : bookmarkList){
            Performance performance =
                performanceRepository.findById(bookmark.getPerformance().getId())
                    .orElseThrow(IdNotFoundException::new);
            PerformanceSchedule performanceSchedule =
                performanceScheduleRepository.findByPerformance(performance)
                    .orElseThrow(IdNotFoundException::new);

            BookmarkResponseDto dto = new BookmarkResponseDto(
                performance.getName(),
                performanceSchedule.getPerformanceDate()
            );
            responseDtoList.add(dto);
        }

        return ResponseDto.builder()
            .message(email + " 님의 북마크 목록 입니다.")
            .statusCode(HttpStatus.OK.value())
            .body(responseDtoList.isEmpty() ? null : responseDtoList)
            .build();
    }
}
