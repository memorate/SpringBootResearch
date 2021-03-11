package anchor.mybatis.mapper;

import anchor.mybatis.object.entity.DateTable;
import org.springframework.stereotype.Repository;

/**
 * @author Anchor
 */
@Repository
public interface DateTableMapper {

    int insert(DateTable timeTable);

    DateTable findById(long id);
}
