package io.vertx.core.net.impl;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/URIDecoder.class */
public final class URIDecoder {
    private URIDecoder() {
        throw new RuntimeException("Static Class");
    }

    public static String decodeURIComponent(String s) {
        return decodeURIComponent(s, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0174, code lost:
    
        return new java.lang.String(r0, 0, r13, java.nio.charset.StandardCharsets.UTF_8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String decodeURIComponent(java.lang.String r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.net.impl.URIDecoder.decodeURIComponent(java.lang.String, boolean):java.lang.String");
    }

    private static char decodeHexNibble(char c) {
        if ('0' <= c && c <= '9') {
            return (char) (c - '0');
        }
        if ('a' <= c && c <= 'f') {
            return (char) ((c - 'a') + 10);
        }
        if ('A' <= c && c <= 'F') {
            return (char) ((c - 'A') + 10);
        }
        return (char) 65535;
    }
}
