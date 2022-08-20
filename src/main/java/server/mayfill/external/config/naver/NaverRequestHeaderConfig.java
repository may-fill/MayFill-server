package server.mayfill.external.config.naver;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import server.mayfill.common.util.YamlPropertySourceFactory;

@Configuration
@PropertySource(value = "classpath:application-naver.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public class NaverRequestHeaderConfig {

    private static final String NAVER_API_KEY_ID = "X-NCP-APIGW-API-KEY-ID";
    private static final String NAVER_API_KEY = "X-NCP-APIGW-API-KEY";

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(NAVER_API_KEY_ID, clientId);
            requestTemplate.header(NAVER_API_KEY, clientSecret);
        };
    }

}
