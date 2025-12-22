package org.antlr.v4.runtime.atn;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.MurmurHash;
import org.antlr.v4.runtime.misc.Utils;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SemanticContext.class */
public abstract class SemanticContext {
    public static final SemanticContext NONE = new Predicate();

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SemanticContext$Operator.class */
    public static abstract class Operator extends SemanticContext {
        public abstract Collection<SemanticContext> getOperands();
    }

    public abstract boolean eval(Recognizer<?, ?> recognizer, RuleContext ruleContext);

    public SemanticContext evalPrecedence(Recognizer<?, ?> parser, RuleContext parserCallStack) {
        return this;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SemanticContext$Predicate.class */
    public static class Predicate extends SemanticContext {
        public final int ruleIndex;
        public final int predIndex;
        public final boolean isCtxDependent;

        protected Predicate() {
            this.ruleIndex = -1;
            this.predIndex = -1;
            this.isCtxDependent = false;
        }

        public Predicate(int ruleIndex, int predIndex, boolean isCtxDependent) {
            this.ruleIndex = ruleIndex;
            this.predIndex = predIndex;
            this.isCtxDependent = isCtxDependent;
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext
        public boolean eval(Recognizer<?, ?> parser, RuleContext parserCallStack) {
            RuleContext localctx = this.isCtxDependent ? parserCallStack : null;
            return parser.sempred(localctx, this.ruleIndex, this.predIndex);
        }

        public int hashCode() {
            int hashCode = MurmurHash.initialize();
            return MurmurHash.finish(MurmurHash.update(MurmurHash.update(MurmurHash.update(hashCode, this.ruleIndex), this.predIndex), this.isCtxDependent ? 1 : 0), 3);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Predicate)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Predicate p = (Predicate) obj;
            return this.ruleIndex == p.ruleIndex && this.predIndex == p.predIndex && this.isCtxDependent == p.isCtxDependent;
        }

        public String toString() {
            return StrPool.DELIM_START + this.ruleIndex + ":" + this.predIndex + "}?";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SemanticContext$PrecedencePredicate.class */
    public static class PrecedencePredicate extends SemanticContext implements Comparable<PrecedencePredicate> {
        public final int precedence;

        protected PrecedencePredicate() {
            this.precedence = 0;
        }

        public PrecedencePredicate(int precedence) {
            this.precedence = precedence;
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext
        public boolean eval(Recognizer<?, ?> parser, RuleContext parserCallStack) {
            return parser.precpred(parserCallStack, this.precedence);
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext
        public SemanticContext evalPrecedence(Recognizer<?, ?> parser, RuleContext parserCallStack) {
            if (parser.precpred(parserCallStack, this.precedence)) {
                return SemanticContext.NONE;
            }
            return null;
        }

        @Override // java.lang.Comparable
        public int compareTo(PrecedencePredicate o) {
            return this.precedence - o.precedence;
        }

        public int hashCode() {
            int hashCode = (31 * 1) + this.precedence;
            return hashCode;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PrecedencePredicate)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            PrecedencePredicate other = (PrecedencePredicate) obj;
            return this.precedence == other.precedence;
        }

        public String toString() {
            return StrPool.DELIM_START + this.precedence + ">=prec}?";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SemanticContext$AND.class */
    public static class AND extends Operator {
        public final SemanticContext[] opnds;

        public AND(SemanticContext a, SemanticContext b) {
            Set<SemanticContext> operands = new HashSet<>();
            if (a instanceof AND) {
                operands.addAll(Arrays.asList(((AND) a).opnds));
            } else {
                operands.add(a);
            }
            if (b instanceof AND) {
                operands.addAll(Arrays.asList(((AND) b).opnds));
            } else {
                operands.add(b);
            }
            List<PrecedencePredicate> precedencePredicates = SemanticContext.filterPrecedencePredicates(operands);
            if (!precedencePredicates.isEmpty()) {
                PrecedencePredicate reduced = (PrecedencePredicate) Collections.min(precedencePredicates);
                operands.add(reduced);
            }
            this.opnds = (SemanticContext[]) operands.toArray(new SemanticContext[operands.size()]);
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext.Operator
        public Collection<SemanticContext> getOperands() {
            return Arrays.asList(this.opnds);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AND)) {
                return false;
            }
            AND other = (AND) obj;
            return Arrays.equals(this.opnds, other.opnds);
        }

        public int hashCode() {
            return MurmurHash.hashCode(this.opnds, AND.class.hashCode());
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext
        public boolean eval(Recognizer<?, ?> parser, RuleContext parserCallStack) {
            SemanticContext[] arr$ = this.opnds;
            for (SemanticContext opnd : arr$) {
                if (!opnd.eval(parser, parserCallStack)) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext
        public SemanticContext evalPrecedence(Recognizer<?, ?> parser, RuleContext parserCallStack) {
            boolean differs = false;
            List<SemanticContext> operands = new ArrayList<>();
            SemanticContext[] arr$ = this.opnds;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                SemanticContext context = arr$[i$];
                SemanticContext evaluated = context.evalPrecedence(parser, parserCallStack);
                differs |= evaluated != context;
                if (evaluated == null) {
                    return null;
                }
                if (evaluated != NONE) {
                    operands.add(evaluated);
                }
            }
            if (!differs) {
                return this;
            }
            if (operands.isEmpty()) {
                return NONE;
            }
            SemanticContext result = operands.get(0);
            for (int i = 1; i < operands.size(); i++) {
                result = SemanticContext.and(result, operands.get(i));
            }
            return result;
        }

        public String toString() {
            return Utils.join(Arrays.asList(this.opnds).iterator(), "&&");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/atn/SemanticContext$OR.class */
    public static class OR extends Operator {
        public final SemanticContext[] opnds;

        public OR(SemanticContext a, SemanticContext b) {
            Set<SemanticContext> operands = new HashSet<>();
            if (a instanceof OR) {
                operands.addAll(Arrays.asList(((OR) a).opnds));
            } else {
                operands.add(a);
            }
            if (b instanceof OR) {
                operands.addAll(Arrays.asList(((OR) b).opnds));
            } else {
                operands.add(b);
            }
            List<PrecedencePredicate> precedencePredicates = SemanticContext.filterPrecedencePredicates(operands);
            if (!precedencePredicates.isEmpty()) {
                PrecedencePredicate reduced = (PrecedencePredicate) Collections.max(precedencePredicates);
                operands.add(reduced);
            }
            this.opnds = (SemanticContext[]) operands.toArray(new SemanticContext[operands.size()]);
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext.Operator
        public Collection<SemanticContext> getOperands() {
            return Arrays.asList(this.opnds);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OR)) {
                return false;
            }
            OR other = (OR) obj;
            return Arrays.equals(this.opnds, other.opnds);
        }

        public int hashCode() {
            return MurmurHash.hashCode(this.opnds, OR.class.hashCode());
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext
        public boolean eval(Recognizer<?, ?> parser, RuleContext parserCallStack) {
            SemanticContext[] arr$ = this.opnds;
            for (SemanticContext opnd : arr$) {
                if (opnd.eval(parser, parserCallStack)) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.antlr.v4.runtime.atn.SemanticContext
        public SemanticContext evalPrecedence(Recognizer<?, ?> parser, RuleContext parserCallStack) {
            boolean differs = false;
            List<SemanticContext> operands = new ArrayList<>();
            SemanticContext[] arr$ = this.opnds;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$++) {
                SemanticContext context = arr$[i$];
                SemanticContext evaluated = context.evalPrecedence(parser, parserCallStack);
                differs |= evaluated != context;
                if (evaluated == NONE) {
                    return NONE;
                }
                if (evaluated != null) {
                    operands.add(evaluated);
                }
            }
            if (!differs) {
                return this;
            }
            if (operands.isEmpty()) {
                return null;
            }
            SemanticContext result = operands.get(0);
            for (int i = 1; i < operands.size(); i++) {
                result = SemanticContext.or(result, operands.get(i));
            }
            return result;
        }

        public String toString() {
            return Utils.join(Arrays.asList(this.opnds).iterator(), "||");
        }
    }

    public static SemanticContext and(SemanticContext a, SemanticContext b) {
        if (a == null || a == NONE) {
            return b;
        }
        if (b == null || b == NONE) {
            return a;
        }
        AND result = new AND(a, b);
        if (result.opnds.length == 1) {
            return result.opnds[0];
        }
        return result;
    }

    public static SemanticContext or(SemanticContext a, SemanticContext b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a == NONE || b == NONE) {
            return NONE;
        }
        OR result = new OR(a, b);
        if (result.opnds.length == 1) {
            return result.opnds[0];
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<PrecedencePredicate> filterPrecedencePredicates(Collection<? extends SemanticContext> collection) {
        ArrayList<PrecedencePredicate> result = null;
        Iterator<? extends SemanticContext> iterator = collection.iterator();
        while (iterator.hasNext()) {
            SemanticContext context = iterator.next();
            if (context instanceof PrecedencePredicate) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add((PrecedencePredicate) context);
                iterator.remove();
            }
        }
        if (result == null) {
            return Collections.emptyList();
        }
        return result;
    }
}
