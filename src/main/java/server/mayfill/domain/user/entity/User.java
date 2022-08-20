package server.mayfill.domain.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.mayfill.app.user.dto.request.CreateUserDto;
import server.mayfill.domain.common.AuditingTimeEntity;
import server.mayfill.domain.post.Post;
import server.mayfill.domain.user.entity.embedded.SocialInfo;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.domain.user.entity.enumerate.UserGrade;
import server.mayfill.domain.user.entity.enumerate.UserStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded
    private SocialInfo socialInfo;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserGrade grade;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Post> posts = new ArrayList<>();

    private User(String socialId, SocialType socialType, String nickname) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.nickname = nickname;
        this.status = UserStatus.ACTIVE;
        this.grade = UserGrade.SQUIRE;
    }

    public static User newInstance(CreateUserDto requestDto) {
        return new User(requestDto.getSocialId(), requestDto.getSocialType(), requestDto.getNickname());
    }

    public void addPosts(Post post) {
        this.posts.add(post);
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeGrade(int count) {
        if (count == 5) {
            this.grade = UserGrade.BARON;
        }
        if (count == 10) {
            this.grade = UserGrade.EARL;
        }
        if (count == 19) {
            this.grade = UserGrade.DUKE;
        }
        if (count == 32) {
            this.grade = UserGrade.LORD;
        }
        if (count == 53) {
            this.grade = UserGrade.KING;
        }
    }

}
