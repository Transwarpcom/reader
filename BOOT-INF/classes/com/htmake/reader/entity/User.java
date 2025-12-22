package com.htmake.reader.entity;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b9\b\u0086\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0002\u0010\u0014J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u000bHÆ\u0003J\t\u0010;\u001a\u00020\u000bHÆ\u0003J\t\u0010<\u001a\u00020\u0012HÆ\u0003J\t\u0010=\u001a\u00020\u0012HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\bHÆ\u0003J\t\u0010B\u001a\u00020\bHÆ\u0003J\t\u0010C\u001a\u00020\u000bHÆ\u0003J\u0017\u0010D\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\rHÆ\u0003J\t\u0010E\u001a\u00020\u000bHÆ\u0003J\u0099\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0012HÆ\u0001J\u0013\u0010G\u001a\u00020\u000b2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010I\u001a\u00020\u0012HÖ\u0001J\t\u0010J\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0013\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u000f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u000e\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010 \"\u0004\b$\u0010\"R\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001c\"\u0004\b*\u0010\u001eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010,\"\u0004\b0\u0010.R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010,\"\u0004\b2\u0010.R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010,\"\u0004\b8\u0010.¨\u0006K"},
   d2 = {"Lcom/htmake/reader/entity/User;", "", "username", "", "password", "salt", "token", "last_login_at", "", "created_at", "enable_webdav", "", "token_map", "", "enable_local_store", "enable_book_source", "enable_rss_source", "book_source_limit", "", "book_limit", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJZLjava/util/Map;ZZZII)V", "getBook_limit", "()I", "setBook_limit", "(I)V", "getBook_source_limit", "setBook_source_limit", "getCreated_at", "()J", "setCreated_at", "(J)V", "getEnable_book_source", "()Z", "setEnable_book_source", "(Z)V", "getEnable_local_store", "setEnable_local_store", "getEnable_rss_source", "setEnable_rss_source", "getEnable_webdav", "setEnable_webdav", "getLast_login_at", "setLast_login_at", "getPassword", "()Ljava/lang/String;", "setPassword", "(Ljava/lang/String;)V", "getSalt", "setSalt", "getToken", "setToken", "getToken_map", "()Ljava/util/Map;", "setToken_map", "(Ljava/util/Map;)V", "getUsername", "setUsername", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "reader-pro"}
)
public final class User {
   @NotNull
   private String username;
   @NotNull
   private String password;
   @NotNull
   private String salt;
   @NotNull
   private String token;
   private long last_login_at;
   private long created_at;
   private boolean enable_webdav;
   @Nullable
   private Map<String, Long> token_map;
   private boolean enable_local_store;
   private boolean enable_book_source;
   private boolean enable_rss_source;
   private int book_source_limit;
   private int book_limit;

   public User(@NotNull String username, @NotNull String password, @NotNull String salt, @NotNull String token, long last_login_at, long created_at, boolean enable_webdav, @Nullable Map<String, Long> token_map, boolean enable_local_store, boolean enable_book_source, boolean enable_rss_source, int book_source_limit, int book_limit) {
      Intrinsics.checkNotNullParameter(username, "username");
      Intrinsics.checkNotNullParameter(password, "password");
      Intrinsics.checkNotNullParameter(salt, "salt");
      Intrinsics.checkNotNullParameter(token, "token");
      super();
      this.username = username;
      this.password = password;
      this.salt = salt;
      this.token = token;
      this.last_login_at = last_login_at;
      this.created_at = created_at;
      this.enable_webdav = enable_webdav;
      this.token_map = token_map;
      this.enable_local_store = enable_local_store;
      this.enable_book_source = enable_book_source;
      this.enable_rss_source = enable_rss_source;
      this.book_source_limit = book_source_limit;
      this.book_limit = book_limit;
   }

