package server.mayfill.app.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.mayfill.app.user.dto.response.UserInfoResponse;
import server.mayfill.app.user.service.UserService;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.config.resolver.LoginUserId;
import server.mayfill.app.user.dto.request.ChangeNicknameRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static server.mayfill.common.exception.ResponseResult.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("[인증] 마이페이지 - 내 정보 조회")
    @Auth
    @GetMapping("/v1/user/me")
    public ApiResponse<UserInfoResponse> retrieveUserInfo(@ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(OK_RETRIEVE_USER_INFO, userService.retrieveUserInfo(userId));
    }

    @ApiOperation("[인증] 마이페이지 - 닉네임 변경")
    @Auth
    @PutMapping("/v1/user/me/nickname")
    public ApiResponse<ResponseResult> updateNickname(@Valid @RequestBody ChangeNicknameRequest request, @ApiIgnore @LoginUserId Long userId) {
        userService.changeNickname(request.getNickname(), userId);
        return ApiResponse.success(SUCCESS_CREATED_UPDATE_NICKNAME);
    }

}
