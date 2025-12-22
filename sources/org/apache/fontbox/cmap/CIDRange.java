package org.apache.fontbox.cmap;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/cmap/CIDRange.class */
class CIDRange {
    private final char from;
    private char to;
    private final int cid;

    CIDRange(char from, char to, int cid) {
        this.from = from;
        this.to = to;
        this.cid = cid;
    }

    public int map(char ch2) {
        if (this.from <= ch2 && ch2 <= this.to) {
            return this.cid + (ch2 - this.from);
        }
        return -1;
    }

    public int unmap(int code) {
        if (this.cid <= code && code <= this.cid + (this.to - this.from)) {
            return this.from + (code - this.cid);
        }
        return -1;
    }

    public boolean extend(char newFrom, char newTo, int newCid) {
        if (newFrom == this.to + 1 && newCid == ((this.cid + this.to) - this.from) + 1) {
            this.to = newTo;
            return true;
        }
        return false;
    }
}
