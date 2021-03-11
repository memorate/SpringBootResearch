package anchor.mybatis.service.impl;

import anchor.mybatis.mapper.CustomerMapper;
import anchor.mybatis.object.entity.Customer;
import anchor.mybatis.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Long insert(Customer customer){
        customerMapper.insert(customer);
        return customer.getId();
    }

    @Override
    public Integer insertBatch(List<Customer> list) {
        return customerMapper.insertBatch(list);
    }

    @Override
    public boolean updateById(Customer customer){
        return customerMapper.updateById(customer) > 0;
    }

    @Override
    public Customer findById(Integer id){
        return customerMapper.selectById(id);
    }

    @Override
    public boolean deleteById(Integer id){
        return customerMapper.deleteById(id) > 0;
    }
}
