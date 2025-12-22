package org.antlr.v4.runtime;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/Vocabulary.class */
public interface Vocabulary {
    int getMaxTokenType();

    String getLiteralName(int i);

    String getSymbolicName(int i);

    String getDisplayName(int i);
}
