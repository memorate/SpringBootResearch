package anchor.common.handler;

import anchor.common.exception.DefaultException;
import anchor.common.response.BaseResponse;
import anchor.common.status.ErrorStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BasicExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<String> ExceptionHandler(Exception e){
        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(ErrorStatus.INTERNAL_ERROR);
        response.setMessage(e.getMessage());
        return response;
    }

    @ResponseBody
    @ExceptionHandler(value = DefaultException.class)
    public BaseResponse<String> DefaultExceptionHandler(DefaultException e){
        BaseResponse<String> response = new BaseResponse<>();
        response.setCode(e.getCode());
        response.setMessage(e.getCode().message());
        response.setData(e.getMessage());
        return response;
    }
}
