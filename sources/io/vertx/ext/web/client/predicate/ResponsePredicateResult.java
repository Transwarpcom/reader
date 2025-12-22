package io.vertx.ext.web.client.predicate;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.impl.predicate.ResponsePredicateResultImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/predicate/ResponsePredicateResult.class */
public interface ResponsePredicateResult {
    boolean succeeded();

    String message();

    HttpResponse<Buffer> response();

    static ResponsePredicateResult success() {
        return ResponsePredicateResultImpl.SUCCESS;
    }

    static ResponsePredicateResult failure(String message) {
        return new ResponsePredicateResultImpl(false, message);
    }
}
