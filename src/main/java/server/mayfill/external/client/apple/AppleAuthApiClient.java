package server.mayfill.external.client.apple;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import server.mayfill.external.client.apple.dto.ApplePublicKeyResponse;

@FeignClient(name = "appleAuthApiClient", url = "https://appleid.apple.com/auth")
public interface AppleAuthApiClient {

    @GetMapping("/keys")
    ApplePublicKeyResponse getAppleAuthPublicKey();

}
