package server.mayfill.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import server.mayfill.common.exception.custom.UnAuthorizeException;
import server.mayfill.common.util.HttpHeaderUtils;
import server.mayfill.common.util.JwtUtils;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class LoginCheckHandler {

    private final JwtUtils jwtProvider;

    public Long getUserId(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaderUtils.AUTH_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HttpHeaderUtils.BEARER_TOKEN)) {
            String accessToken = bearerToken.substring(HttpHeaderUtils.BEARER_TOKEN.length());
            if (jwtProvider.validateToken(accessToken)) {
                Long userId = jwtProvider.getUserIdFromJwt(accessToken);
                if (userId != null) {
                    return userId;
                }
            }
        }
        throw new UnAuthorizeException(String.format("잘못된 JWT (%s) 입니다", bearerToken));
    }

}
