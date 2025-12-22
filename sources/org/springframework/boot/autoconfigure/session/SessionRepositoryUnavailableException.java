package org.springframework.boot.autoconfigure.session;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/session/SessionRepositoryUnavailableException.class */
public class SessionRepositoryUnavailableException extends RuntimeException {
    private final StoreType storeType;

    public SessionRepositoryUnavailableException(String message, StoreType storeType) {
        super(message);
        this.storeType = storeType;
    }

    public StoreType getStoreType() {
        return this.storeType;
    }
}
