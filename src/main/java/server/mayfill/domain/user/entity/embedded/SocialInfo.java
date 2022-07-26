package server.mayfill.domain.user.entity.embedded;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.mayfill.domain.user.entity.enumerate.SocialType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class SocialInfo {

    @Column(name = "social_id", nullable = false, length = 200)
    private String socialId;

    @Column(name = "social_type", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private SocialInfo(String socialId, SocialType socialType) {
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public static SocialInfo of(String socialId, SocialType socialType) {
        return new SocialInfo(socialId, socialType);
    }

}
