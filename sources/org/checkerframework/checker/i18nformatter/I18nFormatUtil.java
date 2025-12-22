package org.checkerframework.checker.i18nformatter;

import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.checkerframework.checker.i18nformatter.qual.I18nChecksFormat;
import org.checkerframework.checker.i18nformatter.qual.I18nConversionCategory;
import org.checkerframework.checker.i18nformatter.qual.I18nValidFormat;

/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/i18nformatter/I18nFormatUtil.class */
public class I18nFormatUtil {
    public static void tryFormatSatisfiability(String format) throws IllegalFormatException {
        MessageFormat.format(format, (Object[]) null);
    }

    public static I18nConversionCategory[] formatParameterCategories(String format) throws IllegalFormatException, NumberFormatException {
        tryFormatSatisfiability(format);
        I18nConversion[] cs = MessageFormatParser.parse(format);
        int maxIndex = -1;
        Map<Integer, I18nConversionCategory> conv = new HashMap<>();
        for (I18nConversion c : cs) {
            int index = c.index;
            conv.put(Integer.valueOf(index), I18nConversionCategory.intersect(c.category, conv.containsKey(Integer.valueOf(index)) ? conv.get(Integer.valueOf(index)) : I18nConversionCategory.UNUSED));
            maxIndex = Math.max(maxIndex, index);
        }
        I18nConversionCategory[] res = new I18nConversionCategory[maxIndex + 1];
        for (int i = 0; i <= maxIndex; i++) {
            res[i] = conv.containsKey(Integer.valueOf(i)) ? conv.get(Integer.valueOf(i)) : I18nConversionCategory.UNUSED;
        }
        return res;
    }

    @I18nChecksFormat
    public static boolean hasFormat(String format, I18nConversionCategory... cc) throws IllegalFormatException, NumberFormatException {
        I18nConversionCategory[] fcc = formatParameterCategories(format);
        if (fcc.length != cc.length) {
            return false;
        }
        for (int i = 0; i < cc.length; i++) {
            if (!I18nConversionCategory.isSubsetOf(cc[i], fcc[i])) {
                return false;
            }
        }
        return true;
    }

