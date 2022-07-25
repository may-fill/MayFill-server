package server.mayfill.common.exception.custom;

import server.mayfill.common.exception.ResponseResult;

public class UnAuthorizeException extends MayFillException {

    public UnAuthorizeException(String message, ResponseResult responseResult) {
        super(message, responseResult);
    }

    public UnAuthorizeException(String message) {
        super(message, ResponseResult.UNAUTHORIZED_EXCEPTION);
    }

}
