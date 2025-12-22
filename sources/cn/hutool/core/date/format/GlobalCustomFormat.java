package cn.hutool.core.date.format;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/format/GlobalCustomFormat.class */
public class GlobalCustomFormat {
    public static final String FORMAT_SECONDS = "#sss";
    public static final String FORMAT_MILLISECONDS = "#SSS";
    private static final Map<CharSequence, Function<Date, String>> formatterMap = new ConcurrentHashMap();
    private static final Map<CharSequence, Function<CharSequence, Date>> parserMap = new ConcurrentHashMap();

    static {
        putFormatter(FORMAT_SECONDS, date -> {
            return String.valueOf(Math.floorDiv(date.getTime(), 1000L));
        });
        putParser(FORMAT_SECONDS, dateStr -> {
            return DateUtil.date(Math.multiplyExact(Long.parseLong(dateStr.toString()), 1000L));
        });
        putFormatter(FORMAT_MILLISECONDS, date2 -> {
            return String.valueOf(date2.getTime());
        });
        putParser(FORMAT_MILLISECONDS, dateStr2 -> {
            return DateUtil.date(Long.parseLong(dateStr2.toString()));
        });
    }

    public static void putFormatter(String format, Function<Date, String> func) throws IllegalArgumentException {
        Assert.notNull(format, "Format must be not null !", new Object[0]);
        Assert.notNull(func, "Function must be not null !", new Object[0]);
        formatterMap.put(format, func);
    }

    public static void putParser(String format, Function<CharSequence, Date> func) throws IllegalArgumentException {
        Assert.notNull(format, "Format must be not null !", new Object[0]);
        Assert.notNull(func, "Function must be not null !", new Object[0]);
        parserMap.put(format, func);
    }

    public static boolean isCustomFormat(String format) {
        return formatterMap.containsKey(format);
    }

    public static String format(Date date, CharSequence format) {
        Function<Date, String> func;
        if (null != formatterMap && null != (func = formatterMap.get(format))) {
            return func.apply(date);
        }
        return null;
    }

    public static String format(TemporalAccessor temporalAccessor, CharSequence format) {
        return format(DateUtil.date(temporalAccessor), format);
    }

    public static Date parse(CharSequence dateStr, String format) {
        Function<CharSequence, Date> func;
        if (null != parserMap && null != (func = parserMap.get(format))) {
            return func.apply(dateStr);
        }
        return null;
    }
}
