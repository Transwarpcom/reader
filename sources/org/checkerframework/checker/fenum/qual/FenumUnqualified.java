package org.checkerframework.checker.fenum.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@Target({})
@DefaultFor({TypeUseLocation.EXCEPTION_PARAMETER})
@SubtypeOf({FenumTop.class})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@DefaultQualifierInHierarchy
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/fenum/qual/FenumUnqualified.class */
public @interface FenumUnqualified {
}
