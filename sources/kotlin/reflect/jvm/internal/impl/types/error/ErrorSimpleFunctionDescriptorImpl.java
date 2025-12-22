package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl.class */
public class ErrorSimpleFunctionDescriptorImpl extends SimpleFunctionDescriptorImpl {
    private final ErrorUtils.ErrorScope ownerScope;

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
            case 8:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 6:
            case 7:
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
            case 8:
            default:
                i2 = 3;
                break;
            case 6:
            case 7:
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
                objArr[0] = "ownerScope";
                break;
            case 2:
                objArr[0] = "newOwner";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = "annotations";
                break;
            case 5:
                objArr[0] = PackageDocumentBase.DCTags.source;
                break;
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl";
                break;
            case 8:
                objArr[0] = "overriddenDescriptors";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl";
                break;
            case 6:
                objArr[1] = "createSubstitutedCopy";
                break;
            case 7:
                objArr[1] = "copy";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[2] = Constants.CONSTRUCTOR_NAME;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 6:
            case 7:
                break;
            case 8:
                objArr[2] = "setOverriddenDescriptors";
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
            case 8:
            default:
                throw new IllegalArgumentException(str2);
            case 6:
            case 7:
                throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorSimpleFunctionDescriptorImpl(@NotNull ClassDescriptor containingDeclaration, @NotNull ErrorUtils.ErrorScope ownerScope) {
        super(containingDeclaration, null, Annotations.Companion.getEMPTY(), Name.special("<ERROR FUNCTION>"), CallableMemberDescriptor.Kind.DECLARATION, SourceElement.NO_SOURCE);
        if (containingDeclaration == null) {
            $$$reportNull$$$0(0);
        }
        if (ownerScope == null) {
            $$$reportNull$$$0(1);
        }
        this.ownerScope = ownerScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    protected FunctionDescriptorImpl createSubstitutedCopy(@NotNull DeclarationDescriptor newOwner, @Nullable FunctionDescriptor original, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name newName, @NotNull Annotations annotations, @NotNull SourceElement source) {
        if (newOwner == null) {
            $$$reportNull$$$0(2);
        }
        if (kind == null) {
            $$$reportNull$$$0(3);
        }
        if (annotations == null) {
            $$$reportNull$$$0(4);
        }
        if (source == null) {
            $$$reportNull$$$0(5);
        }
        if (this == null) {
            $$$reportNull$$$0(6);
        }
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    public SimpleFunctionDescriptor copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides) {
        if (this == null) {
            $$$reportNull$$$0(7);
        }
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    @NotNull
    public FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder() {
        return new FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.types.error.ErrorSimpleFunctionDescriptorImpl.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str;
                int i2;
                switch (i) {
                    case 0:
                    case 2:
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                    case 13:
                    case 15:
                    case 17:
                    case 19:
                    case 29:
                    default:
                        str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 30:
                        str = "@NotNull method %s.%s must not return null";
                        break;
                }
                switch (i) {
                    case 0:
                    case 2:
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                    case 13:
                    case 15:
                    case 17:
                    case 19:
                    case 29:
                    default:
                        i2 = 3;
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 30:
                        i2 = 2;
                        break;
                }
                Object[] objArr = new Object[i2];
                switch (i) {
                    case 0:
                    default:
                        objArr[0] = "owner";
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 30:
                        objArr[0] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl$1";
                        break;
                    case 2:
                        objArr[0] = "modality";
                        break;
                    case 4:
                        objArr[0] = "visibility";
                        break;
                    case 6:
                        objArr[0] = "kind";
                        break;
                    case 9:
                        objArr[0] = "name";
                        break;
                    case 11:
                    case 17:
                        objArr[0] = "parameters";
                        break;
                    case 13:
                        objArr[0] = "substitution";
                        break;
                    case 15:
                        objArr[0] = "userDataKey";
                        break;
                    case 19:
                        objArr[0] = "type";
                        break;
                    case 29:
                        objArr[0] = "additionalAnnotations";
                        break;
                }
                switch (i) {
                    case 0:
                    case 2:
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                    case 13:
                    case 15:
                    case 17:
                    case 19:
                    case 29:
                    default:
                        objArr[1] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl$1";
                        break;
                    case 1:
                        objArr[1] = "setOwner";
                        break;
                    case 3:
                        objArr[1] = "setModality";
                        break;
                    case 5:
                        objArr[1] = "setVisibility";
                        break;
                    case 7:
                        objArr[1] = "setKind";
                        break;
                    case 8:
                        objArr[1] = "setCopyOverrides";
                        break;
                    case 10:
                        objArr[1] = "setName";
                        break;
                    case 12:
                        objArr[1] = "setValueParameters";
                        break;
                    case 14:
                        objArr[1] = "setSubstitution";
                        break;
                    case 16:
                        objArr[1] = "putUserData";
                        break;
                    case 18:
                        objArr[1] = "setTypeParameters";
                        break;
                    case 20:
                        objArr[1] = "setReturnType";
                        break;
                    case 21:
                        objArr[1] = "setExtensionReceiverParameter";
                        break;
                    case 22:
                        objArr[1] = "setDispatchReceiverParameter";
                        break;
                    case 23:
                        objArr[1] = "setOriginal";
                        break;
                    case 24:
                        objArr[1] = "setSignatureChange";
                        break;
                    case 25:
                        objArr[1] = "setPreserveSourceElement";
                        break;
                    case 26:
                        objArr[1] = "setDropOriginalInContainingParts";
                        break;
                    case 27:
                        objArr[1] = "setHiddenToOvercomeSignatureClash";
                        break;
                    case 28:
                        objArr[1] = "setHiddenForResolutionEverywhereBesideSupercalls";
                        break;
                    case 30:
                        objArr[1] = "setAdditionalAnnotations";
                        break;
                }
                switch (i) {
                    case 0:
                    default:
                        objArr[2] = "setOwner";
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 30:
                        break;
                    case 2:
                        objArr[2] = "setModality";
                        break;
                    case 4:
                        objArr[2] = "setVisibility";
                        break;
                    case 6:
                        objArr[2] = "setKind";
                        break;
                    case 9:
                        objArr[2] = "setName";
                        break;
                    case 11:
                        objArr[2] = "setValueParameters";
                        break;
                    case 13:
                        objArr[2] = "setSubstitution";
                        break;
                    case 15:
                        objArr[2] = "putUserData";
                        break;
                    case 17:
                        objArr[2] = "setTypeParameters";
                        break;
                    case 19:
                        objArr[2] = "setReturnType";
                        break;
                    case 29:
                        objArr[2] = "setAdditionalAnnotations";
                        break;
                }
                String str2 = String.format(str, objArr);
                switch (i) {
                    case 0:
                    case 2:
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                    case 13:
                    case 15:
                    case 17:
                    case 19:
                    case 29:
                    default:
                        throw new IllegalArgumentException(str2);
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 30:
                        throw new IllegalStateException(str2);
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setOwner(@NotNull DeclarationDescriptor owner) {
                if (owner == null) {
                    $$$reportNull$$$0(0);
                }
                if (this == null) {
                    $$$reportNull$$$0(1);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setModality(@NotNull Modality modality) {
                if (modality == null) {
                    $$$reportNull$$$0(2);
                }
                if (this == null) {
                    $$$reportNull$$$0(3);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setVisibility(@NotNull DescriptorVisibility visibility) {
                if (visibility == null) {
                    $$$reportNull$$$0(4);
                }
                if (this == null) {
                    $$$reportNull$$$0(5);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setKind(@NotNull CallableMemberDescriptor.Kind kind) {
                if (kind == null) {
                    $$$reportNull$$$0(6);
                }
                if (this == null) {
                    $$$reportNull$$$0(7);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setCopyOverrides(boolean copyOverrides) {
                if (this == null) {
                    $$$reportNull$$$0(8);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setName(@NotNull Name name) {
                if (name == null) {
                    $$$reportNull$$$0(9);
                }
                if (this == null) {
                    $$$reportNull$$$0(10);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setValueParameters(@NotNull List<ValueParameterDescriptor> parameters) {
                if (parameters == null) {
                    $$$reportNull$$$0(11);
                }
                if (this == null) {
                    $$$reportNull$$$0(12);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setSubstitution(@NotNull TypeSubstitution substitution) {
                if (substitution == null) {
                    $$$reportNull$$$0(13);
                }
                if (this == null) {
                    $$$reportNull$$$0(14);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setTypeParameters(@NotNull List<TypeParameterDescriptor> parameters) {
                if (parameters == null) {
                    $$$reportNull$$$0(17);
                }
                if (this == null) {
                    $$$reportNull$$$0(18);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setReturnType(@NotNull KotlinType type) {
                if (type == null) {
                    $$$reportNull$$$0(19);
                }
                if (this == null) {
                    $$$reportNull$$$0(20);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setExtensionReceiverParameter(@Nullable ReceiverParameterDescriptor extensionReceiverParameter) {
                if (this == null) {
                    $$$reportNull$$$0(21);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setDispatchReceiverParameter(@Nullable ReceiverParameterDescriptor dispatchReceiverParameter) {
                if (this == null) {
                    $$$reportNull$$$0(22);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setOriginal(@Nullable CallableMemberDescriptor original) {
                if (this == null) {
                    $$$reportNull$$$0(23);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setSignatureChange() {
                if (this == null) {
                    $$$reportNull$$$0(24);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setPreserveSourceElement() {
                if (this == null) {
                    $$$reportNull$$$0(25);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setDropOriginalInContainingParts() {
                if (this == null) {
                    $$$reportNull$$$0(26);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setHiddenToOvercomeSignatureClash() {
                if (this == null) {
                    $$$reportNull$$$0(27);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setHiddenForResolutionEverywhereBesideSupercalls() {
                if (this == null) {
                    $$$reportNull$$$0(28);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setAdditionalAnnotations(@NotNull Annotations additionalAnnotations) {
                if (additionalAnnotations == null) {
                    $$$reportNull$$$0(29);
                }
                if (this == null) {
                    $$$reportNull$$$0(30);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @Nullable
            public SimpleFunctionDescriptor build() {
                return ErrorSimpleFunctionDescriptorImpl.this;
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public <V> V getUserData(CallableDescriptor.UserDataKey<V> key) {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> overriddenDescriptors) {
        if (overriddenDescriptors == null) {
            $$$reportNull$$$0(8);
        }
    }
}
