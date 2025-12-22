package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-4.1.42.Final.jar:io/netty/resolver/AddressResolverGroup.class */
public abstract class AddressResolverGroup<T extends SocketAddress> implements Closeable {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AddressResolverGroup.class);
    private final Map<EventExecutor, AddressResolver<T>> resolvers = new IdentityHashMap();

    protected abstract AddressResolver<T> newResolver(EventExecutor eventExecutor) throws Exception;

    protected AddressResolverGroup() {
    }

    public AddressResolver<T> getResolver(final EventExecutor executor) {
        AddressResolver<T> r;
        if (executor == null) {
            throw new NullPointerException("executor");
        }
        if (executor.isShuttingDown()) {
            throw new IllegalStateException("executor not accepting a task");
        }
        synchronized (this.resolvers) {
            r = this.resolvers.get(executor);
            if (r == null) {
                try {
                    final AddressResolver<T> newResolver = newResolver(executor);
                    this.resolvers.put(executor, newResolver);
                    executor.terminationFuture().addListener(new FutureListener<Object>() { // from class: io.netty.resolver.AddressResolverGroup.1
                        @Override // io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(Future<Object> future) throws Exception {
                            synchronized (AddressResolverGroup.this.resolvers) {
                                AddressResolverGroup.this.resolvers.remove(executor);
                            }
                            newResolver.close();
                        }
                    });
                    r = newResolver;
                } catch (Exception e) {
                    throw new IllegalStateException("failed to create a new resolver", e);
                }
            }
        }
        return r;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        AddressResolver<T>[] rArray;
        synchronized (this.resolvers) {
            rArray = (AddressResolver[]) this.resolvers.values().toArray(new AddressResolver[0]);
            this.resolvers.clear();
        }
        for (AddressResolver<T> r : rArray) {
            try {
                r.close();
            } catch (Throwable t) {
                logger.warn("Failed to close a resolver:", t);
            }
        }
    }
}
