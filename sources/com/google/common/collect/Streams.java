package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.LongMath;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams.class */
public final class Streams {

    @Beta
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$DoubleFunctionWithIndex.class */
    public interface DoubleFunctionWithIndex<R> {
        R apply(double d, long j);
    }

    @Beta
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$FunctionWithIndex.class */
    public interface FunctionWithIndex<T, R> {
        R apply(T t, long j);
    }

    @Beta
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$IntFunctionWithIndex.class */
    public interface IntFunctionWithIndex<R> {
        R apply(int i, long j);
    }

    @Beta
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$LongFunctionWithIndex.class */
    public interface LongFunctionWithIndex<R> {
        R apply(long j, long j2);
    }

    public static <T> Stream<T> stream(Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).stream();
        }
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    @Beta
    @Deprecated
    public static <T> Stream<T> stream(Collection<T> collection) {
        return collection.stream();
    }

    @Beta
    public static <T> Stream<T> stream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
    }

    @Beta
    public static <T> Stream<T> stream(Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
    }

    @Beta
    public static <T> Stream<T> stream(java.util.Optional<T> optional) {
        return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
    }

    @Beta
    public static IntStream stream(OptionalInt optional) {
        return optional.isPresent() ? IntStream.of(optional.getAsInt()) : IntStream.empty();
    }

    @Beta
    public static LongStream stream(OptionalLong optional) {
        return optional.isPresent() ? LongStream.of(optional.getAsLong()) : LongStream.empty();
    }

    @Beta
    public static DoubleStream stream(OptionalDouble optional) {
        return optional.isPresent() ? DoubleStream.of(optional.getAsDouble()) : DoubleStream.empty();
    }

    @SafeVarargs
    public static <T> Stream<T> concat(Stream<? extends T>... streams) {
        boolean isParallel = false;
        int characteristics = 336;
        long estimatedSize = 0;
        ImmutableList.Builder<Spliterator<? extends T>> splitrsBuilder = new ImmutableList.Builder<>(streams.length);
        for (Stream<? extends T> stream : streams) {
            isParallel |= stream.isParallel();
            Spliterator<? extends T> splitr = stream.spliterator();
            splitrsBuilder.add((ImmutableList.Builder<Spliterator<? extends T>>) splitr);
            characteristics &= splitr.characteristics();
            estimatedSize = LongMath.saturatedAdd(estimatedSize, splitr.estimateSize());
        }
        return (Stream) StreamSupport.stream(CollectSpliterators.flatMap(splitrsBuilder.build().spliterator(), splitr2 -> {
            return splitr2;
        }, characteristics, estimatedSize), isParallel).onClose(() -> {
            for (Stream stream2 : streams) {
                stream2.close();
            }
        });
    }

    public static IntStream concat(IntStream... streams) {
        return Stream.of((Object[]) streams).flatMapToInt(stream -> {
            return stream;
        });
    }

    public static LongStream concat(LongStream... streams) {
        return Stream.of((Object[]) streams).flatMapToLong(stream -> {
            return stream;
        });
    }

    public static DoubleStream concat(DoubleStream... streams) {
        return Stream.of((Object[]) streams).flatMapToDouble(stream -> {
            return stream;
        });
    }

    @Beta
    public static <A, B, R> Stream<R> zip(Stream<A> streamA, Stream<B> streamB, final BiFunction<? super A, ? super B, R> function) {
        Preconditions.checkNotNull(streamA);
        Preconditions.checkNotNull(streamB);
        Preconditions.checkNotNull(function);
        boolean isParallel = streamA.isParallel() || streamB.isParallel();
        Spliterator<A> splitrA = streamA.spliterator();
        Spliterator<B> splitrB = streamB.spliterator();
        int characteristics = splitrA.characteristics() & splitrB.characteristics() & 80;
        final Iterator<A> itrA = Spliterators.iterator(splitrA);
        final Iterator<B> itrB = Spliterators.iterator(splitrB);
        Stream stream = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Math.min(splitrA.estimateSize(), splitrB.estimateSize()), characteristics) { // from class: com.google.common.collect.Streams.1
            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super R> consumer) {
                if (itrA.hasNext() && itrB.hasNext()) {
                    consumer.accept((Object) function.apply(itrA.next(), itrB.next()));
                    return true;
                }
                return false;
            }
        }, isParallel);
        streamA.getClass();
        Stream stream2 = (Stream) stream.onClose(streamA::close);
        streamB.getClass();
        return (Stream) stream2.onClose(streamB::close);
    }

    @Beta
    public static <A, B> void forEachPair(Stream<A> streamA, Stream<B> streamB, BiConsumer<? super A, ? super B> consumer) {
        Preconditions.checkNotNull(consumer);
        if (streamA.isParallel() || streamB.isParallel()) {
            zip(streamA, streamB, TemporaryPair::new).forEach(pair -> {
                consumer.accept(pair.a, pair.b);
            });
            return;
        }
        Iterator<A> iterA = streamA.iterator();
        Iterator<B> iterB = streamB.iterator();
        while (iterA.hasNext() && iterB.hasNext()) {
            consumer.accept(iterA.next(), iterB.next());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$TemporaryPair.class */
    private static class TemporaryPair<A, B> {
        final A a;
        final B b;

        TemporaryPair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }

    @Beta
    public static <T, R> Stream<R> mapWithIndex(Stream<T> stream, final FunctionWithIndex<? super T, ? extends R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        Spliterator<T> fromSpliterator = stream.spliterator();
        if (!fromSpliterator.hasCharacteristics(16384)) {
            final Iterator<T> fromIterator = Spliterators.iterator(fromSpliterator);
            Stream stream2 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(fromSpliterator.estimateSize(), fromSpliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.2
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (fromIterator.hasNext()) {
                        FunctionWithIndex functionWithIndex = function;
                        Object next = fromIterator.next();
                        long j = this.index;
                        this.index = j + 1;
                        consumer.accept((Object) functionWithIndex.apply(next, j));
                        return true;
                    }
                    return false;
                }
            }, isParallel);
            stream.getClass();
            return (Stream) stream2.onClose(stream::close);
        }
        Stream stream3 = StreamSupport.stream(new C1Splitr(fromSpliterator, 0L, function), isParallel);
        stream.getClass();
        return (Stream) stream3.onClose(stream::close);
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* renamed from: com.google.common.collect.Streams$1Splitr, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$1Splitr.class */
    class C1Splitr<R, T> extends MapWithIndexSpliterator<Spliterator<T>, R, C1Splitr> implements Consumer<T> {
        T holder;
        final /* synthetic */ FunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Failed to calculate best type for var: r9v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException
         */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        C1Splitr(Spliterator spliterator, Spliterator<T> spliterator2, long j) {
            FunctionWithIndex functionWithIndex;
            super(spliterator, spliterator2);
            this.val$function = functionWithIndex;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (this.fromSpliterator.tryAdvance(this)) {
                try {
                    FunctionWithIndex functionWithIndex = this.val$function;
                    T t = this.holder;
                    long j = this.index;
                    this.index = j + 1;
                    consumer.accept((Object) functionWithIndex.apply(t, j));
                    return true;
                } finally {
                    this.holder = null;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C1Splitr createSplit(Spliterator<T> from, long i) {
            return new C1Splitr(from, i, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Spliterator$OfInt] */
    @Beta
    public static <R> Stream<R> mapWithIndex(IntStream stream, final IntFunctionWithIndex<R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        ?? Spliterator = stream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfInt fromIterator = Spliterators.iterator((Spliterator.OfInt) Spliterator);
            Stream stream2 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.3
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (fromIterator.hasNext()) {
                        IntFunctionWithIndex intFunctionWithIndex = function;
                        int iNextInt = fromIterator.nextInt();
                        long j = this.index;
                        this.index = j + 1;
                        consumer.accept((Object) intFunctionWithIndex.apply(iNextInt, j));
                        return true;
                    }
                    return false;
                }
            }, isParallel);
            stream.getClass();
            return (Stream) stream2.onClose(stream::close);
        }
        Stream stream3 = StreamSupport.stream(new C2Splitr(Spliterator, 0L, function), isParallel);
        stream.getClass();
        return (Stream) stream3.onClose(stream::close);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$2Splitr, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$2Splitr.class */
    class C2Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfInt, R, C2Splitr> implements IntConsumer, Spliterator<R> {
        int holder;
        final /* synthetic */ IntFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Failed to calculate best type for var: r9v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException
         */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        C2Splitr(Spliterator.OfInt splitr, Spliterator.OfInt ofInt, long j) {
            IntFunctionWithIndex intFunctionWithIndex;
            super(splitr, ofInt);
            this.val$function = intFunctionWithIndex;
        }

        @Override // java.util.function.IntConsumer
        public void accept(int t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (((Spliterator.OfInt) this.fromSpliterator).tryAdvance((IntConsumer) this)) {
                IntFunctionWithIndex intFunctionWithIndex = this.val$function;
                int i = this.holder;
                long j = this.index;
                this.index = j + 1;
                consumer.accept((Object) intFunctionWithIndex.apply(i, j));
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C2Splitr createSplit(Spliterator.OfInt from, long i) {
            return new C2Splitr(from, i, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Spliterator$OfLong] */
    @Beta
    public static <R> Stream<R> mapWithIndex(LongStream stream, final LongFunctionWithIndex<R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        ?? Spliterator = stream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfLong fromIterator = Spliterators.iterator((Spliterator.OfLong) Spliterator);
            Stream stream2 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.4
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (fromIterator.hasNext()) {
                        LongFunctionWithIndex longFunctionWithIndex = function;
                        long jNextLong = fromIterator.nextLong();
                        long j = this.index;
                        this.index = j + 1;
                        consumer.accept((Object) longFunctionWithIndex.apply(jNextLong, j));
                        return true;
                    }
                    return false;
                }
            }, isParallel);
            stream.getClass();
            return (Stream) stream2.onClose(stream::close);
        }
        Stream stream3 = StreamSupport.stream(new C3Splitr(Spliterator, 0L, function), isParallel);
        stream.getClass();
        return (Stream) stream3.onClose(stream::close);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$3Splitr, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$3Splitr.class */
    class C3Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfLong, R, C3Splitr> implements LongConsumer, Spliterator<R> {
        long holder;
        final /* synthetic */ LongFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Failed to calculate best type for var: r9v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException
         */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        C3Splitr(Spliterator.OfLong splitr, Spliterator.OfLong ofLong, long j) {
            LongFunctionWithIndex longFunctionWithIndex;
            super(splitr, ofLong);
            this.val$function = longFunctionWithIndex;
        }

        @Override // java.util.function.LongConsumer
        public void accept(long t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (((Spliterator.OfLong) this.fromSpliterator).tryAdvance((LongConsumer) this)) {
                LongFunctionWithIndex longFunctionWithIndex = this.val$function;
                long j = this.holder;
                long j2 = this.index;
                this.index = j2 + 1;
                consumer.accept((Object) longFunctionWithIndex.apply(j, j2));
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C3Splitr createSplit(Spliterator.OfLong from, long i) {
            return new C3Splitr(from, i, this.val$function);
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.Spliterator$OfDouble] */
    @Beta
    public static <R> Stream<R> mapWithIndex(DoubleStream stream, final DoubleFunctionWithIndex<R> function) {
        Preconditions.checkNotNull(stream);
        Preconditions.checkNotNull(function);
        boolean isParallel = stream.isParallel();
        ?? Spliterator = stream.spliterator();
        if (!Spliterator.hasCharacteristics(16384)) {
            final PrimitiveIterator.OfDouble fromIterator = Spliterators.iterator((Spliterator.OfDouble) Spliterator);
            Stream stream2 = StreamSupport.stream(new Spliterators.AbstractSpliterator<R>(Spliterator.estimateSize(), Spliterator.characteristics() & 80) { // from class: com.google.common.collect.Streams.5
                long index = 0;

                @Override // java.util.Spliterator
                public boolean tryAdvance(Consumer<? super R> consumer) {
                    if (fromIterator.hasNext()) {
                        DoubleFunctionWithIndex doubleFunctionWithIndex = function;
                        double dNextDouble = fromIterator.nextDouble();
                        long j = this.index;
                        this.index = j + 1;
                        consumer.accept((Object) doubleFunctionWithIndex.apply(dNextDouble, j));
                        return true;
                    }
                    return false;
                }
            }, isParallel);
            stream.getClass();
            return (Stream) stream2.onClose(stream::close);
        }
        Stream stream3 = StreamSupport.stream(new C4Splitr(Spliterator, 0L, function), isParallel);
        stream.getClass();
        return (Stream) stream3.onClose(stream::close);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* renamed from: com.google.common.collect.Streams$4Splitr, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$4Splitr.class */
    class C4Splitr<R> extends MapWithIndexSpliterator<Spliterator.OfDouble, R, C4Splitr> implements DoubleConsumer, Spliterator<R> {
        double holder;
        final /* synthetic */ DoubleFunctionWithIndex val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Failed to calculate best type for var: r9v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException
         */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        C4Splitr(Spliterator.OfDouble splitr, Spliterator.OfDouble ofDouble, long j) {
            DoubleFunctionWithIndex doubleFunctionWithIndex;
            super(splitr, ofDouble);
            this.val$function = doubleFunctionWithIndex;
        }

        @Override // java.util.function.DoubleConsumer
        public void accept(double t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super R> consumer) {
            if (((Spliterator.OfDouble) this.fromSpliterator).tryAdvance((DoubleConsumer) this)) {
                DoubleFunctionWithIndex doubleFunctionWithIndex = this.val$function;
                double d = this.holder;
                long j = this.index;
                this.index = j + 1;
                consumer.accept((Object) doubleFunctionWithIndex.apply(d, j));
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Streams.MapWithIndexSpliterator
        public C4Splitr createSplit(Spliterator.OfDouble from, long i) {
            return new C4Splitr(from, i, this.val$function);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$MapWithIndexSpliterator.class */
    private static abstract class MapWithIndexSpliterator<F extends Spliterator<?>, R, S extends MapWithIndexSpliterator<F, R, S>> implements Spliterator<R> {
        final F fromSpliterator;
        long index;

        abstract S createSplit(F f, long j);

        MapWithIndexSpliterator(F fromSpliterator, long index) {
            this.fromSpliterator = fromSpliterator;
            this.index = index;
        }

        @Override // java.util.Spliterator
        public S trySplit() {
            Spliterator spliteratorTrySplit = this.fromSpliterator.trySplit();
            if (spliteratorTrySplit == null) {
                return null;
            }
            S s = (S) createSplit(spliteratorTrySplit, this.index);
            this.index += spliteratorTrySplit.getExactSizeIfKnown();
            return s;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.fromSpliterator.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.fromSpliterator.characteristics() & 16464;
        }
    }

    /* renamed from: com.google.common.collect.Streams$1OptionalState, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Streams$1OptionalState.class */
    class C1OptionalState {
        boolean set = false;
        T value = null;

        C1OptionalState() {
        }

        void set(T t) {
            this.set = true;
            this.value = t;
        }

        T get() {
            Preconditions.checkState(this.set);
            return this.value;
        }
    }

    @Beta
    public static <T> java.util.Optional<T> findLast(Stream<T> stream) {
        C1OptionalState state = new C1OptionalState();
        Deque<Spliterator<T>> splits = new ArrayDeque<>();
        splits.addLast(stream.spliterator());
        while (!splits.isEmpty()) {
            Spliterator<T> spliterator = splits.removeLast();
            if (spliterator.getExactSizeIfKnown() != 0) {
                if (spliterator.hasCharacteristics(16384)) {
                    while (true) {
                        Spliterator<T> prefix = spliterator.trySplit();
                        if (prefix == null || prefix.getExactSizeIfKnown() == 0) {
                            break;
                        }
                        if (spliterator.getExactSizeIfKnown() == 0) {
                            spliterator = prefix;
                            break;
                        }
                    }
                    state.getClass();
                    spliterator.forEachRemaining(state::set);
                    return java.util.Optional.of(state.get());
                }
                Spliterator<T> prefix2 = spliterator.trySplit();
                if (prefix2 == null || prefix2.getExactSizeIfKnown() == 0) {
                    state.getClass();
                    spliterator.forEachRemaining(state::set);
                    if (state.set) {
                        return java.util.Optional.of(state.get());
                    }
                } else {
                    splits.addLast(prefix2);
                    splits.addLast(spliterator);
                }
            }
        }
        return java.util.Optional.empty();
    }

    @Beta
    public static OptionalInt findLast(IntStream stream) {
        java.util.Optional<Integer> boxedLast = findLast(stream.boxed());
        return boxedLast.isPresent() ? OptionalInt.of(boxedLast.get().intValue()) : OptionalInt.empty();
    }

    @Beta
    public static OptionalLong findLast(LongStream stream) {
        java.util.Optional<Long> boxedLast = findLast(stream.boxed());
        return boxedLast.isPresent() ? OptionalLong.of(boxedLast.get().longValue()) : OptionalLong.empty();
    }

    @Beta
    public static OptionalDouble findLast(DoubleStream stream) {
        java.util.Optional<Double> boxedLast = findLast(stream.boxed());
        return boxedLast.isPresent() ? OptionalDouble.of(boxedLast.get().doubleValue()) : OptionalDouble.empty();
    }

    private Streams() {
    }
}
