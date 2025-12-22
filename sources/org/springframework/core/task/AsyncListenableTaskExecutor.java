package org.springframework.core.task;

import java.util.concurrent.Callable;
import org.springframework.util.concurrent.ListenableFuture;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/task/AsyncListenableTaskExecutor.class */
public interface AsyncListenableTaskExecutor extends AsyncTaskExecutor {
    ListenableFuture<?> submitListenable(Runnable runnable);

    <T> ListenableFuture<T> submitListenable(Callable<T> callable);
}
