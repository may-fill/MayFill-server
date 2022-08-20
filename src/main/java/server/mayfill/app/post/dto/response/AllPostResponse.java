package server.mayfill.app.post.dto.response;

import lombok.*;
import server.mayfill.common.dto.AuditingTimeResponse;
import server.mayfill.domain.post.Post;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AllPostResponse extends AuditingTimeResponse {

    private String nickname;
    private String url;
    private String title;
    private String contents;

    @Builder(access = AccessLevel.PACKAGE)
    private AllPostResponse(String nickname, String url, String title, String contents) {
        this.nickname = nickname;
        this.url = url;
        this.title = title;
        this.contents = contents;
    }

    public static AllPostResponse of(Post post) {
        AllPostResponse response = AllPostResponse.builder()
                .nickname(post.getUser().getNickname())
                .url(post.getImageUrl())
                .title(post.getTitle())
                .contents(post.getContents())
                .build();
        response.setBaseTime(post);
        return response;
    }

}
