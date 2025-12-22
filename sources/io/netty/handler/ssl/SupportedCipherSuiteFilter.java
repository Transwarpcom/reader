package io.netty.handler.ssl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/SupportedCipherSuiteFilter.class */
public final class SupportedCipherSuiteFilter implements CipherSuiteFilter {
    public static final SupportedCipherSuiteFilter INSTANCE = new SupportedCipherSuiteFilter();

    private SupportedCipherSuiteFilter() {
    }

    @Override // io.netty.handler.ssl.CipherSuiteFilter
    public String[] filterCipherSuites(Iterable<String> ciphers, List<String> defaultCiphers, Set<String> supportedCiphers) {
        List<String> newCiphers;
        String c;
        if (defaultCiphers == null) {
            throw new NullPointerException("defaultCiphers");
        }
        if (supportedCiphers == null) {
            throw new NullPointerException("supportedCiphers");
        }
        if (ciphers == null) {
            newCiphers = new ArrayList<>(defaultCiphers.size());
            ciphers = defaultCiphers;
        } else {
            newCiphers = new ArrayList<>(supportedCiphers.size());
        }
        Iterator<String> it = ciphers.iterator();
        while (it.hasNext() && (c = it.next()) != null) {
            if (supportedCiphers.contains(c)) {
                newCiphers.add(c);
            }
        }
        return (String[]) newCiphers.toArray(new String[0]);
    }
}
