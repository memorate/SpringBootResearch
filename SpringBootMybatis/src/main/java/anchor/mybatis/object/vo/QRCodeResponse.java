package anchor.mybatis.object.vo;

import lombok.Data;

@Data
public class QRCodeResponse extends MXNResponse{
    private Data data;

    @lombok.Data
    static class Data {
        private String qrCodeUrl;
        private String content;
        private int type;
        private String qrCodeBase64;
    }

    @Override
    public String toString() {
        return "QRCodeResponse{" +
                "code=\"" + this.getCode() +
                "\", msg=\"" + this.getMsg() +
                "\", data=" + data +
                '}';
    }
}
