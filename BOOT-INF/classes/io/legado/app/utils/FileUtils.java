package io.legado.app.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.RetentionPolicy;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0005abcdeB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0016\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0013J\u0016\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eJ\u0016\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0013J'\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130#\"\u00020\u0013¢\u0006\u0002\u0010$J\u000e\u0010 \u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u0013J\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u0013J'\u0010'\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130#\"\u00020\u0013¢\u0006\u0002\u0010$J\u000e\u0010'\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u0013J\u001a\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u001e2\b\b\u0002\u0010+\u001a\u00020\u0011H\u0007J\u001a\u0010)\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010+\u001a\u00020\u0011H\u0007J\u000e\u0010,\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0013J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u001eH\u0002J\u000e\u0010.\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J'\u0010/\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u001e2\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130#\"\u00020\u0013¢\u0006\u0002\u00100J\u0006\u00101\u001a\u00020\u0013J\u0016\u00102\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u0013J\u001a\u00102\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u00103\u001a\u00020\u0013H\u0007J\u000e\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u0013J'\u00106\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001e2\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130#\"\u00020\u0013¢\u0006\u0002\u0010$J\u0018\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u00132\b\b\u0002\u00109\u001a\u00020\u0013J\u000e\u0010:\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010;\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u0013J\u0010\u0010<\u001a\u00020\u00132\b\u00105\u001a\u0004\u0018\u00010\u0013J\u000e\u0010=\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J'\u0010>\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u001e2\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130#\"\u00020\u0013¢\u0006\u0002\u0010?J\u000e\u0010@\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J7\u0010A\u001a\b\u0012\u0004\u0012\u00020\u001e0#2\u0006\u0010B\u001a\u00020\u00132\u0010\b\u0002\u0010C\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010#2\b\b\u0002\u0010D\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010EJ/\u0010F\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010#2\u0006\u0010B\u001a\u00020\u00132\u0010\b\u0002\u0010G\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010#H\u0007¢\u0006\u0002\u0010HJ1\u0010I\u001a\b\u0012\u0004\u0012\u00020\u001e0#2\u0006\u0010B\u001a\u00020\u00132\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\b\b\u0002\u0010D\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010LJ+\u0010I\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010#2\u0006\u0010B\u001a\u00020\u00132\u000e\u0010G\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010#¢\u0006\u0002\u0010HJ%\u0010I\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010#2\u0006\u0010B\u001a\u00020\u00132\b\u0010M\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010NJ\u000e\u0010O\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u001eJ\u000e\u0010O\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010P\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eJ\u0016\u0010P\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0013J\u0010\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010S\u001a\u00020\u0013J\u001a\u0010T\u001a\u00020\u00132\u0006\u0010S\u001a\u00020\u00132\b\b\u0002\u0010U\u001a\u00020\u0013H\u0007J\u0016\u0010V\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eJ\u0016\u0010V\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\u00132\u0006\u0010X\u001a\u00020\u0013J\u000e\u0010Y\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010Z\u001a\u00020\u00132\u0006\u0010[\u001a\u00020\rJ\u0016\u0010\\\u001a\u00020\u00112\u0006\u0010S\u001a\u00020\u00132\u0006\u0010]\u001a\u00020RJ\u0016\u0010^\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010]\u001a\u00020_J\u0016\u0010^\u001a\u00020\u00112\u0006\u0010S\u001a\u00020\u00132\u0006\u0010]\u001a\u00020_J\"\u0010`\u001a\u00020\u00112\u0006\u0010S\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010U\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000¨\u0006f"},
   d2 = {"Lio/legado/app/utils/FileUtils;", "", "()V", "BY_EXTENSION_ASC", "", "BY_EXTENSION_DESC", "BY_NAME_ASC", "BY_NAME_DESC", "BY_SIZE_ASC", "BY_SIZE_DESC", "BY_TIME_ASC", "BY_TIME_DESC", "GB", "", "KB", "MB", "appendText", "", "path", "", "content", "closeSilently", "", "c", "Ljava/io/Closeable;", "compareLastModified", "path1", "path2", "copy", "src", "Ljava/io/File;", "tar", "createFileIfNotExist", "root", "subDirFiles", "", "(Ljava/io/File;[Ljava/lang/String;)Ljava/io/File;", "filePath", "createFileWithReplace", "createFolderIfNotExist", "subDirs", "delete", "file", "deleteRootDir", "deleteFile", "deleteResolveEBUSY", "exist", "exists", "(Ljava/io/File;[Ljava/lang/String;)Z", "getCachePath", "getDateTime", "format", "getExtension", "pathOrUrl", "getFile", "getFileExtetion", "url", "defaultExt", "getLength", "getMimeType", "getName", "getNameExcludeExtension", "getPath", "(Ljava/io/File;[Ljava/lang/String;)Ljava/lang/String;", "getSize", "listDirs", "startDirPath", "excludeDirs", "sortType", "(Ljava/lang/String;[Ljava/lang/String;I)[Ljava/io/File;", "listDirsAndFiles", "allowExtensions", "(Ljava/lang/String;[Ljava/lang/String;)[Ljava/io/File;", "listFiles", "filterPattern", "Ljava/util/regex/Pattern;", "(Ljava/lang/String;Ljava/util/regex/Pattern;I)[Ljava/io/File;", "allowExtension", "(Ljava/lang/String;Ljava/lang/String;)[Ljava/io/File;", "makeDirs", "move", "readBytes", "", "filepath", "readText", "charset", "rename", "oldPath", "newPath", "separator", "toFileSizeString", "fileSize", "writeBytes", "data", "writeInputStream", "Ljava/io/InputStream;", "writeText", "SortByExtension", "SortByName", "SortBySize", "SortByTime", "SortType", "reader-pro"}
)
public final class FileUtils {
   @NotNull
   public static final FileUtils INSTANCE = new FileUtils();
   public static final long GB = 1073741824L;
   public static final long MB = 1048576L;
   public static final long KB = 1024L;
   public static final int BY_NAME_ASC = 0;
   public static final int BY_NAME_DESC = 1;
   public static final int BY_TIME_ASC = 2;
   public static final int BY_TIME_DESC = 3;
   public static final int BY_SIZE_ASC = 4;
   public static final int BY_SIZE_DESC = 5;
   public static final int BY_EXTENSION_ASC = 6;
   public static final int BY_EXTENSION_DESC = 7;

