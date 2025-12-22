package org.checkerframework.checker.nullness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.JavaExpression;
import org.checkerframework.framework.qual.QualifierArgument;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@InheritedAnnotation
@ConditionalPostconditionAnnotation(qualifier = KeyFor.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: reader.jar:BOOT-INF/lib/checker-qual-2.8.1.jar:org/checkerframework/checker/nullness/qual/EnsuresKeyForIf.class */
public @interface EnsuresKeyForIf {
    boolean result();

    String[] expression();

    @QualifierArgument("value")
    @JavaExpression
    String[] map();
}
