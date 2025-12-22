package org.jsoup.internal;

import io.vertx.ext.web.handler.StaticHandler;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import org.jsoup.helper.Validate;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/internal/ConstrainableInputStream.class */
public final class ConstrainableInputStream extends BufferedInputStream {
    private static final int DefaultSize = 32768;
    private final boolean capped;
    private final int maxSize;
    private long startTime;
    private long timeout;
    private int remaining;
    private boolean interrupted;

    private ConstrainableInputStream(InputStream in, int bufferSize, int maxSize) {
        super(in, bufferSize);
        this.timeout = 0L;
        Validate.isTrue(maxSize >= 0);
        this.maxSize = maxSize;
        this.remaining = maxSize;
        this.capped = maxSize != 0;
        this.startTime = System.nanoTime();
    }

    public static ConstrainableInputStream wrap(InputStream in, int bufferSize, int maxSize) {
        if (in instanceof ConstrainableInputStream) {
            return (ConstrainableInputStream) in;
        }
        return new ConstrainableInputStream(in, bufferSize, maxSize);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.interrupted) {
            return -1;
        }
        if (this.capped && this.remaining <= 0) {
            return -1;
        }
        if (Thread.interrupted()) {
            this.interrupted = true;
            return -1;
        }
        if (expired()) {
            throw new SocketTimeoutException("Read timeout");
        }
        if (this.capped && len > this.remaining) {
            len = this.remaining;
        }
        try {
            int read = super.read(b, off, len);
            this.remaining -= read;
            return read;
        } catch (SocketTimeoutException e) {
            return 0;
        }
    }

    public ByteBuffer readToByteBuffer(int max) throws IOException {
        Validate.isTrue(max >= 0, "maxSize must be 0 (unlimited) or larger");
        boolean localCapped = max > 0;
        int bufferSize = (!localCapped || max >= 32768) ? 32768 : max;
        byte[] readBuffer = new byte[bufferSize];
        ByteArrayOutputStream outStream = new ByteArrayOutputStream(bufferSize);
        int remaining = max;
        while (true) {
            int read = read(readBuffer);
            if (read == -1) {
                break;
            }
            if (localCapped) {
                if (read >= remaining) {
                    outStream.write(readBuffer, 0, remaining);
                    break;
                }
                remaining -= read;
            }
            outStream.write(readBuffer, 0, read);
        }
        return ByteBuffer.wrap(outStream.toByteArray());
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        super.reset();
        this.remaining = this.maxSize - this.markpos;
    }

    public ConstrainableInputStream timeout(long startTimeNanos, long timeoutMillis) {
        this.startTime = startTimeNanos;
        this.timeout = timeoutMillis * StaticHandler.DEFAULT_MAX_AVG_SERVE_TIME_NS;
        return this;
    }

    private boolean expired() {
        if (this.timeout == 0) {
            return false;
        }
        long now = System.nanoTime();
        long dur = now - this.startTime;
        return dur > this.timeout;
    }
}
