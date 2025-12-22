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
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u008d\u0001\u00102\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u000207HÖ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012¨\u00069"},
   d2 = {"Lio/legado/app/data/entities/rule/BookInfoRule;", "", "init", "", "name", "author", "intro", "kind", "lastChapter", "updateTime", "coverUrl", "tocUrl", "wordCount", "canReName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthor", "()Ljava/lang/String;", "setAuthor", "(Ljava/lang/String;)V", "getCanReName", "setCanReName", "getCoverUrl", "setCoverUrl", "getInit", "setInit", "getIntro", "setIntro", "getKind", "setKind", "getLastChapter", "setLastChapter", "getName", "setName", "getTocUrl", "setTocUrl", "getUpdateTime", "setUpdateTime", "getWordCount", "setWordCount", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "reader-pro"}
)
public final class BookInfoRule {
   @Nullable
   private String init;
   @Nullable
   private String name;
   @Nullable
   private String author;
   @Nullable
   private String intro;
   @Nullable
   private String kind;
   @Nullable
   private String lastChapter;
   @Nullable
   private String updateTime;
   @Nullable
   private String coverUrl;
   @Nullable
   private String tocUrl;
   @Nullable
   private String wordCount;
   @Nullable
   private String canReName;

   public BookInfoRule(@Nullable String init, @Nullable String name, @Nullable String author, @Nullable String intro, @Nullable String kind, @Nullable String lastChapter, @Nullable String updateTime, @Nullable String coverUrl, @Nullable String tocUrl, @Nullable String wordCount, @Nullable String canReName) {
      this.init = init;
      this.name = name;
      this.author = author;
      this.intro = intro;
      this.kind = kind;
      this.lastChapter = lastChapter;
      this.updateTime = updateTime;
      this.coverUrl = coverUrl;
      this.tocUrl = tocUrl;
      this.wordCount = wordCount;
      this.canReName = canReName;
   }

