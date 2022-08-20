package server.mayfill.controller.home;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.service.home.HomeRetrieveService;
import server.mayfill.service.home.dto.response.AllStoreResponse;
import server.mayfill.service.home.dto.response.OneStoreResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeRetrieveService homeRetrieveService;

    @ApiOperation("[인증] 메인 뷰 - 모든 리필스테이션 정보 조회")
    @Auth
    @GetMapping("/v1/home")
    public ApiResponse<List<AllStoreResponse>> retrieveAllStore() {
        return ApiResponse.success(ResponseResult.OK_RETRIEVE_ALL_STORE, homeRetrieveService.retrieveAllStore());
    }

    @ApiOperation("[인증] 메인 뷰 핀 클릭 시 - 리필스테이션 상세 정보 조회")
    @Auth
    @GetMapping("/v1/home/{storeId}")
    public ApiResponse<OneStoreResponse> retrieveOneStore(@PathVariable Long storeId) {
        return ApiResponse.success(ResponseResult.OK_RETRIEVE_ONE_STORE, homeRetrieveService.retrieveOneStore(storeId));
    }

}
