package io.legado.app.model.localBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0001'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001b\u001a\u00020\u0017H\u0002J$\u0010\u001c\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001dH\u0002J&\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\b\u0010&\u001a\u00020\u001fH\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006("},
   d2 = {"Lio/legado/app/model/localBook/PdfFile;", "", "book", "Lio/legado/app/data/entities/Book;", "(Lio/legado/app/data/entities/Book;)V", "getBook", "()Lio/legado/app/data/entities/Book;", "setBook", "cover", "Ljava/io/InputStream;", "getCover", "()Ljava/io/InputStream;", "setCover", "(Ljava/io/InputStream;)V", "info", "", "", "getInfo", "()Ljava/util/Map;", "setInfo", "(Ljava/util/Map;)V", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getChapterListByOutline", "getChapterListByPage", "getContent", "chapter", "parseBookInfo", "Lkotlin/Pair;", "processOutline", "", "document", "Lorg/apache/pdfbox/pdmodel/PDDocument;", "chapterList", "outline", "Lorg/apache/pdfbox/pdmodel/interactive/documentnavigation/outline/PDOutlineNode;", "upBookInfo", "updateCover", "Companion", "reader-pro"}
)
public final class PdfFile {
   @NotNull
   public static final PdfFile.Companion Companion = new PdfFile.Companion((DefaultConstructorMarker)null);
   @NotNull
   private Book book;
   @Nullable
   private Map<String, Object> info;
   @Nullable
   private InputStream cover;
   @Nullable
   private static PdfFile cFile;

   public PdfFile(@NotNull Book book) {
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
      return new Pair(this.info, this.cover);
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
   }

   private final String getContent(BookChapter chapter) {
      return "";
   }

   private final ArrayList<BookChapter> getChapterList() {
      CharSequence var1 = (CharSequence)this.book.getTocUrl();
      boolean var2 = false;
      if (var1.length() == 0) {
         this.book.setTocUrl("page");
      }

      return Intrinsics.areEqual(this.book.getTocUrl(), "page") ? this.getChapterListByPage() : this.getChapterListByOutline();
   }

   private final ArrayList<BookChapter> getChapterListByPage() {
      ArrayList chapterList = new ArrayList();
      PDDocument document = PDDocument.load(this.book.getLocalFile());
      int var3 = 0;
      int var4 = document.getNumberOfPages();
      if (var3 < var4) {
         do {
            int pageIndex = var3++;
            String name = "output-" + pageIndex + ".png";
            BookChapter chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
            chapter.setTitle(name);
            chapter.setIndex(pageIndex);
            chapter.setBookUrl(this.book.getBookUrl());
            chapter.setUrl(name);
            chapter.setStart((long)pageIndex);
            chapter.setEnd((long)pageIndex);
            chapterList.add(chapter);
         } while(var3 < var4);
      }

      Book var10000 = this.book;
      BookChapter var8 = (BookChapter)CollectionsKt.lastOrNull((List)chapterList);
      var10000.setLatestChapterTitle(var8 == null ? null : var8.getTitle());
      this.book.setTotalChapterNum(chapterList.size());
      Intrinsics.checkNotNullExpressionValue(document, "document");
      Util.closeQuietly((Closeable)document);
      return chapterList;
   }

   private final ArrayList<BookChapter> getChapterListByOutline() {
      ArrayList chapterList = new ArrayList();
      PDDocument document = PDDocument.load(this.book.getLocalFile());
      PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
      if (outline == null) {
         return chapterList;
      } else {
         Intrinsics.checkNotNullExpressionValue(document, "document");
         this.processOutline(document, chapterList, (PDOutlineNode)outline);
         if (chapterList.size() > 0) {
            ((BookChapter)chapterList.get(chapterList.size() - 1)).setEnd((long)document.getNumberOfPages());
         }

         Util.closeQuietly((Closeable)document);
         return chapterList;
      }
   }

