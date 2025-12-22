package org.jsoup.nodes;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.lang3.CharEncoding;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Parser;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Entities.class */
public class Entities {
    private static final int empty = -1;
    private static final String emptyName = "";
    static final int codepointRadix = 36;
    private static final char[] codeDelims = {',', ';'};
    private static final HashMap<String, String> multipoints = new HashMap<>();
    private static final Document.OutputSettings DefaultOutput = new Document.OutputSettings();

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Entities$EscapeMode.class */
    public enum EscapeMode {
        xhtml(EntitiesData.xmlPoints, 4),
        base(EntitiesData.basePoints, 106),
        extended(EntitiesData.fullPoints, 2125);

        private String[] nameKeys;
        private int[] codeVals;
        private int[] codeKeys;
        private String[] nameVals;

        EscapeMode(String file, int size) throws IOException, NumberFormatException {
            Entities.load(this, file, size);
        }

        int codepointForName(String name) {
            int index = Arrays.binarySearch(this.nameKeys, name);
            if (index >= 0) {
                return this.codeVals[index];
            }
            return -1;
        }

        String nameForCodepoint(int codepoint) {
            int index = Arrays.binarySearch(this.codeKeys, codepoint);
            if (index >= 0) {
                return (index >= this.nameVals.length - 1 || this.codeKeys[index + 1] != codepoint) ? this.nameVals[index] : this.nameVals[index + 1];
            }
            return "";
        }

        private int size() {
            return this.nameKeys.length;
        }
    }

    private Entities() {
    }

    public static boolean isNamedEntity(String name) {
        return EscapeMode.extended.codepointForName(name) != -1;
    }

    public static boolean isBaseNamedEntity(String name) {
        return EscapeMode.base.codepointForName(name) != -1;
    }

    public static String getByName(String name) {
        String val = multipoints.get(name);
        if (val != null) {
            return val;
        }
        int codepoint = EscapeMode.extended.codepointForName(name);
        if (codepoint != -1) {
            return new String(new int[]{codepoint}, 0, 1);
        }
        return "";
    }

    public static int codepointsForName(String name, int[] codepoints) {
        String val = multipoints.get(name);
        if (val != null) {
            codepoints[0] = val.codePointAt(0);
            codepoints[1] = val.codePointAt(1);
            return 2;
        }
        int codepoint = EscapeMode.extended.codepointForName(name);
        if (codepoint != -1) {
            codepoints[0] = codepoint;
            return 1;
        }
        return 0;
    }

    public static String escape(String string, Document.OutputSettings out) {
        if (string == null) {
            return "";
        }
        StringBuilder accum = StringUtil.borrowBuilder();
        try {
            escape(accum, string, out, false, false, false);
            return StringUtil.releaseBuilder(accum);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public static String escape(String string) {
        return escape(string, DefaultOutput);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0159  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void escape(java.lang.Appendable r4, java.lang.String r5, org.jsoup.nodes.Document.OutputSettings r6, boolean r7, boolean r8, boolean r9) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Entities.escape(java.lang.Appendable, java.lang.String, org.jsoup.nodes.Document$OutputSettings, boolean, boolean, boolean):void");
    }

    private static void appendEncoded(Appendable accum, EscapeMode escapeMode, int codePoint) throws IOException {
        String name = escapeMode.nameForCodepoint(codePoint);
        if (!"".equals(name)) {
            accum.append('&').append(name).append(';');
        } else {
            accum.append("&#x").append(Integer.toHexString(codePoint)).append(';');
        }
    }

    public static String unescape(String string) {
        return unescape(string, false);
    }

    static String unescape(String string, boolean strict) {
        return Parser.unescapeEntities(string, strict);
    }

    private static boolean canEncode(CoreCharset charset, char c, CharsetEncoder fallback) {
        switch (charset) {
            case ascii:
                return c < 128;
            case utf:
                return true;
            default:
                return fallback.canEncode(c);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Entities$CoreCharset.class */
    enum CoreCharset {
        ascii,
        utf,
        fallback;

        static CoreCharset byName(String name) {
            if (name.equals(CharEncoding.US_ASCII)) {
                return ascii;
            }
            if (name.startsWith("UTF-")) {
                return utf;
            }
            return fallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void load(EscapeMode e, String pointsData, int size) throws IOException, NumberFormatException {
        int cp2;
        e.nameKeys = new String[size];
        e.codeVals = new int[size];
        e.codeKeys = new int[size];
        e.nameVals = new String[size];
        int i = 0;
        CharacterReader reader = new CharacterReader(pointsData);
        while (!reader.isEmpty()) {
            String name = reader.consumeTo('=');
            reader.advance();
            int cp1 = Integer.parseInt(reader.consumeToAny(codeDelims), 36);
            char codeDelim = reader.current();
            reader.advance();
            if (codeDelim == ',') {
                cp2 = Integer.parseInt(reader.consumeTo(';'), 36);
                reader.advance();
            } else {
                cp2 = -1;
            }
            String indexS = reader.consumeTo('&');
            int index = Integer.parseInt(indexS, 36);
            reader.advance();
            e.nameKeys[i] = name;
            e.codeVals[i] = cp1;
            e.codeKeys[index] = cp1;
            e.nameVals[index] = name;
            if (cp2 != -1) {
                multipoints.put(name, new String(new int[]{cp1, cp2}, 0, 2));
            }
            i++;
        }
        Validate.isTrue(i == size, "Unexpected count of entities loaded");
    }
}
