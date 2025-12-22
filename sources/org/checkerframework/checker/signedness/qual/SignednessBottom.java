package org.checkerframework.checker.signedness.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ImplicitFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeKind;
import org.checkerframework.framework.qual.TypeUseLocation;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@TargetLocations({TypeUseLocation.EXPLICIT_LOWER_BOUND, TypeUseLocation.EXPLICIT_UPPER_BOUND})
@ImplicitFor(literals = {LiteralKind.NULL}, types = {TypeKind.NULL})
@SubtypeOf({Constant.class})
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/signedness/qual/SignednessBottom.class */
public @interface SignednessBottom {
}
