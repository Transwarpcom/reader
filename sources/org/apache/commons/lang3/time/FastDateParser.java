package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser.class */
public class FastDateParser implements DateParser, Serializable {
    private static final long serialVersionUID = 3;
    private final String pattern;
    private final TimeZone timeZone;
    private final Locale locale;
    private final int century;
    private final int startYear;
    private transient List<StrategyAndWidth> patterns;
    static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
    private static final Comparator<String> LONGER_FIRST_LOWERCASE = new Comparator<String>() { // from class: org.apache.commons.lang3.time.FastDateParser.1
        @Override // java.util.Comparator
        public int compare(String left, String right) {
            return right.compareTo(left);
        }
    };
    private static final ConcurrentMap<Locale, Strategy>[] caches = new ConcurrentMap[17];
    private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1) { // from class: org.apache.commons.lang3.time.FastDateParser.2
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            return iValue < 100 ? parser.adjustYear(iValue) : iValue;
        }
    };
    private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2) { // from class: org.apache.commons.lang3.time.FastDateParser.3
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            return iValue - 1;
        }
    };
    private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
    private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
    private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
    private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
    private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
    private static final Strategy DAY_OF_WEEK_STRATEGY = new NumberStrategy(7) { // from class: org.apache.commons.lang3.time.FastDateParser.4
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            if (iValue == 7) {
                return 1;
            }
            return iValue + 1;
        }
    };
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
    private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
    private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(11) { // from class: org.apache.commons.lang3.time.FastDateParser.5
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            if (iValue == 24) {
                return 0;
            }
            return iValue;
        }
    };
    private static final Strategy HOUR12_STRATEGY = new NumberStrategy(10) { // from class: org.apache.commons.lang3.time.FastDateParser.6
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            if (iValue == 12) {
                return 0;
            }
            return iValue;
        }
    };
    private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
    private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
    private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
    private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);

    protected FastDateParser(String pattern, TimeZone timeZone, Locale locale) {
        this(pattern, timeZone, locale, null);
    }

    protected FastDateParser(String pattern, TimeZone timeZone, Locale locale, Date centuryStart) {
        int centuryStartYear;
        this.pattern = pattern;
        this.timeZone = timeZone;
        this.locale = locale;
        Calendar definingCalendar = Calendar.getInstance(timeZone, locale);
        if (centuryStart != null) {
            definingCalendar.setTime(centuryStart);
            centuryStartYear = definingCalendar.get(1);
        } else if (locale.equals(JAPANESE_IMPERIAL)) {
            centuryStartYear = 0;
        } else {
            definingCalendar.setTime(new Date());
            centuryStartYear = definingCalendar.get(1) - 80;
        }
        this.century = (centuryStartYear / 100) * 100;
        this.startYear = centuryStartYear - this.century;
        init(definingCalendar);
    }

    private void init(Calendar definingCalendar) {
        this.patterns = new ArrayList();
        StrategyParser fm = new StrategyParser(definingCalendar);
        while (true) {
            StrategyAndWidth field = fm.getNextStrategy();
            if (field != null) {
                this.patterns.add(field);
            } else {
                return;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$StrategyAndWidth.class */
    private static class StrategyAndWidth {
        final Strategy strategy;
        final int width;

        StrategyAndWidth(Strategy strategy, int width) {
            this.strategy = strategy;
            this.width = width;
        }

        int getMaxWidth(ListIterator<StrategyAndWidth> lt) {
            if (!this.strategy.isNumber() || !lt.hasNext()) {
                return 0;
            }
            Strategy nextStrategy = lt.next().strategy;
            lt.previous();
            if (nextStrategy.isNumber()) {
                return this.width;
            }
            return 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$StrategyParser.class */
    private class StrategyParser {
        private final Calendar definingCalendar;
        private int currentIdx;

        StrategyParser(Calendar definingCalendar) {
            this.definingCalendar = definingCalendar;
        }

        StrategyAndWidth getNextStrategy() {
            if (this.currentIdx < FastDateParser.this.pattern.length()) {
                char c = FastDateParser.this.pattern.charAt(this.currentIdx);
                if (FastDateParser.isFormatLetter(c)) {
                    return letterPattern(c);
                }
                return literal();
            }
            return null;
        }

        private StrategyAndWidth letterPattern(char c) {
            int begin = this.currentIdx;
            do {
                int i = this.currentIdx + 1;
                this.currentIdx = i;
                if (i >= FastDateParser.this.pattern.length()) {
                    break;
                }
            } while (FastDateParser.this.pattern.charAt(this.currentIdx) == c);
            int width = this.currentIdx - begin;
            return new StrategyAndWidth(FastDateParser.this.getStrategy(c, width, this.definingCalendar), width);
        }

        private StrategyAndWidth literal() {
            boolean activeQuote = false;
            StringBuilder sb = new StringBuilder();
            while (this.currentIdx < FastDateParser.this.pattern.length()) {
                char c = FastDateParser.this.pattern.charAt(this.currentIdx);
                if (!activeQuote && FastDateParser.isFormatLetter(c)) {
                    break;
                }
                if (c == '\'') {
                    int i = this.currentIdx + 1;
                    this.currentIdx = i;
                    if (i == FastDateParser.this.pattern.length() || FastDateParser.this.pattern.charAt(this.currentIdx) != '\'') {
                        activeQuote = !activeQuote;
                    }
                }
                this.currentIdx++;
                sb.append(c);
            }
            if (activeQuote) {
                throw new IllegalArgumentException("Unterminated quote");
            }
            String formatField = sb.toString();
            return new StrategyAndWidth(new CopyQuotedStrategy(formatField), formatField.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isFormatLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateParser)) {
            return false;
        }
        FastDateParser other = (FastDateParser) obj;
        return this.pattern.equals(other.pattern) && this.timeZone.equals(other.timeZone) && this.locale.equals(other.locale);
    }

    public int hashCode() {
        return this.pattern.hashCode() + (13 * (this.timeZone.hashCode() + (13 * this.locale.hashCode())));
    }

    public String toString() {
        return "FastDateParser[" + this.pattern + "," + this.locale + "," + this.timeZone.getID() + "]";
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        Calendar definingCalendar = Calendar.getInstance(this.timeZone, this.locale);
        init(definingCalendar);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String source) throws ParseException {
        return parse(source);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String source) throws ParseException {
        ParsePosition pp = new ParsePosition(0);
        Date date = parse(source, pp);
        if (date == null) {
            if (this.locale.equals(JAPANESE_IMPERIAL)) {
                throw new ParseException("(The " + this.locale + " locale does not support dates before 1868 AD)\nUnparseable date: \"" + source, pp.getErrorIndex());
            }
            throw new ParseException("Unparseable date: " + source, pp.getErrorIndex());
        }
        return date;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String source, ParsePosition pos) {
        return parse(source, pos);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String source, ParsePosition pos) {
        Calendar cal = Calendar.getInstance(this.timeZone, this.locale);
        cal.clear();
        if (parse(source, pos, cal)) {
            return cal.getTime();
        }
        return null;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public boolean parse(String source, ParsePosition pos, Calendar calendar) {
        ListIterator<StrategyAndWidth> lt = this.patterns.listIterator();
        while (lt.hasNext()) {
            StrategyAndWidth strategyAndWidth = lt.next();
            int maxWidth = strategyAndWidth.getMaxWidth(lt);
            if (!strategyAndWidth.strategy.parse(this, calendar, source, pos, maxWidth)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StringBuilder simpleQuote(StringBuilder sb, String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '$':
                case '(':
                case ')':
                case '*':
                case '+':
                case '.':
                case '?':
                case '[':
                case '\\':
                case '^':
                case '{':
                case '|':
                    sb.append('\\');
                    break;
            }
            sb.append(c);
        }
        if (sb.charAt(sb.length() - 1) == '.') {
            sb.append('?');
        }
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Integer> appendDisplayNames(Calendar cal, Locale locale, int field, StringBuilder regex) {
        Map<String, Integer> values = new HashMap<>();
        Map<String, Integer> displayNames = cal.getDisplayNames(field, 0, locale);
        TreeSet<String> sorted = new TreeSet<>(LONGER_FIRST_LOWERCASE);
        for (Map.Entry<String, Integer> displayName : displayNames.entrySet()) {
            String key = displayName.getKey().toLowerCase(locale);
            if (sorted.add(key)) {
                values.put(key, displayName.getValue());
            }
        }
        Iterator<String> it = sorted.iterator();
        while (it.hasNext()) {
            String symbol = it.next();
            simpleQuote(regex, symbol).append('|');
        }
        return values;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int adjustYear(int twoDigitYear) {
        int trial = this.century + twoDigitYear;
        return twoDigitYear >= this.startYear ? trial : trial + 100;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$Strategy.class */
    private static abstract class Strategy {
        abstract boolean parse(FastDateParser fastDateParser, Calendar calendar, String str, ParsePosition parsePosition, int i);

        private Strategy() {
        }

        boolean isNumber() {
            return false;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$PatternStrategy.class */
    private static abstract class PatternStrategy extends Strategy {
        private Pattern pattern;

        abstract void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str);

        private PatternStrategy() {
            super();
        }

        void createPattern(StringBuilder regex) {
            createPattern(regex.toString());
        }

        void createPattern(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return false;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
            Matcher matcher = this.pattern.matcher(source.substring(pos.getIndex()));
            if (!matcher.lookingAt()) {
                pos.setErrorIndex(pos.getIndex());
                return false;
            }
            pos.setIndex(pos.getIndex() + matcher.end(1));
            setCalendar(parser, calendar, matcher.group(1));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Strategy getStrategy(char f, int width, Calendar definingCalendar) {
        switch (f) {
            case 'D':
                return DAY_OF_YEAR_STRATEGY;
            case 'E':
                return getLocaleSpecificStrategy(7, definingCalendar);
            case 'F':
                return DAY_OF_WEEK_IN_MONTH_STRATEGY;
            case 'G':
                return getLocaleSpecificStrategy(0, definingCalendar);
            case 'H':
                return HOUR_OF_DAY_STRATEGY;
            case 'I':
            case 'J':
            case 'L':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'V':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'b':
            case 'c':
            case 'e':
            case 'f':
            case 'g':
            case 'i':
            case 'j':
            case 'l':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 't':
            case 'v':
            case 'x':
            default:
                throw new IllegalArgumentException("Format '" + f + "' not supported");
            case 'K':
                return HOUR_STRATEGY;
            case 'M':
                return width >= 3 ? getLocaleSpecificStrategy(2, definingCalendar) : NUMBER_MONTH_STRATEGY;
            case 'S':
                return MILLISECOND_STRATEGY;
            case 'W':
                return WEEK_OF_MONTH_STRATEGY;
            case 'X':
                return ISO8601TimeZoneStrategy.getStrategy(width);
            case 'Y':
            case 'y':
                return width > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
            case 'Z':
                if (width == 2) {
                    return ISO8601TimeZoneStrategy.ISO_8601_3_STRATEGY;
                }
                break;
            case 'a':
                return getLocaleSpecificStrategy(9, definingCalendar);
            case 'd':
                return DAY_OF_MONTH_STRATEGY;
            case 'h':
                return HOUR12_STRATEGY;
            case 'k':
                return HOUR24_OF_DAY_STRATEGY;
            case 'm':
                return MINUTE_STRATEGY;
            case 's':
                return SECOND_STRATEGY;
            case 'u':
                return DAY_OF_WEEK_STRATEGY;
            case 'w':
                return WEEK_OF_YEAR_STRATEGY;
            case 'z':
                break;
        }
        return getLocaleSpecificStrategy(15, definingCalendar);
    }

    private static ConcurrentMap<Locale, Strategy> getCache(int field) {
        ConcurrentMap<Locale, Strategy> concurrentMap;
        synchronized (caches) {
            if (caches[field] == null) {
                caches[field] = new ConcurrentHashMap(3);
            }
            concurrentMap = caches[field];
        }
        return concurrentMap;
    }

    private Strategy getLocaleSpecificStrategy(int field, Calendar definingCalendar) {
        ConcurrentMap<Locale, Strategy> cache = getCache(field);
        Strategy strategy = cache.get(this.locale);
        if (strategy == null) {
            strategy = field == 15 ? new TimeZoneStrategy(this.locale) : new CaseInsensitiveTextStrategy(field, definingCalendar, this.locale);
            Strategy inCache = cache.putIfAbsent(this.locale, strategy);
            if (inCache != null) {
                return inCache;
            }
        }
        return strategy;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$CopyQuotedStrategy.class */
    private static class CopyQuotedStrategy extends Strategy {
        private final String formatField;

        CopyQuotedStrategy(String formatField) {
            super();
            this.formatField = formatField;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return false;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
            for (int idx = 0; idx < this.formatField.length(); idx++) {
                int sIdx = idx + pos.getIndex();
                if (sIdx == source.length()) {
                    pos.setErrorIndex(sIdx);
                    return false;
                }
                if (this.formatField.charAt(idx) != source.charAt(sIdx)) {
                    pos.setErrorIndex(sIdx);
                    return false;
                }
            }
            pos.setIndex(this.formatField.length() + pos.getIndex());
            return true;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$CaseInsensitiveTextStrategy.class */
    private static class CaseInsensitiveTextStrategy extends PatternStrategy {
        private final int field;
        final Locale locale;
        private final Map<String, Integer> lKeyValues;

        CaseInsensitiveTextStrategy(int field, Calendar definingCalendar, Locale locale) {
            super();
            this.field = field;
            this.locale = locale;
            StringBuilder regex = new StringBuilder();
            regex.append("((?iu)");
            this.lKeyValues = FastDateParser.appendDisplayNames(definingCalendar, locale, field, regex);
            regex.setLength(regex.length() - 1);
            regex.append(")");
            createPattern(regex);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser parser, Calendar cal, String value) {
            String lowerCase = value.toLowerCase(this.locale);
            Integer iVal = this.lKeyValues.get(lowerCase);
            if (iVal == null) {
                iVal = this.lKeyValues.get(lowerCase + '.');
            }
            cal.set(this.field, iVal.intValue());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$NumberStrategy.class */
    private static class NumberStrategy extends Strategy {
        private final int field;

        NumberStrategy(int field) {
            super();
            this.field = field;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return true;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) throws NumberFormatException {
            int idx = pos.getIndex();
            int last = source.length();
            if (maxWidth == 0) {
                while (idx < last) {
                    char c = source.charAt(idx);
                    if (!Character.isWhitespace(c)) {
                        break;
                    }
                    idx++;
                }
                pos.setIndex(idx);
            } else {
                int end = idx + maxWidth;
                if (last > end) {
                    last = end;
                }
            }
            while (idx < last) {
                char c2 = source.charAt(idx);
                if (!Character.isDigit(c2)) {
                    break;
                }
                idx++;
            }
            if (pos.getIndex() == idx) {
                pos.setErrorIndex(idx);
                return false;
            }
            int value = Integer.parseInt(source.substring(pos.getIndex(), idx));
            pos.setIndex(idx);
            calendar.set(this.field, modify(parser, value));
            return true;
        }

        int modify(FastDateParser parser, int iValue) {
            return iValue;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$TimeZoneStrategy.class */
    static class TimeZoneStrategy extends PatternStrategy {
        private static final String RFC_822_TIME_ZONE = "[+-]\\d{4}";
        private static final String GMT_OPTION = "GMT[+-]\\d{1,2}:\\d{2}";
        private final Locale locale;
        private final Map<String, TzInfo> tzNames;
        private static final int ID = 0;

        /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$TimeZoneStrategy$TzInfo.class */
        private static class TzInfo {
            TimeZone zone;
            int dstOffset;

            TzInfo(TimeZone tz, boolean useDst) {
                this.zone = tz;
                this.dstOffset = useDst ? tz.getDSTSavings() : 0;
            }
        }

        TimeZoneStrategy(Locale locale) {
            super();
            this.tzNames = new HashMap();
            this.locale = locale;
            StringBuilder sb = new StringBuilder();
            sb.append("((?iu)[+-]\\d{4}|GMT[+-]\\d{1,2}:\\d{2}");
            Set<String> sorted = new TreeSet<>((Comparator<? super String>) FastDateParser.LONGER_FIRST_LOWERCASE);
            String[][] zones = DateFormatSymbols.getInstance(locale).getZoneStrings();
            for (String[] zoneNames : zones) {
                String tzId = zoneNames[0];
                if (!tzId.equalsIgnoreCase(TimeZones.GMT_ID)) {
                    TimeZone tz = TimeZone.getTimeZone(tzId);
                    TzInfo standard = new TzInfo(tz, false);
                    TzInfo tzInfo = standard;
                    for (int i = 1; i < zoneNames.length; i++) {
                        switch (i) {
                            case 3:
                                tzInfo = new TzInfo(tz, true);
                                break;
                            case 5:
                                tzInfo = standard;
                                break;
                        }
                        if (zoneNames[i] != null) {
                            String key = zoneNames[i].toLowerCase(locale);
                            if (sorted.add(key)) {
                                this.tzNames.put(key, tzInfo);
                            }
                        }
                    }
                }
            }
            for (String zoneName : sorted) {
                FastDateParser.simpleQuote(sb.append('|'), zoneName);
            }
            sb.append(")");
            createPattern(sb);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser parser, Calendar cal, String timeZone) {
            TimeZone tz = FastTimeZone.getGmtTimeZone(timeZone);
            if (tz != null) {
                cal.setTimeZone(tz);
                return;
            }
            String lowerCase = timeZone.toLowerCase(this.locale);
            TzInfo tzInfo = this.tzNames.get(lowerCase);
            if (tzInfo == null) {
                tzInfo = this.tzNames.get(lowerCase + '.');
            }
            cal.set(16, tzInfo.dstOffset);
            cal.set(15, tzInfo.zone.getRawOffset());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/time/FastDateParser$ISO8601TimeZoneStrategy.class */
    private static class ISO8601TimeZoneStrategy extends PatternStrategy {
        private static final Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}))");
        private static final Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}\\d{2}))");
        private static final Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::)\\d{2}))");

        ISO8601TimeZoneStrategy(String pattern) {
            super();
            createPattern(pattern);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser parser, Calendar cal, String value) {
            cal.setTimeZone(FastTimeZone.getGmtTimeZone(value));
        }

        static Strategy getStrategy(int tokenLen) {
            switch (tokenLen) {
                case 1:
                    return ISO_8601_1_STRATEGY;
                case 2:
                    return ISO_8601_2_STRATEGY;
                case 3:
                    return ISO_8601_3_STRATEGY;
                default:
                    throw new IllegalArgumentException("invalid number of X");
            }
        }
    }
}
