package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: CompanionObjectMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/CompanionObjectMapping$classIds$1.class */
/* synthetic */ class CompanionObjectMapping$classIds$1 extends FunctionReference implements Function1<PrimitiveType, FqName> {
    CompanionObjectMapping$classIds$1(StandardNames $this) {
        super(1, $this);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final FqName invoke(@NotNull PrimitiveType p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return StandardNames.getPrimitiveFqName(p0);
    }

    @Override // kotlin.jvm.internal.CallableReference
    @NotNull
    public final String getSignature() {
        return "getPrimitiveFqName(Lorg/jetbrains/kotlin/builtins/PrimitiveType;)Lorg/jetbrains/kotlin/name/FqName;";
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    @NotNull
    public final String getName() {
        return "getPrimitiveFqName";
    }

    @Override // kotlin.jvm.internal.CallableReference
    @NotNull
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(StandardNames.class);
    }
}
