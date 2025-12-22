package io.legado.app.model.localBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.HtmlFormatter;
import io.legado.app.utils.MD5Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import me.ag2s.epublib.domain.Author;
import me.ag2s.epublib.domain.EpubBook;
import me.ag2s.epublib.domain.Resource;
import me.ag2s.epublib.domain.Resources;
import me.ag2s.epublib.domain.Spine;
import me.ag2s.epublib.domain.SpineReference;
import me.ag2s.epublib.domain.TableOfContents;
import me.ag2s.epublib.epub.EpubReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 '2\u00020\u0001:\u0001'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\b\b\u0002\u0010\u001d\u001a\u00020\u001aJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020\u0017H\u0002J\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\u0013H\u0002J\n\u0010#\u001a\u0004\u0018\u00010\tH\u0002J\b\u0010$\u001a\u00020%H\u0002J\u0006\u0010&\u001a\u00020%R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("},
   d2 = {"Lio/legado/app/model/localBook/EpubFile;", "", "book", "Lio/legado/app/data/entities/Book;", "(Lio/legado/app/data/entities/Book;)V", "getBook", "()Lio/legado/app/data/entities/Book;", "setBook", "epubBook", "Lme/ag2s/epublib/domain/EpubBook;", "getEpubBook", "()Lme/ag2s/epublib/domain/EpubBook;", "mCharset", "Ljava/nio/charset/Charset;", "getBody", "Lorg/jsoup/nodes/Element;", "res", "Lme/ag2s/epublib/domain/Resource;", "startFragmentId", "", "endFragmentId", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "getChapterListBySpinAndToc", "useTocTitle", "", "getChapterListBySpine", "getChapterListByTocAndSpin", "useSpinTitle", "getContent", "chapter", "getImage", "Ljava/io/InputStream;", "href", "readEpub", "upBookInfo", "", "updateCover", "Companion", "reader-pro"}
)
public final class EpubFile {
   @NotNull
   public static final EpubFile.Companion Companion = new EpubFile.Companion((DefaultConstructorMarker)null);
   @NotNull
   private Book book;
   @NotNull
   private Charset mCharset;
   @Nullable
   private EpubBook epubBook;
   @Nullable
   private static EpubFile eFile;

