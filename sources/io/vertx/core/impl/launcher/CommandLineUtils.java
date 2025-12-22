package io.vertx.core.impl.launcher;

import java.io.File;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/CommandLineUtils.class */
public class CommandLineUtils {
    public static String getJar() {
        String segment = getFirstSegmentOfCommand();
        if (segment != null && segment.endsWith(".jar")) {
            return segment;
        }
        String classpath = System.getProperty("java.class.path");
        if (!classpath.isEmpty() && !classpath.contains(File.pathSeparator) && classpath.endsWith(".jar")) {
            return classpath;
        }
        return null;
    }

    public static String getCommand() {
        return System.getProperty("sun.java.command");
    }

    public static String getFirstSegmentOfCommand() {
        String cmd = getCommand();
        if (cmd != null) {
            String[] segments = cmd.split(" ");
            if (segments.length >= 1) {
                return segments[0];
            }
            return null;
        }
        return null;
    }
}
