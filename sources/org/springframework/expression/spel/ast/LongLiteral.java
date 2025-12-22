package org.springframework.expression.spel.ast;

import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.asm.MethodVisitor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.CodeFlow;

/* loaded from: reader.jar:BOOT-INF/lib/spring-expression-5.1.8.RELEASE.jar:org/springframework/expression/spel/ast/LongLiteral.class */
public class LongLiteral extends Literal {
    private final TypedValue value;

    public LongLiteral(String payload, int pos, long value) {
        super(payload, pos);
        this.value = new TypedValue(Long.valueOf(value));
        this.exitTypeDescriptor = OperatorName.SET_LINE_CAPSTYLE;
    }

    @Override // org.springframework.expression.spel.ast.Literal
    public TypedValue getLiteralValue() {
        return this.value;
    }

    @Override // org.springframework.expression.spel.ast.SpelNodeImpl
    public boolean isCompilable() {
        return true;
    }

    @Override // org.springframework.expression.spel.ast.SpelNodeImpl
    public void generateCode(MethodVisitor mv, CodeFlow cf) {
        mv.visitLdcInsn(this.value.getValue());
        cf.pushDescriptor(this.exitTypeDescriptor);
    }
}
