package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils.class */
public final class DescriptorResolverUtils {
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
            case 19:
            case 20:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
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
            case 19:
            case 20:
            default:
                i2 = 3;
                break;
            case 18:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 6:
            case 12:
            case 19:
            default:
                objArr[0] = "name";
                break;
            case 1:
            case 7:
            case 13:
                objArr[0] = "membersFromSupertypes";
                break;
            case 2:
            case 8:
            case 14:
                objArr[0] = "membersFromCurrent";
                break;
            case 3:
            case 9:
            case 15:
                objArr[0] = "classDescriptor";
                break;
            case 4:
            case 10:
            case 16:
                objArr[0] = "errorReporter";
                break;
            case 5:
            case 11:
            case 17:
                objArr[0] = "overridingUtil";
                break;
            case 18:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
                break;
            case 20:
                objArr[0] = "annotationClass";
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
            case 19:
            case 20:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
                break;
            case 18:
                objArr[1] = "resolveOverrides";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                objArr[2] = "resolveOverridesForNonStaticMembers";
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "resolveOverridesForStaticMembers";
                break;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                objArr[2] = "resolveOverrides";
                break;
            case 18:
                break;
            case 19:
            case 20:
                objArr[2] = "getAnnotationParameterByName";
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
            case 19:
            case 20:
            default:
                throw new IllegalArgumentException(str2);
            case 18:
                throw new IllegalStateException(str2);
        }
    }

    @NotNull
    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForNonStaticMembers(@NotNull Name name, @NotNull Collection<D> membersFromSupertypes, @NotNull Collection<D> membersFromCurrent, @NotNull ClassDescriptor classDescriptor, @NotNull ErrorReporter errorReporter, @NotNull OverridingUtil overridingUtil) {
        if (name == null) {
            $$$reportNull$$$0(0);
        }
        if (membersFromSupertypes == null) {
            $$$reportNull$$$0(1);
        }
        if (membersFromCurrent == null) {
            $$$reportNull$$$0(2);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(3);
        }
        if (errorReporter == null) {
            $$$reportNull$$$0(4);
        }
        if (overridingUtil == null) {
            $$$reportNull$$$0(5);
        }
        return resolveOverrides(name, membersFromSupertypes, membersFromCurrent, classDescriptor, errorReporter, overridingUtil, false);
    }

    @NotNull
    public static <D extends CallableMemberDescriptor> Collection<D> resolveOverridesForStaticMembers(@NotNull Name name, @NotNull Collection<D> membersFromSupertypes, @NotNull Collection<D> membersFromCurrent, @NotNull ClassDescriptor classDescriptor, @NotNull ErrorReporter errorReporter, @NotNull OverridingUtil overridingUtil) {
        if (name == null) {
            $$$reportNull$$$0(6);
        }
        if (membersFromSupertypes == null) {
            $$$reportNull$$$0(7);
        }
        if (membersFromCurrent == null) {
            $$$reportNull$$$0(8);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(9);
        }
        if (errorReporter == null) {
            $$$reportNull$$$0(10);
        }
        if (overridingUtil == null) {
            $$$reportNull$$$0(11);
        }
        return resolveOverrides(name, membersFromSupertypes, membersFromCurrent, classDescriptor, errorReporter, overridingUtil, true);
    }

    @NotNull
    private static <D extends CallableMemberDescriptor> Collection<D> resolveOverrides(@NotNull Name name, @NotNull Collection<D> membersFromSupertypes, @NotNull Collection<D> membersFromCurrent, @NotNull ClassDescriptor classDescriptor, @NotNull final ErrorReporter errorReporter, @NotNull OverridingUtil overridingUtil, final boolean isStaticContext) {
        if (name == null) {
            $$$reportNull$$$0(12);
        }
        if (membersFromSupertypes == null) {
            $$$reportNull$$$0(13);
        }
        if (membersFromCurrent == null) {
            $$$reportNull$$$0(14);
        }
        if (classDescriptor == null) {
            $$$reportNull$$$0(15);
        }
        if (errorReporter == null) {
            $$$reportNull$$$0(16);
        }
        if (overridingUtil == null) {
            $$$reportNull$$$0(17);
        }
        final Set<D> result = new LinkedHashSet<>();
        overridingUtil.generateOverridesInFunctionGroup(name, membersFromSupertypes, membersFromCurrent, classDescriptor, new NonReportingOverrideStrategy() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                switch (i) {
                    case 0:
                    default:
                        objArr[0] = "fakeOverride";
                        break;
                    case 1:
                        objArr[0] = "fromSuper";
                        break;
                    case 2:
                        objArr[0] = "fromCurrent";
                        break;
                    case 3:
                        objArr[0] = "member";
                        break;
                    case 4:
                        objArr[0] = "overridden";
                        break;
                }
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1";
                switch (i) {
                    case 0:
                    default:
                        objArr[2] = "addFakeOverride";
                        break;
                    case 1:
                    case 2:
                        objArr[2] = "conflict";
                        break;
                    case 3:
                    case 4:
                        objArr[2] = "setOverriddenDescriptors";
                        break;
                }
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
            public void addFakeOverride(@NotNull CallableMemberDescriptor fakeOverride) {
                if (fakeOverride == null) {
                    $$$reportNull$$$0(0);
                }
                OverridingUtil.resolveUnknownVisibilityForMember(fakeOverride, new Function1<CallableMemberDescriptor, Unit>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.1
                    private static /* synthetic */ void $$$reportNull$$$0(int i) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1$1", "invoke"));
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public Unit invoke(@NotNull CallableMemberDescriptor descriptor) {
                        if (descriptor == null) {
                            $$$reportNull$$$0(0);
                        }
                        errorReporter.reportCannotInferVisibility(descriptor);
                        return Unit.INSTANCE;
                    }
                });
                result.add(fakeOverride);
            }

            @Override // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
            public void conflict(@NotNull CallableMemberDescriptor fromSuper, @NotNull CallableMemberDescriptor fromCurrent) {
                if (fromSuper == null) {
                    $$$reportNull$$$0(1);
                }
                if (fromCurrent == null) {
                    $$$reportNull$$$0(2);
                }
            }

            @Override // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
            public void setOverriddenDescriptors(@NotNull CallableMemberDescriptor member, @NotNull Collection<? extends CallableMemberDescriptor> overridden) {
                if (member == null) {
                    $$$reportNull$$$0(3);
                }
                if (overridden == null) {
                    $$$reportNull$$$0(4);
                }
                if (isStaticContext && member.getKind() != CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
                    return;
                }
                super.setOverriddenDescriptors(member, overridden);
            }
        });
        if (result == null) {
            $$$reportNull$$$0(18);
        }
        return result;
    }

    @Nullable
    public static ValueParameterDescriptor getAnnotationParameterByName(@NotNull Name name, @NotNull ClassDescriptor annotationClass) {
        if (name == null) {
            $$$reportNull$$$0(19);
        }
        if (annotationClass == null) {
            $$$reportNull$$$0(20);
        }
        Collection<ClassConstructorDescriptor> constructors = annotationClass.getConstructors();
        if (constructors.size() != 1) {
            return null;
        }
        for (ValueParameterDescriptor parameter : constructors.iterator().next().getValueParameters()) {
            if (parameter.getName().equals(name)) {
                return parameter;
            }
        }
        return null;
    }
}
