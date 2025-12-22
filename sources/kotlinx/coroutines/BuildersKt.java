package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 5, 1}, k = 4, xi = 48, d1 = {"kotlinx/coroutines/BuildersKt__BuildersKt", "kotlinx/coroutines/BuildersKt__Builders_commonKt"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/BuildersKt.class */
public final class BuildersKt {
    public static final <T> T runBlocking(@NotNull CoroutineContext coroutineContext, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) throws InterruptedException {
        return (T) BuildersKt__BuildersKt.runBlocking(coroutineContext, function2);
    }

    @NotNull
    public static final Job launch(@NotNull CoroutineScope $this$launch, @NotNull CoroutineContext context, @NotNull CoroutineStart start, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return BuildersKt__Builders_commonKt.launch($this$launch, context, start, function2);
    }

    @NotNull
    public static final <T> Deferred<T> async(@NotNull CoroutineScope $this$async, @NotNull CoroutineContext context, @NotNull CoroutineStart start, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        return BuildersKt__Builders_commonKt.async($this$async, context, start, function2);
    }

    @Nullable
    public static final <T> Object withContext(@NotNull CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) throws Throwable {
        return BuildersKt__Builders_commonKt.withContext(context, function2, continuation);
    }

    @Nullable
    public static final <T> Object invoke(@NotNull CoroutineDispatcher $this$invoke, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return BuildersKt__Builders_commonKt.invoke($this$invoke, function2, continuation);
    }
}
