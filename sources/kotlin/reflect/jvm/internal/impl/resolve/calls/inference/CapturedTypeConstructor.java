package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: CapturedTypeConstructor.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/calls/inference/CapturedTypeConstructor.class */
public interface CapturedTypeConstructor extends TypeConstructor, CapturedTypeConstructorMarker {
    @NotNull
    TypeProjection getProjection();
}
