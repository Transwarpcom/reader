package io.vertx.core.parsetools.impl;

import io.netty.buffer.Unpooled;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.parsetools.RecordParser;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/parsetools/impl/RecordParserImpl.class */
public class RecordParserImpl implements RecordParser {
    private static final Buffer EMPTY_BUFFER = Buffer.buffer(Unpooled.EMPTY_BUFFER);
    private int pos;
    private int start;
    private int delimPos;
    private boolean delimited;
    private byte[] delim;
    private int recordSize;
    private int maxRecordSize;
    private Handler<Buffer> eventHandler;
    private Handler<Void> endHandler;
    private Handler<Throwable> exceptionHandler;
    private boolean parsing;
    private boolean streamEnded;
    private final ReadStream<Buffer> stream;
    private Buffer buff = EMPTY_BUFFER;
    private long demand = Long.MAX_VALUE;

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    private RecordParserImpl(ReadStream<Buffer> stream) {
        this.stream = stream;
    }

    @Override // io.vertx.core.parsetools.RecordParser
    public void setOutput(Handler<Buffer> output) {
        Objects.requireNonNull(output, "output");
        this.eventHandler = output;
    }

    public static Buffer latin1StringToBytes(String str) {
        byte[] bytes = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            bytes[i] = (byte) (c & 255);
        }
        return Buffer.buffer(bytes);
    }

    public static RecordParser newDelimited(String delim, ReadStream<Buffer> stream, Handler<Buffer> output) {
        return newDelimited(latin1StringToBytes(delim), stream, output);
    }

    public static RecordParser newDelimited(Buffer delim, ReadStream<Buffer> stream, Handler<Buffer> output) {
        RecordParserImpl ls = new RecordParserImpl(stream);
        ls.handler2(output);
        ls.delimitedMode(delim);
        return ls;
    }

    public static RecordParser newFixed(int size, ReadStream<Buffer> stream, Handler<Buffer> output) {
        Arguments.require(size > 0, "Size must be > 0");
        RecordParserImpl ls = new RecordParserImpl(stream);
        ls.handler2(output);
        ls.fixedSizeMode(size);
        return ls;
    }

    @Override // io.vertx.core.parsetools.RecordParser
    public void delimitedMode(String delim) {
        delimitedMode(latin1StringToBytes(delim));
    }

    @Override // io.vertx.core.parsetools.RecordParser
    public void delimitedMode(Buffer delim) {
        Objects.requireNonNull(delim, "delim");
        this.delimited = true;
        this.delim = delim.getBytes();
        this.delimPos = 0;
    }

    @Override // io.vertx.core.parsetools.RecordParser
    public void fixedSizeMode(int size) {
        Arguments.require(size > 0, "Size must be > 0");
        this.delimited = false;
        this.recordSize = size;
    }

    @Override // io.vertx.core.parsetools.RecordParser
    public RecordParser maxRecordSize(int size) {
        Arguments.require(size > 0, "Size must be > 0");
        this.maxRecordSize = size;
        return this;
    }

    private void handleParsing() {
        int next;
        if (this.parsing) {
            return;
        }
        this.parsing = true;
        while (true) {
            try {
                if (this.demand > 0) {
                    if (this.delimited) {
                        next = parseDelimited();
                    } else {
                        next = parseFixed();
                    }
                    if (next == -1) {
                        if (this.streamEnded) {
                            if (this.buff.length() == 0) {
                                break;
                            } else {
                                next = this.buff.length();
                            }
                        } else {
                            ReadStream<Buffer> s = this.stream;
                            if (s != null) {
                                s.resume2();
                            }
                            if (!this.streamEnded) {
                                break;
                            }
                        }
                    }
                    if (this.demand != Long.MAX_VALUE) {
                        this.demand--;
                    }
                    Buffer event = this.buff.getBuffer(this.start, next);
                    this.start = this.pos;
                    Handler<Buffer> handler = this.eventHandler;
                    if (handler != null) {
                        handler.handle(event);
                    }
                    if (this.streamEnded) {
                        break;
                    }
                } else {
                    ReadStream<Buffer> s2 = this.stream;
                    if (s2 != null) {
                        s2.pause2();
                    }
                }
            } finally {
                this.parsing = false;
            }
        }
        int len = this.buff.length();
        if (this.start == len) {
            this.buff = EMPTY_BUFFER;
        } else {
            this.buff = this.buff.getBuffer(this.start, len);
        }
        this.pos -= this.start;
        this.start = 0;
        if (this.streamEnded) {
            end();
        }
    }

    private int parseDelimited() {
        int len = this.buff.length();
        while (this.pos < len) {
            if (this.buff.getByte(this.pos) == this.delim[this.delimPos]) {
                this.delimPos++;
                if (this.delimPos == this.delim.length) {
                    this.pos++;
                    this.delimPos = 0;
                    return this.pos - this.delim.length;
                }
            } else if (this.delimPos > 0) {
                this.pos -= this.delimPos;
                this.delimPos = 0;
            }
            this.pos++;
        }
        return -1;
    }

    private int parseFixed() {
        int len = this.buff.length();
        if (len - this.start >= this.recordSize) {
            int end = this.start + this.recordSize;
            this.pos = end;
            return end;
        }
        return -1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.Handler
    public void handle(Buffer buffer) {
        if (this.buff.length() == 0) {
            this.buff = buffer;
        } else {
            this.buff.appendBuffer(buffer);
        }
        handleParsing();
        if (this.buff != null && this.maxRecordSize > 0 && this.buff.length() > this.maxRecordSize) {
            IllegalStateException ex = new IllegalStateException("The current record is too long");
            if (this.exceptionHandler != null) {
                this.exceptionHandler.handle(ex);
                return;
            }
            throw ex;
        }
    }

    private void end() {
        Handler<Void> handler = this.endHandler;
        if (handler != null) {
            handler.handle(null);
        }
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public RecordParser exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        this.eventHandler = handler;
        if (this.stream != null) {
            if (handler != null) {
                this.stream.endHandler(v -> {
                    this.streamEnded = true;
                    handleParsing();
                });
                this.stream.exceptionHandler(err -> {
                    if (this.exceptionHandler != null) {
                        this.exceptionHandler.handle(err);
                    }
                });
                this.stream.handler2(this);
            } else {
                this.stream.handler2(null);
                this.stream.endHandler(null);
                this.stream.exceptionHandler((Handler<Throwable>) null);
            }
        }
        return this;
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        this.demand = 0L;
        return this;
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        Arguments.require(amount > 0, "Fetch amount must be > 0");
        this.demand += amount;
        if (this.demand < 0) {
            this.demand = Long.MAX_VALUE;
        }
        handleParsing();
        return this;
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.parsetools.RecordParser, io.vertx.core.streams.ReadStream
    public ReadStream<Buffer> endHandler(Handler<Void> handler) {
        this.endHandler = handler;
        return this;
    }
}
