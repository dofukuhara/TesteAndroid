package br.com.fukuhara.douglas.investmentapp.util;

/*
    TYPE Enum class to represent the type value from Cells JSON
*/
public enum Type {
    FIELD(1),
    TEXT(2),
    IMAGE(3),
    CHECKBOX(4),
    SEND(5);

    private int mValueOfType;

    Type(int type) {
        this.mValueOfType = type;
    }


    public boolean isTypeMatch(int outsideValue) {
        if (this.mValueOfType == outsideValue) {
            return true;
        }
        return false;
    }

}
