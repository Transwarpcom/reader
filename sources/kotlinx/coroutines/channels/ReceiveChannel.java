package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Channel.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018��*\u0006\b��\u0010\u0001 \u00012\u00020\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0017J\u0014\u0010\u0014\u001a\u00020\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H'J\u001a\u0010\u0014\u001a\u00020\u00152\u0010\b\u0002\u0010\u0016\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019H&J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028��0\u001bH¦\u0002J\u000f\u0010\u001c\u001a\u0004\u0018\u00018��H\u0017¢\u0006\u0002\u0010\u001dJ\u0011\u0010\u001e\u001a\u00028��H¦@ø\u0001��¢\u0006\u0002\u0010\u001fJ\"\u0010 \u001a\b\u0012\u0004\u0012\u00028��0\u000fH¦@ø\u0001��ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b!\u0010\u001fJ\u0013\u0010\"\u001a\u0004\u0018\u00018��H\u0097@ø\u0001��¢\u0006\u0002\u0010\u001fJ\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00028��0\u000fH&ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b$\u0010\u001dR\u001a\u0010\u0003\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0006\u001a\u0004\b\b\u0010\u0007R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00028��0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR!\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000f0\u000bX¦\u0004ø\u0001��¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\"\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\u000b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0006\u001a\u0004\b\u0013\u0010\r\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveChannel;", "E", "", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isEmpty", "isEmpty$annotations", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull$annotations", "getOnReceiveOrNull", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching", "receiveCatching-JP2dKIU", "receiveOrNull", "tryReceive", "tryReceive-PtdJZtk", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ReceiveChannel.class */
public interface ReceiveChannel<E> {

    /* compiled from: Channel.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Channel.kt", l = {355}, i = {}, s = {}, n = {}, m = "receiveOrNull", c = "kotlinx.coroutines.channels.ReceiveChannel$DefaultImpls")
    /* renamed from: kotlinx.coroutines.channels.ReceiveChannel$receiveOrNull$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ReceiveChannel$receiveOrNull$1.class */
    static final class AnonymousClass1<E> extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DefaultImpls.receiveOrNull(null, this);
        }
    }

    boolean isClosedForReceive();

    boolean isEmpty();

    @Nullable
    Object receive(@NotNull Continuation<? super E> continuation);

    @NotNull
    SelectClause1<E> getOnReceive();

    @Nullable
    /* renamed from: receiveCatching-JP2dKIU */
    Object mo4209receiveCatchingJP2dKIU(@NotNull Continuation<? super ChannelResult<? extends E>> continuation);

    @NotNull
    SelectClause1<ChannelResult<E>> getOnReceiveCatching();

    @NotNull
    /* renamed from: tryReceive-PtdJZtk */
    Object mo4210tryReceivePtdJZtk();

    @NotNull
    ChannelIterator<E> iterator();

    void cancel(@Nullable CancellationException cancellationException);

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    /* synthetic */ void cancel();

    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    /* synthetic */ boolean cancel(Throwable th);

    @Deprecated(message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}), level = DeprecationLevel.WARNING)
    @Nullable
    E poll();

    @Deprecated(message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}), level = DeprecationLevel.ERROR)
    @LowPriorityInOverloadResolution
    @Nullable
    Object receiveOrNull(@NotNull Continuation<? super E> continuation);

    @NotNull
    SelectClause1<E> getOnReceiveOrNull();

    /* compiled from: Channel.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ReceiveChannel$DefaultImpls.class */
    public static final class DefaultImpls {
        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isClosedForReceive$annotations() {
        }

        @ExperimentalCoroutinesApi
        public static /* synthetic */ void isEmpty$annotations() {
        }

        @Deprecated(message = "Deprecated in favor of onReceiveCatching extension", replaceWith = @ReplaceWith(expression = "onReceiveCatching", imports = {}), level = DeprecationLevel.ERROR)
        public static /* synthetic */ void getOnReceiveOrNull$annotations() {
        }

        public static /* synthetic */ void cancel$default(ReceiveChannel receiveChannel, CancellationException cancellationException, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i & 1) != 0) {
                cancellationException = null;
            }
            receiveChannel.cancel(cancellationException);
        }

        public static /* synthetic */ boolean cancel$default(ReceiveChannel receiveChannel, Throwable th, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
            }
            if ((i & 1) != 0) {
                th = null;
            }
            return receiveChannel.cancel(th);
        }

        @Deprecated(message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}), level = DeprecationLevel.WARNING)
        @Nullable
        public static <E> E poll(@NotNull ReceiveChannel<? extends E> receiveChannel) throws Throwable {
            Object objMo4210tryReceivePtdJZtk = receiveChannel.mo4210tryReceivePtdJZtk();
            if (ChannelResult.m4223isSuccessimpl(objMo4210tryReceivePtdJZtk)) {
                return (E) ChannelResult.m4227getOrThrowimpl(objMo4210tryReceivePtdJZtk);
            }
            Throwable thM4228exceptionOrNullimpl = ChannelResult.m4228exceptionOrNullimpl(objMo4210tryReceivePtdJZtk);
            if (thM4228exceptionOrNullimpl == null) {
                return null;
            }
            throw StackTraceRecoveryKt.recoverStackTrace(thM4228exceptionOrNullimpl);
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
        @kotlin.Deprecated(message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @kotlin.ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}), level = kotlin.DeprecationLevel.ERROR)
        @kotlin.internal.LowPriorityInOverloadResolution
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static <E> java.lang.Object receiveOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.channels.ReceiveChannel<? extends E> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super E> r6) throws java.lang.Throwable {
            /*
                r0 = r6
                boolean r0 = r0 instanceof kotlinx.coroutines.channels.ReceiveChannel.AnonymousClass1
                if (r0 == 0) goto L24
                r0 = r6
                kotlinx.coroutines.channels.ReceiveChannel$receiveOrNull$1 r0 = (kotlinx.coroutines.channels.ReceiveChannel.AnonymousClass1) r0
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
                kotlinx.coroutines.channels.ReceiveChannel$receiveOrNull$1 r0 = new kotlinx.coroutines.channels.ReceiveChannel$receiveOrNull$1
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
                    case 1: goto L69;
                    default: goto L78;
                }
            L50:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r5
                r1 = r8
                r2 = r8
                r3 = 1
                r2.label = r3
                java.lang.Object r0 = r0.mo4209receiveCatchingJP2dKIU(r1)
                r1 = r0
                r2 = r9
                if (r1 != r2) goto L74
                r1 = r9
                return r1
            L69:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
                kotlinx.coroutines.channels.ChannelResult r0 = (kotlinx.coroutines.channels.ChannelResult) r0
                java.lang.Object r0 = r0.m4234unboximpl()
            L74:
                java.lang.Object r0 = kotlinx.coroutines.channels.ChannelResult.m4226getOrNullimpl(r0)
                return r0
            L78:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ReceiveChannel.DefaultImpls.receiveOrNull(kotlinx.coroutines.channels.ReceiveChannel, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @NotNull
        public static <E> SelectClause1<E> getOnReceiveOrNull(@NotNull final ReceiveChannel<? extends E> receiveChannel) {
            return new SelectClause1<E>() { // from class: kotlinx.coroutines.channels.ReceiveChannel$onReceiveOrNull$1
                @Override // kotlinx.coroutines.selects.SelectClause1
                @InternalCoroutinesApi
                public <R> void registerSelectClause1(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
                    receiveChannel.getOnReceiveCatching().registerSelectClause1(selectInstance, new ReceiveChannel$onReceiveOrNull$1$registerSelectClause1$1(function2, null));
                }
            };
        }
    }
}
