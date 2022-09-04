package server.mayfill.app.review;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.app.review.dto.request.AddReviewRequest;
import server.mayfill.app.review.dto.response.ReviewResponse;
import server.mayfill.app.store.service.StoreServiceUtils;
import server.mayfill.app.user.dto.request.CreateUserDto;
import server.mayfill.app.user.service.UserServiceUtils;
import server.mayfill.common.exception.custom.NotFoundException;
import server.mayfill.domain.post.repository.PostRepository;
import server.mayfill.domain.review.Review;
import server.mayfill.domain.review.repository.ReviewRepository;
import server.mayfill.domain.store.repository.StoreRepository;
import server.mayfill.domain.user.entity.User;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private StoreRepository storeRepository;

    @AfterEach
    void cleanUp() {
        postRepository.deleteAllInBatch();
        reviewRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    void 리필스테이션_리뷰_작성_성공() {
        // given
        CreateUserDto createUserDto = CreateUserDto.of("채울지도", "SocialId", SocialType.KAKAO);
        User user = userRepository.save(User.newInstance(createUserDto));

        AddReviewRequest request = AddReviewRequest.testBuilder()
                .storeId(1L)
                .comments("1번 리필스테이션에 대한 리뷰입니다")
                .build();

        // when
        reviewService.addReview(request, user.getId());

        // then
        Review review = reviewRepository.findOneReviewByStoreIdAndUserIdForTest(request.getStoreId(), user.getId());
        assertThat(review).isNotNull();
    }

    @Test
    void 존재하지_않는_리필스테이션에_리뷰_작성_실패() {
        CreateUserDto createUserDto = CreateUserDto.of("채울지도", "SocialId", SocialType.KAKAO);
        User user = userRepository.save(User.newInstance(createUserDto));
        Long notExistStoreId = 0L;
        AddReviewRequest request = AddReviewRequest.testBuilder()
                .storeId(notExistStoreId)
                .comments("존재하지 않는 리필스테이션에 대한 리뷰입니다")
                .build();

        assertThrows(NotFoundException.class, () -> reviewService.addReview(request, user.getId()));
    }

    @Test
    void 리필스테이션의_모든_리뷰_조회_성공() {
        // given
        CreateUserDto firstCreateUserDto = CreateUserDto.of("첫번째", "SocialId", SocialType.KAKAO);
        User firstUser = userRepository.save(User.newInstance(firstCreateUserDto));
        CreateUserDto secondCreateUserDto = CreateUserDto.of("두번째", "SocialId", SocialType.APPLE);
        User secondUser = userRepository.save(User.newInstance(secondCreateUserDto));

        Long existStoreId = 1L;
        Review firstReview = Review.of(
                StoreServiceUtils.findByStoreId(storeRepository, existStoreId),
                UserServiceUtils.findUserById(userRepository, firstUser.getId()),
                "첫번째 유저의 리뷰입니다"
        );
        Review secondReview = Review.of(
                StoreServiceUtils.findByStoreId(storeRepository, existStoreId),
                UserServiceUtils.findUserById(userRepository, secondUser.getId()),
                "두번째 유저의 리뷰입니다"
        );
        List<Review> reviews = List.of(firstReview, secondReview);
        reviewRepository.saveAll(reviews);

        // when
        List<ReviewResponse> response = reviewService.retrieveReview(existStoreId);

        // then
        List<Review> storeReviews = reviewRepository.findAllReviewByStoreId(existStoreId);
        for (int index = 0; index < response.size(); index++) {
            ReviewResponse reviewResponse = response.get(index);
            Review find = storeReviews.get(index);
            assertAll(
                    () -> assertThat(reviewResponse).isNotNull(),
                    () -> assertThat(find).isNotNull(),
                    () -> assertEquals(reviewResponse.getReviewId(), find.getId()),
                    () -> assertEquals(reviewResponse.getNickname(), find.getUser().getNickname()),
                    () -> assertEquals(reviewResponse.getComments(), find.getComments())
            );
        }

    }

    @Test
    void 리필스테이션의_모든_리뷰_조회_실패() {
        Long notExistStoreId = 0L;
        assertThrows(NotFoundException.class, () -> reviewService.retrieveReview(notExistStoreId));
    }

}
