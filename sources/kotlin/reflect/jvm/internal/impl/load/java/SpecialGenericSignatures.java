package kotlin.reflect.jvm.internal.impl.load.java;

import ch.qos.logback.core.joran.action.ActionConst;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SpecialGenericSignatures.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/SpecialGenericSignatures.class */
public class SpecialGenericSignatures {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final List<Companion.NameAndSignature> ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;

    @NotNull
    private static final List<String> ERASED_COLLECTION_PARAMETER_SIGNATURES;

    @NotNull
    private static final List<String> ERASED_COLLECTION_PARAMETER_NAMES;

    @NotNull
    private static final Map<Companion.NameAndSignature, TypeSafeBarrierDescription> GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;

    @NotNull
    private static final Map<String, TypeSafeBarrierDescription> SIGNATURE_TO_DEFAULT_VALUES_MAP;

    @NotNull
    private static final Set<Name> ERASED_VALUE_PARAMETERS_SHORT_NAMES;

    @NotNull
    private static final Set<String> ERASED_VALUE_PARAMETERS_SIGNATURES;

    @NotNull
    private static final Companion.NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE;

    @NotNull
    private static final Map<Companion.NameAndSignature, Name> NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;

    @NotNull
    private static final Map<String, Name> SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    @NotNull
    private static final List<Name> ORIGINAL_SHORT_NAMES;

    @NotNull
    private static final Map<Name, List<Name>> JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: SpecialGenericSignatures.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/SpecialGenericSignatures$TypeSafeBarrierDescription.class */
    public static final class TypeSafeBarrierDescription {

        @Nullable
        private final Object defaultValue;
        public static final TypeSafeBarrierDescription NULL = new TypeSafeBarrierDescription(ActionConst.NULL, 0, null);
        public static final TypeSafeBarrierDescription INDEX = new TypeSafeBarrierDescription("INDEX", 1, -1);
        public static final TypeSafeBarrierDescription FALSE = new TypeSafeBarrierDescription("FALSE", 2, false);
        public static final TypeSafeBarrierDescription MAP_GET_OR_DEFAULT = new MAP_GET_OR_DEFAULT("MAP_GET_OR_DEFAULT", 3);
        private static final /* synthetic */ TypeSafeBarrierDescription[] $VALUES = $values();

        public static TypeSafeBarrierDescription[] values() {
            return (TypeSafeBarrierDescription[]) $VALUES.clone();
        }

        public static TypeSafeBarrierDescription valueOf(String value) {
            return (TypeSafeBarrierDescription) Enum.valueOf(TypeSafeBarrierDescription.class, value);
        }

        private static final /* synthetic */ TypeSafeBarrierDescription[] $values() {
            return new TypeSafeBarrierDescription[]{NULL, INDEX, FALSE, MAP_GET_OR_DEFAULT};
        }

        public /* synthetic */ TypeSafeBarrierDescription(String $enum$name, int $enum$ordinal, Object defaultValue, DefaultConstructorMarker $constructor_marker) {
            this($enum$name, $enum$ordinal, defaultValue);
        }

        private TypeSafeBarrierDescription(String $enum$name, int $enum$ordinal, Object defaultValue) {
            this.defaultValue = defaultValue;
        }

        /* compiled from: SpecialGenericSignatures.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/SpecialGenericSignatures$TypeSafeBarrierDescription$MAP_GET_OR_DEFAULT.class */
        static final class MAP_GET_OR_DEFAULT extends TypeSafeBarrierDescription {
            MAP_GET_OR_DEFAULT(String $enum$name, int $enum$ordinal) {
                super($enum$name, $enum$ordinal, null, null);
            }
        }
    }

    /* compiled from: SpecialGenericSignatures.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/SpecialGenericSignatures$SpecialSignatureInfo.class */
    public enum SpecialSignatureInfo {
        ONE_COLLECTION_PARAMETER("Ljava/util/Collection<+Ljava/lang/Object;>;", false),
        OBJECT_PARAMETER_NON_GENERIC(null, true),
        OBJECT_PARAMETER_GENERIC("Ljava/lang/Object;", true);


