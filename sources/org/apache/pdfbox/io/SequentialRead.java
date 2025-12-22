package org.apache.pdfbox.io;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/io/SequentialRead.class */
public interface SequentialRead extends Closeable {
    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;
}
