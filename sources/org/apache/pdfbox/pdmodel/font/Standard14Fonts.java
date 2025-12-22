package org.apache.pdfbox.pdmodel.font;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.fontbox.afm.AFMParser;
import org.apache.fontbox.afm.FontMetrics;
import org.mozilla.javascript.NativeSymbol;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/Standard14Fonts.class */
final class Standard14Fonts {
    private static final Map<String, String> ALIASES = new HashMap(38);
    private static final Map<String, FontMetrics> FONTS = new HashMap(14);

    static {
        mapName("Courier-Bold");
        mapName("Courier-BoldOblique");
        mapName("Courier");
        mapName("Courier-Oblique");
        mapName("Helvetica");
        mapName("Helvetica-Bold");
        mapName("Helvetica-BoldOblique");
        mapName("Helvetica-Oblique");
        mapName(NativeSymbol.CLASS_NAME);
        mapName("Times-Bold");
        mapName("Times-BoldItalic");
        mapName("Times-Italic");
        mapName("Times-Roman");
        mapName("ZapfDingbats");
        mapName("CourierCourierNew", "Courier");
        mapName("CourierNew", "Courier");
        mapName("CourierNew,Italic", "Courier-Oblique");
        mapName("CourierNew,Bold", "Courier-Bold");
        mapName("CourierNew,BoldItalic", "Courier-BoldOblique");
        mapName("Arial", "Helvetica");
        mapName("Arial,Italic", "Helvetica-Oblique");
        mapName("Arial,Bold", "Helvetica-Bold");
        mapName("Arial,BoldItalic", "Helvetica-BoldOblique");
        mapName("TimesNewRoman", "Times-Roman");
        mapName("TimesNewRoman,Italic", "Times-Italic");
        mapName("TimesNewRoman,Bold", "Times-Bold");
        mapName("TimesNewRoman,BoldItalic", "Times-BoldItalic");
        mapName("Symbol,Italic", NativeSymbol.CLASS_NAME);
        mapName("Symbol,Bold", NativeSymbol.CLASS_NAME);
        mapName("Symbol,BoldItalic", NativeSymbol.CLASS_NAME);
        mapName("Times", "Times-Roman");
        mapName("Times,Italic", "Times-Italic");
        mapName("Times,Bold", "Times-Bold");
        mapName("Times,BoldItalic", "Times-BoldItalic");
        mapName("ArialMT", "Helvetica");
        mapName("Arial-ItalicMT", "Helvetica-Oblique");
        mapName("Arial-BoldMT", "Helvetica-Bold");
        mapName("Arial-BoldItalicMT", "Helvetica-BoldOblique");
    }

    private Standard14Fonts() {
    }

    private static void loadMetrics(String fontName) throws IOException {
        String resourceName = "/org/apache/pdfbox/resources/afm/" + fontName + ".afm";
        InputStream resourceAsStream = PDType1Font.class.getResourceAsStream(resourceName);
        if (resourceAsStream == null) {
            throw new IOException("resource '" + resourceName + "' not found");
        }
        InputStream afmStream = new BufferedInputStream(resourceAsStream);
        try {
            AFMParser parser = new AFMParser(afmStream);
            FontMetrics metric = parser.parse(true);
            FONTS.put(fontName, metric);
            afmStream.close();
        } catch (Throwable th) {
            afmStream.close();
            throw th;
        }
    }

    private static void mapName(String baseName) {
        ALIASES.put(baseName, baseName);
    }

    private static void mapName(String alias, String baseName) {
        ALIASES.put(alias, baseName);
    }

    public static FontMetrics getAFM(String fontName) {
        String baseName = ALIASES.get(fontName);
        if (baseName == null) {
            return null;
        }
        if (FONTS.get(baseName) == null) {
            synchronized (FONTS) {
                if (FONTS.get(baseName) == null) {
                    try {
                        loadMetrics(baseName);
                    } catch (IOException ex) {
                        throw new IllegalArgumentException(ex);
                    }
                }
            }
        }
        return FONTS.get(baseName);
    }

    public static boolean containsName(String fontName) {
        return ALIASES.containsKey(fontName);
    }

    public static Set<String> getNames() {
        return Collections.unmodifiableSet(ALIASES.keySet());
    }

    public static String getMappedFontName(String fontName) {
        return ALIASES.get(fontName);
    }
}
