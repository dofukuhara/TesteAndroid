package br.com.fukuhara.douglas.investmentapp.util;

/*
    TYPEFIELD Enum class to represent the type value from Cells JSON
*/
public enum TypeField {
    TEXT(1),
    TELNUMBER(2),
    EMAIL(3);

    private int mValueOfTypeField;

    TypeField(int typeField) {
        this.mValueOfTypeField = typeField;
    }

    public boolean isTypeMatch(int outsideValue) {
        if (this.mValueOfTypeField == outsideValue) {
            return true;
        }
        return false;
    }
}
