package org.apache.fontbox.ttf;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/ttf/CmapLookup.class */
public interface CmapLookup {
    int getGlyphId(int i);

    List<Integer> getCharCodes(int i);
}
