package br.com.userapi.userapi.util.formater;

public class PhoneFormatter {
    private static final String CELLPHONE_REGEX = "(\\d{2})(\\d{5})(\\d{4})";

    public static String cellphoneMask(String onlyNumbers) {
        if (onlyNumbers == null || onlyNumbers.length() != 11) {
            return onlyNumbers;
        }

        return onlyNumbers.replaceAll(CELLPHONE_REGEX, "($1)$2-$3");
    }
}
