package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: InlineClassAwareCaller.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��:\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u001a\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0002H��\u001a6\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\n\b��\u0010\n*\u0004\u0018\u00010\u000b*\b\u0012\u0004\u0012\u0002H\n0\t2\u0006\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH��\u001a\u0018\u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0007\u001a\u00020\u0002H��\u001a\u0018\u0010\u0011\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0007\u001a\u00020\u0002H��\u001a\f\u0010\u0012\u001a\u00020\r*\u00020\u0002H\u0002\u001a\u0014\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0010*\u0004\u0018\u00010\u0014H��\u001a\u0012\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0010*\u00020\u0001H��\"\u001a\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0015"}, d2 = {"expectedReceiverType", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "getExpectedReceiverType", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;)Lorg/jetbrains/kotlin/types/KotlinType;", "coerceToExpectedReceiverType", "", "descriptor", "createInlineClassAwareCallerIfNeeded", "Lkotlin/reflect/jvm/internal/calls/Caller;", OperatorName.SET_LINE_MITERLIMIT, "Ljava/lang/reflect/Member;", "isDefault", "", "getBoxMethod", "Ljava/lang/reflect/Method;", "Ljava/lang/Class;", "getUnboxMethod", "hasInlineClassReceiver", "toInlineClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/DeclarationDescriptor;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/calls/InlineClassAwareCallerKt.class */
public final class InlineClassAwareCallerKt {
    public static /* synthetic */ Caller createInlineClassAwareCallerIfNeeded$default(Caller caller, CallableMemberDescriptor callableMemberDescriptor, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return createInlineClassAwareCallerIfNeeded(caller, callableMemberDescriptor, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ad  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <M extends java.lang.reflect.Member> kotlin.reflect.jvm.internal.calls.Caller<M> createInlineClassAwareCallerIfNeeded(@org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.calls.Caller<? extends M> r6, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r7, boolean r8) {
        /*
            r0 = r6
            java.lang.String r1 = "$this$createInlineClassAwareCallerIfNeeded"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r7
            java.lang.String r1 = "descriptor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r7
            kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor) r0
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isGetterOfUnderlyingPropertyOfInlineClass(r0)
            if (r0 != 0) goto Lad
            r0 = r7
            java.util.List r0 = r0.getValueParameters()
            r1 = r0
            java.lang.String r2 = "descriptor.valueParameters"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r10
            boolean r0 = r0 instanceof java.util.Collection
            if (r0 == 0) goto L43
            r0 = r10
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L43
            r0 = 0
            goto L87
        L43:
            r0 = r10
            java.util.Iterator r0 = r0.iterator()
            r12 = r0
        L4c:
            r0 = r12
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L86
            r0 = r12
            java.lang.Object r0 = r0.next()
            r13 = r0
            r0 = r13
            kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor) r0
            r14 = r0
            r0 = 0
            r15 = r0
            r0 = r14
            r1 = r0
            java.lang.String r2 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getType()
            r1 = r0
            java.lang.String r2 = "it.type"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isInlineClassType(r0)
            if (r0 == 0) goto L4c
            r0 = 1
            goto L87
        L86:
            r0 = 0
        L87:
            if (r0 != 0) goto Lad
            r0 = r7
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getReturnType()
            r1 = r0
            if (r1 == 0) goto L9e
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isInlineClassType(r0)
            r1 = 1
            if (r0 == r1) goto Lad
            goto L9f
        L9e:
        L9f:
            r0 = r6
            boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.calls.BoundCaller
            if (r0 != 0) goto Lb1
            r0 = r7
            boolean r0 = hasInlineClassReceiver(r0)
            if (r0 == 0) goto Lb1
        Lad:
            r0 = 1
            goto Lb2
        Lb1:
            r0 = 0
        Lb2:
            r9 = r0
            r0 = r9
            if (r0 == 0) goto Lc7
            kotlin.reflect.jvm.internal.calls.InlineClassAwareCaller r0 = new kotlin.reflect.jvm.internal.calls.InlineClassAwareCaller
            r1 = r0
            r2 = r7
            r3 = r6
            r4 = r8
            r1.<init>(r2, r3, r4)
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto Lc8
        Lc7:
            r0 = r6
        Lc8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(kotlin.reflect.jvm.internal.calls.Caller, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, boolean):kotlin.reflect.jvm.internal.calls.Caller");
    }

    private static final boolean hasInlineClassReceiver(CallableMemberDescriptor $this$hasInlineClassReceiver) {
        KotlinType expectedReceiverType = getExpectedReceiverType($this$hasInlineClassReceiver);
        return expectedReceiverType != null && InlineClassesUtilsKt.isInlineClassType(expectedReceiverType);
    }

    @NotNull
    public static final Method getUnboxMethod(@NotNull Class<?> getUnboxMethod, @NotNull CallableMemberDescriptor descriptor) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(getUnboxMethod, "$this$getUnboxMethod");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        try {
            Method declaredMethod = getUnboxMethod.getDeclaredMethod("unbox-impl", new Class[0]);
            Intrinsics.checkNotNullExpressionValue(declaredMethod, "getDeclaredMethod(\"unbox…FOR_INLINE_CLASS_MEMBERS)");
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            throw new KotlinReflectionInternalError("No unbox method found in inline class: " + getUnboxMethod + " (calling " + descriptor + ')');
        }
    }

    @NotNull
    public static final Method getBoxMethod(@NotNull Class<?> getBoxMethod, @NotNull CallableMemberDescriptor descriptor) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(getBoxMethod, "$this$getBoxMethod");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        try {
            Method declaredMethod = getBoxMethod.getDeclaredMethod("box-impl", getUnboxMethod(getBoxMethod, descriptor).getReturnType());
            Intrinsics.checkNotNullExpressionValue(declaredMethod, "getDeclaredMethod(\"box\" …d(descriptor).returnType)");
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            throw new KotlinReflectionInternalError("No box method found in inline class: " + getBoxMethod + " (calling " + descriptor + ')');
        }
    }

    @Nullable
    public static final Class<?> toInlineClass(@NotNull KotlinType toInlineClass) {
        Intrinsics.checkNotNullParameter(toInlineClass, "$this$toInlineClass");
        return toInlineClass(toInlineClass.getConstructor().mo3831getDeclarationDescriptor());
    }

    @Nullable
    public static final Class<?> toInlineClass(@Nullable DeclarationDescriptor $this$toInlineClass) {
        if (($this$toInlineClass instanceof ClassDescriptor) && InlineClassesUtilsKt.isInlineClass($this$toInlineClass)) {
            Class<?> javaClass = UtilKt.toJavaClass((ClassDescriptor) $this$toInlineClass);
            if (javaClass != null) {
                return javaClass;
            }
            throw new KotlinReflectionInternalError("Class object for the class " + ((ClassDescriptor) $this$toInlineClass).getName() + " cannot be found (classId=" + DescriptorUtilsKt.getClassId((ClassifierDescriptor) $this$toInlineClass) + ')');
        }
        return null;
    }

    private static final KotlinType getExpectedReceiverType(CallableMemberDescriptor $this$expectedReceiverType) {
        ReceiverParameterDescriptor extensionReceiver = $this$expectedReceiverType.getExtensionReceiverParameter();
        ReceiverParameterDescriptor dispatchReceiver = $this$expectedReceiverType.getDispatchReceiverParameter();
        if (extensionReceiver != null) {
            return extensionReceiver.getType();
        }
        if (dispatchReceiver == null) {
            return null;
        }
        if ($this$expectedReceiverType instanceof ConstructorDescriptor) {
            return dispatchReceiver.getType();
        }
        DeclarationDescriptor containingDeclaration = $this$expectedReceiverType.getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            containingDeclaration = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
        return classDescriptor != null ? classDescriptor.getDefaultType() : null;
    }

    @Nullable
    public static final Object coerceToExpectedReceiverType(@Nullable Object $this$coerceToExpectedReceiverType, @NotNull CallableMemberDescriptor descriptor) {
        Method unboxMethod;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if ((descriptor instanceof PropertyDescriptor) && InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass((VariableDescriptor) descriptor)) {
            return $this$coerceToExpectedReceiverType;
        }
        KotlinType expectedReceiverType = getExpectedReceiverType(descriptor);
        if (expectedReceiverType != null) {
            Class<?> inlineClass = toInlineClass(expectedReceiverType);
            if (inlineClass != null && (unboxMethod = getUnboxMethod(inlineClass, descriptor)) != null) {
                return unboxMethod.invoke($this$coerceToExpectedReceiverType, new Object[0]);
            }
        }
        return $this$coerceToExpectedReceiverType;
    }
}
