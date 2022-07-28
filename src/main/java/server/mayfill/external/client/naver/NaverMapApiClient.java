package server.mayfill.external.client.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.mayfill.common.util.YamlPropertySourceFactory;
import server.mayfill.external.client.naver.dto.NaverMapGeocodeResponse;
import server.mayfill.external.config.naver.NaverRequestHeaderConfig;

@FeignClient(name = "naverMapApiClient", url = "https://naveropenapi.apigw.ntruss.com", configuration = NaverRequestHeaderConfig.class)
@PropertySource(value = "classpath:application-naver.yml", factory = YamlPropertySourceFactory.class, ignoreResourceNotFound = true)
public interface NaverMapApiClient {

    @GetMapping(value = "/map-geocode/v2/geocode")
    NaverMapGeocodeResponse getGeocode(@RequestParam String query);

}
