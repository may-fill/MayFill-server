package server.mayfill.app.auth.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import server.mayfill.app.auth.service.AuthService;
import server.mayfill.domain.user.entity.enumerate.SocialType;
import server.mayfill.app.auth.service.impl.AppleAuthService;
import server.mayfill.app.auth.service.impl.KakaoAuthService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthServiceProvider {

    private static final Map<SocialType, AuthService> authServiceMap = new HashMap<>();

    private final AppleAuthService appleAuthService;
    private final KakaoAuthService kakaoAuthService;

    @PostConstruct // 의존성 주입이 완료된 후 초기화
    public void initAuthServiceMap() {
        authServiceMap.put(SocialType.APPLE, appleAuthService);
        authServiceMap.put(SocialType.KAKAO, kakaoAuthService);
    }

    public AuthService getAuthService(SocialType socialType) {
        return authServiceMap.get(socialType);
    }

}
