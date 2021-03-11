package anchor.mybatis.service.impl;

import anchor.common.exception.DefaultException;
import anchor.common.status.ErrorStatus;
import anchor.mybatis.object.dto.UserDTO;
import anchor.mybatis.object.entity.Customer;
import anchor.mybatis.object.entity.User;
import anchor.mybatis.object.vo.MobileResponse;
import anchor.mybatis.object.vo.QRCodeResponse;
import anchor.mybatis.service.CommonService;
import anchor.mybatis.service.UserService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    private final static String MXN_HOST = "https://www.mxnzp.com/api";
    private final static String MXN_APP_ID = "fivjhtnnpidhkmmj";
    private final static String MXN_APP_SECRET = "dFY0TUF5cFhnK3QrWnEzOEs5TVRldz09";

    @Resource
    private HttpServletResponse response;

    @Resource
    private UserService userService;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void exportAllUsers() {
        List<User> users = userService.getAllUser("id");
        List<UserDTO> collect = users.stream().map(UserDTO::new).collect(Collectors.toList());
        ExportParams params = new ExportParams(null, "用户信息", ExcelType.HSSF);
        try (Workbook wb = ExcelExportUtil.exportExcel(params, UserDTO.class, collect)) {
            this.response.setContentType("application/vnd.ms-excel");
            // 组装附件名称和格式
            this.response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("用户信息.xls", "UTF-8"));
            ServletOutputStream out = this.response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("Export error!", e);
        }
    }

    @Override
    public String getForString(String mobile) {
        String url = MXN_HOST + "/mobile_location/aim_mobile?mobile={mobile}&app_id={app_id}&app_secret={app_secret}";
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("app_id", MXN_APP_ID);
        params.put("app_secret", MXN_APP_SECRET);
        return restTemplate.getForObject(url, String.class, params);
    }

    @Override
    public MobileResponse getForResponse(String mobile) {
        String url = MXN_HOST + "/mobile_location/aim_mobile?mobile={mobile}&app_id={app_id}&app_secret={app_secret}";
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("app_id", MXN_APP_ID);
        params.put("app_secret", MXN_APP_SECRET);
        return restTemplate.getForObject(url, MobileResponse.class, params);
    }

    @Override
    public ResponseEntity<MobileResponse> getForEntity(String mobile) {
        String url = MXN_HOST + "/mobile_location/aim_mobile?mobile={mobile}&app_id={app_id}&app_secret={app_secret}";
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("app_id", MXN_APP_ID);
        params.put("app_secret", MXN_APP_SECRET);
        return restTemplate.getForEntity(url, MobileResponse.class, params);
    }

    @Override
    public String getForStringWithHeader(String mobile) {
        String url = MXN_HOST + "/mobile_location/aim_mobile?mobile=" + mobile;
        HttpHeaders headers = new HttpHeaders();
        headers.add("app_id", MXN_APP_ID);
        headers.add("app_secret", MXN_APP_SECRET);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MobileResponse> getForEntityWithHeader(String mobile) {
        String url = MXN_HOST + "/mobile_location/aim_mobile?mobile=" + mobile;
        HttpHeaders headers = new HttpHeaders();
        headers.add("app_id", MXN_APP_ID);
        headers.add("app_secret", MXN_APP_SECRET);
        HttpEntity httpEntity = new HttpEntity(headers);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, MobileResponse.class);
    }

    @Override
    public String postForString(String content) {
        String url = MXN_HOST + "qrcode/create/logo";
        FileSystemResource resource = new FileSystemResource(new File("D:/文件/壁纸/avatar.jpg"));
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("content", content);
        params.add("size", 500);
        params.add("logo_size", 100);
        params.add("type", 0);
        params.add("logo_img", resource);
        params.add("app_id", MXN_APP_ID);
        params.add("app_secret", MXN_APP_SECRET);
        return restTemplate.postForObject(url, params, String.class);
    }

    @Override
    public QRCodeResponse postForResponse(String content) {
        String url = MXN_HOST + "qrcode/create/logo";
        FileSystemResource resource = new FileSystemResource(new File("D:/文件/壁纸/cat.png"));
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("content", content);
        params.add("size", 400);
        params.add("logo_size", 120);
        params.add("type", 0);
        params.add("logo_img", resource);
        params.add("app_id", MXN_APP_ID);
        params.add("app_secret", MXN_APP_SECRET);
        return restTemplate.postForObject(url, params, QRCodeResponse.class);
    }

    @Override
    public ResponseEntity<QRCodeResponse> postForEntity(String content) {
        String url = MXN_HOST + "qrcode/create/logo";
        FileSystemResource resource = new FileSystemResource(new File("D:/文件/壁纸/cat.png"));
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("content", content);
        params.add("size", 400);
        params.add("logo_size", 120);
        params.add("type", 0);
        params.add("logo_img", resource);
        params.add("app_id", MXN_APP_ID);
        params.add("app_secret", MXN_APP_SECRET);
        return restTemplate.postForEntity(url, params, QRCodeResponse.class);
    }

    @Override
    public QRCodeResponse postForResponseWithHeader(String content) {
        String url = MXN_HOST + "qrcode/create/logo";
        FileSystemResource resource = new FileSystemResource(new File("D:/文件/壁纸/cat.png"));
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("content", content);
        params.add("size", 400);
        params.add("logo_size", 120);
        params.add("type", 0);
        params.add("logo_img", resource);
        HttpHeaders headers = new HttpHeaders();
        headers.add("app_id", MXN_APP_ID);
        headers.add("app_secret", MXN_APP_SECRET);
        HttpEntity httpEntity = new HttpEntity(params, headers);
        return restTemplate.postForObject(url, httpEntity, QRCodeResponse.class);
    }

    @Override
    public ResponseEntity<QRCodeResponse> postForEntityWithHeader(String content) {
        String url = MXN_HOST + "qrcode/create/logo";
        FileSystemResource resource = new FileSystemResource(new File("D:/文件/壁纸/cat.png"));
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("content", content);
        params.add("size", 400);
        params.add("logo_size", 120);
        params.add("type", 0);
        params.add("logo_img", resource);
        HttpHeaders headers = new HttpHeaders();
        headers.add("app_id", MXN_APP_ID);
        headers.add("app_secret", MXN_APP_SECRET);
        HttpEntity httpEntity = new HttpEntity(params, headers);
        return restTemplate.postForEntity(url, httpEntity, QRCodeResponse.class);
    }

    @Override
    public void exceptionTest(String param, int type) {
        if (type == 0) {
            throw new DefaultException(ErrorStatus.PARAMETER_INVALID, "Type is 1");
        }
        if (type == 1) {
            Customer c = null;
            c.getAge();
        }
        if (type == 2) {
            int[] i = {1, 2, 3};
            int i1 = i[3];
        }
    }

    @Override
    public boolean aopTest() {
        log.info("Executing aopTest()");
        return true;
    }
}
