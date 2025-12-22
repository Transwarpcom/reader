package kotlin.reflect.jvm.internal;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: moduleByClassLoader.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\u001a\b\u0010\u0005\u001a\u00020\u0006H��\u001a\u0010\u0010\u0007\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\bH��\" \u0010��\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006\t"}, d2 = {"moduleByClassLoader", "Ljava/util/concurrent/ConcurrentMap;", "Lkotlin/reflect/jvm/internal/WeakClassLoaderBox;", "Ljava/lang/ref/WeakReference;", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "clearModuleByClassLoaderCache", "", "getOrCreateModule", "Ljava/lang/Class;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/ModuleByClassLoaderKt.class */
public final class ModuleByClassLoaderKt {
    private static final ConcurrentMap<WeakClassLoaderBox, WeakReference<RuntimeModuleData>> moduleByClassLoader = new ConcurrentHashMap();

    @NotNull
    public static final RuntimeModuleData getOrCreateModule(@NotNull Class<?> getOrCreateModule) {
        Intrinsics.checkNotNullParameter(getOrCreateModule, "$this$getOrCreateModule");
        ClassLoader classLoader = ReflectClassUtilKt.getSafeClassLoader(getOrCreateModule);
        WeakClassLoaderBox key = new WeakClassLoaderBox(classLoader);
        WeakReference cached = moduleByClassLoader.get(key);
        if (cached != null) {
            RuntimeModuleData it = cached.get();
            if (it != null) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                return it;
            }
            moduleByClassLoader.remove(key, cached);
        }
        RuntimeModuleData module = RuntimeModuleData.Companion.create(classLoader);
        while (true) {
            try {
                WeakReference ref = moduleByClassLoader.putIfAbsent(key, new WeakReference<>(module));
                if (ref == null) {
                    return module;
                }
                RuntimeModuleData result = ref.get();
                if (result == null) {
                    moduleByClassLoader.remove(key, ref);
                } else {
                    key.setTemporaryStrongRef((ClassLoader) null);
                    return result;
                }
            } finally {
                key.setTemporaryStrongRef(null);
            }
        }
    }

    public static final void clearModuleByClassLoaderCache() {
        moduleByClassLoader.clear();
    }
}
