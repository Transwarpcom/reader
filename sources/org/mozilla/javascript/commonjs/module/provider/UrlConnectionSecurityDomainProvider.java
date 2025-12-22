package org.mozilla.javascript.commonjs.module.provider;

import java.net.URLConnection;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/commonjs/module/provider/UrlConnectionSecurityDomainProvider.class */
public interface UrlConnectionSecurityDomainProvider {
    Object getSecurityDomain(URLConnection uRLConnection);
}