   private FileUtils() {
   }

   public final boolean exists(@NotNull File root, @NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(root, "root");
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      return this.getFile(root, (String[])Arrays.copyOf(subDirFiles, subDirFiles.length)).exists();
   }

   @NotNull
   public final File createFileIfNotExist(@NotNull File root, @NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(root, "root");
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      String filePath = this.getPath(root, (String[])Arrays.copyOf(subDirFiles, subDirFiles.length));
      return this.createFileIfNotExist(filePath);
   }

   @NotNull
   public final File createFolderIfNotExist(@NotNull File root, @NotNull String... subDirs) {
      Intrinsics.checkNotNullParameter(root, "root");
      Intrinsics.checkNotNullParameter(subDirs, "subDirs");
      String filePath = this.getPath(root, (String[])Arrays.copyOf(subDirs, subDirs.length));
      return this.createFolderIfNotExist(filePath);
   }

   @NotNull
   public final File createFolderIfNotExist(@NotNull String filePath) {
      Intrinsics.checkNotNullParameter(filePath, "filePath");
      File file = new File(filePath);
      if (!file.exists()) {
         file.mkdirs();
      }

      return file;
   }

   @NotNull
   public final synchronized File createFileIfNotExist(@NotNull String filePath) {
      Intrinsics.checkNotNullParameter(filePath, "filePath");
      File file = new File(filePath);

      try {
         if (!file.exists()) {
            String var3 = file.getParent();
            if (var3 != null) {
               boolean var5 = false;
               boolean var6 = false;
               int var8 = false;
               INSTANCE.createFolderIfNotExist(var3);
            }

            file.createNewFile();
         }
      } catch (IOException var9) {
         var9.printStackTrace();
      }

      return file;
   }

   @NotNull
   public final File createFileWithReplace(@NotNull String filePath) {
      Intrinsics.checkNotNullParameter(filePath, "filePath");
      File file = new File(filePath);
      if (!file.exists()) {
         String var3 = file.getParent();
         if (var3 != null) {
            boolean var5 = false;
            boolean var6 = false;
            int var8 = false;
            INSTANCE.createFolderIfNotExist(var3);
         }

         file.createNewFile();
      } else {
         file.delete();
         file.createNewFile();
      }

      return file;
   }

   @NotNull
   public final File getFile(@NotNull File root, @NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(root, "root");
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      String filePath = this.getPath(root, (String[])Arrays.copyOf(subDirFiles, subDirFiles.length));
      return new File(filePath);
   }

   @NotNull
   public final String getPath(@NotNull File root, @NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(root, "root");
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      StringBuilder path = new StringBuilder(root.getAbsolutePath());
      int $i$f$forEach = false;
      String[] var6 = subDirFiles;
      int var7 = subDirFiles.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         Object element$iv = var6[var8];
         int var11 = false;
         CharSequence var12 = (CharSequence)element$iv;
         boolean var13 = false;
         if (var12.length() > 0) {
            path.append(File.separator).append(element$iv);
         }
      }

