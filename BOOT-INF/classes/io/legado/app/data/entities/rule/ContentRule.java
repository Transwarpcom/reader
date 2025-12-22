package io.legado.app.data.entities.rule;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003JQ\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006%"},
   d2 = {"Lio/legado/app/data/entities/rule/ContentRule;", "", "content", "", "nextContentUrl", "webJs", "sourceRegex", "replaceRegex", "imageStyle", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getImageStyle", "setImageStyle", "getNextContentUrl", "setNextContentUrl", "getReplaceRegex", "setReplaceRegex", "getSourceRegex", "setSourceRegex", "getWebJs", "setWebJs", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
)
public final class ContentRule {
   @Nullable
   private String content;
   @Nullable
   private String nextContentUrl;
   @Nullable
   private String webJs;
   @Nullable
   private String sourceRegex;
   @Nullable
   private String replaceRegex;
   @Nullable
   private String imageStyle;

   public ContentRule(@Nullable String content, @Nullable String nextContentUrl, @Nullable String webJs, @Nullable String sourceRegex, @Nullable String replaceRegex, @Nullable String imageStyle) {
      this.content = content;
      this.nextContentUrl = nextContentUrl;
      this.webJs = webJs;
      this.sourceRegex = sourceRegex;
      this.replaceRegex = replaceRegex;
      this.imageStyle = imageStyle;
   }

   // $FF: synthetic method
   public ContentRule(String var1, String var2, String var3, String var4, String var5, String var6, int var7, DefaultConstructorMarker var8) {
      if ((var7 & 1) != 0) {
         var1 = null;
      }

      if ((var7 & 2) != 0) {
         var2 = null;
      }

      if ((var7 & 4) != 0) {
         var3 = null;
      }

      if ((var7 & 8) != 0) {
         var4 = null;
      }

      if ((var7 & 16) != 0) {
         var5 = null;
      }

      if ((var7 & 32) != 0) {
         var6 = null;
      }

      this(var1, var2, var3, var4, var5, var6);
   }

   @Nullable
   public final String getContent() {
      return this.content;
   }

   public final void setContent(@Nullable String <set-?>) {
      this.content = var1;
   }

   @Nullable
   public final String getNextContentUrl() {
      return this.nextContentUrl;
   }

   public final void setNextContentUrl(@Nullable String <set-?>) {
      this.nextContentUrl = var1;
   }

   @Nullable
   public final String getWebJs() {
      return this.webJs;
   }

   public final void setWebJs(@Nullable String <set-?>) {
      this.webJs = var1;
   }

   @Nullable
   public final String getSourceRegex() {
      return this.sourceRegex;
   }

   public final void setSourceRegex(@Nullable String <set-?>) {
      this.sourceRegex = var1;
   }

   @Nullable
   public final String getReplaceRegex() {
      return this.replaceRegex;
   }

   public final void setReplaceRegex(@Nullable String <set-?>) {
      this.replaceRegex = var1;
   }

   @Nullable
   public final String getImageStyle() {
      return this.imageStyle;
   }

   public final void setImageStyle(@Nullable String <set-?>) {
      this.imageStyle = var1;
   }

   @Nullable
   public final String component1() {
      return this.content;
   }

   @Nullable
   public final String component2() {
      return this.nextContentUrl;
   }

   @Nullable
   public final String component3() {
      return this.webJs;
   }

   @Nullable
   public final String component4() {
      return this.sourceRegex;
   }

   @Nullable
   public final String component5() {
      return this.replaceRegex;
   }

   @Nullable
   public final String component6() {
      return this.imageStyle;
   }

   @NotNull
   public final ContentRule copy(@Nullable String content, @Nullable String nextContentUrl, @Nullable String webJs, @Nullable String sourceRegex, @Nullable String replaceRegex, @Nullable String imageStyle) {
      return new ContentRule(content, nextContentUrl, webJs, sourceRegex, replaceRegex, imageStyle);
   }

   // $FF: synthetic method
   public static ContentRule copy$default(ContentRule var0, String var1, String var2, String var3, String var4, String var5, String var6, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var1 = var0.content;
      }

      if ((var7 & 2) != 0) {
         var2 = var0.nextContentUrl;
      }

      if ((var7 & 4) != 0) {
         var3 = var0.webJs;
      }

      if ((var7 & 8) != 0) {
         var4 = var0.sourceRegex;
      }

      if ((var7 & 16) != 0) {
         var5 = var0.replaceRegex;
      }

      if ((var7 & 32) != 0) {
         var6 = var0.imageStyle;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6);
   }

   @NotNull
   public String toString() {
      return "ContentRule(content=" + this.content + ", nextContentUrl=" + this.nextContentUrl + ", webJs=" + this.webJs + ", sourceRegex=" + this.sourceRegex + ", replaceRegex=" + this.replaceRegex + ", imageStyle=" + this.imageStyle + ')';
   }

   public int hashCode() {
      int result = this.content == null ? 0 : this.content.hashCode();
      result = result * 31 + (this.nextContentUrl == null ? 0 : this.nextContentUrl.hashCode());
      result = result * 31 + (this.webJs == null ? 0 : this.webJs.hashCode());
      result = result * 31 + (this.sourceRegex == null ? 0 : this.sourceRegex.hashCode());
      result = result * 31 + (this.replaceRegex == null ? 0 : this.replaceRegex.hashCode());
      result = result * 31 + (this.imageStyle == null ? 0 : this.imageStyle.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ContentRule)) {
         return false;
      } else {
         ContentRule var2 = (ContentRule)other;
         if (!Intrinsics.areEqual(this.content, var2.content)) {
            return false;
         } else if (!Intrinsics.areEqual(this.nextContentUrl, var2.nextContentUrl)) {
            return false;
         } else if (!Intrinsics.areEqual(this.webJs, var2.webJs)) {
            return false;
         } else if (!Intrinsics.areEqual(this.sourceRegex, var2.sourceRegex)) {
            return false;
         } else if (!Intrinsics.areEqual(this.replaceRegex, var2.replaceRegex)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.imageStyle, var2.imageStyle);
         }
      }
   }

   public ContentRule() {
      this((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 63, (DefaultConstructorMarker)null);
   }
}
