package anchor.mybatis.mapper;

import anchor.mybatis.object.entity.Customer;
import anchor.mybatis.object.qo.CustomerQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Anchor
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
    int insertBatch(List<Customer> list);

    List<Customer> findLikeName(String name);

    List<Customer> listByQuery(CustomerQuery query);

    List<Customer> findByTime(Integer gender, LocalDateTime startTime, LocalDateTime endTime);

    int update();
}
