package server.mayfill.service.review.dto.request;

import lombok.*;
import server.mayfill.domain.review.Review;
import server.mayfill.domain.store.Store;
import server.mayfill.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddReviewRequestDto {

    @NotNull(message = "${review.storeId.notNull}")
    private Long storeId;

    @NotBlank(message = "${review.comment.notBlank}")
    private String comments;

    public Review toEntity(Store store, User user) {
        return Review.of(store, user, comments);
    }

}
