package anchor.mybatis.constant;

/**
 * @author Anchor
 */
public enum Gender implements BaseEnum {
    MALE(1, "male"),
    FEMALE(2, "female"),
    UNKNOWN(3, "Unknown");

    private int index;
    private String value;

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
