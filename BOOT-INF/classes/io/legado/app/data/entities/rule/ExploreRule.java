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
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b,\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0081\u0001\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011¨\u00066"},
   d2 = {"Lio/legado/app/data/entities/rule/ExploreRule;", "Lio/legado/app/data/entities/rule/BookListRule;", "bookList", "", "name", "author", "intro", "kind", "lastChapter", "updateTime", "bookUrl", "coverUrl", "wordCount", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthor", "()Ljava/lang/String;", "setAuthor", "(Ljava/lang/String;)V", "getBookList", "setBookList", "getBookUrl", "setBookUrl", "getCoverUrl", "setCoverUrl", "getIntro", "setIntro", "getKind", "setKind", "getLastChapter", "setLastChapter", "getName", "setName", "getUpdateTime", "setUpdateTime", "getWordCount", "setWordCount", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "reader-pro"}
)
public final class ExploreRule implements BookListRule {
   @Nullable
   private String bookList;
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
   private String bookUrl;
   @Nullable
   private String coverUrl;
   @Nullable
   private String wordCount;

   public ExploreRule(@Nullable String bookList, @Nullable String name, @Nullable String author, @Nullable String intro, @Nullable String kind, @Nullable String lastChapter, @Nullable String updateTime, @Nullable String bookUrl, @Nullable String coverUrl, @Nullable String wordCount) {
      this.bookList = bookList;
      this.name = name;
      this.author = author;
      this.intro = intro;
      this.kind = kind;
      this.lastChapter = lastChapter;
      this.updateTime = updateTime;
      this.bookUrl = bookUrl;
      this.coverUrl = coverUrl;
      this.wordCount = wordCount;
   }

