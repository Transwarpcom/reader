package org.apache.pdfbox.cos;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/cos/COSNull.class */
public final class COSNull extends COSBase {
    public static final byte[] NULL_BYTES = {110, 117, 108, 108};
    public static final COSNull NULL = new COSNull();

    private COSNull() {
    }

    @Override // org.apache.pdfbox.cos.COSBase
    public Object accept(ICOSVisitor visitor) throws IOException {
        return visitor.visitFromNull(this);
    }

    public void writePDF(OutputStream output) throws IOException {
        output.write(NULL_BYTES);
    }

    public String toString() {
        return "COSNull{}";
    }
}
