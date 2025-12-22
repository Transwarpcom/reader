package org.springframework.boot.web.server;

import java.net.InetAddress;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/server/ConfigurableWebServerFactory.class */
public interface ConfigurableWebServerFactory extends WebServerFactory, ErrorPageRegistry {
    void setPort(int port);

    void setAddress(InetAddress address);

    void setErrorPages(Set<? extends ErrorPage> errorPages);

    void setSsl(Ssl ssl);

    void setSslStoreProvider(SslStoreProvider sslStoreProvider);

    void setHttp2(Http2 http2);

    void setCompression(Compression compression);

    void setServerHeader(String serverHeader);
}
