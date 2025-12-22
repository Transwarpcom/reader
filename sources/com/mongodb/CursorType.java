package com.mongodb;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/CursorType.class */
public enum CursorType {
    NonTailable { // from class: com.mongodb.CursorType.1
        @Override // com.mongodb.CursorType
        public boolean isTailable() {
            return false;
        }
    },
    Tailable { // from class: com.mongodb.CursorType.2
        @Override // com.mongodb.CursorType
        public boolean isTailable() {
            return true;
        }
    },
    TailableAwait { // from class: com.mongodb.CursorType.3
        @Override // com.mongodb.CursorType
        public boolean isTailable() {
            return true;
        }
    };

    public abstract boolean isTailable();
}
