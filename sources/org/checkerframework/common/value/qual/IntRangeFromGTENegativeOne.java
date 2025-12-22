package org.checkerframework.common.value.qual;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({})
@SubtypeOf({UnknownVal.class})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/common/value/qual/IntRangeFromGTENegativeOne.class */
public @interface IntRangeFromGTENegativeOne {
}
