package org.antlr.v4.runtime;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.misc.Interval;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenStreamRewriter.class */
public class TokenStreamRewriter {
    public static final String DEFAULT_PROGRAM_NAME = "default";
    public static final int PROGRAM_INIT_SIZE = 100;
    public static final int MIN_TOKEN_INDEX = 0;
    protected final TokenStream tokens;
    protected final Map<String, List<RewriteOperation>> programs = new HashMap();
    protected final Map<String, Integer> lastRewriteTokenIndexes;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenStreamRewriter$RewriteOperation.class */
    public class RewriteOperation {
        protected int instructionIndex;
        protected int index;
        protected Object text;

        protected RewriteOperation(int index) {
            this.index = index;
        }

        protected RewriteOperation(int index, Object text) {
            this.index = index;
            this.text = text;
        }

        public int execute(StringBuilder buf) {
            return this.index;
        }

        public String toString() {
            String opName = getClass().getName();
            int $index = opName.indexOf(36);
            return "<" + opName.substring($index + 1, opName.length()) + StrPool.AT + TokenStreamRewriter.this.tokens.get(this.index) + ":\"" + this.text + "\">";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenStreamRewriter$InsertBeforeOp.class */
    class InsertBeforeOp extends RewriteOperation {
        public InsertBeforeOp(int index, Object text) {
            super(index, text);
        }

        @Override // org.antlr.v4.runtime.TokenStreamRewriter.RewriteOperation
        public int execute(StringBuilder buf) {
            buf.append(this.text);
            if (TokenStreamRewriter.this.tokens.get(this.index).getType() != -1) {
                buf.append(TokenStreamRewriter.this.tokens.get(this.index).getText());
            }
            return this.index + 1;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenStreamRewriter$InsertAfterOp.class */
    class InsertAfterOp extends InsertBeforeOp {
        public InsertAfterOp(int index, Object text) {
            super(index + 1, text);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/TokenStreamRewriter$ReplaceOp.class */
    class ReplaceOp extends RewriteOperation {
        protected int lastIndex;

        public ReplaceOp(int from, int to, Object text) {
            super(from, text);
            this.lastIndex = to;
        }

        @Override // org.antlr.v4.runtime.TokenStreamRewriter.RewriteOperation
        public int execute(StringBuilder buf) {
            if (this.text != null) {
                buf.append(this.text);
            }
            return this.lastIndex + 1;
        }

        @Override // org.antlr.v4.runtime.TokenStreamRewriter.RewriteOperation
        public String toString() {
            if (this.text == null) {
                return "<DeleteOp@" + TokenStreamRewriter.this.tokens.get(this.index) + ".." + TokenStreamRewriter.this.tokens.get(this.lastIndex) + ">";
            }
            return "<ReplaceOp@" + TokenStreamRewriter.this.tokens.get(this.index) + ".." + TokenStreamRewriter.this.tokens.get(this.lastIndex) + ":\"" + this.text + "\">";
        }
    }

    public TokenStreamRewriter(TokenStream tokens) {
        this.tokens = tokens;
        this.programs.put("default", new ArrayList(100));
        this.lastRewriteTokenIndexes = new HashMap();
    }

    public final TokenStream getTokenStream() {
        return this.tokens;
    }

    public void rollback(int instructionIndex) {
        rollback("default", instructionIndex);
    }

    public void rollback(String programName, int instructionIndex) {
        List<RewriteOperation> is = this.programs.get(programName);
        if (is != null) {
            this.programs.put(programName, is.subList(0, instructionIndex));
        }
    }

    public void deleteProgram() {
        deleteProgram("default");
    }

    public void deleteProgram(String programName) {
        rollback(programName, 0);
    }

    public void insertAfter(Token t, Object text) {
        insertAfter("default", t, text);
    }

    public void insertAfter(int index, Object text) {
        insertAfter("default", index, text);
    }

    public void insertAfter(String programName, Token t, Object text) {
        insertAfter(programName, t.getTokenIndex(), text);
    }

    public void insertAfter(String programName, int index, Object text) {
        RewriteOperation op = new InsertAfterOp(index, text);
        List<RewriteOperation> rewrites = getProgram(programName);
        op.instructionIndex = rewrites.size();
        rewrites.add(op);
    }

    public void insertBefore(Token t, Object text) {
        insertBefore("default", t, text);
    }

    public void insertBefore(int index, Object text) {
        insertBefore("default", index, text);
    }

    public void insertBefore(String programName, Token t, Object text) {
        insertBefore(programName, t.getTokenIndex(), text);
    }

    public void insertBefore(String programName, int index, Object text) {
        RewriteOperation op = new InsertBeforeOp(index, text);
        List<RewriteOperation> rewrites = getProgram(programName);
        op.instructionIndex = rewrites.size();
        rewrites.add(op);
    }

    public void replace(int index, Object text) {
        replace("default", index, index, text);
    }

    public void replace(int from, int to, Object text) {
        replace("default", from, to, text);
    }

    public void replace(Token indexT, Object text) {
        replace("default", indexT, indexT, text);
    }

    public void replace(Token from, Token to, Object text) {
        replace("default", from, to, text);
    }

    public void replace(String programName, int from, int to, Object text) {
        if (from > to || from < 0 || to < 0 || to >= this.tokens.size()) {
            throw new IllegalArgumentException("replace: range invalid: " + from + ".." + to + "(size=" + this.tokens.size() + ")");
        }
        RewriteOperation op = new ReplaceOp(from, to, text);
        List<RewriteOperation> rewrites = getProgram(programName);
        op.instructionIndex = rewrites.size();
        rewrites.add(op);
    }

    public void replace(String programName, Token from, Token to, Object text) {
        replace(programName, from.getTokenIndex(), to.getTokenIndex(), text);
    }

    public void delete(int index) {
        delete("default", index, index);
    }

    public void delete(int from, int to) {
        delete("default", from, to);
    }

    public void delete(Token indexT) {
        delete("default", indexT, indexT);
    }

    public void delete(Token from, Token to) {
        delete("default", from, to);
    }

    public void delete(String programName, int from, int to) {
        replace(programName, from, to, (Object) null);
    }

    public void delete(String programName, Token from, Token to) {
        replace(programName, from, to, (Object) null);
    }

    public int getLastRewriteTokenIndex() {
        return getLastRewriteTokenIndex("default");
    }

    protected int getLastRewriteTokenIndex(String programName) {
        Integer I = this.lastRewriteTokenIndexes.get(programName);
        if (I == null) {
            return -1;
        }
        return I.intValue();
    }

    protected void setLastRewriteTokenIndex(String programName, int i) {
        this.lastRewriteTokenIndexes.put(programName, Integer.valueOf(i));
    }

    protected List<RewriteOperation> getProgram(String name) {
        List<RewriteOperation> is = this.programs.get(name);
        if (is == null) {
            is = initializeProgram(name);
        }
        return is;
    }

    private List<RewriteOperation> initializeProgram(String name) {
        List<RewriteOperation> is = new ArrayList<>(100);
        this.programs.put(name, is);
        return is;
    }

    public String getText() {
        return getText("default", Interval.of(0, this.tokens.size() - 1));
    }

    public String getText(String programName) {
        return getText(programName, Interval.of(0, this.tokens.size() - 1));
    }

    public String getText(Interval interval) {
        return getText("default", interval);
    }

    public String getText(String programName, Interval interval) {
        List<RewriteOperation> rewrites = this.programs.get(programName);
        int start = interval.a;
        int stop = interval.b;
        if (stop > this.tokens.size() - 1) {
            stop = this.tokens.size() - 1;
        }
        if (start < 0) {
            start = 0;
        }
        if (rewrites == null || rewrites.isEmpty()) {
            return this.tokens.getText(interval);
        }
        StringBuilder buf = new StringBuilder();
        Map<Integer, RewriteOperation> indexToOp = reduceToSingleOperationPerIndex(rewrites);
        int i = start;
        while (i <= stop && i < this.tokens.size()) {
            RewriteOperation op = indexToOp.get(Integer.valueOf(i));
            indexToOp.remove(Integer.valueOf(i));
            Token t = this.tokens.get(i);
            if (op == null) {
                if (t.getType() != -1) {
                    buf.append(t.getText());
                }
                i++;
            } else {
                i = op.execute(buf);
            }
        }
        if (stop == this.tokens.size() - 1) {
            for (RewriteOperation op2 : indexToOp.values()) {
                if (op2.index >= this.tokens.size() - 1) {
                    buf.append(op2.text);
                }
            }
        }
        return buf.toString();
    }

    protected Map<Integer, RewriteOperation> reduceToSingleOperationPerIndex(List<RewriteOperation> rewrites) {
        for (int i = 0; i < rewrites.size(); i++) {
            RewriteOperation op = rewrites.get(i);
            if (op != null && (op instanceof ReplaceOp)) {
                ReplaceOp rop = (ReplaceOp) rewrites.get(i);
                List<? extends InsertBeforeOp> inserts = getKindOfOps(rewrites, InsertBeforeOp.class, i);
                for (InsertBeforeOp iop : inserts) {
                    if (iop.index == rop.index) {
                        rewrites.set(iop.instructionIndex, null);
                        rop.text = iop.text.toString() + (rop.text != null ? rop.text.toString() : "");
                    } else if (iop.index > rop.index && iop.index <= rop.lastIndex) {
                        rewrites.set(iop.instructionIndex, null);
                    }
                }
                List<? extends ReplaceOp> prevReplaces = getKindOfOps(rewrites, ReplaceOp.class, i);
                for (ReplaceOp prevRop : prevReplaces) {
                    if (prevRop.index >= rop.index && prevRop.lastIndex <= rop.lastIndex) {
                        rewrites.set(prevRop.instructionIndex, null);
                    } else {
                        boolean disjoint = prevRop.lastIndex < rop.index || prevRop.index > rop.lastIndex;
                        if (prevRop.text == null && rop.text == null && !disjoint) {
                            rewrites.set(prevRop.instructionIndex, null);
                            rop.index = Math.min(prevRop.index, rop.index);
                            rop.lastIndex = Math.max(prevRop.lastIndex, rop.lastIndex);
                            System.out.println("new rop " + rop);
                        } else if (!disjoint) {
                            throw new IllegalArgumentException("replace op boundaries of " + rop + " overlap with previous " + prevRop);
                        }
                    }
                }
            }
        }
        for (int i2 = 0; i2 < rewrites.size(); i2++) {
            RewriteOperation op2 = rewrites.get(i2);
            if (op2 != null && (op2 instanceof InsertBeforeOp)) {
                InsertBeforeOp iop2 = (InsertBeforeOp) rewrites.get(i2);
                List<? extends InsertBeforeOp> prevInserts = getKindOfOps(rewrites, InsertBeforeOp.class, i2);
                for (InsertBeforeOp prevIop : prevInserts) {
                    if (prevIop.index == iop2.index) {
                        if (InsertAfterOp.class.isInstance(prevIop)) {
                            iop2.text = catOpText(prevIop.text, iop2.text);
                            rewrites.set(prevIop.instructionIndex, null);
                        } else if (InsertBeforeOp.class.isInstance(prevIop)) {
                            iop2.text = catOpText(iop2.text, prevIop.text);
                            rewrites.set(prevIop.instructionIndex, null);
                        }
                    }
                }
                List<? extends ReplaceOp> prevReplaces2 = getKindOfOps(rewrites, ReplaceOp.class, i2);
                for (ReplaceOp rop2 : prevReplaces2) {
                    if (iop2.index == rop2.index) {
                        rop2.text = catOpText(iop2.text, rop2.text);
                        rewrites.set(i2, null);
                    } else if (iop2.index >= rop2.index && iop2.index <= rop2.lastIndex) {
                        throw new IllegalArgumentException("insert op " + iop2 + " within boundaries of previous " + rop2);
                    }
                }
            }
        }
        Map<Integer, RewriteOperation> m = new HashMap<>();
        for (int i3 = 0; i3 < rewrites.size(); i3++) {
            RewriteOperation op3 = rewrites.get(i3);
            if (op3 != null) {
                if (m.get(Integer.valueOf(op3.index)) != null) {
                    throw new Error("should only be one op per index");
                }
                m.put(Integer.valueOf(op3.index), op3);
            }
        }
        return m;
    }

    protected String catOpText(Object a, Object b) {
        String x = a != null ? a.toString() : "";
        String y = b != null ? b.toString() : "";
        return x + y;
    }

    protected <T extends RewriteOperation> List<? extends T> getKindOfOps(List<? extends RewriteOperation> rewrites, Class<T> kind, int before) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < before && i < rewrites.size(); i++) {
            RewriteOperation op = rewrites.get(i);
            if (op != null && kind.isInstance(op)) {
                arrayList.add(kind.cast(op));
            }
        }
        return arrayList;
    }
}
