package server.mayfill.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.mayfill.domain.common.AuditingTimeEntity;
import server.mayfill.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Builder(access = AccessLevel.PACKAGE)
    public Post(User user, String imageUrl, String title, String contents) {
        this.user = user;
        this.imageUrl = imageUrl;
        this.title = title;
        this.contents = contents;
    }

    public static Post of(User user, String imageUrl, String title, String contents) {
        return Post.builder()
                .user(user)
                .imageUrl(imageUrl)
                .title(title)
                .contents(contents)
                .build();
    }

}
