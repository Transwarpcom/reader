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
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ji\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\r\"\u0004\b\u0014\u0010\u000fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000f¨\u0006+"},
   d2 = {"Lio/legado/app/data/entities/rule/TocRule;", "", "preUpdateJs", "", "chapterList", "chapterName", "chapterUrl", "isVolume", "isVip", "updateTime", "nextTocUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChapterList", "()Ljava/lang/String;", "setChapterList", "(Ljava/lang/String;)V", "getChapterName", "setChapterName", "getChapterUrl", "setChapterUrl", "setVip", "setVolume", "getNextTocUrl", "setNextTocUrl", "getPreUpdateJs", "setPreUpdateJs", "getUpdateTime", "setUpdateTime", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
)
public final class TocRule {
   @Nullable
   private String preUpdateJs;
   @Nullable
   private String chapterList;
   @Nullable
   private String chapterName;
   @Nullable
   private String chapterUrl;
   @Nullable
   private String isVolume;
   @Nullable
   private String isVip;
   @Nullable
   private String updateTime;
   @Nullable
   private String nextTocUrl;

   public TocRule(@Nullable String preUpdateJs, @Nullable String chapterList, @Nullable String chapterName, @Nullable String chapterUrl, @Nullable String isVolume, @Nullable String isVip, @Nullable String updateTime, @Nullable String nextTocUrl) {
      this.preUpdateJs = preUpdateJs;
      this.chapterList = chapterList;
      this.chapterName = chapterName;
      this.chapterUrl = chapterUrl;
      this.isVolume = isVolume;
      this.isVip = isVip;
      this.updateTime = updateTime;
      this.nextTocUrl = nextTocUrl;
   }

