package server.mayfill.app.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.mayfill.common.util.HttpHeaderUtils;
import server.mayfill.common.util.RandomNicknameProviderUtils;
import server.mayfill.domain.user.entity.User;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.external.client.kakao.KakaoApiClient;
import server.mayfill.external.client.kakao.dto.KakaoAuthResponse;
import server.mayfill.app.auth.service.AuthService;
import server.mayfill.app.auth.dto.request.LoginDto;
import server.mayfill.app.user.service.UserService;
import server.mayfill.app.user.service.UserServiceUtils;

@Service
@RequiredArgsConstructor
public class KakaoAuthService implements AuthService {

    private static final SocialType KAKAO_TYPE = SocialType.KAKAO;

    private final KakaoApiClient kakaoApiCaller;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public User socialLogin(LoginDto request) {
        KakaoAuthResponse response = kakaoApiCaller.getKakaoUserProfile(HttpHeaderUtils.withBearerToken(request.getSocialAccessToken()));
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), KAKAO_TYPE);
        if (user == null) { // 없으면 회원가입
            return userService.registerUser(request.toCreateUserDto(response.getId(), RandomNicknameProviderUtils.getRandomNickname()));
        }
        return user;
    }

}
