package anchor.mybatis.mapper;

import anchor.mybatis.entity.TimeTable;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableMapper {

    int insert(TimeTable timeTable);

    TimeTable findById(long id);
}
