package org.springframework.boot.web.embedded.jetty;

import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/jetty/JettyEmbeddedWebAppContext.class */
class JettyEmbeddedWebAppContext extends WebAppContext {
    JettyEmbeddedWebAppContext() {
    }

    protected ServletHandler newServletHandler() {
        return new JettyEmbeddedServletHandler();
    }

    public void deferredInitialize() throws Exception {
        ((JettyEmbeddedServletHandler) getServletHandler()).deferredInitialize();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/jetty/JettyEmbeddedWebAppContext$JettyEmbeddedServletHandler.class */
    private static class JettyEmbeddedServletHandler extends ServletHandler {
        private JettyEmbeddedServletHandler() {
        }

        public void initialize() throws Exception {
        }

        public void deferredInitialize() throws Exception {
            super.initialize();
        }
    }
}
