package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/Service.class */
public interface Service {

    @Beta
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/Service$State.class */
    public enum State {
        NEW { // from class: com.google.common.util.concurrent.Service.State.1
            @Override // com.google.common.util.concurrent.Service.State
            boolean isTerminal() {
                return false;
            }
        },
        STARTING { // from class: com.google.common.util.concurrent.Service.State.2
            @Override // com.google.common.util.concurrent.Service.State
            boolean isTerminal() {
                return false;
            }
        },
        RUNNING { // from class: com.google.common.util.concurrent.Service.State.3
            @Override // com.google.common.util.concurrent.Service.State
            boolean isTerminal() {
                return false;
            }
        },
        STOPPING { // from class: com.google.common.util.concurrent.Service.State.4
            @Override // com.google.common.util.concurrent.Service.State
            boolean isTerminal() {
                return false;
            }
        },
        TERMINATED { // from class: com.google.common.util.concurrent.Service.State.5
            @Override // com.google.common.util.concurrent.Service.State
            boolean isTerminal() {
                return true;
            }
        },
        FAILED { // from class: com.google.common.util.concurrent.Service.State.6
            @Override // com.google.common.util.concurrent.Service.State
            boolean isTerminal() {
                return true;
            }
        };

        abstract boolean isTerminal();
    }

    @CanIgnoreReturnValue
    Service startAsync();

    boolean isRunning();

    State state();

    @CanIgnoreReturnValue
    Service stopAsync();

    void awaitRunning();

    void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException;

    void awaitTerminated();

    void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException;

    Throwable failureCause();

    void addListener(Listener listener, Executor executor);

    default void awaitRunning(Duration timeout) throws TimeoutException {
        awaitRunning(Internal.saturatedToNanos(timeout), TimeUnit.NANOSECONDS);
    }

    default void awaitTerminated(Duration timeout) throws TimeoutException {
        awaitTerminated(Internal.saturatedToNanos(timeout), TimeUnit.NANOSECONDS);
    }

    @Beta
    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/Service$Listener.class */
    public static abstract class Listener {
        public void starting() {
        }

        public void running() {
        }

        public void stopping(State from) {
        }

        public void terminated(State from) {
        }

        public void failed(State from, Throwable failure) {
        }
    }
}
