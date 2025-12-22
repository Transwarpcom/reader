package org.mozilla.javascript.debug;

import org.mozilla.javascript.Context;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/debug/Debugger.class */
public interface Debugger {
    void handleCompilationDone(Context context, DebuggableScript debuggableScript, String str);

    DebugFrame getFrame(Context context, DebuggableScript debuggableScript);
}
