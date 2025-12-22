package io.vertx.ext.auth;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Collections;
import java.util.Set;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/HashingAlgorithm.class */
public interface HashingAlgorithm {
    String id();

    @GenIgnore
    String hash(HashString hashString, String str);

    default Set<String> params() {
        return Collections.emptySet();
    }

    default boolean needsSeparator() {
        return true;
    }
}
