package org.apache.pdfbox.contentstream;

import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.Matrix;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/contentstream/PDContentStream.class */
public interface PDContentStream {
    InputStream getContents() throws IOException;

    PDResources getResources();

    PDRectangle getBBox();

    Matrix getMatrix();
}
