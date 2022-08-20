package server.mayfill.app.post;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.config.resolver.LoginUserId;
import server.mayfill.app.post.dto.request.AddPostRequest;
import server.mayfill.app.post.dto.response.AllPostResponse;
import server.mayfill.app.post.dto.response.GradeResponse;
import server.mayfill.app.post.dto.response.MyPostResponse;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static server.mayfill.common.exception.ResponseResult.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ApiOperation("[인증] 인증 페이지 - 새로운 인증 등록")
    @Auth
    @PostMapping("/v1/certification-post")
    public ApiResponse<GradeResponse> addPostWithImage(@Valid AddPostRequest request,
                                                       @RequestPart MultipartFile image,
                                                       @ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(SUCCESS_CREATED_CERTIFICATION_POST, postService.addPostWithImage(request, image, userId));
    }

    @ApiOperation("[인증] 인증 페이지 - 개인 인증 포스트 조회")
    @Auth
    @GetMapping("/v1/certification-post")
    public ApiResponse<List<MyPostResponse>> retrieveMyPost(@ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(OK_SEARCH_MY_POST, postService.retrieveMyPost(userId));
    }

    @ApiOperation("[인증] 둘러보기 페이지 - 모든 유저의 인증 포스트 조회")
    @Auth
    @GetMapping("/v1/certification-post/all")
    public ApiResponse<List<AllPostResponse>> retrieveAllPost() {
        return ApiResponse.success(OK_SEARCH_ALL_POST ,postService.retrieveAllPost());
    }

}
