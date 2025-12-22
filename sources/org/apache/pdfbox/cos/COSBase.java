package org.apache.pdfbox.cos;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/cos/COSBase.class */
public abstract class COSBase implements COSObjectable {
    private boolean direct;

    public abstract Object accept(ICOSVisitor iCOSVisitor) throws IOException;

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }
}
