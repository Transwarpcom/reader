package com.mongodb.internal.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/OpCode.class */
enum OpCode {
    OP_REPLY(1),
    OP_UPDATE(2001),
    OP_INSERT(2002),
    OP_QUERY(2004),
    OP_GETMORE(2005),
    OP_DELETE(2006),
    OP_KILL_CURSORS(2007),
    OP_COMPRESSED(2012),
    OP_MSG(2013);

    private final int value;

    OpCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
