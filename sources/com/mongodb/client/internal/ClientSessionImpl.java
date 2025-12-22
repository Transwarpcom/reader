package com.mongodb.client.internal;

import com.mongodb.ClientSessionOptions;
import com.mongodb.MongoClientException;
import com.mongodb.MongoInternalException;
import com.mongodb.ReadConcern;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ClientSession;
import com.mongodb.internal.session.BaseClientSessionImpl;
import com.mongodb.internal.session.ServerSessionPool;
import com.mongodb.operation.AbortTransactionOperation;
import com.mongodb.operation.CommitTransactionOperation;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ClientSessionImpl.class */
final class ClientSessionImpl extends BaseClientSessionImpl implements ClientSession {
    private final MongoClientDelegate delegate;
    private TransactionState transactionState;
    private boolean messageSentInCurrentTransaction;
    private boolean commitInProgress;
    private TransactionOptions transactionOptions;

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ClientSessionImpl$TransactionState.class */
    private enum TransactionState {
        NONE,
        IN,
        COMMITTED,
        ABORTED
    }

    ClientSessionImpl(ServerSessionPool serverSessionPool, Object originator, ClientSessionOptions options, MongoClientDelegate delegate) {
        super(serverSessionPool, originator, options);
        this.transactionState = TransactionState.NONE;
        this.delegate = delegate;
    }

    @Override // com.mongodb.client.ClientSession
    public boolean hasActiveTransaction() {
        return this.transactionState == TransactionState.IN || (this.transactionState == TransactionState.COMMITTED && this.commitInProgress);
    }

    @Override // com.mongodb.client.ClientSession
    public boolean notifyMessageSent() {
        if (hasActiveTransaction()) {
            boolean firstMessageInCurrentTransaction = !this.messageSentInCurrentTransaction;
            this.messageSentInCurrentTransaction = true;
            return firstMessageInCurrentTransaction;
        }
        if (this.transactionState == TransactionState.COMMITTED || this.transactionState == TransactionState.ABORTED) {
            cleanupTransaction(TransactionState.NONE);
            return false;
        }
        return false;
    }

    @Override // com.mongodb.client.ClientSession
    public TransactionOptions getTransactionOptions() {
        Assertions.isTrue("in transaction", this.transactionState == TransactionState.IN || this.transactionState == TransactionState.COMMITTED);
        return this.transactionOptions;
    }

    @Override // com.mongodb.client.ClientSession
    public void startTransaction() {
        startTransaction(TransactionOptions.builder().build());
    }

    @Override // com.mongodb.client.ClientSession
    public void startTransaction(TransactionOptions transactionOptions) {
        Assertions.notNull("transactionOptions", transactionOptions);
        if (this.transactionState == TransactionState.IN) {
            throw new IllegalStateException("Transaction already in progress");
        }
        if (this.transactionState == TransactionState.COMMITTED) {
            cleanupTransaction(TransactionState.IN);
        } else {
            this.transactionState = TransactionState.IN;
        }
        getServerSession().advanceTransactionNumber();
        this.transactionOptions = TransactionOptions.merge(transactionOptions, getOptions().getDefaultTransactionOptions());
        WriteConcern writeConcern = this.transactionOptions.getWriteConcern();
        if (writeConcern == null) {
            throw new MongoInternalException("Invariant violated.  Transaction options write concern can not be null");
        }
        if (!writeConcern.isAcknowledged()) {
            throw new MongoClientException("Transactions do not support unacknowledged write concern");
        }
    }

    @Override // com.mongodb.client.ClientSession
    public void commitTransaction() {
        if (this.transactionState == TransactionState.ABORTED) {
            throw new IllegalStateException("Cannot call commitTransaction after calling abortTransaction");
        }
        if (this.transactionState == TransactionState.NONE) {
            throw new IllegalStateException("There is no transaction started");
        }
        try {
            if (this.messageSentInCurrentTransaction) {
                ReadConcern readConcern = this.transactionOptions.getReadConcern();
                if (readConcern == null) {
                    throw new MongoInternalException("Invariant violated.  Transaction options read concern can not be null");
                }
                this.commitInProgress = true;
                this.delegate.getOperationExecutor().execute(new CommitTransactionOperation(this.transactionOptions.getWriteConcern()), readConcern, this);
            }
        } finally {
            this.commitInProgress = false;
            this.transactionState = TransactionState.COMMITTED;
        }
    }

    @Override // com.mongodb.client.ClientSession
    public void abortTransaction() {
        if (this.transactionState == TransactionState.ABORTED) {
            throw new IllegalStateException("Cannot call abortTransaction twice");
        }
        if (this.transactionState == TransactionState.COMMITTED) {
            throw new IllegalStateException("Cannot call abortTransaction after calling commitTransaction");
        }
        if (this.transactionState == TransactionState.NONE) {
            throw new IllegalStateException("There is no transaction started");
        }
        try {
            if (this.messageSentInCurrentTransaction) {
                ReadConcern readConcern = this.transactionOptions.getReadConcern();
                if (readConcern == null) {
                    throw new MongoInternalException("Invariant violated.  Transaction options read concern can not be null");
                }
                this.delegate.getOperationExecutor().execute(new AbortTransactionOperation(this.transactionOptions.getWriteConcern()), readConcern, this);
            }
        } catch (Exception e) {
        } finally {
            cleanupTransaction(TransactionState.ABORTED);
        }
    }

    @Override // com.mongodb.internal.session.BaseClientSessionImpl, com.mongodb.session.ClientSession, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            if (this.transactionState == TransactionState.IN) {
                abortTransaction();
            }
        } finally {
            super.close();
        }
    }

    private void cleanupTransaction(TransactionState nextState) {
        this.messageSentInCurrentTransaction = false;
        this.transactionOptions = null;
        this.transactionState = nextState;
    }
}
