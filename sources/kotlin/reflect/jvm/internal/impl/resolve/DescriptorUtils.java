package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils.class */
public class DescriptorUtils {
    public static final FqName JVM_NAME;
    static final /* synthetic */ boolean $assertionsDisabled;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 41:
            case 44:
            case 45:
            case 46:
            case 48:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 61:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 70:
            case 71:
            case 72:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 82:
            case 84:
            case 85:
            case 87:
            case 88:
            case 89:
            case 90:
            case 92:
            case 94:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 69:
            case 73:
            case 80:
            case 81:
            case 83:
            case 86:
            case 91:
            case 93:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 41:
            case 44:
            case 45:
            case 46:
            case 48:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 61:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 70:
            case 71:
            case 72:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 82:
            case 84:
            case 85:
            case 87:
            case 88:
            case 89:
            case 90:
            case 92:
            case 94:
            default:
                i2 = 3;
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 69:
            case 73:
            case 80:
            case 81:
            case 83:
            case 86:
            case 91:
            case 93:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 21:
            case 23:
            case 24:
            case 34:
            case 35:
            case 36:
            case 57:
            case 58:
            case 59:
            case 61:
            case 79:
            case 92:
            case 94:
                objArr[0] = "descriptor";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 69:
            case 73:
            case 80:
            case 81:
            case 83:
            case 86:
            case 91:
            case 93:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
            case 16:
                objArr[0] = "first";
                break;
            case 17:
                objArr[0] = "second";
                break;
            case 18:
            case 19:
                objArr[0] = "aClass";
                break;
            case 20:
                objArr[0] = "kotlinType";
                break;
            case 25:
                objArr[0] = "declarationDescriptor";
                break;
            case 26:
            case 28:
                objArr[0] = "subClass";
                break;
            case 27:
            case 29:
            case 33:
                objArr[0] = "superClass";
                break;
            case 30:
            case 32:
            case 45:
            case 64:
                objArr[0] = "type";
                break;
            case 31:
                objArr[0] = "other";
                break;
            case 37:
                objArr[0] = "classKind";
                break;
            case 38:
            case 39:
            case 41:
            case 44:
            case 48:
            case 54:
            case 65:
            case 66:
            case 67:
            case 74:
            case 75:
                objArr[0] = "classDescriptor";
                break;
            case 46:
                objArr[0] = "typeConstructor";
                break;
            case 55:
                objArr[0] = "innerClassName";
                break;
            case 56:
                objArr[0] = "location";
                break;
            case 63:
                objArr[0] = "variable";
                break;
            case 68:
                objArr[0] = OperatorName.FILL_NON_ZERO;
                break;
            case 70:
                objArr[0] = "current";
                break;
            case 71:
                objArr[0] = CacheOperationExpressionEvaluator.RESULT_VARIABLE;
                break;
            case 72:
                objArr[0] = "memberDescriptor";
                break;
            case 76:
            case 77:
            case 78:
                objArr[0] = "annotated";
                break;
            case 82:
            case 84:
            case 87:
            case 89:
                objArr[0] = "scope";
                break;
            case 85:
            case 88:
            case 90:
                objArr[0] = "name";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 41:
            case 44:
            case 45:
            case 46:
            case 48:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 61:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 70:
            case 71:
            case 72:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 82:
            case 84:
            case 85:
            case 87:
            case 88:
            case 89:
            case 90:
            case 92:
            case 94:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
            case 4:
                objArr[1] = "getFqNameSafe";
                break;
            case 7:
                objArr[1] = "getFqNameUnsafe";
                break;
            case 9:
            case 10:
                objArr[1] = "getFqNameFromTopLevelClass";
                break;
            case 12:
                objArr[1] = "getClassIdForNonLocalClass";
                break;
            case 22:
                objArr[1] = "getContainingModule";
                break;
            case 40:
                objArr[1] = "getSuperclassDescriptors";
                break;
            case 42:
            case 43:
                objArr[1] = "getSuperClassType";
                break;
            case 47:
                objArr[1] = "getClassDescriptorForTypeConstructor";
                break;
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
                objArr[1] = "getDefaultConstructorVisibility";
                break;
            case 60:
                objArr[1] = "unwrapFakeOverride";
                break;
            case 62:
                objArr[1] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 69:
                objArr[1] = "getAllOverriddenDescriptors";
                break;
            case 73:
                objArr[1] = "getAllOverriddenDeclarations";
                break;
            case 80:
            case 81:
                objArr[1] = "getContainingSourceFile";
                break;
            case 83:
                objArr[1] = "getAllDescriptors";
                break;
            case 86:
                objArr[1] = "getFunctionByName";
                break;
            case 91:
                objArr[1] = "getPropertyByName";
                break;
            case 93:
                objArr[1] = "getDirectMember";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "getDispatchReceiverParameterIfNeeded";
                break;
            case 1:
                objArr[2] = "isLocal";
                break;
            case 2:
                objArr[2] = "getFqName";
                break;
            case 3:
                objArr[2] = "getFqNameSafe";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 69:
            case 73:
            case 80:
            case 81:
            case 83:
            case 86:
            case 91:
            case 93:
                break;
            case 5:
                objArr[2] = "getFqNameSafeIfPossible";
                break;
            case 6:
                objArr[2] = "getFqNameUnsafe";
                break;
            case 8:
                objArr[2] = "getFqNameFromTopLevelClass";
                break;
            case 11:
                objArr[2] = "getClassIdForNonLocalClass";
                break;
            case 13:
                objArr[2] = "isExtension";
                break;
            case 14:
                objArr[2] = "isOverride";
                break;
            case 15:
                objArr[2] = "isStaticDeclaration";
                break;
            case 16:
            case 17:
                objArr[2] = "areInSameModule";
                break;
            case 18:
            case 19:
                objArr[2] = "getParentOfType";
                break;
            case 20:
            case 23:
                objArr[2] = "getContainingModuleOrNull";
                break;
            case 21:
                objArr[2] = "getContainingModule";
                break;
            case 24:
                objArr[2] = "getContainingClass";
                break;
            case 25:
                objArr[2] = "isAncestor";
                break;
            case 26:
            case 27:
                objArr[2] = "isDirectSubclass";
                break;
            case 28:
            case 29:
                objArr[2] = "isSubclass";
                break;
            case 30:
            case 31:
                objArr[2] = "isSameClass";
                break;
            case 32:
            case 33:
                objArr[2] = "isSubtypeOfClass";
                break;
            case 34:
                objArr[2] = "isAnonymousObject";
                break;
            case 35:
                objArr[2] = "isAnonymousFunction";
                break;
            case 36:
                objArr[2] = "isEnumEntry";
                break;
            case 37:
                objArr[2] = "isKindOf";
                break;
            case 38:
                objArr[2] = "hasAbstractMembers";
                break;
            case 39:
                objArr[2] = "getSuperclassDescriptors";
                break;
            case 41:
                objArr[2] = "getSuperClassType";
                break;
            case 44:
                objArr[2] = "getSuperClassDescriptor";
                break;
            case 45:
                objArr[2] = "getClassDescriptorForType";
                break;
            case 46:
                objArr[2] = "getClassDescriptorForTypeConstructor";
                break;
            case 48:
                objArr[2] = "getDefaultConstructorVisibility";
                break;
            case 54:
            case 55:
            case 56:
                objArr[2] = "getInnerClassByName";
                break;
            case 57:
                objArr[2] = "isStaticNestedClass";
                break;
            case 58:
                objArr[2] = "isTopLevelOrInnerClass";
                break;
            case 59:
                objArr[2] = "unwrapFakeOverride";
                break;
            case 61:
                objArr[2] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 63:
            case 64:
                objArr[2] = "shouldRecordInitializerForProperty";
                break;
            case 65:
                objArr[2] = "classCanHaveAbstractFakeOverride";
                break;
            case 66:
                objArr[2] = "classCanHaveAbstractDeclaration";
                break;
            case 67:
                objArr[2] = "classCanHaveOpenMembers";
                break;
            case 68:
                objArr[2] = "getAllOverriddenDescriptors";
                break;
            case 70:
            case 71:
                objArr[2] = "collectAllOverriddenDescriptors";
                break;
            case 72:
                objArr[2] = "getAllOverriddenDeclarations";
                break;
            case 74:
                objArr[2] = "isSingletonOrAnonymousObject";
                break;
            case 75:
                objArr[2] = "canHaveDeclaredConstructors";
                break;
            case 76:
                objArr[2] = "getJvmName";
                break;
            case 77:
                objArr[2] = "findJvmNameAnnotation";
                break;
            case 78:
                objArr[2] = "hasJvmNameAnnotation";
                break;
            case 79:
                objArr[2] = "getContainingSourceFile";
                break;
            case 82:
                objArr[2] = "getAllDescriptors";
                break;
            case 84:
            case 85:
                objArr[2] = "getFunctionByName";
                break;
            case 87:
            case 88:
                objArr[2] = "getFunctionByNameOrNull";
                break;
            case 89:
            case 90:
                objArr[2] = "getPropertyByName";
                break;
            case 92:
                objArr[2] = "getDirectMember";
                break;
            case 94:
                objArr[2] = "isMethodOfAny";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 41:
            case 44:
            case 45:
            case 46:
            case 48:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 61:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 70:
            case 71:
            case 72:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 82:
            case 84:
            case 85:
            case 87:
            case 88:
            case 89:
            case 90:
            case 92:
            case 94:
            default:
                throw new IllegalArgumentException(str2);
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case 69:
            case 73:
            case 80:
            case 81:
            case 83:
            case 86:
            case 91:
            case 93:
                throw new IllegalStateException(str2);
        }
    }

    static {
        $assertionsDisabled = !DescriptorUtils.class.desiredAssertionStatus();
        JVM_NAME = new FqName("kotlin.jvm.JvmName");
    }

    private DescriptorUtils() {
    }

    @Nullable
    public static ReceiverParameterDescriptor getDispatchReceiverParameterIfNeeded(@NotNull DeclarationDescriptor containingDeclaration) {
        if (containingDeclaration == null) {
            $$$reportNull$$$0(0);
        }
        if (containingDeclaration instanceof ClassDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) containingDeclaration;
            return classDescriptor.getThisAsReceiverParameter();
        }
        return null;
    }

    public static boolean isLocal(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(1);
        }
        DeclarationDescriptor containingDeclaration = descriptor;
        while (true) {
            DeclarationDescriptor current = containingDeclaration;
            if (current != null) {
                if (isAnonymousObject(current) || isDescriptorWithLocalVisibility(current)) {
                    return true;
                }
                containingDeclaration = current.getContainingDeclaration();
            } else {
                return false;
            }
        }
    }

    public static boolean isDescriptorWithLocalVisibility(DeclarationDescriptor current) {
        return (current instanceof DeclarationDescriptorWithVisibility) && ((DeclarationDescriptorWithVisibility) current).getVisibility() == DescriptorVisibilities.LOCAL;
    }

    @NotNull
    public static FqNameUnsafe getFqName(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(2);
        }
        FqName safe = getFqNameSafeIfPossible(descriptor);
        return safe != null ? safe.toUnsafe() : getFqNameUnsafe(descriptor);
    }

    @NotNull
    public static FqName getFqNameSafe(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(3);
        }
        FqName safe = getFqNameSafeIfPossible(descriptor);
        FqName safe2 = safe != null ? safe : getFqNameUnsafe(descriptor).toSafe();
        if (safe2 == null) {
            $$$reportNull$$$0(4);
        }
        return safe2;
    }

    @Nullable
    private static FqName getFqNameSafeIfPossible(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(5);
        }
        if ((descriptor instanceof ModuleDescriptor) || ErrorUtils.isError(descriptor)) {
            return FqName.ROOT;
        }
        if (descriptor instanceof PackageViewDescriptor) {
            return ((PackageViewDescriptor) descriptor).getFqName();
        }
        if (descriptor instanceof PackageFragmentDescriptor) {
            return ((PackageFragmentDescriptor) descriptor).getFqName();
        }
        return null;
    }

    @NotNull
    private static FqNameUnsafe getFqNameUnsafe(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(6);
        }
        DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
        if (!$assertionsDisabled && containingDeclaration == null) {
            throw new AssertionError("Not package/module descriptor doesn't have containing declaration: " + descriptor);
        }
        FqNameUnsafe fqNameUnsafeChild = getFqName(containingDeclaration).child(descriptor.getName());
        if (fqNameUnsafeChild == null) {
            $$$reportNull$$$0(7);
        }
        return fqNameUnsafeChild;
    }

    public static boolean isTopLevelDeclaration(@Nullable DeclarationDescriptor descriptor) {
        return descriptor != null && (descriptor.getContainingDeclaration() instanceof PackageFragmentDescriptor);
    }

    public static boolean areInSameModule(@NotNull DeclarationDescriptor first, @NotNull DeclarationDescriptor second) {
        if (first == null) {
            $$$reportNull$$$0(16);
        }
        if (second == null) {
            $$$reportNull$$$0(17);
        }
        return getContainingModule(first).equals(getContainingModule(second));
    }

    @Nullable
    public static <D extends DeclarationDescriptor> D getParentOfType(@Nullable DeclarationDescriptor declarationDescriptor, @NotNull Class<D> cls) {
        if (cls == null) {
            $$$reportNull$$$0(18);
        }
        return (D) getParentOfType(declarationDescriptor, cls, true);
    }

    @Nullable
    public static <D extends DeclarationDescriptor> D getParentOfType(@Nullable DeclarationDescriptor declarationDescriptor, @NotNull Class<D> cls, boolean z) {
        if (cls == null) {
            $$$reportNull$$$0(19);
        }
        if (declarationDescriptor == null) {
            return null;
        }
        if (z) {
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        while (declarationDescriptor != null) {
            if (cls.isInstance(declarationDescriptor)) {
                return (D) declarationDescriptor;
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return null;
    }

    @Nullable
    public static ModuleDescriptor getContainingModuleOrNull(@NotNull KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(20);
        }
        ClassifierDescriptor descriptor = kotlinType.getConstructor().mo3831getDeclarationDescriptor();
        if (descriptor == null) {
            return null;
        }
        return getContainingModuleOrNull(descriptor);
    }

    @NotNull
    public static ModuleDescriptor getContainingModule(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(21);
        }
        ModuleDescriptor module = getContainingModuleOrNull(descriptor);
        if (!$assertionsDisabled && module == null) {
            throw new AssertionError("Descriptor without a containing module: " + descriptor);
        }
        if (module == null) {
            $$$reportNull$$$0(22);
        }
        return module;
    }

    @Nullable
    public static ModuleDescriptor getContainingModuleOrNull(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(23);
        }
        while (descriptor != null) {
            if (descriptor instanceof ModuleDescriptor) {
                return (ModuleDescriptor) descriptor;
            }
            if (descriptor instanceof PackageViewDescriptor) {
                return ((PackageViewDescriptor) descriptor).getModule();
            }
            descriptor = descriptor.getContainingDeclaration();
        }
        return null;
    }

    public static boolean isDirectSubclass(@NotNull ClassDescriptor subClass, @NotNull ClassDescriptor superClass) {
        if (subClass == null) {
            $$$reportNull$$$0(26);
        }
        if (superClass == null) {
            $$$reportNull$$$0(27);
        }
        for (KotlinType superType : subClass.getTypeConstructor().mo3835getSupertypes()) {
            if (isSameClass(superType, superClass.getOriginal())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSubclass(@NotNull ClassDescriptor subClass, @NotNull ClassDescriptor superClass) {
        if (subClass == null) {
            $$$reportNull$$$0(28);
        }
        if (superClass == null) {
            $$$reportNull$$$0(29);
        }
        return isSubtypeOfClass(subClass.getDefaultType(), superClass.getOriginal());
    }

    private static boolean isSameClass(@NotNull KotlinType type, @NotNull DeclarationDescriptor other) {
        if (type == null) {
            $$$reportNull$$$0(30);
        }
        if (other == null) {
            $$$reportNull$$$0(31);
        }
        DeclarationDescriptor descriptor = type.getConstructor().mo3831getDeclarationDescriptor();
        if (descriptor != null) {
            DeclarationDescriptor originalDescriptor = descriptor.getOriginal();
            if ((originalDescriptor instanceof ClassifierDescriptor) && (other instanceof ClassifierDescriptor) && ((ClassifierDescriptor) other).getTypeConstructor().equals(((ClassifierDescriptor) originalDescriptor).getTypeConstructor())) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean isSubtypeOfClass(@NotNull KotlinType type, @NotNull DeclarationDescriptor superClass) {
        if (type == null) {
            $$$reportNull$$$0(32);
        }
        if (superClass == null) {
            $$$reportNull$$$0(33);
        }
        if (isSameClass(type, superClass)) {
            return true;
        }
        for (KotlinType superType : type.getConstructor().mo3835getSupertypes()) {
            if (isSubtypeOfClass(superType, superClass)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCompanionObject(@Nullable DeclarationDescriptor descriptor) {
        return isKindOf(descriptor, ClassKind.OBJECT) && ((ClassDescriptor) descriptor).isCompanionObject();
    }

    public static boolean isSealedClass(@Nullable DeclarationDescriptor descriptor) {
        return (isKindOf(descriptor, ClassKind.CLASS) || isKindOf(descriptor, ClassKind.INTERFACE)) && ((ClassDescriptor) descriptor).getModality() == Modality.SEALED;
    }

    public static boolean isAnonymousObject(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(34);
        }
        return isClass(descriptor) && descriptor.getName().equals(SpecialNames.NO_NAME_PROVIDED);
    }

    public static boolean isEnumEntry(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(36);
        }
        return isKindOf(descriptor, ClassKind.ENUM_ENTRY);
    }

    public static boolean isEnumClass(@Nullable DeclarationDescriptor descriptor) {
        return isKindOf(descriptor, ClassKind.ENUM_CLASS);
    }

    public static boolean isAnnotationClass(@Nullable DeclarationDescriptor descriptor) {
        return isKindOf(descriptor, ClassKind.ANNOTATION_CLASS);
    }

    public static boolean isInterface(@Nullable DeclarationDescriptor descriptor) {
        return isKindOf(descriptor, ClassKind.INTERFACE);
    }

    public static boolean isClass(@Nullable DeclarationDescriptor descriptor) {
        return isKindOf(descriptor, ClassKind.CLASS);
    }

    public static boolean isClassOrEnumClass(@Nullable DeclarationDescriptor descriptor) {
        return isClass(descriptor) || isEnumClass(descriptor);
    }

    private static boolean isKindOf(@Nullable DeclarationDescriptor descriptor, @NotNull ClassKind classKind) {
        if (classKind == null) {
            $$$reportNull$$$0(37);
        }
        return (descriptor instanceof ClassDescriptor) && ((ClassDescriptor) descriptor).getKind() == classKind;
    }

    @Nullable
    public static ClassDescriptor getSuperClassDescriptor(@NotNull ClassDescriptor classDescriptor) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(44);
        }
        Collection<KotlinType> superclassTypes = classDescriptor.getTypeConstructor().mo3835getSupertypes();
        for (KotlinType type : superclassTypes) {
            ClassDescriptor superClassDescriptor = getClassDescriptorForType(type);
            if (superClassDescriptor.getKind() != ClassKind.INTERFACE) {
                return superClassDescriptor;
            }
        }
        return null;
    }

    @NotNull
    public static ClassDescriptor getClassDescriptorForType(@NotNull KotlinType type) {
        if (type == null) {
            $$$reportNull$$$0(45);
        }
        return getClassDescriptorForTypeConstructor(type.getConstructor());
    }

    @NotNull
    public static ClassDescriptor getClassDescriptorForTypeConstructor(@NotNull TypeConstructor typeConstructor) {
        if (typeConstructor == null) {
            $$$reportNull$$$0(46);
        }
        ClassifierDescriptor descriptor = typeConstructor.mo3831getDeclarationDescriptor();
        if (!$assertionsDisabled && !(descriptor instanceof ClassDescriptor)) {
            throw new AssertionError("Classifier descriptor of a type should be of type ClassDescriptor: " + typeConstructor);
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) descriptor;
        if (classDescriptor == null) {
            $$$reportNull$$$0(47);
        }
        return classDescriptor;
    }

    @NotNull
    public static DescriptorVisibility getDefaultConstructorVisibility(@NotNull ClassDescriptor classDescriptor, boolean freedomForSealedInterfacesSupported) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(48);
        }
        ClassKind classKind = classDescriptor.getKind();
        if (classKind == ClassKind.ENUM_CLASS || classKind.isSingleton()) {
            DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
            if (descriptorVisibility == null) {
                $$$reportNull$$$0(49);
            }
            return descriptorVisibility;
        }
        if (isSealedClass(classDescriptor)) {
            if (freedomForSealedInterfacesSupported) {
                DescriptorVisibility descriptorVisibility2 = DescriptorVisibilities.PROTECTED;
                if (descriptorVisibility2 == null) {
                    $$$reportNull$$$0(50);
                }
                return descriptorVisibility2;
            }
            DescriptorVisibility descriptorVisibility3 = DescriptorVisibilities.PRIVATE;
            if (descriptorVisibility3 == null) {
                $$$reportNull$$$0(51);
            }
            return descriptorVisibility3;
        }
        if (isAnonymousObject(classDescriptor)) {
            DescriptorVisibility descriptorVisibility4 = DescriptorVisibilities.DEFAULT_VISIBILITY;
            if (descriptorVisibility4 == null) {
                $$$reportNull$$$0(52);
            }
            return descriptorVisibility4;
        }
        if (!$assertionsDisabled && classKind != ClassKind.CLASS && classKind != ClassKind.INTERFACE && classKind != ClassKind.ANNOTATION_CLASS) {
            throw new AssertionError();
        }
        DescriptorVisibility descriptorVisibility5 = DescriptorVisibilities.PUBLIC;
        if (descriptorVisibility5 == null) {
            $$$reportNull$$$0(53);
        }
        return descriptorVisibility5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor] */
    @NotNull
    public static <D extends CallableMemberDescriptor> D unwrapFakeOverride(@NotNull D descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(59);
        }
        while (descriptor.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            Collection<? extends CallableMemberDescriptor> overridden = descriptor.getOverriddenDescriptors();
            if (overridden.isEmpty()) {
                throw new IllegalStateException("Fake override should have at least one overridden descriptor: " + descriptor);
            }
            descriptor = overridden.iterator().next();
        }
        D d = descriptor;
        if (d == null) {
            $$$reportNull$$$0(60);
        }
        return d;
    }

    @NotNull
    public static <D extends DeclarationDescriptorWithVisibility> D unwrapFakeOverrideToAnyDeclaration(@NotNull D descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(61);
        }
        if (descriptor instanceof CallableMemberDescriptor) {
            return unwrapFakeOverride((CallableMemberDescriptor) descriptor);
        }
        if (descriptor == null) {
            $$$reportNull$$$0(62);
        }
        return descriptor;
    }

    public static boolean shouldRecordInitializerForProperty(@NotNull VariableDescriptor variable, @NotNull KotlinType type) {
        if (variable == null) {
            $$$reportNull$$$0(63);
        }
        if (type == null) {
            $$$reportNull$$$0(64);
        }
        if (variable.isVar() || KotlinTypeKt.isError(type)) {
            return false;
        }
        if (TypeUtils.acceptsNullable(type)) {
            return true;
        }
        KotlinBuiltIns builtIns = DescriptorUtilsKt.getBuiltIns(variable);
        if (!KotlinBuiltIns.isPrimitiveType(type) && !KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getStringType(), type) && !KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getNumber().getDefaultType(), type) && !KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getAnyType(), type)) {
            UnsignedTypes unsignedTypes = UnsignedTypes.INSTANCE;
            if (!UnsignedTypes.isUnsignedType(type)) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    public static <D extends CallableDescriptor> Set<D> getAllOverriddenDescriptors(@NotNull D f) {
        if (f == null) {
            $$$reportNull$$$0(68);
        }
        Set<D> result = new LinkedHashSet<>();
        collectAllOverriddenDescriptors(f.getOriginal(), result);
        if (result == null) {
            $$$reportNull$$$0(69);
        }
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <D extends CallableDescriptor> void collectAllOverriddenDescriptors(@NotNull D current, @NotNull Set<D> set) {
        if (current == null) {
            $$$reportNull$$$0(70);
        }
        if (set == 0) {
            $$$reportNull$$$0(71);
        }
        if (set.contains(current)) {
            return;
        }
        for (CallableDescriptor callableDescriptor : current.getOriginal().getOverriddenDescriptors()) {
            CallableDescriptor original = callableDescriptor.getOriginal();
            collectAllOverriddenDescriptors(original, set);
            set.add(original);
        }
    }

    @NotNull
    public static SourceFile getContainingSourceFile(@NotNull DeclarationDescriptor descriptor) {
        if (descriptor == null) {
            $$$reportNull$$$0(79);
        }
        if (descriptor instanceof PropertySetterDescriptor) {
            descriptor = ((PropertySetterDescriptor) descriptor).getCorrespondingProperty();
        }
        if (descriptor instanceof DeclarationDescriptorWithSource) {
            SourceFile containingFile = ((DeclarationDescriptorWithSource) descriptor).getSource().getContainingFile();
            if (containingFile == null) {
                $$$reportNull$$$0(80);
            }
            return containingFile;
        }
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        if (sourceFile == null) {
            $$$reportNull$$$0(81);
        }
        return sourceFile;
    }
}
