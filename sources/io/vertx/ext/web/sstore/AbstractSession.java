package io.vertx.ext.web.sstore;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PRNG;
import io.vertx.ext.web.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/sstore/AbstractSession.class */
public abstract class AbstractSession implements Session {
    private static final char[] HEX = "0123456789abcdef".toCharArray();
    private PRNG prng;
    private String id;
    private long timeout;
    private volatile Map<String, Object> data;
    private long lastAccessed;
    private int version;
    private boolean destroyed;
    private boolean renewed;
    private String oldId;
    private int crc;

    protected void setId(String id) {
        this.id = id;
    }

    protected void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    protected void setData(Map<String, Object> data) {
        if (data != null) {
            this.data = data;
            this.crc = checksum();
        }
    }

    protected void setData(JsonObject data) {
        if (data != null) {
            setData(data.getMap());
        }
    }

    protected void setLastAccessed(long lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    public AbstractSession() {
    }

    public AbstractSession(PRNG random) {
        this.prng = random;
    }

    public AbstractSession(PRNG random, long timeout, int length) {
        this.prng = random;
        this.id = generateId(this.prng, length);
        this.timeout = timeout;
        this.lastAccessed = System.currentTimeMillis();
    }

    public void setPRNG(PRNG prng) {
        this.prng = prng;
    }

    @Override // io.vertx.ext.web.Session
    public String id() {
        return this.id;
    }

    @Override // io.vertx.ext.web.Session
    public Session regenerateId() {
        if (this.oldId == null) {
            this.oldId = this.id;
        }
        this.id = generateId(this.prng, this.oldId.length() / 2);
        this.renewed = true;
        return this;
    }

    @Override // io.vertx.ext.web.Session
    public long timeout() {
        return this.timeout;
    }

    @Override // io.vertx.ext.web.Session
    public <T> T get(String str) {
        if (isEmpty()) {
            return null;
        }
        return (T) data().get(str);
    }

    @Override // io.vertx.ext.web.Session
    public Session put(String key, Object obj) {
        Map<String, Object> data = data();
        if (obj == null) {
            data.remove(key);
        } else {
            data.put(key, obj);
        }
        return this;
    }

    @Override // io.vertx.ext.web.Session
    public <T> T remove(String str) {
        if (isEmpty()) {
            return null;
        }
        return (T) data().remove(str);
    }

    @Override // io.vertx.ext.web.Session
    public Map<String, Object> data() {
        if (this.data == null) {
            synchronized (this) {
                if (this.data == null) {
                    this.data = new ConcurrentHashMap();
                    if (this.destroyed) {
                        regenerateId();
                        this.destroyed = false;
                    }
                }
            }
        }
        return this.data;
    }

    @Override // io.vertx.ext.web.Session
    public boolean isEmpty() {
        return this.data == null || this.data.size() == 0;
    }

    @Override // io.vertx.ext.web.Session
    public long lastAccessed() {
        return this.lastAccessed;
    }

    @Override // io.vertx.ext.web.Session
    public void setAccessed() {
        this.lastAccessed = System.currentTimeMillis();
    }

    @Override // io.vertx.ext.web.Session
    public void destroy() {
        synchronized (this) {
            this.destroyed = true;
            this.data = null;
        }
    }

    @Override // io.vertx.ext.web.Session
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override // io.vertx.ext.web.Session
    public boolean isRegenerated() {
        return this.renewed;
    }

    @Override // io.vertx.ext.web.Session
    public String oldId() {
        return this.oldId;
    }

    public int version() {
        return this.version;
    }

    public void incrementVersion() {
        int old = this.crc;
        this.crc = checksum();
        if (this.crc != old) {
            this.version++;
        }
    }

    private static String generateId(PRNG rng, int length) {
        byte[] bytes = new byte[length];
        rng.nextBytes(bytes);
        char[] hex = new char[length * 2];
        for (int j = 0; j < length; j++) {
            int v = bytes[j] & 255;
            hex[j * 2] = HEX[v >>> 4];
            hex[(j * 2) + 1] = HEX[v & 15];
        }
        return new String(hex);
    }

    protected int crc() {
        return this.crc;
    }

    protected int checksum() {
        if (isEmpty()) {
            return 0;
        }
        int result = 1;
        for (Map.Entry<String, Object> kv : this.data.entrySet()) {
            String key = kv.getKey();
            result = (31 * result) + key.hashCode();
            Object value = kv.getValue();
            if (value != null) {
                result = (31 * result) + value.hashCode();
            }
        }
        return result;
    }
}
