package org.bson.codecs.configuration;

import java.util.NoSuchElementException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/Optional.class */
abstract class Optional<T> {
    private static final Optional<Object> NONE = new Optional<Object>() { // from class: org.bson.codecs.configuration.Optional.1
        @Override // org.bson.codecs.configuration.Optional
        public Object get() {
            throw new NoSuchElementException(".get call on None!");
        }

        @Override // org.bson.codecs.configuration.Optional
        public boolean isEmpty() {
            return true;
        }
    };

    public abstract T get();

    public abstract boolean isEmpty();

    Optional() {
    }

    public static <T> Optional<T> empty() {
        return (Optional<T>) NONE;
    }

    public static <T> Optional<T> of(T t) {
        if (t == null) {
            return (Optional<T>) NONE;
        }
        return new Some(t);
    }

    public String toString() {
        return "None";
    }

    public boolean isDefined() {
        return !isEmpty();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/Optional$Some.class */
    public static class Some<T> extends Optional<T> {
        private final T value;

        @Override // org.bson.codecs.configuration.Optional
        public /* bridge */ /* synthetic */ boolean isDefined() {
            return super.isDefined();
        }

        Some(T value) {
            this.value = value;
        }

        @Override // org.bson.codecs.configuration.Optional
        public T get() {
            return this.value;
        }

        @Override // org.bson.codecs.configuration.Optional
        public boolean isEmpty() {
            return false;
        }

        @Override // org.bson.codecs.configuration.Optional
        public String toString() {
            return String.format("Some(%s)", this.value);
        }
    }
}