      String var4 = path.toString();
      Intrinsics.checkNotNullExpressionValue(var4, "path.toString()");
      return var4;
   }

   public final synchronized void deleteFile(@NotNull String filePath) {
      Intrinsics.checkNotNullParameter(filePath, "filePath");
      File file = new File(filePath);
      if (file.exists()) {
         if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
               int $i$f$forEach = false;
               File[] var7 = files;
               int var8 = files.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  Object element$iv = var7[var9];
                  int var12 = false;
                  String path = element$iv.getPath();
                  FileUtils var10000 = INSTANCE;
                  Intrinsics.checkNotNullExpressionValue(path, "path");
                  var10000.deleteFile(path);
               }
            }
         }

         file.delete();
      }
   }

   @NotNull
   public final String getCachePath() {
      throw new Exception("Not implemented");
   }

   @NotNull
   public final String separator(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      String separator = File.separator;
      Intrinsics.checkNotNullExpressionValue(separator, "separator");
      String path1 = StringsKt.replace$default(path, "\\", separator, false, 4, (Object)null);
      if (!StringsKt.endsWith$default(path1, separator, false, 2, (Object)null)) {
         path1 = Intrinsics.stringPlus(path1, separator);
      }

      return path1;
   }

   public final void closeSilently(@Nullable Closeable c) {
      if (c != null) {
         try {
            c.close();
         } catch (IOException var3) {
         }

      }
   }

   @JvmOverloads
   @NotNull
   public final File[] listDirs(@NotNull String startDirPath, @Nullable String[] excludeDirs, int sortType) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      String[] excludeDirs1 = excludeDirs;
      ArrayList dirList = new ArrayList();
      File startDir = new File(startDirPath);
      if (!startDir.isDirectory()) {
         return new File[0];
      } else {
         File[] var8 = startDir.listFiles(FileUtils::listDirs$lambda-4);
         if (var8 == null) {
            return new File[0];
         } else {
            if (excludeDirs == null) {
               excludeDirs1 = new String[0];
            }

            var8 = var8;
            int var9 = 0;
            int var10 = var8.length;

            while(var9 < var10) {
               File dir = var8[var9];
               ++var9;
               File file = dir.getAbsoluteFile();
               boolean var14 = false;
               CharSequence var10000 = (CharSequence)ArraysKt.contentDeepToString(excludeDirs1);
               String var13 = file.getName();
               Intrinsics.checkNotNullExpressionValue(var13, "file.name");
               if (!StringsKt.contains$default(var10000, (CharSequence)var13, false, 2, (Object)null)) {
                  dirList.add(file);
               }
            }

            switch(sortType) {
            case 0:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortByName()));
               break;
            case 1:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortByName()));
               CollectionsKt.reverse((List)dirList);
               break;
            case 2:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortByTime()));
               break;
            case 3:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortByTime()));
               CollectionsKt.reverse((List)dirList);
               break;
            case 4:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortBySize()));
               break;
            case 5:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortBySize()));
               CollectionsKt.reverse((List)dirList);
               break;
            case 6:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortByExtension()));
               break;
            case 7:
               Collections.sort((List)dirList, (Comparator)(new FileUtils.SortByExtension()));
               CollectionsKt.reverse((List)dirList);
            }

            Collection $this$toTypedArray$iv = (Collection)dirList;
            int $i$f$toTypedArray = false;
            Object[] var17 = $this$toTypedArray$iv.toArray(new File[0]);
            if (var17 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            } else {
               return (File[])var17;
            }
         }
      }
   }

   // $FF: synthetic method
   public static File[] listDirs$default(FileUtils var0, String var1, String[] var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return var0.listDirs(var1, var2, var3);
   }

   @JvmOverloads
   @Nullable
   public final File[] listDirsAndFiles(@NotNull String startDirPath, @Nullable String[] allowExtensions) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      File[] dirs = null;
      File[] files = allowExtensions == null ? listFiles$default(this, startDirPath, (Pattern)null, 0, 6, (Object)null) : this.listFiles(startDirPath, allowExtensions);
      dirs = listDirs$default(this, startDirPath, (String[])null, 0, 6, (Object)null);
      return files == null ? null : (File[])ArraysKt.plus(dirs, files);
   }

   // $FF: synthetic method
   public static File[] listDirsAndFiles$default(FileUtils var0, String var1, String[] var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      return var0.listDirsAndFiles(var1, var2);
   }

   @JvmOverloads
   @NotNull
   public final File[] listFiles(@NotNull String startDirPath, @Nullable Pattern filterPattern, int sortType) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      ArrayList fileList = new ArrayList();
      File f = new File(startDirPath);
      if (!f.isDirectory()) {
         return new File[0];
      } else {
         File[] var7 = f.listFiles(FileUtils::listFiles$lambda-5);
         if (var7 == null) {
            return new File[0];
         } else {
            var7 = var7;
            int var8 = 0;
            int var9 = var7.length;

            while(var8 < var9) {
               File file = var7[var8];
               ++var8;
               fileList.add(file.getAbsoluteFile());
            }

            switch(sortType) {
            case 0:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortByName()));
               break;
            case 1:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortByName()));
               CollectionsKt.reverse((List)fileList);
               break;
            case 2:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortByTime()));
               break;
            case 3:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortByTime()));
               CollectionsKt.reverse((List)fileList);
               break;
            case 4:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortBySize()));
               break;
            case 5:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortBySize()));
               CollectionsKt.reverse((List)fileList);
               break;
            case 6:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortByExtension()));
               break;
            case 7:
               Collections.sort((List)fileList, (Comparator)(new FileUtils.SortByExtension()));
               CollectionsKt.reverse((List)fileList);
            }

            Collection $this$toTypedArray$iv = (Collection)fileList;
            int $i$f$toTypedArray = false;
            Object[] var10000 = $this$toTypedArray$iv.toArray(new File[0]);
            if (var10000 == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            } else {
               return (File[])var10000;
            }
         }
      }
   }

   // $FF: synthetic method
   public static File[] listFiles$default(FileUtils var0, String var1, Pattern var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return var0.listFiles(var1, var2, var3);
   }

   @Nullable
   public final File[] listFiles(@NotNull String startDirPath, @Nullable String[] allowExtensions) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      File file = new File(startDirPath);
      return file.listFiles(FileUtils::listFiles$lambda-6);
   }

   @Nullable
   public final File[] listFiles(@NotNull String startDirPath, @Nullable String allowExtension) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      File[] var10000;
      if (allowExtension == null) {
         var10000 = this.listFiles(startDirPath, (String)null);
      } else {
         String[] var3 = new String[]{allowExtension};
         var10000 = this.listFiles(startDirPath, var3);
      }

      return var10000;
   }

   public final boolean exist(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      File file = new File(path);
      return file.exists();
   }

   @JvmOverloads
   public final boolean delete(@NotNull File file, boolean deleteRootDir) {
      Intrinsics.checkNotNullParameter(file, "file");
      boolean result = false;
      if (file.isFile()) {
         result = this.deleteResolveEBUSY(file);
      } else {
         File[] var5 = file.listFiles();
         if (var5 == null) {
            return false;
         }

         File[] files = var5;
         boolean var6 = false;
         if (var5.length != 0) {
            var5 = var5;
            int var9 = 0;

            File f;
            for(int var7 = files.length; var9 < var7; result = this.deleteResolveEBUSY(f)) {
               f = var5[var9];
               ++var9;
               Intrinsics.checkNotNullExpressionValue(f, "f");
               this.delete(f, deleteRootDir);
            }
         } else {
            result = deleteRootDir && this.deleteResolveEBUSY(file);
         }

         if (deleteRootDir) {
            result = this.deleteResolveEBUSY(file);
         }
      }

      return result;
   }

   // $FF: synthetic method
   public static boolean delete$default(FileUtils var0, File var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return var0.delete(var1, var2);
   }

   private final boolean deleteResolveEBUSY(File file) {
      File to = new File(Intrinsics.stringPlus(file.getAbsolutePath(), System.currentTimeMillis()));
      file.renameTo(to);
      return to.delete();
   }

   @JvmOverloads
   public final boolean delete(@NotNull String path, boolean deleteRootDir) {
      Intrinsics.checkNotNullParameter(path, "path");
      File file = new File(path);
      return file.exists() ? this.delete(file, deleteRootDir) : false;
   }

   // $FF: synthetic method
   public static boolean delete$default(FileUtils var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return var0.delete(var1, var2);
   }

   public final boolean copy(@NotNull String src, @NotNull String tar) {
      Intrinsics.checkNotNullParameter(src, "src");
      Intrinsics.checkNotNullParameter(tar, "tar");
      File srcFile = new File(src);
      return srcFile.exists() && this.copy(srcFile, new File(tar));
   }

   public final boolean copy(@NotNull File src, @NotNull File tar) {
      Intrinsics.checkNotNullParameter(src, "src");
      Intrinsics.checkNotNullParameter(tar, "tar");

      try {
         int len;
         if (src.isFile()) {
            FileInputStream is = new FileInputStream(src);
            FileOutputStream op = new FileOutputStream(tar);
            BufferedInputStream bis = new BufferedInputStream((InputStream)is);
            BufferedOutputStream bos = new BufferedOutputStream((OutputStream)op);
            byte[] bt = new byte[8192];

            while(true) {
               len = bis.read(bt);
               if (len == -1) {
                  bis.close();
                  bos.close();
                  break;
               }

               bos.write(bt, 0, len);
            }
         } else if (src.isDirectory()) {
            tar.mkdirs();
            File[] var14 = src.listFiles();
            if (var14 != null) {
               int $i$f$forEach = false;
               File[] var16 = var14;
               int var17 = var14.length;

               for(len = 0; len < var17; ++len) {
                  Object element$iv = var16[len];
                  int var11 = false;
                  FileUtils var10000 = INSTANCE;
                  File var12 = element$iv.getAbsoluteFile();
                  Intrinsics.checkNotNullExpressionValue(var12, "file.absoluteFile");
                  var10000.copy(var12, new File(tar.getAbsoluteFile(), element$iv.getName()));
               }
            }
         }

         return true;
      } catch (Exception var13) {
         return false;
      }
   }

   public final boolean move(@NotNull String src, @NotNull String tar) {
      Intrinsics.checkNotNullParameter(src, "src");
      Intrinsics.checkNotNullParameter(tar, "tar");
      return this.move(new File(src), new File(tar));
   }

   public final boolean move(@NotNull File src, @NotNull File tar) {
      Intrinsics.checkNotNullParameter(src, "src");
      Intrinsics.checkNotNullParameter(tar, "tar");
      return this.rename(src, tar);
   }

   public final boolean rename(@NotNull String oldPath, @NotNull String newPath) {
      Intrinsics.checkNotNullParameter(oldPath, "oldPath");
      Intrinsics.checkNotNullParameter(newPath, "newPath");
      return this.rename(new File(oldPath), new File(newPath));
   }

   public final boolean rename(@NotNull File src, @NotNull File tar) {
      Intrinsics.checkNotNullParameter(src, "src");
      Intrinsics.checkNotNullParameter(tar, "tar");
      return src.renameTo(tar);
   }

   @JvmOverloads
   @NotNull
   public final String readText(@NotNull String filepath, @NotNull String charset) {
      Intrinsics.checkNotNullParameter(filepath, "filepath");
      Intrinsics.checkNotNullParameter(charset, "charset");

      try {
         byte[] data = this.readBytes(filepath);
         if (data != null) {
            Charset var4 = Charset.forName(charset);
            Intrinsics.checkNotNullExpressionValue(var4, "forName(charset)");
            boolean $i$f$trim = false;
            String $this$trim$iv = new String(data, var4);
            $i$f$trim = false;
            CharSequence $this$trim$iv$iv = (CharSequence)$this$trim$iv;
            int $i$f$trim = false;
            int startIndex$iv$iv = 0;
            int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
            boolean startFound$iv$iv = false;

            while(startIndex$iv$iv <= endIndex$iv$iv) {
               int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
               char it = $this$trim$iv$iv.charAt(index$iv$iv);
               int var13 = false;
               boolean match$iv$iv = Intrinsics.compare(it, 32) <= 0;
               if (!startFound$iv$iv) {
                  if (!match$iv$iv) {
                     startFound$iv$iv = true;
                  } else {
                     ++startIndex$iv$iv;
                  }
               } else {
                  if (!match$iv$iv) {
                     break;
                  }

                  --endIndex$iv$iv;
               }
            }

            return $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
         }
      } catch (UnsupportedEncodingException var14) {
      }

      return "";
   }

   // $FF: synthetic method
   public static String readText$default(FileUtils var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = "utf-8";
      }

      return var0.readText(var1, var2);
   }

   @Nullable
   public final byte[] readBytes(@NotNull String filepath) {
      Intrinsics.checkNotNullParameter(filepath, "filepath");
      FileInputStream fis = null;

      Object var4;
      try {
         fis = new FileInputStream(filepath);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];

         while(true) {
            int len = fis.read(buffer, 0, buffer.length);
            if (len == -1) {
               byte[] data = baos.toByteArray();
               baos.close();
               byte[] var6 = data;
               return var6;
            }

            baos.write(buffer, 0, len);
         }
      } catch (IOException var9) {
         var4 = null;
      } finally {
         this.closeSilently((Closeable)fis);
      }

      return (byte[])var4;
   }

   @JvmOverloads
   public final boolean writeText(@NotNull String filepath, @NotNull String content, @NotNull String charset) {
      Intrinsics.checkNotNullParameter(filepath, "filepath");
      Intrinsics.checkNotNullParameter(content, "content");
      Intrinsics.checkNotNullParameter(charset, "charset");

      boolean var4;
      try {
         boolean var5 = false;
         Charset var10002 = Charset.forName(charset);
         Intrinsics.checkNotNullExpressionValue(var10002, "Charset.forName(charsetName)");
         Charset var8 = var10002;
         boolean var6 = false;
         byte[] var9 = content.getBytes(var8);
         Intrinsics.checkNotNullExpressionValue(var9, "(this as java.lang.String).getBytes(charset)");
         var4 = this.writeBytes(filepath, var9);
      } catch (UnsupportedEncodingException var7) {
         var4 = false;
      }

      return var4;
   }

   // $FF: synthetic method
   public static boolean writeText$default(FileUtils var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = "utf-8";
      }

      return var0.writeText(var1, var2, var3);
   }

   public final boolean writeBytes(@NotNull String filepath, @NotNull byte[] data) {
      Intrinsics.checkNotNullParameter(filepath, "filepath");
      Intrinsics.checkNotNullParameter(data, "data");
      File file = new File(filepath);
      FileOutputStream fos = null;

      boolean var5;
      try {
         if (!file.exists()) {
            File var11 = file.getParentFile();
            if (var11 != null) {
               var11.mkdirs();
            }

            file.createNewFile();
         }

         fos = new FileOutputStream(filepath);
         fos.write(data);
         var5 = true;
      } catch (IOException var9) {
         var5 = false;
      } finally {
         this.closeSilently((Closeable)fos);
      }

      return var5;
   }

   public final boolean writeInputStream(@NotNull String filepath, @NotNull InputStream data) {
      Intrinsics.checkNotNullParameter(filepath, "filepath");
      Intrinsics.checkNotNullParameter(data, "data");
      File file = new File(filepath);
      return this.writeInputStream(file, data);
   }

   public final boolean writeInputStream(@NotNull File file, @NotNull InputStream data) {
      Intrinsics.checkNotNullParameter(file, "file");
      Intrinsics.checkNotNullParameter(data, "data");
      FileOutputStream fos = null;

      boolean var4;
      try {
         if (!file.exists()) {
            File var10 = file.getParentFile();
            if (var10 != null) {
               var10.mkdirs();
            }

            file.createNewFile();
         }

         byte[] buffer = new byte[4096];
         fos = new FileOutputStream(file);

         while(true) {
            int len = data.read(buffer, 0, buffer.length);
            if (len == -1) {
               data.close();
               fos.flush();
               var4 = true;
               break;
            }

            fos.write(buffer, 0, len);
         }
      } catch (IOException var8) {
         var4 = false;
      } finally {
         this.closeSilently((Closeable)fos);
      }

      return var4;
   }

   public final boolean appendText(@NotNull String path, @NotNull String content) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(content, "content");
      File file = new File(path);
      FileWriter writer = null;

      boolean var5;
      try {
         if (!file.exists()) {
            file.createNewFile();
         }

         writer = new FileWriter(file, true);
         writer.write(content);
         var5 = true;
      } catch (IOException var9) {
         var5 = false;
      } finally {
         this.closeSilently((Closeable)writer);
      }

      return var5;
   }

   public final long getLength(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      File file = new File(path);
      return file.isFile() && file.exists() ? file.length() : 0L;
   }

   @NotNull
   public final String getName(@Nullable String pathOrUrl) {
      if (pathOrUrl == null) {
         return "";
      } else {
         int pos = StringsKt.lastIndexOf$default((CharSequence)pathOrUrl, '/', 0, false, 6, (Object)null);
         String var10000;
         if (0 <= pos) {
            int var4 = pos + 1;
            boolean var5 = false;
            var10000 = pathOrUrl.substring(var4);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
         } else {
            var10000 = "" + System.currentTimeMillis() + '.' + this.getExtension(pathOrUrl);
         }

         return var10000;
      }
   }

   @NotNull
   public final String getNameExcludeExtension(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");

      String var2;
      try {
         String fileName = (new File(path)).getName();
         Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
         int lastIndexOf = StringsKt.lastIndexOf$default((CharSequence)fileName, ".", 0, false, 6, (Object)null);
         if (lastIndexOf != -1) {
            Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
            byte var6 = 0;
            boolean var7 = false;
            String var10000 = fileName.substring(var6, lastIndexOf);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            fileName = var10000;
         }

         Intrinsics.checkNotNullExpressionValue(fileName, "{\n            var fileName = File(path).name\n            val lastIndexOf = fileName.lastIndexOf(\".\")\n            if (lastIndexOf != -1) {\n                fileName = fileName.substring(0, lastIndexOf)\n            }\n            fileName\n        }");
         var2 = fileName;
      } catch (Exception var8) {
         var2 = "";
      }

      return var2;
   }

   @NotNull
   public final String getSize(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      long fileSize = this.getLength(path);
      return this.toFileSizeString(fileSize);
   }

   @NotNull
   public final String toFileSizeString(long fileSize) {
      DecimalFormat df = new DecimalFormat("0.00");
      String fileSizeString = null;
      fileSizeString = fileSize < 1024L ? "" + fileSize + 'B' : (fileSize < 1048576L ? Intrinsics.stringPlus(df.format((double)fileSize / (double)1024L), "K") : (fileSize < 1073741824L ? Intrinsics.stringPlus(df.format((double)fileSize / (double)1048576L), "M") : Intrinsics.stringPlus(df.format((double)fileSize / (double)1073741824L), "G")));
      return fileSizeString;
   }

   @NotNull
   public final String getExtension(@NotNull String pathOrUrl) {
      Intrinsics.checkNotNullParameter(pathOrUrl, "pathOrUrl");
      int dotPos = StringsKt.lastIndexOf$default((CharSequence)pathOrUrl, '.', 0, false, 6, (Object)null);
      String var10000;
      if (0 <= dotPos) {
         int var4 = dotPos + 1;
         boolean var5 = false;
         var10000 = pathOrUrl.substring(var4);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
      } else {
         var10000 = "ext";
      }

      return var10000;
   }

   @NotNull
   public final String getFileExtetion(@NotNull String url, @NotNull String defaultExt) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(defaultExt, "defaultExt");

      try {
         CharSequence var10000 = (CharSequence)url;
         String[] var4 = new String[]{"?"};
         List seqs = StringsKt.split(var10000, var4, true, 2);
         var10000 = (CharSequence)seqs.get(0);
         String[] var5 = new String[]{"/"};
         String file = (String)CollectionsKt.last(StringsKt.split$default(var10000, var5, false, 0, 6, (Object)null));
         int dotPos = StringsKt.lastIndexOf$default((CharSequence)file, '.', 0, false, 6, (Object)null);
         String var12;
         if (0 <= dotPos) {
            int var7 = dotPos + 1;
            boolean var8 = false;
            if (file == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            var12 = file.substring(var7);
            Intrinsics.checkNotNullExpressionValue(var12, "(this as java.lang.String).substring(startIndex)");
         } else {
            var12 = defaultExt;
         }

         return var12;
      } catch (Exception var9) {
         return defaultExt;
      }
   }

   // $FF: synthetic method
   public static String getFileExtetion$default(FileUtils var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = "";
      }

      return var0.getFileExtetion(var1, var2);
   }

   @NotNull
   public final String getMimeType(@NotNull String pathOrUrl) {
      Intrinsics.checkNotNullParameter(pathOrUrl, "pathOrUrl");
      throw new Exception("Not implemented");
   }

   @JvmOverloads
   @NotNull
   public final String getDateTime(@NotNull String path, @NotNull String format) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(format, "format");
      File file = new File(path);
      return this.getDateTime(file, format);
   }

   // $FF: synthetic method
   public static String getDateTime$default(FileUtils var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = "yyyy年MM月dd日HH:mm";
      }

      return var0.getDateTime(var1, var2);
   }

   @NotNull
   public final String getDateTime(@NotNull File file, @NotNull String format) {
      Intrinsics.checkNotNullParameter(file, "file");
      Intrinsics.checkNotNullParameter(format, "format");
      Calendar cal = Calendar.getInstance();
      cal.setTimeInMillis(file.lastModified());
      String var4 = (new SimpleDateFormat(format, Locale.PRC)).format(cal.getTime());
      Intrinsics.checkNotNullExpressionValue(var4, "SimpleDateFormat(format, Locale.PRC).format(cal.time)");
      return var4;
   }

   public final int compareLastModified(@NotNull String path1, @NotNull String path2) {
      Intrinsics.checkNotNullParameter(path1, "path1");
      Intrinsics.checkNotNullParameter(path2, "path2");
      long stamp1 = (new File(path1)).lastModified();
      long stamp2 = (new File(path2)).lastModified();
      return stamp1 > stamp2 ? 1 : (stamp1 < stamp2 ? -1 : 0);
   }

   public final boolean makeDirs(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      return this.makeDirs(new File(path));
   }

   public final boolean makeDirs(@NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      return file.mkdirs();
   }

   @JvmOverloads
   @NotNull
   public final File[] listDirs(@NotNull String startDirPath, @Nullable String[] excludeDirs) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      return listDirs$default(this, startDirPath, excludeDirs, 0, 4, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final File[] listDirs(@NotNull String startDirPath) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      return listDirs$default(this, startDirPath, (String[])null, 0, 6, (Object)null);
   }

   @JvmOverloads
   @Nullable
   public final File[] listDirsAndFiles(@NotNull String startDirPath) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      return listDirsAndFiles$default(this, startDirPath, (String[])null, 2, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final File[] listFiles(@NotNull String startDirPath, @Nullable Pattern filterPattern) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      return listFiles$default(this, startDirPath, filterPattern, 0, 4, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final File[] listFiles(@NotNull String startDirPath) {
      Intrinsics.checkNotNullParameter(startDirPath, "startDirPath");
      return listFiles$default(this, startDirPath, (Pattern)null, 0, 6, (Object)null);
   }

   @JvmOverloads
   public final boolean delete(@NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      return delete$default(this, (File)file, false, 2, (Object)null);
   }

   @JvmOverloads
   public final boolean delete(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      return delete$default(this, (String)path, false, 2, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String readText(@NotNull String filepath) {
      Intrinsics.checkNotNullParameter(filepath, "filepath");
      return readText$default(this, filepath, (String)null, 2, (Object)null);
   }

   @JvmOverloads
   public final boolean writeText(@NotNull String filepath, @NotNull String content) {
      Intrinsics.checkNotNullParameter(filepath, "filepath");
      Intrinsics.checkNotNullParameter(content, "content");
      return writeText$default(this, filepath, content, (String)null, 4, (Object)null);
   }

   @JvmOverloads
   @NotNull
   public final String getDateTime(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      return getDateTime$default(this, path, (String)null, 2, (Object)null);
   }

   private static final boolean listDirs$lambda_4/* $FF was: listDirs$lambda-4*/(File f) {
      return f == null ? false : f.isDirectory();
   }

   private static final boolean listFiles$lambda_5/* $FF was: listFiles$lambda-5*/(Pattern $filterPattern, File file) {
      if (file == null) {
         return false;
      } else if (file.isDirectory()) {
         return false;
      } else {
         boolean var10000;
         if ($filterPattern == null) {
            var10000 = true;
         } else {
            Matcher var3 = $filterPattern.matcher((CharSequence)file.getName());
            if (var3 == null) {
               var10000 = true;
            } else {
               boolean var4 = var3.find();
               var10000 = var4;
            }
         }

         return var10000;
      }
   }

   private static final boolean listFiles$lambda_6/* $FF was: listFiles$lambda-6*/(String[] $allowExtensions, File $noName_0, String name) {
      FileUtils var10000 = INSTANCE;
      Intrinsics.checkNotNullExpressionValue(name, "name");
      String extension = var10000.getExtension(name);
      boolean var8;
      if ($allowExtensions == null) {
         var8 = false;
      } else {
         boolean var7 = false;
         String var5 = ArraysKt.contentDeepToString($allowExtensions);
         var8 = var5 == null ? false : StringsKt.contains$default((CharSequence)var5, (CharSequence)extension, false, 2, (Object)null);
      }

      return var8 || $allowExtensions == null;
   }

   @Retention(AnnotationRetention.SOURCE)
   @java.lang.annotation.Retention(RetentionPolicy.SOURCE)
   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"},
      d2 = {"Lio/legado/app/utils/FileUtils$SortType;", "", "reader-pro"}
   )
   public @interface SortType {
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"},
      d2 = {"Lio/legado/app/utils/FileUtils$SortByExtension;", "Ljava/util/Comparator;", "Ljava/io/File;", "()V", "compare", "", "f1", "f2", "reader-pro"}
   )
   public static final class SortByExtension implements Comparator<File> {
      public int compare(@Nullable File f1, @Nullable File f2) {
         int var10000;
         if (f1 != null && f2 != null) {
            if (f1.isDirectory() && f2.isFile()) {
               var10000 = -1;
            } else if (f1.isFile() && f2.isDirectory()) {
               var10000 = 1;
            } else {
               String var3 = f1.getName();
               Intrinsics.checkNotNullExpressionValue(var3, "f1.name");
               String var4 = var3;
               var3 = f2.getName();
               Intrinsics.checkNotNullExpressionValue(var3, "f2.name");
               var10000 = StringsKt.compareTo(var4, var3, true);
            }
         } else {
            var10000 = f1 == null ? -1 : 1;
         }

         return var10000;
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0007\b\u0016¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"},
      d2 = {"Lio/legado/app/utils/FileUtils$SortByName;", "Ljava/util/Comparator;", "Ljava/io/File;", "caseSensitive", "", "(Z)V", "()V", "compare", "", "f1", "f2", "reader-pro"}
   )
   public static final class SortByName implements Comparator<File> {
      private boolean caseSensitive;

      public SortByName(boolean caseSensitive) {
         this.caseSensitive = caseSensitive;
      }

      public SortByName() {
         this.caseSensitive = false;
      }

      public int compare(@Nullable File f1, @Nullable File f2) {
         if (f1 != null && f2 != null) {
            int var10000;
            if (f1.isDirectory() && f2.isFile()) {
               var10000 = -1;
            } else if (f1.isFile() && f2.isDirectory()) {
               var10000 = 1;
            } else {
               String s1 = f1.getName();
               String s2 = f2.getName();
               if (this.caseSensitive) {
                  Intrinsics.checkNotNullExpressionValue(s1, "s1");
                  Intrinsics.checkNotNullExpressionValue(s2, "s2");
                  var10000 = StringsKt.compareTo(s1, s2, false);
               } else {
                  Intrinsics.checkNotNullExpressionValue(s1, "s1");
                  Intrinsics.checkNotNullExpressionValue(s2, "s2");
                  var10000 = StringsKt.compareTo(s1, s2, true);
               }
            }

            return var10000;
         } else {
            return f1 == null ? -1 : 1;
         }
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"},
      d2 = {"Lio/legado/app/utils/FileUtils$SortBySize;", "Ljava/util/Comparator;", "Ljava/io/File;", "()V", "compare", "", "f1", "f2", "reader-pro"}
   )
   public static final class SortBySize implements Comparator<File> {
      public int compare(@Nullable File f1, @Nullable File f2) {
         return f1 != null && f2 != null ? (f1.isDirectory() && f2.isFile() ? -1 : (f1.isFile() && f2.isDirectory() ? 1 : (f1.length() < f2.length() ? -1 : 1))) : (f1 == null ? -1 : 1);
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"},
      d2 = {"Lio/legado/app/utils/FileUtils$SortByTime;", "Ljava/util/Comparator;", "Ljava/io/File;", "()V", "compare", "", "f1", "f2", "reader-pro"}
   )
   public static final class SortByTime implements Comparator<File> {
      public int compare(@Nullable File f1, @Nullable File f2) {
         return f1 != null && f2 != null ? (f1.isDirectory() && f2.isFile() ? -1 : (f1.isFile() && f2.isDirectory() ? 1 : (f1.lastModified() > f2.lastModified() ? -1 : 1))) : (f1 == null ? -1 : 1);
      }
   }
}
