package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ImplicitFor;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@TargetLocations({TypeUseLocation.EXPLICIT_LOWER_BOUND, TypeUseLocation.EXPLICIT_UPPER_BOUND})
@ImplicitFor(literals = {LiteralKind.NULL}, typeNames = {Void.class})
@InvisibleQualifier
@SubtypeOf({ArrayLen.class, BoolVal.class, DoubleVal.class, IntVal.class, StringVal.class, ArrayLenRange.class, IntRange.class, IntRangeFromPositive.class, IntRangeFromGTENegativeOne.class, IntRangeFromNonNegative.class})
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/common/value/qual/BottomVal.class */
public @interface BottomVal {
}