   // $FF: synthetic method
   public User(String var1, String var2, String var3, String var4, long var5, long var7, boolean var9, Map var10, boolean var11, boolean var12, boolean var13, int var14, int var15, int var16, DefaultConstructorMarker var17) {
      if ((var16 & 1) != 0) {
         var1 = "";
      }

      if ((var16 & 2) != 0) {
         var2 = "";
      }

      if ((var16 & 4) != 0) {
         var3 = "";
      }

      if ((var16 & 8) != 0) {
         var4 = "";
      }

      if ((var16 & 16) != 0) {
         var5 = System.currentTimeMillis();
      }

      if ((var16 & 32) != 0) {
         var7 = System.currentTimeMillis();
      }

      if ((var16 & 64) != 0) {
         var9 = false;
      }

      if ((var16 & 128) != 0) {
         var10 = null;
      }

      if ((var16 & 256) != 0) {
         var11 = false;
      }

      if ((var16 & 512) != 0) {
         var12 = true;
      }

      if ((var16 & 1024) != 0) {
         var13 = true;
      }

      if ((var16 & 2048) != 0) {
         var14 = 100;
      }

      if ((var16 & 4096) != 0) {
         var15 = 200;
      }

      this(var1, var2, var3, var4, var5, var7, var9, var10, var11, var12, var13, var14, var15);
   }

   @NotNull
   public final String getUsername() {
      return this.username;
   }

   public final void setUsername(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.username = var1;
   }

   @NotNull
   public final String getPassword() {
      return this.password;
   }

   public final void setPassword(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.password = var1;
   }

   @NotNull
   public final String getSalt() {
      return this.salt;
   }

   public final void setSalt(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.salt = var1;
   }

   @NotNull
   public final String getToken() {
      return this.token;
   }

   public final void setToken(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.token = var1;
   }

   public final long getLast_login_at() {
      return this.last_login_at;
   }

   public final void setLast_login_at(long <set-?>) {
      this.last_login_at = var1;
   }

   public final long getCreated_at() {
      return this.created_at;
   }

   public final void setCreated_at(long <set-?>) {
      this.created_at = var1;
   }

   public final boolean getEnable_webdav() {
      return this.enable_webdav;
   }

   public final void setEnable_webdav(boolean <set-?>) {
      this.enable_webdav = var1;
   }

   @Nullable
   public final Map<String, Long> getToken_map() {
      return this.token_map;
   }

   public final void setToken_map(@Nullable Map<String, Long> <set-?>) {
      this.token_map = var1;
   }

   public final boolean getEnable_local_store() {
      return this.enable_local_store;
   }

   public final void setEnable_local_store(boolean <set-?>) {
      this.enable_local_store = var1;
   }

   public final boolean getEnable_book_source() {
      return this.enable_book_source;
   }

   public final void setEnable_book_source(boolean <set-?>) {
      this.enable_book_source = var1;
   }

   public final boolean getEnable_rss_source() {
      return this.enable_rss_source;
   }

   public final void setEnable_rss_source(boolean <set-?>) {
      this.enable_rss_source = var1;
   }

   public final int getBook_source_limit() {
      return this.book_source_limit;
   }

   public final void setBook_source_limit(int <set-?>) {
      this.book_source_limit = var1;
   }

   public final int getBook_limit() {
      return this.book_limit;
   }

   public final void setBook_limit(int <set-?>) {
      this.book_limit = var1;
   }

   @NotNull
   public final String component1() {
      return this.username;
   }

   @NotNull
   public final String component2() {
      return this.password;
   }

   @NotNull
   public final String component3() {
      return this.salt;
   }

   @NotNull
   public final String component4() {
      return this.token;
   }

   public final long component5() {
      return this.last_login_at;
   }

   public final long component6() {
      return this.created_at;
   }

   public final boolean component7() {
      return this.enable_webdav;
   }

   @Nullable
   public final Map<String, Long> component8() {
      return this.token_map;
   }

   public final boolean component9() {
      return this.enable_local_store;
   }

   public final boolean component10() {
      return this.enable_book_source;
   }

   public final boolean component11() {
      return this.enable_rss_source;
   }

   public final int component12() {
      return this.book_source_limit;
   }

   public final int component13() {
      return this.book_limit;
   }

   @NotNull
   public final User copy(@NotNull String username, @NotNull String password, @NotNull String salt, @NotNull String token, long last_login_at, long created_at, boolean enable_webdav, @Nullable Map<String, Long> token_map, boolean enable_local_store, boolean enable_book_source, boolean enable_rss_source, int book_source_limit, int book_limit) {
      Intrinsics.checkNotNullParameter(username, "username");
      Intrinsics.checkNotNullParameter(password, "password");
      Intrinsics.checkNotNullParameter(salt, "salt");
      Intrinsics.checkNotNullParameter(token, "token");
      return new User(username, password, salt, token, last_login_at, created_at, enable_webdav, token_map, enable_local_store, enable_book_source, enable_rss_source, book_source_limit, book_limit);
   }

