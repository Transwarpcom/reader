package io.legado.app.utils;

import io.legado.app.adapters.ReaderAdapterHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001c\u001d\u001eB\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u000fJ\"\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0007H\u0007J\u0016\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0011J\u001e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0007J\u0016\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000fJ\u001e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\t\u001a\b\u0018\u00010\nR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"},
   d2 = {"Lio/legado/app/utils/ACache;", "", "cacheDir", "Ljava/io/File;", "max_size", "", "max_count", "", "(Ljava/io/File;JI)V", "mCache", "Lio/legado/app/utils/ACache$ACacheManager;", "clear", "", "file", "key", "", "getAsBinary", "", "getAsObject", "getAsString", "getByHashCode", "hashCode", "put", "value", "Ljava/io/Serializable;", "saveTime", "remove", "", "ACacheManager", "Companion", "Utils", "reader-pro"}
)
public final class ACache {
   @NotNull
   public static final ACache.Companion Companion = new ACache.Companion((DefaultConstructorMarker)null);
   @Nullable
   private ACache.ACacheManager mCache;
   public static final int TIME_HOUR = 3600;
   public static final int TIME_DAY = 86400;
   private static final int MAX_SIZE = 50000000;
   private static final int MAX_COUNT = Integer.MAX_VALUE;
   @NotNull
   private static final HashMap<String, ACache> mInstanceMap = new HashMap();

   private ACache(File cacheDir, long max_size, int max_count) {
      try {
         if (!cacheDir.exists() && !cacheDir.mkdirs()) {
            ACacheKt.access$getLogger$p().info(Intrinsics.stringPlus("ACache can't make dirs in %s", cacheDir.getAbsolutePath()));
         }

         this.mCache = new ACache.ACacheManager(cacheDir, max_size, max_count);
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   public final void put(@NotNull String key, @NotNull String value) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      ACache.ACacheManager var3 = this.mCache;
      if (var3 != null) {
         boolean var5 = false;
         boolean var6 = false;
         ACache.ACacheManager mCache = var3;
         boolean var8 = false;

         try {
            File file = mCache.newFile(key);
            FilesKt.writeText$default(file, value, (Charset)null, 2, (Object)null);
            mCache.put(file);
         } catch (Exception var10) {
            var10.printStackTrace();
         }
      }

   }

   public final void put(@NotNull String key, @NotNull String value, int saveTime) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      if (saveTime <= 0) {
         this.put(key, value);
      } else {
         this.put(key, ACache.Utils.INSTANCE.newStringWithDateInfo(saveTime, value));
      }
   }

   @Nullable
   public final String getAsString(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      ACache.ACacheManager var2 = this.mCache;
      if (var2 != null) {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         File file = var2.get(key);
         if (!file.exists()) {
            return null;
         }

         boolean var9 = false;

         try {
            String text = FilesKt.readText$default(file, (Charset)null, 1, (Object)null);
            if (!ACache.Utils.INSTANCE.isDue(text)) {
               String var11 = ACache.Utils.INSTANCE.clearDateInfo(text);
               return var11;
            }

            var9 = true;
         } catch (IOException var14) {
            var14.printStackTrace();
            return null;
         } finally {
            ;
         }

         this.remove(key);
      }

      return null;
   }

   @Nullable
   public final String getByHashCode(@NotNull String hashCode) {
      Intrinsics.checkNotNullParameter(hashCode, "hashCode");
      ACache.ACacheManager var2 = this.mCache;
      if (var2 != null) {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         File file = var2.newFileFromHashCode(hashCode);
         if (!file.exists()) {
            return null;
         }

         boolean var9 = false;

         try {
            String text = FilesKt.readText$default(file, (Charset)null, 1, (Object)null);
            if (!ACache.Utils.INSTANCE.isDue(text)) {
               String var11 = ACache.Utils.INSTANCE.clearDateInfo(text);
               return var11;
            }

            var9 = true;
         } catch (IOException var14) {
            var14.printStackTrace();
            return null;
         } finally {
            ;
         }

         file.delete();
      }

      return null;
   }

