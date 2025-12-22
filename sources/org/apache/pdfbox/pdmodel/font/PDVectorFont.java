package org.apache.pdfbox.pdmodel.font;

import java.awt.geom.GeneralPath;
import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/PDVectorFont.class */
public interface PDVectorFont {
    GeneralPath getPath(int i) throws IOException;

    boolean hasGlyph(int i) throws IOException;
}
