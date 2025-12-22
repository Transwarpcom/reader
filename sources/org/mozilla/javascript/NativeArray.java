package org.mozilla.javascript;

import com.jayway.jsonpath.internal.function.text.Length;
import io.vertx.core.cli.converters.FromBasedConverter;
import io.vertx.core.http.Http2Settings;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.mozilla.javascript.TopLevel;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeArray.class */
public class NativeArray extends IdScriptableObject implements List {
    private static final long serialVersionUID = 7331366857676127338L;
    private static final int Id_length = 1;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 4;
    private static final int Id_join = 5;
    private static final int Id_reverse = 6;
    private static final int Id_sort = 7;
    private static final int Id_push = 8;
    private static final int Id_pop = 9;
    private static final int Id_shift = 10;
    private static final int Id_unshift = 11;
    private static final int Id_splice = 12;
    private static final int Id_concat = 13;
    private static final int Id_slice = 14;
    private static final int Id_indexOf = 15;
    private static final int Id_lastIndexOf = 16;
    private static final int Id_every = 17;
    private static final int Id_filter = 18;
    private static final int Id_forEach = 19;
    private static final int Id_map = 20;
    private static final int Id_some = 21;
    private static final int Id_find = 22;
    private static final int Id_findIndex = 23;
    private static final int Id_reduce = 24;
    private static final int Id_reduceRight = 25;
    private static final int Id_fill = 26;
    private static final int Id_keys = 27;
    private static final int Id_values = 28;
    private static final int Id_entries = 29;
    private static final int Id_includes = 30;
    private static final int Id_copyWithin = 31;
    private static final int SymbolId_iterator = 32;
    private static final int MAX_PROTOTYPE_ID = 32;
    private static final int ConstructorId_join = -5;
    private static final int ConstructorId_reverse = -6;
    private static final int ConstructorId_sort = -7;
    private static final int ConstructorId_push = -8;
    private static final int ConstructorId_pop = -9;
    private static final int ConstructorId_shift = -10;
    private static final int ConstructorId_unshift = -11;
    private static final int ConstructorId_splice = -12;
    private static final int ConstructorId_concat = -13;
    private static final int ConstructorId_slice = -14;
    private static final int ConstructorId_indexOf = -15;
    private static final int ConstructorId_lastIndexOf = -16;
    private static final int ConstructorId_every = -17;
    private static final int ConstructorId_filter = -18;
    private static final int ConstructorId_forEach = -19;
    private static final int ConstructorId_map = -20;
    private static final int ConstructorId_some = -21;
    private static final int ConstructorId_find = -22;
    private static final int ConstructorId_findIndex = -23;
    private static final int ConstructorId_reduce = -24;
    private static final int ConstructorId_reduceRight = -25;
    private static final int ConstructorId_isArray = -26;
    private static final int ConstructorId_of = -27;
    private static final int ConstructorId_from = -28;
    private long length;
    private int lengthAttr;
    private transient int modCount;
    private Object[] dense;
    private boolean denseOnly;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5d;
    private static final int MAX_PRE_GROW_SIZE = 1431655764;
    private static final Object ARRAY_TAG = "Array";
    private static final Long NEGATIVE_ONE = -1L;
    private static final Comparator<Object> STRING_COMPARATOR = new StringLikeComparator();
    private static final Comparator<Object> DEFAULT_COMPARATOR = new ElementComparator();
    private static int maximumInitialCapacity = 10000;

    static void init(Scriptable scope, boolean sealed) {
        NativeArray obj = new NativeArray(0L);
        obj.exportAsJSClass(32, scope, sealed);
    }

    static int getMaximumInitialCapacity() {
        return maximumInitialCapacity;
    }

    static void setMaximumInitialCapacity(int maximumInitialCapacity2) {
        maximumInitialCapacity = maximumInitialCapacity2;
    }

    public NativeArray(long lengthArg) {
        this.lengthAttr = 6;
        this.denseOnly = lengthArg <= ((long) maximumInitialCapacity);
        if (this.denseOnly) {
            int intLength = (int) lengthArg;
            this.dense = new Object[intLength < 10 ? 10 : intLength];
            Arrays.fill(this.dense, Scriptable.NOT_FOUND);
        }
        this.length = lengthArg;
    }

