package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.builtins.DefaultBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorSimpleFunctionDescriptorImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/ErrorUtils.class */
public class ErrorUtils {
    private static final ModuleDescriptor ERROR_MODULE = new ModuleDescriptor() { // from class: kotlin.reflect.jvm.internal.impl.types.ErrorUtils.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 7:
                case 11:
                case 12:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 1:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 13:
                case 14:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 7:
                case 11:
                case 12:
                default:
                    i2 = 3;
                    break;
                case 1:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 13:
                case 14:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "capability";
                    break;
                case 1:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 13:
                case 14:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$1";
                    break;
                case 2:
                case 7:
                    objArr[0] = "fqName";
                    break;
                case 3:
                    objArr[0] = "nameFilter";
                    break;
                case 11:
                    objArr[0] = "visitor";
                    break;
                case 12:
                    objArr[0] = "targetModule";
                    break;
            }
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 7:
                case 11:
                case 12:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$1";
                    break;
                case 1:
                    objArr[1] = "getAnnotations";
                    break;
                case 4:
                    objArr[1] = "getSubPackagesOf";
                    break;
                case 5:
                    objArr[1] = "getName";
                    break;
                case 6:
                    objArr[1] = "getStableName";
                    break;
                case 8:
                    objArr[1] = "getAllDependencyModules";
                    break;
                case 9:
                    objArr[1] = "getExpectedByModules";
                    break;
                case 10:
                    objArr[1] = "getAllExpectedByModules";
                    break;
                case 13:
                    objArr[1] = "getOriginal";
                    break;
                case 14:
                    objArr[1] = "getBuiltIns";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = "getCapability";
                    break;
                case 1:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 13:
                case 14:
                    break;
                case 2:
                case 3:
                    objArr[2] = "getSubPackagesOf";
                    break;
                case 7:
                    objArr[2] = "getPackage";
                    break;
                case 11:
                    objArr[2] = "accept";
                    break;
                case 12:
                    objArr[2] = "shouldSeeInternalsOf";
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 2:
                case 3:
                case 7:
                case 11:
                case 12:
                default:
                    throw new IllegalArgumentException(str2);
                case 1:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 13:
                case 14:
                    throw new IllegalStateException(str2);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
        @Nullable
        public <T> T getCapability(@NotNull ModuleCapability<T> capability) {
            if (capability == null) {
                $$$reportNull$$$0(0);
                return null;
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
        @NotNull
        public Annotations getAnnotations() {
            Annotations empty = Annotations.Companion.getEMPTY();
            if (empty == null) {
                $$$reportNull$$$0(1);
            }
            return empty;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
        @NotNull
        public Collection<FqName> getSubPackagesOf(@NotNull FqName fqName, @NotNull Function1<? super Name, Boolean> nameFilter) {
            if (fqName == null) {
                $$$reportNull$$$0(2);
            }
            if (nameFilter == null) {
                $$$reportNull$$$0(3);
            }
            List listEmptyList = CollectionsKt.emptyList();
            if (listEmptyList == null) {
                $$$reportNull$$$0(4);
            }
            return listEmptyList;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Named
        @NotNull
        public Name getName() {
            Name nameSpecial = Name.special("<ERROR MODULE>");
            if (nameSpecial == null) {
                $$$reportNull$$$0(5);
            }
            return nameSpecial;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
        @NotNull
        public PackageViewDescriptor getPackage(@NotNull FqName fqName) {
            if (fqName == null) {
                $$$reportNull$$$0(7);
            }
            throw new IllegalStateException("Should not be called!");
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
        @NotNull
        public List<ModuleDescriptor> getExpectedByModules() {
            List<ModuleDescriptor> listEmptyList = CollectionsKt.emptyList();
            if (listEmptyList == null) {
                $$$reportNull$$$0(9);
            }
            return listEmptyList;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
        public <R, D> R accept(@NotNull DeclarationDescriptorVisitor<R, D> visitor, D data) {
            if (visitor == null) {
                $$$reportNull$$$0(11);
                return null;
            }
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
        public boolean shouldSeeInternalsOf(@NotNull ModuleDescriptor targetModule) {
            if (targetModule == null) {
                $$$reportNull$$$0(12);
                return false;
            }
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
        @NotNull
        public DeclarationDescriptor getOriginal() {
            if (this == null) {
                $$$reportNull$$$0(13);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
        @Nullable
        public DeclarationDescriptor getContainingDeclaration() {
            return null;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
        @NotNull
        public KotlinBuiltIns getBuiltIns() {
            DefaultBuiltIns defaultBuiltIns = DefaultBuiltIns.getInstance();
            if (defaultBuiltIns == null) {
                $$$reportNull$$$0(14);
            }
            return defaultBuiltIns;
        }
    };
    private static final ErrorClassDescriptor ERROR_CLASS = new ErrorClassDescriptor(Name.special("<ERROR CLASS>"));
    public static final SimpleType ERROR_TYPE_FOR_LOOP_IN_SUPERTYPES = createErrorType("<LOOP IN SUPERTYPES>");
    private static final KotlinType ERROR_PROPERTY_TYPE = createErrorType("<ERROR PROPERTY TYPE>");
    private static final PropertyDescriptor ERROR_PROPERTY = createErrorProperty();
    private static final Set<PropertyDescriptor> ERROR_PROPERTY_GROUP = Collections.singleton(ERROR_PROPERTY);

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 4:
            case 6:
            case 19:
                str = "@NotNull method %s.%s must not return null";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            default:
                i2 = 3;
                break;
            case 4:
            case 6:
            case 19:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            default:
                objArr[0] = "function";
                break;
            case 1:
            case 2:
            case 3:
            case 7:
            case 11:
            case 15:
                objArr[0] = "debugMessage";
                break;
            case 4:
            case 6:
            case 19:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils";
                break;
            case 5:
                objArr[0] = "ownerScope";
                break;
            case 8:
            case 9:
            case 16:
            case 17:
                objArr[0] = "debugName";
                break;
            case 10:
                objArr[0] = "typeConstructor";
                break;
            case 12:
            case 14:
                objArr[0] = "arguments";
                break;
            case 13:
                objArr[0] = "presentableName";
                break;
            case 18:
                objArr[0] = "errorClass";
                break;
            case 20:
                objArr[0] = "typeParameterDescriptor";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils";
                break;
            case 4:
                objArr[1] = "createErrorProperty";
                break;
            case 6:
                objArr[1] = "createErrorFunction";
                break;
            case 19:
                objArr[1] = "getErrorModule";
                break;
        }
        switch (i) {
            case 0:
            default:
                objArr[2] = "containsErrorTypeInParameters";
                break;
            case 1:
                objArr[2] = "createErrorClass";
                break;
            case 2:
            case 3:
                objArr[2] = "createErrorScope";
                break;
            case 4:
            case 6:
            case 19:
                break;
            case 5:
                objArr[2] = "createErrorFunction";
                break;
            case 7:
                objArr[2] = "createErrorType";
                break;
            case 8:
                objArr[2] = "createErrorTypeWithCustomDebugName";
                break;
            case 9:
            case 10:
                objArr[2] = "createErrorTypeWithCustomConstructor";
                break;
            case 11:
            case 12:
                objArr[2] = "createErrorTypeWithArguments";
                break;
            case 13:
            case 14:
                objArr[2] = "createUnresolvedType";
                break;
            case 15:
                objArr[2] = "createErrorTypeConstructor";
                break;
            case 16:
            case 17:
            case 18:
                objArr[2] = "createErrorTypeConstructorWithCustomDebugName";
                break;
            case 20:
                objArr[2] = "createUninferredParameterType";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 20:
            default:
                throw new IllegalArgumentException(str2);
            case 4:
            case 6:
            case 19:
                throw new IllegalStateException(str2);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ErrorScope.class */
    public static class ErrorScope implements MemberScope {
        private final String debugMessage;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 14:
                case 15:
                case 16:
                case 17:
                case 19:
                case 20:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 7:
                case 10:
                case 11:
                case 12:
                case 13:
                case 18:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 14:
                case 15:
                case 16:
                case 17:
                case 19:
                case 20:
                default:
                    i2 = 3;
                    break;
                case 7:
                case 10:
                case 11:
                case 12:
                case 13:
                case 18:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "debugMessage";
                    break;
                case 1:
                case 3:
                case 5:
                case 8:
                case 14:
                case 19:
                    objArr[0] = "name";
                    break;
                case 2:
                case 4:
                case 6:
                case 9:
                case 15:
                    objArr[0] = "location";
                    break;
                case 7:
                case 10:
                case 11:
                case 12:
                case 13:
                case 18:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ErrorScope";
                    break;
                case 16:
                    objArr[0] = "kindFilter";
                    break;
                case 17:
                    objArr[0] = "nameFilter";
                    break;
                case 20:
                    objArr[0] = "p";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 14:
                case 15:
                case 16:
                case 17:
                case 19:
                case 20:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ErrorScope";
                    break;
                case 7:
                    objArr[1] = "getContributedVariables";
                    break;
                case 10:
                    objArr[1] = "getContributedFunctions";
                    break;
                case 11:
                    objArr[1] = "getFunctionNames";
                    break;
                case 12:
                    objArr[1] = "getVariableNames";
                    break;
                case 13:
                    objArr[1] = "getClassifierNames";
                    break;
                case 18:
                    objArr[1] = "getContributedDescriptors";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 1:
                case 2:
                    objArr[2] = "getContributedClassifier";
                    break;
                case 3:
                case 4:
                    objArr[2] = "getContributedClassifierIncludeDeprecated";
                    break;
                case 5:
                case 6:
                    objArr[2] = "getContributedVariables";
                    break;
                case 7:
                case 10:
                case 11:
                case 12:
                case 13:
                case 18:
                    break;
                case 8:
                case 9:
                    objArr[2] = "getContributedFunctions";
                    break;
                case 14:
                case 15:
                    objArr[2] = "recordLookup";
                    break;
                case 16:
                case 17:
                    objArr[2] = "getContributedDescriptors";
                    break;
                case 19:
                    objArr[2] = "definitelyDoesNotContainName";
                    break;
                case 20:
                    objArr[2] = "printScopeStructure";
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 8:
                case 9:
                case 14:
                case 15:
                case 16:
                case 17:
                case 19:
                case 20:
                default:
                    throw new IllegalArgumentException(str2);
                case 7:
                case 10:
                case 11:
                case 12:
                case 13:
                case 18:
                    throw new IllegalStateException(str2);
            }
        }

        private ErrorScope(@NotNull String debugMessage) {
            if (debugMessage == null) {
                $$$reportNull$$$0(0);
            }
            this.debugMessage = debugMessage;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @Nullable
        /* renamed from: getContributedClassifier */
        public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(1);
            }
            if (location == null) {
                $$$reportNull$$$0(2);
            }
            return ErrorUtils.createErrorClass(name.asString());
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Set<? extends PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(5);
            }
            if (location == null) {
                $$$reportNull$$$0(6);
            }
            Set<? extends PropertyDescriptor> set = ErrorUtils.ERROR_PROPERTY_GROUP;
            if (set == null) {
                $$$reportNull$$$0(7);
            }
            return set;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        public Set<? extends SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(8);
            }
            if (location == null) {
                $$$reportNull$$$0(9);
            }
            Set<? extends SimpleFunctionDescriptor> setSingleton = Collections.singleton(ErrorUtils.createErrorFunction(this));
            if (setSingleton == null) {
                $$$reportNull$$$0(10);
            }
            return setSingleton;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Set<Name> getFunctionNames() {
            Set<Name> setEmptySet = Collections.emptySet();
            if (setEmptySet == null) {
                $$$reportNull$$$0(11);
            }
            return setEmptySet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Set<Name> getVariableNames() {
            Set<Name> setEmptySet = Collections.emptySet();
            if (setEmptySet == null) {
                $$$reportNull$$$0(12);
            }
            return setEmptySet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Set<Name> getClassifierNames() {
            Set<Name> setEmptySet = Collections.emptySet();
            if (setEmptySet == null) {
                $$$reportNull$$$0(13);
            }
            return setEmptySet;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(14);
            }
            if (location == null) {
                $$$reportNull$$$0(15);
            }
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
            if (kindFilter == null) {
                $$$reportNull$$$0(16);
            }
            if (nameFilter == null) {
                $$$reportNull$$$0(17);
            }
            List listEmptyList = Collections.emptyList();
            if (listEmptyList == null) {
                $$$reportNull$$$0(18);
            }
            return listEmptyList;
        }

        public String toString() {
            return "ErrorScope{" + this.debugMessage + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ThrowingScope.class */
    private static class ThrowingScope implements MemberScope {
        private final String debugMessage;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "message";
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 11:
                case 13:
                    objArr[0] = "name";
                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                case 12:
                    objArr[0] = "location";
                    break;
                case 9:
                    objArr[0] = "kindFilter";
                    break;
                case 10:
                    objArr[0] = "nameFilter";
                    break;
                case 14:
                    objArr[0] = "p";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ThrowingScope";
            switch (i) {
                case 0:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 1:
                case 2:
                    objArr[2] = "getContributedClassifier";
                    break;
                case 3:
                case 4:
                    objArr[2] = "getContributedClassifierIncludeDeprecated";
                    break;
                case 5:
                case 6:
                    objArr[2] = "getContributedVariables";
                    break;
                case 7:
                case 8:
                    objArr[2] = "getContributedFunctions";
                    break;
                case 9:
                case 10:
                    objArr[2] = "getContributedDescriptors";
                    break;
                case 11:
                case 12:
                    objArr[2] = "recordLookup";
                    break;
                case 13:
                    objArr[2] = "definitelyDoesNotContainName";
                    break;
                case 14:
                    objArr[2] = "printScopeStructure";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private ThrowingScope(@NotNull String message) {
            if (message == null) {
                $$$reportNull$$$0(0);
            }
            this.debugMessage = message;
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @Nullable
        /* renamed from: getContributedClassifier */
        public ClassifierDescriptor mo3858getContributedClassifier(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(1);
            }
            if (location == null) {
                $$$reportNull$$$0(2);
            }
            throw new IllegalStateException(this.debugMessage + ", required name: " + name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Collection<? extends PropertyDescriptor> getContributedVariables(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(5);
            }
            if (location == null) {
                $$$reportNull$$$0(6);
            }
            throw new IllegalStateException(this.debugMessage + ", required name: " + name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope, kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        public Collection<? extends SimpleFunctionDescriptor> getContributedFunctions(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(7);
            }
            if (location == null) {
                $$$reportNull$$$0(8);
            }
            throw new IllegalStateException(this.debugMessage + ", required name: " + name);
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        @NotNull
        public Collection<DeclarationDescriptor> getContributedDescriptors(@NotNull DescriptorKindFilter kindFilter, @NotNull Function1<? super Name, Boolean> nameFilter) {
            if (kindFilter == null) {
                $$$reportNull$$$0(9);
            }
            if (nameFilter == null) {
                $$$reportNull$$$0(10);
            }
            throw new IllegalStateException(this.debugMessage);
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Set<Name> getFunctionNames() {
            throw new IllegalStateException();
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        @NotNull
        public Set<Name> getVariableNames() {
            throw new IllegalStateException();
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
        public Set<Name> getClassifierNames() {
            throw new IllegalStateException();
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
        public void recordLookup(@NotNull Name name, @NotNull LookupLocation location) {
            if (name == null) {
                $$$reportNull$$$0(11);
            }
            if (location == null) {
                $$$reportNull$$$0(12);
            }
            throw new IllegalStateException();
        }

        public String toString() {
            return "ThrowingScope{" + this.debugMessage + '}';
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ErrorClassDescriptor.class */
    private static class ErrorClassDescriptor extends ClassDescriptorImpl {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 1:
                case 3:
                case 4:
                case 6:
                case 7:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 2:
                case 5:
                case 8:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 3:
                case 4:
                case 6:
                case 7:
                default:
                    i2 = 3;
                    break;
                case 2:
                case 5:
                case 8:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "name";
                    break;
                case 1:
                    objArr[0] = "substitutor";
                    break;
                case 2:
                case 5:
                case 8:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ErrorClassDescriptor";
                    break;
                case 3:
                    objArr[0] = "typeArguments";
                    break;
                case 4:
                case 7:
                    objArr[0] = "kotlinTypeRefiner";
                    break;
                case 6:
                    objArr[0] = "typeSubstitution";
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 3:
                case 4:
                case 6:
                case 7:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$ErrorClassDescriptor";
                    break;
                case 2:
                    objArr[1] = "substitute";
                    break;
                case 5:
                case 8:
                    objArr[1] = "getMemberScope";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 1:
                    objArr[2] = "substitute";
                    break;
                case 2:
                case 5:
                case 8:
                    break;
                case 3:
                case 4:
                case 6:
                case 7:
                    objArr[2] = "getMemberScope";
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 1:
                case 3:
                case 4:
                case 6:
                case 7:
                default:
                    throw new IllegalArgumentException(str2);
                case 2:
                case 5:
                case 8:
                    throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ErrorClassDescriptor(@NotNull Name name) {
            super(ErrorUtils.getErrorModule(), name, Modality.OPEN, ClassKind.CLASS, Collections.emptyList(), SourceElement.NO_SOURCE, false, LockBasedStorageManager.NO_LOCKS);
            if (name == null) {
                $$$reportNull$$$0(0);
            }
            ClassConstructorDescriptorImpl errorConstructor = ClassConstructorDescriptorImpl.create(this, Annotations.Companion.getEMPTY(), true, SourceElement.NO_SOURCE);
            errorConstructor.initialize(Collections.emptyList(), DescriptorVisibilities.INTERNAL);
            MemberScope memberScope = ErrorUtils.createErrorScope(getName().asString());
            errorConstructor.setReturnType(new ErrorType(ErrorUtils.createErrorTypeConstructorWithCustomDebugName("<ERROR>", this), memberScope));
            initialize(memberScope, Collections.singleton(errorConstructor), errorConstructor);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
        @NotNull
        public ClassDescriptor substitute(@NotNull TypeSubstitutor substitutor) {
            if (substitutor == null) {
                $$$reportNull$$$0(1);
            }
            if (this == null) {
                $$$reportNull$$$0(2);
            }
            return this;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl
        public String toString() {
            return getName().asString();
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
        @NotNull
        public MemberScope getMemberScope(@NotNull TypeSubstitution typeSubstitution, @NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            if (typeSubstitution == null) {
                $$$reportNull$$$0(6);
            }
            if (kotlinTypeRefiner == null) {
                $$$reportNull$$$0(7);
            }
            MemberScope memberScopeCreateErrorScope = ErrorUtils.createErrorScope("Error scope for class " + getName() + " with arguments: " + typeSubstitution);
            if (memberScopeCreateErrorScope == null) {
                $$$reportNull$$$0(8);
            }
            return memberScopeCreateErrorScope;
        }
    }

    @NotNull
    public static ClassDescriptor createErrorClass(@NotNull String debugMessage) {
        if (debugMessage == null) {
            $$$reportNull$$$0(1);
        }
        return new ErrorClassDescriptor(Name.special("<ERROR CLASS: " + debugMessage + ">"));
    }

    @NotNull
    public static MemberScope createErrorScope(@NotNull String debugMessage) {
        if (debugMessage == null) {
            $$$reportNull$$$0(2);
        }
        return createErrorScope(debugMessage, false);
    }

    @NotNull
    public static MemberScope createErrorScope(@NotNull String debugMessage, boolean throwExceptions) {
        if (debugMessage == null) {
            $$$reportNull$$$0(3);
        }
        if (throwExceptions) {
            return new ThrowingScope(debugMessage);
        }
        return new ErrorScope(debugMessage);
    }

    @NotNull
    private static PropertyDescriptorImpl createErrorProperty() {
        PropertyDescriptorImpl descriptor = PropertyDescriptorImpl.create(ERROR_CLASS, Annotations.Companion.getEMPTY(), Modality.OPEN, DescriptorVisibilities.PUBLIC, true, Name.special("<ERROR PROPERTY>"), CallableMemberDescriptor.Kind.DECLARATION, SourceElement.NO_SOURCE, false, false, false, false, false, false);
        descriptor.setType(ERROR_PROPERTY_TYPE, Collections.emptyList(), null, null);
        if (descriptor == null) {
            $$$reportNull$$$0(4);
        }
        return descriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NotNull
    public static SimpleFunctionDescriptor createErrorFunction(@NotNull ErrorScope ownerScope) {
        if (ownerScope == null) {
            $$$reportNull$$$0(5);
        }
        ErrorSimpleFunctionDescriptorImpl function = new ErrorSimpleFunctionDescriptorImpl(ERROR_CLASS, ownerScope);
        function.initialize((ReceiverParameterDescriptor) null, (ReceiverParameterDescriptor) null, Collections.emptyList(), Collections.emptyList(), (KotlinType) createErrorType("<ERROR FUNCTION RETURN TYPE>"), Modality.OPEN, DescriptorVisibilities.PUBLIC);
        if (function == null) {
            $$$reportNull$$$0(6);
        }
        return function;
    }

    @NotNull
    public static SimpleType createErrorType(@NotNull String debugMessage) {
        if (debugMessage == null) {
            $$$reportNull$$$0(7);
        }
        return createErrorTypeWithArguments(debugMessage, Collections.emptyList());
    }

    @NotNull
    public static SimpleType createErrorTypeWithCustomDebugName(@NotNull String debugName) {
        if (debugName == null) {
            $$$reportNull$$$0(8);
        }
        return createErrorTypeWithCustomConstructor(debugName, createErrorTypeConstructorWithCustomDebugName(debugName));
    }

    @NotNull
    public static SimpleType createErrorTypeWithCustomConstructor(@NotNull String debugName, @NotNull TypeConstructor typeConstructor) {
        if (debugName == null) {
            $$$reportNull$$$0(9);
        }
        if (typeConstructor == null) {
            $$$reportNull$$$0(10);
        }
        return new ErrorType(typeConstructor, createErrorScope(debugName));
    }

    @NotNull
    public static SimpleType createErrorTypeWithArguments(@NotNull String debugMessage, @NotNull List<TypeProjection> arguments) {
        if (debugMessage == null) {
            $$$reportNull$$$0(11);
        }
        if (arguments == null) {
            $$$reportNull$$$0(12);
        }
        return new ErrorType(createErrorTypeConstructor(debugMessage), createErrorScope(debugMessage), arguments, false);
    }

    @NotNull
    public static TypeConstructor createErrorTypeConstructor(@NotNull String debugMessage) {
        if (debugMessage == null) {
            $$$reportNull$$$0(15);
        }
        return createErrorTypeConstructorWithCustomDebugName("[ERROR : " + debugMessage + "]", ERROR_CLASS);
    }

    @NotNull
    public static TypeConstructor createErrorTypeConstructorWithCustomDebugName(@NotNull String debugName) {
        if (debugName == null) {
            $$$reportNull$$$0(16);
        }
        return createErrorTypeConstructorWithCustomDebugName(debugName, ERROR_CLASS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NotNull
    public static TypeConstructor createErrorTypeConstructorWithCustomDebugName(@NotNull final String debugName, @NotNull final ErrorClassDescriptor errorClass) {
        if (debugName == null) {
            $$$reportNull$$$0(17);
        }
        if (errorClass == null) {
            $$$reportNull$$$0(18);
        }
        return new TypeConstructor() { // from class: kotlin.reflect.jvm.internal.impl.types.ErrorUtils.2
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str;
                int i2;
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    default:
                        str = "@NotNull method %s.%s must not return null";
                        break;
                    case 3:
                        str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                        break;
                }
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    default:
                        i2 = 2;
                        break;
                    case 3:
                        i2 = 3;
                        break;
                }
                Object[] objArr = new Object[i2];
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    default:
                        objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$2";
                        break;
                    case 3:
                        objArr[0] = "kotlinTypeRefiner";
                        break;
                }
                switch (i) {
                    case 0:
                    default:
                        objArr[1] = "getParameters";
                        break;
                    case 1:
                        objArr[1] = "getSupertypes";
                        break;
                    case 2:
                        objArr[1] = "getBuiltIns";
                        break;
                    case 3:
                        objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$2";
                        break;
                    case 4:
                        objArr[1] = "refine";
                        break;
                }
                switch (i) {
                    case 3:
                        objArr[2] = "refine";
                        break;
                }
                String str2 = String.format(str, objArr);
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    default:
                        throw new IllegalStateException(str2);
                    case 3:
                        throw new IllegalArgumentException(str2);
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            public List<TypeParameterDescriptor> getParameters() {
                List<TypeParameterDescriptor> listEmptyList = CollectionsKt.emptyList();
                if (listEmptyList == null) {
                    $$$reportNull$$$0(0);
                }
                return listEmptyList;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            /* renamed from: getSupertypes */
            public Collection<KotlinType> mo3835getSupertypes() {
                List listEmptyList = CollectionsKt.emptyList();
                if (listEmptyList == null) {
                    $$$reportNull$$$0(1);
                }
                return listEmptyList;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public boolean isDenotable() {
                return false;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @Nullable
            /* renamed from: getDeclarationDescriptor */
            public ClassifierDescriptor mo3831getDeclarationDescriptor() {
                return errorClass;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            public KotlinBuiltIns getBuiltIns() {
                DefaultBuiltIns defaultBuiltIns = DefaultBuiltIns.getInstance();
                if (defaultBuiltIns == null) {
                    $$$reportNull$$$0(2);
                }
                return defaultBuiltIns;
            }

            public String toString() {
                return debugName;
            }

            @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            @NotNull
            public TypeConstructor refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
                if (kotlinTypeRefiner == null) {
                    $$$reportNull$$$0(3);
                }
                if (this == null) {
                    $$$reportNull$$$0(4);
                }
                return this;
            }
        };
    }

    public static boolean isError(@Nullable DeclarationDescriptor candidate) {
        if (candidate == null) {
            return false;
        }
        return isErrorClass(candidate) || isErrorClass(candidate.getContainingDeclaration()) || candidate == ERROR_MODULE;
    }

    private static boolean isErrorClass(@Nullable DeclarationDescriptor candidate) {
        return candidate instanceof ErrorClassDescriptor;
    }

    @NotNull
    public static ModuleDescriptor getErrorModule() {
        ModuleDescriptor moduleDescriptor = ERROR_MODULE;
        if (moduleDescriptor == null) {
            $$$reportNull$$$0(19);
        }
        return moduleDescriptor;
    }

    public static boolean isUninferredParameter(@Nullable KotlinType type) {
        return type != null && (type.getConstructor() instanceof UninferredParameterTypeConstructor);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/ErrorUtils$UninferredParameterTypeConstructor.class */
    public static class UninferredParameterTypeConstructor implements TypeConstructor {
        private final TypeParameterDescriptor typeParameterDescriptor;
        private final TypeConstructor errorTypeConstructor;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                case 5:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                case 5:
                default:
                    i2 = 3;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "descriptor";
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$UninferredParameterTypeConstructor";
                    break;
                case 5:
                    objArr[0] = "kotlinTypeRefiner";
                    break;
            }
            switch (i) {
                case 0:
                case 5:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/types/ErrorUtils$UninferredParameterTypeConstructor";
                    break;
                case 1:
                    objArr[1] = "getTypeParameterDescriptor";
                    break;
                case 2:
                    objArr[1] = "getParameters";
                    break;
                case 3:
                    objArr[1] = "getSupertypes";
                    break;
                case 4:
                    objArr[1] = "getBuiltIns";
                    break;
                case 6:
                    objArr[1] = "refine";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                    break;
                case 5:
                    objArr[2] = "refine";
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                case 5:
                default:
                    throw new IllegalArgumentException(str2);
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                    throw new IllegalStateException(str2);
            }
        }

        @NotNull
        public TypeParameterDescriptor getTypeParameterDescriptor() {
            TypeParameterDescriptor typeParameterDescriptor = this.typeParameterDescriptor;
            if (typeParameterDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            return typeParameterDescriptor;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public List<TypeParameterDescriptor> getParameters() {
            List<TypeParameterDescriptor> parameters = this.errorTypeConstructor.getParameters();
            if (parameters == null) {
                $$$reportNull$$$0(2);
            }
            return parameters;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        /* renamed from: getSupertypes */
        public Collection<KotlinType> mo3835getSupertypes() {
            Collection<KotlinType> collectionMo3835getSupertypes = this.errorTypeConstructor.mo3835getSupertypes();
            if (collectionMo3835getSupertypes == null) {
                $$$reportNull$$$0(3);
            }
            return collectionMo3835getSupertypes;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return this.errorTypeConstructor.isDenotable();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @Nullable
        /* renamed from: getDeclarationDescriptor */
        public ClassifierDescriptor mo3831getDeclarationDescriptor() {
            return this.errorTypeConstructor.mo3831getDeclarationDescriptor();
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public KotlinBuiltIns getBuiltIns() {
            KotlinBuiltIns builtIns = DescriptorUtilsKt.getBuiltIns(this.typeParameterDescriptor);
            if (builtIns == null) {
                $$$reportNull$$$0(4);
            }
            return builtIns;
        }

        @Override // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        @NotNull
        public TypeConstructor refine(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
            if (kotlinTypeRefiner == null) {
                $$$reportNull$$$0(5);
            }
            if (this == null) {
                $$$reportNull$$$0(6);
            }
            return this;
        }
    }
}
