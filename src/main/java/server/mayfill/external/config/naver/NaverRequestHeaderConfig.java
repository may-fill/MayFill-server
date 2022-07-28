package server.mayfill.external.config.naver;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import server.mayfill.common.util.HttpHeaderUtils;
import server.mayfill.common.util.YamlPropertySourceFactory;

@Configuration
@PropertySource(value = "classpath:application-naver.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class NaverRequestHeaderConfig {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaderUtils.X_NCP_APIGW_API_KEY_ID, clientId);
            requestTemplate.header(HttpHeaderUtils.X_NCP_APIGW_API_KEY, clientSecret);
        };
    }

}
