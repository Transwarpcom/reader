package com.mongodb.internal.connection;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoCredential;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/MongoCredentialWithCache.class */
public class MongoCredentialWithCache {
    private final MongoCredential credential;
    private final Cache cache;

    public static List<MongoCredentialWithCache> wrapCredentialList(List<MongoCredential> credentialList) {
        ArrayList<MongoCredentialWithCache> credentialListWithCache = new ArrayList<>();
        for (MongoCredential credential : credentialList) {
            credentialListWithCache.add(new MongoCredentialWithCache(credential));
        }
        return credentialListWithCache;
    }

    public MongoCredentialWithCache(MongoCredential credential) {
        this(credential, null);
    }

    public MongoCredentialWithCache(MongoCredential credential, Cache cache) {
        this.credential = credential;
        this.cache = cache != null ? cache : new Cache();
    }

    public MongoCredentialWithCache withMechanism(AuthenticationMechanism mechanism) {
        return new MongoCredentialWithCache(this.credential.withMechanism(mechanism), this.cache);
    }

    public AuthenticationMechanism getAuthenticationMechanism() {
        return this.credential.getAuthenticationMechanism();
    }

    public MongoCredential getCredential() {
        return this.credential;
    }

    public <T> T getFromCache(Object key, Class<T> clazz) {
        return clazz.cast(this.cache.get(key));
    }

    public void putInCache(Object key, Object value) {
        this.cache.set(key, value);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/MongoCredentialWithCache$Cache.class */
    static class Cache {
        private Object cacheKey;
        private Object cacheValue;

        Cache() {
        }

        synchronized Object get(Object key) {
            if (this.cacheKey != null && this.cacheKey.equals(key)) {
                return this.cacheValue;
            }
            return null;
        }

        synchronized void set(Object key, Object value) {
            this.cacheKey = key;
            this.cacheValue = value;
        }
    }
}
