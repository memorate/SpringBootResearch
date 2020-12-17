package anchor.common.handler;

import anchor.common.exception.DefaultException;
import anchor.common.response.BaseResponse;
import anchor.common.status.DefaultStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse<String> ExceptionHandler(Exception e) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(DefaultStatus.INTERNAL_ERROR);
        response.setMessage(e.getMessage());
        response.setData(e.getClass().getName());
        return response;
    }

    @ExceptionHandler(DefaultException.class)
    public BaseResponse<String> DefaultExceptionHandler(DefaultException e) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(e.getCode());
        response.setMessage(e.getCode().message());
        response.setData(e.getMessage());
        return response;
    }
}
