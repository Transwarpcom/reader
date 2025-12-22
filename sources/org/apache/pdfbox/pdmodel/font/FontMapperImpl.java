package org.apache.pdfbox.pdmodel.font;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.ttf.OpenTypeFont;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.fontbox.type1.Type1Font;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import org.mozilla.javascript.NativeSymbol;
import org.slf4j.Marker;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontMapperImpl.class */
final class FontMapperImpl implements FontMapper {
    private static final Log LOG = LogFactory.getLog((Class<?>) FontMapperImpl.class);
    private static final FontCache fontCache = new FontCache();
    private FontProvider fontProvider;
    private Map<String, FontInfo> fontInfoByName;
    private final TrueTypeFont lastResortFont;
    private final Map<String, List<String>> substitutes = new HashMap();

    FontMapperImpl() throws IOException {
        addSubstitutes("Courier", new ArrayList(Arrays.asList("CourierNew", "CourierNewPSMT", "LiberationMono", "NimbusMonL-Regu")));
        addSubstitutes("Courier-Bold", new ArrayList(Arrays.asList("CourierNewPS-BoldMT", "CourierNew-Bold", "LiberationMono-Bold", "NimbusMonL-Bold")));
        addSubstitutes("Courier-Oblique", new ArrayList(Arrays.asList("CourierNewPS-ItalicMT", "CourierNew-Italic", "LiberationMono-Italic", "NimbusMonL-ReguObli")));
        addSubstitutes("Courier-BoldOblique", new ArrayList(Arrays.asList("CourierNewPS-BoldItalicMT", "CourierNew-BoldItalic", "LiberationMono-BoldItalic", "NimbusMonL-BoldObli")));
        addSubstitutes("Helvetica", new ArrayList(Arrays.asList("ArialMT", "Arial", "LiberationSans", "NimbusSanL-Regu")));
        addSubstitutes("Helvetica-Bold", new ArrayList(Arrays.asList("Arial-BoldMT", "Arial-Bold", "LiberationSans-Bold", "NimbusSanL-Bold")));
        addSubstitutes("Helvetica-Oblique", new ArrayList(Arrays.asList("Arial-ItalicMT", "Arial-Italic", "Helvetica-Italic", "LiberationSans-Italic", "NimbusSanL-ReguItal")));
        addSubstitutes("Helvetica-BoldOblique", new ArrayList(Arrays.asList("Arial-BoldItalicMT", "Helvetica-BoldItalic", "LiberationSans-BoldItalic", "NimbusSanL-BoldItal")));
        addSubstitutes("Times-Roman", new ArrayList(Arrays.asList("TimesNewRomanPSMT", "TimesNewRoman", "TimesNewRomanPS", "LiberationSerif", "NimbusRomNo9L-Regu")));
        addSubstitutes("Times-Bold", new ArrayList(Arrays.asList("TimesNewRomanPS-BoldMT", "TimesNewRomanPS-Bold", "TimesNewRoman-Bold", "LiberationSerif-Bold", "NimbusRomNo9L-Medi")));
        addSubstitutes("Times-Italic", new ArrayList(Arrays.asList("TimesNewRomanPS-ItalicMT", "TimesNewRomanPS-Italic", "TimesNewRoman-Italic", "LiberationSerif-Italic", "NimbusRomNo9L-ReguItal")));
        addSubstitutes("Times-BoldItalic", new ArrayList(Arrays.asList("TimesNewRomanPS-BoldItalicMT", "TimesNewRomanPS-BoldItalic", "TimesNewRoman-BoldItalic", "LiberationSerif-BoldItalic", "NimbusRomNo9L-MediItal")));
        addSubstitutes(NativeSymbol.CLASS_NAME, new ArrayList(Arrays.asList(NativeSymbol.CLASS_NAME, "SymbolMT", "StandardSymL")));
        addSubstitutes("ZapfDingbats", new ArrayList(Arrays.asList("ZapfDingbatsITCbyBT-Regular", "ZapfDingbatsITC", "Dingbats", "MS-Gothic")));
        for (String baseName : Standard14Fonts.getNames()) {
            if (getSubstitutes(baseName).isEmpty()) {
                String mappedName = Standard14Fonts.getMappedFontName(baseName);
                addSubstitutes(baseName, copySubstitutes(mappedName.toLowerCase(Locale.ENGLISH)));
            }
        }
        try {
            InputStream resourceAsStream = FontMapper.class.getResourceAsStream("/org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
            if (resourceAsStream == null) {
                throw new IOException("resource '/org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf' not found");
            }
            InputStream ttfStream = new BufferedInputStream(resourceAsStream);
            TTFParser ttfParser = new TTFParser();
            this.lastResortFont = ttfParser.parse(ttfStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontMapperImpl$DefaultFontProvider.class */
    private static class DefaultFontProvider {
        private static final FontProvider INSTANCE = new FileSystemFontProvider(FontMapperImpl.fontCache);

        private DefaultFontProvider() {
        }
    }

    public synchronized void setProvider(FontProvider fontProvider) {
        this.fontInfoByName = createFontInfoByName(fontProvider.getFontInfo());
        this.fontProvider = fontProvider;
    }

    public synchronized FontProvider getProvider() {
        if (this.fontProvider == null) {
            setProvider(DefaultFontProvider.INSTANCE);
        }
        return this.fontProvider;
    }

    public FontCache getFontCache() {
        return fontCache;
    }

    private Map<String, FontInfo> createFontInfoByName(List<? extends FontInfo> fontInfoList) {
        Map<String, FontInfo> map = new LinkedHashMap<>();
        for (FontInfo info : fontInfoList) {
            for (String name : getPostScriptNames(info.getPostScriptName())) {
                map.put(name.toLowerCase(Locale.ENGLISH), info);
            }
        }
        return map;
    }

    private Set<String> getPostScriptNames(String postScriptName) {
        Set<String> names = new HashSet<>(2);
        names.add(postScriptName);
        names.add(postScriptName.replace("-", ""));
        return names;
    }

    private List<String> copySubstitutes(String postScriptName) {
        return new ArrayList(this.substitutes.get(postScriptName));
    }

    public void addSubstitute(String match, String replace) {
        String lowerCaseMatch = match.toLowerCase(Locale.ENGLISH);
        if (!this.substitutes.containsKey(lowerCaseMatch)) {
            this.substitutes.put(lowerCaseMatch, new ArrayList());
        }
        this.substitutes.get(lowerCaseMatch).add(replace);
    }

    private void addSubstitutes(String match, List<String> replacements) {
        this.substitutes.put(match.toLowerCase(Locale.ENGLISH), replacements);
    }

    private List<String> getSubstitutes(String postScriptName) {
        List<String> subs = this.substitutes.get(postScriptName.replace(" ", "").toLowerCase(Locale.ENGLISH));
        if (subs != null) {
            return subs;
        }
        return Collections.emptyList();
    }

    private String getFallbackFontName(PDFontDescriptor fontDescriptor) {
        String fontName;
        if (fontDescriptor != null) {
            boolean isBold = false;
            String name = fontDescriptor.getFontName();
            if (name != null) {
                String lower = fontDescriptor.getFontName().toLowerCase();
                isBold = lower.contains("bold") || lower.contains("black") || lower.contains("heavy");
            }
            if (fontDescriptor.isFixedPitch()) {
                fontName = "Courier";
                if (isBold && fontDescriptor.isItalic()) {
                    fontName = fontName + "-BoldOblique";
                } else if (isBold) {
                    fontName = fontName + "-Bold";
                } else if (fontDescriptor.isItalic()) {
                    fontName = fontName + "-Oblique";
                }
            } else if (fontDescriptor.isSerif()) {
                if (isBold && fontDescriptor.isItalic()) {
                    fontName = "Times-BoldItalic";
                } else {
                    fontName = isBold ? "Times-Bold" : fontDescriptor.isItalic() ? "Times-Italic" : "Times-Roman";
                }
            } else {
                fontName = "Helvetica";
                if (isBold && fontDescriptor.isItalic()) {
                    fontName = fontName + "-BoldOblique";
                } else if (isBold) {
                    fontName = fontName + "-Bold";
                } else if (fontDescriptor.isItalic()) {
                    fontName = fontName + "-Oblique";
                }
            }
        } else {
            fontName = "Times-Roman";
        }
        return fontName;
    }

    @Override // org.apache.pdfbox.pdmodel.font.FontMapper
    public FontMapping<TrueTypeFont> getTrueTypeFont(String baseFont, PDFontDescriptor fontDescriptor) {
        TrueTypeFont ttf = (TrueTypeFont) findFont(FontFormat.TTF, baseFont);
        if (ttf != null) {
            return new FontMapping<>(ttf, false);
        }
        String fontName = getFallbackFontName(fontDescriptor);
        TrueTypeFont ttf2 = (TrueTypeFont) findFont(FontFormat.TTF, fontName);
        if (ttf2 == null) {
            ttf2 = this.lastResortFont;
        }
        return new FontMapping<>(ttf2, true);
    }

    @Override // org.apache.pdfbox.pdmodel.font.FontMapper
    public FontMapping<FontBoxFont> getFontBoxFont(String baseFont, PDFontDescriptor fontDescriptor) {
        FontBoxFont font = findFontBoxFont(baseFont);
        if (font != null) {
            return new FontMapping<>(font, false);
        }
        String fallbackName = getFallbackFontName(fontDescriptor);
        FontBoxFont font2 = findFontBoxFont(fallbackName);
        if (font2 == null) {
            font2 = this.lastResortFont;
        }
        return new FontMapping<>(font2, true);
    }

    private FontBoxFont findFontBoxFont(String postScriptName) {
        Type1Font t1 = (Type1Font) findFont(FontFormat.PFB, postScriptName);
        if (t1 != null) {
            return t1;
        }
        TrueTypeFont ttf = (TrueTypeFont) findFont(FontFormat.TTF, postScriptName);
        if (ttf != null) {
            return ttf;
        }
        OpenTypeFont otf = (OpenTypeFont) findFont(FontFormat.OTF, postScriptName);
        if (otf != null) {
            return otf;
        }
        return null;
    }

    private FontBoxFont findFont(FontFormat format, String postScriptName) {
        if (postScriptName == null) {
            return null;
        }
        if (this.fontProvider == null) {
            getProvider();
        }
        FontInfo info = getFont(format, postScriptName);
        if (info != null) {
            return info.getFont();
        }
        FontInfo info2 = getFont(format, postScriptName.replace("-", ""));
        if (info2 != null) {
            return info2.getFont();
        }
        for (String substituteName : getSubstitutes(postScriptName)) {
            FontInfo info3 = getFont(format, substituteName);
            if (info3 != null) {
                return info3.getFont();
            }
        }
        FontInfo info4 = getFont(format, postScriptName.replace(",", "-"));
        if (info4 != null) {
            return info4.getFont();
        }
        FontInfo info5 = getFont(format, postScriptName + "-Regular");
        if (info5 != null) {
            return info5.getFont();
        }
        return null;
    }

    private FontInfo getFont(FontFormat format, String postScriptName) {
        if (postScriptName.contains(Marker.ANY_NON_NULL_MARKER)) {
            postScriptName = postScriptName.substring(postScriptName.indexOf(43) + 1);
        }
        FontInfo info = this.fontInfoByName.get(postScriptName.toLowerCase(Locale.ENGLISH));
        if (info != null && info.getFormat() == format) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("getFont('%s','%s') returns %s", format, postScriptName, info));
            }
            return info;
        }
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.font.FontMapper
    public CIDFontMapping getCIDFont(String baseFont, PDFontDescriptor fontDescriptor, PDCIDSystemInfo cidSystemInfo) {
        OpenTypeFont otf1 = (OpenTypeFont) findFont(FontFormat.OTF, baseFont);
        if (otf1 != null) {
            return new CIDFontMapping(otf1, null, false);
        }
        TrueTypeFont ttf = (TrueTypeFont) findFont(FontFormat.TTF, baseFont);
        if (ttf != null) {
            return new CIDFontMapping(null, ttf, false);
        }
        if (cidSystemInfo != null) {
            String collection = cidSystemInfo.getRegistry() + "-" + cidSystemInfo.getOrdering();
            if (collection.equals("Adobe-GB1") || collection.equals("Adobe-CNS1") || collection.equals("Adobe-Japan1") || collection.equals("Adobe-Korea1")) {
                PriorityQueue<FontMatch> queue = getFontMatches(fontDescriptor, cidSystemInfo);
                FontMatch bestMatch = queue.poll();
                if (bestMatch != null) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Best match for '" + baseFont + "': " + bestMatch.info);
                    }
                    FontBoxFont font = bestMatch.info.getFont();
                    if (font instanceof OpenTypeFont) {
                        return new CIDFontMapping((OpenTypeFont) font, null, true);
                    }
                    if (font != null) {
                        return new CIDFontMapping(null, font, true);
                    }
                }
            }
        }
        return new CIDFontMapping(null, this.lastResortFont, true);
    }

