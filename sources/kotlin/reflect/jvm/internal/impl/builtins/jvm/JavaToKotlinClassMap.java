package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaToKotlinClassMap.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/JavaToKotlinClassMap.class */
public final class JavaToKotlinClassMap {

    @NotNull
    public static final JavaToKotlinClassMap INSTANCE = new JavaToKotlinClassMap();

    @NotNull
    private static final String NUMBERED_FUNCTION_PREFIX = FunctionClassKind.Function.getPackageFqName().toString() + '.' + FunctionClassKind.Function.getClassNamePrefix();

    @NotNull
    private static final String NUMBERED_K_FUNCTION_PREFIX = FunctionClassKind.KFunction.getPackageFqName().toString() + '.' + FunctionClassKind.KFunction.getClassNamePrefix();

    @NotNull
    private static final String NUMBERED_SUSPEND_FUNCTION_PREFIX = FunctionClassKind.SuspendFunction.getPackageFqName().toString() + '.' + FunctionClassKind.SuspendFunction.getClassNamePrefix();

    @NotNull
    private static final String NUMBERED_K_SUSPEND_FUNCTION_PREFIX = FunctionClassKind.KSuspendFunction.getPackageFqName().toString() + '.' + FunctionClassKind.KSuspendFunction.getClassNamePrefix();

    @NotNull
    private static final ClassId FUNCTION_N_CLASS_ID;

    @NotNull
    private static final FqName FUNCTION_N_FQ_NAME;

    @NotNull
    private static final ClassId K_FUNCTION_CLASS_ID;

    @NotNull
    private static final ClassId K_CLASS_CLASS_ID;

    @NotNull
    private static final ClassId CLASS_CLASS_ID;

    @NotNull
    private static final HashMap<FqNameUnsafe, ClassId> javaToKotlin;

    @NotNull
    private static final HashMap<FqNameUnsafe, ClassId> kotlinToJava;

    @NotNull
    private static final HashMap<FqNameUnsafe, FqName> mutableToReadOnly;

    @NotNull
    private static final HashMap<FqNameUnsafe, FqName> readOnlyToMutable;

    @NotNull
    private static final List<PlatformMutabilityMapping> mutabilityMappings;

    private JavaToKotlinClassMap() {
    }

