package io.vertx.core.eventbus.impl.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.eventbus.ReplyFailure;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/codecs/ReplyExceptionMessageCodec.class */
public class ReplyExceptionMessageCodec implements MessageCodec<ReplyException, ReplyException> {
    @Override // io.vertx.core.eventbus.MessageCodec
    public void encodeToWire(Buffer buffer, ReplyException body) {
        buffer.appendByte((byte) body.failureType().toInt());
        buffer.appendInt(body.failureCode());
        if (body.getMessage() == null) {
            buffer.appendByte((byte) 0);
            return;
        }
        buffer.appendByte((byte) 1);
        byte[] encoded = body.getMessage().getBytes(CharsetUtil.UTF_8);
        buffer.appendInt(encoded.length);
        buffer.appendBytes(encoded);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.eventbus.MessageCodec
    public ReplyException decodeFromWire(int pos, Buffer buffer) {
        String message;
        int i = buffer.getByte(pos);
        ReplyFailure rf = ReplyFailure.fromInt(i);
        int pos2 = pos + 1;
        int failureCode = buffer.getInt(pos2);
        int pos3 = pos2 + 4;
        boolean isNull = buffer.getByte(pos3) == 0;
        if (!isNull) {
            int pos4 = pos3 + 1;
            int strLength = buffer.getInt(pos4);
            int pos5 = pos4 + 4;
            byte[] bytes = buffer.getBytes(pos5, pos5 + strLength);
            message = new String(bytes, CharsetUtil.UTF_8);
        } else {
            message = null;
        }
        return new ReplyException(rf, failureCode, message);
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public ReplyException transform(ReplyException exception) {
        return exception;
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public String name() {
        return "replyexception";
    }

    @Override // io.vertx.core.eventbus.MessageCodec
    public byte systemCodecID() {
        return (byte) 15;
    }
}
