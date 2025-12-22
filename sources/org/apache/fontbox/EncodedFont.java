package org.apache.fontbox;

import java.io.IOException;
import org.apache.fontbox.encoding.Encoding;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/EncodedFont.class */
public interface EncodedFont {
    Encoding getEncoding() throws IOException;
}
