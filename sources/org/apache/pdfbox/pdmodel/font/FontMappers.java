package org.apache.pdfbox.pdmodel.font;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontMappers.class */
public final class FontMappers {
    private static FontMapper instance;

    private FontMappers() {
    }

    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontMappers$DefaultFontMapper.class */
    private static class DefaultFontMapper {
        private static final FontMapper INSTANCE = new FontMapperImpl();

        private DefaultFontMapper() {
        }
    }

    public static FontMapper instance() {
        if (instance == null) {
            instance = DefaultFontMapper.INSTANCE;
        }
        return instance;
    }

    public static synchronized void set(FontMapper fontMapper) {
        instance = fontMapper;
    }
}
