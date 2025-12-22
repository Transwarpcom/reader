package io.vertx.ext.web.client.predicate;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.impl.NoStackTraceThrowable;
import io.vertx.ext.web.client.impl.predicate.ErrorConverterImpl;
import java.util.function.Function;

@FunctionalInterface
@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/predicate/ErrorConverter.class */
public interface ErrorConverter {
    public static final ErrorConverter DEFAULT_CONVERTER = result -> {
        String message = result.message();
        if (message == null) {
            return null;
        }
        return new NoStackTraceThrowable(message);
    };

    Throwable apply(ResponsePredicateResult responsePredicateResult);

    static ErrorConverter create(Function<ResponsePredicateResult, Throwable> converter) {
        converter.getClass();
        return (v1) -> {
            return r0.apply(v1);
        };
    }

    static ErrorConverter createFullBody(Function<ResponsePredicateResult, Throwable> converter) {
        return new ErrorConverterImpl(converter, true);
    }

    default boolean requiresBody() {
        return false;
    }
}
