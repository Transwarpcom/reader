package org.springframework.boot.autoconfigure.web.reactive.error;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/reactive/error/DefaultErrorWebExceptionHandler.class */
public class DefaultErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    private static final Map<HttpStatus.Series, String> SERIES_VIEWS;
    private final ErrorProperties errorProperties;

    static {
        Map<HttpStatus.Series, String> views = new EnumMap<>(HttpStatus.Series.class);
        views.put(HttpStatus.Series.CLIENT_ERROR, "4xx");
        views.put(HttpStatus.Series.SERVER_ERROR, "5xx");
        SERIES_VIEWS = Collections.unmodifiableMap(views);
    }

    public DefaultErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.errorProperties = errorProperties;
    }

    @Override // org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(acceptsTextHtml(), this::renderErrorView).andRoute(RequestPredicates.all(), this::renderErrorResponse);
    }

    protected Mono<ServerResponse> renderErrorView(ServerRequest request) {
        boolean includeStackTrace = isIncludeStackTrace(request, MediaType.TEXT_HTML);
        Map<String, Object> error = getErrorAttributes(request, includeStackTrace);
        HttpStatus errorStatus = getHttpStatus(error);
        ServerResponse.BodyBuilder responseBody = ServerResponse.status(errorStatus).contentType(MediaType.TEXT_HTML);
        return Flux.just(new String[]{"error/" + errorStatus.value(), "error/" + SERIES_VIEWS.get(errorStatus.series()), "error/error"}).flatMap(viewName -> {
            return renderErrorView(viewName, responseBody, error);
        }).switchIfEmpty(this.errorProperties.getWhitelabel().isEnabled() ? renderDefaultErrorView(responseBody, error) : Mono.error(getError(request))).next();
    }

    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        boolean includeStackTrace = isIncludeStackTrace(request, MediaType.ALL);
        Map<String, Object> error = getErrorAttributes(request, includeStackTrace);
        return ServerResponse.status(getHttpStatus(error)).contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(error));
    }

    protected boolean isIncludeStackTrace(ServerRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.errorProperties.getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return isTraceEnabled(request);
        }
        return false;
    }

    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = ((Integer) errorAttributes.get("status")).intValue();
        return HttpStatus.valueOf(statusCode);
    }

    protected RequestPredicate acceptsTextHtml() {
        return serverRequest -> {
            try {
                List<MediaType> acceptedMediaTypes = serverRequest.headers().accept();
                acceptedMediaTypes.remove(MediaType.ALL);
                MediaType.sortBySpecificityAndQuality(acceptedMediaTypes);
                Stream<MediaType> stream = acceptedMediaTypes.stream();
                MediaType mediaType = MediaType.TEXT_HTML;
                mediaType.getClass();
                return stream.anyMatch(mediaType::isCompatibleWith);
            } catch (InvalidMediaTypeException e) {
                return false;
            }
        };
    }
}
