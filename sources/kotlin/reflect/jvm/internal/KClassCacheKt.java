package kotlin.reflect.jvm.internal;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.pcollections.HashPMap;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: kClassCache.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��&\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u001a\b\u0010\u0005\u001a\u00020\u0006H��\u001a&\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\b\b��\u0010\t*\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH��\"*\u0010��\u001a\u001e\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u00040\u0001X\u0082\u000e¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"K_CLASS_CACHE", "Lkotlin/reflect/jvm/internal/pcollections/HashPMap;", "", "kotlin.jvm.PlatformType", "", "clearKClassCache", "", "getOrCreateKotlinClass", "Lkotlin/reflect/jvm/internal/KClassImpl;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "jClass", "Ljava/lang/Class;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KClassCacheKt.class */
public final class KClassCacheKt {
    private static HashPMap<String, Object> K_CLASS_CACHE;

    static {
        HashPMap<String, Object> hashPMapEmpty = HashPMap.empty();
        Intrinsics.checkNotNullExpressionValue(hashPMapEmpty, "HashPMap.empty<String, Any>()");
        K_CLASS_CACHE = hashPMapEmpty;
    }

    @NotNull
    public static final <T> KClassImpl<T> getOrCreateKotlinClass(@NotNull Class<T> jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        String name = jClass.getName();
        Object cached = K_CLASS_CACHE.get(name);
        if (cached instanceof WeakReference) {
            KClassImpl kClass = (KClassImpl) ((WeakReference) cached).get();
            if (Intrinsics.areEqual(kClass != null ? kClass.getJClass() : null, jClass)) {
                return kClass;
            }
        } else if (cached != null) {
            for (WeakReference ref : (WeakReference[]) cached) {
                KClassImpl kClass2 = (KClassImpl) ref.get();
                if (Intrinsics.areEqual(kClass2 != null ? kClass2.getJClass() : null, jClass)) {
                    return kClass2;
                }
            }
            int size = ((Object[]) cached).length;
            WeakReference[] newArray = new WeakReference[size + 1];
            System.arraycopy(cached, 0, newArray, 0, size);
            KClassImpl newKClass = new KClassImpl(jClass);
            newArray[size] = new WeakReference(newKClass);
            HashPMap<String, Object> hashPMapPlus = K_CLASS_CACHE.plus(name, newArray);
            Intrinsics.checkNotNullExpressionValue(hashPMapPlus, "K_CLASS_CACHE.plus(name, newArray)");
            K_CLASS_CACHE = hashPMapPlus;
            return newKClass;
        }
        KClassImpl newKClass2 = new KClassImpl(jClass);
        HashPMap<String, Object> hashPMapPlus2 = K_CLASS_CACHE.plus(name, new WeakReference(newKClass2));
        Intrinsics.checkNotNullExpressionValue(hashPMapPlus2, "K_CLASS_CACHE.plus(name, WeakReference(newKClass))");
        K_CLASS_CACHE = hashPMapPlus2;
        return newKClass2;
    }

    public static final void clearKClassCache() {
        HashPMap<String, Object> hashPMapEmpty = HashPMap.empty();
        Intrinsics.checkNotNullExpressionValue(hashPMapEmpty, "HashPMap.empty()");
        K_CLASS_CACHE = hashPMapEmpty;
    }
}
