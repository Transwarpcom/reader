package org.springframework.boot.autoconfigure.jooq;

import org.jooq.Transaction;
import org.springframework.transaction.TransactionStatus;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/jooq/SpringTransaction.class */
class SpringTransaction implements Transaction {
    private final TransactionStatus transactionStatus;

    SpringTransaction(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionStatus getTxStatus() {
        return this.transactionStatus;
    }
}
