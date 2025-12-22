package org.antlr.v4.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.antlr.v4.runtime.misc.Interval;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/UnbufferedCharStream.class */
public class UnbufferedCharStream implements CharStream {
    protected int[] data;
    protected int n;
    protected int p;
    protected int numMarkers;
    protected int lastChar;
    protected int lastCharBufferStart;
    protected int currentCharIndex;
    protected Reader input;
    public String name;

    public UnbufferedCharStream() {
        this(256);
    }

    public UnbufferedCharStream(int bufferSize) {
        this.p = 0;
        this.numMarkers = 0;
        this.lastChar = -1;
        this.currentCharIndex = 0;
        this.n = 0;
        this.data = new int[bufferSize];
    }

    public UnbufferedCharStream(InputStream input) {
        this(input, 256);
    }

    public UnbufferedCharStream(Reader input) {
        this(input, 256);
    }

    public UnbufferedCharStream(InputStream input, int bufferSize) {
        this(input, bufferSize, StandardCharsets.UTF_8);
    }

    public UnbufferedCharStream(InputStream input, int bufferSize, Charset charset) {
        this(bufferSize);
        this.input = new InputStreamReader(input, charset);
        fill(1);
    }

    public UnbufferedCharStream(Reader input, int bufferSize) {
        this(bufferSize);
        this.input = input;
        fill(1);
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void consume() {
        if (LA(1) == -1) {
            throw new IllegalStateException("cannot consume EOF");
        }
        this.lastChar = this.data[this.p];
        if (this.p == this.n - 1 && this.numMarkers == 0) {
            this.n = 0;
            this.p = -1;
            this.lastCharBufferStart = this.lastChar;
        }
        this.p++;
        this.currentCharIndex++;
        sync(1);
    }

    protected void sync(int want) {
        int need = (((this.p + want) - 1) - this.n) + 1;
        if (need > 0) {
            fill(need);
        }
    }

    protected int fill(int n) {
        for (int i = 0; i < n; i++) {
            if (this.n > 0 && this.data[this.n - 1] == -1) {
                return i;
            }
            try {
                int c = nextChar();
                if (c > 65535 || c == -1) {
                    add(c);
                } else {
                    char ch2 = (char) c;
                    if (Character.isLowSurrogate(ch2)) {
                        throw new RuntimeException("Invalid UTF-16 (low surrogate with no preceding high surrogate)");
                    }
                    if (Character.isHighSurrogate(ch2)) {
                        int lowSurrogate = nextChar();
                        if (lowSurrogate > 65535) {
                            throw new RuntimeException("Invalid UTF-16 (high surrogate followed by code point > U+FFFF");
                        }
                        if (lowSurrogate == -1) {
                            throw new RuntimeException("Invalid UTF-16 (dangling high surrogate at end of file)");
                        }
                        char lowSurrogateChar = (char) lowSurrogate;
                        if (Character.isLowSurrogate(lowSurrogateChar)) {
                            add(Character.toCodePoint(ch2, lowSurrogateChar));
                        } else {
                            throw new RuntimeException("Invalid UTF-16 (dangling high surrogate");
                        }
                    } else {
                        add(c);
                    }
                }
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
        return n;
    }

    protected int nextChar() throws IOException {
        return this.input.read();
    }

    protected void add(int c) {
        if (this.n >= this.data.length) {
            this.data = Arrays.copyOf(this.data, this.data.length * 2);
        }
        int[] iArr = this.data;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = c;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int LA(int i) {
        if (i == -1) {
            return this.lastChar;
        }
        sync(i);
        int index = (this.p + i) - 1;
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index >= this.n) {
            return -1;
        }
        return this.data[index];
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int mark() {
        if (this.numMarkers == 0) {
            this.lastCharBufferStart = this.lastChar;
        }
        int mark = (-this.numMarkers) - 1;
        this.numMarkers++;
        return mark;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void release(int marker) {
        int expectedMark = -this.numMarkers;
        if (marker != expectedMark) {
            throw new IllegalStateException("release() called with an invalid marker.");
        }
        this.numMarkers--;
        if (this.numMarkers == 0 && this.p > 0) {
            System.arraycopy(this.data, this.p, this.data, 0, this.n - this.p);
            this.n -= this.p;
            this.p = 0;
            this.lastCharBufferStart = this.lastChar;
        }
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int index() {
        return this.currentCharIndex;
    }

    @Override // org.antlr.v4.runtime.IntStream
    public void seek(int index) {
        if (index == this.currentCharIndex) {
            return;
        }
        if (index > this.currentCharIndex) {
            sync(index - this.currentCharIndex);
            index = Math.min(index, (getBufferStartIndex() + this.n) - 1);
        }
        int i = index - getBufferStartIndex();
        if (i < 0) {
            throw new IllegalArgumentException("cannot seek to negative index " + index);
        }
        if (i >= this.n) {
            throw new UnsupportedOperationException("seek to index outside buffer: " + index + " not in " + getBufferStartIndex() + ".." + (getBufferStartIndex() + this.n));
        }
        this.p = i;
        this.currentCharIndex = index;
        if (this.p == 0) {
            this.lastChar = this.lastCharBufferStart;
        } else {
            this.lastChar = this.data[this.p - 1];
        }
    }

    @Override // org.antlr.v4.runtime.IntStream
    public int size() {
        throw new UnsupportedOperationException("Unbuffered stream cannot know its size");
    }

    @Override // org.antlr.v4.runtime.IntStream
    public String getSourceName() {
        if (this.name == null || this.name.isEmpty()) {
            return IntStream.UNKNOWN_SOURCE_NAME;
        }
        return this.name;
    }

    @Override // org.antlr.v4.runtime.CharStream
    public String getText(Interval interval) {
        if (interval.a < 0 || interval.b < interval.a - 1) {
            throw new IllegalArgumentException("invalid interval");
        }
        int bufferStartIndex = getBufferStartIndex();
        if (this.n > 0 && this.data[this.n - 1] == 65535 && interval.a + interval.length() > bufferStartIndex + this.n) {
            throw new IllegalArgumentException("the interval extends past the end of the stream");
        }
        if (interval.a < bufferStartIndex || interval.b >= bufferStartIndex + this.n) {
            throw new UnsupportedOperationException("interval " + interval + " outside buffer: " + bufferStartIndex + ".." + ((bufferStartIndex + this.n) - 1));
        }
        int i = interval.a - bufferStartIndex;
        return new String(this.data, i, interval.length());
    }

    protected final int getBufferStartIndex() {
        return this.currentCharIndex - this.p;
    }
}
