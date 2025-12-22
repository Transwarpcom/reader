package com.script;

import java.io.Reader;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/Compilable.class */
public interface Compilable {
    CompiledScript compile(Reader reader) throws ScriptException;

    CompiledScript compile(String str) throws ScriptException;
}
