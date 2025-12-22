package io.legado.app.model.localBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.MD5Utils;
import io.legado.app.utils.XmlUtils;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J$\u0010\u001a\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006 "},
   d2 = {"Lio/legado/app/model/localBook/CbzFile;", "", "book", "Lio/legado/app/data/entities/Book;", "(Lio/legado/app/data/entities/Book;)V", "getBook", "()Lio/legado/app/data/entities/Book;", "setBook", "cover", "Ljava/io/InputStream;", "getCover", "()Ljava/io/InputStream;", "setCover", "(Ljava/io/InputStream;)V", "info", "", "", "getInfo", "()Ljava/util/Map;", "setInfo", "(Ljava/util/Map;)V", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getContent", "chapter", "parseBookInfo", "Lkotlin/Pair;", "upBookInfo", "", "updateCover", "Companion", "reader-pro"}
)
public final class CbzFile {
   @NotNull
   public static final CbzFile.Companion Companion = new CbzFile.Companion((DefaultConstructorMarker)null);
   @NotNull
   private Book book;
   @Nullable
   private Map<String, Object> info;
   @Nullable
   private InputStream cover;
   @Nullable
   private static CbzFile cFile;

   public CbzFile(@NotNull Book book) {
      Intrinsics.checkNotNullParameter(book, "book");
      super();
      this.book = book;
   }

   @NotNull
   public final Book getBook() {
      return this.book;
   }

   public final void setBook(@NotNull Book <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.book = var1;
   }

   @Nullable
   public final Map<String, Object> getInfo() {
      return this.info;
   }

   public final void setInfo(@Nullable Map<String, Object> <set-?>) {
      this.info = var1;
   }

   @Nullable
   public final InputStream getCover() {
      return this.cover;
   }

   public final void setCover(@Nullable InputStream <set-?>) {
      this.cover = var1;
   }

