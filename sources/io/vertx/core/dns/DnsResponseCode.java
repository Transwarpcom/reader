package io.vertx.core.dns;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/DnsResponseCode.class */
public enum DnsResponseCode {
    NOERROR(0, "no error"),
    FORMERROR(1, "format error"),
    SERVFAIL(2, "server failure"),
    NXDOMAIN(3, "name error"),
    NOTIMPL(4, "not implemented"),
    REFUSED(5, "operation refused"),
    YXDOMAIN(6, "domain name should not exist"),
    YXRRSET(7, "resource record set should not exist"),
    NXRRSET(8, "rrset does not exist"),
    NOTAUTH(9, "not authoritative for zone"),
    NOTZONE(10, "name not in zone"),
    BADVERS(11, "bad extension mechanism for version"),
    BADSIG(12, "bad signature"),
    BADKEY(13, "bad key"),
    BADTIME(14, "bad timestamp");

    private final int errorCode;
    private final String message;

    public static DnsResponseCode valueOf(int responseCode) {
        DnsResponseCode[] errors = values();
        for (DnsResponseCode e : errors) {
            if (e.errorCode == responseCode) {
                return e;
            }
        }
        return null;
    }

    DnsResponseCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int code() {
        return this.errorCode;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name() + ": type " + this.errorCode + ", " + this.message;
    }
}
