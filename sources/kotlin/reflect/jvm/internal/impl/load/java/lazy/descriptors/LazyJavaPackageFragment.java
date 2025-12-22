package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryPackageSourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LazyJavaPackageFragment.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageFragment.class */
public final class LazyJavaPackageFragment extends PackageFragmentDescriptorImpl {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "binaryClasses", "getBinaryClasses$descriptors_jvm()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "partToFacade", "getPartToFacade()Ljava/util/HashMap;"))};

    @NotNull
    private final JavaPackage jPackage;

    @NotNull
    private final LazyJavaResolverContext c;

    @NotNull
    private final NotNullLazyValue binaryClasses$delegate;

    @NotNull
    private final JvmPackageScope scope;

    @NotNull
    private final NotNullLazyValue<List<FqName>> subPackages;

    @NotNull
    private final Annotations annotations;

    @NotNull
    private final NotNullLazyValue partToFacade$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaPackageFragment(@NotNull LazyJavaResolverContext outerContext, @NotNull JavaPackage jPackage) {
        super(outerContext.getModule(), jPackage.getFqName());
        Intrinsics.checkNotNullParameter(outerContext, "outerContext");
        Intrinsics.checkNotNullParameter(jPackage, "jPackage");
        this.jPackage = jPackage;
        this.c = ContextKt.childForClassOrPackage$default(outerContext, this, null, 0, 6, null);
        this.binaryClasses$delegate = this.c.getStorageManager().createLazyValue(new Function0<Map<String, ? extends KotlinJvmBinaryClass>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment$binaryClasses$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Map<String, ? extends KotlinJvmBinaryClass> invoke() {
                PackagePartProvider packagePartProvider = this.this$0.c.getComponents().getPackagePartProvider();
                String strAsString = this.this$0.getFqName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "fqName.asString()");
                Iterable $this$mapNotNull$iv = packagePartProvider.findPackageParts(strAsString);
                LazyJavaPackageFragment lazyJavaPackageFragment = this.this$0;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                    String partName = (String) element$iv$iv$iv;
                    ClassId classId = ClassId.topLevel(JvmClassName.byInternalName(partName).getFqNameForTopLevelClassMaybeWithDollars());
                    Intrinsics.checkNotNullExpressionValue(classId, "topLevel(JvmClassName.byInternalName(partName).fqNameForTopLevelClassMaybeWithDollars)");
                    KotlinJvmBinaryClass it = KotlinClassFinderKt.findKotlinClass(lazyJavaPackageFragment.c.getComponents().getKotlinClassFinder(), classId);
                    Pair pair = it == null ? null : TuplesKt.to(partName, it);
                    if (pair != null) {
                        destination$iv$iv.add(pair);
                    }
                }
                return MapsKt.toMap((List) destination$iv$iv);
            }
        });
        this.scope = new JvmPackageScope(this.c, this.jPackage, this);
        this.subPackages = this.c.getStorageManager().createRecursionTolerantLazyValue(new Function0<List<? extends FqName>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment$subPackages$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final List<? extends FqName> invoke() {
                Iterable $this$map$iv = this.this$0.jPackage.getSubPackages();
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    JavaPackage p0 = (JavaPackage) item$iv$iv;
                    destination$iv$iv.add(p0.getFqName());
                }
                return (List) destination$iv$iv;
            }
        }, CollectionsKt.emptyList());
        this.annotations = this.c.getComponents().getJavaTypeEnhancementState().getDisabledDefaultAnnotations() ? Annotations.Companion.getEMPTY() : LazyJavaAnnotationsKt.resolveAnnotations(this.c, this.jPackage);
        this.partToFacade$delegate = this.c.getStorageManager().createLazyValue(new Function0<HashMap<JvmClassName, JvmClassName>>() { // from class: kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment$partToFacade$2

            /* compiled from: LazyJavaPackageFragment.kt */
            /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/lazy/descriptors/LazyJavaPackageFragment$partToFacade$2$WhenMappings.class */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[KotlinClassHeader.Kind.values().length];
                    iArr[KotlinClassHeader.Kind.MULTIFILE_CLASS_PART.ordinal()] = 1;
                    iArr[KotlinClassHeader.Kind.FILE_FACADE.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final HashMap<JvmClassName, JvmClassName> invoke() {
                HashMap result = new HashMap();
                for (Map.Entry<String, KotlinJvmBinaryClass> entry : this.this$0.getBinaryClasses$descriptors_jvm().entrySet()) {
                    String partInternalName = entry.getKey();
                    KotlinJvmBinaryClass kotlinClass = entry.getValue();
                    JvmClassName partName = JvmClassName.byInternalName(partInternalName);
                    Intrinsics.checkNotNullExpressionValue(partName, "byInternalName(partInternalName)");
                    KotlinClassHeader header = kotlinClass.getClassHeader();
                    switch (WhenMappings.$EnumSwitchMapping$0[header.getKind().ordinal()]) {
                        case 1:
                            HashMap map = result;
                            String multifileClassName = header.getMultifileClassName();
                            if (multifileClassName == null) {
                                break;
                            } else {
                                JvmClassName jvmClassNameByInternalName = JvmClassName.byInternalName(multifileClassName);
                                Intrinsics.checkNotNullExpressionValue(jvmClassNameByInternalName, "byInternalName(header.multifileClassName ?: continue@kotlinClasses)");
                                map.put(partName, jvmClassNameByInternalName);
                                break;
                            }
                        case 2:
                            result.put(partName, partName);
                            break;
                    }
                }
                return result;
            }
        });
    }

    @NotNull
    public final Map<String, KotlinJvmBinaryClass> getBinaryClasses$descriptors_jvm() {
        return (Map) StorageKt.getValue(this.binaryClasses$delegate, this, (KProperty<?>) $$delegatedProperties[0]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    @NotNull
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @NotNull
    public final List<FqName> getSubPackageFqNames$descriptors_jvm() {
        return this.subPackages.invoke();
    }

    @Nullable
    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(@NotNull JavaClass jClass) {
        Intrinsics.checkNotNullParameter(jClass, "jClass");
        return this.scope.getJavaScope$descriptors_jvm().findClassifierByJavaClass$descriptors_jvm(jClass);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
    @NotNull
    public JvmPackageScope getMemberScope() {
        return this.scope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    @NotNull
    public String toString() {
        return Intrinsics.stringPlus("Lazy Java package fragment: ", getFqName());
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    @NotNull
    public SourceElement getSource() {
        return new KotlinJvmBinaryPackageSourceElement(this);
    }
}
