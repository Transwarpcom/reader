package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DeserializedDescriptorResolver.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/DeserializedDescriptorResolver.class */
public final class DeserializedDescriptorResolver {
    public DeserializationComponents components;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Set<KotlinClassHeader.Kind> KOTLIN_CLASS = SetsKt.setOf(KotlinClassHeader.Kind.CLASS);

    @NotNull
    private static final Set<KotlinClassHeader.Kind> KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART = SetsKt.setOf((Object[]) new KotlinClassHeader.Kind[]{KotlinClassHeader.Kind.FILE_FACADE, KotlinClassHeader.Kind.MULTIFILE_CLASS_PART});

    @NotNull
    private static final JvmMetadataVersion KOTLIN_1_1_EAP_METADATA_VERSION = new JvmMetadataVersion(1, 1, 2);

    @NotNull
    private static final JvmMetadataVersion KOTLIN_1_3_M1_METADATA_VERSION = new JvmMetadataVersion(1, 1, 11);

    @NotNull
    private static final JvmMetadataVersion KOTLIN_1_3_RC_METADATA_VERSION = new JvmMetadataVersion(1, 1, 13);

    @NotNull
    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents != null) {
            return deserializationComponents;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        throw null;
    }

    public final void setComponents(@NotNull DeserializationComponents deserializationComponents) {
        Intrinsics.checkNotNullParameter(deserializationComponents, "<set-?>");
        this.components = deserializationComponents;
    }

    public final void setComponents(@NotNull DeserializationComponentsForJava components) {
        Intrinsics.checkNotNullParameter(components, "components");
        setComponents(components.getComponents());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSkipMetadataVersionCheck() {
        return getComponents().getConfiguration().getSkipMetadataVersionCheck();
    }

    @Nullable
    public final ClassDescriptor resolveClass(@NotNull KotlinJvmBinaryClass kotlinClass) {
        Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
        ClassData classData = readClassData$descriptors_jvm(kotlinClass);
        if (classData == null) {
            return null;
        }
        return getComponents().getClassDeserializer().deserializeClass(kotlinClass.getClassId(), classData);
    }

    @Nullable
    public final ClassData readClassData$descriptors_jvm(@NotNull KotlinJvmBinaryClass kotlinClass) {
        String[] strings;
        Pair<JvmNameResolver, ProtoBuf.Class> classDataFrom;
        Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
        String[] data = readData(kotlinClass, KOTLIN_CLASS);
        if (data == null || (strings = kotlinClass.getClassHeader().getStrings()) == null) {
            return null;
        }
        try {
            try {
                JvmProtoBufUtil jvmProtoBufUtil = JvmProtoBufUtil.INSTANCE;
                classDataFrom = JvmProtoBufUtil.readClassDataFrom(data, strings);
            } catch (InvalidProtocolBufferException e$iv) {
                throw new IllegalStateException(Intrinsics.stringPlus("Could not read data from ", kotlinClass.getLocation()), e$iv);
            }
        } catch (Throwable e$iv2) {
            if (getSkipMetadataVersionCheck() || kotlinClass.getClassHeader().getMetadataVersion().isCompatible()) {
                throw e$iv2;
            }
            classDataFrom = null;
        }
        Pair<JvmNameResolver, ProtoBuf.Class> pair = classDataFrom;
        if (pair != null) {
            JvmNameResolver nameResolver = pair.component1();
            ProtoBuf.Class classProto = pair.component2();
            KotlinJvmBinarySourceElement source = new KotlinJvmBinarySourceElement(kotlinClass, getIncompatibility(kotlinClass), isPreReleaseInvisible(kotlinClass), getAbiStability(kotlinClass));
            return new ClassData(nameResolver, classProto, kotlinClass.getClassHeader().getMetadataVersion(), source);
        }
        return null;
    }

    @Nullable
    public final MemberScope createKotlinPackagePartScope(@NotNull PackageFragmentDescriptor descriptor, @NotNull KotlinJvmBinaryClass kotlinClass) {
        String[] strings;
        Pair<JvmNameResolver, ProtoBuf.Package> packageDataFrom;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(kotlinClass, "kotlinClass");
        String[] data = readData(kotlinClass, KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART);
        if (data == null || (strings = kotlinClass.getClassHeader().getStrings()) == null) {
            return null;
        }
        try {
            try {
                JvmProtoBufUtil jvmProtoBufUtil = JvmProtoBufUtil.INSTANCE;
                packageDataFrom = JvmProtoBufUtil.readPackageDataFrom(data, strings);
            } catch (InvalidProtocolBufferException e$iv) {
                throw new IllegalStateException(Intrinsics.stringPlus("Could not read data from ", kotlinClass.getLocation()), e$iv);
            }
        } catch (Throwable e$iv2) {
            if (getSkipMetadataVersionCheck() || kotlinClass.getClassHeader().getMetadataVersion().isCompatible()) {
                throw e$iv2;
            }
            packageDataFrom = null;
        }
        Pair<JvmNameResolver, ProtoBuf.Package> pair = packageDataFrom;
        if (pair != null) {
            JvmNameResolver nameResolver = pair.component1();
            ProtoBuf.Package packageProto = pair.component2();
            JvmPackagePartSource source = new JvmPackagePartSource(kotlinClass, packageProto, nameResolver, getIncompatibility(kotlinClass), isPreReleaseInvisible(kotlinClass), getAbiStability(kotlinClass));
            return new DeserializedPackageMemberScope(descriptor, packageProto, nameResolver, kotlinClass.getClassHeader().getMetadataVersion(), source, getComponents(), new Function0<Collection<? extends Name>>() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2
                @Override // kotlin.jvm.functions.Function0
                @NotNull
                public final Collection<? extends Name> invoke() {
                    return CollectionsKt.emptyList();
                }
            });
        }
        return null;
    }

    private final IncompatibleVersionErrorData<JvmMetadataVersion> getIncompatibility(KotlinJvmBinaryClass $this$incompatibility) {
        if (getSkipMetadataVersionCheck() || $this$incompatibility.getClassHeader().getMetadataVersion().isCompatible()) {
            return null;
        }
        return new IncompatibleVersionErrorData<>($this$incompatibility.getClassHeader().getMetadataVersion(), JvmMetadataVersion.INSTANCE, $this$incompatibility.getLocation(), $this$incompatibility.getClassId());
    }

    private final boolean isPreReleaseInvisible(KotlinJvmBinaryClass $this$isPreReleaseInvisible) {
        return (getComponents().getConfiguration().getReportErrorsOnPreReleaseDependencies() && ($this$isPreReleaseInvisible.getClassHeader().isPreRelease() || Intrinsics.areEqual($this$isPreReleaseInvisible.getClassHeader().getMetadataVersion(), KOTLIN_1_1_EAP_METADATA_VERSION))) || isCompiledWith13M1($this$isPreReleaseInvisible);
    }

    private final boolean isCompiledWith13M1(KotlinJvmBinaryClass $this$isCompiledWith13M1) {
        return !getComponents().getConfiguration().getSkipPrereleaseCheck() && $this$isCompiledWith13M1.getClassHeader().isPreRelease() && Intrinsics.areEqual($this$isCompiledWith13M1.getClassHeader().getMetadataVersion(), KOTLIN_1_3_M1_METADATA_VERSION);
    }

    private final DeserializedContainerAbiStability getAbiStability(KotlinJvmBinaryClass $this$abiStability) {
        return getComponents().getConfiguration().getAllowUnstableDependencies() ? DeserializedContainerAbiStability.STABLE : $this$abiStability.getClassHeader().isUnstableFirBinary() ? DeserializedContainerAbiStability.FIR_UNSTABLE : $this$abiStability.getClassHeader().isUnstableJvmIrBinary() ? DeserializedContainerAbiStability.IR_UNSTABLE : DeserializedContainerAbiStability.STABLE;
    }

    private final String[] readData(KotlinJvmBinaryClass kotlinClass, Set<? extends KotlinClassHeader.Kind> set) {
        KotlinClassHeader header = kotlinClass.getClassHeader();
        String[] data = header.getData();
        String[] incompatibleData = data == null ? header.getIncompatibleData() : data;
        if (incompatibleData != null && set.contains(header.getKind())) {
            return incompatibleData;
        }
        return null;
    }

    /* compiled from: DeserializedDescriptorResolver.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/DeserializedDescriptorResolver$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final JvmMetadataVersion getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm() {
            return DeserializedDescriptorResolver.KOTLIN_1_3_RC_METADATA_VERSION;
        }
    }
}
