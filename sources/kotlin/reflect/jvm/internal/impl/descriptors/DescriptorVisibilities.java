package kotlin.reflect.jvm.internal.impl.descriptors;

import io.vertx.core.cli.converters.FromBasedConverter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.SuperCallReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities.class */
public class DescriptorVisibilities {

    @NotNull
    public static final DescriptorVisibility PRIVATE = new DelegatedDescriptorVisibility(Visibilities.Private.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 0:
                default:
                    objArr[0] = "descriptor";
                    break;
                case 1:
                    objArr[0] = "what";
                    break;
                case 2:
                    objArr[0] = FromBasedConverter.FROM;
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$1";
            switch (i) {
                case 0:
                default:
                    objArr[2] = "hasContainingSourceFile";
                    break;
                case 1:
                case 2:
                    objArr[2] = "isVisible";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private boolean hasContainingSourceFile(@NotNull DeclarationDescriptor descriptor) {
            if (descriptor == null) {
                $$$reportNull$$$0(0);
            }
            return DescriptorUtils.getContainingSourceFile(descriptor) != SourceFile.NO_SOURCE_FILE;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
        public boolean isVisible(@Nullable ReceiverValue receiver, @NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
            if (what == null) {
                $$$reportNull$$$0(1);
            }
            if (from == null) {
                $$$reportNull$$$0(2);
            }
            if (DescriptorUtils.isTopLevelDeclaration(what) && hasContainingSourceFile(from)) {
                return DescriptorVisibilities.inSameFile(what, from);
            }
            if (what instanceof ConstructorDescriptor) {
                ClassifierDescriptorWithTypeParameters classDescriptor = ((ConstructorDescriptor) what).getContainingDeclaration();
                if (DescriptorUtils.isSealedClass(classDescriptor) && DescriptorUtils.isTopLevelDeclaration(classDescriptor) && (from instanceof ConstructorDescriptor) && DescriptorUtils.isTopLevelDeclaration(from.getContainingDeclaration()) && DescriptorVisibilities.inSameFile(what, from)) {
                    return true;
                }
            }
            DeclarationDescriptor parent = what;
            while (parent != null) {
                parent = parent.getContainingDeclaration();
                if (((parent instanceof ClassDescriptor) && !DescriptorUtils.isCompanionObject(parent)) || (parent instanceof PackageFragmentDescriptor)) {
                    break;
                }
            }
            if (parent == null) {
                return false;
            }
            DeclarationDescriptor containingDeclaration = from;
            while (true) {
                DeclarationDescriptor fromParent = containingDeclaration;
                if (fromParent != null) {
                    if (parent == fromParent) {
                        return true;
                    }
                    if (fromParent instanceof PackageFragmentDescriptor) {
                        return (parent instanceof PackageFragmentDescriptor) && ((PackageFragmentDescriptor) parent).getFqName().equals(((PackageFragmentDescriptor) fromParent).getFqName()) && DescriptorUtils.areInSameModule(fromParent, parent);
                    }
                    containingDeclaration = fromParent.getContainingDeclaration();
                } else {
                    return false;
                }
            }
        }
    };

    @NotNull
    public static final DescriptorVisibility PRIVATE_TO_THIS = new DelegatedDescriptorVisibility(Visibilities.PrivateToThis.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.2
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
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$2";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
        public boolean isVisible(@Nullable ReceiverValue thisObject, @NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
            DeclarationDescriptor classDescriptor;
            if (what == null) {
                $$$reportNull$$$0(0);
            }
            if (from == null) {
                $$$reportNull$$$0(1);
            }
            if (DescriptorVisibilities.PRIVATE.isVisible(thisObject, what, from)) {
                if (thisObject == DescriptorVisibilities.ALWAYS_SUITABLE_RECEIVER) {
                    return true;
                }
                if (thisObject != DescriptorVisibilities.IRRELEVANT_RECEIVER && (classDescriptor = DescriptorUtils.getParentOfType(what, ClassDescriptor.class)) != null && (thisObject instanceof ThisClassReceiver)) {
                    return ((ThisClassReceiver) thisObject).getClassDescriptor().getOriginal().equals(classDescriptor.getOriginal());
                }
                return false;
            }
            return false;
        }
    };

    @NotNull
    public static final DescriptorVisibility PROTECTED = new DelegatedDescriptorVisibility(Visibilities.Protected.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.3
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
                case 2:
                    objArr[0] = "whatDeclaration";
                    break;
                case 3:
                    objArr[0] = "fromClass";
                    break;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$3";
            switch (i) {
                case 0:
                case 1:
                default:
                    objArr[2] = "isVisible";
                    break;
                case 2:
                case 3:
                    objArr[2] = "doesReceiverFitForProtectedVisibility";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
        public boolean isVisible(@Nullable ReceiverValue receiver, @NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
            ClassDescriptor companionOwner;
            if (what == null) {
                $$$reportNull$$$0(0);
            }
            if (from == null) {
                $$$reportNull$$$0(1);
            }
            ClassDescriptor givenDescriptorContainingClass = (ClassDescriptor) DescriptorUtils.getParentOfType(what, ClassDescriptor.class);
            ClassDescriptor fromClass = (ClassDescriptor) DescriptorUtils.getParentOfType(from, ClassDescriptor.class, false);
            if (fromClass == null) {
                return false;
            }
            if (givenDescriptorContainingClass != null && DescriptorUtils.isCompanionObject(givenDescriptorContainingClass) && (companionOwner = (ClassDescriptor) DescriptorUtils.getParentOfType(givenDescriptorContainingClass, ClassDescriptor.class)) != null && DescriptorUtils.isSubclass(fromClass, companionOwner)) {
                return true;
            }
            DeclarationDescriptorWithVisibility whatDeclaration = DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(what);
            ClassDescriptor classDescriptor = (ClassDescriptor) DescriptorUtils.getParentOfType(whatDeclaration, ClassDescriptor.class);
            if (classDescriptor == null) {
                return false;
            }
            if (DescriptorUtils.isSubclass(fromClass, classDescriptor) && doesReceiverFitForProtectedVisibility(receiver, whatDeclaration, fromClass)) {
                return true;
            }
            return isVisible(receiver, what, fromClass.getContainingDeclaration());
        }

        private boolean doesReceiverFitForProtectedVisibility(@Nullable ReceiverValue receiver, @NotNull DeclarationDescriptorWithVisibility whatDeclaration, @NotNull ClassDescriptor fromClass) {
            if (whatDeclaration == null) {
                $$$reportNull$$$0(2);
            }
            if (fromClass == null) {
                $$$reportNull$$$0(3);
            }
            if (receiver == DescriptorVisibilities.FALSE_IF_PROTECTED) {
                return false;
            }
            if (!(whatDeclaration instanceof CallableMemberDescriptor) || (whatDeclaration instanceof ConstructorDescriptor) || receiver == DescriptorVisibilities.ALWAYS_SUITABLE_RECEIVER) {
                return true;
            }
            if (receiver == DescriptorVisibilities.IRRELEVANT_RECEIVER || receiver == null) {
                return false;
            }
            KotlinType actualReceiverType = receiver instanceof SuperCallReceiverValue ? ((SuperCallReceiverValue) receiver).getThisType() : receiver.getType();
            return DescriptorUtils.isSubtypeOfClass(actualReceiverType, fromClass) || DynamicTypesKt.isDynamic(actualReceiverType);
        }
    };

    @NotNull
    public static final DescriptorVisibility INTERNAL = new DelegatedDescriptorVisibility(Visibilities.Internal.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.4
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
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$4";
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
            ModuleDescriptor whatModule = DescriptorUtils.getContainingModule(what);
            ModuleDescriptor fromModule = DescriptorUtils.getContainingModule(from);
            if (fromModule.shouldSeeInternalsOf(whatModule)) {
                return DescriptorVisibilities.MODULE_VISIBILITY_HELPER.isInFriendModule(what, from);
            }
            return false;
        }
    };

    @NotNull
    public static final DescriptorVisibility PUBLIC = new DelegatedDescriptorVisibility(Visibilities.Public.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.5
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
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$5";
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
                return true;
            }
            return true;
        }
    };

