package io.legado.app.help;

import io.legado.app.adapters.ReaderAdapterHelper;
import io.legado.app.adapters.ReaderAdapterInterface;
import io.legado.app.model.analyzeRule.QueryTTF;
import io.legado.app.utils.ACache;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0013\u001a\u00020\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u0003J\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0013\u001a\u00020\u0003J\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010\u001dJ\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010 J\u0015\u0010!\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010\"J\u0010\u0010#\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u0003J\"\u0010$\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00012\b\b\u0002\u0010&\u001a\u00020\u001fH\u0007J \u0010'\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u001fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bRB\u0010\t\u001a6\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\nj\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006("},
   d2 = {"Lio/legado/app/help/CacheManager;", "", "userNameSpace", "", "(Ljava/lang/String;)V", "cacheInstance", "Lio/legado/app/utils/ACache;", "getCacheInstance", "()Lio/legado/app/utils/ACache;", "queryTTFMap", "Ljava/util/HashMap;", "Lkotlin/Pair;", "", "Lio/legado/app/model/analyzeRule/QueryTTF;", "Lkotlin/collections/HashMap;", "getUserNameSpace", "()Ljava/lang/String;", "delete", "", "key", "get", "getByteArray", "", "getDouble", "", "(Ljava/lang/String;)Ljava/lang/Double;", "getFile", "getFloat", "", "(Ljava/lang/String;)Ljava/lang/Float;", "getInt", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "getLong", "(Ljava/lang/String;)Ljava/lang/Long;", "getQueryTTF", "put", "value", "saveTime", "putFile", "reader-pro"}
)
public final class CacheManager {
   @NotNull
   private final String userNameSpace;
   @NotNull
   private final HashMap<String, Pair<Long, QueryTTF>> queryTTFMap;
   @NotNull
   private final ACache cacheInstance;

   public CacheManager(@NotNull String userNameSpace) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      super();
      this.userNameSpace = userNameSpace;
      boolean var2 = false;
      this.queryTTFMap = new HashMap();
      ReaderAdapterInterface var10002 = ReaderAdapterHelper.INSTANCE.getAdapter();
      String[] var3 = new String[]{"storage", "cache", "runtimeCache", this.userNameSpace};
      File cacheDir = new File(var10002.getWorkDir(var3));
      this.cacheInstance = ACache.Companion.get(cacheDir, 50000000L, 1000000);
   }

   @NotNull
   public final String getUserNameSpace() {
      return this.userNameSpace;
   }

   @NotNull
   public final ACache getCacheInstance() {
      return this.cacheInstance;
   }

   @JvmOverloads
   public final void put(@NotNull String key, @NotNull Object value, int saveTime) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      CharSequence var4 = (CharSequence)key;
      boolean var5 = false;
      if (var4.length() != 0) {
         long deadline = saveTime == 0 ? 0L : System.currentTimeMillis() + (long)(saveTime * 1000);
         if (value instanceof QueryTTF) {
            Map var7 = (Map)this.queryTTFMap;
            Pair var8 = new Pair(deadline, value);
            boolean var9 = false;
            var7.put(key, var8);
         } else if (value instanceof byte[]) {
            this.cacheInstance.put(key, (byte[])value, saveTime);
         } else {
            this.cacheInstance.put(key, value.toString(), saveTime);
         }

      }
   }

   // $FF: synthetic method
   public static void put$default(CacheManager var0, String var1, Object var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      var0.put(var1, var2, var3);
   }

   @Nullable
   public final String get(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      CharSequence var2 = (CharSequence)key;
      boolean var3 = false;
      return var2.length() == 0 ? null : this.cacheInstance.getAsString(key);
   }

   @Nullable
   public final Integer getInt(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      String var2 = this.get(key);
      return var2 == null ? null : StringsKt.toIntOrNull(var2);
   }

   @Nullable
   public final Long getLong(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      String var2 = this.get(key);
      return var2 == null ? null : StringsKt.toLongOrNull(var2);
   }

   @Nullable
   public final Double getDouble(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      String var2 = this.get(key);
      return var2 == null ? null : StringsKt.toDoubleOrNull(var2);
   }

   @Nullable
   public final Float getFloat(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      String var2 = this.get(key);
      return var2 == null ? null : StringsKt.toFloatOrNull(var2);
   }

   @Nullable
   public final byte[] getByteArray(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      CharSequence var2 = (CharSequence)key;
      boolean var3 = false;
      return var2.length() == 0 ? null : this.cacheInstance.getAsBinary(key);
   }

   @Nullable
   public final QueryTTF getQueryTTF(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      Pair var4 = (Pair)this.queryTTFMap.get(key);
      if (var4 == null) {
         return null;
      } else {
         return ((Number)var4.getFirst()).longValue() != 0L && ((Number)var4.getFirst()).longValue() <= System.currentTimeMillis() ? null : (QueryTTF)var4.getSecond();
      }
   }

   public final void putFile(@NotNull String key, @NotNull String value, int saveTime) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      CharSequence var4 = (CharSequence)key;
      boolean var5 = false;
      if (var4.length() != 0) {
         this.cacheInstance.put(key, value, saveTime);
      }
   }

   // $FF: synthetic method
   public static void putFile$default(CacheManager var0, String var1, String var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      var0.putFile(var1, var2, var3);
   }

   @Nullable
   public final String getFile(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      CharSequence var2 = (CharSequence)key;
      boolean var3 = false;
      return var2.length() == 0 ? null : this.cacheInstance.getAsString(key);
   }

   public final void delete(@NotNull String key) {
      Intrinsics.checkNotNullParameter(key, "key");
      CharSequence var2 = (CharSequence)key;
      boolean var3 = false;
      if (var2.length() != 0) {
         this.cacheInstance.remove(key);
      }
   }

   @JvmOverloads
   public final void put(@NotNull String key, @NotNull Object value) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      put$default(this, key, value, 0, 4, (Object)null);
   }
}
