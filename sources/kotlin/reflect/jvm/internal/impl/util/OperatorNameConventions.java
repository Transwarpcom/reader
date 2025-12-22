package kotlin.reflect.jvm.internal.impl.util;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;

/* compiled from: OperatorNameConventions.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/OperatorNameConventions.class */
public final class OperatorNameConventions {

    @NotNull
    public static final OperatorNameConventions INSTANCE = new OperatorNameConventions();

    @JvmField
    @NotNull
    public static final Name GET_VALUE;

    @JvmField
    @NotNull
    public static final Name SET_VALUE;

    @JvmField
    @NotNull
    public static final Name PROVIDE_DELEGATE;

    @JvmField
    @NotNull
    public static final Name EQUALS;

    @JvmField
    @NotNull
    public static final Name COMPARE_TO;

    @JvmField
    @NotNull
    public static final Name CONTAINS;

    @JvmField
    @NotNull
    public static final Name INVOKE;

    @JvmField
    @NotNull
    public static final Name ITERATOR;

    @JvmField
    @NotNull
    public static final Name GET;

    @JvmField
    @NotNull
    public static final Name SET;

    @JvmField
    @NotNull
    public static final Name NEXT;

    @JvmField
    @NotNull
    public static final Name HAS_NEXT;

    @JvmField
    @NotNull
    public static final Name TO_STRING;

    @JvmField
    @NotNull
    public static final Regex COMPONENT_REGEX;

    @JvmField
    @NotNull
    public static final Name AND;

    @JvmField
    @NotNull
    public static final Name OR;

    @JvmField
    @NotNull
    public static final Name XOR;

    @JvmField
    @NotNull
    public static final Name INV;

    @JvmField
    @NotNull
    public static final Name SHL;

    @JvmField
    @NotNull
    public static final Name SHR;

    @JvmField
    @NotNull
    public static final Name USHR;

    @JvmField
    @NotNull
    public static final Name INC;

    @JvmField
    @NotNull
    public static final Name DEC;

    @JvmField
    @NotNull
    public static final Name PLUS;

    @JvmField
    @NotNull
    public static final Name MINUS;

    @JvmField
    @NotNull
    public static final Name NOT;

    @JvmField
    @NotNull
    public static final Name UNARY_MINUS;

    @JvmField
    @NotNull
    public static final Name UNARY_PLUS;

    @JvmField
    @NotNull
    public static final Name TIMES;

    @JvmField
    @NotNull
    public static final Name DIV;

    @JvmField
    @NotNull
    public static final Name MOD;

    @JvmField
    @NotNull
    public static final Name REM;

    @JvmField
    @NotNull
    public static final Name RANGE_TO;

    @JvmField
    @NotNull
    public static final Name TIMES_ASSIGN;

    @JvmField
    @NotNull
    public static final Name DIV_ASSIGN;

    @JvmField
    @NotNull
    public static final Name MOD_ASSIGN;

    @JvmField
    @NotNull
    public static final Name REM_ASSIGN;

    @JvmField
    @NotNull
    public static final Name PLUS_ASSIGN;

    @JvmField
    @NotNull
    public static final Name MINUS_ASSIGN;

    @JvmField
    @NotNull
    public static final Set<Name> UNARY_OPERATION_NAMES;

    @JvmField
    @NotNull
    public static final Set<Name> SIMPLE_UNARY_OPERATION_NAMES;

    @JvmField
    @NotNull
    public static final Set<Name> BINARY_OPERATION_NAMES;

    @JvmField
    @NotNull
    public static final Set<Name> ASSIGNMENT_OPERATIONS;

    @JvmField
    @NotNull
    public static final Set<Name> DELEGATED_PROPERTY_OPERATORS;

    private OperatorNameConventions() {
    }

