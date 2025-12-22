package org.mozilla.javascript;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/ScriptRuntimeES6.class */
public class ScriptRuntimeES6 {
    public static Object requireObjectCoercible(Context cx, Object val, IdFunctionObject idFuncObj) {
        if (val == null || Undefined.isUndefined(val)) {
            throw ScriptRuntime.typeError2("msg.called.null.or.undefined", idFuncObj.getTag(), idFuncObj.getFunctionName());
        }
        return val;
    }
}
