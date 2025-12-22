package org.mozilla.javascript;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.Iterator;
import org.mozilla.javascript.Hashtable;
import org.mozilla.javascript.NativeCollectionIterator;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeMap.class */
public class NativeMap extends IdScriptableObject {
    private static final long serialVersionUID = 1171922614280016891L;
    static final String ITERATOR_TAG = "Map Iterator";
    private final Hashtable entries = new Hashtable();
    private boolean instanceOfMap = false;
    private static final int Id_constructor = 1;
    private static final int Id_set = 2;
    private static final int Id_get = 3;
    private static final int Id_delete = 4;
    private static final int Id_has = 5;
    private static final int Id_clear = 6;
    private static final int Id_keys = 7;
    private static final int Id_values = 8;
    private static final int Id_entries = 9;
    private static final int Id_forEach = 10;
    private static final int SymbolId_getSize = 11;
    private static final int SymbolId_toStringTag = 12;
    private static final int MAX_PROTOTYPE_ID = 12;
    private static final Object MAP_TAG = "Map";
    private static final Object NULL_VALUE = new Object();

    static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeMap nativeMap = new NativeMap();
        nativeMap.exportAsJSClass(12, scope, false);
        ScriptableObject scriptableObject = (ScriptableObject) cx.newObject(scope);
        scriptableObject.put("enumerable", scriptableObject, Boolean.FALSE);
        scriptableObject.put("configurable", scriptableObject, Boolean.TRUE);
        scriptableObject.put(BeanUtil.PREFIX_GETTER_GET, scriptableObject, nativeMap.get(NativeSet.GETSIZE, nativeMap));
        nativeMap.defineOwnProperty(cx, "size", scriptableObject);
        if (sealed) {
            nativeMap.sealObject();
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Map";
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(MAP_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1:
                if (thisObj == null) {
                    NativeMap nm = new NativeMap();
                    nm.instanceOfMap = true;
                    if (args.length > 0) {
                        loadFromIterable(cx, scope, nm, args[0]);
                    }
                    return nm;
                }
                throw ScriptRuntime.typeError1("msg.no.new", "Map");
            case 2:
                return realThis(thisObj, f).js_set(args.length > 0 ? args[0] : Undefined.instance, args.length > 1 ? args[1] : Undefined.instance);
            case 3:
                return realThis(thisObj, f).js_get(args.length > 0 ? args[0] : Undefined.instance);
            case 4:
                return realThis(thisObj, f).js_delete(args.length > 0 ? args[0] : Undefined.instance);
            case 5:
                return realThis(thisObj, f).js_has(args.length > 0 ? args[0] : Undefined.instance);
            case 6:
                return realThis(thisObj, f).js_clear();
            case 7:
                return realThis(thisObj, f).js_iterator(scope, NativeCollectionIterator.Type.KEYS);
            case 8:
                return realThis(thisObj, f).js_iterator(scope, NativeCollectionIterator.Type.VALUES);
            case 9:
                return realThis(thisObj, f).js_iterator(scope, NativeCollectionIterator.Type.BOTH);
            case 10:
                return realThis(thisObj, f).js_forEach(cx, scope, args.length > 0 ? args[0] : Undefined.instance, args.length > 1 ? args[1] : Undefined.instance);
            case 11:
                return realThis(thisObj, f).js_getSize();
            default:
                throw new IllegalArgumentException("Map.prototype has no method: " + f.getFunctionName());
        }
    }

    private Object js_set(Object k, Object v) {
        Object value = v == null ? NULL_VALUE : v;
        Object key = k;
        if ((key instanceof Number) && ((Number) key).doubleValue() == ScriptRuntime.negativeZero) {
            key = ScriptRuntime.zeroObj;
        }
        this.entries.put(key, value);
        return this;
    }

    private Object js_delete(Object arg) {
        Object e = this.entries.delete(arg);
        return Boolean.valueOf(e != null);
    }

    private Object js_get(Object arg) {
        Object val = this.entries.get(arg);
        if (val == null) {
            return Undefined.instance;
        }
        if (val == NULL_VALUE) {
            return null;
        }
        return val;
    }

    private Object js_has(Object arg) {
        return Boolean.valueOf(this.entries.has(arg));
    }

    private Object js_getSize() {
        return Integer.valueOf(this.entries.size());
    }

    private Object js_iterator(Scriptable scope, NativeCollectionIterator.Type type) {
        return new NativeCollectionIterator(scope, ITERATOR_TAG, type, this.entries.iterator());
    }

    private Object js_clear() {
        this.entries.clear();
        return Undefined.instance;
    }

    private Object js_forEach(Context cx, Scriptable scope, Object arg1, Object arg2) {
        if (!(arg1 instanceof Callable)) {
            throw ScriptRuntime.typeError2("msg.isnt.function", arg1, ScriptRuntime.typeof(arg1));
        }
        Callable f = (Callable) arg1;
        boolean isStrict = cx.isStrictMode();
        Iterator<Hashtable.Entry> i = this.entries.iterator();
        while (i.hasNext()) {
            Scriptable thisObj = ScriptRuntime.toObjectOrNull(cx, arg2, scope);
            if (thisObj == null && !isStrict) {
                thisObj = scope;
            }
            if (thisObj == null) {
                thisObj = Undefined.SCRIPTABLE_UNDEFINED;
            }
            Hashtable.Entry e = i.next();
            Object val = e.value;
            if (val == NULL_VALUE) {
                val = null;
            }
            f.call(cx, scope, thisObj, new Object[]{val, e.key, this});
        }
        return Undefined.instance;
    }

