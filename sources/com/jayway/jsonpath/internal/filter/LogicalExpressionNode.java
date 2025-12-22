package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.internal.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/LogicalExpressionNode.class */
public class LogicalExpressionNode extends ExpressionNode {
    protected List<ExpressionNode> chain = new ArrayList();
    private final LogicalOperator operator;

    public static ExpressionNode createLogicalNot(ExpressionNode op) {
        return new LogicalExpressionNode(op, LogicalOperator.NOT, null);
    }

    public static LogicalExpressionNode createLogicalOr(ExpressionNode left, ExpressionNode right) {
        return new LogicalExpressionNode(left, LogicalOperator.OR, right);
    }

    public static LogicalExpressionNode createLogicalOr(Collection<ExpressionNode> operands) {
        return new LogicalExpressionNode(LogicalOperator.OR, operands);
    }

    public static LogicalExpressionNode createLogicalAnd(ExpressionNode left, ExpressionNode right) {
        return new LogicalExpressionNode(left, LogicalOperator.AND, right);
    }

    public static LogicalExpressionNode createLogicalAnd(Collection<ExpressionNode> operands) {
        return new LogicalExpressionNode(LogicalOperator.AND, operands);
    }

    private LogicalExpressionNode(ExpressionNode left, LogicalOperator operator, ExpressionNode right) {
        this.chain.add(left);
        this.chain.add(right);
        this.operator = operator;
    }

    private LogicalExpressionNode(LogicalOperator operator, Collection<ExpressionNode> operands) {
        this.chain.addAll(operands);
        this.operator = operator;
    }

    public LogicalExpressionNode and(LogicalExpressionNode other) {
        return createLogicalAnd(this, other);
    }

    public LogicalExpressionNode or(LogicalExpressionNode other) {
        return createLogicalOr(this, other);
    }

    public LogicalOperator getOperator() {
        return this.operator;
    }

    public LogicalExpressionNode append(ExpressionNode expressionNode) {
        this.chain.add(0, expressionNode);
        return this;
    }

    public String toString() {
        return "(" + Utils.join(" " + this.operator.getOperatorString() + " ", this.chain) + ")";
    }

    @Override // com.jayway.jsonpath.Predicate
    public boolean apply(Predicate.PredicateContext ctx) {
        if (this.operator == LogicalOperator.OR) {
            for (ExpressionNode expression : this.chain) {
                if (expression.apply(ctx)) {
                    return true;
                }
            }
            return false;
        }
        if (this.operator == LogicalOperator.AND) {
            for (ExpressionNode expression2 : this.chain) {
                if (!expression2.apply(ctx)) {
                    return false;
                }
            }
            return true;
        }
        ExpressionNode expression3 = this.chain.get(0);
        return !expression3.apply(ctx);
    }
}
