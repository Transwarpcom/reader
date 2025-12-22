package com.script;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/ScriptContext.class */
public interface ScriptContext {
    public static final int ENGINE_SCOPE = 100;
    public static final int GLOBAL_SCOPE = 200;

    Object getAttribute(String str);

    Object getAttribute(String str, int i);

    int getAttributesScope(String str);

    Bindings getBindings(int i);

    Writer getErrorWriter();

    Reader getReader();

    List<Integer> getScopes();

    Writer getWriter();

    Object removeAttribute(String str, int i);

    void setAttribute(String str, Object obj, int i);

    void setBindings(Bindings bindings, int i);

    void setErrorWriter(Writer writer);

    void setReader(Reader reader);

    void setWriter(Writer writer);
}
