package org.checkerframework.common.aliasing.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({ElementType.TYPE_USE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({NonLeaked.class})
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/common/aliasing/qual/LeakedToResult.class */
public @interface LeakedToResult {
}
