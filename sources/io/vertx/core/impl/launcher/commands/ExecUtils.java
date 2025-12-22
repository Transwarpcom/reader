package io.vertx.core.impl.launcher.commands;

import io.vertx.core.impl.Utils;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/ExecUtils.class */
public class ExecUtils {
    private static final String SINGLE_QUOTE = "'";
    private static final String DOUBLE_QUOTE = "\"";
    public static final int VERTX_INITIALIZATION_EXIT_CODE = 11;
    public static final int VERTX_DEPLOYMENT_EXIT_CODE = 15;
    public static final int PROCESS_ERROR_EXIT_CODE = 12;
    public static final int SYSTEM_CONFIGURATION_EXIT_CODE = 14;
    private static String osName = System.getProperty("os.name").toLowerCase();

    public static String quoteArgument(String argument) {
        String cleanedArgument;
        String strTrim = argument.trim();
        while (true) {
            cleanedArgument = strTrim;
            if ((!cleanedArgument.startsWith("'") || !cleanedArgument.endsWith("'")) && (!cleanedArgument.startsWith("\"") || !cleanedArgument.endsWith("\""))) {
                break;
            }
            strTrim = cleanedArgument.substring(1, cleanedArgument.length() - 1);
        }
        StringBuilder buf = new StringBuilder();
        if (cleanedArgument.contains("\"")) {
            if (cleanedArgument.contains("'")) {
                throw new IllegalArgumentException("Can't handle single and double quotes in same argument");
            }
            if (Utils.isWindows()) {
                return buf.append("\"").append(cleanedArgument.replace("\"", "\\\"")).append("\"").toString();
            }
            return buf.append("'").append(cleanedArgument).append("'").toString();
        }
        if (cleanedArgument.contains("'") || cleanedArgument.contains(" ")) {
            return buf.append("\"").append(cleanedArgument).append("\"").toString();
        }
        return cleanedArgument;
    }

    public static void addArgument(List<String> args, String argument) {
        args.add(quoteArgument(argument));
    }

    public static boolean isWindows() {
        return osName.contains("windows");
    }

    public static boolean isLinux() {
        return osName.contains("nux");
    }

    public static void exit(int code) {
        System.exit(code);
    }

    public static void exitBecauseOfVertxInitializationIssue() {
        exit(11);
    }

    public static void exitBecauseOfVertxDeploymentIssue() {
        exit(15);
    }

    public static void exitBecauseOfProcessIssue() {
        exit(12);
    }

    public static void exitBecauseOfSystemConfigurationIssue() {
        exit(14);
    }
}
