package anchor.mybatis.vo;

import lombok.Data;

@Data
public class MobileResponse {
    private int code;
    private String msg;
    private Data data;

    @lombok.Data
    static class Data {
        private String mobile;
        private String province;
        private String carrier;
    }
}
