package com.script.javascript;

import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.ClassShutter;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/RhinoClassShutter.class */
final class RhinoClassShutter implements ClassShutter {
    private static Map<String, Boolean> protectedClasses;
    private static RhinoClassShutter theInstance;

    private RhinoClassShutter() {
    }

    static synchronized ClassShutter getInstance() {
        if (theInstance == null) {
            theInstance = new RhinoClassShutter();
            protectedClasses = new HashMap();
            protectedClasses.put("java.lang.Runtime", Boolean.TRUE);
            protectedClasses.put("java.io.File", Boolean.TRUE);
            protectedClasses.put("java.security.AccessController", Boolean.TRUE);
        }
        return theInstance;
    }

    @Override // org.mozilla.javascript.ClassShutter
    public boolean visibleToScripts(String fullClassName) {
        int i;
        SecurityManager sm = System.getSecurityManager();
        if (sm != null && (i = fullClassName.lastIndexOf(".")) != -1) {
            try {
                sm.checkPackageAccess(fullClassName.substring(0, i));
            } catch (SecurityException e) {
                return false;
            }
        }
        return protectedClasses.get(fullClassName) == null;
    }
}
