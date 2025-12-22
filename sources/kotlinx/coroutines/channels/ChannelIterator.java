package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Channel.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018��*\u0006\b��\u0010\u0001 \u00012\u00020\u0002J\u0011\u0010\u0003\u001a\u00020\u0004H¦Bø\u0001��¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00028��H¦\u0002¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00028��H\u0097@ø\u0001��¢\u0006\u0004\b\u0006\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ChannelIterator;", "E", "", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "next", "()Ljava/lang/Object;", "next0", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelIterator.class */
public interface ChannelIterator<E> {
    @Nullable
    Object hasNext(@NotNull Continuation<? super Boolean> continuation);

    @Deprecated(message = "Since 1.3.0, binary compatibility with versions <= 1.2.x", level = DeprecationLevel.HIDDEN)
    @JvmName(name = "next")
    /* synthetic */ Object next(Continuation continuation);

    E next();

    /* compiled from: Channel.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelIterator$DefaultImpls.class */
    public static final class DefaultImpls {
        /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
        @kotlin.Deprecated(message = "Since 1.3.0, binary compatibility with versions <= 1.2.x", level = kotlin.DeprecationLevel.HIDDEN)
        @kotlin.jvm.JvmName(name = "next")
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static /* synthetic */ java.lang.Object next(kotlinx.coroutines.channels.ChannelIterator r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
            /*
                r0 = r6
                boolean r0 = r0 instanceof kotlinx.coroutines.channels.ChannelIterator$next0$1
                if (r0 == 0) goto L24
                r0 = r6
                kotlinx.coroutines.channels.ChannelIterator$next0$1 r0 = (kotlinx.coroutines.channels.ChannelIterator$next0$1) r0
                r8 = r0
                r0 = r8
                int r0 = r0.label
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r0 = r0 & r1
                if (r0 == 0) goto L24
                r0 = r8
                r1 = r0
                int r1 = r1.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                int r1 = r1 - r2
                r0.label = r1
                goto L2d
            L24:
                kotlinx.coroutines.channels.ChannelIterator$next0$1 r0 = new kotlinx.coroutines.channels.ChannelIterator$next0$1
                r1 = r0
                r2 = r6
                r1.<init>(r2)
                r8 = r0
            L2d:
                r0 = r8
                java.lang.Object r0 = r0.result
                r7 = r0
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                r9 = r0
                r0 = r8
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L50;
                    case 1: goto L6e;
                    default: goto L95;
                }
            L50:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r5
                r1 = r8
                r2 = r8
                r3 = r5
                r2.L$0 = r3
                r2 = r8
                r3 = 1
                r2.label = r3
                java.lang.Object r0 = r0.hasNext(r1)
                r1 = r0
                r2 = r9
                if (r1 != r2) goto L7b
                r1 = r9
                return r1
            L6e:
                r0 = r8
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
                r5 = r0
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            L7b:
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r0 = r0.booleanValue()
                if (r0 != 0) goto L8e
                kotlinx.coroutines.channels.ClosedReceiveChannelException r0 = new kotlinx.coroutines.channels.ClosedReceiveChannelException
                r1 = r0
                java.lang.String r2 = "Channel was closed"
                r1.<init>(r2)
                throw r0
            L8e:
                r0 = r5
                java.lang.Object r0 = r0.next()
                return r0
            L95:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ChannelIterator.DefaultImpls.next(kotlinx.coroutines.channels.ChannelIterator, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
