package anchor.mybatis.service;

import anchor.mybatis.vo.MobileResponse;

public interface CommonService {

    void exportAllUsers();

    String getForString(String mobile);

    MobileResponse getForResponse(String mobile);

    String getForStringWithHeader(String mobile);

    MobileResponse getForResponseWithHeader(String mobile);

    void sendPostRequest();
}
