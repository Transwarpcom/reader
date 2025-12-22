package io.vertx.ext.auth;

import io.vertx.core.Vertx;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/PRNG.class */
public class PRNG implements VertxContextPRNG {
    private static final int DEFAULT_SEED_INTERVAL_MILLIS = 300000;
    private static final int DEFAULT_SEED_BITS = 64;
    private final SecureRandom random;
    private final long seedID;
    private final Vertx vertx;
    private volatile boolean dirty = false;

    public PRNG(Vertx vertx) {
        this.vertx = vertx;
        String algorithm = System.getProperty("io.vertx.ext.auth.prng.algorithm");
        int seedInterval = Integer.getInteger("io.vertx.ext.auth.prng.seed.interval", DEFAULT_SEED_INTERVAL_MILLIS).intValue();
        int seedBits = Integer.getInteger("io.vertx.ext.auth.prng.seed.bits", 64).intValue();
        if (algorithm != null) {
            try {
                this.random = SecureRandom.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } else {
            this.random = new SecureRandom();
        }
        this.random.nextBytes(new byte[1]);
        if (seedInterval > 0 && seedBits > 0) {
            AtomicBoolean seeding = new AtomicBoolean(false);
            this.seedID = vertx.setPeriodic(seedInterval, id -> {
                if (this.dirty && seeding.compareAndSet(false, true)) {
                    vertx.executeBlocking(future -> {
                        future.complete(this.random.generateSeed(seedBits / 8));
                    }, false, generateSeed -> {
                        seeding.set(false);
                        this.dirty = false;
                        this.random.setSeed((byte[]) generateSeed.result());
                    });
                }
            });
        } else {
            this.seedID = -1L;
        }
    }

    public void close() {
        if (this.seedID != -1) {
            this.vertx.cancelTimer(this.seedID);
        }
    }

    @Override // io.vertx.ext.auth.VertxContextPRNG
    public void nextBytes(byte[] bytes) {
        if (bytes != null) {
            this.random.nextBytes(bytes);
            this.dirty = true;
        }
    }

    @Override // io.vertx.ext.auth.VertxContextPRNG
    public int nextInt() {
        int rand = this.random.nextInt();
        this.dirty = true;
        return rand;
    }

    @Override // io.vertx.ext.auth.VertxContextPRNG
    public int nextInt(int bound) {
        int rand = this.random.nextInt(bound);
        this.dirty = true;
        return rand;
    }
}
