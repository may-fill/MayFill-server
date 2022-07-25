package server.mayfill.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static server.mayfill.common.exception.StatusCode.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseMessage {

    // 200 OK
    SUCCESS_OK(OK, ""),

    // 201 CREATED
    SUCCESS_CREATED(CREATED, ""),

    // 202 ACCEPTED
    SUCCESS_ACCEPTED(ACCEPTED, ""),

    // 204 NOT_CONTENT
    SUCCESS_NO_CONTENT(NO_CONTENT, ""),

    // 400 BAD_REQUEST
    VALIDATION_AUTH_TOKEN_EXCEPTION(BAD_REQUEST, "만료되거나 유효하지 않은 인증 토큰입니다"),
    VALIDATION_EXCEPTION(BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_ENUM_VALUE_EXCEPTION(BAD_REQUEST, "잘못된 Enum 값 입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(BAD_REQUEST, "필수적인 요청 값이 입력되지 않았습니다"),
    VALIDATION_WRONG_TYPE_EXCEPTION(BAD_REQUEST, "잘못된 타입이 입력되었습니다."),
    VALIDATION_SOCIAL_TYPE_EXCEPTION(BAD_REQUEST, "잘못된 소셜 프로바이더 입니다."),

    // 401 UNAUTHORIZED
    UNAUTHORIZED_EXCEPTION(UNAUTHORIZED, "토큰이 만료되었습니다.\n다시 로그인 해주세요"),

    // 403 FORBIDDEN
    FORBIDDEN_EXCEPTION(FORBIDDEN, "허용하지 않는 요청입니다."),

    // 404 NOT_FOUND
    NOT_FOUND_EXCEPTION(NOT_FOUND, "존재하지 않습니다"),
    NOT_FOUND_USER_EXCEPTION(NOT_FOUND, "탈퇴하거나 존재하지 않는 유저입니다"),

    // 409 CONFLICT
    CONFLICT_EXCEPTION(CONFLICT, "이미 존재합니다"),

    // 500 INTERNAL_SERVER
    INTERNAL_SERVER_EXCEPTION(INTERNAL_SERVER, "예상치 못한 서버 에러가 발생하였습니다."),

    // 502 BAD_GATEWAY
    BAD_GATEWAY_EXCEPTION(BAD_GATEWAY, "일시적인 에러가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),
    ;

    private final StatusCode statusCode;
    private final String message;

}
