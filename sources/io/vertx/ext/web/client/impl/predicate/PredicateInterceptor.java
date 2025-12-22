package io.vertx.ext.web.client.impl.predicate;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.impl.NoStackTraceThrowable;
import io.vertx.ext.web.client.impl.ClientPhase;
import io.vertx.ext.web.client.impl.HttpContext;
import io.vertx.ext.web.client.impl.HttpRequestImpl;
import io.vertx.ext.web.client.impl.HttpResponseImpl;
import io.vertx.ext.web.client.predicate.ErrorConverter;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/predicate/PredicateInterceptor.class */
public class PredicateInterceptor implements Handler<HttpContext<?>> {
    @Override // io.vertx.core.Handler
    public void handle(HttpContext<?> httpContext) {
        if (httpContext.phase() == ClientPhase.RECEIVE_RESPONSE) {
            HttpRequestImpl request = (HttpRequestImpl) httpContext.request();
            HttpClientResponse resp = httpContext.clientResponse();
            List<ResponsePredicate> expectations = request.expectations;
            if (expectations != null) {
                for (ResponsePredicate expectation : expectations) {
                    try {
                        ResponsePredicateResultImpl predicateResult = (ResponsePredicateResultImpl) expectation.apply(responseCopy(resp, httpContext, null));
                        if (!predicateResult.succeeded()) {
                            ErrorConverter errorConverter = expectation.errorConverter();
                            if (!errorConverter.requiresBody()) {
                                failOnPredicate(httpContext, errorConverter, predicateResult);
                                return;
                            } else {
                                resp.bodyHandler(buffer -> {
                                    predicateResult.setHttpResponse(responseCopy(resp, httpContext, buffer));
                                    failOnPredicate(httpContext, errorConverter, predicateResult);
                                });
                                resp.resume2();
                                return;
                            }
                        }
                    } catch (Exception e) {
                        httpContext.fail(e);
                        return;
                    }
                }
            }
        }
        httpContext.next();
    }

    private <B> HttpResponseImpl<B> responseCopy(HttpClientResponse resp, HttpContext<?> httpContext, B value) {
        return new HttpResponseImpl<>(resp.version(), resp.statusCode(), resp.statusMessage(), MultiMap.caseInsensitiveMultiMap().addAll(resp.headers()), null, new ArrayList(resp.cookies()), value, httpContext.getRedirectedLocations());
    }

    private void failOnPredicate(HttpContext<?> ctx, ErrorConverter converter, ResponsePredicateResultImpl predicateResult) {
        Throwable result;
        try {
            result = converter.apply(predicateResult);
        } catch (Exception e) {
            result = e;
        }
        if (result != null) {
            ctx.fail(result);
        } else {
            ctx.fail(new NoStackTraceThrowable("Invalid HTTP response"));
        }
    }
}
