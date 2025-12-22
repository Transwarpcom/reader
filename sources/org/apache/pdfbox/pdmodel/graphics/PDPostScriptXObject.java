package org.apache.pdfbox.pdmodel.graphics;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/graphics/PDPostScriptXObject.class */
public class PDPostScriptXObject extends PDXObject {
    public PDPostScriptXObject(COSStream stream) {
        super(stream, COSName.PS);
    }
}
