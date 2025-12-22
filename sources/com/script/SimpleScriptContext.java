package com.script;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/SimpleScriptContext.class */
public class SimpleScriptContext implements ScriptContext {
    private static List<Integer> scopes;
    protected Bindings engineScope = new SimpleBindings();
    protected Writer errorWriter = new PrintWriter((OutputStream) System.err, true);
    protected Bindings globalScope = null;
    protected Reader reader = new InputStreamReader(System.in);
    protected Writer writer = new PrintWriter((OutputStream) System.out, true);

    @Override // com.script.ScriptContext
    public void setBindings(Bindings bindings, int scope) {
        switch (scope) {
            case 100:
                if (bindings == null) {
                    throw new NullPointerException("Engine scope cannot be null.");
                }
                this.engineScope = bindings;
                return;
            case 200:
                this.globalScope = bindings;
                return;
            default:
                throw new IllegalArgumentException("Invalid scope value.");
        }
    }

    @Override // com.script.ScriptContext
    public Object getAttribute(String name) {
        if (this.engineScope.containsKey(name)) {
            return getAttribute(name, 100);
        }
        if (this.globalScope == null || !this.globalScope.containsKey(name)) {
            return null;
        }
        return getAttribute(name, 200);
    }

    @Override // com.script.ScriptContext
    public Object getAttribute(String name, int scope) {
        switch (scope) {
            case 100:
                return this.engineScope.get(name);
            case 200:
                if (this.globalScope != null) {
                    return this.globalScope.get(name);
                }
                return null;
            default:
                throw new IllegalArgumentException("Illegal scope value.");
        }
    }

    @Override // com.script.ScriptContext
    public Object removeAttribute(String name, int scope) {
        switch (scope) {
            case 100:
                if (getBindings(100) != null) {
                    return getBindings(100).remove(name);
                }
                return null;
            case 200:
                if (getBindings(200) != null) {
                    return getBindings(200).remove(name);
                }
                return null;
            default:
                throw new IllegalArgumentException("Illegal scope value.");
        }
    }

    @Override // com.script.ScriptContext
    public void setAttribute(String name, Object value, int scope) {
        switch (scope) {
            case 100:
                this.engineScope.put(name, value);
                return;
            case 200:
                if (this.globalScope != null) {
                    this.globalScope.put(name, value);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("Illegal scope value.");
        }
    }

    @Override // com.script.ScriptContext
    public Writer getWriter() {
        return this.writer;
    }

    @Override // com.script.ScriptContext
    public Reader getReader() {
        return this.reader;
    }

    @Override // com.script.ScriptContext
    public void setReader(Reader reader2) {
        this.reader = reader2;
    }

    @Override // com.script.ScriptContext
    public void setWriter(Writer writer2) {
        this.writer = writer2;
    }

    @Override // com.script.ScriptContext
    public Writer getErrorWriter() {
        return this.errorWriter;
    }

    @Override // com.script.ScriptContext
    public void setErrorWriter(Writer writer2) {
        this.errorWriter = writer2;
    }

    @Override // com.script.ScriptContext
    public int getAttributesScope(String name) {
        if (this.engineScope.containsKey(name)) {
            return 100;
        }
        if (this.globalScope == null || !this.globalScope.containsKey(name)) {
            return -1;
        }
        return 200;
    }

    @Override // com.script.ScriptContext
    public Bindings getBindings(int scope) {
        if (scope == 100) {
            return this.engineScope;
        }
        if (scope == 200) {
            return this.globalScope;
        }
        throw new IllegalArgumentException("Illegal scope value.");
    }

    @Override // com.script.ScriptContext
    public List<Integer> getScopes() {
        return scopes;
    }

    static {
        scopes = new ArrayList(2);
        scopes.add(100);
        scopes.add(200);
        scopes = Collections.unmodifiableList(scopes);
    }
}
