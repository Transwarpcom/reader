package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: reflectClassUtil.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/runtime/structure/ReflectClassUtilKt.class */
public final class ReflectClassUtilKt {

    @NotNull
    private static final List<KClass<? extends Object>> PRIMITIVE_CLASSES = CollectionsKt.listOf((Object[]) new KClass[]{Reflection.getOrCreateKotlinClass(Boolean.TYPE), Reflection.getOrCreateKotlinClass(Byte.TYPE), Reflection.getOrCreateKotlinClass(Character.TYPE), Reflection.getOrCreateKotlinClass(Double.TYPE), Reflection.getOrCreateKotlinClass(Float.TYPE), Reflection.getOrCreateKotlinClass(Integer.TYPE), Reflection.getOrCreateKotlinClass(Long.TYPE), Reflection.getOrCreateKotlinClass(Short.TYPE)});

    @NotNull
    private static final Map<Class<? extends Object>, Class<? extends Object>> WRAPPER_TO_PRIMITIVE;

    @NotNull
    private static final Map<Class<? extends Object>, Class<? extends Object>> PRIMITIVE_TO_WRAPPER;

    @NotNull
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES;

    @NotNull
    public static final ClassLoader getSafeClassLoader(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Intrinsics.checkNotNullExpressionValue(systemClassLoader, "getSystemClassLoader()");
        return systemClassLoader;
    }

    public static final boolean isEnumClassOrSpecializedEnumEntryClass(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return Enum.class.isAssignableFrom(cls);
    }

