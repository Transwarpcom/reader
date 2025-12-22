package io.legado.app.help.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"},
   d2 = {"Lio/legado/app/help/http/Res;", "", "url", "", "body", "(Ljava/lang/String;Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
)
public final class Res {
   @NotNull
   private final String url;
   @Nullable
   private final String body;

   public Res(@NotNull String url, @Nullable String body) {
      Intrinsics.checkNotNullParameter(url, "url");
      super();
      this.url = url;
      this.body = body;
   }

   @NotNull
   public final String getUrl() {
      return this.url;
   }

   @Nullable
   public final String getBody() {
      return this.body;
   }

   @NotNull
   public final String component1() {
      return this.url;
   }

   @Nullable
   public final String component2() {
      return this.body;
   }

   @NotNull
   public final Res copy(@NotNull String url, @Nullable String body) {
      Intrinsics.checkNotNullParameter(url, "url");
      return new Res(url, body);
   }

   // $FF: synthetic method
   public static Res copy$default(Res var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.url;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.body;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "Res(url=" + this.url + ", body=" + this.body + ')';
   }

   public int hashCode() {
      int result = this.url.hashCode();
      result = result * 31 + (this.body == null ? 0 : this.body.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Res)) {
         return false;
      } else {
         Res var2 = (Res)other;
         if (!Intrinsics.areEqual(this.url, var2.url)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.body, var2.body);
         }
      }
   }
}
