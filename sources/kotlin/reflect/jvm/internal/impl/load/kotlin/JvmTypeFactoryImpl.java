package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.NoWhenBranchMatchedException;
import kotlin._Assertions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: methodSignatureMapping.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/JvmTypeFactoryImpl.class */
final class JvmTypeFactoryImpl implements JvmTypeFactory<JvmType> {

    @NotNull
    public static final JvmTypeFactoryImpl INSTANCE = new JvmTypeFactoryImpl();

    /* compiled from: methodSignatureMapping.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/load/kotlin/JvmTypeFactoryImpl$WhenMappings.class */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PrimitiveType.values().length];
            iArr[PrimitiveType.BOOLEAN.ordinal()] = 1;
            iArr[PrimitiveType.CHAR.ordinal()] = 2;
            iArr[PrimitiveType.BYTE.ordinal()] = 3;
            iArr[PrimitiveType.SHORT.ordinal()] = 4;
            iArr[PrimitiveType.INT.ordinal()] = 5;
            iArr[PrimitiveType.FLOAT.ordinal()] = 6;
            iArr[PrimitiveType.LONG.ordinal()] = 7;
            iArr[PrimitiveType.DOUBLE.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private JvmTypeFactoryImpl() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType boxType(@NotNull JvmType possiblyPrimitiveType) {
        Intrinsics.checkNotNullParameter(possiblyPrimitiveType, "possiblyPrimitiveType");
        if ((possiblyPrimitiveType instanceof JvmType.Primitive) && ((JvmType.Primitive) possiblyPrimitiveType).getJvmPrimitiveType() != null) {
            String internalName = JvmClassName.byFqNameWithoutInnerClasses(((JvmType.Primitive) possiblyPrimitiveType).getJvmPrimitiveType().getWrapperFqName()).getInternalName();
            Intrinsics.checkNotNullExpressionValue(internalName, "byFqNameWithoutInnerClasses(possiblyPrimitiveType.jvmPrimitiveType.wrapperFqName).internalName");
            return createObjectType2(internalName);
        }
        return possiblyPrimitiveType;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType createFromString(@NotNull String representation) {
        JvmPrimitiveType jvmPrimitiveType;
        Intrinsics.checkNotNullParameter(representation, "representation");
        boolean z = representation.length() > 0;
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("empty string as JvmType");
        }
        char firstChar = representation.charAt(0);
        JvmPrimitiveType[] jvmPrimitiveTypeArrValues = JvmPrimitiveType.values();
        int length = jvmPrimitiveTypeArrValues.length;
        int i = 0;
        while (true) {
            if (i < length) {
                JvmPrimitiveType jvmPrimitiveType2 = jvmPrimitiveTypeArrValues[i];
                if (jvmPrimitiveType2.getDesc().charAt(0) == firstChar) {
                    jvmPrimitiveType = jvmPrimitiveType2;
                    break;
                }
                i++;
            } else {
                jvmPrimitiveType = null;
                break;
            }
        }
        JvmPrimitiveType it = jvmPrimitiveType;
        if (it != null) {
            return new JvmType.Primitive(it);
        }
        if (firstChar == 'V') {
            return new JvmType.Primitive(null);
        }
        if (firstChar == '[') {
            String strSubstring = representation.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return new JvmType.Array(createFromString(strSubstring));
        }
        boolean z2 = firstChar == 'L' && StringsKt.endsWith$default((CharSequence) representation, ';', false, 2, (Object) null);
        if (_Assertions.ENABLED && !z2) {
            throw new AssertionError("Type that is not primitive nor array should be Object, but '" + representation + "' was found");
        }
        String strSubstring2 = representation.substring(1, representation.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return new JvmType.Object(strSubstring2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType createPrimitiveType(@NotNull PrimitiveType primitiveType) {
        Intrinsics.checkNotNullParameter(primitiveType, "primitiveType");
        switch (WhenMappings.$EnumSwitchMapping$0[primitiveType.ordinal()]) {
            case 1:
                return JvmType.Companion.getBOOLEAN$descriptors_jvm();
            case 2:
                return JvmType.Companion.getCHAR$descriptors_jvm();
            case 3:
                return JvmType.Companion.getBYTE$descriptors_jvm();
            case 4:
                return JvmType.Companion.getSHORT$descriptors_jvm();
            case 5:
                return JvmType.Companion.getINT$descriptors_jvm();
            case 6:
                return JvmType.Companion.getFLOAT$descriptors_jvm();
            case 7:
                return JvmType.Companion.getLONG$descriptors_jvm();
            case 8:
                return JvmType.Companion.getDOUBLE$descriptors_jvm();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    /* renamed from: createObjectType, reason: merged with bridge method [inline-methods] */
    public JvmType createObjectType2(@NotNull String internalName) {
        Intrinsics.checkNotNullParameter(internalName, "internalName");
        return new JvmType.Object(internalName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public String toString(@NotNull JvmType type) {
        String str;
        Intrinsics.checkNotNullParameter(type, "type");
        if (type instanceof JvmType.Array) {
            return Intrinsics.stringPlus("[", toString(((JvmType.Array) type).getElementType()));
        }
        if (!(type instanceof JvmType.Primitive)) {
            if (type instanceof JvmType.Object) {
                return 'L' + ((JvmType.Object) type).getInternalName() + ';';
            }
            throw new NoWhenBranchMatchedException();
        }
        JvmPrimitiveType jvmPrimitiveType = ((JvmType.Primitive) type).getJvmPrimitiveType();
        if (jvmPrimitiveType == null) {
            str = "V";
        } else {
            String desc = jvmPrimitiveType.getDesc();
            str = desc == null ? "V" : desc;
        }
        return str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    @NotNull
    public JvmType getJavaLangClassType() {
        return createObjectType2("java/lang/Class");
    }
}
