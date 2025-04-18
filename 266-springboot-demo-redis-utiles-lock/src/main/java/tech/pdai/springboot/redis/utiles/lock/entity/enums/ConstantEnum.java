package tech.pdai.springboot.redis.utiles.lock.entity.enums;

public enum ConstantEnum {
    /**
     * redis分隔符
     */
    KEY_SPLIT(":"),
    ;


    private final String value;

    ConstantEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
