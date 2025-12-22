package org.checkerframework.checker.regex.qual;

import java.lang.annotation.Target;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({})
@InvisibleQualifier
@SubtypeOf({UnknownRegex.class})
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/regex/qual/PartialRegex.class */
public @interface PartialRegex {
    String value() default "";
}
