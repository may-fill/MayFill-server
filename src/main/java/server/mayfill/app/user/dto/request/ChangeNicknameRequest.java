package server.mayfill.controller.user.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChangeNicknameRequest {

    @NotBlank(message = "${user.nickname.notBlank}")
    private String nickname;

}
