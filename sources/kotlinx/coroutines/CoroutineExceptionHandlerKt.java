package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.jetbrains.annotations.NotNull;

/* compiled from: CoroutineExceptionHandler.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a%\u0010��\u001a\u00020\u00012\u001a\b\u0004\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u0086\b\u001a\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H��¨\u0006\r"}, d2 = {"CoroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "", "", "handleCoroutineException", CoreConstants.CONTEXT_SCOPE_VALUE, "exception", "handlerException", "originalException", "thrownException", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CoroutineExceptionHandlerKt.class */
public final class CoroutineExceptionHandlerKt {
    @InternalCoroutinesApi
    public static final void handleCoroutineException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        try {
            CoroutineExceptionHandler it = (CoroutineExceptionHandler) context.get(CoroutineExceptionHandler.Key);
            if (it != null) {
                it.handleException(context, exception);
            } else {
                CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(context, exception);
            }
        } catch (Throwable t) {
            CoroutineExceptionHandlerImplKt.handleCoroutineExceptionImpl(context, handlerException(exception, t));
        }
    }

    @NotNull
    public static final Throwable handlerException(@NotNull Throwable originalException, @NotNull Throwable thrownException) {
        if (originalException == thrownException) {
            return originalException;
        }
        Throwable $this$handlerException_u24lambda_u2d1 = new RuntimeException("Exception while trying to handle coroutine exception", thrownException);
        Throwable $this$addSuppressedThrowable$iv = $this$handlerException_u24lambda_u2d1;
        kotlin.ExceptionsKt.addSuppressed($this$addSuppressedThrowable$iv, originalException);
        return $this$handlerException_u24lambda_u2d1;
    }

    /* compiled from: CoroutineExceptionHandler.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��!\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n��*\u0001��\b\n\u0018��2\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"})
    /* renamed from: kotlinx.coroutines.CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1.class */
    public static final class AnonymousClass1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
        final /* synthetic */ Function2<CoroutineContext, Throwable, Unit> $handler;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function2<? super CoroutineContext, ? super Throwable, Unit> function2, CoroutineExceptionHandler.Key $super_call_param$1) {
            super($super_call_param$1);
            this.$handler = function2;
        }

        @Override // kotlinx.coroutines.CoroutineExceptionHandler
        public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
            this.$handler.invoke(context, exception);
        }
    }

    @NotNull
    public static final CoroutineExceptionHandler CoroutineExceptionHandler(@NotNull Function2<? super CoroutineContext, ? super Throwable, Unit> function2) {
        return new AnonymousClass1(function2, CoroutineExceptionHandler.Key);
    }
}
