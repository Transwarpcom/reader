package org.mozilla.javascript;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/RefCallable.class */
public interface RefCallable extends Callable {
    Ref refCall(Context context, Scriptable scriptable, Object[] objArr);
}
