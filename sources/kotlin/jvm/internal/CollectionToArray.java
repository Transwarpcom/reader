package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CollectionToArray.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��2\n��\n\u0002\u0010\u0011\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010��\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��¨\u0006\u0015"}, d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"})
@JvmName(name = "CollectionToArray")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/jvm/internal/CollectionToArray.class */
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];
    private static final int MAX_SIZE = 2147483645;

    @JvmName(name = "toArray")
    @NotNull
    public static final Object[] toArray(@NotNull Collection<?> collection) {
        Intrinsics.checkNotNullParameter(collection, "collection");
        int size$iv = collection.size();
        if (size$iv == 0) {
            return EMPTY;
        }
        Iterator iter$iv = collection.iterator();
        if (!iter$iv.hasNext()) {
            return EMPTY;
        }
        Object[] result$iv = new Object[size$iv];
        int i$iv = 0;
        while (true) {
            int i = i$iv;
            i$iv++;
            result$iv[i] = iter$iv.next();
            if (i$iv >= result$iv.length) {
                if (!iter$iv.hasNext()) {
                    return result$iv;
                }
                int newSize$iv = ((i$iv * 3) + 1) >>> 1;
                if (newSize$iv <= i$iv) {
                    if (i$iv >= MAX_SIZE) {
                        throw new OutOfMemoryError();
                    }
                    newSize$iv = MAX_SIZE;
                }
                Object[] objArrCopyOf = Arrays.copyOf(result$iv, newSize$iv);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "Arrays.copyOf(result, newSize)");
                result$iv = objArrCopyOf;
            } else if (!iter$iv.hasNext()) {
                Object[] result = result$iv;
                Object[] objArrCopyOf2 = Arrays.copyOf(result, i$iv);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "Arrays.copyOf(result, size)");
                return objArrCopyOf2;
            }
        }
    }

    @JvmName(name = "toArray")
    @NotNull
    public static final Object[] toArray(@NotNull Collection<?> collection, @Nullable Object[] a) throws NegativeArraySizeException {
        Object[] objArr;
        Intrinsics.checkNotNullParameter(collection, "collection");
        if (a == null) {
            throw new NullPointerException();
        }
        int size$iv = collection.size();
        if (size$iv == 0) {
            if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }
        Iterator iter$iv = collection.iterator();
        if (!iter$iv.hasNext()) {
            if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }
        if (size$iv <= a.length) {
            objArr = a;
        } else {
            Object objNewInstance = Array.newInstance(a.getClass().getComponentType(), size$iv);
            if (objNewInstance == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            }
            objArr = (Object[]) objNewInstance;
        }
        Object[] result$iv = objArr;
        int i$iv = 0;
        while (true) {
            int i = i$iv;
            i$iv++;
            result$iv[i] = iter$iv.next();
            if (i$iv >= result$iv.length) {
                if (!iter$iv.hasNext()) {
                    return result$iv;
                }
                int newSize$iv = ((i$iv * 3) + 1) >>> 1;
                if (newSize$iv <= i$iv) {
                    if (i$iv >= MAX_SIZE) {
                        throw new OutOfMemoryError();
                    }
                    newSize$iv = MAX_SIZE;
                }
                Object[] objArrCopyOf = Arrays.copyOf(result$iv, newSize$iv);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "Arrays.copyOf(result, newSize)");
                result$iv = objArrCopyOf;
            } else if (!iter$iv.hasNext()) {
                Object[] result = result$iv;
                if (result == a) {
                    a[i$iv] = null;
                    return a;
                }
                Object[] objArrCopyOf2 = Arrays.copyOf(result, i$iv);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "Arrays.copyOf(result, size)");
                return objArrCopyOf2;
            }
        }
    }

    private static final Object[] toArrayImpl(Collection<?> collection, Function0<Object[]> function0, Function1<? super Integer, Object[]> function1, Function2<? super Object[], ? super Integer, Object[]> function2) {
        int size = collection.size();
        if (size == 0) {
            return function0.invoke();
        }
        Iterator iter = collection.iterator();
        if (!iter.hasNext()) {
            return function0.invoke();
        }
        Object[] result = function1.invoke(Integer.valueOf(size));
        int i = 0;
        while (true) {
            int i2 = i;
            i++;
            result[i2] = iter.next();
            if (i >= result.length) {
                if (!iter.hasNext()) {
                    return result;
                }
                int newSize = ((i * 3) + 1) >>> 1;
                if (newSize <= i) {
                    if (i >= MAX_SIZE) {
                        throw new OutOfMemoryError();
                    }
                    newSize = MAX_SIZE;
                }
                Object[] objArrCopyOf = Arrays.copyOf(result, newSize);
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "Arrays.copyOf(result, newSize)");
                result = objArrCopyOf;
            } else if (!iter.hasNext()) {
                return function2.invoke(result, Integer.valueOf(i));
            }
        }
    }
}
