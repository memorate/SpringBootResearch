package anchor.common.status;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author Anchor
 */
public interface StatusCode extends Serializable {

    @JsonValue
    int code();

    String message();
}