    static {
        Iterable $this$map$iv = PRIMITIVE_CLASSES;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            KClass it = (KClass) item$iv$iv;
            destination$iv$iv.add(TuplesKt.to(JvmClassMappingKt.getJavaObjectType(it), JvmClassMappingKt.getJavaPrimitiveType(it)));
        }
        WRAPPER_TO_PRIMITIVE = MapsKt.toMap((List) destination$iv$iv);
        Iterable $this$map$iv2 = PRIMITIVE_CLASSES;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            KClass it2 = (KClass) item$iv$iv2;
            destination$iv$iv2.add(TuplesKt.to(JvmClassMappingKt.getJavaPrimitiveType(it2), JvmClassMappingKt.getJavaObjectType(it2)));
        }
        PRIMITIVE_TO_WRAPPER = MapsKt.toMap((List) destination$iv$iv2);
        Iterable $this$mapIndexed$iv = CollectionsKt.listOf((Object[]) new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv3 : $this$mapIndexed$iv) {
            int i = index$iv$iv;
            index$iv$iv++;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Class clazz = (Class) item$iv$iv3;
            destination$iv$iv3.add(TuplesKt.to(clazz, Integer.valueOf(i)));
        }
        FUNCTION_CLASSES = MapsKt.toMap((List) destination$iv$iv3);
    }

    @Nullable
    public static final Class<?> getPrimitiveByWrapper(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return WRAPPER_TO_PRIMITIVE.get(cls);
    }

    @Nullable
    public static final Class<?> getWrapperByPrimitive(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return PRIMITIVE_TO_WRAPPER.get(cls);
    }

    @Nullable
    public static final Integer getFunctionClassArity(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return FUNCTION_CLASSES.get(cls);
    }

    @NotNull
    public static final ClassId getClassId(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Can't compute ClassId for primitive type: ", cls));
        }
        if (cls.isArray()) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Can't compute ClassId for array type: ", cls));
        }
        if (cls.getEnclosingMethod() == null && cls.getEnclosingConstructor() == null) {
            String simpleName = cls.getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "simpleName");
            if (!(simpleName.length() == 0)) {
                Class<?> declaringClass = cls.getDeclaringClass();
                ClassId classIdCreateNestedClassId = declaringClass == null ? null : getClassId(declaringClass).createNestedClassId(Name.identifier(cls.getSimpleName()));
                ClassId classId = classIdCreateNestedClassId == null ? ClassId.topLevel(new FqName(cls.getName())) : classIdCreateNestedClassId;
                Intrinsics.checkNotNullExpressionValue(classId, "declaringClass?.classId?.createNestedClassId(Name.identifier(simpleName)) ?: ClassId.topLevel(FqName(name))");
                return classId;
            }
        }
        FqName fqName = new FqName(cls.getName());
        return new ClassId(fqName.parent(), FqName.topLevel(fqName.shortName()), true);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @NotNull
    public static final String getDesc(@NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        if (cls.isPrimitive()) {
            String name = cls.getName();
            if (name != null) {
                switch (name.hashCode()) {
                    case -1325958191:
                        if (name.equals("double")) {
                            return "D";
                        }
                        break;
                    case 104431:
                        if (name.equals("int")) {
                            return "I";
                        }
                        break;
                    case 3039496:
                        if (name.equals("byte")) {
                            return "B";
                        }
                        break;
                    case 3052374:
                        if (name.equals("char")) {
                            return "C";
                        }
                        break;
                    case 3327612:
                        if (name.equals("long")) {
                            return OperatorName.SET_LINE_CAPSTYLE;
                        }
                        break;
                    case 3625364:
                        if (name.equals("void")) {
                            return "V";
                        }
                        break;
                    case 64711720:
                        if (name.equals("boolean")) {
                            return "Z";
                        }
                        break;
                    case 97526364:
                        if (name.equals("float")) {
                            return "F";
                        }
                        break;
                    case 109413500:
                        if (name.equals("short")) {
                            return "S";
                        }
                        break;
                }
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("Unsupported primitive type: ", cls));
        }
        if (cls.isArray()) {
            String name2 = cls.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "name");
            return StringsKt.replace$default(name2, '.', '/', false, 4, (Object) null);
        }
        StringBuilder sbAppend = new StringBuilder().append('L');
        String name3 = cls.getName();
        Intrinsics.checkNotNullExpressionValue(name3, "name");
        return sbAppend.append(StringsKt.replace$default(name3, '.', '/', false, 4, (Object) null)).append(';').toString();
    }

    @NotNull
    public static final List<Type> getParameterizedTypeArguments(@NotNull Type $this$parameterizedTypeArguments) {
        Intrinsics.checkNotNullParameter($this$parameterizedTypeArguments, "<this>");
        if (!($this$parameterizedTypeArguments instanceof ParameterizedType)) {
            return CollectionsKt.emptyList();
        }
        if (((ParameterizedType) $this$parameterizedTypeArguments).getOwnerType() != null) {
            return SequencesKt.toList(SequencesKt.flatMap(SequencesKt.generateSequence($this$parameterizedTypeArguments, new Function1<ParameterizedType, ParameterizedType>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt$parameterizedTypeArguments$1
                @Override // kotlin.jvm.functions.Function1
                @Nullable
                public final ParameterizedType invoke(@NotNull ParameterizedType it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Type ownerType = it.getOwnerType();
                    if (ownerType instanceof ParameterizedType) {
                        return (ParameterizedType) ownerType;
                    }
                    return null;
                }
            }), new Function1<ParameterizedType, Sequence<? extends Type>>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt$parameterizedTypeArguments$2
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Sequence<Type> invoke(@NotNull ParameterizedType it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Type[] actualTypeArguments = it.getActualTypeArguments();
                    Intrinsics.checkNotNullExpressionValue(actualTypeArguments, "it.actualTypeArguments");
                    return ArraysKt.asSequence(actualTypeArguments);
                }
            }));
        }
        Type[] actualTypeArguments = ((ParameterizedType) $this$parameterizedTypeArguments).getActualTypeArguments();
        Intrinsics.checkNotNullExpressionValue(actualTypeArguments, "actualTypeArguments");
        return ArraysKt.toList(actualTypeArguments);
    }
}
