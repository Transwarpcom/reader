package org.mozilla.javascript;

import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.mozilla.javascript.ES6Generator;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebugFrame;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/Interpreter.class */
public final class Interpreter extends Icode implements Evaluator {
    InterpreterData itsData;
    static final int EXCEPTION_TRY_START_SLOT = 0;
    static final int EXCEPTION_TRY_END_SLOT = 1;
    static final int EXCEPTION_HANDLER_SLOT = 2;
    static final int EXCEPTION_TYPE_SLOT = 3;
    static final int EXCEPTION_LOCAL_SLOT = 4;
    static final int EXCEPTION_SCOPE_SLOT = 5;
    static final int EXCEPTION_SLOT_SIZE = 6;

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/Interpreter$CallFrame.class */
    private static class CallFrame implements Cloneable, Serializable {
        private static final long serialVersionUID = -2843792508994958978L;
        CallFrame parentFrame;
        int frameIndex;
        boolean frozen;
        final InterpretedFunction fnOrScript;
        final InterpreterData idata;
        Object[] stack;
        int[] stackAttributes;
        double[] sDbl;
        final CallFrame varSource;
        final int localShift;
        final int emptyStackTop;
        final DebugFrame debuggerFrame;
        final boolean useActivation;
        boolean isContinuationsTopFrame;
        final Scriptable thisObj;
        Object result;
        double resultDbl;
        int pc;
        int pcPrevBranch;
        int pcSourceLineStart;
        Scriptable scope;
        int savedStackTop;
        int savedCallOp;
        Object throwable;

        CallFrame(Context cx, Scriptable thisObj, InterpretedFunction fnOrScript, CallFrame parentFrame) {
            this.idata = fnOrScript.idata;
            this.debuggerFrame = cx.debugger != null ? cx.debugger.getFrame(cx, this.idata) : null;
            this.useActivation = this.debuggerFrame != null || this.idata.itsNeedsActivation;
            this.emptyStackTop = (this.idata.itsMaxVars + this.idata.itsMaxLocals) - 1;
            this.fnOrScript = fnOrScript;
            this.varSource = this;
            this.localShift = this.idata.itsMaxVars;
            this.thisObj = thisObj;
            this.parentFrame = parentFrame;
            this.frameIndex = parentFrame == null ? 0 : parentFrame.frameIndex + 1;
            if (this.frameIndex > cx.getMaximumInterpreterStackDepth()) {
                throw Context.reportRuntimeError("Exceeded maximum stack depth");
            }
            this.result = Undefined.instance;
            this.pcSourceLineStart = this.idata.firstLinePC;
            this.savedStackTop = this.emptyStackTop;
        }

        void initializeArgs(Context cx, Scriptable callerScope, Object[] args, double[] argsDbl, int argShift, int argCount) throws RuntimeException {
            if (this.useActivation) {
                if (argsDbl != null) {
                    args = Interpreter.getArgsArray(args, argsDbl, argShift, argCount);
                }
                argShift = 0;
                argsDbl = null;
            }
            if (this.idata.itsFunctionType != 0) {
                this.scope = this.fnOrScript.getParentScope();
                if (this.useActivation) {
                    if (this.idata.itsFunctionType == 4) {
                        this.scope = ScriptRuntime.createArrowFunctionActivation(this.fnOrScript, this.scope, args, this.idata.isStrict);
                    } else {
                        this.scope = ScriptRuntime.createFunctionActivation(this.fnOrScript, this.scope, args, this.idata.isStrict);
                    }
                }
            } else {
                this.scope = callerScope;
                ScriptRuntime.initScript(this.fnOrScript, this.thisObj, cx, this.scope, this.fnOrScript.idata.evalScriptFlag);
            }
            if (this.idata.itsNestedFunctions != null) {
                if (this.idata.itsFunctionType != 0 && !this.idata.itsNeedsActivation) {
                    Kit.codeBug();
                }
                for (int i = 0; i < this.idata.itsNestedFunctions.length; i++) {
                    InterpreterData fdata = this.idata.itsNestedFunctions[i];
                    if (fdata.itsFunctionType == 1) {
                        Interpreter.initFunction(cx, this.scope, this.fnOrScript, i);
                    }
                }
            }
            int maxFrameArray = this.idata.itsMaxFrameArray;
            if (maxFrameArray != this.emptyStackTop + this.idata.itsMaxStack + 1) {
                Kit.codeBug();
            }
            this.stack = new Object[maxFrameArray];
            this.stackAttributes = new int[maxFrameArray];
            this.sDbl = new double[maxFrameArray];
            int varCount = this.idata.getParamAndVarCount();
            for (int i2 = 0; i2 < varCount; i2++) {
                if (this.idata.getParamOrVarConst(i2)) {
                    this.stackAttributes[i2] = 13;
                }
            }
            int definedArgs = this.idata.argCount;
            if (definedArgs > argCount) {
                definedArgs = argCount;
            }
            System.arraycopy(args, argShift, this.stack, 0, definedArgs);
            if (argsDbl != null) {
                System.arraycopy(argsDbl, argShift, this.sDbl, 0, definedArgs);
            }
            for (int i3 = definedArgs; i3 != this.idata.itsMaxVars; i3++) {
                this.stack[i3] = Undefined.instance;
            }
        }

        CallFrame cloneFrozen() throws RuntimeException {
            if (!this.frozen) {
                Kit.codeBug();
            }
            try {
                CallFrame copy = (CallFrame) clone();
                copy.stack = (Object[]) this.stack.clone();
                copy.stackAttributes = (int[]) this.stackAttributes.clone();
                copy.sDbl = (double[]) this.sDbl.clone();
                copy.frozen = false;
                return copy;
            } catch (CloneNotSupportedException e) {
                throw new IllegalStateException();
            }
        }

        public boolean equals(Object other) {
            if (other instanceof CallFrame) {
                Context cx = Context.enter();
                try {
                    if (ScriptRuntime.hasTopCall(cx)) {
                        boolean zBooleanValue = equalsInTopScope(other).booleanValue();
                        Context.exit();
                        return zBooleanValue;
                    }
                    Scriptable top = ScriptableObject.getTopLevelScope(this.scope);
                    boolean zBooleanValue2 = ((Boolean) ScriptRuntime.doTopCall((c, scope, thisObj, args) -> {
                        return equalsInTopScope(other);
                    }, cx, top, top, ScriptRuntime.emptyArgs, isStrictTopFrame())).booleanValue();
                    Context.exit();
                    return zBooleanValue2;
                } catch (Throwable th) {
                    Context.exit();
                    throw th;
                }
            }
            return false;
        }

        public int hashCode() {
            int i;
            int depth = 0;
            CallFrame f = this;
            int h = 0;
            do {
                h = (31 * ((31 * h) + f.pc)) + f.idata.icodeHashCode();
                f = f.parentFrame;
                if (f == null) {
                    break;
                }
                i = depth;
                depth++;
            } while (i < 8);
            return h;
        }

        private Boolean equalsInTopScope(Object other) {
            return (Boolean) EqualObjectGraphs.withThreadLocal(eq -> {
                return equals(this, (CallFrame) other, eq);
            });
        }

