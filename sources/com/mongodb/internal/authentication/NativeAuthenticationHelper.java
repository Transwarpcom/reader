package com.mongodb.internal.authentication;

import ch.qos.logback.classic.ClassicConstants;
import com.mongodb.internal.HexUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/authentication/NativeAuthenticationHelper.class */
public final class NativeAuthenticationHelper {
    private static final Charset UTF_8_CHARSET = Charset.forName("UTF-8");

    public static String createAuthenticationHash(String userName, char[] password) {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(userName.length() + 20 + password.length);
        try {
            bout.write(userName.getBytes(UTF_8_CHARSET));
            bout.write(":mongo:".getBytes(UTF_8_CHARSET));
            bout.write(new String(password).getBytes(UTF_8_CHARSET));
            return HexUtils.hexMD5(bout.toByteArray());
        } catch (IOException ioe) {
            throw new RuntimeException("impossible", ioe);
        }
    }

    public static BsonDocument getAuthCommand(String userName, char[] password, String nonce) {
        return getAuthCommand(userName, createAuthenticationHash(userName, password), nonce);
    }

    public static BsonDocument getAuthCommand(String userName, String authHash, String nonce) {
        String key = nonce + userName + authHash;
        BsonDocument cmd = new BsonDocument();
        cmd.put("authenticate", (BsonValue) new BsonInt32(1));
        cmd.put(ClassicConstants.USER_MDC_KEY, (BsonValue) new BsonString(userName));
        cmd.put("nonce", (BsonValue) new BsonString(nonce));
        cmd.put("key", (BsonValue) new BsonString(HexUtils.hexMD5(key.getBytes(UTF_8_CHARSET))));
        return cmd;
    }

    public static BsonDocument getNonceCommand() {
        return new BsonDocument("getnonce", new BsonInt32(1));
    }

    private NativeAuthenticationHelper() {
    }
}
