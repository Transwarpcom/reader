package org.apache.pdfbox.pdmodel.font;

import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontProvider.class */
public abstract class FontProvider {
    public abstract String toDebugString();

    public abstract List<? extends FontInfo> getFontInfo();
}
