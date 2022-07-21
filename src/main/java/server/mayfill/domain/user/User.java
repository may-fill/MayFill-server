package server.mayfill.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.mayfill.domain.common.AuditingTimeEntity;
import server.mayfill.domain.user.embedded.SocialInfo;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.domain.user.enumerate.UserStatus;

import javax.persistence.*;

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

    private User(String socialId, SocialType socialType, String nickname) {
        this.socialInfo = SocialInfo.of(socialId, socialType);
        this.nickname = nickname;
        this.status = UserStatus.ACTIVE;
    }

    public static User newInstance(String socialId, SocialType socialType, String nickname) {
        return new User(socialId, socialType, nickname);
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

}
