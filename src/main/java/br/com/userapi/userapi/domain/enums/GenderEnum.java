package br.com.userapi.userapi.domain.enums;

public enum GenderEnum {
    MALE('M'),
    FEMALE('F');

    private final char value;

    GenderEnum(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static GenderEnum fromValue(char value) {
        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.value == value) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid Gender value: " + value);
    }
}
