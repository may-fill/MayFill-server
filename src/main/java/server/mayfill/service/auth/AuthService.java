package server.mayfill.service.auth;

import server.mayfill.domain.user.User;
import server.mayfill.service.auth.dto.request.LoginDto;

public interface AuthService {
    User socialLogin(LoginDto request);
}
