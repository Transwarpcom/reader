package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;

/* compiled from: BroadcastChannel.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\u001a\u001c\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"BroadcastChannel", "Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "capacity", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/BroadcastChannelKt.class */
public final class BroadcastChannelKt {
    @ObsoleteCoroutinesApi
    @NotNull
    public static final <E> BroadcastChannel<E> BroadcastChannel(int capacity) {
        switch (capacity) {
            case -2:
                return new ArrayBroadcastChannel(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core());
            case -1:
                return new ConflatedBroadcastChannel();
            case 0:
                throw new IllegalArgumentException("Unsupported 0 capacity for BroadcastChannel");
            case Integer.MAX_VALUE:
                throw new IllegalArgumentException("Unsupported UNLIMITED capacity for BroadcastChannel");
            default:
                return new ArrayBroadcastChannel(capacity);
        }
    }
}
