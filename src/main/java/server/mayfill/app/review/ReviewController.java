package server.mayfill.app.review;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.config.resolver.LoginUserId;
import server.mayfill.app.review.dto.request.AddReviewRequest;
import server.mayfill.app.review.dto.response.ReviewResponse;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation("[인증] 리필 스테이션 상세보기 페이지 - 후기 등록")
    @Auth
    @PostMapping("/v1/store/review")
    public ApiResponse<ResponseResult> addReview(@Valid @RequestBody AddReviewRequest request, @ApiIgnore @LoginUserId Long userId) {
        reviewService.addReview(request, userId);
        return ApiResponse.success(ResponseResult.SUCCESS_CREATED_REVIEW_COMMENT);
    }

    @ApiOperation("[인증] 리필 스테이션 상세보기 페이지 - 후기 조회")
    @Auth
    @GetMapping("/v1/store/{storeId}/review")
    public ApiResponse<List<ReviewResponse>> retrieveReview(@PathVariable Long storeId) {
        return ApiResponse.success(ResponseResult.OK_RETRIEVE_STORE_REVIEW, reviewService.retrieveReview(storeId));
    }

}
