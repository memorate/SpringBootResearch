package anchor.common.handler;


import anchor.common.response.BaseResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
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
    public BaseResponse<String> error(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Throwable error = attributes.getError(webRequest);
        Map<String, Object> map = attributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        BaseResponse<String> baseResponse = new BaseResponse<>();
        return baseResponse;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
