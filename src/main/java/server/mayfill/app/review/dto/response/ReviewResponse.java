package server.mayfill.service.review.dto.response;

import lombok.*;
import server.mayfill.common.dto.AuditingTimeResponse;
import server.mayfill.domain.review.Review;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewResponse extends AuditingTimeResponse {

    private Long reviewId;
    private String nickname;
    private String comments;

    @Builder(access = AccessLevel.PACKAGE)
    public ReviewResponse(String nickname, Long reviewId, String comments) {
        this.nickname = nickname;
        this.reviewId = reviewId;
        this.comments = comments;
    }

    public static ReviewResponse of(Review review) {
        ReviewResponse response = ReviewResponse.builder()
                .reviewId(review.getId())
                .nickname(review.getUser().getNickname())
                .comments(review.getComments())
                .build();
        response.setBaseTime(review);
        return response;
    }

}

