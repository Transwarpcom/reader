package cn.hutool.core.date.format;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Tuple;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/format/FormatCache.class */
abstract class FormatCache<F extends Format> {
    static final int NONE = -1;
    private final ConcurrentMap<Tuple, F> cInstanceCache = new ConcurrentHashMap(7);
    private static final ConcurrentMap<Tuple, String> C_DATE_TIME_INSTANCE_CACHE = new ConcurrentHashMap(7);

    protected abstract F createInstance(String str, TimeZone timeZone, Locale locale);

    FormatCache() {
    }

    public F getInstance() {
        return (F) getDateTimeInstance(3, 3, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.text.Format] */
    public F getInstance(String pattern, TimeZone timeZone, Locale locale) throws IllegalArgumentException {
        Assert.notBlank(pattern, "pattern must not be blank", new Object[0]);
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        Tuple key = new Tuple(pattern, timeZone, locale);
        F format = this.cInstanceCache.get(key);
        if (format == null) {
            format = createInstance(pattern, timeZone, locale);
            F previousValue = this.cInstanceCache.putIfAbsent(key, format);
            if (previousValue != null) {
                format = previousValue;
            }
        }
        return format;
    }

    F getDateTimeInstance(Integer num, Integer num2, TimeZone timeZone, Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return (F) getInstance(getPatternForStyle(num, num2, locale), timeZone, locale);
    }

    F getDateInstance(int i, TimeZone timeZone, Locale locale) {
        return (F) getDateTimeInstance(Integer.valueOf(i), null, timeZone, locale);
    }

    F getTimeInstance(int i, TimeZone timeZone, Locale locale) {
        return (F) getDateTimeInstance(null, Integer.valueOf(i), timeZone, locale);
    }

    static String getPatternForStyle(Integer dateStyle, Integer timeStyle, Locale locale) {
        DateFormat formatter;
        Tuple key = new Tuple(dateStyle, timeStyle, locale);
        String pattern = C_DATE_TIME_INSTANCE_CACHE.get(key);
        if (pattern == null) {
            try {
                if (dateStyle == null) {
                    formatter = DateFormat.getTimeInstance(timeStyle.intValue(), locale);
                } else if (timeStyle == null) {
                    formatter = DateFormat.getDateInstance(dateStyle.intValue(), locale);
                } else {
                    formatter = DateFormat.getDateTimeInstance(dateStyle.intValue(), timeStyle.intValue(), locale);
                }
                pattern = ((SimpleDateFormat) formatter).toPattern();
                String previous = C_DATE_TIME_INSTANCE_CACHE.putIfAbsent(key, pattern);
                if (previous != null) {
                    pattern = previous;
                }
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("No date time pattern for locale: " + locale);
            }
        }
        return pattern;
    }
}
