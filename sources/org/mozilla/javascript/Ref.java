package org.mozilla.javascript;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/Ref.class */
public abstract class Ref implements Serializable {
    private static final long serialVersionUID = 4044540354730911424L;

    public abstract Object get(Context context);

    @Deprecated
    public abstract Object set(Context context, Object obj);

    public boolean has(Context cx) {
        return true;
    }

    public Object set(Context cx, Scriptable scope, Object value) {
        return set(cx, value);
    }

    public boolean delete(Context cx) {
        return false;
    }
}
