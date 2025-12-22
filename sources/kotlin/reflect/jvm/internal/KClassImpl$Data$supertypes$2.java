package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: KClassImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\u0014\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\u0010��\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0003*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00010\u0001\"\b\b��\u0010\u0004*\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KTypeImpl;", "kotlin.jvm.PlatformType", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "invoke"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KClassImpl$Data$supertypes$2.class */
final class KClassImpl$Data$supertypes$2 extends Lambda implements Function0<List<? extends KTypeImpl>> {
    final /* synthetic */ KClassImpl.Data this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    KClassImpl$Data$supertypes$2(KClassImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<? extends KTypeImpl> invoke() {
        boolean z;
        TypeConstructor typeConstructor = this.this$0.getDescriptor().getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor, "descriptor.typeConstructor");
        Collection kotlinTypes = typeConstructor.mo3835getSupertypes();
        Intrinsics.checkNotNullExpressionValue(kotlinTypes, "descriptor.typeConstructor.supertypes");
        ArrayList result = new ArrayList(kotlinTypes.size());
        Collection $this$mapTo$iv = kotlinTypes;
        for (Object item$iv : $this$mapTo$iv) {
            final KotlinType kotlinType = (KotlinType) item$iv;
            Intrinsics.checkNotNullExpressionValue(kotlinType, "kotlinType");
            result.add(new KTypeImpl(kotlinType, new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Type invoke() {
                    ClassifierDescriptor superClass = kotlinType.getConstructor().mo3831getDeclarationDescriptor();
                    if (!(superClass instanceof ClassDescriptor)) {
                        throw new KotlinReflectionInternalError("Supertype not a class: " + superClass);
                    }
                    Class superJavaClass = UtilKt.toJavaClass((ClassDescriptor) superClass);
                    if (superJavaClass != null) {
                        if (Intrinsics.areEqual(KClassImpl.this.getJClass().getSuperclass(), superJavaClass)) {
                            Type genericSuperclass = KClassImpl.this.getJClass().getGenericSuperclass();
                            Intrinsics.checkNotNullExpressionValue(genericSuperclass, "jClass.genericSuperclass");
                            return genericSuperclass;
                        }
                        Class<?>[] interfaces = KClassImpl.this.getJClass().getInterfaces();
                        Intrinsics.checkNotNullExpressionValue(interfaces, "jClass.interfaces");
                        int index = ArraysKt.indexOf(interfaces, superJavaClass);
                        if (index < 0) {
                            throw new KotlinReflectionInternalError("No superclass of " + this.this$0 + " in Java reflection for " + superClass);
                        }
                        Type type = KClassImpl.this.getJClass().getGenericInterfaces()[index];
                        Intrinsics.checkNotNullExpressionValue(type, "jClass.genericInterfaces[index]");
                        return type;
                    }
                    throw new KotlinReflectionInternalError("Unsupported superclass of " + this.this$0 + ": " + superClass);
                }
            }));
        }
        if (!KotlinBuiltIns.isSpecialClassWithNoSupertypes(this.this$0.getDescriptor())) {
            ArrayList $this$all$iv = result;
            if (!($this$all$iv instanceof Collection) || !$this$all$iv.isEmpty()) {
                Iterator it = $this$all$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        KTypeImpl it2 = (KTypeImpl) element$iv;
                        ClassDescriptor classDescriptorForType = DescriptorUtils.getClassDescriptorForType(it2.getType());
                        Intrinsics.checkNotNullExpressionValue(classDescriptorForType, "DescriptorUtils.getClassDescriptorForType(it.type)");
                        ClassKind classKind = classDescriptorForType.getKind();
                        Intrinsics.checkNotNullExpressionValue(classKind, "DescriptorUtils.getClass…ptorForType(it.type).kind");
                        if (!(classKind == ClassKind.INTERFACE || classKind == ClassKind.ANNOTATION_CLASS)) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                SimpleType anyType = DescriptorUtilsKt.getBuiltIns(this.this$0.getDescriptor()).getAnyType();
                Intrinsics.checkNotNullExpressionValue(anyType, "descriptor.builtIns.anyType");
                result.add(new KTypeImpl(anyType, new Function0<Type>() { // from class: kotlin.reflect.jvm.internal.KClassImpl$Data$supertypes$2.3
                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    public final Type invoke() {
                        return Object.class;
                    }
                }));
            }
        }
        return CollectionsKt.compact(result);
    }
}
