package uk.org.lidalia.sysoutslf4j.context;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/LoggingSystemRegister.class */
class LoggingSystemRegister {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) SysOutOverSLF4J.class);
    private final Set<String> loggingSystemNameFragments = new CopyOnWriteArraySet();

    void registerLoggingSystem(String packageName) {
        this.loggingSystemNameFragments.add(packageName);
        LOG.info("Package {} registered; all classes within it or subpackages of it will be allowed to print to System.out and System.err", packageName);
    }

    void unregisterLoggingSystem(String packageName) {
        if (this.loggingSystemNameFragments.remove(packageName)) {
            LOG.info("Package {} unregistered; all classes within it or subpackages of it will have System.out and System.err redirected to SLF4J", packageName);
        }
    }

    boolean isInLoggingSystem(String className) {
        boolean isInLoggingSystem = false;
        Iterator<String> it = this.loggingSystemNameFragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String packageName = it.next();
            if (className.startsWith(packageName)) {
                isInLoggingSystem = true;
                break;
            }
        }
        return isInLoggingSystem;
    }

    LoggingSystemRegister() {
    }
}
