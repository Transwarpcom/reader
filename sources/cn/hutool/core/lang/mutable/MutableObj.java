package cn.hutool.core.lang.mutable;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/mutable/MutableObj.class */
public class MutableObj<T> implements Mutable<T>, Serializable {
    private static final long serialVersionUID = 1;
    private T value;

    public MutableObj() {
    }

    public MutableObj(T value) {
        this.value = value;
    }

    @Override // cn.hutool.core.lang.mutable.Mutable
    public T get() {
        return this.value;
    }

    @Override // cn.hutool.core.lang.mutable.Mutable
    public void set(T value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() == obj.getClass()) {
            MutableObj<?> that = (MutableObj) obj;
            return this.value.equals(that.value);
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
        return this.value == null ? "null" : this.value.toString();
    }
}
