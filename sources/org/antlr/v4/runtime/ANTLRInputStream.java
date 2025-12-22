package org.antlr.v4.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import org.antlr.v4.runtime.misc.Interval;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/ANTLRInputStream.class */
public class ANTLRInputStream implements CharStream {
    public static final int READ_BUFFER_SIZE = 1024;
    public static final int INITIAL_BUFFER_SIZE = 1024;
    protected char[] data;
    protected int n;
    protected int p;
    public String name;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ANTLRInputStream.class.desiredAssertionStatus();
    }

    public ANTLRInputStream() {
        this.p = 0;
    }

    public ANTLRInputStream(String input) {
        this.p = 0;
        this.data = input.toCharArray();
        this.n = input.length();
    }

    public ANTLRInputStream(char[] data, int numberOfActualCharsInArray) {
        this.p = 0;
        this.data = data;
        this.n = numberOfActualCharsInArray;
    }

    public ANTLRInputStream(Reader r) throws IOException {
        this(r, 1024, 1024);
    }

    public ANTLRInputStream(Reader r, int initialSize) throws IOException {
        this(r, initialSize, 1024);
    }

    public ANTLRInputStream(Reader r, int initialSize, int readChunkSize) throws IOException {
        this.p = 0;
        load(r, initialSize, readChunkSize);
    }

    public ANTLRInputStream(InputStream input) throws IOException {
        this(new InputStreamReader(input), 1024);
    }

    public ANTLRInputStream(InputStream input, int initialSize) throws IOException {
        this(new InputStreamReader(input), initialSize);
    }

    public ANTLRInputStream(InputStream input, int initialSize, int readChunkSize) throws IOException {
        this(new InputStreamReader(input), initialSize, readChunkSize);
    }

    public void load(Reader r, int size, int readChunkSize) throws IOException {
        int numRead;
        if (r == null) {
            return;
        }
        if (size <= 0) {
            size = 1024;
        }
        if (readChunkSize <= 0) {
            readChunkSize = 1024;
        }
        try {
            this.data = new char[size];
            int p = 0;
            do {
                if (p + readChunkSize > this.data.length) {
                    this.data = Arrays.copyOf(this.data, this.data.length * 2);
                }
                numRead = r.read(this.data, p, readChunkSize);
                p += numRead;
            } while (numRead != -1);
            this.n = p + 1;
            r.close();
        } catch (Throwable th) {
            r.close();
            throw th;
        }
    }

    public void reset() {
        this.p = 0;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void consume() {
        if (this.p >= this.n) {
            if (!$assertionsDisabled && LA(1) != -1) {
                throw new AssertionError();
            }
            throw new IllegalStateException("cannot consume EOF");
        }
        if (this.p < this.n) {
            this.p++;
        }
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int LA(int i) {
        if (i == 0) {
            return 0;
        }
        if (i < 0) {
            i++;
            if ((this.p + i) - 1 < 0) {
                return -1;
            }
        }
        if ((this.p + i) - 1 >= this.n) {
            return -1;
        }
        return this.data[(this.p + i) - 1];
    }

    public int LT(int i) {
        return LA(i);
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int index() {
        return this.p;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int size() {
        return this.n;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int mark() {
        return -1;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void release(int marker) {
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void seek(int index) {
        if (index <= this.p) {
            this.p = index;
            return;
        }
        int index2 = Math.min(index, this.n);
        while (this.p < index2) {
            consume();
        }
    }

    @Override // org.antlr.v4.runtime.CharStream
    public String getText(Interval interval) {
        int start = interval.a;
        int stop = interval.b;
        if (stop >= this.n) {
            stop = this.n - 1;
        }
        int count = (stop - start) + 1;
        return start >= this.n ? "" : new String(this.data, start, count);
    }

    @Override // org.antlr.v4.runtime.IntStream
    public String getSourceName() {
        if (this.name == null || this.name.isEmpty()) {
            return IntStream.UNKNOWN_SOURCE_NAME;
        }
        return this.name;
    }

    public String toString() {
        return new String(this.data);
    }
}
