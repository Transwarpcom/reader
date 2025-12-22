package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;
import okhttp3.Call;
import okhttp3.ResponseBody;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import retrofit2.Utils;

/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/HttpServiceMethod.class */
abstract class HttpServiceMethod<ResponseT, ReturnT> extends ServiceMethod<ReturnT> {
    private final RequestFactory requestFactory;
    private final Call.Factory callFactory;
    private final Converter<ResponseBody, ResponseT> responseConverter;

    @Nullable
    protected abstract ReturnT adapt(Call<ResponseT> call, Object[] objArr);

    static <ResponseT, ReturnT> HttpServiceMethod<ResponseT, ReturnT> parseAnnotations(Retrofit retrofit, Method method, RequestFactory requestFactory) {
        Type adapterType;
        boolean isKotlinSuspendFunction = requestFactory.isKotlinSuspendFunction;
        boolean continuationWantsResponse = false;
        Annotation[] annotations = method.getAnnotations();
        if (isKotlinSuspendFunction) {
            Type[] parameterTypes = method.getGenericParameterTypes();
            Type responseType = Utils.getParameterLowerBound(0, (ParameterizedType) parameterTypes[parameterTypes.length - 1]);
            if (Utils.getRawType(responseType) == Response.class && (responseType instanceof ParameterizedType)) {
                responseType = Utils.getParameterUpperBound(0, (ParameterizedType) responseType);
                continuationWantsResponse = true;
            }
            adapterType = new Utils.ParameterizedTypeImpl(null, Call.class, responseType);
            annotations = SkipCallbackExecutorImpl.ensurePresent(annotations);
        } else {
            adapterType = method.getGenericReturnType();
        }
        CallAdapter<ResponseT, ReturnT> callAdapter = createCallAdapter(retrofit, method, adapterType, annotations);
        Type responseType2 = callAdapter.responseType();
        if (responseType2 == okhttp3.Response.class) {
            throw Utils.methodError(method, OperatorName.SHOW_TEXT_LINE + Utils.getRawType(responseType2).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
        }
        if (responseType2 == Response.class) {
            throw Utils.methodError(method, "Response must include generic type (e.g., Response<String>)", new Object[0]);
        }
        if (requestFactory.httpMethod.equals("HEAD") && !Void.class.equals(responseType2)) {
            throw Utils.methodError(method, "HEAD method must use Void as response type.", new Object[0]);
        }
        Converter<ResponseBody, ResponseT> responseConverter = createResponseConverter(retrofit, method, responseType2);
        Call.Factory callFactory = retrofit.callFactory;
        if (!isKotlinSuspendFunction) {
            return new CallAdapted(requestFactory, callFactory, responseConverter, callAdapter);
        }
        if (continuationWantsResponse) {
            return new SuspendForResponse(requestFactory, callFactory, responseConverter, callAdapter);
        }
        return new SuspendForBody(requestFactory, callFactory, responseConverter, callAdapter, false);
    }

    private static <ResponseT, ReturnT> CallAdapter<ResponseT, ReturnT> createCallAdapter(Retrofit retrofit, Method method, Type type, Annotation[] annotationArr) {
        try {
            return (CallAdapter<ResponseT, ReturnT>) retrofit.callAdapter(type, annotationArr);
        } catch (RuntimeException e) {
            throw Utils.methodError(method, e, "Unable to create call adapter for %s", type);
        }
    }

    private static <ResponseT> Converter<ResponseBody, ResponseT> createResponseConverter(Retrofit retrofit, Method method, Type responseType) {
        Annotation[] annotations = method.getAnnotations();
        try {
            return retrofit.responseBodyConverter(responseType, annotations);
        } catch (RuntimeException e) {
            throw Utils.methodError(method, e, "Unable to create converter for %s", responseType);
        }
    }

    HttpServiceMethod(RequestFactory requestFactory, Call.Factory callFactory, Converter<ResponseBody, ResponseT> responseConverter) {
        this.requestFactory = requestFactory;
        this.callFactory = callFactory;
        this.responseConverter = responseConverter;
    }

    @Override // retrofit2.ServiceMethod
    @Nullable
    final ReturnT invoke(Object[] args) {
        Call<ResponseT> call = new OkHttpCall<>(this.requestFactory, args, this.callFactory, this.responseConverter);
        return adapt(call, args);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/HttpServiceMethod$CallAdapted.class */
    static final class CallAdapted<ResponseT, ReturnT> extends HttpServiceMethod<ResponseT, ReturnT> {
        private final CallAdapter<ResponseT, ReturnT> callAdapter;

        CallAdapted(RequestFactory requestFactory, Call.Factory callFactory, Converter<ResponseBody, ResponseT> responseConverter, CallAdapter<ResponseT, ReturnT> callAdapter) {
            super(requestFactory, callFactory, responseConverter);
            this.callAdapter = callAdapter;
        }

        @Override // retrofit2.HttpServiceMethod
        protected ReturnT adapt(Call<ResponseT> call, Object[] args) {
            return this.callAdapter.adapt2(call);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/HttpServiceMethod$SuspendForResponse.class */
    static final class SuspendForResponse<ResponseT> extends HttpServiceMethod<ResponseT, Object> {
        private final CallAdapter<ResponseT, Call<ResponseT>> callAdapter;

        SuspendForResponse(RequestFactory requestFactory, Call.Factory callFactory, Converter<ResponseBody, ResponseT> responseConverter, CallAdapter<ResponseT, Call<ResponseT>> callAdapter) {
            super(requestFactory, callFactory, responseConverter);
            this.callAdapter = callAdapter;
        }

        @Override // retrofit2.HttpServiceMethod
        protected Object adapt(Call<ResponseT> call, Object[] args) {
            Call<ResponseT> call2 = this.callAdapter.adapt2(call);
            Continuation<Response<ResponseT>> continuation = (Continuation) args[args.length - 1];
            return KotlinExtensions.awaitResponse(call2, continuation);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/HttpServiceMethod$SuspendForBody.class */
    static final class SuspendForBody<ResponseT> extends HttpServiceMethod<ResponseT, Object> {
        private final CallAdapter<ResponseT, Call<ResponseT>> callAdapter;
        private final boolean isNullable;

        SuspendForBody(RequestFactory requestFactory, Call.Factory callFactory, Converter<ResponseBody, ResponseT> responseConverter, CallAdapter<ResponseT, Call<ResponseT>> callAdapter, boolean isNullable) {
            super(requestFactory, callFactory, responseConverter);
            this.callAdapter = callAdapter;
            this.isNullable = isNullable;
        }

        @Override // retrofit2.HttpServiceMethod
        protected Object adapt(Call<ResponseT> call, Object[] args) {
            Call<ResponseT> call2 = this.callAdapter.adapt2(call);
            Continuation<ResponseT> continuation = (Continuation) args[args.length - 1];
            try {
                if (this.isNullable) {
                    return KotlinExtensions.awaitNullable(call2, continuation);
                }
                return KotlinExtensions.await(call2, continuation);
            } catch (Exception e) {
                return KotlinExtensions.yieldAndThrow(e, continuation);
            }
        }
    }
}
