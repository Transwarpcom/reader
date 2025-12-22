package org.apache.pdfbox.pdmodel.font;

import org.apache.fontbox.FontBoxFont;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/font/FontInfo.class */
public abstract class FontInfo {
    public abstract String getPostScriptName();

    public abstract FontFormat getFormat();

    public abstract CIDSystemInfo getCIDSystemInfo();

    public abstract FontBoxFont getFont();

    public abstract int getFamilyClass();

    public abstract int getWeightClass();

    public abstract int getCodePageRange1();

    public abstract int getCodePageRange2();

    public abstract int getMacStyle();

    public abstract PDPanoseClassification getPanose();

    final int getWeightClassAsPanose() {
        int usWeightClass = getWeightClass();
        switch (usWeightClass) {
            case -1:
                break;
            case 0:
                break;
            case 100:
                break;
            case 200:
                break;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT /* 300 */:
                break;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL /* 400 */:
                break;
            case 500:
                break;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_SEMI_BOLD /* 600 */:
                break;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_BOLD /* 700 */:
                break;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_EXTRA_BOLD /* 800 */:
                break;
            case OS2WindowsMetricsTable.WEIGHT_CLASS_BLACK /* 900 */:
                break;
        }
        return 0;
    }

    final long getCodePageRange() {
        long range1 = getCodePageRange1() & 4294967295L;
        long range2 = getCodePageRange2() & 4294967295L;
        return (range2 << 32) | range1;
    }

    public String toString() {
        return getPostScriptName() + " (" + getFormat() + ", mac: 0x" + Integer.toHexString(getMacStyle()) + ", os/2: 0x" + Integer.toHexString(getFamilyClass()) + ", cid: " + getCIDSystemInfo() + ")";
    }
}
