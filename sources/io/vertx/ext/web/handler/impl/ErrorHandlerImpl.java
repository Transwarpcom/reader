package io.vertx.ext.web.handler.impl;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.MIMEHeader;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.impl.Utils;
import java.util.List;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/ErrorHandlerImpl.class */
public class ErrorHandlerImpl implements ErrorHandler {
    private final boolean displayExceptionDetails;
    private final String errorTemplate;

    public ErrorHandlerImpl(String errorTemplateName, boolean displayExceptionDetails) {
        Objects.requireNonNull(errorTemplateName);
        this.displayExceptionDetails = displayExceptionDetails;
        this.errorTemplate = Utils.readResourceToBuffer(errorTemplateName).toString();
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        HttpServerResponse response = context.response();
        Throwable failure = context.failure();
        int errorCode = context.statusCode();
        String errorMessage = null;
        if (errorCode != -1) {
            context.response().setStatusCode(errorCode);
            errorMessage = context.response().getStatusMessage();
        } else {
            errorCode = 500;
            if (this.displayExceptionDetails) {
                errorMessage = failure.getMessage();
            }
            if (errorMessage == null) {
                errorMessage = "Internal Server Error";
            }
            response.setStatusMessage(errorMessage.replaceAll("\\r|\\n", " "));
        }
        answerWithError(context, errorCode, errorMessage);
    }

    private void answerWithError(RoutingContext context, int errorCode, String errorMessage) {
        context.response().setStatusCode(errorCode);
        if (!sendErrorResponseMIME(context, errorCode, errorMessage) && !sendErrorAcceptMIME(context, errorCode, errorMessage)) {
            sendError(context, "text/plain", errorCode, errorMessage);
        }
    }

    private boolean sendErrorResponseMIME(RoutingContext context, int errorCode, String errorMessage) {
        String mime = context.response().headers().get(HttpHeaders.CONTENT_TYPE);
        return mime != null && sendError(context, mime, errorCode, errorMessage);
    }

    private boolean sendErrorAcceptMIME(RoutingContext context, int errorCode, String errorMessage) {
        List<MIMEHeader> acceptableMimes = context.parsedHeaders().accept();
        for (MIMEHeader accept : acceptableMimes) {
            if (sendError(context, accept.value(), errorCode, errorMessage)) {
                return true;
            }
        }
        return false;
    }

    private boolean sendError(RoutingContext context, String mime, int errorCode, String errorMessage) {
        HttpServerResponse response = context.response();
        if (mime.startsWith("text/html")) {
            StringBuilder stack = new StringBuilder();
            if (context.failure() != null && this.displayExceptionDetails) {
                for (StackTraceElement elem : context.failure().getStackTrace()) {
                    stack.append("<li>").append(elem).append("</li>");
                }
            }
            response.putHeader(HttpHeaders.CONTENT_TYPE, "text/html");
            response.end(this.errorTemplate.replace("{title}", "An unexpected error occurred").replace("{errorCode}", Integer.toString(errorCode)).replace("{errorMessage}", errorMessage).replace("{stackTrace}", stack.toString()));
            return true;
        }
        if (mime.startsWith("application/json")) {
            JsonObject jsonError = new JsonObject();
            jsonError.put("error", new JsonObject().put("code", Integer.valueOf(errorCode)).put("message", errorMessage));
            if (context.failure() != null && this.displayExceptionDetails) {
                JsonArray stack2 = new JsonArray();
                for (StackTraceElement elem2 : context.failure().getStackTrace()) {
                    stack2.add(elem2.toString());
                }
                jsonError.put("stack", stack2);
            }
            response.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            response.end(jsonError.encode());
            return true;
        }
        if (mime.startsWith("text/plain")) {
            response.putHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
            StringBuilder sb = new StringBuilder();
            sb.append("Error ");
            sb.append(errorCode);
            sb.append(": ");
            sb.append(errorMessage);
            if (context.failure() != null && this.displayExceptionDetails) {
                for (StackTraceElement elem3 : context.failure().getStackTrace()) {
                    sb.append("\tat ").append(elem3).append("\n");
                }
            }
            response.end(sb.toString());
            return true;
        }
        return false;
    }
}
