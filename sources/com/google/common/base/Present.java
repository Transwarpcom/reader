package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Set;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/Present.class */
final class Present<T> extends Optional<T> {
    private final T reference;
    private static final long serialVersionUID = 0;

    Present(T reference) {
        this.reference = reference;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return true;
    }

    @Override // com.google.common.base.Optional
    public T get() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public T or(T defaultValue) {
        Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public Optional<T> or(Optional<? extends T> secondChoice) {
        Preconditions.checkNotNull(secondChoice);
        return this;
    }

    @Override // com.google.common.base.Optional
    public T or(Supplier<? extends T> supplier) {
        Preconditions.checkNotNull(supplier);
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public T orNull() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public Set<T> asSet() {
        return Collections.singleton(this.reference);
    }

    @Override // com.google.common.base.Optional
    public <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Preconditions.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    @Override // com.google.common.base.Optional
    public boolean equals(Object object) {
        if (object instanceof Present) {
            Present<?> other = (Present) object;
            return this.reference.equals(other.reference);
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return 1502476572 + this.reference.hashCode();
    }

    @Override // com.google.common.base.Optional
    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
