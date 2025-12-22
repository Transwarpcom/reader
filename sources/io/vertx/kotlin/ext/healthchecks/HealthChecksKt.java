package io.vertx.kotlin.ext.healthchecks;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.healthchecks.HealthChecks;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HealthChecks.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"invokeAwait", "Lio/vertx/core/json/JsonObject;", "Lio/vertx/ext/healthchecks/HealthChecks;", "name", "", "(Lio/vertx/ext/healthchecks/HealthChecks;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/healthchecks/HealthChecksKt.class */
public final class HealthChecksKt {
    @Nullable
    public static final Object invokeAwait(@NotNull final HealthChecks $this$invokeAwait, @NotNull final String name, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.healthchecks.HealthChecksKt.invokeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$invokeAwait.invoke(name, it);
            }
        }, continuation);
    }
}
