package org.apache.pdfbox.pdmodel.fixup;

import org.apache.pdfbox.pdmodel.PDDocument;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/fixup/AbstractFixup.class */
public abstract class AbstractFixup implements PDDocumentFixup {
    protected PDDocument document;

    protected AbstractFixup(PDDocument document) {
        this.document = document;
    }
}
