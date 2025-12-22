package com.mongodb;

import java.util.Arrays;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/ErrorCategory.class */
public enum ErrorCategory {
    UNCATEGORIZED,
    DUPLICATE_KEY,
    EXECUTION_TIMEOUT;

    private static final List<Integer> DUPLICATE_KEY_ERROR_CODES = Arrays.asList(11000, 11001, 12582);
    private static final List<Integer> EXECUTION_TIMEOUT_ERROR_CODES = Arrays.asList(50);

    public static ErrorCategory fromErrorCode(int code) {
        if (DUPLICATE_KEY_ERROR_CODES.contains(Integer.valueOf(code))) {
            return DUPLICATE_KEY;
        }
        if (EXECUTION_TIMEOUT_ERROR_CODES.contains(Integer.valueOf(code))) {
            return EXECUTION_TIMEOUT;
        }
        return UNCATEGORIZED;
    }
}
