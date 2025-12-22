package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.ChannelResult;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Channel.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��4\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u001a\u001e\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0007\u001a>\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u001aX\u0010\n\u001a\u0002H\u000b\"\u0004\b��\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u000b0\bH\u0086\bø\u0001��ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0004\b\u0012\u0010\u0013\u001a^\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000b0\f\"\u0004\b��\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\bH\u0086\bø\u0001��ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0004\b\u0016\u0010\u0013\u001a^\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\f\"\u0004\b��\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\bH\u0086\bø\u0001��ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0004\b\u0017\u0010\u0013\u001a\\\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u000b0\f\"\u0004\b��\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\t0\bH\u0086\bø\u0001��ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 ��¢\u0006\u0004\b\u001a\u0010\u0013\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Channel", "Lkotlinx/coroutines/channels/Channel;", "E", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "onUndeliveredElement", "Lkotlin/Function1;", "", "getOrElse", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/channels/ChannelResult;", "onFailure", "", "Lkotlin/ParameterName;", "name", "exception", "getOrElse-WpGqRn0", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "onClosed", "action", "onClosed-WpGqRn0", "onFailure-WpGqRn0", "onSuccess", "value", "onSuccess-WpGqRn0", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelKt.class */
public final class ChannelKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getOrElse-WpGqRn0, reason: not valid java name */
    public static final <T> T m4219getOrElseWpGqRn0(@NotNull Object obj, @NotNull Function1<? super Throwable, ? extends T> function1) {
        return obj instanceof ChannelResult.Failed ? function1.invoke(ChannelResult.m4228exceptionOrNullimpl(obj)) : obj;
    }

    @NotNull
    /* renamed from: onSuccess-WpGqRn0, reason: not valid java name */
    public static final <T> Object m4220onSuccessWpGqRn0(@NotNull Object $this$onSuccess, @NotNull Function1<? super T, Unit> function1) {
        if (!($this$onSuccess instanceof ChannelResult.Failed)) {
            function1.invoke($this$onSuccess);
        }
        return $this$onSuccess;
    }

    @NotNull
    /* renamed from: onFailure-WpGqRn0, reason: not valid java name */
    public static final <T> Object m4221onFailureWpGqRn0(@NotNull Object $this$onFailure, @NotNull Function1<? super Throwable, Unit> function1) {
        if ($this$onFailure instanceof ChannelResult.Failed) {
            function1.invoke(ChannelResult.m4228exceptionOrNullimpl($this$onFailure));
        }
        return $this$onFailure;
    }

    @NotNull
    /* renamed from: onClosed-WpGqRn0, reason: not valid java name */
    public static final <T> Object m4222onClosedWpGqRn0(@NotNull Object $this$onClosed, @NotNull Function1<? super Throwable, Unit> function1) {
        if ($this$onClosed instanceof ChannelResult.Closed) {
            function1.invoke(ChannelResult.m4228exceptionOrNullimpl($this$onClosed));
        }
        return $this$onClosed;
    }

    public static /* synthetic */ Channel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return Channel(i, bufferOverflow, function1);
    }

    @NotNull
    public static final <E> Channel<E> Channel(int capacity, @NotNull BufferOverflow onBufferOverflow, @Nullable Function1<? super E, Unit> function1) {
        ArrayChannel arrayChannel;
        ArrayChannel arrayChannel2;
        switch (capacity) {
            case -2:
                return new ArrayChannel(onBufferOverflow == BufferOverflow.SUSPEND ? Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core() : 1, onBufferOverflow, function1);
            case -1:
                if (!(onBufferOverflow == BufferOverflow.SUSPEND)) {
                    throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
                }
                return new ConflatedChannel(function1);
            case 0:
                if (onBufferOverflow == BufferOverflow.SUSPEND) {
                    arrayChannel2 = new RendezvousChannel(function1);
                } else {
                    arrayChannel2 = new ArrayChannel(1, onBufferOverflow, function1);
                }
                return arrayChannel2;
            case Integer.MAX_VALUE:
                return new LinkedListChannel(function1);
            default:
                if (capacity == 1 && onBufferOverflow == BufferOverflow.DROP_OLDEST) {
                    arrayChannel = new ConflatedChannel(function1);
                } else {
                    arrayChannel = new ArrayChannel(capacity, onBufferOverflow, function1);
                }
                return arrayChannel;
        }
    }

    @Deprecated(message = "Since 1.4.0, binary compatibility with earlier versions", level = DeprecationLevel.HIDDEN)
    public static final /* synthetic */ Channel Channel(int capacity) {
        return Channel$default(capacity, null, null, 6, null);
    }

    public static /* synthetic */ Channel Channel$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return Channel(i);
    }
}
