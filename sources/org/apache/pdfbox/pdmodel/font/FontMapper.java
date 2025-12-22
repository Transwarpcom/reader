package org.apache.pdfbox.pdmodel.font;

import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.ttf.TrueTypeFont;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontMapper.class */
public interface FontMapper {
    FontMapping<TrueTypeFont> getTrueTypeFont(String str, PDFontDescriptor pDFontDescriptor);

    FontMapping<FontBoxFont> getFontBoxFont(String str, PDFontDescriptor pDFontDescriptor);

    CIDFontMapping getCIDFont(String str, PDFontDescriptor pDFontDescriptor, PDCIDSystemInfo pDCIDSystemInfo);
}
