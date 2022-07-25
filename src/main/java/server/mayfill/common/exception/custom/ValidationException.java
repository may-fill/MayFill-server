package server.mayfill.common.exception.custom;

import server.mayfill.common.exception.ResponseResult;

public class ValidationException extends MayFillException {

    public ValidationException(String message, ResponseResult responseResult) {
        super(message, responseResult);
    }

    public ValidationException(String message) {
        super(message, ResponseResult.VALIDATION_EXCEPTION);
    }
}
