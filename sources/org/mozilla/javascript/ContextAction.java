package org.mozilla.javascript;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ContextAction.class */
public interface ContextAction<T> {
    T run(Context context);
}
