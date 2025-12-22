package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/interactive/digitalsignature/ExternalSigningSupport.class */
public interface ExternalSigningSupport {
    InputStream getContent() throws IOException;

    void setSignature(byte[] bArr) throws IOException;
}
