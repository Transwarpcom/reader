package io.vertx.ext.web.client.impl.predicate;

import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.predicate.ErrorConverter;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/predicate/ResponsePredicateImpl.class */
public class ResponsePredicateImpl implements ResponsePredicate {
    private final Function<HttpResponse<Void>, ResponsePredicateResult> predicate;
    private final ErrorConverter errorConverter;

    public ResponsePredicateImpl(Function<HttpResponse<Void>, ResponsePredicateResult> predicate, ErrorConverter errorConverter) {
        this.predicate = predicate;
        this.errorConverter = errorConverter;
    }

    @Override // java.util.function.Function
    public ResponsePredicateResult apply(HttpResponse<Void> response) {
        return this.predicate.apply(response);
    }

    @Override // io.vertx.ext.web.client.predicate.ResponsePredicate
    public ErrorConverter errorConverter() {
        return this.errorConverter;
    }
}