    @NotNull
    public static final DescriptorVisibility LOCAL = new DelegatedDescriptorVisibility(Visibilities.Local.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.6
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
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$6";
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
            throw new IllegalStateException("This method shouldn't be invoked for LOCAL visibility");
        }
    };

    @NotNull
    public static final DescriptorVisibility INHERITED = new DelegatedDescriptorVisibility(Visibilities.Inherited.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.7
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
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$7";
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
            throw new IllegalStateException("Visibility is unknown yet");
        }
    };

    @NotNull
    public static final DescriptorVisibility INVISIBLE_FAKE = new DelegatedDescriptorVisibility(Visibilities.InvisibleFake.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.8
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
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$8";
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
                return false;
            }
            return false;
        }
    };

    @NotNull
    public static final DescriptorVisibility UNKNOWN = new DelegatedDescriptorVisibility(Visibilities.Unknown.INSTANCE) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.9
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
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$9";
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
                return false;
            }
            return false;
        }
    };
    public static final Set<DescriptorVisibility> INVISIBLE_FROM_OTHER_MODULES = Collections.unmodifiableSet(SetsKt.setOf((Object[]) new DescriptorVisibility[]{PRIVATE, PRIVATE_TO_THIS, INTERNAL, LOCAL}));
    private static final Map<DescriptorVisibility, Integer> ORDERED_VISIBILITIES;
    public static final DescriptorVisibility DEFAULT_VISIBILITY;
    private static final ReceiverValue IRRELEVANT_RECEIVER;
    public static final ReceiverValue ALWAYS_SUITABLE_RECEIVER;

    @Deprecated
    public static final ReceiverValue FALSE_IF_PROTECTED;

    @NotNull
    private static final ModuleVisibilityHelper MODULE_VISIBILITY_HELPER;

    @NotNull
    private static final Map<Visibility, DescriptorVisibility> visibilitiesMapping;

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
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            case 16:
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
            default:
                i2 = 3;
                break;
            case 16:
                i2 = 2;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 8:
            default:
                objArr[0] = "what";
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 9:
                objArr[0] = FromBasedConverter.FROM;
                break;
            case 10:
            case 12:
                objArr[0] = "first";
                break;
            case 11:
            case 13:
                objArr[0] = "second";
                break;
            case 14:
            case 15:
                objArr[0] = "visibility";
                break;
            case 16:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities";
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
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities";
                break;
            case 16:
                objArr[1] = "toDescriptorVisibility";
                break;
        }
        switch (i) {
            case 0:
            case 1:
            default:
                objArr[2] = "isVisible";
                break;
            case 2:
            case 3:
                objArr[2] = "isVisibleIgnoringReceiver";
                break;
            case 4:
            case 5:
                objArr[2] = "isVisibleWithAnyReceiver";
                break;
            case 6:
            case 7:
                objArr[2] = "inSameFile";
                break;
            case 8:
            case 9:
                objArr[2] = "findInvisibleMember";
                break;
            case 10:
            case 11:
                objArr[2] = "compareLocal";
                break;
            case 12:
            case 13:
                objArr[2] = "compare";
                break;
            case 14:
                objArr[2] = "isPrivate";
                break;
            case 15:
                objArr[2] = "toDescriptorVisibility";
                break;
            case 16:
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
            default:
                throw new IllegalArgumentException(str2);
            case 16:
                throw new IllegalStateException(str2);
        }
    }

    static {
        Map<DescriptorVisibility, Integer> visibilities = CollectionsKt.newHashMapWithExpectedSize(4);
        visibilities.put(PRIVATE_TO_THIS, 0);
        visibilities.put(PRIVATE, 0);
        visibilities.put(INTERNAL, 1);
        visibilities.put(PROTECTED, 1);
        visibilities.put(PUBLIC, 2);
        ORDERED_VISIBILITIES = Collections.unmodifiableMap(visibilities);
        DEFAULT_VISIBILITY = PUBLIC;
        IRRELEVANT_RECEIVER = new ReceiverValue() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.10
            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            @NotNull
            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        ALWAYS_SUITABLE_RECEIVER = new ReceiverValue() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.11
            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            @NotNull
            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        FALSE_IF_PROTECTED = new ReceiverValue() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.12
            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            @NotNull
            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        Iterator<ModuleVisibilityHelper> iterator = ServiceLoader.load(ModuleVisibilityHelper.class, ModuleVisibilityHelper.class.getClassLoader()).iterator();
        MODULE_VISIBILITY_HELPER = iterator.hasNext() ? iterator.next() : ModuleVisibilityHelper.EMPTY.INSTANCE;
        visibilitiesMapping = new HashMap();
        recordVisibilityMapping(PRIVATE);
        recordVisibilityMapping(PRIVATE_TO_THIS);
        recordVisibilityMapping(PROTECTED);
        recordVisibilityMapping(INTERNAL);
        recordVisibilityMapping(PUBLIC);
        recordVisibilityMapping(LOCAL);
        recordVisibilityMapping(INHERITED);
        recordVisibilityMapping(INVISIBLE_FAKE);
        recordVisibilityMapping(UNKNOWN);
    }

    public static boolean isVisibleIgnoringReceiver(@NotNull DeclarationDescriptorWithVisibility what, @NotNull DeclarationDescriptor from) {
        if (what == null) {
            $$$reportNull$$$0(2);
        }
        if (from == null) {
            $$$reportNull$$$0(3);
        }
        return findInvisibleMember(ALWAYS_SUITABLE_RECEIVER, what, from) == null;
    }

    public static boolean inSameFile(@NotNull DeclarationDescriptor what, @NotNull DeclarationDescriptor from) {
        if (what == null) {
            $$$reportNull$$$0(6);
        }
        if (from == null) {
            $$$reportNull$$$0(7);
        }
        SourceFile fromContainingFile = DescriptorUtils.getContainingSourceFile(from);
        if (fromContainingFile != SourceFile.NO_SOURCE_FILE) {
            return fromContainingFile.equals(DescriptorUtils.getContainingSourceFile(what));
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        if ((r6 instanceof kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0051, code lost:
    
        r0 = findInvisibleMember(r5, ((kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor) r6).getUnderlyingConstructorDescriptor(), r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
    
        if (r0 == null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0068, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0069, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
    
        return null;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility findInvisibleMember(@org.jetbrains.annotations.Nullable kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue r5, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility r6, @org.jetbrains.annotations.NotNull kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r7) {
        /*
            r0 = r6
            if (r0 != 0) goto L9
            r0 = 8
            $$$reportNull$$$0(r0)
        L9:
            r0 = r7
            if (r0 != 0) goto L12
            r0 = 9
            $$$reportNull$$$0(r0)
        L12:
            r0 = r6
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = r0.getOriginal()
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility r0 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility) r0
            r8 = r0
        L1c:
            r0 = r8
            if (r0 == 0) goto L4a
            r0 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r0 = r0.getVisibility()
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r1 = kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.LOCAL
            if (r0 == r1) goto L4a
            r0 = r8
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r0 = r0.getVisibility()
            r1 = r5
            r2 = r8
            r3 = r7
            boolean r0 = r0.isVisible(r1, r2, r3)
            if (r0 != 0) goto L3d
            r0 = r8
            return r0
        L3d:
            r0 = r8
            java.lang.Class<kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility> r1 = kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility.class
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r0 = kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils.getParentOfType(r0, r1)
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility r0 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility) r0
            r8 = r0
            goto L1c
        L4a:
            r0 = r6
            boolean r0 = r0 instanceof kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor
            if (r0 == 0) goto L69
            r0 = r5
            r1 = r6
            kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor) r1
            kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor r1 = r1.getUnderlyingConstructorDescriptor()
            r2 = r7
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility r0 = findInvisibleMember(r0, r1, r2)
            r9 = r0
            r0 = r9
            if (r0 == 0) goto L69
            r0 = r9
            return r0
        L69:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.findInvisibleMember(kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor):kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility");
    }

    @Nullable
    public static Integer compare(@NotNull DescriptorVisibility first, @NotNull DescriptorVisibility second) {
        if (first == null) {
            $$$reportNull$$$0(12);
        }
        if (second == null) {
            $$$reportNull$$$0(13);
        }
        Integer result = first.compareTo(second);
        if (result != null) {
            return result;
        }
        Integer oppositeResult = second.compareTo(first);
        if (oppositeResult != null) {
            return Integer.valueOf(-oppositeResult.intValue());
        }
        return null;
    }

    public static boolean isPrivate(@NotNull DescriptorVisibility visibility) {
        if (visibility == null) {
            $$$reportNull$$$0(14);
        }
        return visibility == PRIVATE || visibility == PRIVATE_TO_THIS;
    }

    private static void recordVisibilityMapping(DescriptorVisibility visibility) {
        visibilitiesMapping.put(visibility.getDelegate(), visibility);
    }

    @NotNull
    public static DescriptorVisibility toDescriptorVisibility(@NotNull Visibility visibility) {
        if (visibility == null) {
            $$$reportNull$$$0(15);
        }
        DescriptorVisibility correspondingVisibility = visibilitiesMapping.get(visibility);
        if (correspondingVisibility == null) {
            throw new IllegalArgumentException("Inapplicable visibility: " + visibility);
        }
        if (correspondingVisibility == null) {
            $$$reportNull$$$0(16);
        }
        return correspondingVisibility;
    }
}
