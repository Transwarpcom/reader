package org.apache.pdfbox.contentstream.operator.graphics;

import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/contentstream/operator/graphics/GraphicsOperatorProcessor.class */
public abstract class GraphicsOperatorProcessor extends OperatorProcessor {
    protected PDFGraphicsStreamEngine context;

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void setContext(PDFStreamEngine context) {
        super.setContext(context);
        this.context = (PDFGraphicsStreamEngine) context;
    }
}
