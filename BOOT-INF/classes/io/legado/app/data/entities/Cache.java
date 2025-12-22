package io.legado.app.data.entities;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\r\"\u0004\b\u000f\u0010\u0010¨\u0006\u001b"},
   d2 = {"Lio/legado/app/data/entities/Cache;", "", "key", "", "value", "deadline", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getDeadline", "()J", "setDeadline", "(J)V", "getKey", "()Ljava/lang/String;", "getValue", "setValue", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
)
public final class Cache {
   @NotNull
   private final String key;
   @Nullable
   private String value;
   private long deadline;

   public Cache(@NotNull String key, @Nullable String value, long deadline) {
      Intrinsics.checkNotNullParameter(key, "key");
      super();
      this.key = key;
      this.value = value;
      this.deadline = deadline;
   }

   // $FF: synthetic method
   public Cache(String var1, String var2, long var3, int var5, DefaultConstructorMarker var6) {
      if ((var5 & 1) != 0) {
         var1 = "";
      }

      if ((var5 & 2) != 0) {
         var2 = null;
      }

      if ((var5 & 4) != 0) {
         var3 = 0L;
      }

      this(var1, var2, var3);
   }

   @NotNull
   public final String getKey() {
      return this.key;
   }

   @Nullable
   public final String getValue() {
      return this.value;
   }

   public final void setValue(@Nullable String <set-?>) {
      this.value = var1;
   }

   public final long getDeadline() {
      return this.deadline;
   }

   public final void setDeadline(long <set-?>) {
      this.deadline = var1;
   }

   @NotNull
   public final String component1() {
      return this.key;
   }

   @Nullable
   public final String component2() {
      return this.value;
   }

   public final long component3() {
      return this.deadline;
   }

   @NotNull
   public final Cache copy(@NotNull String key, @Nullable String value, long deadline) {
      Intrinsics.checkNotNullParameter(key, "key");
      return new Cache(key, value, deadline);
   }

   // $FF: synthetic method
   public static Cache copy$default(Cache var0, String var1, String var2, long var3, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.key;
      }

      if ((var5 & 2) != 0) {
         var2 = var0.value;
      }

      if ((var5 & 4) != 0) {
         var3 = var0.deadline;
      }

      return var0.copy(var1, var2, var3);
   }

   @NotNull
   public String toString() {
      return "Cache(key=" + this.key + ", value=" + this.value + ", deadline=" + this.deadline + ')';
   }

   public int hashCode() {
      int result = this.key.hashCode();
      result = result * 31 + (this.value == null ? 0 : this.value.hashCode());
      result = result * 31 + Long.hashCode(this.deadline);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Cache)) {
         return false;
      } else {
         Cache var2 = (Cache)other;
         if (!Intrinsics.areEqual(this.key, var2.key)) {
            return false;
         } else if (!Intrinsics.areEqual(this.value, var2.value)) {
            return false;
         } else {
            return this.deadline == var2.deadline;
         }
      }
   }

   public Cache() {
      this((String)null, (String)null, 0L, 7, (DefaultConstructorMarker)null);
   }
}
