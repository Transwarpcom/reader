package com.script;

import java.io.Reader;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/ScriptEngine.class */
public interface ScriptEngine {
    public static final String FILENAME = "javax.script.filename";

    Bindings createBindings();

    Object eval(Reader reader) throws ScriptException;

    Object eval(Reader reader, Bindings bindings) throws ScriptException;

    Object eval(Reader reader, ScriptContext scriptContext) throws ScriptException;

    Object eval(String str) throws ScriptException;

    Object eval(String str, Bindings bindings) throws ScriptException;

    Object eval(String str, ScriptContext scriptContext) throws ScriptException;

    Object get(String str);

    Bindings getBindings(int i);

    ScriptContext getContext();

    void put(String str, Object obj);

    void setBindings(Bindings bindings, int i);

    void setContext(ScriptContext scriptContext);
}