        @Nullable
        private final String valueParametersSignature;
        private final boolean isObjectReplacedWithTypeParameter;

        SpecialSignatureInfo(String valueParametersSignature, boolean isObjectReplacedWithTypeParameter) {
            this.valueParametersSignature = valueParametersSignature;
            this.isObjectReplacedWithTypeParameter = isObjectReplacedWithTypeParameter;
        }
    }

    /* compiled from: SpecialGenericSignatures.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/SpecialGenericSignatures$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final SpecialSignatureInfo getSpecialSignatureInfo(@NotNull String builtinSignature) {
            Intrinsics.checkNotNullParameter(builtinSignature, "builtinSignature");
            if (getERASED_COLLECTION_PARAMETER_SIGNATURES().contains(builtinSignature)) {
                return SpecialSignatureInfo.ONE_COLLECTION_PARAMETER;
            }
            TypeSafeBarrierDescription defaultValue = (TypeSafeBarrierDescription) MapsKt.getValue(getSIGNATURE_TO_DEFAULT_VALUES_MAP(), builtinSignature);
            if (defaultValue == TypeSafeBarrierDescription.NULL) {
                return SpecialSignatureInfo.OBJECT_PARAMETER_GENERIC;
            }
            return SpecialSignatureInfo.OBJECT_PARAMETER_NON_GENERIC;
        }

        /* compiled from: SpecialGenericSignatures.kt */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/java/SpecialGenericSignatures$Companion$NameAndSignature.class */
        public static final class NameAndSignature {

            @NotNull
            private final Name name;

            @NotNull
            private final String signature;

            @NotNull
            public String toString() {
                return "NameAndSignature(name=" + this.name + ", signature=" + this.signature + ')';
            }

