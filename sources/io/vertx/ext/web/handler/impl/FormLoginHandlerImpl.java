package io.vertx.ext.web.handler.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.FormLoginHandler;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/FormLoginHandlerImpl.class */
public class FormLoginHandlerImpl implements FormLoginHandler {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) FormLoginHandlerImpl.class);
    private final AuthProvider authProvider;
    private String usernameParam;
    private String passwordParam;
    private String returnURLParam;
    private String directLoggedInOKURL;
    private static final String DEFAULT_DIRECT_LOGGED_IN_OK_PAGE = "<html><body><h1>Login successful</h1></body></html>";

    @Override // io.vertx.ext.web.handler.FormLoginHandler
    public FormLoginHandler setUsernameParam(String usernameParam) {
        this.usernameParam = usernameParam;
        return this;
    }

    @Override // io.vertx.ext.web.handler.FormLoginHandler
    public FormLoginHandler setPasswordParam(String passwordParam) {
        this.passwordParam = passwordParam;
        return this;
    }

    @Override // io.vertx.ext.web.handler.FormLoginHandler
    public FormLoginHandler setReturnURLParam(String returnURLParam) {
        this.returnURLParam = returnURLParam;
        return this;
    }

    @Override // io.vertx.ext.web.handler.FormLoginHandler
    public FormLoginHandler setDirectLoggedInOKURL(String directLoggedInOKURL) {
        this.directLoggedInOKURL = directLoggedInOKURL;
        return this;
    }

    public FormLoginHandlerImpl(AuthProvider authProvider, String usernameParam, String passwordParam, String returnURLParam, String directLoggedInOKURL) {
        this.authProvider = authProvider;
        this.usernameParam = usernameParam;
        this.passwordParam = passwordParam;
        this.returnURLParam = returnURLParam;
        this.directLoggedInOKURL = directLoggedInOKURL;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        HttpServerRequest req = context.request();
        if (req.method() != HttpMethod.POST) {
            context.fail(405);
            return;
        }
        if (!req.isExpectMultipart()) {
            throw new IllegalStateException("HttpServerRequest should have setExpectMultipart set to true, but it is currently set to false.");
        }
        MultiMap params = req.formAttributes();
        String username = params.get(this.usernameParam);
        String password = params.get(this.passwordParam);
        if (username == null || password == null) {
            log.warn("No username or password provided in form - did you forget to include a BodyHandler?");
            context.fail(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
        } else {
            Session session = context.session();
            JsonObject authInfo = new JsonObject().put(FormLoginHandler.DEFAULT_USERNAME_PARAM, username).put(FormLoginHandler.DEFAULT_PASSWORD_PARAM, password);
            this.authProvider.authenticate(authInfo, res -> {
                if (res.succeeded()) {
                    User user = (User) res.result();
                    context.setUser(user);
                    if (session != null) {
                        session.regenerateId();
                        String returnURL = (String) session.remove(this.returnURLParam);
                        if (returnURL != null) {
                            doRedirect(req.response(), returnURL);
                            return;
                        }
                    }
                    if (this.directLoggedInOKURL != null) {
                        doRedirect(req.response(), this.directLoggedInOKURL);
                        return;
                    } else {
                        req.response().end(DEFAULT_DIRECT_LOGGED_IN_OK_PAGE);
                        return;
                    }
                }
                context.fail(403);
            });
        }
    }

    private void doRedirect(HttpServerResponse response, String url) {
        response.putHeader("location", url).setStatusCode(302).end();
    }
}
