package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor.class */
public abstract class AbstractClassTypeConstructor extends AbstractTypeConstructor implements TypeConstructor {
    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    /* renamed from: getDeclarationDescriptor */
    public abstract ClassDescriptor mo3831getDeclarationDescriptor();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 2:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 1:
            case 3:
            case 4:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 2:
            default:
                i2 = 3;
                break;
            case 1:
            case 3:
            case 4:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "storageManager";
                break;
            case 1:
            case 3:
            case 4:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor";
                break;
            case 2:
                objArr[0] = "classifier";
                break;
        }
        switch (i) {
            case 0:
            case 2:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor";
                break;
            case 1:
                objArr[1] = "getBuiltIns";
                break;
            case 3:
            case 4:
                objArr[1] = "getAdditionalNeighboursInSupertypeGraph";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 1:
            case 3:
            case 4:
                break;
            case 2:
                objArr[2] = "isSameClassifier";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 2:
            default:
                throw new IllegalArgumentException(str2);
            case 1:
            case 3:
            case 4:
                throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractClassTypeConstructor(@NotNull StorageManager storageManager) {
        super(storageManager);
        if (storageManager == null) {
            $$$reportNull$$$0(0);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    @NotNull
    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = DescriptorUtilsKt.getBuiltIns(mo3831getDeclarationDescriptor());
        if (builtIns == null) {
            $$$reportNull$$$0(1);
        }
        return builtIns;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    protected boolean isSameClassifier(@NotNull ClassifierDescriptor classifier) {
        if (classifier == null) {
            $$$reportNull$$$0(2);
        }
        return (classifier instanceof ClassDescriptor) && areFqNamesEqual(mo3831getDeclarationDescriptor(), classifier);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    @NotNull
    protected Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean useCompanions) {
        DeclarationDescriptor containingDeclaration = mo3831getDeclarationDescriptor().getContainingDeclaration();
        if (!(containingDeclaration instanceof ClassDescriptor)) {
            List listEmptyList = Collections.emptyList();
            if (listEmptyList == null) {
                $$$reportNull$$$0(3);
            }
            return listEmptyList;
        }
        Collection<KotlinType> additionalNeighbours = new SmartList<>();
        ClassDescriptor containingClassDescriptor = (ClassDescriptor) containingDeclaration;
        additionalNeighbours.add(containingClassDescriptor.getDefaultType());
        ClassDescriptor companion = containingClassDescriptor.mo3497getCompanionObjectDescriptor();
        if (useCompanions && companion != null) {
            additionalNeighbours.add(companion.getDefaultType());
        }
        if (additionalNeighbours == null) {
            $$$reportNull$$$0(4);
        }
        return additionalNeighbours;
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    @Nullable
    protected KotlinType defaultSupertypeIfEmpty() {
        if (KotlinBuiltIns.isSpecialClassWithNoSupertypes(mo3831getDeclarationDescriptor())) {
            return null;
        }
        return getBuiltIns().getAnyType();
    }
}