    static {
        Name nameIdentifier = Name.identifier("getValue");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "identifier(\"getValue\")");
        GET_VALUE = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("setValue");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "identifier(\"setValue\")");
        SET_VALUE = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("provideDelegate");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier3, "identifier(\"provideDelegate\")");
        PROVIDE_DELEGATE = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("equals");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier4, "identifier(\"equals\")");
        EQUALS = nameIdentifier4;
        Name nameIdentifier5 = Name.identifier("compareTo");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier5, "identifier(\"compareTo\")");
        COMPARE_TO = nameIdentifier5;
        Name nameIdentifier6 = Name.identifier("contains");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier6, "identifier(\"contains\")");
        CONTAINS = nameIdentifier6;
        Name nameIdentifier7 = Name.identifier("invoke");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier7, "identifier(\"invoke\")");
        INVOKE = nameIdentifier7;
        Name nameIdentifier8 = Name.identifier("iterator");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier8, "identifier(\"iterator\")");
        ITERATOR = nameIdentifier8;
        Name nameIdentifier9 = Name.identifier(BeanUtil.PREFIX_GETTER_GET);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier9, "identifier(\"get\")");
        GET = nameIdentifier9;
        Name nameIdentifier10 = Name.identifier("set");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier10, "identifier(\"set\")");
        SET = nameIdentifier10;
        Name nameIdentifier11 = Name.identifier("next");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier11, "identifier(\"next\")");
        NEXT = nameIdentifier11;
        Name nameIdentifier12 = Name.identifier("hasNext");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier12, "identifier(\"hasNext\")");
        HAS_NEXT = nameIdentifier12;
        Name nameIdentifier13 = Name.identifier("toString");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier13, "identifier(\"toString\")");
        TO_STRING = nameIdentifier13;
        COMPONENT_REGEX = new Regex("component\\d+");
        Name nameIdentifier14 = Name.identifier("and");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier14, "identifier(\"and\")");
        AND = nameIdentifier14;
        Name nameIdentifier15 = Name.identifier("or");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier15, "identifier(\"or\")");
        OR = nameIdentifier15;
        Name nameIdentifier16 = Name.identifier("xor");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier16, "identifier(\"xor\")");
        XOR = nameIdentifier16;
        Name nameIdentifier17 = Name.identifier("inv");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier17, "identifier(\"inv\")");
        INV = nameIdentifier17;
        Name nameIdentifier18 = Name.identifier("shl");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier18, "identifier(\"shl\")");
        SHL = nameIdentifier18;
        Name nameIdentifier19 = Name.identifier("shr");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier19, "identifier(\"shr\")");
        SHR = nameIdentifier19;
        Name nameIdentifier20 = Name.identifier("ushr");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier20, "identifier(\"ushr\")");
        USHR = nameIdentifier20;
        Name nameIdentifier21 = Name.identifier("inc");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier21, "identifier(\"inc\")");
        INC = nameIdentifier21;
        Name nameIdentifier22 = Name.identifier("dec");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier22, "identifier(\"dec\")");
        DEC = nameIdentifier22;
        Name nameIdentifier23 = Name.identifier("plus");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier23, "identifier(\"plus\")");
        PLUS = nameIdentifier23;
        Name nameIdentifier24 = Name.identifier("minus");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier24, "identifier(\"minus\")");
        MINUS = nameIdentifier24;
        Name nameIdentifier25 = Name.identifier("not");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier25, "identifier(\"not\")");
        NOT = nameIdentifier25;
        Name nameIdentifier26 = Name.identifier("unaryMinus");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier26, "identifier(\"unaryMinus\")");
        UNARY_MINUS = nameIdentifier26;
        Name nameIdentifier27 = Name.identifier("unaryPlus");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier27, "identifier(\"unaryPlus\")");
        UNARY_PLUS = nameIdentifier27;
        Name nameIdentifier28 = Name.identifier("times");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier28, "identifier(\"times\")");
        TIMES = nameIdentifier28;
        Name nameIdentifier29 = Name.identifier("div");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier29, "identifier(\"div\")");
        DIV = nameIdentifier29;
        Name nameIdentifier30 = Name.identifier("mod");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier30, "identifier(\"mod\")");
        MOD = nameIdentifier30;
        Name nameIdentifier31 = Name.identifier("rem");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier31, "identifier(\"rem\")");
        REM = nameIdentifier31;
        Name nameIdentifier32 = Name.identifier("rangeTo");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier32, "identifier(\"rangeTo\")");
        RANGE_TO = nameIdentifier32;
        Name nameIdentifier33 = Name.identifier("timesAssign");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier33, "identifier(\"timesAssign\")");
        TIMES_ASSIGN = nameIdentifier33;
        Name nameIdentifier34 = Name.identifier("divAssign");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier34, "identifier(\"divAssign\")");
        DIV_ASSIGN = nameIdentifier34;
        Name nameIdentifier35 = Name.identifier("modAssign");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier35, "identifier(\"modAssign\")");
        MOD_ASSIGN = nameIdentifier35;
        Name nameIdentifier36 = Name.identifier("remAssign");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier36, "identifier(\"remAssign\")");
        REM_ASSIGN = nameIdentifier36;
        Name nameIdentifier37 = Name.identifier("plusAssign");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier37, "identifier(\"plusAssign\")");
        PLUS_ASSIGN = nameIdentifier37;
        Name nameIdentifier38 = Name.identifier("minusAssign");
        Intrinsics.checkNotNullExpressionValue(nameIdentifier38, "identifier(\"minusAssign\")");
        MINUS_ASSIGN = nameIdentifier38;
        UNARY_OPERATION_NAMES = SetsKt.setOf((Object[]) new Name[]{INC, DEC, UNARY_PLUS, UNARY_MINUS, NOT});
        SIMPLE_UNARY_OPERATION_NAMES = SetsKt.setOf((Object[]) new Name[]{UNARY_PLUS, UNARY_MINUS, NOT});
        BINARY_OPERATION_NAMES = SetsKt.setOf((Object[]) new Name[]{TIMES, PLUS, MINUS, DIV, MOD, REM, RANGE_TO});
        ASSIGNMENT_OPERATIONS = SetsKt.setOf((Object[]) new Name[]{TIMES_ASSIGN, DIV_ASSIGN, MOD_ASSIGN, REM_ASSIGN, PLUS_ASSIGN, MINUS_ASSIGN});
        DELEGATED_PROPERTY_OPERATORS = SetsKt.setOf((Object[]) new Name[]{GET_VALUE, SET_VALUE, PROVIDE_DELEGATE});
    }
}
