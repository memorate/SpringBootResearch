package anchor.mybatis.mapper;

import anchor.mybatis.entity.YearTable;
import org.springframework.stereotype.Repository;

@Repository
public interface YearTableMapper {

    int insert(YearTable yearTable);

    YearTable findById(long id);
}
