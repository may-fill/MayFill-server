package server.mayfill.app.user.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import server.mayfill.common.exception.custom.ConflictException;
import server.mayfill.common.exception.custom.NotFoundException;
import server.mayfill.domain.user.entity.User;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;

import static server.mayfill.common.exception.ResponseResult.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceUtils {

    public static void validateNotExistsUser(UserRepository userRepository, String socialId, SocialType socialType) {
        if (userRepository.existsBySocialIdAndSocialType(socialId, socialType)) {
            throw new ConflictException(String.format("이미 존재하는 유저 (%s - %s) 입니다", socialId, socialType), CONFLICT_ALREADY_EXIST_USER_EXCEPTION);
        }
    }

    @NotNull
    public static User findUserById(UserRepository userRepository, Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", userId), NOT_FOUND_USER_EXCEPTION);
        }
        return user;
    }

    public static User findUserBySocialIdAndSocialType(UserRepository userRepository, String socialId, SocialType socialType) {
        return userRepository.findUserBySocialIdAndSocialType(socialId, socialType);
    }

}
