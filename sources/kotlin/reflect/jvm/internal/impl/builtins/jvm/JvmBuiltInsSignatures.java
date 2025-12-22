package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import org.jetbrains.annotations.NotNull;

/* compiled from: JvmBuiltInsSignatures.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/jvm/JvmBuiltInsSignatures.class */
public final class JvmBuiltInsSignatures {

    @NotNull
    public static final JvmBuiltInsSignatures INSTANCE = new JvmBuiltInsSignatures();

    @NotNull
    private static final Set<String> DROP_LIST_METHOD_SIGNATURES = SetsKt.plus(SignatureBuildingComponents.INSTANCE.inJavaUtil("Collection", "toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");

    @NotNull
    private static final Set<String> HIDDEN_METHOD_SIGNATURES;

    @NotNull
    private static final Set<String> VISIBLE_METHOD_SIGNATURES;

    @NotNull
    private static final Set<String> MUTABLE_METHOD_SIGNATURES;

    @NotNull
    private static final Set<String> HIDDEN_CONSTRUCTOR_SIGNATURES;

    @NotNull
    private static final Set<String> VISIBLE_CONSTRUCTOR_SIGNATURES;

    private JvmBuiltInsSignatures() {
    }

    @NotNull
    public final Set<String> getDROP_LIST_METHOD_SIGNATURES() {
        return DROP_LIST_METHOD_SIGNATURES;
    }

    static {
        SignatureBuildingComponents $this$HIDDEN_METHOD_SIGNATURES_u24lambda_u2d0 = SignatureBuildingComponents.INSTANCE;
        HIDDEN_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus((Set) INSTANCE.buildPrimitiveValueMethodsSet(), (Iterable) $this$HIDDEN_METHOD_SIGNATURES_u24lambda_u2d0.inJavaUtil(PDListAttributeObject.OWNER_LIST, "sort(Ljava/util/Comparator;)V")), (Iterable) $this$HIDDEN_METHOD_SIGNATURES_u24lambda_u2d0.inJavaLang("String", "codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;")), (Iterable) $this$HIDDEN_METHOD_SIGNATURES_u24lambda_u2d0.inJavaLang(PDLayoutAttributeObject.BORDER_STYLE_DOUBLE, "isInfinite()Z", "isNaN()Z")), (Iterable) $this$HIDDEN_METHOD_SIGNATURES_u24lambda_u2d0.inJavaLang("Float", "isInfinite()Z", "isNaN()Z")), (Iterable) $this$HIDDEN_METHOD_SIGNATURES_u24lambda_u2d0.inJavaLang("Enum", "getDeclaringClass()Ljava/lang/Class;", "finalize()V")), (Iterable) $this$HIDDEN_METHOD_SIGNATURES_u24lambda_u2d0.inJavaLang("CharSequence", "isEmpty()Z"));
        SignatureBuildingComponents $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3 = SignatureBuildingComponents.INSTANCE;
        VISIBLE_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus((Set) $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3.inJavaLang("CharSequence", "codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"), (Iterable) $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3.inJavaUtil("Iterator", "forEachRemaining(Ljava/util/function/Consumer;)V")), (Iterable) $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3.inJavaLang("Iterable", "forEach(Ljava/util/function/Consumer;)V", "spliterator()Ljava/util/Spliterator;")), (Iterable) $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3.inJavaLang("Throwable", "setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V")), (Iterable) $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3.inJavaUtil("Collection", "spliterator()Ljava/util/Spliterator;", "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", "removeIf(Ljava/util/function/Predicate;)Z")), (Iterable) $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3.inJavaUtil(PDListAttributeObject.OWNER_LIST, "replaceAll(Ljava/util/function/UnaryOperator;)V")), (Iterable) $this$VISIBLE_METHOD_SIGNATURES_u24lambda_u2d3.inJavaUtil("Map", "getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"));
        SignatureBuildingComponents $this$MUTABLE_METHOD_SIGNATURES_u24lambda_u2d4 = SignatureBuildingComponents.INSTANCE;
        MUTABLE_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus((Set) $this$MUTABLE_METHOD_SIGNATURES_u24lambda_u2d4.inJavaUtil("Collection", "removeIf(Ljava/util/function/Predicate;)Z"), (Iterable) $this$MUTABLE_METHOD_SIGNATURES_u24lambda_u2d4.inJavaUtil(PDListAttributeObject.OWNER_LIST, "replaceAll(Ljava/util/function/UnaryOperator;)V", "sort(Ljava/util/Comparator;)V")), (Iterable) $this$MUTABLE_METHOD_SIGNATURES_u24lambda_u2d4.inJavaUtil("Map", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"));
        SignatureBuildingComponents $this$HIDDEN_CONSTRUCTOR_SIGNATURES_u24lambda_u2d5 = SignatureBuildingComponents.INSTANCE;
        Set<String> setBuildPrimitiveStringConstructorsSet = INSTANCE.buildPrimitiveStringConstructorsSet();
        String[] strArrConstructors = $this$HIDDEN_CONSTRUCTOR_SIGNATURES_u24lambda_u2d5.constructors("D");
        String[] strArr = new String[strArrConstructors.length];
        System.arraycopy(strArrConstructors, 0, strArr, 0, strArrConstructors.length);
        Set setPlus = SetsKt.plus((Set) setBuildPrimitiveStringConstructorsSet, (Iterable) $this$HIDDEN_CONSTRUCTOR_SIGNATURES_u24lambda_u2d5.inJavaLang("Float", strArr));
        String[] strArrConstructors2 = $this$HIDDEN_CONSTRUCTOR_SIGNATURES_u24lambda_u2d5.constructors("[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;");
        String[] strArr2 = new String[strArrConstructors2.length];
        System.arraycopy(strArrConstructors2, 0, strArr2, 0, strArrConstructors2.length);
        HIDDEN_CONSTRUCTOR_SIGNATURES = SetsKt.plus(setPlus, (Iterable) $this$HIDDEN_CONSTRUCTOR_SIGNATURES_u24lambda_u2d5.inJavaLang("String", strArr2));
        SignatureBuildingComponents $this$VISIBLE_CONSTRUCTOR_SIGNATURES_u24lambda_u2d6 = SignatureBuildingComponents.INSTANCE;
        String[] strArrConstructors3 = $this$VISIBLE_CONSTRUCTOR_SIGNATURES_u24lambda_u2d6.constructors("Ljava/lang/String;Ljava/lang/Throwable;ZZ");
        String[] strArr3 = new String[strArrConstructors3.length];
        System.arraycopy(strArrConstructors3, 0, strArr3, 0, strArrConstructors3.length);
        VISIBLE_CONSTRUCTOR_SIGNATURES = $this$VISIBLE_CONSTRUCTOR_SIGNATURES_u24lambda_u2d6.inJavaLang("Throwable", strArr3);
    }

    @NotNull
    public final Set<String> getHIDDEN_METHOD_SIGNATURES() {
        return HIDDEN_METHOD_SIGNATURES;
    }

    private final Set<String> buildPrimitiveValueMethodsSet() {
        SignatureBuildingComponents $this$buildPrimitiveValueMethodsSet_u24lambda_u2d2 = SignatureBuildingComponents.INSTANCE;
        Iterable $this$flatMapTo$iv = CollectionsKt.listOf((Object[]) new JvmPrimitiveType[]{JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.CHAR});
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$flatMapTo$iv) {
            JvmPrimitiveType it = (JvmPrimitiveType) element$iv;
            String strAsString = it.getWrapperFqName().shortName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "it.wrapperFqName.shortName().asString()");
            Iterable list$iv = $this$buildPrimitiveValueMethodsSet_u24lambda_u2d2.inJavaLang(strAsString, it.getJavaKeywordName() + "Value()" + it.getDesc());
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (LinkedHashSet) destination$iv;
    }

    @NotNull
    public final Set<String> getVISIBLE_METHOD_SIGNATURES() {
        return VISIBLE_METHOD_SIGNATURES;
    }

    @NotNull
    public final Set<String> getMUTABLE_METHOD_SIGNATURES() {
        return MUTABLE_METHOD_SIGNATURES;
    }

    @NotNull
    public final Set<String> getHIDDEN_CONSTRUCTOR_SIGNATURES() {
        return HIDDEN_CONSTRUCTOR_SIGNATURES;
    }

    @NotNull
    public final Set<String> getVISIBLE_CONSTRUCTOR_SIGNATURES() {
        return VISIBLE_CONSTRUCTOR_SIGNATURES;
    }

    private final Set<String> buildPrimitiveStringConstructorsSet() {
        SignatureBuildingComponents $this$buildPrimitiveStringConstructorsSet_u24lambda_u2d8 = SignatureBuildingComponents.INSTANCE;
        Iterable $this$flatMapTo$iv = CollectionsKt.listOf((Object[]) new JvmPrimitiveType[]{JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.BYTE, JvmPrimitiveType.DOUBLE, JvmPrimitiveType.FLOAT, JvmPrimitiveType.BYTE, JvmPrimitiveType.INT, JvmPrimitiveType.LONG, JvmPrimitiveType.SHORT});
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$flatMapTo$iv) {
            JvmPrimitiveType it = (JvmPrimitiveType) element$iv;
            String strAsString = it.getWrapperFqName().shortName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "it.wrapperFqName.shortName().asString()");
            String[] strArrConstructors = $this$buildPrimitiveStringConstructorsSet_u24lambda_u2d8.constructors("Ljava/lang/String;");
            String[] strArr = new String[strArrConstructors.length];
            System.arraycopy(strArrConstructors, 0, strArr, 0, strArrConstructors.length);
            Iterable list$iv = $this$buildPrimitiveStringConstructorsSet_u24lambda_u2d8.inJavaLang(strAsString, strArr);
            CollectionsKt.addAll(destination$iv, list$iv);
        }
        return (LinkedHashSet) destination$iv;
    }

    public final boolean isSerializableInJava(@NotNull FqNameUnsafe fqName) throws ClassNotFoundException {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        if (isArrayOrPrimitiveArray(fqName)) {
            return true;
        }
        ClassId javaClassId = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqName);
        if (javaClassId == null) {
            return false;
        }
        try {
            Class classViaReflection = Class.forName(javaClassId.asSingleFqName().asString());
            return Serializable.class.isAssignableFrom(classViaReflection);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public final boolean isArrayOrPrimitiveArray(@NotNull FqNameUnsafe fqName) {
        Intrinsics.checkNotNullParameter(fqName, "fqName");
        if (!Intrinsics.areEqual(fqName, StandardNames.FqNames.array)) {
            StandardNames standardNames = StandardNames.INSTANCE;
            if (!StandardNames.isPrimitiveArray(fqName)) {
                return false;
            }
        }
        return true;
    }
}
