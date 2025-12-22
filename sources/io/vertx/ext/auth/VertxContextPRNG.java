package io.vertx.ext.auth;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import java.util.Base64;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/VertxContextPRNG.class */
public interface VertxContextPRNG {
    @GenIgnore
    void nextBytes(byte[] bArr);

    int nextInt();

    int nextInt(int i);

    static VertxContextPRNG current() {
        Context currentContext = Vertx.currentContext();
        if (currentContext != null) {
            return current(currentContext);
        }
        throw new IllegalStateException("Not running in a Vert.x Context.");
    }

    @GenIgnore
    static VertxContextPRNG current(Context context) {
        PRNG random = (PRNG) context.get("__vertx.VertxContextPRNG");
        if (random == null) {
            synchronized (context) {
                random = (PRNG) context.get("__vertx.VertxContextPRNG");
                if (random == null) {
                    random = new PRNG(context.owner());
                    context.put("__vertx.VertxContextPRNG", random);
                }
            }
        }
        return random;
    }

    static VertxContextPRNG current(Vertx vertx) {
        Context currentContext = Vertx.currentContext();
        if (currentContext != null) {
            return current(currentContext);
        }
        return new PRNG(vertx);
    }

    default String nextString(int length) {
        byte[] data = new byte[length];
        nextBytes(data);
        return Base64.getMimeEncoder().encodeToString(data);
    }
}
