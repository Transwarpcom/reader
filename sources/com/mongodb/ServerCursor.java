package com.mongodb;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/ServerCursor.class */
public final class ServerCursor implements Serializable {
    private static final long serialVersionUID = -7013636754565190109L;
    private final long id;
    private final ServerAddress address;

    public ServerCursor(long id, ServerAddress address) {
        if (id == 0) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.address = address;
    }

    public long getId() {
        return this.id;
    }

    public ServerAddress getAddress() {
        return this.address;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerCursor that = (ServerCursor) o;
        if (this.id != that.id || !this.address.equals(that.address)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = (int) (this.id ^ (this.id >>> 32));
        return (31 * result) + this.address.hashCode();
    }

    public String toString() {
        return "ServerCursor{getId=" + this.id + ", address=" + this.address + '}';
    }
}
