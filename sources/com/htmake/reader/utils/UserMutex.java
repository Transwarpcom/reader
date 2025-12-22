package com.htmake.reader.utils;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UserMutex.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000eR\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/htmake/reader/utils/UserMutex;", "", "()V", "lockerMap", "Lcom/htmake/reader/utils/LRUCache;", "", "Lkotlinx/coroutines/sync/Mutex;", "getLockerMap", "()Lcom/htmake/reader/utils/LRUCache;", "mutex", "getMutex", "()Lkotlinx/coroutines/sync/Mutex;", "getLocker", "lockKey", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/utils/UserMutex.class */
public final class UserMutex {

    @NotNull
    public static final UserMutex INSTANCE = new UserMutex();

    @NotNull
    private static final Mutex mutex = MutexKt.Mutex$default(false, 1, null);

    @NotNull
    private static final LRUCache<String, Mutex> lockerMap = new LRUCache<>(10);

    /* compiled from: UserMutex.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "UserMutex.kt", l = {24}, i = {0, 0}, s = {"L$0", "L$1"}, n = {"lockKey", "$this$withLock_u24default$iv"}, m = "getLocker", c = "com.htmake.reader.utils.UserMutex")
    /* renamed from: com.htmake.reader.utils.UserMutex$getLocker$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/utils/UserMutex$getLocker$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return UserMutex.this.getLocker(null, this);
        }
    }

    private UserMutex() {
    }

    @NotNull
    public final Mutex getMutex() {
        return mutex;
    }

    @NotNull
    public final LRUCache<String, Mutex> getLockerMap() {
        return lockerMap;
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getLocker(@org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlinx.coroutines.sync.Mutex> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.utils.UserMutex.getLocker(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
