package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoCursorNotFoundException.class */
public class MongoCursorNotFoundException extends MongoQueryException {
    private static final long serialVersionUID = -4415279469780082174L;
    private final long cursorId;
    private final ServerAddress serverAddress;

    public MongoCursorNotFoundException(long cursorId, ServerAddress serverAddress) {
        super(serverAddress, -5, "Cursor " + cursorId + " not found on server " + serverAddress);
        this.cursorId = cursorId;
        this.serverAddress = serverAddress;
    }

    public long getCursorId() {
        return this.cursorId;
    }

    @Override // com.mongodb.MongoServerException
    public ServerAddress getServerAddress() {
        return this.serverAddress;
    }
}
