package org.apache.pdfbox.cos;

import java.util.Collections;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/cos/UnmodifiableCOSDictionary.class */
final class UnmodifiableCOSDictionary extends COSDictionary {
    UnmodifiableCOSDictionary(COSDictionary dict) {
        this.items = Collections.unmodifiableMap(dict.items);
    }

    @Override // org.apache.pdfbox.cos.COSDictionary
    public void mergeInto(COSDictionary dic) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.cos.COSDictionary, org.apache.pdfbox.cos.COSUpdateInfo
    public void setNeedToBeUpdated(boolean flag) {
        throw new UnsupportedOperationException();
    }
}
