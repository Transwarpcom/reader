package com.fasterxml.jackson.core;

import org.apache.commons.lang3.CharEncoding;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-core-2.9.9.jar:com/fasterxml/jackson/core/JsonEncoding.class */
public enum JsonEncoding {
    UTF8("UTF-8", false, 8),
    UTF16_BE(CharEncoding.UTF_16BE, true, 16),
    UTF16_LE(CharEncoding.UTF_16LE, false, 16),
    UTF32_BE("UTF-32BE", true, 32),
    UTF32_LE("UTF-32LE", false, 32);

    private final String _javaName;
    private final boolean _bigEndian;
    private final int _bits;

    JsonEncoding(String javaName, boolean bigEndian, int bits) {
        this._javaName = javaName;
        this._bigEndian = bigEndian;
        this._bits = bits;
    }

    public String getJavaName() {
        return this._javaName;
    }

    public boolean isBigEndian() {
        return this._bigEndian;
    }

    public int bits() {
        return this._bits;
    }
}
