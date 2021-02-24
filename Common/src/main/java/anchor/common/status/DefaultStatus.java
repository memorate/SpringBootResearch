package anchor.common.status;

public enum DefaultStatus implements StatusCode {

    SUCCESS(200, "Operation Success"),

    PARAM_ERROR(400, "Invalid parameters"),

    NOT_FOUND(404, "Resource not found"),

    INTERNAL_ERROR(500,"Application internal error"),

    VALIDATION_ERROR(5001, "Validation fail");

    private final int code;
    private final String message;

    DefaultStatus(int code, String message) {
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
