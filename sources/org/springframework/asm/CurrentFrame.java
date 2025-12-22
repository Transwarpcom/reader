package org.springframework.asm;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/asm/CurrentFrame.class */
final class CurrentFrame extends Frame {
    CurrentFrame(Label owner) {
        super(owner);
    }

    @Override // org.springframework.asm.Frame
    void execute(int opcode, int arg, Symbol symbolArg, SymbolTable symbolTable) {
        super.execute(opcode, arg, symbolArg, symbolTable);
        Frame successor = new Frame(null);
        merge(symbolTable, successor, 0);
        copyFrom(successor);
    }
}
