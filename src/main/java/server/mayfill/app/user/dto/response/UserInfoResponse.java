package server.mayfill.app.user.dto.response;

import lombok.*;
import server.mayfill.domain.user.entity.enumerate.UserGrade;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoResponse {

    private String nickname;
    private UserGrade grade;

    public static UserInfoResponse of(String nickname, UserGrade grade) {
        return new UserInfoResponse(nickname, grade);
    }

}
