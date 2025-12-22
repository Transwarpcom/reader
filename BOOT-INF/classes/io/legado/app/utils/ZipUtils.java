package io.legado.app.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\nJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0018\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\nJ\u0012\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0002J6\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u001e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006J\u001e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nJ,\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\nJ(\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\nJ*\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\nH\u0002J(\u0010\u000b\u001a\u00020\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\nH\u0007J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010#\u001a\u00020\nJ.\u0010%\u001a\u00020\u00042\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010'2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\nH\u0007J'\u0010%\u001a\u00020\u00042\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0'2\u0006\u0010\f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010(J5\u0010%\u001a\u00020\u00042\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010'2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010#\u001a\u0004\u0018\u00010\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"},
   d2 = {"Lio/legado/app/utils/ZipUtils;", "", "()V", "createOrExistsDir", "", "file", "Ljava/io/File;", "createOrExistsFile", "getComments", "", "", "zipFile", "zipFilePath", "getFileByPath", "filePath", "getFilesPath", "isSpace", "s", "unzipChildFile", "destDir", "files", "", "zip", "Ljava/util/zip/ZipFile;", "entry", "Ljava/util/zip/ZipEntry;", "name", "unzipFile", "destDirPath", "unzipFileByKeyword", "keyword", "srcFile", "rootPath", "zos", "Ljava/util/zip/ZipOutputStream;", "comment", "srcFilePath", "zipFiles", "srcFiles", "", "(Ljava/util/Collection;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "srcFilePaths", "(Ljava/util/Collection;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class ZipUtils {
   @NotNull
   public static final ZipUtils INSTANCE = new ZipUtils();

   private ZipUtils() {
   }

   @Nullable
   public final Object zipFiles(@NotNull Collection<String> srcFiles, @NotNull String zipFilePath, @NotNull Continuation<? super Boolean> $completion) {
      return this.zipFiles(srcFiles, zipFilePath, (String)null, $completion);
   }

   @Nullable
   public final Object zipFiles(@Nullable Collection<String> srcFilePaths, @Nullable String zipFilePath, @Nullable String comment, @NotNull Continuation<? super Boolean> $completion) {
      return BuildersKt.withContext((CoroutineContext)Dispatchers.getIO(), (Function2)(new Function2<CoroutineScope, Continuation<? super Boolean>, Object>((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch(this.label) {
            case 0:
               ResultKt.throwOnFailure(var1);
               if (srcFilePaths != null && zipFilePath != null) {
                  Closeable var2 = (Closeable)(new ZipOutputStream((OutputStream)(new FileOutputStream(zipFilePath))));
                  Collection var3 = srcFilePaths;
                  String var4 = comment;
                  boolean var5 = false;
                  boolean var6 = false;
                  Throwable var18 = (Throwable)null;

                  Boolean var11;
                  try {
                     ZipOutputStream it = (ZipOutputStream)var2;
                     int var8 = false;
                     Iterator var9 = var3.iterator();

                     while(var9.hasNext()) {
                        String srcFile = (String)var9.next();
                        ZipUtils var10000 = ZipUtils.INSTANCE;
                        File var10001 = ZipUtils.INSTANCE.getFileByPath(srcFile);
                        Intrinsics.checkNotNull(var10001);
                        if (!var10000.zipFile(var10001, "", it, var4)) {
                           Boolean var12 = Boxing.boxBoolean(false);
                           return var12;
                        }
                     }

                     var11 = Boxing.boxBoolean(true);
                  } catch (Throwable var16) {
                     var18 = var16;
                     throw var16;
                  } finally {
                     CloseableKt.closeFinally(var2, var18);
                  }

                  return var11;
               } else {
                  return Boxing.boxBoolean(false);
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Boolean> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), $completion);
   }

   @JvmOverloads
   public final boolean zipFiles(@Nullable Collection<? extends File> srcFiles, @Nullable File zipFile, @Nullable String comment) throws IOException {
      if (srcFiles != null && zipFile != null) {
         Closeable var4 = (Closeable)(new ZipOutputStream((OutputStream)(new FileOutputStream(zipFile))));
         boolean var5 = false;
         boolean var6 = false;
         Throwable var17 = (Throwable)null;

         boolean var11;
         try {
            ZipOutputStream it = (ZipOutputStream)var4;
            int var8 = false;
            Iterator var9 = srcFiles.iterator();

            while(var9.hasNext()) {
               File srcFile = (File)var9.next();
               if (!INSTANCE.zipFile(srcFile, "", it, comment)) {
                  boolean var12 = false;
                  return var12;
               }
            }

            var11 = true;
         } catch (Throwable var15) {
            var17 = var15;
            throw var15;
         } finally {
            CloseableKt.closeFinally(var4, var17);
         }

         return var11;
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   public static boolean zipFiles$default(ZipUtils var0, Collection var1, File var2, String var3, int var4, Object var5) throws IOException {
      if ((var4 & 4) != 0) {
         var3 = null;
      }

      return var0.zipFiles(var1, var2, var3);
   }

   public final boolean zipFile(@NotNull String srcFilePath, @NotNull String zipFilePath) throws IOException {
      Intrinsics.checkNotNullParameter(srcFilePath, "srcFilePath");
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      return this.zipFile((File)this.getFileByPath(srcFilePath), (File)this.getFileByPath(zipFilePath), (String)null);
   }

   public final boolean zipFile(@NotNull String srcFilePath, @NotNull String zipFilePath, @NotNull String comment) throws IOException {
      Intrinsics.checkNotNullParameter(srcFilePath, "srcFilePath");
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      Intrinsics.checkNotNullParameter(comment, "comment");
      return this.zipFile(this.getFileByPath(srcFilePath), this.getFileByPath(zipFilePath), comment);
   }

   @JvmOverloads
   public final boolean zipFile(@Nullable File srcFile, @Nullable File zipFile, @Nullable String comment) throws IOException {
      if (srcFile != null && zipFile != null) {
         Closeable var4 = (Closeable)(new ZipOutputStream((OutputStream)(new FileOutputStream(zipFile))));
         boolean var5 = false;
         boolean var6 = false;
         Throwable var14 = (Throwable)null;

         boolean var9;
         try {
            ZipOutputStream zos = (ZipOutputStream)var4;
            int var8 = false;
            var9 = INSTANCE.zipFile(srcFile, "", zos, comment);
         } catch (Throwable var12) {
            var14 = var12;
            throw var12;
         } finally {
            CloseableKt.closeFinally(var4, var14);
         }

         return var9;
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   public static boolean zipFile$default(ZipUtils var0, File var1, File var2, String var3, int var4, Object var5) throws IOException {
      if ((var4 & 4) != 0) {
         var3 = null;
      }

      return var0.zipFile(var1, var2, var3);
   }

   private final boolean zipFile(File srcFile, String rootPath, ZipOutputStream zos, String comment) throws IOException {
      Object rootPath1 = null;
      if (!srcFile.exists()) {
         return true;
      } else {
         rootPath1 = rootPath + (this.isSpace(rootPath) ? "" : File.separator) + srcFile.getName();
         boolean var8;
         if (srcFile.isDirectory()) {
            File[] fileList = srcFile.listFiles();
            if (fileList != null) {
               var8 = false;
               if (fileList.length != 0) {
                  File[] var17 = fileList;
                  int var19 = 0;
                  int var9 = fileList.length;

                  File file;
                  do {
                     if (var19 >= var9) {
                        return true;
                     }

                     file = var17[var19];
                     ++var19;
                     Intrinsics.checkNotNullExpressionValue(file, "file");
                  } while(this.zipFile(file, rootPath1, zos, comment));

                  return false;
               }
            }

            ZipEntry entry = new ZipEntry(Intrinsics.stringPlus(rootPath1, "/"));
            entry.setComment(comment);
            zos.putNextEntry(entry);
            zos.closeEntry();
         } else {
            Closeable var16 = (Closeable)(new BufferedInputStream((InputStream)(new FileInputStream(srcFile))));
            boolean var18 = false;
            var8 = false;
            Throwable var20 = (Throwable)null;

            try {
               BufferedInputStream is = (BufferedInputStream)var16;
               int var23 = false;
               ZipEntry entry = new ZipEntry(rootPath1);
               entry.setComment(comment);
               zos.putNextEntry(entry);
               zos.write(ByteStreamsKt.readBytes((InputStream)is));
               zos.closeEntry();
               Unit var22 = Unit.INSTANCE;
            } catch (Throwable var14) {
               var20 = var14;
               throw var14;
            } finally {
               CloseableKt.closeFinally(var16, var20);
            }
         }

         return true;
      }
   }

   @Nullable
   public final List<File> unzipFile(@NotNull String zipFilePath, @NotNull String destDirPath) throws IOException {
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      Intrinsics.checkNotNullParameter(destDirPath, "destDirPath");
      return this.unzipFileByKeyword((String)zipFilePath, (String)destDirPath, (String)null);
   }

   @Nullable
   public final List<File> unzipFile(@NotNull File zipFile, @NotNull File destDir) throws IOException {
      Intrinsics.checkNotNullParameter(zipFile, "zipFile");
      Intrinsics.checkNotNullParameter(destDir, "destDir");
      return this.unzipFileByKeyword((File)zipFile, (File)destDir, (String)null);
   }

   @Nullable
   public final List<File> unzipFileByKeyword(@NotNull String zipFilePath, @NotNull String destDirPath, @Nullable String keyword) throws IOException {
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      Intrinsics.checkNotNullParameter(destDirPath, "destDirPath");
      return this.unzipFileByKeyword(this.getFileByPath(zipFilePath), this.getFileByPath(destDirPath), keyword);
   }

   @Nullable
   public final List<File> unzipFileByKeyword(@Nullable File zipFile, @Nullable File destDir, @Nullable String keyword) throws IOException {
      if (zipFile != null && destDir != null) {
         ArrayList files = new ArrayList();
         ZipFile zip = new ZipFile(zipFile);
         Enumeration entries = zip.entries();
         Closeable var7 = (Closeable)zip;
         boolean var8 = false;
         boolean var9 = false;
         Throwable var20 = (Throwable)null;

         try {
            ZipFile it = (ZipFile)var7;
            int var11 = false;
            Object var12;
            ZipEntry entry;
            String entryName;
            if (INSTANCE.isSpace(keyword)) {
               while(entries.hasMoreElements()) {
                  var12 = entries.nextElement();
                  if (var12 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
                  }

                  entry = (ZipEntry)var12;
                  entryName = entry.getName();
                  Intrinsics.checkNotNullExpressionValue(entryName, "entryName");
                  if (StringsKt.contains$default((CharSequence)entryName, (CharSequence)"../", false, 2, (Object)null)) {
                     ZipUtilsKt.access$getLogger$p().error("ZipUtils entryName: " + entryName + " is dangerous!");
                  } else if (!INSTANCE.unzipChildFile(destDir, (List)files, zip, entry, entryName)) {
                     List var15 = (List)files;
                     return var15;
                  }
               }
            } else {
               while(entries.hasMoreElements()) {
                  var12 = entries.nextElement();
                  if (var12 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
                  }

                  entry = (ZipEntry)var12;
                  entryName = entry.getName();
                  Intrinsics.checkNotNullExpressionValue(entryName, "entryName");
                  if (StringsKt.contains$default((CharSequence)entryName, (CharSequence)"../", false, 2, (Object)null)) {
                     ZipUtilsKt.access$getLogger$p().error("ZipUtils entryName: " + entryName + " is dangerous!");
                  } else {
                     CharSequence var10000 = (CharSequence)entryName;
                     Intrinsics.checkNotNull(keyword);
                     if (StringsKt.contains$default(var10000, (CharSequence)keyword, false, 2, (Object)null) && !INSTANCE.unzipChildFile(destDir, (List)files, zip, entry, entryName)) {
                        List var14 = (List)files;
                        return var14;
                     }
                  }
               }
            }

            Unit var21 = Unit.INSTANCE;
         } catch (Throwable var18) {
            var20 = var18;
            throw var18;
         } finally {
            CloseableKt.closeFinally(var7, var20);
         }

         return (List)files;
      } else {
         return null;
      }
   }

   private final boolean unzipChildFile(File destDir, List<File> files, ZipFile zip, ZipEntry entry, String name) throws IOException {
      File file = new File(destDir, name);
      files.add(file);
      if (entry.isDirectory()) {
         return this.createOrExistsDir(file);
      } else if (!this.createOrExistsFile(file)) {
         return false;
      } else {
         Closeable var7 = (Closeable)(new BufferedInputStream(zip.getInputStream(entry)));
         boolean var8 = false;
         boolean var9 = false;
         Throwable var29 = (Throwable)null;

         try {
            BufferedInputStream in = (BufferedInputStream)var7;
            int var11 = false;
            Closeable var12 = (Closeable)(new BufferedOutputStream((OutputStream)(new FileOutputStream(file))));
            boolean var13 = false;
            boolean var14 = false;
            Throwable var31 = (Throwable)null;

            try {
               BufferedOutputStream out = (BufferedOutputStream)var12;
               int var16 = false;
               out.write(ByteStreamsKt.readBytes((InputStream)in));
               Unit var32 = Unit.INSTANCE;
            } catch (Throwable var25) {
               var31 = var25;
               throw var25;
            } finally {
               CloseableKt.closeFinally(var12, var31);
            }

            Unit var30 = Unit.INSTANCE;
         } catch (Throwable var27) {
            var29 = var27;
            throw var27;
         } finally {
            CloseableKt.closeFinally(var7, var29);
         }

         return true;
      }
   }

   @Nullable
   public final List<String> getFilesPath(@NotNull String zipFilePath) throws IOException {
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      return this.getFilesPath(this.getFileByPath(zipFilePath));
   }

   @Nullable
   public final List<String> getFilesPath(@Nullable File zipFile) throws IOException {
      if (zipFile == null) {
         return null;
      } else {
         ArrayList paths = new ArrayList();
         ZipFile zip = new ZipFile(zipFile);
         Enumeration entries = zip.entries();

         while(entries.hasMoreElements()) {
            Object var6 = entries.nextElement();
            if (var6 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
            }

            String entryName = ((ZipEntry)var6).getName();
            Intrinsics.checkNotNullExpressionValue(entryName, "entryName");
            if (StringsKt.contains$default((CharSequence)entryName, (CharSequence)"../", false, 2, (Object)null)) {
               ZipUtilsKt.access$getLogger$p().error("ZipUtils entryName: " + entryName + " is dangerous!");
               paths.add(entryName);
            } else {
               paths.add(entryName);
            }
         }

         zip.close();
         return (List)paths;
      }
   }

   @Nullable
   public final List<String> getComments(@NotNull String zipFilePath) throws IOException {
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      return this.getComments(this.getFileByPath(zipFilePath));
   }

   @Nullable
   public final List<String> getComments(@Nullable File zipFile) throws IOException {
      if (zipFile == null) {
         return null;
      } else {
         ArrayList comments = new ArrayList();
         ZipFile zip = new ZipFile(zipFile);
         Enumeration entries = zip.entries();

         while(entries.hasMoreElements()) {
            Object var6 = entries.nextElement();
            if (var6 == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
            }

            ZipEntry entry = (ZipEntry)var6;
            comments.add(entry.getComment());
         }

         zip.close();
         return (List)comments;
      }
   }

   private final boolean createOrExistsDir(File file) {
      return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
   }

   private final boolean createOrExistsFile(File file) {
      if (file == null) {
         return false;
      } else if (file.exists()) {
         return file.isFile();
      } else if (!this.createOrExistsDir(file.getParentFile())) {
         return false;
      } else {
         boolean var2;
         try {
            var2 = file.createNewFile();
         } catch (IOException var4) {
            var4.printStackTrace();
            var2 = false;
         }

         return var2;
      }
   }

   private final File getFileByPath(String filePath) {
      return this.isSpace(filePath) ? null : new File(filePath);
   }

   private final boolean isSpace(String s) {
      if (s == null) {
         return true;
      } else {
         int i = 0;

         for(int len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
               return false;
            }
         }

         return true;
      }
   }

   @JvmOverloads
   public final boolean zipFiles(@Nullable Collection<? extends File> srcFiles, @Nullable File zipFile) throws IOException {
      return zipFiles$default(this, srcFiles, zipFile, (String)null, 4, (Object)null);
   }

   @JvmOverloads
   public final boolean zipFile(@Nullable File srcFile, @Nullable File zipFile) throws IOException {
      return zipFile$default(this, srcFile, zipFile, (String)null, 4, (Object)null);
   }
}
