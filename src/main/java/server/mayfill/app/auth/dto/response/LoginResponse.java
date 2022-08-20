package server.mayfill.controller.auth.dto.response;

import lombok.*;
import server.mayfill.domain.user.User;
import server.mayfill.service.auth.dto.response.TokenResponseDto;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private Long userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;

    public static LoginResponse of(User user, TokenResponseDto token) {
        return new LoginResponse(user.getId(), user.getNickname(), token.getAccessToken(), token.getRefreshToken());
    }

}
