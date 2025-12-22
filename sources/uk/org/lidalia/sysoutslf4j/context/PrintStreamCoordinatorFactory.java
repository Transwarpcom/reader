package uk.org.lidalia.sysoutslf4j.context;

import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.org.lidalia.sysoutslf4j.common.ExceptionUtils;
import uk.org.lidalia.sysoutslf4j.common.PrintStreamCoordinator;
import uk.org.lidalia.sysoutslf4j.common.ReflectionUtils;
import uk.org.lidalia.sysoutslf4j.system.PrintStreamCoordinatorImpl;

/* loaded from: reader.jar:BOOT-INF/lib/sysout-over-slf4j-1.0.2.jar:uk/org/lidalia/sysoutslf4j/context/PrintStreamCoordinatorFactory.class */
final class PrintStreamCoordinatorFactory {
    private static final String LINE_END = System.getProperty("line.separator");
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) SysOutOverSLF4J.class);

    static PrintStreamCoordinator createPrintStreamCoordinator() throws ClassNotFoundException {
        Class<?> candidateCoordinatorClass = getConfiguratorClassFromSLF4JPrintStreamClassLoader();
        if (candidateCoordinatorClass == null) {
            candidateCoordinatorClass = getConfiguratorClassFromSystemClassLoader();
        }
        if (candidateCoordinatorClass == null) {
            candidateCoordinatorClass = getConfiguratorClassFromCurrentClassLoader();
        }
        checkCoordinator(candidateCoordinatorClass);
        return makeCoordinator(candidateCoordinatorClass);
    }

    private static PrintStreamCoordinator makeCoordinator(final Class<?> coordinatorClass) {
        return (PrintStreamCoordinator) ExceptionUtils.doUnchecked(new Callable<PrintStreamCoordinator>() { // from class: uk.org.lidalia.sysoutslf4j.context.PrintStreamCoordinatorFactory.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public PrintStreamCoordinator call() throws IllegalAccessException, InstantiationException {
                Object coordinator = coordinatorClass.newInstance();
                return (PrintStreamCoordinator) ReflectionUtils.wrap(coordinator, PrintStreamCoordinator.class);
            }
        });
    }

    private static Class<?> getConfiguratorClassFromSLF4JPrintStreamClassLoader() {
        Class<?> configuratorClass;
        if (SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams()) {
            ClassLoader classLoader = System.out.getClass().getClassLoader();
            configuratorClass = ClassLoaderUtils.loadClass(classLoader, PrintStreamCoordinatorImpl.class);
        } else {
            configuratorClass = null;
        }
        return configuratorClass;
    }

    private static Class<?> getConfiguratorClassFromSystemClassLoader() throws ClassNotFoundException {
        Class<?> configuratorClass = null;
        try {
            configuratorClass = ClassLoader.getSystemClassLoader().loadClass(PrintStreamCoordinatorImpl.class.getName());
        } catch (Exception e) {
            LOG.debug("failed to load [" + PrintStreamCoordinatorImpl.class + "] from system class loader due to " + e);
        }
        return configuratorClass;
    }

    private static void checkCoordinator(Class<?> candidateCoordinatorClass) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        boolean usingSystemClassLoader = ClassLoader.getSystemClassLoader() == contextClassLoader;
        if (!usingSystemClassLoader && candidateCoordinatorClass.getClassLoader() == contextClassLoader) {
            reportFailureToAvoidClassLoaderLeak();
        }
    }

    private static void reportFailureToAvoidClassLoaderLeak() {
        LOG.warn("Unfortunately it is not possible to set up Sysout over SLF4J on this system without introducing a class loader memory leak." + LINE_END + "If you never need to discard the current class loader [" + Thread.currentThread().getContextClassLoader() + "] this will not be a problem and you can suppress this warning." + LINE_END + "In the worst case discarding the current class loader may cause all subsequent attempts to print to System.out or err to throw an exception.");
    }

    private static Class<PrintStreamCoordinatorImpl> getConfiguratorClassFromCurrentClassLoader() {
        return PrintStreamCoordinatorImpl.class;
    }

    private PrintStreamCoordinatorFactory() {
        throw new UnsupportedOperationException("Not instantiable");
    }
}
