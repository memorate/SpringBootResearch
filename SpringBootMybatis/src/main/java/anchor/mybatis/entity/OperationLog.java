package anchor.mybatis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Anchor
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class OperationLog {
    private String uid;
    private String uname;
    private String resource;
    private String method;
    private Integer code;
    private String message;
    private LocalDateTime time;
}
