package com.script.javascript;

import com.script.CompiledScript;
import com.script.ScriptContext;
import com.script.ScriptEngine;
import com.script.ScriptException;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/javascript/RhinoCompiledScript.class */
final class RhinoCompiledScript extends CompiledScript {
    private RhinoScriptEngine engine;
    private Script script;

    RhinoCompiledScript(RhinoScriptEngine engine, Script script) {
        this.engine = engine;
        this.script = script;
    }

    @Override // com.script.CompiledScript
    public Object eval(ScriptContext context) throws ScriptException {
        String msg;
        Context cx = Context.enter();
        try {
            try {
                Scriptable scope = this.engine.getRuntimeScope(context);
                Object ret = this.script.exec(cx, scope);
                Object result = this.engine.unwrapReturnValue(ret);
                Context.exit();
                return result;
            } catch (RhinoException re) {
                int line = re.lineNumber();
                int line2 = line == 0 ? -1 : line;
                if (re instanceof JavaScriptException) {
                    msg = String.valueOf(((JavaScriptException) re).getValue());
                } else {
                    msg = re.toString();
                }
                ScriptException se = new ScriptException(msg, re.sourceName(), line2);
                se.initCause(re);
                throw se;
            }
        } catch (Throwable th) {
            Context.exit();
            throw th;
        }
    }

    @Override // com.script.CompiledScript
    public ScriptEngine getEngine() {
        return this.engine;
    }
}
