package anchor.mybatis.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CustomerQuery {
    private int startAge;
    private int endAge;
    private int customerGender;
    private List<String> provinces;
}
