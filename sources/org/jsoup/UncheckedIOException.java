package org.jsoup;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/UncheckedIOException.class */
public class UncheckedIOException extends RuntimeException {
    public UncheckedIOException(IOException cause) {
        super(cause);
    }

    public UncheckedIOException(String message) {
        super(new IOException(message));
    }

    public IOException ioException() {
        return (IOException) getCause();
    }
}
