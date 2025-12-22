package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: util.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/descriptors/ValueParameterData.class */
public final class ValueParameterData {

    @NotNull
    private final KotlinType type;
    private final boolean hasDefaultValue;

    public ValueParameterData(@NotNull KotlinType type, boolean hasDefaultValue) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.hasDefaultValue = hasDefaultValue;
    }

    @NotNull
    public final KotlinType getType() {
        return this.type;
    }

    public final boolean getHasDefaultValue() {
        return this.hasDefaultValue;
    }
}
