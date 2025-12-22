package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import org.jetbrains.annotations.Nullable;

/* compiled from: utils.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/checker/NewTypeVariableConstructor.class */
public interface NewTypeVariableConstructor {
    @Nullable
    TypeParameterDescriptor getOriginalTypeParameter();
}
