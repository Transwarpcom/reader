package io.legado.app.adapters;

import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J©\u0001\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u00020\u00032\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0018\"\u00020\u0003H&¢\u0006\u0002\u0010\u0019J\u0012\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u0003H&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"},
   d2 = {"Lio/legado/app/adapters/ReaderAdapterInterface;", "", "getCacheDir", "", "getStrResponseByRemoteWebview", "Lio/legado/app/help/http/StrResponse;", "url", "html", "encode", "tag", "headerMap", "", "sourceRegex", "javaScript", "proxy", "post", "", "body", "userNameSpace", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWorkDir", "subDirFiles", "", "([Ljava/lang/String;)Ljava/lang/String;", "subPath", "reader-pro"}
)
public interface ReaderAdapterInterface {
   @NotNull
   String getWorkDir(@NotNull String subPath);

   @NotNull
   String getWorkDir(@NotNull String... subDirFiles);

   @NotNull
   String getCacheDir();

   @Nullable
   Object getStrResponseByRemoteWebview(@Nullable String url, @Nullable String html, @Nullable String encode, @Nullable String tag, @Nullable Map<String, String> headerMap, @Nullable String sourceRegex, @Nullable String javaScript, @Nullable String proxy, boolean post, @Nullable String body, @NotNull String userNameSpace, @Nullable DebugLog debugLog, @NotNull Continuation<? super StrResponse> $completion);

   @Metadata(
      mv = {1, 5, 1},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      public static String getWorkDir$default(ReaderAdapterInterface var0, String var1, int var2, Object var3) {
         if (var3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWorkDir");
         } else {
            if ((var2 & 1) != 0) {
               var1 = "";
            }

            return var0.getWorkDir(var1);
         }
      }

      // $FF: synthetic method
      public static Object getStrResponseByRemoteWebview$default(ReaderAdapterInterface var0, String var1, String var2, String var3, String var4, Map var5, String var6, String var7, String var8, boolean var9, String var10, String var11, DebugLog var12, Continuation var13, int var14, Object var15) {
         if (var15 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getStrResponseByRemoteWebview");
         } else {
            if ((var14 & 1) != 0) {
               var1 = null;
            }

            if ((var14 & 2) != 0) {
               var2 = null;
            }

            if ((var14 & 4) != 0) {
               var3 = null;
            }

            if ((var14 & 8) != 0) {
               var4 = null;
            }

            if ((var14 & 16) != 0) {
               var5 = null;
            }

            if ((var14 & 32) != 0) {
               var6 = null;
            }

            if ((var14 & 64) != 0) {
               var7 = null;
            }

            if ((var14 & 128) != 0) {
               var8 = null;
            }

            if ((var14 & 256) != 0) {
               var9 = false;
            }

            if ((var14 & 512) != 0) {
               var10 = null;
            }

            if ((var14 & 1024) != 0) {
               var11 = "";
            }

            if ((var14 & 2048) != 0) {
               var12 = null;
            }

            return var0.getStrResponseByRemoteWebview(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
         }
      }
   }
}
