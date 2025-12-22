package org.jsoup;

import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/UnsupportedMimeTypeException.class */
public class UnsupportedMimeTypeException extends IOException {
    private final String mimeType;
    private final String url;

    public UnsupportedMimeTypeException(String message, String mimeType, String url) {
        super(message);
        this.mimeType = mimeType;
        this.url = url;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + ". Mimetype=" + this.mimeType + ", URL=" + this.url;
    }
}