            public int hashCode() {
                int result = this.name.hashCode();
                return (result * 31) + this.signature.hashCode();
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof NameAndSignature)) {
                    return false;
                }
                NameAndSignature nameAndSignature = (NameAndSignature) other;
                return Intrinsics.areEqual(this.name, nameAndSignature.name) && Intrinsics.areEqual(this.signature, nameAndSignature.signature);
            }

            public NameAndSignature(@NotNull Name name, @NotNull String signature) {
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(signature, "signature");
                this.name = name;
                this.signature = signature;
            }

            @NotNull
            public final Name getName() {
                return this.name;
            }

            @NotNull
            public final String getSignature() {
                return this.signature;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final NameAndSignature method(String $this$method, String name, String parameters, String returnType) {
            Name nameIdentifier = Name.identifier(name);
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(name)");
            return new NameAndSignature(nameIdentifier, SignatureBuildingComponents.INSTANCE.signature($this$method, name + '(' + parameters + ')' + returnType));
        }

        @NotNull
        public final List<String> getERASED_COLLECTION_PARAMETER_SIGNATURES() {
            return SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_SIGNATURES;
        }

        @NotNull
        public final Map<String, TypeSafeBarrierDescription> getSIGNATURE_TO_DEFAULT_VALUES_MAP() {
            return SpecialGenericSignatures.SIGNATURE_TO_DEFAULT_VALUES_MAP;
        }

        @NotNull
        public final Set<Name> getERASED_VALUE_PARAMETERS_SHORT_NAMES() {
            return SpecialGenericSignatures.ERASED_VALUE_PARAMETERS_SHORT_NAMES;
        }

        @NotNull
        public final Set<String> getERASED_VALUE_PARAMETERS_SIGNATURES() {
            return SpecialGenericSignatures.ERASED_VALUE_PARAMETERS_SIGNATURES;
        }

        @NotNull
        public final NameAndSignature getREMOVE_AT_NAME_AND_SIGNATURE() {
            return SpecialGenericSignatures.REMOVE_AT_NAME_AND_SIGNATURE;
        }

        @NotNull
        public final Map<String, Name> getSIGNATURE_TO_JVM_REPRESENTATION_NAME() {
            return SpecialGenericSignatures.SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        }

        @NotNull
        public final List<Name> getORIGINAL_SHORT_NAMES() {
            return SpecialGenericSignatures.ORIGINAL_SHORT_NAMES;
        }

        @NotNull
        public final Map<Name, List<Name>> getJVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP() {
            return SpecialGenericSignatures.JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
        }

        @NotNull
        public final List<Name> getBuiltinFunctionNamesByJvmName(@NotNull Name name) {
            Intrinsics.checkNotNullParameter(name, "name");
            List<Name> list = getJVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP().get(name);
            return list == null ? CollectionsKt.emptyList() : list;
        }

        public final boolean getSameAsRenamedInJvmBuiltin(@NotNull Name $this$sameAsRenamedInJvmBuiltin) {
            Intrinsics.checkNotNullParameter($this$sameAsRenamedInJvmBuiltin, "<this>");
            return getORIGINAL_SHORT_NAMES().contains($this$sameAsRenamedInJvmBuiltin);
        }
    }

    static {
        Object obj;
        Iterable $this$map$iv = SetsKt.setOf((Object[]) new String[]{"containsAll", "removeAll", "retainAll"});
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            Companion companion = Companion;
            String desc = JvmPrimitiveType.BOOLEAN.getDesc();
            Intrinsics.checkNotNullExpressionValue(desc, "BOOLEAN.desc");
            destination$iv$iv.add(companion.method("java/util/Collection", it, "Ljava/util/Collection;", desc));
        }
        ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES = (List) destination$iv$iv;
        Iterable $this$map$iv2 = ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            Companion.NameAndSignature it2 = (Companion.NameAndSignature) item$iv$iv2;
            destination$iv$iv2.add(it2.getSignature());
        }
        ERASED_COLLECTION_PARAMETER_SIGNATURES = (List) destination$iv$iv2;
        Iterable $this$map$iv3 = ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv3, 10));
        for (Object item$iv$iv3 : $this$map$iv3) {
            Companion.NameAndSignature it3 = (Companion.NameAndSignature) item$iv$iv3;
            destination$iv$iv3.add(it3.getName().asString());
        }
        ERASED_COLLECTION_PARAMETER_NAMES = (List) destination$iv$iv3;
        SignatureBuildingComponents $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3 = SignatureBuildingComponents.INSTANCE;
        Companion companion2 = Companion;
        String strJavaUtil = $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Collection");
        String desc2 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc2, "BOOLEAN.desc");
        Companion companion3 = Companion;
        String strJavaUtil2 = $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Collection");
        String desc3 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc3, "BOOLEAN.desc");
        Companion companion4 = Companion;
        String strJavaUtil3 = $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Map");
        String desc4 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc4, "BOOLEAN.desc");
        Companion companion5 = Companion;
        String strJavaUtil4 = $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Map");
        String desc5 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc5, "BOOLEAN.desc");
        Companion companion6 = Companion;
        String strJavaUtil5 = $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Map");
        String desc6 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc6, "BOOLEAN.desc");
        Companion companion7 = Companion;
        String strJavaUtil6 = $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil(PDListAttributeObject.OWNER_LIST);
        String desc7 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc7, "INT.desc");
        Companion companion8 = Companion;
        String strJavaUtil7 = $this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil(PDListAttributeObject.OWNER_LIST);
        String desc8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc8, "INT.desc");
        GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP = MapsKt.mapOf(TuplesKt.to(companion2.method(strJavaUtil, "contains", "Ljava/lang/Object;", desc2), TypeSafeBarrierDescription.FALSE), TuplesKt.to(companion3.method(strJavaUtil2, "remove", "Ljava/lang/Object;", desc3), TypeSafeBarrierDescription.FALSE), TuplesKt.to(companion4.method(strJavaUtil3, "containsKey", "Ljava/lang/Object;", desc4), TypeSafeBarrierDescription.FALSE), TuplesKt.to(companion5.method(strJavaUtil4, "containsValue", "Ljava/lang/Object;", desc5), TypeSafeBarrierDescription.FALSE), TuplesKt.to(companion6.method(strJavaUtil5, "remove", "Ljava/lang/Object;Ljava/lang/Object;", desc6), TypeSafeBarrierDescription.FALSE), TuplesKt.to(Companion.method($this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Map"), "getOrDefault", "Ljava/lang/Object;Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.MAP_GET_OR_DEFAULT), TuplesKt.to(Companion.method($this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Map"), BeanUtil.PREFIX_GETTER_GET, "Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.NULL), TuplesKt.to(Companion.method($this$GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP_u24lambda_u2d3.javaUtil("Map"), "remove", "Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.NULL), TuplesKt.to(companion7.method(strJavaUtil6, "indexOf", "Ljava/lang/Object;", desc7), TypeSafeBarrierDescription.INDEX), TuplesKt.to(companion8.method(strJavaUtil7, "lastIndexOf", "Ljava/lang/Object;", desc8), TypeSafeBarrierDescription.INDEX));
        Map $this$mapKeys$iv = GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;
        Map destination$iv$iv4 = new LinkedHashMap(MapsKt.mapCapacity($this$mapKeys$iv.size()));
        Iterable $this$associateByTo$iv$iv$iv = $this$mapKeys$iv.entrySet();
        for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it4 = (Map.Entry) element$iv$iv$iv;
            Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
            destination$iv$iv4.put(((Companion.NameAndSignature) it4.getKey()).getSignature(), it$iv$iv.getValue());
        }
        SIGNATURE_TO_DEFAULT_VALUES_MAP = destination$iv$iv4;
        Iterable allMethods = SetsKt.plus((Set) GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP.keySet(), (Iterable) ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES);
        Iterable $this$map$iv4 = allMethods;
        Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv4, 10));
        for (Object item$iv$iv4 : $this$map$iv4) {
            Companion.NameAndSignature it5 = (Companion.NameAndSignature) item$iv$iv4;
            destination$iv$iv5.add(it5.getName());
        }
        ERASED_VALUE_PARAMETERS_SHORT_NAMES = CollectionsKt.toSet((List) destination$iv$iv5);
        Iterable $this$map$iv5 = allMethods;
        Collection destination$iv$iv6 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv5, 10));
        for (Object item$iv$iv5 : $this$map$iv5) {
            Companion.NameAndSignature it6 = (Companion.NameAndSignature) item$iv$iv5;
            destination$iv$iv6.add(it6.getSignature());
        }
        ERASED_VALUE_PARAMETERS_SIGNATURES = CollectionsKt.toSet((List) destination$iv$iv6);
        Companion companion9 = Companion;
        String desc9 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc9, "INT.desc");
        REMOVE_AT_NAME_AND_SIGNATURE = companion9.method("java/util/List", "removeAt", desc9, "Ljava/lang/Object;");
        SignatureBuildingComponents $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7 = SignatureBuildingComponents.INSTANCE;
        Companion companion10 = Companion;
        String strJavaLang = $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7.javaLang("Number");
        String desc10 = JvmPrimitiveType.BYTE.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc10, "BYTE.desc");
        Companion companion11 = Companion;
        String strJavaLang2 = $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7.javaLang("Number");
        String desc11 = JvmPrimitiveType.SHORT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc11, "SHORT.desc");
        Companion companion12 = Companion;
        String strJavaLang3 = $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7.javaLang("Number");
        String desc12 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc12, "INT.desc");
        Companion companion13 = Companion;
        String strJavaLang4 = $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7.javaLang("Number");
        String desc13 = JvmPrimitiveType.LONG.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc13, "LONG.desc");
        Companion companion14 = Companion;
        String strJavaLang5 = $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7.javaLang("Number");
        String desc14 = JvmPrimitiveType.FLOAT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc14, "FLOAT.desc");
        Companion companion15 = Companion;
        String strJavaLang6 = $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7.javaLang("Number");
        String desc15 = JvmPrimitiveType.DOUBLE.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc15, "DOUBLE.desc");
        Companion companion16 = Companion;
        String strJavaLang7 = $this$NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP_u24lambda_u2d7.javaLang("CharSequence");
        String desc16 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc16, "INT.desc");
        String desc17 = JvmPrimitiveType.CHAR.getDesc();
        Intrinsics.checkNotNullExpressionValue(desc17, "CHAR.desc");
        NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = MapsKt.mapOf(TuplesKt.to(companion10.method(strJavaLang, "toByte", "", desc10), Name.identifier("byteValue")), TuplesKt.to(companion11.method(strJavaLang2, "toShort", "", desc11), Name.identifier("shortValue")), TuplesKt.to(companion12.method(strJavaLang3, "toInt", "", desc12), Name.identifier("intValue")), TuplesKt.to(companion13.method(strJavaLang4, "toLong", "", desc13), Name.identifier("longValue")), TuplesKt.to(companion14.method(strJavaLang5, "toFloat", "", desc14), Name.identifier("floatValue")), TuplesKt.to(companion15.method(strJavaLang6, "toDouble", "", desc15), Name.identifier("doubleValue")), TuplesKt.to(REMOVE_AT_NAME_AND_SIGNATURE, Name.identifier("remove")), TuplesKt.to(companion16.method(strJavaLang7, BeanUtil.PREFIX_GETTER_GET, desc16, desc17), Name.identifier("charAt")));
        Map $this$mapKeys$iv2 = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
        Map destination$iv$iv7 = new LinkedHashMap(MapsKt.mapCapacity($this$mapKeys$iv2.size()));
        Iterable $this$associateByTo$iv$iv$iv2 = $this$mapKeys$iv2.entrySet();
        for (Object element$iv$iv$iv2 : $this$associateByTo$iv$iv$iv2) {
            Map.Entry it7 = (Map.Entry) element$iv$iv$iv2;
            Map.Entry it$iv$iv2 = (Map.Entry) element$iv$iv$iv2;
            destination$iv$iv7.put(((Companion.NameAndSignature) it7.getKey()).getSignature(), it$iv$iv2.getValue());
        }
        SIGNATURE_TO_JVM_REPRESENTATION_NAME = destination$iv$iv7;
        Iterable $this$map$iv6 = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        Collection destination$iv$iv8 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv6, 10));
        for (Object item$iv$iv6 : $this$map$iv6) {
            Companion.NameAndSignature it8 = (Companion.NameAndSignature) item$iv$iv6;
            destination$iv$iv8.add(it8.getName());
        }
        ORIGINAL_SHORT_NAMES = (List) destination$iv$iv8;
        Iterable $this$map$iv7 = NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        Collection destination$iv$iv9 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv7, 10));
        for (Object item$iv$iv7 : $this$map$iv7) {
            Map.Entry it9 = (Map.Entry) item$iv$iv7;
            destination$iv$iv9.add(new Pair(((Companion.NameAndSignature) it9.getKey()).getName(), it9.getValue()));
        }
        Iterable $this$groupBy$iv = (List) destination$iv$iv9;
        Map destination$iv$iv10 = new LinkedHashMap();
        for (Object element$iv$iv : $this$groupBy$iv) {
            Pair it10 = (Pair) element$iv$iv;
            Name name = (Name) it10.getSecond();
            Object value$iv$iv$iv = destination$iv$iv10.get(name);
            if (value$iv$iv$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination$iv$iv10.put(name, arrayList);
                obj = arrayList;
            } else {
                obj = value$iv$iv$iv;
            }
            List list$iv$iv = (List) obj;
            Pair it11 = (Pair) element$iv$iv;
            list$iv$iv.add((Name) it11.getFirst());
        }
        JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = destination$iv$iv10;
    }
}
