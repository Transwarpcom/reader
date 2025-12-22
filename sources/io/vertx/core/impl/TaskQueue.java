package io.vertx.core.impl;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.LinkedList;
import java.util.concurrent.Executor;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/TaskQueue.class */
public class TaskQueue {
    static final Logger log = LoggerFactory.getLogger((Class<?>) TaskQueue.class);
    private Executor current;
    private final LinkedList<Task> tasks = new LinkedList<>();
    private final Runnable runner = this::run;

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/TaskQueue$Task.class */
    private static class Task {
        private final Runnable runnable;
        private final Executor exec;

        public Task(Runnable runnable, Executor exec) {
            this.runnable = runnable;
            this.exec = exec;
        }
    }

    private void run() {
        Task task;
        while (true) {
            synchronized (this.tasks) {
                task = this.tasks.poll();
                if (task != null) {
                    if (task.exec != this.current) {
                        this.tasks.addFirst(task);
                        task.exec.execute(this.runner);
                        this.current = task.exec;
                        return;
                    }
                } else {
                    this.current = null;
                    return;
                }
            }
            try {
                task.runnable.run();
            } catch (Throwable t) {
                log.error("Caught unexpected Throwable", t);
            }
        }
    }

    public void execute(Runnable task, Executor executor) {
        synchronized (this.tasks) {
            this.tasks.add(new Task(task, executor));
            if (this.current == null) {
                this.current = executor;
                executor.execute(this.runner);
            }
        }
    }
}
