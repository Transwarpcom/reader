package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TransactionOptions.class */
public final class TransactionOptions {
    private final ReadConcern readConcern;
    private final WriteConcern writeConcern;
    private final ReadPreference readPreference;

    @Nullable
    public ReadConcern getReadConcern() {
        return this.readConcern;
    }

    @Nullable
    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    @Nullable
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static TransactionOptions merge(TransactionOptions options, TransactionOptions defaultOptions) {
        Assertions.notNull("options", options);
        Assertions.notNull("defaultOptions", defaultOptions);
        return builder().writeConcern(options.getWriteConcern() == null ? defaultOptions.getWriteConcern() : options.getWriteConcern()).readConcern(options.getReadConcern() == null ? defaultOptions.getReadConcern() : options.getReadConcern()).readPreference(options.getReadPreference() == null ? defaultOptions.getReadPreference() : options.getReadPreference()).build();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionOptions that = (TransactionOptions) o;
        if (this.readConcern != null) {
            if (!this.readConcern.equals(that.readConcern)) {
                return false;
            }
        } else if (that.readConcern != null) {
            return false;
        }
        if (this.writeConcern != null) {
            if (!this.writeConcern.equals(that.writeConcern)) {
                return false;
            }
        } else if (that.writeConcern != null) {
            return false;
        }
        if (this.readPreference != null) {
            if (!this.readPreference.equals(that.readPreference)) {
                return false;
            }
            return true;
        }
        if (that.readPreference != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.readConcern != null ? this.readConcern.hashCode() : 0;
        return (31 * ((31 * result) + (this.writeConcern != null ? this.writeConcern.hashCode() : 0))) + (this.readPreference != null ? this.readPreference.hashCode() : 0);
    }

    public String toString() {
        return "TransactionOptions{readConcern=" + this.readConcern + ", writeConcern=" + this.writeConcern + ", readPreference=" + this.readPreference + '}';
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TransactionOptions$Builder.class */
    public static final class Builder {
        private ReadConcern readConcern;
        private WriteConcern writeConcern;
        private ReadPreference readPreference;

        public Builder readConcern(@Nullable ReadConcern readConcern) {
            this.readConcern = readConcern;
            return this;
        }

        public Builder writeConcern(@Nullable WriteConcern writeConcern) {
            this.writeConcern = writeConcern;
            return this;
        }

        public Builder readPreference(@Nullable ReadPreference readPreference) {
            this.readPreference = readPreference;
            return this;
        }

        public TransactionOptions build() {
            return new TransactionOptions(this);
        }

        private Builder() {
        }
    }

    private TransactionOptions(Builder builder) {
        this.readConcern = builder.readConcern;
        this.writeConcern = builder.writeConcern;
        this.readPreference = builder.readPreference;
    }
}
