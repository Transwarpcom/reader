package cn.hutool.core.date;

import cn.hutool.core.date.format.GlobalCustomFormat;
import cn.hutool.core.util.StrUtil;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.UnsupportedTemporalTypeException;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/date/TemporalAccessorUtil.class */
public class TemporalAccessorUtil extends TemporalUtil {
    public static int get(TemporalAccessor temporalAccessor, TemporalField field) {
        if (temporalAccessor.isSupported(field)) {
            return temporalAccessor.get(field);
        }
        return (int) field.range().getMinimum();
    }

    public static String format(TemporalAccessor time, DateTimeFormatter formatter) {
        if (null == time) {
            return null;
        }
        if (time instanceof java.time.Month) {
            return time.toString();
        }
        if (null == formatter) {
            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        }
        try {
            return formatter.format(time);
        } catch (UnsupportedTemporalTypeException e) {
            if ((time instanceof LocalDate) && e.getMessage().contains("HourOfDay")) {
                return formatter.format(((LocalDate) time).atStartOfDay());
            }
            if ((time instanceof LocalTime) && e.getMessage().contains("YearOfEra")) {
                return formatter.format(((LocalTime) time).atDate(LocalDate.now()));
            }
            if (time instanceof Instant) {
                return formatter.format(((Instant) time).atZone(ZoneId.systemDefault()));
            }
            throw e;
        }
    }

    public static String format(TemporalAccessor time, String format) {
        if (null == time) {
            return null;
        }
        if (time instanceof java.time.Month) {
            return time.toString();
        }
        if (GlobalCustomFormat.isCustomFormat(format)) {
            return GlobalCustomFormat.format(time, format);
        }
        DateTimeFormatter formatter = StrUtil.isBlank(format) ? null : DateTimeFormatter.ofPattern(format);
        return format(time, formatter);
    }

    public static long toEpochMilli(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof java.time.Month) {
            return ((java.time.Month) temporalAccessor).getValue();
        }
        return toInstant(temporalAccessor).toEpochMilli();
    }

    /* JADX WARN: Type inference failed for: r0v25, types: [java.time.ZonedDateTime] */
    /* JADX WARN: Type inference failed for: r0v39, types: [java.time.ZonedDateTime] */
    public static Instant toInstant(TemporalAccessor temporalAccessor) {
        Instant result;
        if (null == temporalAccessor) {
            return null;
        }
        if (temporalAccessor instanceof Instant) {
            result = (Instant) temporalAccessor;
        } else if (temporalAccessor instanceof LocalDateTime) {
            result = ((LocalDateTime) temporalAccessor).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof ZonedDateTime) {
            result = ((ZonedDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof OffsetDateTime) {
            result = ((OffsetDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof LocalDate) {
            result = ((LocalDate) temporalAccessor).atStartOfDay(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof LocalTime) {
            result = ((LocalTime) temporalAccessor).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof OffsetTime) {
            result = ((OffsetTime) temporalAccessor).atDate(LocalDate.now()).toInstant();
        } else {
            result = toInstant(LocalDateTimeUtil.of(temporalAccessor));
        }
        return result;
    }
}
