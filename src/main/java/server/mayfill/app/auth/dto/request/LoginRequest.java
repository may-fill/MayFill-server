package server.mayfill.app.auth.dto.request;

import lombok.*;
import server.mayfill.domain.user.entity.enumerate.SocialType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "{auth.accessToken.notBlank}")
    private String socialToken;

    @NotNull(message = "{auth.socialType.notNull}")
    private SocialType socialType;

    public LoginDto toServiceDto() {
        return LoginDto.of(socialToken, socialType);
    }

}
