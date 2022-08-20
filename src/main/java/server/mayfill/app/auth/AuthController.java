package server.mayfill.controller.auth;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.mayfill.common.dto.ApiResponse;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.controller.auth.dto.request.LoginRequest;
import server.mayfill.controller.auth.dto.request.TokenRequest;
import server.mayfill.controller.auth.dto.response.LoginResponse;
import server.mayfill.domain.user.User;
import server.mayfill.service.auth.AuthService;
import server.mayfill.service.auth.AuthServiceProvider;
import server.mayfill.service.auth.CreateTokenService;
import server.mayfill.service.auth.dto.response.TokenResponseDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceProvider authServiceProvider;
    private final CreateTokenService createTokenService;

    @ApiOperation("소셜 로그인 - 회원가입 및 로그인 페이지")
    @PostMapping("/v1/auth/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        User user = authService.socialLogin(request.toServiceDto());
        TokenResponseDto tokenInfo = createTokenService.createTokenInfo(user.getId());
        return ApiResponse.success(LoginResponse.of(user, tokenInfo));
    }

    @ApiOperation("토큰 만료 시 엑세스 토큰 재발급 요청")
    @PostMapping("/v1/auth/reissue")
    public ApiResponse<TokenResponseDto> reissueToken(@Valid @RequestBody TokenRequest request) {
        return ApiResponse.success(ResponseResult.SUCCESS_CREATED_REISSUE_TOKEN, createTokenService.reissueToken(request));
    }

}
