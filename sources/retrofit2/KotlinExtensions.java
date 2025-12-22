package retrofit2;

import java.lang.reflect.Method;
import kotlin.KotlinNullPointerException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinExtensions.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 2, d1 = {"��.\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010��\u001a\u0002H\u0001\"\b\b��\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a+\u0010��\u001a\u0004\u0018\u0001H\u0001\"\b\b��\u0010\u0001*\u00020\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003H\u0087@ø\u0001��¢\u0006\u0004\b\u0005\u0010\u0004\u001a+\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0007\"\b\b��\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u001a\u0010\b\u001a\u0002H\u0001\"\u0006\b��\u0010\u0001\u0018\u0001*\u00020\tH\u0086\b¢\u0006\u0002\u0010\n\u001a\u0019\u0010\u000b\u001a\u00020\f*\u00060\rj\u0002`\u000eH\u0080@ø\u0001��¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"await", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "Lretrofit2/Call;", "(Lretrofit2/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitNullable", "awaitResponse", "Lretrofit2/Response;", "create", "Lretrofit2/Retrofit;", "(Lretrofit2/Retrofit;)Ljava/lang/Object;", "yieldAndThrow", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retrofit"})
@JvmName(name = "KotlinExtensions")
/* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/KotlinExtensions.class */
public final class KotlinExtensions {

