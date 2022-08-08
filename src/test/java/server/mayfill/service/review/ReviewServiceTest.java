package server.mayfill.service.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.mayfill.domain.review.Review;
import server.mayfill.domain.review.repository.ReviewRepository;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.service.review.dto.request.AddReviewRequestDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void 유저의_후기가_정상적으로_등록() {
        // given
        User testUser = User.newInstance("socialId", SocialType.KAKAO, "닉네임");
        User savedUser = userRepository.save(testUser);
        AddReviewRequestDto request = AddReviewRequestDto.testBuilder()
                .storeId(1L)
                .comments("아주 좋은 테스트 리필 스테이션 입니다")
                .build();

        // when
        reviewService.addReview(request, savedUser.getId());

        // then
        Review review = reviewRepository.findByUserId(savedUser.getId());
        assertAll(
        );
    }

}