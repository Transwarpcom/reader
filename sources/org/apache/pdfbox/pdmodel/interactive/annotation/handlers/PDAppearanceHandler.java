package org.apache.pdfbox.pdmodel.interactive.annotation.handlers;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/interactive/annotation/handlers/PDAppearanceHandler.class */
public interface PDAppearanceHandler {
    void generateAppearanceStreams();

    void generateNormalAppearance();

    void generateRolloverAppearance();

    void generateDownAppearance();
}
