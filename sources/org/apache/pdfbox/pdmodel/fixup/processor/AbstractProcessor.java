package org.apache.pdfbox.pdmodel.fixup.processor;

import org.apache.pdfbox.pdmodel.PDDocument;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/fixup/processor/AbstractProcessor.class */
public abstract class AbstractProcessor implements PDDocumentProcessor {
    protected PDDocument document;

    protected AbstractProcessor(PDDocument document) {
        this.document = document;
    }
}
