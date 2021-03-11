package anchor.common.status;

/**
 * @author Anchor
 */
public enum ErrorStatus implements StatusCode{

    PARAMETER_MISSING(10001,"Missing required parameter"),
    PARAMETER_INVALID(10002,"Invalid parameter"),
    RESOURCE_NOT_FOUND(10003,"Resource not found"),
    REQUEST_THIRD_PARTY_FAILED(10004,"Request third-party system failed");

    private final int code;
    private final String message;

    ErrorStatus(int code, String message) {
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

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}
