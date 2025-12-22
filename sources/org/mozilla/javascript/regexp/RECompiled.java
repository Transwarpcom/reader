package org.mozilla.javascript.regexp;

import java.io.Serializable;

/* compiled from: NativeRegExp.java */
/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/RECompiled.class */
class RECompiled implements Serializable {
    private static final long serialVersionUID = -6144956577595844213L;
    final char[] source;
    int parenCount;
    int flags;
    byte[] program;
    int classCount;
    RECharSet[] classList;
    int anchorCh = -1;

    RECompiled(String str) {
        this.source = str.toCharArray();
    }
}
