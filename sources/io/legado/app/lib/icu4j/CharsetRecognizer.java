package io.legado.app.lib.icu4j;

/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/lib/icu4j/CharsetRecognizer.class */
abstract class CharsetRecognizer {
    abstract String getName();

    abstract CharsetMatch match(CharsetDetector det);

    CharsetRecognizer() {
    }

    public String getLanguage() {
        return null;
    }
}
