package io.vertx.ext.web.handler.impl;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CSRFHandler;
import io.vertx.ext.web.handler.SessionHandler;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/CSRFHandlerImpl.class */
public class CSRFHandlerImpl implements CSRFHandler {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) CSRFHandlerImpl.class);
    private static final Base64.Encoder BASE64 = Base64.getMimeEncoder();
    private final Mac mac;
    private boolean nagHttps;
    private final Random RAND = new SecureRandom();
    private String cookieName = CSRFHandler.DEFAULT_COOKIE_NAME;
    private String cookiePath = "/";
    private String headerName = CSRFHandler.DEFAULT_HEADER_NAME;
    private String responseBody = DEFAULT_RESPONSE_BODY;
    private long timeout = SessionHandler.DEFAULT_SESSION_TIMEOUT;

    public CSRFHandlerImpl(String secret) throws InvalidKeyException {
        try {
            this.mac = Mac.getInstance("HmacSHA256");
            this.mac.init(new SecretKeySpec(secret.getBytes(), "HmacSHA256"));
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // io.vertx.ext.web.handler.CSRFHandler
    public CSRFHandler setCookieName(String cookieName) {
        this.cookieName = cookieName;
        return this;
    }

    @Override // io.vertx.ext.web.handler.CSRFHandler
    public CSRFHandler setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
        return this;
    }

    @Override // io.vertx.ext.web.handler.CSRFHandler
    public CSRFHandler setHeaderName(String headerName) {
        this.headerName = headerName;
        return this;
    }

    @Override // io.vertx.ext.web.handler.CSRFHandler
    public CSRFHandler setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    @Override // io.vertx.ext.web.handler.CSRFHandler
    public CSRFHandler setNagHttps(boolean nag) {
        this.nagHttps = nag;
        return this;
    }

    @Override // io.vertx.ext.web.handler.CSRFHandler
    public CSRFHandler setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    private String generateToken() {
        byte[] salt = new byte[32];
        this.RAND.nextBytes(salt);
        String saltPlusToken = BASE64.encodeToString(salt) + "." + Long.toString(System.currentTimeMillis());
        String signature = BASE64.encodeToString(this.mac.doFinal(saltPlusToken.getBytes()));
        return saltPlusToken + "." + signature;
    }

    private boolean validateToken(String header, Cookie cookie) {
        if (header == null || cookie == null || !header.equals(cookie.getValue())) {
            return false;
        }
        String[] tokens = header.split("\\.");
        if (tokens.length != 3) {
            return false;
        }
        String saltPlusToken = tokens[0] + "." + tokens[1];
        String signature = BASE64.encodeToString(this.mac.doFinal(saltPlusToken.getBytes()));
        if (!signature.equals(tokens[2])) {
            return false;
        }
        try {
            return System.currentTimeMillis() <= Long.parseLong(tokens[1]) + this.timeout;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected void forbidden(RoutingContext ctx) {
        if (this.responseBody != null) {
            ctx.response().setStatusCode(403).end(this.responseBody);
        } else {
            ctx.fail(new HttpStatusException(403, CSRFHandler.ERROR_MESSAGE));
        }
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext ctx) {
        String uri;
        if (this.nagHttps && (uri = ctx.request().absoluteURI()) != null && !uri.startsWith("https:")) {
            log.warn("Using session cookies without https could make you susceptible to session hijacking: " + uri);
        }
        HttpMethod method = ctx.request().method();
        switch (method) {
            case GET:
                String token = generateToken();
                ctx.put(this.headerName, token);
                ctx.addCookie(Cookie.cookie(this.cookieName, token).setPath(this.cookiePath));
                ctx.next();
                break;
            case POST:
            case PUT:
            case DELETE:
            case PATCH:
                String header = ctx.request().getHeader(this.headerName);
                Cookie cookie = ctx.getCookie(this.cookieName);
                if (validateToken(header == null ? ctx.request().getFormAttribute(this.headerName) : header, cookie)) {
                    ctx.next();
                    break;
                } else {
                    forbidden(ctx);
                    break;
                }
            default:
                ctx.next();
                break;
        }
    }
}
