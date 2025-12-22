package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.JavaExpression;

@Target({ElementType.FIELD})
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/index/qual/HasSubsequence.class */
public @interface HasSubsequence {
    @JavaExpression
    String subsequence();

    @JavaExpression
    String from();

    @JavaExpression
    String to();
}
