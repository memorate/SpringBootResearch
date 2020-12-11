package anchor.mybatis.service;

import anchor.mybatis.entity.Customer;
import anchor.mybatis.mapper.CustomerMapper;
import anchor.mybatis.query.CustomerQuery;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Resource
    private CustomerMapper mapper;

    @Test
    public void initData() {
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("Jhonny", 22, 1, "132165465@gmail.com", "Anhui"));
        list.add(new Customer("Andy", 19, 1, "73578357@gmail.com", "Beijing"));
        list.add(new Customer("Isabella", 20, 2, "4564564456@gmail.com", "Hebei"));
        list.add(new Customer("Kevin", 23, 1, "37875464@gmail.com", "Hubei"));
        list.add(new Customer("Ashley", 25, 2, "12375378@gmail.com", "Guangdong"));
        list.add(new Customer("Warren", 27, 1, "8673345657@gmail.com", "Chongqing"));
        list.add(new Customer("Buddie", 24, 3, "36787527@gmail.com", "Hebei"));
        list.add(new Customer("Larissa", 26, 2, "5678653455@gmail.com", "Qinghai"));
        int success = mapper.insertBatch(list);
        log.info(list.get(0).toString());
        assertEquals(list.size(), success);
    }

    @Test
    public void insertOne() {
        Customer customer = new Customer("Mark", 29, 1, "65465512@gmail.com", "Anhui");
        mapper.insert(customer);
        log.info(customer.toString());
    }

    @Test
    public void selectOneTest() {
        Customer jhonny = mapper.selectOne(
                Wrappers.lambdaQuery(Customer.class)
                        .eq(Customer::getName, "Jhonny"));
        assertEquals(22, (int) jhonny.getAge());
    }

    @Test
    public void selectListTest() {
        List<Customer> list = mapper.selectList(Wrappers.lambdaQuery(Customer.class)
                .ge(Customer::getAge, 20)
                .le(Customer::getAge, 30)
                .in(Customer::getProvince, Arrays.asList("Hubei", "Hebei")));
        assertNotEquals(0, list.size());
    }

    @Test
    public void simpleDeleteTest() {
        int num = mapper.delete(Wrappers.lambdaQuery(Customer.class)
                .eq(Customer::getGender, 1)
                .ge(Customer::getAge, 20)
                .le(Customer::getAge, 30)
                .in(Customer::getProvince, Arrays.asList("Hubei", "Chongqing", "Beijing"))
        );
    }

    @Test
    public void queryTest() {
        CustomerQuery query = new CustomerQuery()
                .setStartAge(20)
                .setEndAge(30)
                .setCustomerGender(1)
                .setProvinces(Arrays.asList("Hubei", "Chongqing", "Beijing"));
        List<Customer> list = mapper.listByQuery(query);
        log.info(list.toString());
    }
}