   public EpubFile(@NotNull Book book) {
      Intrinsics.checkNotNullParameter(book, "book");
      super();
      this.book = book;
      Charset var2 = Charset.defaultCharset();
      Intrinsics.checkNotNullExpressionValue(var2, "defaultCharset()");
      this.mCharset = var2;

      try {
         EpubBook var9 = this.getEpubBook();
         if (var9 != null) {
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

   private final EpubBook getEpubBook() {
      if (this.epubBook != null) {
         return this.epubBook;
      } else {
         this.epubBook = this.readEpub();
         return this.epubBook;
      }
   }

   private final EpubBook readEpub() {
      try {
         File file = this.book.getLocalFile();
         return (new EpubReader()).readEpubLazy(new ZipFile(file), "utf-8");
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   private final String getContent(BookChapter chapter) {
      if (StringsKt.contains$default((CharSequence)chapter.getUrl(), (CharSequence)"titlepage.xhtml", false, 2, (Object)null)) {
         return "<img src=\"cover.jpeg\" />";
      } else {
         EpubBook var2 = this.getEpubBook();
         if (var2 == null) {
            return null;
         } else {
            boolean var4 = false;
            boolean var5 = false;
            int var7 = false;
            String nextUrl = chapter.getVariable("nextUrl");
            String startFragmentId = chapter.getStartFragmentId();
            String endFragmentId = chapter.getEndFragmentId();
            Elements elements = new Elements();
            boolean isChapter = false;
            Iterator var13 = var2.getContents().iterator();

            while(var13.hasNext()) {
               Resource res = (Resource)var13.next();
               if (Intrinsics.areEqual(StringsKt.substringBeforeLast$default(chapter.getUrl(), "#", (String)null, 2, (Object)null), res.getHref())) {
                  Intrinsics.checkNotNullExpressionValue(res, "res");
                  elements.add(this.getBody(res, startFragmentId, endFragmentId));
                  isChapter = true;
                  if (nextUrl == null || Intrinsics.areEqual(res.getHref(), StringsKt.substringBeforeLast$default(nextUrl, "#", (String)null, 2, (Object)null))) {
                     break;
                  }
               } else if (isChapter) {
                  if (Intrinsics.areEqual(res.getHref(), nextUrl == null ? null : StringsKt.substringBeforeLast$default(nextUrl, "#", (String)null, 2, (Object)null))) {
                     break;
                  }

                  Intrinsics.checkNotNullExpressionValue(res, "res");
                  elements.add(this.getBody(res, startFragmentId, endFragmentId));
               }
            }

            String html = elements.outerHtml();
            long tag = 4L;
            if (this.getBook().getDelTag(tag)) {
               Intrinsics.checkNotNullExpressionValue(html, "html");
               CharSequence var18 = (CharSequence)html;
               String var19 = "<ruby>\\s?([\\u4e00-\\u9fa5])\\s?.*?</ruby>";
               boolean var20 = false;
               Regex var23 = new Regex(var19);
               String var24 = "$1";
               boolean var21 = false;
               html = var23.replace(var18, var24);
            }

            return HtmlFormatter.formatKeepImg$default(HtmlFormatter.INSTANCE, html, (URL)null, 2, (Object)null);
         }
      }
   }

   private final Element getBody(Resource res, String startFragmentId, String endFragmentId) {
      byte[] var5 = res.getData();
      Intrinsics.checkNotNullExpressionValue(var5, "res.data");
      Charset var6 = this.mCharset;
      boolean var7 = false;
      Element body = Jsoup.parse(new String(var5, var6)).body();
      CharSequence var11 = (CharSequence)startFragmentId;
      boolean var13 = false;
      var7 = false;
      Element var12;
      if (var11 != null && !StringsKt.isBlank(var11)) {
         var12 = body.getElementById(startFragmentId);
         if (var12 != null) {
            Elements var14 = var12.previousElementSiblings();
            if (var14 != null) {
               var14.remove();
            }
         }
      }

      var11 = (CharSequence)endFragmentId;
      var13 = false;
      var7 = false;
      if (var11 != null && !StringsKt.isBlank(var11) && !Intrinsics.areEqual(endFragmentId, startFragmentId)) {
         var12 = body.getElementById(endFragmentId);
         if (var12 != null) {
            var7 = false;
            boolean var8 = false;
            int var10 = false;
            var12.nextElementSiblings().remove();
            var12.remove();
         }
      }

      long tag = 2L;
      if (this.book.getDelTag(tag)) {
         body.getElementsByTag("h1").remove();
         body.getElementsByTag("h2").remove();
         body.getElementsByTag("h3").remove();
         body.getElementsByTag("h4").remove();
         body.getElementsByTag("h5").remove();
         body.getElementsByTag("h6").remove();
      }

      Elements children = body.children();
      children.select("script").remove();
      children.select("style").remove();
      Intrinsics.checkNotNullExpressionValue(body, "body");
      return body;
   }

   private final InputStream getImage(String href) {
      String abHref = StringsKt.replace$default(href, "../", "", false, 4, (Object)null);
      EpubBook var3 = this.getEpubBook();
      InputStream var10000;
      if (var3 == null) {
         var10000 = null;
      } else {
         Resources var4 = var3.getResources();
         if (var4 == null) {
            var10000 = null;
         } else {
            Resource var5 = var4.getByHref(abHref);
            var10000 = var5 == null ? null : var5.getInputStream();
         }
      }

      return var10000;
   }

   private final void upBookInfo() {
      if (this.getEpubBook() == null) {
         EpubFile.Companion var10000 = Companion;
         eFile = null;
         this.book.setIntro("书籍导入异常");
      } else {
         EpubBook var12 = this.getEpubBook();
         Intrinsics.checkNotNull(var12);
         me.ag2s.epublib.domain.Metadata metadata = var12.getMetadata();
         Book var13 = this.book;
         String author = metadata.getFirstTitle();
         Intrinsics.checkNotNullExpressionValue(author, "metadata.firstTitle");
         var13.setName(author);
         CharSequence var7 = (CharSequence)this.book.getName();
         boolean var3 = false;
         if (var7.length() == 0) {
            this.book.setName(StringsKt.replace$default(this.book.getOriginName(), ".epub", "", false, 4, (Object)null));
         }

         if (metadata.getAuthors().size() > 0) {
            String var8 = ((Author)metadata.getAuthors().get(0)).toString();
            Intrinsics.checkNotNullExpressionValue(var8, "metadata.authors[0].toString()");
            CharSequence var9 = (CharSequence)var8;
            String var4 = "^, |, $";
            boolean var5 = false;
            Regex var10 = new Regex(var4);
            String var11 = "";
            boolean var6 = false;
            author = var10.replace(var9, var11);
            this.book.setAuthor(author);
         }

         if (metadata.getDescriptions().size() > 0) {
            this.book.setIntro(Jsoup.parse((String)metadata.getDescriptions().get(0)).text());
         }

         this.updateCover();
      }

   }

   public final void updateCover() {
      String coverFile = Intrinsics.stringPlus(MD5Utils.INSTANCE.md5Encode16(this.book.getBookUrl()), ".jpg");
      String[] var3 = new String[]{this.book.getUserNameSpace(), "covers", coverFile};
      String relativeCoverUrl = Paths.get("assets", var3).toString();
      this.book.setCoverUrl(Intrinsics.stringPlus("/", StringsKt.replace$default(relativeCoverUrl, "\\", "/", false, 4, (Object)null)));
      String var10000 = this.book.workRoot();
      String[] var4 = new String[]{"storage", relativeCoverUrl};
      String coverUrl = Paths.get(var10000, var4).toString();
      if (!(new File(coverUrl)).exists()) {
         EpubBook var13 = this.getEpubBook();
         if (var13 != null) {
            Resource var5 = var13.getCoverImage();
            if (var5 != null) {
               byte[] var6 = var5.getData();
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

   @NotNull
   public final ArrayList<BookChapter> getChapterListBySpine() {
      ArrayList chapterList = new ArrayList();
      EpubBook var2 = this.getEpubBook();
      if (var2 != null) {
         Spine var3 = var2.getSpine();
         if (var3 != null) {
            List var4 = var3.getSpineReferences();
            if (var4 != null) {
               Iterable $this$forEachIndexed$iv = (Iterable)var4;
               int $i$f$forEachIndexed = false;
               int index$iv = 0;

               BookChapter chapter;
               for(Iterator var8 = $this$forEachIndexed$iv.iterator(); var8.hasNext(); chapterList.add(chapter)) {
                  Object item$iv = var8.next();
                  int var10 = index$iv++;
                  boolean var11 = false;
                  if (var10 < 0) {
                     CollectionsKt.throwIndexOverflow();
                  }

                  SpineReference spinResource = (SpineReference)item$iv;
                  int var14 = false;
                  Resource resource = spinResource.getResource();
                  String title = resource.getTitle();
                  CharSequence var17 = (CharSequence)title;
                  boolean var18 = false;
                  boolean var19 = false;
                  if (var17 == null || var17.length() == 0) {
                     try {
                        byte[] var26 = resource.getData();
                        Intrinsics.checkNotNullExpressionValue(var26, "resource.data");
                        Charset var29 = this.mCharset;
                        boolean var20 = false;
                        Document doc = Jsoup.parse(new String(var26, var29));
                        Elements elements = doc.getElementsByTag("title");
                        if (elements.size() > 0) {
                           title = ((Element)elements.get(0)).text();
                        }
                     } catch (IOException var21) {
                        var21.printStackTrace();
                     }
                  }

                  chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                  chapter.setIndex(var10);
                  chapter.setBookUrl(this.getBook().getBookUrl());
                  String var28 = resource.getHref();
                  Intrinsics.checkNotNullExpressionValue(var28, "resource.href");
                  chapter.setUrl(var28);
                  if (var10 == 0) {
                     Intrinsics.checkNotNullExpressionValue(title, "title");
                     CharSequence var30 = (CharSequence)title;
                     var19 = false;
                     if (var30.length() == 0) {
                        chapter.setTitle("封面");
                        continue;
                     }
                  }

                  chapter.setTitle(title == null ? "" : title);
               }
            }
         }
      }

      Book var23 = this.book;
      BookChapter var22 = (BookChapter)CollectionsKt.lastOrNull((List)chapterList);
      var23.setLatestChapterTitle(var22 == null ? null : var22.getTitle());
      this.book.setTotalChapterNum(chapterList.size());
      return chapterList;
   }

   @NotNull
   public final ArrayList<BookChapter> getChapterList() {
      ArrayList chapterList = new ArrayList();
      EpubBook var2 = this.getEpubBook();
      if (var2 != null) {
         TableOfContents var3 = var2.getTableOfContents();
         if (var3 != null) {
            List var4 = var3.getAllUniqueResources();
            if (var4 != null) {
               Iterable $this$forEachIndexed$iv = (Iterable)var4;
               int $i$f$forEachIndexed = false;
               int index$iv = 0;

               BookChapter chapter;
               for(Iterator var8 = $this$forEachIndexed$iv.iterator(); var8.hasNext(); chapterList.add(chapter)) {
                  Object item$iv = var8.next();
                  int var10 = index$iv++;
                  boolean var11 = false;
                  if (var10 < 0) {
                     CollectionsKt.throwIndexOverflow();
                  }

                  Resource resource = (Resource)item$iv;
                  int var14 = false;
                  String title = resource.getTitle();
                  CharSequence var16 = (CharSequence)title;
                  boolean var17 = false;
                  boolean var18 = false;
                  if (var16 == null || var16.length() == 0) {
                     try {
                        byte[] var25 = resource.getData();
                        Intrinsics.checkNotNullExpressionValue(var25, "resource.data");
                        Charset var28 = this.mCharset;
                        boolean var19 = false;
                        Document doc = Jsoup.parse(new String(var25, var28));
                        Elements elements = doc.getElementsByTag("title");
                        if (elements.size() > 0) {
                           title = ((Element)elements.get(0)).text();
                        }
                     } catch (IOException var20) {
                        var20.printStackTrace();
                     }
                  }

                  chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                  chapter.setIndex(var10);
                  chapter.setBookUrl(this.getBook().getBookUrl());
                  String var27 = resource.getHref();
                  Intrinsics.checkNotNullExpressionValue(var27, "resource.href");
                  chapter.setUrl(var27);
                  if (var10 == 0) {
                     Intrinsics.checkNotNullExpressionValue(title, "title");
                     CharSequence var29 = (CharSequence)title;
                     var18 = false;
                     if (var29.length() == 0) {
                        chapter.setTitle("封面");
                        continue;
                     }
                  }

                  chapter.setTitle(title == null ? "" : title);
               }
            }
         }
      }

      Book var22 = this.book;
      BookChapter var21 = (BookChapter)CollectionsKt.lastOrNull((List)chapterList);
      var22.setLatestChapterTitle(var21 == null ? null : var21.getTitle());
      this.book.setTotalChapterNum(chapterList.size());
      return chapterList;
   }

   @NotNull
   public final ArrayList<BookChapter> getChapterListBySpinAndToc(boolean useTocTitle) {
      ArrayList tocChapterList = this.getChapterList();
      ArrayList spinChapterList = this.getChapterListBySpine();
      if (spinChapterList.size() == 0) {
         return tocChapterList;
      } else if (tocChapterList.size() == 0) {
         return spinChapterList;
      } else {
         boolean var5 = false;
         Map titleMap = (Map)(new LinkedHashMap());
         int var12 = 0;
         int var6 = tocChapterList.size();
         int i;
         if (var12 < var6) {
            do {
               i = var12++;
               String var10001 = ((BookChapter)tocChapterList.get(i)).getUrl();
               Object var8 = tocChapterList.get(i);
               Intrinsics.checkNotNullExpressionValue(var8, "tocChapterList.get(i)");
               titleMap.put(var10001, var8);
            } while(var12 < var6);
         }

         var12 = 0;
         var6 = spinChapterList.size();
         if (var12 < var6) {
            do {
               i = var12++;
               Object var9 = spinChapterList.get(i);
               Intrinsics.checkNotNullExpressionValue(var9, "spinChapterList.get(i)");
               BookChapter chapter = (BookChapter)var9;
               BookChapter tocChapter = (BookChapter)titleMap.get(chapter.getUrl());
               if (tocChapter != null) {
                  CharSequence var10 = (CharSequence)tocChapter.getTitle();
                  boolean var11 = false;
                  if (var10.length() > 0) {
                     if (!useTocTitle) {
                        var10 = (CharSequence)chapter.getTitle();
                        var11 = false;
                        if (var10.length() != 0) {
                           continue;
                        }
                     }

                     chapter.setTitle(tocChapter.getTitle());
                  }
               }
            } while(var12 < var6);
         }

         Book var10000 = this.book;
         BookChapter var13 = (BookChapter)CollectionsKt.lastOrNull((List)spinChapterList);
         var10000.setLatestChapterTitle(var13 == null ? null : var13.getTitle());
         this.book.setTotalChapterNum(spinChapterList.size());
         return spinChapterList;
      }
   }

   // $FF: synthetic method
   public static ArrayList getChapterListBySpinAndToc$default(EpubFile var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      return var0.getChapterListBySpinAndToc(var1);
   }

   @NotNull
   public final ArrayList<BookChapter> getChapterListByTocAndSpin(boolean useSpinTitle) {
      ArrayList tocChapterList = this.getChapterList();
      ArrayList spinChapterList = this.getChapterListBySpine();
      if (tocChapterList.size() == 0) {
         return spinChapterList;
      } else if (spinChapterList.size() == 0) {
         return tocChapterList;
      } else {
         boolean var5 = false;
         Map titleMap = (Map)(new LinkedHashMap());
         int var12 = 0;
         int var6 = spinChapterList.size();
         int i;
         if (var12 < var6) {
            do {
               i = var12++;
               String var10001 = ((BookChapter)spinChapterList.get(i)).getUrl();
               Object var8 = spinChapterList.get(i);
               Intrinsics.checkNotNullExpressionValue(var8, "spinChapterList.get(i)");
               titleMap.put(var10001, var8);
            } while(var12 < var6);
         }

         var12 = 0;
         var6 = tocChapterList.size();
         if (var12 < var6) {
            do {
               i = var12++;
               Object var9 = tocChapterList.get(i);
               Intrinsics.checkNotNullExpressionValue(var9, "tocChapterList.get(i)");
               BookChapter chapter = (BookChapter)var9;
               BookChapter tocChapter = (BookChapter)titleMap.get(chapter.getUrl());
               if (tocChapter != null) {
                  CharSequence var10 = (CharSequence)tocChapter.getTitle();
                  boolean var11 = false;
                  if (var10.length() > 0) {
                     if (!useSpinTitle) {
                        var10 = (CharSequence)chapter.getTitle();
                        var11 = false;
                        if (var10.length() != 0) {
                           continue;
                        }
                     }

                     chapter.setTitle(tocChapter.getTitle());
                  }
               }
            } while(var12 < var6);
         }

         Book var10000 = this.book;
         BookChapter var13 = (BookChapter)CollectionsKt.lastOrNull((List)tocChapterList);
         var10000.setLatestChapterTitle(var13 == null ? null : var13.getTitle());
         this.book.setTotalChapterNum(tocChapterList.size());
         return tocChapterList;
      }
   }

   // $FF: synthetic method
   public static ArrayList getChapterListByTocAndSpin$default(EpubFile var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      return var0.getChapterListByTocAndSpin(var1);
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"},
      d2 = {"Lio/legado/app/model/localBook/EpubFile$Companion;", "", "()V", "eFile", "Lio/legado/app/model/localBook/EpubFile;", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "book", "Lio/legado/app/data/entities/Book;", "getContent", "", "chapter", "getEFile", "getImage", "Ljava/io/InputStream;", "href", "upBookInfo", "", "onlyCover", "", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      private final synchronized EpubFile getEFile(Book book) {
         EpubFile var4;
         if (EpubFile.eFile != null) {
            EpubFile var2 = EpubFile.eFile;
            String var10000;
            if (var2 == null) {
               var10000 = null;
            } else {
               Book var3 = var2.getBook();
               var10000 = var3.getBookUrl();
            }

            if (Intrinsics.areEqual(var10000, book.getBookUrl())) {
               var2 = EpubFile.eFile;
               if (var2 != null) {
                  var2.setBook(book);
               }

               var4 = EpubFile.eFile;
               Intrinsics.checkNotNull(var4);
               return var4;
            }
         }

         EpubFile.eFile = new EpubFile(book);
         var4 = EpubFile.eFile;
         Intrinsics.checkNotNull(var4);
         return var4;
      }

      @NotNull
      public final synchronized ArrayList<BookChapter> getChapterList(@NotNull Book book) {
         Intrinsics.checkNotNullParameter(book, "book");
         CharSequence var2 = (CharSequence)book.getTocUrl();
         boolean var3 = false;
         if (var2.length() == 0) {
            book.setTocUrl("spin+toc");
         }

         EpubFile epubFile = this.getEFile(book);
         String var5 = book.getTocUrl();
         ArrayList var10000;
         switch(var5.hashCode()) {
         case -2010033025:
            if (var5.equals("spin+toc")) {
               EpubFileKt.access$getLogger$p().info("epubFile.getChapterListBySpinAndToc");
               var10000 = EpubFile.getChapterListBySpinAndToc$default(epubFile, false, 1, (Object)null);
               return var10000;
            }
            break;
         case -2009526578:
            if (var5.equals("spin<toc")) {
               EpubFileKt.access$getLogger$p().info("epubFile.getChapterListBySpinAndToc true");
               var10000 = epubFile.getChapterListBySpinAndToc(true);
               return var10000;
            }
            break;
         case -1386236251:
            if (var5.equals("toc+spin")) {
               EpubFileKt.access$getLogger$p().info("epubFile.getChapterListByTocAndSpin");
               var10000 = EpubFile.getChapterListByTocAndSpin$default(epubFile, false, 1, (Object)null);
               return var10000;
            }
            break;
         case -1370536394:
            if (var5.equals("toc<spin")) {
               EpubFileKt.access$getLogger$p().info("epubFile.getChapterListByTocAndSpin true");
               var10000 = epubFile.getChapterListByTocAndSpin(true);
               return var10000;
            }
            break;
         case 115016:
            if (var5.equals("toc")) {
               EpubFileKt.access$getLogger$p().info("epubFile.getChapterList");
               var10000 = epubFile.getChapterList();
               return var10000;
            }
            break;
         case 3536962:
            if (var5.equals("spin")) {
               EpubFileKt.access$getLogger$p().info("epubFile.getChapterListBySpine");
               var10000 = epubFile.getChapterListBySpine();
               return var10000;
            }
         }

         EpubFileKt.access$getLogger$p().info("epubFile.getChapterListBySpinAndToc");
         var10000 = EpubFile.getChapterListBySpinAndToc$default(epubFile, false, 1, (Object)null);
         return var10000;
      }

      @Nullable
      public final synchronized String getContent(@NotNull Book book, @NotNull BookChapter chapter) {
         Intrinsics.checkNotNullParameter(book, "book");
         Intrinsics.checkNotNullParameter(chapter, "chapter");
         return this.getEFile(book).getContent(chapter);
      }

      @Nullable
      public final synchronized InputStream getImage(@NotNull Book book, @NotNull String href) {
         Intrinsics.checkNotNullParameter(book, "book");
         Intrinsics.checkNotNullParameter(href, "href");
         return this.getEFile(book).getImage(href);
      }

      public final synchronized void upBookInfo(@NotNull Book book, boolean onlyCover) {
         Intrinsics.checkNotNullParameter(book, "book");
         if (onlyCover) {
            this.getEFile(book).updateCover();
         } else {
            this.getEFile(book).upBookInfo();
         }
      }

      // $FF: synthetic method
      public static void upBookInfo$default(EpubFile.Companion var0, Book var1, boolean var2, int var3, Object var4) {
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
