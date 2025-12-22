package io.vertx.core.http.impl.pool;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.LongSupplier;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/pool/Pool.class */
public class Pool<C> {
    private final ContextInternal context;
    private final ConnectionProvider<C> connector;
    private final Consumer<C> connectionAdded;
    private final Consumer<C> connectionRemoved;
    private final LongSupplier clock;
    private final int queueMaxSize;
    private final Deque<Waiter<C>> waitersQueue = new ArrayDeque();
    private final Deque<Pool<C>.Holder> available = new ArrayDeque();
    private final boolean fifo;
    private long capacity;
    private long connecting;
    private final long initialWeight;
    private final long maxWeight;
    private long weight;
    private boolean checkInProgress;
    private boolean closed;
    private final Handler<Void> poolClosed;
    static final /* synthetic */ boolean $assertionsDisabled;

    /*  JADX ERROR: Failed to decode insn: 0x0031: MOVE_MULTI
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[6]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:52)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:137)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:50)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:46)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:158)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:460)
        	at jadx.core.ProcessClass.process(ProcessClass.java:69)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:403)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:391)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:341)
        */
    private java.lang.Runnable nextTask() {
        /*
            Method dump skipped, instructions count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.http.impl.pool.Pool.nextTask():java.lang.Runnable");
    }

    static {
        $assertionsDisabled = !Pool.class.desiredAssertionStatus();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/pool/Pool$Holder.class */
    public class Holder implements ConnectionListener<C> {
        boolean removed;
        C connection;
        long concurrency;
        long capacity;
        long weight;
        long expirationTimestamp;

        public Holder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void init(long concurrency, C conn, long weight) {
            this.concurrency = concurrency;
            this.connection = conn;
            this.weight = weight;
            this.capacity = concurrency;
            this.expirationTimestamp = -1L;
        }

        @Override // io.vertx.core.http.impl.pool.ConnectionListener
        public void onConcurrencyChange(long concurrency) {
            Pool.this.setConcurrency(this, concurrency);
        }

        @Override // io.vertx.core.http.impl.pool.ConnectionListener
        public void onRecycle(long expirationTimestamp) {
            Pool.this.recycle(this, expirationTimestamp);
        }

        @Override // io.vertx.core.http.impl.pool.ConnectionListener
        public void onEvict() {
            Pool.this.evicted(this);
        }

        void connect() {
            Pool.this.connector.connect(this, Pool.this.context, ar -> {
                if (ar.succeeded()) {
                    Pool.this.connectSucceeded(this, (ConnectResult) ar.result());
                } else {
                    Pool.this.connectFailed(this, ar.cause());
                }
            });
        }

        public String toString() {
            return "Holder[removed=" + this.removed + ",capacity=" + this.capacity + ",concurrency=" + this.concurrency + ",expirationTimestamp=" + this.expirationTimestamp + "]";
        }
    }

    public Pool(Context context, ConnectionProvider<C> connector, LongSupplier clock, int queueMaxSize, long initialWeight, long maxWeight, Handler<Void> poolClosed, Consumer<C> connectionAdded, Consumer<C> connectionRemoved, boolean fifo) {
        this.clock = clock;
        this.context = (ContextInternal) context;
        this.maxWeight = maxWeight;
        this.initialWeight = initialWeight;
        this.connector = connector;
        this.queueMaxSize = queueMaxSize;
        this.poolClosed = poolClosed;
        this.connectionAdded = connectionAdded;
        this.connectionRemoved = connectionRemoved;
        this.fifo = fifo;
    }

    public synchronized int waitersInQueue() {
        return this.waitersQueue.size();
    }

    public synchronized long weight() {
        return this.weight;
    }

    public synchronized long capacity() {
        return this.capacity;
    }

    public synchronized boolean getConnection(Handler<AsyncResult<C>> handler) {
        if (this.closed) {
            return false;
        }
        Waiter<C> waiter = new Waiter<>(handler);
        this.waitersQueue.add(waiter);
        checkProgress();
        return true;
    }

    public synchronized void closeIdle() {
        checkProgress();
    }

    private void checkProgress() {
        if (this.checkInProgress) {
            return;
        }
        if (canProgress() || canClose()) {
            this.checkInProgress = true;
            this.context.nettyEventLoop().execute(this::checkPendingTasks);
        }
    }

    private boolean canProgress() {
        return this.waitersQueue.size() > 0 ? canAcquireConnection() || needToCreateConnection() || canEvictWaiter() : this.capacity > 0;
    }

    private void checkPendingTasks() {
        Runnable task;
        while (true) {
            synchronized (this) {
                task = nextTask();
                if (task == null) {
                    this.checkInProgress = false;
                    checkClose();
                    return;
                }
            }
            task.run();
        }
    }

    private boolean canAcquireConnection() {
        return this.capacity > 0;
    }

    private boolean needToCreateConnection() {
        return this.weight < this.maxWeight && ((long) this.waitersQueue.size()) - this.connecting > 0;
    }

    private boolean canEvictWaiter() {
        return this.queueMaxSize >= 0 && ((long) this.waitersQueue.size()) - this.connecting > ((long) this.queueMaxSize);
    }

    private boolean canClose() {
        return this.weight == 0 && this.waitersQueue.isEmpty();
    }

    private void checkClose() {
        if (canClose()) {
            this.closed = true;
            this.poolClosed.handle(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectSucceeded(Pool<C>.Holder holder, ConnectResult<C> result) {
        List<Waiter<C>> waiters;
        synchronized (this) {
            this.connecting--;
            this.weight += this.initialWeight - result.weight();
            holder.init(result.concurrency(), result.connection(), result.weight());
            waiters = new ArrayList<>();
            while (holder.capacity > 0 && this.waitersQueue.size() > 0) {
                waiters.add(this.waitersQueue.poll());
                holder.capacity--;
            }
            if (holder.capacity > 0) {
                this.available.add(holder);
                this.capacity += holder.capacity;
            }
            checkProgress();
        }
        this.connectionAdded.accept(holder.connection);
        for (Waiter<C> waiter : waiters) {
            waiter.handler.handle(Future.succeededFuture(holder.connection));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectFailed(Pool<C>.Holder holder, Throwable cause) {
        Waiter<C> waiter;
        synchronized (this) {
            this.connecting--;
            waiter = this.waitersQueue.poll();
            this.weight -= this.initialWeight;
            holder.removed = true;
            checkProgress();
        }
        if (waiter != null) {
            waiter.handler.handle(Future.failedFuture(cause));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setConcurrency(Pool<C>.Holder holder, long concurrency) {
        if (concurrency < 0) {
            throw new IllegalArgumentException("Cannot set a negative concurrency value");
        }
        if (holder.removed) {
            if (!$assertionsDisabled) {
                throw new AssertionError("Cannot recycle removed holder");
            }
            return;
        }
        if (holder.concurrency >= concurrency) {
            if (holder.concurrency > concurrency) {
                throw new UnsupportedOperationException("Not yet implemented");
            }
            return;
        }
        long diff = concurrency - holder.concurrency;
        if (holder.capacity == 0) {
            this.available.add(holder);
        }
        this.capacity += diff;
        holder.capacity += diff;
        holder.concurrency = concurrency;
        checkProgress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recycle(Pool<C>.Holder holder, long timestamp) {
        C toClose;
        if (timestamp < 0) {
            throw new IllegalArgumentException("Invalid timestamp");
        }
        if (holder.removed) {
            return;
        }
        synchronized (this) {
            if (recycleConnection(holder, timestamp)) {
                toClose = holder.connection;
            } else {
                toClose = null;
            }
        }
        if (toClose != null) {
            this.connector.close(holder.connection);
        } else {
            synchronized (this) {
                checkProgress();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void evicted(Pool<C>.Holder holder) {
        if (holder.removed) {
            return;
        }
        evictConnection(holder);
        checkProgress();
    }

    private void evictConnection(Pool<C>.Holder holder) {
        holder.removed = true;
        this.connectionRemoved.accept(holder.connection);
        if (holder.capacity > 0) {
            this.capacity -= holder.capacity;
            this.available.remove(holder);
            holder.capacity = 0L;
        }
        this.weight -= holder.weight;
    }

    private boolean recycleConnection(Pool<C>.Holder holder, long timestamp) {
        long newCapacity = holder.capacity + 1;
        if (newCapacity > holder.concurrency) {
            throw new AssertionError("Attempt to recycle a connection more than permitted");
        }
        if (timestamp == 0 && newCapacity == holder.concurrency && this.capacity >= this.waitersQueue.size()) {
            if (holder.capacity > 0) {
                this.capacity -= holder.capacity;
                this.available.remove(holder);
            }
            holder.expirationTimestamp = -1L;
            holder.capacity = 0L;
            return true;
        }
        this.capacity++;
        if (holder.capacity == 0) {
            if (this.fifo) {
                this.available.addLast(holder);
            } else {
                this.available.addFirst(holder);
            }
        }
        holder.expirationTimestamp = timestamp;
        holder.capacity++;
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        synchronized (this) {
            sb.append("Available:").append(File.separator);
            this.available.forEach(holder -> {
                sb.append(holder).append(File.separator);
            });
            sb.append("Waiters").append(File.separator);
            this.waitersQueue.forEach(w -> {
                sb.append(w.handler).append(File.separator);
            });
            sb.append("InitialWeight:").append(this.initialWeight).append(File.separator);
            sb.append("MaxWeight:").append(this.maxWeight).append(File.separator);
            sb.append("Weight:").append(this.weight).append(File.separator);
            sb.append("Capacity:").append(this.capacity).append(File.separator);
            sb.append("Connecting:").append(this.connecting).append(File.separator);
            sb.append("CheckInProgress:").append(this.checkInProgress).append(File.separator);
            sb.append("Closed:").append(this.closed).append(File.separator);
        }
        return sb.toString();
    }
}
