package server.mayfill.external.client.naver;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "naverApiClient")
public interface NaverApiClient {
}
