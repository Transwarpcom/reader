package org.mozilla.javascript;

import org.mozilla.javascript.PolicySecurityController;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/RhinoSecurityManager.class */
public class RhinoSecurityManager extends SecurityManager {
    protected Class<?> getCurrentScriptClass() {
        Class<?>[] context = getClassContext();
        for (Class<?> c : context) {
            if ((c != InterpretedFunction.class && NativeFunction.class.isAssignableFrom(c)) || PolicySecurityController.SecureCaller.class.isAssignableFrom(c)) {
                return c;
            }
        }
        return null;
    }
}
