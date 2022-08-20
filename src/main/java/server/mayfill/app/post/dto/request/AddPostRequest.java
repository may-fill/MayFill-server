package server.mayfill.controller.post.dto.request;

import lombok.*;
import server.mayfill.domain.post.Post;
import server.mayfill.domain.user.User;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddPostRequest {

    @NotBlank(message = "{post.title.notBlank}")
    private String title;

    @NotBlank(message = "{post.contents.notBlank}")
    private String comments;

    public Post toPostEntity(User user, String imageUrl) {
        return Post.of(user, imageUrl, title, comments);
    }

}
