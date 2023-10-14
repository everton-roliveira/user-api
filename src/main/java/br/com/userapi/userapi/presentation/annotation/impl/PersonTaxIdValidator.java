package br.com.userapi.userapi.presentation.annotation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.userapi.userapi.presentation.annotation.PersonTaxIdConstraint;

public class PersonTaxIdValidator implements ConstraintValidator<PersonTaxIdConstraint, String> {

    @Override
    public void initialize(PersonTaxIdConstraint personTaxIdConstraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Campo nulo é considerado válido
        }

        String cleanValue = value.replaceAll("[^0-9]", "");

        if (cleanValue.length() == 11) {
            return isValidCPF(cleanValue);
        } else if (cleanValue.length() == 14) {
            return isValidCNPJ(cleanValue);
        }

        return false;
    }

    public static boolean isValidCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica dígitos repetidos
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * (cpf.charAt(i) - '0');
        }
        int remainder = 11 - (sum % 11);
        int firstVerifier = (remainder == 10 || remainder == 11) ? 0 : remainder;

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * (cpf.charAt(i) - '0');
        }
        remainder = 11 - (sum % 11);
        int secondVerifier = (remainder == 10 || remainder == 11) ? 0 : remainder;

        // Verifica se os dígitos verificadores estão corretos
        return (cpf.charAt(9) - '0' == firstVerifier) && (cpf.charAt(10) - '0' == secondVerifier);
    }

    public static boolean isValidCNPJ(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verifica se o CNPJ tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica dígitos repetidos
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        int[] weights1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weights1[i];
        }
        int remainder1 = sum % 11;
        int firstVerifier = (remainder1 < 2) ? 0 : 11 - remainder1;

        // Calcula o segundo dígito verificador
        int[] weights2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weights2[i];
        }
        int remainder2 = sum % 11;
        int secondVerifier = (remainder2 < 2) ? 0 : 11 - remainder2;

        // Verifica se os dígitos verificadores estão corretos
        return (cnpj.charAt(12) - '0' == firstVerifier) && (cnpj.charAt(13) - '0' == secondVerifier);
    }
}
