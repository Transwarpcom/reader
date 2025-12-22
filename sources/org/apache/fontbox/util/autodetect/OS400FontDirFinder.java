package org.apache.fontbox.util.autodetect;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/util/autodetect/OS400FontDirFinder.class */
public class OS400FontDirFinder extends NativeFontDirFinder {
    @Override // org.apache.fontbox.util.autodetect.NativeFontDirFinder
    protected String[] getSearchableDirectories() {
        return new String[]{System.getProperty("user.home") + "/.fonts", "/QIBM/ProdData/OS400/Fonts"};
    }
}