   // $FF: synthetic method
   public BookInfoRule(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, int var12, DefaultConstructorMarker var13) {
      if ((var12 & 1) != 0) {
         var1 = null;
      }

      if ((var12 & 2) != 0) {
         var2 = null;
      }

      if ((var12 & 4) != 0) {
         var3 = null;
      }

      if ((var12 & 8) != 0) {
         var4 = null;
      }

      if ((var12 & 16) != 0) {
         var5 = null;
      }

      if ((var12 & 32) != 0) {
         var6 = null;
      }

      if ((var12 & 64) != 0) {
         var7 = null;
      }

      if ((var12 & 128) != 0) {
         var8 = null;
      }

      if ((var12 & 256) != 0) {
         var9 = null;
      }

      if ((var12 & 512) != 0) {
         var10 = null;
      }

      if ((var12 & 1024) != 0) {
         var11 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
   }

   @Nullable
   public final String getInit() {
      return this.init;
   }

   public final void setInit(@Nullable String <set-?>) {
      this.init = var1;
   }

   @Nullable
   public final String getName() {
      return this.name;
   }

   public final void setName(@Nullable String <set-?>) {
      this.name = var1;
   }

   @Nullable
   public final String getAuthor() {
      return this.author;
   }

   public final void setAuthor(@Nullable String <set-?>) {
      this.author = var1;
   }

   @Nullable
   public final String getIntro() {
      return this.intro;
   }

   public final void setIntro(@Nullable String <set-?>) {
      this.intro = var1;
   }

   @Nullable
   public final String getKind() {
      return this.kind;
   }

   public final void setKind(@Nullable String <set-?>) {
      this.kind = var1;
   }

   @Nullable
   public final String getLastChapter() {
      return this.lastChapter;
   }

   public final void setLastChapter(@Nullable String <set-?>) {
      this.lastChapter = var1;
   }

   @Nullable
   public final String getUpdateTime() {
      return this.updateTime;
   }

   public final void setUpdateTime(@Nullable String <set-?>) {
      this.updateTime = var1;
   }

   @Nullable
   public final String getCoverUrl() {
      return this.coverUrl;
   }

   public final void setCoverUrl(@Nullable String <set-?>) {
      this.coverUrl = var1;
   }

   @Nullable
   public final String getTocUrl() {
      return this.tocUrl;
   }

   public final void setTocUrl(@Nullable String <set-?>) {
      this.tocUrl = var1;
   }

   @Nullable
   public final String getWordCount() {
      return this.wordCount;
   }

   public final void setWordCount(@Nullable String <set-?>) {
      this.wordCount = var1;
   }

   @Nullable
   public final String getCanReName() {
      return this.canReName;
   }

   public final void setCanReName(@Nullable String <set-?>) {
      this.canReName = var1;
   }

   @Nullable
   public final String component1() {
      return this.init;
   }

   @Nullable
   public final String component2() {
      return this.name;
   }

   @Nullable
   public final String component3() {
      return this.author;
   }

   @Nullable
   public final String component4() {
      return this.intro;
   }

   @Nullable
   public final String component5() {
      return this.kind;
   }

   @Nullable
   public final String component6() {
      return this.lastChapter;
   }

   @Nullable
   public final String component7() {
      return this.updateTime;
   }

   @Nullable
   public final String component8() {
      return this.coverUrl;
   }

   @Nullable
   public final String component9() {
      return this.tocUrl;
   }

   @Nullable
   public final String component10() {
      return this.wordCount;
   }

   @Nullable
   public final String component11() {
      return this.canReName;
   }

   @NotNull
   public final BookInfoRule copy(@Nullable String init, @Nullable String name, @Nullable String author, @Nullable String intro, @Nullable String kind, @Nullable String lastChapter, @Nullable String updateTime, @Nullable String coverUrl, @Nullable String tocUrl, @Nullable String wordCount, @Nullable String canReName) {
      return new BookInfoRule(init, name, author, intro, kind, lastChapter, updateTime, coverUrl, tocUrl, wordCount, canReName);
   }

   // $FF: synthetic method
   public static BookInfoRule copy$default(BookInfoRule var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, int var12, Object var13) {
      if ((var12 & 1) != 0) {
         var1 = var0.init;
      }

      if ((var12 & 2) != 0) {
         var2 = var0.name;
      }

      if ((var12 & 4) != 0) {
         var3 = var0.author;
      }

      if ((var12 & 8) != 0) {
         var4 = var0.intro;
      }

      if ((var12 & 16) != 0) {
         var5 = var0.kind;
      }

      if ((var12 & 32) != 0) {
         var6 = var0.lastChapter;
      }

      if ((var12 & 64) != 0) {
         var7 = var0.updateTime;
      }

      if ((var12 & 128) != 0) {
         var8 = var0.coverUrl;
      }

      if ((var12 & 256) != 0) {
         var9 = var0.tocUrl;
      }

      if ((var12 & 512) != 0) {
         var10 = var0.wordCount;
      }

      if ((var12 & 1024) != 0) {
         var11 = var0.canReName;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("BookInfoRule(init=").append(this.init).append(", name=").append(this.name).append(", author=").append(this.author).append(", intro=").append(this.intro).append(", kind=").append(this.kind).append(", lastChapter=").append(this.lastChapter).append(", updateTime=").append(this.updateTime).append(", coverUrl=").append(this.coverUrl).append(", tocUrl=").append(this.tocUrl).append(", wordCount=").append(this.wordCount).append(", canReName=").append(this.canReName).append(')');
      return var1.toString();
   }

   public int hashCode() {
      int result = this.init == null ? 0 : this.init.hashCode();
      result = result * 31 + (this.name == null ? 0 : this.name.hashCode());
      result = result * 31 + (this.author == null ? 0 : this.author.hashCode());
      result = result * 31 + (this.intro == null ? 0 : this.intro.hashCode());
      result = result * 31 + (this.kind == null ? 0 : this.kind.hashCode());
      result = result * 31 + (this.lastChapter == null ? 0 : this.lastChapter.hashCode());
      result = result * 31 + (this.updateTime == null ? 0 : this.updateTime.hashCode());
      result = result * 31 + (this.coverUrl == null ? 0 : this.coverUrl.hashCode());
      result = result * 31 + (this.tocUrl == null ? 0 : this.tocUrl.hashCode());
      result = result * 31 + (this.wordCount == null ? 0 : this.wordCount.hashCode());
      result = result * 31 + (this.canReName == null ? 0 : this.canReName.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof BookInfoRule)) {
         return false;
      } else {
         BookInfoRule var2 = (BookInfoRule)other;
         if (!Intrinsics.areEqual(this.init, var2.init)) {
            return false;
         } else if (!Intrinsics.areEqual(this.name, var2.name)) {
            return false;
         } else if (!Intrinsics.areEqual(this.author, var2.author)) {
            return false;
         } else if (!Intrinsics.areEqual(this.intro, var2.intro)) {
            return false;
         } else if (!Intrinsics.areEqual(this.kind, var2.kind)) {
            return false;
         } else if (!Intrinsics.areEqual(this.lastChapter, var2.lastChapter)) {
            return false;
         } else if (!Intrinsics.areEqual(this.updateTime, var2.updateTime)) {
            return false;
         } else if (!Intrinsics.areEqual(this.coverUrl, var2.coverUrl)) {
            return false;
         } else if (!Intrinsics.areEqual(this.tocUrl, var2.tocUrl)) {
            return false;
         } else if (!Intrinsics.areEqual(this.wordCount, var2.wordCount)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.canReName, var2.canReName);
         }
      }
   }

   public BookInfoRule() {
      this((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 2047, (DefaultConstructorMarker)null);
   }
}
