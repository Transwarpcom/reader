package io.vertx.ext.web.client.predicate;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.impl.predicate.ResponsePredicateImpl;
import io.vertx.ext.web.handler.TimeoutHandler;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import okhttp3.internal.http.StatusLine;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.mozilla.classfile.ByteCode;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/predicate/ResponsePredicate.class */
public interface ResponsePredicate extends Function<HttpResponse<Void>, ResponsePredicateResult> {
    public static final ResponsePredicate SC_INFORMATIONAL_RESPONSE = status(100, 200);
    public static final ResponsePredicate SC_CONTINUE = status(100);
    public static final ResponsePredicate SC_SWITCHING_PROTOCOLS = status(101);
    public static final ResponsePredicate SC_PROCESSING = status(102);
    public static final ResponsePredicate SC_EARLY_HINTS = status(103);
    public static final ResponsePredicate SC_SUCCESS = status(200, OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT);
    public static final ResponsePredicate SC_OK = status(200);
    public static final ResponsePredicate SC_CREATED = status(ByteCode.JSR_W);
    public static final ResponsePredicate SC_ACCEPTED = status(ByteCode.BREAKPOINT);
    public static final ResponsePredicate SC_NON_AUTHORITATIVE_INFORMATION = status(203);
    public static final ResponsePredicate SC_NO_CONTENT = status(204);
    public static final ResponsePredicate SC_RESET_CONTENT = status(205);
    public static final ResponsePredicate SC_PARTIAL_CONTENT = status(206);
    public static final ResponsePredicate SC_MULTI_STATUS = status(207);
    public static final ResponsePredicate SC_REDIRECTION = status(OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT, OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
    public static final ResponsePredicate SC_MULTIPLE_CHOICES = status(OS2WindowsMetricsTable.WEIGHT_CLASS_LIGHT);
    public static final ResponsePredicate SC_MOVED_PERMANENTLY = status(301);
    public static final ResponsePredicate SC_FOUND = status(302);
    public static final ResponsePredicate SC_SEE_OTHER = status(303);
    public static final ResponsePredicate SC_NOT_MODIFIED = status(304);
    public static final ResponsePredicate SC_USE_PROXY = status(305);
    public static final ResponsePredicate SC_TEMPORARY_REDIRECT = status(StatusLine.HTTP_TEMP_REDIRECT);
    public static final ResponsePredicate SC_PERMANENT_REDIRECT = status(StatusLine.HTTP_PERM_REDIRECT);
    public static final ResponsePredicate SC_CLIENT_ERRORS = status(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL, 500);
    public static final ResponsePredicate SC_BAD_REQUEST = status(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
    public static final ResponsePredicate SC_UNAUTHORIZED = status(401);
    public static final ResponsePredicate SC_PAYMENT_REQUIRED = status(402);
    public static final ResponsePredicate SC_FORBIDDEN = status(403);
    public static final ResponsePredicate SC_NOT_FOUND = status(404);
    public static final ResponsePredicate SC_METHOD_NOT_ALLOWED = status(405);
    public static final ResponsePredicate SC_NOT_ACCEPTABLE = status(406);
    public static final ResponsePredicate SC_PROXY_AUTHENTICATION_REQUIRED = status(407);
    public static final ResponsePredicate SC_REQUEST_TIMEOUT = status(408);
    public static final ResponsePredicate SC_CONFLICT = status(409);
    public static final ResponsePredicate SC_GONE = status(410);
    public static final ResponsePredicate SC_LENGTH_REQUIRED = status(411);
    public static final ResponsePredicate SC_PRECONDITION_FAILED = status(412);
    public static final ResponsePredicate SC_REQUEST_ENTITY_TOO_LARGE = status(413);
    public static final ResponsePredicate SC_REQUEST_URI_TOO_LONG = status(414);
    public static final ResponsePredicate SC_UNSUPPORTED_MEDIA_TYPE = status(415);
    public static final ResponsePredicate SC_REQUESTED_RANGE_NOT_SATISFIABLE = status(416);
    public static final ResponsePredicate SC_EXPECTATION_FAILED = status(417);
    public static final ResponsePredicate SC_MISDIRECTED_REQUEST = status(StatusLine.HTTP_MISDIRECTED_REQUEST);
    public static final ResponsePredicate SC_UNPROCESSABLE_ENTITY = status(422);
    public static final ResponsePredicate SC_LOCKED = status(423);
    public static final ResponsePredicate SC_FAILED_DEPENDENCY = status(424);
    public static final ResponsePredicate SC_UNORDERED_COLLECTION = status(425);
    public static final ResponsePredicate SC_UPGRADE_REQUIRED = status(426);
    public static final ResponsePredicate SC_PRECONDITION_REQUIRED = status(428);
    public static final ResponsePredicate SC_TOO_MANY_REQUESTS = status(429);
    public static final ResponsePredicate SC_REQUEST_HEADER_FIELDS_TOO_LARGE = status(431);
    public static final ResponsePredicate SC_SERVER_ERRORS = status(500, OS2WindowsMetricsTable.WEIGHT_CLASS_SEMI_BOLD);
    public static final ResponsePredicate SC_INTERNAL_SERVER_ERROR = status(500);
    public static final ResponsePredicate SC_NOT_IMPLEMENTED = status(501);
    public static final ResponsePredicate SC_BAD_GATEWAY = status(502);
    public static final ResponsePredicate SC_SERVICE_UNAVAILABLE = status(TimeoutHandler.DEFAULT_ERRORCODE);
    public static final ResponsePredicate SC_GATEWAY_TIMEOUT = status(504);
    public static final ResponsePredicate SC_HTTP_VERSION_NOT_SUPPORTED = status(505);
    public static final ResponsePredicate SC_VARIANT_ALSO_NEGOTIATES = status(506);
    public static final ResponsePredicate SC_INSUFFICIENT_STORAGE = status(507);
    public static final ResponsePredicate SC_NOT_EXTENDED = status(510);
    public static final ResponsePredicate SC_NETWORK_AUTHENTICATION_REQUIRED = status(511);
    public static final ResponsePredicate JSON = contentType("application/json");

    static ResponsePredicate status(int statusCode) {
        return status(statusCode, statusCode + 1);
    }

    static ResponsePredicate status(int min, int max) {
        return response -> {
            int sc = response.statusCode();
            if (sc >= min && sc < max) {
                return ResponsePredicateResult.success();
            }
            if (max - min == 1) {
                return ResponsePredicateResult.failure("Response status code " + sc + " is not equal to " + min);
            }
            return ResponsePredicateResult.failure("Response status code " + sc + " is not between " + min + " and " + max);
        };
    }

    static ResponsePredicate contentType(String mimeType) {
        return contentType((List<String>) Collections.singletonList(mimeType));
    }

    static ResponsePredicate contentType(List<String> mimeTypes) {
        return response -> {
            String contentType = response.headers().get(HttpHeaders.CONTENT_TYPE);
            if (contentType == null) {
                return ResponsePredicateResult.failure("Missing response content type");
            }
            Iterator it = mimeTypes.iterator();
            while (it.hasNext()) {
                String mimeType = (String) it.next();
                if (contentType.equalsIgnoreCase(mimeType)) {
                    return ResponsePredicateResult.success();
                }
            }
            StringBuilder sb = new StringBuilder("Expect content type ").append(contentType).append(" to be one of ");
            boolean first = true;
            Iterator it2 = mimeTypes.iterator();
            while (it2.hasNext()) {
                String mimeType2 = (String) it2.next();
                if (!first) {
                    sb.append(", ");
                }
                first = false;
                sb.append(mimeType2);
            }
            return ResponsePredicateResult.failure(sb.toString());
        };
    }

    static ResponsePredicate create(Function<HttpResponse<Void>, ResponsePredicateResult> test) {
        test.getClass();
        return (v1) -> {
            return r0.apply(v1);
        };
    }

    static ResponsePredicate create(Function<HttpResponse<Void>, ResponsePredicateResult> test, ErrorConverter errorConverter) {
        return new ResponsePredicateImpl(test, errorConverter);
    }

    default ErrorConverter errorConverter() {
        return ErrorConverter.DEFAULT_CONVERTER;
    }
}
