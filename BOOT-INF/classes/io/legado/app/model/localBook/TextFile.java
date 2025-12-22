package io.legado.app.model.localBook;

import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.TxtTocRule;
import io.legado.app.help.DefaultData;
import io.legado.app.utils.EncodingDetect;
import io.legado.app.utils.MD5Utils;
import io.legado.app.utils.StringUtils;
import io.legado.app.utils.Utf8BomUtils;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J,\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0002J\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001e"},
   d2 = {"Lio/legado/app/model/localBook/TextFile;", "", "book", "Lio/legado/app/data/entities/Book;", "(Lio/legado/app/data/entities/Book;)V", "blank", "", "bufferSize", "", "charset", "Ljava/nio/charset/Charset;", "maxLengthWithNoToc", "maxLengthWithToc", "analyze", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "Lkotlin/collections/ArrayList;", "pattern", "Ljava/util/regex/Pattern;", "fileStart", "", "fileEnd", "getChapterList", "getTocRule", "content", "", "getTocRules", "", "Lio/legado/app/data/entities/TxtTocRule;", "Companion", "reader-pro"}
)
public final class TextFile {
   @NotNull
   public static final TextFile.Companion Companion = new TextFile.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Book book;
   private final byte blank;
   private final int bufferSize;
   private final int maxLengthWithNoToc;
   private final int maxLengthWithToc;
   @NotNull
   private Charset charset;

   public TextFile(@NotNull Book book) {
      Intrinsics.checkNotNullParameter(book, "book");
      super();
      this.book = book;
      this.blank = 10;
      this.bufferSize = 512000;
      this.maxLengthWithNoToc = 10240;
      this.maxLengthWithToc = 102400;
      this.charset = this.book.fileCharset();
   }

