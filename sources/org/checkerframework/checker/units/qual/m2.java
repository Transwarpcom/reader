package org.checkerframework.checker.units.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({Area.class})
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/units/qual/m2.class */
public @interface m2 {
    Prefix value() default Prefix.one;
}
