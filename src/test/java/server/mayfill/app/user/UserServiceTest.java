package server.mayfill.app.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.mayfill.app.user.dto.request.ChangeNicknameRequest;
import server.mayfill.app.user.dto.request.CreateUserDto;
import server.mayfill.app.user.dto.response.UserInfoResponse;
import server.mayfill.app.user.service.UserService;
import server.mayfill.domain.post.repository.PostRepository;
import server.mayfill.domain.review.repository.ReviewRepository;
import server.mayfill.domain.user.entity.User;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @AfterEach
    void cleanUp() {
        reviewRepository.deleteAllInBatch();
        postRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    void 유저_등록_성공() {
        // given
        CreateUserDto createUserDto = CreateUserDto.of("채울지도", "SocialId", SocialType.KAKAO);

        // when
        User saved = userService.registerUser(createUserDto);

        // then
        User find = userRepository.findUserById(saved.getId());
        assertAll(
                () -> assertThat(find).isNotNull(),
                () -> assertEquals(saved.getId(), find.getId()),
                () -> assertEquals(saved.getNickname(), find.getNickname()),
                () -> assertEquals(saved.getGrade(), find.getGrade()),
                () -> assertEquals(saved.getSocialInfo().getSocialId(), find.getSocialInfo().getSocialId()),
                () -> assertEquals(saved.getSocialInfo().getSocialType(), find.getSocialInfo().getSocialType())
        );
    }

    @Test
    void 유저_닉네임_변경_성공() {
        // given
        CreateUserDto createUserDto = CreateUserDto.of("채울지도", "SocialId", SocialType.KAKAO);
        User saved = userRepository.save(User.newInstance(createUserDto));

        ChangeNicknameRequest request = ChangeNicknameRequest.fromTest("안채울지도");

        // when
        userService.changeNickname(request.getNickname(), saved.getId());
        // then
        User find = userRepository.findUserById(saved.getId());
        assertAll(
                () -> assertThat(find).isNotNull(),
                () -> assertEquals(find.getNickname(), request.getNickname())
        );
    }

    @Test
    void 유저_정보_조회_성공() {
        // given
        CreateUserDto createUserDto = CreateUserDto.of("채울지도", "SocialId", SocialType.KAKAO);
        User saved = userRepository.save(User.newInstance(createUserDto));

        // when
        UserInfoResponse userInfo = userService.retrieveUserInfo(saved.getId());

        // then
        assertAll(
                () -> assertEquals(userInfo.getNickname(), saved.getNickname()),
                () -> assertEquals(userInfo.getGrade(), saved.getGrade())
        );
    }

}
