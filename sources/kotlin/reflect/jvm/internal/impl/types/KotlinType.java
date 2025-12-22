package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.StrictEqualityTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/KotlinType.class */
public abstract class KotlinType implements Annotated, KotlinTypeMarker {
    private int cachedHashCode;

    @NotNull
    public abstract TypeConstructor getConstructor();

    @NotNull
    public abstract List<TypeProjection> getArguments();

    public abstract boolean isMarkedNullable();

    @NotNull
    public abstract MemberScope getMemberScope();

    @NotNull
    public abstract UnwrappedType unwrap();

    @NotNull
    public abstract KotlinType refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner);

    public /* synthetic */ KotlinType(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    private KotlinType() {
    }

    private final int computeHashCode() {
        if (KotlinTypeKt.isError(this)) {
            return super.hashCode();
        }
        int result = getConstructor().hashCode();
        return (31 * ((31 * result) + getArguments().hashCode())) + (isMarkedNullable() ? 1 : 0);
    }

    public final int hashCode() {
        int currentHashCode = this.cachedHashCode;
        if (currentHashCode != 0) {
            return currentHashCode;
        }
        int currentHashCode2 = computeHashCode();
        this.cachedHashCode = currentHashCode2;
        return currentHashCode2;
    }

    public final boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof KotlinType) && isMarkedNullable() == ((KotlinType) other).isMarkedNullable() && StrictEqualityTypeChecker.INSTANCE.strictEqualTypes(unwrap(), ((KotlinType) other).unwrap());
    }
}
