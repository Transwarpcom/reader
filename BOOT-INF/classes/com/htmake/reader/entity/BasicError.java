package com.htmake.reader.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\bHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"},
   d2 = {"Lcom/htmake/reader/entity/BasicError;", "", "error", "", "exception", "message", "path", "status", "", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJ)V", "getError", "()Ljava/lang/String;", "getException", "getMessage", "getPath", "getStatus", "()I", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "reader-pro"}
)
public final class BasicError {
   @NotNull
   private final String error;
   @NotNull
   private final String exception;
   @NotNull
   private final String message;
   @NotNull
   private final String path;
   private final int status;
   private final long timestamp;

   public BasicError(@NotNull String error, @NotNull String exception, @NotNull String message, @NotNull String path, int status, long timestamp) {
      Intrinsics.checkNotNullParameter(error, "error");
      Intrinsics.checkNotNullParameter(exception, "exception");
      Intrinsics.checkNotNullParameter(message, "message");
      Intrinsics.checkNotNullParameter(path, "path");
      super();
      this.error = error;
      this.exception = exception;
      this.message = message;
      this.path = path;
      this.status = status;
      this.timestamp = timestamp;
   }

   @NotNull
   public final String getError() {
      return this.error;
   }

   @NotNull
   public final String getException() {
      return this.exception;
   }

   @NotNull
   public final String getMessage() {
      return this.message;
   }

   @NotNull
   public final String getPath() {
      return this.path;
   }

   public final int getStatus() {
      return this.status;
   }

   public final long getTimestamp() {
      return this.timestamp;
   }

   @NotNull
   public final String component1() {
      return this.error;
   }

   @NotNull
   public final String component2() {
      return this.exception;
   }

   @NotNull
   public final String component3() {
      return this.message;
   }

   @NotNull
   public final String component4() {
      return this.path;
   }

   public final int component5() {
      return this.status;
   }

   public final long component6() {
      return this.timestamp;
   }

   @NotNull
   public final BasicError copy(@NotNull String error, @NotNull String exception, @NotNull String message, @NotNull String path, int status, long timestamp) {
      Intrinsics.checkNotNullParameter(error, "error");
      Intrinsics.checkNotNullParameter(exception, "exception");
      Intrinsics.checkNotNullParameter(message, "message");
      Intrinsics.checkNotNullParameter(path, "path");
      return new BasicError(error, exception, message, path, status, timestamp);
   }

   // $FF: synthetic method
   public static BasicError copy$default(BasicError var0, String var1, String var2, String var3, String var4, int var5, long var6, int var8, Object var9) {
      if ((var8 & 1) != 0) {
         var1 = var0.error;
      }

      if ((var8 & 2) != 0) {
         var2 = var0.exception;
      }

      if ((var8 & 4) != 0) {
         var3 = var0.message;
      }

      if ((var8 & 8) != 0) {
         var4 = var0.path;
      }

      if ((var8 & 16) != 0) {
         var5 = var0.status;
      }

      if ((var8 & 32) != 0) {
         var6 = var0.timestamp;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6);
   }

   @NotNull
   public String toString() {
      return "BasicError(error=" + this.error + ", exception=" + this.exception + ", message=" + this.message + ", path=" + this.path + ", status=" + this.status + ", timestamp=" + this.timestamp + ')';
   }

   public int hashCode() {
      int result = this.error.hashCode();
      result = result * 31 + this.exception.hashCode();
      result = result * 31 + this.message.hashCode();
      result = result * 31 + this.path.hashCode();
      result = result * 31 + Integer.hashCode(this.status);
      result = result * 31 + Long.hashCode(this.timestamp);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof BasicError)) {
         return false;
      } else {
         BasicError var2 = (BasicError)other;
         if (!Intrinsics.areEqual(this.error, var2.error)) {
            return false;
         } else if (!Intrinsics.areEqual(this.exception, var2.exception)) {
            return false;
         } else if (!Intrinsics.areEqual(this.message, var2.message)) {
            return false;
         } else if (!Intrinsics.areEqual(this.path, var2.path)) {
            return false;
         } else if (this.status != var2.status) {
            return false;
         } else {
            return this.timestamp == var2.timestamp;
         }
      }
   }
}
