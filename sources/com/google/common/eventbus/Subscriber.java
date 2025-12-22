package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/eventbus/Subscriber.class */
class Subscriber {

    @Weak
    private EventBus bus;

    @VisibleForTesting
    final Object target;
    private final Method method;
    private final Executor executor;

    static Subscriber create(EventBus bus, Object listener, Method method) {
        return isDeclaredThreadSafe(method) ? new Subscriber(bus, listener, method) : new SynchronizedSubscriber(bus, listener, method);
    }

    private Subscriber(EventBus bus, Object target, Method method) {
        this.bus = bus;
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        method.setAccessible(true);
        this.executor = bus.executor();
    }

    final void dispatchEvent(final Object event) {
        this.executor.execute(new Runnable() { // from class: com.google.common.eventbus.Subscriber.1
            @Override // java.lang.Runnable
            public void run() throws IllegalAccessException, IllegalArgumentException {
                try {
                    Subscriber.this.invokeSubscriberMethod(event);
                } catch (InvocationTargetException e) {
                    Subscriber.this.bus.handleSubscriberException(e.getCause(), Subscriber.this.context(event));
                }
            }
        });
    }

    @VisibleForTesting
    void invokeSubscriberMethod(Object event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            this.method.invoke(this.target, Preconditions.checkNotNull(event));
        } catch (IllegalAccessException e) {
            throw new Error("Method became inaccessible: " + event, e);
        } catch (IllegalArgumentException e2) {
            throw new Error("Method rejected target/argument: " + event, e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SubscriberExceptionContext context(Object event) {
        return new SubscriberExceptionContext(this.bus, event, this.target, this.method);
    }

    public final int hashCode() {
        return ((31 + this.method.hashCode()) * 31) + System.identityHashCode(this.target);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Subscriber) {
            Subscriber that = (Subscriber) obj;
            return this.target == that.target && this.method.equals(that.method);
        }
        return false;
    }

    private static boolean isDeclaredThreadSafe(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    @VisibleForTesting
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/eventbus/Subscriber$SynchronizedSubscriber.class */
    static final class SynchronizedSubscriber extends Subscriber {
        private SynchronizedSubscriber(EventBus bus, Object target, Method method) {
            super(bus, target, method);
        }

        @Override // com.google.common.eventbus.Subscriber
        void invokeSubscriberMethod(Object event) throws InvocationTargetException {
            synchronized (this) {
                super.invokeSubscriberMethod(event);
            }
        }
    }
}
