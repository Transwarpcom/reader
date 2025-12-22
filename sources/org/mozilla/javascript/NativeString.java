package org.mozilla.javascript;

import com.jayway.jsonpath.internal.function.text.Length;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.TopLevel;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/NativeString.class */
final class NativeString extends IdScriptableObject {
    private static final long serialVersionUID = 920268368584188687L;
    private static final Object STRING_TAG = "String";
    private static final int Id_length = 1;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int ConstructorId_fromCharCode = -1;
    private static final int ConstructorId_fromCodePoint = -2;
    private static final int ConstructorId_raw = -3;
    private static final int Id_constructor = 1;
    private static final int Id_toString = 2;
    private static final int Id_toSource = 3;
    private static final int Id_valueOf = 4;
    private static final int Id_charAt = 5;
    private static final int Id_charCodeAt = 6;
    private static final int Id_indexOf = 7;
    private static final int Id_lastIndexOf = 8;
    private static final int Id_split = 9;
    private static final int Id_substring = 10;
    private static final int Id_toLowerCase = 11;
    private static final int Id_toUpperCase = 12;
    private static final int Id_substr = 13;
    private static final int Id_concat = 14;
    private static final int Id_slice = 15;
    private static final int Id_bold = 16;
    private static final int Id_italics = 17;
    private static final int Id_fixed = 18;
    private static final int Id_strike = 19;
    private static final int Id_small = 20;
    private static final int Id_big = 21;
    private static final int Id_blink = 22;
    private static final int Id_sup = 23;
    private static final int Id_sub = 24;
    private static final int Id_fontsize = 25;
    private static final int Id_fontcolor = 26;
    private static final int Id_link = 27;
    private static final int Id_anchor = 28;
    private static final int Id_equals = 29;
    private static final int Id_equalsIgnoreCase = 30;
    private static final int Id_match = 31;
    private static final int Id_search = 32;
    private static final int Id_replace = 33;
    private static final int Id_localeCompare = 34;
    private static final int Id_toLocaleLowerCase = 35;
    private static final int Id_toLocaleUpperCase = 36;
    private static final int Id_trim = 37;
    private static final int Id_trimLeft = 38;
    private static final int Id_trimRight = 39;
    private static final int Id_includes = 40;
    private static final int Id_startsWith = 41;
    private static final int Id_endsWith = 42;
    private static final int Id_normalize = 43;
    private static final int Id_repeat = 44;
    private static final int Id_codePointAt = 45;
    private static final int Id_padStart = 46;
    private static final int Id_padEnd = 47;
    private static final int SymbolId_iterator = 48;
    private static final int Id_trimStart = 49;
    private static final int Id_trimEnd = 50;
    private static final int MAX_PROTOTYPE_ID = 50;
    private static final int ConstructorId_charAt = -5;
    private static final int ConstructorId_charCodeAt = -6;
    private static final int ConstructorId_indexOf = -7;
    private static final int ConstructorId_lastIndexOf = -8;
    private static final int ConstructorId_split = -9;
    private static final int ConstructorId_substring = -10;
    private static final int ConstructorId_toLowerCase = -11;
    private static final int ConstructorId_toUpperCase = -12;
    private static final int ConstructorId_substr = -13;
    private static final int ConstructorId_concat = -14;
    private static final int ConstructorId_slice = -15;
    private static final int ConstructorId_equalsIgnoreCase = -30;
    private static final int ConstructorId_match = -31;
    private static final int ConstructorId_search = -32;
    private static final int ConstructorId_replace = -33;
    private static final int ConstructorId_localeCompare = -34;
    private static final int ConstructorId_toLocaleLowerCase = -35;
    private CharSequence string;

    static void init(Scriptable scope, boolean sealed) {
        NativeString obj = new NativeString("");
        obj.exportAsJSClass(50, scope, sealed);
    }

