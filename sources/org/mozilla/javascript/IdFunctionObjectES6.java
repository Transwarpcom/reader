package org.mozilla.javascript;

import com.jayway.jsonpath.internal.function.text.Length;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/IdFunctionObjectES6.class */
public class IdFunctionObjectES6 extends IdFunctionObject {
    private static final long serialVersionUID = -8023088662589035261L;
    private static final int Id_length = 1;
    private static final int Id_name = 3;
    private boolean myLength;
    private boolean myName;

    public IdFunctionObjectES6(IdFunctionCall idcall, Object tag, int id, String name, int arity, Scriptable scope) {
        super(idcall, tag, id, name, arity, scope);
        this.myLength = true;
        this.myName = true;
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String s) {
        return s.equals(Length.TOKEN_NAME) ? instanceIdInfo(3, 1) : s.equals("name") ? instanceIdInfo(3, 3) : super.findInstanceIdInfo(s);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int id) {
        if (id == 1 && !this.myLength) {
            return NOT_FOUND;
        }
        if (id == 3 && !this.myName) {
            return NOT_FOUND;
        }
        return super.getInstanceIdValue(id);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int id, Object value) {
        if (id == 1 && value == NOT_FOUND) {
            this.myLength = false;
        } else if (id == 3 && value == NOT_FOUND) {
            this.myName = false;
        } else {
            super.setInstanceIdValue(id, value);
        }
    }
}
