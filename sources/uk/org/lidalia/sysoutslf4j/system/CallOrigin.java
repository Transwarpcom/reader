package uk.org.lidalia.sysoutslf4j.system;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/system/CallOrigin.class */
final class CallOrigin {
    private final boolean printingStackTrace;
    private final String className;

    private CallOrigin(boolean isStacktrace, String className) {
        this.printingStackTrace = isStacktrace;
        this.className = className;
    }

    boolean isPrintingStackTrace() {
        return this.printingStackTrace;
    }

    String getClassName() {
        return this.className;
    }

    static CallOrigin getCallOrigin(StackTraceElement[] stackTraceElements, String libraryPackageName) {
        boolean isStackTrace = false;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            String className = stackTraceElement.getClassName();
            if (className.equals(Throwable.class.getName())) {
                isStackTrace = true;
            } else if (outsideThisLibrary(className, libraryPackageName)) {
                return new CallOrigin(isStackTrace, getOuterClassName(className));
            }
        }
        throw new IllegalStateException("Nothing in the stack originated from outside package name " + libraryPackageName);
    }

    private static boolean outsideThisLibrary(String className, String libraryPackageName) {
        return (className.equals(Thread.class.getName()) || className.startsWith(libraryPackageName)) ? false : true;
    }

    private static String getOuterClassName(String className) {
        String outerClassName;
        int startOfInnerClassName = className.indexOf(36);
        if (startOfInnerClassName == -1) {
            outerClassName = className;
        } else {
            outerClassName = className.substring(0, startOfInnerClassName);
        }
        return outerClassName;
    }
}
