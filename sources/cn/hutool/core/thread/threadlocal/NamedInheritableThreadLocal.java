package cn.hutool.core.thread.threadlocal;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/thread/threadlocal/NamedInheritableThreadLocal.class */
public class NamedInheritableThreadLocal<T> extends InheritableThreadLocal<T> {
    private final String name;

    public NamedInheritableThreadLocal(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
