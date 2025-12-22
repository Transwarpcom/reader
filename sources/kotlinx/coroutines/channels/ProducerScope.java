package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.channels.SendChannel;
import org.jetbrains.annotations.NotNull;

/* compiled from: Produce.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018��*\u0006\b��\u0010\u0001 ��2\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/ProducerScope;", "E", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/channels/SendChannel;", "channel", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "kotlinx-coroutines-core"})
@ExperimentalCoroutinesApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ProducerScope.class */
public interface ProducerScope<E> extends CoroutineScope, SendChannel<E> {
    @NotNull
    SendChannel<E> getChannel();

    /* compiled from: Produce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ProducerScope$DefaultImpls.class */
    public static final class DefaultImpls {
        @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}), level = DeprecationLevel.WARNING)
        public static <E> boolean offer(@NotNull ProducerScope<? super E> producerScope, E e) {
            return SendChannel.DefaultImpls.offer(producerScope, e);
        }
    }
}
