package server.mayfill.common.exception.custom;

import lombok.Getter;
import server.mayfill.common.exception.ResponseResult;

@Getter
public abstract class MayFillException extends RuntimeException {

    private final ResponseResult responseResult;

    public MayFillException(String message, ResponseResult responseResult) {
        super(message);
        this.responseResult = responseResult;
    }

    public int getStatus() {
        return responseResult.getStatus();
    }

}
