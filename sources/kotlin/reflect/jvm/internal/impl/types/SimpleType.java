package kotlin.reflect.jvm.internal.impl.types;

import ch.qos.logback.classic.spi.CallerData;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: KotlinType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/SimpleType.class */
public abstract class SimpleType extends UnwrappedType implements SimpleTypeMarker, TypeArgumentListMarker {
    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    public abstract SimpleType replaceAnnotations(@NotNull Annotations annotations);

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    public abstract SimpleType makeNullableAsSpecified(boolean z);

    public SimpleType() {
        super(null);
    }

    @NotNull
    public String toString() {
        StringBuilder $this$toString_u24lambda_u2d0 = new StringBuilder();
        for (AnnotationDescriptor annotation : getAnnotations()) {
            StringsKt.append($this$toString_u24lambda_u2d0, "[", DescriptorRenderer.renderAnnotation$default(DescriptorRenderer.DEBUG_TEXT, annotation, null, 2, null), "] ");
        }
        $this$toString_u24lambda_u2d0.append(getConstructor());
        if (!getArguments().isEmpty()) {
            CollectionsKt.joinTo$default(getArguments(), $this$toString_u24lambda_u2d0, ", ", "<", ">", 0, null, null, 112, null);
        }
        if (isMarkedNullable()) {
            $this$toString_u24lambda_u2d0.append(CallerData.NA);
        }
        String string = $this$toString_u24lambda_u2d0.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
