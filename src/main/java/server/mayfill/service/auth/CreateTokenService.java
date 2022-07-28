package server.mayfill.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mayfill.common.exception.custom.UnAuthorizeException;
import server.mayfill.common.util.JwtUtils;
import server.mayfill.controller.auth.dto.request.TokenRequest;
import server.mayfill.service.auth.dto.response.TokenResponseDto;

@Service
@RequiredArgsConstructor
public class CreateTokenService {

    private final JwtUtils jwtProvider;

    @Transactional
    public TokenResponseDto createTokenInfo(Long userId) {
        return jwtProvider.createTokenByUserId(userId);
    }

    @Transactional
    public TokenResponseDto reissueToken(TokenRequest request) {
        if (!jwtProvider.validateToken(request.getRefreshToken())) {
            throw new UnAuthorizeException(String.format("리프레시 토큰 (%s) 이 유효하지 않습니다", request.getRefreshToken()));
        }
        Long userId = jwtProvider.getUserIdFromJwt(request.getAccessToken());
        return jwtProvider.createTokenByUserId(userId);
    }

}
