package server.mayfill.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.mayfill.domain.user.User;
import server.mayfill.domain.user.enumerate.SocialType;
import server.mayfill.service.auth.dto.request.LoginDto;

@Service
@RequiredArgsConstructor
public class AppleAuthService implements AuthService {

    private static final SocialType SOCIAL_TYPE = SocialType.APPLE;

    @Override
    public User socialLogin(LoginDto request) {
        return null;
    }

}
