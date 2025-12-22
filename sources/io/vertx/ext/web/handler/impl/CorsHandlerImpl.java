package io.vertx.ext.web.handler.impl;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/CorsHandlerImpl.class */
public class CorsHandlerImpl implements CorsHandler {
    private final Pattern allowedOrigin;
    private String allowedMethodsString;
    private String allowedHeadersString;
    private String exposedHeadersString;
    private boolean allowCredentials;
    private String maxAgeSeconds;
    private final Set<HttpMethod> allowedMethods = new LinkedHashSet();
    private final Set<String> allowedHeaders = new LinkedHashSet();
    private final Set<String> exposedHeaders = new LinkedHashSet();

    public CorsHandlerImpl(String allowedOriginPattern) {
        Objects.requireNonNull(allowedOriginPattern);
        if ("*".equals(allowedOriginPattern)) {
            this.allowedOrigin = null;
        } else {
            this.allowedOrigin = Pattern.compile(allowedOriginPattern);
        }
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler allowedMethod(HttpMethod method) {
        this.allowedMethods.add(method);
        this.allowedMethodsString = join(this.allowedMethods);
        return this;
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler allowedMethods(Set<HttpMethod> methods) {
        this.allowedMethods.addAll(methods);
        this.allowedMethodsString = join(this.allowedMethods);
        return this;
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler allowedHeader(String headerName) {
        this.allowedHeaders.add(headerName);
        this.allowedHeadersString = join(this.allowedHeaders);
        return this;
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler allowedHeaders(Set<String> headerNames) {
        this.allowedHeaders.addAll(headerNames);
        this.allowedHeadersString = join(this.allowedHeaders);
        return this;
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler exposedHeader(String headerName) {
        this.exposedHeaders.add(headerName);
        this.exposedHeadersString = join(this.exposedHeaders);
        return this;
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler exposedHeaders(Set<String> headerNames) {
        this.exposedHeaders.addAll(headerNames);
        this.exposedHeadersString = join(this.exposedHeaders);
        return this;
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler allowCredentials(boolean allow) {
        if (this.allowedOrigin == null && allow) {
            throw new IllegalStateException("wildcard origin with credentials is not allowed");
        }
        this.allowCredentials = allow;
        return this;
    }

    @Override // io.vertx.ext.web.handler.CorsHandler
    public CorsHandler maxAgeSeconds(int maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds == -1 ? null : String.valueOf(maxAgeSeconds);
        return this;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        HttpServerRequest request = context.request();
        HttpServerResponse response = context.response();
        String origin = context.request().headers().get(HttpHeaders.ORIGIN);
        if (origin == null) {
            context.next();
            return;
        }
        if (isValidOrigin(origin)) {
            String accessControlRequestMethod = request.headers().get(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD);
            if (request.method() == HttpMethod.OPTIONS && accessControlRequestMethod != null) {
                addCredentialsAndOriginHeader(response, origin);
                if (this.allowedMethodsString != null) {
                    response.putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, this.allowedMethodsString);
                }
                if (this.allowedHeadersString != null) {
                    response.putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, this.allowedHeadersString);
                }
                if (this.maxAgeSeconds != null) {
                    response.putHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, this.maxAgeSeconds);
                }
                response.setStatusCode(200).end();
                return;
            }
            addCredentialsAndOriginHeader(response, origin);
            if (this.exposedHeadersString != null) {
                response.putHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, this.exposedHeadersString);
            }
            context.next();
            return;
        }
        sendInvalid(request.response());
    }

    private void addCredentialsAndOriginHeader(HttpServerResponse response, String origin) {
        if (this.allowCredentials) {
            response.putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            response.putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        } else {
            response.putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, getAllowedOrigin(origin));
        }
    }

    private void sendInvalid(HttpServerResponse resp) {
        resp.setStatusCode(403).setStatusMessage("CORS Rejected - Invalid origin").end();
    }

    private boolean isValidOrigin(String origin) {
        if (this.allowedOrigin == null) {
            return true;
        }
        return this.allowedOrigin.matcher(origin).matches();
    }

    private String getAllowedOrigin(String origin) {
        return this.allowedOrigin == null ? "*" : origin;
    }

    private String join(Collection<?> ss) {
        if (ss == null || ss.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object s : ss) {
            if (!first) {
                sb.append(',');
            }
            sb.append(s);
            first = false;
        }
        return sb.toString();
    }
}
