package org.mozilla.javascript;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ConstProperties.class */
public interface ConstProperties {
    void putConst(String str, Scriptable scriptable, Object obj);

    void defineConst(String str, Scriptable scriptable);

    boolean isConst(String str);
}
