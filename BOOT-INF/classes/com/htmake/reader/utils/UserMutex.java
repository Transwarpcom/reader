package com.htmake.reader.utils;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"},
   d2 = {"Lcom/htmake/reader/utils/UserMutex;", "", "()V", "lockerMap", "Lcom/htmake/reader/utils/LRUCache;", "", "Lkotlinx/coroutines/sync/Mutex;", "getLockerMap", "()Lcom/htmake/reader/utils/LRUCache;", "mutex", "getMutex", "()Lkotlinx/coroutines/sync/Mutex;", "getLocker", "lockKey", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class UserMutex {
   @NotNull
   public static final UserMutex INSTANCE = new UserMutex();
   @NotNull
   private static final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object)null);
   @NotNull
   private static final LRUCache<String, Mutex> lockerMap = new LRUCache(10);

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

   @Nullable
   public final Object getLocker(@NotNull String lockKey, @NotNull Continuation<? super Mutex> $completion) {
      Object $continuation;
      label70: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label70;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return UserMutex.this.getLocker((String)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Mutex $this$withLock_u24default$iv;
      Object owner$iv;
      boolean $i$f$withLock;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         $this$withLock_u24default$iv = this.getMutex();
         owner$iv = null;
         $i$f$withLock = false;
         boolean var6 = false;
         ((<undefinedtype>)$continuation).L$0 = lockKey;
         ((<undefinedtype>)$continuation).L$1 = $this$withLock_u24default$iv;
         ((<undefinedtype>)$continuation).label = 1;
         if ($this$withLock_u24default$iv.lock(owner$iv, (Continuation)$continuation) == var10) {
            return var10;
         }
         break;
      case 1:
         $i$f$withLock = false;
         owner$iv = null;
         $this$withLock_u24default$iv = (Mutex)((<undefinedtype>)$continuation).L$1;
         lockKey = (String)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      Mutex var13;
      try {
         int var7 = false;
         if (INSTANCE.getLockerMap().get(lockKey) == null) {
            INSTANCE.getLockerMap().put(lockKey, MutexKt.Mutex$default(false, 1, (Object)null));
         }

         Object var10000 = INSTANCE.getLockerMap().get(lockKey);
         Intrinsics.checkNotNull(var10000);
         var13 = (Mutex)var10000;
      } finally {
         $this$withLock_u24default$iv.unlock(owner$iv);
      }

      return var13;
   }
}
