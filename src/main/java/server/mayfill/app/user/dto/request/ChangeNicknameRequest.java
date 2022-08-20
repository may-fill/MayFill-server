package server.mayfill.app.user.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
public class ChangeNicknameRequest {

    @NotBlank(message = "${user.nickname.notBlank}")
    private String nickname;

    private ChangeNicknameRequest() {}

    private ChangeNicknameRequest(String nickname) {
        this.nickname = nickname;
    }

    public static ChangeNicknameRequest fromTest(String nickname) {
        return new ChangeNicknameRequest(nickname);
    }

}
