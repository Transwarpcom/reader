package com.mongodb.internal.connection;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.BufferProvider;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.session.SessionContext;
import java.util.List;
import org.bson.ByteBuf;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalConnection.class */
public interface InternalConnection extends BufferProvider {
    ConnectionDescription getDescription();

    void open();

    void openAsync(SingleResultCallback<Void> singleResultCallback);

    void close();

    boolean opened();

    boolean isClosed();

    <T> T sendAndReceive(CommandMessage commandMessage, Decoder<T> decoder, SessionContext sessionContext);

    <T> void sendAndReceiveAsync(CommandMessage commandMessage, Decoder<T> decoder, SessionContext sessionContext, SingleResultCallback<T> singleResultCallback);

    void sendMessage(List<ByteBuf> list, int i);

    ResponseBuffers receiveMessage(int i);

    void sendMessageAsync(List<ByteBuf> list, int i, SingleResultCallback<Void> singleResultCallback);

    void receiveMessageAsync(int i, SingleResultCallback<ResponseBuffers> singleResultCallback);
}
