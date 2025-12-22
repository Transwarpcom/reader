package com.htmake.reader.init;

import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.RemoteWebview;
import io.legado.app.adapters.ReaderAdapterInterface;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u001f\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004¢\u0006\u0002\u0010\bJ\u0091\u0001\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ!\u0010\u001b\u001a\u00020\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004H\u0016¢\u0006\u0002\u0010\bJ\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"},
   d2 = {"Lcom/htmake/reader/init/ReaderAdapter;", "Lio/legado/app/adapters/ReaderAdapterInterface;", "()V", "getCacheDir", "", "getRelativePath", "subDirFiles", "", "([Ljava/lang/String;)Ljava/lang/String;", "getStrResponseByRemoteWebview", "Lio/legado/app/help/http/StrResponse;", "url", "html", "encode", "tag", "headerMap", "", "sourceRegex", "javaScript", "proxy", "post", "", "body", "userNameSpace", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWorkDir", "subPath", "reader-pro"}
)
public final class ReaderAdapter implements ReaderAdapterInterface {
   @NotNull
   public static final ReaderAdapter INSTANCE = new ReaderAdapter();

   private ReaderAdapter() {
   }

   @NotNull
   public String getWorkDir(@NotNull String subPath) {
      Intrinsics.checkNotNullParameter(subPath, "subPath");
      return ExtKt.getWorkDir(subPath);
   }

   @NotNull
   public String getWorkDir(@NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      return ExtKt.getWorkDir(this.getRelativePath((String[])Arrays.copyOf(subDirFiles, subDirFiles.length)));
   }

   @NotNull
   public final String getRelativePath(@NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      StringBuilder path = new StringBuilder("");
      int $i$f$forEach = false;
      String[] var5 = subDirFiles;
      int var6 = subDirFiles.length;

      boolean var10;
      for(int var7 = 0; var7 < var6; ++var7) {
         Object element$iv = var5[var7];
         var10 = false;
         CharSequence var11 = (CharSequence)element$iv;
         boolean var12 = false;
         if (var11.length() > 0) {
            path.append(File.separator).append(element$iv);
         }
      }

      String var3 = path.toString();
      $i$f$forEach = false;
      boolean var13 = false;
      int var14 = false;
      Intrinsics.checkNotNullExpressionValue(var3, "it");
      String var10000;
      if (StringsKt.startsWith$default(var3, "/", false, 2, (Object)null)) {
         byte var9 = 1;
         var10 = false;
         var10000 = var3.substring(var9);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
      } else {
         var10000 = var3;
      }

      return var10000;
   }

   @NotNull
   public String getCacheDir() {
      String[] var1 = new String[]{"storage", "cache"};
      return this.getWorkDir(var1);
   }

   @Nullable
   public Object getStrResponseByRemoteWebview(@Nullable String url, @Nullable String html, @Nullable String encode, @Nullable String tag, @Nullable Map<String, String> headerMap, @Nullable String sourceRegex, @Nullable String javaScript, @Nullable String proxy, boolean post, @Nullable String body, @NotNull String userNameSpace, @Nullable DebugLog debugLog, @NotNull Continuation<? super StrResponse> $completion) {
      String encodeStr = encode;
      CharSequence var15 = (CharSequence)encode;
      boolean var16 = false;
      boolean var17 = false;
      if (var15 == null || var15.length() == 0) {
         encodeStr = headerMap == null ? null : (String)headerMap.get("charset");
      }

      return RemoteWebview.INSTANCE.getStrResponse(url, html, encodeStr, tag, headerMap, sourceRegex, javaScript, proxy, post, body, userNameSpace, debugLog, $completion);
   }
}
