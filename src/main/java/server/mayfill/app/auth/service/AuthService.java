package server.mayfill.app.auth.service;

import server.mayfill.domain.user.entity.User;
import server.mayfill.app.auth.dto.request.LoginDto;

public interface AuthService {
    User socialLogin(LoginDto request);
}
