package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import org.jetbrains.annotations.NotNull;

/* compiled from: StandardNames.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/StandardNames.class */
public final class StandardNames {

    @NotNull
    public static final StandardNames INSTANCE = new StandardNames();

    @JvmField
    @NotNull
    public static final Name ENUM_VALUES;

    @JvmField
    @NotNull
    public static final Name ENUM_VALUE_OF;

    @JvmField
    @NotNull
    public static final Name CHAR_CODE;

    @JvmField
    @NotNull
    public static final FqName COROUTINES_PACKAGE_FQ_NAME_RELEASE;

    @JvmField
    @NotNull
    public static final FqName COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL;

    @JvmField
    @NotNull
    public static final FqName COROUTINES_INTRINSICS_PACKAGE_FQ_NAME_EXPERIMENTAL;

    @JvmField
    @NotNull
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL;

    @JvmField
    @NotNull
    public static final FqName CONTINUATION_INTERFACE_FQ_NAME_RELEASE;

    @JvmField
    @NotNull
    public static final FqName RESULT_FQ_NAME;

    @JvmField
    @NotNull
    public static final FqName KOTLIN_REFLECT_FQ_NAME;

    @JvmField
    @NotNull
    public static final List<String> PREFIXES;

    @JvmField
    @NotNull
    public static final Name BUILT_INS_PACKAGE_NAME;

    @JvmField
    @NotNull
    public static final FqName BUILT_INS_PACKAGE_FQ_NAME;

    @JvmField
    @NotNull
    public static final FqName ANNOTATION_PACKAGE_FQ_NAME;

    @JvmField
    @NotNull
    public static final FqName COLLECTIONS_PACKAGE_FQ_NAME;

    @JvmField
    @NotNull
    public static final FqName RANGES_PACKAGE_FQ_NAME;

    @JvmField
    @NotNull
    public static final FqName TEXT_PACKAGE_FQ_NAME;

    @JvmField
    @NotNull
    public static final Set<FqName> BUILT_INS_PACKAGE_FQ_NAMES;

    private StandardNames() {
    }

