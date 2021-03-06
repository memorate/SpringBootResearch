package anchor.mybatis.mapper;

import anchor.mybatis.object.entity.DatetimeTable;
import org.springframework.stereotype.Repository;

/**
 * @author Anchor
 */
@Repository
public interface DatetimeTableMapper {

    int insert(DatetimeTable datetimeTable);

    DatetimeTable findById(long id);
}
