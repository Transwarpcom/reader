package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

/* compiled from: RegExpImpl.java */
/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/GlobData.class */
final class GlobData {
    int mode;
    boolean global;
    String str;
    Scriptable arrayobj;
    Function lambda;
    String repstr;
    int dollar = -1;
    StringBuilder charBuf;
    int leftIndex;

    GlobData() {
    }
}
