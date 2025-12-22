package com.mongodb.internal.connection;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DecimalFormatHelper.class */
public final class DecimalFormatHelper {
    private static final DecimalFormatSymbols DECIMAL_FORMAT_SYMBOLS = initializeDecimalFormatSymbols();

    private DecimalFormatHelper() {
    }

    private static DecimalFormatSymbols initializeDecimalFormatSymbols() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        return decimalFormatSymbols;
    }

    public static String format(String pattern, double number) {
        return new DecimalFormat(pattern, DECIMAL_FORMAT_SYMBOLS).format(number);
    }
}
