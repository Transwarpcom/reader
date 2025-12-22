package io.vertx.ext.auth;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.impl.HashingStrategyImpl;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/HashingStrategy.class */
public interface HashingStrategy {
    String hash(String str, Map<String, String> map, String str2, String str3);

    boolean verify(String str, String str2);

    HashingAlgorithm get(String str);

    @Fluent
    HashingStrategy put(String str, HashingAlgorithm hashingAlgorithm);

    static HashingStrategy load() {
        HashingStrategyImpl strategy = new HashingStrategyImpl();
        ServiceLoader<HashingAlgorithm> serviceLoader = ServiceLoader.load(HashingAlgorithm.class);
        Iterator<HashingAlgorithm> it = serviceLoader.iterator();
        while (it.hasNext()) {
            HashingAlgorithm algorithm = it.next();
            strategy.add(algorithm);
        }
        return strategy;
    }
}
