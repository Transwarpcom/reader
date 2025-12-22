package org.checkerframework.checker.i18n.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ImplicitFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@ImplicitFor(literals = {LiteralKind.INT, LiteralKind.LONG, LiteralKind.FLOAT, LiteralKind.DOUBLE, LiteralKind.BOOLEAN, LiteralKind.NULL})
@SubtypeOf({UnknownLocalized.class})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/i18n/qual/Localized.class */
public @interface Localized {
}