   // $FF: synthetic method
   public TocRule(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, int var9, DefaultConstructorMarker var10) {
      if ((var9 & 1) != 0) {
         var1 = null;
      }

      if ((var9 & 2) != 0) {
         var2 = null;
      }

      if ((var9 & 4) != 0) {
         var3 = null;
      }

      if ((var9 & 8) != 0) {
         var4 = null;
      }

      if ((var9 & 16) != 0) {
         var5 = null;
      }

      if ((var9 & 32) != 0) {
         var6 = null;
      }

      if ((var9 & 64) != 0) {
         var7 = null;
      }

      if ((var9 & 128) != 0) {
         var8 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   @Nullable
   public final String getPreUpdateJs() {
      return this.preUpdateJs;
   }

   public final void setPreUpdateJs(@Nullable String <set-?>) {
      this.preUpdateJs = var1;
   }

   @Nullable
   public final String getChapterList() {
      return this.chapterList;
   }

   public final void setChapterList(@Nullable String <set-?>) {
      this.chapterList = var1;
   }

   @Nullable
   public final String getChapterName() {
      return this.chapterName;
   }

   public final void setChapterName(@Nullable String <set-?>) {
      this.chapterName = var1;
   }

   @Nullable
   public final String getChapterUrl() {
      return this.chapterUrl;
   }

   public final void setChapterUrl(@Nullable String <set-?>) {
      this.chapterUrl = var1;
   }

   @Nullable
   public final String isVolume() {
      return this.isVolume;
   }

   public final void setVolume(@Nullable String <set-?>) {
      this.isVolume = var1;
   }

   @Nullable
   public final String isVip() {
      return this.isVip;
   }

   public final void setVip(@Nullable String <set-?>) {
      this.isVip = var1;
   }

   @Nullable
   public final String getUpdateTime() {
      return this.updateTime;
   }

   public final void setUpdateTime(@Nullable String <set-?>) {
      this.updateTime = var1;
   }

   @Nullable
   public final String getNextTocUrl() {
      return this.nextTocUrl;
   }

   public final void setNextTocUrl(@Nullable String <set-?>) {
      this.nextTocUrl = var1;
   }

   @Nullable
   public final String component1() {
      return this.preUpdateJs;
   }

   @Nullable
   public final String component2() {
      return this.chapterList;
   }

   @Nullable
   public final String component3() {
      return this.chapterName;
   }

   @Nullable
   public final String component4() {
      return this.chapterUrl;
   }

   @Nullable
   public final String component5() {
      return this.isVolume;
   }

   @Nullable
   public final String component6() {
      return this.isVip;
   }

   @Nullable
   public final String component7() {
      return this.updateTime;
   }

   @Nullable
   public final String component8() {
      return this.nextTocUrl;
   }

   @NotNull
   public final TocRule copy(@Nullable String preUpdateJs, @Nullable String chapterList, @Nullable String chapterName, @Nullable String chapterUrl, @Nullable String isVolume, @Nullable String isVip, @Nullable String updateTime, @Nullable String nextTocUrl) {
      return new TocRule(preUpdateJs, chapterList, chapterName, chapterUrl, isVolume, isVip, updateTime, nextTocUrl);
   }

   // $FF: synthetic method
   public static TocRule copy$default(TocRule var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, int var9, Object var10) {
      if ((var9 & 1) != 0) {
         var1 = var0.preUpdateJs;
      }

      if ((var9 & 2) != 0) {
         var2 = var0.chapterList;
      }

      if ((var9 & 4) != 0) {
         var3 = var0.chapterName;
      }

      if ((var9 & 8) != 0) {
         var4 = var0.chapterUrl;
      }

      if ((var9 & 16) != 0) {
         var5 = var0.isVolume;
      }

      if ((var9 & 32) != 0) {
         var6 = var0.isVip;
      }

      if ((var9 & 64) != 0) {
         var7 = var0.updateTime;
      }

      if ((var9 & 128) != 0) {
         var8 = var0.nextTocUrl;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   @NotNull
   public String toString() {
      return "TocRule(preUpdateJs=" + this.preUpdateJs + ", chapterList=" + this.chapterList + ", chapterName=" + this.chapterName + ", chapterUrl=" + this.chapterUrl + ", isVolume=" + this.isVolume + ", isVip=" + this.isVip + ", updateTime=" + this.updateTime + ", nextTocUrl=" + this.nextTocUrl + ')';
   }

   public int hashCode() {
      int result = this.preUpdateJs == null ? 0 : this.preUpdateJs.hashCode();
      result = result * 31 + (this.chapterList == null ? 0 : this.chapterList.hashCode());
      result = result * 31 + (this.chapterName == null ? 0 : this.chapterName.hashCode());
      result = result * 31 + (this.chapterUrl == null ? 0 : this.chapterUrl.hashCode());
      result = result * 31 + (this.isVolume == null ? 0 : this.isVolume.hashCode());
      result = result * 31 + (this.isVip == null ? 0 : this.isVip.hashCode());
      result = result * 31 + (this.updateTime == null ? 0 : this.updateTime.hashCode());
      result = result * 31 + (this.nextTocUrl == null ? 0 : this.nextTocUrl.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof TocRule)) {
         return false;
      } else {
         TocRule var2 = (TocRule)other;
         if (!Intrinsics.areEqual(this.preUpdateJs, var2.preUpdateJs)) {
            return false;
         } else if (!Intrinsics.areEqual(this.chapterList, var2.chapterList)) {
            return false;
         } else if (!Intrinsics.areEqual(this.chapterName, var2.chapterName)) {
            return false;
         } else if (!Intrinsics.areEqual(this.chapterUrl, var2.chapterUrl)) {
            return false;
         } else if (!Intrinsics.areEqual(this.isVolume, var2.isVolume)) {
            return false;
         } else if (!Intrinsics.areEqual(this.isVip, var2.isVip)) {
            return false;
         } else if (!Intrinsics.areEqual(this.updateTime, var2.updateTime)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.nextTocUrl, var2.nextTocUrl);
         }
      }
   }

   public TocRule() {
      this((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 255, (DefaultConstructorMarker)null);
   }
}
