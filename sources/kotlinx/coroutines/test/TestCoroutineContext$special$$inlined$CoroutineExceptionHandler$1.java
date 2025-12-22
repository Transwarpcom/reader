package kotlinx.coroutines.test;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import org.jetbrains.annotations.NotNull;

/* compiled from: CoroutineExceptionHandler.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��!\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n��*\u0001��\b\n\u0018��2\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006��"}, d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/test/TestCoroutineContext$special$$inlined$CoroutineExceptionHandler$1.class */
public final class TestCoroutineContext$special$$inlined$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    final /* synthetic */ TestCoroutineContext this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TestCoroutineContext$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key $super_call_param$1, TestCoroutineContext testCoroutineContext) {
        super($super_call_param$1);
        this.this$0 = testCoroutineContext;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        this.this$0.uncaughtExceptions.add(exception);
    }
}
