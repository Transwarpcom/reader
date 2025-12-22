package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/ClientSessionOptions.class */
public final class ClientSessionOptions {
    private final Boolean causallyConsistent;
    private final TransactionOptions defaultTransactionOptions;

    @Nullable
    public Boolean isCausallyConsistent() {
        return this.causallyConsistent;
    }

    public TransactionOptions getDefaultTransactionOptions() {
        return this.defaultTransactionOptions;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientSessionOptions that = (ClientSessionOptions) o;
        if (this.causallyConsistent != null) {
            if (!this.causallyConsistent.equals(that.causallyConsistent)) {
                return false;
            }
        } else if (that.causallyConsistent != null) {
            return false;
        }
        if (this.defaultTransactionOptions != null) {
            if (!this.defaultTransactionOptions.equals(that.defaultTransactionOptions)) {
                return false;
            }
            return true;
        }
        if (that.defaultTransactionOptions != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.causallyConsistent != null ? this.causallyConsistent.hashCode() : 0;
        return (31 * result) + (this.defaultTransactionOptions != null ? this.defaultTransactionOptions.hashCode() : 0);
    }

    public String toString() {
        return "ClientSessionOptions{causallyConsistent=" + this.causallyConsistent + ", defaultTransactionOptions=" + this.defaultTransactionOptions + '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ClientSessionOptions options) {
        Assertions.notNull("options", options);
        Builder builder = new Builder();
        builder.causallyConsistent = options.isCausallyConsistent();
        builder.defaultTransactionOptions = options.getDefaultTransactionOptions();
        return builder;
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/ClientSessionOptions$Builder.class */
    public static final class Builder {
        private Boolean causallyConsistent;
        private TransactionOptions defaultTransactionOptions;

        public Builder causallyConsistent(boolean causallyConsistent) {
            this.causallyConsistent = Boolean.valueOf(causallyConsistent);
            return this;
        }

        public Builder defaultTransactionOptions(TransactionOptions defaultTransactionOptions) {
            this.defaultTransactionOptions = (TransactionOptions) Assertions.notNull("defaultTransactionOptions", defaultTransactionOptions);
            return this;
        }

        public ClientSessionOptions build() {
            return new ClientSessionOptions(this);
        }

        private Builder() {
            this.defaultTransactionOptions = TransactionOptions.builder().build();
        }
    }

    private ClientSessionOptions(Builder builder) {
        this.causallyConsistent = builder.causallyConsistent;
        this.defaultTransactionOptions = builder.defaultTransactionOptions;
    }
}
