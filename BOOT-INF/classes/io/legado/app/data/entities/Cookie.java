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
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"},
   d2 = {"Lio/legado/app/data/entities/Cookie;", "", "url", "", "cookie", "(Ljava/lang/String;Ljava/lang/String;)V", "getCookie", "()Ljava/lang/String;", "setCookie", "(Ljava/lang/String;)V", "getUrl", "setUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
)
public final class Cookie {
   @NotNull
   private String url;
   @NotNull
   private String cookie;

   public Cookie(@NotNull String url, @NotNull String cookie) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(cookie, "cookie");
      super();
      this.url = url;
      this.cookie = cookie;
   }

   // $FF: synthetic method
   public Cookie(String var1, String var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 1) != 0) {
         var1 = "";
      }

      if ((var3 & 2) != 0) {
         var2 = "";
      }

      this(var1, var2);
   }

   @NotNull
   public final String getUrl() {
      return this.url;
   }

   public final void setUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.url = var1;
   }

   @NotNull
   public final String getCookie() {
      return this.cookie;
   }

   public final void setCookie(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.cookie = var1;
   }

   @NotNull
   public final String component1() {
      return this.url;
   }

   @NotNull
   public final String component2() {
      return this.cookie;
   }

   @NotNull
   public final Cookie copy(@NotNull String url, @NotNull String cookie) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(cookie, "cookie");
      return new Cookie(url, cookie);
   }

   // $FF: synthetic method
   public static Cookie copy$default(Cookie var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.url;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.cookie;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "Cookie(url=" + this.url + ", cookie=" + this.cookie + ')';
   }

   public int hashCode() {
      int result = this.url.hashCode();
      result = result * 31 + this.cookie.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Cookie)) {
         return false;
      } else {
         Cookie var2 = (Cookie)other;
         if (!Intrinsics.areEqual(this.url, var2.url)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.cookie, var2.cookie);
         }
      }
   }

   public Cookie() {
      this((String)null, (String)null, 3, (DefaultConstructorMarker)null);
   }
}
