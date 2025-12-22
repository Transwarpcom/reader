package io.vertx.core.cli.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/annotations/Option.class */
public @interface Option {
    public static final String NO_NAME = "��";

    String longName() default "��";

    String shortName() default "��";

    String argName() default "value";

    boolean required() default false;

    boolean acceptValue() default true;

    boolean acceptMultipleValues() default false;

    boolean flag() default false;

    boolean help() default false;

    String[] choices() default {};
}