   private final Pair<Map<String, Object>, InputStream> parseBookInfo() {
      if (this.cover == null && this.info == null) {
         ZipFile zf = new ZipFile(this.book.getLocalFile());
         Enumeration entries = zf.entries();
         String[] var4 = new String[]{"jpg", "jpeg", "gif", "png", "bmp", "webp", "svg"};
         List imageExt = CollectionsKt.listOf(var4);

         while(true) {
            if (entries.hasMoreElements()) {
               Object var5 = entries.nextElement();
               if (var5 == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
               }

               ZipEntry zipEntry = (ZipEntry)var5;
               if (!zipEntry.isDirectory()) {
                  String name = zipEntry.getName();
                  if (name.equals("ComicInfo.xml")) {
                     InputStream inputStream = zf.getInputStream(zipEntry);
                     XmlUtils var10001 = XmlUtils.INSTANCE;
                     Intrinsics.checkNotNullExpressionValue(inputStream, "inputStream");
                     this.info = var10001.xml2map(inputStream);
                  } else if (this.cover == null) {
                     FileUtils var10000 = FileUtils.INSTANCE;
                     Intrinsics.checkNotNullExpressionValue(name, "name");
                     String var7 = FileUtils.getFileExtetion$default(var10000, name, (String)null, 2, (Object)null);
                     boolean var8 = false;
                     if (var7 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                     }

                     String var11 = var7.toLowerCase(Locale.ROOT);
                     Intrinsics.checkNotNullExpressionValue(var11, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
                     String ext = var11;
                     if (imageExt.contains(ext)) {
                        this.cover = zf.getInputStream(zipEntry);
                     }
                  }
               }

               if (this.cover == null || this.info == null) {
                  continue;
               }
            }

            return new Pair(this.info, this.cover);
         }
      } else {
         return new Pair(this.info, this.cover);
      }
   }

   private final void upBookInfo() {
      Pair result = this.parseBookInfo();
      if (result.getFirst() != null) {
         Object var3 = result.getFirst();
         if (var3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
         }

         Map bookInfo = (Map)var3;
         Map var4 = (Map)bookInfo.get("ComicInfo");
         Map info = var4 == null ? null : var4;
         Object var7 = info == null ? null : info.get("Title");
         this.book.setName((String)(var7 == null ? this.book.getName() : var7));
         var7 = info == null ? null : info.get("Writer");
         this.book.setAuthor((String)(var7 == null ? this.book.getAuthor() : var7));
      }

      this.updateCover();
   }

   private final void updateCover() {
      String coverFile = Intrinsics.stringPlus(MD5Utils.INSTANCE.md5Encode16(this.book.getBookUrl()), ".jpg");
      String[] var3 = new String[]{this.book.getUserNameSpace(), "covers", coverFile};
      String relativeCoverUrl = Paths.get("assets", var3).toString();
      this.book.setCoverUrl(Intrinsics.stringPlus("/", StringsKt.replace$default(relativeCoverUrl, "\\", "/", false, 4, (Object)null)));
      String var10000 = this.book.workRoot();
      String[] var4 = new String[]{"storage", relativeCoverUrl};
      String coverUrl = Paths.get(var10000, var4).toString();
      if (!(new File(coverUrl)).exists()) {
         Pair result = this.parseBookInfo();
         if (result.getSecond() != null) {
            Object var6 = result.getSecond();
            if (var6 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.io.InputStream");
            }

            InputStream coverStream = (InputStream)var6;
            FileUtils.INSTANCE.writeInputStream(coverUrl, coverStream);
         }
      }

   }

   private final String getContent(BookChapter chapter) {
      return "";
   }

   private final ArrayList<BookChapter> getChapterList() {
      ArrayList chapterList = new ArrayList();
      ZipFile zf = new ZipFile(this.book.getLocalFile());
      Enumeration entries = zf.entries();
      boolean var5 = false;
      ArrayList imageFileList = new ArrayList();

      while(entries.hasMoreElements()) {
         Object var6 = entries.nextElement();
         if (var6 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
         }

         ZipEntry zipEntry = (ZipEntry)var6;
         if (!zipEntry.isDirectory()) {
            String name = zipEntry.getName();
            Intrinsics.checkNotNullExpressionValue(name, "name");
            if (!StringsKt.endsWith$default(name, ".xml", false, 2, (Object)null)) {
               imageFileList.add(name);
            }
         }
      }

      CollectionsKt.sort((List)imageFileList);
      int var11 = 0;
      int var14 = imageFileList.size();
      if (var11 < var14) {
         do {
            int i = var11++;
            Object var9 = imageFileList.get(i);
            Intrinsics.checkNotNullExpressionValue(var9, "imageFileList.get(i)");
            String name = (String)var9;
            BookChapter chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
            chapter.setTitle(name);
            chapter.setIndex(i);
            chapter.setBookUrl(this.book.getBookUrl());
            chapter.setUrl(name);
            chapterList.add(chapter);
         } while(var11 < var14);
      }

      Book var10000 = this.book;
      BookChapter var12 = (BookChapter)CollectionsKt.lastOrNull((List)chapterList);
      var10000.setLatestChapterTitle(var12 == null ? null : var12.getTitle());
      this.book.setTotalChapterNum(chapterList.size());
      return chapterList;
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\nJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"},
      d2 = {"Lio/legado/app/model/localBook/CbzFile$Companion;", "", "()V", "cFile", "Lio/legado/app/model/localBook/CbzFile;", "getCbzFile", "book", "Lio/legado/app/data/entities/Book;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getContent", "", "chapter", "upBookInfo", "", "onlyCover", "", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      private final synchronized CbzFile getCbzFile(Book book) {
         CbzFile var4;
         if (CbzFile.cFile != null) {
            CbzFile var2 = CbzFile.cFile;
            String var10000;
            if (var2 == null) {
               var10000 = null;
            } else {
               Book var3 = var2.getBook();
               var10000 = var3.getBookUrl();
            }

            if (Intrinsics.areEqual(var10000, book.getBookUrl())) {
               var2 = CbzFile.cFile;
               if (var2 != null) {
                  var2.setBook(book);
               }

               var4 = CbzFile.cFile;
               Intrinsics.checkNotNull(var4);
               return var4;
            }
         }

         CbzFile.cFile = new CbzFile(book);
         var4 = CbzFile.cFile;
         Intrinsics.checkNotNull(var4);
         return var4;
      }

      @NotNull
      public final synchronized ArrayList<BookChapter> getChapterList(@NotNull Book book) {
         Intrinsics.checkNotNullParameter(book, "book");
         return this.getCbzFile(book).getChapterList();
      }

      @Nullable
      public final synchronized String getContent(@NotNull Book book, @NotNull BookChapter chapter) {
         Intrinsics.checkNotNullParameter(book, "book");
         Intrinsics.checkNotNullParameter(chapter, "chapter");
         return this.getCbzFile(book).getContent(chapter);
      }

      public final synchronized void upBookInfo(@NotNull Book book, boolean onlyCover) {
         Intrinsics.checkNotNullParameter(book, "book");
         if (onlyCover) {
            this.getCbzFile(book).updateCover();
         } else {
            this.getCbzFile(book).upBookInfo();
         }
      }

      // $FF: synthetic method
      public static void upBookInfo$default(CbzFile.Companion var0, Book var1, boolean var2, int var3, Object var4) {
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