    NativeString(CharSequence s) {
        this.string = s;
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "String";
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 1;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String s) {
        if (s.equals(Length.TOKEN_NAME)) {
            return instanceIdInfo(7, 1);
        }
        return super.findInstanceIdInfo(s);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int id) {
        return id == 1 ? Length.TOKEN_NAME : super.getInstanceIdName(id);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int id) {
        if (id == 1) {
            return ScriptRuntime.wrapInt(this.string.length());
        }
        return super.getInstanceIdValue(id);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void fillConstructorProperties(IdFunctionObject ctor) {
        addIdFunctionProperty(ctor, STRING_TAG, -1, "fromCharCode", 1);
        addIdFunctionProperty(ctor, STRING_TAG, -2, "fromCodePoint", 1);
        addIdFunctionProperty(ctor, STRING_TAG, -3, "raw", 1);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_charAt, "charAt", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_charCodeAt, "charCodeAt", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_indexOf, "indexOf", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_lastIndexOf, "lastIndexOf", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_split, "split", 3);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_substring, "substring", 3);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_toLowerCase, "toLowerCase", 1);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_toUpperCase, "toUpperCase", 1);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_substr, "substr", 3);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_concat, "concat", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_slice, "slice", 3);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_equalsIgnoreCase, "equalsIgnoreCase", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_match, BeanDefinitionParserDelegate.ARG_TYPE_MATCH_ATTRIBUTE, 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_search, "search", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_replace, ch.qos.logback.core.pattern.parser.Parser.REPLACE_CONVERTER_WORD, 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_localeCompare, "localeCompare", 2);
        addIdFunctionProperty(ctor, STRING_TAG, ConstructorId_toLocaleLowerCase, "toLocaleLowerCase", 1);
        super.fillConstructorProperties(ctor);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int id) {
        int arity;
        String s;
        if (id == 48) {
            initPrototypeMethod(STRING_TAG, id, SymbolKey.ITERATOR, "[Symbol.iterator]", 0);
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
                s = "toSource";
                break;
            case 4:
                arity = 0;
                s = "valueOf";
                break;
            case 5:
                arity = 1;
                s = "charAt";
                break;
            case 6:
                arity = 1;
                s = "charCodeAt";
                break;
            case 7:
                arity = 1;
                s = "indexOf";
                break;
            case 8:
                arity = 1;
                s = "lastIndexOf";
                break;
            case 9:
                arity = 2;
                s = "split";
                break;
            case 10:
                arity = 2;
                s = "substring";
                break;
            case 11:
                arity = 0;
                s = "toLowerCase";
                break;
            case 12:
                arity = 0;
                s = "toUpperCase";
                break;
            case 13:
                arity = 2;
                s = "substr";
                break;
            case 14:
                arity = 1;
                s = "concat";
                break;
            case 15:
                arity = 2;
                s = "slice";
                break;
            case 16:
                arity = 0;
                s = "bold";
                break;
            case 17:
                arity = 0;
                s = "italics";
                break;
            case 18:
                arity = 0;
                s = "fixed";
                break;
            case 19:
                arity = 0;
                s = "strike";
                break;
            case 20:
                arity = 0;
                s = "small";
                break;
            case 21:
                arity = 0;
                s = "big";
                break;
            case 22:
                arity = 0;
                s = "blink";
                break;
            case 23:
                arity = 0;
                s = "sup";
                break;
            case 24:
                arity = 0;
                s = "sub";
                break;
            case 25:
                arity = 0;
                s = "fontsize";
                break;
            case 26:
                arity = 0;
                s = "fontcolor";
                break;
            case 27:
                arity = 0;
                s = "link";
                break;
            case 28:
                arity = 0;
                s = "anchor";
                break;
            case 29:
                arity = 1;
                s = "equals";
                break;
            case 30:
                arity = 1;
                s = "equalsIgnoreCase";
                break;
            case 31:
                arity = 1;
                s = BeanDefinitionParserDelegate.ARG_TYPE_MATCH_ATTRIBUTE;
                break;
            case 32:
                arity = 1;
                s = "search";
                break;
            case 33:
                arity = 2;
                s = ch.qos.logback.core.pattern.parser.Parser.REPLACE_CONVERTER_WORD;
                break;
            case 34:
                arity = 1;
                s = "localeCompare";
                break;
            case 35:
                arity = 0;
                s = "toLocaleLowerCase";
                break;
            case 36:
                arity = 0;
                s = "toLocaleUpperCase";
                break;
            case 37:
                arity = 0;
                s = "trim";
                break;
            case 38:
                arity = 0;
                s = "trimLeft";
                break;
            case 39:
                arity = 0;
                s = "trimRight";
                break;
            case 40:
                arity = 1;
                s = "includes";
                break;
            case 41:
                arity = 1;
                s = "startsWith";
                break;
            case 42:
                arity = 1;
                s = "endsWith";
                break;
            case 43:
                arity = 0;
                s = "normalize";
                break;
            case 44:
                arity = 1;
                s = "repeat";
                break;
            case 45:
                arity = 1;
                s = "codePointAt";
                break;
            case 46:
                arity = 1;
                s = "padStart";
                break;
            case 47:
                arity = 1;
                s = "padEnd";
                break;
            case 48:
            default:
                throw new IllegalArgumentException(String.valueOf(id));
            case 49:
                arity = 0;
                s = "trimStart";
                break;
            case 50:
                arity = 0;
                s = "trimEnd";
                break;
        }
        initPrototypeMethod(STRING_TAG, id, s, (String) null, arity);
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x0420, code lost:
    
        r1 = r11;
        r3 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x042a, code lost:
    
        if (r13 != 46) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x042d, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0431, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0435, code lost:
    
        return js_pad(r9, r1, r8, r3, r4);
     */
    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object execIdCall(org.mozilla.javascript.IdFunctionObject r8, org.mozilla.javascript.Context r9, org.mozilla.javascript.Scriptable r10, org.mozilla.javascript.Scriptable r11, java.lang.Object[] r12) {
        /*
            Method dump skipped, instructions count: 2060
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.execIdCall(org.mozilla.javascript.IdFunctionObject, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    private static NativeString realThis(Scriptable thisObj, IdFunctionObject f) {
        if (!(thisObj instanceof NativeString)) {
            throw incompatibleCallError(f);
        }
        return (NativeString) thisObj;
    }

    private static String tagify(Scriptable thisObj, String tag, String attribute, Object[] args) {
        String str = ScriptRuntime.toString(thisObj);
        StringBuilder result = new StringBuilder();
        result.append('<').append(tag);
        if (attribute != null) {
            result.append(' ').append(attribute).append("=\"").append(ScriptRuntime.toString(args, 0)).append('\"');
        }
        result.append('>').append(str).append("</").append(tag).append('>');
        return result.toString();
    }

    public CharSequence toCharSequence() {
        return this.string;
    }

    public String toString() {
        return this.string instanceof String ? (String) this.string : this.string.toString();
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public Object get(int index, Scriptable start) {
        if (0 <= index && index < this.string.length()) {
            return String.valueOf(this.string.charAt(index));
        }
        return super.get(index, start);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public void put(int index, Scriptable start, Object value) {
        if (0 <= index && index < this.string.length()) {
            return;
        }
        super.put(index, start, value);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public boolean has(int index, Scriptable start) {
        if (0 <= index && index < this.string.length()) {
            return true;
        }
        return super.has(index, start);
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public int getAttributes(int index) {
        if (0 <= index && index < this.string.length()) {
            int attribs = 5;
            if (Context.getContext().getLanguageVersion() < 200) {
                attribs = 5 | 2;
            }
            return attribs;
        }
        return super.getAttributes(index);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected Object[] getIds(boolean nonEnumerable, boolean getSymbols) {
        Context cx = Context.getCurrentContext();
        if (cx != null && cx.getLanguageVersion() >= 200) {
            Object[] sids = super.getIds(nonEnumerable, getSymbols);
            Object[] a = new Object[sids.length + this.string.length()];
            int i = 0;
            while (i < this.string.length()) {
                a[i] = Integer.valueOf(i);
                i++;
            }
            System.arraycopy(sids, 0, a, i, sids.length);
            return a;
        }
        return super.getIds(nonEnumerable, getSymbols);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.ScriptableObject
    protected ScriptableObject getOwnPropertyDescriptor(Context cx, Object id) {
        if (!(id instanceof Symbol) && cx != null && cx.getLanguageVersion() >= 200) {
            ScriptRuntime.StringIdOrIndex s = ScriptRuntime.toStringIdOrIndex(cx, id);
            if (s.stringId == null && 0 <= s.index && s.index < this.string.length()) {
                String value = String.valueOf(this.string.charAt(s.index));
                return defaultIndexPropertyDescriptor(value);
            }
        }
        return super.getOwnPropertyDescriptor(cx, id);
    }

    private ScriptableObject defaultIndexPropertyDescriptor(Object value) {
        Scriptable scope = getParentScope();
        if (scope == null) {
            scope = this;
        }
        ScriptableObject desc = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(desc, scope, TopLevel.Builtins.Object);
        desc.defineProperty("value", value, 0);
        desc.defineProperty("writable", Boolean.FALSE, 0);
        desc.defineProperty("enumerable", Boolean.TRUE, 0);
        desc.defineProperty("configurable", Boolean.FALSE, 0);
        return desc;
    }

    private static int js_indexOf(int methodId, String target, Object[] args) {
        String searchStr = ScriptRuntime.toString(args, 0);
        double position = ScriptRuntime.toInteger(args, 1);
        if (methodId != 41 && methodId != 42 && searchStr.length() == 0) {
            return position > ((double) target.length()) ? target.length() : (int) position;
        }
        if (methodId != 41 && methodId != 42 && position > target.length()) {
            return -1;
        }
        if (position < 0.0d) {
            position = 0.0d;
        } else if (position > target.length()) {
            position = target.length();
        } else if (methodId == 42 && (Double.isNaN(position) || position > target.length())) {
            position = target.length();
        }
        if (42 == methodId) {
            if (args.length == 0 || args.length == 1 || (args.length == 2 && args[1] == Undefined.instance)) {
                position = target.length();
            }
            return target.substring(0, (int) position).endsWith(searchStr) ? 0 : -1;
        }
        if (methodId == 41) {
            return target.startsWith(searchStr, (int) position) ? 0 : -1;
        }
        return target.indexOf(searchStr, (int) position);
    }

    private static int js_lastIndexOf(String target, Object[] args) {
        String search = ScriptRuntime.toString(args, 0);
        double end = ScriptRuntime.toNumber(args, 1);
        if (Double.isNaN(end) || end > target.length()) {
            end = target.length();
        } else if (end < 0.0d) {
            end = 0.0d;
        }
        return target.lastIndexOf(search, (int) end);
    }

    private static CharSequence js_substring(Context cx, CharSequence target, Object[] args) {
        double end;
        int length = target.length();
        double start = ScriptRuntime.toInteger(args, 0);
        if (start < 0.0d) {
            start = 0.0d;
        } else if (start > length) {
            start = length;
        }
        if (args.length <= 1 || args[1] == Undefined.instance) {
            end = length;
        } else {
            end = ScriptRuntime.toInteger(args[1]);
            if (end < 0.0d) {
                end = 0.0d;
            } else if (end > length) {
                end = length;
            }
            if (end < start) {
                if (cx.getLanguageVersion() != 120) {
                    double temp = start;
                    start = end;
                    end = temp;
                } else {
                    end = start;
                }
            }
        }
        return target.subSequence((int) start, (int) end);
    }

    int getLength() {
        return this.string.length();
    }

    private static CharSequence js_substr(CharSequence target, Object[] args) {
        if (args.length < 1) {
            return target;
        }
        double begin = ScriptRuntime.toInteger(args[0]);
        int length = target.length();
        if (begin < 0.0d) {
            begin += length;
            if (begin < 0.0d) {
                begin = 0.0d;
            }
        } else if (begin > length) {
            begin = length;
        }
        double end = length;
        if (args.length > 1) {
            Object lengthArg = args[1];
            if (!Undefined.isUndefined(lengthArg)) {
                double end2 = ScriptRuntime.toInteger(lengthArg);
                if (end2 < 0.0d) {
                    end2 = 0.0d;
                }
                end = end2 + begin;
                if (end > length) {
                    end = length;
                }
            }
        }
        return target.subSequence((int) begin, (int) end);
    }

    private static String js_concat(String target, Object[] args) {
        int N = args.length;
        if (N == 0) {
            return target;
        }
        if (N == 1) {
            String arg = ScriptRuntime.toString(args[0]);
            return target.concat(arg);
        }
        int size = target.length();
        String[] argsAsStrings = new String[N];
        for (int i = 0; i != N; i++) {
            String s = ScriptRuntime.toString(args[i]);
            argsAsStrings[i] = s;
            size += s.length();
        }
        StringBuilder result = new StringBuilder(size);
        result.append(target);
        for (int i2 = 0; i2 != N; i2++) {
            result.append(argsAsStrings[i2]);
        }
        return result.toString();
    }

    private static CharSequence js_slice(CharSequence target, Object[] args) {
        double end;
        double begin = args.length < 1 ? 0.0d : ScriptRuntime.toInteger(args[0]);
        int length = target.length();
        if (begin < 0.0d) {
            begin += length;
            if (begin < 0.0d) {
                begin = 0.0d;
            }
        } else if (begin > length) {
            begin = length;
        }
        if (args.length < 2 || args[1] == Undefined.instance) {
            end = length;
        } else {
            end = ScriptRuntime.toInteger(args[1]);
            if (end < 0.0d) {
                end += length;
                if (end < 0.0d) {
                    end = 0.0d;
                }
            } else if (end > length) {
                end = length;
            }
            if (end < begin) {
                end = begin;
            }
        }
        return target.subSequence((int) begin, (int) end);
    }

    private static String js_repeat(Context cx, Scriptable thisObj, IdFunctionObject f, Object[] args) {
        String str = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(cx, thisObj, f));
        double cnt = ScriptRuntime.toInteger(args, 0);
        if (cnt < 0.0d || cnt == Double.POSITIVE_INFINITY) {
            throw ScriptRuntime.rangeError("Invalid count value");
        }
        if (cnt == 0.0d || str.length() == 0) {
            return "";
        }
        long size = str.length() * ((long) cnt);
        if (cnt > 2.147483647E9d || size > 2147483647L) {
            throw ScriptRuntime.rangeError("Invalid size or count value");
        }
        StringBuilder sb = new StringBuilder((int) size);
        sb.append(str);
        int i = 1;
        int icnt = (int) cnt;
        while (i <= icnt / 2) {
            sb.append((CharSequence) sb);
            i *= 2;
        }
        if (i < icnt) {
            sb.append(sb.substring(0, str.length() * (icnt - i)));
        }
        return sb.toString();
    }

    private static String js_pad(Context cx, Scriptable thisObj, IdFunctionObject f, Object[] args, boolean atStart) {
        String pad = ScriptRuntime.toString(ScriptRuntimeES6.requireObjectCoercible(cx, thisObj, f));
        long intMaxLength = ScriptRuntime.toLength(args, 0);
        if (intMaxLength <= pad.length()) {
            return pad;
        }
        String filler = " ";
        if (args.length >= 2 && !Undefined.isUndefined(args[1])) {
            filler = ScriptRuntime.toString(args[1]);
            if (filler.length() < 1) {
                return pad;
            }
        }
        int fillLen = (int) (intMaxLength - pad.length());
        StringBuilder concat = new StringBuilder();
        do {
            concat.append(filler);
        } while (concat.length() < fillLen);
        concat.setLength(fillLen);
        if (atStart) {
            return concat.append(pad).toString();
        }
        return concat.insert(0, pad).toString();
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol k) {
        if (SymbolKey.ITERATOR.equals(k)) {
            return 48;
        }
        return 0;
    }

    private static CharSequence js_raw(Context cx, Scriptable scope, Object[] args) {
        Object arg0 = args.length > 0 ? args[0] : Undefined.instance;
        Scriptable cooked = ScriptRuntime.toObject(cx, scope, arg0);
        Object rawValue = ScriptRuntime.getObjectProp(cooked, "raw", cx);
        Scriptable raw = ScriptRuntime.toObject(cx, scope, rawValue);
        long rawLength = NativeArray.getLengthProperty(cx, raw, false);
        if (rawLength > 2147483647L) {
            throw ScriptRuntime.rangeError("raw.length > " + Integer.toString(Integer.MAX_VALUE));
        }
        int literalSegments = (int) rawLength;
        if (literalSegments <= 0) {
            return "";
        }
        StringBuilder elements = new StringBuilder();
        int nextIndex = 0;
        while (true) {
            Object next = ScriptRuntime.getObjectIndex(raw, nextIndex, cx);
            String nextSeg = ScriptRuntime.toString(next);
            elements.append(nextSeg);
            nextIndex++;
            if (nextIndex != literalSegments) {
                if (args.length > nextIndex) {
                    Object next2 = args[nextIndex];
                    String nextSub = ScriptRuntime.toString(next2);
                    elements.append(nextSub);
                }
            } else {
                return elements;
            }
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String s) {
        int id;
        switch (s) {
            case "constructor":
                id = 1;
                break;
            case "toString":
                id = 2;
                break;
            case "toSource":
                id = 3;
                break;
            case "valueOf":
                id = 4;
                break;
            case "charAt":
                id = 5;
                break;
            case "charCodeAt":
                id = 6;
                break;
            case "indexOf":
                id = 7;
                break;
            case "lastIndexOf":
                id = 8;
                break;
            case "split":
                id = 9;
                break;
            case "substring":
                id = 10;
                break;
            case "toLowerCase":
                id = 11;
                break;
            case "toUpperCase":
                id = 12;
                break;
            case "substr":
                id = 13;
                break;
            case "concat":
                id = 14;
                break;
            case "slice":
                id = 15;
                break;
            case "bold":
                id = 16;
                break;
            case "italics":
                id = 17;
                break;
            case "fixed":
                id = 18;
                break;
            case "strike":
                id = 19;
                break;
            case "small":
                id = 20;
                break;
            case "big":
                id = 21;
                break;
            case "blink":
                id = 22;
                break;
            case "sup":
                id = 23;
                break;
            case "sub":
                id = 24;
                break;
            case "fontsize":
                id = 25;
                break;
            case "fontcolor":
                id = 26;
                break;
            case "link":
                id = 27;
                break;
            case "anchor":
                id = 28;
                break;
            case "equals":
                id = 29;
                break;
            case "equalsIgnoreCase":
                id = 30;
                break;
            case "match":
                id = 31;
                break;
            case "search":
                id = 32;
                break;
            case "replace":
                id = 33;
                break;
            case "localeCompare":
                id = 34;
                break;
            case "toLocaleLowerCase":
                id = 35;
                break;
            case "toLocaleUpperCase":
                id = 36;
                break;
            case "trim":
                id = 37;
                break;
            case "trimLeft":
                id = 38;
                break;
            case "trimRight":
                id = 39;
                break;
            case "includes":
                id = 40;
                break;
            case "startsWith":
                id = 41;
                break;
            case "endsWith":
                id = 42;
                break;
            case "normalize":
                id = 43;
                break;
            case "repeat":
                id = 44;
                break;
            case "codePointAt":
                id = 45;
                break;
            case "padStart":
                id = 46;
                break;
            case "padEnd":
                id = 47;
                break;
            case "trimStart":
                id = 49;
                break;
            case "trimEnd":
                id = 50;
                break;
            default:
                id = 0;
                break;
        }
        return id;
    }
}
