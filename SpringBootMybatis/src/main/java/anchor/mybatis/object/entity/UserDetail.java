package anchor.mybatis.object.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anchor
 */
@Data
@NoArgsConstructor
public class UserDetail {

    private Long id;

    private Long userId;

    private String position;

    private String phoneNumber;

    private String education;

    public UserDetail(Long userId, String position, String phoneNumber, String education) {
        this.userId = userId;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.education = education;
    }
}
