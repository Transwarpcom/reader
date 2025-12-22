package io.vertx.core.eventbus.impl.clustered;

import io.netty.util.CharsetUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.impl.CodecManager;
import io.vertx.core.eventbus.impl.EventBusImpl;
import io.vertx.core.eventbus.impl.MessageImpl;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.impl.ServerID;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/clustered/ClusteredMessage.class */
public class ClusteredMessage<U, V> extends MessageImpl<U, V> {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) ClusteredMessage.class);
    private static final byte WIRE_PROTOCOL_VERSION = 1;
    private ServerID sender;
    private Buffer wireBuffer;
    private int bodyPos;
    private int headersPos;
    private boolean fromWire;

    public ClusteredMessage() {
    }

    public ClusteredMessage(ServerID sender, String address, String replyAddress, MultiMap headers, U sentBody, MessageCodec<U, V> messageCodec, boolean send, EventBusImpl bus, Handler<AsyncResult<Void>> writeHandler) {
        super(address, replyAddress, headers, sentBody, messageCodec, send, bus, writeHandler);
        this.sender = sender;
    }

    protected ClusteredMessage(ClusteredMessage<U, V> other) {
        super(other);
        this.sender = other.sender;
        if (other.sentBody == null) {
            this.wireBuffer = other.wireBuffer;
            this.bodyPos = other.bodyPos;
            this.headersPos = other.headersPos;
        }
        this.fromWire = other.fromWire;
    }

    @Override // io.vertx.core.eventbus.impl.MessageImpl
    public ClusteredMessage<U, V> copyBeforeReceive() {
        return new ClusteredMessage<>(this);
    }

    @Override // io.vertx.core.eventbus.impl.MessageImpl, io.vertx.core.eventbus.Message
    public MultiMap headers() {
        if (this.headers == null) {
            if (this.headersPos != 0) {
                decodeHeaders();
            }
            if (this.headers == null) {
                this.headers = new CaseInsensitiveHeaders();
            }
        }
        return this.headers;
    }

    @Override // io.vertx.core.eventbus.impl.MessageImpl, io.vertx.core.eventbus.Message
    public V body() {
        if (this.receivedBody == null && this.bodyPos != 0) {
            decodeBody();
        }
        return this.receivedBody;
    }

    @Override // io.vertx.core.eventbus.impl.MessageImpl, io.vertx.core.eventbus.Message
    public String replyAddress() {
        return this.replyAddress;
    }

    public Buffer encodeToWire() {
        Buffer buffer = Buffer.buffer(1024);
        buffer.appendInt(0);
        buffer.appendByte((byte) 1);
        byte systemCodecID = this.messageCodec.systemCodecID();
        buffer.appendByte(systemCodecID);
        if (systemCodecID == -1) {
            writeString(buffer, this.messageCodec.name());
        }
        buffer.appendByte(this.send ? (byte) 0 : (byte) 1);
        writeString(buffer, this.address);
        if (this.replyAddress != null) {
            writeString(buffer, this.replyAddress);
        } else {
            buffer.appendInt(0);
        }
        buffer.appendInt(this.sender.port);
        writeString(buffer, this.sender.host);
        encodeHeaders(buffer);
        writeBody(buffer);
        buffer.setInt(0, buffer.length() - 4);
        return buffer;
    }

    public void readFromWire(Buffer buffer, CodecManager codecManager) {
        byte protocolVersion = buffer.getByte(0);
        if (protocolVersion > 1) {
            throw new IllegalStateException("Invalid wire protocol version " + ((int) protocolVersion) + " should be <= 1");
        }
        int pos = 0 + 1;
        byte systemCodecCode = buffer.getByte(pos);
        int pos2 = pos + 1;
        if (systemCodecCode == -1) {
            int length = buffer.getInt(pos2);
            int pos3 = pos2 + 4;
            byte[] bytes = buffer.getBytes(pos3, pos3 + length);
            String codecName = new String(bytes, CharsetUtil.UTF_8);
            this.messageCodec = codecManager.getCodec(codecName);
            if (this.messageCodec == null) {
                throw new IllegalStateException("No message codec registered with name " + codecName);
            }
            pos2 = pos3 + length;
        } else {
            this.messageCodec = codecManager.systemCodecs()[systemCodecCode];
        }
        byte bsend = buffer.getByte(pos2);
        this.send = bsend == 0;
        int pos4 = pos2 + 1;
        int length2 = buffer.getInt(pos4);
        int pos5 = pos4 + 4;
        byte[] bytes2 = buffer.getBytes(pos5, pos5 + length2);
        this.address = new String(bytes2, CharsetUtil.UTF_8);
        int pos6 = pos5 + length2;
        int length3 = buffer.getInt(pos6);
        int pos7 = pos6 + 4;
        if (length3 != 0) {
            byte[] bytes3 = buffer.getBytes(pos7, pos7 + length3);
            this.replyAddress = new String(bytes3, CharsetUtil.UTF_8);
            pos7 += length3;
        }
        int senderPort = buffer.getInt(pos7);
        int pos8 = pos7 + 4;
        int length4 = buffer.getInt(pos8);
        int pos9 = pos8 + 4;
        byte[] bytes4 = buffer.getBytes(pos9, pos9 + length4);
        String senderHost = new String(bytes4, CharsetUtil.UTF_8);
        int pos10 = pos9 + length4;
        this.headersPos = pos10;
        int headersLength = buffer.getInt(pos10);
        this.bodyPos = pos10 + headersLength;
        this.sender = new ServerID(senderPort, senderHost);
        this.wireBuffer = buffer;
        this.fromWire = true;
    }

    private void decodeBody() {
        this.receivedBody = this.messageCodec.decodeFromWire(this.bodyPos, this.wireBuffer);
        this.bodyPos = 0;
    }

    private void encodeHeaders(Buffer buffer) {
        if (this.headers != null && !this.headers.isEmpty()) {
            int headersLengthPos = buffer.length();
            buffer.appendInt(0);
            buffer.appendInt(this.headers.size());
            List<Map.Entry<String, String>> entries = this.headers.entries();
            for (Map.Entry<String, String> entry : entries) {
                writeString(buffer, entry.getKey());
                writeString(buffer, entry.getValue());
            }
            int headersEndPos = buffer.length();
            buffer.setInt(headersLengthPos, headersEndPos - headersLengthPos);
            return;
        }
        buffer.appendInt(4);
    }

    private void decodeHeaders() {
        int length = this.wireBuffer.getInt(this.headersPos);
        if (length != 4) {
            this.headersPos += 4;
            int numHeaders = this.wireBuffer.getInt(this.headersPos);
            this.headersPos += 4;
            this.headers = new CaseInsensitiveHeaders();
            for (int i = 0; i < numHeaders; i++) {
                int keyLength = this.wireBuffer.getInt(this.headersPos);
                this.headersPos += 4;
                byte[] bytes = this.wireBuffer.getBytes(this.headersPos, this.headersPos + keyLength);
                String key = new String(bytes, CharsetUtil.UTF_8);
                this.headersPos += keyLength;
                int valLength = this.wireBuffer.getInt(this.headersPos);
                this.headersPos += 4;
                byte[] bytes2 = this.wireBuffer.getBytes(this.headersPos, this.headersPos + valLength);
                String val = new String(bytes2, CharsetUtil.UTF_8);
                this.headersPos += valLength;
                this.headers.add(key, val);
            }
        }
        this.headersPos = 0;
    }

    private void writeBody(Buffer buff) {
        this.messageCodec.encodeToWire(buff, this.sentBody);
    }

    private void writeString(Buffer buff, String str) {
        byte[] strBytes = str.getBytes(CharsetUtil.UTF_8);
        buff.appendInt(strBytes.length);
        buff.appendBytes(strBytes);
    }

    ServerID getSender() {
        return this.sender;
    }

    public boolean isFromWire() {
        return this.fromWire;
    }

    @Override // io.vertx.core.eventbus.impl.MessageImpl
    protected boolean isLocal() {
        return !isFromWire();
    }
}
