package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: GroupingJVM.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��&\n��\n\u0002\u0010$\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n��\u001a0\u0010��\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b��\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aZ\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000bH\u0081\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"eachCount", "", OperatorName.STROKING_COLOR_CMYK, "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlin/collections/Grouping;", "mapValuesInPlace", "", "R", "V", OperatorName.FILL_NON_ZERO, "Lkotlin/Function1;", "", "kotlin-stdlib"}, xs = "kotlin/collections/GroupingKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/GroupingKt__GroupingJVMKt.class */
class GroupingKt__GroupingJVMKt {
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K> Map<K, Integer> eachCount(@NotNull Grouping<T, ? extends K> eachCount) {
        Object intRef;
        Intrinsics.checkNotNullParameter(eachCount, "$this$eachCount");
        Map destination$iv = new LinkedHashMap();
        Iterator<T> itSourceIterator = eachCount.sourceIterator();
        while (itSourceIterator.hasNext()) {
            K kKeyOf = eachCount.keyOf(itSourceIterator.next());
            Object accumulator$iv$iv = destination$iv.get(kKeyOf);
            boolean first$iv = accumulator$iv$iv == null && !destination$iv.containsKey(kKeyOf);
            K k = kKeyOf;
            if (first$iv) {
                k = k;
                intRef = new Ref.IntRef();
            } else {
                intRef = accumulator$iv$iv;
            }
            Ref.IntRef acc = (Ref.IntRef) intRef;
            acc.element++;
            destination$iv.put(kKeyOf, acc);
        }
        for (Map.Entry it : destination$iv.entrySet()) {
            if (it == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            TypeIntrinsics.asMutableMapEntry(it).setValue(Integer.valueOf(((Ref.IntRef) it.getValue()).element));
        }
        return TypeIntrinsics.asMutableMap(destination$iv);
    }

    @PublishedApi
    @InlineOnly
    private static final <K, V, R> Map<K, R> mapValuesInPlace(Map<K, V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterable $this$forEach$iv = map.entrySet();
        for (Object element$iv : $this$forEach$iv) {
            Map.Entry it = (Map.Entry) element$iv;
            if (it == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            TypeIntrinsics.asMutableMapEntry(it).setValue(function1.invoke(it));
        }
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>");
        }
        return TypeIntrinsics.asMutableMap(map);
    }
}
