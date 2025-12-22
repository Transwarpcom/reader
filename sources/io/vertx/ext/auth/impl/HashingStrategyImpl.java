package io.vertx.ext.auth.impl;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;
import io.vertx.ext.auth.HashingStrategy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/impl/HashingStrategyImpl.class */
public class HashingStrategyImpl implements HashingStrategy {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) HashingStrategyImpl.class);
    private final Map<String, HashingAlgorithm> algorithms = new HashMap();

    public void add(HashingAlgorithm algorithm) {
        this.algorithms.put(algorithm.id(), algorithm);
    }

    @Override // io.vertx.ext.auth.HashingStrategy
    public String hash(String id, Map<String, String> params, String salt, String password) {
        HashingAlgorithm algorithm = this.algorithms.get(id);
        if (algorithm == null) {
            throw new RuntimeException(id + " algorithm is not available.");
        }
        String hash = algorithm.hash(new HashString(id, params, salt), password);
        return HashString.encode(algorithm, params, salt, hash);
    }

    @Override // io.vertx.ext.auth.HashingStrategy
    public boolean verify(String hash, String password) {
        if (hash == null || password == null) {
            return false;
        }
        HashString hashString = new HashString(hash);
        HashingAlgorithm algorithm = this.algorithms.get(hashString.id());
        if (algorithm == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No hash strategy for algorithm: " + hashString.id());
                return false;
            }
            return false;
        }
        if (hashString.hash() == null) {
            return false;
        }
        String hasha = hashString.hash();
        String hashb = algorithm.hash(hashString, password);
        int diff = hasha.length() ^ hashb.length();
        for (int i = 0; i < hasha.length() && i < hashb.length(); i++) {
            diff |= hasha.charAt(i) ^ hashb.charAt(i);
        }
        return diff == 0;
    }

    @Override // io.vertx.ext.auth.HashingStrategy
    public HashingAlgorithm get(String id) {
        return this.algorithms.get(id);
    }

    @Override // io.vertx.ext.auth.HashingStrategy
    public HashingStrategy put(String id, HashingAlgorithm algorithm) {
        if (this.algorithms.containsKey(id)) {
            LOG.warn("Existing algorithm: " + id + " will be replaced!");
        }
        this.algorithms.put(id, algorithm);
        return this;
    }
}
