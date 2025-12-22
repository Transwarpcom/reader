package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: CompanionObjectMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/CompanionObjectMapping.class */
public final class CompanionObjectMapping {

    @NotNull
    public static final CompanionObjectMapping INSTANCE = new CompanionObjectMapping();

    @NotNull
    private static final Set<ClassId> classIds;

    private CompanionObjectMapping() {
    }

    @NotNull
    public final Set<ClassId> getClassIds() {
        return classIds;
    }

    static {
        Iterable $this$map$iv = PrimitiveType.NUMBER_TYPES;
        StandardNames standardNames = StandardNames.INSTANCE;
        Function1 transform$iv = new CompanionObjectMapping$classIds$1(StandardNames.INSTANCE);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv.add(transform$iv.invoke(item$iv$iv));
        }
        FqName safe = StandardNames.FqNames.string.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe, "string.toSafe()");
        List listPlus = CollectionsKt.plus((Collection<? extends FqName>) destination$iv$iv, safe);
        FqName safe2 = StandardNames.FqNames._boolean.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe2, "_boolean.toSafe()");
        List listPlus2 = CollectionsKt.plus((Collection<? extends FqName>) listPlus, safe2);
        FqName safe3 = StandardNames.FqNames._enum.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe3, "_enum.toSafe()");
        Iterable $this$mapTo$iv = CollectionsKt.plus((Collection<? extends FqName>) listPlus2, safe3);
        Collection destination$iv = new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            FqName p0 = (FqName) item$iv;
            destination$iv.add(ClassId.topLevel(p0));
        }
        classIds = (Set) destination$iv;
    }

    @NotNull
    public final Set<ClassId> allClassesWithIntrinsicCompanions() {
        return classIds;
    }
}
