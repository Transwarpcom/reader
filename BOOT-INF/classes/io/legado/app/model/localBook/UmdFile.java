package io.legado.app.model.localBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.MD5Utils;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.ag2s.umdlib.domain.UmdBook;
import me.ag2s.umdlib.domain.UmdChapters;
import me.ag2s.umdlib.domain.UmdCover;
import me.ag2s.umdlib.domain.UmdHeader;
import me.ag2s.umdlib.umd.UmdReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"},
   d2 = {"Lio/legado/app/model/localBook/UmdFile;", "", "book", "Lio/legado/app/data/entities/Book;", "(Lio/legado/app/data/entities/Book;)V", "getBook", "()Lio/legado/app/data/entities/Book;", "setBook", "umdBook", "Lme/ag2s/umdlib/domain/UmdBook;", "getUmdBook", "()Lme/ag2s/umdlib/domain/UmdBook;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getContent", "", "chapter", "getImage", "Ljava/io/InputStream;", "href", "readUmd", "upBookInfo", "", "updateCover", "Companion", "reader-pro"}
)
public final class UmdFile {
   @NotNull
   public static final UmdFile.Companion Companion = new UmdFile.Companion((DefaultConstructorMarker)null);
   @NotNull
   private Book book;
   @Nullable
   private UmdBook umdBook;
   @Nullable
   private static UmdFile uFile;