    /* compiled from: KotlinExtensions.kt */
    @Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0018\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0001\u0010��\u001a\u0004\u0018\u00010\u0001*\u00060\u0002j\u0002`\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0080@ø\u0001��"}, d2 = {"yieldAndThrow", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "continuation", "Lkotlin/coroutines/Continuation;", ""})
    @DebugMetadata(f = "KotlinExtensions.kt", l = {100, 102}, i = {0}, s = {"L$0"}, n = {"$receiver"}, m = "yieldAndThrow", c = "retrofit2/KotlinExtensions")
    /* renamed from: retrofit2.KotlinExtensions$yieldAndThrow$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/retrofit-2.6.1.jar:retrofit2/KotlinExtensions$yieldAndThrow$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;
        Object L$0;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object result) {
            this.result = result;
            this.label |= Integer.MIN_VALUE;
            return KotlinExtensions.yieldAndThrow(null, this);
        }

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }
    }

    private static final <T> T create(@NotNull Retrofit retrofit) {
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        return (T) retrofit.create(Object.class);
    }

    @Nullable
    public static final <T> Object await(@NotNull final Call<T> call, @NotNull Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl continuation2 = cancellable$iv;
        continuation2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit2.KotlinExtensions$await$$inlined$suspendCancellableCoroutine$lambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable it) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$await$2$2
            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<T> call2, @NotNull Response<T> response) {
                Intrinsics.checkParameterIsNotNull(call2, "call");
                Intrinsics.checkParameterIsNotNull(response, "response");
                if (response.isSuccessful()) {
                    Object body = response.body();
                    if (body == null) {
                        Object objTag = call2.request().tag(Invocation.class);
                        if (objTag == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(objTag, "call.request().tag(Invocation::class.java)!!");
                        Invocation invocation = (Invocation) objTag;
                        Method method = invocation.method();
                        StringBuilder sbAppend = new StringBuilder().append("Response from ");
                        Intrinsics.checkExpressionValueIsNotNull(method, "method");
                        Class<?> declaringClass = method.getDeclaringClass();
                        Intrinsics.checkExpressionValueIsNotNull(declaringClass, "method.declaringClass");
                        KotlinNullPointerException e = new KotlinNullPointerException(sbAppend.append(declaringClass.getName()).append('.').append(method.getName()).append(" was null but response body type was declared as non-null").toString());
                        CancellableContinuation cancellableContinuation = continuation2;
                        Result.Companion companion = Result.Companion;
                        cancellableContinuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(e)));
                        return;
                    }
                    CancellableContinuation cancellableContinuation2 = continuation2;
                    Result.Companion companion2 = Result.Companion;
                    cancellableContinuation2.resumeWith(Result.m2105constructorimpl(body));
                    return;
                }
                CancellableContinuation cancellableContinuation3 = continuation2;
                HttpException httpException = new HttpException(response);
                Result.Companion companion3 = Result.Companion;
                cancellableContinuation3.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(httpException)));
            }

            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<T> call2, @NotNull Throwable t) {
                Intrinsics.checkParameterIsNotNull(call2, "call");
                Intrinsics.checkParameterIsNotNull(t, "t");
                CancellableContinuation cancellableContinuation = continuation2;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @JvmName(name = "awaitNullable")
    @Nullable
    public static final <T> Object awaitNullable(@NotNull final Call<T> call, @NotNull Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl continuation2 = cancellable$iv;
        continuation2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit2.KotlinExtensions$await$$inlined$suspendCancellableCoroutine$lambda$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable it) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$await$4$2
            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<T> call2, @NotNull Response<T> response) {
                Intrinsics.checkParameterIsNotNull(call2, "call");
                Intrinsics.checkParameterIsNotNull(response, "response");
                if (response.isSuccessful()) {
                    CancellableContinuation cancellableContinuation = continuation2;
                    T tBody = response.body();
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m2105constructorimpl(tBody));
                    return;
                }
                CancellableContinuation cancellableContinuation2 = continuation2;
                HttpException httpException = new HttpException(response);
                Result.Companion companion2 = Result.Companion;
                cancellableContinuation2.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(httpException)));
            }

            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<T> call2, @NotNull Throwable t) {
                Intrinsics.checkParameterIsNotNull(call2, "call");
                Intrinsics.checkParameterIsNotNull(t, "t");
                CancellableContinuation cancellableContinuation = continuation2;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Nullable
    public static final <T> Object awaitResponse(@NotNull final Call<T> call, @NotNull Continuation<? super Response<T>> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl continuation2 = cancellable$iv;
        continuation2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit2.KotlinExtensions$awaitResponse$$inlined$suspendCancellableCoroutine$lambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable it) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$awaitResponse$2$2
            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<T> call2, @NotNull Response<T> response) {
                Intrinsics.checkParameterIsNotNull(call2, "call");
                Intrinsics.checkParameterIsNotNull(response, "response");
                CancellableContinuation cancellableContinuation = continuation2;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m2105constructorimpl(response));
            }

            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<T> call2, @NotNull Throwable t) {
                Intrinsics.checkParameterIsNotNull(call2, "call");
                Intrinsics.checkParameterIsNotNull(t, "t");
                CancellableContinuation cancellableContinuation = continuation2;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object yieldAndThrow(@org.jetbrains.annotations.NotNull java.lang.Exception r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<?> r5) throws java.lang.Exception {
        /*
            r0 = r5
            boolean r0 = r0 instanceof retrofit2.KotlinExtensions.AnonymousClass1
            if (r0 == 0) goto L24
            r0 = r5
            retrofit2.KotlinExtensions$yieldAndThrow$1 r0 = (retrofit2.KotlinExtensions.AnonymousClass1) r0
            r7 = r0
            r0 = r7
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L24
            r0 = r7
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L2d
        L24:
            retrofit2.KotlinExtensions$yieldAndThrow$1 r0 = new retrofit2.KotlinExtensions$yieldAndThrow$1
            r1 = r0
            r2 = r5
            r1.<init>(r2)
            r7 = r0
        L2d:
            r0 = r7
            java.lang.Object r0 = r0.result
            r6 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r8 = r0
            r0 = r7
            int r0 = r0.label
            switch(r0) {
                case 0: goto L50;
                case 1: goto L77;
                default: goto L96;
            }
        L50:
            r0 = r6
            r1 = r0
            boolean r1 = r1 instanceof kotlin.Result.Failure
            if (r1 == 0) goto L5f
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        L5f:
            r0 = r7
            r1 = r7
            r2 = r4
            r1.L$0 = r2
            r1 = r7
            r2 = 1
            r1.label = r2
            java.lang.Object r0 = kotlinx.coroutines.YieldKt.yield(r0)
            r1 = r0
            r2 = r8
            if (r1 != r2) goto L90
            r1 = r8
            return r1
        L77:
            r0 = r7
            java.lang.Object r0 = r0.L$0
            java.lang.Exception r0 = (java.lang.Exception) r0
            r4 = r0
            r0 = r6
            r1 = r0
            boolean r1 = r1 instanceof kotlin.Result.Failure
            if (r1 == 0) goto L8e
            kotlin.Result$Failure r0 = (kotlin.Result.Failure) r0
            java.lang.Throwable r0 = r0.exception
            throw r0
        L8e:
            r0 = r6
        L90:
            r0 = r4
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L96:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.KotlinExtensions.yieldAndThrow(java.lang.Exception, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
