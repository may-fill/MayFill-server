package server.mayfill.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpHeaderUtils {

    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_TOKEN = "Bearer ";

    public static final String X_NCP_APIGW_API_KEY_ID = "X-NCP-APIGW-API-KEY-ID";
    public static final String X_NCP_APIGW_API_KEY = "X-NCP-APIGW-API-KEY";

    public static String withBearerToken(String token) {
        return BEARER_TOKEN.concat(token);
    }

}