   // $FF: synthetic method
   public static User copy$default(User var0, String var1, String var2, String var3, String var4, long var5, long var7, boolean var9, Map var10, boolean var11, boolean var12, boolean var13, int var14, int var15, int var16, Object var17) {
      if ((var16 & 1) != 0) {
         var1 = var0.username;
      }

      if ((var16 & 2) != 0) {
         var2 = var0.password;
      }

      if ((var16 & 4) != 0) {
         var3 = var0.salt;
      }

      if ((var16 & 8) != 0) {
         var4 = var0.token;
      }

      if ((var16 & 16) != 0) {
         var5 = var0.last_login_at;
      }

      if ((var16 & 32) != 0) {
         var7 = var0.created_at;
      }

      if ((var16 & 64) != 0) {
         var9 = var0.enable_webdav;
      }

      if ((var16 & 128) != 0) {
         var10 = var0.token_map;
      }

      if ((var16 & 256) != 0) {
         var11 = var0.enable_local_store;
      }

      if ((var16 & 512) != 0) {
         var12 = var0.enable_book_source;
      }

      if ((var16 & 1024) != 0) {
         var13 = var0.enable_rss_source;
      }

      if ((var16 & 2048) != 0) {
         var14 = var0.book_source_limit;
      }

      if ((var16 & 4096) != 0) {
         var15 = var0.book_limit;
      }

      return var0.copy(var1, var2, var3, var4, var5, var7, var9, var10, var11, var12, var13, var14, var15);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("User(username=").append(this.username).append(", password=").append(this.password).append(", salt=").append(this.salt).append(", token=").append(this.token).append(", last_login_at=").append(this.last_login_at).append(", created_at=").append(this.created_at).append(", enable_webdav=").append(this.enable_webdav).append(", token_map=").append(this.token_map).append(", enable_local_store=").append(this.enable_local_store).append(", enable_book_source=").append(this.enable_book_source).append(", enable_rss_source=").append(this.enable_rss_source).append(", book_source_limit=");
      var1.append(this.book_source_limit).append(", book_limit=").append(this.book_limit).append(')');
      return var1.toString();
   }

   public int hashCode() {
      int result = this.username.hashCode();
      result = result * 31 + this.password.hashCode();
      result = result * 31 + this.salt.hashCode();
      result = result * 31 + this.token.hashCode();
      result = result * 31 + Long.hashCode(this.last_login_at);
      result = result * 31 + Long.hashCode(this.created_at);
      int var10000 = result * 31;
      byte var10001 = this.enable_webdav;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      result = result * 31 + (this.token_map == null ? 0 : this.token_map.hashCode());
      var10000 = result * 31;
      var10001 = this.enable_local_store;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      var10000 = result * 31;
      var10001 = this.enable_book_source;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      var10000 = result * 31;
      var10001 = this.enable_rss_source;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      result = result * 31 + Integer.hashCode(this.book_source_limit);
      result = result * 31 + Integer.hashCode(this.book_limit);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof User)) {
         return false;
      } else {
         User var2 = (User)other;
         if (!Intrinsics.areEqual(this.username, var2.username)) {
            return false;
         } else if (!Intrinsics.areEqual(this.password, var2.password)) {
            return false;
         } else if (!Intrinsics.areEqual(this.salt, var2.salt)) {
            return false;
         } else if (!Intrinsics.areEqual(this.token, var2.token)) {
            return false;
         } else if (this.last_login_at != var2.last_login_at) {
            return false;
         } else if (this.created_at != var2.created_at) {
            return false;
         } else if (this.enable_webdav != var2.enable_webdav) {
            return false;
         } else if (!Intrinsics.areEqual(this.token_map, var2.token_map)) {
            return false;
         } else if (this.enable_local_store != var2.enable_local_store) {
            return false;
         } else if (this.enable_book_source != var2.enable_book_source) {
            return false;
         } else if (this.enable_rss_source != var2.enable_rss_source) {
            return false;
         } else if (this.book_source_limit != var2.book_source_limit) {
            return false;
         } else {
            return this.book_limit == var2.book_limit;
         }
      }
   }

   public User() {
      this((String)null, (String)null, (String)null, (String)null, 0L, 0L, false, (Map)null, false, false, false, 0, 0, 8191, (DefaultConstructorMarker)null);
   }
}