   public UmdFile(@NotNull Book book) {
      Intrinsics.checkNotNullParameter(book, "book");
      super();
      this.book = book;

      try {
         UmdBook var2 = this.getUmdBook();
         if (var2 != null) {
            boolean var4 = false;
            boolean var5 = false;
            boolean var7 = false;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   @NotNull
   public final Book getBook() {
      return this.book;
   }

   public final void setBook(@NotNull Book <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.book = var1;
   }

   private final UmdBook getUmdBook() {
      if (this.umdBook != null) {
         return this.umdBook;
      } else {
         this.umdBook = this.readUmd();
         return this.umdBook;
      }
   }

   private final UmdBook readUmd() {
      InputStream input = LocalBook.INSTANCE.getBookInputStream(this.book);
      return (new UmdReader()).read(input);
   }

   private final void upBookInfo() {
      if (this.getUmdBook() == null) {
         UmdFile.Companion var10000 = Companion;
         uFile = null;
         this.book.setIntro("书籍导入异常");
      } else {
         UmdBook var3 = this.getUmdBook();
         Intrinsics.checkNotNull(var3);
         UmdHeader hd = var3.getHeader();
         Book var4 = this.book;
         String var2 = hd.getTitle();
         Intrinsics.checkNotNullExpressionValue(var2, "hd.title");
         var4.setName(var2);
         var4 = this.book;
         var2 = hd.getAuthor();
         Intrinsics.checkNotNullExpressionValue(var2, "hd.author");
         var4.setAuthor(var2);
         this.book.setKind(hd.getBookType());
         this.updateCover();
      }

   }

   private final void updateCover() {
      if (this.getUmdBook() == null) {
         UmdFile.Companion var14 = Companion;
         uFile = null;
      } else {
         String coverFile = Intrinsics.stringPlus(MD5Utils.INSTANCE.md5Encode16(this.book.getBookUrl()), ".jpg");
         String[] var3 = new String[]{this.book.getUserNameSpace(), "covers", coverFile};
         String relativeCoverUrl = Paths.get("assets", var3).toString();
         this.book.setCoverUrl(Intrinsics.stringPlus("/", StringsKt.replace$default(relativeCoverUrl, "\\", "/", false, 4, (Object)null)));
         String var10000 = this.book.workRoot();
         String[] var4 = new String[]{"storage", relativeCoverUrl};
         String coverUrl = Paths.get(var10000, var4).toString();
         if (!(new File(coverUrl)).exists()) {
            UmdBook var13 = this.getUmdBook();
            if (var13 != null) {
               UmdCover var5 = var13.getCover();
               if (var5 != null) {
                  byte[] var6 = var5.getCoverData();
                  if (var6 != null) {
                     boolean var8 = false;
                     boolean var9 = false;
                     int var11 = false;
                     FileUtils.INSTANCE.writeBytes(coverUrl, var6);
                  }
               }
            }
         }

      }
   }

   private final String getContent(BookChapter chapter) {
      UmdBook var2 = this.getUmdBook();
      String var10000;
      if (var2 == null) {
         var10000 = null;
      } else {
         UmdChapters var3 = var2.getChapters();
         var10000 = var3 == null ? null : var3.getContentString(chapter.getIndex());
      }

      return var10000;
   }

   private final ArrayList<BookChapter> getChapterList() {
      ArrayList chapterList = new ArrayList();
      UmdBook var2 = this.getUmdBook();
      if (var2 != null) {
         UmdChapters var3 = var2.getChapters();
         if (var3 != null) {
            List var4 = var3.getTitles();
            if (var4 != null) {
               Iterable $this$forEachIndexed$iv = (Iterable)var4;
               int $i$f$forEachIndexed = false;
               int index$iv = 0;
               Iterator var8 = $this$forEachIndexed$iv.iterator();

               while(var8.hasNext()) {
                  Object item$iv = var8.next();
                  int var10 = index$iv++;
                  boolean var11 = false;
                  if (var10 < 0) {
                     CollectionsKt.throwIndexOverflow();
                  }

                  byte[] $noName_1 = (byte[])item$iv;
                  int var14 = false;
                  UmdBook var18 = this.getUmdBook();
                  Intrinsics.checkNotNull(var18);
                  String title = var18.getChapters().getTitle(var10);
                  BookChapter chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                  Intrinsics.checkNotNullExpressionValue(title, "title");
                  chapter.setTitle(title);
                  chapter.setIndex(var10);
                  chapter.setBookUrl(this.getBook().getBookUrl());
                  chapter.setUrl(String.valueOf(var10));
                  System.out.println(Intrinsics.stringPlus("UMD", chapter.getUrl()));
                  chapterList.add(chapter);
               }
            }
         }
      }

      Book var19 = this.book;
      BookChapter var17 = (BookChapter)CollectionsKt.lastOrNull((List)chapterList);
      var19.setLatestChapterTitle(var17 == null ? null : var17.getTitle());
      this.book.setTotalChapterNum(chapterList.size());
      return chapterList;
   }

   private final InputStream getImage(String href) {
      return null;
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0007J\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000bJ\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"},
      d2 = {"Lio/legado/app/model/localBook/UmdFile$Companion;", "", "()V", "uFile", "Lio/legado/app/model/localBook/UmdFile;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "book", "Lio/legado/app/data/entities/Book;", "getContent", "", "chapter", "getImage", "Ljava/io/InputStream;", "href", "getUFile", "upBookInfo", "", "onlyCover", "", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      private final synchronized UmdFile getUFile(Book book) {
         UmdFile var4;
         if (UmdFile.uFile != null) {
            UmdFile var2 = UmdFile.uFile;
            String var10000;
            if (var2 == null) {
               var10000 = null;
            } else {
               Book var3 = var2.getBook();
               var10000 = var3.getBookUrl();
            }

            if (Intrinsics.areEqual(var10000, book.getBookUrl())) {
               var2 = UmdFile.uFile;
               if (var2 != null) {
                  var2.setBook(book);
               }

               var4 = UmdFile.uFile;
               Intrinsics.checkNotNull(var4);
               return var4;
            }
         }

         UmdFile.uFile = new UmdFile(book);
         var4 = UmdFile.uFile;
         Intrinsics.checkNotNull(var4);
         return var4;
      }

      @NotNull
      public final synchronized ArrayList<BookChapter> getChapterList(@NotNull Book book) {
         Intrinsics.checkNotNullParameter(book, "book");
         return this.getUFile(book).getChapterList();
      }

      @Nullable
      public final synchronized String getContent(@NotNull Book book, @NotNull BookChapter chapter) {
         Intrinsics.checkNotNullParameter(book, "book");
         Intrinsics.checkNotNullParameter(chapter, "chapter");
         return this.getUFile(book).getContent(chapter);
      }

      @Nullable
      public final synchronized InputStream getImage(@NotNull Book book, @NotNull String href) {
         Intrinsics.checkNotNullParameter(book, "book");
         Intrinsics.checkNotNullParameter(href, "href");
         return this.getUFile(book).getImage(href);
      }

      public final synchronized void upBookInfo(@NotNull Book book, boolean onlyCover) {
         Intrinsics.checkNotNullParameter(book, "book");
         if (onlyCover) {
            this.getUFile(book).updateCover();
         } else {
            this.getUFile(book).upBookInfo();
         }
      }

      // $FF: synthetic method
      public static void upBookInfo$default(UmdFile.Companion var0, Book var1, boolean var2, int var3, Object var4) {
         if ((var3 & 2) != 0) {
            var2 = false;
         }

         var0.upBookInfo(var1, var2);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
