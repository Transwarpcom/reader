package org.apache.pdfbox.pdmodel.fdf;

import java.io.IOException;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.w3c.dom.Element;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/fdf/FDFAnnotationStrikeOut.class */
public class FDFAnnotationStrikeOut extends FDFAnnotationTextMarkup {
    public static final String SUBTYPE = "StrikeOut";

    public FDFAnnotationStrikeOut() {
        this.annot.setName(COSName.SUBTYPE, "StrikeOut");
    }

    public FDFAnnotationStrikeOut(COSDictionary a) {
        super(a);
    }

    public FDFAnnotationStrikeOut(Element element) throws IOException {
        super(element);
        this.annot.setName(COSName.SUBTYPE, "StrikeOut");
    }
}
