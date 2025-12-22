package io.vertx.core;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.json.JsonObject;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/Context.class */
public interface Context {
    void runOnContext(Handler<Void> handler);

    <T> void executeBlocking(Handler<Promise<T>> handler, boolean z, Handler<AsyncResult<T>> handler2);

    <T> void executeBlocking(Handler<Promise<T>> handler, Handler<AsyncResult<T>> handler2);

    String deploymentID();

    JsonObject config();

    List<String> processArgs();

    boolean isEventLoopContext();

    boolean isWorkerContext();

    boolean isMultiThreadedWorkerContext();

    <T> T get(String str);

    void put(String str, Object obj);

    boolean remove(String str);

    Vertx owner();

    int getInstanceCount();

    @Fluent
    Context exceptionHandler(Handler<Throwable> handler);

    @GenIgnore
    Handler<Throwable> exceptionHandler();

    @GenIgnore({"permitted-type"})
    void addCloseHook(Closeable closeable);

    @GenIgnore({"permitted-type"})
    boolean removeCloseHook(Closeable closeable);

    static boolean isOnWorkerThread() {
        return ContextInternal.isOnWorkerThread();
    }

    static boolean isOnEventLoopThread() {
        return ContextInternal.isOnEventLoopThread();
    }

    static boolean isOnVertxThread() {
        return ContextInternal.isOnVertxThread();
    }
}
