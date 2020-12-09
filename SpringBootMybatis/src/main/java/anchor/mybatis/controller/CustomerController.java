package anchor.mybatis.controller;

import anchor.common.response.BaseResponse;
import anchor.mybatis.entity.Customer;
import anchor.mybatis.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PutMapping
    public BaseResponse<Integer> initialize(){
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("Jhonny", 22, 1, "132165465@gmail.com", "Anhui"));
        list.add(new Customer("Andy", 19, 1, "73578357@gmail.com", "Beijing"));
        list.add(new Customer("Isabella", 20, 2, "4564564456@gmail.com", "Hebei"));
        list.add(new Customer("Kevin", 23, 1, "37875464@gmail.com", "Hubei"));
        list.add(new Customer("Ashley", 25, 2, "12375378@gmail.com", "Guangdong"));
        list.add(new Customer("Warren", 27, 1, "8673345657@gmail.com", "Chongqing"));
        list.add(new Customer("Buddie", 24, 3, "36787527@gmail.com", "Hebei"));
        list.add(new Customer("Larissa", 26, 2, "5678653455@gmail.com", "Qinghai"));
        return new BaseResponse<>(customerService.insertBatch(list));
    }
}
