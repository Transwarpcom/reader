package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.fontbox.cmap.CMap;
import org.apache.fontbox.cmap.CMapParser;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/CMapManager.class */
final class CMapManager {
    private static final Map<String, CMap> CMAP_CACHE = new ConcurrentHashMap();

    private CMapManager() {
    }

    public static CMap getPredefinedCMap(String cMapName) throws IOException {
        CMap cmap = CMAP_CACHE.get(cMapName);
        if (cmap != null) {
            return cmap;
        }
        CMap targetCmap = new CMapParser().parsePredefined(cMapName);
        CMAP_CACHE.put(targetCmap.getName(), targetCmap);
        return targetCmap;
    }

    public static CMap parseCMap(InputStream cMapStream) throws IOException, NumberFormatException {
        CMap targetCmap = null;
        if (cMapStream != null) {
            targetCmap = new CMapParser(true).parse(cMapStream);
        }
        return targetCmap;
    }
}
