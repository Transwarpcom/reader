package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/WriteConcern.class */
public class WriteConcern implements Serializable {
    private static final long serialVersionUID = 1884671104750417011L;
    private final Object w;
    private final Integer wTimeoutMS;
    private final Boolean fsync;
    private final Boolean journal;
    public static final WriteConcern ACKNOWLEDGED = new WriteConcern((Object) null, (Integer) null, (Boolean) null, (Boolean) null);
    public static final WriteConcern W1 = new WriteConcern(1);
    public static final WriteConcern W2 = new WriteConcern(2);
    public static final WriteConcern W3 = new WriteConcern(3);
    public static final WriteConcern UNACKNOWLEDGED = new WriteConcern(0);

    @Deprecated
    public static final WriteConcern FSYNCED = ACKNOWLEDGED.withFsync(true);
    public static final WriteConcern JOURNALED = ACKNOWLEDGED.withJournal(true);

    @Deprecated
    public static final WriteConcern REPLICA_ACKNOWLEDGED = new WriteConcern(2);

    @Deprecated
    public static final WriteConcern NORMAL = UNACKNOWLEDGED;

    @Deprecated
    public static final WriteConcern SAFE = ACKNOWLEDGED;
    public static final WriteConcern MAJORITY = new WriteConcern("majority");

    @Deprecated
    public static final WriteConcern FSYNC_SAFE = FSYNCED;

    @Deprecated
    public static final WriteConcern JOURNAL_SAFE = JOURNALED;

    @Deprecated
    public static final WriteConcern REPLICAS_SAFE = REPLICA_ACKNOWLEDGED;
    private static final Map<String, WriteConcern> NAMED_CONCERNS = new HashMap();

