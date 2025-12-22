package com.fasterxml.jackson.module.kotlin;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UnsignedNumbers.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��,\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\n\n��\n\u0002\u0018\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n��\u001a\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\u00020\u0004ø\u0001��\u001a\u000f\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u0007ø\u0001��\u001a\u000f\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u0001ø\u0001��\u001a\u000f\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\fø\u0001��\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"uLongMaxValue", "Ljava/math/BigInteger;", "asUByte", "Lkotlin/UByte;", "", "asUInt", "Lkotlin/UInt;", "", "asULong", "Lkotlin/ULong;", "asUShort", "Lkotlin/UShort;", "", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/UnsignedNumbersKt.class */
public final class UnsignedNumbersKt {

    @NotNull
    private static final BigInteger uLongMaxValue = new BigInteger(Long.toUnsignedString(-1));

    @Nullable
    public static final UByte asUByte(short $this$asUByte) {
        if ($this$asUByte < 0 || $this$asUByte > ((short) (((short) (-1)) & 255))) {
            return null;
        }
        return UByte.m2166boximpl(UByte.m2165constructorimpl((byte) $this$asUByte));
    }

    @Nullable
    public static final UShort asUShort(int $this$asUShort) {
        if ($this$asUShort < 0 || $this$asUShort > ((-1) & 65535)) {
            return null;
        }
        return UShort.m2430boximpl(UShort.m2429constructorimpl((short) $this$asUShort));
    }

    @Nullable
    public static final UInt asUInt(long $this$asUInt) {
        if ($this$asUInt < 0 || $this$asUInt > ((-1) & 4294967295L)) {
            return null;
        }
        return UInt.m2245boximpl(UInt.m2244constructorimpl((int) $this$asUInt));
    }

    @Nullable
    public static final ULong asULong(@NotNull BigInteger $this$asULong) {
        Intrinsics.checkNotNullParameter($this$asULong, "<this>");
        if ($this$asULong.compareTo(BigInteger.ZERO) < 0 || $this$asULong.compareTo(uLongMaxValue) > 0) {
            return null;
        }
        return ULong.m2324boximpl(ULong.m2323constructorimpl($this$asULong.longValue()));
    }
}
