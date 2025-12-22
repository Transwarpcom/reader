package cn.hutool.core.thread;

import java.util.concurrent.Semaphore;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/thread/SemaphoreRunnable.class */
public class SemaphoreRunnable implements Runnable {
    private final Runnable runnable;
    private final Semaphore semaphore;

    public SemaphoreRunnable(Runnable runnable, Semaphore semaphore) {
        this.runnable = runnable;
        this.semaphore = semaphore;
    }

    public Semaphore getSemaphore() {
        return this.semaphore;
    }

    @Override // java.lang.Runnable
    public void run() throws InterruptedException {
        if (null != this.semaphore) {
            try {
                this.semaphore.acquire();
                try {
                    this.runnable.run();
                    this.semaphore.release();
                } catch (Throwable th) {
                    this.semaphore.release();
                    throw th;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
