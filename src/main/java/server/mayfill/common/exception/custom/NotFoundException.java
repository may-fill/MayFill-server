package server.mayfill.common.exception.custom;

import server.mayfill.common.exception.ResponseResult;

public class NotFoundException extends MayFillException {

    public NotFoundException(String message, ResponseResult responseResult) {
        super(message, responseResult);
    }

}
