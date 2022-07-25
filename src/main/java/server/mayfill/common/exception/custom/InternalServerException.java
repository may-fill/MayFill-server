package server.mayfill.common.exception.custom;

import server.mayfill.common.exception.ResponseResult;

public class InternalServerException extends MayFillException {

    public InternalServerException(String message, ResponseResult responseResult) {
        super(message, responseResult);
    }

    public InternalServerException(String message) {
        super(message, ResponseResult.INTERNAL_SERVER_EXCEPTION);
    }

}
