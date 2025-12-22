package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.SessionStore;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/SessionHandlerImpl.class */
public class SessionHandlerImpl implements SessionHandler {
    private static final String SESSION_USER_HOLDER_KEY = "__vertx.userHolder";
    private static final Logger log = LoggerFactory.getLogger((Class<?>) SessionHandlerImpl.class);
    private final SessionStore sessionStore;
    private String sessionCookieName;
    private String sessionCookiePath;
    private long sessionTimeout;
    private boolean nagHttps;
    private boolean sessionCookieSecure;
    private boolean sessionCookieHttpOnly;
    private int minLength;
    private AuthProvider authProvider;

    public SessionHandlerImpl(String sessionCookieName, String sessionCookiePath, long sessionTimeout, boolean nagHttps, boolean sessionCookieSecure, boolean sessionCookieHttpOnly, int minLength, SessionStore sessionStore) {
        this.sessionCookieName = sessionCookieName;
        this.sessionCookiePath = sessionCookiePath;
        this.sessionTimeout = sessionTimeout;
        this.nagHttps = nagHttps;
        this.sessionStore = sessionStore;
        this.sessionCookieSecure = sessionCookieSecure;
        this.sessionCookieHttpOnly = sessionCookieHttpOnly;
        this.minLength = minLength;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setSessionTimeout(long timeout) {
        this.sessionTimeout = timeout;
        return this;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setNagHttps(boolean nag) {
        this.nagHttps = nag;
        return this;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setCookieSecureFlag(boolean secure) {
        this.sessionCookieSecure = secure;
        return this;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setCookieHttpOnlyFlag(boolean httpOnly) {
        this.sessionCookieHttpOnly = httpOnly;
        return this;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setSessionCookieName(String sessionCookieName) {
        this.sessionCookieName = sessionCookieName;
        return this;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setSessionCookiePath(String sessionCookiePath) {
        this.sessionCookiePath = sessionCookiePath;
        return this;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setMinLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    @Override // io.vertx.ext.web.handler.SessionHandler
    public SessionHandler setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
        return this;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        String sessionID;
        if (this.nagHttps && log.isDebugEnabled()) {
            String uri = context.request().absoluteURI();
            if (!uri.startsWith("https:")) {
                log.debug("Using session cookies without https could make you susceptible to session hijacking: " + uri);
            }
        }
        Cookie cookie = context.getCookie(this.sessionCookieName);
        if (cookie != null && (sessionID = cookie.getValue()) != null && sessionID.length() > this.minLength) {
            getSession(context.vertx(), sessionID, res -> {
                if (res.succeeded()) {
                    Session session = (Session) res.result();
                    if (session != null) {
                        context.setSession(session);
                        if (this.authProvider != null) {
                            UserHolder holder = (UserHolder) session.get(SESSION_USER_HOLDER_KEY);
                            if (holder != null) {
                                User user = null;
                                RoutingContext prevContext = holder.context;
                                if (prevContext != null) {
                                    user = prevContext.user();
                                } else if (holder.user != null) {
                                    user = holder.user;
                                    user.setAuthProvider(this.authProvider);
                                    holder.context = context;
                                    holder.user = null;
                                }
                                holder.context = context;
                                if (user != null) {
                                    context.setUser(user);
                                }
                            }
                            addStoreSessionHandler(context, holder == null);
                        } else {
                            addStoreSessionHandler(context, false);
                        }
                    } else {
                        createNewSession(context);
                    }
                } else {
                    context.fail(res.cause());
                }
                context.next();
            });
        } else {
            createNewSession(context);
            context.next();
        }
    }

    private void getSession(Vertx vertx, String sessionID, Handler<AsyncResult<Session>> resultHandler) {
        doGetSession(vertx, System.currentTimeMillis(), sessionID, resultHandler);
    }

    private void doGetSession(Vertx vertx, long startTime, String sessionID, Handler<AsyncResult<Session>> resultHandler) {
        this.sessionStore.get(sessionID, res -> {
            if (res.succeeded() && res.result() == null) {
                long retryTimeout = this.sessionStore.retryTimeout();
                if (retryTimeout > 0 && System.currentTimeMillis() - startTime < retryTimeout) {
                    vertx.setTimer(5L, v -> {
                        doGetSession(vertx, startTime, sessionID, resultHandler);
                    });
                    return;
                }
            }
            resultHandler.handle(res);
        });
    }

    private void addStoreSessionHandler(RoutingContext context, boolean storeUser) {
        context.addHeadersEndHandler(v -> {
            Session session = context.session();
            if (!session.isDestroyed()) {
                int currentStatusCode = context.response().getStatusCode();
                if (currentStatusCode >= 200 && currentStatusCode < 400) {
                    if (storeUser && context.user() != null) {
                        session.put(SESSION_USER_HOLDER_KEY, new UserHolder(context));
                    }
                    session.setAccessed();
                    if (session.isRegenerated()) {
                        Cookie cookie = context.getCookie(this.sessionCookieName);
                        cookie.setValue(session.value()).setPath("/").setSecure(this.sessionCookieSecure).setHttpOnly(this.sessionCookieHttpOnly);
                        this.sessionStore.delete(session.oldId(), delete -> {
                            if (delete.failed()) {
                                log.error("Failed to delete previous session", delete.cause());
                            } else {
                                this.sessionStore.put(session, res -> {
                                    if (res.failed()) {
                                        log.error("Failed to store session", res.cause());
                                    }
                                });
                            }
                        });
                        return;
                    }
                    this.sessionStore.put(session, res -> {
                        if (res.failed()) {
                            log.error("Failed to store session", res.cause());
                        }
                    });
                    return;
                }
                context.removeCookie(this.sessionCookieName, false);
                return;
            }
            context.removeCookie(this.sessionCookieName);
            this.sessionStore.delete(session.id(), res2 -> {
                if (res2.failed()) {
                    log.error("Failed to delete session", res2.cause());
                }
            });
        });
    }

    private void createNewSession(RoutingContext context) {
        Session session = this.sessionStore.createSession(this.sessionTimeout, this.minLength);
        context.setSession(session);
        Cookie cookie = Cookie.cookie(this.sessionCookieName, session.value());
        cookie.setPath(this.sessionCookiePath);
        cookie.setSecure(this.sessionCookieSecure);
        cookie.setHttpOnly(this.sessionCookieHttpOnly);
        context.addCookie(cookie);
        addStoreSessionHandler(context, this.authProvider != null);
    }
}
