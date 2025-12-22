package io.vertx.kotlin.ext.shell.command;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.shell.command.Command;
import io.vertx.ext.shell.command.CommandRegistry;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CommandRegistry.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a)\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006*\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a\u001d\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"registerCommandAwait", "Lio/vertx/ext/shell/command/Command;", "Lio/vertx/ext/shell/command/CommandRegistry;", "command", "(Lio/vertx/ext/shell/command/CommandRegistry;Lio/vertx/ext/shell/command/Command;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerCommandsAwait", "", "commands", "(Lio/vertx/ext/shell/command/CommandRegistry;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterCommandAwait", "", "commandName", "", "(Lio/vertx/ext/shell/command/CommandRegistry;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/shell/command/CommandRegistryKt.class */
public final class CommandRegistryKt {
    @Nullable
    public static final Object registerCommandAwait(@NotNull final CommandRegistry $this$registerCommandAwait, @NotNull final Command command, @NotNull Continuation<? super Command> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Command>>, Unit>() { // from class: io.vertx.kotlin.ext.shell.command.CommandRegistryKt.registerCommandAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Command>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Command>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$registerCommandAwait.registerCommand(command, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object registerCommandsAwait(@NotNull final CommandRegistry $this$registerCommandsAwait, @NotNull final List<? extends Command> list, @NotNull Continuation<? super List<? extends Command>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Command>>>, Unit>() { // from class: io.vertx.kotlin.ext.shell.command.CommandRegistryKt.registerCommandsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Command>>> handler) {
                invoke2((Handler<AsyncResult<List<Command>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Command>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$registerCommandsAwait.registerCommands(list, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object unregisterCommandAwait(@NotNull final CommandRegistry $this$unregisterCommandAwait, @NotNull final String commandName, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.shell.command.CommandRegistryKt.unregisterCommandAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$unregisterCommandAwait.unregisterCommand(commandName, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.shell.command.CommandRegistryKt.unregisterCommandAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }
}
