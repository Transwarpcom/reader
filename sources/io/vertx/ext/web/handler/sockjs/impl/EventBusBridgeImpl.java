package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.User;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.ag2s.epublib.epub.NCXDocumentV3;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/EventBusBridgeImpl.class */
public class EventBusBridgeImpl implements Handler<SockJSSocket> {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) EventBusBridgeImpl.class);
    private final List<PermittedOptions> inboundPermitted;
    private final List<PermittedOptions> outboundPermitted;
    private final int maxAddressLength;
    private final int maxHandlersPerSocket;
    private final long pingTimeout;
    private final long replyTimeout;
    private final Vertx vertx;
    private final EventBus eb;
    private final Handler<BridgeEvent> bridgeEventHandler;
    private final Map<SockJSSocket, SockInfo> sockInfos = new HashMap();
    private final Map<String, Message> messagesAwaitingReply = new HashMap();
    private final Map<String, Pattern> compiledREs = new HashMap();

    public EventBusBridgeImpl(Vertx vertx, BridgeOptions options, Handler<BridgeEvent> bridgeEventHandler) {
        this.vertx = vertx;
        this.eb = vertx.eventBus();
        this.inboundPermitted = options.getInboundPermitteds() == null ? new ArrayList<>() : options.getInboundPermitteds();
        this.outboundPermitted = options.getOutboundPermitteds() == null ? new ArrayList<>() : options.getOutboundPermitteds();
        this.maxAddressLength = options.getMaxAddressLength();
        this.maxHandlersPerSocket = options.getMaxHandlersPerSocket();
        this.pingTimeout = options.getPingTimeout();
        this.replyTimeout = options.getReplyTimeout();
        this.bridgeEventHandler = bridgeEventHandler;
    }

    private void handleSocketClosed(SockJSSocket sock, Map<String, MessageConsumer> registrations) {
        PingInfo pingInfo;
        registrations.forEach((key, value) -> {
            value.unregister();
            checkCallHook(() -> {
                return new BridgeEventImpl(BridgeEventType.UNREGISTER, new JsonObject().put("type", "unregister").put("address", value.address()), sock);
            }, null, null);
        });
        SockInfo info = this.sockInfos.remove(sock);
        if (info != null && (pingInfo = info.pingInfo) != null) {
            this.vertx.cancelTimer(pingInfo.timerID);
        }
        checkCallHook(() -> {
            return new BridgeEventImpl(BridgeEventType.SOCKET_CLOSED, null, sock);
        }, null, null);
    }

    private void handleSocketData(SockJSSocket sock, Buffer data, Map<String, MessageConsumer> registrations) {
        try {
            JsonObject msg = new JsonObject(data.toString());
            String type = msg.getString("type");
            if (type == null) {
                replyError(sock, "missing_type");
            }
            if (type.equals("ping")) {
                internalHandlePing(sock);
                return;
            }
            String address = msg.getString("address");
            if (address == null) {
                replyError(sock, "missing_address");
                return;
            }
            switch (type) {
                case "send":
                    internalHandleSendOrPub(sock, true, msg);
                    break;
                case "publish":
                    internalHandleSendOrPub(sock, false, msg);
                    break;
                case "register":
                    internalHandleRegister(sock, msg, registrations);
                    break;
                case "unregister":
                    internalHandleUnregister(sock, msg, registrations);
                    break;
                default:
                    log.error("Invalid type in incoming message: " + type);
                    replyError(sock, "invalid_type");
                    break;
            }
        } catch (DecodeException e) {
            replyError(sock, "invalid_json");
        }
    }

    private void checkCallHook(Supplier<BridgeEventImpl> eventSupplier, Runnable okAction, Runnable rejectAction) {
        if (this.bridgeEventHandler == null) {
            if (okAction != null) {
                okAction.run();
            }
        } else {
            BridgeEventImpl event = eventSupplier.get();
            this.bridgeEventHandler.handle(event);
            event.future().setHandler2(res -> {
                if (res.succeeded()) {
                    if (((Boolean) res.result()).booleanValue()) {
                        if (okAction != null) {
                            okAction.run();
                            return;
                        }
                        return;
                    } else if (rejectAction != null) {
                        rejectAction.run();
                        return;
                    } else {
                        log.debug("Bridge handler prevented send or pub");
                        return;
                    }
                }
                log.error("Failure in bridge event handler", res.cause());
            });
        }
    }

    private void internalHandleSendOrPub(SockJSSocket sock, boolean send, JsonObject msg) {
        checkCallHook(() -> {
            return new BridgeEventImpl(send ? BridgeEventType.SEND : BridgeEventType.PUBLISH, msg, sock);
        }, () -> {
            String address = msg.getString("address");
            if (address == null) {
                replyError(sock, "missing_address");
            } else {
                doSendOrPub(send, sock, address, msg);
            }
        }, () -> {
            replyError(sock, "rejected");
        });
    }

    private boolean checkMaxHandlers(SockJSSocket sock, SockInfo info) {
        if (info.handlerCount < this.maxHandlersPerSocket) {
            return true;
        }
        log.warn("Refusing to register as max_handlers_per_socket reached already");
        replyError(sock, "max_handlers_reached");
        return false;
    }

    private void internalHandleRegister(SockJSSocket sock, JsonObject rawMsg, Map<String, MessageConsumer> registrations) {
        SockInfo info = this.sockInfos.get(sock);
        if (!checkMaxHandlers(sock, info)) {
            return;
        }
        checkCallHook(() -> {
            return new BridgeEventImpl(BridgeEventType.REGISTER, rawMsg, sock);
        }, () -> {
            boolean debug = log.isDebugEnabled();
            String address = rawMsg.getString("address");
            if (address == null) {
                replyError(sock, "missing_address");
                return;
            }
            if (address.length() > this.maxAddressLength) {
                log.warn("Refusing to register as address length > max_address_length");
                replyError(sock, "max_address_length_reached");
                return;
            }
            Match match = checkMatches(false, address, null);
            if (match.doesMatch) {
                Handler<Message<Object>> handler = msg -> {
                    Match curMatch = checkMatches(false, address, msg.body());
                    if (curMatch.doesMatch) {
                        if (curMatch.requiredAuthority != null) {
                            authorise(curMatch, sock.webUser(), res -> {
                                if (res.succeeded()) {
                                    if (((Boolean) res.result()).booleanValue()) {
                                        checkAddAccceptedReplyAddress(msg);
                                        deliverMessage(sock, address, msg);
                                        return;
                                    } else {
                                        if (debug) {
                                            log.debug("Outbound message for address " + address + " rejected because auth is required and socket is not authed");
                                            return;
                                        }
                                        return;
                                    }
                                }
                                log.error(res.cause());
                            });
                            return;
                        } else {
                            checkAddAccceptedReplyAddress(msg);
                            deliverMessage(sock, address, msg);
                            return;
                        }
                    }
                    if (debug) {
                        log.debug("Outbound message for address " + address + " rejected because there is no inbound match");
                    }
                };
                MessageConsumer reg = this.eb.consumer(address).handler2((Handler) handler);
                registrations.put(address, reg);
                info.handlerCount++;
                return;
            }
            if (debug) {
                log.debug("Cannot register handler for address " + address + " because there is no inbound match");
            }
            replyError(sock, "access_denied");
        }, () -> {
            replyError(sock, "rejected");
        });
    }

    private void internalHandleUnregister(SockJSSocket sock, JsonObject rawMsg, Map<String, MessageConsumer> registrations) {
        checkCallHook(() -> {
            return new BridgeEventImpl(BridgeEventType.UNREGISTER, rawMsg, sock);
        }, () -> {
            String address = rawMsg.getString("address");
            if (address == null) {
                replyError(sock, "missing_address");
                return;
            }
            Match match = checkMatches(false, address, null);
            if (match.doesMatch) {
                MessageConsumer reg = (MessageConsumer) registrations.remove(address);
                if (reg != null) {
                    reg.unregister();
                    SockInfo info = this.sockInfos.get(sock);
                    info.handlerCount--;
                    return;
                }
                return;
            }
            if (log.isDebugEnabled()) {
                log.debug("Cannot unregister handler for address " + address + " because there is no inbound match");
            }
            replyError(sock, "access_denied");
        }, () -> {
            replyError(sock, "rejected");
        });
    }

    private void internalHandlePing(SockJSSocket sock) {
        Session webSession = sock.webSession();
        if (webSession != null) {
            webSession.setAccessed();
        }
        SockInfo info = this.sockInfos.get(sock);
        if (info != null) {
            info.pingInfo.lastPing = System.currentTimeMillis();
            checkCallHook(() -> {
                return new BridgeEventImpl(BridgeEventType.SOCKET_PING, null, sock);
            }, null, null);
        }
    }

    @Override // io.vertx.core.Handler
    public void handle(SockJSSocket sock) {
        Supplier<BridgeEventImpl> supplier = () -> {
            return new BridgeEventImpl(BridgeEventType.SOCKET_CREATED, null, sock);
        };
        Runnable runnable = () -> {
            Map<String, MessageConsumer> registrations = new HashMap<>();
            sock.endHandler(v -> {
                handleSocketClosed(sock, registrations);
            });
            sock.handler2(data -> {
                handleSocketData(sock, data, registrations);
            });
            PingInfo pingInfo = new PingInfo();
            pingInfo.timerID = this.vertx.setPeriodic(this.pingTimeout, id -> {
                if (System.currentTimeMillis() - pingInfo.lastPing >= this.pingTimeout) {
                    Supplier<BridgeEventImpl> supplier2 = () -> {
                        return new BridgeEventImpl(BridgeEventType.SOCKET_IDLE, null, sock);
                    };
                    SockJSSocketBase sockJSSocketBase = (SockJSSocketBase) sock;
                    sockJSSocketBase.getClass();
                    checkCallHook(supplier2, sockJSSocketBase::closeAfterSessionExpired, () -> {
                        replyError(sock, "rejected");
                    });
                }
            });
            SockInfo sockInfo = new SockInfo();
            sockInfo.pingInfo = pingInfo;
            this.sockInfos.put(sock, sockInfo);
        };
        sock.getClass();
        checkCallHook(supplier, runnable, sock::close);
    }

    private void checkAddAccceptedReplyAddress(Message message) {
        String replyAddress = message.replyAddress();
        if (replyAddress != null) {
            this.messagesAwaitingReply.put(replyAddress, message);
            this.vertx.setTimer(this.replyTimeout, tid -> {
                this.messagesAwaitingReply.remove(replyAddress);
            });
        }
    }

    private void deliverMessage(SockJSSocket sock, String address, Message message) {
        JsonObject envelope = new JsonObject().put("type", "rec").put("address", address).put(NCXDocumentV3.XHTMLTgs.body, message.body());
        if (message.replyAddress() != null) {
            envelope.put("replyAddress", message.replyAddress());
        }
        if (message.headers() != null && !message.headers().isEmpty()) {
            JsonObject headersCopy = new JsonObject();
            for (String name : message.headers().names()) {
                List<String> values = message.headers().getAll(name);
                if (values.size() == 1) {
                    headersCopy.put(name, values.get(0));
                } else {
                    headersCopy.put(name, values);
                }
            }
            envelope.put("headers", headersCopy);
        }
        checkCallHook(() -> {
            return new BridgeEventImpl(BridgeEventType.RECEIVE, envelope, sock);
        }, () -> {
            sock.write(Buffer.buffer(envelope.encode()));
        }, () -> {
            log.debug("outbound message rejected by bridge event handler");
        });
    }

    private void doSendOrPub(boolean send, SockJSSocket sock, String address, JsonObject message) {
        Match curMatch;
        Object body = message.getValue(NCXDocumentV3.XHTMLTgs.body);
        JsonObject headers = message.getJsonObject("headers");
        String replyAddress = message.getString("replyAddress");
        if (replyAddress != null && replyAddress.length() > 36) {
            log.error("Will not send message, reply address is > 36 chars");
            replyError(sock, "invalid_reply_address");
            return;
        }
        boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("Received msg from client in bridge. address:" + address + " message:" + body);
        }
        Message awaitingReply = this.messagesAwaitingReply.remove(address);
        if (awaitingReply != null) {
            curMatch = new Match(true);
        } else {
            curMatch = checkMatches(true, address, body);
        }
        if (curMatch.doesMatch) {
            if (curMatch.requiredAuthority != null) {
                User webUser = sock.webUser();
                if (webUser != null) {
                    authorise(curMatch, webUser, res -> {
                        if (res.succeeded()) {
                            if (((Boolean) res.result()).booleanValue()) {
                                checkAndSend(send, address, body, headers, sock, replyAddress, null);
                                return;
                            }
                            replyError(sock, "access_denied");
                            if (debug) {
                                log.debug("Inbound message for address " + address + " rejected because is not authorised");
                                return;
                            }
                            return;
                        }
                        replyError(sock, "auth_error");
                        log.error("Error in performing authorisation", res.cause());
                    });
                    return;
                }
                replyError(sock, "not_logged_in");
                if (debug) {
                    log.debug("Inbound message for address " + address + " rejected because it requires auth and user is not authenticated");
                    return;
                }
                return;
            }
            checkAndSend(send, address, body, headers, sock, replyAddress, awaitingReply);
            return;
        }
        replyError(sock, "access_denied");
        if (debug) {
            log.debug("Inbound message for address " + address + " rejected because there is no match");
        }
    }

    private void checkAndSend(boolean send, String address, Object body, JsonObject headers, SockJSSocket sock, String replyAddress, Message awaitingReply) {
        Handler<AsyncResult<Message<Object>>> replyHandler;
        MultiMap mHeaders;
        SockInfo info = this.sockInfos.get(sock);
        if (replyAddress != null && !checkMaxHandlers(sock, info)) {
            return;
        }
        if (replyAddress != null) {
            replyHandler = result -> {
                if (result.succeeded()) {
                    Message message = (Message) result.result();
                    checkAddAccceptedReplyAddress(message);
                    deliverMessage(sock, replyAddress, message);
                } else {
                    ReplyException cause = (ReplyException) result.cause();
                    JsonObject envelope = new JsonObject().put("type", "err").put("address", replyAddress).put("failureCode", Integer.valueOf(cause.failureCode())).put("failureType", cause.failureType().name()).put("message", cause.getMessage());
                    sock.write(Buffer.buffer(envelope.encode()));
                }
                info.handlerCount--;
            };
        } else {
            replyHandler = null;
        }
        if (log.isDebugEnabled()) {
            log.debug("Forwarding message to address " + address + " on event bus");
        }
        if (headers != null) {
            mHeaders = new CaseInsensitiveHeaders();
            headers.forEach(entry -> {
                mHeaders.add((String) entry.getKey(), entry.getValue().toString());
            });
        } else {
            mHeaders = null;
        }
        if (send) {
            if (awaitingReply != null) {
                if (replyHandler != null) {
                    awaitingReply.replyAndRequest(body, new DeliveryOptions().setSendTimeout(this.replyTimeout).setHeaders(mHeaders), replyHandler);
                } else {
                    awaitingReply.reply(body, new DeliveryOptions().setSendTimeout(this.replyTimeout).setHeaders(mHeaders));
                }
            } else if (replyAddress != null) {
                this.eb.request(address, body, new DeliveryOptions().setSendTimeout(this.replyTimeout).setHeaders(mHeaders), replyHandler);
            } else {
                this.eb.send(address, body, new DeliveryOptions().setSendTimeout(this.replyTimeout).setHeaders(mHeaders));
            }
            if (replyAddress != null) {
                info.handlerCount++;
                return;
            }
            return;
        }
        this.eb.publish(address, body, new DeliveryOptions().setHeaders(mHeaders));
    }

    private void authorise(Match curMatch, User webUser, Handler<AsyncResult<Boolean>> handler) {
        if (curMatch.requiredAuthority != null) {
            webUser.isAuthorized(curMatch.requiredAuthority, res -> {
                if (res.succeeded()) {
                    handler.handle(Future.succeededFuture(res.result()));
                } else {
                    log.error(res.cause());
                }
            });
        }
    }

    private Match checkMatches(boolean inbound, String address, Object body) {
        String matchRegex;
        boolean addressOK;
        List<PermittedOptions> matches = inbound ? this.inboundPermitted : this.outboundPermitted;
        for (PermittedOptions matchHolder : matches) {
            String matchAddress = matchHolder.getAddress();
            if (matchAddress == null) {
                matchRegex = matchHolder.getAddressRegex();
            } else {
                matchRegex = null;
            }
            if (matchAddress == null) {
                addressOK = matchRegex == null || regexMatches(matchRegex, address);
            } else {
                addressOK = matchAddress.equals(address);
            }
            if (addressOK) {
                boolean matched = structureMatches(matchHolder.getMatch(), body);
                if (matched) {
                    String requiredAuthority = matchHolder.getRequiredAuthority();
                    return new Match(true, requiredAuthority);
                }
            }
        }
        return new Match(false);
    }

    private boolean regexMatches(String matchRegex, String address) {
        Pattern pattern = this.compiledREs.computeIfAbsent(matchRegex, Pattern::compile);
        Matcher m = pattern.matcher(address);
        return m.matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void replyError(SockJSSocket sock, String err) {
        JsonObject envelope = new JsonObject().put("type", "err").put(NCXDocumentV3.XHTMLTgs.body, err);
        sock.write(Buffer.buffer(envelope.encode()));
    }

    private static boolean structureMatches(JsonObject match, Object bodyObject) {
        if (match == null || bodyObject == null) {
            return true;
        }
        if (bodyObject instanceof JsonObject) {
            JsonObject body = (JsonObject) bodyObject;
            for (String fieldName : match.fieldNames()) {
                Object mv = match.getValue(fieldName);
                Object bv = body.getValue(fieldName);
                if (mv instanceof JsonObject) {
                    if (!structureMatches((JsonObject) mv, bv)) {
                        return false;
                    }
                } else if (!match.getValue(fieldName).equals(body.getValue(fieldName))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/EventBusBridgeImpl$Match.class */
    private static class Match {
        public final boolean doesMatch;
        public final String requiredAuthority;

        Match(boolean doesMatch, String requiredAuthority) {
            this.doesMatch = doesMatch;
            this.requiredAuthority = requiredAuthority;
        }

        Match(boolean doesMatch) {
            this.doesMatch = doesMatch;
            this.requiredAuthority = null;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/EventBusBridgeImpl$PingInfo.class */
    private static final class PingInfo {
        long lastPing;
        long timerID;

        private PingInfo() {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/EventBusBridgeImpl$SockInfo.class */
    private static final class SockInfo {
        int handlerCount;
        PingInfo pingInfo;

        private SockInfo() {
        }
    }
}
