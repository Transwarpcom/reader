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
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003Jm\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012¨\u0006+"},
   d2 = {"Lio/legado/app/data/entities/SearchResult;", "", "resultCount", "", "resultCountWithinChapter", "resultText", "", "chapterTitle", "query", "pageSize", "chapterIndex", "pageIndex", "queryIndexInResult", "queryIndexInChapter", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIII)V", "getChapterIndex", "()I", "getChapterTitle", "()Ljava/lang/String;", "getPageIndex", "getPageSize", "getQuery", "getQueryIndexInChapter", "getQueryIndexInResult", "getResultCount", "getResultCountWithinChapter", "getResultText", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "reader-pro"}
)
public final class SearchResult {
   private final int resultCount;
   private final int resultCountWithinChapter;
   @NotNull
   private final String resultText;
   @NotNull
   private final String chapterTitle;
   @NotNull
   private final String query;
   private final int pageSize;
   private final int chapterIndex;
   private final int pageIndex;
   private final int queryIndexInResult;
   private final int queryIndexInChapter;

   public SearchResult(int resultCount, int resultCountWithinChapter, @NotNull String resultText, @NotNull String chapterTitle, @NotNull String query, int pageSize, int chapterIndex, int pageIndex, int queryIndexInResult, int queryIndexInChapter) {
      Intrinsics.checkNotNullParameter(resultText, "resultText");
      Intrinsics.checkNotNullParameter(chapterTitle, "chapterTitle");
      Intrinsics.checkNotNullParameter(query, "query");
      super();
      this.resultCount = resultCount;
      this.resultCountWithinChapter = resultCountWithinChapter;
      this.resultText = resultText;
      this.chapterTitle = chapterTitle;
      this.query = query;
      this.pageSize = pageSize;
      this.chapterIndex = chapterIndex;
      this.pageIndex = pageIndex;
      this.queryIndexInResult = queryIndexInResult;
      this.queryIndexInChapter = queryIndexInChapter;
   }

