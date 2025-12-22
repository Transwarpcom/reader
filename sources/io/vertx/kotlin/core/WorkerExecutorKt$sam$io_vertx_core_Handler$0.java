package io.vertx.kotlin.core;

import io.vertx.core.Handler;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WorkerExecutor.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/WorkerExecutorKt$sam$io_vertx_core_Handler$0.class */
final class WorkerExecutorKt$sam$io_vertx_core_Handler$0 implements Handler {
    private final /* synthetic */ Function1 function;

    WorkerExecutorKt$sam$io_vertx_core_Handler$0(Function1 function1) {
        this.function = function1;
    }

    @Override // io.vertx.core.Handler
    public final /* synthetic */ void handle(Object p0) {
        Intrinsics.checkExpressionValueIsNotNull(this.function.invoke(p0), "invoke(...)");
    }
}
