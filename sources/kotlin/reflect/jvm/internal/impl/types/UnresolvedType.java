package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;

/* compiled from: ErrorType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/UnresolvedType.class */
public final class UnresolvedType extends ErrorType {

    @NotNull
    private final String presentableName;

    @Override // kotlin.reflect.jvm.internal.impl.types.ErrorType
    @NotNull
    public String getPresentableName() {
        return this.presentableName;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnresolvedType(@NotNull String presentableName, @NotNull TypeConstructor constructor, @NotNull MemberScope memberScope, @NotNull List<? extends TypeProjection> arguments, boolean isMarkedNullable) {
        super(constructor, memberScope, arguments, isMarkedNullable, null, 16, null);
        Intrinsics.checkNotNullParameter(presentableName, "presentableName");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(memberScope, "memberScope");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        this.presentableName = presentableName;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.ErrorType, kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    public SimpleType makeNullableAsSpecified(boolean newNullability) {
        return new UnresolvedType(getPresentableName(), getConstructor(), getMemberScope(), getArguments(), newNullability);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.ErrorType, kotlin.reflect.jvm.internal.impl.types.KotlinType
    @NotNull
    public UnresolvedType refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner, "kotlinTypeRefiner");
        return this;
    }
}
