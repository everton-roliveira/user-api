package br.com.userapi.userapi.domain.enums;

public enum GenderEnum {
    MALE("M"),
    FEMALE("F");

    private final String value;

    GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(String key) {
        if (key == null) {
            return null;
        }

        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.name().equals(key)) {
                return gender.getValue();
            }
        }
        return null;
    }
}
