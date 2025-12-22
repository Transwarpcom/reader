package org.apache.pdfbox.rendering;

import java.awt.geom.GeneralPath;
import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/rendering/Glyph2D.class */
interface Glyph2D {
    GeneralPath getPathForCharacterCode(int i) throws IOException;

    void dispose();
}
