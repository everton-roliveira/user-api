package br.com.userapi.userapi.util.formater;

public class PersonTaxIdFormatter {
    private static final String PERSON_TAX_ID_REGEX = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";

    public static String personTaxIdMask(String onlyNumbers) {
        if (onlyNumbers == null) {
            return null;
        }

        if (onlyNumbers.length() < 11) {
            return onlyNumbers;
        }

        return onlyNumbers.replaceAll(PERSON_TAX_ID_REGEX, "$1.$2.$3-$4");
    }
}
