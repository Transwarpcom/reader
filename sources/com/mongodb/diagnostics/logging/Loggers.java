package com.mongodb.diagnostics.logging;

import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/diagnostics/logging/Loggers.class */
public final class Loggers {
    public static final String PREFIX = "org.mongodb.driver";
    private static final boolean USE_SLF4J = shouldUseSLF4J();

    public static Logger getLogger(String suffix) {
        Assertions.notNull("suffix", suffix);
        if (suffix.startsWith(".") || suffix.endsWith(".")) {
            throw new IllegalArgumentException("The suffix can not start or end with a '.'");
        }
        String name = "org.mongodb.driver." + suffix;
        if (USE_SLF4J) {
            return new SLF4JLogger(name);
        }
        return new JULLogger(name);
    }

    private Loggers() {
    }

    private static boolean shouldUseSLF4J() throws ClassNotFoundException {
        try {
            Class.forName("org.slf4j.Logger");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