   public final void put(@NotNull String key, @NotNull byte[] value) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      ACache.ACacheManager var3 = this.mCache;
      if (var3 != null) {
         boolean var5 = false;
         boolean var6 = false;
         int var8 = false;
         File file = var3.newFile(key);
         FilesKt.writeBytes(file, value);
         var3.put(file);
      }

   }

   public final void put(@NotNull String key, @NotNull byte[] value, int saveTime) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      if (saveTime <= 0) {
         this.put(key, value);
      } else {
         this.put(key, ACache.Utils.INSTANCE.newByteArrayWithDateInfo(saveTime, value));
      }
   }

   @Nullable
   public final byte[] getAsBinary(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      ACache.ACacheManager var2 = this.mCache;
      if (var2 != null) {
         boolean var4 = false;
         boolean var5 = false;
         ACache.ACacheManager mCache = var2;
         int var7 = false;
         boolean removeFile = false;

         byte[] var11;
         try {
            File file = mCache.get(key);
            if (!file.exists()) {
               Object var16 = null;
               return (byte[])var16;
            }

            byte[] byteArray = FilesKt.readBytes(file);
            byte[] var10000;
            if (!ACache.Utils.INSTANCE.isDue(byteArray)) {
               var10000 = ACache.Utils.INSTANCE.clearDateInfo(byteArray);
            } else {
               removeFile = true;
               var10000 = (byte[])null;
            }

            var11 = var10000;
         } catch (Exception var14) {
            var14.printStackTrace();
            return null;
         } finally {
            if (removeFile) {
               this.remove(key);
            }

         }

         return var11;
      } else {
         return null;
      }
   }

   @JvmOverloads
   public final void put(@NotNull String key, @NotNull Serializable value, int saveTime) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");

      try {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         Closeable var5 = (Closeable)(new ObjectOutputStream((OutputStream)byteArrayOutputStream));
         boolean var6 = false;
         boolean var7 = false;
         Throwable var17 = (Throwable)null;

         try {
            ObjectOutputStream oos = (ObjectOutputStream)var5;
            int var9 = false;
            oos.writeObject(value);
            byte[] data = byteArrayOutputStream.toByteArray();
            if (saveTime != -1) {
               Intrinsics.checkNotNullExpressionValue(data, "data");
               this.put(key, data, saveTime);
            } else {
               Intrinsics.checkNotNullExpressionValue(data, "data");
               this.put(key, data);
            }

            Unit var18 = Unit.INSTANCE;
         } catch (Throwable var14) {
            var17 = var14;
            throw var14;
         } finally {
            CloseableKt.closeFinally(var5, var17);
         }
      } catch (Exception var16) {
         var16.printStackTrace();
      }

   }

   // $FF: synthetic method
   public static void put$default(ACache var0, String var1, Serializable var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = -1;
      }

      var0.put(var1, var2, var3);
   }

   @Nullable
   public final Object getAsObject(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      byte[] data = this.getAsBinary(key);
      if (data != null) {
         ByteArrayInputStream bis = null;
         ObjectInputStream ois = null;
         boolean var15 = false;

         Object var5;
         label135: {
            try {
               var15 = true;
               bis = new ByteArrayInputStream(data);
               ois = new ObjectInputStream((InputStream)bis);
               var5 = ois.readObject();
               var15 = false;
               break label135;
            } catch (Exception var22) {
               var22.printStackTrace();
               var15 = false;
            } finally {
               if (var15) {
                  try {
                     if (bis != null) {
                        bis.close();
                     }
                  } catch (IOException var17) {
                     var17.printStackTrace();
                  }

                  try {
                     if (ois != null) {
                        ois.close();
                     }
                  } catch (IOException var16) {
                     var16.printStackTrace();
                  }

               }
            }

            try {
               if (bis != null) {
                  bis.close();
               }
            } catch (IOException var21) {
               var21.printStackTrace();
            }

            try {
               if (ois != null) {
                  ois.close();
                  return null;
               }
            } catch (IOException var20) {
               var20.printStackTrace();
            }

            return null;
         }

         try {
            bis.close();
         } catch (IOException var19) {
            var19.printStackTrace();
         }

         try {
            ois.close();
         } catch (IOException var18) {
            var18.printStackTrace();
         }

         return var5;
      } else {
         return null;
      }
   }

   @Nullable
   public final File file(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      ACache.ACacheManager var2 = this.mCache;
      if (var2 != null) {
         boolean var4 = false;
         boolean var5 = false;
         ACache.ACacheManager mCache = var2;
         boolean var7 = false;

         try {
            File f = mCache.newFile(key);
            if (f.exists()) {
               return f;
            }
         } catch (Exception var9) {
            var9.printStackTrace();
         }
      }

      return null;
   }

   public final boolean remove(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      ACache.ACacheManager var2 = this.mCache;
      return var2 == null ? false : var2.remove(key);
   }

   public final void clear() {
      ACache.ACacheManager var1 = this.mCache;
      if (var1 != null) {
         var1.clear();
      }

   }

   @JvmOverloads
   public final void put(@NotNull String key, @NotNull Serializable value) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      put$default(this, key, value, 0, 4, (Object)null);
   }

   // $FF: synthetic method
   public static final HashMap access$getMInstanceMap$cp() {
      return mInstanceMap;
   }

   // $FF: synthetic method
   public ACache(File cacheDir, long max_size, int max_count, DefaultConstructorMarker $constructor_marker) {
      this(cacheDir, max_size, max_count);
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0004H\u0007J&\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"},
      d2 = {"Lio/legado/app/utils/ACache$Companion;", "", "()V", "MAX_COUNT", "", "MAX_SIZE", "TIME_DAY", "TIME_HOUR", "mInstanceMap", "Ljava/util/HashMap;", "", "Lio/legado/app/utils/ACache;", "get", "cacheDir", "Ljava/io/File;", "maxSize", "", "maxCount", "cacheName", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @JvmOverloads
      @NotNull
      public final ACache get(@NotNull String cacheName, long maxSize, int maxCount) {
         Intrinsics.checkNotNullParameter(cacheName, "cacheName");
         File f = new File(ReaderAdapterHelper.INSTANCE.getAdapter().getCacheDir(), cacheName);
         return this.get(f, maxSize, maxCount);
      }

      // $FF: synthetic method
      public static ACache get$default(ACache.Companion var0, String var1, long var2, int var4, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = "ACache";
         }

         if ((var5 & 2) != 0) {
            var2 = 50000000L;
         }

         if ((var5 & 4) != 0) {
            var4 = Integer.MAX_VALUE;
         }

         return var0.get(var1, var2, var4);
      }

      @JvmOverloads
      @NotNull
      public final ACache get(@NotNull File cacheDir, long maxSize, int maxCount) {
         // $FF: Couldn't be decompiled
      }

      // $FF: synthetic method
      public static ACache get$default(ACache.Companion var0, File var1, long var2, int var4, int var5, Object var6) {
         if ((var5 & 2) != 0) {
            var2 = 50000000L;
         }

         if ((var5 & 4) != 0) {
            var4 = Integer.MAX_VALUE;
         }

         return var0.get(var1, var2, var4);
      }

      @JvmOverloads
      @NotNull
      public final ACache get(@NotNull String cacheName, long maxSize) {
         Intrinsics.checkNotNullParameter(cacheName, "cacheName");
         return get$default(this, (String)cacheName, maxSize, 0, 4, (Object)null);
      }

      @JvmOverloads
      @NotNull
      public final ACache get(@NotNull String cacheName) {
         Intrinsics.checkNotNullParameter(cacheName, "cacheName");
         return get$default(this, (String)cacheName, 0L, 0, 6, (Object)null);
      }

      @JvmOverloads
      @NotNull
      public final ACache get() {
         return get$default(this, (String)null, 0L, 0, 7, (Object)null);
      }

      @JvmOverloads
      @NotNull
      public final ACache get(@NotNull File cacheDir, long maxSize) {
         Intrinsics.checkNotNullParameter(cacheDir, "cacheDir");
         return get$default(this, (File)cacheDir, maxSize, 0, 4, (Object)null);
      }

      @JvmOverloads
      @NotNull
      public final ACache get(@NotNull File cacheDir) {
         Intrinsics.checkNotNullParameter(cacheDir, "cacheDir");
         return get$default(this, (File)cacheDir, 0L, 0, 6, (Object)null);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bJ \u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u001b\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00122\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0004H\u0002J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\bJ\u0016\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0006J\u0016\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001d"},
      d2 = {"Lio/legado/app/utils/ACache$Utils;", "", "()V", "mSeparator", "", "clearDateInfo", "", "data", "", "strInfo", "copyOfRange", "original", "from", "", "to", "createDateInfo", "second", "getDateInfoFromDate", "", "([B)[Ljava/lang/String;", "hasDateInfo", "", "indexOf", "c", "isDue", "str", "newByteArrayWithDateInfo", "data2", "newStringWithDateInfo", "reader-pro"}
   )
   private static final class Utils {
      @NotNull
      public static final ACache.Utils INSTANCE = new ACache.Utils();
      private static final char mSeparator = ' ';

      public final boolean isDue(@NotNull String str) {
         Intrinsics.checkNotNullParameter(str, "str");
         Charset var3 = Charsets.UTF_8;
         boolean var4 = false;
         byte[] var10001 = str.getBytes(var3);
         Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).getBytes(charset)");
         return this.isDue(var10001);
      }

      public final boolean isDue(@NotNull byte[] data) {
         Intrinsics.checkNotNullParameter(data, "data");

         try {
            String[] text = this.getDateInfoFromDate(data);
            if (text != null && text.length == 2) {
               String var10000;
               String saveTimeStr;
               for(saveTimeStr = text[0]; StringsKt.startsWith$default(saveTimeStr, "0", false, 2, (Object)null); saveTimeStr = var10000) {
                  byte var5 = 1;
                  boolean var6 = false;
                  if (saveTimeStr == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                  }

                  var10000 = saveTimeStr.substring(var5);
                  Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
               }

               Long saveTime = Long.valueOf(saveTimeStr);
               Long deleteAfter = Long.valueOf(text[1]);
               if (System.currentTimeMillis() > saveTime + deleteAfter * (long)1000) {
                  return true;
               }
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }

         return false;
      }

      @NotNull
      public final String newStringWithDateInfo(int second, @NotNull String strInfo) {
         Intrinsics.checkNotNullParameter(strInfo, "strInfo");
         return Intrinsics.stringPlus(this.createDateInfo(second), strInfo);
      }

      @NotNull
      public final byte[] newByteArrayWithDateInfo(int second, @NotNull byte[] data2) {
         Intrinsics.checkNotNullParameter(data2, "data2");
         String var4 = this.createDateInfo(second);
         Charset var5 = Charsets.UTF_8;
         boolean var6 = false;
         if (var4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
         } else {
            byte[] var10000 = var4.getBytes(var5);
            Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).getBytes(charset)");
            byte[] data1 = var10000;
            byte[] retData = new byte[data1.length + data2.length];
            System.arraycopy(data1, 0, retData, 0, data1.length);
            System.arraycopy(data2, 0, retData, data1.length, data2.length);
            return retData;
         }
      }

      @Nullable
      public final String clearDateInfo(@Nullable String strInfo) {
         if (strInfo != null) {
            boolean var4 = false;
            boolean var5 = false;
            int var7 = false;
            ACache.Utils var10000 = INSTANCE;
            Charset var9 = Charsets.UTF_8;
            boolean var10 = false;
            if (strInfo == null) {
               throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }

            byte[] var10001 = strInfo.getBytes(var9);
            Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).getBytes(charset)");
            if (var10000.hasDateInfo(var10001)) {
               int var12 = StringsKt.indexOf$default((CharSequence)strInfo, ' ', 0, false, 6, (Object)null) + 1;
               var10 = false;
               if (strInfo == null) {
                  throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
               }

               String var11 = strInfo.substring(var12);
               Intrinsics.checkNotNullExpressionValue(var11, "(this as java.lang.String).substring(startIndex)");
               return var11;
            }
         }

         return strInfo;
      }

      @NotNull
      public final byte[] clearDateInfo(@NotNull byte[] data) {
         Intrinsics.checkNotNullParameter(data, "data");
         return this.hasDateInfo(data) ? this.copyOfRange(data, this.indexOf(data, ' ') + 1, data.length) : data;
      }

      public final boolean hasDateInfo(@Nullable byte[] data) {
         boolean var4;
         if (data != null && data.length > 15) {
            byte var10000 = data[13];
            byte var2 = 45;
            boolean var3 = false;
            if (var10000 == (byte)var2 && this.indexOf(data, ' ') > 14) {
               var4 = true;
               return var4;
            }
         }

         var4 = false;
         return var4;
      }

      @Nullable
      public final String[] getDateInfoFromDate(@NotNull byte[] data) {
         Intrinsics.checkNotNullParameter(data, "data");
         if (this.hasDateInfo(data)) {
            byte[] var3 = this.copyOfRange(data, 0, 13);
            boolean var4 = false;
            String saveDate = new String(var3, Charsets.UTF_8);
            byte[] var7 = this.copyOfRange(data, 14, this.indexOf(data, ' '));
            boolean var5 = false;
            String deleteAfter = new String(var7, Charsets.UTF_8);
            String[] var8 = new String[]{saveDate, deleteAfter};
            return var8;
         } else {
            return null;
         }
      }

      private final int indexOf(byte[] data, char c) {
         int var3 = 0;
         int var4 = data.length + -1;
         if (var3 <= var4) {
            do {
               int i = var3++;
               byte var10000 = data[i];
               boolean var7 = false;
               if (var10000 == (byte)c) {
                  return i;
               }
            } while(var3 <= var4);
         }

         return -1;
      }

      private final byte[] copyOfRange(byte[] original, int from, int to) {
         int newLength = to - from;
         boolean var5 = newLength >= 0;
         boolean var6 = false;
         boolean var7 = false;
         if (!var5) {
            int var8 = false;
            String var11 = from + " > " + to;
            throw (Throwable)(new IllegalArgumentException(var11.toString()));
         } else {
            byte[] copy = new byte[newLength];
            int var10 = original.length - from;
            var7 = false;
            System.arraycopy(original, from, copy, 0, Math.min(var10, newLength));
            return copy;
         }
      }

      private final String createDateInfo(int second) {
         StringBuilder currentTime = new StringBuilder(System.currentTimeMillis() + "");

         while(currentTime.length() < 13) {
            currentTime.insert(0, "0");
         }

         return "" + currentTime + '-' + second + ' ';
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0096\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\u0006\u0010\u0015\u001a\u00020\u0012J\u0011\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\u0002J\u000e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0018J\u000e\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0003J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u001f\u001a\u00020\u0005H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000RN\u0010\r\u001aB\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00030\u0003\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00050\u0005 \u000f* \u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00030\u0003\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "},
      d2 = {"Lio/legado/app/utils/ACache$ACacheManager;", "", "cacheDir", "Ljava/io/File;", "sizeLimit", "", "countLimit", "", "(Lio/legado/app/utils/ACache;Ljava/io/File;JI)V", "cacheCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "cacheSize", "Ljava/util/concurrent/atomic/AtomicLong;", "lastUsageDates", "", "kotlin.jvm.PlatformType", "", "calculateCacheSizeAndCacheCount", "", "calculateSize", "file", "clear", "get", "key", "", "newFile", "newFileFromHashCode", "hashCode", "put", "remove", "", "removeNext", "reader-pro"}
   )
   public class ACacheManager {
      @NotNull
      private File cacheDir;
      private final long sizeLimit;
      private final int countLimit;
      @NotNull
      private final AtomicLong cacheSize;
      @NotNull
      private final AtomicInteger cacheCount;
      private final Map<File, Long> lastUsageDates;

      public ACacheManager(@NotNull File cacheDir, long sizeLimit, int countLimit) {
         Intrinsics.checkNotNullParameter(ACache.this, "this$0");
         Intrinsics.checkNotNullParameter(cacheDir, "cacheDir");
         super();
         this.cacheDir = cacheDir;
         this.sizeLimit = sizeLimit;
         this.countLimit = countLimit;
         this.cacheSize = new AtomicLong();
         this.cacheCount = new AtomicInteger();
         this.lastUsageDates = Collections.synchronizedMap((Map)(new HashMap()));
         this.calculateCacheSizeAndCacheCount();
      }

      private final void calculateCacheSizeAndCacheCount() {
         (new Thread(ACache.ACacheManager::calculateCacheSizeAndCacheCount$lambda-0)).start();
      }

      public final void put(@NotNull File file) {
         Intrinsics.checkNotNullParameter(file, "file");

         try {
            long valueSize;
            for(int curCacheCount = this.cacheCount.get(); curCacheCount + 1 > this.countLimit; curCacheCount = this.cacheCount.addAndGet(-1)) {
               valueSize = this.removeNext();
               this.cacheSize.addAndGet(-valueSize);
            }

            this.cacheCount.addAndGet(1);
            valueSize = this.calculateSize(file);

            long freedSize;
            for(long curCacheSize = this.cacheSize.get(); curCacheSize + valueSize > this.sizeLimit; curCacheSize = this.cacheSize.addAndGet(-freedSize)) {
               freedSize = this.removeNext();
            }

            this.cacheSize.addAndGet(valueSize);
            freedSize = System.currentTimeMillis();
            file.setLastModified(freedSize);
            Map var9 = this.lastUsageDates;
            Intrinsics.checkNotNullExpressionValue(var9, "lastUsageDates");
            Long var10 = freedSize;
            boolean var11 = false;
            var9.put(file, var10);
         } catch (Exception var12) {
            var12.printStackTrace();
         }

      }

      @NotNull
      public final File get(@NotNull String key) {
         Intrinsics.checkNotNullParameter(key, "key");
         File file = this.newFile(key);
         long currentTime = System.currentTimeMillis();
         file.setLastModified(currentTime);
         Map var5 = this.lastUsageDates;
         Intrinsics.checkNotNullExpressionValue(var5, "lastUsageDates");
         Long var6 = currentTime;
         boolean var7 = false;
         var5.put(file, var6);
         return file;
      }

      @NotNull
      public final File newFile(@NotNull String key) {
         Intrinsics.checkNotNullParameter(key, "key");
         return new File(this.cacheDir, key.hashCode() + "");
      }

      @NotNull
      public final File newFileFromHashCode(@NotNull String hashCode) {
         Intrinsics.checkNotNullParameter(hashCode, "hashCode");
         return new File(this.cacheDir, hashCode);
      }

      public final boolean remove(@NotNull String key) {
         Intrinsics.checkNotNullParameter(key, "key");
         File image = this.get(key);
         return image.delete();
      }

      public final void clear() {
         try {
            this.lastUsageDates.clear();
            this.cacheSize.set(0L);
            File[] files = this.cacheDir.listFiles();
            if (files != null) {
               File[] var2 = files;
               int var3 = 0;
               int var4 = files.length;

               while(var3 < var4) {
                  File f = var2[var3];
                  ++var3;
                  f.delete();
               }
            }
         } catch (Exception var6) {
            var6.printStackTrace();
         }

      }

      private final long removeNext() {
         try {
            if (this.lastUsageDates.isEmpty()) {
               return 0L;
            } else {
               Object oldestUsage = null;
               Object mostLongUsedFile = null;
               Set entries = this.lastUsageDates.entrySet();
               Map var4 = this.lastUsageDates;
               Intrinsics.checkNotNullExpressionValue(var4, "lastUsageDates");
               boolean var5 = false;
               boolean var6 = false;
               synchronized(var4) {
                  int var7 = false;
                  Iterator var8 = entries.iterator();

                  while(var8.hasNext()) {
                     Entry var9 = (Entry)var8.next();
                     boolean var11 = false;
                     File key = (File)var9.getKey();
                     boolean var13 = false;
                     Long lastValueUsage = (Long)var9.getValue();
                     if (mostLongUsedFile == null) {
                        mostLongUsedFile = key;
                        oldestUsage = lastValueUsage;
                     } else {
                        Intrinsics.checkNotNullExpressionValue(lastValueUsage, "lastValueUsage");
                        long var10000 = lastValueUsage;
                        Intrinsics.checkNotNull(oldestUsage);
                        if (var10000 < ((Number)oldestUsage).longValue()) {
                           oldestUsage = lastValueUsage;
                           mostLongUsedFile = key;
                        }
                     }
                  }

                  Unit var17 = Unit.INSTANCE;
               }

               long fileSize = 0L;
               if (mostLongUsedFile != null) {
                  fileSize = this.calculateSize(mostLongUsedFile);
                  if (mostLongUsedFile.delete()) {
                     this.lastUsageDates.remove(mostLongUsedFile);
                  }
               }

               return fileSize;
            }
         } catch (Exception var15) {
            var15.printStackTrace();
            return 0L;
         }
      }

      private final long calculateSize(File file) {
         return file.length();
      }

      private static final void calculateCacheSizeAndCacheCount$lambda_0/* $FF was: calculateCacheSizeAndCacheCount$lambda-0*/(ACache.ACacheManager this$0) {
         Intrinsics.checkNotNullParameter(this$0, "this$0");

         try {
            int size = 0;
            int count = 0;
            File[] cachedFiles = this$0.cacheDir.listFiles();
            if (cachedFiles != null) {
               File[] var4 = cachedFiles;
               int var5 = 0;
               int var6 = cachedFiles.length;

               while(var5 < var6) {
                  File cachedFile = var4[var5];
                  ++var5;
                  Intrinsics.checkNotNullExpressionValue(cachedFile, "cachedFile");
                  size += (int)this$0.calculateSize(cachedFile);
                  ++count;
                  Map var8 = this$0.lastUsageDates;
                  Intrinsics.checkNotNullExpressionValue(var8, "lastUsageDates");
                  Long var9 = cachedFile.lastModified();
                  boolean var10 = false;
                  var8.put(cachedFile, var9);
               }

               this$0.cacheSize.set((long)size);
               this$0.cacheCount.set(count);
            }
         } catch (Exception var11) {
            var11.printStackTrace();
         }

      }
   }
}