   @NotNull
   public final ArrayList<BookChapter> getChapterList() throws FileNotFoundException {
      boolean $i$f$forEachIndexed;
      int length;
      boolean var11;
      if (this.book.getCharset() == null || StringsKt.isBlank((CharSequence)this.book.getTocUrl())) {
         Closeable var1 = (Closeable)LocalBook.INSTANCE.getBookInputStream(this.book);
         boolean var2 = false;
         $i$f$forEachIndexed = false;
         Throwable var19 = (Throwable)null;

         try {
            InputStream bis = (InputStream)var1;
            int var5 = false;
            byte[] buffer = new byte[this.bufferSize];
            length = bis.read(buffer);
            CharSequence var8 = (CharSequence)this.book.getCharset();
            boolean var9 = false;
            boolean var10 = false;
            Book var10000;
            if (var8 == null || StringsKt.isBlank(var8)) {
               var10000 = this.book;
               EncodingDetect var10001 = EncodingDetect.INSTANCE;
               var9 = false;
               byte[] var10002 = Arrays.copyOf(buffer, length);
               Intrinsics.checkNotNullExpressionValue(var10002, "java.util.Arrays.copyOf(this, newSize)");
               var10000.setCharset(var10001.getEncode(var10002));
            }

            this.charset = this.book.fileCharset();
            if (StringsKt.isBlank((CharSequence)this.book.getTocUrl())) {
               byte var28 = 0;
               Charset var31 = this.charset;
               var11 = false;
               String blockContent = new String(buffer, var28, length, var31);
               var10000 = this.book;
               Pattern var29 = this.getTocRule(blockContent);
               String var34;
               if (var29 == null) {
                  var34 = "";
               } else {
                  String var32 = var29.pattern();
                  var34 = var32 == null ? "" : var32;
               }

               var10000.setTocUrl(var34);
            }

            Unit var21 = Unit.INSTANCE;
         } catch (Throwable var14) {
            var19 = var14;
            throw var14;
         } finally {
            CloseableKt.closeFinally(var1, var19);
         }
      }

      String var17 = this.book.getTocUrl();
      byte var20 = 8;
      boolean var22 = false;
      Pattern var35 = Pattern.compile(var17, var20);
      Intrinsics.checkNotNullExpressionValue(var35, "java.util.regex.Pattern.compile(this, flags)");
      ArrayList toc = this.analyze(var35);
      Iterable $this$forEachIndexed$iv = (Iterable)toc;
      $i$f$forEachIndexed = false;
      int index$iv = 0;
      Iterator var24 = $this$forEachIndexed$iv.iterator();

      while(var24.hasNext()) {
         Object item$iv = var24.next();
         length = index$iv++;
         boolean var27 = false;
         if (length < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         BookChapter bookChapter = (BookChapter)item$iv;
         var11 = false;
         bookChapter.setIndex(length);
         bookChapter.setBookUrl(this.book.getBookUrl());
         bookChapter.setUrl(MD5Utils.INSTANCE.md5Encode16(this.book.getOriginName() + length + bookChapter.getTitle()));
      }

      this.book.setLatestChapterTitle(((BookChapter)CollectionsKt.last((List)toc)).getTitle());
      this.book.setTotalChapterNum(toc.size());
      return toc;
   }

   private final ArrayList<BookChapter> analyze(Pattern pattern) {
      CharSequence var2 = (CharSequence)(pattern == null ? null : pattern.pattern());
      boolean var3 = false;
      boolean var4 = false;
      if (var2 == null || var2.length() == 0) {
         return analyze$default(this, 0L, 0L, 3, (Object)null);
      } else if (pattern == null) {
         return analyze$default(this, 0L, 0L, 3, (Object)null);
      } else {
         var3 = false;
         ArrayList toc = new ArrayList();
         Closeable var48 = (Closeable)LocalBook.INSTANCE.getBookInputStream(this.book);
         var4 = false;
         boolean var5 = false;
         Throwable var49 = (Throwable)null;

         try {
            InputStream bis = (InputStream)var48;
            int var7 = false;
            String blockContent = null;
            long curOffset = 0L;
            int length = false;
            byte[] buffer = new byte[this.bufferSize];
            int bufferStart = 3;
            bis.read(buffer, 0, 3);
            if (Utf8BomUtils.INSTANCE.hasBom(buffer)) {
               bufferStart = 0;
               curOffset = 3L;
            }

            while(true) {
               int end = bis.read(buffer, bufferStart, this.bufferSize - bufferStart);
               boolean var15 = false;
               boolean var16 = false;
               int var18 = false;
               int length = end;
               Unit var10000 = Unit.INSTANCE;
               if (end <= 0) {
                  Unit var50 = Unit.INSTANCE;
                  break;
               }

               end += bufferStart;
               int seekPos;
               if (end == this.bufferSize) {
                  seekPos = bufferStart + length - 1;
                  if (0 <= seekPos) {
                     do {
                        int i = seekPos--;
                        if (buffer[i] == this.blank) {
                           end = i;
                           break;
                        }
                     } while(0 <= seekPos);
                  }
               }

               byte var53 = 0;
               Charset var55 = this.charset;
               boolean var17 = false;
               blockContent = new String(buffer, var53, end, var55);
               ArraysKt.copyInto(buffer, buffer, 0, end, bufferStart + length);
               bufferStart = bufferStart + length - end;
               seekPos = 0;
               Matcher var57 = pattern.matcher((CharSequence)blockContent);
               Intrinsics.checkNotNullExpressionValue(var57, "pattern.matcher(blockContent)");

               String chapterContent;
               for(Matcher matcher = var57; matcher.find(); seekPos += chapterContent.length()) {
                  int chapterStart = matcher.start();
                  boolean var20 = false;
                  String var76 = blockContent.substring(seekPos, chapterStart);
                  Intrinsics.checkNotNullExpressionValue(var76, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                  chapterContent = var76;
                  Charset var21 = this.charset;
                  boolean var22 = false;
                  if (chapterContent == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  byte[] var77 = chapterContent.getBytes(var21);
                  Intrinsics.checkNotNullExpressionValue(var77, "(this as java.lang.String).getBytes(charset)");
                  int chapterLength = var77.length;
                  BookChapter lastChapter = (BookChapter)CollectionsKt.lastOrNull((List)toc);
                  long var78;
                  if (lastChapter == null) {
                     var78 = curOffset;
                  } else {
                     Long var23 = lastChapter.getStart();
                     var78 = var23 == null ? curOffset : var23;
                  }

                  long lastStart = var78;
                  boolean var27;
                  BookChapter curChapter;
                  if (this.book.getSplitLongChapter() && curOffset + (long)chapterLength - lastStart > (long)this.maxLengthWithToc) {
                     lastChapter = (BookChapter)CollectionsKt.lastOrNull((List)toc);
                     boolean var29;
                     if (lastChapter == null) {
                        var10000 = null;
                     } else {
                        boolean var70 = false;
                        var27 = false;
                        var29 = false;
                        lastChapter.setEnd(lastChapter.getStart());
                        var10000 = Unit.INSTANCE;
                        var10000 = Unit.INSTANCE;
                     }

                     curChapter = (BookChapter)CollectionsKt.lastOrNull((List)toc);
                     String lastTitle = curChapter == null ? null : curChapter.getTitle();
                     boolean var30;
                     int var80;
                     if (lastTitle == null) {
                        var80 = 0;
                     } else {
                        Charset var75 = this.charset;
                        var30 = false;
                        var77 = lastTitle.getBytes(var75);
                        Intrinsics.checkNotNullExpressionValue(var77, "(this as java.lang.String).getBytes(charset)");
                        byte[] var72 = var77;
                        if (var72 == null) {
                           var80 = 0;
                        } else {
                           int var28 = var72.length;
                           var80 = var28;
                        }
                     }

                     int lastTitleLength = var80;
                     ArrayList chapters = this.analyze(lastStart + (long)lastTitleLength, curOffset + (long)chapterLength);
                     if (lastTitle == null) {
                        var10000 = null;
                     } else {
                        var29 = false;
                        var30 = false;
                        int var32 = false;
                        Iterable $this$forEachIndexed$iv = (Iterable)chapters;
                        int $i$f$forEachIndexed = false;
                        int index$iv = 0;
                        Iterator var36 = $this$forEachIndexed$iv.iterator();

                        while(var36.hasNext()) {
                           Object item$iv = var36.next();
                           int var38 = index$iv++;
                           boolean var39 = false;
                           if (var38 < 0) {
                              CollectionsKt.throwIndexOverflow();
                           }

                           BookChapter bookChapter = (BookChapter)item$iv;
                           int var42 = false;
                           bookChapter.setTitle(lastTitle + '(' + (var38 + 1) + ')');
                        }

                        var10000 = Unit.INSTANCE;
                        var10000 = Unit.INSTANCE;
                     }

                     toc.addAll((Collection)chapters);
                     BookChapter curChapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                     String var74 = matcher.group();
                     Intrinsics.checkNotNullExpressionValue(var74, "matcher.group()");
                     curChapter.setTitle(var74);
                     curChapter.setStart(curOffset + (long)chapterLength);
                     toc.add(curChapter);
                  } else {
                     Long var10001;
                     boolean var63;
                     String var66;
                     String var69;
                     if (seekPos == 0 && chapterStart != 0) {
                        if (toc.isEmpty()) {
                           CharSequence var64 = (CharSequence)StringUtils.INSTANCE.trim(chapterContent);
                           var63 = false;
                           if (var64.length() > 0) {
                              lastChapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                              lastChapter.setTitle("前言");
                              lastChapter.setStart(curOffset);
                              lastChapter.setEnd((long)chapterLength);
                              toc.add(lastChapter);
                           }

                           lastChapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                           var66 = matcher.group();
                           Intrinsics.checkNotNullExpressionValue(var66, "matcher.group()");
                           lastChapter.setTitle(var66);
                           lastChapter.setStart((long)chapterLength);
                           toc.add(lastChapter);
                        } else {
                           lastChapter = (BookChapter)CollectionsKt.last((List)toc);
                           lastChapter.setVolume(StringsKt.isBlank((CharSequence)StringsKt.substringAfter$default(chapterContent, lastChapter.getTitle(), (String)null, 2, (Object)null)));
                           var10001 = lastChapter.getEnd();
                           Intrinsics.checkNotNull(var10001);
                           lastChapter.setEnd(var10001 + (long)chapterLength);
                           curChapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                           var69 = matcher.group();
                           Intrinsics.checkNotNullExpressionValue(var69, "matcher.group()");
                           curChapter.setTitle(var69);
                           curChapter.setStart(lastChapter.getEnd());
                           toc.add(curChapter);
                        }
                     } else {
                        Collection var62 = (Collection)toc;
                        var63 = false;
                        if (!var62.isEmpty()) {
                           lastChapter = (BookChapter)CollectionsKt.last((List)toc);
                           lastChapter.setVolume(StringsKt.isBlank((CharSequence)StringsKt.substringAfter$default(chapterContent, lastChapter.getTitle(), (String)null, 2, (Object)null)));
                           var10001 = lastChapter.getStart();
                           Intrinsics.checkNotNull(var10001);
                           long var79 = var10001;
                           Charset var26 = this.charset;
                           var27 = false;
                           if (chapterContent == null) {
                              throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                           }

                           byte[] var10002 = chapterContent.getBytes(var26);
                           Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.String).getBytes(charset)");
                           lastChapter.setEnd(var79 + (long)var10002.length);
                           curChapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                           var69 = matcher.group();
                           Intrinsics.checkNotNullExpressionValue(var69, "matcher.group()");
                           curChapter.setTitle(var69);
                           curChapter.setStart(lastChapter.getEnd());
                           toc.add(curChapter);
                        } else {
                           lastChapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                           var66 = matcher.group();
                           Intrinsics.checkNotNullExpressionValue(var66, "matcher.group()");
                           lastChapter.setTitle(var66);
                           lastChapter.setStart(curOffset);
                           lastChapter.setEnd(curOffset);
                           toc.add(lastChapter);
                        }
                     }
                  }
               }

               curOffset += (long)end;
               BookChapter var59 = (BookChapter)CollectionsKt.lastOrNull((List)toc);
               if (var59 != null) {
                  var59.setEnd(curOffset);
               }
            }
         } catch (Throwable var45) {
            var49 = var45;
            throw var45;
         } finally {
            CloseableKt.closeFinally(var48, var49);
         }

         System.gc();
         System.runFinalization();
         return toc;
      }
   }

   private final ArrayList<BookChapter> analyze(long fileStart, long fileEnd) {
      boolean var6 = false;
      ArrayList toc = new ArrayList();
      Closeable var32 = (Closeable)LocalBook.INSTANCE.getBookInputStream(this.book);
      boolean var7 = false;
      boolean var8 = false;
      Throwable var33 = (Throwable)null;

      try {
         InputStream bis = (InputStream)var32;
         int var10 = false;
         int blockPos = 0;
         long curOffset = 0L;
         int chapterPos = 0;
         int length = false;
         byte[] buffer = new byte[this.bufferSize];
         int bufferStart = false;
         int bufferStart = 3;
         if (fileStart == 0L) {
            bis.read(buffer, 0, 3);
            if (Utf8BomUtils.INSTANCE.hasBom(buffer)) {
               bufferStart = 0;
               curOffset = 3L;
            }
         } else {
            bis.skip(fileStart);
            curOffset = fileStart;
            bufferStart = 0;
         }

         Long var10001;
         boolean var25;
         int length;
         label171:
         for(; fileEnd - curOffset - (long)bufferStart > 0L; curOffset += (long)length) {
            long var18 = (long)(this.bufferSize - bufferStart);
            long var20 = fileEnd - curOffset - (long)bufferStart;
            boolean var22 = false;
            int chapterOffset = bis.read(buffer, bufferStart, (int)Math.min(var18, var20));
            boolean var24 = false;
            var25 = false;
            var22 = false;
            length = chapterOffset;
            if (chapterOffset <= 0) {
               break;
            }

            ++blockPos;
            chapterOffset = 0;
            length += bufferStart;
            int strLength = length;
            chapterPos = 0;

            while(true) {
               while(true) {
                  if (strLength <= 0) {
                     continue label171;
                  }

                  ++chapterPos;
                  if (strLength > this.maxLengthWithNoToc) {
                     int end = length;
                     int var26 = chapterOffset + this.maxLengthWithNoToc;
                     if (var26 < length) {
                        do {
                           int i = var26++;
                           if (buffer[i] == this.blank) {
                              end = i;
                              break;
                           }
                        } while(var26 < length);
                     }

                     BookChapter chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
                     chapter.setTitle("" + '第' + blockPos + "章(" + chapterPos + ')');
                     BookChapter var37 = (BookChapter)CollectionsKt.lastOrNull((List)toc);
                     if (var37 == null) {
                        var10001 = curOffset;
                     } else {
                        Long var27 = var37.getEnd();
                        var10001 = var27 == null ? curOffset : var27;
                     }

                     chapter.setStart(var10001);
                     var10001 = chapter.getStart();
                     Intrinsics.checkNotNull(var10001);
                     chapter.setEnd(var10001 + (long)end - (long)chapterOffset);
                     toc.add(chapter);
                     strLength -= end - chapterOffset;
                     chapterOffset = end;
                  } else {
                     ArraysKt.copyInto(buffer, buffer, 0, length - strLength, length);
                     length -= strLength;
                     bufferStart = strLength;
                     strLength = 0;
                  }
               }
            }
         }

         BookChapter chapter;
         if (bufferStart <= 100 && !toc.isEmpty()) {
            chapter = (BookChapter)CollectionsKt.lastOrNull((List)toc);
            Unit var10000;
            if (chapter == null) {
               var10000 = null;
            } else {
               var25 = false;
               boolean var44 = false;
               int var45 = false;
               var10001 = chapter.getEnd();
               Intrinsics.checkNotNull(var10001);
               chapter.setEnd(var10001 + (long)bufferStart);
               var10000 = Unit.INSTANCE;
            }
         } else {
            chapter = new BookChapter((String)null, (String)null, false, (String)null, (String)null, 0, (String)null, (String)null, (Long)null, (Long)null, (String)null, (String)null, (String)null, 8191, (DefaultConstructorMarker)null);
            chapter.setTitle("" + '第' + blockPos + "章(" + chapterPos + ')');
            BookChapter var40 = (BookChapter)CollectionsKt.lastOrNull((List)toc);
            if (var40 == null) {
               var10001 = curOffset;
            } else {
               Long var42 = var40.getEnd();
               var10001 = var42 == null ? curOffset : var42;
            }

            chapter.setStart(var10001);
            var10001 = chapter.getStart();
            Intrinsics.checkNotNull(var10001);
            chapter.setEnd(var10001 + (long)bufferStart);
            toc.add(chapter);
         }
      } catch (Throwable var30) {
         var33 = var30;
         throw var30;
      } finally {
         CloseableKt.closeFinally(var32, var33);
      }

      return toc;
   }

   // $FF: synthetic method
   static ArrayList analyze$default(TextFile var0, long var1, long var3, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = 0L;
      }

      if ((var5 & 2) != 0) {
         var3 = Long.MAX_VALUE;
      }

      return var0.analyze(var1, var3);
   }

   private final Pattern getTocRule(String content) {
      List rules = CollectionsKt.reversed((Iterable)this.getTocRules());
      int maxCs = 1;
      Pattern tocPattern = null;
      Iterator var5 = rules.iterator();

      while(var5.hasNext()) {
         TxtTocRule tocRule = (TxtTocRule)var5.next();
         String var8 = tocRule.getRule();
         byte var9 = 8;
         boolean var10 = false;
         Pattern var10000 = Pattern.compile(var8, var9);
         Intrinsics.checkNotNullExpressionValue(var10000, "java.util.regex.Pattern.compile(this, flags)");
         Pattern pattern = var10000;
         Matcher matcher = pattern.matcher((CharSequence)content);

         int cs;
         for(cs = 0; matcher.find(); ++cs) {
         }

         if (cs >= maxCs) {
            maxCs = cs;
            tocPattern = pattern;
         }
      }

      return tocPattern;
   }

   private final List<TxtTocRule> getTocRules() {
      Iterable $this$filter$iv = (Iterable)DefaultData.INSTANCE.getTxtTocRules();
      int $i$f$filter = false;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterTo = false;
      Iterator var6 = $this$filter$iv.iterator();

      while(var6.hasNext()) {
         Object element$iv$iv = var6.next();
         TxtTocRule it = (TxtTocRule)element$iv$iv;
         int var9 = false;
         if (it.getEnable()) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      return (List)destination$iv$iv;
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0005¨\u0006\f"},
      d2 = {"Lio/legado/app/model/localBook/TextFile$Companion;", "", "()V", "getChapterList", "Ljava/util/ArrayList;", "Lio/legado/app/data/entities/BookChapter;", "Lkotlin/collections/ArrayList;", "book", "Lio/legado/app/data/entities/Book;", "getContent", "", "bookChapter", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final ArrayList<BookChapter> getChapterList(@NotNull Book book) throws FileNotFoundException {
         Intrinsics.checkNotNullParameter(book, "book");
         return (new TextFile(book)).getChapterList();
      }

      @NotNull
      public final String getContent(@NotNull Book book, @NotNull BookChapter bookChapter) throws FileNotFoundException {
         Intrinsics.checkNotNullParameter(book, "book");
         Intrinsics.checkNotNullParameter(bookChapter, "bookChapter");
         Long var10000 = bookChapter.getEnd();
         Intrinsics.checkNotNull(var10000);
         long var21 = var10000;
         Long var10001 = bookChapter.getStart();
         Intrinsics.checkNotNull(var10001);
         int count = (int)(var21 - var10001);
         byte[] buffer = new byte[count];
         Closeable var5 = (Closeable)LocalBook.INSTANCE.getBookInputStream(book);
         boolean var6 = false;
         boolean var7 = false;
         Throwable var18 = (Throwable)null;

         try {
            InputStream bis = (InputStream)var5;
            int var9 = false;
            var10001 = bookChapter.getStart();
            Intrinsics.checkNotNull(var10001);
            bis.skip(var10001);
            bis.read(buffer);
         } catch (Throwable var12) {
            var18 = var12;
            throw var12;
         } finally {
            CloseableKt.closeFinally(var5, var18);
         }

         if (book.getCharset() == null) {
            book.setCharset(EncodingDetect.INSTANCE.getEncode(book.getLocalFile()));
         }

         Charset var14 = book.fileCharset();
         var6 = false;
         CharSequence var15 = (CharSequence)StringsKt.substringAfter$default(new String(buffer, var14), bookChapter.getTitle(), (String)null, 2, (Object)null);
         String var16 = "^[\\n\\s]+";
         var7 = false;
         Regex var17 = new Regex(var16);
         String var20 = "　　";
         boolean var19 = false;
         return var17.replace(var15, var20);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
