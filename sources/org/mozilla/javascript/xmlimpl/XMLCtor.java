package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/xmlimpl/XMLCtor.class */
class XMLCtor extends IdFunctionObject {
    static final long serialVersionUID = -8708195078359817341L;
    private static final Object XMLCTOR_TAG = "XMLCtor";
    private XmlProcessor options;
    private static final int Id_ignoreComments = 1;
    private static final int Id_ignoreProcessingInstructions = 2;
    private static final int Id_ignoreWhitespace = 3;
    private static final int Id_prettyIndent = 4;
    private static final int Id_prettyPrinting = 5;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int Id_defaultSettings = 1;
    private static final int Id_settings = 2;
    private static final int Id_setSettings = 3;
    private static final int MAX_FUNCTION_ID = 3;

    XMLCtor(XML xml, Object tag, int id, int arity) {
        super(xml, tag, id, arity);
        this.options = xml.getProcessor();
        activatePrototypeMap(3);
    }

    private void writeSetting(Scriptable target) {
        for (int i = 1; i <= 5; i++) {
            int id = super.getMaxInstanceId() + i;
            String name = getInstanceIdName(id);
            Object value = getInstanceIdValue(id);
            ScriptableObject.putProperty(target, name, value);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readSettings(org.mozilla.javascript.Scriptable r5) {
        /*
            r4 = this;
            r0 = 1
            r6 = r0
        L2:
            r0 = r6
            r1 = 5
            if (r0 > r1) goto L77
            r0 = r4
            int r0 = super.getMaxInstanceId()
            r1 = r6
            int r0 = r0 + r1
            r7 = r0
            r0 = r4
            r1 = r7
            java.lang.String r0 = r0.getInstanceIdName(r1)
            r8 = r0
            r0 = r5
            r1 = r8
            java.lang.Object r0 = org.mozilla.javascript.ScriptableObject.getProperty(r0, r1)
            r9 = r0
            r0 = r9
            java.lang.Object r1 = org.mozilla.javascript.Scriptable.NOT_FOUND
            if (r0 != r1) goto L28
            goto L71
        L28:
            r0 = r6
            switch(r0) {
                case 1: goto L4c;
                case 2: goto L4c;
                case 3: goto L4c;
                case 4: goto L57;
                case 5: goto L4c;
                default: goto L62;
            }
        L4c:
            r0 = r9
            boolean r0 = r0 instanceof java.lang.Boolean
            if (r0 != 0) goto L6a
            goto L71
        L57:
            r0 = r9
            boolean r0 = r0 instanceof java.lang.Number
            if (r0 != 0) goto L6a
            goto L71
        L62:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r1.<init>()
            throw r0
        L6a:
            r0 = r4
            r1 = r7
            r2 = r9
            r0.setInstanceIdValue(r1, r2)
        L71:
            int r6 = r6 + 1
            goto L2
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLCtor.readSettings(org.mozilla.javascript.Scriptable):void");
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return super.getMaxInstanceId() + 5;
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String s) {
        int id = 0;
        String X = null;
        switch (s.length()) {
            case 12:
                X = "prettyIndent";
                id = 4;
                break;
            case 14:
                int c = s.charAt(0);
                if (c != 105) {
                    if (c == 112) {
                        X = "prettyPrinting";
                        id = 5;
                        break;
                    }
                } else {
                    X = "ignoreComments";
                    id = 1;
                    break;
                }
                break;
            case 16:
                X = "ignoreWhitespace";
                id = 3;
                break;
            case 28:
                X = "ignoreProcessingInstructions";
                id = 2;
                break;
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        if (id == 0) {
            return super.findInstanceIdInfo(s);
        }
        switch (id) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return instanceIdInfo(6, super.getMaxInstanceId() + id);
            default:
                throw new IllegalStateException();
        }
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int id) {
        switch (id - super.getMaxInstanceId()) {
            case 1:
                return "ignoreComments";
            case 2:
                return "ignoreProcessingInstructions";
            case 3:
                return "ignoreWhitespace";
            case 4:
                return "prettyIndent";
            case 5:
                return "prettyPrinting";
            default:
                return super.getInstanceIdName(id);
        }
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int id) {
        switch (id - super.getMaxInstanceId()) {
            case 1:
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreComments());
            case 2:
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreProcessingInstructions());
            case 3:
                return ScriptRuntime.wrapBoolean(this.options.isIgnoreWhitespace());
            case 4:
                return ScriptRuntime.wrapInt(this.options.getPrettyIndent());
            case 5:
                return ScriptRuntime.wrapBoolean(this.options.isPrettyPrinting());
            default:
                return super.getInstanceIdValue(id);
        }
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int id, Object value) {
        switch (id - super.getMaxInstanceId()) {
            case 1:
                this.options.setIgnoreComments(ScriptRuntime.toBoolean(value));
                break;
            case 2:
                this.options.setIgnoreProcessingInstructions(ScriptRuntime.toBoolean(value));
                break;
            case 3:
                this.options.setIgnoreWhitespace(ScriptRuntime.toBoolean(value));
                break;
            case 4:
                this.options.setPrettyIndent(ScriptRuntime.toInt32(value));
                break;
            case 5:
                this.options.setPrettyPrinting(ScriptRuntime.toBoolean(value));
                break;
            default:
                super.setInstanceIdValue(id, value);
                break;
        }
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String s) {
        int id = 0;
        String X = null;
        int s_length = s.length();
        if (s_length == 8) {
            X = "settings";
            id = 2;
        } else if (s_length == 11) {
            X = "setSettings";
            id = 3;
        } else if (s_length == 15) {
            X = "defaultSettings";
            id = 1;
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        return id;
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int id) {
        int arity;
        String s;
        switch (id) {
            case 1:
                arity = 0;
                s = "defaultSettings";
                break;
            case 2:
                arity = 0;
                s = "settings";
                break;
            case 3:
                arity = 1;
                s = "setSettings";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(XMLCTOR_TAG, id, s, arity);
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(XMLCTOR_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1:
                this.options.setDefault();
                Scriptable obj = cx.newObject(scope);
                writeSetting(obj);
                return obj;
            case 2:
                Scriptable obj2 = cx.newObject(scope);
                writeSetting(obj2);
                return obj2;
            case 3:
                if (args.length == 0 || args[0] == null || args[0] == Undefined.instance) {
                    this.options.setDefault();
                } else if (args[0] instanceof Scriptable) {
                    readSettings((Scriptable) args[0]);
                }
                return Undefined.instance;
            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean hasInstance(Scriptable instance) {
        return (instance instanceof XML) || (instance instanceof XMLList);
    }
}
