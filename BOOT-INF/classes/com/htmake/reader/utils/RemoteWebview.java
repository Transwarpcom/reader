package com.htmake.reader.utils;

import io.legado.app.help.http.CookieStore;
import io.legado.app.help.http.HttpHelperKt;
import io.legado.app.help.http.OkHttpUtilsKt;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.utils.NetworkUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J©\u0001\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"},
   d2 = {"Lcom/htmake/reader/utils/RemoteWebview;", "", "()V", "remoteWebviewApi", "", "getRemoteWebviewApi", "()Ljava/lang/String;", "setRemoteWebviewApi", "(Ljava/lang/String;)V", "getStrResponse", "Lio/legado/app/help/http/StrResponse;", "url", "html", "encode", "tag", "headerMap", "", "sourceRegex", "javaScript", "proxy", "post", "", "body", "userNameSpace", "debugLog", "Lio/legado/app/model/DebugLog;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lio/legado/app/model/DebugLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setRemoteApi", "", "remoteApi", "reader-pro"}
)
public final class RemoteWebview {
   @NotNull
   public static final RemoteWebview INSTANCE = new RemoteWebview();
   @NotNull
   private static String remoteWebviewApi = "";

   private RemoteWebview() {
   }

   @NotNull
   public final String getRemoteWebviewApi() {
      return remoteWebviewApi;
   }

   public final void setRemoteWebviewApi(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      remoteWebviewApi = var1;
   }

   public final void setRemoteApi(@NotNull String remoteApi) {
      Intrinsics.checkNotNullParameter(remoteApi, "remoteApi");
      remoteWebviewApi = remoteApi;
   }

   @Nullable
   public final Object getStrResponse(@Nullable String url, @Nullable String html, @Nullable String encode, @Nullable String tag, @Nullable Map<String, String> headerMap, @Nullable String sourceRegex, @Nullable String javaScript, @Nullable String proxy, boolean post, @Nullable String body, @NotNull String userNameSpace, @Nullable DebugLog debugLog, @NotNull Continuation<? super StrResponse> $completion) {
      Object $continuation;
      label65: {
         if (var13 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var13;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label65;
            }
         }

         $continuation = new ContinuationImpl(var13) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return RemoteWebview.this.getStrResponse((String)null, (String)null, (String)null, (String)null, (Map)null, (String)null, (String)null, (String)null, false, (String)null, (String)null, (DebugLog)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var28 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         CharSequence var14 = (CharSequence)this.getRemoteWebviewApi();
         boolean var15 = false;
         boolean var16 = false;
         if (var14 == null || var14.length() == 0) {
            throw new Exception("不支持webview");
         }

         final ObjectRef requestBody = new ObjectRef();
         Pair[] var30 = new Pair[]{TuplesKt.to("url", url), TuplesKt.to("html", html), TuplesKt.to("headers", headerMap), TuplesKt.to("js_source", javaScript), TuplesKt.to("proxy", proxy), TuplesKt.to("http_method", post ? "POST" : "GET"), TuplesKt.to("body", body), TuplesKt.to("encode", encode), TuplesKt.to("tag", tag), TuplesKt.to("sourceRegex", sourceRegex)};
         requestBody.element = ExtKt.jsonEncode$default(MapsKt.mapOf(var30), false, 2, (Object)null);
         final ObjectRef remoteApi = new ObjectRef();
         remoteApi.element = Intrinsics.stringPlus(this.getRemoteWebviewApi(), "/render.html");
         OkHttpClient var33 = HttpHelperKt.getProxyClient$default((String)null, debugLog, 1, (Object)null);
         Function1 var10002 = (Function1)(new Function1<Builder, Unit>() {
            public final void invoke(@NotNull Builder $this$newCallStrResponse) {
               Intrinsics.checkNotNullParameter($this$newCallStrResponse, "$this$newCallStrResponse");
               $this$newCallStrResponse.url((String)remoteApi.element);
               OkHttpUtilsKt.postJson($this$newCallStrResponse, (String)requestBody.element);
            }
         });
         ((<undefinedtype>)$continuation).L$0 = url;
         ((<undefinedtype>)$continuation).L$1 = userNameSpace;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = OkHttpUtilsKt.newCallStrResponse(var33, 0, var10002, (Continuation)$continuation);
         if (var10000 == var28) {
            return var28;
         }
         break;
      case 1:
         userNameSpace = (String)((<undefinedtype>)$continuation).L$1;
         url = (String)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      StrResponse strResponse = (StrResponse)var10000;
      if (url != null) {
         String domain = NetworkUtils.INSTANCE.getSubDomain(url);
         CharSequence var18 = (CharSequence)domain;
         boolean var19 = false;
         if (var18.length() > 0) {
            List cookieList = strResponse.getRaw().headers("Set-Cookie");
            if (cookieList.size() > 0) {
               CookieStore cookieStore = new CookieStore(userNameSpace);
               Iterable $this$forEach$iv = (Iterable)cookieList;
               int $i$f$forEach = false;
               Iterator var22 = $this$forEach$iv.iterator();

               while(var22.hasNext()) {
                  Object element$iv = var22.next();
                  String it = (String)element$iv;
                  int var25 = false;
                  cookieStore.replaceCookie(Intrinsics.stringPlus(domain, "_cookieJar"), it);
               }
            }
         }
      }

      return new StrResponse(url == null ? "" : url, strResponse.getBody());
   }

   // $FF: synthetic method
   public static Object getStrResponse$default(RemoteWebview var0, String var1, String var2, String var3, String var4, Map var5, String var6, String var7, String var8, boolean var9, String var10, String var11, DebugLog var12, Continuation var13, int var14, Object var15) {
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

      return var0.getStrResponse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }
}
