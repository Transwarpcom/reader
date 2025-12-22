package io.vertx.ext.web.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.apache.commons.lang3.time.TimeZones;
import org.springframework.util.MimeTypeUtils;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/Utils.class */
public class Utils extends io.vertx.core.impl.Utils {
    private static final ZoneId ZONE_GMT = ZoneId.of(TimeZones.GMT_ID);

    public static ClassLoader getClassLoader() {
        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        return tccl == null ? Utils.class.getClassLoader() : tccl;
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r10v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r9v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x00b7: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:37:0x00b7 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x00b3: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('in' java.io.InputStream)]) A[TRY_LEAVE], block:B:35:0x00b3 */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r9v0, names: [in], types: [java.io.InputStream] */
    public static Buffer readResourceToBuffer(String resource) throws IOException {
        ClassLoader cl = getClassLoader();
        try {
            try {
                Buffer buffer = Buffer.buffer();
                InputStream resourceAsStream = cl.getResourceAsStream(resource);
                Throwable th = null;
                if (resourceAsStream != null) {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = resourceAsStream.read(bArr, 0, bArr.length);
                        if (i == -1) {
                            break;
                        }
                        if (i == bArr.length) {
                            buffer.appendBytes(bArr);
                        } else {
                            byte[] bArr2 = new byte[i];
                            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
                            buffer.appendBytes(bArr2);
                        }
                    }
                    if (resourceAsStream != null) {
                        if (0 != 0) {
                            try {
                                resourceAsStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            resourceAsStream.close();
                        }
                    }
                    return buffer;
                }
                if (resourceAsStream != null) {
                    if (0 != 0) {
                        try {
                            resourceAsStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    } else {
                        resourceAsStream.close();
                    }
                }
                return null;
            } finally {
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        throw new RuntimeException(ioe);
    }

    public static String readFileToString(Vertx vertx, String resource) {
        return readFileToString(vertx, resource, StandardCharsets.UTF_8);
    }

    public static String readFileToString(Vertx vertx, String resource, Charset charset) {
        try {
            Buffer buff = vertx.fileSystem().readFileBlocking(resource);
            return buff.toString(charset);
        } catch (Exception e) {
            throw new VertxException(e);
        }
    }

    public static String formatRFC1123DateTime(long time) {
        return DateTimeFormatter.RFC_1123_DATE_TIME.format(Instant.ofEpochMilli(time).atZone(ZONE_GMT));
    }

    public static long parseRFC1123DateTime(String header) {
        if (header != null) {
            try {
                if (!header.isEmpty()) {
                    return LocalDateTime.parse(header, DateTimeFormatter.RFC_1123_DATE_TIME).toInstant(ZoneOffset.UTC).toEpochMilli();
                }
            } catch (DateTimeParseException e) {
                return -1L;
            }
        }
        return -1L;
    }

    public static String pathOffset(String path, RoutingContext context) {
        int prefixLen = 0;
        String mountPoint = context.mountPoint();
        if (mountPoint != null) {
            prefixLen = mountPoint.length();
            if (mountPoint.charAt(mountPoint.length() - 1) == '/') {
                prefixLen--;
            }
        }
        String routePath = context.currentRoute().getPath();
        if (routePath != null) {
            prefixLen += routePath.length();
            if (routePath.charAt(routePath.length() - 1) == '/') {
                prefixLen--;
            }
        }
        return prefixLen != 0 ? path.substring(prefixLen) : path;
    }

    public static long secondsFactor(long millis) {
        return millis - (millis % 1000);
    }

    public static JsonNode toJsonNode(String object) {
        try {
            return new ObjectMapper().readTree(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode toJsonNode(JsonObject object) {
        try {
            return new ObjectMapper().readTree(object.encode());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject toJsonObject(JsonNode node) {
        return new JsonObject(node.toString());
    }

    public static JsonArray toJsonArray(JsonNode node) {
        return new JsonArray(node.toString());
    }

    public static Object toVertxJson(JsonNode node) {
        if (node.isArray()) {
            return toJsonArray(node);
        }
        if (node.isObject()) {
            return toJsonObject(node);
        }
        return node.toString();
    }

    public static boolean isJsonContentType(String contentType) {
        return contentType.contains("application/json") || contentType.contains("+json");
    }

    public static boolean isXMLContentType(String contentType) {
        return contentType.contains(MimeTypeUtils.APPLICATION_XML_VALUE) || contentType.contains(MimeTypeUtils.TEXT_XML_VALUE) || contentType.contains("+xml");
    }

    public static void addToMapIfAbsent(MultiMap map, String key, String value) {
        if (!map.contains(key)) {
            map.set(key, value);
        }
    }
}
