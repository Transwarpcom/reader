package org.springframework.boot.web.servlet.context;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import org.springframework.util.Assert;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.ServletContextAwareProcessor;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/servlet/context/WebApplicationContextServletContextAwareProcessor.class */
public class WebApplicationContextServletContextAwareProcessor extends ServletContextAwareProcessor {
    private final ConfigurableWebApplicationContext webApplicationContext;

    public WebApplicationContextServletContextAwareProcessor(ConfigurableWebApplicationContext webApplicationContext) {
        Assert.notNull(webApplicationContext, "WebApplicationContext must not be null");
        this.webApplicationContext = webApplicationContext;
    }

    protected ServletContext getServletContext() {
        ServletContext servletContext = this.webApplicationContext.getServletContext();
        return servletContext != null ? servletContext : super.getServletContext();
    }

    protected ServletConfig getServletConfig() {
        ServletConfig servletConfig = this.webApplicationContext.getServletConfig();
        return servletConfig != null ? servletConfig : super.getServletConfig();
    }
}
