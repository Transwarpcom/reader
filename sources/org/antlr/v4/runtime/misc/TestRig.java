package org.antlr.v4.runtime.misc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/TestRig.class */
public class TestRig {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> testRigClass = Class.forName("org.antlr.v4.gui.TestRig");
            System.err.println("Warning: TestRig moved to org.antlr.v4.gui.TestRig; calling automatically");
            try {
                Method mainMethod = testRigClass.getMethod("main", String[].class);
                mainMethod.invoke(null, args);
            } catch (Exception e) {
                System.err.println("Problems calling org.antlr.v4.gui.TestRig.main(args)");
            }
        } catch (ClassNotFoundException e2) {
            System.err.println("Use of TestRig now requires the use of the tool jar, antlr-4.X-complete.jar");
            System.err.println("Maven users need group ID org.antlr and artifact ID antlr4");
        }
    }
}
