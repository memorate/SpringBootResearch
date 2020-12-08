package anchor.common.exception.handler;

import org.springframework.boot.web.servlet.error.ErrorController;

public class BasicErrorHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }
}
