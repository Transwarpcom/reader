package io.vertx.kotlin.coroutines;

import ch.qos.logback.core.CoreConstants;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.WriteStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: ReceiveChannelHandler.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��0\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b��\u0010\u0004*\u00020\u0005\u001a$\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007\"\u0004\b��\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\b2\u0006\u0010\t\u001a\u00020\n\u001a$\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007\"\u0004\b��\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\b2\u0006\u0010\u000b\u001a\u00020\u0005\u001a0\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\f\"\u0004\b��\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\r2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0007\u001a0\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\f\"\u0004\b��\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\r2\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0007\"\u000e\u0010��\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��¨\u0006\u000f"}, d2 = {"DEFAULT_CAPACITY", "", "receiveChannelHandler", "Lio/vertx/kotlin/coroutines/ReceiveChannelHandler;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lio/vertx/core/Vertx;", "toChannel", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/vertx/core/streams/ReadStream;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/core/Context;", "vertx", "Lkotlinx/coroutines/channels/SendChannel;", "Lio/vertx/core/streams/WriteStream;", "capacity", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/ReceiveChannelHandlerKt.class */
public final class ReceiveChannelHandlerKt {
    private static final int DEFAULT_CAPACITY = 16;

    @NotNull
    public static final <T> ReceiveChannelHandler<T> receiveChannelHandler(@NotNull Vertx receiveChannelHandler) {
        Intrinsics.checkParameterIsNotNull(receiveChannelHandler, "$this$receiveChannelHandler");
        return new ReceiveChannelHandler<>(receiveChannelHandler);
    }

    @NotNull
    public static final <T> ReceiveChannel<T> toChannel(@NotNull ReadStream<T> toChannel, @NotNull Vertx vertx) {
        Intrinsics.checkParameterIsNotNull(toChannel, "$this$toChannel");
        Intrinsics.checkParameterIsNotNull(vertx, "vertx");
        Context orCreateContext = vertx.getOrCreateContext();
        Intrinsics.checkExpressionValueIsNotNull(orCreateContext, "vertx.getOrCreateContext()");
        return toChannel(toChannel, orCreateContext);
    }

    @NotNull
    public static final <T> ReceiveChannel<T> toChannel(@NotNull ReadStream<T> toChannel, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(toChannel, "$this$toChannel");
        Intrinsics.checkParameterIsNotNull(context, "context");
        toChannel.pause2();
        ChannelReadStream ret = new ChannelReadStream(toChannel, ChannelKt.Channel(0), context);
        ret.subscribe();
        toChannel.fetch2(1L);
        return ret;
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static /* synthetic */ SendChannel toChannel$default(WriteStream writeStream, Vertx vertx, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 16;
        }
        return toChannel(writeStream, vertx, i);
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T> SendChannel<T> toChannel(@NotNull WriteStream<T> toChannel, @NotNull Vertx vertx, int capacity) {
        Intrinsics.checkParameterIsNotNull(toChannel, "$this$toChannel");
        Intrinsics.checkParameterIsNotNull(vertx, "vertx");
        Context orCreateContext = vertx.getOrCreateContext();
        Intrinsics.checkExpressionValueIsNotNull(orCreateContext, "vertx.getOrCreateContext()");
        return toChannel(toChannel, orCreateContext, capacity);
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static /* synthetic */ SendChannel toChannel$default(WriteStream writeStream, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 16;
        }
        return toChannel(writeStream, context, i);
    }

    @ExperimentalCoroutinesApi
    @NotNull
    public static final <T> SendChannel<T> toChannel(@NotNull WriteStream<T> toChannel, @NotNull Context context, int capacity) {
        Intrinsics.checkParameterIsNotNull(toChannel, "$this$toChannel");
        Intrinsics.checkParameterIsNotNull(context, "context");
        ChannelWriteStream ret = new ChannelWriteStream(toChannel, ChannelKt.Channel(capacity), context);
        ret.subscribe();
        return ret;
    }
}
