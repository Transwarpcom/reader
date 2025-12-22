package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/PDFontLike.class */
public interface PDFontLike {
    String getName();

    PDFontDescriptor getFontDescriptor();

    Matrix getFontMatrix();

    BoundingBox getBoundingBox() throws IOException;

    Vector getPositionVector(int i);

    @Deprecated
    float getHeight(int i) throws IOException;

    float getWidth(int i) throws IOException;

    boolean hasExplicitWidth(int i) throws IOException;

    float getWidthFromFont(int i) throws IOException;

    boolean isEmbedded();

    boolean isDamaged();

    float getAverageFontWidth();
}
