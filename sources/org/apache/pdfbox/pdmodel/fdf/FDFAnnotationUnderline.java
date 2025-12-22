package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/fdf/FDFAnnotationUnderline.class */
public class FDFAnnotationUnderline extends FDFAnnotationTextMarkup {
    public static final String SUBTYPE = "Underline";

    public FDFAnnotationUnderline() {
        this.annot.setName(COSName.SUBTYPE, "Underline");
    }

    public FDFAnnotationUnderline(COSDictionary a) {
        super(a);
    }

    public FDFAnnotationUnderline(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "Underline");
    }
}
