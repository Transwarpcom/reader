package org.springframework.expression.common;

import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.expression.ParserContext;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/common/TemplateParserContext.class */
public class TemplateParserContext implements ParserContext {
    private final String expressionPrefix;
    private final String expressionSuffix;

    public TemplateParserContext() {
        this(StandardBeanExpressionResolver.DEFAULT_EXPRESSION_PREFIX, "}");
    }

    public TemplateParserContext(String expressionPrefix, String expressionSuffix) {
        this.expressionPrefix = expressionPrefix;
        this.expressionSuffix = expressionSuffix;
    }

    @Override // org.springframework.expression.ParserContext
    public final boolean isTemplate() {
        return true;
    }

    @Override // org.springframework.expression.ParserContext
    public final String getExpressionPrefix() {
        return this.expressionPrefix;
    }

    @Override // org.springframework.expression.ParserContext
    public final String getExpressionSuffix() {
        return this.expressionSuffix;
    }
}
