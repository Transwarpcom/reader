package org.mozilla.javascript;

import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeJavaConstructor.class */
public class NativeJavaConstructor extends BaseFunction {
    private static final long serialVersionUID = -8149253217482668463L;
    MemberBox ctor;

    public NativeJavaConstructor(MemberBox ctor) {
        this.ctor = ctor;
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.Function, org.mozilla.javascript.Callable
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return NativeJavaClass.constructSpecific(cx, scope, args, this.ctor);
    }

    @Override // org.mozilla.javascript.BaseFunction
    public String getFunctionName() {
        String sig = JavaMembers.liveConnectSignature(this.ctor.argTypes);
        return Constants.CONSTRUCTOR_NAME.concat(sig);
    }

    public String toString() {
        return "[JavaConstructor " + this.ctor.getName() + "]";
    }
}
