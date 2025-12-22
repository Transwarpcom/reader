package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.JsonPathException;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.filter.ValueNodes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory.class */
public class EvaluatorFactory {
    private static final Map<RelationalOperator, Evaluator> evaluators = new HashMap();

    static {
        evaluators.put(RelationalOperator.EXISTS, new ExistsEvaluator());
        evaluators.put(RelationalOperator.NE, new NotEqualsEvaluator());
        evaluators.put(RelationalOperator.TSNE, new TypeSafeNotEqualsEvaluator());
        evaluators.put(RelationalOperator.EQ, new EqualsEvaluator());
        evaluators.put(RelationalOperator.TSEQ, new TypeSafeEqualsEvaluator());
        evaluators.put(RelationalOperator.LT, new LessThanEvaluator());
        evaluators.put(RelationalOperator.LTE, new LessThanEqualsEvaluator());
        evaluators.put(RelationalOperator.GT, new GreaterThanEvaluator());
        evaluators.put(RelationalOperator.GTE, new GreaterThanEqualsEvaluator());
        evaluators.put(RelationalOperator.REGEX, new RegexpEvaluator());
        evaluators.put(RelationalOperator.SIZE, new SizeEvaluator());
        evaluators.put(RelationalOperator.EMPTY, new EmptyEvaluator());
        evaluators.put(RelationalOperator.IN, new InEvaluator());
        evaluators.put(RelationalOperator.NIN, new NotInEvaluator());
        evaluators.put(RelationalOperator.ALL, new AllEvaluator());
        evaluators.put(RelationalOperator.CONTAINS, new ContainsEvaluator());
        evaluators.put(RelationalOperator.MATCHES, new PredicateMatchEvaluator());
        evaluators.put(RelationalOperator.TYPE, new TypeEvaluator());
        evaluators.put(RelationalOperator.SUBSETOF, new SubsetOfEvaluator());
        evaluators.put(RelationalOperator.ANYOF, new AnyOfEvaluator());
        evaluators.put(RelationalOperator.NONEOF, new NoneOfEvaluator());
    }

