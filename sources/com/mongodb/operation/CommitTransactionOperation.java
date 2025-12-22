package com.mongodb.operation;

import com.mongodb.MongoException;
import com.mongodb.MongoNodeIsRecoveringException;
import com.mongodb.MongoNotPrimaryException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.WriteConcern;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.WriteBinding;
import java.util.Arrays;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommitTransactionOperation.class */
public class CommitTransactionOperation extends TransactionOperation {
    private static final List<Integer> NON_RETRYABLE_WRITE_CONCERN_ERROR_CODES = Arrays.asList(79, 100);

    public CommitTransactionOperation(WriteConcern writeConcern) {
        super(writeConcern);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.TransactionOperation, com.mongodb.operation.WriteOperation
    public Void execute(WriteBinding binding) {
        try {
            return super.execute(binding);
        } catch (MongoException e) {
            addErrorLabels(e);
            throw e;
        }
    }

    @Override // com.mongodb.operation.TransactionOperation, com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(AsyncWriteBinding binding, final SingleResultCallback<Void> callback) {
        super.executeAsync(binding, new SingleResultCallback<Void>() { // from class: com.mongodb.operation.CommitTransactionOperation.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(Void result, Throwable t) {
                if (t instanceof MongoException) {
                    CommitTransactionOperation.this.addErrorLabels((MongoException) t);
                }
                callback.onResult(result, t);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addErrorLabels(MongoException e) {
        if (shouldAddUnknownTransactionCommitResultLabel(e)) {
            e.addLabel(MongoException.UNKNOWN_TRANSACTION_COMMIT_RESULT_LABEL);
        }
    }

    static boolean shouldAddUnknownTransactionCommitResultLabel(Throwable t) {
        if (!(t instanceof MongoException)) {
            return false;
        }
        MongoException e = (MongoException) t;
        if ((e instanceof MongoSocketException) || (e instanceof MongoTimeoutException) || (e instanceof MongoNotPrimaryException) || (e instanceof MongoNodeIsRecoveringException)) {
            return true;
        }
        return (e instanceof MongoWriteConcernException) && !NON_RETRYABLE_WRITE_CONCERN_ERROR_CODES.contains(Integer.valueOf(e.getCode()));
    }

    @Override // com.mongodb.operation.TransactionOperation
    protected String getCommandName() {
        return "commitTransaction";
    }
}
