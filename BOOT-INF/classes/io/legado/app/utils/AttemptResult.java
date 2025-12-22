package io.legado.app.utils;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u001b\b\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J,\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0000\"\u0004\b\u0001\u0010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u001a0\u001cH\u0086\bø\u0001\u0000J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001f"},
   d2 = {"Lio/legado/app/utils/AttemptResult;", "T", "", "value", "error", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "hasValue", "", "getHasValue", "()Z", "isError", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Throwable;)Lio/legado/app/utils/AttemptResult;", "equals", "other", "hashCode", "", "then", "R", "f", "Lkotlin/Function1;", "toString", "", "reader-pro"}
)
public final class AttemptResult<T> {
   @Nullable
   private final T value;
   @Nullable
   private final Throwable error;

   @PublishedApi
   public AttemptResult(@Nullable T value, @Nullable Throwable error) {
      this.value = value;
      this.error = error;
   }

   @Nullable
   public final T getValue() {
      return this.value;
   }

   @Nullable
   public final Throwable getError() {
      return this.error;
   }

   @NotNull
   public final <R> AttemptResult<R> then(@NotNull Function1<? super T, ? extends R> f) {
      Intrinsics.checkNotNullParameter(f, "f");
      int $i$f$then = false;
      int $i$f$isError = false;
      if (this.getError() != null) {
         return this;
      } else {
         int $i$f$attempt = false;
         Object value$iv = null;
         Throwable error$iv = null;

         try {
            int var6 = false;
            value$iv = f.invoke(this.getValue());
         } catch (Throwable var7) {
            error$iv = var7;
         }

         return new AttemptResult(value$iv, error$iv);
      }
   }

   public final boolean isError() {
      int $i$f$isError = false;
      return this.getError() != null;
   }

   public final boolean getHasValue() {
      int $i$f$getHasValue = false;
      return this.getError() == null;
   }

   @Nullable
   public final T component1() {
      return this.value;
   }

   @Nullable
   public final Throwable component2() {
      return this.error;
   }

   @NotNull
   public final AttemptResult<T> copy(@Nullable T value, @Nullable Throwable error) {
      return new AttemptResult(value, error);
   }

   // $FF: synthetic method
   public static AttemptResult copy$default(AttemptResult var0, Object var1, Throwable var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.value;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.error;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "AttemptResult(value=" + this.value + ", error=" + this.error + ')';
   }

   public int hashCode() {
      int result = this.value == null ? 0 : this.value.hashCode();
      result = result * 31 + (this.error == null ? 0 : this.error.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof AttemptResult)) {
         return false;
      } else {
         AttemptResult var2 = (AttemptResult)other;
         if (!Intrinsics.areEqual(this.value, var2.value)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.error, var2.error);
         }
      }
   }
}
