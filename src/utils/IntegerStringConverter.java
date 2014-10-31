package utils;

import javafx.util.StringConverter;

public class IntegerStringConverter extends StringConverter<Integer> {

    @Override
    public Integer fromString(String value) {
        // If the specified value is null or zero-length, return null
        if (value == null) {
            return null;
        }

        // Take off the spaces
        value = value.trim();

        // If it's 0 (Was full of spaces) return 0
        if (value.length() < 1) {
            return null;
        }
        int valueConverted = 0;
        try {
            valueConverted = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            System.err.println("[ERROR-INFO (IntegerString Converter)] - " + e);
        }
        return valueConverted;
    }

    @Override
    public String toString(Integer value) {
        // If the specified value is null, return a zero-length String
        if (value == null) {
            return "";
        }
        return (Integer.toString(((Integer) value).intValue()));
    }
}
