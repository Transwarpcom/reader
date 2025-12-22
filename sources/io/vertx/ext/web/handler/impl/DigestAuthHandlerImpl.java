package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.htdigest.HtdigestAuth;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.DigestAuthHandler;
import io.vertx.ext.web.handler.impl.AuthorizationAuthHandler;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/DigestAuthHandlerImpl.class */
public class DigestAuthHandlerImpl extends AuthorizationAuthHandler implements DigestAuthHandler {
    private static final Pattern PARSER = Pattern.compile("(\\w+)=[\"]?([^\"]*)[\"]?$");
    private static final Pattern SPLITTER = Pattern.compile(",(?=(?:[^\"]|\"[^\"]*\")*$)");
    private static final MessageDigest MD5;
    private final SecureRandom random;
    private final Map<String, Nonce> nonces;
    private final long nonceExpireTimeout;
    private long lastExpireRun;
    private static final char[] hexArray;

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/DigestAuthHandlerImpl$Nonce.class */
    private static class Nonce {
        private final long createdAt = System.currentTimeMillis();
        private int count = 0;

        Nonce() {
        }
    }

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
            hexArray = "0123456789abcdef".toCharArray();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public DigestAuthHandlerImpl(HtdigestAuth authProvider, long nonceExpireTimeout) {
        super(authProvider, authProvider.realm(), AuthorizationAuthHandler.Type.DIGEST);
        this.random = new SecureRandom();
        this.nonces = new HashMap();
        this.nonceExpireTimeout = nonceExpireTimeout;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
        long now = System.currentTimeMillis();
        if (now - this.lastExpireRun > this.nonceExpireTimeout / 2) {
            this.nonces.entrySet().removeIf(entry -> {
                return ((Nonce) entry.getValue()).createdAt + this.nonceExpireTimeout < now;
            });
            this.lastExpireRun = now;
        }
        parseAuthorization(context, false, parseAuthorization -> {
            String opaque;
            String nonce;
            if (parseAuthorization.failed()) {
                handler.handle(Future.failedFuture(parseAuthorization.cause()));
                return;
            }
            JsonObject authInfo = new JsonObject();
            try {
                String[] tokens = SPLITTER.split((CharSequence) parseAuthorization.result());
                for (String str : tokens) {
                    Matcher m = PARSER.matcher(str);
                    if (m.find()) {
                        authInfo.put(m.group(1), m.group(2));
                    }
                }
                nonce = authInfo.getString("nonce");
            } catch (RuntimeException e) {
                handler.handle(Future.failedFuture(e));
            }
            if (!this.nonces.containsKey(nonce)) {
                handler.handle(Future.failedFuture(UNAUTHORIZED));
                return;
            }
            if (authInfo.containsKey("qop")) {
                int nc = Integer.parseInt(authInfo.getString("nc"));
                Nonce n = this.nonces.get(nonce);
                if (nc > n.count) {
                    n.count = nc;
                } else {
                    handler.handle(Future.failedFuture(UNAUTHORIZED));
                    return;
                }
            }
            Session session = context.session();
            if (session != null && (opaque = (String) session.data().get("opaque")) != null && !opaque.equals(authInfo.getString("opaque"))) {
                handler.handle(Future.failedFuture(UNAUTHORIZED));
            } else {
                authInfo.put("method", context.request().method().name());
                handler.handle(Future.succeededFuture(authInfo));
            }
        });
    }

    @Override // io.vertx.ext.web.handler.impl.AuthHandlerImpl
    protected String authenticateHeader(RoutingContext context) {
        byte[] bytes = new byte[32];
        this.random.nextBytes(bytes);
        String nonce = md5(bytes);
        this.nonces.put(nonce, new Nonce());
        String opaque = null;
        Session session = context.session();
        if (session != null) {
            opaque = (String) session.data().get("opaque");
        }
        if (opaque == null) {
            this.random.nextBytes(bytes);
            opaque = md5(bytes);
        }
        return "Digest realm=\"" + this.realm + "\", qop=\"auth\", nonce=\"" + nonce + "\", opaque=\"" + opaque + OperatorName.SHOW_TEXT_LINE_AND_SPACE;
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[(j * 2) + 1] = hexArray[v & 15];
        }
        return new String(hexChars);
    }

    private static synchronized String md5(byte[] payload) {
        MD5.reset();
        return bytesToHex(MD5.digest(payload));
    }
}
