package com.script;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/Invocable.class */
public interface Invocable {
    <T> T getInterface(Class<T> cls);

    <T> T getInterface(Object obj, Class<T> cls);

    Object invokeFunction(String str, Object... objArr) throws NoSuchMethodException, ScriptException;

    Object invokeMethod(Object obj, String str, Object... objArr) throws NoSuchMethodException, ScriptException;
}
