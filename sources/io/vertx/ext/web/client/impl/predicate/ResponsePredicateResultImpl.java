package io.vertx.ext.web.client.impl.predicate;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/predicate/ResponsePredicateResultImpl.class */
public class ResponsePredicateResultImpl implements ResponsePredicateResult {
    public static final ResponsePredicateResultImpl SUCCESS = new ResponsePredicateResultImpl(true, null);
    private final boolean passed;
    private final String message;
    private HttpResponse<Buffer> httpResponse;

    public ResponsePredicateResultImpl(boolean passed, String message) {
        this.passed = passed;
        this.message = message;
    }

    @Override // io.vertx.ext.web.client.predicate.ResponsePredicateResult
    public boolean succeeded() {
        return this.passed;
    }

    @Override // io.vertx.ext.web.client.predicate.ResponsePredicateResult
    public String message() {
        return this.message;
    }

    @Override // io.vertx.ext.web.client.predicate.ResponsePredicateResult
    public HttpResponse<Buffer> response() {
        return this.httpResponse;
    }

    public ResponsePredicateResultImpl setHttpResponse(HttpResponse<Buffer> httpResponse) {
        this.httpResponse = httpResponse;
        return this;
    }
}
