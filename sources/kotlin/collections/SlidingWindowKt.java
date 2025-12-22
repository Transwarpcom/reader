package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SlidingWindow.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��*\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u001a\u0018\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H��\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b��\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH��\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b��\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH��¨\u0006\u000f"}, d2 = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/SlidingWindowKt.class */
public final class SlidingWindowKt {
    public static final void checkWindowSizeStep(int size, int step) {
        String str;
        if (!(size > 0 && step > 0)) {
            if (size != step) {
                str = "Both size " + size + " and step " + step + " must be greater than zero.";
            } else {
                str = "size " + size + " must be greater than zero.";
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    @NotNull
    public static final <T> Sequence<List<T>> windowedSequence(@NotNull final Sequence<? extends T> windowedSequence, final int i, final int i2, final boolean z, final boolean z2) {
        Intrinsics.checkNotNullParameter(windowedSequence, "$this$windowedSequence");
        checkWindowSizeStep(i, i2);
        return new Sequence<List<? extends T>>() { // from class: kotlin.collections.SlidingWindowKt$windowedSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<List<? extends T>> iterator() {
                return SlidingWindowKt.windowedIterator(windowedSequence.iterator(), i, i2, z, z2);
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: SlidingWindow.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\u0014\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
    @DebugMetadata(f = "SlidingWindow.kt", l = {34, 40, 49, 55, 58}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlin.collections.SlidingWindowKt$windowedIterator$1")
    /* renamed from: kotlin.collections.SlidingWindowKt$windowedIterator$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/SlidingWindowKt$windowedIterator$1.class */
    static final class AnonymousClass1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int I$0;
        int label;
        final /* synthetic */ int $size;
        final /* synthetic */ int $step;
        final /* synthetic */ Iterator $iterator;
        final /* synthetic */ boolean $reuseBuffer;
        final /* synthetic */ boolean $partialWindows;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
            super(2, continuation);
            this.$size = i;
            this.$step = i2;
            this.$iterator = it;
            this.$reuseBuffer = z;
            this.$partialWindows = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
            Intrinsics.checkNotNullParameter(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, completion);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:111:? A[PHI: r8 r11
  PHI (r8v3 '$this$iterator' kotlin.sequences.SequenceScope) = (r8v0 '$this$iterator' kotlin.sequences.SequenceScope), (r8v4 '$this$iterator' kotlin.sequences.SequenceScope) binds: [B:90:0x023f, B:60:0x023c] A[DONT_GENERATE, DONT_INLINE]
  PHI (r11v3 'buffer' kotlin.collections.RingBuffer) = (r11v0 'buffer' kotlin.collections.RingBuffer), (r11v5 'buffer' kotlin.collections.RingBuffer) binds: [B:90:0x023f, B:60:0x023c] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0107  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x012f  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x016b  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0198  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x024b  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x02c3  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x02c7  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x02cb  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x007b  */
        /* JADX WARN: Type inference failed for: r0v101, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r0v103, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r0v123, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r0v36, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r0v54, types: [kotlin.sequences.SequenceScope] */
        /* JADX WARN: Type inference failed for: r0v63, types: [kotlin.collections.RingBuffer] */
        /* JADX WARN: Type inference failed for: r0v70, types: [kotlin.sequences.SequenceScope] */
        /* JADX WARN: Type inference failed for: r0v76, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r0v78, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r0v93, types: [java.util.ArrayList] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 771
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @NotNull
    public static final <T> Iterator<List<T>> windowedIterator(@NotNull Iterator<? extends T> iterator, int size, int step, boolean partialWindows, boolean reuseBuffer) {
        Intrinsics.checkNotNullParameter(iterator, "iterator");
        return !iterator.hasNext() ? EmptyIterator.INSTANCE : SequencesKt.iterator(new AnonymousClass1(size, step, iterator, reuseBuffer, partialWindows, null));
    }
}
