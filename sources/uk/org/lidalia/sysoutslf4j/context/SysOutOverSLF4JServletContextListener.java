package uk.org.lidalia.sysoutslf4j.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/SysOutOverSLF4JServletContextListener.class */
public class SysOutOverSLF4JServletContextListener implements ServletContextListener {
    public final void contextInitialized(ServletContextEvent servletContextEvent) {
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SysOutOverSLF4J.stopSendingSystemOutAndErrToSLF4J();
    }
}
