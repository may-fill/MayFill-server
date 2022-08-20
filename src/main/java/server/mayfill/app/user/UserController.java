package server.mayfill.app.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.mayfill.app.user.service.UserService;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.config.interceptor.Auth;
import server.mayfill.config.resolver.LoginUserId;
import server.mayfill.app.user.dto.request.ChangeNicknameRequest;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("[인증] 마이페이지 - 닉네임 변경")
    @Auth
    @PutMapping("/v1/user/me/nickname")
    public ApiResponse<ResponseResult> updateNickname(@Valid @RequestBody ChangeNicknameRequest request, @LoginUserId Long userId) {
        userService.changeNickname(request.getNickname(), userId);
        return ApiResponse.success(ResponseResult.SUCCESS_CREATED_UPDATE_NICKNAME);
    }

}
