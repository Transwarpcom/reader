package org.bson.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/DateTimeFormatter.class */
final class DateTimeFormatter {
    private static final FormatterImpl FORMATTER_IMPL;

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/DateTimeFormatter$FormatterImpl.class */
    private interface FormatterImpl {
        long parse(String str);

        String format(long j);
    }

    static {
        FormatterImpl dateTimeHelper;
        try {
            dateTimeHelper = loadDateTimeFormatter("org.bson.json.DateTimeFormatter$Java8DateTimeFormatter");
        } catch (LinkageError e) {
            dateTimeHelper = loadDateTimeFormatter("org.bson.json.DateTimeFormatter$JaxbDateTimeFormatter");
        }
        FORMATTER_IMPL = dateTimeHelper;
    }

    private static FormatterImpl loadDateTimeFormatter(String className) {
        try {
            return (FormatterImpl) Class.forName(className).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        } catch (IllegalAccessException e2) {
            throw new ExceptionInInitializerError(e2);
        } catch (InstantiationException e3) {
            throw new ExceptionInInitializerError(e3);
        } catch (NoSuchMethodException e4) {
            throw new ExceptionInInitializerError(e4);
        } catch (InvocationTargetException e5) {
            throw new ExceptionInInitializerError(e5);
        }
    }

    static long parse(String dateTimeString) {
        return FORMATTER_IMPL.parse(dateTimeString);
    }

    static String format(long dateTime) {
        return FORMATTER_IMPL.format(dateTime);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/DateTimeFormatter$JaxbDateTimeFormatter.class */
    static class JaxbDateTimeFormatter implements FormatterImpl {
        private static final Method DATATYPE_CONVERTER_PARSE_DATE_TIME_METHOD;
        private static final Method DATATYPE_CONVERTER_PRINT_DATE_TIME_METHOD;

        JaxbDateTimeFormatter() {
        }

        static {
            try {
                DATATYPE_CONVERTER_PARSE_DATE_TIME_METHOD = Class.forName("javax.xml.bind.DatatypeConverter").getDeclaredMethod("parseDateTime", String.class);
                DATATYPE_CONVERTER_PRINT_DATE_TIME_METHOD = Class.forName("javax.xml.bind.DatatypeConverter").getDeclaredMethod("printDateTime", Calendar.class);
            } catch (ClassNotFoundException e) {
                throw new ExceptionInInitializerError(e);
            } catch (NoSuchMethodException e2) {
                throw new ExceptionInInitializerError(e2);
            }
        }

        @Override // org.bson.json.DateTimeFormatter.FormatterImpl
        public long parse(String dateTimeString) {
            try {
                return ((Calendar) DATATYPE_CONVERTER_PARSE_DATE_TIME_METHOD.invoke(null, dateTimeString)).getTimeInMillis();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(e);
            } catch (InvocationTargetException e2) {
                throw ((RuntimeException) e2.getCause());
            }
        }

        @Override // org.bson.json.DateTimeFormatter.FormatterImpl
        public String format(long dateTime) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dateTime);
            calendar.setTimeZone(TimeZone.getTimeZone("Z"));
            try {
                return (String) DATATYPE_CONVERTER_PRINT_DATE_TIME_METHOD.invoke(null, calendar);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException();
            } catch (InvocationTargetException e2) {
                throw ((RuntimeException) e2.getCause());
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/DateTimeFormatter$Java8DateTimeFormatter.class */
    static class Java8DateTimeFormatter implements FormatterImpl {
        Java8DateTimeFormatter() {
        }

        static {
            try {
                Class.forName("java.time.format.DateTimeFormatter");
            } catch (ClassNotFoundException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        @Override // org.bson.json.DateTimeFormatter.FormatterImpl
        public long parse(String dateTimeString) {
            try {
                return ((Instant) java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateTimeString, new TemporalQuery<Instant>() { // from class: org.bson.json.DateTimeFormatter.Java8DateTimeFormatter.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.time.temporal.TemporalQuery
                    public Instant queryFrom(TemporalAccessor temporal) {
                        return Instant.from(temporal);
                    }
                })).toEpochMilli();
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        @Override // org.bson.json.DateTimeFormatter.FormatterImpl
        public String format(long dateTime) {
            return ZonedDateTime.ofInstant(Instant.ofEpochMilli(dateTime), ZoneId.of("Z")).format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
    }

    private DateTimeFormatter() {
    }
}
