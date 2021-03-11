package anchor.mybatis.controller;

import anchor.common.response.BaseResponse;
import anchor.mybatis.base.aop.ResultRecorder;
import anchor.mybatis.base.validation.InsertGroup;
import anchor.mybatis.object.vo.MobileResponse;
import anchor.mybatis.object.vo.Validation;
import anchor.mybatis.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Anchor
 */
@Slf4j
@ResultRecorder("公共资源")
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

    @GetMapping("/aopTest")
    public BaseResponse<Boolean> aopTest(String aa, String bb){
        return new BaseResponse<>(commonService.aopTest());
    }

    @GetMapping
    public BaseResponse<Boolean> annotationTest(){
        log.info("Executing annotationTest()...");
        return new BaseResponse<>(true);
    }

    @PostMapping("/validate")
    public BaseResponse<Boolean> validate(@Validated(InsertGroup.class) @RequestBody Validation vo){
        log.info(vo.toString());
        return new BaseResponse<>(true);
    }
}