   // $FF: synthetic method
   public SearchResult(int var1, int var2, String var3, String var4, String var5, int var6, int var7, int var8, int var9, int var10, int var11, DefaultConstructorMarker var12) {
      if ((var11 & 1) != 0) {
         var1 = 0;
      }

      if ((var11 & 2) != 0) {
         var2 = 0;
      }

      if ((var11 & 4) != 0) {
         var3 = "";
      }

      if ((var11 & 8) != 0) {
         var4 = "";
      }

      if ((var11 & 16) != 0) {
         var5 = "";
      }

      if ((var11 & 32) != 0) {
         var6 = 0;
      }

      if ((var11 & 64) != 0) {
         var7 = 0;
      }

      if ((var11 & 128) != 0) {
         var8 = 0;
      }

      if ((var11 & 256) != 0) {
         var9 = 0;
      }

      if ((var11 & 512) != 0) {
         var10 = 0;
      }

      this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   public final int getResultCount() {
      return this.resultCount;
   }

   public final int getResultCountWithinChapter() {
      return this.resultCountWithinChapter;
   }

   @NotNull
   public final String getResultText() {
      return this.resultText;
   }

   @NotNull
   public final String getChapterTitle() {
      return this.chapterTitle;
   }

   @NotNull
   public final String getQuery() {
      return this.query;
   }

   public final int getPageSize() {
      return this.pageSize;
   }

   public final int getChapterIndex() {
      return this.chapterIndex;
   }

   public final int getPageIndex() {
      return this.pageIndex;
   }

   public final int getQueryIndexInResult() {
      return this.queryIndexInResult;
   }

   public final int getQueryIndexInChapter() {
      return this.queryIndexInChapter;
   }

   public final int component1() {
      return this.resultCount;
   }

   public final int component2() {
      return this.resultCountWithinChapter;
   }

   @NotNull
   public final String component3() {
      return this.resultText;
   }

   @NotNull
   public final String component4() {
      return this.chapterTitle;
   }

   @NotNull
   public final String component5() {
      return this.query;
   }

   public final int component6() {
      return this.pageSize;
   }

   public final int component7() {
      return this.chapterIndex;
   }

   public final int component8() {
      return this.pageIndex;
   }

   public final int component9() {
      return this.queryIndexInResult;
   }

   public final int component10() {
      return this.queryIndexInChapter;
   }

   @NotNull
   public final SearchResult copy(int resultCount, int resultCountWithinChapter, @NotNull String resultText, @NotNull String chapterTitle, @NotNull String query, int pageSize, int chapterIndex, int pageIndex, int queryIndexInResult, int queryIndexInChapter) {
      Intrinsics.checkNotNullParameter(resultText, "resultText");
      Intrinsics.checkNotNullParameter(chapterTitle, "chapterTitle");
      Intrinsics.checkNotNullParameter(query, "query");
      return new SearchResult(resultCount, resultCountWithinChapter, resultText, chapterTitle, query, pageSize, chapterIndex, pageIndex, queryIndexInResult, queryIndexInChapter);
   }

   // $FF: synthetic method
   public static SearchResult copy$default(SearchResult var0, int var1, int var2, String var3, String var4, String var5, int var6, int var7, int var8, int var9, int var10, int var11, Object var12) {
      if ((var11 & 1) != 0) {
         var1 = var0.resultCount;
      }

      if ((var11 & 2) != 0) {
         var2 = var0.resultCountWithinChapter;
      }

      if ((var11 & 4) != 0) {
         var3 = var0.resultText;
      }

      if ((var11 & 8) != 0) {
         var4 = var0.chapterTitle;
      }

      if ((var11 & 16) != 0) {
         var5 = var0.query;
      }

      if ((var11 & 32) != 0) {
         var6 = var0.pageSize;
      }

      if ((var11 & 64) != 0) {
         var7 = var0.chapterIndex;
      }

      if ((var11 & 128) != 0) {
         var8 = var0.pageIndex;
      }

      if ((var11 & 256) != 0) {
         var9 = var0.queryIndexInResult;
      }

      if ((var11 & 512) != 0) {
         var10 = var0.queryIndexInChapter;
      }

      return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   @NotNull
   public String toString() {
      return "SearchResult(resultCount=" + this.resultCount + ", resultCountWithinChapter=" + this.resultCountWithinChapter + ", resultText=" + this.resultText + ", chapterTitle=" + this.chapterTitle + ", query=" + this.query + ", pageSize=" + this.pageSize + ", chapterIndex=" + this.chapterIndex + ", pageIndex=" + this.pageIndex + ", queryIndexInResult=" + this.queryIndexInResult + ", queryIndexInChapter=" + this.queryIndexInChapter + ')';
   }

   public int hashCode() {
      int result = Integer.hashCode(this.resultCount);
      result = result * 31 + Integer.hashCode(this.resultCountWithinChapter);
      result = result * 31 + this.resultText.hashCode();
      result = result * 31 + this.chapterTitle.hashCode();
      result = result * 31 + this.query.hashCode();
      result = result * 31 + Integer.hashCode(this.pageSize);
      result = result * 31 + Integer.hashCode(this.chapterIndex);
      result = result * 31 + Integer.hashCode(this.pageIndex);
      result = result * 31 + Integer.hashCode(this.queryIndexInResult);
      result = result * 31 + Integer.hashCode(this.queryIndexInChapter);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof SearchResult)) {
         return false;
      } else {
         SearchResult var2 = (SearchResult)other;
         if (this.resultCount != var2.resultCount) {
            return false;
         } else if (this.resultCountWithinChapter != var2.resultCountWithinChapter) {
            return false;
         } else if (!Intrinsics.areEqual(this.resultText, var2.resultText)) {
            return false;
         } else if (!Intrinsics.areEqual(this.chapterTitle, var2.chapterTitle)) {
            return false;
         } else if (!Intrinsics.areEqual(this.query, var2.query)) {
            return false;
         } else if (this.pageSize != var2.pageSize) {
            return false;
         } else if (this.chapterIndex != var2.chapterIndex) {
            return false;
         } else if (this.pageIndex != var2.pageIndex) {
            return false;
         } else if (this.queryIndexInResult != var2.queryIndexInResult) {
            return false;
         } else {
            return this.queryIndexInChapter == var2.queryIndexInChapter;
         }
      }
   }

   public SearchResult() {
      this(0, 0, (String)null, (String)null, (String)null, 0, 0, 0, 0, 0, 1023, (DefaultConstructorMarker)null);
   }
}
