package anchor.mybatis.mapper;

import anchor.mybatis.object.entity.TimeTable;
import org.springframework.stereotype.Repository;

/**
 * @author Anchor
 */
@Repository
public interface TimeTableMapper {

    int insert(TimeTable timeTable);

    TimeTable findById(long id);
}
