package br.com.fukuhara.douglas.investmentapp.util;

import android.text.TextUtils;

public class Utils {

    // Utils function to format number, from "x.y" to "x,y%"
    public static String formatPercentage(String number) {
        return number.replace(".", ",") + "%";
    }

    // Utils function to retrieve a Type enum based on it's correlated ID
    public static Type getType(String queryParam) {
        if (queryParam == null || TextUtils.isEmpty(queryParam)) {
            return null;
        } else if (queryParam.trim().matches("^\\d+$")) {
            return getTypeById(Integer.valueOf(queryParam.trim()));
        } else {
            return null;
        }
    }

    private static Type getTypeById(int id) {
        Type customType = null;

        for (Type type : Type.values()) {
            if(type.isTypeMatch(id)) {
                customType = type;
                break;
            }
        }

        return customType;
    }

    // Utils frunction to retrieve a TypeField enum based on it's correlated ID
    public static TypeField getTypeField(String queryParam) {
        if (queryParam == null || TextUtils.isEmpty(queryParam)) {
            return null;
        } else if (queryParam.trim().matches("^\\d+$")) {
            return getTypeFieldById(Integer.valueOf(queryParam.trim()));
        } else {
            return getTypeFieldByName(queryParam.trim());
        }
    }

    private static TypeField getTypeFieldById(int id) {
        TypeField customTypeField = null;

        for (TypeField typeField : TypeField.values()) {
            if(typeField.isTypeMatch(id)) {
                customTypeField = typeField;
                break;
            }
        }

        return customTypeField;
    }

    private static TypeField getTypeFieldByName(String name) {
        TypeField customTypeField = null;

        for (TypeField typeField : TypeField.values()) {
            if (typeField.toString().toLowerCase().equals(name.trim().toLowerCase())) {
                customTypeField = typeField;
                break;
            }
        }
        return customTypeField;
    }
}
