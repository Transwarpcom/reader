package com.mongodb.operation;

import com.mongodb.WriteConcern;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AbortTransactionOperation.class */
public class AbortTransactionOperation extends TransactionOperation {
    public AbortTransactionOperation(WriteConcern writeConcern) {
        super(writeConcern);
    }

    @Override // com.mongodb.operation.TransactionOperation
    protected String getCommandName() {
        return "abortTransaction";
    }
}
