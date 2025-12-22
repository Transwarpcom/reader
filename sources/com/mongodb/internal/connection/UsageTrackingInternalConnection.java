package com.mongodb.internal.connection;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.session.SessionContext;
import java.util.List;
import org.bson.ByteBuf;
import org.bson.codecs.Decoder;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    */
/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/UsageTrackingInternalConnection.class */
class UsageTrackingInternalConnection implements InternalConnection {
    private static final Logger LOGGER = Loggers.getLogger("connection");
    private volatile long openedAt = Long.MAX_VALUE;
    private volatile long lastUsedAt = this.openedAt;
    private final int generation;
    private final InternalConnection wrapped;

    /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    static /* synthetic */ long access$002(com.mongodb.internal.connection.UsageTrackingInternalConnection r6, long r7) {
        /*
            r0 = r6
            r1 = r7
            // decode failed: arraycopy: source index -1 out of bounds for object array[6]
            r0.openedAt = r1
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mongodb.internal.connection.UsageTrackingInternalConnection.access$002(com.mongodb.internal.connection.UsageTrackingInternalConnection, long):long");
    }

    /*  JADX ERROR: Failed to decode insn: 0x0002: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    static /* synthetic */ long access$102(com.mongodb.internal.connection.UsageTrackingInternalConnection r6, long r7) {
        /*
            r0 = r6
            r1 = r7
            // decode failed: arraycopy: source index -1 out of bounds for object array[6]
            r0.lastUsedAt = r1
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mongodb.internal.connection.UsageTrackingInternalConnection.access$102(com.mongodb.internal.connection.UsageTrackingInternalConnection, long):long");
    }

    static {
    }

    UsageTrackingInternalConnection(InternalConnection wrapped, int generation) {
        this.wrapped = wrapped;
        this.generation = generation;
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void open() {
        this.wrapped.open();
        this.openedAt = System.currentTimeMillis();
        this.lastUsedAt = this.openedAt;
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void openAsync(final SingleResultCallback<Void> callback) {
        this.wrapped.openAsync(new SingleResultCallback<Void>() { // from class: com.mongodb.internal.connection.UsageTrackingInternalConnection.1
            /* JADX WARN: Failed to check method for inline after forced processcom.mongodb.internal.connection.UsageTrackingInternalConnection.access$002(com.mongodb.internal.connection.UsageTrackingInternalConnection, long):long */
            /* JADX WARN: Failed to check method for inline after forced processcom.mongodb.internal.connection.UsageTrackingInternalConnection.access$102(com.mongodb.internal.connection.UsageTrackingInternalConnection, long):long */
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(Void result, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                    return;
                }
                UsageTrackingInternalConnection.access$002(UsageTrackingInternalConnection.this, System.currentTimeMillis());
                UsageTrackingInternalConnection.access$102(UsageTrackingInternalConnection.this, UsageTrackingInternalConnection.this.openedAt);
                callback.onResult(null, null);
            }
        });
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void close() {
        this.wrapped.close();
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public boolean opened() {
        return this.wrapped.opened();
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public boolean isClosed() {
        return this.wrapped.isClosed();
    }

    @Override // com.mongodb.connection.BufferProvider
    public ByteBuf getBuffer(int size) {
        return this.wrapped.getBuffer(size);
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void sendMessage(List<ByteBuf> byteBuffers, int lastRequestId) {
        this.wrapped.sendMessage(byteBuffers, lastRequestId);
        this.lastUsedAt = System.currentTimeMillis();
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public <T> T sendAndReceive(CommandMessage commandMessage, Decoder<T> decoder, SessionContext sessionContext) {
        T t = (T) this.wrapped.sendAndReceive(commandMessage, decoder, sessionContext);
        this.lastUsedAt = System.currentTimeMillis();
        return t;
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public <T> void sendAndReceiveAsync(CommandMessage message, Decoder<T> decoder, SessionContext sessionContext, final SingleResultCallback<T> callback) {
        SingleResultCallback<T> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(new SingleResultCallback<T>() { // from class: com.mongodb.internal.connection.UsageTrackingInternalConnection.2
            /* JADX WARN: Failed to check method for inline after forced processcom.mongodb.internal.connection.UsageTrackingInternalConnection.access$102(com.mongodb.internal.connection.UsageTrackingInternalConnection, long):long */
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(T result, Throwable t) {
                UsageTrackingInternalConnection.access$102(UsageTrackingInternalConnection.this, System.currentTimeMillis());
                callback.onResult(result, t);
            }
        }, LOGGER);
        this.wrapped.sendAndReceiveAsync(message, decoder, sessionContext, errHandlingCallback);
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public ResponseBuffers receiveMessage(int responseTo) {
        ResponseBuffers responseBuffers = this.wrapped.receiveMessage(responseTo);
        this.lastUsedAt = System.currentTimeMillis();
        return responseBuffers;
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void sendMessageAsync(List<ByteBuf> byteBuffers, int lastRequestId, final SingleResultCallback<Void> callback) {
        SingleResultCallback<Void> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(new SingleResultCallback<Void>() { // from class: com.mongodb.internal.connection.UsageTrackingInternalConnection.3
            /* JADX WARN: Failed to check method for inline after forced processcom.mongodb.internal.connection.UsageTrackingInternalConnection.access$102(com.mongodb.internal.connection.UsageTrackingInternalConnection, long):long */
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(Void result, Throwable t) {
                UsageTrackingInternalConnection.access$102(UsageTrackingInternalConnection.this, System.currentTimeMillis());
                callback.onResult(result, t);
            }
        }, LOGGER);
        this.wrapped.sendMessageAsync(byteBuffers, lastRequestId, errHandlingCallback);
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public void receiveMessageAsync(int responseTo, final SingleResultCallback<ResponseBuffers> callback) {
        SingleResultCallback<ResponseBuffers> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(new SingleResultCallback<ResponseBuffers>() { // from class: com.mongodb.internal.connection.UsageTrackingInternalConnection.4
            /* JADX WARN: Failed to check method for inline after forced processcom.mongodb.internal.connection.UsageTrackingInternalConnection.access$102(com.mongodb.internal.connection.UsageTrackingInternalConnection, long):long */
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(ResponseBuffers result, Throwable t) {
                UsageTrackingInternalConnection.access$102(UsageTrackingInternalConnection.this, System.currentTimeMillis());
                callback.onResult(result, t);
            }
        }, LOGGER);
        this.wrapped.receiveMessageAsync(responseTo, errHandlingCallback);
    }

    @Override // com.mongodb.internal.connection.InternalConnection
    public ConnectionDescription getDescription() {
        return this.wrapped.getDescription();
    }

    int getGeneration() {
        return this.generation;
    }

    long getOpenedAt() {
        return this.openedAt;
    }

    long getLastUsedAt() {
        return this.lastUsedAt;
    }
}
