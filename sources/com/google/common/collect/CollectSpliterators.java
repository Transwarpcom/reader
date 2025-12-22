package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CollectSpliterators.class */
final class CollectSpliterators {
    private CollectSpliterators() {
    }

    static <T> Spliterator<T> indexed(int size, int extraCharacteristics, IntFunction<T> function) {
        return indexed(size, extraCharacteristics, function, null);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Spliterator$OfInt] */
    static <T> Spliterator<T> indexed(int size, int extraCharacteristics, IntFunction<T> function, Comparator<? super T> comparator) {
        if (comparator != null) {
            Preconditions.checkArgument((extraCharacteristics & 4) != 0);
        }
        return new C1WithCharacteristics(IntStream.range(0, size).spliterator(), function, extraCharacteristics, comparator);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1WithCharacteristics, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CollectSpliterators$1WithCharacteristics.class */
    class C1WithCharacteristics<T> implements Spliterator<T> {
        private final Spliterator.OfInt delegate;
        final /* synthetic */ IntFunction val$function;
        final /* synthetic */ int val$extraCharacteristics;
        final /* synthetic */ Comparator val$comparator;

        C1WithCharacteristics(Spliterator.OfInt delegate, IntFunction intFunction, int i, Comparator comparator) {
            this.val$function = intFunction;
            this.val$extraCharacteristics = i;
            this.val$comparator = comparator;
            this.delegate = delegate;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> action) {
            Spliterator.OfInt ofInt = this.delegate;
            IntFunction intFunction = this.val$function;
            return ofInt.tryAdvance(i -> {
                action.accept(intFunction.apply(i));
            });
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> action) {
            Spliterator.OfInt ofInt = this.delegate;
            IntFunction intFunction = this.val$function;
            ofInt.forEachRemaining(i -> {
                action.accept(intFunction.apply(i));
            });
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator.OfInt split = this.delegate.trySplit();
            if (split == null) {
                return null;
            }
            return new C1WithCharacteristics(split, this.val$function, this.val$extraCharacteristics, this.val$comparator);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.delegate.estimateSize();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16464 | this.val$extraCharacteristics;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(4)) {
                return this.val$comparator;
            }
            throw new IllegalStateException();
        }
    }

