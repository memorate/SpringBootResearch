package anchor.mybatis.mapper;

import anchor.mybatis.entity.DateTable;
import org.springframework.stereotype.Repository;

@Repository
public interface DateTableMapper {

    int insert(DateTable timeTable);

    DateTable findById(long id);
}
