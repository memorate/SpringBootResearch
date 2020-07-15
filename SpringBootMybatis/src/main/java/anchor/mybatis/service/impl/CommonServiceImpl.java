package anchor.mybatis.service.impl;

import anchor.mybatis.dto.UserDTO;
import anchor.mybatis.entity.User;
import anchor.mybatis.service.CommonService;
import anchor.mybatis.service.UserService;
import anchor.mybatis.vo.MobileResponse;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    private final static String MXNUrl = "https://www.mxnzp.com/api";
    private final static String APP_ID = "fivjhtnnpidhkmmj";
    private final static String APP_SECRET = "dFY0TUF5cFhnK3QrWnEzOEs5TVRldz09";

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
        String url = MXNUrl + "/mobile_location/aim_mobile?mobile={mobile}&app_id={app_id}&app_secret={app_secret}";
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("app_id", APP_ID);
        params.put("app_secret", APP_SECRET);
        return restTemplate.getForObject(url, String.class, params);
    }

    @Override
    public MobileResponse getForResponse(String mobile) {
        String url = MXNUrl + "/mobile_location/aim_mobile?mobile={mobile}&app_id={app_id}&app_secret={app_secret}";
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("app_id", APP_ID);
        params.put("app_secret", APP_SECRET);
        return restTemplate.getForObject(url, MobileResponse.class, params);
    }

    @Override
    public String getForStringWithHeader(String mobile) {
        String url = MXNUrl + "/mobile_location/aim_mobile?mobile=" + mobile;
        HttpHeaders headers = new HttpHeaders();
        headers.add("app_id", APP_ID);
        headers.add("app_secret", APP_SECRET);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public MobileResponse getForResponseWithHeader(String mobile) {
        String url = MXNUrl + "/mobile_location/aim_mobile?mobile=" + mobile;
        HttpHeaders headers = new HttpHeaders();
        headers.add("app_id", APP_ID);
        headers.add("app_secret", APP_SECRET);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<MobileResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, MobileResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public void sendPostRequest() {

    }
}
