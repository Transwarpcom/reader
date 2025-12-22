package cn.hutool.core.lang;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Map;
import java.util.function.Supplier;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/Assert.class */
public class Assert {
    private static final String TEMPLATE_VALUE_MUST_BE_BETWEEN_AND = "The value must be between {} and {}.";

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <X extends Throwable> void isTrue(boolean expression, Supplier<? extends X> supplier) throws Throwable {
        if (false == expression) {
            throw supplier.get();
        }
    }

    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) throws Throwable {
        isTrue(expression, () -> {
            return new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        });
    }

    public static void isTrue(boolean expression) throws Throwable {
        isTrue(expression, "[Assertion failed] - this expression must be true", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <X extends Throwable> void isFalse(boolean expression, Supplier<X> errorSupplier) throws Throwable {
        if (expression) {
            throw errorSupplier.get();
        }
    }

    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) throws Throwable {
        isFalse(expression, () -> {
            return new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        });
    }

    public static void isFalse(boolean expression) throws Throwable {
        isFalse(expression, "[Assertion failed] - this expression must be false", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <X extends Throwable> void isNull(Object object, Supplier<X> errorSupplier) throws Throwable {
        if (null != object) {
            throw errorSupplier.get();
        }
    }

    public static void isNull(Object object, String errorMsgTemplate, Object... params) throws Throwable {
        isNull(object, () -> {
            return new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        });
    }

    public static void isNull(Object object) throws Throwable {
        isNull(object, "[Assertion failed] - the object argument must be null", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T, X extends Throwable> T notNull(T object, Supplier<X> errorSupplier) throws Throwable {
        if (null == object) {
            throw errorSupplier.get();
        }
        return object;
    }

    public static <T> T notNull(T t, String str, Object... objArr) throws IllegalArgumentException {
        return (T) notNull(t, () -> {
            return new IllegalArgumentException(StrUtil.format(str, objArr));
        });
    }

    public static <T> T notNull(T t) throws IllegalArgumentException {
        return (T) notNull(t, "[Assertion failed] - this argument is required; it must not be null", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T extends CharSequence, X extends Throwable> T notEmpty(T text, Supplier<X> errorSupplier) throws Throwable {
        if (StrUtil.isEmpty(text)) {
            throw errorSupplier.get();
        }
        return text;
    }

    public static <T extends CharSequence> T notEmpty(T t, String str, Object... objArr) throws IllegalArgumentException {
        return (T) notEmpty(t, () -> {
            return new IllegalArgumentException(StrUtil.format(str, objArr));
        });
    }

    public static <T extends CharSequence> T notEmpty(T t) throws IllegalArgumentException {
        return (T) notEmpty(t, "[Assertion failed] - this String argument must have length; it must not be null or empty", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T extends CharSequence, X extends Throwable> T notBlank(T text, Supplier<X> errorMsgSupplier) throws Throwable {
        if (StrUtil.isBlank(text)) {
            throw errorMsgSupplier.get();
        }
        return text;
    }

    public static <T extends CharSequence> T notBlank(T t, String str, Object... objArr) throws IllegalArgumentException {
        return (T) notBlank(t, () -> {
            return new IllegalArgumentException(StrUtil.format(str, objArr));
        });
    }

    public static <T extends CharSequence> T notBlank(T t) throws IllegalArgumentException {
        return (T) notBlank(t, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T extends CharSequence, X extends Throwable> T notContain(CharSequence textToSearch, T substring, Supplier<X> errorSupplier) throws Throwable {
        if (StrUtil.contains(textToSearch, substring)) {
            throw errorSupplier.get();
        }
        return substring;
    }

    public static String notContain(String textToSearch, String substring, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        return (String) notContain(textToSearch, substring, () -> {
            return new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        });
    }

    public static String notContain(String textToSearch, String substring) throws IllegalArgumentException {
        return notContain(textToSearch, substring, "[Assertion failed] - this String argument must not contain the substring [{}]", substring);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T, X extends Throwable> T[] notEmpty(T[] array, Supplier<X> errorSupplier) throws Throwable {
        if (ArrayUtil.isEmpty((Object[]) array)) {
            throw errorSupplier.get();
        }
        return array;
    }

    public static <T> T[] notEmpty(T[] tArr, String str, Object... objArr) throws IllegalArgumentException {
        return (T[]) notEmpty(tArr, () -> {
            return new IllegalArgumentException(StrUtil.format(str, objArr));
        });
    }

    public static <T> T[] notEmpty(T[] tArr) throws IllegalArgumentException {
        return (T[]) notEmpty(tArr, "[Assertion failed] - this array must not be empty: it must contain at least 1 element", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T, X extends Throwable> T[] noNullElements(T[] array, Supplier<X> errorSupplier) throws Throwable {
        if (ArrayUtil.hasNull(array)) {
            throw errorSupplier.get();
        }
        return array;
    }

    public static <T> T[] noNullElements(T[] tArr, String str, Object... objArr) throws IllegalArgumentException {
        return (T[]) noNullElements(tArr, () -> {
            return new IllegalArgumentException(StrUtil.format(str, objArr));
        });
    }

    public static <T> T[] noNullElements(T[] tArr) throws IllegalArgumentException {
        return (T[]) noNullElements(tArr, "[Assertion failed] - this array must not contain any null elements", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <E, T extends Iterable<E>, X extends Throwable> T notEmpty(T collection, Supplier<X> errorSupplier) throws Throwable {
        if (CollUtil.isEmpty((Iterable<?>) collection)) {
            throw errorSupplier.get();
        }
        return collection;
    }

    public static <E, T extends Iterable<E>> T notEmpty(T t, String str, Object... objArr) throws IllegalArgumentException {
        return (T) notEmpty(t, () -> {
            return new IllegalArgumentException(StrUtil.format(str, objArr));
        });
    }

    public static <E, T extends Iterable<E>> T notEmpty(T t) throws IllegalArgumentException {
        return (T) notEmpty(t, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element", new Object[0]);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <K, V, T extends Map<K, V>, X extends Throwable> T notEmpty(T map, Supplier<X> errorSupplier) throws Throwable {
        if (MapUtil.isEmpty(map)) {
            throw errorSupplier.get();
        }
        return map;
    }

    public static <K, V, T extends Map<K, V>> T notEmpty(T t, String str, Object... objArr) throws IllegalArgumentException {
        return (T) notEmpty(t, () -> {
            return new IllegalArgumentException(StrUtil.format(str, objArr));
        });
    }

    public static <K, V, T extends Map<K, V>> T notEmpty(T t) throws IllegalArgumentException {
        return (T) notEmpty(t, "[Assertion failed] - this map must not be empty; it must contain at least one entry", new Object[0]);
    }

    public static <T> T isInstanceOf(Class<?> cls, T t) {
        return (T) isInstanceOf(cls, t, "Object [{}] is not instanceof [{}]", t, cls);
    }

    public static <T> T isInstanceOf(Class<?> type, T obj, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        notNull(type, "Type to check against must not be null", new Object[0]);
        if (false == type.isInstance(obj)) {
            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        }
        return obj;
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) throws IllegalArgumentException {
        isAssignable(superType, subType, "{} is not assignable to {})", subType, superType);
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        notNull(superType, "Type to check against must not be null", new Object[0]);
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    public static void state(boolean expression, Supplier<String> errorMsgSupplier) throws IllegalStateException {
        if (false == expression) {
            throw new IllegalStateException(errorMsgSupplier.get());
        }
    }

    public static void state(boolean expression, String errorMsgTemplate, Object... params) throws IllegalStateException {
        if (false == expression) {
            throw new IllegalStateException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    public static void state(boolean expression) throws IllegalStateException {
        state(expression, "[Assertion failed] - this state invariant must be true", new Object[0]);
    }

    public static int checkIndex(int index, int size) throws IndexOutOfBoundsException, IllegalArgumentException {
        return checkIndex(index, size, "[Assertion failed]", new Object[0]);
    }

    public static int checkIndex(int index, int size, String errorMsgTemplate, Object... params) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(badIndexMsg(index, size, errorMsgTemplate, params));
        }
        return index;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <X extends Throwable> int checkBetween(int value, int min, int max, Supplier<? extends X> errorSupplier) throws Throwable {
        if (value < min || value > max) {
            throw errorSupplier.get();
        }
        return value;
    }

    public static int checkBetween(int value, int min, int max, String errorMsgTemplate, Object... params) {
        return checkBetween(value, min, max, () -> {
            return new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        });
    }

    public static int checkBetween(int value, int min, int max) {
        return checkBetween(value, min, max, TEMPLATE_VALUE_MUST_BE_BETWEEN_AND, Integer.valueOf(min), Integer.valueOf(max));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <X extends Throwable> long checkBetween(long value, long min, long max, Supplier<? extends X> errorSupplier) throws Throwable {
        if (value < min || value > max) {
            throw errorSupplier.get();
        }
        return value;
    }

    public static long checkBetween(long value, long min, long max, String errorMsgTemplate, Object... params) {
        return checkBetween(value, min, max, () -> {
            return new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        });
    }

    public static long checkBetween(long value, long min, long max) {
        return checkBetween(value, min, max, TEMPLATE_VALUE_MUST_BE_BETWEEN_AND, Long.valueOf(min), Long.valueOf(max));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <X extends Throwable> double checkBetween(double value, double min, double max, Supplier<? extends X> errorSupplier) throws Throwable {
        if (value < min || value > max) {
            throw errorSupplier.get();
        }
        return value;
    }

    public static double checkBetween(double value, double min, double max, String errorMsgTemplate, Object... params) {
        return checkBetween(value, min, max, () -> {
            return new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        });
    }

    public static double checkBetween(double value, double min, double max) {
        return checkBetween(value, min, max, TEMPLATE_VALUE_MUST_BE_BETWEEN_AND, Double.valueOf(min), Double.valueOf(max));
    }

    public static Number checkBetween(Number value, Number min, Number max) throws IllegalArgumentException {
        notNull(value);
        notNull(min);
        notNull(max);
        double valueDouble = value.doubleValue();
        double minDouble = min.doubleValue();
        double maxDouble = max.doubleValue();
        if (valueDouble < minDouble || valueDouble > maxDouble) {
            throw new IllegalArgumentException(StrUtil.format(TEMPLATE_VALUE_MUST_BE_BETWEEN_AND, min, max));
        }
        return value;
    }

    private static String badIndexMsg(int index, int size, String desc, Object... params) {
        if (index < 0) {
            return StrUtil.format("{} ({}) must not be negative", StrUtil.format(desc, params), Integer.valueOf(index));
        }
        if (size < 0) {
            throw new IllegalArgumentException("negative size: " + size);
        }
        return StrUtil.format("{} ({}) must be less than size ({})", StrUtil.format(desc, params), Integer.valueOf(index), Integer.valueOf(size));
    }
}
