package server.mayfill.app.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.mayfill.common.util.RandomNicknameProviderUtils;
import server.mayfill.domain.user.entity.User;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.external.client.apple.AppleTokenDecoder;
import server.mayfill.app.auth.service.AuthService;
import server.mayfill.app.auth.dto.request.LoginDto;
import server.mayfill.app.user.service.UserService;
import server.mayfill.app.user.service.UserServiceUtils;

@Service
@RequiredArgsConstructor
public class AppleAuthService implements AuthService {

    private static final SocialType SOCIAL_TYPE = SocialType.APPLE;

    private final AppleTokenDecoder appleTokenDecoder;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public User socialLogin(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getSocialAccessToken());
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, socialId, SOCIAL_TYPE);
        if (user == null) {
            return userService.registerUser(request.toCreateUserDto(socialId, RandomNicknameProviderUtils.getRandomNickname()));
        }
        return user;
    }

}
