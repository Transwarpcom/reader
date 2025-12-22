package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInsResourceLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReflectKotlinClassFinder.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectKotlinClassFinder.class */
public final class ReflectKotlinClassFinder implements KotlinClassFinder {

    @NotNull
    private final ClassLoader classLoader;

    @NotNull
    private final BuiltInsResourceLoader builtInsResourceLoader;

    public ReflectKotlinClassFinder(@NotNull ClassLoader classLoader) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        this.classLoader = classLoader;
        this.builtInsResourceLoader = new BuiltInsResourceLoader();
    }

    private final KotlinClassFinder.Result findKotlinClass(String fqName) throws ClassNotFoundException {
        KotlinJvmBinaryClass kotlinJvmBinaryClassCreate;
        KotlinClassFinder.Result.KotlinClass kotlinClass;
        Class it = ReflectJavaClassFinderKt.tryLoadClass(this.classLoader, fqName);
        if (it == null || (kotlinJvmBinaryClassCreate = ReflectKotlinClass.Factory.create(it)) == null) {
            kotlinClass = null;
        } else {
            KotlinJvmBinaryClass p0 = kotlinJvmBinaryClassCreate;
            kotlinClass = new KotlinClassFinder.Result.KotlinClass(p0, null, 2, null);
        }
        return kotlinClass;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder
    @Nullable
    public KotlinClassFinder.Result findKotlinClassOrContent(@NotNull ClassId classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        return findKotlinClass(ReflectKotlinClassFinderKt.toRuntimeFqName(classId));
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder
    @Nullable
    public KotlinClassFinder.Result findKotlinClassOrContent(@NotNull JavaClass javaClass) {
        Intrinsics.checkNotNullParameter(javaClass, "javaClass");
        FqName fqName = javaClass.getFqName();
        String strAsString = fqName == null ? null : fqName.asString();
        if (strAsString == null) {
            return null;
        }
        return findKotlinClass(strAsString);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.KotlinMetadataFinder
    @Nullable
    public InputStream findBuiltInsData(@NotNull FqName packageFqName) {
        Intrinsics.checkNotNullParameter(packageFqName, "packageFqName");
        if (packageFqName.startsWith(StandardNames.BUILT_INS_PACKAGE_NAME)) {
            return this.builtInsResourceLoader.loadResource(BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(packageFqName));
        }
        return null;
    }
}