    private PriorityQueue<FontMatch> getFontMatches(PDFontDescriptor fontDescriptor, PDCIDSystemInfo cidSystemInfo) {
        PriorityQueue<FontMatch> queue = new PriorityQueue<>(20);
        for (FontInfo info : this.fontInfoByName.values()) {
            if (cidSystemInfo == null || isCharSetMatch(cidSystemInfo, info)) {
                FontMatch match = new FontMatch(info);
                if (fontDescriptor.getPanose() != null && info.getPanose() != null) {
                    PDPanoseClassification panose = fontDescriptor.getPanose().getPanose();
                    if (panose.getFamilyKind() == info.getPanose().getFamilyKind()) {
                        if (panose.getFamilyKind() != 0 || ((!info.getPostScriptName().toLowerCase().contains("barcode") && !info.getPostScriptName().startsWith(StandardStructureTypes.CODE)) || probablyBarcodeFont(fontDescriptor))) {
                            if (panose.getSerifStyle() == info.getPanose().getSerifStyle()) {
                                match.score += 2.0d;
                            } else if (panose.getSerifStyle() >= 2 && panose.getSerifStyle() <= 5 && info.getPanose().getSerifStyle() >= 2 && info.getPanose().getSerifStyle() <= 5) {
                                match.score += 1.0d;
                            } else if (panose.getSerifStyle() >= 11 && panose.getSerifStyle() <= 13 && info.getPanose().getSerifStyle() >= 11 && info.getPanose().getSerifStyle() <= 13) {
                                match.score += 1.0d;
                            } else if (panose.getSerifStyle() != 0 && info.getPanose().getSerifStyle() != 0) {
                                match.score -= 1.0d;
                            }
                            int weight = info.getPanose().getWeight();
                            int weightClass = info.getWeightClassAsPanose();
                            if (Math.abs(weight - weightClass) > 2) {
                                weight = weightClass;
                            }
                            if (panose.getWeight() == weight) {
                                match.score += 2.0d;
                            } else if (panose.getWeight() > 1 && weight > 1) {
                                float dist = Math.abs(panose.getWeight() - weight);
                                match.score += 1.0d - (dist * 0.5d);
                            }
                        }
                    }
                    queue.add(match);
                } else {
                    if (fontDescriptor.getFontWeight() > 0.0f && info.getWeightClass() > 0) {
                        float dist2 = Math.abs(fontDescriptor.getFontWeight() - info.getWeightClass());
                        match.score += 1.0d - ((dist2 / 100.0f) * 0.5d);
                    }
                    queue.add(match);
                }
            }
        }
        return queue;
    }

