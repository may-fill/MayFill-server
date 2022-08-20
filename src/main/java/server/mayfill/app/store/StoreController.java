package server.mayfill.app.store;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.mayfill.app.store.dto.request.UpdateStoreTagsRequest;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.app.store.dto.request.AddStoreRequest;
import server.mayfill.external.client.naver.NaverMapApiClient;
import server.mayfill.external.client.naver.dto.NaverMapGeocodeResponse;
import server.mayfill.app.store.service.StoreService;
import springfox.documentation.annotations.ApiIgnore;

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
    public ApiResponse<ResponseResult> addStore(@Valid @RequestBody AddStoreRequest request, @RequestPart MultipartFile imageFile) {
        storeService.registerStoreWithImage(request, imageFile);
        return ApiResponse.success(ResponseResult.SUCCESS_CREATED_STORE);
    }

    @ApiOperation("리필 스테이션 등록 페이지 - 리필 스테이션 정보 수정")
    @PatchMapping("/v1/store")
    public ApiResponse<ResponseResult> updateStoreTags(@Valid @RequestBody UpdateStoreTagsRequest request, @RequestPart MultipartFile imageFile) {
        storeService.updateStore(request, imageFile);
        return ApiResponse.success(ResponseResult.SUCCESS_CREATED_UPDATE_STORE);
    }

}
