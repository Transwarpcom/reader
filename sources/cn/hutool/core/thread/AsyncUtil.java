package cn.hutool.core.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/thread/AsyncUtil.class */
public class AsyncUtil {
    public static void waitAll(CompletableFuture<?>... tasks) throws ExecutionException, InterruptedException {
        try {
            CompletableFuture.allOf(tasks).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ThreadException(e);
        }
    }

    public static <T> T waitAny(CompletableFuture<?>... completableFutureArr) {
        try {
            return (T) CompletableFuture.anyOf(completableFutureArr).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ThreadException(e);
        }
    }

    public static <T> T get(CompletableFuture<T> task) {
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ThreadException(e);
        }
    }
}
