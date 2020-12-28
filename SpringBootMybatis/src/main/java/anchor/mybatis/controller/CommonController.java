package anchor.mybatis.controller;

import anchor.common.response.BaseResponse;
import anchor.mybatis.aop.LogTag;
import anchor.mybatis.service.CommonService;
import anchor.mybatis.vo.MobileResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Anchor
 */
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

    @GetMapping("/exception")
    public BaseResponse exceptionTest(@RequestParam String param, @RequestParam int type, @RequestHeader String header) {
        commonService.exceptionTest(param, type);
        return new BaseResponse();
    }

    @LogTag
    @GetMapping("/aopTest")
    public BaseResponse<Boolean> aopTest(){
        return new BaseResponse<>(commonService.aopTest());
    }
}
