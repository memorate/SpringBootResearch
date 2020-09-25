package anchor.mybatis.service;

import anchor.common.exception.DefaultException;
import anchor.common.status.ErrorStatus;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void name() {
        verify("sdasda", 3);
    }

    public void verify(String param, int type) {
        if (param.isEmpty())
            throw new DefaultException(ErrorStatus.PARAMETER_MISSING);
        if (type != 0 && type != 1 && type != 2)
            throw new DefaultException(ErrorStatus.PARAMETER_INVALID, "Type can only be 1 or 2");
    }
}
