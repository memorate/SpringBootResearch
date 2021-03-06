package anchor.mybatis.object.vo;

import lombok.Data;

/**
 * @author Anchor
 */
@Data
public class MobileResponse extends MXNResponse{
    private Data data;

    @lombok.Data
    public static class Data {
        private String mobile;
        private String province;
        private String carrier;
    }

    @Override
    public String toString() {
        return "MobileResponse{" +
                "code=\"" + this.getCode() +
                "\", msg=\"" + this.getMsg() +
                "\", data=" + data +
                '}';
    }
}
