package com.starter.performance.repository.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.starter.performance.controller.dto.PerformanceSearchConditionDto;
import com.starter.performance.domain.Performance;
import com.starter.performance.domain.QArtist;
import com.starter.performance.domain.QPerformance;
import com.starter.performance.repository.PerformanceRepositoryQueryDsl;
import com.starter.performance.repository.QueryDslRepositorySupport;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

public class PerformanceRepositoryQueryDslImpl extends QueryDslRepositorySupport implements
    PerformanceRepositoryQueryDsl {

    public PerformanceRepositoryQueryDslImpl() {
        super(Performance.class);
    }

    @Override
    public Slice<Performance> findAllByConditions(PerformanceSearchConditionDto performanceSearchConditionDto,
        Pageable pageable) {

        List<Performance> performances = selectFrom(QPerformance.performance)
            .distinct()
            .leftJoin(QPerformance.performance.artists, QArtist.artist)
            .where(
                performanceNameContains(performanceSearchConditionDto.getPerformanceName()),
                artistNameContains(performanceSearchConditionDto.getArtistName())
            ).offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .orderBy(orderBy(pageable))
            .fetch();

        if (performances.size() <= pageable.getPageSize()) {
            return new SliceImpl<>(performances, pageable, false);
        }

        return new SliceImpl<>(performances, pageable, true);
    }

    private BooleanExpression artistNameContains(String artistName) {
        return artistName == null ? null : QArtist.artist.name.contains(artistName);
    }

    private BooleanExpression performanceNameContains(String performanceName) {
        return performanceName == null ? null : QPerformance.performance.name.contains(performanceName);
    }

    private OrderSpecifier<?> orderBy(Pageable pageable) {
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder<Performance> orderByExpression = new PathBuilder<>(Performance.class, "performance");
            return new OrderSpecifier(order.isAscending() ? Order.ASC : Order.DESC, orderByExpression.get(order.getProperty()));
        }
        return null;
    }

}
