package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.Predicate;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/ExpressionNode.class */
public abstract class ExpressionNode implements Predicate {
    public static ExpressionNode createExpressionNode(ExpressionNode right, LogicalOperator operator, ExpressionNode left) {
        if (operator == LogicalOperator.AND) {
            if ((right instanceof LogicalExpressionNode) && ((LogicalExpressionNode) right).getOperator() == LogicalOperator.AND) {
                LogicalExpressionNode len = (LogicalExpressionNode) right;
                return len.append(left);
            }
            return LogicalExpressionNode.createLogicalAnd(left, right);
        }
        if ((right instanceof LogicalExpressionNode) && ((LogicalExpressionNode) right).getOperator() == LogicalOperator.OR) {
            LogicalExpressionNode len2 = (LogicalExpressionNode) right;
            return len2.append(left);
        }
        return LogicalExpressionNode.createLogicalOr(left, right);
    }
}
