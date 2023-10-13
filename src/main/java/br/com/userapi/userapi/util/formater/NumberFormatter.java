package br.com.userapi.userapi.util.formater;

public class NumberFormatter {
    private static final String REGEX_ONLY_NUMBER = "[^0-9]";

    public static String onlyNumber(String input) {
        return input.replaceAll(REGEX_ONLY_NUMBER, "");
    }
}
