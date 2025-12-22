package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'UBYTE' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: UnsignedType.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/builtins/UnsignedType.class */
public final class UnsignedType {

    @NotNull
    private final ClassId classId;

    @NotNull
    private final Name typeName;

    @NotNull
    private final ClassId arrayClassId;
    public static final UnsignedType UBYTE;
    public static final UnsignedType USHORT;
    public static final UnsignedType UINT;
    public static final UnsignedType ULONG;
    private static final /* synthetic */ UnsignedType[] $VALUES;

    public static UnsignedType[] values() {
        return (UnsignedType[]) $VALUES.clone();
    }

    public static UnsignedType valueOf(String value) {
        return (UnsignedType) Enum.valueOf(UnsignedType.class, value);
    }

    private static final /* synthetic */ UnsignedType[] $values() {
        return new UnsignedType[]{UBYTE, USHORT, UINT, ULONG};
    }

    private UnsignedType(String $enum$name, int $enum$ordinal, ClassId classId) {
        this.classId = classId;
        Name shortClassName = this.classId.getShortClassName();
        Intrinsics.checkNotNullExpressionValue(shortClassName, "classId.shortClassName");
        this.typeName = shortClassName;
        this.arrayClassId = new ClassId(this.classId.getPackageFqName(), Name.identifier(Intrinsics.stringPlus(this.typeName.asString(), "Array")));
    }

    @NotNull
    public final ClassId getClassId() {
        return this.classId;
    }

    static {
        ClassId classIdFromString = ClassId.fromString("kotlin/UByte");
        Intrinsics.checkNotNullExpressionValue(classIdFromString, "fromString(\"kotlin/UByte\")");
        UBYTE = new UnsignedType("UBYTE", 0, classIdFromString);
        ClassId classIdFromString2 = ClassId.fromString("kotlin/UShort");
        Intrinsics.checkNotNullExpressionValue(classIdFromString2, "fromString(\"kotlin/UShort\")");
        USHORT = new UnsignedType("USHORT", 1, classIdFromString2);
        ClassId classIdFromString3 = ClassId.fromString("kotlin/UInt");
        Intrinsics.checkNotNullExpressionValue(classIdFromString3, "fromString(\"kotlin/UInt\")");
        UINT = new UnsignedType("UINT", 2, classIdFromString3);
        ClassId classIdFromString4 = ClassId.fromString("kotlin/ULong");
        Intrinsics.checkNotNullExpressionValue(classIdFromString4, "fromString(\"kotlin/ULong\")");
        ULONG = new UnsignedType("ULONG", 3, classIdFromString4);
        $VALUES = $values();
    }

    @NotNull
    public final Name getTypeName() {
        return this.typeName;
    }

    @NotNull
    public final ClassId getArrayClassId() {
        return this.arrayClassId;
    }
}