        private boolean isStrictTopFrame() {
            CallFrame callFrame = this;
            while (true) {
                CallFrame f = callFrame;
                CallFrame p = f.parentFrame;
                if (p == null) {
                    return f.idata.isStrict;
                }
                callFrame = p;
            }
        }

        private static Boolean equals(CallFrame f1, CallFrame f2, EqualObjectGraphs equal) {
            while (f1 != f2) {
                if (f1 == null || f2 == null) {
                    return Boolean.FALSE;
                }
                if (!f1.fieldsEqual(f2, equal)) {
                    return Boolean.FALSE;
                }
                f1 = f1.parentFrame;
                f2 = f2.parentFrame;
            }
            return Boolean.TRUE;
        }

        private boolean fieldsEqual(CallFrame other, EqualObjectGraphs equal) {
            return this.frameIndex == other.frameIndex && this.pc == other.pc && Interpreter.compareIdata(this.idata, other.idata) && equal.equalGraphs(this.varSource.stack, other.varSource.stack) && Arrays.equals(this.varSource.sDbl, other.varSource.sDbl) && equal.equalGraphs(this.thisObj, other.thisObj) && equal.equalGraphs(this.fnOrScript, other.fnOrScript) && equal.equalGraphs(this.scope, other.scope);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean compareIdata(InterpreterData i1, InterpreterData i2) {
        return i1 == i2 || Objects.equals(getEncodedSource(i1), getEncodedSource(i2));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/Interpreter$ContinuationJump.class */
    private static final class ContinuationJump implements Serializable {
        private static final long serialVersionUID = 7687739156004308247L;
        CallFrame capturedFrame;
        CallFrame branchFrame;
        Object result;
        double resultDbl;

        ContinuationJump(NativeContinuation c, CallFrame current) throws RuntimeException {
            this.capturedFrame = (CallFrame) c.getImplementation();
            if (this.capturedFrame == null || current == null) {
                this.branchFrame = null;
                return;
            }
            CallFrame chain1 = this.capturedFrame;
            CallFrame chain2 = current;
            int diff = chain1.frameIndex - chain2.frameIndex;
            if (diff != 0) {
                if (diff < 0) {
                    chain1 = current;
                    chain2 = this.capturedFrame;
                    diff = -diff;
                }
                do {
                    chain1 = chain1.parentFrame;
                    diff--;
                } while (diff != 0);
                if (chain1.frameIndex != chain2.frameIndex) {
                    Kit.codeBug();
                }
            }
            while (chain1 != chain2 && chain1 != null) {
                chain1 = chain1.parentFrame;
                chain2 = chain2.parentFrame;
            }
            this.branchFrame = chain1;
            if (this.branchFrame != null && !this.branchFrame.frozen) {
                Kit.codeBug();
            }
        }
    }

    private static CallFrame captureFrameForGenerator(CallFrame frame) throws RuntimeException {
        frame.frozen = true;
        CallFrame result = frame.cloneFrozen();
        frame.frozen = false;
        result.parentFrame = null;
        result.frameIndex = 0;
        return result;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Object compile(CompilerEnvirons compilerEnv, ScriptNode tree, String encodedSource, boolean returnFunction) {
        CodeGenerator cgen = new CodeGenerator();
        this.itsData = cgen.compile(compilerEnv, tree, encodedSource, returnFunction);
        return this.itsData;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Script createScriptObject(Object bytecode, Object staticSecurityDomain) throws RuntimeException {
        if (bytecode != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createScript(this.itsData, staticSecurityDomain);
    }

    @Override // org.mozilla.javascript.Evaluator
    public void setEvalScriptFlag(Script script) {
        ((InterpretedFunction) script).idata.evalScriptFlag = true;
    }

    @Override // org.mozilla.javascript.Evaluator
    public Function createFunctionObject(Context cx, Scriptable scope, Object bytecode, Object staticSecurityDomain) throws RuntimeException {
        if (bytecode != this.itsData) {
            Kit.codeBug();
        }
        return InterpretedFunction.createFunction(cx, scope, this.itsData, staticSecurityDomain);
    }

    private static int getShort(byte[] iCode, int pc) {
        return (iCode[pc] << 8) | (iCode[pc + 1] & 255);
    }

    private static int getIndex(byte[] iCode, int pc) {
        return ((iCode[pc] & 255) << 8) | (iCode[pc + 1] & 255);
    }

    private static int getInt(byte[] iCode, int pc) {
        return (iCode[pc] << 24) | ((iCode[pc + 1] & 255) << 16) | ((iCode[pc + 2] & 255) << 8) | (iCode[pc + 3] & 255);
    }

    private static int getExceptionHandler(CallFrame frame, boolean onlyFinally) throws RuntimeException {
        int[] exceptionTable = frame.idata.itsExceptionTable;
        if (exceptionTable == null) {
            return -1;
        }
        int pc = frame.pc - 1;
        int best = -1;
        int bestStart = 0;
        int bestEnd = 0;
        for (int i = 0; i != exceptionTable.length; i += 6) {
            int start = exceptionTable[i + 0];
            int end = exceptionTable[i + 1];
            if (start <= pc && pc < end && (!onlyFinally || exceptionTable[i + 3] == 1)) {
                if (best >= 0) {
                    if (bestEnd >= end) {
                        if (bestStart > start) {
                            Kit.codeBug();
                        }
                        if (bestEnd == end) {
                            Kit.codeBug();
                        }
                        best = i;
                        bestStart = start;
                        bestEnd = end;
                    }
                } else {
                    best = i;
                    bestStart = start;
                    bestEnd = end;
                }
            }
        }
        return best;
    }

    static void dumpICode(InterpreterData idata) {
    }

    private static int bytecodeSpan(int bytecode) {
        switch (bytecode) {
            case -66:
            case ByteSourceJsonBootstrapper.UTF8_BOM_3 /* -65 */:
            case -63:
            case -62:
            case 50:
            case 73:
                return 3;
            case -64:
            case -60:
            case -59:
            case -58:
            case -57:
            case -56:
            case -55:
            case -53:
            case -52:
            case -51:
            case -50:
            case -44:
            case -43:
            case -42:
            case -41:
            case -37:
            case -36:
            case -35:
            case -34:
            case -33:
            case -32:
            case -31:
            case -30:
            case -29:
            case -25:
            case -24:
            case -22:
            case -20:
            case -19:
            case -18:
            case ByteSourceJsonBootstrapper.UTF8_BOM_1 /* -17 */:
            case -16:
            case -15:
            case -14:
            case -13:
            case -12:
            case -5:
            case -4:
            case -3:
            case -2:
            case -1:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            default:
                if (validBytecode(bytecode)) {
                    return 1;
                }
                throw Kit.codeBug();
            case -61:
            case -49:
            case -48:
                return 2;
            case -54:
            case -23:
            case -6:
            case 5:
            case 6:
            case 7:
                return 3;
            case -47:
                return 5;
            case -46:
                return 3;
            case -45:
                return 2;
            case -40:
                return 5;
            case -39:
                return 3;
            case -38:
                return 2;
            case -28:
                return 5;
            case -27:
                return 3;
            case -26:
                return 3;
            case -21:
                return 5;
            case -11:
            case -10:
            case -9:
            case -8:
            case -7:
                return 2;
            case 57:
                return 2;
        }
    }

    static int[] getLineNumbers(InterpreterData data) throws RuntimeException {
        UintMap presentLines = new UintMap();
        byte[] iCode = data.itsICode;
        int iCodeLength = iCode.length;
        int i = 0;
        while (true) {
            int pc = i;
            if (pc != iCodeLength) {
                byte b = iCode[pc];
                int span = bytecodeSpan(b);
                if (b == -26) {
                    if (span != 3) {
                        Kit.codeBug();
                    }
                    int line = getIndex(iCode, pc + 1);
                    presentLines.put(line, 0);
                }
                i = pc + span;
            } else {
                return presentLines.getKeys();
            }
        }
    }

    @Override // org.mozilla.javascript.Evaluator
    public void captureStackInfo(RhinoException ex) throws RuntimeException {
        CallFrame[] array;
        Context cx = Context.getCurrentContext();
        if (cx == null || cx.lastInterpreterFrame == null) {
            ex.interpreterStackInfo = null;
            ex.interpreterLineData = null;
            return;
        }
        if (cx.previousInterpreterInvocations == null || cx.previousInterpreterInvocations.size() == 0) {
            array = new CallFrame[1];
        } else {
            int previousCount = cx.previousInterpreterInvocations.size();
            if (cx.previousInterpreterInvocations.peek() == cx.lastInterpreterFrame) {
                previousCount--;
            }
            array = new CallFrame[previousCount + 1];
            cx.previousInterpreterInvocations.toArray(array);
        }
        array[array.length - 1] = (CallFrame) cx.lastInterpreterFrame;
        int interpreterFrameCount = 0;
        for (int i = 0; i != array.length; i++) {
            interpreterFrameCount += 1 + array[i].frameIndex;
        }
        int[] linePC = new int[interpreterFrameCount];
        int linePCIndex = interpreterFrameCount;
        int i2 = array.length;
        while (i2 != 0) {
            i2--;
            CallFrame callFrame = array[i2];
            while (true) {
                CallFrame frame = callFrame;
                if (frame != null) {
                    linePCIndex--;
                    linePC[linePCIndex] = frame.pcSourceLineStart;
                    callFrame = frame.parentFrame;
                }
            }
        }
        if (linePCIndex != 0) {
            Kit.codeBug();
        }
        ex.interpreterStackInfo = array;
        ex.interpreterLineData = linePC;
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getSourcePositionFromStack(Context cx, int[] linep) {
        CallFrame frame = (CallFrame) cx.lastInterpreterFrame;
        InterpreterData idata = frame.idata;
        if (frame.pcSourceLineStart >= 0) {
            linep[0] = getIndex(idata.itsICode, frame.pcSourceLineStart);
        } else {
            linep[0] = 0;
        }
        return idata.itsSourceFile;
    }

    @Override // org.mozilla.javascript.Evaluator
    public String getPatchedStack(RhinoException ex, String nativeStackTrace) throws RuntimeException {
        char c;
        StringBuilder sb = new StringBuilder(nativeStackTrace.length() + 1000);
        String lineSeparator = SecurityUtilities.getSystemProperty("line.separator");
        CallFrame[] array = (CallFrame[]) ex.interpreterStackInfo;
        int[] linePC = ex.interpreterLineData;
        int arrayIndex = array.length;
        int linePCIndex = linePC.length;
        int offset = 0;
        while (arrayIndex != 0) {
            arrayIndex--;
            int pos = nativeStackTrace.indexOf("org.mozilla.javascript.Interpreter.interpretLoop", offset);
            if (pos < 0) {
                break;
            }
            int pos2 = pos + "org.mozilla.javascript.Interpreter.interpretLoop".length();
            while (pos2 != nativeStackTrace.length() && (c = nativeStackTrace.charAt(pos2)) != '\n' && c != '\r') {
                pos2++;
            }
            sb.append(nativeStackTrace.substring(offset, pos2));
            offset = pos2;
            CallFrame callFrame = array[arrayIndex];
            while (true) {
                CallFrame frame = callFrame;
                if (frame != null) {
                    if (linePCIndex == 0) {
                        Kit.codeBug();
                    }
                    linePCIndex--;
                    InterpreterData idata = frame.idata;
                    sb.append(lineSeparator);
                    sb.append("\tat script");
                    if (idata.itsName != null && idata.itsName.length() != 0) {
                        sb.append('.');
                        sb.append(idata.itsName);
                    }
                    sb.append('(');
                    sb.append(idata.itsSourceFile);
                    int pc = linePC[linePCIndex];
                    if (pc >= 0) {
                        sb.append(':');
                        sb.append(getIndex(idata.itsICode, pc));
                    }
                    sb.append(')');
                    callFrame = frame.parentFrame;
                }
            }
        }
        sb.append(nativeStackTrace.substring(offset));
        return sb.toString();
    }

    @Override // org.mozilla.javascript.Evaluator
    public List<String> getScriptStack(RhinoException ex) {
        ScriptStackElement[][] stack = getScriptStackElements(ex);
        List<String> list = new ArrayList<>(stack.length);
        String lineSeparator = SecurityUtilities.getSystemProperty("line.separator");
        for (ScriptStackElement[] group : stack) {
            StringBuilder sb = new StringBuilder();
            for (ScriptStackElement elem : group) {
                elem.renderJavaStyle(sb);
                sb.append(lineSeparator);
            }
            list.add(sb.toString());
        }
        return list;
    }

    public ScriptStackElement[][] getScriptStackElements(RhinoException ex) {
        if (ex.interpreterStackInfo == null) {
            return (ScriptStackElement[][]) null;
        }
        ArrayList arrayList = new ArrayList();
        CallFrame[] array = (CallFrame[]) ex.interpreterStackInfo;
        int[] linePC = ex.interpreterLineData;
        int arrayIndex = array.length;
        int linePCIndex = linePC.length;
        while (arrayIndex != 0) {
            arrayIndex--;
            CallFrame frame = array[arrayIndex];
            List<ScriptStackElement> group = new ArrayList<>();
            while (frame != null) {
                if (linePCIndex == 0) {
                    Kit.codeBug();
                }
                linePCIndex--;
                InterpreterData idata = frame.idata;
                String fileName = idata.itsSourceFile;
                String functionName = null;
                int lineNumber = -1;
                int pc = linePC[linePCIndex];
                if (pc >= 0) {
                    lineNumber = getIndex(idata.itsICode, pc);
                }
                if (idata.itsName != null && idata.itsName.length() != 0) {
                    functionName = idata.itsName;
                }
                frame = frame.parentFrame;
                group.add(new ScriptStackElement(fileName, functionName, lineNumber));
            }
            arrayList.add(group.toArray(new ScriptStackElement[group.size()]));
        }
        return (ScriptStackElement[][]) arrayList.toArray(new ScriptStackElement[arrayList.size()]);
    }

    static String getEncodedSource(InterpreterData idata) {
        if (idata.encodedSource == null) {
            return null;
        }
        return idata.encodedSource.substring(idata.encodedSourceStart, idata.encodedSourceEnd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initFunction(Context cx, Scriptable scope, InterpretedFunction parent, int index) {
        InterpretedFunction fn = InterpretedFunction.createFunction(cx, scope, parent, index);
        ScriptRuntime.initFunction(cx, scope, fn, fn.idata.itsFunctionType, parent.idata.evalScriptFlag);
    }

    static Object interpret(InterpretedFunction ifun, Context cx, Scriptable scope, Scriptable thisObj, Object[] args) throws RuntimeException {
        if (!ScriptRuntime.hasTopCall(cx)) {
            Kit.codeBug();
        }
        if (cx.interpreterSecurityDomain != ifun.securityDomain) {
            Object savedDomain = cx.interpreterSecurityDomain;
            cx.interpreterSecurityDomain = ifun.securityDomain;
            try {
                Object objCallWithDomain = ifun.securityController.callWithDomain(ifun.securityDomain, cx, ifun, scope, thisObj, args);
                cx.interpreterSecurityDomain = savedDomain;
                return objCallWithDomain;
            } catch (Throwable th) {
                cx.interpreterSecurityDomain = savedDomain;
                throw th;
            }
        }
        CallFrame frame = initFrame(cx, scope, thisObj, args, null, 0, args.length, ifun, null);
        frame.isContinuationsTopFrame = cx.isContinuationsTopCall;
        cx.isContinuationsTopCall = false;
        return interpretLoop(cx, frame, null);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/Interpreter$GeneratorState.class */
    static class GeneratorState {
        int operation;
        Object value;
        RuntimeException returnedException;

        GeneratorState(int operation, Object value) {
            this.operation = operation;
            this.value = value;
        }
    }

    public static Object resumeGenerator(Context cx, Scriptable scope, int operation, Object savedState, Object value) throws RuntimeException {
        CallFrame frame = (CallFrame) savedState;
        GeneratorState generatorState = new GeneratorState(operation, value);
        if (operation == 2) {
            try {
                return interpretLoop(cx, frame, generatorState);
            } catch (RuntimeException e) {
                if (e != value) {
                    throw e;
                }
                return Undefined.instance;
            }
        }
        Object result = interpretLoop(cx, frame, generatorState);
        if (generatorState.returnedException != null) {
            throw generatorState.returnedException;
        }
        return result;
    }

    public static Object restartContinuation(NativeContinuation c, Context cx, Scriptable scope, Object[] args) {
        Object arg;
        if (!ScriptRuntime.hasTopCall(cx)) {
            return ScriptRuntime.doTopCall(c, cx, scope, null, args, cx.isTopLevelStrict);
        }
        if (args.length == 0) {
            arg = Undefined.instance;
        } else {
            arg = args[0];
        }
        CallFrame capturedFrame = (CallFrame) c.getImplementation();
        if (capturedFrame == null) {
            return arg;
        }
        ContinuationJump cjump = new ContinuationJump(c, null);
        cjump.result = arg;
        return interpretLoop(cx, null, cjump);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    /* JADX WARN: Finally extract failed */
    private static java.lang.Object interpretLoop(org.mozilla.javascript.Context r12, org.mozilla.javascript.Interpreter.CallFrame r13, java.lang.Object r14) throws java.lang.RuntimeException {
        /*
            Method dump skipped, instructions count: 6568
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Interpreter.interpretLoop(org.mozilla.javascript.Context, org.mozilla.javascript.Interpreter$CallFrame, java.lang.Object):java.lang.Object");
    }

    private static int doInOrInstanceof(Context cx, int op, Object[] stack, double[] sDbl, int stackTop) {
        boolean valBln;
        Object rhs = stack[stackTop];
        if (rhs == UniqueTag.DOUBLE_MARK) {
            rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
        }
        int stackTop2 = stackTop - 1;
        Object lhs = stack[stackTop2];
        if (lhs == UniqueTag.DOUBLE_MARK) {
            lhs = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        if (op == 52) {
            valBln = ScriptRuntime.in(lhs, rhs, cx);
        } else {
            valBln = ScriptRuntime.instanceOf(lhs, rhs, cx);
        }
        stack[stackTop2] = ScriptRuntime.wrapBoolean(valBln);
        return stackTop2;
    }

    private static int doCompare(CallFrame frame, int op, Object[] stack, double[] sDbl, int stackTop) {
        boolean valBln;
        double rDbl;
        double lDbl;
        int stackTop2 = stackTop - 1;
        Object rhs = stack[stackTop2 + 1];
        Object lhs = stack[stackTop2];
        if (rhs == UniqueTag.DOUBLE_MARK) {
            rDbl = sDbl[stackTop2 + 1];
            lDbl = stack_double(frame, stackTop2);
        } else if (lhs == UniqueTag.DOUBLE_MARK) {
            rDbl = ScriptRuntime.toNumber(rhs);
            lDbl = sDbl[stackTop2];
        } else {
            switch (op) {
                case 14:
                    valBln = ScriptRuntime.cmp_LT(lhs, rhs);
                    break;
                case 15:
                    valBln = ScriptRuntime.cmp_LE(lhs, rhs);
                    break;
                case 16:
                    valBln = ScriptRuntime.cmp_LT(rhs, lhs);
                    break;
                case 17:
                    valBln = ScriptRuntime.cmp_LE(rhs, lhs);
                    break;
                default:
                    throw Kit.codeBug();
            }
            stack[stackTop2] = ScriptRuntime.wrapBoolean(valBln);
            return stackTop2;
        }
        switch (op) {
            case 14:
                valBln = lDbl < rDbl;
                break;
            case 15:
                valBln = lDbl <= rDbl;
                break;
            case 16:
                valBln = lDbl > rDbl;
                break;
            case 17:
                valBln = lDbl >= rDbl;
                break;
            default:
                throw Kit.codeBug();
        }
        stack[stackTop2] = ScriptRuntime.wrapBoolean(valBln);
        return stackTop2;
    }

    private static int doBitOp(CallFrame frame, int op, Object[] stack, double[] sDbl, int stackTop) {
        int lIntValue = stack_int32(frame, stackTop - 1);
        int rIntValue = stack_int32(frame, stackTop);
        int stackTop2 = stackTop - 1;
        stack[stackTop2] = UniqueTag.DOUBLE_MARK;
        switch (op) {
            case 9:
                lIntValue |= rIntValue;
                break;
            case 10:
                lIntValue ^= rIntValue;
                break;
            case 11:
                lIntValue &= rIntValue;
                break;
            case 18:
                lIntValue <<= rIntValue;
                break;
            case 19:
                lIntValue >>= rIntValue;
                break;
        }
        sDbl[stackTop2] = lIntValue;
        return stackTop2;
    }

    private static int doDelName(Context cx, CallFrame frame, int op, Object[] stack, double[] sDbl, int stackTop) {
        Object rhs = stack[stackTop];
        if (rhs == UniqueTag.DOUBLE_MARK) {
            rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
        }
        int stackTop2 = stackTop - 1;
        Object lhs = stack[stackTop2];
        if (lhs == UniqueTag.DOUBLE_MARK) {
            lhs = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        stack[stackTop2] = ScriptRuntime.delete(lhs, rhs, cx, frame.scope, op == 0);
        return stackTop2;
    }

    private static int doGetElem(Context cx, CallFrame frame, Object[] stack, double[] sDbl, int stackTop) {
        Object value;
        int stackTop2 = stackTop - 1;
        Object lhs = stack[stackTop2];
        if (lhs == UniqueTag.DOUBLE_MARK) {
            lhs = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        Object id = stack[stackTop2 + 1];
        if (id != UniqueTag.DOUBLE_MARK) {
            value = ScriptRuntime.getObjectElem(lhs, id, cx, frame.scope);
        } else {
            double d = sDbl[stackTop2 + 1];
            value = ScriptRuntime.getObjectIndex(lhs, d, cx, frame.scope);
        }
        stack[stackTop2] = value;
        return stackTop2;
    }

    private static int doSetElem(Context cx, CallFrame frame, Object[] stack, double[] sDbl, int stackTop) {
        Object value;
        int stackTop2 = stackTop - 2;
        Object rhs = stack[stackTop2 + 2];
        if (rhs == UniqueTag.DOUBLE_MARK) {
            rhs = ScriptRuntime.wrapNumber(sDbl[stackTop2 + 2]);
        }
        Object lhs = stack[stackTop2];
        if (lhs == UniqueTag.DOUBLE_MARK) {
            lhs = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        Object id = stack[stackTop2 + 1];
        if (id != UniqueTag.DOUBLE_MARK) {
            value = ScriptRuntime.setObjectElem(lhs, id, rhs, cx, frame.scope);
        } else {
            double d = sDbl[stackTop2 + 1];
            value = ScriptRuntime.setObjectIndex(lhs, d, rhs, cx, frame.scope);
        }
        stack[stackTop2] = value;
        return stackTop2;
    }

    private static int doElemIncDec(Context cx, CallFrame frame, byte[] iCode, Object[] stack, double[] sDbl, int stackTop) {
        Object rhs = stack[stackTop];
        if (rhs == UniqueTag.DOUBLE_MARK) {
            rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
        }
        int stackTop2 = stackTop - 1;
        Object lhs = stack[stackTop2];
        if (lhs == UniqueTag.DOUBLE_MARK) {
            lhs = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        stack[stackTop2] = ScriptRuntime.elemIncrDecr(lhs, rhs, cx, frame.scope, iCode[frame.pc]);
        frame.pc++;
        return stackTop2;
    }

    private static int doCallSpecial(Context cx, CallFrame frame, Object[] stack, double[] sDbl, int stackTop, byte[] iCode, int indexReg) {
        int stackTop2;
        int callType = iCode[frame.pc] & 255;
        boolean isNew = iCode[frame.pc + 1] != 0;
        int sourceLine = getIndex(iCode, frame.pc + 2);
        if (isNew) {
            stackTop2 = stackTop - indexReg;
            Object function = stack[stackTop2];
            if (function == UniqueTag.DOUBLE_MARK) {
                function = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
            }
            Object[] outArgs = getArgsArray(stack, sDbl, stackTop2 + 1, indexReg);
            stack[stackTop2] = ScriptRuntime.newSpecial(cx, function, outArgs, frame.scope, callType);
        } else {
            stackTop2 = stackTop - (1 + indexReg);
            Scriptable functionThis = (Scriptable) stack[stackTop2 + 1];
            Callable function2 = (Callable) stack[stackTop2];
            Object[] outArgs2 = getArgsArray(stack, sDbl, stackTop2 + 2, indexReg);
            stack[stackTop2] = ScriptRuntime.callSpecial(cx, function2, functionThis, outArgs2, frame.scope, frame.thisObj, callType, frame.idata.itsSourceFile, sourceLine);
        }
        frame.pc += 4;
        return stackTop2;
    }

    private static int doSetConstVar(CallFrame frame, Object[] stack, double[] sDbl, int stackTop, Object[] vars, double[] varDbls, int[] varAttributes, int indexReg) {
        if (!frame.useActivation) {
            if ((varAttributes[indexReg] & 1) == 0) {
                throw Context.reportRuntimeError1("msg.var.redecl", frame.idata.argNames[indexReg]);
            }
            if ((varAttributes[indexReg] & 8) != 0) {
                vars[indexReg] = stack[stackTop];
                varAttributes[indexReg] = varAttributes[indexReg] & (-9);
                varDbls[indexReg] = sDbl[stackTop];
            }
        } else {
            Object val = stack[stackTop];
            if (val == UniqueTag.DOUBLE_MARK) {
                val = ScriptRuntime.wrapNumber(sDbl[stackTop]);
            }
            String stringReg = frame.idata.argNames[indexReg];
            if (frame.scope instanceof ConstProperties) {
                ConstProperties cp = (ConstProperties) frame.scope;
                cp.putConst(stringReg, frame.scope, val);
            } else {
                throw Kit.codeBug();
            }
        }
        return stackTop;
    }

    private static int doSetVar(CallFrame frame, Object[] stack, double[] sDbl, int stackTop, Object[] vars, double[] varDbls, int[] varAttributes, int indexReg) {
        if (!frame.useActivation) {
            if ((varAttributes[indexReg] & 1) == 0) {
                vars[indexReg] = stack[stackTop];
                varDbls[indexReg] = sDbl[stackTop];
            }
        } else {
            Object val = stack[stackTop];
            if (val == UniqueTag.DOUBLE_MARK) {
                val = ScriptRuntime.wrapNumber(sDbl[stackTop]);
            }
            String stringReg = frame.idata.argNames[indexReg];
            frame.scope.put(stringReg, frame.scope, val);
        }
        return stackTop;
    }

    private static int doGetVar(CallFrame frame, Object[] stack, double[] sDbl, int stackTop, Object[] vars, double[] varDbls, int indexReg) {
        int stackTop2 = stackTop + 1;
        if (!frame.useActivation) {
            stack[stackTop2] = vars[indexReg];
            sDbl[stackTop2] = varDbls[indexReg];
        } else {
            String stringReg = frame.idata.argNames[indexReg];
            stack[stackTop2] = frame.scope.get(stringReg, frame.scope);
        }
        return stackTop2;
    }

    private static int doVarIncDec(Context cx, CallFrame frame, Object[] stack, double[] sDbl, int stackTop, Object[] vars, double[] varDbls, int[] varAttributes, int indexReg) {
        double d;
        int stackTop2 = stackTop + 1;
        byte b = frame.idata.itsICode[frame.pc];
        if (!frame.useActivation) {
            Object varValue = vars[indexReg];
            if (varValue == UniqueTag.DOUBLE_MARK) {
                d = varDbls[indexReg];
            } else {
                d = ScriptRuntime.toNumber(varValue);
            }
            double d2 = (b & 1) == 0 ? d + 1.0d : d - 1.0d;
            boolean post = (b & 2) != 0;
            if ((varAttributes[indexReg] & 1) == 0) {
                if (varValue != UniqueTag.DOUBLE_MARK) {
                    vars[indexReg] = UniqueTag.DOUBLE_MARK;
                }
                varDbls[indexReg] = d2;
                stack[stackTop2] = UniqueTag.DOUBLE_MARK;
                sDbl[stackTop2] = post ? d : d2;
            } else if (post && varValue != UniqueTag.DOUBLE_MARK) {
                stack[stackTop2] = varValue;
            } else {
                stack[stackTop2] = UniqueTag.DOUBLE_MARK;
                sDbl[stackTop2] = post ? d : d2;
            }
        } else {
            String varName = frame.idata.argNames[indexReg];
            stack[stackTop2] = ScriptRuntime.nameIncrDecr(frame.scope, varName, cx, b);
        }
        frame.pc++;
        return stackTop2;
    }

    private static int doRefMember(Context cx, Object[] stack, double[] sDbl, int stackTop, int flags) {
        Object elem = stack[stackTop];
        if (elem == UniqueTag.DOUBLE_MARK) {
            elem = ScriptRuntime.wrapNumber(sDbl[stackTop]);
        }
        int stackTop2 = stackTop - 1;
        Object obj = stack[stackTop2];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        stack[stackTop2] = ScriptRuntime.memberRef(obj, elem, cx, flags);
        return stackTop2;
    }

    private static int doRefNsMember(Context cx, Object[] stack, double[] sDbl, int stackTop, int flags) {
        Object elem = stack[stackTop];
        if (elem == UniqueTag.DOUBLE_MARK) {
            elem = ScriptRuntime.wrapNumber(sDbl[stackTop]);
        }
        int stackTop2 = stackTop - 1;
        Object ns = stack[stackTop2];
        if (ns == UniqueTag.DOUBLE_MARK) {
            ns = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        int stackTop3 = stackTop2 - 1;
        Object obj = stack[stackTop3];
        if (obj == UniqueTag.DOUBLE_MARK) {
            obj = ScriptRuntime.wrapNumber(sDbl[stackTop3]);
        }
        stack[stackTop3] = ScriptRuntime.memberRef(obj, ns, elem, cx, flags);
        return stackTop3;
    }

    private static int doRefNsName(Context cx, CallFrame frame, Object[] stack, double[] sDbl, int stackTop, int flags) {
        Object name = stack[stackTop];
        if (name == UniqueTag.DOUBLE_MARK) {
            name = ScriptRuntime.wrapNumber(sDbl[stackTop]);
        }
        int stackTop2 = stackTop - 1;
        Object ns = stack[stackTop2];
        if (ns == UniqueTag.DOUBLE_MARK) {
            ns = ScriptRuntime.wrapNumber(sDbl[stackTop2]);
        }
        stack[stackTop2] = ScriptRuntime.nameRef(ns, name, cx, frame.scope, flags);
        return stackTop2;
    }

    private static CallFrame initFrameForNoSuchMethod(Context cx, CallFrame frame, int indexReg, Object[] stack, double[] sDbl, int stackTop, int op, Scriptable funThisObj, Scriptable calleeScope, ScriptRuntime.NoSuchMethodShim noSuchMethodShim, InterpretedFunction ifun) throws RuntimeException {
        int shift = stackTop + 2;
        Object[] elements = new Object[indexReg];
        int i = 0;
        while (i < indexReg) {
            Object val = stack[shift];
            if (val == UniqueTag.DOUBLE_MARK) {
                val = ScriptRuntime.wrapNumber(sDbl[shift]);
            }
            elements[i] = val;
            i++;
            shift++;
        }
        Object[] argsArray = {noSuchMethodShim.methodName, cx.newArray(calleeScope, elements)};
        CallFrame callParentFrame = frame;
        if (op == -55) {
            callParentFrame = frame.parentFrame;
            exitFrame(cx, frame, null);
        }
        CallFrame calleeFrame = initFrame(cx, calleeScope, funThisObj, argsArray, null, 0, 2, ifun, callParentFrame);
        if (op != -55) {
            frame.savedStackTop = stackTop;
            frame.savedCallOp = op;
        }
        return calleeFrame;
    }

    private static boolean doEquals(Object[] stack, double[] sDbl, int stackTop) {
        Object rhs = stack[stackTop + 1];
        Object lhs = stack[stackTop];
        if (rhs == UniqueTag.DOUBLE_MARK) {
            if (lhs == UniqueTag.DOUBLE_MARK) {
                return sDbl[stackTop] == sDbl[stackTop + 1];
            }
            return ScriptRuntime.eqNumber(sDbl[stackTop + 1], lhs);
        }
        if (lhs == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.eqNumber(sDbl[stackTop], rhs);
        }
        return ScriptRuntime.eq(lhs, rhs);
    }

    private static boolean doShallowEquals(Object[] stack, double[] sDbl, int stackTop) {
        double ldbl;
        double rdbl;
        Object rhs = stack[stackTop + 1];
        Object lhs = stack[stackTop];
        Object DBL_MRK = UniqueTag.DOUBLE_MARK;
        if (rhs == DBL_MRK) {
            rdbl = sDbl[stackTop + 1];
            if (lhs == DBL_MRK) {
                ldbl = sDbl[stackTop];
            } else if (lhs instanceof Number) {
                ldbl = ((Number) lhs).doubleValue();
            } else {
                return false;
            }
        } else if (lhs == DBL_MRK) {
            ldbl = sDbl[stackTop];
            if (rhs instanceof Number) {
                rdbl = ((Number) rhs).doubleValue();
            } else {
                return false;
            }
        } else {
            return ScriptRuntime.shallowEq(lhs, rhs);
        }
        return ldbl == rdbl;
    }

    private static CallFrame processThrowable(Context cx, Object throwable, CallFrame frame, int indexReg, boolean instructionCounting) throws RuntimeException {
        Object throwable2;
        if (indexReg >= 0) {
            if (frame.frozen) {
                frame = frame.cloneFrozen();
            }
            int[] table = frame.idata.itsExceptionTable;
            frame.pc = table[indexReg + 2];
            if (instructionCounting) {
                frame.pcPrevBranch = frame.pc;
            }
            frame.savedStackTop = frame.emptyStackTop;
            int scopeLocal = frame.localShift + table[indexReg + 5];
            int exLocal = frame.localShift + table[indexReg + 4];
            frame.scope = (Scriptable) frame.stack[scopeLocal];
            frame.stack[exLocal] = throwable;
            throwable2 = null;
        } else {
            ContinuationJump cjump = (ContinuationJump) throwable;
            throwable2 = null;
            if (cjump.branchFrame != frame) {
                Kit.codeBug();
            }
            if (cjump.capturedFrame == null) {
                Kit.codeBug();
            }
            int rewindCount = cjump.capturedFrame.frameIndex + 1;
            if (cjump.branchFrame != null) {
                rewindCount -= cjump.branchFrame.frameIndex;
            }
            int enterCount = 0;
            CallFrame[] enterFrames = null;
            CallFrame x = cjump.capturedFrame;
            for (int i = 0; i != rewindCount; i++) {
                if (!x.frozen) {
                    Kit.codeBug();
                }
                if (x.useActivation) {
                    if (enterFrames == null) {
                        enterFrames = new CallFrame[rewindCount - i];
                    }
                    enterFrames[enterCount] = x;
                    enterCount++;
                }
                x = x.parentFrame;
            }
            while (enterCount != 0) {
                enterCount--;
                CallFrame x2 = enterFrames[enterCount];
                enterFrame(cx, x2, ScriptRuntime.emptyArgs, true);
            }
            frame = cjump.capturedFrame.cloneFrozen();
            setCallResult(frame, cjump.result, cjump.resultDbl);
        }
        frame.throwable = throwable2;
        return frame;
    }

    private static Object freezeGenerator(Context cx, CallFrame frame, int stackTop, GeneratorState generatorState, boolean yieldStar) {
        if (generatorState.operation == 2) {
            throw ScriptRuntime.typeError0("msg.yield.closing");
        }
        frame.frozen = true;
        frame.result = frame.stack[stackTop];
        frame.resultDbl = frame.sDbl[stackTop];
        frame.savedStackTop = stackTop;
        frame.pc--;
        ScriptRuntime.exitActivationFunction(cx);
        Object result = frame.result != UniqueTag.DOUBLE_MARK ? frame.result : ScriptRuntime.wrapNumber(frame.resultDbl);
        if (yieldStar) {
            return new ES6Generator.YieldStarResult(result);
        }
        return result;
    }

    private static Object thawGenerator(CallFrame frame, int stackTop, GeneratorState generatorState, int op) {
        frame.frozen = false;
        int sourceLine = getIndex(frame.idata.itsICode, frame.pc);
        frame.pc += 2;
        if (generatorState.operation == 1) {
            return new JavaScriptException(generatorState.value, frame.idata.itsSourceFile, sourceLine);
        }
        if (generatorState.operation == 2) {
            return generatorState.value;
        }
        if (generatorState.operation != 0) {
            throw Kit.codeBug();
        }
        if (op == 73 || op == -66) {
            frame.stack[stackTop] = generatorState.value;
        }
        return Scriptable.NOT_FOUND;
    }

    private static CallFrame initFrameForApplyOrCall(Context cx, CallFrame frame, int indexReg, Object[] stack, double[] sDbl, int stackTop, int op, Scriptable calleeScope, IdFunctionObject ifun, InterpretedFunction iApplyCallable) throws RuntimeException {
        Scriptable applyThis;
        CallFrame calleeFrame;
        if (indexReg != 0) {
            Object obj = stack[stackTop + 2];
            if (obj == UniqueTag.DOUBLE_MARK) {
                obj = ScriptRuntime.wrapNumber(sDbl[stackTop + 2]);
            }
            applyThis = ScriptRuntime.toObjectOrNull(cx, obj, frame.scope);
        } else {
            applyThis = null;
        }
        if (applyThis == null) {
            applyThis = ScriptRuntime.getTopCallScope(cx);
        }
        if (op == -55) {
            exitFrame(cx, frame, null);
            frame = frame.parentFrame;
        } else {
            frame.savedStackTop = stackTop;
            frame.savedCallOp = op;
        }
        if (BaseFunction.isApply(ifun)) {
            Object[] callArgs = indexReg < 2 ? ScriptRuntime.emptyArgs : ScriptRuntime.getApplyArguments(cx, stack[stackTop + 3]);
            calleeFrame = initFrame(cx, calleeScope, applyThis, callArgs, null, 0, callArgs.length, iApplyCallable, frame);
        } else {
            for (int i = 1; i < indexReg; i++) {
                stack[stackTop + 1 + i] = stack[stackTop + 2 + i];
                sDbl[stackTop + 1 + i] = sDbl[stackTop + 2 + i];
            }
            int argCount = indexReg < 2 ? 0 : indexReg - 1;
            calleeFrame = initFrame(cx, calleeScope, applyThis, stack, sDbl, stackTop + 2, argCount, iApplyCallable, frame);
        }
        return calleeFrame;
    }

    private static CallFrame initFrame(Context cx, Scriptable callerScope, Scriptable thisObj, Object[] args, double[] argsDbl, int argShift, int argCount, InterpretedFunction fnOrScript, CallFrame parentFrame) throws RuntimeException {
        CallFrame frame = new CallFrame(cx, thisObj, fnOrScript, parentFrame);
        frame.initializeArgs(cx, callerScope, args, argsDbl, argShift, argCount);
        enterFrame(cx, frame, args, false);
        return frame;
    }

    private static void enterFrame(Context cx, CallFrame frame, Object[] args, boolean continuationRestart) throws RuntimeException {
        boolean usesActivation = frame.idata.itsNeedsActivation;
        boolean isDebugged = frame.debuggerFrame != null;
        if (usesActivation || isDebugged) {
            Scriptable scope = frame.scope;
            if (scope == null) {
                Kit.codeBug();
            } else if (continuationRestart) {
                while (scope instanceof NativeWith) {
                    scope = scope.getParentScope();
                    if (scope == null || (frame.parentFrame != null && frame.parentFrame.scope == scope)) {
                        Kit.codeBug();
                        break;
                    }
                }
            }
            if (isDebugged) {
                frame.debuggerFrame.onEnter(cx, scope, frame.thisObj, args);
            }
            if (usesActivation) {
                ScriptRuntime.enterActivationFunction(cx, scope);
            }
        }
    }

    private static void exitFrame(Context cx, CallFrame frame, Object throwable) {
        Object result;
        double resultDbl;
        if (frame.idata.itsNeedsActivation) {
            ScriptRuntime.exitActivationFunction(cx);
        }
        if (frame.debuggerFrame != null) {
            try {
                if (throwable instanceof Throwable) {
                    frame.debuggerFrame.onExit(cx, true, throwable);
                } else {
                    ContinuationJump cjump = (ContinuationJump) throwable;
                    if (cjump == null) {
                        result = frame.result;
                    } else {
                        result = cjump.result;
                    }
                    if (result == UniqueTag.DOUBLE_MARK) {
                        if (cjump == null) {
                            resultDbl = frame.resultDbl;
                        } else {
                            resultDbl = cjump.resultDbl;
                        }
                        result = ScriptRuntime.wrapNumber(resultDbl);
                    }
                    frame.debuggerFrame.onExit(cx, false, result);
                }
            } catch (Throwable ex) {
                System.err.println("RHINO USAGE WARNING: onExit terminated with exception");
                ex.printStackTrace(System.err);
            }
        }
    }

    private static void setCallResult(CallFrame frame, Object callResult, double callResultDbl) throws RuntimeException {
        if (frame.savedCallOp == 38) {
            frame.stack[frame.savedStackTop] = callResult;
            frame.sDbl[frame.savedStackTop] = callResultDbl;
        } else if (frame.savedCallOp == 30) {
            if (callResult instanceof Scriptable) {
                frame.stack[frame.savedStackTop] = callResult;
            }
        } else {
            Kit.codeBug();
        }
        frame.savedCallOp = 0;
    }

    public static NativeContinuation captureContinuation(Context cx) {
        if (cx.lastInterpreterFrame == null || !(cx.lastInterpreterFrame instanceof CallFrame)) {
            throw new IllegalStateException("Interpreter frames not found");
        }
        return captureContinuation(cx, (CallFrame) cx.lastInterpreterFrame, true);
    }

    private static NativeContinuation captureContinuation(Context cx, CallFrame frame, boolean requireContinuationsTopFrame) throws RuntimeException {
        NativeContinuation c = new NativeContinuation();
        ScriptRuntime.setObjectProtoAndParent(c, ScriptRuntime.getTopCallScope(cx));
        CallFrame outermost = frame;
        for (CallFrame x = frame; x != null && !x.frozen; x = x.parentFrame) {
            x.frozen = true;
            for (int i = x.savedStackTop + 1; i != x.stack.length; i++) {
                x.stack[i] = null;
                x.stackAttributes[i] = 0;
            }
            if (x.savedCallOp == 38) {
                x.stack[x.savedStackTop] = null;
            } else if (x.savedCallOp != 30) {
                Kit.codeBug();
            }
            outermost = x;
        }
        if (requireContinuationsTopFrame) {
            while (outermost.parentFrame != null) {
                outermost = outermost.parentFrame;
            }
            if (!outermost.isContinuationsTopFrame) {
                throw new IllegalStateException("Cannot capture continuation from JavaScript code not called directly by executeScriptWithContinuations or callFunctionWithContinuations");
            }
        }
        c.initImplementation(frame);
        return c;
    }

    private static int stack_int32(CallFrame frame, int i) {
        Object x = frame.stack[i];
        if (x == UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toInt32(frame.sDbl[i]);
        }
        return ScriptRuntime.toInt32(x);
    }

    private static double stack_double(CallFrame frame, int i) {
        Object x = frame.stack[i];
        if (x != UniqueTag.DOUBLE_MARK) {
            return ScriptRuntime.toNumber(x);
        }
        return frame.sDbl[i];
    }

    private static boolean stack_boolean(CallFrame frame, int i) {
        Object x = frame.stack[i];
        if (Boolean.TRUE.equals(x)) {
            return true;
        }
        if (Boolean.FALSE.equals(x)) {
            return false;
        }
        if (x == UniqueTag.DOUBLE_MARK) {
            double d = frame.sDbl[i];
            return (Double.isNaN(d) || d == 0.0d) ? false : true;
        }
        if (x == null || x == Undefined.instance) {
            return false;
        }
        if (x instanceof Number) {
            double d2 = ((Number) x).doubleValue();
            return (Double.isNaN(d2) || d2 == 0.0d) ? false : true;
        }
        return ScriptRuntime.toBoolean(x);
    }

    private static void doAdd(Object[] stack, double[] sDbl, int stackTop, Context cx) {
        double d;
        boolean leftRightOrder;
        Object rhs = stack[stackTop + 1];
        Object lhs = stack[stackTop];
        if (rhs == UniqueTag.DOUBLE_MARK) {
            d = sDbl[stackTop + 1];
            if (lhs == UniqueTag.DOUBLE_MARK) {
                sDbl[stackTop] = sDbl[stackTop] + d;
                return;
            }
            leftRightOrder = true;
        } else if (lhs == UniqueTag.DOUBLE_MARK) {
            d = sDbl[stackTop];
            lhs = rhs;
            leftRightOrder = false;
        } else {
            if ((lhs instanceof Scriptable) || (rhs instanceof Scriptable)) {
                stack[stackTop] = ScriptRuntime.add(lhs, rhs, cx);
                return;
            }
            if (lhs instanceof CharSequence) {
                if (rhs instanceof CharSequence) {
                    stack[stackTop] = new ConsString((CharSequence) lhs, (CharSequence) rhs);
                    return;
                } else {
                    stack[stackTop] = new ConsString((CharSequence) lhs, ScriptRuntime.toCharSequence(rhs));
                    return;
                }
            }
            if (rhs instanceof CharSequence) {
                stack[stackTop] = new ConsString(ScriptRuntime.toCharSequence(lhs), (CharSequence) rhs);
                return;
            }
            double lDbl = lhs instanceof Number ? ((Number) lhs).doubleValue() : ScriptRuntime.toNumber(lhs);
            double rDbl = rhs instanceof Number ? ((Number) rhs).doubleValue() : ScriptRuntime.toNumber(rhs);
            stack[stackTop] = UniqueTag.DOUBLE_MARK;
            sDbl[stackTop] = lDbl + rDbl;
            return;
        }
        if (lhs instanceof Scriptable) {
            Object rhs2 = ScriptRuntime.wrapNumber(d);
            if (!leftRightOrder) {
                Object tmp = lhs;
                lhs = rhs2;
                rhs2 = tmp;
            }
            stack[stackTop] = ScriptRuntime.add(lhs, rhs2, cx);
            return;
        }
        if (lhs instanceof CharSequence) {
            CharSequence rstr = ScriptRuntime.numberToString(d, 10);
            if (leftRightOrder) {
                stack[stackTop] = new ConsString((CharSequence) lhs, rstr);
                return;
            } else {
                stack[stackTop] = new ConsString(rstr, (CharSequence) lhs);
                return;
            }
        }
        double lDbl2 = lhs instanceof Number ? ((Number) lhs).doubleValue() : ScriptRuntime.toNumber(lhs);
        stack[stackTop] = UniqueTag.DOUBLE_MARK;
        sDbl[stackTop] = lDbl2 + d;
    }

    private static int doArithmetic(CallFrame frame, int op, Object[] stack, double[] sDbl, int stackTop) {
        double rDbl = stack_double(frame, stackTop);
        int stackTop2 = stackTop - 1;
        double lDbl = stack_double(frame, stackTop2);
        stack[stackTop2] = UniqueTag.DOUBLE_MARK;
        switch (op) {
            case 22:
                lDbl -= rDbl;
                break;
            case 23:
                lDbl *= rDbl;
                break;
            case 24:
                lDbl /= rDbl;
                break;
            case 25:
                lDbl %= rDbl;
                break;
        }
        sDbl[stackTop2] = lDbl;
        return stackTop2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object[] getArgsArray(Object[] stack, double[] sDbl, int shift, int count) {
        if (count == 0) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] args = new Object[count];
        int i = 0;
        while (i != count) {
            Object val = stack[shift];
            if (val == UniqueTag.DOUBLE_MARK) {
                val = ScriptRuntime.wrapNumber(sDbl[shift]);
            }
            args[i] = val;
            i++;
            shift++;
        }
        return args;
    }

    private static void addInstructionCount(Context cx, CallFrame frame, int extra) {
        cx.instructionCount += (frame.pc - frame.pcPrevBranch) + extra;
        if (cx.instructionCount > cx.instructionThreshold) {
            cx.observeInstructionCount(cx.instructionCount);
            cx.instructionCount = 0;
        }
    }
}
