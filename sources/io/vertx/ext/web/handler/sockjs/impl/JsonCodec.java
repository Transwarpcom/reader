package io.vertx.ext.web.handler.sockjs.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.EncodeException;
import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/JsonCodec.class */
public class JsonCodec {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class, new JsonSerializer<String>() { // from class: io.vertx.ext.web.handler.sockjs.impl.JsonCodec.1
            final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
            final int[] ESCAPE_CODES = CharTypes.get7BitOutputEscapes();

            private void writeUnicodeEscape(JsonGenerator gen, char c) throws IOException {
                gen.writeRaw('\\');
                gen.writeRaw('u');
                gen.writeRaw(this.HEX_CHARS[(c >> '\f') & 15]);
                gen.writeRaw(this.HEX_CHARS[(c >> '\b') & 15]);
                gen.writeRaw(this.HEX_CHARS[(c >> 4) & 15]);
                gen.writeRaw(this.HEX_CHARS[c & 15]);
            }

            private void writeShortEscape(JsonGenerator gen, char c) throws IOException {
                gen.writeRaw('\\');
                gen.writeRaw(c);
            }

            @Override // com.fasterxml.jackson.databind.JsonSerializer
            public void serialize(String str, JsonGenerator gen, SerializerProvider provider) throws IOException {
                int status = ((JsonWriteContext) gen.getOutputContext()).writeValue();
                switch (status) {
                    case 1:
                        gen.writeRaw(',');
                        break;
                    case 2:
                        gen.writeRaw(':');
                        break;
                    case 5:
                        throw new JsonGenerationException("Can not write string value here", gen);
                }
                gen.writeRaw('\"');
                char[] charArray = str.toCharArray();
                int length = charArray.length;
                for (int i = 0; i < length; i++) {
                    char c = charArray[i];
                    if (c >= 128) {
                        writeUnicodeEscape(gen, c);
                    } else {
                        int code = c < this.ESCAPE_CODES.length ? this.ESCAPE_CODES[c] : 0;
                        if (code == 0) {
                            gen.writeRaw(c);
                        } else if (code == -1) {
                            writeUnicodeEscape(gen, c);
                        } else {
                            writeShortEscape(gen, (char) code);
                        }
                    }
                }
                gen.writeRaw('\"');
            }
        });
        mapper.registerModule(simpleModule);
    }

    public static String encode(Object obj) throws EncodeException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new EncodeException("Failed to encode as JSON");
        }
    }

    public static <T> T decodeValue(String str, Class<T> cls) throws DecodeException {
        try {
            return (T) mapper.readValue(str, cls);
        } catch (Exception e) {
            throw new DecodeException("Failed to decode");
        }
    }
}
