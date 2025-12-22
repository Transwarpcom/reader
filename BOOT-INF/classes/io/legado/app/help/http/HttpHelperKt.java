package io.legado.app.help.http;

import io.legado.app.model.DebugLog;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.Proxy.Type;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"'\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\u0005\u001a\u0004\b\t\u0010\n¨\u0006\u0010"},
   d2 = {"okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "okHttpClient$delegate", "Lkotlin/Lazy;", "proxyClientCache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "getProxyClientCache", "()Ljava/util/concurrent/ConcurrentHashMap;", "proxyClientCache$delegate", "getProxyClient", "proxy", "debugLog", "Lio/legado/app/model/DebugLog;", "reader-pro"}
)
public final class HttpHelperKt {
   @NotNull
   private static final Lazy proxyClientCache$delegate;
   @NotNull
   private static final Lazy okHttpClient$delegate;

   private static final ConcurrentHashMap<String, OkHttpClient> getProxyClientCache() {
      Lazy var0 = proxyClientCache$delegate;
      Object var1 = null;
      boolean var3 = false;
      return (ConcurrentHashMap)var0.getValue();
   }

   @NotNull
   public static final OkHttpClient getOkHttpClient() {
      Lazy var0 = okHttpClient$delegate;
      Object var1 = null;
      boolean var3 = false;
      return (OkHttpClient)var0.getValue();
   }

   @NotNull
   public static final OkHttpClient getProxyClient(@Nullable String proxy, @Nullable DebugLog debugLog) {
      CharSequence var2 = (CharSequence)proxy;
      boolean var3 = false;
      boolean var4 = false;
      if (var2 == null || StringsKt.isBlank(var2)) {
         if (debugLog == null) {
            return getOkHttpClient();
         } else {
            Builder builder = getOkHttpClient().newBuilder();
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor((Logger)debugLog);
            logInterceptor.setLevel(Level.BODY);
            builder.addNetworkInterceptor((Interceptor)logInterceptor);
            return builder.build();
         }
      } else {
         if (debugLog == null) {
            OkHttpClient var14 = (OkHttpClient)getProxyClientCache().get(proxy);
            if (var14 != null) {
               var4 = false;
               boolean var20 = false;
               int var21 = false;
               return var14;
            }
         }

         Regex r = new Regex("(http|socks4|socks5)://(.*):(\\d{2,5})(@.*@.*)?");
         Sequence ms = Regex.findAll$default(r, (CharSequence)proxy, 0, 2, (Object)null);
         MatchResult group = (MatchResult)SequencesKt.first(ms);
         final ObjectRef username = new ObjectRef();
         username.element = "";
         final ObjectRef password = new ObjectRef();
         password.element = "";
         String type = Intrinsics.areEqual(group.getGroupValues().get(1), "http") ? "http" : "socks";
         String host = (String)group.getGroupValues().get(2);
         String var10 = (String)group.getGroupValues().get(3);
         boolean var11 = false;
         int port = Integer.parseInt(var10);
         if (!Intrinsics.areEqual(group.getGroupValues().get(4), "")) {
            CharSequence var10001 = (CharSequence)group.getGroupValues().get(4);
            String[] var22 = new String[]{"@"};
            username.element = StringsKt.split$default(var10001, var22, false, 0, 6, (Object)null).get(1);
            var10001 = (CharSequence)group.getGroupValues().get(4);
            var22 = new String[]{"@"};
            password.element = StringsKt.split$default(var10001, var22, false, 0, 6, (Object)null).get(2);
         }

         if (!Intrinsics.areEqual(type, "direct") && !Intrinsics.areEqual(host, "")) {
            Builder builder = getOkHttpClient().newBuilder();
            if (Intrinsics.areEqual(type, "http")) {
               builder.proxy(new Proxy(Type.HTTP, (SocketAddress)(new InetSocketAddress(host, port))));
            } else {
               builder.proxy(new Proxy(Type.SOCKS, (SocketAddress)(new InetSocketAddress(host, port))));
            }

            if (!Intrinsics.areEqual(username.element, "") && !Intrinsics.areEqual(password.element, "")) {
               <undefinedtype> proxyAuthenticator = new Authenticator() {
                  @NotNull
                  public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
                     Intrinsics.checkNotNullParameter(response, "response");
                     String credential = Credentials.basic$default((String)username.element, (String)password.element, (Charset)null, 4, (Object)null);
                     return response.request().newBuilder().header("Proxy-Authorization", credential).build();
                  }
               };
               builder.proxyAuthenticator((Authenticator)proxyAuthenticator);
            }

            if (debugLog != null) {
               HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor((Logger)debugLog);
               logInterceptor.setLevel(Level.BODY);
               builder.addNetworkInterceptor((Interceptor)logInterceptor);
               return builder.build();
            } else {
               OkHttpClient proxyClient = builder.build();
               Map var12 = (Map)getProxyClientCache();
               boolean var13 = false;
               var12.put(proxy, proxyClient);
               return proxyClient;
            }
         } else {
            return getOkHttpClient();
         }
      }
   }

   // $FF: synthetic method
   public static OkHttpClient getProxyClient$default(String var0, DebugLog var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var0 = null;
      }

      if ((var2 & 2) != 0) {
         var1 = null;
      }

      return getProxyClient(var0, var1);
   }

   static {
      proxyClientCache$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      okHttpClient$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }
}
