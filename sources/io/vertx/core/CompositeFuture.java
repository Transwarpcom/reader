package io.vertx.core;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.impl.CompositeFutureImpl;
import java.util.ArrayList;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/CompositeFuture.class */
public interface CompositeFuture extends Future<CompositeFuture> {
    @Override // io.vertx.core.Future
    /* renamed from: setHandler, reason: merged with bridge method [inline-methods] */
    Future<CompositeFuture> setHandler2(Handler<AsyncResult<CompositeFuture>> handler);

    @Override // io.vertx.core.Future
    void complete();

    @Override // io.vertx.core.Future
    boolean tryComplete();

    Throwable cause(int i);

    boolean succeeded(int i);

    boolean failed(int i);

    boolean isComplete(int i);

    <T> T resultAt(int i);

    int size();

    @Override // io.vertx.core.Future
    /* renamed from: onFailure, reason: avoid collision after fix types in other method */
    /* bridge */ /* synthetic */ default Future<CompositeFuture> onFailure2(Handler handler) {
        return onFailure((Handler<Throwable>) handler);
    }

    static <T1, T2> CompositeFuture all(Future<T1> f1, Future<T2> f2) {
        return CompositeFutureImpl.all((Future<?>[]) new Future[]{f1, f2});
    }

    static <T1, T2, T3> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3) {
        return CompositeFutureImpl.all((Future<?>[]) new Future[]{f1, f2, f3});
    }

    static <T1, T2, T3, T4> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4) {
        return CompositeFutureImpl.all((Future<?>[]) new Future[]{f1, f2, f3, f4});
    }

    static <T1, T2, T3, T4, T5> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5) {
        return CompositeFutureImpl.all((Future<?>[]) new Future[]{f1, f2, f3, f4, f5});
    }

    static <T1, T2, T3, T4, T5, T6> CompositeFuture all(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5, Future<T6> f6) {
        return CompositeFutureImpl.all((Future<?>[]) new Future[]{f1, f2, f3, f4, f5, f6});
    }

    static CompositeFuture all(List<Future> futures) {
        return CompositeFutureImpl.all((Future<?>[]) futures.toArray(new Future[futures.size()]));
    }

    static <T1, T2> CompositeFuture any(Future<T1> f1, Future<T2> f2) {
        return CompositeFutureImpl.any((Future<?>[]) new Future[]{f1, f2});
    }

    static <T1, T2, T3> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3) {
        return CompositeFutureImpl.any((Future<?>[]) new Future[]{f1, f2, f3});
    }

    static <T1, T2, T3, T4> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4) {
        return CompositeFutureImpl.any((Future<?>[]) new Future[]{f1, f2, f3, f4});
    }

    static <T1, T2, T3, T4, T5> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5) {
        return CompositeFutureImpl.any((Future<?>[]) new Future[]{f1, f2, f3, f4, f5});
    }

    static <T1, T2, T3, T4, T5, T6> CompositeFuture any(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5, Future<T6> f6) {
        return CompositeFutureImpl.any((Future<?>[]) new Future[]{f1, f2, f3, f4, f5, f6});
    }

    static CompositeFuture any(List<Future> futures) {
        return CompositeFutureImpl.any((Future<?>[]) futures.toArray(new Future[futures.size()]));
    }

    static <T1, T2> CompositeFuture join(Future<T1> f1, Future<T2> f2) {
        return CompositeFutureImpl.join((Future<?>[]) new Future[]{f1, f2});
    }

    static <T1, T2, T3> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3) {
        return CompositeFutureImpl.join((Future<?>[]) new Future[]{f1, f2, f3});
    }

    static <T1, T2, T3, T4> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4) {
        return CompositeFutureImpl.join((Future<?>[]) new Future[]{f1, f2, f3, f4});
    }

    static <T1, T2, T3, T4, T5> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5) {
        return CompositeFutureImpl.join((Future<?>[]) new Future[]{f1, f2, f3, f4, f5});
    }

    static <T1, T2, T3, T4, T5, T6> CompositeFuture join(Future<T1> f1, Future<T2> f2, Future<T3> f3, Future<T4> f4, Future<T5> f5, Future<T6> f6) {
        return CompositeFutureImpl.join((Future<?>[]) new Future[]{f1, f2, f3, f4, f5, f6});
    }

    static CompositeFuture join(List<Future> futures) {
        return CompositeFutureImpl.join((Future<?>[]) futures.toArray(new Future[futures.size()]));
    }

    @Override // io.vertx.core.Future
    /* renamed from: onComplete, reason: merged with bridge method [inline-methods] */
    default Future<CompositeFuture> onComplete2(Handler<AsyncResult<CompositeFuture>> handler) {
        return setHandler2(handler);
    }

    @Override // io.vertx.core.Future
    /* renamed from: onSuccess, reason: merged with bridge method [inline-methods] */
    default Future<CompositeFuture> onSuccess2(Handler<CompositeFuture> handler) {
        super.onSuccess2((Handler) handler);
        return this;
    }

    @Override // io.vertx.core.Future
    default Future<CompositeFuture> onFailure(Handler<Throwable> handler) {
        super.onFailure(handler);
        return this;
    }

    @GenIgnore
    default <T> List<T> list() {
        int size = size();
        ArrayList arrayList = new ArrayList(size);
        for (int index = 0; index < size; index++) {
            arrayList.add(resultAt(index));
        }
        return arrayList;
    }
}
