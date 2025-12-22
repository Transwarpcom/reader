package com.script;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/CompiledScript.class */
public abstract class CompiledScript {
    public abstract Object eval(ScriptContext scriptContext) throws ScriptException;

    public abstract ScriptEngine getEngine();

    public Object eval(Bindings bindings) throws ScriptException {
        ScriptContext ctxt = getEngine().getContext();
        if (bindings != null) {
            SimpleScriptContext tempctxt = new SimpleScriptContext();
            tempctxt.setBindings(bindings, 100);
            tempctxt.setBindings(ctxt.getBindings(200), 200);
            tempctxt.setWriter(ctxt.getWriter());
            tempctxt.setReader(ctxt.getReader());
            tempctxt.setErrorWriter(ctxt.getErrorWriter());
            ctxt = tempctxt;
        }
        return eval(ctxt);
    }

    public Object eval() throws ScriptException {
        return eval(getEngine().getContext());
    }
}
