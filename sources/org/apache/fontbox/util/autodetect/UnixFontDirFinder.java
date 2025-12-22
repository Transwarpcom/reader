package org.apache.fontbox.util.autodetect;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/util/autodetect/UnixFontDirFinder.class */
public class UnixFontDirFinder extends NativeFontDirFinder {
    @Override // org.apache.fontbox.util.autodetect.NativeFontDirFinder
    protected String[] getSearchableDirectories() {
        return new String[]{System.getProperty("user.home") + "/.fonts", "/usr/local/fonts", "/usr/local/share/fonts", "/usr/share/fonts", "/usr/X11R6/lib/X11/fonts", "/usr/share/X11/fonts"};
    }
}
