package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Collection;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassicBuiltinSpecialProperties.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/ClassicBuiltinSpecialProperties.class */
public final class ClassicBuiltinSpecialProperties {

    @NotNull
    public static final ClassicBuiltinSpecialProperties INSTANCE = new ClassicBuiltinSpecialProperties();

    private ClassicBuiltinSpecialProperties() {
    }

    @Nullable
    public final String getBuiltinSpecialPropertyGetterName(@NotNull CallableMemberDescriptor $this$getBuiltinSpecialPropertyGetterName) {
        Name name;
        Intrinsics.checkNotNullParameter($this$getBuiltinSpecialPropertyGetterName, "<this>");
        boolean zIsBuiltIn = KotlinBuiltIns.isBuiltIn($this$getBuiltinSpecialPropertyGetterName);
        if (_Assertions.ENABLED && !zIsBuiltIn) {
            throw new AssertionError("This method is defined only for builtin members, but " + $this$getBuiltinSpecialPropertyGetterName + " found");
        }
        CallableMemberDescriptor descriptor = DescriptorUtilsKt.firstOverridden$default(DescriptorUtilsKt.getPropertyIfAccessor($this$getBuiltinSpecialPropertyGetterName), false, new Function1<CallableMemberDescriptor, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties$getBuiltinSpecialPropertyGetterName$descriptor$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(@NotNull CallableMemberDescriptor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(it);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(CallableMemberDescriptor callableMemberDescriptor) {
                return Boolean.valueOf(invoke2(callableMemberDescriptor));
            }
        }, 1, null);
        if (descriptor == null || (name = BuiltinSpecialProperties.INSTANCE.getPROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP().get(DescriptorUtilsKt.getFqNameSafe(descriptor))) == null) {
            return null;
        }
        return name.asString();
    }

    public final boolean hasBuiltinSpecialPropertyFqName(@NotNull CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "callableMemberDescriptor");
        if (BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(callableMemberDescriptor.getName())) {
            return hasBuiltinSpecialPropertyFqNameImpl(callableMemberDescriptor);
        }
        return false;
    }

    private final boolean hasBuiltinSpecialPropertyFqNameImpl(CallableMemberDescriptor $this$hasBuiltinSpecialPropertyFqNameImpl) {
        if (CollectionsKt.contains(BuiltinSpecialProperties.INSTANCE.getSPECIAL_FQ_NAMES(), DescriptorUtilsKt.fqNameOrNull($this$hasBuiltinSpecialPropertyFqNameImpl)) && $this$hasBuiltinSpecialPropertyFqNameImpl.getValueParameters().isEmpty()) {
            return true;
        }
        if (!KotlinBuiltIns.isBuiltIn($this$hasBuiltinSpecialPropertyFqNameImpl)) {
            return false;
        }
        Iterable overriddenDescriptors = $this$hasBuiltinSpecialPropertyFqNameImpl.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(overriddenDescriptors, "overriddenDescriptors");
        Iterable $this$any$iv = overriddenDescriptors;
        if (((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            CallableMemberDescriptor it = (CallableMemberDescriptor) element$iv;
            ClassicBuiltinSpecialProperties classicBuiltinSpecialProperties = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (classicBuiltinSpecialProperties.hasBuiltinSpecialPropertyFqName(it)) {
                return true;
            }
        }
        return false;
    }
}
