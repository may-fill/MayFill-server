package server.mayfill.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.mayfill.common.util.RandomNicknameProviderUtils;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.external.client.apple.AppleTokenDecoder;
import server.mayfill.service.auth.AuthService;
import server.mayfill.service.auth.dto.request.LoginDto;
import server.mayfill.service.user.UserService;
import server.mayfill.service.user.UserServiceUtils;

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