    static {
        ClassId classId = ClassId.topLevel(new FqName("kotlin.jvm.functions.FunctionN"));
        Intrinsics.checkNotNullExpressionValue(classId, "topLevel(FqName(\"kotlin.jvm.functions.FunctionN\"))");
        FUNCTION_N_CLASS_ID = classId;
        FqName fqNameAsSingleFqName = FUNCTION_N_CLASS_ID.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "FUNCTION_N_CLASS_ID.asSingleFqName()");
        FUNCTION_N_FQ_NAME = fqNameAsSingleFqName;
        ClassId classId2 = ClassId.topLevel(new FqName("kotlin.reflect.KFunction"));
        Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(FqName(\"kotlin.reflect.KFunction\"))");
        K_FUNCTION_CLASS_ID = classId2;
        ClassId classId3 = ClassId.topLevel(new FqName("kotlin.reflect.KClass"));
        Intrinsics.checkNotNullExpressionValue(classId3, "topLevel(FqName(\"kotlin.reflect.KClass\"))");
        K_CLASS_CLASS_ID = classId3;
        CLASS_CLASS_ID = INSTANCE.classId(Class.class);
        javaToKotlin = new HashMap<>();
        kotlinToJava = new HashMap<>();
        mutableToReadOnly = new HashMap<>();
        readOnlyToMutable = new HashMap<>();
        JavaToKotlinClassMap this_$iv = INSTANCE;
        ClassId kotlinReadOnly$iv = ClassId.topLevel(StandardNames.FqNames.iterable);
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv, "topLevel(FqNames.iterable)");
        FqName kotlinMutable$iv = StandardNames.FqNames.mutableIterable;
        FqName packageFqName = kotlinReadOnly$iv.getPackageFqName();
        FqName packageFqName2 = kotlinReadOnly$iv.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName2, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv = new ClassId(packageFqName, FqNamesUtilKt.tail(kotlinMutable$iv, packageFqName2), false);
        JavaToKotlinClassMap this_$iv2 = INSTANCE;
        ClassId kotlinReadOnly$iv2 = ClassId.topLevel(StandardNames.FqNames.iterator);
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv2, "topLevel(FqNames.iterator)");
        FqName kotlinMutable$iv2 = StandardNames.FqNames.mutableIterator;
        FqName packageFqName3 = kotlinReadOnly$iv2.getPackageFqName();
        FqName packageFqName4 = kotlinReadOnly$iv2.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName4, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv2 = new ClassId(packageFqName3, FqNamesUtilKt.tail(kotlinMutable$iv2, packageFqName4), false);
        JavaToKotlinClassMap this_$iv3 = INSTANCE;
        ClassId kotlinReadOnly$iv3 = ClassId.topLevel(StandardNames.FqNames.collection);
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv3, "topLevel(FqNames.collection)");
        FqName kotlinMutable$iv3 = StandardNames.FqNames.mutableCollection;
        FqName packageFqName5 = kotlinReadOnly$iv3.getPackageFqName();
        FqName packageFqName6 = kotlinReadOnly$iv3.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName6, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv3 = new ClassId(packageFqName5, FqNamesUtilKt.tail(kotlinMutable$iv3, packageFqName6), false);
        JavaToKotlinClassMap this_$iv4 = INSTANCE;
        ClassId kotlinReadOnly$iv4 = ClassId.topLevel(StandardNames.FqNames.list);
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv4, "topLevel(FqNames.list)");
        FqName kotlinMutable$iv4 = StandardNames.FqNames.mutableList;
        FqName packageFqName7 = kotlinReadOnly$iv4.getPackageFqName();
        FqName packageFqName8 = kotlinReadOnly$iv4.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName8, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv4 = new ClassId(packageFqName7, FqNamesUtilKt.tail(kotlinMutable$iv4, packageFqName8), false);
        JavaToKotlinClassMap this_$iv5 = INSTANCE;
        ClassId kotlinReadOnly$iv5 = ClassId.topLevel(StandardNames.FqNames.set);
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv5, "topLevel(FqNames.set)");
        FqName kotlinMutable$iv5 = StandardNames.FqNames.mutableSet;
        FqName packageFqName9 = kotlinReadOnly$iv5.getPackageFqName();
        FqName packageFqName10 = kotlinReadOnly$iv5.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName10, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv5 = new ClassId(packageFqName9, FqNamesUtilKt.tail(kotlinMutable$iv5, packageFqName10), false);
        JavaToKotlinClassMap this_$iv6 = INSTANCE;
        ClassId kotlinReadOnly$iv6 = ClassId.topLevel(StandardNames.FqNames.listIterator);
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv6, "topLevel(FqNames.listIterator)");
        FqName kotlinMutable$iv6 = StandardNames.FqNames.mutableListIterator;
        FqName packageFqName11 = kotlinReadOnly$iv6.getPackageFqName();
        FqName packageFqName12 = kotlinReadOnly$iv6.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName12, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv6 = new ClassId(packageFqName11, FqNamesUtilKt.tail(kotlinMutable$iv6, packageFqName12), false);
        JavaToKotlinClassMap this_$iv7 = INSTANCE;
        ClassId kotlinReadOnly$iv7 = ClassId.topLevel(StandardNames.FqNames.map);
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv7, "topLevel(FqNames.map)");
        FqName kotlinMutable$iv7 = StandardNames.FqNames.mutableMap;
        FqName packageFqName13 = kotlinReadOnly$iv7.getPackageFqName();
        FqName packageFqName14 = kotlinReadOnly$iv7.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName14, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv7 = new ClassId(packageFqName13, FqNamesUtilKt.tail(kotlinMutable$iv7, packageFqName14), false);
        JavaToKotlinClassMap this_$iv8 = INSTANCE;
        ClassId kotlinReadOnly$iv8 = ClassId.topLevel(StandardNames.FqNames.map).createNestedClassId(StandardNames.FqNames.mapEntry.shortName());
        Intrinsics.checkNotNullExpressionValue(kotlinReadOnly$iv8, "topLevel(FqNames.map).createNestedClassId(FqNames.mapEntry.shortName())");
        FqName kotlinMutable$iv8 = StandardNames.FqNames.mutableMapEntry;
        FqName packageFqName15 = kotlinReadOnly$iv8.getPackageFqName();
        FqName packageFqName16 = kotlinReadOnly$iv8.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(packageFqName16, "kotlinReadOnly.packageFqName");
        ClassId mutableClassId$iv8 = new ClassId(packageFqName15, FqNamesUtilKt.tail(kotlinMutable$iv8, packageFqName16), false);
        mutabilityMappings = CollectionsKt.listOf((Object[]) new PlatformMutabilityMapping[]{new PlatformMutabilityMapping(this_$iv.classId(Iterable.class), kotlinReadOnly$iv, mutableClassId$iv), new PlatformMutabilityMapping(this_$iv2.classId(Iterator.class), kotlinReadOnly$iv2, mutableClassId$iv2), new PlatformMutabilityMapping(this_$iv3.classId(Collection.class), kotlinReadOnly$iv3, mutableClassId$iv3), new PlatformMutabilityMapping(this_$iv4.classId(List.class), kotlinReadOnly$iv4, mutableClassId$iv4), new PlatformMutabilityMapping(this_$iv5.classId(Set.class), kotlinReadOnly$iv5, mutableClassId$iv5), new PlatformMutabilityMapping(this_$iv6.classId(ListIterator.class), kotlinReadOnly$iv6, mutableClassId$iv6), new PlatformMutabilityMapping(this_$iv7.classId(Map.class), kotlinReadOnly$iv7, mutableClassId$iv7), new PlatformMutabilityMapping(this_$iv8.classId(Map.Entry.class), kotlinReadOnly$iv8, mutableClassId$iv8)});
        INSTANCE.addTopLevel(Object.class, StandardNames.FqNames.any);
        INSTANCE.addTopLevel(String.class, StandardNames.FqNames.string);
        INSTANCE.addTopLevel(CharSequence.class, StandardNames.FqNames.charSequence);
        INSTANCE.addTopLevel(Throwable.class, StandardNames.FqNames.throwable);
        INSTANCE.addTopLevel(Cloneable.class, StandardNames.FqNames.cloneable);
        INSTANCE.addTopLevel(Number.class, StandardNames.FqNames.number);
        INSTANCE.addTopLevel(Comparable.class, StandardNames.FqNames.comparable);
        INSTANCE.addTopLevel(Enum.class, StandardNames.FqNames._enum);
        INSTANCE.addTopLevel(Annotation.class, StandardNames.FqNames.annotation);
        JavaToKotlinClassMap javaToKotlinClassMap = INSTANCE;
        for (PlatformMutabilityMapping platformCollection : mutabilityMappings) {
            INSTANCE.addMapping(platformCollection);
        }
        JvmPrimitiveType[] jvmPrimitiveTypeArrValues = JvmPrimitiveType.values();
        int i = 0;
        int length = jvmPrimitiveTypeArrValues.length;
        while (i < length) {
            JvmPrimitiveType jvmType = jvmPrimitiveTypeArrValues[i];
            i++;
            JavaToKotlinClassMap javaToKotlinClassMap2 = INSTANCE;
            ClassId classId4 = ClassId.topLevel(jvmType.getWrapperFqName());
            Intrinsics.checkNotNullExpressionValue(classId4, "topLevel(jvmType.wrapperFqName)");
            StandardNames standardNames = StandardNames.INSTANCE;
            PrimitiveType primitiveType = jvmType.getPrimitiveType();
            Intrinsics.checkNotNullExpressionValue(primitiveType, "jvmType.primitiveType");
            ClassId classId5 = ClassId.topLevel(StandardNames.getPrimitiveFqName(primitiveType));
            Intrinsics.checkNotNullExpressionValue(classId5, "topLevel(StandardNames.getPrimitiveFqName(jvmType.primitiveType))");
            javaToKotlinClassMap2.add(classId4, classId5);
        }
        for (ClassId classId6 : CompanionObjectMapping.INSTANCE.allClassesWithIntrinsicCompanions()) {
            JavaToKotlinClassMap javaToKotlinClassMap3 = INSTANCE;
            ClassId classId7 = ClassId.topLevel(new FqName("kotlin.jvm.internal." + classId6.getShortClassName().asString() + "CompanionObject"));
            Intrinsics.checkNotNullExpressionValue(classId7, "topLevel(FqName(\"kotlin.jvm.internal.\" + classId.shortClassName.asString() + \"CompanionObject\"))");
            ClassId classIdCreateNestedClassId = classId6.createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT);
            Intrinsics.checkNotNullExpressionValue(classIdCreateNestedClassId, "classId.createNestedClassId(SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT)");
            javaToKotlinClassMap3.add(classId7, classIdCreateNestedClassId);
        }
        int i2 = 0;
        do {
            int i3 = i2;
            i2++;
            JavaToKotlinClassMap javaToKotlinClassMap4 = INSTANCE;
            ClassId classId8 = ClassId.topLevel(new FqName(Intrinsics.stringPlus("kotlin.jvm.functions.Function", Integer.valueOf(i3))));
            Intrinsics.checkNotNullExpressionValue(classId8, "topLevel(FqName(\"kotlin.jvm.functions.Function$i\"))");
            StandardNames standardNames2 = StandardNames.INSTANCE;
            javaToKotlinClassMap4.add(classId8, StandardNames.getFunctionClassId(i3));
            INSTANCE.addKotlinToJava(new FqName(Intrinsics.stringPlus(NUMBERED_K_FUNCTION_PREFIX, Integer.valueOf(i3))), K_FUNCTION_CLASS_ID);
        } while (i2 < 23);
        int i4 = 0;
        do {
            int i5 = i4;
            i4++;
            FunctionClassKind kSuspendFunction = FunctionClassKind.KSuspendFunction;
            String kSuspendFun = kSuspendFunction.getPackageFqName().toString() + '.' + kSuspendFunction.getClassNamePrefix();
            INSTANCE.addKotlinToJava(new FqName(Intrinsics.stringPlus(kSuspendFun, Integer.valueOf(i5))), K_FUNCTION_CLASS_ID);
        } while (i4 < 22);
        JavaToKotlinClassMap javaToKotlinClassMap5 = INSTANCE;
        FqName safe = StandardNames.FqNames.nothing.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe, "nothing.toSafe()");
        javaToKotlinClassMap5.addKotlinToJava(safe, INSTANCE.classId(Void.class));
    }

    @NotNull
    public final FqName getFUNCTION_N_FQ_NAME() {
        return FUNCTION_N_FQ_NAME;
    }

    /* compiled from: JavaToKotlinClassMap.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/JavaToKotlinClassMap$PlatformMutabilityMapping.class */
    public static final class PlatformMutabilityMapping {

        @NotNull
        private final ClassId javaClass;

        @NotNull
        private final ClassId kotlinReadOnly;

        @NotNull
        private final ClassId kotlinMutable;

        @NotNull
        public final ClassId component1() {
            return this.javaClass;
        }

        @NotNull
        public final ClassId component2() {
            return this.kotlinReadOnly;
        }

        @NotNull
        public final ClassId component3() {
            return this.kotlinMutable;
        }

        @NotNull
        public String toString() {
            return "PlatformMutabilityMapping(javaClass=" + this.javaClass + ", kotlinReadOnly=" + this.kotlinReadOnly + ", kotlinMutable=" + this.kotlinMutable + ')';
        }

        public int hashCode() {
            int result = this.javaClass.hashCode();
            return (((result * 31) + this.kotlinReadOnly.hashCode()) * 31) + this.kotlinMutable.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PlatformMutabilityMapping)) {
                return false;
            }
            PlatformMutabilityMapping platformMutabilityMapping = (PlatformMutabilityMapping) other;
            return Intrinsics.areEqual(this.javaClass, platformMutabilityMapping.javaClass) && Intrinsics.areEqual(this.kotlinReadOnly, platformMutabilityMapping.kotlinReadOnly) && Intrinsics.areEqual(this.kotlinMutable, platformMutabilityMapping.kotlinMutable);
        }

        public PlatformMutabilityMapping(@NotNull ClassId javaClass, @NotNull ClassId kotlinReadOnly, @NotNull ClassId kotlinMutable) {
            Intrinsics.checkNotNullParameter(javaClass, "javaClass");
            Intrinsics.checkNotNullParameter(kotlinReadOnly, "kotlinReadOnly");
            Intrinsics.checkNotNullParameter(kotlinMutable, "kotlinMutable");
            this.javaClass = javaClass;
            this.kotlinReadOnly = kotlinReadOnly;
            this.kotlinMutable = kotlinMutable;
        }

        @NotNull
        public final ClassId getJavaClass() {
            return this.javaClass;
        }
    }

    @NotNull
    public final List<PlatformMutabilityMapping> getMutabilityMappings() {
        return mutabilityMappings;
    }

    @Nullable
    public final ClassId mapJavaToKotlin(@NotNull FqName fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        return javaToKotlin.get(fqName.toUnsafe());
    }

    @Nullable
    public final ClassId mapKotlinToJava(@NotNull FqNameUnsafe kotlinFqName) {
        Intrinsics.checkNotNullParameter(kotlinFqName, "kotlinFqName");
        if (!isKotlinFunctionWithBigArity(kotlinFqName, NUMBERED_FUNCTION_PREFIX) && !isKotlinFunctionWithBigArity(kotlinFqName, NUMBERED_SUSPEND_FUNCTION_PREFIX)) {
            if (!isKotlinFunctionWithBigArity(kotlinFqName, NUMBERED_K_FUNCTION_PREFIX) && !isKotlinFunctionWithBigArity(kotlinFqName, NUMBERED_K_SUSPEND_FUNCTION_PREFIX)) {
                return kotlinToJava.get(kotlinFqName);
            }
            return K_FUNCTION_CLASS_ID;
        }
        return FUNCTION_N_CLASS_ID;
    }

    private final boolean isKotlinFunctionWithBigArity(FqNameUnsafe kotlinFqName, String prefix) {
        Integer arity;
        String strAsString = kotlinFqName.asString();
        Intrinsics.checkNotNullExpressionValue(strAsString, "kotlinFqName.asString()");
        String arityString = StringsKt.substringAfter(strAsString, prefix, "");
        return (arityString.length() > 0) && !StringsKt.startsWith$default((CharSequence) arityString, '0', false, 2, (Object) null) && (arity = StringsKt.toIntOrNull(arityString)) != null && arity.intValue() >= 23;
    }

    private final void addMapping(PlatformMutabilityMapping platformMutabilityMapping) {
        ClassId javaClassId = platformMutabilityMapping.component1();
        ClassId readOnlyClassId = platformMutabilityMapping.component2();
        ClassId mutableClassId = platformMutabilityMapping.component3();
        add(javaClassId, readOnlyClassId);
        FqName fqNameAsSingleFqName = mutableClassId.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "mutableClassId.asSingleFqName()");
        addKotlinToJava(fqNameAsSingleFqName, javaClassId);
        FqName readOnlyFqName = readOnlyClassId.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(readOnlyFqName, "readOnlyClassId.asSingleFqName()");
        FqName mutableFqName = mutableClassId.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(mutableFqName, "mutableClassId.asSingleFqName()");
        HashMap<FqNameUnsafe, FqName> map = mutableToReadOnly;
        FqNameUnsafe unsafe = mutableClassId.asSingleFqName().toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "mutableClassId.asSingleFqName().toUnsafe()");
        map.put(unsafe, readOnlyFqName);
        HashMap<FqNameUnsafe, FqName> map2 = readOnlyToMutable;
        FqNameUnsafe unsafe2 = readOnlyFqName.toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe2, "readOnlyFqName.toUnsafe()");
        map2.put(unsafe2, mutableFqName);
    }

    private final void add(ClassId javaClassId, ClassId kotlinClassId) {
        addJavaToKotlin(javaClassId, kotlinClassId);
        FqName fqNameAsSingleFqName = kotlinClassId.asSingleFqName();
        Intrinsics.checkNotNullExpressionValue(fqNameAsSingleFqName, "kotlinClassId.asSingleFqName()");
        addKotlinToJava(fqNameAsSingleFqName, javaClassId);
    }

    private final void addTopLevel(Class<?> cls, FqNameUnsafe kotlinFqName) {
        FqName safe = kotlinFqName.toSafe();
        Intrinsics.checkNotNullExpressionValue(safe, "kotlinFqName.toSafe()");
        addTopLevel(cls, safe);
    }

    private final void addTopLevel(Class<?> cls, FqName kotlinFqName) {
        ClassId classId = classId(cls);
        ClassId classId2 = ClassId.topLevel(kotlinFqName);
        Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(kotlinFqName)");
        add(classId, classId2);
    }

    private final void addJavaToKotlin(ClassId javaClassId, ClassId kotlinClassId) {
        HashMap<FqNameUnsafe, ClassId> map = javaToKotlin;
        FqNameUnsafe unsafe = javaClassId.asSingleFqName().toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "javaClassId.asSingleFqName().toUnsafe()");
        map.put(unsafe, kotlinClassId);
    }

    private final void addKotlinToJava(FqName kotlinFqNameUnsafe, ClassId javaClassId) {
        HashMap<FqNameUnsafe, ClassId> map = kotlinToJava;
        FqNameUnsafe unsafe = kotlinFqNameUnsafe.toUnsafe();
        Intrinsics.checkNotNullExpressionValue(unsafe, "kotlinFqNameUnsafe.toUnsafe()");
        map.put(unsafe, javaClassId);
    }

    @Nullable
    public final FqName mutableToReadOnly(@Nullable FqNameUnsafe fqNameUnsafe) {
        return mutableToReadOnly.get(fqNameUnsafe);
    }

    @Nullable
    public final FqName readOnlyToMutable(@Nullable FqNameUnsafe fqNameUnsafe) {
        return readOnlyToMutable.get(fqNameUnsafe);
    }

    public final boolean isMutable(@Nullable FqNameUnsafe fqNameUnsafe) {
        HashMap<FqNameUnsafe, FqName> map = mutableToReadOnly;
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
        }
        return map.containsKey(fqNameUnsafe);
    }

    public final boolean isReadOnly(@Nullable FqNameUnsafe fqNameUnsafe) {
        HashMap<FqNameUnsafe, FqName> map = readOnlyToMutable;
        if (map == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
        }
        return map.containsKey(fqNameUnsafe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ClassId classId(Class<?> cls) {
        boolean z = (cls.isPrimitive() || cls.isArray()) ? false : true;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError(Intrinsics.stringPlus("Invalid class: ", cls));
        }
        Class outer = cls.getDeclaringClass();
        if (outer == null) {
            ClassId classId = ClassId.topLevel(new FqName(cls.getCanonicalName()));
            Intrinsics.checkNotNullExpressionValue(classId, "topLevel(FqName(clazz.canonicalName))");
            return classId;
        }
        ClassId classIdCreateNestedClassId = classId(outer).createNestedClassId(Name.identifier(cls.getSimpleName()));
        Intrinsics.checkNotNullExpressionValue(classIdCreateNestedClassId, "classId(outer).createNestedClassId(Name.identifier(clazz.simpleName))");
        return classIdCreateNestedClassId;
    }
}
