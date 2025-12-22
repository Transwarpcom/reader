package org.bson.codecs.configuration;

import org.bson.codecs.Codec;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/configuration/ChildCodecRegistry.class */
class ChildCodecRegistry<T> implements CodecRegistry {
    private final ChildCodecRegistry<?> parent;
    private final ProvidersCodecRegistry registry;
    private final Class<T> codecClass;

    ChildCodecRegistry(ProvidersCodecRegistry registry, Class<T> codecClass) {
        this.codecClass = codecClass;
        this.parent = null;
        this.registry = registry;
    }

    private ChildCodecRegistry(ChildCodecRegistry<?> parent, Class<T> codecClass) {
        this.parent = parent;
        this.codecClass = codecClass;
        this.registry = parent.registry;
    }

    public Class<T> getCodecClass() {
        return this.codecClass;
    }

    @Override // org.bson.codecs.configuration.CodecRegistry
    public <U> Codec<U> get(Class<U> clazz) {
        if (hasCycles(clazz).booleanValue()) {
            return new LazyCodec(this.registry, clazz);
        }
        return this.registry.get(new ChildCodecRegistry((ChildCodecRegistry<?>) this, (Class) clazz));
    }

    private <U> Boolean hasCycles(Class<U> theClass) {
        ChildCodecRegistry childCodecRegistry = this;
        while (true) {
            ChildCodecRegistry current = childCodecRegistry;
            if (current != null) {
                if (current.codecClass.equals(theClass)) {
                    return true;
                }
                childCodecRegistry = current.parent;
            } else {
                return false;
            }
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChildCodecRegistry<?> that = (ChildCodecRegistry) o;
        if (!this.codecClass.equals(that.codecClass)) {
            return false;
        }
        if (this.parent != null) {
            if (!this.parent.equals(that.parent)) {
                return false;
            }
        } else if (that.parent != null) {
            return false;
        }
        if (!this.registry.equals(that.registry)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.parent != null ? this.parent.hashCode() : 0;
        return (31 * ((31 * result) + this.registry.hashCode())) + this.codecClass.hashCode();
    }
}