    static void loadFromIterable(Context cx, Scriptable scope, ScriptableObject map, Object arg1) {
        if (arg1 == null || Undefined.instance.equals(arg1)) {
            return;
        }
        Object ito = ScriptRuntime.callIterator(arg1, cx, scope);
        if (Undefined.instance.equals(ito)) {
            return;
        }
        ScriptableObject dummy = ensureScriptableObject(cx.newObject(scope, map.getClassName()));
        Callable set = ScriptRuntime.getPropFunctionAndThis(dummy.getPrototype(), "set", cx, scope);
        ScriptRuntime.lastStoredScriptable(cx);
        IteratorLikeIterable it = new IteratorLikeIterable(cx, scope, ito);
        Throwable th = null;
        try {
            try {
                Iterator<Object> itIterator2 = it.iterator2();
                while (itIterator2.hasNext()) {
                    Object val = itIterator2.next();
                    Scriptable sVal = ScriptableObject.ensureScriptable(val);
                    if (sVal instanceof Symbol) {
                        throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(sVal));
                    }
                    Object finalKey = sVal.get(0, sVal);
                    if (finalKey == NOT_FOUND) {
                        finalKey = Undefined.instance;
                    }
                    Object finalVal = sVal.get(1, sVal);
                    if (finalVal == NOT_FOUND) {
                        finalVal = Undefined.instance;
                    }
                    set.call(cx, scope, map, new Object[]{finalKey, finalVal});
                }
                if (it != null) {
                    if (0 != 0) {
                        try {
                            it.close();
                            return;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            return;
                        }
                    }
                    it.close();
                }
            } catch (Throwable th3) {
                th = th3;
                throw th3;
            }
        } catch (Throwable th4) {
            if (it != null) {
                if (th != null) {
                    try {
                        it.close();
                    } catch (Throwable th5) {
                        th.addSuppressed(th5);
                    }
                } else {
                    it.close();
                }
            }
            throw th4;
        }
    }

    private static NativeMap realThis(Scriptable thisObj, IdFunctionObject f) {
        if (thisObj == null) {
            throw incompatibleCallError(f);
        }
        try {
            NativeMap nm = (NativeMap) thisObj;
            if (!nm.instanceOfMap) {
                throw incompatibleCallError(f);
            }
            return nm;
        } catch (ClassCastException e) {
            throw incompatibleCallError(f);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int id) {
        int arity;
        String s;
        switch (id) {
            case 11:
                initPrototypeMethod(MAP_TAG, id, NativeSet.GETSIZE, "get size", 0);
                return;
            case 12:
                initPrototypeValue(12, SymbolKey.TO_STRING_TAG, getClassName(), 3);
                return;
            default:
                switch (id) {
                    case 1:
                        arity = 0;
                        s = BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE;
                        break;
                    case 2:
                        arity = 2;
                        s = "set";
                        break;
                    case 3:
                        arity = 1;
                        s = BeanUtil.PREFIX_GETTER_GET;
                        break;
                    case 4:
                        arity = 1;
                        s = "delete";
                        break;
                    case 5:
                        arity = 1;
                        s = "has";
                        break;
                    case 6:
                        arity = 0;
                        s = "clear";
                        break;
                    case 7:
                        arity = 0;
                        s = "keys";
                        break;
                    case 8:
                        arity = 0;
                        s = "values";
                        break;
                    case 9:
                        arity = 0;
                        s = "entries";
                        break;
                    case 10:
                        arity = 1;
                        s = "forEach";
                        break;
                    default:
                        throw new IllegalArgumentException(String.valueOf(id));
                }
                initPrototypeMethod(MAP_TAG, id, s, (String) null, arity);
                return;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol k) {
        if (NativeSet.GETSIZE.equals(k)) {
            return 11;
        }
        if (SymbolKey.ITERATOR.equals(k)) {
            return 9;
        }
        if (SymbolKey.TO_STRING_TAG.equals(k)) {
            return 12;
        }
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:40:0x010a A[PHI: r5 r6
  0x010a: PHI (r5v1 'id' int) = 
  (r5v0 'id' int)
  (r5v3 'id' int)
  (r5v0 'id' int)
  (r5v4 'id' int)
  (r5v5 'id' int)
  (r5v0 'id' int)
  (r5v6 'id' int)
  (r5v7 'id' int)
  (r5v8 'id' int)
  (r5v9 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
 binds: [B:3:0x0008, B:39:0x0102, B:37:0x00f6, B:38:0x00f9, B:35:0x00e9, B:31:0x00cf, B:32:0x00d2, B:29:0x00c3, B:26:0x00ac, B:25:0x00a3, B:19:0x0087, B:21:0x0091, B:23:0x009b, B:14:0x0071, B:16:0x007b, B:7:0x0051, B:9:0x005b] A[DONT_GENERATE, DONT_INLINE]
  0x010a: PHI (r6v1 'X' java.lang.String) = 
  (r6v0 'X' java.lang.String)
  (r6v2 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v3 'X' java.lang.String)
  (r6v4 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v5 'X' java.lang.String)
  (r6v6 'X' java.lang.String)
  (r6v7 'X' java.lang.String)
  (r6v8 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
 binds: [B:3:0x0008, B:39:0x0102, B:37:0x00f6, B:38:0x00f9, B:35:0x00e9, B:31:0x00cf, B:32:0x00d2, B:29:0x00c3, B:26:0x00ac, B:25:0x00a3, B:19:0x0087, B:21:0x0091, B:23:0x009b, B:14:0x0071, B:16:0x007b, B:7:0x0051, B:9:0x005b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010e  */
    @Override // org.mozilla.javascript.IdScriptableObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int findPrototypeId(java.lang.String r4) {
        /*
            Method dump skipped, instructions count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeMap.findPrototypeId(java.lang.String):int");
    }
}
