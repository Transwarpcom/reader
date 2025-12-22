package kotlin.collections.jdk8;

import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.TypeIntrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* compiled from: Collections.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0010%\n\u0002\b\u0003\u001aA\u0010��\u001a\u0002H\u0001\"\t\b��\u0010\u0002¢\u0006\u0002\b\u0003\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u0002H\u0001H\u0087\b¢\u0006\u0002\u0010\u0007\u001aH\u0010\b\u001a\u00020\t\"\t\b��\u0010\u0002¢\u0006\u0002\b\u0003\"\t\b\u0001\u0010\u0001¢\u0006\u0002\b\u0003*\u0012\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0006\b\u0001\u0012\u0002H\u00010\n2\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u000b\u001a\u0002H\u0001H\u0087\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"getOrDefault", "V", OperatorName.STROKING_COLOR_CMYK, "Lkotlin/internal/OnlyInputTypes;", "", "key", "defaultValue", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove", "", "", "value", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)Z", "kotlin-stdlib-jdk8"}, pn = "kotlin.collections")
@JvmName(name = "CollectionsJDK8Kt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-jdk8-1.5.21.jar:kotlin/collections/jdk8/CollectionsJDK8Kt.class */
public final class CollectionsJDK8Kt {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <K, V> V getOrDefault(Map<? extends K, ? extends V> map, K k, V v) {
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
        }
        return map.getOrDefault(k, v);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <K, V> boolean remove(Map<? extends K, ? extends V> map, K k, V v) {
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
        }
        return TypeIntrinsics.asMutableMap(map).remove(k, v);
    }
}
