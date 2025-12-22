package io.vertx.kotlin.core.datagram;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramPacket;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.streams.WriteStream;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DatagramSocket.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��4\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a-\u0010��\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a#\u0010\u0013\u001a\u00020\t*\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a-\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001b\u001a-\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001d\u001a5\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001f\u001a\u001d\u0010 \u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a/\u0010 \u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"blockMulticastGroupAwait", "Lio/vertx/core/datagram/DatagramSocket;", "multicastAddress", "", "sourceToBlock", "(Lio/vertx/core/datagram/DatagramSocket;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "networkInterface", "(Lio/vertx/core/datagram/DatagramSocket;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeAwait", "", "(Lio/vertx/core/datagram/DatagramSocket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listenAwait", RtspHeaders.Values.PORT, "", "host", "(Lio/vertx/core/datagram/DatagramSocket;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listenMulticastGroupAwait", "(Lio/vertx/core/datagram/DatagramSocket;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", PackageDocumentBase.DCTags.source, "pipeToAwait", "dst", "Lio/vertx/core/streams/WriteStream;", "Lio/vertx/core/datagram/DatagramPacket;", "(Lio/vertx/core/datagram/DatagramSocket;Lio/vertx/core/streams/WriteStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAwait", "packet", "Lio/vertx/core/buffer/Buffer;", "(Lio/vertx/core/datagram/DatagramSocket;Lio/vertx/core/buffer/Buffer;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "str", "(Lio/vertx/core/datagram/DatagramSocket;Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enc", "(Lio/vertx/core/datagram/DatagramSocket;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlistenMulticastGroupAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/datagram/DatagramSocketKt.class */
public final class DatagramSocketKt {
    @Nullable
    public static final Object pipeToAwait(@NotNull final DatagramSocket $this$pipeToAwait, @NotNull final WriteStream<DatagramPacket> writeStream, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.pipeToAwait.2
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
                $this$pipeToAwait.pipeTo(writeStream, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.pipeToAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object sendAwait(@NotNull final DatagramSocket $this$sendAwait, @NotNull final Buffer packet, final int port, @NotNull final String host, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.sendAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendAwait.send(packet, port, host, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object sendAwait(@NotNull final DatagramSocket $this$sendAwait, @NotNull final String str, final int port, @NotNull final String host, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.sendAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendAwait.send(str, port, host, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object sendAwait(@NotNull final DatagramSocket $this$sendAwait, @NotNull final String str, @NotNull final String enc, final int port, @NotNull final String host, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.sendAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$sendAwait.send(str, enc, port, host, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object closeAwait(@NotNull final DatagramSocket $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.closeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.closeAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object listenMulticastGroupAwait(@NotNull final DatagramSocket $this$listenMulticastGroupAwait, @NotNull final String multicastAddress, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.listenMulticastGroupAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listenMulticastGroupAwait.listenMulticastGroup(multicastAddress, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listenMulticastGroupAwait(@NotNull final DatagramSocket $this$listenMulticastGroupAwait, @NotNull final String multicastAddress, @NotNull final String networkInterface, @Nullable final String source, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.listenMulticastGroupAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listenMulticastGroupAwait.listenMulticastGroup(multicastAddress, networkInterface, source, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object unlistenMulticastGroupAwait(@NotNull final DatagramSocket $this$unlistenMulticastGroupAwait, @NotNull final String multicastAddress, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.unlistenMulticastGroupAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$unlistenMulticastGroupAwait.unlistenMulticastGroup(multicastAddress, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object unlistenMulticastGroupAwait(@NotNull final DatagramSocket $this$unlistenMulticastGroupAwait, @NotNull final String multicastAddress, @NotNull final String networkInterface, @Nullable final String source, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.unlistenMulticastGroupAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$unlistenMulticastGroupAwait.unlistenMulticastGroup(multicastAddress, networkInterface, source, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object blockMulticastGroupAwait(@NotNull final DatagramSocket $this$blockMulticastGroupAwait, @NotNull final String multicastAddress, @NotNull final String sourceToBlock, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.blockMulticastGroupAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$blockMulticastGroupAwait.blockMulticastGroup(multicastAddress, sourceToBlock, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object blockMulticastGroupAwait(@NotNull final DatagramSocket $this$blockMulticastGroupAwait, @NotNull final String multicastAddress, @NotNull final String networkInterface, @NotNull final String sourceToBlock, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.blockMulticastGroupAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$blockMulticastGroupAwait.blockMulticastGroup(multicastAddress, networkInterface, sourceToBlock, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listenAwait(@NotNull final DatagramSocket $this$listenAwait, final int port, @NotNull final String host, @NotNull Continuation<? super DatagramSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<DatagramSocket>>, Unit>() { // from class: io.vertx.kotlin.core.datagram.DatagramSocketKt.listenAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<DatagramSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<DatagramSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listenAwait.listen(port, host, it);
            }
        }, continuation);
    }
}