    static {
        Name nameIdentifier = Name.identifier("values");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(\"values\")");
        ENUM_VALUES = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("valueOf");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "identifier(\"valueOf\")");
        ENUM_VALUE_OF = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("code");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier3, "identifier(\"code\")");
        CHAR_CODE = nameIdentifier3;
        COROUTINES_PACKAGE_FQ_NAME_RELEASE = new FqName("kotlin.coroutines");
        FqName fqNameChild = COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier("experimental"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild, "COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier(\"experimental\"))");
        COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL = fqNameChild;
        FqName fqNameChild2 = COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL.child(Name.identifier("intrinsics"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild2, "COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL.child(Name.identifier(\"intrinsics\"))");
        COROUTINES_INTRINSICS_PACKAGE_FQ_NAME_EXPERIMENTAL = fqNameChild2;
        FqName fqNameChild3 = COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL.child(Name.identifier("Continuation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild3, "COROUTINES_PACKAGE_FQ_NAME_EXPERIMENTAL.child(Name.identifier(\"Continuation\"))");
        CONTINUATION_INTERFACE_FQ_NAME_EXPERIMENTAL = fqNameChild3;
        FqName fqNameChild4 = COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier("Continuation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild4, "COROUTINES_PACKAGE_FQ_NAME_RELEASE.child(Name.identifier(\"Continuation\"))");
        CONTINUATION_INTERFACE_FQ_NAME_RELEASE = fqNameChild4;
        RESULT_FQ_NAME = new FqName("kotlin.Result");
        KOTLIN_REFLECT_FQ_NAME = new FqName("kotlin.reflect");
        PREFIXES = CollectionsKt.listOf((Object[]) new String[]{"KProperty", "KMutableProperty", "KFunction", "KSuspendFunction"});
        Name nameIdentifier4 = Name.identifier("kotlin");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier4, "identifier(\"kotlin\")");
        BUILT_INS_PACKAGE_NAME = nameIdentifier4;
        FqName fqName = FqName.topLevel(BUILT_INS_PACKAGE_NAME);
        Intrinsics.checkNotNullExpressionValue(fqName, "topLevel(BUILT_INS_PACKAGE_NAME)");
        BUILT_INS_PACKAGE_FQ_NAME = fqName;
        FqName fqNameChild5 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("annotation"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild5, "BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(\"annotation\"))");
        ANNOTATION_PACKAGE_FQ_NAME = fqNameChild5;
        FqName fqNameChild6 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("collections"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild6, "BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(\"collections\"))");
        COLLECTIONS_PACKAGE_FQ_NAME = fqNameChild6;
        FqName fqNameChild7 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("ranges"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild7, "BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(\"ranges\"))");
        RANGES_PACKAGE_FQ_NAME = fqNameChild7;
        FqName fqNameChild8 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(NCXDocumentV2.NCXTags.text));
        Intrinsics.checkNotNullExpressionValue(fqNameChild8, "BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(\"text\"))");
        TEXT_PACKAGE_FQ_NAME = fqNameChild8;
        FqName fqNameChild9 = BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier("internal"));
        Intrinsics.checkNotNullExpressionValue(fqNameChild9, "BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(\"internal\"))");
        BUILT_INS_PACKAGE_FQ_NAMES = SetsKt.setOf((Object[]) new FqName[]{BUILT_INS_PACKAGE_FQ_NAME, COLLECTIONS_PACKAGE_FQ_NAME, RANGES_PACKAGE_FQ_NAME, ANNOTATION_PACKAGE_FQ_NAME, KOTLIN_REFLECT_FQ_NAME, fqNameChild9, COROUTINES_PACKAGE_FQ_NAME_RELEASE});
    }

    /* compiled from: StandardNames.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/StandardNames$FqNames.class */
    public static final class FqNames {

        @NotNull
        public static final FqNames INSTANCE = new FqNames();

        @JvmField
        @NotNull
        public static final FqNameUnsafe any = INSTANCE.fqNameUnsafe("Any");

        @JvmField
        @NotNull
        public static final FqNameUnsafe nothing = INSTANCE.fqNameUnsafe("Nothing");

        @JvmField
        @NotNull
        public static final FqNameUnsafe cloneable = INSTANCE.fqNameUnsafe("Cloneable");

        @JvmField
        @NotNull
        public static final FqName suppress = INSTANCE.fqName("Suppress");

        @JvmField
        @NotNull
        public static final FqNameUnsafe unit = INSTANCE.fqNameUnsafe("Unit");

        @JvmField
        @NotNull
        public static final FqNameUnsafe charSequence = INSTANCE.fqNameUnsafe("CharSequence");

        @JvmField
        @NotNull
        public static final FqNameUnsafe string = INSTANCE.fqNameUnsafe("String");

        @JvmField
        @NotNull
        public static final FqNameUnsafe array = INSTANCE.fqNameUnsafe("Array");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _boolean = INSTANCE.fqNameUnsafe("Boolean");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _char = INSTANCE.fqNameUnsafe("Char");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _byte = INSTANCE.fqNameUnsafe("Byte");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _short = INSTANCE.fqNameUnsafe("Short");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _int = INSTANCE.fqNameUnsafe("Int");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _long = INSTANCE.fqNameUnsafe("Long");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _float = INSTANCE.fqNameUnsafe("Float");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _double = INSTANCE.fqNameUnsafe(PDLayoutAttributeObject.BORDER_STYLE_DOUBLE);

        @JvmField
        @NotNull
        public static final FqNameUnsafe number = INSTANCE.fqNameUnsafe("Number");

        @JvmField
        @NotNull
        public static final FqNameUnsafe _enum = INSTANCE.fqNameUnsafe("Enum");

        @JvmField
        @NotNull
        public static final FqNameUnsafe functionSupertype = INSTANCE.fqNameUnsafe("Function");

        @JvmField
        @NotNull
        public static final FqName throwable = INSTANCE.fqName("Throwable");

        @JvmField
        @NotNull
        public static final FqName comparable = INSTANCE.fqName("Comparable");

        @JvmField
        @NotNull
        public static final FqNameUnsafe intRange = INSTANCE.rangesFqName("IntRange");

        @JvmField
        @NotNull
        public static final FqNameUnsafe longRange = INSTANCE.rangesFqName("LongRange");

        @JvmField
        @NotNull
        public static final FqName deprecated = INSTANCE.fqName("Deprecated");

        @JvmField
        @NotNull
        public static final FqName deprecatedSinceKotlin = INSTANCE.fqName("DeprecatedSinceKotlin");

        @JvmField
        @NotNull
        public static final FqName deprecationLevel = INSTANCE.fqName("DeprecationLevel");

        @JvmField
        @NotNull
        public static final FqName replaceWith = INSTANCE.fqName("ReplaceWith");

        @JvmField
        @NotNull
        public static final FqName extensionFunctionType = INSTANCE.fqName("ExtensionFunctionType");

        @JvmField
        @NotNull
        public static final FqName parameterName = INSTANCE.fqName("ParameterName");

        @JvmField
        @NotNull
        public static final FqName annotation = INSTANCE.fqName("Annotation");

        @JvmField
        @NotNull
        public static final FqName target = INSTANCE.annotationName("Target");

        @JvmField
        @NotNull
        public static final FqName annotationTarget = INSTANCE.annotationName("AnnotationTarget");

        @JvmField
        @NotNull
        public static final FqName annotationRetention = INSTANCE.annotationName("AnnotationRetention");

        @JvmField
        @NotNull
        public static final FqName retention = INSTANCE.annotationName("Retention");

        @JvmField
        @NotNull
        public static final FqName repeatable = INSTANCE.annotationName("Repeatable");

        @JvmField
        @NotNull
        public static final FqName mustBeDocumented = INSTANCE.annotationName("MustBeDocumented");

        @JvmField
        @NotNull
        public static final FqName unsafeVariance = INSTANCE.fqName("UnsafeVariance");

        @JvmField
        @NotNull
        public static final FqName publishedApi = INSTANCE.fqName("PublishedApi");

        @JvmField
        @NotNull
        public static final FqName iterator = INSTANCE.collectionsFqName("Iterator");

        @JvmField
        @NotNull
        public static final FqName iterable = INSTANCE.collectionsFqName("Iterable");

        @JvmField
        @NotNull
        public static final FqName collection = INSTANCE.collectionsFqName("Collection");

        @JvmField
        @NotNull
        public static final FqName list = INSTANCE.collectionsFqName(PDListAttributeObject.OWNER_LIST);

        @JvmField
        @NotNull
        public static final FqName listIterator = INSTANCE.collectionsFqName("ListIterator");

        @JvmField
        @NotNull
        public static final FqName set = INSTANCE.collectionsFqName("Set");

        @JvmField
        @NotNull
        public static final FqName map = INSTANCE.collectionsFqName("Map");

        @JvmField
        @NotNull
        public static final FqName mapEntry;

        @JvmField
        @NotNull
        public static final FqName mutableIterator;

        @JvmField
        @NotNull
        public static final FqName mutableIterable;

        @JvmField
        @NotNull
        public static final FqName mutableCollection;

        @JvmField
        @NotNull
        public static final FqName mutableList;

        @JvmField
        @NotNull
        public static final FqName mutableListIterator;

        @JvmField
        @NotNull
        public static final FqName mutableSet;

        @JvmField
        @NotNull
        public static final FqName mutableMap;

        @JvmField
        @NotNull
        public static final FqName mutableMapEntry;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kClass;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kCallable;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kProperty0;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kProperty1;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kProperty2;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kMutableProperty0;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kMutableProperty1;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kMutableProperty2;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kPropertyFqName;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kMutablePropertyFqName;

        @JvmField
        @NotNull
        public static final ClassId kProperty;

        @JvmField
        @NotNull
        public static final FqNameUnsafe kDeclarationContainer;

        @JvmField
        @NotNull
        public static final FqName uByteFqName;

        @JvmField
        @NotNull
        public static final FqName uShortFqName;

        @JvmField
        @NotNull
        public static final FqName uIntFqName;

        @JvmField
        @NotNull
        public static final FqName uLongFqName;

        @JvmField
        @NotNull
        public static final ClassId uByte;

        @JvmField
        @NotNull
        public static final ClassId uShort;

        @JvmField
        @NotNull
        public static final ClassId uInt;

        @JvmField
        @NotNull
        public static final ClassId uLong;

        @JvmField
        @NotNull
        public static final FqName uByteArrayFqName;

        @JvmField
        @NotNull
        public static final FqName uShortArrayFqName;

        @JvmField
        @NotNull
        public static final FqName uIntArrayFqName;

        @JvmField
        @NotNull
        public static final FqName uLongArrayFqName;

        @JvmField
        @NotNull
        public static final Set<Name> primitiveTypeShortNames;

        @JvmField
        @NotNull
        public static final Set<Name> primitiveArrayTypeShortNames;

        @JvmField
        @NotNull
        public static final Map<FqNameUnsafe, PrimitiveType> fqNameToPrimitiveType;

        @JvmField
        @NotNull
        public static final Map<FqNameUnsafe, PrimitiveType> arrayClassFqNameToPrimitiveType;

        private FqNames() {
        }

        static {
            FqName fqNameChild = map.child(Name.identifier("Entry"));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "map.child(Name.identifier(\"Entry\"))");
            mapEntry = fqNameChild;
            mutableIterator = INSTANCE.collectionsFqName("MutableIterator");
            mutableIterable = INSTANCE.collectionsFqName("MutableIterable");
            mutableCollection = INSTANCE.collectionsFqName("MutableCollection");
            mutableList = INSTANCE.collectionsFqName("MutableList");
            mutableListIterator = INSTANCE.collectionsFqName("MutableListIterator");
            mutableSet = INSTANCE.collectionsFqName("MutableSet");
            mutableMap = INSTANCE.collectionsFqName("MutableMap");
            FqName fqNameChild2 = mutableMap.child(Name.identifier("MutableEntry"));
            Intrinsics.checkNotNullExpressionValue(fqNameChild2, "mutableMap.child(Name.identifier(\"MutableEntry\"))");
            mutableMapEntry = fqNameChild2;
            FqNames fqNames = INSTANCE;
            kClass = reflect("KClass");
            FqNames fqNames2 = INSTANCE;
            kCallable = reflect("KCallable");
            FqNames fqNames3 = INSTANCE;
            kProperty0 = reflect("KProperty0");
            FqNames fqNames4 = INSTANCE;
            kProperty1 = reflect("KProperty1");
            FqNames fqNames5 = INSTANCE;
            kProperty2 = reflect("KProperty2");
            FqNames fqNames6 = INSTANCE;
            kMutableProperty0 = reflect("KMutableProperty0");
            FqNames fqNames7 = INSTANCE;
            kMutableProperty1 = reflect("KMutableProperty1");
            FqNames fqNames8 = INSTANCE;
            kMutableProperty2 = reflect("KMutableProperty2");
            FqNames fqNames9 = INSTANCE;
            kPropertyFqName = reflect("KProperty");
            FqNames fqNames10 = INSTANCE;
            kMutablePropertyFqName = reflect("KMutableProperty");
            ClassId classId = ClassId.topLevel(kPropertyFqName.toSafe());
            Intrinsics.checkNotNullExpressionValue(classId, "topLevel(kPropertyFqName.toSafe())");
            kProperty = classId;
            FqNames fqNames11 = INSTANCE;
            kDeclarationContainer = reflect("KDeclarationContainer");
            uByteFqName = INSTANCE.fqName("UByte");
            uShortFqName = INSTANCE.fqName("UShort");
            uIntFqName = INSTANCE.fqName("UInt");
            uLongFqName = INSTANCE.fqName("ULong");
            ClassId classId2 = ClassId.topLevel(uByteFqName);
            Intrinsics.checkNotNullExpressionValue(classId2, "topLevel(uByteFqName)");
            uByte = classId2;
            ClassId classId3 = ClassId.topLevel(uShortFqName);
            Intrinsics.checkNotNullExpressionValue(classId3, "topLevel(uShortFqName)");
            uShort = classId3;
            ClassId classId4 = ClassId.topLevel(uIntFqName);
            Intrinsics.checkNotNullExpressionValue(classId4, "topLevel(uIntFqName)");
            uInt = classId4;
            ClassId classId5 = ClassId.topLevel(uLongFqName);
            Intrinsics.checkNotNullExpressionValue(classId5, "topLevel(uLongFqName)");
            uLong = classId5;
            uByteArrayFqName = INSTANCE.fqName("UByteArray");
            uShortArrayFqName = INSTANCE.fqName("UShortArray");
            uIntArrayFqName = INSTANCE.fqName("UIntArray");
            uLongArrayFqName = INSTANCE.fqName("ULongArray");
            HashSet $this$primitiveTypeShortNames_u24lambda_u2d1 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            for (PrimitiveType primitiveType : PrimitiveType.values()) {
                $this$primitiveTypeShortNames_u24lambda_u2d1.add(primitiveType.getTypeName());
            }
            primitiveTypeShortNames = $this$primitiveTypeShortNames_u24lambda_u2d1;
            HashSet $this$primitiveArrayTypeShortNames_u24lambda_u2d3 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashSetWithExpectedSize(PrimitiveType.values().length);
            for (PrimitiveType primitiveType2 : PrimitiveType.values()) {
                $this$primitiveArrayTypeShortNames_u24lambda_u2d3.add(primitiveType2.getArrayTypeName());
            }
            primitiveArrayTypeShortNames = $this$primitiveArrayTypeShortNames_u24lambda_u2d3;
            HashMap $this$fqNameToPrimitiveType_u24lambda_u2d4 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] primitiveTypeArrValues = PrimitiveType.values();
            int i = 0;
            int length = primitiveTypeArrValues.length;
            while (i < length) {
                PrimitiveType primitiveType3 = primitiveTypeArrValues[i];
                i++;
                HashMap map2 = $this$fqNameToPrimitiveType_u24lambda_u2d4;
                FqNames fqNames12 = INSTANCE;
                String strAsString = primitiveType3.getTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString, "primitiveType.typeName.asString()");
                map2.put(fqNames12.fqNameUnsafe(strAsString), primitiveType3);
            }
            fqNameToPrimitiveType = $this$fqNameToPrimitiveType_u24lambda_u2d4;
            HashMap $this$arrayClassFqNameToPrimitiveType_u24lambda_u2d5 = kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.newHashMapWithExpectedSize(PrimitiveType.values().length);
            PrimitiveType[] primitiveTypeArrValues2 = PrimitiveType.values();
            int i2 = 0;
            int length2 = primitiveTypeArrValues2.length;
            while (i2 < length2) {
                PrimitiveType primitiveType4 = primitiveTypeArrValues2[i2];
                i2++;
                HashMap map3 = $this$arrayClassFqNameToPrimitiveType_u24lambda_u2d5;
                FqNames fqNames13 = INSTANCE;
                String strAsString2 = primitiveType4.getArrayTypeName().asString();
                Intrinsics.checkNotNullExpressionValue(strAsString2, "primitiveType.arrayTypeName.asString()");
                map3.put(fqNames13.fqNameUnsafe(strAsString2), primitiveType4);
            }
            arrayClassFqNameToPrimitiveType = $this$arrayClassFqNameToPrimitiveType_u24lambda_u2d5;
        }

        private final FqNameUnsafe fqNameUnsafe(String simpleName) {
            FqNameUnsafe unsafe = fqName(simpleName).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "fqName(simpleName).toUnsafe()");
            return unsafe;
        }

        private final FqName fqName(String simpleName) {
            FqName fqNameChild = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(simpleName));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "BUILT_INS_PACKAGE_FQ_NAME.child(Name.identifier(simpleName))");
            return fqNameChild;
        }

        private final FqName collectionsFqName(String simpleName) {
            FqName fqNameChild = StandardNames.COLLECTIONS_PACKAGE_FQ_NAME.child(Name.identifier(simpleName));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "COLLECTIONS_PACKAGE_FQ_NAME.child(Name.identifier(simpleName))");
            return fqNameChild;
        }

        private final FqNameUnsafe rangesFqName(String simpleName) {
            FqNameUnsafe unsafe = StandardNames.RANGES_PACKAGE_FQ_NAME.child(Name.identifier(simpleName)).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "RANGES_PACKAGE_FQ_NAME.child(Name.identifier(simpleName)).toUnsafe()");
            return unsafe;
        }

        @JvmStatic
        @NotNull
        public static final FqNameUnsafe reflect(@NotNull String simpleName) {
            Intrinsics.checkNotNullParameter(simpleName, "simpleName");
            FqNameUnsafe unsafe = StandardNames.KOTLIN_REFLECT_FQ_NAME.child(Name.identifier(simpleName)).toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "KOTLIN_REFLECT_FQ_NAME.child(Name.identifier(simpleName)).toUnsafe()");
            return unsafe;
        }

        private final FqName annotationName(String simpleName) {
            FqName fqNameChild = StandardNames.ANNOTATION_PACKAGE_FQ_NAME.child(Name.identifier(simpleName));
            Intrinsics.checkNotNullExpressionValue(fqNameChild, "ANNOTATION_PACKAGE_FQ_NAME.child(Name.identifier(simpleName))");
            return fqNameChild;
        }
    }

    @JvmStatic
    @NotNull
    public static final String getFunctionName(int parameterCount) {
        return Intrinsics.stringPlus("Function", Integer.valueOf(parameterCount));
    }

    @JvmStatic
    @NotNull
    public static final ClassId getFunctionClassId(int parameterCount) {
        FqName fqName = BUILT_INS_PACKAGE_FQ_NAME;
        StandardNames standardNames = INSTANCE;
        return new ClassId(fqName, Name.identifier(getFunctionName(parameterCount)));
    }

    @JvmStatic
    @NotNull
    public static final String getSuspendFunctionName(int parameterCount) {
        return Intrinsics.stringPlus(FunctionClassKind.SuspendFunction.getClassNamePrefix(), Integer.valueOf(parameterCount));
    }

    @JvmStatic
    public static final boolean isPrimitiveArray(@NotNull FqNameUnsafe arrayFqName) {
        Intrinsics.checkNotNullParameter(arrayFqName, "arrayFqName");
        return FqNames.arrayClassFqNameToPrimitiveType.get(arrayFqName) != null;
    }

    @JvmStatic
    @NotNull
    public static final FqName getPrimitiveFqName(@NotNull PrimitiveType primitiveType) {
        Intrinsics.checkNotNullParameter(primitiveType, "primitiveType");
        FqName fqNameChild = BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType.getTypeName());
        Intrinsics.checkNotNullExpressionValue(fqNameChild, "BUILT_INS_PACKAGE_FQ_NAME.child(primitiveType.typeName)");
        return fqNameChild;
    }
}
