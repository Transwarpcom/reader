package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.impl.AuthProviderInternal;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.ext.web.handler.impl.AuthorizationAuthHandler;
import java.util.Base64;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/BasicAuthHandlerImpl.class */
public class BasicAuthHandlerImpl extends AuthorizationAuthHandler {
    private static AuthProvider verifyProvider(AuthProvider provider) {
        if (provider instanceof AuthProviderInternal) {
            ((AuthProviderInternal) provider).verifyIsUsingPassword();
        }
        return provider;
    }

    public BasicAuthHandlerImpl(AuthProvider authProvider, String realm) {
        super(verifyProvider(authProvider), realm, AuthorizationAuthHandler.Type.BASIC);
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
        parseAuthorization(context, false, parseAuthorization -> {
            String suser;
            String spass;
            if (parseAuthorization.failed()) {
                handler.handle(Future.failedFuture(parseAuthorization.cause()));
                return;
            }
            try {
                String decoded = new String(Base64.getDecoder().decode((String) parseAuthorization.result()));
                int colonIdx = decoded.indexOf(":");
                if (colonIdx != -1) {
                    suser = decoded.substring(0, colonIdx);
                    spass = decoded.substring(colonIdx + 1);
                } else {
                    suser = decoded;
                    spass = null;
                }
                handler.handle(Future.succeededFuture(new JsonObject().put(FormLoginHandler.DEFAULT_USERNAME_PARAM, suser).put(FormLoginHandler.DEFAULT_PASSWORD_PARAM, spass)));
            } catch (RuntimeException e) {
                context.fail(e);
            }
        });
    }

    @Override // io.vertx.ext.web.handler.impl.AuthHandlerImpl
    protected String authenticateHeader(RoutingContext context) {
        return "Basic realm=\"" + this.realm + OperatorName.SHOW_TEXT_LINE_AND_SPACE;
    }
}
