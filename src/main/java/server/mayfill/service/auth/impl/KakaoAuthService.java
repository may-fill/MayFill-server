package server.mayfill.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.mayfill.common.util.HttpHeaderUtils;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.domain.user.repository.UserRepository;
import server.mayfill.external.client.kakao.KakaoApiClient;
import server.mayfill.external.client.kakao.dto.KakaoAuthResponse;
import server.mayfill.service.auth.dto.request.LoginDto;
import server.mayfill.service.user.UserService;
import server.mayfill.service.user.UserServiceUtils;

@Service
@RequiredArgsConstructor
public class KakaoAuthService implements AuthService {

    private static final SocialType SOCIAL_TYPE = SocialType.KAKAO;

    private final KakaoApiClient kakaoApiCaller;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public User socialLogin(LoginDto request) {
        KakaoAuthResponse response = kakaoApiCaller.getKakaoUserProfile(HttpHeaderUtils.withBearerToken(request.getSocialAccessToken()));
        User user = UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), SOCIAL_TYPE);
        if (user == null) { // 없으면 회원가입
            userService.registerUser(request.toCreateUserDto(response.getId()));
        }
        return user;
    }

}
