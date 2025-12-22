package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/NativeRegExpCallable.class */
class NativeRegExpCallable extends NativeRegExp implements Function {
    NativeRegExpCallable(Scriptable scope, RECompiled compiled) {
        super(scope, compiled);
    }

    NativeRegExpCallable() {
    }

    @Override // org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return execSub(cx, scope, args, 1);
    }

    @Override // org.mozilla.javascript.Function
    public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
        return (Scriptable) execSub(cx, scope, args, 1);
    }
}
