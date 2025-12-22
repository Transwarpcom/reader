package io.netty.util.internal.shaded.org.jctools.queues;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/MessagePassingQueue.class */
public interface MessagePassingQueue<T> {
    public static final int UNBOUNDED_CAPACITY = -1;

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/MessagePassingQueue$Consumer.class */
    public interface Consumer<T> {
        void accept(T t);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/MessagePassingQueue$ExitCondition.class */
    public interface ExitCondition {
        boolean keepRunning();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/MessagePassingQueue$Supplier.class */
    public interface Supplier<T> {
        T get();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/MessagePassingQueue$WaitStrategy.class */
    public interface WaitStrategy {
        int idle(int i);
    }

    boolean offer(T t);

    T poll();

    T peek();

    int size();

    void clear();

    boolean isEmpty();

    int capacity();

    boolean relaxedOffer(T t);

    T relaxedPoll();

    T relaxedPeek();

    int drain(Consumer<T> consumer);

    int fill(Supplier<T> supplier);

    int drain(Consumer<T> consumer, int i);

    int fill(Supplier<T> supplier, int i);

    void drain(Consumer<T> consumer, WaitStrategy waitStrategy, ExitCondition exitCondition);

    void fill(Supplier<T> supplier, WaitStrategy waitStrategy, ExitCondition exitCondition);
}
