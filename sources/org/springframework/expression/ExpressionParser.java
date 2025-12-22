package org.springframework.expression;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/ExpressionParser.class */
public interface ExpressionParser {
    Expression parseExpression(String str) throws ParseException;

    Expression parseExpression(String str, ParserContext parserContext) throws ParseException;
}
