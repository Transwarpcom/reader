package org.apache.fontbox.cff;

import org.apache.fontbox.encoding.Encoding;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/cff/CFFEncoding.class */
public abstract class CFFEncoding extends Encoding {
    CFFEncoding() {
    }

    public void add(int code, int sid, String name) {
        addCharacterEncoding(code, name);
    }

    protected void add(int code, int sid) {
        addCharacterEncoding(code, CFFStandardString.getName(sid));
    }
}
