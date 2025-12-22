package org.apache.fontbox.encoding;

import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/encoding/BuiltInEncoding.class */
public class BuiltInEncoding extends Encoding {
    public BuiltInEncoding(Map<Integer, String> codeToName) {
        for (Map.Entry<Integer, String> entry : codeToName.entrySet()) {
            addCharacterEncoding(entry.getKey().intValue(), entry.getValue());
        }
    }
}
