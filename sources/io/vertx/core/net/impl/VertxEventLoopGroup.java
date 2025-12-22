package io.vertx.core.net.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.AbstractEventExecutorGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/VertxEventLoopGroup.class */
public final class VertxEventLoopGroup extends AbstractEventExecutorGroup implements EventLoopGroup {
    private int pos;
    private final List<EventLoopHolder> workers = new ArrayList();
    private final Set<EventExecutor> children = new Set<EventExecutor>() { // from class: io.vertx.core.net.impl.VertxEventLoopGroup.1
        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public Iterator<EventExecutor> iterator() {
            return new EventLoopIterator(VertxEventLoopGroup.this.workers.iterator());
        }

        @Override // java.util.Set, java.util.Collection
        public int size() {
            return VertxEventLoopGroup.this.workers.size();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean isEmpty() {
            return VertxEventLoopGroup.this.workers.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean contains(Object o) {
            return VertxEventLoopGroup.this.workers.contains(o);
        }

        @Override // java.util.Set, java.util.Collection
        public Object[] toArray() {
            return VertxEventLoopGroup.this.workers.toArray();
        }

        @Override // java.util.Set, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) VertxEventLoopGroup.this.workers.toArray(tArr);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean add(EventExecutor eventExecutor) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean containsAll(Collection<?> c) {
            return VertxEventLoopGroup.this.workers.containsAll(c);
        }

        @Override // java.util.Set, java.util.Collection
        public boolean addAll(Collection<? extends EventExecutor> c) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException();
        }
    };

    @Override // io.netty.util.concurrent.EventExecutorGroup, io.netty.channel.EventLoopGroup
    public synchronized EventLoop next() {
        if (this.workers.isEmpty()) {
            throw new IllegalStateException();
        }
        EventLoop worker = this.workers.get(this.pos).worker;
        this.pos++;
        checkPos();
        return worker;
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup, java.lang.Iterable
    public Iterator<EventExecutor> iterator() {
        return this.children.iterator();
    }

    @Override // io.netty.channel.EventLoopGroup
    public ChannelFuture register(Channel channel) {
        return next().register(channel);
    }

    @Override // io.netty.channel.EventLoopGroup
    public ChannelFuture register(Channel channel, ChannelPromise promise) {
        return next().register(channel, promise);
    }

    @Override // io.netty.channel.EventLoopGroup
    public ChannelFuture register(ChannelPromise promise) {
        return next().register(promise);
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public synchronized boolean awaitTermination(long timeout, TimeUnit unit) {
        return false;
    }

    public synchronized void addWorker(EventLoop worker) {
        EventLoopHolder holder = findHolder(worker);
        if (holder == null) {
            this.workers.add(new EventLoopHolder(worker));
        } else {
            holder.count++;
        }
    }

    @Override // io.netty.util.concurrent.AbstractEventExecutorGroup, io.netty.util.concurrent.EventExecutorGroup, java.util.concurrent.ExecutorService
    public synchronized void shutdown() {
        throw new UnsupportedOperationException("Should never be called");
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public boolean isShuttingDown() {
        return false;
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public Future<?> shutdownGracefully(long quietPeriod, long timeout, TimeUnit unit) {
        throw new UnsupportedOperationException("Should never be called");
    }

    @Override // io.netty.util.concurrent.EventExecutorGroup
    public Future<?> terminationFuture() {
        throw new UnsupportedOperationException("Should never be called");
    }

    private EventLoopHolder findHolder(EventLoop worker) {
        EventLoopHolder wh = new EventLoopHolder(worker);
        for (EventLoopHolder holder : this.workers) {
            if (holder.equals(wh)) {
                return holder;
            }
        }
        return null;
    }

    public synchronized void removeWorker(EventLoop worker) {
        EventLoopHolder holder = findHolder(worker);
        if (holder != null) {
            holder.count--;
            if (holder.count == 0) {
                this.workers.remove(holder);
            }
            checkPos();
            return;
        }
        throw new IllegalStateException("Can't find worker to remove");
    }

    public synchronized int workerCount() {
        return this.workers.size();
    }

    private void checkPos() {
        if (this.pos == this.workers.size()) {
            this.pos = 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/VertxEventLoopGroup$EventLoopHolder.class */
    private static class EventLoopHolder {
        int count = 1;
        final EventLoop worker;

        EventLoopHolder(EventLoop worker) {
            this.worker = worker;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EventLoopHolder that = (EventLoopHolder) o;
            return this.worker != null ? this.worker.equals(that.worker) : that.worker == null;
        }

        public int hashCode() {
            if (this.worker != null) {
                return this.worker.hashCode();
            }
            return 0;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/VertxEventLoopGroup$EventLoopIterator.class */
    private static final class EventLoopIterator implements Iterator<EventExecutor> {
        private final Iterator<EventLoopHolder> holderIt;

        public EventLoopIterator(Iterator<EventLoopHolder> holderIt) {
            this.holderIt = holderIt;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.holderIt.hasNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public EventExecutor next() {
            return this.holderIt.next().worker;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("read-only");
        }
    }
}
