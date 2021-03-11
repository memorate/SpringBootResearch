package anchor.mybatis.service;

import anchor.mybatis.object.entity.Customer;

import java.util.List;

public interface CustomerService {

    Long insert(Customer customer);

    Integer insertBatch(List<Customer> list);

    boolean updateById(Customer customer);

    Customer findById(Integer id);

    boolean deleteById(Integer id);

}
