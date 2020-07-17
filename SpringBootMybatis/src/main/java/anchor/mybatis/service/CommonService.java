package anchor.mybatis.service;

import anchor.mybatis.vo.MobileResponse;
import anchor.mybatis.vo.QRCodeResponse;
import org.springframework.http.ResponseEntity;

public interface CommonService {

    void exportAllUsers();

    String getForString(String mobile);

    MobileResponse getForResponse(String mobile);

    ResponseEntity<MobileResponse> getForEntity(String mobile);

    String getForStringWithHeader(String mobile);

    ResponseEntity<MobileResponse> getForEntityWithHeader(String mobile);

    String postForString(String content);

    QRCodeResponse postForResponse(String content);

    ResponseEntity<QRCodeResponse> postForEntity(String content);

    QRCodeResponse postForResponseWithHeader(String content);

    ResponseEntity<QRCodeResponse> postForEntityWithHeader(String content);
}
