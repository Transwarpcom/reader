package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/Subsetter.class */
interface Subsetter {
    void addToSubset(int i);

    void subset() throws IOException;
}
