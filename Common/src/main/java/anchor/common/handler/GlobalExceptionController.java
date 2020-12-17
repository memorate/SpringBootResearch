package anchor.common.handler;


import anchor.common.response.BaseResponse;
import anchor.common.status.StatusCode;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class GlobalExceptionController implements ErrorController {

    private final static String ERROR_PATH = "/error";

    private final ErrorAttributes errorAttributes;

    public GlobalExceptionController(ErrorAttributes attributes) {
        this.errorAttributes = attributes;
    }

    @RequestMapping(path = ERROR_PATH)
    public BaseResponse<String> error(HttpServletRequest request, HttpServletResponse response) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
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
