package anchor.mybatis.mapper;

import anchor.mybatis.object.entity.TimestampTable;
import org.springframework.stereotype.Repository;

@Repository
public interface TimestampTableMapper {

    int insert(TimestampTable timeTable);

    TimestampTable findById(long id);
}
