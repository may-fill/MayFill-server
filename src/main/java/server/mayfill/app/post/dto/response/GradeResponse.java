package server.mayfill.service.post.dto.response;

import lombok.*;
import server.mayfill.domain.user.enumerate.UserGrade;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GradeResponse {

    private UserGrade grade;

    private GradeResponse(UserGrade grade) {
        this.grade = grade;
    }

    public static GradeResponse of(UserGrade grade) {
        return new GradeResponse(grade);
    }

}
