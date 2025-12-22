package kotlin.reflect.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

/* compiled from: ReflectionObjectRenderer.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014J\u001a\u0010\u0015\u001a\u00020\u0016*\u00060\u0017j\u0002`\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\u0016*\u00060\u0017j\u0002`\u00182\u0006\u0010\u001c\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001d"}, d2 = {"Lkotlin/reflect/jvm/internal/ReflectionObjectRenderer;", "", "()V", "renderer", "Lkotlin/reflect/jvm/internal/impl/renderer/DescriptorRenderer;", "renderCallable", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "renderFunction", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "renderLambda", "invoke", "renderParameter", "parameter", "Lkotlin/reflect/jvm/internal/KParameterImpl;", "renderProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "renderType", "type", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "appendReceiverType", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "receiver", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "appendReceivers", "callable", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/ReflectionObjectRenderer.class */
public final class ReflectionObjectRenderer {

    @NotNull
    public static final ReflectionObjectRenderer INSTANCE = new ReflectionObjectRenderer();
    private static final DescriptorRenderer renderer = DescriptorRenderer.FQ_NAMES_IN_TYPES;

    private ReflectionObjectRenderer() {
    }

    private final void appendReceiverType(StringBuilder $this$appendReceiverType, ReceiverParameterDescriptor receiver) {
        if (receiver != null) {
            KotlinType type = receiver.getType();
            Intrinsics.checkNotNullExpressionValue(type, "receiver.type");
            $this$appendReceiverType.append(renderType(type));
            $this$appendReceiverType.append(".");
        }
    }

    private final void appendReceivers(StringBuilder $this$appendReceivers, CallableDescriptor callable) {
        ReceiverParameterDescriptor dispatchReceiver = UtilKt.getInstanceReceiverParameter(callable);
        ReceiverParameterDescriptor extensionReceiver = callable.getExtensionReceiverParameter();
        appendReceiverType($this$appendReceivers, dispatchReceiver);
        boolean addParentheses = (dispatchReceiver == null || extensionReceiver == null) ? false : true;
        if (addParentheses) {
            $this$appendReceivers.append("(");
        }
        appendReceiverType($this$appendReceivers, extensionReceiver);
        if (addParentheses) {
            $this$appendReceivers.append(")");
        }
    }

    private final String renderCallable(CallableDescriptor descriptor) {
        if (descriptor instanceof PropertyDescriptor) {
            return renderProperty((PropertyDescriptor) descriptor);
        }
        if (descriptor instanceof FunctionDescriptor) {
            return renderFunction((FunctionDescriptor) descriptor);
        }
        throw new IllegalStateException(("Illegal callable: " + descriptor).toString());
    }

    @NotNull
    public final String renderProperty(@NotNull PropertyDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        StringBuilder $this$buildString = new StringBuilder();
        $this$buildString.append(descriptor.isVar() ? "var " : "val ");
        INSTANCE.appendReceivers($this$buildString, descriptor);
        DescriptorRenderer descriptorRenderer = renderer;
        Name name = descriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "descriptor.name");
        $this$buildString.append(descriptorRenderer.renderName(name, true));
        $this$buildString.append(": ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType type = descriptor.getType();
        Intrinsics.checkNotNullExpressionValue(type, "descriptor.type");
        $this$buildString.append(reflectionObjectRenderer.renderType(type));
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @NotNull
    public final String renderFunction(@NotNull FunctionDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        StringBuilder $this$buildString = new StringBuilder();
        $this$buildString.append("fun ");
        INSTANCE.appendReceivers($this$buildString, descriptor);
        DescriptorRenderer descriptorRenderer = renderer;
        Name name = descriptor.getName();
        Intrinsics.checkNotNullExpressionValue(name, "descriptor.name");
        $this$buildString.append(descriptorRenderer.renderName(name, true));
        List<ValueParameterDescriptor> valueParameters = descriptor.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "descriptor.valueParameters");
        CollectionsKt.joinTo$default(valueParameters, $this$buildString, ", ", "(", ")", 0, null, new Function1<ValueParameterDescriptor, CharSequence>() { // from class: kotlin.reflect.jvm.internal.ReflectionObjectRenderer$renderFunction$1$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(ValueParameterDescriptor it) {
                ReflectionObjectRenderer reflectionObjectRenderer = ReflectionObjectRenderer.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                KotlinType type = it.getType();
                Intrinsics.checkNotNullExpressionValue(type, "it.type");
                return reflectionObjectRenderer.renderType(type);
            }
        }, 48, null);
        $this$buildString.append(": ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType returnType = descriptor.getReturnType();
        Intrinsics.checkNotNull(returnType);
        Intrinsics.checkNotNullExpressionValue(returnType, "descriptor.returnType!!");
        $this$buildString.append(reflectionObjectRenderer.renderType(returnType));
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @NotNull
    public final String renderLambda(@NotNull FunctionDescriptor invoke) {
        Intrinsics.checkNotNullParameter(invoke, "invoke");
        StringBuilder $this$buildString = new StringBuilder();
        INSTANCE.appendReceivers($this$buildString, invoke);
        List<ValueParameterDescriptor> valueParameters = invoke.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(valueParameters, "invoke.valueParameters");
        CollectionsKt.joinTo$default(valueParameters, $this$buildString, ", ", "(", ")", 0, null, new Function1<ValueParameterDescriptor, CharSequence>() { // from class: kotlin.reflect.jvm.internal.ReflectionObjectRenderer$renderLambda$1$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CharSequence invoke(ValueParameterDescriptor it) {
                ReflectionObjectRenderer reflectionObjectRenderer = ReflectionObjectRenderer.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                KotlinType type = it.getType();
                Intrinsics.checkNotNullExpressionValue(type, "it.type");
                return reflectionObjectRenderer.renderType(type);
            }
        }, 48, null);
        $this$buildString.append(" -> ");
        ReflectionObjectRenderer reflectionObjectRenderer = INSTANCE;
        KotlinType returnType = invoke.getReturnType();
        Intrinsics.checkNotNull(returnType);
        Intrinsics.checkNotNullExpressionValue(returnType, "invoke.returnType!!");
        $this$buildString.append(reflectionObjectRenderer.renderType(returnType));
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @NotNull
    public final String renderParameter(@NotNull KParameterImpl parameter) {
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        StringBuilder $this$buildString = new StringBuilder();
        switch (parameter.getKind()) {
            case EXTENSION_RECEIVER:
                $this$buildString.append("extension receiver parameter");
                break;
            case INSTANCE:
                $this$buildString.append("instance parameter");
                break;
            case VALUE:
                $this$buildString.append("parameter #" + parameter.getIndex() + ' ' + parameter.getName());
                break;
        }
        $this$buildString.append(" of ");
        $this$buildString.append(INSTANCE.renderCallable(parameter.getCallable().getDescriptor()));
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    @NotNull
    public final String renderType(@NotNull KotlinType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return renderer.renderType(type);
    }
}