   private final void processOutline(PDDocument document, ArrayList<BookChapter> chapterList, PDOutlineNode outline) {
      PDOutlineItem current = outline.getFirstChild();

      while(true) {
         while(current != null) {
            PDPage page = current.findDestinationPage(document);
            int pageIndex = document.getDocumentCatalog().getPages().indexOf(page);
            BookChapter chapter;
            if (chapterList.size() == 0 && pageIndex >= 1) {
               chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
               chapter.setTitle("首章");
               chapter.setIndex(0);
               chapter.setBookUrl(this.book.getBookUrl());
               chapter.setUrl("chapter-0");
               chapter.setStart(0L);
               chapter.setEnd((long)pageIndex);
               chapterList.add(chapter);
            }

            if (chapterList.size() > 0) {
               Long var10000 = ((BookChapter)chapterList.get(chapterList.size() - 1)).getStart();
               long var9 = (long)pageIndex;
               if (var10000 != null) {
                  if (var10000 == var9) {
                     current = current.getNextSibling();
                     continue;
                  }
               }

               chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
               String var8 = current.getTitle();
               Intrinsics.checkNotNullExpressionValue(var8, "current.getTitle()");
               chapter.setTitle(var8);
               chapter.setIndex(chapterList.size());
               chapter.setBookUrl(this.book.getBookUrl());
               chapter.setUrl(Intrinsics.stringPlus("chapter-", chapterList.size()));
               chapter.setStart((long)pageIndex);
               ((BookChapter)chapterList.get(chapterList.size() - 1)).setEnd((long)pageIndex - 1L);
               chapterList.add(chapter);
            }

            if (current.hasChildren()) {
               this.processOutline(document, chapterList, (PDOutlineNode)current);
            }

            current = current.getNextSibling();
         }

         return;
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u0011R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"},
      d2 = {"Lio/legado/app/model/localBook/PdfFile$Companion;", "", "()V", "cFile", "Lio/legado/app/model/localBook/PdfFile;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "book", "Lio/legado/app/data/entities/Book;", "getContent", "", "chapter", "getPdfFile", "upBookInfo", "", "onlyCover", "", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      private final synchronized PdfFile getPdfFile(Book book) {
         PdfFile var4;
         if (PdfFile.cFile != null) {
            PdfFile var2 = PdfFile.cFile;
            String var10000;
            if (var2 == null) {
               var10000 = null;
            } else {
               Book var3 = var2.getBook();
               var10000 = var3.getBookUrl();
            }

            if (Intrinsics.areEqual(var10000, book.getBookUrl())) {
               var2 = PdfFile.cFile;
               if (var2 != null) {
                  var2.setBook(book);
               }

               var4 = PdfFile.cFile;
               Intrinsics.checkNotNull(var4);
               return var4;
            }
         }

         PdfFile.cFile = new PdfFile(book);
         var4 = PdfFile.cFile;
         Intrinsics.checkNotNull(var4);
         return var4;
      }

      @NotNull
      public final synchronized ArrayList<BookChapter> getChapterList(@NotNull Book book) {
         Intrinsics.checkNotNullParameter(book, "book");
         return this.getPdfFile(book).getChapterList();
      }

      @Nullable
      public final synchronized String getContent(@NotNull Book book, @NotNull BookChapter chapter) {
         Intrinsics.checkNotNullParameter(book, "book");
         Intrinsics.checkNotNullParameter(chapter, "chapter");
         return this.getPdfFile(book).getContent(chapter);
      }

      public final synchronized void upBookInfo(@NotNull Book book, boolean onlyCover) {
         Intrinsics.checkNotNullParameter(book, "book");
         if (onlyCover) {
            this.getPdfFile(book).updateCover();
         } else {
            this.getPdfFile(book).upBookInfo();
         }
      }

      // $FF: synthetic method
      public static void upBookInfo$default(PdfFile.Companion var0, Book var1, boolean var2, int var3, Object var4) {
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
