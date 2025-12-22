package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 5, 1}, k = 4, xi = 48, d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt", "kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelsKt.class */
public final class ChannelsKt {

    @NotNull
    public static final String DEFAULT_CLOSE_MESSAGE = "Channel was closed";

    @Deprecated(message = "Deprecated in the favour of 'trySendBlocking'. Consider handling the result of 'trySendBlocking' explicitly and rethrow exception if necessary", replaceWith = @ReplaceWith(expression = "trySendBlocking(element)", imports = {}), level = DeprecationLevel.WARNING)
    public static final <E> void sendBlocking(@NotNull SendChannel<? super E> sendChannel, E e) {
        ChannelsKt__ChannelsKt.sendBlocking(sendChannel, e);
    }

    @NotNull
    public static final <E> Object trySendBlocking(@NotNull SendChannel<? super E> sendChannel, E e) throws InterruptedException {
        return ChannelsKt__ChannelsKt.trySendBlocking(sendChannel, e);
    }

    @ObsoleteCoroutinesApi
    public static final <E, R> R consume(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        return (R) ChannelsKt__Channels_commonKt.consume(broadcastChannel, function1);
    }

    @Deprecated(message = "Deprecated in the favour of 'receiveCatching'", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}), level = DeprecationLevel.WARNING)
    @Nullable
    public static final <E> Object receiveOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super E> continuation) {
        return ChannelsKt__Channels_commonKt.receiveOrNull(receiveChannel, continuation);
    }

    @Deprecated(message = "Deprecated in the favour of 'onReceiveCatching'", level = DeprecationLevel.WARNING)
    @NotNull
    public static final <E> SelectClause1<E> onReceiveOrNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__Channels_commonKt.onReceiveOrNull(receiveChannel);
    }

    public static final <E, R> R consume(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        return (R) ChannelsKt__Channels_commonKt.consume(receiveChannel, function1);
    }

    @Nullable
    public static final <E> Object consumeEach(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(receiveChannel, function1, continuation);
    }

    @Nullable
    public static final <E> Object toList(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super List<? extends E>> continuation) {
        return ChannelsKt__Channels_commonKt.toList(receiveChannel, continuation);
    }

    @ObsoleteCoroutinesApi
    @Nullable
    public static final <E> Object consumeEach(@NotNull BroadcastChannel<E> broadcastChannel, @NotNull Function1<? super E, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        return ChannelsKt__Channels_commonKt.consumeEach(broadcastChannel, function1, continuation);
    }

    @PublishedApi
    public static final void cancelConsumed(@NotNull ReceiveChannel<?> receiveChannel, @Nullable Throwable cause) {
        ChannelsKt__Channels_commonKt.cancelConsumed(receiveChannel, cause);
    }

    @PublishedApi
    @NotNull
    public static final Function1<Throwable, Unit> consumesAll(@NotNull ReceiveChannel<?>... receiveChannelArr) {
        return ChannelsKt__DeprecatedKt.consumesAll(receiveChannelArr);
    }

    @PublishedApi
    @NotNull
    public static final <E> ReceiveChannel<E> filter(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext context, @NotNull Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ChannelsKt__DeprecatedKt.filter(receiveChannel, context, function2);
    }

    @PublishedApi
    @NotNull
    public static final <E> ReceiveChannel<E> filterNotNull(@NotNull ReceiveChannel<? extends E> receiveChannel) {
        return ChannelsKt__DeprecatedKt.filterNotNull(receiveChannel);
    }

    @PublishedApi
    @Nullable
    public static final <E, C extends SendChannel<? super E>> Object toChannel(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__DeprecatedKt.toChannel(receiveChannel, c, continuation);
    }

    @PublishedApi
    @Nullable
    public static final <E, C extends Collection<? super E>> Object toCollection(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull C c, @NotNull Continuation<? super C> continuation) {
        return ChannelsKt__DeprecatedKt.toCollection(receiveChannel, c, continuation);
    }

    @PublishedApi
    @Nullable
    public static final <K, V, M extends Map<? super K, ? super V>> Object toMap(@NotNull ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, @NotNull M m, @NotNull Continuation<? super M> continuation) {
        return ChannelsKt__DeprecatedKt.toMap(receiveChannel, m, continuation);
    }

    @PublishedApi
    @NotNull
    public static final <E, R> ReceiveChannel<R> map(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext context, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ChannelsKt__DeprecatedKt.map(receiveChannel, context, function2);
    }

    @PublishedApi
    @NotNull
    public static final <E, R> ReceiveChannel<R> mapIndexed(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext context, @NotNull Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ChannelsKt__DeprecatedKt.mapIndexed(receiveChannel, context, function3);
    }

    @PublishedApi
    @NotNull
    public static final <E, K> ReceiveChannel<E> distinctBy(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull CoroutineContext context, @NotNull Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ChannelsKt__DeprecatedKt.distinctBy(receiveChannel, context, function2);
    }

    @PublishedApi
    @Nullable
    public static final <E> Object toMutableSet(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull Continuation<? super Set<E>> continuation) {
        return ChannelsKt__DeprecatedKt.toMutableSet(receiveChannel, continuation);
    }

    @PublishedApi
    @NotNull
    public static final <E, R, V> ReceiveChannel<V> zip(@NotNull ReceiveChannel<? extends E> receiveChannel, @NotNull ReceiveChannel<? extends R> receiveChannel2, @NotNull CoroutineContext context, @NotNull Function2<? super E, ? super R, ? extends V> function2) {
        return ChannelsKt__DeprecatedKt.zip(receiveChannel, receiveChannel2, context, function2);
    }

    @PublishedApi
    @NotNull
    public static final Function1<Throwable, Unit> consumes(@NotNull ReceiveChannel<?> receiveChannel) {
        return ChannelsKt__DeprecatedKt.consumes(receiveChannel);
    }
}
