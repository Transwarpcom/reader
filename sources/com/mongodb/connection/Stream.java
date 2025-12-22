package com.mongodb.connection;

import com.mongodb.ServerAddress;
import java.io.IOException;
import java.util.List;
import org.bson.ByteBuf;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/Stream.class */
public interface Stream extends BufferProvider {
    void open() throws IOException;

    void openAsync(AsyncCompletionHandler<Void> asyncCompletionHandler);

    void write(List<ByteBuf> list) throws IOException;

    ByteBuf read(int i) throws IOException;

    void writeAsync(List<ByteBuf> list, AsyncCompletionHandler<Void> asyncCompletionHandler);

    void readAsync(int i, AsyncCompletionHandler<ByteBuf> asyncCompletionHandler);

    ServerAddress getAddress();

    void close();

    boolean isClosed();
}
