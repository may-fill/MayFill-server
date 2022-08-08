package server.mayfill.domain.review.repository;

import server.mayfill.domain.review.Review;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findAllReviewByStoreId(Long storeId);
    Review findByUserId(Long userId);

}
