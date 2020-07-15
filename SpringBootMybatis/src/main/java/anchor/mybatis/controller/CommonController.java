package anchor.mybatis.controller;

import anchor.mybatis.service.CommonService;
import anchor.mybatis.vo.MobileResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/common")
public class CommonController {
    @Resource
    private CommonService commonService;

    @GetMapping("/usersExcel")
    public void exportUsers() {
        commonService.exportAllUsers();
    }

    @GetMapping("/getForString")
    public String sendGetForString(@RequestParam String mobile) {
        return commonService.getForString(mobile);
    }

    @GetMapping("/getForResponse")
    public MobileResponse sendGetForResponse(@RequestParam String mobile) {
        return commonService.getForResponse(mobile);
    }
}
