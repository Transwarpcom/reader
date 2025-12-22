package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;

/* compiled from: NativeRegExp.java */
/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/CompilerState.class */
class CompilerState {
    Context cx;
    char[] cpbegin;
    int cpend;
    int flags;
    int parenNesting;
    RENode result;
    int cp = 0;
    int backReferenceLimit = Integer.MAX_VALUE;
    int maxBackReference = 0;
    int parenCount = 0;
    int classCount = 0;
    int progLength = 0;

    CompilerState(Context cx, char[] source, int length, int flags) {
        this.cx = cx;
        this.cpbegin = source;
        this.cpend = length;
        this.flags = flags;
    }
}
