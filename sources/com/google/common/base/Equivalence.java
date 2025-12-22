package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.ForOverride;
import java.io.Serializable;
import java.util.function.BiPredicate;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Equivalence.class */
public abstract class Equivalence<T> implements BiPredicate<T, T> {
    @ForOverride
    protected abstract boolean doEquivalent(T t, T t2);

    @ForOverride
    protected abstract int doHash(T t);

    protected Equivalence() {
    }

    public final boolean equivalent(T a, T b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return doEquivalent(a, b);
    }

    @Override // java.util.function.BiPredicate
    @Deprecated
    public final boolean test(T t, T u) {
        return equivalent(t, u);
    }

    public final int hash(T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    public final <F> Equivalence<F> onResultOf(Function<F, ? extends T> function) {
        return new FunctionalEquivalence(function, this);
    }

    public final <S extends T> Wrapper<S> wrap(S reference) {
        return new Wrapper<>(reference);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Equivalence$Wrapper.class */
    public static final class Wrapper<T> implements Serializable {
        private final Equivalence<? super T> equivalence;
        private final T reference;
        private static final long serialVersionUID = 0;

        private Wrapper(Equivalence<? super T> equivalence, T reference) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
            this.reference = reference;
        }

        public T get() {
            return this.reference;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Wrapper) {
                Wrapper<?> that = (Wrapper) obj;
                if (this.equivalence.equals(that.equivalence)) {
                    Equivalence<Object> equivalence = this.equivalence;
                    return equivalence.equivalent(this.reference, that.reference);
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return this.equivalence.hash(this.reference);
        }

        public String toString() {
            return this.equivalence + ".wrap(" + this.reference + ")";
        }
    }

    @GwtCompatible(serializable = true)
    public final <S extends T> Equivalence<Iterable<S>> pairwise() {
        return new PairwiseEquivalence(this);
    }

    public final Predicate<T> equivalentTo(T target) {
        return new EquivalentToPredicate(this, target);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Equivalence$EquivalentToPredicate.class */
    private static final class EquivalentToPredicate<T> implements Predicate<T>, Serializable {
        private final Equivalence<T> equivalence;
        private final T target;
        private static final long serialVersionUID = 0;

        EquivalentToPredicate(Equivalence<T> equivalence, T target) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence);
            this.target = target;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(T input) {
            return this.equivalence.equivalent(input, this.target);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof EquivalentToPredicate) {
                EquivalentToPredicate<?> that = (EquivalentToPredicate) obj;
                return this.equivalence.equals(that.equivalence) && Objects.equal(this.target, that.target);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.equivalence, this.target);
        }

        public String toString() {
            return this.equivalence + ".equivalentTo(" + this.target + ")";
        }
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Equivalence$Equals.class */
    static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals INSTANCE = new Equals();
        private static final long serialVersionUID = 1;

        Equals() {
        }

        @Override // com.google.common.base.Equivalence
        protected boolean doEquivalent(Object a, Object b) {
            return a.equals(b);
        }

        @Override // com.google.common.base.Equivalence
        protected int doHash(Object o) {
            return o.hashCode();
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Equivalence$Identity.class */
    static final class Identity extends Equivalence<Object> implements Serializable {
        static final Identity INSTANCE = new Identity();
        private static final long serialVersionUID = 1;

        Identity() {
        }

        @Override // com.google.common.base.Equivalence
        protected boolean doEquivalent(Object a, Object b) {
            return false;
        }

        @Override // com.google.common.base.Equivalence
        protected int doHash(Object o) {
            return System.identityHashCode(o);
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}
