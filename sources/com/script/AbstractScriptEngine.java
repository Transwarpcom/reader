package com.script;

import java.io.Reader;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/AbstractScriptEngine.class */
public abstract class AbstractScriptEngine implements ScriptEngine {
    protected ScriptContext context;

    public AbstractScriptEngine() {
        this.context = new SimpleScriptContext();
    }

    public AbstractScriptEngine(Bindings n) {
        this();
        if (n == null) {
            throw new NullPointerException("n is null");
        }
        this.context.setBindings(n, 100);
    }

    @Override // com.script.ScriptEngine
    public void setContext(ScriptContext ctxt) {
        if (ctxt == null) {
            throw new NullPointerException("null context");
        }
        this.context = ctxt;
    }

    @Override // com.script.ScriptEngine
    public ScriptContext getContext() {
        return this.context;
    }

    @Override // com.script.ScriptEngine
    public Bindings getBindings(int scope) {
        if (scope == 200) {
            return this.context.getBindings(200);
        }
        if (scope == 100) {
            return this.context.getBindings(100);
        }
        throw new IllegalArgumentException("Invalid scope value.");
    }

    @Override // com.script.ScriptEngine
    public void setBindings(Bindings bindings, int scope) {
        if (scope == 200) {
            this.context.setBindings(bindings, 200);
        } else {
            if (scope == 100) {
                this.context.setBindings(bindings, 100);
                return;
            }
            throw new IllegalArgumentException("Invalid scope value.");
        }
    }

    @Override // com.script.ScriptEngine
    public void put(String key, Object value) {
        Bindings nn = getBindings(100);
        if (nn != null) {
            nn.put(key, value);
        }
    }

    @Override // com.script.ScriptEngine
    public Object get(String key) {
        Bindings nn = getBindings(100);
        if (nn != null) {
            return nn.get(key);
        }
        return null;
    }

    @Override // com.script.ScriptEngine
    public Object eval(Reader reader, Bindings bindings) throws ScriptException {
        return eval(reader, getScriptContext(bindings));
    }

    @Override // com.script.ScriptEngine
    public Object eval(String script, Bindings bindings) throws ScriptException {
        return eval(script, getScriptContext(bindings));
    }

    @Override // com.script.ScriptEngine
    public Object eval(Reader reader) throws ScriptException {
        return eval(reader, this.context);
    }

    @Override // com.script.ScriptEngine
    public Object eval(String script) throws ScriptException {
        return eval(script, this.context);
    }

    protected ScriptContext getScriptContext(Bindings nn) {
        SimpleScriptContext ctxt = new SimpleScriptContext();
        Bindings gs = getBindings(200);
        if (gs != null) {
            ctxt.setBindings(gs, 200);
        }
        if (nn != null) {
            ctxt.setBindings(nn, 100);
            ctxt.setReader(this.context.getReader());
            ctxt.setWriter(this.context.getWriter());
            ctxt.setErrorWriter(this.context.getErrorWriter());
            return ctxt;
        }
        throw new NullPointerException("Engine scope Bindings may not be null.");
    }
}
