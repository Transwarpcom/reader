package kotlin.reflect.jvm.internal.impl.load.java;

import io.vertx.core.cli.converters.FromBasedConverter;
import java.util.HashMap;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DelegatedDescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities.class */
public class JavaDescriptorVisibilities {

    @NotNull
    public static final DescriptorVisibility PACKAGE_VISIBILITY = new DelegatedDescriptorVisibility(JavaVisibilities.PackageVisibility.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "what";
                    break;
                case 1:
                    objArr[0] = FromBasedConverter.FROM;
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$1";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
        public boolean isVisible(@Nullable ReceiverValue receiver, @NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
            if (what == null) {
                $$$reportNull$$$0(0);
            }
            if (from == null) {
                $$$reportNull$$$0(1);
            }
            return JavaDescriptorVisibilities.areInSamePackage(what, from);
        }
    };

    @NotNull
    public static final DescriptorVisibility PROTECTED_STATIC_VISIBILITY = new DelegatedDescriptorVisibility(JavaVisibilities.ProtectedStaticVisibility.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.2
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "what";
                    break;
                case 1:
                    objArr[0] = FromBasedConverter.FROM;
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$2";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
        public boolean isVisible(@Nullable ReceiverValue receiver, @NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
            if (what == null) {
                $$$reportNull$$$0(0);
            }
            if (from == null) {
                $$$reportNull$$$0(1);
            }
            return JavaDescriptorVisibilities.isVisibleForProtectedAndPackage(receiver, what, from);
        }
    };

    @NotNull
    public static final DescriptorVisibility PROTECTED_AND_PACKAGE = new DelegatedDescriptorVisibility(JavaVisibilities.ProtectedAndPackage.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.3
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "what";
                    break;
                case 1:
                    objArr[0] = FromBasedConverter.FROM;
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$3";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
        public boolean isVisible(@Nullable ReceiverValue receiver, @NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
            if (what == null) {
                $$$reportNull$$$0(0);
            }
            if (from == null) {
                $$$reportNull$$$0(1);
            }
            return JavaDescriptorVisibilities.isVisibleForProtectedAndPackage(receiver, what, from);
        }
    };

    @NotNull
    private static final Map<Visibility, DescriptorVisibility> visibilitiesMapping = new HashMap();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 5:
            case 6:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                i2 = 3;
                break;
            case 5:
            case 6:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "what";
                break;
            case 1:
                objArr[0] = FromBasedConverter.FROM;
                break;
            case 2:
                objArr[0] = "first";
                break;
            case 3:
                objArr[0] = "second";
                break;
            case 4:
                objArr[0] = "visibility";
                break;
            case 5:
            case 6:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
                break;
            case 5:
            case 6:
                objArr[1] = "toDescriptorVisibility";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[2] = "isVisibleForProtectedAndPackage";
                break;
            case 2:
            case 3:
                objArr[2] = "areInSamePackage";
                break;
            case 4:
                objArr[2] = "toDescriptorVisibility";
                break;
            case 5:
            case 6:
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException(str2);
            case 5:
            case 6:
                throw new IllegalStateException(str2);
        }
    }

    static {
        recordVisibilityMapping(PACKAGE_VISIBILITY);
        recordVisibilityMapping(PROTECTED_STATIC_VISIBILITY);
        recordVisibilityMapping(PROTECTED_AND_PACKAGE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isVisibleForProtectedAndPackage(@Nullable ReceiverValue receiver, @NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
        if (what == null) {
            $$$reportNull$$$0(0);
        }
        if (from == null) {
            $$$reportNull$$$0(1);
        }
        if (areInSamePackage(DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(what), from)) {
            return true;
        }
        return DescriptorVisibilities.PROTECTED.isVisible(receiver, what, from);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean areInSamePackage(@NotNull DeclarationDescriptor first, @NotNull DeclarationDescriptor second) {
        if (first == null) {
            $$$reportNull$$$0(2);
        }
        if (second == null) {
            $$$reportNull$$$0(3);
        }
        PackageFragmentDescriptor whatPackage = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(first, PackageFragmentDescriptor.class, false);
        PackageFragmentDescriptor fromPackage = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(second, PackageFragmentDescriptor.class, false);
        return (fromPackage == null || whatPackage == null || !whatPackage.getFqName().equals(fromPackage.getFqName())) ? false : true;
    }

    private static void recordVisibilityMapping(DescriptorVisibility visibility) {
        visibilitiesMapping.put(visibility.getDelegate(), visibility);
    }

    @NotNull
    public static DescriptorVisibility toDescriptorVisibility(@NotNull Visibility visibility) {
        if (visibility == null) {
            $$$reportNull$$$0(4);
        }
        DescriptorVisibility correspondingVisibility = visibilitiesMapping.get(visibility);
        if (correspondingVisibility == null) {
            DescriptorVisibility descriptorVisibility = DescriptorVisibilities.toDescriptorVisibility(visibility);
            if (descriptorVisibility == null) {
                $$$reportNull$$$0(5);
            }
            return descriptorVisibility;
        }
        if (correspondingVisibility == null) {
            $$$reportNull$$$0(6);
        }
        return correspondingVisibility;
    }
}
