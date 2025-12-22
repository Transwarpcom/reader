package org.mozilla.javascript;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.mozilla.javascript.ScriptRuntime;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeObject.class */
public class NativeObject extends IdScriptableObject implements Map {
    private static final long serialVersionUID = -6345305608474346996L;
    private static final Object OBJECT_TAG = "Object";
    private static final int ConstructorId_getPrototypeOf = -1;
    private static final int ConstructorId_keys = -2;
    private static final int ConstructorId_getOwnPropertyNames = -3;
    private static final int ConstructorId_getOwnPropertyDescriptor = -4;
    private static final int ConstructorId_getOwnPropertyDescriptors = -5;
    private static final int ConstructorId_defineProperty = -6;
    private static final int ConstructorId_isExtensible = -7;
    private static final int ConstructorId_preventExtensions = -8;
    private static final int ConstructorId_defineProperties = -9;
    private static final int ConstructorId_create = -10;
    private static final int ConstructorId_isSealed = -11;
    private static final int ConstructorId_isFrozen = -12;
    private static final int ConstructorId_seal = -13;
    private static final int ConstructorId_freeze = -14;
    private static final int ConstructorId_getOwnPropertySymbols = -15;
    private static final int ConstructorId_assign = -16;
    private static final int ConstructorId_is = -17;
    private static final int ConstructorId_setPrototypeOf = -18;
    private static final int ConstructorId_entries = -19;
    private static final int ConstructorId_fromEntries = -20;
    private static final int ConstructorId_values = -21;
    private static final int ConstructorId_hasOwn = -22;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toLocaleString = 3;
    private static final int Id_valueOf = 4;
    private static final int Id_hasOwnProperty = 5;
    private static final int Id_propertyIsEnumerable = 6;
    private static final int Id_isPrototypeOf = 7;
    private static final int Id_toSource = 8;
    private static final int Id___defineGetter__ = 9;
    private static final int Id___defineSetter__ = 10;
    private static final int Id___lookupGetter__ = 11;
    private static final int Id___lookupSetter__ = 12;
    private static final int MAX_PROTOTYPE_ID = 12;

    static void init(Scriptable scope, boolean sealed) {
        NativeObject obj = new NativeObject();
        obj.exportAsJSClass(12, scope, sealed);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Object";
    }

