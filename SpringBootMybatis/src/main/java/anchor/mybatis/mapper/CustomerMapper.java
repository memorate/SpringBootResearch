package anchor.mybatis.mapper;

import anchor.mybatis.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
    int insertBatch(List<Customer> list);
}
