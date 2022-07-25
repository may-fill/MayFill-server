package server.mayfill.controller.auth.dto.request;

import lombok.*;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.service.auth.dto.request.LoginDto;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "{auth.accessToken.notBlank}")
    private String socialToken;

    @NotBlank(message = "{auth.socialType.notNull}")
    private SocialType socialType;

    public LoginDto toServiceDto() {
        return LoginDto.of(socialToken, socialType);
    }

}