    public String toString() {
        return ScriptRuntime.defaultObjectToString(this);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        addIdFunctionProperty(ctor, OBJECT_TAG, -1, "getPrototypeOf", 1);
        if (Context.getCurrentContext().version >= 200) {
            addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_setPrototypeOf, "setPrototypeOf", 2);
            addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_entries, "entries", 1);
            addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_fromEntries, "fromEntries", 1);
            addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_values, "values", 1);
            addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_hasOwn, "hasOwn", 1);
        }
        addIdFunctionProperty(ctor, OBJECT_TAG, -2, "keys", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, -3, "getOwnPropertyNames", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_getOwnPropertySymbols, "getOwnPropertySymbols", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_getOwnPropertyDescriptor, "getOwnPropertyDescriptor", 2);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_getOwnPropertyDescriptors, "getOwnPropertyDescriptors", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_defineProperty, "defineProperty", 3);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_isExtensible, "isExtensible", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_preventExtensions, "preventExtensions", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_defineProperties, "defineProperties", 2);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_create, "create", 2);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_isSealed, "isSealed", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_isFrozen, "isFrozen", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_seal, "seal", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_freeze, "freeze", 1);
        addIdFunctionProperty(ctor, OBJECT_TAG, ConstructorId_assign, "assign", 2);
        addIdFunctionProperty(ctor, OBJECT_TAG, -17, BeanUtil.PREFIX_GETTER_IS, 2);
        super.fillConstructorProperties(ctor);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int id) {
        int arity;
        String s;
        switch (id) {
            case 1:
                arity = 1;
                s = BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE;
                break;
            case 2:
                arity = 0;
                s = "toString";
                break;
            case 3:
                arity = 0;
                s = "toLocaleString";
                break;
            case 4:
                arity = 0;
                s = "valueOf";
                break;
            case 5:
                arity = 1;
                s = "hasOwnProperty";
                break;
            case 6:
                arity = 1;
                s = "propertyIsEnumerable";
                break;
            case 7:
                arity = 1;
                s = "isPrototypeOf";
                break;
            case 8:
                arity = 0;
                s = "toSource";
                break;
            case 9:
                arity = 2;
                s = "__defineGetter__";
                break;
            case 10:
                arity = 2;
                s = "__defineSetter__";
                break;
            case 11:
                arity = 1;
                s = "__lookupGetter__";
                break;
            case 12:
                arity = 1;
                s = "__lookupSetter__";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(OBJECT_TAG, id, s, arity);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable targetObj;
        int ii;
        Object val;
        boolean result;
        Object gs;
        Scriptable v;
        boolean result2;
        boolean result3;
        if (!f.hasTag(OBJECT_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case ConstructorId_hasOwn /* -22 */:
                Object arg = args.length < 1 ? Undefined.instance : args[0];
                Object propertyName = args.length < 2 ? Undefined.instance : args[1];
                ScriptableObject obj = ScriptableObject.ensureScriptableObject(arg);
                if (propertyName instanceof Symbol) {
                    result = ScriptableObject.ensureSymbolScriptable(arg).has((Symbol) propertyName, obj);
                } else {
                    ScriptRuntime.StringIdOrIndex s = ScriptRuntime.toStringIdOrIndex(cx, propertyName);
                    if (s.stringId == null) {
                        result = obj.has(s.index, obj);
                    } else {
                        result = obj.has(s.stringId, obj);
                    }
                }
                return Boolean.valueOf(result);
            case ConstructorId_values /* -21 */:
                Scriptable obj2 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]);
                Object[] ids = obj2.getIds();
                int j = 0;
                for (int i = 0; i < ids.length; i++) {
                    if (ids[i] instanceof Integer) {
                        int intId = ((Integer) ids[i]).intValue();
                        if (obj2.has(intId, obj2) && isEnumerable(intId, obj2)) {
                            int i2 = j;
                            j++;
                            ids[i2] = obj2.get(intId, obj2);
                        }
                    } else {
                        String stringId = ScriptRuntime.toString(ids[i]);
                        if (obj2.has(stringId, obj2) && isEnumerable(stringId, obj2)) {
                            int i3 = j;
                            j++;
                            ids[i3] = obj2.get(stringId, obj2);
                        }
                    }
                }
                if (j != ids.length) {
                    ids = Arrays.copyOf(ids, j);
                }
                return cx.newArray(scope, ids);
            case ConstructorId_fromEntries /* -20 */:
                Object arg2 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]);
                Scriptable obj3 = cx.newObject(scope);
                ScriptRuntime.loadFromIterable(cx, scope, arg2, (key, value) -> {
                    if (key instanceof Integer) {
                        obj3.put(((Integer) key).intValue(), obj3, value);
                    } else if ((key instanceof Symbol) && (obj3 instanceof SymbolScriptable)) {
                        ((SymbolScriptable) obj3).put((Symbol) key, obj3, value);
                    } else {
                        obj3.put(ScriptRuntime.toString(key), obj3, value);
                    }
                });
                return obj3;
            case ConstructorId_entries /* -19 */:
                Scriptable obj4 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]);
                Object[] ids2 = obj4.getIds();
                int j2 = 0;
                for (int i4 = 0; i4 < ids2.length; i4++) {
                    if (ids2[i4] instanceof Integer) {
                        int intId2 = ((Integer) ids2[i4]).intValue();
                        if (obj4.has(intId2, obj4) && isEnumerable(intId2, obj4)) {
                            Object[] entry = {ScriptRuntime.toString(ids2[i4]), obj4.get(intId2, obj4)};
                            int i5 = j2;
                            j2++;
                            ids2[i5] = cx.newArray(scope, entry);
                        }
                    } else {
                        String stringId2 = ScriptRuntime.toString(ids2[i4]);
                        if (obj4.has(stringId2, obj4) && isEnumerable(stringId2, obj4)) {
                            Object[] entry2 = {stringId2, obj4.get(stringId2, obj4)};
                            int i6 = j2;
                            j2++;
                            ids2[i6] = cx.newArray(scope, entry2);
                        }
                    }
                }
                if (j2 != ids2.length) {
                    ids2 = Arrays.copyOf(ids2, j2);
                }
                return cx.newArray(scope, ids2);
            case ConstructorId_setPrototypeOf /* -18 */:
                if (args.length < 2) {
                    throw ScriptRuntime.typeError1("msg.incompat.call", "setPrototypeOf");
                }
                Scriptable proto = args[1] == null ? null : ensureScriptable(args[1]);
                if (proto instanceof Symbol) {
                    throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(proto));
                }
                Object arg0 = args[0];
                if (cx.getLanguageVersion() >= 200) {
                    ScriptRuntimeES6.requireObjectCoercible(cx, arg0, f);
                }
                if (!(arg0 instanceof ScriptableObject)) {
                    return arg0;
                }
                ScriptableObject obj5 = (ScriptableObject) arg0;
                if (!obj5.isExtensible()) {
                    throw ScriptRuntime.typeError0("msg.not.extensible");
                }
                Scriptable prototype = proto;
                while (true) {
                    Scriptable prototypeProto = prototype;
                    if (prototypeProto != null) {
                        if (prototypeProto == obj5) {
                            throw ScriptRuntime.typeError1("msg.object.cyclic.prototype", obj5.getClass().getSimpleName());
                        }
                        prototype = prototypeProto.getPrototype();
                    } else {
                        obj5.setPrototype(proto);
                        return obj5;
                    }
                }
            case -17:
                Object a1 = args.length < 1 ? Undefined.instance : args[0];
                Object a2 = args.length < 2 ? Undefined.instance : args[1];
                return ScriptRuntime.wrapBoolean(ScriptRuntime.same(a1, a2));
            case ConstructorId_assign /* -16 */:
                if (args.length > 0) {
                    targetObj = ScriptRuntime.toObject(cx, scope, args[0]);
                } else {
                    targetObj = ScriptRuntime.toObject(cx, scope, Undefined.instance);
                }
                for (int i7 = 1; i7 < args.length; i7++) {
                    if (args[i7] != null && !Undefined.isUndefined(args[i7])) {
                        Scriptable sourceObj = ScriptRuntime.toObject(cx, thisObj, args[i7]);
                        for (Object key2 : sourceObj.getIds()) {
                            if (key2 instanceof String) {
                                Object val2 = sourceObj.get((String) key2, sourceObj);
                                if (val2 != Scriptable.NOT_FOUND && !Undefined.isUndefined(val2)) {
                                    targetObj.put((String) key2, targetObj, val2);
                                }
                            } else if ((key2 instanceof Number) && (val = sourceObj.get((ii = ScriptRuntime.toInt32(key2)), sourceObj)) != Scriptable.NOT_FOUND && !Undefined.isUndefined(val)) {
                                targetObj.put(ii, targetObj, val);
                            }
                        }
                    }
                }
                return targetObj;
            case ConstructorId_getOwnPropertySymbols /* -15 */:
                Scriptable s2 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]);
                Object[] ids3 = ensureScriptableObject(s2).getIds(true, true);
                ArrayList<Object> syms = new ArrayList<>();
                for (int i8 = 0; i8 < ids3.length; i8++) {
                    if (ids3[i8] instanceof Symbol) {
                        syms.add(ids3[i8]);
                    }
                }
                return cx.newArray(scope, syms.toArray());
            case ConstructorId_freeze /* -14 */:
                Object arg3 = args.length < 1 ? Undefined.instance : args[0];
                if (cx.getLanguageVersion() >= 200 && !(arg3 instanceof ScriptableObject)) {
                    return arg3;
                }
                ScriptableObject scriptableObjectEnsureScriptableObject = ensureScriptableObject(arg3);
                for (Object name : scriptableObjectEnsureScriptableObject.getIds(true, true)) {
                    ScriptableObject ownPropertyDescriptor = scriptableObjectEnsureScriptableObject.getOwnPropertyDescriptor(cx, name);
                    if (isDataDescriptor(ownPropertyDescriptor) && Boolean.TRUE.equals(ownPropertyDescriptor.get("writable"))) {
                        ownPropertyDescriptor.put("writable", ownPropertyDescriptor, Boolean.FALSE);
                    }
                    if (Boolean.TRUE.equals(ownPropertyDescriptor.get("configurable"))) {
                        ownPropertyDescriptor.put("configurable", ownPropertyDescriptor, Boolean.FALSE);
                    }
                    scriptableObjectEnsureScriptableObject.defineOwnProperty(cx, name, ownPropertyDescriptor, false);
                }
                scriptableObjectEnsureScriptableObject.preventExtensions();
                return scriptableObjectEnsureScriptableObject;
            case ConstructorId_seal /* -13 */:
                Object arg4 = args.length < 1 ? Undefined.instance : args[0];
                if (cx.getLanguageVersion() >= 200 && !(arg4 instanceof ScriptableObject)) {
                    return arg4;
                }
                ScriptableObject scriptableObjectEnsureScriptableObject2 = ensureScriptableObject(arg4);
                for (Object name2 : scriptableObjectEnsureScriptableObject2.getAllIds()) {
                    ScriptableObject ownPropertyDescriptor2 = scriptableObjectEnsureScriptableObject2.getOwnPropertyDescriptor(cx, name2);
                    if (Boolean.TRUE.equals(ownPropertyDescriptor2.get("configurable"))) {
                        ownPropertyDescriptor2.put("configurable", ownPropertyDescriptor2, Boolean.FALSE);
                        scriptableObjectEnsureScriptableObject2.defineOwnProperty(cx, name2, ownPropertyDescriptor2, false);
                    }
                }
                scriptableObjectEnsureScriptableObject2.preventExtensions();
                return scriptableObjectEnsureScriptableObject2;
            case ConstructorId_isFrozen /* -12 */:
                Object arg5 = args.length < 1 ? Undefined.instance : args[0];
                if (cx.getLanguageVersion() >= 200 && !(arg5 instanceof ScriptableObject)) {
                    return Boolean.TRUE;
                }
                ScriptableObject obj6 = ensureScriptableObject(arg5);
                if (obj6.isExtensible()) {
                    return Boolean.FALSE;
                }
                for (Object obj7 : obj6.getAllIds()) {
                    ScriptableObject desc = obj6.getOwnPropertyDescriptor(cx, obj7);
                    if (Boolean.TRUE.equals(desc.get("configurable"))) {
                        return Boolean.FALSE;
                    }
                    if (isDataDescriptor(desc) && Boolean.TRUE.equals(desc.get("writable"))) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            case ConstructorId_isSealed /* -11 */:
                Object arg6 = args.length < 1 ? Undefined.instance : args[0];
                if (cx.getLanguageVersion() >= 200 && !(arg6 instanceof ScriptableObject)) {
                    return Boolean.TRUE;
                }
                ScriptableObject obj8 = ensureScriptableObject(arg6);
                if (obj8.isExtensible()) {
                    return Boolean.FALSE;
                }
                for (Object obj9 : obj8.getAllIds()) {
                    Object configurable = obj8.getOwnPropertyDescriptor(cx, obj9).get("configurable");
                    if (Boolean.TRUE.equals(configurable)) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            case ConstructorId_create /* -10 */:
                Object arg7 = args.length < 1 ? Undefined.instance : args[0];
                Scriptable obj10 = arg7 == null ? null : ensureScriptable(arg7);
                ScriptableObject newObject = new NativeObject();
                newObject.setParentScope(scope);
                newObject.setPrototype(obj10);
                if (args.length > 1 && !Undefined.isUndefined(args[1])) {
                    Scriptable props = Context.toObject(args[1], scope);
                    newObject.defineOwnProperties(cx, ensureScriptableObject(props));
                }
                return newObject;
            case ConstructorId_defineProperties /* -9 */:
                ScriptableObject obj11 = ensureScriptableObject(args.length < 1 ? Undefined.instance : args[0]);
                Object propsObj = args.length < 2 ? Undefined.instance : args[1];
                Scriptable props2 = Context.toObject(propsObj, scope);
                obj11.defineOwnProperties(cx, ensureScriptableObject(props2));
                return obj11;
            case ConstructorId_preventExtensions /* -8 */:
                Object arg8 = args.length < 1 ? Undefined.instance : args[0];
                if (cx.getLanguageVersion() >= 200 && !(arg8 instanceof ScriptableObject)) {
                    return arg8;
                }
                ScriptableObject obj12 = ensureScriptableObject(arg8);
                obj12.preventExtensions();
                return obj12;
            case ConstructorId_isExtensible /* -7 */:
                Object arg9 = args.length < 1 ? Undefined.instance : args[0];
                if (cx.getLanguageVersion() >= 200 && !(arg9 instanceof ScriptableObject)) {
                    return Boolean.FALSE;
                }
                return Boolean.valueOf(ensureScriptableObject(arg9).isExtensible());
            case ConstructorId_defineProperty /* -6 */:
                ScriptableObject obj13 = ensureScriptableObject(args.length < 1 ? Undefined.instance : args[0]);
                Object name3 = args.length < 2 ? Undefined.instance : args[1];
                Object descArg = args.length < 3 ? Undefined.instance : args[2];
                obj13.defineOwnProperty(cx, name3, ensureScriptableObject(descArg));
                return obj13;
            case ConstructorId_getOwnPropertyDescriptors /* -5 */:
                Scriptable s3 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]);
                ScriptableObject obj14 = ensureScriptableObject(s3);
                ScriptableObject scriptableObject = (ScriptableObject) cx.newObject(scope);
                for (Object key3 : obj14.getIds(true, true)) {
                    Object desc2 = obj14.getOwnPropertyDescriptor(cx, key3);
                    if (desc2 != null) {
                        if (key3 instanceof Symbol) {
                            scriptableObject.put((Symbol) key3, scriptableObject, desc2);
                        } else if (key3 instanceof Integer) {
                            scriptableObject.put(((Integer) key3).intValue(), scriptableObject, desc2);
                        } else {
                            scriptableObject.put(ScriptRuntime.toString(key3), scriptableObject, desc2);
                        }
                    }
                }
                return scriptableObject;
            case ConstructorId_getOwnPropertyDescriptor /* -4 */:
                Scriptable s4 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]);
                ScriptableObject obj15 = ensureScriptableObject(s4);
                Object nameArg = args.length < 2 ? Undefined.instance : args[1];
                Scriptable desc3 = obj15.getOwnPropertyDescriptor(cx, nameArg);
                return desc3 == null ? Undefined.instance : desc3;
            case -3:
                Scriptable s5 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]);
                Object[] ids4 = ensureScriptableObject(s5).getIds(true, false);
                for (int i9 = 0; i9 < ids4.length; i9++) {
                    ids4[i9] = ScriptRuntime.toString(ids4[i9]);
                }
                return cx.newArray(scope, ids4);
            case -2:
                Object[] ids5 = getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]).getIds();
                for (int i10 = 0; i10 < ids5.length; i10++) {
                    ids5[i10] = ScriptRuntime.toString(ids5[i10]);
                }
                return cx.newArray(scope, ids5);
            case -1:
                return getCompatibleObject(cx, scope, args.length < 1 ? Undefined.instance : args[0]).getPrototype();
            case 0:
            default:
                throw new IllegalArgumentException(String.valueOf(id));
            case 1:
                if (thisObj != null) {
                    return f.construct(cx, scope, args);
                }
                if (args.length == 0 || args[0] == null || Undefined.isUndefined(args[0])) {
                    return new NativeObject();
                }
                return ScriptRuntime.toObject(cx, scope, args[0]);
            case 2:
                if (cx.hasFeature(4)) {
                    String s6 = ScriptRuntime.defaultObjectToSource(cx, scope, thisObj, args);
                    int L = s6.length();
                    if (L != 0 && s6.charAt(0) == '(' && s6.charAt(L - 1) == ')') {
                        s6 = s6.substring(1, L - 1);
                    }
                    return s6;
                }
                return ScriptRuntime.defaultObjectToString(thisObj);
            case 3:
                Object toString = ScriptableObject.getProperty(thisObj, "toString");
                if (!(toString instanceof Callable)) {
                    throw ScriptRuntime.notFunctionError(toString);
                }
                Callable fun = (Callable) toString;
                return fun.call(cx, scope, thisObj, ScriptRuntime.emptyArgs);
            case 4:
                if (cx.getLanguageVersion() < 180 || !(thisObj == null || Undefined.isUndefined(thisObj))) {
                    return thisObj;
                }
                throw ScriptRuntime.typeError0("msg." + (thisObj == null ? "null" : "undef") + ".to.object");
            case 5:
                if (cx.getLanguageVersion() >= 180 && (thisObj == null || Undefined.isUndefined(thisObj))) {
                    throw ScriptRuntime.typeError0("msg." + (thisObj == null ? "null" : "undef") + ".to.object");
                }
                Object arg10 = args.length < 1 ? Undefined.instance : args[0];
                if (arg10 instanceof Symbol) {
                    result3 = ensureSymbolScriptable(thisObj).has((Symbol) arg10, thisObj);
                } else {
                    ScriptRuntime.StringIdOrIndex s7 = ScriptRuntime.toStringIdOrIndex(cx, arg10);
                    if (s7.stringId == null) {
                        result3 = thisObj.has(s7.index, thisObj);
                    } else {
                        result3 = thisObj.has(s7.stringId, thisObj);
                    }
                }
                return ScriptRuntime.wrapBoolean(result3);
            case 6:
                if (cx.getLanguageVersion() >= 180 && (thisObj == null || Undefined.isUndefined(thisObj))) {
                    throw ScriptRuntime.typeError0("msg." + (thisObj == null ? "null" : "undef") + ".to.object");
                }
                Object arg11 = args.length < 1 ? Undefined.instance : args[0];
                if (arg11 instanceof Symbol) {
                    result2 = ((SymbolScriptable) thisObj).has((Symbol) arg11, thisObj);
                    if (result2 && (thisObj instanceof ScriptableObject)) {
                        ScriptableObject so = (ScriptableObject) thisObj;
                        int attrs = so.getAttributes((Symbol) arg11);
                        result2 = (attrs & 2) == 0;
                    }
                } else {
                    ScriptRuntime.StringIdOrIndex s8 = ScriptRuntime.toStringIdOrIndex(cx, arg11);
                    try {
                        if (s8.stringId == null) {
                            result2 = thisObj.has(s8.index, thisObj);
                            if (result2 && (thisObj instanceof ScriptableObject)) {
                                ScriptableObject so2 = (ScriptableObject) thisObj;
                                int attrs2 = so2.getAttributes(s8.index);
                                result2 = (attrs2 & 2) == 0;
                            }
                        } else {
                            result2 = thisObj.has(s8.stringId, thisObj);
                            if (result2 && (thisObj instanceof ScriptableObject)) {
                                ScriptableObject so3 = (ScriptableObject) thisObj;
                                int attrs3 = so3.getAttributes(s8.stringId);
                                result2 = (attrs3 & 2) == 0;
                            }
                        }
                    } catch (EvaluatorException ee) {
                        if (ee.getMessage().startsWith(ScriptRuntime.getMessage1("msg.prop.not.found", s8.stringId == null ? Integer.toString(s8.index) : s8.stringId))) {
                            result2 = false;
                        } else {
                            throw ee;
                        }
                    }
                }
                return ScriptRuntime.wrapBoolean(result2);
            case 7:
                if (cx.getLanguageVersion() >= 180 && (thisObj == null || Undefined.isUndefined(thisObj))) {
                    throw ScriptRuntime.typeError0("msg." + (thisObj == null ? "null" : "undef") + ".to.object");
                }
                boolean result4 = false;
                if (args.length != 0 && (args[0] instanceof Scriptable)) {
                    Scriptable v2 = (Scriptable) args[0];
                    while (true) {
                        v2 = v2.getPrototype();
                        if (v2 == thisObj) {
                            result4 = true;
                        } else if (v2 == null) {
                        }
                    }
                }
                return ScriptRuntime.wrapBoolean(result4);
            case 8:
                return ScriptRuntime.defaultObjectToSource(cx, scope, thisObj, args);
            case 9:
            case 10:
                if (args.length < 2 || !(args[1] instanceof Callable)) {
                    Object badArg = args.length >= 2 ? args[1] : Undefined.instance;
                    throw ScriptRuntime.notFunctionError(badArg);
                }
                if (!(thisObj instanceof ScriptableObject)) {
                    throw Context.reportRuntimeError2("msg.extend.scriptable", thisObj == null ? "null" : thisObj.getClass().getName(), String.valueOf(args[0]));
                }
                ScriptableObject so4 = (ScriptableObject) thisObj;
                ScriptRuntime.StringIdOrIndex s9 = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
                int index = s9.stringId != null ? 0 : s9.index;
                Callable getterOrSetter = (Callable) args[1];
                boolean isSetter = id == 10;
                so4.setGetterOrSetter(s9.stringId, index, getterOrSetter, isSetter);
                if (so4 instanceof NativeArray) {
                    ((NativeArray) so4).setDenseOnly(false);
                }
                return Undefined.instance;
            case 11:
            case 12:
                if (args.length < 1 || !(thisObj instanceof ScriptableObject)) {
                    return Undefined.instance;
                }
                ScriptableObject so5 = (ScriptableObject) thisObj;
                ScriptRuntime.StringIdOrIndex s10 = ScriptRuntime.toStringIdOrIndex(cx, args[0]);
                int index2 = s10.stringId != null ? 0 : s10.index;
                boolean isSetter2 = id == 12;
                while (true) {
                    gs = so5.getGetterOrSetter(s10.stringId, index2, isSetter2);
                    if (gs == null && (v = so5.getPrototype()) != null && (v instanceof ScriptableObject)) {
                        so5 = (ScriptableObject) v;
                    }
                }
                if (gs != null) {
                    return gs;
                }
                return Undefined.instance;
        }
    }

    private static Scriptable getCompatibleObject(Context cx, Scriptable scope, Object arg) {
        if (cx.getLanguageVersion() >= 200) {
            Scriptable s = ScriptRuntime.toObject(cx, scope, arg);
            return ensureScriptable(s);
        }
        return ensureScriptable(arg);
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        if (key instanceof String) {
            return has((String) key, this);
        }
        if (key instanceof Number) {
            return has(((Number) key).intValue(), this);
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        for (Object obj : values()) {
            if (value != obj) {
                if (value != null && value.equals(obj)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public Object remove(Object key) {
        Object value = get(key);
        if (key instanceof String) {
            delete((String) key);
        } else if (key instanceof Number) {
            delete(((Number) key).intValue());
        }
        return value;
    }

    @Override // java.util.Map
    public Set<Object> keySet() {
        return new KeySet();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return new ValueCollection();
    }

    @Override // java.util.Map
    public Set<Map.Entry<Object, Object>> entrySet() {
        return new EntrySet();
    }

    @Override // java.util.Map
    public Object put(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void putAll(Map m) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeObject$EntrySet.class */
    class EntrySet extends AbstractSet<Map.Entry<Object, Object>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Object, Object>> iterator() {
            return new Iterator<Map.Entry<Object, Object>>() { // from class: org.mozilla.javascript.NativeObject.EntrySet.1
                Object[] ids;
                Object key = null;
                int index = 0;

                {
                    this.ids = NativeObject.this.getIds();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Map.Entry<Object, Object> next() {
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    final Object ekey = objArr[i];
                    this.key = ekey;
                    final Object value = NativeObject.this.get(this.key);
                    return new Map.Entry<Object, Object>() { // from class: org.mozilla.javascript.NativeObject.EntrySet.1.1
                        @Override // java.util.Map.Entry
                        public Object getKey() {
                            return ekey;
                        }

                        @Override // java.util.Map.Entry
                        public Object getValue() {
                            return value;
                        }

                        @Override // java.util.Map.Entry
                        public Object setValue(Object value2) {
                            throw new UnsupportedOperationException();
                        }

                        @Override // java.util.Map.Entry
                        public boolean equals(Object other) {
                            if (!(other instanceof Map.Entry)) {
                                return false;
                            }
                            Map.Entry<?, ?> e = (Map.Entry) other;
                            if (ekey != null ? ekey.equals(e.getKey()) : e.getKey() == null) {
                                if (value != null ? value.equals(e.getValue()) : e.getValue() == null) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        @Override // java.util.Map.Entry
                        public int hashCode() {
                            return (ekey == null ? 0 : ekey.hashCode()) ^ (value == null ? 0 : value.hashCode());
                        }

                        public String toString() {
                            return ekey + "=" + value;
                        }
                    };
                }

                @Override // java.util.Iterator
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return NativeObject.this.size();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeObject$KeySet.class */
    class KeySet extends AbstractSet<Object> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object key) {
            return NativeObject.this.containsKey(key);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Object> iterator() {
            return new Iterator<Object>() { // from class: org.mozilla.javascript.NativeObject.KeySet.1
                Object[] ids;
                Object key;
                int index = 0;

                {
                    this.ids = NativeObject.this.getIds();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override // java.util.Iterator
                public Object next() {
                    try {
                        Object[] objArr = this.ids;
                        int i = this.index;
                        this.index = i + 1;
                        Object obj = objArr[i];
                        this.key = obj;
                        return obj;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        this.key = null;
                        throw new NoSuchElementException();
                    }
                }

                @Override // java.util.Iterator
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return NativeObject.this.size();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeObject$ValueCollection.class */
    class ValueCollection extends AbstractCollection<Object> {
        ValueCollection() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return new Iterator<Object>() { // from class: org.mozilla.javascript.NativeObject.ValueCollection.1
                Object[] ids;
                Object key;
                int index = 0;

                {
                    this.ids = NativeObject.this.getIds();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                @Override // java.util.Iterator
                public Object next() {
                    NativeObject nativeObject = NativeObject.this;
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    Object obj = objArr[i];
                    this.key = obj;
                    return nativeObject.get(obj);
                }

                @Override // java.util.Iterator
                public void remove() {
                    if (this.key == null) {
                        throw new IllegalStateException();
                    }
                    NativeObject.this.remove(this.key);
                    this.key = null;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return NativeObject.this.size();
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String s) {
        int id = 0;
        String X = null;
        switch (s.length()) {
            case 7:
                X = "valueOf";
                id = 4;
                break;
            case 8:
                int c = s.charAt(3);
                if (c != 111) {
                    if (c == 116) {
                        X = "toString";
                        id = 2;
                        break;
                    }
                } else {
                    X = "toSource";
                    id = 8;
                    break;
                }
                break;
            case 11:
                X = BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE;
                id = 1;
                break;
            case 13:
                X = "isPrototypeOf";
                id = 7;
                break;
            case 14:
                int c2 = s.charAt(0);
                if (c2 != 104) {
                    if (c2 == 116) {
                        X = "toLocaleString";
                        id = 3;
                        break;
                    }
                } else {
                    X = "hasOwnProperty";
                    id = 5;
                    break;
                }
                break;
            case 16:
                int c3 = s.charAt(2);
                if (c3 == 100) {
                    int c4 = s.charAt(8);
                    if (c4 != 71) {
                        if (c4 == 83) {
                            X = "__defineSetter__";
                            id = 10;
                            break;
                        }
                    } else {
                        X = "__defineGetter__";
                        id = 9;
                        break;
                    }
                } else if (c3 == 108) {
                    int c5 = s.charAt(8);
                    if (c5 != 71) {
                        if (c5 == 83) {
                            X = "__lookupSetter__";
                            id = 12;
                            break;
                        }
                    } else {
                        X = "__lookupGetter__";
                        id = 11;
                        break;
                    }
                }
                break;
            case 20:
                X = "propertyIsEnumerable";
                id = 6;
                break;
        }
        if (X != null && X != s && !X.equals(s)) {
            id = 0;
        }
        return id;
    }

    private boolean isEnumerable(int index, Object obj) {
        if (obj instanceof ScriptableObject) {
            ScriptableObject so = (ScriptableObject) obj;
            int attrs = so.getAttributes(index);
            return (attrs & 2) == 0;
        }
        return true;
    }

    private boolean isEnumerable(String key, Object obj) {
        if (obj instanceof ScriptableObject) {
            ScriptableObject so = (ScriptableObject) obj;
            int attrs = so.getAttributes(key);
            return (attrs & 2) == 0;
        }
        return true;
    }

    private boolean isEnumerable(Symbol sym, Object obj) {
        if (obj instanceof ScriptableObject) {
            ScriptableObject so = (ScriptableObject) obj;
            int attrs = so.getAttributes(sym);
            return (attrs & 2) == 0;
        }
        return true;
    }
}
