package io.vertx.core.impl;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/Utils.class */
public class Utils {
    public static String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final boolean isWindows;

    static {
        String os = System.getProperty("os.name").toLowerCase();
        isWindows = os.contains("win");
    }

    public static boolean isWindows() {
        return isWindows;
    }
}
