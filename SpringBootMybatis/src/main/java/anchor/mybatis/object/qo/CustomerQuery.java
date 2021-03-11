package anchor.mybatis.object.qo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Anchor
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CustomerQuery {
    private int startAge;
    private int endAge;
    private int customerGender;
    private List<String> provinces;
}
