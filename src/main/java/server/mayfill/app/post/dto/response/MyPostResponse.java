package server.mayfill.app.post.dto.response;

import lombok.*;
import server.mayfill.common.dto.AuditingTimeResponse;
import server.mayfill.domain.post.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyPostResponse extends AuditingTimeResponse {

    private String url;
    private String title;
    private String contents;

    public static MyPostResponse of(Post post) {
        MyPostResponse response = new MyPostResponse(post.getImageUrl(), post.getTitle(), post.getContents());
        response.setBaseTime(post);
        return response;
    }

}
