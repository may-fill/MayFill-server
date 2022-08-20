package server.mayfill.app.auth.dto.request;

import lombok.*;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.app.user.dto.request.CreateUserDto;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {

    private String socialAccessToken;
    private SocialType socialType;

    public static LoginDto of(String socialAccessToken, SocialType socialType) {
        return new LoginDto(socialAccessToken, socialType);
    }

    public CreateUserDto toCreateUserDto(String socialId, String nickname) {
        return CreateUserDto.of(nickname, socialId, socialType);
    }

}
