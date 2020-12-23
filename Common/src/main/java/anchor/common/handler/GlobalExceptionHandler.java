package anchor.common.handler;

import anchor.common.exception.DefaultException;
import anchor.common.response.BaseResponse;
import anchor.common.status.DefaultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Anchor
 */
@Slf4j
@RestControllerAdvice                       //该注解 = @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    /**
     * Exception处理
     */
    @ExceptionHandler(Exception.class)      //指定要处理的异常类，可以是一个数组
    public BaseResponse<String> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        BaseResponse<String> response = BaseResponse.with(DefaultStatus.INTERNAL_ERROR);
        if (!StringUtils.isEmpty(e.getMessage())) {
            response.setMessage(e.getMessage());
        }
        response.setData("朋友，我们好像碰到了点麻烦！");
        return response;
    }

    /**
     * DefaultException处理
     */
    @ExceptionHandler(DefaultException.class)
    public BaseResponse<String> defaultExceptionHandler(DefaultException e) {
        log.error(e.getMessage(), e);
        BaseResponse<String> response = BaseResponse.with(e.getCode());
        response.setData(e.getMessage());
        return response;
    }
}
