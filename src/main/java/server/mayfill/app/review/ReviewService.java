package server.mayfill.app.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.domain.review.repository.ReviewRepository;
import server.mayfill.domain.store.repository.StoreRepository;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.app.store.service.StoreServiceUtils;
import server.mayfill.app.review.dto.request.AddReviewRequest;
import server.mayfill.app.review.dto.response.ReviewResponse;
import server.mayfill.app.user.service.UserServiceUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addReview(AddReviewRequest request, Long userId) {
        StoreServiceUtils.validateNotExistsStore(storeRepository, request.getStoreId());
        reviewRepository.save(request.toEntity(
                storeRepository.findStoreByStoreId(request.getStoreId()),
                UserServiceUtils.findUserById(userRepository, userId))
        );
    }

    public List<ReviewResponse> retrieveReview(Long storeId) {
        StoreServiceUtils.validateNotExistsStore(storeRepository, storeId);
        return reviewRepository.findAllReviewByStoreId(storeId)
                .stream()
                .map(ReviewResponse::of)
                .collect(Collectors.toList());
    }

}
