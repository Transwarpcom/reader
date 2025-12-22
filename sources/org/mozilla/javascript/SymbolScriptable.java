package org.mozilla.javascript;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/SymbolScriptable.class */
public interface SymbolScriptable {
    Object get(Symbol symbol, Scriptable scriptable);

    boolean has(Symbol symbol, Scriptable scriptable);

    void put(Symbol symbol, Scriptable scriptable, Object obj);

    void delete(Symbol symbol);
}
