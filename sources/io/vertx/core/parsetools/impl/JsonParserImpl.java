package io.vertx.core.parsetools.impl;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.parsetools.JsonEvent;
import io.vertx.core.parsetools.JsonEventType;
import io.vertx.core.parsetools.JsonParser;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/parsetools/impl/JsonParserImpl.class */
public class JsonParserImpl implements JsonParser {
    private NonBlockingJsonParser parser;
    private JsonToken currentToken;
    private Handler<JsonEvent> eventHandler;
    private BufferingHandler arrayHandler;
    private BufferingHandler objectHandler;
    private Handler<Throwable> exceptionHandler;
    private String currentField;
    private Handler<Void> endHandler;
    private boolean ended;
    private final ReadStream<Buffer> stream;
    private Handler<JsonToken> tokenHandler = this::handleToken;
    private long demand = Long.MAX_VALUE;

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<JsonEvent> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    public JsonParserImpl(ReadStream<Buffer> stream) {
        this.stream = stream;
        JsonFactory factory = new JsonFactory();
        try {
            this.parser = (NonBlockingJsonParser) factory.createNonBlockingByteArrayParser();
        } catch (Exception e) {
            throw new VertxException(e);
        }
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<JsonEvent> pause2() {
        this.demand = 0L;
        return this;
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<JsonEvent> resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<JsonEvent> fetch2(long amount) throws Exception {
        Arguments.require(amount > 0, "Fetch amount must be > 0L");
        this.demand += amount;
        if (this.demand < 0) {
            this.demand = Long.MAX_VALUE;
        }
        checkPending();
        return this;
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream
    public ReadStream<JsonEvent> endHandler(Handler<Void> handler) {
        this.endHandler = handler;
        return this;
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<JsonEvent> handler2(Handler<JsonEvent> handler) {
        this.eventHandler = handler;
        if (this.stream != null) {
            if (handler != null) {
                this.stream.endHandler(v -> {
                    end();
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

    private void handleEvent(JsonEvent event) {
        if (this.demand != Long.MAX_VALUE) {
            this.demand--;
        }
        Handler<JsonEvent> handler = this.eventHandler;
        if (handler != null) {
            handler.handle(event);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleToken(JsonToken token) {
        try {
            switch (token) {
                case START_OBJECT:
                    BufferingHandler handler = this.objectHandler;
                    if (handler != null) {
                        this.tokenHandler = handler;
                        handler.handle(token);
                        break;
                    } else {
                        handleEvent(new JsonEventImpl(JsonEventType.START_OBJECT, this.currentField, null));
                        break;
                    }
                case START_ARRAY:
                    BufferingHandler handler2 = this.arrayHandler;
                    if (handler2 != null) {
                        this.tokenHandler = handler2;
                        handler2.handle(token);
                        break;
                    } else {
                        handleEvent(new JsonEventImpl(JsonEventType.START_ARRAY, this.currentField, null));
                        break;
                    }
                case FIELD_NAME:
                    this.currentField = this.parser.getCurrentName();
                    break;
                case VALUE_STRING:
                    String f = this.currentField;
                    this.currentField = null;
                    handleEvent(new JsonEventImpl(JsonEventType.VALUE, f, this.parser.getText()));
                    break;
                case VALUE_TRUE:
                    handleEvent(new JsonEventImpl(JsonEventType.VALUE, this.currentField, Boolean.TRUE));
                    break;
                case VALUE_FALSE:
                    handleEvent(new JsonEventImpl(JsonEventType.VALUE, this.currentField, Boolean.FALSE));
                    break;
                case VALUE_NULL:
                    handleEvent(new JsonEventImpl(JsonEventType.VALUE, this.currentField, null));
                    break;
                case VALUE_NUMBER_INT:
                    handleEvent(new JsonEventImpl(JsonEventType.VALUE, this.currentField, Long.valueOf(this.parser.getLongValue())));
                    break;
                case VALUE_NUMBER_FLOAT:
                    handleEvent(new JsonEventImpl(JsonEventType.VALUE, this.currentField, Double.valueOf(this.parser.getDoubleValue())));
                    break;
                case END_OBJECT:
                    handleEvent(new JsonEventImpl(JsonEventType.END_OBJECT, null, null));
                    break;
                case END_ARRAY:
                    handleEvent(new JsonEventImpl(JsonEventType.END_ARRAY, null, null));
                    break;
                default:
                    throw new UnsupportedOperationException("Token " + token + " not implemented");
            }
        } catch (IOException e) {
            throw new DecodeException(e.getMessage());
        }
    }

    @Override // io.vertx.core.Handler
    public void handle(Buffer event) throws Exception {
        byte[] bytes = event.getBytes();
        try {
            this.parser.feedInput(bytes, 0, bytes.length);
            checkPending();
        } catch (IOException e) {
            if (this.exceptionHandler != null) {
                this.exceptionHandler.handle(e);
                return;
            }
            throw new DecodeException(e.getMessage(), e);
        }
    }

    @Override // io.vertx.core.parsetools.JsonParser
    public void end() throws Exception {
        if (this.ended) {
            throw new IllegalStateException("Parsing already done");
        }
        this.ended = true;
        this.parser.endOfInput();
        checkPending();
    }

    private void checkPending() throws Exception {
        JsonToken next;
        while (true) {
            try {
                if (this.currentToken == null && (next = this.parser.nextToken()) != null && next != JsonToken.NOT_AVAILABLE) {
                    this.currentToken = next;
                }
                if (this.currentToken != null) {
                    if (this.demand <= 0) {
                        break;
                    }
                    JsonToken token = this.currentToken;
                    this.currentToken = null;
                    this.tokenHandler.handle(token);
                } else if (this.ended) {
                    if (this.endHandler != null) {
                        this.endHandler.handle(null);
                        return;
                    }
                    return;
                }
            } catch (IOException e) {
                if (this.exceptionHandler != null) {
                    this.exceptionHandler.handle(e);
                    return;
                }
                throw new DecodeException(e.getMessage());
            } catch (Exception e2) {
                if (this.exceptionHandler != null) {
                    this.exceptionHandler.handle(e2);
                    return;
                }
                throw e2;
            }
        }
        if (this.demand == 0) {
            if (this.stream != null) {
                this.stream.pause2();
            }
        } else if (this.stream != null) {
            this.stream.resume2();
        }
    }

    @Override // io.vertx.core.parsetools.JsonParser
    public JsonParser objectEventMode() {
        if (this.objectHandler != null) {
            this.objectHandler = null;
            this.tokenHandler = this::handleToken;
        }
        return this;
    }

    @Override // io.vertx.core.parsetools.JsonParser
    public JsonParser objectValueMode() {
        if (this.objectHandler == null) {
            BufferingHandler handler = new BufferingHandler();
            handler.handler = buffer -> {
                handleEvent(new JsonEventImpl(JsonEventType.VALUE, this.currentField, new JsonObject((Map<String, Object>) handler.convert(Map.class)), handler.buffer));
            };
            this.objectHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.parsetools.JsonParser
    public JsonParser arrayEventMode() {
        if (this.arrayHandler != null) {
            this.arrayHandler = null;
            this.tokenHandler = this::handleToken;
        }
        return this;
    }

    @Override // io.vertx.core.parsetools.JsonParser
    public JsonParser arrayValueMode() {
        if (this.arrayHandler == null) {
            BufferingHandler handler = new BufferingHandler();
            handler.handler = buffer -> {
                handleEvent(new JsonEventImpl(JsonEventType.VALUE, this.currentField, new JsonArray((List) handler.convert(List.class)), handler.buffer));
            };
            this.arrayHandler = handler;
        }
        return this;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/parsetools/impl/JsonParserImpl$BufferingHandler.class */
    private class BufferingHandler implements Handler<JsonToken> {
        Handler<Void> handler;
        int depth;
        TokenBuffer buffer;

        private BufferingHandler() {
        }

        @Override // io.vertx.core.Handler
        public void handle(JsonToken event) {
            try {
                switch (event) {
                    case START_OBJECT:
                    case START_ARRAY:
                        int i = this.depth;
                        this.depth = i + 1;
                        if (i == 0) {
                            this.buffer = new TokenBuffer((ObjectCodec) Json.mapper, false);
                        }
                        if (event == JsonToken.START_OBJECT) {
                            this.buffer.writeStartObject();
                            break;
                        } else {
                            this.buffer.writeStartArray();
                            break;
                        }
                    case FIELD_NAME:
                        this.buffer.writeFieldName(JsonParserImpl.this.parser.getCurrentName());
                        break;
                    case VALUE_STRING:
                        this.buffer.writeString(JsonParserImpl.this.parser.getText());
                        break;
                    case VALUE_TRUE:
                        this.buffer.writeBoolean(true);
                        break;
                    case VALUE_FALSE:
                        this.buffer.writeBoolean(false);
                        break;
                    case VALUE_NULL:
                        this.buffer.writeNull();
                        break;
                    case VALUE_NUMBER_INT:
                        this.buffer.writeNumber(JsonParserImpl.this.parser.getLongValue());
                        break;
                    case VALUE_NUMBER_FLOAT:
                        this.buffer.writeNumber(JsonParserImpl.this.parser.getDoubleValue());
                        break;
                    case END_OBJECT:
                    case END_ARRAY:
                        if (event == JsonToken.END_OBJECT) {
                            this.buffer.writeEndObject();
                        } else {
                            this.buffer.writeEndArray();
                        }
                        int i2 = this.depth - 1;
                        this.depth = i2;
                        if (i2 == 0) {
                            JsonParserImpl jsonParserImpl = JsonParserImpl.this;
                            JsonParserImpl jsonParserImpl2 = JsonParserImpl.this;
                            jsonParserImpl.tokenHandler = x$0 -> {
                                jsonParserImpl2.handleToken(x$0);
                            };
                            this.buffer.flush();
                            this.handler.handle(null);
                            break;
                        }
                        break;
                    default:
                        throw new UnsupportedOperationException("Not implemented " + event);
                }
            } catch (IOException e) {
                throw new VertxException(e);
            }
        }

        <T> T convert(Class<T> cls) {
            try {
                return (T) Json.mapper.readValue(this.buffer.asParser(), cls);
            } catch (Exception e) {
                throw new DecodeException(e.getMessage());
            }
        }
    }

    @Override // io.vertx.core.parsetools.JsonParser
    public JsonParser write(Buffer buffer) throws Exception {
        handle(buffer);
        return this;
    }

    @Override // io.vertx.core.parsetools.JsonParser, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public JsonParser exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }
}
