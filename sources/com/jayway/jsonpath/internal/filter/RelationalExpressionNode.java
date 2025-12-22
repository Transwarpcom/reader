package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/RelationalExpressionNode.class */
public class RelationalExpressionNode extends ExpressionNode {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) RelationalExpressionNode.class);
    private final ValueNode left;
    private final RelationalOperator relationalOperator;
    private final ValueNode right;

    public RelationalExpressionNode(ValueNode left, RelationalOperator relationalOperator, ValueNode right) {
        this.left = left;
        this.relationalOperator = relationalOperator;
        this.right = right;
        logger.trace("ExpressionNode {}", toString());
    }

    public String toString() {
        if (this.relationalOperator == RelationalOperator.EXISTS) {
            return this.left.toString();
        }
        return this.left.toString() + " " + this.relationalOperator.toString() + " " + this.right.toString();
    }

    @Override // com.jayway.jsonpath.Predicate
    public boolean apply(Predicate.PredicateContext ctx) {
        ValueNode l = this.left;
        ValueNode r = this.right;
        if (this.left.isPathNode()) {
            l = this.left.asPathNode().evaluate(ctx);
        }
        if (this.right.isPathNode()) {
            r = this.right.asPathNode().evaluate(ctx);
        }
        Evaluator evaluator = EvaluatorFactory.createEvaluator(this.relationalOperator);
        if (evaluator != null) {
            return evaluator.evaluate(l, r, ctx);
        }
        return false;
    }
}
