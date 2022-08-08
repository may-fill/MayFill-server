package server.mayfill.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import server.mayfill.domain.review.Review;

import java.util.List;

import static server.mayfill.domain.review.QReview.*;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<Review> findAllReviewByStoreId(Long storeId) {
        return query
                .selectFrom(review)
                .where(review.store.id.eq(storeId))
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    @Override
    public Review findByUserId(Long userId) {
        return query
                .selectFrom(review)
                .where(review.user.id.eq(userId))
                .fetchOne();
    }

}
