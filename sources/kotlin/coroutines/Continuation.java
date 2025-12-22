package kotlin.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: Continuation.kt */
@SinceKotlin(version = "1.3")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\"\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018��*\u0006\b��\u0010\u0001 ��2\u00020\u0002J\u001e\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028��0\nH&ø\u0001��¢\u0006\u0002\u0010\u000bR\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/coroutines/Continuation;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", "", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "Lkotlin/Result;", "(Ljava/lang/Object;)V", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/coroutines/Continuation.class */
public interface Continuation<T> {
    @NotNull
    CoroutineContext getContext();

    void resumeWith(@NotNull Object obj);
}
