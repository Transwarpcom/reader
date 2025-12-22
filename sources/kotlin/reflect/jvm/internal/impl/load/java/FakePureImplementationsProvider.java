package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FakePureImplementationsProvider.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/FakePureImplementationsProvider.class */
public final class FakePureImplementationsProvider {

    @NotNull
    public static final FakePureImplementationsProvider INSTANCE = new FakePureImplementationsProvider();

    @NotNull
    private static final HashMap<FqName, FqName> pureImplementations = new HashMap<>();

    private FakePureImplementationsProvider() {
    }

    @Nullable
    public final FqName getPurelyImplementedInterface(@NotNull FqName classFqName) {
        Intrinsics.checkNotNullParameter(classFqName, "classFqName");
        return pureImplementations.get(classFqName);
    }

    static {
        INSTANCE.implementedWith(StandardNames.FqNames.mutableList, INSTANCE.fqNameListOf("java.util.ArrayList", "java.util.LinkedList"));
        INSTANCE.implementedWith(StandardNames.FqNames.mutableSet, INSTANCE.fqNameListOf("java.util.HashSet", "java.util.TreeSet", "java.util.LinkedHashSet"));
        INSTANCE.implementedWith(StandardNames.FqNames.mutableMap, INSTANCE.fqNameListOf("java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.concurrent.ConcurrentSkipListMap"));
        INSTANCE.implementedWith(new FqName("java.util.function.Function"), INSTANCE.fqNameListOf("java.util.function.UnaryOperator"));
        INSTANCE.implementedWith(new FqName("java.util.function.BiFunction"), INSTANCE.fqNameListOf("java.util.function.BinaryOperator"));
    }

    private final void implementedWith(FqName $this$implementedWith, List<FqName> list) {
        List<FqName> $this$associateWithTo$iv = list;
        Map destination$iv = pureImplementations;
        for (Object element$iv : $this$associateWithTo$iv) {
            destination$iv.put(element$iv, $this$implementedWith);
        }
    }

    private final List<FqName> fqNameListOf(String... names) {
        Collection destination$iv$iv = new ArrayList(names.length);
        for (String str : names) {
            destination$iv$iv.add(new FqName(str));
        }
        return (List) destination$iv$iv;
    }
}
