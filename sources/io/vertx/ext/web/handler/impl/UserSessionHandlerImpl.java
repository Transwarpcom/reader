package io.vertx.ext.web.handler.impl;

import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.UserSessionHandler;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/UserSessionHandlerImpl.class */
public class UserSessionHandlerImpl implements UserSessionHandler {
    private static final String SESSION_USER_HOLDER_KEY = "__vertx.userHolder";
    private final AuthProvider authProvider;

    public UserSessionHandlerImpl(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext routingContext) {
        Session session = routingContext.session();
        if (session != null) {
            User user = null;
            UserHolder holder = (UserHolder) session.get(SESSION_USER_HOLDER_KEY);
            if (holder != null) {
                RoutingContext prevContext = holder.context;
                if (prevContext != null) {
                    user = prevContext.user();
                } else if (holder.user != null) {
                    user = holder.user;
                    user.setAuthProvider(this.authProvider);
                    holder.context = routingContext;
                    holder.user = null;
                }
                holder.context = routingContext;
            } else {
                routingContext.addHeadersEndHandler(v -> {
                    if (routingContext.user() != null) {
                        session.put(SESSION_USER_HOLDER_KEY, new UserHolder(routingContext));
                    }
                });
            }
            if (user != null) {
                routingContext.setUser(user);
            }
        }
        routingContext.next();
    }
}
