package anchor.mybatis.mapper;

import anchor.mybatis.entity.Customer;
import anchor.mybatis.query.CustomerQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
    int insertBatch(List<Customer> list);

    List<Customer> listByQuery(CustomerQuery query);

    List<Customer> findByTime(@Param("start")LocalDateTime startTime,
                              @Param("end")LocalDateTime endTime);
}
