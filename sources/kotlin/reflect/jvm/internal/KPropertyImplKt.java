package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.JvmFunctionSignature;
import kotlin.reflect.jvm.internal.JvmPropertySignature;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass;
import kotlin.reflect.jvm.internal.calls.ThrowingCaller;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KPropertyImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"�� \n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\u001a \u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\b*\u00020\nH\u0002\"\"\u0010��\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000b"}, d2 = {"boundReceiver", "", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "getBoundReceiver", "(Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;)Ljava/lang/Object;", "computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/calls/Caller;", "isGetter", "", "isJvmFieldPropertyInCompanionObject", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KPropertyImplKt.class */
public final class KPropertyImplKt {
    @Nullable
    public static final Object getBoundReceiver(@NotNull KPropertyImpl.Accessor<?, ?> boundReceiver) {
        Intrinsics.checkNotNullParameter(boundReceiver, "$this$boundReceiver");
        return boundReceiver.getProperty().getBoundReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Caller<?> computeCallerForAccessor(final KPropertyImpl.Accessor<?, ?> accessor, final boolean isGetter) throws NoSuchMethodException, SecurityException {
        JvmFunctionSignature.KotlinFunction setterSignature;
        Method setterMethod;
        Caller boundInstance;
        JvmProtoBuf.JvmMethodSignature setter;
        Method unboxMethod;
        if (KDeclarationContainerImpl.Companion.getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection().matches(accessor.getProperty().getSignature())) {
            return ThrowingCaller.INSTANCE;
        }
        final AnonymousClass1 $fun$isJvmStaticProperty$1 = new AnonymousClass1(accessor);
        final AnonymousClass2 $fun$isNotNullProperty$2 = new AnonymousClass2(accessor);
        Function1<Field, CallerImpl<? extends Field>> function1 = new Function1<Field, CallerImpl<? extends Field>>() { // from class: kotlin.reflect.jvm.internal.KPropertyImplKt.computeCallerForAccessor.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CallerImpl<Field> invoke(@NotNull Field field) {
                Intrinsics.checkNotNullParameter(field, "field");
                if (KPropertyImplKt.isJvmFieldPropertyInCompanionObject(accessor.getProperty().getDescriptor()) || !Modifier.isStatic(field.getModifiers())) {
                    if (isGetter) {
                        return accessor.isBound() ? new CallerImpl.FieldGetter.BoundInstance(field, KPropertyImplKt.getBoundReceiver(accessor)) : new CallerImpl.FieldGetter.Instance(field);
                    }
                    return accessor.isBound() ? new CallerImpl.FieldSetter.BoundInstance(field, $fun$isNotNullProperty$2.invoke2(), KPropertyImplKt.getBoundReceiver(accessor)) : new CallerImpl.FieldSetter.Instance(field, $fun$isNotNullProperty$2.invoke2());
                }
                if (!$fun$isJvmStaticProperty$1.invoke2()) {
                    return isGetter ? new CallerImpl.FieldGetter.Static(field) : new CallerImpl.FieldSetter.Static(field, $fun$isNotNullProperty$2.invoke2());
                }
                if (isGetter) {
                    return accessor.isBound() ? new CallerImpl.FieldGetter.BoundJvmStaticInObject(field) : new CallerImpl.FieldGetter.JvmStaticInObject(field);
                }
                return accessor.isBound() ? new CallerImpl.FieldSetter.BoundJvmStaticInObject(field, $fun$isNotNullProperty$2.invoke2()) : new CallerImpl.FieldSetter.JvmStaticInObject(field, $fun$isNotNullProperty$2.invoke2());
            }
        };
        JvmPropertySignature jvmSignature = RuntimeTypeMapper.INSTANCE.mapPropertySignature(accessor.getProperty().getDescriptor());
        if (jvmSignature instanceof JvmPropertySignature.KotlinProperty) {
            JvmProtoBuf.JvmPropertySignature $this$run = ((JvmPropertySignature.KotlinProperty) jvmSignature).getSignature();
            if (isGetter) {
                setter = $this$run.hasGetter() ? $this$run.getGetter() : null;
            } else {
                setter = $this$run.hasSetter() ? $this$run.getSetter() : null;
            }
            JvmProtoBuf.JvmMethodSignature accessorSignature = setter;
            Method accessor2 = accessorSignature != null ? accessor.getProperty().getContainer().findMethodBySignature(((JvmPropertySignature.KotlinProperty) jvmSignature).getNameResolver().getString(accessorSignature.getName()), ((JvmPropertySignature.KotlinProperty) jvmSignature).getNameResolver().getString(accessorSignature.getDesc())) : null;
            if (accessor2 == null) {
                if (InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(accessor.getProperty().getDescriptor()) && Intrinsics.areEqual(accessor.getProperty().getDescriptor().getVisibility(), DescriptorVisibilities.INTERNAL)) {
                    Class<?> inlineClass = InlineClassAwareCallerKt.toInlineClass(accessor.getProperty().getDescriptor().getContainingDeclaration());
                    if (inlineClass == null || (unboxMethod = InlineClassAwareCallerKt.getUnboxMethod(inlineClass, accessor.getProperty().getDescriptor())) == null) {
                        throw new KotlinReflectionInternalError("Underlying property of inline class " + accessor.getProperty() + " should have a field");
                    }
                    boundInstance = accessor.isBound() ? new InternalUnderlyingValOfInlineClass.Bound(unboxMethod, getBoundReceiver(accessor)) : new InternalUnderlyingValOfInlineClass.Unbound(unboxMethod);
                } else {
                    Field javaField = accessor.getProperty().getJavaField();
                    if (javaField == null) {
                        throw new KotlinReflectionInternalError("No accessors or field is found for property " + accessor.getProperty());
                    }
                    boundInstance = function1.invoke(javaField);
                }
            } else if (!Modifier.isStatic(accessor2.getModifiers())) {
                boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundInstance(accessor2, getBoundReceiver(accessor)) : new CallerImpl.Method.Instance(accessor2);
            } else if ($fun$isJvmStaticProperty$1.invoke2()) {
                boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundJvmStaticInObject(accessor2) : new CallerImpl.Method.JvmStaticInObject(accessor2);
            } else {
                boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundStatic(accessor2, getBoundReceiver(accessor)) : new CallerImpl.Method.Static(accessor2);
            }
        } else if (jvmSignature instanceof JvmPropertySignature.JavaField) {
            boundInstance = function1.invoke(((JvmPropertySignature.JavaField) jvmSignature).getField());
        } else if (jvmSignature instanceof JvmPropertySignature.JavaMethodProperty) {
            if (isGetter) {
                setterMethod = ((JvmPropertySignature.JavaMethodProperty) jvmSignature).getGetterMethod();
            } else {
                setterMethod = ((JvmPropertySignature.JavaMethodProperty) jvmSignature).getSetterMethod();
                if (setterMethod == null) {
                    throw new KotlinReflectionInternalError("No source found for setter of Java method property: " + ((JvmPropertySignature.JavaMethodProperty) jvmSignature).getGetterMethod());
                }
            }
            Method method = setterMethod;
            boundInstance = accessor.isBound() ? new CallerImpl.Method.BoundInstance(method, getBoundReceiver(accessor)) : new CallerImpl.Method.Instance(method);
        } else {
            if (!(jvmSignature instanceof JvmPropertySignature.MappedKotlinProperty)) {
                throw new NoWhenBranchMatchedException();
            }
            if (isGetter) {
                setterSignature = ((JvmPropertySignature.MappedKotlinProperty) jvmSignature).getGetterSignature();
            } else {
                setterSignature = ((JvmPropertySignature.MappedKotlinProperty) jvmSignature).getSetterSignature();
                if (setterSignature == null) {
                    throw new KotlinReflectionInternalError("No setter found for property " + accessor.getProperty());
                }
            }
            JvmFunctionSignature.KotlinFunction signature = setterSignature;
            Method accessor3 = accessor.getProperty().getContainer().findMethodBySignature(signature.getMethodName(), signature.getMethodDesc());
            if (accessor3 == null) {
                throw new KotlinReflectionInternalError("No accessor found for property " + accessor.getProperty());
            }
            boolean z = !Modifier.isStatic(accessor3.getModifiers());
            if (!_Assertions.ENABLED || z) {
                return accessor.isBound() ? new CallerImpl.Method.BoundInstance(accessor3, getBoundReceiver(accessor)) : new CallerImpl.Method.Instance(accessor3);
            }
            throw new AssertionError("Mapped property cannot have a static accessor: " + accessor.getProperty());
        }
        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(boundInstance, accessor.getDescriptor(), false, 2, null);
    }

    /* compiled from: KPropertyImpl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\b\n��\n\u0002\u0010\u000b\n��\u0010��\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isJvmStaticProperty", "", "invoke"})
    /* renamed from: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KPropertyImplKt$computeCallerForAccessor$1.class */
    static final class AnonymousClass1 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ KPropertyImpl.Accessor $this_computeCallerForAccessor;

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(KPropertyImpl.Accessor accessor) {
            super(0);
            this.$this_computeCallerForAccessor = accessor;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            return this.$this_computeCallerForAccessor.getProperty().getDescriptor().getAnnotations().hasAnnotation(UtilKt.getJVM_STATIC());
        }
    }

    /* compiled from: KPropertyImpl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\b\n��\n\u0002\u0010\u000b\n��\u0010��\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"isNotNullProperty", "", "invoke"})
    /* renamed from: kotlin.reflect.jvm.internal.KPropertyImplKt$computeCallerForAccessor$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KPropertyImplKt$computeCallerForAccessor$2.class */
    static final class AnonymousClass2 extends Lambda implements Function0<Boolean> {
        final /* synthetic */ KPropertyImpl.Accessor $this_computeCallerForAccessor;

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(KPropertyImpl.Accessor accessor) {
            super(0);
            this.$this_computeCallerForAccessor = accessor;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            return !TypeUtils.isNullableType(this.$this_computeCallerForAccessor.getProperty().getDescriptor().getType());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isJvmFieldPropertyInCompanionObject(PropertyDescriptor $this$isJvmFieldPropertyInCompanionObject) {
        DeclarationDescriptor containingDeclaration = $this$isJvmFieldPropertyInCompanionObject.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "containingDeclaration");
        if (!DescriptorUtils.isCompanionObject(containingDeclaration)) {
            return false;
        }
        DeclarationDescriptor outerClass = containingDeclaration.getContainingDeclaration();
        if (DescriptorUtils.isInterface(outerClass) || DescriptorUtils.isAnnotationClass(outerClass)) {
            return ($this$isJvmFieldPropertyInCompanionObject instanceof DeserializedPropertyDescriptor) && JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor) $this$isJvmFieldPropertyInCompanionObject).getProto());
        }
        return true;
    }
}
