package org.springframework.expression.spel.ast;

import cn.hutool.core.text.StrPool;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/spel/ast/BeanReference.class */
public class BeanReference extends SpelNodeImpl {
    private static final String FACTORY_BEAN_PREFIX = "&";
    private final String beanName;

    public BeanReference(int pos, String beanName) {
        super(pos, new SpelNodeImpl[0]);
        this.beanName = beanName;
    }

    @Override // org.springframework.expression.spel.ast.SpelNodeImpl
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        BeanResolver beanResolver = state.getEvaluationContext().getBeanResolver();
        if (beanResolver == null) {
            throw new SpelEvaluationException(getStartPosition(), SpelMessage.NO_BEAN_RESOLVER_REGISTERED, this.beanName);
        }
        try {
            return new TypedValue(beanResolver.resolve(state.getEvaluationContext(), this.beanName));
        } catch (AccessException ex) {
            throw new SpelEvaluationException(getStartPosition(), ex, SpelMessage.EXCEPTION_DURING_BEAN_RESOLUTION, this.beanName, ex.getMessage());
        }
    }

    @Override // org.springframework.expression.spel.SpelNode
    public String toStringAST() {
        StringBuilder sb = new StringBuilder();
        if (!this.beanName.startsWith("&")) {
            sb.append(StrPool.AT);
        }
        if (!this.beanName.contains(".")) {
            sb.append(this.beanName);
        } else {
            sb.append(OperatorName.SHOW_TEXT_LINE).append(this.beanName).append(OperatorName.SHOW_TEXT_LINE);
        }
        return sb.toString();
    }
}
