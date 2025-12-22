package net.minidev.asm;

import cn.hutool.core.date.chinese.LunarInfo;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeMap;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/accessors-smart-2.4.7.jar:net/minidev/asm/ConvertDate.class */
public class ConvertDate {
    public static TimeZone defaultTimeZone;
    static TreeMap<String, Integer> monthsTable = new TreeMap<>(new StringCmpNS());
    static TreeMap<String, Integer> daysTable = new TreeMap<>(new StringCmpNS());
    private static HashSet<String> voidData = new HashSet<>();
    static TreeMap<String, TimeZone> timeZoneMapping = new TreeMap<>();

    static {
        voidData.add("à");
        voidData.add("at");
        voidData.add("MEZ");
        voidData.add("Uhr");
        voidData.add(OperatorName.CLOSE_PATH);
        voidData.add("pm");
        voidData.add("PM");
        voidData.add("am");
        voidData.add("AM");
        voidData.add("min");
        voidData.add("um");
        voidData.add("o'clock");
        for (String tz : TimeZone.getAvailableIDs()) {
            timeZoneMapping.put(tz, TimeZone.getTimeZone(tz));
        }
        for (Locale locale : DateFormatSymbols.getAvailableLocales()) {
            if (!"ja".equals(locale.getLanguage()) && !"ko".equals(locale.getLanguage()) && !"zh".equals(locale.getLanguage())) {
                DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
                String[] keys = dfs.getMonths();
                for (int i = 0; i < keys.length; i++) {
                    if (keys[i].length() != 0) {
                        fillMap(monthsTable, keys[i], Integer.valueOf(i));
                    }
                }
                String[] keys2 = dfs.getShortMonths();
                for (int i2 = 0; i2 < keys2.length; i2++) {
                    String s = keys2[i2];
                    if (s.length() != 0 && !Character.isDigit(s.charAt(s.length() - 1))) {
                        fillMap(monthsTable, keys2[i2], Integer.valueOf(i2));
                        fillMap(monthsTable, keys2[i2].replace(".", ""), Integer.valueOf(i2));
                    }
                }
                String[] keys3 = dfs.getWeekdays();
                for (int i3 = 0; i3 < keys3.length; i3++) {
                    String s2 = keys3[i3];
                    if (s2.length() != 0) {
                        fillMap(daysTable, s2, Integer.valueOf(i3));
                        fillMap(daysTable, s2.replace(".", ""), Integer.valueOf(i3));
                    }
                }
                String[] keys4 = dfs.getShortWeekdays();
                for (int i4 = 0; i4 < keys4.length; i4++) {
                    String s3 = keys4[i4];
                    if (s3.length() != 0) {
                        fillMap(daysTable, s3, Integer.valueOf(i4));
                        fillMap(daysTable, s3.replace(".", ""), Integer.valueOf(i4));
                    }
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/accessors-smart-2.4.7.jar:net/minidev/asm/ConvertDate$StringCmpNS.class */
    public static class StringCmpNS implements Comparator<String> {
        @Override // java.util.Comparator
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    public static Integer getMonth(String month) {
        return monthsTable.get(month);
    }

    private static Integer parseMonth(String s1) {
        if (Character.isDigit(s1.charAt(0))) {
            return Integer.valueOf(Integer.parseInt(s1) - 1);
        }
        Integer month = monthsTable.get(s1);
        if (month == null) {
            throw new NullPointerException("can not parse " + s1 + " as month");
        }
        return Integer.valueOf(month.intValue());
    }

    private static GregorianCalendar newCalandar() {
        GregorianCalendar cal = new GregorianCalendar(2000, 0, 0, 0, 0, 0);
        if (defaultTimeZone != null) {
            cal.setTimeZone(defaultTimeZone);
        }
        TimeZone TZ = cal.getTimeZone();
        if (TZ == null) {
            TZ = TimeZone.getDefault();
        }
        cal.setTimeInMillis(-TZ.getRawOffset());
        return cal;
    }

    private static void fillMap(TreeMap<String, Integer> map, String key, Integer value) {
        map.put(key, value);
        map.put(key.replace("é", "e").replace("û", "u"), value);
    }

    public static Date convertToDate(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Number) {
            return new Date(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            StringTokenizer st = new StringTokenizer(((String) obj).replace("p.m.", "pm").replace("a.m.", "am"), " -/:,.+年月日曜時分秒");
            if (!st.hasMoreTokens()) {
                return null;
            }
            String s1 = st.nextToken();
            if (s1.length() == 4 && Character.isDigit(s1.charAt(0))) {
                return getYYYYMMDD(st, s1);
            }
            if (daysTable.containsKey(s1)) {
                if (!st.hasMoreTokens()) {
                    return null;
                }
                s1 = st.nextToken();
            }
            if (monthsTable.containsKey(s1)) {
                return getMMDDYYYY(st, s1);
            }
            if (Character.isDigit(s1.charAt(0))) {
                return getDDMMYYYY(st, s1);
            }
            return null;
        }
        throw new RuntimeException("Primitive: Can not convert " + obj.getClass().getName() + " to int");
    }

    private static Date getYYYYMMDD(StringTokenizer st, String s1) throws NumberFormatException {
        GregorianCalendar cal = newCalandar();
        int year = Integer.parseInt(s1);
        cal.set(1, year);
        if (!st.hasMoreTokens()) {
            return cal.getTime();
        }
        String s12 = st.nextToken();
        cal.set(2, parseMonth(s12).intValue());
        if (!st.hasMoreTokens()) {
            return cal.getTime();
        }
        String s13 = st.nextToken();
        if (Character.isDigit(s13.charAt(0))) {
            if (s13.length() == 5 && s13.charAt(2) == 'T') {
                int day = Integer.parseInt(s13.substring(0, 2));
                cal.set(5, day);
                return addHour(st, cal, s13.substring(3));
            }
            int day2 = Integer.parseInt(s13);
            cal.set(5, day2);
            return addHour(st, cal, null);
        }
        return cal.getTime();
    }

    private static int getYear(String s1) throws NumberFormatException {
        int year = Integer.parseInt(s1);
        if (year < 100) {
            if (year > 30) {
                year += 2000;
            } else {
                year += LunarInfo.BASE_YEAR;
            }
        }
        return year;
    }

    private static Date getMMDDYYYY(StringTokenizer st, String s1) throws NumberFormatException {
        GregorianCalendar cal = newCalandar();
        Integer month = monthsTable.get(s1);
        if (month == null) {
            throw new NullPointerException("can not parse " + s1 + " as month");
        }
        cal.set(2, month.intValue());
        if (!st.hasMoreTokens()) {
            return null;
        }
        int day = Integer.parseInt(st.nextToken());
        cal.set(5, day);
        if (!st.hasMoreTokens()) {
            return null;
        }
        String s12 = st.nextToken();
        if (Character.isLetter(s12.charAt(0))) {
            if (!st.hasMoreTokens()) {
                return null;
            }
            s12 = st.nextToken();
        }
        if (s12.length() == 4) {
            cal.set(1, getYear(s12));
        } else if (s12.length() == 2) {
            return addHour2(st, cal, s12);
        }
        return addHour(st, cal, null);
    }

    private static Date getDDMMYYYY(StringTokenizer st, String s1) throws NumberFormatException {
        GregorianCalendar cal = newCalandar();
        int day = Integer.parseInt(s1);
        cal.set(5, day);
        if (!st.hasMoreTokens()) {
            return null;
        }
        String s12 = st.nextToken();
        cal.set(2, parseMonth(s12).intValue());
        if (!st.hasMoreTokens()) {
            return null;
        }
        String s13 = st.nextToken();
        cal.set(1, getYear(s13));
        return addHour(st, cal, null);
    }

    private static Date addHour(StringTokenizer st, Calendar cal, String s1) {
        if (s1 == null) {
            if (!st.hasMoreTokens()) {
                return cal.getTime();
            }
            s1 = st.nextToken();
        }
        return addHour2(st, cal, s1);
    }

    private static Date addHour2(StringTokenizer st, Calendar cal, String s1) {
        cal.set(11, Integer.parseInt(trySkip(st, s1, cal)));
        if (!st.hasMoreTokens()) {
            return cal.getTime();
        }
        String s12 = st.nextToken();
        String s13 = trySkip(st, s12, cal);
        if (s13 == null) {
            return cal.getTime();
        }
        cal.set(12, Integer.parseInt(s13));
        if (!st.hasMoreTokens()) {
            return cal.getTime();
        }
        String s14 = st.nextToken();
        String s15 = trySkip(st, s14, cal);
        if (s15 == null) {
            return cal.getTime();
        }
        cal.set(13, Integer.parseInt(s15));
        if (!st.hasMoreTokens()) {
            return cal.getTime();
        }
        String s16 = st.nextToken();
        String s17 = trySkip(st, s16, cal);
        if (s17 == null) {
            return cal.getTime();
        }
        String s18 = trySkip(st, s17, cal);
        if (s18.length() == 4 && Character.isDigit(s18.charAt(0))) {
            cal.set(1, getYear(s18));
        }
        return cal.getTime();
    }

    private static String trySkip(StringTokenizer st, String s1, Calendar cal) {
        while (true) {
            TimeZone tz = timeZoneMapping.get(s1);
            if (tz != null) {
                cal.setTimeZone(tz);
                if (!st.hasMoreTokens()) {
                    return null;
                }
                s1 = st.nextToken();
            } else if (voidData.contains(s1)) {
                if (s1.equalsIgnoreCase("pm")) {
                    cal.add(9, 1);
                }
                if (s1.equalsIgnoreCase("am")) {
                    cal.add(9, 0);
                }
                if (!st.hasMoreTokens()) {
                    return null;
                }
                s1 = st.nextToken();
            } else {
                return s1;
            }
        }
    }
}
