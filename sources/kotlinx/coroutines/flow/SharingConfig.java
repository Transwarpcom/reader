package kotlinx.coroutines.flow;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.channels.BufferOverflow;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* compiled from: Share.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B+\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0010\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n��R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/flow/SharingConfig;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "upstream", "Lkotlinx/coroutines/flow/Flow;", "extraBufferCapacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/Flow;ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/coroutines/CoroutineContext;)V", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/SharingConfig.class */
final class SharingConfig<T> {

    @JvmField
    @NotNull
    public final Flow<T> upstream;

    @JvmField
    public final int extraBufferCapacity;

    @JvmField
    @NotNull
    public final BufferOverflow onBufferOverflow;

    @JvmField
    @NotNull
    public final CoroutineContext context;

    /* JADX WARN: Multi-variable type inference failed */
    public SharingConfig(@NotNull Flow<? extends T> flow, int extraBufferCapacity, @NotNull BufferOverflow onBufferOverflow, @NotNull CoroutineContext context) {
        this.upstream = flow;
        this.extraBufferCapacity = extraBufferCapacity;
        this.onBufferOverflow = onBufferOverflow;
        this.context = context;
    }
}
