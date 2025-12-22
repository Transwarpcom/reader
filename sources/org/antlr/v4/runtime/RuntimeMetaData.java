package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/RuntimeMetaData.class */
public class RuntimeMetaData {
    public static final String VERSION = "4.7.2";

    public static String getRuntimeVersion() {
        return VERSION;
    }

    public static void checkVersion(String generatingToolVersion, String compileTimeVersion) {
        boolean runtimeConflictsWithGeneratingTool = false;
        if (generatingToolVersion != null) {
            runtimeConflictsWithGeneratingTool = (VERSION.equals(generatingToolVersion) || getMajorMinorVersion(VERSION).equals(getMajorMinorVersion(generatingToolVersion))) ? false : true;
        }
        boolean runtimeConflictsWithCompileTimeTool = (VERSION.equals(compileTimeVersion) || getMajorMinorVersion(VERSION).equals(getMajorMinorVersion(compileTimeVersion))) ? false : true;
        if (runtimeConflictsWithGeneratingTool) {
            System.err.printf("ANTLR Tool version %s used for code generation does not match the current runtime version %s", generatingToolVersion, VERSION);
        }
        if (runtimeConflictsWithCompileTimeTool) {
            System.err.printf("ANTLR Runtime version %s used for parser compilation does not match the current runtime version %s", compileTimeVersion, VERSION);
        }
    }

    public static String getMajorMinorVersion(String version) {
        int firstDot = version.indexOf(46);
        int secondDot = firstDot >= 0 ? version.indexOf(46, firstDot + 1) : -1;
        int firstDash = version.indexOf(45);
        int referenceLength = version.length();
        if (secondDot >= 0) {
            referenceLength = Math.min(referenceLength, secondDot);
        }
        if (firstDash >= 0) {
            referenceLength = Math.min(referenceLength, firstDash);
        }
        return version.substring(0, referenceLength);
    }
}
