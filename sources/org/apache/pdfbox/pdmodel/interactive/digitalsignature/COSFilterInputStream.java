package org.apache.pdfbox.pdmodel.interactive.digitalsignature;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.pdfbox.io.IOUtils;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/interactive/digitalsignature/COSFilterInputStream.class */
public class COSFilterInputStream extends FilterInputStream {
    private int[][] ranges;
    private int range;
    private long position;

    public COSFilterInputStream(InputStream in, int[] byteRange) {
        super(in);
        this.position = 0L;
        calculateRanges(byteRange);
    }

    public COSFilterInputStream(byte[] in, int[] byteRange) {
        this(new ByteArrayInputStream(in), byteRange);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if ((this.range == -1 || getRemaining() <= 0) && !nextRange()) {
            return -1;
        }
        int result = super.read();
        this.position++;
        return result;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if ((this.range == -1 || getRemaining() <= 0) && !nextRange()) {
            return -1;
        }
        int bytesRead = super.read(b, off, (int) Math.min(len, getRemaining()));
        this.position += bytesRead;
        return bytesRead;
    }

    public byte[] toByteArray() throws IOException {
        return IOUtils.toByteArray(this);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [int[], int[][]] */
    private void calculateRanges(int[] byteRange) {
        this.ranges = new int[byteRange.length / 2];
        for (int i = 0; i < byteRange.length / 2; i++) {
            this.ranges[i] = new int[]{byteRange[i * 2], byteRange[i * 2] + byteRange[(i * 2) + 1]};
        }
        this.range = -1;
    }

    private long getRemaining() {
        return this.ranges[this.range][1] - this.position;
    }

    private boolean nextRange() throws IOException {
        if (this.range + 1 < this.ranges.length) {
            this.range++;
            while (this.position < this.ranges[this.range][0]) {
                long skipped = super.skip(this.ranges[this.range][0] - this.position);
                if (skipped == 0) {
                    throw new IOException("FilterInputStream.skip() returns 0, range: " + Arrays.toString(this.ranges[this.range]));
                }
                this.position += skipped;
            }
            return true;
        }
        return false;
    }
}
