package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsBinaryVersion;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragmentImpl;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;

/* compiled from: BuiltInsPackageFragmentImpl.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/builtins/BuiltInsPackageFragmentImpl.class */
public final class BuiltInsPackageFragmentImpl extends DeserializedPackageFragmentImpl implements BuiltInsPackageFragment {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private final boolean isFallback;

    public /* synthetic */ BuiltInsPackageFragmentImpl(FqName fqName, StorageManager storageManager, ModuleDescriptor module, ProtoBuf.PackageFragment proto, BuiltInsBinaryVersion metadataVersion, boolean isFallback, DefaultConstructorMarker $constructor_marker) {
        this(fqName, storageManager, module, proto, metadataVersion, isFallback);
    }

    private BuiltInsPackageFragmentImpl(FqName fqName, StorageManager storageManager, ModuleDescriptor module, ProtoBuf.PackageFragment proto, BuiltInsBinaryVersion metadataVersion, boolean isFallback) {
        super(fqName, storageManager, module, proto, metadataVersion, null);
        this.isFallback = isFallback;
    }

    /* compiled from: BuiltInsPackageFragmentImpl.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/serialization/deserialization/builtins/BuiltInsPackageFragmentImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final BuiltInsPackageFragmentImpl create(@NotNull FqName fqName, @NotNull StorageManager storageManager, @NotNull ModuleDescriptor module, @NotNull InputStream inputStream, boolean isFallback) {
            Intrinsics.checkNotNullParameter(fqName, "fqName");
            Intrinsics.checkNotNullParameter(storageManager, "storageManager");
            Intrinsics.checkNotNullParameter(module, "module");
            Intrinsics.checkNotNullParameter(inputStream, "inputStream");
            InputStream inputStream2 = inputStream;
            Throwable th = (Throwable) null;
            try {
                InputStream stream = inputStream2;
                BuiltInsBinaryVersion from = BuiltInsBinaryVersion.Companion.readFrom(stream);
                if (from == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("version");
                    throw null;
                }
                if (!from.isCompatible()) {
                    throw new UnsupportedOperationException("Kotlin built-in definition format version is not supported: expected " + BuiltInsBinaryVersion.INSTANCE + ", actual " + from + ". Please update Kotlin");
                }
                ProtoBuf.PackageFragment proto = ProtoBuf.PackageFragment.parseFrom(stream, BuiltInSerializerProtocol.INSTANCE.getExtensionRegistry());
                CloseableKt.closeFinally(inputStream2, th);
                Intrinsics.checkNotNullExpressionValue(proto, "proto");
                return new BuiltInsPackageFragmentImpl(fqName, storageManager, module, proto, from, isFallback, null);
            } catch (Throwable th2) {
                CloseableKt.closeFinally(inputStream2, th);
                throw th2;
            }
        }
    }
}
