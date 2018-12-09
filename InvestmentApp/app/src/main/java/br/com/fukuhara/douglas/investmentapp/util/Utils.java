package br.com.fukuhara.douglas.investmentapp.util;

public class Utils {

    public static String formatPercentage(String number) {
        return number.replace(".", ",") + "%";
    }
}