   // $FF: synthetic method
   public ExploreRule(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, int var11, DefaultConstructorMarker var12) {
      if ((var11 & 1) != 0) {
         var1 = null;
      }

      if ((var11 & 2) != 0) {
         var2 = null;
      }

      if ((var11 & 4) != 0) {
         var3 = null;
      }

      if ((var11 & 8) != 0) {
         var4 = null;
      }

      if ((var11 & 16) != 0) {
         var5 = null;
      }

      if ((var11 & 32) != 0) {
         var6 = null;
      }

      if ((var11 & 64) != 0) {
         var7 = null;
      }

      if ((var11 & 128) != 0) {
         var8 = null;
      }

      if ((var11 & 256) != 0) {
         var9 = null;
      }

      if ((var11 & 512) != 0) {
         var10 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   @Nullable
   public String getBookList() {
      return this.bookList;
   }

   public void setBookList(@Nullable String <set-?>) {
      this.bookList = var1;
   }

   @Nullable
   public String getName() {
      return this.name;
   }

   public void setName(@Nullable String <set-?>) {
      this.name = var1;
   }

   @Nullable
   public String getAuthor() {
      return this.author;
   }

   public void setAuthor(@Nullable String <set-?>) {
      this.author = var1;
   }

   @Nullable
   public String getIntro() {
      return this.intro;
   }

   public void setIntro(@Nullable String <set-?>) {
      this.intro = var1;
   }

   @Nullable
   public String getKind() {
      return this.kind;
   }

   public void setKind(@Nullable String <set-?>) {
      this.kind = var1;
   }

   @Nullable
   public String getLastChapter() {
      return this.lastChapter;
   }

   public void setLastChapter(@Nullable String <set-?>) {
      this.lastChapter = var1;
   }

   @Nullable
   public String getUpdateTime() {
      return this.updateTime;
   }

   public void setUpdateTime(@Nullable String <set-?>) {
      this.updateTime = var1;
   }

   @Nullable
   public String getBookUrl() {
      return this.bookUrl;
   }

   public void setBookUrl(@Nullable String <set-?>) {
      this.bookUrl = var1;
   }

   @Nullable
   public String getCoverUrl() {
      return this.coverUrl;
   }

   public void setCoverUrl(@Nullable String <set-?>) {
      this.coverUrl = var1;
   }

   @Nullable
   public String getWordCount() {
      return this.wordCount;
   }

   public void setWordCount(@Nullable String <set-?>) {
      this.wordCount = var1;
   }

   @Nullable
   public final String component1() {
      return this.getBookList();
   }

   @Nullable
   public final String component2() {
      return this.getName();
   }

   @Nullable
   public final String component3() {
      return this.getAuthor();
   }

   @Nullable
   public final String component4() {
      return this.getIntro();
   }

   @Nullable
   public final String component5() {
      return this.getKind();
   }

   @Nullable
   public final String component6() {
      return this.getLastChapter();
   }

   @Nullable
   public final String component7() {
      return this.getUpdateTime();
   }

   @Nullable
   public final String component8() {
      return this.getBookUrl();
   }

   @Nullable
   public final String component9() {
      return this.getCoverUrl();
   }

   @Nullable
   public final String component10() {
      return this.getWordCount();
   }

   @NotNull
   public final ExploreRule copy(@Nullable String bookList, @Nullable String name, @Nullable String author, @Nullable String intro, @Nullable String kind, @Nullable String lastChapter, @Nullable String updateTime, @Nullable String bookUrl, @Nullable String coverUrl, @Nullable String wordCount) {
      return new ExploreRule(bookList, name, author, intro, kind, lastChapter, updateTime, bookUrl, coverUrl, wordCount);
   }

   // $FF: synthetic method
   public static ExploreRule copy$default(ExploreRule var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, int var11, Object var12) {
      if ((var11 & 1) != 0) {
         var1 = var0.getBookList();
      }

      if ((var11 & 2) != 0) {
         var2 = var0.getName();
      }

      if ((var11 & 4) != 0) {
         var3 = var0.getAuthor();
      }

      if ((var11 & 8) != 0) {
         var4 = var0.getIntro();
      }

      if ((var11 & 16) != 0) {
         var5 = var0.getKind();
      }

      if ((var11 & 32) != 0) {
         var6 = var0.getLastChapter();
      }

      if ((var11 & 64) != 0) {
         var7 = var0.getUpdateTime();
      }

      if ((var11 & 128) != 0) {
         var8 = var0.getBookUrl();
      }

      if ((var11 & 256) != 0) {
         var9 = var0.getCoverUrl();
      }

      if ((var11 & 512) != 0) {
         var10 = var0.getWordCount();
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   @NotNull
   public String toString() {
      return "ExploreRule(bookList=" + this.getBookList() + ", name=" + this.getName() + ", author=" + this.getAuthor() + ", intro=" + this.getIntro() + ", kind=" + this.getKind() + ", lastChapter=" + this.getLastChapter() + ", updateTime=" + this.getUpdateTime() + ", bookUrl=" + this.getBookUrl() + ", coverUrl=" + this.getCoverUrl() + ", wordCount=" + this.getWordCount() + ')';
   }

   public int hashCode() {
      int result = this.getBookList() == null ? 0 : this.getBookList().hashCode();
      result = result * 31 + (this.getName() == null ? 0 : this.getName().hashCode());
      result = result * 31 + (this.getAuthor() == null ? 0 : this.getAuthor().hashCode());
      result = result * 31 + (this.getIntro() == null ? 0 : this.getIntro().hashCode());
      result = result * 31 + (this.getKind() == null ? 0 : this.getKind().hashCode());
      result = result * 31 + (this.getLastChapter() == null ? 0 : this.getLastChapter().hashCode());
      result = result * 31 + (this.getUpdateTime() == null ? 0 : this.getUpdateTime().hashCode());
      result = result * 31 + (this.getBookUrl() == null ? 0 : this.getBookUrl().hashCode());
      result = result * 31 + (this.getCoverUrl() == null ? 0 : this.getCoverUrl().hashCode());
      result = result * 31 + (this.getWordCount() == null ? 0 : this.getWordCount().hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ExploreRule)) {
         return false;
      } else {
         ExploreRule var2 = (ExploreRule)other;
         if (!Intrinsics.areEqual(this.getBookList(), var2.getBookList())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getName(), var2.getName())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getAuthor(), var2.getAuthor())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getIntro(), var2.getIntro())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getKind(), var2.getKind())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getLastChapter(), var2.getLastChapter())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getUpdateTime(), var2.getUpdateTime())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getBookUrl(), var2.getBookUrl())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getCoverUrl(), var2.getCoverUrl())) {
            return false;
         } else {
            return Intrinsics.areEqual(this.getWordCount(), var2.getWordCount());
         }
      }
   }

   public ExploreRule() {
      this((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, 1023, (DefaultConstructorMarker)null);
   }
}