    static <F, T> Spliterator<T> map(final Spliterator<F> fromSpliterator, final Function<? super F, ? extends T> function) {
        Preconditions.checkNotNull(fromSpliterator);
        Preconditions.checkNotNull(function);
        return new Spliterator<T>() { // from class: com.google.common.collect.CollectSpliterators.1
            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> action) {
                Spliterator spliterator = fromSpliterator;
                Function function2 = function;
                return spliterator.tryAdvance(fromElement -> {
                    action.accept(function2.apply(fromElement));
                });
            }

            @Override // java.util.Spliterator
            public void forEachRemaining(Consumer<? super T> action) {
                Spliterator spliterator = fromSpliterator;
                Function function2 = function;
                spliterator.forEachRemaining(fromElement -> {
                    action.accept(function2.apply(fromElement));
                });
            }

            @Override // java.util.Spliterator
            public Spliterator<T> trySplit() {
                Spliterator<T> spliteratorTrySplit = fromSpliterator.trySplit();
                if (spliteratorTrySplit != null) {
                    return CollectSpliterators.map(spliteratorTrySplit, function);
                }
                return null;
            }

            @Override // java.util.Spliterator
            public long estimateSize() {
                return fromSpliterator.estimateSize();
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return fromSpliterator.characteristics() & (-262);
            }
        };
    }

    static <T> Spliterator<T> filter(Spliterator<T> fromSpliterator, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(fromSpliterator);
        Preconditions.checkNotNull(predicate);
        return new C1Splitr(fromSpliterator, predicate);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1Splitr, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CollectSpliterators$1Splitr.class */
    class C1Splitr<T> implements Spliterator<T>, Consumer<T> {
        T holder = null;
        final /* synthetic */ Spliterator val$fromSpliterator;
        final /* synthetic */ Predicate val$predicate;

        C1Splitr(Spliterator spliterator, Predicate predicate) {
            this.val$fromSpliterator = spliterator;
            this.val$predicate = predicate;
        }

        @Override // java.util.function.Consumer
        public void accept(T t) {
            this.holder = t;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            while (this.val$fromSpliterator.tryAdvance(this)) {
                try {
                    if (this.val$predicate.test(this.holder)) {
                        consumer.accept(this.holder);
                        return true;
                    }
                } finally {
                    this.holder = null;
                }
            }
            return false;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator<T> fromSplit = this.val$fromSpliterator.trySplit();
            if (fromSplit == null) {
                return null;
            }
            return CollectSpliterators.filter(fromSplit, this.val$predicate);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.val$fromSpliterator.estimateSize() / 2;
        }

        @Override // java.util.Spliterator
        public Comparator<? super T> getComparator() {
            return this.val$fromSpliterator.getComparator();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.val$fromSpliterator.characteristics() & 277;
        }
    }

    static <F, T> Spliterator<T> flatMap(Spliterator<F> fromSpliterator, Function<? super F, Spliterator<T>> function, int topCharacteristics, long topSize) {
        Preconditions.checkArgument((topCharacteristics & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        Preconditions.checkArgument((topCharacteristics & 4) == 0, "flatMap does not support SORTED characteristic");
        Preconditions.checkNotNull(fromSpliterator);
        Preconditions.checkNotNull(function);
        return new C1FlatMapSpliterator(null, fromSpliterator, topCharacteristics, topSize, function);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* renamed from: com.google.common.collect.CollectSpliterators$1FlatMapSpliterator, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CollectSpliterators$1FlatMapSpliterator.class */
    class C1FlatMapSpliterator<T> implements Spliterator<T> {
        Spliterator<T> prefix;
        final Spliterator<F> from;
        int characteristics;
        long estimatedSize;
        final /* synthetic */ Function val$function;

        /* JADX WARN: Failed to calculate best type for var: r10v0 ??
        java.lang.NullPointerException
         */
        /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
        	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
        	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
         */
        /* JADX WARN: Not initialized variable reg: 10, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r10v0, types: [java.util.function.Function] */
        /* JADX WARN: Type inference failed for: r6v0, types: [java.util.Spliterator<F>, java.util.Spliterator<T>] */
        /* JADX WARN: Type inference failed for: r7v0, types: [int, java.util.Spliterator<F>] */
        C1FlatMapSpliterator(Spliterator spliterator, Spliterator<T> spliterator2, Spliterator<F> spliterator3, int i, long j) {
            ?? r10;
            this.val$function = r10;
            this.prefix = spliterator;
            this.from = spliterator2;
            this.characteristics = spliterator3;
            this.estimatedSize = i;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> action) {
            Spliterator<F> spliterator;
            Function function;
            do {
                if (this.prefix != null && this.prefix.tryAdvance(action)) {
                    if (this.estimatedSize != Long.MAX_VALUE) {
                        this.estimatedSize--;
                        return true;
                    }
                    return true;
                }
                this.prefix = null;
                spliterator = this.from;
                function = this.val$function;
            } while (spliterator.tryAdvance(fromElement -> {
                this.prefix = (Spliterator) function.apply(fromElement);
            }));
            return false;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> action) {
            if (this.prefix != null) {
                this.prefix.forEachRemaining(action);
                this.prefix = null;
            }
            Spliterator<F> spliterator = this.from;
            Function function = this.val$function;
            spliterator.forEachRemaining(fromElement -> {
                ((Spliterator) function.apply(fromElement)).forEachRemaining(action);
            });
            this.estimatedSize = 0L;
        }

        @Override // java.util.Spliterator
        public Spliterator<T> trySplit() {
            Spliterator spliteratorTrySplit = this.from.trySplit();
            if (spliteratorTrySplit != null) {
                int splitCharacteristics = this.characteristics & (-65);
                long estSplitSize = estimateSize();
                if (estSplitSize < Long.MAX_VALUE) {
                    estSplitSize /= 2;
                    this.estimatedSize -= estSplitSize;
                    this.characteristics = splitCharacteristics;
                }
                Spliterator<T> result = new C1FlatMapSpliterator<>(this.prefix, spliteratorTrySplit, splitCharacteristics, estSplitSize, this.val$function);
                this.prefix = null;
                return result;
            }
            if (this.prefix != null) {
                Spliterator<T> result2 = this.prefix;
                this.prefix = null;
                return result2;
            }
            return null;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            if (this.prefix != null) {
                this.estimatedSize = Math.max(this.estimatedSize, this.prefix.estimateSize());
            }
            return Math.max(this.estimatedSize, 0L);
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }
    }
}
