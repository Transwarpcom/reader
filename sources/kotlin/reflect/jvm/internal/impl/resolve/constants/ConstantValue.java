package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: constantValues.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue.class */
public abstract class ConstantValue<T> {
    private final T value;

    @NotNull
    public abstract KotlinType getType(@NotNull ModuleDescriptor moduleDescriptor);

    public ConstantValue(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            T value = getValue();
            ConstantValue constantValue = other instanceof ConstantValue ? (ConstantValue) other : null;
            if (!Intrinsics.areEqual(value, constantValue == null ? null : constantValue.getValue())) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        T value = getValue();
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    @NotNull
    public String toString() {
        return String.valueOf(getValue());
    }
}
