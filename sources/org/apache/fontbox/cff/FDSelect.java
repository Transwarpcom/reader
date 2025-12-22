package org.apache.fontbox.cff;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/cff/FDSelect.class */
public abstract class FDSelect {
    protected final CFFCIDFont owner;

    public abstract int getFDIndex(int i);

    public FDSelect(CFFCIDFont owner) {
        this.owner = owner;
    }
}
