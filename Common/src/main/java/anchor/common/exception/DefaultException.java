package anchor.common.exception;

import anchor.common.status.StatusCode;

public class DefaultException extends RuntimeException {

    private static final long serialVersionUID = -8618092465858207782L;

    private StatusCode code;
    private String message;

    public DefaultException(StatusCode code) {
        this.code = code;
        this.message = code.message();
    }

    public DefaultException(String message) {
        this.message = message;
    }

    public DefaultException(StatusCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public DefaultException(StatusCode code, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = code.message();
    }
}
