package anchor.mybatis.controller;

import anchor.mybatis.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FileService fileService;

    @GetMapping("/UsersExcel")
    public void exportUsers(){
        fileService.exportAllUsers();
    }
}
