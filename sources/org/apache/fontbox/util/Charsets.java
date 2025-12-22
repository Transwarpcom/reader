package org.apache.fontbox.util;

import java.nio.charset.Charset;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/util/Charsets.class */
public final class Charsets {
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_16 = Charset.forName(CharEncoding.UTF_16);
    public static final Charset UTF_16BE = Charset.forName(CharEncoding.UTF_16BE);
    public static final Charset US_ASCII = Charset.forName(CharEncoding.US_ASCII);
    public static final Charset ISO_10646 = Charset.forName("ISO-10646-UCS-2");

    private Charsets() {
    }
}
