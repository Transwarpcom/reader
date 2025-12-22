package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/fdf/FDFAnnotationSound.class */
public class FDFAnnotationSound extends FDFAnnotation {
    public static final String SUBTYPE = "Sound";

    public FDFAnnotationSound() {
        this.annot.setName(COSName.SUBTYPE, "Sound");
    }

    public FDFAnnotationSound(COSDictionary a) {
        super(a);
    }

    public FDFAnnotationSound(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Sound");
    }
}
