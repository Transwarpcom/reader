package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Element;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/CombiningEvaluator.class */
abstract class CombiningEvaluator extends Evaluator {
    final ArrayList<Evaluator> evaluators;
    int num;

    CombiningEvaluator() {
        this.num = 0;
        this.evaluators = new ArrayList<>();
    }

    CombiningEvaluator(Collection<Evaluator> evaluators) {
        this();
        this.evaluators.addAll(evaluators);
        updateNumEvaluators();
    }

    @Nullable
    Evaluator rightMostEvaluator() {
        if (this.num > 0) {
            return this.evaluators.get(this.num - 1);
        }
        return null;
    }

    void replaceRightMostEvaluator(Evaluator replacement) {
        this.evaluators.set(this.num - 1, replacement);
    }

    void updateNumEvaluators() {
        this.num = this.evaluators.size();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/CombiningEvaluator$And.class */
    static final class And extends CombiningEvaluator {
        And(Collection<Evaluator> evaluators) {
            super(evaluators);
        }

        And(Evaluator... evaluators) {
            this(Arrays.asList(evaluators));
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element node) {
            for (int i = this.num - 1; i >= 0; i--) {
                Evaluator s = this.evaluators.get(i);
                if (!s.matches(root, node)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return StringUtil.join(this.evaluators, "");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/select/CombiningEvaluator$Or.class */
    static final class Or extends CombiningEvaluator {
        Or(Collection<Evaluator> evaluators) {
            if (this.num > 1) {
                this.evaluators.add(new And(evaluators));
            } else {
                this.evaluators.addAll(evaluators);
            }
            updateNumEvaluators();
        }

        Or(Evaluator... evaluators) {
            this(Arrays.asList(evaluators));
        }

        Or() {
        }

        public void add(Evaluator e) {
            this.evaluators.add(e);
            updateNumEvaluators();
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element root, Element node) {
            for (int i = 0; i < this.num; i++) {
                Evaluator s = this.evaluators.get(i);
                if (s.matches(root, node)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return StringUtil.join(this.evaluators, ", ");
        }
    }
}
