package io.legado.app.model.localBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.exception.TocEmptyException;
import io.legado.app.help.BookHelp;
import io.legado.app.utils.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.Result.Companion;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u00152\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0016\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0014R\u001e\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0018"},
   d2 = {"Lio/legado/app/model/localBook/LocalBook;", "", "()V", "nameAuthorPatterns", "", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "[Ljava/util/regex/Pattern;", "analyzeNameAuthor", "Lkotlin/Pair;", "", "fileName", "deleteBook", "", "book", "Lio/legado/app/data/entities/Book;", "getBookInputStream", "Ljava/io/InputStream;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "Lkotlin/collections/ArrayList;", "getContent", "chapter", "reader-pro"}
)
public final class LocalBook {
   @NotNull
   public static final LocalBook INSTANCE = new LocalBook();
   @NotNull
   private static final Pattern[] nameAuthorPatterns;

   private LocalBook() {
   }

   @NotNull
   public final InputStream getBookInputStream(@NotNull Book book) throws FileNotFoundException, SecurityException {
      Intrinsics.checkNotNullParameter(book, "book");
      File file = book.getLocalFile();
      if (file.exists()) {
         return (InputStream)(new FileInputStream(file));
      } else {
         throw new FileNotFoundException(Intrinsics.stringPlus(book.getName(), " 文件不存在"));
      }
   }

   @NotNull
   public final ArrayList<BookChapter> getChapterList(@NotNull Book book) throws Exception {
      Intrinsics.checkNotNullParameter(book, "book");
      ArrayList chapters = book.isEpub() ? EpubFile.Companion.getChapterList(book) : (book.isUmd() ? UmdFile.Companion.getChapterList(book) : (book.isCbz() ? CbzFile.Companion.getChapterList(book) : (book.isPdf() ? PdfFile.Companion.getChapterList(book) : TextFile.Companion.getChapterList(book))));
      if (chapters.isEmpty()) {
         throw new TocEmptyException(Intrinsics.stringPlus("Chapterlist is empty  ", book.getLocalFile()));
      } else {
         return chapters;
      }
   }

   @Nullable
   public final String getContent(@NotNull Book book, @NotNull BookChapter chapter) {
      Intrinsics.checkNotNullParameter(book, "book");
      Intrinsics.checkNotNullParameter(chapter, "chapter");
      return book.isEpub() ? EpubFile.Companion.getContent(book, chapter) : (book.isUmd() ? UmdFile.Companion.getContent(book, chapter) : (book.isCbz() ? CbzFile.Companion.getContent(book, chapter) : (book.isPdf() ? PdfFile.Companion.getContent(book, chapter) : TextFile.Companion.getContent(book, chapter))));
   }

   @NotNull
   public final Pair<String, String> analyzeNameAuthor(@NotNull String fileName) {
      Intrinsics.checkNotNullParameter(fileName, "fileName");
      String tempFileName = StringsKt.substringBeforeLast$default(fileName, ".", (String)null, 2, (Object)null);
      Object name = null;
      Object author = null;
      Pattern[] var5 = nameAuthorPatterns;
      int var6 = 0;
      int var7 = var5.length;

      Matcher var9;
      boolean var11;
      boolean var12;
      boolean var14;
      do {
         if (var6 >= var7) {
            name = BookHelp.INSTANCE.formatBookName(tempFileName);
            String var20 = BookHelp.INSTANCE.formatBookAuthor(StringsKt.replace$default(tempFileName, name, "", false, 4, (Object)null));
            boolean var21 = false;
            boolean var22 = false;
            int var23 = false;
            boolean var18 = var20.length() != tempFileName.length();
            String var19 = var18 ? var20 : null;
            author = var19 == null ? "" : var19;
            return new Pair(name, author);
         }

         Pattern pattern = var5[var6];
         ++var6;
         Matcher var10 = pattern.matcher((CharSequence)tempFileName);
         var11 = false;
         var12 = false;
         var14 = false;
         var9 = var10.find() ? var10 : null;
      } while(var9 == null);

      var11 = false;
      var12 = false;
      var14 = false;
      String var10000 = var9.group(2);
      Intrinsics.checkNotNull(var10000);
      name = var10000;
      String group3 = var9.group(1);
      String group1 = group3 == null ? "" : group3;
      String var17 = var9.group(3);
      group3 = var17 == null ? "" : var17;
      author = BookHelp.INSTANCE.formatBookAuthor(Intrinsics.stringPlus(group1, group3));
      return new Pair(name, author);
   }

   public final void deleteBook(@NotNull Book book) {
      Intrinsics.checkNotNullParameter(book, "book");
      boolean var2 = false;

      Object var3;
      try {
         Companion var8 = Result.Companion;
         int var4 = false;
         File bookFile = book.getLocalFile();
         if ((book.isLocalTxt() || book.isUmd()) && bookFile.exists()) {
            bookFile.delete();
         }

         if (book.isEpub()) {
            File var12 = bookFile.getParentFile();
            Intrinsics.checkNotNullExpressionValue(var12, "bookFile.parentFile");
            if (var12.exists()) {
               FileUtils.INSTANCE.delete(var12, true);
            }
         }

         Unit var9 = Unit.INSTANCE;
         boolean var11 = false;
         var3 = Result.constructor-impl(var9);
      } catch (Throwable var7) {
         Companion var5 = Result.Companion;
         boolean var6 = false;
         var3 = Result.constructor-impl(ResultKt.createFailure(var7));
      }

   }

   static {
      Pattern[] var0 = new Pattern[]{Pattern.compile("(.*?)《([^《》]+)》.*?作者：(.*)"), Pattern.compile("(.*?)《([^《》]+)》(.*)"), Pattern.compile("(^)(.+) 作者：(.+)$"), Pattern.compile("(^)(.+) by (.+)$")};
      nameAuthorPatterns = var0;
   }
}