    private boolean probablyBarcodeFont(PDFontDescriptor fontDescriptor) {
        String ff = fontDescriptor.getFontFamily();
        if (ff == null) {
            ff = "";
        }
        String fn = fontDescriptor.getFontName();
        if (fn == null) {
            fn = "";
        }
        return ff.startsWith(StandardStructureTypes.CODE) || ff.toLowerCase().contains("barcode") || fn.startsWith(StandardStructureTypes.CODE) || fn.toLowerCase().contains("barcode");
    }

    private boolean isCharSetMatch(PDCIDSystemInfo cidSystemInfo, FontInfo info) {
        if (info.getCIDSystemInfo() != null) {
            return info.getCIDSystemInfo().getRegistry().equals(cidSystemInfo.getRegistry()) && info.getCIDSystemInfo().getOrdering().equals(cidSystemInfo.getOrdering());
        }
        long codePageRange = info.getCodePageRange();
        if ("MalgunGothic-Semilight".equals(info.getPostScriptName())) {
            codePageRange &= ((131072 | 262144) | 1048576) ^ (-1);
        }
        if (cidSystemInfo.getOrdering().equals("GB1") && (codePageRange & 262144) == 262144) {
            return true;
        }
        if (cidSystemInfo.getOrdering().equals("CNS1") && (codePageRange & 1048576) == 1048576) {
            return true;
        }
        if (cidSystemInfo.getOrdering().equals("Japan1") && (codePageRange & 131072) == 131072) {
            return true;
        }
        return cidSystemInfo.getOrdering().equals("Korea1") && ((codePageRange & 524288) == 524288 || (codePageRange & 2097152) == 2097152);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontMapperImpl$FontMatch.class */
    private static class FontMatch implements Comparable<FontMatch> {
        double score;
        final FontInfo info;

        FontMatch(FontInfo info) {
            this.info = info;
        }

        @Override // java.lang.Comparable
        public int compareTo(FontMatch match) {
            return Double.compare(match.score, this.score);
        }
    }

    private FontMatch printMatches(PriorityQueue<FontMatch> queue) {
        FontMatch bestMatch = queue.peek();
        System.out.println("-------");
        while (!queue.isEmpty()) {
            FontMatch match = queue.poll();
            FontInfo info = match.info;
            System.out.println(match.score + " | " + info.getMacStyle() + " " + info.getFamilyClass() + " " + info.getPanose() + " " + info.getCIDSystemInfo() + " " + info.getPostScriptName() + " " + info.getFormat());
        }
        System.out.println("-------");
        return bestMatch;
    }
}