    @I18nValidFormat
    public static boolean isFormat(String format) {
        try {
            formatParameterCategories(format);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/i18nformatter/I18nFormatUtil$I18nConversion.class */
    private static class I18nConversion {
        public int index;
        public I18nConversionCategory category;

        public I18nConversion(int index, I18nConversionCategory category) {
            this.index = index;
            this.category = category;
        }

        public String toString() {
            return this.category.toString() + "(index: " + this.index + ")";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/i18nformatter/I18nFormatUtil$MessageFormatParser.class */
    private static class MessageFormatParser {
        public static int maxOffset;
        private static Locale locale;
        private static List<I18nConversionCategory> categories;
        private static List<Integer> argumentIndices;
        private static int numFormat;
        private static final int SEG_RAW = 0;
        private static final int SEG_INDEX = 1;
        private static final int SEG_TYPE = 2;
        private static final int SEG_MODIFIER = 3;
        private static final int TYPE_NULL = 0;
        private static final int TYPE_NUMBER = 1;
        private static final int TYPE_DATE = 2;
        private static final int TYPE_TIME = 3;
        private static final int TYPE_CHOICE = 4;
        private static final int MODIFIER_DEFAULT = 0;
        private static final int MODIFIER_CURRENCY = 1;
        private static final int MODIFIER_PERCENT = 2;
        private static final int MODIFIER_INTEGER = 3;
        private static final String[] TYPE_KEYWORDS = {"", "number", PackageDocumentBase.DCTags.date, "time", "choice"};
        private static final String[] NUMBER_MODIFIER_KEYWORDS = {"", "currency", "percent", "integer"};
        private static final String[] DATE_TIME_MODIFIER_KEYWORDS = {"", "short", "medium", "long", "full"};

        private MessageFormatParser() {
        }

        public static I18nConversion[] parse(String pattern) throws NumberFormatException {
            categories = new ArrayList();
            argumentIndices = new ArrayList();
            locale = Locale.getDefault(Locale.Category.FORMAT);
            applyPattern(pattern);
            I18nConversion[] ret = new I18nConversion[numFormat];
            for (int i = 0; i < numFormat; i++) {
                ret[i] = new I18nConversion(argumentIndices.get(i).intValue(), categories.get(i));
            }
            return ret;
        }

        private static void applyPattern(String pattern) throws NumberFormatException {
            StringBuilder[] segments = new StringBuilder[4];
            segments[0] = new StringBuilder();
            int part = 0;
            numFormat = 0;
            boolean inQuote = false;
            int braceStack = 0;
            maxOffset = -1;
            int i = 0;
            while (i < pattern.length()) {
                char ch2 = pattern.charAt(i);
                if (part == 0) {
                    if (ch2 == '\'') {
                        if (i + 1 < pattern.length() && pattern.charAt(i + 1) == '\'') {
                            segments[part].append(ch2);
                            i++;
                        } else {
                            inQuote = !inQuote;
                        }
                    } else if (ch2 == '{' && !inQuote) {
                        part = 1;
                        if (segments[1] == null) {
                            segments[1] = new StringBuilder();
                        }
                    } else {
                        segments[part].append(ch2);
                    }
                } else if (inQuote) {
                    segments[part].append(ch2);
                    if (ch2 == '\'') {
                        inQuote = false;
                    }
                } else {
                    switch (ch2) {
                        case ' ':
                            if (part == 2 && segments[2].length() <= 0) {
                                break;
                            } else {
                                segments[part].append(ch2);
                                break;
                            }
                        case '\'':
                            inQuote = true;
                            segments[part].append(ch2);
                            break;
                        case ',':
                            if (part < 3) {
                                part++;
                                if (segments[part] != null) {
                                    break;
                                } else {
                                    segments[part] = new StringBuilder();
                                    break;
                                }
                            } else {
                                segments[part].append(ch2);
                                break;
                            }
                        case '{':
                            braceStack++;
                            segments[part].append(ch2);
                            break;
                        case '}':
                            if (braceStack == 0) {
                                part = 0;
                                makeFormat(numFormat, segments);
                                numFormat++;
                                segments[1] = null;
                                segments[2] = null;
                                segments[3] = null;
                                break;
                            } else {
                                braceStack--;
                                segments[part].append(ch2);
                                break;
                            }
                        default:
                            segments[part].append(ch2);
                            break;
                    }
                }
                i++;
            }
            if (braceStack == 0 && part != 0) {
                maxOffset = -1;
                throw new IllegalArgumentException("Unmatched braces in the pattern");
            }
        }

        private static void makeFormat(int offsetNumber, StringBuilder[] textSegments) throws NumberFormatException {
            I18nConversionCategory category;
            String[] segments = new String[textSegments.length];
            for (int i = 0; i < textSegments.length; i++) {
                StringBuilder oneseg = textSegments[i];
                segments[i] = oneseg != null ? oneseg.toString() : "";
            }
            try {
                int argumentNumber = Integer.parseInt(segments[1]);
                if (argumentNumber < 0) {
                    throw new IllegalArgumentException("negative argument number: " + argumentNumber);
                }
                int oldMaxOffset = maxOffset;
                maxOffset = offsetNumber;
                argumentIndices.add(Integer.valueOf(argumentNumber));
                if (segments[2].length() != 0) {
                    int type = findKeyword(segments[2], TYPE_KEYWORDS);
                    switch (type) {
                        case 0:
                            category = I18nConversionCategory.GENERAL;
                            break;
                        case 1:
                            switch (findKeyword(segments[3], NUMBER_MODIFIER_KEYWORDS)) {
                                default:
                                    try {
                                        new DecimalFormat(segments[3], DecimalFormatSymbols.getInstance(locale));
                                    } catch (IllegalArgumentException e) {
                                        maxOffset = oldMaxOffset;
                                        throw e;
                                    }
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                    category = I18nConversionCategory.NUMBER;
                                    break;
                            }
                        case 2:
                        case 3:
                            int mod = findKeyword(segments[3], DATE_TIME_MODIFIER_KEYWORDS);
                            if (mod < 0 || mod >= DATE_TIME_MODIFIER_KEYWORDS.length) {
                                try {
                                    new SimpleDateFormat(segments[3], locale);
                                } catch (IllegalArgumentException e2) {
                                    maxOffset = oldMaxOffset;
                                    throw e2;
                                }
                            }
                            category = I18nConversionCategory.DATE;
                            break;
                        case 4:
                            if (segments[3].length() == 0) {
                                throw new IllegalArgumentException("Choice Pattern requires Subformat Pattern: " + segments[3]);
                            }
                            try {
                                new ChoiceFormat(segments[3]);
                                category = I18nConversionCategory.NUMBER;
                                break;
                            } catch (Exception e3) {
                                maxOffset = oldMaxOffset;
                                throw new IllegalArgumentException("Choice Pattern incorrect: " + segments[3], e3);
                            }
                        default:
                            maxOffset = oldMaxOffset;
                            throw new IllegalArgumentException("unknown format type: " + segments[2]);
                    }
                } else {
                    category = I18nConversionCategory.GENERAL;
                }
                categories.add(category);
            } catch (NumberFormatException e4) {
                throw new IllegalArgumentException("can't parse argument number: " + segments[1], e4);
            }
        }

        private static final int findKeyword(String s, String[] list) {
            for (int i = 0; i < list.length; i++) {
                if (s.equals(list[i])) {
                    return i;
                }
            }
            String ls = s.trim().toLowerCase(Locale.ROOT);
            if (ls != s) {
                for (int i2 = 0; i2 < list.length; i2++) {
                    if (ls.equals(list[i2])) {
                        return i2;
                    }
                }
                return -1;
            }
            return -1;
        }
    }
}
