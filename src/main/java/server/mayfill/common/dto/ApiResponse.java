package server.mayfill.common.dto;

import lombok.*;
import server.mayfill.common.exception.ResponseResult;
import server.mayfill.common.exception.StatusCode;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    public static final ApiResponse<String> SUCCESS = success("");

    private StatusCode statusCode;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(StatusCode.OK, "", data);
    }

    public static <T> ApiResponse<T> success(ResponseResult responseResult) {
        return new ApiResponse<>(responseResult.getStatusCode(), responseResult.getMessage(), null);
    }

    public static <T> ApiResponse<T> success(ResponseResult responseResult, T data) {
        return new ApiResponse<>(responseResult.getStatusCode(), responseResult.getMessage(), data);
    }

    public static <T> ApiResponse<T> error(ResponseResult responseResult) {
        return new ApiResponse<>(responseResult.getStatusCode(), responseResult.getMessage(), null);
    }

    public static <T> ApiResponse<T> error(ResponseResult responseResult, String message) {
        return new ApiResponse<>(responseResult.getStatusCode(), message, null);
    }

}
