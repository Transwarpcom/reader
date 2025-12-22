package org.antlr.v4.runtime.tree.pattern;

import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/tree/pattern/TextChunk.class */
class TextChunk extends Chunk {
    private final String text;

    public TextChunk(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null");
        }
        this.text = text;
    }

    public final String getText() {
        return this.text;
    }

    public String toString() {
        return OperatorName.SHOW_TEXT_LINE + this.text + OperatorName.SHOW_TEXT_LINE;
    }
}
