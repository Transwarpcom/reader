package kotlin.collections.builders;

import com.jayway.jsonpath.internal.function.text.Length;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: ListBuilder.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��2\n��\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a!\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H��¢\u0006\u0002\u0010\u0005\u001a+\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0001\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\b\u001a\u00020\u0004H��¢\u0006\u0002\u0010\t\u001a%\u0010\n\u001a\u00020\u000b\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\f\u001a\u00020\u0004H��¢\u0006\u0002\u0010\r\u001a-\u0010\u000e\u001a\u00020\u000b\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H��¢\u0006\u0002\u0010\u0011\u001a9\u0010\u0012\u001a\u00020\u0013\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0002¢\u0006\u0002\u0010\u0018\u001a-\u0010\u0019\u001a\u00020\u0004\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u001a\u001a/\u0010\u001b\u001a\u00020\u001c\"\u0004\b��\u0010\u0007*\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u001d¨\u0006\u001e"}, d2 = {"arrayOfUninitializedElements", "", "E", "size", "", "(I)[Ljava/lang/Object;", "copyOfUninitializedElements", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "resetAt", "", "index", "([Ljava/lang/Object;I)V", "resetRange", "fromIndex", "toIndex", "([Ljava/lang/Object;II)V", "subarrayContentEquals", "", "offset", Length.TOKEN_NAME, "other", "", "([Ljava/lang/Object;IILjava/util/List;)Z", "subarrayContentHashCode", "([Ljava/lang/Object;II)I", "subarrayContentToString", "", "([Ljava/lang/Object;II)Ljava/lang/String;", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/builders/ListBuilderKt.class */
public final class ListBuilderKt {
    @NotNull
    public static final <E> E[] arrayOfUninitializedElements(int i) {
        if (!(i >= 0)) {
            throw new IllegalArgumentException("capacity must be non-negative.".toString());
        }
        return (E[]) new Object[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> String subarrayContentToString(T[] tArr, int offset, int length) {
        StringBuilder sb = new StringBuilder(2 + (length * 3));
        sb.append("[");
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tArr[offset + i]);
        }
        sb.append("]");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> int subarrayContentHashCode(T[] tArr, int offset, int length) {
        int result = 1;
        for (int i = 0; i < length; i++) {
            T t = tArr[offset + i];
            result = (result * 31) + (t != null ? t.hashCode() : 0);
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> boolean subarrayContentEquals(T[] tArr, int offset, int length, List<?> list) {
        if (length != list.size()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!Intrinsics.areEqual(tArr[offset + i], list.get(i))) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static final <T> T[] copyOfUninitializedElements(@NotNull T[] copyOfUninitializedElements, int i) {
        Intrinsics.checkNotNullParameter(copyOfUninitializedElements, "$this$copyOfUninitializedElements");
        T[] tArr = (T[]) Arrays.copyOf(copyOfUninitializedElements, i);
        Intrinsics.checkNotNullExpressionValue(tArr, "java.util.Arrays.copyOf(this, newSize)");
        if (tArr == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return tArr;
    }

    public static final <E> void resetAt(@NotNull E[] resetAt, int index) {
        Intrinsics.checkNotNullParameter(resetAt, "$this$resetAt");
        resetAt[index] = null;
    }

    public static final <E> void resetRange(@NotNull E[] resetRange, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(resetRange, "$this$resetRange");
        for (int index = fromIndex; index < toIndex; index++) {
            resetAt(resetRange, index);
        }
    }
}
