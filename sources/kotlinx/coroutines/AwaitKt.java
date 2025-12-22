package kotlinx.coroutines;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Await.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��*\n��\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a=\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004\"\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0004\"\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a-\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u001a\u001b\u0010\u0007\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\n0\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"awaitAll", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "deferreds", "", "Lkotlinx/coroutines/Deferred;", "([Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinAll", "", "jobs", "Lkotlinx/coroutines/Job;", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/AwaitKt.class */
public final class AwaitKt {

    /* compiled from: Await.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Await.kt", l = {54}, i = {}, s = {}, n = {}, m = "joinAll", c = "kotlinx.coroutines.AwaitKt")
    /* renamed from: kotlinx.coroutines.AwaitKt$joinAll$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/AwaitKt$joinAll$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int I$0;
        int I$1;
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
            return AwaitKt.joinAll((Job[]) null, this);
        }
    }

    /* compiled from: Await.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Await.kt", l = {66}, i = {}, s = {}, n = {}, m = "joinAll", c = "kotlinx.coroutines.AwaitKt")
    /* renamed from: kotlinx.coroutines.AwaitKt$joinAll$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/AwaitKt$joinAll$3.class */
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return AwaitKt.joinAll((Collection<? extends Job>) null, this);
        }
    }

    @Nullable
    public static final <T> Object awaitAll(@NotNull Deferred<? extends T>[] deferredArr, @NotNull Continuation<? super List<? extends T>> continuation) {
        return deferredArr.length == 0 ? CollectionsKt.emptyList() : new AwaitAll(deferredArr).await(continuation);
    }

    @Nullable
    public static final <T> Object awaitAll(@NotNull Collection<? extends Deferred<? extends T>> collection, @NotNull Continuation<? super List<? extends T>> continuation) {
        if (collection.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        Object[] array = collection.toArray(new Deferred[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return new AwaitAll((Deferred[]) array).await(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object joinAll(@org.jetbrains.annotations.NotNull kotlinx.coroutines.Job[] r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.joinAll(kotlinx.coroutines.Job[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object joinAll(@org.jetbrains.annotations.NotNull java.util.Collection<? extends kotlinx.coroutines.Job> r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r6) throws java.lang.Throwable {
        /*
            r0 = r6
            boolean r0 = r0 instanceof kotlinx.coroutines.AwaitKt.AnonymousClass3
            if (r0 == 0) goto L27
            r0 = r6
            kotlinx.coroutines.AwaitKt$joinAll$3 r0 = (kotlinx.coroutines.AwaitKt.AnonymousClass3) r0
            r14 = r0
            r0 = r14
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r14
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L31
        L27:
            kotlinx.coroutines.AwaitKt$joinAll$3 r0 = new kotlinx.coroutines.AwaitKt$joinAll$3
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            r14 = r0
        L31:
            r0 = r14
            java.lang.Object r0 = r0.result
            r13 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r15 = r0
            r0 = r14
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto La8;
                default: goto Lc7;
            }
        L58:
            r0 = r13
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r5
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = r7
            java.util.Iterator r0 = r0.iterator()
            r9 = r0
        L6c:
            r0 = r9
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Lc2
            r0 = r9
            java.lang.Object r0 = r0.next()
            r10 = r0
            r0 = r10
            kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r11
            r1 = r14
            r2 = r14
            r3 = r9
            r2.L$0 = r3
            r2 = r14
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = r0.join(r1)
            r1 = r0
            r2 = r15
            if (r1 != r2) goto Lbe
            r1 = r15
            return r1
        La8:
            r0 = 0
            r8 = r0
            r0 = 0
            r12 = r0
            r0 = r14
            java.lang.Object r0 = r0.L$0
            java.util.Iterator r0 = (java.util.Iterator) r0
            r9 = r0
            r0 = r13
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r13
        Lbe:
            goto L6c
        Lc2:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lc7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AwaitKt.joinAll(java.util.Collection, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