    static {
        for (Field f : WriteConcern.class.getFields()) {
            if (Modifier.isStatic(f.getModifiers()) && f.getType().equals(WriteConcern.class)) {
                String key = f.getName().toLowerCase();
                try {
                    NAMED_CONCERNS.put(key, (WriteConcern) f.get(null));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Deprecated
    public WriteConcern() {
        this(0);
    }

    public WriteConcern(int w) {
        this(Integer.valueOf(w), (Integer) null, (Boolean) null, (Boolean) null);
    }

    public WriteConcern(String w) {
        this(w, (Integer) null, (Boolean) null, (Boolean) null);
        Assertions.notNull(OperatorName.SET_LINE_WIDTH, w);
    }

    public WriteConcern(int w, int wTimeoutMS) {
        this(Integer.valueOf(w), Integer.valueOf(wTimeoutMS), (Boolean) null, (Boolean) null);
    }

    @Deprecated
    public WriteConcern(boolean fsync) {
        this((Object) null, (Integer) null, Boolean.valueOf(fsync), (Boolean) null);
    }

    @Deprecated
    public WriteConcern(int w, int wTimeoutMS, boolean fsync) {
        this(Integer.valueOf(w), Integer.valueOf(wTimeoutMS), Boolean.valueOf(fsync), (Boolean) null);
    }

    @Deprecated
    public WriteConcern(int w, int wTimeoutMS, boolean fsync, boolean journal) {
        this(Integer.valueOf(w), Integer.valueOf(wTimeoutMS), Boolean.valueOf(fsync), Boolean.valueOf(journal));
    }

    @Deprecated
    public WriteConcern(String w, int wTimeoutMS, boolean fsync, boolean journal) {
        this(Assertions.notNull(OperatorName.SET_LINE_WIDTH, w), Integer.valueOf(wTimeoutMS), Boolean.valueOf(fsync), Boolean.valueOf(journal));
    }

    private WriteConcern(@Nullable Object w, @Nullable Integer wTimeoutMS, @Nullable Boolean fsync, @Nullable Boolean journal) {
        if (w instanceof Integer) {
            Assertions.isTrueArgument("w >= 0", ((Integer) w).intValue() >= 0);
            if (((Integer) w).intValue() == 0) {
                Assertions.isTrueArgument("fsync is false when w is 0", fsync == null || !fsync.booleanValue());
                Assertions.isTrueArgument("journal is false when w is 0", journal == null || !journal.booleanValue());
            }
        } else if (w != null) {
            Assertions.isTrueArgument("w must be String or int", w instanceof String);
        }
        Assertions.isTrueArgument("wtimeout >= 0", wTimeoutMS == null || wTimeoutMS.intValue() >= 0);
        this.w = w;
        this.wTimeoutMS = wTimeoutMS;
        this.fsync = fsync;
        this.journal = journal;
    }

    public Object getWObject() {
        return this.w;
    }

    public int getW() {
        Assertions.isTrue("w is an Integer", this.w != null && (this.w instanceof Integer));
        return ((Integer) this.w).intValue();
    }

    public String getWString() {
        Assertions.isTrue("w is a String", this.w != null && (this.w instanceof String));
        return (String) this.w;
    }

    @Nullable
    public Integer getWTimeout(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        if (this.wTimeoutMS == null) {
            return null;
        }
        return Integer.valueOf((int) timeUnit.convert(this.wTimeoutMS.intValue(), TimeUnit.MILLISECONDS));
    }

    @Deprecated
    public int getWtimeout() {
        if (this.wTimeoutMS == null) {
            return 0;
        }
        return this.wTimeoutMS.intValue();
    }

    public Boolean getJournal() {
        return this.journal;
    }

    @Deprecated
    public boolean getJ() {
        if (this.journal == null) {
            return false;
        }
        return this.journal.booleanValue();
    }

    @Deprecated
    public boolean getFsync() {
        if (this.fsync == null) {
            return false;
        }
        return this.fsync.booleanValue();
    }

    @Deprecated
    public boolean fsync() {
        return getFsync();
    }

    @Deprecated
    public boolean callGetLastError() {
        return isAcknowledged();
    }

    public boolean isServerDefault() {
        return equals(ACKNOWLEDGED);
    }

    public BsonDocument asDocument() {
        BsonDocument document = new BsonDocument();
        addW(document);
        addWTimeout(document);
        addFSync(document);
        addJ(document);
        return document;
    }

    public boolean isAcknowledged() {
        return !(this.w instanceof Integer) || ((Integer) this.w).intValue() > 0 || (this.journal != null && this.journal.booleanValue()) || (this.fsync != null && this.fsync.booleanValue());
    }

    public static WriteConcern valueOf(String name) {
        return NAMED_CONCERNS.get(name.toLowerCase());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WriteConcern that = (WriteConcern) o;
        if (this.w != null) {
            if (!this.w.equals(that.w)) {
                return false;
            }
        } else if (that.w != null) {
            return false;
        }
        if (this.wTimeoutMS != null) {
            if (!this.wTimeoutMS.equals(that.wTimeoutMS)) {
                return false;
            }
        } else if (that.wTimeoutMS != null) {
            return false;
        }
        if (this.fsync != null) {
            if (!this.fsync.equals(that.fsync)) {
                return false;
            }
        } else if (that.fsync != null) {
            return false;
        }
        if (this.journal != null) {
            if (!this.journal.equals(that.journal)) {
                return false;
            }
            return true;
        }
        if (that.journal != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.w != null ? this.w.hashCode() : 0;
        return (31 * ((31 * ((31 * result) + (this.wTimeoutMS != null ? this.wTimeoutMS.hashCode() : 0))) + (this.fsync != null ? this.fsync.hashCode() : 0))) + (this.journal != null ? this.journal.hashCode() : 0);
    }

    public String toString() {
        return "WriteConcern{w=" + this.w + ", wTimeout=" + this.wTimeoutMS + " ms, fsync=" + this.fsync + ", journal=" + this.journal;
    }

    public WriteConcern withW(int w) {
        return new WriteConcern(Integer.valueOf(w), this.wTimeoutMS, this.fsync, this.journal);
    }

    public WriteConcern withW(String w) {
        Assertions.notNull(OperatorName.SET_LINE_WIDTH, w);
        return new WriteConcern(w, this.wTimeoutMS, this.fsync, this.journal);
    }

    @Deprecated
    public WriteConcern withFsync(boolean fsync) {
        return new WriteConcern(this.w, this.wTimeoutMS, Boolean.valueOf(fsync), this.journal);
    }

    public WriteConcern withJournal(Boolean journal) {
        return new WriteConcern(this.w, this.wTimeoutMS, this.fsync, journal);
    }

    @Deprecated
    public WriteConcern withJ(boolean journal) {
        return withJournal(Boolean.valueOf(journal));
    }

    public WriteConcern withWTimeout(long wTimeout, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        long newWTimeOutMS = TimeUnit.MILLISECONDS.convert(wTimeout, timeUnit);
        Assertions.isTrueArgument("wTimeout >= 0", wTimeout >= 0);
        Assertions.isTrueArgument("wTimeout <= 2147483647 ms", newWTimeOutMS <= 2147483647L);
        return new WriteConcern(this.w, Integer.valueOf((int) newWTimeOutMS), this.fsync, this.journal);
    }

    private void addW(BsonDocument document) {
        if (this.w instanceof String) {
            document.put(OperatorName.SET_LINE_WIDTH, (BsonValue) new BsonString((String) this.w));
        } else if (this.w instanceof Integer) {
            document.put(OperatorName.SET_LINE_WIDTH, (BsonValue) new BsonInt32(((Integer) this.w).intValue()));
        }
    }

    private void addJ(BsonDocument document) {
        if (this.journal != null) {
            document.put(OperatorName.SET_LINE_JOINSTYLE, (BsonValue) BsonBoolean.valueOf(this.journal.booleanValue()));
        }
    }

    private void addFSync(BsonDocument document) {
        if (this.fsync != null) {
            document.put("fsync", (BsonValue) BsonBoolean.valueOf(this.fsync.booleanValue()));
        }
    }

    private void addWTimeout(BsonDocument document) {
        if (this.wTimeoutMS != null) {
            document.put("wtimeout", (BsonValue) new BsonInt32(this.wTimeoutMS.intValue()));
        }
    }

    @Deprecated
    public static Majority majorityWriteConcern(int wtimeout, boolean fsync, boolean j) {
        return new Majority(wtimeout, fsync, j);
    }

    @Deprecated
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/WriteConcern$Majority.class */
    public static class Majority extends WriteConcern {
        private static final long serialVersionUID = -4128295115883875212L;

        public Majority() {
            this(0, false, false);
        }

        public Majority(int wtimeout, boolean fsync, boolean j) {
            super("majority", wtimeout, fsync, j);
        }
    }
}
