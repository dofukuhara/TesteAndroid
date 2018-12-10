package br.com.fukuhara.douglas.investmentapp.contact;

public class ContactFieldValidator {

    public static boolean isNameValid(String name) {
        boolean result = false;

        if (name.matches("^.+$")) {
            result = true;
        }

        return result;
    }

    public static boolean isEmailValid(String email) {
        boolean result = false;

        if (email.matches("^[a-zA-Z][\\w._-]+@(\\w+\\.)+\\w+")) {
            result = true;
        }

        return result;
    }

    public static boolean isPhoneValid(String phone) {
        boolean result = false;

        if (phone.matches("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$")) {
            result = true;
        }

        return result;
    }
}
