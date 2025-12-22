package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.model.StubTypeMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: StubType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/StubType.class */
public final class StubType extends AbstractStubType implements StubTypeMarker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StubType(@NotNull TypeConstructor originalTypeVariable, boolean isMarkedNullable, @NotNull TypeConstructor constructor, @NotNull MemberScope memberScope) {
        super(originalTypeVariable, isMarkedNullable, constructor, memberScope);
        Intrinsics.checkNotNullParameter(originalTypeVariable, "originalTypeVariable");
        Intrinsics.checkNotNullParameter(constructor, "constructor");
        Intrinsics.checkNotNullParameter(memberScope, "memberScope");
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractStubType
    @NotNull
    public AbstractStubType materialize(boolean newNullability) {
        return new StubType(getOriginalTypeVariable(), newNullability, getConstructor(), getMemberScope());
    }
}
