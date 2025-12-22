package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.CompositePackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.SingleModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.resolve.sam.SamConversionResolverImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;
import org.jetbrains.annotations.NotNull;

/* compiled from: RuntimeModuleData.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData.class */
public final class RuntimeModuleData {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final DeserializationComponents deserialization;

    @NotNull
    private final PackagePartScopeCache packagePartScopeCache;

    public /* synthetic */ RuntimeModuleData(DeserializationComponents deserialization, PackagePartScopeCache packagePartScopeCache, DefaultConstructorMarker $constructor_marker) {
        this(deserialization, packagePartScopeCache);
    }

    private RuntimeModuleData(DeserializationComponents deserialization, PackagePartScopeCache packagePartScopeCache) {
        this.deserialization = deserialization;
        this.packagePartScopeCache = packagePartScopeCache;
    }

    @NotNull
    public final DeserializationComponents getDeserialization() {
        return this.deserialization;
    }

    @NotNull
    public final PackagePartScopeCache getPackagePartScopeCache() {
        return this.packagePartScopeCache;
    }

    @NotNull
    public final ModuleDescriptor getModule() {
        return this.deserialization.getModuleDescriptor();
    }

    /* compiled from: RuntimeModuleData.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final RuntimeModuleData create(@NotNull ClassLoader classLoader) {
            Intrinsics.checkNotNullParameter(classLoader, "classLoader");
            LockBasedStorageManager storageManager = new LockBasedStorageManager("RuntimeModuleData");
            JvmBuiltIns builtIns = new JvmBuiltIns(storageManager, JvmBuiltIns.Kind.FROM_DEPENDENCIES);
            Name nameSpecial = Name.special("<runtime module for " + classLoader + '>');
            Intrinsics.checkNotNullExpressionValue(nameSpecial, "special(\"<runtime module for $classLoader>\")");
            ModuleDescriptorImpl module = new ModuleDescriptorImpl(nameSpecial, storageManager, builtIns, null, null, null, 56, null);
            builtIns.setBuiltInsModule(module);
            builtIns.initialize(module, true);
            ReflectKotlinClassFinder reflectKotlinClassFinder = new ReflectKotlinClassFinder(classLoader);
            DeserializedDescriptorResolver deserializedDescriptorResolver = new DeserializedDescriptorResolver();
            SingleModuleClassResolver singleModuleClassResolver = new SingleModuleClassResolver();
            NotFoundClasses notFoundClasses = new NotFoundClasses(storageManager, module);
            LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider = RuntimeModuleDataKt.makeLazyJavaPackageFragmentFromClassLoaderProvider$default(classLoader, module, storageManager, notFoundClasses, reflectKotlinClassFinder, deserializedDescriptorResolver, singleModuleClassResolver, null, 128, null);
            DeserializationComponentsForJava deserializationComponentsForJava = RuntimeModuleDataKt.makeDeserializationComponentsForJava(module, storageManager, notFoundClasses, lazyJavaPackageFragmentProvider, reflectKotlinClassFinder, deserializedDescriptorResolver);
            deserializedDescriptorResolver.setComponents(deserializationComponentsForJava);
            JavaResolverCache EMPTY = JavaResolverCache.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
            JavaDescriptorResolver javaDescriptorResolver = new JavaDescriptorResolver(lazyJavaPackageFragmentProvider, EMPTY);
            singleModuleClassResolver.setResolver(javaDescriptorResolver);
            ClassLoader stdlibClassLoader = Unit.class.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(stdlibClassLoader, "stdlibClassLoader");
            JvmBuiltInsPackageFragmentProvider builtinsProvider = new JvmBuiltInsPackageFragmentProvider(storageManager, new ReflectKotlinClassFinder(stdlibClassLoader), module, notFoundClasses, builtIns.getCustomizer(), builtIns.getCustomizer(), DeserializationConfiguration.Default.INSTANCE, NewKotlinTypeChecker.Companion.getDefault(), new SamConversionResolverImpl(storageManager, CollectionsKt.emptyList()));
            module.setDependencies(module);
            module.initialize(new CompositePackageFragmentProvider(CollectionsKt.listOf((Object[]) new PackageFragmentProviderOptimized[]{javaDescriptorResolver.getPackageFragmentProvider(), builtinsProvider})));
            return new RuntimeModuleData(deserializationComponentsForJava.getComponents(), new PackagePartScopeCache(deserializedDescriptorResolver, reflectKotlinClassFinder), null);
        }
    }
}
