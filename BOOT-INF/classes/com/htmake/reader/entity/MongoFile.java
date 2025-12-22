package com.htmake.reader.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006 "},
   d2 = {"Lcom/htmake/reader/entity/MongoFile;", "", "path", "", "content", "created_at", "", "updated_at", "(Ljava/lang/String;Ljava/lang/String;JJ)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getCreated_at", "()J", "setCreated_at", "(J)V", "getPath", "setPath", "getUpdated_at", "setUpdated_at", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
)
public final class MongoFile {
   @NotNull
   private String path;
   @NotNull
   private String content;
   private long created_at;
   private long updated_at;

   public MongoFile(@NotNull String path, @NotNull String content, long created_at, long updated_at) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(content, "content");
      super();
      this.path = path;
      this.content = content;
      this.created_at = created_at;
      this.updated_at = updated_at;
   }

   // $FF: synthetic method
   public MongoFile(String var1, String var2, long var3, long var5, int var7, DefaultConstructorMarker var8) {
      if ((var7 & 1) != 0) {
         var1 = "";
      }

      if ((var7 & 2) != 0) {
         var2 = "";
      }

      if ((var7 & 4) != 0) {
         var3 = System.currentTimeMillis();
      }

      if ((var7 & 8) != 0) {
         var5 = System.currentTimeMillis();
      }

      this(var1, var2, var3, var5);
   }

   @NotNull
   public final String getPath() {
      return this.path;
   }

   public final void setPath(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.path = var1;
   }

   @NotNull
   public final String getContent() {
      return this.content;
   }

   public final void setContent(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.content = var1;
   }

   public final long getCreated_at() {
      return this.created_at;
   }

   public final void setCreated_at(long <set-?>) {
      this.created_at = var1;
   }

   public final long getUpdated_at() {
      return this.updated_at;
   }

   public final void setUpdated_at(long <set-?>) {
      this.updated_at = var1;
   }

   @NotNull
   public final String component1() {
      return this.path;
   }

   @NotNull
   public final String component2() {
      return this.content;
   }

   public final long component3() {
      return this.created_at;
   }

   public final long component4() {
      return this.updated_at;
   }

   @NotNull
   public final MongoFile copy(@NotNull String path, @NotNull String content, long created_at, long updated_at) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(content, "content");
      return new MongoFile(path, content, created_at, updated_at);
   }

   // $FF: synthetic method
   public static MongoFile copy$default(MongoFile var0, String var1, String var2, long var3, long var5, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var1 = var0.path;
      }

      if ((var7 & 2) != 0) {
         var2 = var0.content;
      }

      if ((var7 & 4) != 0) {
         var3 = var0.created_at;
      }

      if ((var7 & 8) != 0) {
         var5 = var0.updated_at;
      }

      return var0.copy(var1, var2, var3, var5);
   }

   @NotNull
   public String toString() {
      return "MongoFile(path=" + this.path + ", content=" + this.content + ", created_at=" + this.created_at + ", updated_at=" + this.updated_at + ')';
   }

   public int hashCode() {
      int result = this.path.hashCode();
      result = result * 31 + this.content.hashCode();
      result = result * 31 + Long.hashCode(this.created_at);
      result = result * 31 + Long.hashCode(this.updated_at);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof MongoFile)) {
         return false;
      } else {
         MongoFile var2 = (MongoFile)other;
         if (!Intrinsics.areEqual(this.path, var2.path)) {
            return false;
         } else if (!Intrinsics.areEqual(this.content, var2.content)) {
            return false;
         } else if (this.created_at != var2.created_at) {
            return false;
         } else {
            return this.updated_at == var2.updated_at;
         }
      }
   }

   public MongoFile() {
      this((String)null, (String)null, 0L, 0L, 15, (DefaultConstructorMarker)null);
   }
}
