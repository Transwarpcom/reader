package org.checkerframework.checker.units.qual;

import java.lang.annotation.Annotation;

/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/units/qual/UnitsMultiple.class */
public @interface UnitsMultiple {
    Class<? extends Annotation> quantity();

    Prefix prefix() default Prefix.one;
}
