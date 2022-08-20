package server.mayfill.app.user.dto.request;

import lombok.*;
import server.mayfill.domain.user.entity.enumerate.SocialType;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserDto {

    private String nickname;
    private String socialId;
    private SocialType socialType;

    public static CreateUserDto of(String nickname, String socialId, SocialType socialType) {
        return new CreateUserDto(nickname, socialId, socialType);
    }

}
