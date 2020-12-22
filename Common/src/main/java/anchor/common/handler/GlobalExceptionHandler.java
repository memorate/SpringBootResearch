package anchor.common.handler;

import anchor.common.exception.DefaultException;
import anchor.common.response.BaseResponse;
import anchor.common.status.DefaultStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice                       //该注解 = @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)      //指定要处理的异常类，可以是一个数组
    public BaseResponse<String> ExceptionHandler(Exception e) {
        BaseResponse<String> response = BaseResponse.with(DefaultStatus.INTERNAL_ERROR);
        if (!StringUtils.isEmpty(e.getMessage())) {
            response.setMessage(e.getMessage());
        }
        response.setData("朋友，我们好像碰到了点麻烦！");
        return response;
    }

    @ExceptionHandler(DefaultException.class)
    public BaseResponse<String> DefaultExceptionHandler(DefaultException e) {
        BaseResponse<String> response = BaseResponse.with(e.getCode());
        response.setData(e.getMessage());
        return response;
    }
}
