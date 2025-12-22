package org.apache.pdfbox.io;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/io/RandomAccessOutputStream.class */
public class RandomAccessOutputStream extends OutputStream {
    private final RandomAccessWrite writer;

    public RandomAccessOutputStream(RandomAccessWrite writer) {
        this.writer = writer;
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int offset, int length) throws IOException {
        this.writer.write(b, offset, length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        this.writer.write(b);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.writer.write(b);
    }
}
