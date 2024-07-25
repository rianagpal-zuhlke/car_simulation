package org.autodrivingcar.utils;

public class StringFormatter {
    public static String normalizeLineSeparators(String text) {
        return text.replace("\r\n", "\n").replace("\r", "\n");
    }
}
