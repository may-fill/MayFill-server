package server.mayfill.app.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.mayfill.app.auth.provider.AuthServiceProvider;
import server.mayfill.app.auth.service.AuthService;
import server.mayfill.app.auth.service.impl.AppleAuthService;
import server.mayfill.app.auth.service.impl.KakaoAuthService;
import server.mayfill.domain.user.entity.enumerate.SocialType;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class AuthServiceProviderTest {

    @Autowired
    private AuthServiceProvider authServiceProvider;

    @Test
    void 소셜타입이_애플일때_애플인증_서비스_정상_제공() {
        // given
        SocialType apple = SocialType.APPLE;
        boolean success = false;
        // when
        AuthService authService = authServiceProvider.getAuthService(apple);
        if (authService instanceof AppleAuthService) {
            success = true;
        }
        // then
        assertTrue(success);
    }

    @Test
    void 소셜타입이_카카오일때_카카오인증_서비스_정상_제공() {
        // given
        SocialType kakao = SocialType.KAKAO;
        boolean success = false;
        // when
        AuthService authService = authServiceProvider.getAuthService(kakao);
        if (authService instanceof KakaoAuthService) {
            success = true;
        }
        // then
        assertTrue(success);
    }

}
