package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Map;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions.class */
public final class Functions {
    private Functions() {
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$ToStringFunction.class */
    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        @Override // com.google.common.base.Function, java.util.function.Function
        public String apply(Object o) {
            Preconditions.checkNotNull(o);
            return o.toString();
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.toStringFunction()";
        }
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$IdentityFunction.class */
    private enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        @Override // com.google.common.base.Function, java.util.function.Function
        public Object apply(Object o) {
            return o;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.identity()";
        }
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, V defaultValue) {
        return new ForMapWithDefault(map, defaultValue);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$FunctionForMapNoDefault.class */
    private static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
        final Map<K, V> map;
        private static final long serialVersionUID = 0;

        FunctionForMapNoDefault(Map<K, V> map) {
            this.map = (Map) Preconditions.checkNotNull(map);
        }

        @Override // com.google.common.base.Function, java.util.function.Function
        public V apply(K key) {
            V result = this.map.get(key);
            Preconditions.checkArgument(result != null || this.map.containsKey(key), "Key '%s' not present in map", key);
            return result;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object o) {
            if (o instanceof FunctionForMapNoDefault) {
                FunctionForMapNoDefault<?, ?> that = (FunctionForMapNoDefault) o;
                return this.map.equals(that.map);
            }
            return false;
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ")";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$ForMapWithDefault.class */
    private static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
        final Map<K, ? extends V> map;
        final V defaultValue;
        private static final long serialVersionUID = 0;

        ForMapWithDefault(Map<K, ? extends V> map, V defaultValue) {
            this.map = (Map) Preconditions.checkNotNull(map);
            this.defaultValue = defaultValue;
        }

        @Override // com.google.common.base.Function, java.util.function.Function
        public V apply(K key) {
            V result = this.map.get(key);
            return (result != null || this.map.containsKey(key)) ? result : this.defaultValue;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object o) {
            if (o instanceof ForMapWithDefault) {
                ForMapWithDefault<?, ?> that = (ForMapWithDefault) o;
                return this.map.equals(that.map) && Objects.equal(this.defaultValue, that.defaultValue);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.map, this.defaultValue);
        }

        public String toString() {
            return "Functions.forMap(" + this.map + ", defaultValue=" + this.defaultValue + ")";
        }
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, ? extends B> f) {
        return new FunctionComposition(g, f);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$FunctionComposition.class */
    private static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
        private final Function<B, C> g;
        private final Function<A, ? extends B> f;
        private static final long serialVersionUID = 0;

        public FunctionComposition(Function<B, C> g, Function<A, ? extends B> f) {
            this.g = (Function) Preconditions.checkNotNull(g);
            this.f = (Function) Preconditions.checkNotNull(f);
        }

        @Override // com.google.common.base.Function, java.util.function.Function
        public C apply(A a) {
            return (C) this.g.apply(this.f.apply(a));
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof FunctionComposition) {
                FunctionComposition<?, ?, ?> that = (FunctionComposition) obj;
                return this.f.equals(that.f) && this.g.equals(that.g);
            }
            return false;
        }

        public int hashCode() {
            return this.f.hashCode() ^ this.g.hashCode();
        }

        public String toString() {
            return this.g + "(" + this.f + ")";
        }
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$PredicateFunction.class */
    private static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {
        private final Predicate<T> predicate;
        private static final long serialVersionUID = 0;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.base.Function, java.util.function.Function
        public /* bridge */ /* synthetic */ Object apply(Object obj) {
            return apply((PredicateFunction<T>) obj);
        }

        private PredicateFunction(Predicate<T> predicate) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        @Override // com.google.common.base.Function, java.util.function.Function
        public Boolean apply(T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof PredicateFunction) {
                PredicateFunction<?> that = (PredicateFunction) obj;
                return this.predicate.equals(that.predicate);
            }
            return false;
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            return "Functions.forPredicate(" + this.predicate + ")";
        }
    }

    public static <E> Function<Object, E> constant(E value) {
        return new ConstantFunction(value);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$ConstantFunction.class */
    private static class ConstantFunction<E> implements Function<Object, E>, Serializable {
        private final E value;
        private static final long serialVersionUID = 0;

        public ConstantFunction(E value) {
            this.value = value;
        }

        @Override // com.google.common.base.Function, java.util.function.Function
        public E apply(Object from) {
            return this.value;
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof ConstantFunction) {
                ConstantFunction<?> that = (ConstantFunction) obj;
                return Objects.equal(this.value, that.value);
            }
            return false;
        }

        public int hashCode() {
            if (this.value == null) {
                return 0;
            }
            return this.value.hashCode();
        }

        public String toString() {
            return "Functions.constant(" + this.value + ")";
        }
    }

    public static <T> Function<Object, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Functions$SupplierFunction.class */
    private static class SupplierFunction<T> implements Function<Object, T>, Serializable {
        private final Supplier<T> supplier;
        private static final long serialVersionUID = 0;

        private SupplierFunction(Supplier<T> supplier) {
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Function, java.util.function.Function
        public T apply(Object input) {
            return this.supplier.get();
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof SupplierFunction) {
                SupplierFunction<?> that = (SupplierFunction) obj;
                return this.supplier.equals(that.supplier);
            }
            return false;
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            return "Functions.forSupplier(" + this.supplier + ")";
        }
    }
}
