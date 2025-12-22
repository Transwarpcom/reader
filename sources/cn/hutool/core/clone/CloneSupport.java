package cn.hutool.core.clone;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/clone/CloneSupport.class */
public class CloneSupport<T> implements Cloneable<T> {
    @Override // cn.hutool.core.clone.Cloneable
    public T clone() {
        try {
            return (T) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneRuntimeException(e);
        }
    }
}
