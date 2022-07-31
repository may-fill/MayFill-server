package server.mayfill.controller.post;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.config.resolver.LoginUserId;
import server.mayfill.controller.post.dto.request.AddPostRequest;
import server.mayfill.service.post.PostService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation("[인증] 인증 페이지 - 새로운 인증 등록")
    @Auth
    @PostMapping("/v1/certification-post")
    public ApiResponse<ResponseResult> addPostWithImage(@Valid AddPostRequest request,
                                                        @RequestPart MultipartFile image,
                                                        @ApiIgnore @LoginUserId Long userId) {
        postService.addPostWithImage(request, image, userId);
        return ApiResponse.success(ResponseResult.SUCCESS_CREATED_CERTIFICATION_POST);
    }

}
