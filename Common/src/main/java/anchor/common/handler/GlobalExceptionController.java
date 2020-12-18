package anchor.common.handler;


import anchor.common.response.BaseResponse;
import anchor.common.status.StatusCode;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class GlobalExceptionController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    private final DefaultErrorAttributes attributes;

    public GlobalExceptionController(DefaultErrorAttributes attributes) {
        this.attributes = attributes;
    }

    @RequestMapping(path = ERROR_PATH)
    public BaseResponse<String> error(HttpServletRequest request, HttpServletResponse response) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
//        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
//        Throwable cause = exception.getCause();
//        String message = exception.getMessage();
        Map<String, Object> errorAttributes = attributes.getErrorAttributes(webRequest, false);
        BaseResponse<String> info = new BaseResponse<>();
        return info;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    class Error implements StatusCode {
        Integer code;
        String message;

        public Error(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public int code() {
            return code;
        }

        @Override
        public String message() {
            return message;
        }
    }
}
