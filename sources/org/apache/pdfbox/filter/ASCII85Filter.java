package org.apache.pdfbox.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.io.IOUtils;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/filter/ASCII85Filter.class */
final class ASCII85Filter extends Filter {
    ASCII85Filter() {
    }

    @Override // org.apache.pdfbox.filter.Filter
    public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
        ASCII85InputStream is = null;
        try {
            is = new ASCII85InputStream(encoded);
            IOUtils.copy(is, decoded);
            decoded.flush();
            IOUtils.closeQuietly(is);
            return new DecodeResult(parameters);
        } catch (Throwable th) {
            IOUtils.closeQuietly(is);
            throw th;
        }
    }

    @Override // org.apache.pdfbox.filter.Filter
    protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
        ASCII85OutputStream os = new ASCII85OutputStream(encoded);
        IOUtils.copy(input, os);
        os.close();
        encoded.flush();
    }
}
