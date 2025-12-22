package io.vertx.kotlin.ext.web.common.template;

import ch.qos.logback.core.CoreConstants;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TemplateEngine.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010$\n\u0002\u0010��\n\u0002\b\u0002\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a1\u0010��\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"renderAwait", "Lio/vertx/core/buffer/Buffer;", "Lio/vertx/ext/web/common/template/TemplateEngine;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/core/json/JsonObject;", "templateFileName", "", "(Lio/vertx/ext/web/common/template/TemplateEngine;Lio/vertx/core/json/JsonObject;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "", "(Lio/vertx/ext/web/common/template/TemplateEngine;Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/common/template/TemplateEngineKt.class */
public final class TemplateEngineKt {
    @Nullable
    public static final Object renderAwait(@NotNull final TemplateEngine $this$renderAwait, @NotNull final JsonObject context, @NotNull final String templateFileName, @NotNull Continuation<? super Buffer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Buffer>>, Unit>() { // from class: io.vertx.kotlin.ext.web.common.template.TemplateEngineKt.renderAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Buffer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Buffer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$renderAwait.render(context, templateFileName, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object renderAwait(@NotNull final TemplateEngine $this$renderAwait, @NotNull final Map<String, ? extends Object> map, @NotNull final String templateFileName, @NotNull Continuation<? super Buffer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Buffer>>, Unit>() { // from class: io.vertx.kotlin.ext.web.common.template.TemplateEngineKt.renderAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Buffer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Buffer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$renderAwait.render(map, templateFileName, it);
            }
        }, continuation);
    }
}
