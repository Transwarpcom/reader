package io.vertx.ext.web.client.impl.predicate;

import io.vertx.ext.web.client.predicate.ErrorConverter;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/predicate/ErrorConverterImpl.class */
public class ErrorConverterImpl implements ErrorConverter {
    private final Function<ResponsePredicateResult, Throwable> converter;
    private final boolean needsBody;

    public ErrorConverterImpl(Function<ResponsePredicateResult, Throwable> converter, boolean needsBody) {
        this.converter = converter;
        this.needsBody = needsBody;
    }

    @Override // io.vertx.ext.web.client.predicate.ErrorConverter
    public boolean requiresBody() {
        return this.needsBody;
    }

    @Override // io.vertx.ext.web.client.predicate.ErrorConverter
    public Throwable apply(ResponsePredicateResult result) {
        return this.converter.apply(result);
    }
}