    public NativeArray(Object[] array) {
        this.lengthAttr = 6;
        this.denseOnly = true;
        this.dense = array;
        this.length = array.length;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "Array";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 1;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdAttributes(int id, int attr) {
        if (id == 1) {
            this.lengthAttr = attr;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String s) {
        if (s.equals(Length.TOKEN_NAME)) {
            return instanceIdInfo(this.lengthAttr, 1);
        }
        return super.findInstanceIdInfo(s);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int id) {
        if (id == 1) {
            return Length.TOKEN_NAME;
        }
        return super.getInstanceIdName(id);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int id) {
        if (id == 1) {
            return ScriptRuntime.wrapNumber(this.length);
        }
        return super.getInstanceIdValue(id);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int id, Object value) {
        if (id == 1) {
            setLength(value);
        } else {
            super.setInstanceIdValue(id, value);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_join, "join", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_reverse, "reverse", 0);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_sort, "sort", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_push, "push", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_pop, "pop", 0);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_shift, "shift", 0);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_unshift, "unshift", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_splice, "splice", 2);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_concat, "concat", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_slice, "slice", 2);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_indexOf, "indexOf", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_lastIndexOf, "lastIndexOf", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, -17, "every", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_filter, "filter", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_forEach, "forEach", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_map, BeanDefinitionParserDelegate.MAP_ELEMENT, 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_some, "some", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_find, "find", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_findIndex, "findIndex", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_reduce, "reduce", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_reduceRight, "reduceRight", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_isArray, "isArray", 1);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_of, "of", 0);
        addIdFunctionProperty(ctor, ARRAY_TAG, ConstructorId_from, FromBasedConverter.FROM, 1);
        super.fillConstructorProperties(ctor);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int id) {
        int arity;
        String s;
        if (id == 32) {
            initPrototypeMethod(ARRAY_TAG, id, SymbolKey.ITERATOR, "[Symbol.iterator]", 0);
            return;
        }
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
                s = "toSource";
                break;
            case 5:
                arity = 1;
                s = "join";
                break;
            case 6:
                arity = 0;
                s = "reverse";
                break;
            case 7:
                arity = 1;
                s = "sort";
                break;
            case 8:
                arity = 1;
                s = "push";
                break;
            case 9:
                arity = 0;
                s = "pop";
                break;
            case 10:
                arity = 0;
                s = "shift";
                break;
            case 11:
                arity = 1;
                s = "unshift";
                break;
            case 12:
                arity = 2;
                s = "splice";
                break;
            case 13:
                arity = 1;
                s = "concat";
                break;
            case 14:
                arity = 2;
                s = "slice";
                break;
            case 15:
                arity = 1;
                s = "indexOf";
                break;
            case 16:
                arity = 1;
                s = "lastIndexOf";
                break;
            case 17:
                arity = 1;
                s = "every";
                break;
            case 18:
                arity = 1;
                s = "filter";
                break;
            case 19:
                arity = 1;
                s = "forEach";
                break;
            case 20:
                arity = 1;
                s = BeanDefinitionParserDelegate.MAP_ELEMENT;
                break;
            case 21:
                arity = 1;
                s = "some";
                break;
            case 22:
                arity = 1;
                s = "find";
                break;
            case 23:
                arity = 1;
                s = "findIndex";
                break;
            case 24:
                arity = 1;
                s = "reduce";
                break;
            case 25:
                arity = 1;
                s = "reduceRight";
                break;
            case 26:
                arity = 1;
                s = "fill";
                break;
            case 27:
                arity = 0;
                s = "keys";
                break;
            case 28:
                arity = 0;
                s = "values";
                break;
            case 29:
                arity = 0;
                s = "entries";
                break;
            case 30:
                arity = 1;
                s = "includes";
                break;
            case 31:
                arity = 2;
                s = "copyWithin";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(ARRAY_TAG, id, s, (String) null, arity);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:281)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public java.lang.Object execIdCall(org.mozilla.javascript.IdFunctionObject r8, org.mozilla.javascript.Context r9, org.mozilla.javascript.Scriptable r10, org.mozilla.javascript.Scriptable r11, java.lang.Object[] r12) {
        /*
            Method dump skipped, instructions count: 741
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeArray.execIdCall(org.mozilla.javascript.IdFunctionObject, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int index, Scriptable start) {
        if (!this.denseOnly && isGetterOrSetter(null, index, false)) {
            return super.get(index, start);
        }
        if (this.dense != null && 0 <= index && index < this.dense.length) {
            return this.dense[index];
        }
        return super.get(index, start);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int index, Scriptable start) {
        if (!this.denseOnly && isGetterOrSetter(null, index, false)) {
            return super.has(index, start);
        }
        if (this.dense == null || 0 > index || index >= this.dense.length) {
            return super.has(index, start);
        }
        return this.dense[index] != NOT_FOUND;
    }

    private static long toArrayIndex(Object id) {
        if (id instanceof String) {
            return toArrayIndex((String) id);
        }
        if (id instanceof Number) {
            return toArrayIndex(((Number) id).doubleValue());
        }
        return -1L;
    }

    private static long toArrayIndex(String id) {
        long index = toArrayIndex(ScriptRuntime.toNumber(id));
        if (Long.toString(index).equals(id)) {
            return index;
        }
        return -1L;
    }

    private static long toArrayIndex(double d) {
        if (!Double.isNaN(d)) {
            long index = ScriptRuntime.toUint32(d);
            if (index == d && index != 4294967295L) {
                return index;
            }
            return -1L;
        }
        return -1L;
    }

    private static int toDenseIndex(Object id) {
        long index = toArrayIndex(id);
        if (0 > index || index >= 2147483647L) {
            return -1;
        }
        return (int) index;
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(String id, Scriptable start, Object value) {
        super.put(id, start, value);
        if (start == this) {
            long index = toArrayIndex(id);
            if (index >= this.length) {
                this.length = index + 1;
                this.modCount++;
                this.denseOnly = false;
            }
        }
    }

    private boolean ensureCapacity(int capacity) {
        if (capacity > this.dense.length) {
            if (capacity > MAX_PRE_GROW_SIZE) {
                this.denseOnly = false;
                return false;
            }
            Object[] newDense = new Object[Math.max(capacity, (int) (this.dense.length * 1.5d))];
            System.arraycopy(this.dense, 0, newDense, 0, this.dense.length);
            Arrays.fill(newDense, this.dense.length, newDense.length, Scriptable.NOT_FOUND);
            this.dense = newDense;
            return true;
        }
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int index, Scriptable start, Object value) {
        if (start == this && !isSealed() && this.dense != null && 0 <= index && (this.denseOnly || !isGetterOrSetter(null, index, true))) {
            if (!isExtensible() && this.length <= index) {
                return;
            }
            if (index < this.dense.length) {
                this.dense[index] = value;
                if (this.length <= index) {
                    this.length = index + 1;
                }
                this.modCount++;
                return;
            }
            if (this.denseOnly && index < this.dense.length * 1.5d && ensureCapacity(index + 1)) {
                this.dense[index] = value;
                this.length = index + 1;
                this.modCount++;
                return;
            }
            this.denseOnly = false;
        }
        super.put(index, start, value);
        if (start == this && (this.lengthAttr & 1) == 0 && this.length <= index) {
            this.length = index + 1;
            this.modCount++;
        }
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void delete(int index) {
        if (this.dense != null && 0 <= index && index < this.dense.length && !isSealed() && (this.denseOnly || !isGetterOrSetter(null, index, true))) {
            this.dense[index] = NOT_FOUND;
        } else {
            super.delete(index);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    public Object[] getIds(boolean nonEnumerable, boolean getSymbols) {
        Object[] superIds = super.getIds(nonEnumerable, getSymbols);
        if (this.dense == null) {
            return superIds;
        }
        int N = this.dense.length;
        long currentLength = this.length;
        if (N > currentLength) {
            N = (int) currentLength;
        }
        if (N == 0) {
            return superIds;
        }
        int superLength = superIds.length;
        Object[] ids = new Object[N + superLength];
        int presentCount = 0;
        for (int i = 0; i != N; i++) {
            if (this.dense[i] != NOT_FOUND) {
                ids[presentCount] = Integer.valueOf(i);
                presentCount++;
            }
        }
        if (presentCount != N) {
            Object[] tmp = new Object[presentCount + superLength];
            System.arraycopy(ids, 0, tmp, 0, presentCount);
            ids = tmp;
        }
        System.arraycopy(superIds, 0, ids, presentCount, superLength);
        return ids;
    }

    public List<Integer> getIndexIds() {
        Object[] ids = getIds();
        List<Integer> indices = new ArrayList<>(ids.length);
        for (Object id : ids) {
            int int32Id = ScriptRuntime.toInt32(id);
            if (int32Id >= 0 && ScriptRuntime.toString(int32Id).equals(ScriptRuntime.toString(id))) {
                indices.add(Integer.valueOf(int32Id));
            }
        }
        return indices;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object getDefaultValue(Class<?> hint) {
        if (hint == ScriptRuntime.NumberClass) {
            Context cx = Context.getContext();
            if (cx.getLanguageVersion() == 120) {
                return Long.valueOf(this.length);
            }
        }
        return super.getDefaultValue(hint);
    }

    private ScriptableObject defaultIndexPropertyDescriptor(Object value) {
        Scriptable scope = getParentScope();
        if (scope == null) {
            scope = this;
        }
        ScriptableObject desc = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
        desc.defineProperty("value", value, 0);
        desc.defineProperty("writable", Boolean.TRUE, 0);
        desc.defineProperty("enumerable", Boolean.TRUE, 0);
        desc.defineProperty("configurable", Boolean.TRUE, 0);
        return desc;
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public int getAttributes(int index) {
        if (this.dense != null && index >= 0 && index < this.dense.length && this.dense[index] != NOT_FOUND) {
            return 0;
        }
        return super.getAttributes(index);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
        int index;
        if (this.dense != null && 0 <= (index = toDenseIndex(id)) && index < this.dense.length && this.dense[index] != NOT_FOUND) {
            Object value = this.dense[index];
            return defaultIndexPropertyDescriptor(value);
        }
        return super.getOwnPropertyDescriptor(cx, id);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    protected void defineOwnProperty(Context cx, Object id, ScriptableObject desc, boolean checkValid) {
        long index = toArrayIndex(id);
        if (index >= this.length) {
            this.length = index + 1;
            this.modCount++;
        }
        if (index != -1 && this.dense != null) {
            Object[] values = this.dense;
            this.dense = null;
            this.denseOnly = false;
            for (int i = 0; i < values.length; i++) {
                if (values[i] != NOT_FOUND) {
                    if (!isExtensible()) {
                        setAttributes(i, 0);
                    }
                    put(i, this, values[i]);
                }
            }
        }
        super.defineOwnProperty(cx, id, desc, checkValid);
        if ((id instanceof String) && ((String) id).equals(Length.TOKEN_NAME)) {
            this.lengthAttr = getAttributes(Length.TOKEN_NAME);
        }
    }

    private static Object jsConstructor(Context cx, Scriptable scope, Object[] args) {
        if (args.length == 0) {
            return new NativeArray(0L);
        }
        if (cx.getLanguageVersion() == 120) {
            return new NativeArray(args);
        }
        Object arg0 = args[0];
        if (args.length > 1 || !(arg0 instanceof Number)) {
            return new NativeArray(args);
        }
        long len = ScriptRuntime.toUint32(arg0);
        if (len != ((Number) arg0).doubleValue()) {
            String msg = ScriptRuntime.getMessage0("msg.arraylength.bad");
            throw ScriptRuntime.rangeError(msg);
        }
        return new NativeArray(len);
    }

    private static Scriptable callConstructorOrCreateArray(Context cx, Scriptable scope, Scriptable arg, long length, boolean lengthAlways) {
        Scriptable result = null;
        if (arg instanceof Function) {
            try {
                Object[] args = (lengthAlways || length > 0) ? new Object[]{Long.valueOf(length)} : ScriptRuntime.emptyArgs;
                result = ((Function) arg).construct(cx, scope, args);
            } catch (EcmaError ee) {
                if (!"TypeError".equals(ee.getName())) {
                    throw ee;
                }
            }
        }
        if (result == null) {
            result = cx.newArray(scope, length > 2147483647L ? 0 : (int) length);
        }
        return result;
    }

    private static Object js_from(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable items = ScriptRuntime.toObject(scope, args.length >= 1 ? args[0] : Undefined.instance);
        Object mapArg = args.length >= 2 ? args[1] : Undefined.instance;
        Scriptable thisArg = Undefined.SCRIPTABLE_UNDEFINED;
        boolean mapping = !Undefined.isUndefined(mapArg);
        Function mapFn = null;
        if (mapping) {
            if (!(mapArg instanceof Function)) {
                throw ScriptRuntime.typeError0("msg.map.function.not");
            }
            mapFn = (Function) mapArg;
            if (args.length >= 3) {
                thisArg = ensureScriptable(args[2]);
            }
        }
        Object iteratorProp = ScriptableObject.getProperty(items, SymbolKey.ITERATOR);
        if (!(items instanceof NativeArray) && iteratorProp != Scriptable.NOT_FOUND && !Undefined.isUndefined(iteratorProp)) {
            Object iterator = ScriptRuntime.callIterator(items, cx, scope);
            if (!Undefined.isUndefined(iterator)) {
                Scriptable result = callConstructorOrCreateArray(cx, scope, thisObj, 0L, false);
                long k = 0;
                IteratorLikeIterable it = new IteratorLikeIterable(cx, scope, iterator);
                Throwable th = null;
                try {
                    try {
                        Iterator<Object> it2 = it.iterator2();
                        while (it2.hasNext()) {
                            Object temp = it2.next();
                            if (mapping) {
                                temp = mapFn.call(cx, scope, thisArg, new Object[]{temp, Long.valueOf(k)});
                            }
                            defineElem(cx, result, k, temp);
                            k++;
                        }
                        if (it != null) {
                            if (0 != 0) {
                                try {
                                    it.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                it.close();
                            }
                        }
                        setLengthProperty(cx, result, k);
                        return result;
                    } finally {
                    }
                } catch (Throwable th3) {
                    if (it != null) {
                        if (th != null) {
                            try {
                                it.close();
                            } catch (Throwable th4) {
                                th.addSuppressed(th4);
                            }
                        } else {
                            it.close();
                        }
                    }
                    throw th3;
                }
            }
        }
        long length = getLengthProperty(cx, items, false);
        Scriptable result2 = callConstructorOrCreateArray(cx, scope, thisObj, length, true);
        long j = 0;
        while (true) {
            long k2 = j;
            if (k2 < length) {
                Object temp2 = getElem(cx, items, k2);
                if (mapping) {
                    temp2 = mapFn.call(cx, scope, thisArg, new Object[]{temp2, Long.valueOf(k2)});
                }
                defineElem(cx, result2, k2, temp2);
                j = k2 + 1;
            } else {
                setLengthProperty(cx, result2, length);
                return result2;
            }
        }
    }

    private static Object js_of(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable result = callConstructorOrCreateArray(cx, scope, thisObj, args.length, true);
        for (int i = 0; i < args.length; i++) {
            defineElem(cx, result, i, args[i]);
        }
        setLengthProperty(cx, result, args.length);
        return result;
    }

    public long getLength() {
        return this.length;
    }

    @Deprecated
    public long jsGet_length() {
        return getLength();
    }

    void setDenseOnly(boolean denseOnly) {
        if (denseOnly && !this.denseOnly) {
            throw new IllegalArgumentException();
        }
        this.denseOnly = denseOnly;
    }

    private void setLength(Object val) {
        if ((this.lengthAttr & 1) != 0) {
            return;
        }
        double d = ScriptRuntime.toNumber(val);
        long longVal = ScriptRuntime.toUint32(d);
        if (longVal != d) {
            String msg = ScriptRuntime.getMessage0("msg.arraylength.bad");
            throw ScriptRuntime.rangeError(msg);
        }
        if (this.denseOnly) {
            if (longVal < this.length) {
                Arrays.fill(this.dense, (int) longVal, this.dense.length, NOT_FOUND);
                this.length = longVal;
                this.modCount++;
                return;
            } else {
                if (longVal < 1431655764 && longVal < this.length * 1.5d && ensureCapacity((int) longVal)) {
                    this.length = longVal;
                    this.modCount++;
                    return;
                }
                this.denseOnly = false;
            }
        }
        if (longVal < this.length) {
            if (this.length - longVal > Http2Settings.DEFAULT_HEADER_TABLE_SIZE) {
                Object[] e = getIds();
                for (Object id : e) {
                    if (id instanceof String) {
                        String strId = (String) id;
                        if (toArrayIndex(strId) >= longVal) {
                            delete(strId);
                        }
                    } else {
                        int index = ((Integer) id).intValue();
                        if (index >= longVal) {
                            delete(index);
                        }
                    }
                }
            } else {
                long j = longVal;
                while (true) {
                    long i = j;
                    if (i >= this.length) {
                        break;
                    }
                    deleteElem(this, i);
                    j = i + 1;
                }
            }
        }
        this.length = longVal;
        this.modCount++;
    }

    static long getLengthProperty(Context cx, Scriptable obj, boolean throwIfTooLarge) {
        if (obj instanceof NativeString) {
            return ((NativeString) obj).getLength();
        }
        if (obj instanceof NativeArray) {
            return ((NativeArray) obj).getLength();
        }
        Object len = ScriptableObject.getProperty(obj, Length.TOKEN_NAME);
        if (len == Scriptable.NOT_FOUND) {
            return 0L;
        }
        double doubleLen = ScriptRuntime.toNumber(len);
        if (doubleLen <= 9.007199254740991E15d) {
            if (doubleLen < 0.0d) {
                return 0L;
            }
            return ScriptRuntime.toUint32(len);
        }
        if (throwIfTooLarge) {
            String msg = ScriptRuntime.getMessage0("msg.arraylength.bad");
            throw ScriptRuntime.rangeError(msg);
        }
        return 2147483647L;
    }

    private static Object setLengthProperty(Context cx, Scriptable target, long length) {
        Object len = ScriptRuntime.wrapNumber(length);
        ScriptableObject.putProperty(target, Length.TOKEN_NAME, len);
        return len;
    }

    private static void deleteElem(Scriptable target, long index) {
        int i = (int) index;
        if (i == index) {
            target.delete(i);
        } else {
            target.delete(Long.toString(index));
        }
    }

    private static Object getElem(Context cx, Scriptable target, long index) {
        Object elem = getRawElem(target, index);
        return elem != Scriptable.NOT_FOUND ? elem : Undefined.instance;
    }

    private static Object getRawElem(Scriptable target, long index) {
        if (index > 2147483647L) {
            return ScriptableObject.getProperty(target, Long.toString(index));
        }
        return ScriptableObject.getProperty(target, (int) index);
    }

    private static void defineElem(Context cx, Scriptable target, long index, Object value) {
        if (index > 2147483647L) {
            String id = Long.toString(index);
            target.put(id, target, value);
        } else {
            target.put((int) index, target, value);
        }
    }

    private static void setElem(Context cx, Scriptable target, long index, Object value) {
        if (index > 2147483647L) {
            String id = Long.toString(index);
            ScriptableObject.putProperty(target, id, value);
        } else {
            ScriptableObject.putProperty(target, (int) index, value);
        }
    }

    private static void setRawElem(Context cx, Scriptable target, long index, Object value) {
        if (value == NOT_FOUND) {
            deleteElem(target, index);
        } else {
            setElem(cx, target, index, value);
        }
    }

    private static String toStringHelper(Context cx, Scriptable scope, Scriptable thisObj, boolean toSource, boolean toLocale) {
        String separator;
        boolean toplevel;
        boolean iterating;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long length = getLengthProperty(cx, o, false);
        StringBuilder result = new StringBuilder(256);
        if (toSource) {
            result.append('[');
            separator = ", ";
        } else {
            separator = ",";
        }
        boolean haslast = false;
        long i = 0;
        if (cx.iterating == null) {
            toplevel = true;
            iterating = false;
            cx.iterating = new ObjToIntMap(31);
        } else {
            toplevel = false;
            iterating = cx.iterating.has(o);
        }
        if (!iterating) {
            try {
                cx.iterating.put(o, 0);
                boolean skipUndefinedAndNull = !toSource || cx.getLanguageVersion() < 150;
                i = 0;
                while (i < length) {
                    if (i > 0) {
                        result.append(separator);
                    }
                    Object elem = getRawElem(o, i);
                    if (elem == NOT_FOUND || (skipUndefinedAndNull && (elem == null || elem == Undefined.instance))) {
                        haslast = false;
                    } else {
                        haslast = true;
                        if (toSource) {
                            result.append(ScriptRuntime.uneval(cx, scope, elem));
                        } else if (elem instanceof String) {
                            result.append((String) elem);
                        } else {
                            if (toLocale) {
                                Callable fun = ScriptRuntime.getPropFunctionAndThis(elem, "toLocaleString", cx, scope);
                                Scriptable funThis = ScriptRuntime.lastStoredScriptable(cx);
                                elem = fun.call(cx, scope, funThis, ScriptRuntime.emptyArgs);
                            }
                            result.append(ScriptRuntime.toString(elem));
                        }
                    }
                    i++;
                }
                cx.iterating.remove(o);
            } finally {
                if (toplevel) {
                    cx.iterating = null;
                }
            }
        }
        if (toSource) {
            if (!haslast && i > 0) {
                result.append(", ]");
            } else {
                result.append(']');
            }
        }
        return result.toString();
    }

    private static String js_join(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object temp;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long llength = getLengthProperty(cx, o, false);
        int length = (int) llength;
        if (llength != length) {
            throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(llength));
        }
        String separator = (args.length < 1 || args[0] == Undefined.instance) ? "," : ScriptRuntime.toString(args[0]);
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    if (i != 0) {
                        sb.append(separator);
                    }
                    if (i < na.dense.length && (temp = na.dense[i]) != null && temp != Undefined.instance && temp != Scriptable.NOT_FOUND) {
                        sb.append(ScriptRuntime.toString(temp));
                    }
                }
                return sb.toString();
            }
        }
        if (length == 0) {
            return "";
        }
        String[] buf = new String[length];
        int total_size = 0;
        for (int i2 = 0; i2 != length; i2++) {
            Object temp2 = getElem(cx, o, i2);
            if (temp2 != null && temp2 != Undefined.instance) {
                String str = ScriptRuntime.toString(temp2);
                total_size += str.length();
                buf[i2] = str;
            }
        }
        StringBuilder sb2 = new StringBuilder(total_size + ((length - 1) * separator.length()));
        for (int i3 = 0; i3 != length; i3++) {
            if (i3 != 0) {
                sb2.append(separator);
            }
            String str2 = buf[i3];
            if (str2 != null) {
                sb2.append(str2);
            }
        }
        return sb2.toString();
    }

    private static Scriptable js_reverse(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly) {
                int i = 0;
                for (int j = ((int) na.length) - 1; i < j; j--) {
                    Object temp = na.dense[i];
                    na.dense[i] = na.dense[j];
                    na.dense[j] = temp;
                    i++;
                }
                return o;
            }
        }
        long len = getLengthProperty(cx, o, false);
        long half = len / 2;
        long j2 = 0;
        while (true) {
            long i2 = j2;
            if (i2 < half) {
                long j3 = (len - i2) - 1;
                Object temp1 = getRawElem(o, i2);
                Object temp2 = getRawElem(o, j3);
                setRawElem(cx, o, i2, temp2);
                setRawElem(cx, o, j3, temp1);
                j2 = i2 + 1;
            } else {
                return o;
            }
        }
    }

    private static Scriptable js_sort(final Context cx, final Scriptable scope, Scriptable thisObj, Object[] args) {
        Comparator<Object> comparator;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        if (args.length > 0 && Undefined.instance != args[0]) {
            final Callable jsCompareFunction = ScriptRuntime.getValueFunctionAndThis(args[0], cx);
            final Scriptable funThis = ScriptRuntime.lastStoredScriptable(cx);
            final Object[] cmpBuf = new Object[2];
            comparator = new ElementComparator(new Comparator<Object>() { // from class: org.mozilla.javascript.NativeArray.1
                @Override // java.util.Comparator
                public int compare(Object x, Object y) {
                    cmpBuf[0] = x;
                    cmpBuf[1] = y;
                    Object ret = jsCompareFunction.call(cx, scope, funThis, cmpBuf);
                    double d = ScriptRuntime.toNumber(ret);
                    int cmp = Double.compare(d, 0.0d);
                    if (cmp < 0) {
                        return -1;
                    }
                    if (cmp > 0) {
                        return 1;
                    }
                    return 0;
                }
            });
        } else {
            comparator = DEFAULT_COMPARATOR;
        }
        long llength = getLengthProperty(cx, o, false);
        int length = (int) llength;
        if (llength != length) {
            throw Context.reportRuntimeError1("msg.arraylength.too.big", String.valueOf(llength));
        }
        Object[] working = new Object[length];
        for (int i = 0; i != length; i++) {
            working[i] = getRawElem(o, i);
        }
        Sorting.get().hybridSort(working, comparator);
        for (int i2 = 0; i2 < length; i2++) {
            setRawElem(cx, o, i2, working[i2]);
        }
        return o;
    }

    private static Object js_push(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly && na.ensureCapacity(((int) na.length) + args.length)) {
                for (Object arg : args) {
                    Object[] objArr = na.dense;
                    long j = na.length;
                    na.length = j + 1;
                    objArr[(int) j] = arg;
                    na.modCount++;
                }
                return ScriptRuntime.wrapNumber(na.length);
            }
        }
        long length = getLengthProperty(cx, o, false);
        for (int i = 0; i < args.length; i++) {
            setElem(cx, o, length + i, args[i]);
        }
        Object lengthObj = setLengthProperty(cx, o, length + args.length);
        if (cx.getLanguageVersion() == 120) {
            return args.length == 0 ? Undefined.instance : args[args.length - 1];
        }
        return lengthObj;
    }

    private static Object js_pop(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object result;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly && na.length > 0) {
                na.length--;
                na.modCount++;
                Object result2 = na.dense[(int) na.length];
                na.dense[(int) na.length] = NOT_FOUND;
                return result2;
            }
        }
        long length = getLengthProperty(cx, o, false);
        if (length > 0) {
            length--;
            result = getElem(cx, o, length);
            deleteElem(o, length);
        } else {
            result = Undefined.instance;
        }
        setLengthProperty(cx, o, length);
        return result;
    }

    private static Object js_shift(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Object result;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly && na.length > 0) {
                na.length--;
                na.modCount++;
                Object result2 = na.dense[0];
                System.arraycopy(na.dense, 1, na.dense, 0, (int) na.length);
                na.dense[(int) na.length] = NOT_FOUND;
                return result2 == NOT_FOUND ? Undefined.instance : result2;
            }
        }
        long length = getLengthProperty(cx, o, false);
        if (length > 0) {
            length--;
            result = getElem(cx, o, 0L);
            if (length > 0) {
                long j = 1;
                while (true) {
                    long i = j;
                    if (i > length) {
                        break;
                    }
                    Object temp = getRawElem(o, i);
                    setRawElem(cx, o, i - 1, temp);
                    j = i + 1;
                }
            }
            deleteElem(o, length);
        } else {
            result = Undefined.instance;
        }
        setLengthProperty(cx, o, length);
        return result;
    }

    private static Object js_unshift(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly && na.ensureCapacity(((int) na.length) + args.length)) {
                System.arraycopy(na.dense, 0, na.dense, args.length, (int) na.length);
                for (int i = 0; i < args.length; i++) {
                    na.dense[i] = args[i];
                }
                na.length += args.length;
                na.modCount++;
                return ScriptRuntime.wrapNumber(na.length);
            }
        }
        long length = getLengthProperty(cx, o, false);
        int argc = args.length;
        if (args.length > 0) {
            if (length > 0) {
                long j = length;
                while (true) {
                    long last = j - 1;
                    if (last < 0) {
                        break;
                    }
                    Object temp = getRawElem(o, last);
                    setRawElem(cx, o, last + argc, temp);
                    j = last;
                }
            }
            for (int i2 = 0; i2 < args.length; i2++) {
                setElem(cx, o, i2, args[i2]);
            }
        }
        return setLengthProperty(cx, o, length + args.length);
    }

    private static Object js_splice(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        long count;
        Object result;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        NativeArray na = null;
        boolean denseMode = false;
        if (o instanceof NativeArray) {
            na = (NativeArray) o;
            denseMode = na.denseOnly;
        }
        Scriptable scope2 = getTopLevelScope(scope);
        int argc = args.length;
        if (argc == 0) {
            return cx.newArray(scope2, 0);
        }
        long length = getLengthProperty(cx, o, false);
        long begin = toSliceIndex(ScriptRuntime.toInteger(args[0]), length);
        int argc2 = argc - 1;
        if (args.length == 1) {
            count = length - begin;
        } else {
            double dcount = ScriptRuntime.toInteger(args[1]);
            if (dcount < 0.0d) {
                count = 0;
            } else if (dcount > length - begin) {
                count = length - begin;
            } else {
                count = (long) dcount;
            }
            argc2--;
        }
        long end = begin + count;
        if (count != 0) {
            if (count == 1 && cx.getLanguageVersion() == 120) {
                result = getElem(cx, o, begin);
            } else if (denseMode) {
                int intLen = (int) (end - begin);
                Object[] copy = new Object[intLen];
                System.arraycopy(na.dense, (int) begin, copy, 0, intLen);
                result = cx.newArray(scope2, copy);
            } else {
                Scriptable resultArray = cx.newArray(scope2, 0);
                long j = begin;
                while (true) {
                    long last = j;
                    if (last == end) {
                        break;
                    }
                    Object temp = getRawElem(o, last);
                    if (temp != NOT_FOUND) {
                        setElem(cx, resultArray, last - begin, temp);
                    }
                    j = last + 1;
                }
                setLengthProperty(cx, resultArray, end - begin);
                result = resultArray;
            }
        } else if (cx.getLanguageVersion() == 120) {
            result = Undefined.instance;
        } else {
            result = cx.newArray(scope2, 0);
        }
        long delta = argc2 - count;
        if (denseMode && length + delta < 2147483647L && na.ensureCapacity((int) (length + delta))) {
            System.arraycopy(na.dense, (int) end, na.dense, (int) (begin + argc2), (int) (length - end));
            if (argc2 > 0) {
                System.arraycopy(args, 2, na.dense, (int) begin, argc2);
            }
            if (delta < 0) {
                Arrays.fill(na.dense, (int) (length + delta), (int) length, NOT_FOUND);
            }
            na.length = length + delta;
            na.modCount++;
            return result;
        }
        if (delta > 0) {
            long j2 = length;
            while (true) {
                long last2 = j2 - 1;
                if (last2 < end) {
                    break;
                }
                Object temp2 = getRawElem(o, last2);
                setRawElem(cx, o, last2 + delta, temp2);
                j2 = last2;
            }
        } else if (delta < 0) {
            long j3 = end;
            while (true) {
                long last3 = j3;
                if (last3 >= length) {
                    break;
                }
                Object temp3 = getRawElem(o, last3);
                setRawElem(cx, o, last3 + delta, temp3);
                j3 = last3 + 1;
            }
            long j4 = length;
            while (true) {
                long k = j4 - 1;
                if (k < length + delta) {
                    break;
                }
                deleteElem(o, k);
                j4 = k;
            }
        }
        int argoffset = args.length - argc2;
        for (int i = 0; i < argc2; i++) {
            setElem(cx, o, begin + i, args[i + argoffset]);
        }
        setLengthProperty(cx, o, length + delta);
        return result;
    }

    private static boolean isConcatSpreadable(Context cx, Scriptable scope, Object val) {
        Object spreadable;
        if ((val instanceof Scriptable) && (spreadable = ScriptableObject.getProperty((Scriptable) val, SymbolKey.IS_CONCAT_SPREADABLE)) != Scriptable.NOT_FOUND && !Undefined.isUndefined(spreadable)) {
            return ScriptRuntime.toBoolean(spreadable);
        }
        if (cx.getLanguageVersion() < 200) {
            Function ctor = ScriptRuntime.getExistingCtor(cx, scope, "Array");
            if (ScriptRuntime.instanceOf(val, ctor, cx)) {
                return true;
            }
        }
        return js_isArray(val);
    }

    private static long concatSpreadArg(Context cx, Scriptable result, Scriptable arg, long offset) {
        long srclen = getLengthProperty(cx, arg, false);
        long newlen = srclen + offset;
        if (newlen <= 2147483647L && (result instanceof NativeArray)) {
            NativeArray denseResult = (NativeArray) result;
            if (denseResult.denseOnly && (arg instanceof NativeArray)) {
                NativeArray denseArg = (NativeArray) arg;
                if (denseArg.denseOnly) {
                    denseResult.ensureCapacity((int) newlen);
                    System.arraycopy(denseArg.dense, 0, denseResult.dense, (int) offset, (int) srclen);
                    return newlen;
                }
            }
        }
        long dstpos = offset;
        long srcpos = 0;
        while (srcpos < srclen) {
            Object temp = getRawElem(arg, srcpos);
            if (temp != Scriptable.NOT_FOUND) {
                defineElem(cx, result, dstpos, temp);
            }
            srcpos++;
            dstpos++;
        }
        return newlen;
    }

    private static long doConcat(Context cx, Scriptable scope, Scriptable result, Object arg, long offset) {
        if (isConcatSpreadable(cx, scope, arg)) {
            return concatSpreadArg(cx, result, (Scriptable) arg, offset);
        }
        defineElem(cx, result, offset, arg);
        return offset + 1;
    }

    private static Scriptable js_concat(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        Scriptable scope2 = getTopLevelScope(scope);
        Scriptable result = cx.newArray(scope2, 0);
        long length = doConcat(cx, scope2, result, o, 0L);
        for (Object arg : args) {
            length = doConcat(cx, scope2, result, arg, length);
        }
        setLengthProperty(cx, result, length);
        return result;
    }

    private static Scriptable js_slice(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        long begin;
        long end;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        Scriptable result = cx.newArray(scope, 0);
        long len = getLengthProperty(cx, o, false);
        if (args.length == 0) {
            begin = 0;
            end = len;
        } else {
            begin = toSliceIndex(ScriptRuntime.toInteger(args[0]), len);
            if (args.length == 1 || args[1] == Undefined.instance) {
                end = len;
            } else {
                end = toSliceIndex(ScriptRuntime.toInteger(args[1]), len);
            }
        }
        long j = begin;
        while (true) {
            long slot = j;
            if (slot < end) {
                Object temp = getRawElem(o, slot);
                if (temp != NOT_FOUND) {
                    defineElem(cx, result, slot - begin, temp);
                }
                j = slot + 1;
            } else {
                setLengthProperty(cx, result, Math.max(0L, end - begin));
                return result;
            }
        }
    }

    private static long toSliceIndex(double value, long length) {
        long result;
        if (value < 0.0d) {
            if (value + length < 0.0d) {
                result = 0;
            } else {
                result = (long) (value + length);
            }
        } else if (value > length) {
            result = length;
        } else {
            result = (long) value;
        }
        return result;
    }

    private static Object js_indexOf(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        long start;
        Object compareTo = args.length > 0 ? args[0] : Undefined.instance;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long length = getLengthProperty(cx, o, false);
        if (args.length < 2) {
            start = 0;
        } else {
            start = (long) ScriptRuntime.toInteger(args[1]);
            if (start < 0) {
                start += length;
                if (start < 0) {
                    start = 0;
                }
            }
            if (start > length - 1) {
                return NEGATIVE_ONE;
            }
        }
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly) {
                Scriptable proto = na.getPrototype();
                for (int i = (int) start; i < length; i++) {
                    Object val = na.dense[i];
                    if (val == NOT_FOUND && proto != null) {
                        val = ScriptableObject.getProperty(proto, i);
                    }
                    if (val != NOT_FOUND && ScriptRuntime.shallowEq(val, compareTo)) {
                        return Long.valueOf(i);
                    }
                }
                return NEGATIVE_ONE;
            }
        }
        long j = start;
        while (true) {
            long i2 = j;
            if (i2 < length) {
                Object val2 = getRawElem(o, i2);
                if (val2 == NOT_FOUND || !ScriptRuntime.shallowEq(val2, compareTo)) {
                    j = i2 + 1;
                } else {
                    return Long.valueOf(i2);
                }
            } else {
                return NEGATIVE_ONE;
            }
        }
    }

    private static Object js_lastIndexOf(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        long start;
        Object compareTo = args.length > 0 ? args[0] : Undefined.instance;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long length = getLengthProperty(cx, o, false);
        if (args.length < 2) {
            start = length - 1;
        } else {
            start = (long) ScriptRuntime.toInteger(args[1]);
            if (start >= length) {
                start = length - 1;
            } else if (start < 0) {
                start += length;
            }
            if (start < 0) {
                return NEGATIVE_ONE;
            }
        }
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly) {
                Scriptable proto = na.getPrototype();
                for (int i = (int) start; i >= 0; i--) {
                    Object val = na.dense[i];
                    if (val == NOT_FOUND && proto != null) {
                        val = ScriptableObject.getProperty(proto, i);
                    }
                    if (val != NOT_FOUND && ScriptRuntime.shallowEq(val, compareTo)) {
                        return Long.valueOf(i);
                    }
                }
                return NEGATIVE_ONE;
            }
        }
        long j = start;
        while (true) {
            long i2 = j;
            if (i2 >= 0) {
                Object val2 = getRawElem(o, i2);
                if (val2 == NOT_FOUND || !ScriptRuntime.shallowEq(val2, compareTo)) {
                    j = i2 - 1;
                } else {
                    return Long.valueOf(i2);
                }
            } else {
                return NEGATIVE_ONE;
            }
        }
    }

    private static Boolean js_includes(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        long k;
        Object compareTo = args.length > 0 ? args[0] : Undefined.instance;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long len = ScriptRuntime.toLength(new Object[]{getProperty(thisObj, Length.TOKEN_NAME)}, 0);
        if (len == 0) {
            return Boolean.FALSE;
        }
        if (args.length < 2) {
            k = 0;
        } else {
            k = (long) ScriptRuntime.toInteger(args[1]);
            if (k < 0) {
                k += len;
                if (k < 0) {
                    k = 0;
                }
            }
            if (k > len - 1) {
                return Boolean.FALSE;
            }
        }
        if (o instanceof NativeArray) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly) {
                Scriptable proto = na.getPrototype();
                for (int i = (int) k; i < len; i++) {
                    Object elementK = na.dense[i];
                    if (elementK == NOT_FOUND && proto != null) {
                        elementK = ScriptableObject.getProperty(proto, i);
                    }
                    if (elementK == NOT_FOUND) {
                        elementK = Undefined.instance;
                    }
                    if (ScriptRuntime.sameZero(elementK, compareTo)) {
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            }
        }
        while (k < len) {
            Object elementK2 = getRawElem(o, k);
            if (elementK2 == NOT_FOUND) {
                elementK2 = Undefined.instance;
            }
            if (!ScriptRuntime.sameZero(elementK2, compareTo)) {
                k++;
            } else {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private static Object js_fill(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        long k;
        long fin;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long len = getLengthProperty(cx, o, false);
        long relativeStart = 0;
        if (args.length >= 2) {
            relativeStart = (long) ScriptRuntime.toInteger(args[1]);
        }
        if (relativeStart < 0) {
            k = Math.max(len + relativeStart, 0L);
        } else {
            k = Math.min(relativeStart, len);
        }
        long relativeEnd = len;
        if (args.length >= 3 && !Undefined.isUndefined(args[2])) {
            relativeEnd = (long) ScriptRuntime.toInteger(args[2]);
        }
        if (relativeEnd < 0) {
            fin = Math.max(len + relativeEnd, 0L);
        } else {
            fin = Math.min(relativeEnd, len);
        }
        Object value = args.length > 0 ? args[0] : Undefined.instance;
        long j = k;
        while (true) {
            long i = j;
            if (i < fin) {
                setRawElem(cx, thisObj, i, value);
                j = i + 1;
            } else {
                return thisObj;
            }
        }
    }

    private static Object js_copyWithin(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        long to;
        long from;
        long fin;
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long len = getLengthProperty(cx, o, false);
        Object targetArg = args.length >= 1 ? args[0] : Undefined.instance;
        long relativeTarget = (long) ScriptRuntime.toInteger(targetArg);
        if (relativeTarget < 0) {
            to = Math.max(len + relativeTarget, 0L);
        } else {
            to = Math.min(relativeTarget, len);
        }
        Object startArg = args.length >= 2 ? args[1] : Undefined.instance;
        long relativeStart = (long) ScriptRuntime.toInteger(startArg);
        if (relativeStart < 0) {
            from = Math.max(len + relativeStart, 0L);
        } else {
            from = Math.min(relativeStart, len);
        }
        long relativeEnd = len;
        if (args.length >= 3 && !Undefined.isUndefined(args[2])) {
            relativeEnd = (long) ScriptRuntime.toInteger(args[2]);
        }
        if (relativeEnd < 0) {
            fin = Math.max(len + relativeEnd, 0L);
        } else {
            fin = Math.min(relativeEnd, len);
        }
        long count = Math.min(fin - from, len - to);
        int direction = 1;
        if (from < to && to < from + count) {
            direction = -1;
            from = (from + count) - 1;
            to = (to + count) - 1;
        }
        if ((o instanceof NativeArray) && count <= 2147483647L) {
            NativeArray na = (NativeArray) o;
            if (na.denseOnly) {
                while (count > 0) {
                    na.dense[(int) to] = na.dense[(int) from];
                    from += direction;
                    to += direction;
                    count--;
                }
                return thisObj;
            }
        }
        while (count > 0) {
            Object temp = getRawElem(o, from);
            if (temp == Scriptable.NOT_FOUND || Undefined.isUndefined(temp)) {
                deleteElem(o, to);
            } else {
                setElem(cx, o, to, temp);
            }
            from += direction;
            to += direction;
            count--;
        }
        return thisObj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x01c2, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01c2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object iterativeMethod(org.mozilla.javascript.Context r9, org.mozilla.javascript.IdFunctionObject r10, org.mozilla.javascript.Scriptable r11, org.mozilla.javascript.Scriptable r12, java.lang.Object[] r13) {
        /*
            Method dump skipped, instructions count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeArray.iterativeMethod(org.mozilla.javascript.Context, org.mozilla.javascript.IdFunctionObject, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    private static Object reduceMethod(Context cx, int id, Scriptable scope, Scriptable thisObj, Object[] args) {
        Scriptable o = ScriptRuntime.toObject(cx, scope, thisObj);
        long length = getLengthProperty(cx, o, false);
        Object callbackArg = args.length > 0 ? args[0] : Undefined.instance;
        if (callbackArg == null || !(callbackArg instanceof Function)) {
            throw ScriptRuntime.notFunctionError(callbackArg);
        }
        Function f = (Function) callbackArg;
        Scriptable parent = ScriptableObject.getTopLevelScope(f);
        boolean movingLeft = id == 24;
        Object value = args.length > 1 ? args[1] : Scriptable.NOT_FOUND;
        long j = 0;
        while (true) {
            long i = j;
            if (i >= length) {
                break;
            }
            long index = movingLeft ? i : (length - 1) - i;
            Object elem = getRawElem(o, index);
            if (elem != Scriptable.NOT_FOUND) {
                if (value == Scriptable.NOT_FOUND) {
                    value = elem;
                } else {
                    Object[] innerArgs = {value, elem, Long.valueOf(index), o};
                    value = f.call(cx, parent, parent, innerArgs);
                }
            }
            j = i + 1;
        }
        if (value == Scriptable.NOT_FOUND) {
            throw ScriptRuntime.typeError0("msg.empty.array.reduce");
        }
        return value;
    }

    private static boolean js_isArray(Object o) {
        if (!(o instanceof Scriptable)) {
            return false;
        }
        return "Array".equals(((Scriptable) o).getClassName());
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return toArray(ScriptRuntime.emptyArgs);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray(Object[] a) {
        long longLen = this.length;
        if (longLen > 2147483647L) {
            throw new IllegalStateException();
        }
        int len = (int) longLen;
        Object[] array = a.length >= len ? a : (Object[]) Array.newInstance(a.getClass().getComponentType(), len);
        for (int i = 0; i < len; i++) {
            array[i] = get(i);
        }
        return array;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection c) {
        for (Object aC : c) {
            if (!contains(aC)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public int size() {
        long longLen = this.length;
        if (longLen > 2147483647L) {
            throw new IllegalStateException();
        }
        return (int) longLen;
    }

    @Override // org.mozilla.javascript.ScriptableObject, java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.length == 0;
    }

    public Object get(long index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        Object value = getRawElem(this, index);
        if (value == Scriptable.NOT_FOUND || value == Undefined.instance) {
            return null;
        }
        if (value instanceof Wrapper) {
            return ((Wrapper) value).unwrap();
        }
        return value;
    }

    @Override // java.util.List
    public Object get(int index) {
        return get(index);
    }

    @Override // java.util.List
    public int indexOf(Object o) {
        long longLen = this.length;
        if (longLen > 2147483647L) {
            throw new IllegalStateException();
        }
        int len = (int) longLen;
        if (o == null) {
            for (int i = 0; i < len; i++) {
                if (get(i) == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = 0; i2 < len; i2++) {
            if (o.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object o) {
        long longLen = this.length;
        if (longLen > 2147483647L) {
            throw new IllegalStateException();
        }
        int len = (int) longLen;
        if (o == null) {
            for (int i = len - 1; i >= 0; i--) {
                if (get(i) == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i2 = len - 1; i2 >= 0; i2--) {
            if (o.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public ListIterator listIterator(final int start) {
        final int len = size();
        if (start < 0 || start > len) {
            throw new IndexOutOfBoundsException("Index: " + start);
        }
        return new ListIterator() { // from class: org.mozilla.javascript.NativeArray.2
            int cursor;
            int modCount;

            {
                this.cursor = start;
                this.modCount = NativeArray.this.modCount;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                return this.cursor < len;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public Object next() {
                NativeArray.this.checkModCount(this.modCount);
                if (this.cursor == len) {
                    throw new NoSuchElementException();
                }
                NativeArray nativeArray = NativeArray.this;
                int i = this.cursor;
                this.cursor = i + 1;
                return nativeArray.get(i);
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return this.cursor > 0;
            }

            @Override // java.util.ListIterator
            public Object previous() {
                NativeArray.this.checkModCount(this.modCount);
                if (this.cursor == 0) {
                    throw new NoSuchElementException();
                }
                NativeArray nativeArray = NativeArray.this;
                int i = this.cursor - 1;
                this.cursor = i;
                return nativeArray.get(i);
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.cursor;
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.cursor - 1;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void add(Object o) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.ListIterator
            public void set(Object o) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public Object remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public List subList(final int fromIndex, final int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > size()) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        return new AbstractList() { // from class: org.mozilla.javascript.NativeArray.3
            private int modCount;

            {
                this.modCount = NativeArray.this.modCount;
            }

            @Override // java.util.AbstractList, java.util.List
            public Object get(int index) {
                NativeArray.this.checkModCount(this.modCount);
                return NativeArray.this.get(index + fromIndex);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                NativeArray.this.checkModCount(this.modCount);
                return toIndex - fromIndex;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkModCount(int modCount) {
        if (this.modCount != modCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol k) {
        if (SymbolKey.ITERATOR.equals(k)) {
            return 32;
        }
        return 0;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeArray$StringLikeComparator.class */
    public static final class StringLikeComparator implements Comparator<Object>, Serializable {
        private static final long serialVersionUID = 5299017659728190979L;

        @Override // java.util.Comparator
        public int compare(Object x, Object y) {
            String a = ScriptRuntime.toString(x);
            String b = ScriptRuntime.toString(y);
            return a.compareTo(b);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeArray$ElementComparator.class */
    public static final class ElementComparator implements Comparator<Object>, Serializable {
        private static final long serialVersionUID = -1189948017688708858L;
        private final Comparator<Object> child;

        public ElementComparator() {
            this.child = NativeArray.STRING_COMPARATOR;
        }

        public ElementComparator(Comparator<Object> c) {
            this.child = c;
        }

        @Override // java.util.Comparator
        public int compare(Object x, Object y) {
            if (x == Undefined.instance) {
                if (y == Undefined.instance) {
                    return 0;
                }
                if (y == Scriptable.NOT_FOUND) {
                    return -1;
                }
                return 1;
            }
            if (x == Scriptable.NOT_FOUND) {
                return y == Scriptable.NOT_FOUND ? 0 : 1;
            }
            if (y == Scriptable.NOT_FOUND || y == Undefined.instance) {
                return -1;
            }
            return this.child.compare(x, y);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02b5 A[PHI: r5 r6
  0x02b5: PHI (r5v1 'id' int) = 
  (r5v0 'id' int)
  (r5v3 'id' int)
  (r5v0 'id' int)
  (r5v4 'id' int)
  (r5v5 'id' int)
  (r5v6 'id' int)
  (r5v7 'id' int)
  (r5v8 'id' int)
  (r5v0 'id' int)
  (r5v9 'id' int)
  (r5v10 'id' int)
  (r5v11 'id' int)
  (r5v0 'id' int)
  (r5v12 'id' int)
  (r5v13 'id' int)
  (r5v14 'id' int)
  (r5v15 'id' int)
  (r5v16 'id' int)
  (r5v0 'id' int)
  (r5v17 'id' int)
  (r5v18 'id' int)
  (r5v19 'id' int)
  (r5v20 'id' int)
  (r5v21 'id' int)
  (r5v0 'id' int)
  (r5v22 'id' int)
  (r5v23 'id' int)
  (r5v24 'id' int)
  (r5v0 'id' int)
  (r5v25 'id' int)
  (r5v26 'id' int)
  (r5v27 'id' int)
  (r5v28 'id' int)
  (r5v29 'id' int)
  (r5v30 'id' int)
  (r5v31 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
  (r5v0 'id' int)
 binds: [B:3:0x0008, B:73:0x02ad, B:71:0x02a1, B:72:0x02a4, B:69:0x0294, B:66:0x0285, B:63:0x026e, B:62:0x0265, B:60:0x025a, B:61:0x025d, B:58:0x024e, B:55:0x023e, B:46:0x01cd, B:51:0x0224, B:50:0x021b, B:49:0x0212, B:48:0x0209, B:47:0x0200, B:38:0x0165, B:43:0x01bc, B:42:0x01b3, B:41:0x01aa, B:40:0x01a1, B:39:0x0198, B:35:0x0154, B:36:0x0157, B:33:0x0147, B:30:0x0137, B:19:0x0096, B:26:0x011d, B:25:0x0114, B:24:0x010b, B:23:0x0102, B:22:0x00f9, B:21:0x00f0, B:20:0x00e8, B:12:0x0074, B:14:0x007e, B:16:0x0088, B:7:0x005d, B:9:0x0067] A[DONT_GENERATE, DONT_INLINE]
  0x02b5: PHI (r6v1 'X' java.lang.String) = 
  (r6v0 'X' java.lang.String)
  (r6v2 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v3 'X' java.lang.String)
  (r6v4 'X' java.lang.String)
  (r6v5 'X' java.lang.String)
  (r6v6 'X' java.lang.String)
  (r6v7 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v8 'X' java.lang.String)
  (r6v9 'X' java.lang.String)
  (r6v10 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v11 'X' java.lang.String)
  (r6v12 'X' java.lang.String)
  (r6v13 'X' java.lang.String)
  (r6v14 'X' java.lang.String)
  (r6v15 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v16 'X' java.lang.String)
  (r6v17 'X' java.lang.String)
  (r6v18 'X' java.lang.String)
  (r6v19 'X' java.lang.String)
  (r6v20 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v21 'X' java.lang.String)
  (r6v22 'X' java.lang.String)
  (r6v23 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v24 'X' java.lang.String)
  (r6v25 'X' java.lang.String)
  (r6v26 'X' java.lang.String)
  (r6v27 'X' java.lang.String)
  (r6v28 'X' java.lang.String)
  (r6v29 'X' java.lang.String)
  (r6v30 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
  (r6v0 'X' java.lang.String)
 binds: [B:3:0x0008, B:73:0x02ad, B:71:0x02a1, B:72:0x02a4, B:69:0x0294, B:66:0x0285, B:63:0x026e, B:62:0x0265, B:60:0x025a, B:61:0x025d, B:58:0x024e, B:55:0x023e, B:46:0x01cd, B:51:0x0224, B:50:0x021b, B:49:0x0212, B:48:0x0209, B:47:0x0200, B:38:0x0165, B:43:0x01bc, B:42:0x01b3, B:41:0x01aa, B:40:0x01a1, B:39:0x0198, B:35:0x0154, B:36:0x0157, B:33:0x0147, B:30:0x0137, B:19:0x0096, B:26:0x011d, B:25:0x0114, B:24:0x010b, B:23:0x0102, B:22:0x00f9, B:21:0x00f0, B:20:0x00e8, B:12:0x0074, B:14:0x007e, B:16:0x0088, B:7:0x005d, B:9:0x0067] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.mozilla.javascript.IdScriptableObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int findPrototypeId(java.lang.String r4) {
        /*
            Method dump skipped, instructions count: 717
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeArray.findPrototypeId(java.lang.String):int");
    }
}
