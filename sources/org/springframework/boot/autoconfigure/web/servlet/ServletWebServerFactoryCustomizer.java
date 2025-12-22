package org.springframework.boot.autoconfigure.web.servlet;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.core.Ordered;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/web/servlet/ServletWebServerFactoryCustomizer.class */
public class ServletWebServerFactoryCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, Ordered {
    private final ServerProperties serverProperties;

    public ServletWebServerFactoryCustomizer(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override // org.springframework.core.Ordered
    public int getOrder() {
        return 0;
    }

    @Override // org.springframework.boot.web.server.WebServerFactoryCustomizer
    public void customize(ConfigurableServletWebServerFactory factory) {
        PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
        ServerProperties serverProperties = this.serverProperties;
        serverProperties.getClass();
        PropertyMapper.Source sourceFrom = map.from(serverProperties::getPort);
        factory.getClass();
        sourceFrom.to((v1) -> {
            r1.setPort(v1);
        });
        ServerProperties serverProperties2 = this.serverProperties;
        serverProperties2.getClass();
        PropertyMapper.Source sourceFrom2 = map.from(serverProperties2::getAddress);
        factory.getClass();
        sourceFrom2.to(factory::setAddress);
        ServerProperties.Servlet servlet = this.serverProperties.getServlet();
        servlet.getClass();
        PropertyMapper.Source sourceFrom3 = map.from(servlet::getContextPath);
        factory.getClass();
        sourceFrom3.to(factory::setContextPath);
        ServerProperties.Servlet servlet2 = this.serverProperties.getServlet();
        servlet2.getClass();
        PropertyMapper.Source sourceFrom4 = map.from(servlet2::getApplicationDisplayName);
        factory.getClass();
        sourceFrom4.to(factory::setDisplayName);
        ServerProperties.Servlet servlet3 = this.serverProperties.getServlet();
        servlet3.getClass();
        PropertyMapper.Source sourceFrom5 = map.from(servlet3::getSession);
        factory.getClass();
        sourceFrom5.to(factory::setSession);
        ServerProperties serverProperties3 = this.serverProperties;
        serverProperties3.getClass();
        PropertyMapper.Source sourceFrom6 = map.from(serverProperties3::getSsl);
        factory.getClass();
        sourceFrom6.to(factory::setSsl);
        ServerProperties.Servlet servlet4 = this.serverProperties.getServlet();
        servlet4.getClass();
        PropertyMapper.Source sourceFrom7 = map.from(servlet4::getJsp);
        factory.getClass();
        sourceFrom7.to(factory::setJsp);
        ServerProperties serverProperties4 = this.serverProperties;
        serverProperties4.getClass();
        PropertyMapper.Source sourceFrom8 = map.from(serverProperties4::getCompression);
        factory.getClass();
        sourceFrom8.to(factory::setCompression);
        ServerProperties serverProperties5 = this.serverProperties;
        serverProperties5.getClass();
        PropertyMapper.Source sourceFrom9 = map.from(serverProperties5::getHttp2);
        factory.getClass();
        sourceFrom9.to(factory::setHttp2);
        ServerProperties serverProperties6 = this.serverProperties;
        serverProperties6.getClass();
        PropertyMapper.Source sourceFrom10 = map.from(serverProperties6::getServerHeader);
        factory.getClass();
        sourceFrom10.to(factory::setServerHeader);
        ServerProperties.Servlet servlet5 = this.serverProperties.getServlet();
        servlet5.getClass();
        PropertyMapper.Source sourceFrom11 = map.from(servlet5::getContextParameters);
        factory.getClass();
        sourceFrom11.to(factory::setInitParameters);
    }
}
