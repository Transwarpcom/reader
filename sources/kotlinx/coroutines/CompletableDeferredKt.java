package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: CompletableDeferred.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a,\u0010\u0007\u001a\u00020\b\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\nø\u0001��¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"CompletableDeferred", "Lkotlinx/coroutines/CompletableDeferred;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;", "parent", "Lkotlinx/coroutines/Job;", "completeWith", "", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "Lkotlin/Result;", "(Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Object;)Z", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/CompletableDeferredKt.class */
public final class CompletableDeferredKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> boolean completeWith(@NotNull CompletableDeferred<T> completableDeferred, @NotNull Object obj) {
        Throwable it = Result.m2103exceptionOrNullimpl(obj);
        return it == null ? completableDeferred.complete(obj) : completableDeferred.completeExceptionally(it);
    }

    @NotNull
    public static final <T> CompletableDeferred<T> CompletableDeferred(@Nullable Job parent) {
        return new CompletableDeferredImpl(parent);
    }

    public static /* synthetic */ CompletableDeferred CompletableDeferred$default(Job job, int i, Object obj) {
        if ((i & 1) != 0) {
            job = null;
        }
        return CompletableDeferred(job);
    }

    @NotNull
    public static final <T> CompletableDeferred<T> CompletableDeferred(T t) {
        CompletableDeferredImpl $this$CompletableDeferred_u24lambda_u2d2 = new CompletableDeferredImpl(null);
        $this$CompletableDeferred_u24lambda_u2d2.complete(t);
        return $this$CompletableDeferred_u24lambda_u2d2;
    }
}
