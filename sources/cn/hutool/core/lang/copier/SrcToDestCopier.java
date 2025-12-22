package cn.hutool.core.lang.copier;

import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.copier.SrcToDestCopier;
import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/copier/SrcToDestCopier.class */
public abstract class SrcToDestCopier<T, C extends SrcToDestCopier<T, C>> implements Copier<T>, Serializable {
    private static final long serialVersionUID = 1;
    protected T src;
    protected T dest;
    protected Filter<T> copyFilter;

    public T getSrc() {
        return this.src;
    }

    public C setSrc(T src) {
        this.src = src;
        return this;
    }

    public T getDest() {
        return this.dest;
    }

    public C setDest(T dest) {
        this.dest = dest;
        return this;
    }

    public Filter<T> getCopyFilter() {
        return this.copyFilter;
    }

    public C setCopyFilter(Filter<T> copyFilter) {
        this.copyFilter = copyFilter;
        return this;
    }
}
