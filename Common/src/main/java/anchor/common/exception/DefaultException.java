package anchor.common.exception;

import anchor.common.status.StatusCode;

public class DefaultException extends RuntimeException {

    private static final long serialVersionUID = -8618092465858207782L;

    private StatusCode code;
    private String message;
    private String detail;

    public DefaultException(StatusCode code) {
        super(code.message());
        this.code = code;
    }

    public DefaultException(StatusCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public StatusCode getCode() {
        return code;
    }

    public void setCode(StatusCode code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
