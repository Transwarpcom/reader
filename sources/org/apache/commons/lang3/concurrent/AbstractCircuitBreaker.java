package org.apache.commons.lang3.concurrent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/AbstractCircuitBreaker.class */
public abstract class AbstractCircuitBreaker<T> implements CircuitBreaker<T> {
    public static final String PROPERTY_NAME = "open";
    protected final AtomicReference<State> state = new AtomicReference<>(State.CLOSED);
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/AbstractCircuitBreaker$State.class */
    protected enum State {
        CLOSED { // from class: org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State.1
            @Override // org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State
            public State oppositeState() {
                return OPEN;
            }
        },
        OPEN { // from class: org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State.2
            @Override // org.apache.commons.lang3.concurrent.AbstractCircuitBreaker.State
            public State oppositeState() {
                return CLOSED;
            }
        };

        public abstract State oppositeState();
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public abstract boolean checkState();

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public abstract boolean incrementAndCheckState(T t);

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public boolean isOpen() {
        return isOpen(this.state.get());
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public boolean isClosed() {
        return !isOpen();
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public void close() {
        changeState(State.CLOSED);
    }

    @Override // org.apache.commons.lang3.concurrent.CircuitBreaker
    public void open() {
        changeState(State.OPEN);
    }

    protected static boolean isOpen(State state) {
        return state == State.OPEN;
    }

    protected void changeState(State newState) {
        if (this.state.compareAndSet(newState.oppositeState(), newState)) {
            this.changeSupport.firePropertyChange("open", !isOpen(newState), isOpen(newState));
        }
    }

    public void addChangeListener(PropertyChangeListener listener) {
        this.changeSupport.addPropertyChangeListener(listener);
    }

    public void removeChangeListener(PropertyChangeListener listener) {
        this.changeSupport.removePropertyChangeListener(listener);
    }
}
