package anchor.mybatis.object.constant;

import anchor.mybatis.base.aop.BaseEnum;

/**
 * @author Anchor
 */
public enum Gender implements BaseEnum {
    /**
     * 男
     */
    MALE(1, "male"),
    /**
     * 女
     */
    FEMALE(2, "female"),
    /**
     * 未知
     */
    UNKNOWN(3, "Unknown");

    private final int index;
    private final String value;

    Gender(int index, String value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
