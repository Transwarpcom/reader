package org.apache.pdfbox.pdmodel.font;

import org.apache.fontbox.FontBoxFont;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontMapping.class */
public class FontMapping<T extends FontBoxFont> {
    private final T font;
    private final boolean isFallback;

    public FontMapping(T font, boolean isFallback) {
        this.font = font;
        this.isFallback = isFallback;
    }

    public T getFont() {
        return this.font;
    }

    public boolean isFallback() {
        return this.isFallback;
    }
}
