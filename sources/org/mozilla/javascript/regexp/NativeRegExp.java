package org.mozilla.javascript.regexp;

import me.ag2s.epublib.epub.PackageDocumentBase;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Symbol;
import org.mozilla.javascript.SymbolKey;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/regexp/NativeRegExp.class */
public class NativeRegExp extends IdScriptableObject {
    private static final long serialVersionUID = 4965263491464903264L;
    private static final Object REGEXP_TAG;
    public static final int JSREG_GLOB = 1;
    public static final int JSREG_FOLD = 2;
    public static final int JSREG_MULTILINE = 4;
    public static final int JSREG_STICKY = 8;
    public static final int TEST = 0;
    public static final int MATCH = 1;
    public static final int PREFIX = 2;
    private static final boolean debug = false;
    private static final byte REOP_SIMPLE_START = 1;
    private static final byte REOP_EMPTY = 1;
    private static final byte REOP_BOL = 2;
    private static final byte REOP_EOL = 3;
    private static final byte REOP_WBDRY = 4;
    private static final byte REOP_WNONBDRY = 5;
    private static final byte REOP_DOT = 6;
    private static final byte REOP_DIGIT = 7;
    private static final byte REOP_NONDIGIT = 8;
    private static final byte REOP_ALNUM = 9;
    private static final byte REOP_NONALNUM = 10;
    private static final byte REOP_SPACE = 11;
    private static final byte REOP_NONSPACE = 12;
    private static final byte REOP_BACKREF = 13;
    private static final byte REOP_FLAT = 14;
    private static final byte REOP_FLAT1 = 15;
    private static final byte REOP_FLATi = 16;
    private static final byte REOP_FLAT1i = 17;
    private static final byte REOP_UCFLAT1 = 18;
    private static final byte REOP_UCFLAT1i = 19;
    private static final byte REOP_CLASS = 22;
    private static final byte REOP_NCLASS = 23;
    private static final byte REOP_SIMPLE_END = 23;
    private static final byte REOP_QUANT = 25;
    private static final byte REOP_STAR = 26;
    private static final byte REOP_PLUS = 27;
    private static final byte REOP_OPT = 28;
    private static final byte REOP_LPAREN = 29;
    private static final byte REOP_RPAREN = 30;
    private static final byte REOP_ALT = 31;
    private static final byte REOP_JUMP = 32;
    private static final byte REOP_ASSERT = 41;
    private static final byte REOP_ASSERT_NOT = 42;
    private static final byte REOP_ASSERTTEST = 43;
    private static final byte REOP_ASSERTNOTTEST = 44;
    private static final byte REOP_MINIMALSTAR = 45;
    private static final byte REOP_MINIMALPLUS = 46;
    private static final byte REOP_MINIMALOPT = 47;
    private static final byte REOP_MINIMALQUANT = 48;
    private static final byte REOP_ENDCHILD = 49;
    private static final byte REOP_REPEAT = 51;
    private static final byte REOP_MINIMALREPEAT = 52;
    private static final byte REOP_ALTPREREQ = 53;
    private static final byte REOP_ALTPREREQi = 54;
    private static final byte REOP_ALTPREREQ2 = 55;
    private static final byte REOP_END = 57;
    private static final int ANCHOR_BOL = -2;
    private static final int INDEX_LEN = 2;
    private static final int Id_lastIndex = 1;
    private static final int Id_source = 2;
    private static final int Id_flags = 3;
    private static final int Id_global = 4;
    private static final int Id_ignoreCase = 5;
    private static final int Id_multiline = 6;
    private static final int Id_sticky = 7;
    private static final int MAX_INSTANCE_ID = 7;
    private static final int Id_compile = 1;
    private static final int Id_toString = 2;
    private static final int Id_toSource = 3;
    private static final int Id_exec = 4;
    private static final int Id_test = 5;
    private static final int Id_prefix = 6;
    private static final int SymbolId_match = 7;
    private static final int SymbolId_search = 8;
    private static final int MAX_PROTOTYPE_ID = 8;
    private RECompiled re;
    Object lastIndex = ScriptRuntime.zeroObj;
    private int lastIndexAttr = 6;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !NativeRegExp.class.desiredAssertionStatus();
        REGEXP_TAG = new Object();
    }

    public static void init(Context cx, Scriptable scope, boolean sealed) {
        NativeRegExp nativeRegExpWithLanguageVersion = NativeRegExpInstantiator.withLanguageVersion(cx.getLanguageVersion());
        nativeRegExpWithLanguageVersion.re = compileRE(cx, "", null, false);
        nativeRegExpWithLanguageVersion.activatePrototypeMap(8);
        nativeRegExpWithLanguageVersion.setParentScope(scope);
        nativeRegExpWithLanguageVersion.setPrototype(getObjectPrototype(scope));
        NativeRegExpCtor nativeRegExpCtor = new NativeRegExpCtor();
        nativeRegExpWithLanguageVersion.defineProperty(BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE, nativeRegExpCtor, 2);
        ScriptRuntime.setFunctionProtoAndParent(nativeRegExpCtor, scope);
        nativeRegExpCtor.setImmunePrototypeProperty(nativeRegExpWithLanguageVersion);
        if (sealed) {
            nativeRegExpWithLanguageVersion.sealObject();
            nativeRegExpCtor.sealObject();
        }
        defineProperty(scope, "RegExp", nativeRegExpCtor, 2);
    }

    NativeRegExp(Scriptable scope, RECompiled regexpCompiled) {
        this.re = regexpCompiled;
        setLastIndex(ScriptRuntime.zeroObj);
        ScriptRuntime.setBuiltinProtoAndParent(this, scope, TopLevel.Builtins.RegExp);
    }

    @Override // org.mozilla.javascript.ScriptableObject, org.mozilla.javascript.Scriptable
    public String getClassName() {
        return "RegExp";
    }

    @Override // org.mozilla.javascript.ScriptableObject
    public String getTypeOf() {
        return "object";
    }

    Scriptable compile(Context cx, Scriptable scope, Object[] args) {
        if (args.length > 0 && (args[0] instanceof NativeRegExp)) {
            if (args.length > 1 && args[1] != Undefined.instance) {
                throw ScriptRuntime.typeError0("msg.bad.regexp.compile");
            }
            NativeRegExp thatObj = (NativeRegExp) args[0];
            this.re = thatObj.re;
            setLastIndex(thatObj.lastIndex);
            return this;
        }
        String s = (args.length == 0 || (args[0] instanceof Undefined)) ? "" : escapeRegExp(args[0]);
        String global = (args.length <= 1 || args[1] == Undefined.instance) ? null : ScriptRuntime.toString(args[1]);
        this.re = compileRE(cx, s, global, false);
        setLastIndex(ScriptRuntime.zeroObj);
        return this;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append('/');
        if (this.re.source.length != 0) {
            buf.append(this.re.source);
        } else {
            buf.append("(?:)");
        }
        buf.append('/');
        appendFlags(buf);
        return buf.toString();
    }

    private void appendFlags(StringBuilder buf) {
        if ((this.re.flags & 1) != 0) {
            buf.append('g');
        }
        if ((this.re.flags & 2) != 0) {
            buf.append('i');
        }
        if ((this.re.flags & 4) != 0) {
            buf.append('m');
        }
        if ((this.re.flags & 8) != 0) {
            buf.append('y');
        }
    }

    NativeRegExp() {
    }

    private static RegExpImpl getImpl(Context cx) {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(cx);
    }

    private static String escapeRegExp(Object src) {
        String s = ScriptRuntime.toString(src);
        StringBuilder sb = null;
        int start = 0;
        int iIndexOf = s.indexOf(47);
        while (true) {
            int slash = iIndexOf;
            if (slash <= -1) {
                break;
            }
            if (slash == start || s.charAt(slash - 1) != '\\') {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append((CharSequence) s, start, slash);
                sb.append("\\/");
                start = slash + 1;
            }
            iIndexOf = s.indexOf(47, slash + 1);
        }
        if (sb != null) {
            sb.append((CharSequence) s, start, s.length());
            s = sb.toString();
        }
        return s;
    }

    Object execSub(Context cx, Scriptable scopeObj, Object[] args, int matchType) {
        String str;
        RegExpImpl reImpl = getImpl(cx);
        if (args.length == 0) {
            str = reImpl.input;
            if (str == null) {
                str = ScriptRuntime.toString(Undefined.instance);
            }
        } else {
            str = ScriptRuntime.toString(args[0]);
        }
        boolean globalOrSticky = ((this.re.flags & 1) == 0 && (this.re.flags & 8) == 0) ? false : true;
        double d = 0.0d;
        if (globalOrSticky) {
            d = ScriptRuntime.toInteger(this.lastIndex);
            if (d < 0.0d || str.length() < d) {
                setLastIndex(ScriptRuntime.zeroObj);
                return null;
            }
        }
        int[] indexp = {(int) d};
        Object rval = executeRegExp(cx, scopeObj, reImpl, str, indexp, matchType);
        if (globalOrSticky) {
            if (rval == null || rval == Undefined.instance) {
                setLastIndex(ScriptRuntime.zeroObj);
            } else {
                setLastIndex(Double.valueOf(indexp[0]));
            }
        }
        return rval;
    }

    static RECompiled compileRE(Context cx, String str, String global, boolean flat) {
        RECompiled regexp = new RECompiled(str);
        int length = str.length();
        int flags = 0;
        if (global != null) {
            for (int i = 0; i < global.length(); i++) {
                char c = global.charAt(i);
                int f = 0;
                if (c == 'g') {
                    f = 1;
                } else if (c == 'i') {
                    f = 2;
                } else if (c == 'm') {
                    f = 4;
                } else if (c == 'y') {
                    f = 8;
                } else {
                    reportError("msg.invalid.re.flag", String.valueOf(c));
                }
                if ((flags & f) != 0) {
                    reportError("msg.invalid.re.flag", String.valueOf(c));
                }
                flags |= f;
            }
        }
        regexp.flags = flags;
        CompilerState state = new CompilerState(cx, regexp.source, length, flags);
        if (flat && length > 0) {
            state.result = new RENode((byte) 14);
            state.result.chr = state.cpbegin[0];
            state.result.length = length;
            state.result.flatIndex = 0;
            state.progLength += 5;
        } else {
            if (!parseDisjunction(state)) {
                return null;
            }
            if (state.maxBackReference > state.parenCount) {
                state = new CompilerState(cx, regexp.source, length, flags);
                state.backReferenceLimit = state.parenCount;
                if (!parseDisjunction(state)) {
                    return null;
                }
            }
        }
        regexp.program = new byte[state.progLength + 1];
        if (state.classCount != 0) {
            regexp.classList = new RECharSet[state.classCount];
            regexp.classCount = state.classCount;
        }
        int endPC = emitREBytecode(state, regexp, 0, state.result);
        int i2 = endPC + 1;
        regexp.program[endPC] = 57;
        regexp.parenCount = state.parenCount;
        switch (regexp.program[0]) {
            case 2:
                regexp.anchorCh = -2;
                break;
            case 14:
            case 16:
                int k = getIndex(regexp.program, 1);
                regexp.anchorCh = regexp.source[k];
                break;
            case 15:
            case 17:
                regexp.anchorCh = (char) (regexp.program[1] & 255);
                break;
            case 18:
            case 19:
                regexp.anchorCh = (char) getIndex(regexp.program, 1);
                break;
            case 31:
                RENode n = state.result;
                if (n.kid.op == 2 && n.kid2.op == 2) {
                    regexp.anchorCh = -2;
                    break;
                }
                break;
        }
        return regexp;
    }

    static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static boolean isWord(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || isDigit(c) || c == '_';
    }

    private static boolean isControlLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    private static boolean isLineTerm(char c) {
        return ScriptRuntime.isJSLineTerminator(c);
    }

    private static boolean isREWhiteSpace(int c) {
        return ScriptRuntime.isJSWhitespaceOrLineTerminator(c);
    }

    private static char upcase(char ch2) {
        if (ch2 < 128) {
            if ('a' <= ch2 && ch2 <= 'z') {
                return (char) (ch2 - ' ');
            }
            return ch2;
        }
        char cu = Character.toUpperCase(ch2);
        return cu < 128 ? ch2 : cu;
    }

    private static char downcase(char ch2) {
        if (ch2 < 128) {
            if ('A' <= ch2 && ch2 <= 'Z') {
                return (char) (ch2 + ' ');
            }
            return ch2;
        }
        char cl = Character.toLowerCase(ch2);
        return cl < 128 ? ch2 : cl;
    }

    private static int toASCIIHexDigit(int c) {
        if (c < 48) {
            return -1;
        }
        if (c <= 57) {
            return c - 48;
        }
        int c2 = c | 32;
        if (97 <= c2 && c2 <= 102) {
            return (c2 - 97) + 10;
        }
        return -1;
    }

    private static boolean parseDisjunction(CompilerState state) {
        if (!parseAlternative(state)) {
            return false;
        }
        char[] source = state.cpbegin;
        int index = state.cp;
        if (index != source.length && source[index] == '|') {
            state.cp++;
            RENode result = new RENode((byte) 31);
            result.kid = state.result;
            if (!parseDisjunction(state)) {
                return false;
            }
            result.kid2 = state.result;
            state.result = result;
            if (result.kid.op == 14 && result.kid2.op == 14) {
                result.op = (state.flags & 2) == 0 ? (byte) 53 : (byte) 54;
                result.chr = result.kid.chr;
                result.index = result.kid2.chr;
                state.progLength += 13;
                return true;
            }
            if (result.kid.op == 22 && result.kid.index < 256 && result.kid2.op == 14 && (state.flags & 2) == 0) {
                result.op = (byte) 55;
                result.chr = result.kid2.chr;
                result.index = result.kid.index;
                state.progLength += 13;
                return true;
            }
            if (result.kid.op == 14 && result.kid2.op == 22 && result.kid2.index < 256 && (state.flags & 2) == 0) {
                result.op = (byte) 55;
                result.chr = result.kid.chr;
                result.index = result.kid2.index;
                state.progLength += 13;
                return true;
            }
            state.progLength += 9;
            return true;
        }
        return true;
    }

    private static boolean parseAlternative(CompilerState state) {
        RENode headTerm = null;
        RENode tailTerm = null;
        char[] source = state.cpbegin;
        while (state.cp != state.cpend && source[state.cp] != '|' && (state.parenNesting == 0 || source[state.cp] != ')')) {
            if (!parseTerm(state)) {
                return false;
            }
            if (headTerm == null) {
                headTerm = state.result;
                tailTerm = headTerm;
            } else {
                tailTerm.next = state.result;
            }
            while (tailTerm.next != null) {
                tailTerm = tailTerm.next;
            }
        }
        if (headTerm == null) {
            state.result = new RENode((byte) 1);
            return true;
        }
        state.result = headTerm;
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:31:0x01eb
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:225)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:195)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:62)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:124)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static boolean calculateBitmapSize(org.mozilla.javascript.regexp.CompilerState r4, org.mozilla.javascript.regexp.RENode r5, char[] r6, int r7, int r8) {
        /*
            Method dump skipped, instructions count: 816
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.calculateBitmapSize(org.mozilla.javascript.regexp.CompilerState, org.mozilla.javascript.regexp.RENode, char[], int, int):boolean");
    }

    private static void doFlat(CompilerState state, char c) {
        state.result = new RENode((byte) 14);
        state.result.chr = c;
        state.result.length = 1;
        state.result.flatIndex = -1;
        state.progLength += 3;
    }

    private static int getDecimalValue(char c, CompilerState state, int maxValue, String overflowMessageId) {
        boolean overflow = false;
        int start = state.cp;
        char[] src = state.cpbegin;
        int value = c - 48;
        while (state.cp != state.cpend) {
            char c2 = src[state.cp];
            if (!isDigit(c2)) {
                break;
            }
            if (!overflow) {
                int v = (value * 10) + (c2 - '0');
                if (v < maxValue) {
                    value = v;
                } else {
                    overflow = true;
                    value = maxValue;
                }
            }
            state.cp++;
        }
        if (overflow) {
            reportError(overflowMessageId, String.valueOf(src, start, state.cp - start));
        }
        return value;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:64:0x03d5
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:225)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:195)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:62)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:95)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static boolean parseTerm(org.mozilla.javascript.regexp.CompilerState r9) {
        /*
            Method dump skipped, instructions count: 2381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.parseTerm(org.mozilla.javascript.regexp.CompilerState):boolean");
    }

    private static void resolveForwardJump(byte[] array, int from, int pc) {
        if (from > pc) {
            throw Kit.codeBug();
        }
        addIndex(array, from, pc - from);
    }

    private static int getOffset(byte[] array, int pc) {
        return getIndex(array, pc);
    }

    private static int addIndex(byte[] array, int pc, int index) {
        if (index < 0) {
            throw Kit.codeBug();
        }
        if (index > 65535) {
            throw Context.reportRuntimeError("Too complex regexp");
        }
        array[pc] = (byte) (index >> 8);
        array[pc + 1] = (byte) index;
        return pc + 2;
    }

    private static int getIndex(byte[] array, int pc) {
        return ((array[pc] & 255) << 8) | (array[pc + 1] & 255);
    }

    private static int emitREBytecode(CompilerState state, RECompiled re, int pc, RENode t) {
        byte[] program = re.program;
        while (t != null) {
            int i = pc;
            pc++;
            program[i] = t.op;
            switch (t.op) {
                case 1:
                    pc--;
                    continue;
                    t = t.next;
                case 13:
                    pc = addIndex(program, pc, t.parenIndex);
                    continue;
                    t = t.next;
                case 14:
                    if (t.flatIndex != -1) {
                        while (t.next != null && t.next.op == 14 && t.flatIndex + t.length == t.next.flatIndex) {
                            t.length += t.next.length;
                            t.next = t.next.next;
                        }
                    }
                    if (t.flatIndex != -1 && t.length > 1) {
                        if ((state.flags & 2) != 0) {
                            program[pc - 1] = 16;
                        } else {
                            program[pc - 1] = 14;
                        }
                        pc = addIndex(program, addIndex(program, pc, t.flatIndex), t.length);
                        continue;
                    } else if (t.chr < 256) {
                        if ((state.flags & 2) != 0) {
                            program[pc - 1] = 17;
                        } else {
                            program[pc - 1] = 15;
                        }
                        pc++;
                        program[pc] = (byte) t.chr;
                    } else {
                        if ((state.flags & 2) != 0) {
                            program[pc - 1] = 19;
                        } else {
                            program[pc - 1] = 18;
                        }
                        pc = addIndex(program, pc, t.chr);
                    }
                    t = t.next;
                    break;
                case 22:
                    if (!t.sense) {
                        program[pc - 1] = 23;
                    }
                    pc = addIndex(program, pc, t.index);
                    re.classList[t.index] = new RECharSet(t.bmsize, t.startIndex, t.kidlen, t.sense);
                    continue;
                    t = t.next;
                case 25:
                    if (t.min == 0 && t.max == -1) {
                        program[pc - 1] = t.greedy ? (byte) 26 : (byte) 45;
                    } else if (t.min == 0 && t.max == 1) {
                        program[pc - 1] = t.greedy ? (byte) 28 : (byte) 47;
                    } else if (t.min == 1 && t.max == -1) {
                        program[pc - 1] = t.greedy ? (byte) 27 : (byte) 46;
                    } else {
                        if (!t.greedy) {
                            program[pc - 1] = 48;
                        }
                        pc = addIndex(program, addIndex(program, pc, t.min), t.max + 1);
                    }
                    int pc2 = addIndex(program, addIndex(program, pc, t.parenCount), t.parenIndex);
                    int pc3 = emitREBytecode(state, re, pc2 + 2, t.kid);
                    pc = pc3 + 1;
                    program[pc3] = 49;
                    resolveForwardJump(program, pc2, pc);
                    continue;
                    t = t.next;
                    break;
                case 29:
                    int pc4 = emitREBytecode(state, re, addIndex(program, pc, t.parenIndex), t.kid);
                    program[pc4] = 30;
                    pc = addIndex(program, pc4 + 1, t.parenIndex);
                    continue;
                    t = t.next;
                case 31:
                    break;
                case 41:
                    int pc5 = emitREBytecode(state, re, pc + 2, t.kid);
                    pc = pc5 + 1;
                    program[pc5] = 43;
                    resolveForwardJump(program, pc, pc);
                    continue;
                    t = t.next;
                case 42:
                    int pc6 = emitREBytecode(state, re, pc + 2, t.kid);
                    pc = pc6 + 1;
                    program[pc6] = 44;
                    resolveForwardJump(program, pc, pc);
                    continue;
                    t = t.next;
                case 53:
                case 54:
                case 55:
                    boolean ignoreCase = t.op == 54;
                    addIndex(program, pc, ignoreCase ? upcase(t.chr) : t.chr);
                    int pc7 = pc + 2;
                    addIndex(program, pc7, ignoreCase ? upcase((char) t.index) : t.index);
                    pc = pc7 + 2;
                    break;
                default:
                    t = t.next;
            }
            RENode nextAlt = t.kid2;
            int nextAltFixup = pc;
            int pc8 = emitREBytecode(state, re, pc + 2, t.kid);
            int pc9 = pc8 + 1;
            program[pc8] = 32;
            int pc10 = pc9 + 2;
            resolveForwardJump(program, nextAltFixup, pc10);
            int pc11 = emitREBytecode(state, re, pc10, nextAlt);
            int pc12 = pc11 + 1;
            program[pc11] = 32;
            pc = pc12 + 2;
            resolveForwardJump(program, pc9, pc);
            resolveForwardJump(program, pc12, pc);
            t = t.next;
        }
        return pc;
    }

    private static void pushProgState(REGlobalData gData, int min, int max, int cp, REBackTrackData backTrackLastToSave, int continuationOp, int continuationPc) {
        gData.stateStackTop = new REProgState(gData.stateStackTop, min, max, cp, backTrackLastToSave, continuationOp, continuationPc);
    }

    private static REProgState popProgState(REGlobalData gData) {
        REProgState state = gData.stateStackTop;
        gData.stateStackTop = state.previous;
        return state;
    }

    private static void pushBackTrackState(REGlobalData gData, byte op, int pc) {
        REProgState state = gData.stateStackTop;
        gData.backTrackStackTop = new REBackTrackData(gData, op, pc, gData.cp, state.continuationOp, state.continuationPc);
    }

    private static void pushBackTrackState(REGlobalData gData, byte op, int pc, int cp, int continuationOp, int continuationPc) {
        gData.backTrackStackTop = new REBackTrackData(gData, op, pc, cp, continuationOp, continuationPc);
    }

    private static boolean flatNMatcher(REGlobalData gData, int matchChars, int length, String input, int end) {
        if (gData.cp + length > end) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (gData.regexp.source[matchChars + i] != input.charAt(gData.cp + i)) {
                return false;
            }
        }
        gData.cp += length;
        return true;
    }

    private static boolean flatNIMatcher(REGlobalData gData, int matchChars, int length, String input, int end) {
        if (gData.cp + length > end) {
            return false;
        }
        char[] source = gData.regexp.source;
        for (int i = 0; i < length; i++) {
            char c1 = source[matchChars + i];
            char c2 = input.charAt(gData.cp + i);
            if (c1 != c2 && upcase(c1) != upcase(c2)) {
                return false;
            }
        }
        gData.cp += length;
        return true;
    }

    private static boolean backrefMatcher(REGlobalData gData, int parenIndex, String input, int end) {
        if (gData.parens == null || parenIndex >= gData.parens.length) {
            return false;
        }
        int parenContent = gData.parensIndex(parenIndex);
        if (parenContent == -1) {
            return true;
        }
        int len = gData.parensLength(parenIndex);
        if (gData.cp + len > end) {
            return false;
        }
        if ((gData.regexp.flags & 2) != 0) {
            for (int i = 0; i < len; i++) {
                char c1 = input.charAt(parenContent + i);
                char c2 = input.charAt(gData.cp + i);
                if (c1 != c2 && upcase(c1) != upcase(c2)) {
                    return false;
                }
            }
        } else if (!input.regionMatches(parenContent, input, gData.cp, len)) {
            return false;
        }
        gData.cp += len;
        return true;
    }

    private static void addCharacterToCharSet(RECharSet cs, char c) {
        int byteIndex = c / '\b';
        if (c >= cs.length) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        byte[] bArr = cs.bits;
        bArr[byteIndex] = (byte) (bArr[byteIndex] | (1 << (c & 7)));
    }

    private static void addCharacterRangeToCharSet(RECharSet cs, char c1, char c2) {
        int byteIndex1 = c1 / '\b';
        int byteIndex2 = c2 / '\b';
        if (c2 >= cs.length || c1 > c2) {
            throw ScriptRuntime.constructError("SyntaxError", "invalid range in character class");
        }
        char c12 = (char) (c1 & 7);
        char c22 = (char) (c2 & 7);
        if (byteIndex1 == byteIndex2) {
            byte[] bArr = cs.bits;
            bArr[byteIndex1] = (byte) (bArr[byteIndex1] | ((255 >> (7 - (c22 - c12))) << c12));
            return;
        }
        byte[] bArr2 = cs.bits;
        bArr2[byteIndex1] = (byte) (bArr2[byteIndex1] | (255 << c12));
        for (int i = byteIndex1 + 1; i < byteIndex2; i++) {
            cs.bits[i] = -1;
        }
        byte[] bArr3 = cs.bits;
        bArr3[byteIndex2] = (byte) (bArr3[byteIndex2] | (255 >> (7 - c22)));
    }

    private static void processCharSet(REGlobalData gData, RECharSet charSet) {
        synchronized (charSet) {
            if (!charSet.converted) {
                processCharSetImpl(gData, charSet);
                charSet.converted = true;
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:41:0x023a
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:225)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:195)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:62)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.processFallThroughCases(SwitchRegionMaker.java:105)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:64)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:124)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:101)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0488 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x040e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData r5, org.mozilla.javascript.regexp.RECharSet r6) {
        /*
            Method dump skipped, instructions count: 1234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.processCharSetImpl(org.mozilla.javascript.regexp.REGlobalData, org.mozilla.javascript.regexp.RECharSet):void");
    }

    private static boolean classMatcher(REGlobalData gData, RECharSet charSet, char ch2) {
        if (!charSet.converted) {
            processCharSet(gData, charSet);
        }
        int byteIndex = ch2 >> 3;
        return (charSet.length == 0 || ch2 >= charSet.length || (charSet.bits[byteIndex] & (1 << (ch2 & 7))) == 0) ^ charSet.sense;
    }

    private static boolean reopIsSimple(int op) {
        return op >= 1 && op <= 23;
    }

    private static int simpleMatch(REGlobalData gData, String input, int op, byte[] program, int pc, int end, boolean updatecp) {
        char c;
        char c2;
        boolean result = false;
        int startcp = gData.cp;
        switch (op) {
            case 1:
                result = true;
                break;
            case 2:
                if (gData.cp == 0 || (gData.multiline && isLineTerm(input.charAt(gData.cp - 1)))) {
                    result = true;
                    break;
                }
                break;
            case 3:
                if (gData.cp == end || (gData.multiline && isLineTerm(input.charAt(gData.cp)))) {
                    result = true;
                    break;
                }
                break;
            case 4:
                result = (gData.cp == 0 || !isWord(input.charAt(gData.cp - 1))) ^ (gData.cp >= end || !isWord(input.charAt(gData.cp)));
                break;
            case 5:
                result = (gData.cp == 0 || !isWord(input.charAt(gData.cp - 1))) ^ (gData.cp < end && isWord(input.charAt(gData.cp)));
                break;
            case 6:
                if (gData.cp != end && !isLineTerm(input.charAt(gData.cp))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 7:
                if (gData.cp != end && isDigit(input.charAt(gData.cp))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 8:
                if (gData.cp != end && !isDigit(input.charAt(gData.cp))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 9:
                if (gData.cp != end && isWord(input.charAt(gData.cp))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 10:
                if (gData.cp != end && !isWord(input.charAt(gData.cp))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 11:
                if (gData.cp != end && isREWhiteSpace(input.charAt(gData.cp))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 12:
                if (gData.cp != end && !isREWhiteSpace(input.charAt(gData.cp))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 13:
                int parenIndex = getIndex(program, pc);
                pc += 2;
                result = backrefMatcher(gData, parenIndex, input, end);
                break;
            case 14:
                int offset = getIndex(program, pc);
                int pc2 = pc + 2;
                int length = getIndex(program, pc2);
                pc = pc2 + 2;
                result = flatNMatcher(gData, offset, length, input, end);
                break;
            case 15:
                pc++;
                char matchCh = (char) (program[pc] & 255);
                if (gData.cp != end && input.charAt(gData.cp) == matchCh) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 16:
                int offset2 = getIndex(program, pc);
                int pc3 = pc + 2;
                int length2 = getIndex(program, pc3);
                pc = pc3 + 2;
                result = flatNIMatcher(gData, offset2, length2, input, end);
                break;
            case 17:
                pc++;
                char matchCh2 = (char) (program[pc] & 255);
                if (gData.cp != end && (matchCh2 == (c2 = input.charAt(gData.cp)) || upcase(matchCh2) == upcase(c2))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 18:
                char matchCh3 = (char) getIndex(program, pc);
                pc += 2;
                if (gData.cp != end && input.charAt(gData.cp) == matchCh3) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 19:
                char matchCh4 = (char) getIndex(program, pc);
                pc += 2;
                if (gData.cp != end && (matchCh4 == (c = input.charAt(gData.cp)) || upcase(matchCh4) == upcase(c))) {
                    result = true;
                    gData.cp++;
                    break;
                }
                break;
            case 20:
            case 21:
            default:
                throw Kit.codeBug();
            case 22:
            case 23:
                int index = getIndex(program, pc);
                pc += 2;
                if (gData.cp != end && classMatcher(gData, gData.regexp.classList[index], input.charAt(gData.cp))) {
                    gData.cp++;
                    result = true;
                    break;
                }
                break;
        }
        if (result) {
            if (!updatecp) {
                gData.cp = startcp;
            }
            return pc;
        }
        gData.cp = startcp;
        return -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:220:0x020f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0469 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x044c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean executeREBytecode(org.mozilla.javascript.regexp.REGlobalData r8, java.lang.String r9, int r10) {
        /*
            Method dump skipped, instructions count: 2067
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExp.executeREBytecode(org.mozilla.javascript.regexp.REGlobalData, java.lang.String, int):boolean");
    }

    private static boolean matchRegExp(REGlobalData gData, RECompiled re, String input, int start, int end, boolean multiline) {
        if (re.parenCount != 0) {
            gData.parens = new long[re.parenCount];
        } else {
            gData.parens = null;
        }
        gData.backTrackStackTop = null;
        gData.stateStackTop = null;
        gData.multiline = multiline || (re.flags & 4) != 0;
        gData.regexp = re;
        int anchorCh = gData.regexp.anchorCh;
        int i = start;
        while (i <= end) {
            if (anchorCh >= 0) {
                while (i != end) {
                    char matchCh = input.charAt(i);
                    if (matchCh != anchorCh && ((gData.regexp.flags & 2) == 0 || upcase(matchCh) != upcase((char) anchorCh))) {
                        if ((gData.regexp.flags & 8) != 0) {
                            return false;
                        }
                        i++;
                    }
                }
                return false;
            }
            gData.cp = i;
            gData.skipped = i - start;
            for (int j = 0; j < re.parenCount; j++) {
                gData.parens[j] = -1;
            }
            boolean result = executeREBytecode(gData, input, end);
            gData.backTrackStackTop = null;
            gData.stateStackTop = null;
            if (result) {
                return true;
            }
            if (anchorCh == -2 && !gData.multiline) {
                gData.skipped = end;
                return false;
            }
            if ((gData.regexp.flags & 8) != 0) {
                return false;
            }
            int i2 = start + gData.skipped;
            i = i2 + 1;
        }
        return false;
    }

    Object executeRegExp(Context cx, Scriptable scope, RegExpImpl res, String str, int[] indexp, int matchType) {
        Object result;
        Scriptable obj;
        REGlobalData gData = new REGlobalData();
        int start = indexp[0];
        int end = str.length();
        if (start > end) {
            start = end;
        }
        boolean matches = matchRegExp(gData, this.re, str, start, end, res.multiline);
        if (!matches) {
            if (matchType != 2) {
                return null;
            }
            return Undefined.instance;
        }
        int index = gData.cp;
        indexp[0] = index;
        int matchlen = index - (start + gData.skipped);
        int index2 = index - matchlen;
        if (matchType == 0) {
            result = Boolean.TRUE;
            obj = null;
        } else {
            result = cx.newArray(scope, 0);
            obj = (Scriptable) result;
            String matchstr = str.substring(index2, index2 + matchlen);
            obj.put(0, obj, matchstr);
        }
        if (this.re.parenCount == 0) {
            res.parens = null;
            res.lastParen = new SubString();
        } else {
            SubString parsub = null;
            res.parens = new SubString[this.re.parenCount];
            for (int num = 0; num < this.re.parenCount; num++) {
                int cap_index = gData.parensIndex(num);
                if (cap_index != -1) {
                    int cap_length = gData.parensLength(num);
                    parsub = new SubString(str, cap_index, cap_length);
                    res.parens[num] = parsub;
                    if (matchType != 0) {
                        obj.put(num + 1, obj, parsub.toString());
                    }
                } else if (matchType != 0) {
                    obj.put(num + 1, obj, Undefined.instance);
                }
            }
            res.lastParen = parsub;
        }
        if (matchType != 0) {
            obj.put("index", obj, Integer.valueOf(start + gData.skipped));
            obj.put("input", obj, str);
        }
        if (res.lastMatch == null) {
            res.lastMatch = new SubString();
            res.leftContext = new SubString();
            res.rightContext = new SubString();
        }
        res.lastMatch.str = str;
        res.lastMatch.index = index2;
        res.lastMatch.length = matchlen;
        res.leftContext.str = str;
        if (cx.getLanguageVersion() == 120) {
            res.leftContext.index = start;
            res.leftContext.length = gData.skipped;
        } else {
            res.leftContext.index = 0;
            res.leftContext.length = start + gData.skipped;
        }
        res.rightContext.str = str;
        res.rightContext.index = index;
        res.rightContext.length = end - index;
        return result;
    }

    int getFlags() {
        return this.re.flags;
    }

    private static void reportWarning(Context cx, String messageId, String arg) {
        if (cx.hasFeature(11)) {
            String msg = ScriptRuntime.getMessage1(messageId, arg);
            Context.reportWarning(msg);
        }
    }

    private static void reportError(String messageId, String arg) {
        String msg = ScriptRuntime.getMessage1(messageId, arg);
        throw ScriptRuntime.constructError("SyntaxError", msg);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int getMaxInstanceId() {
        return 7;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findInstanceIdInfo(String s) {
        int id;
        int attr;
        switch (s) {
            case "lastIndex":
                id = 1;
                break;
            case "source":
                id = 2;
                break;
            case "flags":
                id = 3;
                break;
            case "global":
                id = 4;
                break;
            case "ignoreCase":
                id = 5;
                break;
            case "multiline":
                id = 6;
                break;
            case "sticky":
                id = 7;
                break;
            default:
                id = 0;
                break;
        }
        if (id == 0) {
            return super.findInstanceIdInfo(s);
        }
        switch (id) {
            case 1:
                attr = this.lastIndexAttr;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                attr = 7;
                break;
            default:
                throw new IllegalStateException();
        }
        return instanceIdInfo(attr, id);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected String getInstanceIdName(int id) {
        switch (id) {
            case 1:
                return "lastIndex";
            case 2:
                return PackageDocumentBase.DCTags.source;
            case 3:
                return "flags";
            case 4:
                return "global";
            case 5:
                return "ignoreCase";
            case 6:
                return "multiline";
            case 7:
                return "sticky";
            default:
                return super.getInstanceIdName(id);
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected Object getInstanceIdValue(int id) {
        switch (id) {
            case 1:
                return this.lastIndex;
            case 2:
                return new String(this.re.source);
            case 3:
                StringBuilder buf = new StringBuilder();
                appendFlags(buf);
                return buf.toString();
            case 4:
                return ScriptRuntime.wrapBoolean((this.re.flags & 1) != 0);
            case 5:
                return ScriptRuntime.wrapBoolean((this.re.flags & 2) != 0);
            case 6:
                return ScriptRuntime.wrapBoolean((this.re.flags & 4) != 0);
            case 7:
                return ScriptRuntime.wrapBoolean((this.re.flags & 8) != 0);
            default:
                return super.getInstanceIdValue(id);
        }
    }

    private void setLastIndex(Object value) {
        if ((this.lastIndexAttr & 1) != 0) {
            throw ScriptRuntime.typeError1("msg.modify.readonly", "lastIndex");
        }
        this.lastIndex = value;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdValue(int id, Object value) {
        switch (id) {
            case 1:
                setLastIndex(value);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                break;
            default:
                super.setInstanceIdValue(id, value);
                break;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void setInstanceIdAttributes(int id, int attr) {
        switch (id) {
            case 1:
                this.lastIndexAttr = attr;
                break;
            default:
                super.setInstanceIdAttributes(id, attr);
                break;
        }
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected void initPrototypeId(int id) {
        int arity;
        String s;
        if (id == 7) {
            initPrototypeMethod(REGEXP_TAG, id, SymbolKey.MATCH, "[Symbol.match]", 1);
            return;
        }
        if (id == 8) {
            initPrototypeMethod(REGEXP_TAG, id, SymbolKey.SEARCH, "[Symbol.search]", 1);
            return;
        }
        switch (id) {
            case 1:
                arity = 2;
                s = "compile";
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
                arity = 1;
                s = "exec";
                break;
            case 5:
                arity = 1;
                s = "test";
                break;
            case 6:
                arity = 1;
                s = "prefix";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(REGEXP_TAG, id, s, arity);
    }

    @Override // org.mozilla.javascript.IdScriptableObject, org.mozilla.javascript.IdFunctionCall
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (!f.hasTag(REGEXP_TAG)) {
            return super.execIdCall(f, cx, scope, thisObj, args);
        }
        int id = f.methodId();
        switch (id) {
            case 1:
                return realThis(thisObj, f).compile(cx, scope, args);
            case 2:
            case 3:
                return realThis(thisObj, f).toString();
            case 4:
            case 7:
                return realThis(thisObj, f).execSub(cx, scope, args, 1);
            case 5:
                Object x = realThis(thisObj, f).execSub(cx, scope, args, 0);
                return Boolean.TRUE.equals(x) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return realThis(thisObj, f).execSub(cx, scope, args, 2);
            case 8:
                Scriptable scriptable = (Scriptable) realThis(thisObj, f).execSub(cx, scope, args, 1);
                if (scriptable == null) {
                    return -1;
                }
                return scriptable.get("index", scriptable);
            default:
                throw new IllegalArgumentException(String.valueOf(id));
        }
    }

    private static NativeRegExp realThis(Scriptable thisObj, IdFunctionObject f) {
        return (NativeRegExp) ensureType(thisObj, NativeRegExp.class, f);
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(Symbol k) {
        if (SymbolKey.MATCH.equals(k)) {
            return 7;
        }
        if (SymbolKey.SEARCH.equals(k)) {
            return 8;
        }
        return 0;
    }

    @Override // org.mozilla.javascript.IdScriptableObject
    protected int findPrototypeId(String s) {
        int id;
        switch (s) {
            case "compile":
                id = 1;
                break;
            case "toString":
                id = 2;
                break;
            case "toSource":
                id = 3;
                break;
            case "exec":
                id = 4;
                break;
            case "test":
                id = 5;
                break;
            case "prefix":
                id = 6;
                break;
            default:
                id = 0;
                break;
        }
        return id;
    }
}
