package kotlin.reflect.jvm.internal.impl.storage;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/storage/SingleThreadValue.class */
class SingleThreadValue<T> {
    private final T value;
    private final Thread thread = Thread.currentThread();

    SingleThreadValue(T value) {
        this.value = value;
    }

    public boolean hasValue() {
        return this.thread == Thread.currentThread();
    }

    public T getValue() {
        if (hasValue()) {
            return this.value;
        }
        throw new IllegalStateException("No value in this thread (hasValue should be checked before)");
    }
}
