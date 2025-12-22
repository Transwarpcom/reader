package io.vertx.core.dns;

import io.vertx.core.impl.NoStackTraceThrowable;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/DnsException.class */
public final class DnsException extends NoStackTraceThrowable {
    private static final String ERROR_MESSAGE_PREFIX = "DNS query error occurred: ";
    private DnsResponseCode code;

    public DnsException(DnsResponseCode code) {
        super(ERROR_MESSAGE_PREFIX + code);
        Objects.requireNonNull(code, "code");
        this.code = code;
    }

    public DnsResponseCode code() {
        return this.code;
    }
}
