package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/ANTLRErrorStrategy.class */
public interface ANTLRErrorStrategy {
    void reset(Parser parser);

    Token recoverInline(Parser parser) throws RecognitionException;

    void recover(Parser parser, RecognitionException recognitionException) throws RecognitionException;

    void sync(Parser parser) throws RecognitionException;

    boolean inErrorRecoveryMode(Parser parser);

    void reportMatch(Parser parser);

    void reportError(Parser parser, RecognitionException recognitionException);
}