    public static Evaluator createEvaluator(RelationalOperator operator) {
        return evaluators.get(operator);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$ExistsEvaluator.class */
    private static class ExistsEvaluator implements Evaluator {
        private ExistsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            if (left.isBooleanNode() || right.isBooleanNode()) {
                return left.asBooleanNode().getBoolean() == right.asBooleanNode().getBoolean();
            }
            throw new JsonPathException("Failed to evaluate exists expression");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$NotEqualsEvaluator.class */
    private static class NotEqualsEvaluator implements Evaluator {
        private NotEqualsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return !((Evaluator) EvaluatorFactory.evaluators.get(RelationalOperator.EQ)).evaluate(left, right, ctx);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$TypeSafeNotEqualsEvaluator.class */
    private static class TypeSafeNotEqualsEvaluator implements Evaluator {
        private TypeSafeNotEqualsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return !((Evaluator) EvaluatorFactory.evaluators.get(RelationalOperator.TSEQ)).evaluate(left, right, ctx);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$EqualsEvaluator.class */
    private static class EqualsEvaluator implements Evaluator {
        private EqualsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            if (left.isJsonNode() && right.isJsonNode()) {
                return left.asJsonNode().equals(right.asJsonNode(), ctx);
            }
            return left.equals(right);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$TypeSafeEqualsEvaluator.class */
    private static class TypeSafeEqualsEvaluator implements Evaluator {
        private TypeSafeEqualsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            if (left.getClass().equals(right.getClass())) {
                return ((Evaluator) EvaluatorFactory.evaluators.get(RelationalOperator.EQ)).evaluate(left, right, ctx);
            }
            return false;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$TypeEvaluator.class */
    private static class TypeEvaluator implements Evaluator {
        private TypeEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return right.asClassNode().getClazz() == left.type(ctx);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$LessThanEvaluator.class */
    private static class LessThanEvaluator implements Evaluator {
        private LessThanEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return (left.isNumberNode() && right.isNumberNode()) ? left.asNumberNode().getNumber().compareTo(right.asNumberNode().getNumber()) < 0 : (left.isStringNode() && right.isStringNode()) ? left.asStringNode().getString().compareTo(right.asStringNode().getString()) < 0 : left.isOffsetDateTimeNode() && right.isOffsetDateTimeNode() && left.asOffsetDateTimeNode().getDate().compareTo(right.asOffsetDateTimeNode().getDate()) < 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$LessThanEqualsEvaluator.class */
    private static class LessThanEqualsEvaluator implements Evaluator {
        private LessThanEqualsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return (left.isNumberNode() && right.isNumberNode()) ? left.asNumberNode().getNumber().compareTo(right.asNumberNode().getNumber()) <= 0 : (left.isStringNode() && right.isStringNode()) ? left.asStringNode().getString().compareTo(right.asStringNode().getString()) <= 0 : left.isOffsetDateTimeNode() && right.isOffsetDateTimeNode() && left.asOffsetDateTimeNode().getDate().compareTo(right.asOffsetDateTimeNode().getDate()) <= 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$GreaterThanEvaluator.class */
    private static class GreaterThanEvaluator implements Evaluator {
        private GreaterThanEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return (left.isNumberNode() && right.isNumberNode()) ? left.asNumberNode().getNumber().compareTo(right.asNumberNode().getNumber()) > 0 : (left.isStringNode() && right.isStringNode()) ? left.asStringNode().getString().compareTo(right.asStringNode().getString()) > 0 : left.isOffsetDateTimeNode() && right.isOffsetDateTimeNode() && left.asOffsetDateTimeNode().getDate().compareTo(right.asOffsetDateTimeNode().getDate()) > 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$GreaterThanEqualsEvaluator.class */
    private static class GreaterThanEqualsEvaluator implements Evaluator {
        private GreaterThanEqualsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return (left.isNumberNode() && right.isNumberNode()) ? left.asNumberNode().getNumber().compareTo(right.asNumberNode().getNumber()) >= 0 : (left.isStringNode() && right.isStringNode()) ? left.asStringNode().getString().compareTo(right.asStringNode().getString()) >= 0 : left.isOffsetDateTimeNode() && right.isOffsetDateTimeNode() && left.asOffsetDateTimeNode().getDate().compareTo(right.asOffsetDateTimeNode().getDate()) >= 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$SizeEvaluator.class */
    private static class SizeEvaluator implements Evaluator {
        private SizeEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            if (!right.isNumberNode()) {
                return false;
            }
            int expectedSize = right.asNumberNode().getNumber().intValue();
            return left.isStringNode() ? left.asStringNode().length() == expectedSize : left.isJsonNode() && left.asJsonNode().length(ctx) == expectedSize;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$EmptyEvaluator.class */
    private static class EmptyEvaluator implements Evaluator {
        private EmptyEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return left.isStringNode() ? left.asStringNode().isEmpty() == right.asBooleanNode().getBoolean() : left.isJsonNode() && left.asJsonNode().isEmpty(ctx) == right.asBooleanNode().getBoolean();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$InEvaluator.class */
    private static class InEvaluator implements Evaluator {
        private InEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            ValueNodes.ValueListNode valueListNode;
            if (right.isJsonNode()) {
                ValueNode vn = right.asJsonNode().asValueListNode(ctx);
                if (vn.isUndefinedNode()) {
                    return false;
                }
                valueListNode = vn.asValueListNode();
            } else {
                valueListNode = right.asValueListNode();
            }
            return valueListNode.contains(left);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$NotInEvaluator.class */
    private static class NotInEvaluator implements Evaluator {
        private NotInEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return !((Evaluator) EvaluatorFactory.evaluators.get(RelationalOperator.IN)).evaluate(left, right, ctx);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$AllEvaluator.class */
    private static class AllEvaluator implements Evaluator {
        private AllEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            ValueNodes.ValueListNode requiredValues = right.asValueListNode();
            if (left.isJsonNode()) {
                ValueNode valueNode = left.asJsonNode().asValueListNode(ctx);
                if (valueNode.isValueListNode()) {
                    ValueNodes.ValueListNode shouldContainAll = valueNode.asValueListNode();
                    Iterator<ValueNode> it = requiredValues.iterator();
                    while (it.hasNext()) {
                        ValueNode required = it.next();
                        if (!shouldContainAll.contains(required)) {
                            return false;
                        }
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$ContainsEvaluator.class */
    private static class ContainsEvaluator implements Evaluator {
        private ContainsEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            if (left.isStringNode() && right.isStringNode()) {
                return left.asStringNode().contains(right.asStringNode().getString());
            }
            if (left.isJsonNode()) {
                ValueNode valueNode = left.asJsonNode().asValueListNode(ctx);
                if (valueNode.isUndefinedNode()) {
                    return false;
                }
                boolean res = valueNode.asValueListNode().contains(right);
                return res;
            }
            return false;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$PredicateMatchEvaluator.class */
    private static class PredicateMatchEvaluator implements Evaluator {
        private PredicateMatchEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            return right.asPredicateNode().getPredicate().apply(ctx);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$RegexpEvaluator.class */
    private static class RegexpEvaluator implements Evaluator {
        private RegexpEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            if (!(left.isPatternNode() ^ right.isPatternNode())) {
                return false;
            }
            if (left.isPatternNode()) {
                return matches(left.asPatternNode(), getInput(right));
            }
            return matches(right.asPatternNode(), getInput(left));
        }

        private boolean matches(ValueNodes.PatternNode patternNode, String inputToMatch) {
            return patternNode.getCompiledPattern().matcher(inputToMatch).matches();
        }

        private String getInput(ValueNode valueNode) {
            String input = "";
            if (valueNode.isStringNode() || valueNode.isNumberNode()) {
                input = valueNode.asStringNode().getString();
            } else if (valueNode.isBooleanNode()) {
                input = valueNode.asBooleanNode().toString();
            }
            return input;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$SubsetOfEvaluator.class */
    private static class SubsetOfEvaluator implements Evaluator {
        private SubsetOfEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            ValueNodes.ValueListNode rightValueListNode;
            ValueNodes.ValueListNode leftValueListNode;
            if (right.isJsonNode()) {
                ValueNode vn = right.asJsonNode().asValueListNode(ctx);
                if (vn.isUndefinedNode()) {
                    return false;
                }
                rightValueListNode = vn.asValueListNode();
            } else {
                rightValueListNode = right.asValueListNode();
            }
            if (left.isJsonNode()) {
                ValueNode vn2 = left.asJsonNode().asValueListNode(ctx);
                if (vn2.isUndefinedNode()) {
                    return false;
                }
                leftValueListNode = vn2.asValueListNode();
            } else {
                leftValueListNode = left.asValueListNode();
            }
            return leftValueListNode.subsetof(rightValueListNode);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$AnyOfEvaluator.class */
    private static class AnyOfEvaluator implements Evaluator {
        private AnyOfEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            ValueNodes.ValueListNode rightValueListNode;
            ValueNodes.ValueListNode leftValueListNode;
            if (right.isJsonNode()) {
                ValueNode vn = right.asJsonNode().asValueListNode(ctx);
                if (vn.isUndefinedNode()) {
                    return false;
                }
                rightValueListNode = vn.asValueListNode();
            } else {
                rightValueListNode = right.asValueListNode();
            }
            if (left.isJsonNode()) {
                ValueNode vn2 = left.asJsonNode().asValueListNode(ctx);
                if (vn2.isUndefinedNode()) {
                    return false;
                }
                leftValueListNode = vn2.asValueListNode();
            } else {
                leftValueListNode = left.asValueListNode();
            }
            Iterator<ValueNode> it = leftValueListNode.iterator();
            while (it.hasNext()) {
                ValueNode leftValueNode = it.next();
                Iterator<ValueNode> it2 = rightValueListNode.iterator();
                while (it2.hasNext()) {
                    ValueNode rightValueNode = it2.next();
                    if (leftValueNode.equals(rightValueNode)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/EvaluatorFactory$NoneOfEvaluator.class */
    private static class NoneOfEvaluator implements Evaluator {
        private NoneOfEvaluator() {
        }

        @Override // com.jayway.jsonpath.internal.filter.Evaluator
        public boolean evaluate(ValueNode left, ValueNode right, Predicate.PredicateContext ctx) {
            ValueNodes.ValueListNode rightValueListNode;
            ValueNodes.ValueListNode leftValueListNode;
            if (right.isJsonNode()) {
                ValueNode vn = right.asJsonNode().asValueListNode(ctx);
                if (vn.isUndefinedNode()) {
                    return false;
                }
                rightValueListNode = vn.asValueListNode();
            } else {
                rightValueListNode = right.asValueListNode();
            }
            if (left.isJsonNode()) {
                ValueNode vn2 = left.asJsonNode().asValueListNode(ctx);
                if (vn2.isUndefinedNode()) {
                    return false;
                }
                leftValueListNode = vn2.asValueListNode();
            } else {
                leftValueListNode = left.asValueListNode();
            }
            Iterator<ValueNode> it = leftValueListNode.iterator();
            while (it.hasNext()) {
                ValueNode leftValueNode = it.next();
                Iterator<ValueNode> it2 = rightValueListNode.iterator();
                while (it2.hasNext()) {
                    ValueNode rightValueNode = it2.next();
                    if (leftValueNode.equals(rightValueNode)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
