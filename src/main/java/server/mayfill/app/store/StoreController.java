package server.mayfill.controller.store;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.controller.store.dto.request.AddStoreRequest;
import server.mayfill.external.client.naver.NaverMapApiClient;
import server.mayfill.external.client.naver.dto.NaverMapGeocodeResponse;
import server.mayfill.service.store.StoreService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final NaverMapApiClient naverMapApiCaller;
    private final StoreService storeService;

    @ApiOperation("[인증] 리필 스테이션 등록 페이지 - 리필 스테이션 위치 검색하기")
    @Auth
    @GetMapping("/v1/store/search")
    public ApiResponse<NaverMapGeocodeResponse> searchStore(@RequestParam String query) {
        return ApiResponse.success(ResponseResult.OK_SEARCH_STORE, naverMapApiCaller.getGeocode(query));
    }

    @ApiOperation("[인증] 리필 스테이션 등록 페이지 - 리필 스테이션 등록하기")
    @Auth
    @PostMapping("/v1/store/new")
    public ApiResponse<ResponseResult> addStore(@Valid @RequestBody AddStoreRequest request) {
        storeService.registerStore(request);
        return ApiResponse.success(ResponseResult.SUCCESS_CREATED_STORE);
    }

}
