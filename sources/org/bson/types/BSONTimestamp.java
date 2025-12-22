package org.bson.types;

import java.io.Serializable;
import java.util.Date;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/BSONTimestamp.class */
public final class BSONTimestamp implements Comparable<BSONTimestamp>, Serializable {
    private static final long serialVersionUID = -3268482672267936464L;
    private final int inc;
    private final Date time;

    public BSONTimestamp() {
        this.inc = 0;
        this.time = null;
    }

    public BSONTimestamp(int time, int increment) {
        this.time = new Date(time * 1000);
        this.inc = increment;
    }

    public int getTime() {
        if (this.time == null) {
            return 0;
        }
        return (int) (this.time.getTime() / 1000);
    }

    public int getInc() {
        return this.inc;
    }

    public String toString() {
        return "TS time:" + this.time + " inc:" + this.inc;
    }

    @Override // java.lang.Comparable
    public int compareTo(BSONTimestamp ts) {
        if (getTime() != ts.getTime()) {
            return getTime() - ts.getTime();
        }
        return getInc() - ts.getInc();
    }

    public int hashCode() {
        int result = (31 * 1) + this.inc;
        return (31 * result) + getTime();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BSONTimestamp) {
            BSONTimestamp t2 = (BSONTimestamp) obj;
            return getTime() == t2.getTime() && getInc() == t2.getInc();
        }
        return false;
    }
}
