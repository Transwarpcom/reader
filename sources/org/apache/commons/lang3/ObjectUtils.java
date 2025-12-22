package org.apache.commons.lang3;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.text.StrBuilder;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/ObjectUtils.class */
public class ObjectUtils {
    private static final char AT_SIGN = '@';
    public static final Null NULL = new Null();

    public static <T> T defaultIfNull(T object, T defaultValue) {
        return object != null ? object : defaultValue;
    }

    @SafeVarargs
    public static <T> T firstNonNull(T... values) {
        if (values != null) {
            for (T val : values) {
                if (val != null) {
                    return val;
                }
            }
            return null;
        }
        return null;
    }

    public static boolean anyNotNull(Object... values) {
        return firstNonNull(values) != null;
    }

    public static boolean allNotNull(Object... values) {
        if (values == null) {
            return false;
        }
        for (Object val : values) {
            if (val == null) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public static boolean equals(Object object1, Object object2) {
        if (object1 == object2) {
            return true;
        }
        if (object1 == null || object2 == null) {
            return false;
        }
        return object1.equals(object2);
    }

    public static boolean notEqual(Object object1, Object object2) {
        return !equals(object1, object2);
    }

    @Deprecated
    public static int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Deprecated
    public static int hashCodeMulti(Object... objects) {
        int hash = 1;
        if (objects != null) {
            for (Object object : objects) {
                int tmpHash = hashCode(object);
                hash = (hash * 31) + tmpHash;
            }
        }
        return hash;
    }

    public static String identityToString(Object object) {
        if (object == null) {
            return null;
        }
        String name = object.getClass().getName();
        String hexString = Integer.toHexString(System.identityHashCode(object));
        StringBuilder builder = new StringBuilder(name.length() + 1 + hexString.length());
        builder.append(name).append('@').append(hexString);
        return builder.toString();
    }

    public static void identityToString(Appendable appendable, Object object) throws IOException {
        Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
        appendable.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
    }

    @Deprecated
    public static void identityToString(StrBuilder builder, Object object) {
        Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
        String name = object.getClass().getName();
        String hexString = Integer.toHexString(System.identityHashCode(object));
        builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
        builder.append(name).append('@').append(hexString);
    }

    public static void identityToString(StringBuffer buffer, Object object) {
        Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
        String name = object.getClass().getName();
        String hexString = Integer.toHexString(System.identityHashCode(object));
        buffer.ensureCapacity(buffer.length() + name.length() + 1 + hexString.length());
        buffer.append(name).append('@').append(hexString);
    }

    public static void identityToString(StringBuilder builder, Object object) {
        Validate.notNull(object, "Cannot get the toString of a null object", new Object[0]);
        String name = object.getClass().getName();
        String hexString = Integer.toHexString(System.identityHashCode(object));
        builder.ensureCapacity(builder.length() + name.length() + 1 + hexString.length());
        builder.append(name).append('@').append(hexString);
    }

    @Deprecated
    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    @Deprecated
    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T min(T... values) {
        T result = null;
        if (values != null) {
            for (T value : values) {
                if (compare(value, result, true) < 0) {
                    result = value;
                }
            }
        }
        return result;
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T max(T... values) {
        T result = null;
        if (values != null) {
            for (T value : values) {
                if (compare(value, result, false) > 0) {
                    result = value;
                }
            }
        }
        return result;
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
        return compare(c1, c2, false);
    }

    public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater) {
        if (c1 == c2) {
            return 0;
        }
        if (c1 == null) {
            return nullGreater ? 1 : -1;
        }
        if (c2 == null) {
            return nullGreater ? -1 : 1;
        }
        return c1.compareTo(c2);
    }

    @SafeVarargs
    public static <T extends Comparable<? super T>> T median(T... items) {
        Validate.notEmpty(items);
        Validate.noNullElements(items);
        TreeSet<T> sort = new TreeSet<>();
        Collections.addAll(sort, items);
        return (T) sort.toArray()[(sort.size() - 1) / 2];
    }

    @SafeVarargs
    public static <T> T median(Comparator<T> comparator, T... tArr) {
        Validate.notEmpty(tArr, "null/empty items", new Object[0]);
        Validate.noNullElements(tArr);
        Validate.notNull(comparator, "null comparator", new Object[0]);
        TreeSet treeSet = new TreeSet(comparator);
        Collections.addAll(treeSet, tArr);
        return (T) treeSet.toArray()[(treeSet.size() - 1) / 2];
    }

    @SafeVarargs
    public static <T> T mode(T... items) {
        if (ArrayUtils.isNotEmpty(items)) {
            HashMap<T, MutableInt> occurrences = new HashMap<>(items.length);
            for (T t : items) {
                MutableInt count = occurrences.get(t);
                if (count == null) {
                    occurrences.put(t, new MutableInt(1));
                } else {
                    count.increment();
                }
            }
            T result = null;
            int max = 0;
            for (Map.Entry<T, MutableInt> e : occurrences.entrySet()) {
                int cmp = e.getValue().intValue();
                if (cmp == max) {
                    result = null;
                } else if (cmp > max) {
                    max = cmp;
                    result = e.getKey();
                }
            }
            return result;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T clone(T t) throws IllegalAccessException, ArrayIndexOutOfBoundsException, IllegalArgumentException, CloneNotSupportedException, NegativeArraySizeException, InvocationTargetException {
        Object objInvoke;
        if (t instanceof Cloneable) {
            if (t.getClass().isArray()) {
                Class<?> componentType = t.getClass().getComponentType();
                if (componentType.isPrimitive()) {
                    int length = Array.getLength(t);
                    objInvoke = Array.newInstance(componentType, length);
                    while (true) {
                        int i = length;
                        length--;
                        if (i <= 0) {
                            break;
                        }
                        Array.set(objInvoke, length, Array.get(t, length));
                    }
                } else {
                    objInvoke = ((Object[]) t).clone();
                }
            } else {
                try {
                    objInvoke = t.getClass().getMethod("clone", new Class[0]).invoke(t, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new CloneFailedException("Cannot clone Cloneable type " + t.getClass().getName(), e);
                } catch (NoSuchMethodException e2) {
                    throw new CloneFailedException("Cloneable type " + t.getClass().getName() + " has no clone method", e2);
                } catch (InvocationTargetException e3) {
                    throw new CloneFailedException("Exception cloning Cloneable type " + t.getClass().getName(), e3.getCause());
                }
            }
            return (T) objInvoke;
        }
        return null;
    }

    public static <T> T cloneIfPossible(T t) {
        T t2 = (T) clone(t);
        return t2 == null ? t : t2;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/ObjectUtils$Null.class */
    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        Null() {
        }

        private Object readResolve() {
            return ObjectUtils.NULL;
        }
    }

    public static boolean CONST(boolean v) {
        return v;
    }

    public static byte CONST(byte v) {
        return v;
    }

    public static byte CONST_BYTE(int v) {
        if (v < -128 || v > 127) {
            throw new IllegalArgumentException("Supplied value must be a valid byte literal between -128 and 127: [" + v + "]");
        }
        return (byte) v;
    }

    public static char CONST(char v) {
        return v;
    }

    public static short CONST(short v) {
        return v;
    }

    public static short CONST_SHORT(int v) {
        if (v < -32768 || v > 32767) {
            throw new IllegalArgumentException("Supplied value must be a valid byte literal between -32768 and 32767: [" + v + "]");
        }
        return (short) v;
    }

    public static int CONST(int v) {
        return v;
    }

    public static long CONST(long v) {
        return v;
    }

    public static float CONST(float v) {
        return v;
    }

    public static double CONST(double v) {
        return v;
    }

    public static <T> T CONST(T v) {
        return v;
    }
}
