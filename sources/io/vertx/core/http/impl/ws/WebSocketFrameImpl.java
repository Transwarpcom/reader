package io.vertx.core.http.impl.ws;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCounted;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.impl.FrameType;
import java.nio.charset.StandardCharsets;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/ws/WebSocketFrameImpl.class */
public class WebSocketFrameImpl implements WebSocketFrameInternal, ReferenceCounted {
    private final FrameType type;
    private final boolean isFinalFrame;
    private ByteBuf binaryData;
    private boolean closeParsed;
    private short closeStatusCode;
    private String closeReason;

    public WebSocketFrameImpl() {
        this(null, Unpooled.EMPTY_BUFFER, true);
    }

    public WebSocketFrameImpl(FrameType frameType) {
        this(frameType, Unpooled.EMPTY_BUFFER, true);
    }

    public WebSocketFrameImpl(String textData) {
        this(textData, true);
    }

    public WebSocketFrameImpl(String textData, boolean isFinalFrame) {
        this.closeParsed = false;
        this.type = FrameType.TEXT;
        this.isFinalFrame = isFinalFrame;
        this.binaryData = Unpooled.copiedBuffer(textData, CharsetUtil.UTF_8);
    }

    public WebSocketFrameImpl(FrameType type, ByteBuf binaryData) {
        this(type, binaryData, true);
    }

    public WebSocketFrameImpl(FrameType type, ByteBuf binaryData, boolean isFinalFrame) {
        this.closeParsed = false;
        this.type = type;
        this.isFinalFrame = isFinalFrame;
        this.binaryData = Unpooled.unreleasableBuffer(binaryData);
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public boolean isText() {
        return this.type == FrameType.TEXT;
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public boolean isBinary() {
        return this.type == FrameType.BINARY;
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public boolean isContinuation() {
        return this.type == FrameType.CONTINUATION;
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public boolean isClose() {
        return this.type == FrameType.CLOSE;
    }

    @Override // io.vertx.core.http.impl.ws.WebSocketFrameInternal
    public ByteBuf getBinaryData() {
        return this.binaryData;
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public String textData() {
        return getBinaryData().toString(CharsetUtil.UTF_8);
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public Buffer binaryData() {
        return Buffer.buffer(this.binaryData);
    }

    @Override // io.vertx.core.http.impl.ws.WebSocketFrameInternal
    public void setBinaryData(ByteBuf binaryData) {
        if (this.binaryData != null) {
            this.binaryData.release();
        }
        this.binaryData = binaryData;
    }

    @Override // io.vertx.core.http.impl.ws.WebSocketFrameInternal
    public void setTextData(String textData) {
        if (this.binaryData != null) {
            this.binaryData.release();
        }
        this.binaryData = Unpooled.copiedBuffer(textData, CharsetUtil.UTF_8);
    }

    @Override // io.vertx.core.http.impl.ws.WebSocketFrameInternal
    public int length() {
        return this.binaryData.readableBytes();
    }

    public String toString() {
        return getClass().getSimpleName() + "(type: " + this.type + ", data: " + getBinaryData() + ')';
    }

    @Override // io.netty.util.ReferenceCounted
    public int refCnt() {
        return this.binaryData.refCnt();
    }

    @Override // io.netty.util.ReferenceCounted
    public ReferenceCounted retain() {
        return this.binaryData.retain();
    }

    @Override // io.netty.util.ReferenceCounted
    public ReferenceCounted retain(int increment) {
        return this.binaryData.retain(increment);
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release() {
        return this.binaryData.release();
    }

    @Override // io.netty.util.ReferenceCounted
    public boolean release(int decrement) {
        return this.binaryData.release(decrement);
    }

    @Override // io.netty.util.ReferenceCounted
    public ReferenceCounted touch() {
        this.binaryData.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public ReferenceCounted touch(Object hint) {
        this.binaryData.touch(hint);
        return this;
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public boolean isFinal() {
        return this.isFinalFrame;
    }

    private void parseCloseFrame() {
        int length = length();
        if (length < 2) {
            this.closeStatusCode = (short) 1000;
            this.closeReason = null;
            return;
        }
        int index = this.binaryData.readerIndex();
        this.closeStatusCode = this.binaryData.getShort(index);
        if (length == 2) {
            this.closeReason = null;
        } else {
            this.closeReason = this.binaryData.toString(index + 2, length - 2, StandardCharsets.UTF_8);
        }
    }

    private void checkClose() {
        if (!isClose()) {
            throw new IllegalStateException("This should be a close frame");
        }
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public short closeStatusCode() {
        checkClose();
        if (!this.closeParsed) {
            parseCloseFrame();
        }
        return this.closeStatusCode;
    }

    @Override // io.vertx.core.http.WebSocketFrame
    public String closeReason() {
        checkClose();
        if (!this.closeParsed) {
            parseCloseFrame();
        }
        return this.closeReason;
    }

    @Override // io.vertx.core.http.impl.ws.WebSocketFrameInternal
    public FrameType type() {
        return this.type;
    }
}
