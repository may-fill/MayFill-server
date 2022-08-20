package server.mayfill.common.exception.custom;

import server.mayfill.common.exception.ResponseResult;

public class ConflictException extends MayFillException {

    public ConflictException(String message, ResponseResult responseResult) {
        super(message, responseResult);
    }

}
