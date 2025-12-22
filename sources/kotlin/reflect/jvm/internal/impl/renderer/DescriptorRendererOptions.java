package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

/* compiled from: DescriptorRenderer.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/renderer/DescriptorRendererOptions.class */
public interface DescriptorRendererOptions {
    void setClassifierNamePolicy(@NotNull ClassifierNamePolicy classifierNamePolicy);

    void setWithDefinedIn(boolean z);

    void setModifiers(@NotNull Set<? extends DescriptorRendererModifier> set);

    void setStartFromName(boolean z);

    boolean getDebugMode();

    void setDebugMode(boolean z);

    void setVerbose(boolean z);

    boolean getEnhancedTypes();

    void setTextFormat(@NotNull RenderingFormat renderingFormat);

    @NotNull
    Set<FqName> getExcludedTypeAnnotationClasses();

    void setExcludedTypeAnnotationClasses(@NotNull Set<FqName> set);

    @NotNull
    AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy();

    void setAnnotationArgumentsRenderingPolicy(@NotNull AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy);

    void setParameterNameRenderingPolicy(@NotNull ParameterNameRenderingPolicy parameterNameRenderingPolicy);

    void setWithoutTypeParameters(boolean z);

    void setReceiverAfterName(boolean z);

    void setRenderCompanionObjectName(boolean z);

    void setWithoutSuperTypes(boolean z);

    /* compiled from: DescriptorRenderer.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/renderer/DescriptorRendererOptions$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean getIncludeAnnotationArguments(@NotNull DescriptorRendererOptions descriptorRendererOptions) {
            Intrinsics.checkNotNullParameter(descriptorRendererOptions, "this");
            return descriptorRendererOptions.getAnnotationArgumentsRenderingPolicy().getIncludeAnnotationArguments();
        }

        public static boolean getIncludeEmptyAnnotationArguments(@NotNull DescriptorRendererOptions descriptorRendererOptions) {
            Intrinsics.checkNotNullParameter(descriptorRendererOptions, "this");
            return descriptorRendererOptions.getAnnotationArgumentsRenderingPolicy().getIncludeEmptyAnnotationArguments();
        }
    }
}
