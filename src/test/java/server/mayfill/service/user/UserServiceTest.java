package server.mayfill.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    void 유저의_후기가_정상적으로_등록() {
        // given
        User user = User.newInstance("socialId", SocialType.KAKAO, "닉네임");
        User saved = userRepository.save(user);

        // when

        // then
    }

}