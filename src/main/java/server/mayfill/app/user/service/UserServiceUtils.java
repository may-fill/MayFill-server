package server.mayfill.service.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.common.exception.custom.NotFoundException;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceUtils {

    @NotNull
    public static User findUserById(UserRepository userRepository, Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", userId), ResponseResult.NOT_FOUND_EXCEPTION);
        }
        return user;
    }

    public static User findUserBySocialIdAndSocialType(UserRepository userRepository, String socialId, SocialType socialType) {
        return userRepository.findUserBySocialIdAndSocialType(socialId, socialType);
    }

}
