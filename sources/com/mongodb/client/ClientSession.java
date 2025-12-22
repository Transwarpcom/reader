package com.mongodb.client;

import com.mongodb.TransactionOptions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/ClientSession.class */
public interface ClientSession extends com.mongodb.session.ClientSession {
    boolean hasActiveTransaction();

    boolean notifyMessageSent();

    TransactionOptions getTransactionOptions();

    void startTransaction();

    void startTransaction(TransactionOptions transactionOptions);

    void commitTransaction();

    void abortTransaction();
}
