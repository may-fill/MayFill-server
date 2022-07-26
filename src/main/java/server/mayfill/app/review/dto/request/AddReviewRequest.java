package server.mayfill.app.review.dto.request;

import lombok.*;
import server.mayfill.domain.review.Review;
import server.mayfill.domain.store.entity.Store;
import server.mayfill.domain.user.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddReviewRequest {

    @NotNull(message = "${review.storeId.notNull}")
    private Long storeId;

    @NotBlank(message = "${review.comment.notBlank}")
    private String comments;

    @Builder(builderMethodName = "testBuilder", access = AccessLevel.PUBLIC)
    private AddReviewRequest(Long storeId, String comments) {
        this.storeId = storeId;
        this.comments = comments;
    }

    public Review toEntity(Store store, User user) {
        return Review.of(store, user, comments);
    }

}
