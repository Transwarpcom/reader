package org.checkerframework.checker.lock.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultInUncheckedCodeFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchyInUncheckedCode;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

@Target({})
@DefaultQualifierInHierarchyInUncheckedCode
@SubtypeOf({})
@Retention(RetentionPolicy.RUNTIME)
@InvisibleQualifier
@DefaultFor({TypeUseLocation.LOWER_BOUND})
@Documented
@DefaultQualifierInHierarchy
@DefaultInUncheckedCodeFor({TypeUseLocation.PARAMETER, TypeUseLocation.LOWER_BOUND})
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/lock/qual/LockPossiblyHeld.class */
public @interface LockPossiblyHeld {
}
