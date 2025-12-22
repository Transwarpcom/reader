package io.legado.app.help.http;

import io.legado.app.utils.EncodingDetect;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.Utf8BomUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.Result.Companion;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Request.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a0\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\r\u001a\u00020\u000e\u001a8\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a8\u0010\u0018\u001a\u00020\u0007*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a8\u0010\u0019\u001a\u00020\u0010*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a8\u0010\u001a\u001a\u00020\u001b*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a(\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\r\u001a\u00020\u000e\u001a\u0014\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u001a(\u0010 \u001a\u00020\u0001*\u00020\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\"0\u0004\u001a\u0016\u0010#\u001a\u00020\u0005*\u00020\u00102\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"},
   d2 = {"addHeaders", "", "Lokhttp3/Request$Builder;", "headers", "", "", "await", "Lokhttp3/Response;", "Lokhttp3/Call;", "(Lokhttp3/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "url", "queryMap", "encoded", "", "newCall", "Lokhttp3/ResponseBody;", "Lokhttp3/OkHttpClient;", "retry", "", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lokhttp3/OkHttpClient;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newCallResponse", "newCallResponseBody", "newCallStrResponse", "Lio/legado/app/help/http/StrResponse;", "postForm", "form", "postJson", "json", "postMultipart", "type", "", "text", "encode", "reader-pro"}
)
public final class OkHttpUtilsKt {
   @Nullable
   public static final Object newCallResponse(@NotNull OkHttpClient $this$newCallResponse, int retry, @NotNull Function1<? super Builder, Unit> builder, @NotNull Continuation<? super Response> $completion) {
      return BuildersKt.withContext((CoroutineContext)Dispatchers.getIO(), (Function2)(new Function2<CoroutineScope, Continuation<? super Response>, Object>((Continuation)null) {
         Object L$0;
         int I$0;
         int I$1;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Response response;
            label36: {
               Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               Builder requestBuilder;
               int var4;
               int i;
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  requestBuilder = new Builder();
                  Function1 var8 = builder;
                  boolean var9 = false;
                  boolean var6 = false;
                  var8.invoke(requestBuilder);
                  response = null;
                  var4 = 0;
                  if (var4 > retry) {
                     break label36;
                  }
                  break;
               case 1:
                  i = this.I$1;
                  var4 = this.I$0;
                  requestBuilder = (Builder)this.L$0;
                  ResultKt.throwOnFailure($result);
                  response = (Response)$result;
                  if (response.isSuccessful()) {
                     return response;
                  }

                  if (i == retry) {
                     break label36;
                  }
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               do {
                  i = var4++;
                  Call var10000 = $this$newCallResponse.newCall(requestBuilder.build());
                  Continuation var10001 = (Continuation)this;
                  this.L$0 = requestBuilder;
                  this.I$0 = var4;
                  this.I$1 = i;
                  this.label = 1;
                  Object var10 = OkHttpUtilsKt.await(var10000, var10001);
                  if (var10 == var7) {
                     return var7;
                  }

                  response = (Response)var10;
                  if (response.isSuccessful()) {
                     return response;
                  }
               } while(i != retry);
            }

            Intrinsics.checkNotNull(response);
            return response;
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Response> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), $completion);
   }

   // $FF: synthetic method
   public static Object newCallResponse$default(OkHttpClient var0, int var1, Function1 var2, Continuation var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      return newCallResponse(var0, var1, var2, var3);
   }

   @Nullable
   public static final Object newCallResponseBody(@NotNull OkHttpClient $this$newCallResponseBody, int retry, @NotNull Function1<? super Builder, Unit> builder, @NotNull Continuation<? super ResponseBody> $completion) {
      Object $continuation;
      label24: {
         if (var3 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var3;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label24;
            }
         }

         $continuation = new ContinuationImpl(var3) {
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return OkHttpUtilsKt.newCallResponseBody((OkHttpClient)null, 0, (Function1)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = newCallResponse($this$newCallResponseBody, retry, builder, (Continuation)$continuation);
         if (var10000 == var12) {
            return var12;
         }
         break;
      case 1:
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      Object var4 = var10000;
      boolean var5 = false;
      boolean var6 = false;
      Response it = (Response)var4;
      int var8 = false;
      ResponseBody var9 = it.body();
      if (var9 == null) {
         throw new IOException(it.message());
      } else {
         return var9;
      }
   }

   // $FF: synthetic method
   public static Object newCallResponseBody$default(OkHttpClient var0, int var1, Function1 var2, Continuation var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      return newCallResponseBody(var0, var1, var2, var3);
   }

   @Nullable
   public static final Object newCall(@NotNull OkHttpClient $this$newCall, int retry, @NotNull Function1<? super Builder, Unit> builder, @NotNull Continuation<? super ResponseBody> $completion) {
      Object $continuation;
      label51: {
         if (var3 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var3;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label51;
            }
         }

         $continuation = new ContinuationImpl(var3) {
            Object L$0;
            Object L$1;
            int I$0;
            int I$1;
            int I$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return OkHttpUtilsKt.newCall((OkHttpClient)null, 0, (Function1)null, (Continuation)this);
            }
         };
      }

      Response response;
      label45: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         Builder requestBuilder;
         int var6;
         int i;
         ResponseBody var10000;
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            requestBuilder = new Builder();
            boolean var11 = false;
            boolean var12 = false;
            builder.invoke(requestBuilder);
            response = null;
            var6 = 0;
            if (var6 > retry) {
               break label45;
            }
            break;
         case 1:
            i = ((<undefinedtype>)$continuation).I$2;
            var6 = ((<undefinedtype>)$continuation).I$1;
            retry = ((<undefinedtype>)$continuation).I$0;
            requestBuilder = (Builder)((<undefinedtype>)$continuation).L$1;
            $this$newCall = (OkHttpClient)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            response = (Response)$result;
            if (response.isSuccessful()) {
               var10000 = response.body();
               Intrinsics.checkNotNull(var10000);
               return var10000;
            }

            if (i == retry) {
               break label45;
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         do {
            i = var6++;
            Call var14 = $this$newCall.newCall(requestBuilder.build());
            ((<undefinedtype>)$continuation).L$0 = $this$newCall;
            ((<undefinedtype>)$continuation).L$1 = requestBuilder;
            ((<undefinedtype>)$continuation).I$0 = retry;
            ((<undefinedtype>)$continuation).I$1 = var6;
            ((<undefinedtype>)$continuation).I$2 = i;
            ((<undefinedtype>)$continuation).label = 1;
            Object var15 = await(var14, (Continuation)$continuation);
            if (var15 == var10) {
               return var10;
            }

            response = (Response)var15;
            if (response.isSuccessful()) {
               var10000 = response.body();
               Intrinsics.checkNotNull(var10000);
               return var10000;
            }
         } while(i != retry);
      }

      Intrinsics.checkNotNull(response);
      ResponseBody var13 = response.body();
      if (var13 == null) {
         throw new IOException(response.message());
      } else {
         return var13;
      }
   }

   // $FF: synthetic method
   public static Object newCall$default(OkHttpClient var0, int var1, Function1 var2, Continuation var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      return newCall(var0, var1, var2, var3);
   }

   @Nullable
   public static final Object newCallStrResponse(@NotNull OkHttpClient $this$newCallStrResponse, int retry, @NotNull Function1<? super Builder, Unit> builder, @NotNull Continuation<? super StrResponse> $completion) {
      Object $continuation;
      label52: {
         if (var3 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var3;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label52;
            }
         }

         $continuation = new ContinuationImpl(var3) {
            Object L$0;
            Object L$1;
            int I$0;
            int I$1;
            int I$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return OkHttpUtilsKt.newCallStrResponse((OkHttpClient)null, 0, (Function1)null, (Continuation)this);
            }
         };
      }

      Response response;
      label46: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         Builder requestBuilder;
         int var6;
         int i;
         ResponseBody var10003;
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            requestBuilder = new Builder();
            boolean var11 = false;
            boolean var12 = false;
            builder.invoke(requestBuilder);
            response = null;
            var6 = 0;
            if (var6 > retry) {
               break label46;
            }
            break;
         case 1:
            i = ((<undefinedtype>)$continuation).I$2;
            var6 = ((<undefinedtype>)$continuation).I$1;
            retry = ((<undefinedtype>)$continuation).I$0;
            requestBuilder = (Builder)((<undefinedtype>)$continuation).L$1;
            $this$newCallStrResponse = (OkHttpClient)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            response = (Response)$result;
            if (response.isSuccessful()) {
               var10003 = response.body();
               Intrinsics.checkNotNull(var10003);
               return new StrResponse(response, text$default(var10003, (String)null, 1, (Object)null));
            }

            if (i == retry) {
               break label46;
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         do {
            i = var6++;
            JobKt.ensureActive(((Continuation)$continuation).getContext());
            Call var10000 = $this$newCallStrResponse.newCall(requestBuilder.build());
            ((<undefinedtype>)$continuation).L$0 = $this$newCallStrResponse;
            ((<undefinedtype>)$continuation).L$1 = requestBuilder;
            ((<undefinedtype>)$continuation).I$0 = retry;
            ((<undefinedtype>)$continuation).I$1 = var6;
            ((<undefinedtype>)$continuation).I$2 = i;
            ((<undefinedtype>)$continuation).label = 1;
            Object var14 = await(var10000, (Continuation)$continuation);
            if (var14 == var10) {
               return var10;
            }

            response = (Response)var14;
            if (response.isSuccessful()) {
               var10003 = response.body();
               Intrinsics.checkNotNull(var10003);
               return new StrResponse(response, text$default(var10003, (String)null, 1, (Object)null));
            }
         } while(i != retry);
      }

      Intrinsics.checkNotNull(response);
      ResponseBody var13 = response.body();
      return new StrResponse(response, var13 == null ? response.message() : text$default(var13, (String)null, 1, (Object)null));
   }

   // $FF: synthetic method
   public static Object newCallStrResponse$default(OkHttpClient var0, int var1, Function1 var2, Continuation var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      return newCallStrResponse(var0, var1, var2, var3);
   }

   @Nullable
   public static final Object await(@NotNull Call $this$await, @NotNull Continuation<? super Response> $completion) {
      int $i$f$suspendCancellableCoroutine = false;
      int var4 = false;
      CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted($completion), 1);
      cancellable$iv.initCancellability();
      final CancellableContinuation block = (CancellableContinuation)cancellable$iv;
      int var7 = false;
      block.invokeOnCancellation((Function1)(new Function1<Throwable, Unit>() {
         public final void invoke(@Nullable Throwable it) {
            $this$await.cancel();
         }
      }));
      $this$await.enqueue((Callback)(new Callback() {
         public void onFailure(@NotNull Call call, @NotNull IOException e) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(e, "e");
            Continuation var3 = (Continuation)block;
            boolean var4 = false;
            Companion var5 = Result.Companion;
            boolean var6 = false;
            var3.resumeWith(Result.constructor-impl(ResultKt.createFailure((Throwable)e)));
         }

         public void onResponse(@NotNull Call call, @NotNull Response response) {
            Intrinsics.checkNotNullParameter(call, "call");
            Intrinsics.checkNotNullParameter(response, "response");
            Continuation var3 = (Continuation)block;
            boolean var4 = false;
            Companion var5 = Result.Companion;
            boolean var6 = false;
            var3.resumeWith(Result.constructor-impl(response));
         }
      }));
      Object var10000 = cancellable$iv.getResult();
      if (var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended($completion);
      }

      return var10000;
   }

   @NotNull
   public static final String text(@NotNull ResponseBody $this$text, @Nullable String encode) {
      Intrinsics.checkNotNullParameter($this$text, "<this>");
      byte[] responseBytes = Utf8BomUtils.INSTANCE.removeUTF8BOM($this$text.bytes());
      Object charsetName = null;
      boolean var7;
      boolean var11;
      if (encode == null) {
         MediaType var4 = $this$text.contentType();
         if (var4 != null) {
            Charset var5 = MediaType.charset$default(var4, (Charset)null, 1, (Object)null);
            if (var5 != null) {
               var7 = false;
               boolean var8 = false;
               int var14 = false;
               var11 = false;
               return new String(responseBytes, var5);
            }
         }

         charsetName = EncodingDetect.INSTANCE.getHtmlEncode(responseBytes);
         Charset var12 = Charset.forName(charsetName);
         Intrinsics.checkNotNullExpressionValue(var12, "forName(charsetName)");
         boolean var13 = false;
         return new String(responseBytes, var12);
      } else {
         boolean var6 = false;
         var7 = false;
         int var9 = false;
         Charset var10 = Charset.forName(encode);
         Intrinsics.checkNotNullExpressionValue(var10, "forName(charsetName)");
         var11 = false;
         return new String(responseBytes, var10);
      }
   }

   // $FF: synthetic method
   public static String text$default(ResponseBody var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
      }

      return text(var0, var1);
   }

   public static final void addHeaders(@NotNull Builder $this$addHeaders, @NotNull Map<String, String> headers) {
      Intrinsics.checkNotNullParameter($this$addHeaders, "<this>");
      Intrinsics.checkNotNullParameter(headers, "headers");
      int $i$f$forEach = false;
      boolean var5 = false;
      Iterator var6 = headers.entrySet().iterator();

      while(var6.hasNext()) {
         Entry element$iv = (Entry)var6.next();
         int var9 = false;
         $this$addHeaders.addHeader((String)element$iv.getKey(), (String)element$iv.getValue());
      }

   }

   public static final void get(@NotNull Builder $this$get, @NotNull String url, @NotNull Map<String, String> queryMap, boolean encoded) {
      Intrinsics.checkNotNullParameter($this$get, "<this>");
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(queryMap, "queryMap");
      okhttp3.HttpUrl.Builder httpBuilder = HttpUrl.Companion.get(url).newBuilder();
      int $i$f$forEach = false;
      boolean var8 = false;
      Iterator var9 = queryMap.entrySet().iterator();

      while(var9.hasNext()) {
         Entry element$iv = (Entry)var9.next();
         int var12 = false;
         if (encoded) {
            httpBuilder.addEncodedQueryParameter((String)element$iv.getKey(), (String)element$iv.getValue());
         } else {
            httpBuilder.addQueryParameter((String)element$iv.getKey(), (String)element$iv.getValue());
         }
      }

      $this$get.url(httpBuilder.build());
   }

   // $FF: synthetic method
   public static void get$default(Builder var0, String var1, Map var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      get(var0, var1, var2, var3);
   }

   public static final void postForm(@NotNull Builder $this$postForm, @NotNull Map<String, String> form, boolean encoded) {
      Intrinsics.checkNotNullParameter($this$postForm, "<this>");
      Intrinsics.checkNotNullParameter(form, "form");
      okhttp3.FormBody.Builder formBody = new okhttp3.FormBody.Builder((Charset)null, 1, (DefaultConstructorMarker)null);
      int $i$f$forEach = false;
      boolean var7 = false;
      Iterator var8 = form.entrySet().iterator();

      while(var8.hasNext()) {
         Entry element$iv = (Entry)var8.next();
         int var11 = false;
         if (encoded) {
            formBody.addEncoded((String)element$iv.getKey(), (String)element$iv.getValue());
         } else {
            formBody.add((String)element$iv.getKey(), (String)element$iv.getValue());
         }
      }

      $this$postForm.post((RequestBody)formBody.build());
   }

   // $FF: synthetic method
   public static void postForm$default(Builder var0, Map var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      postForm(var0, var1, var2);
   }

   public static final void postMultipart(@NotNull Builder $this$postMultipart, @Nullable String type, @NotNull Map<String, ? extends Object> form) {
      Intrinsics.checkNotNullParameter($this$postMultipart, "<this>");
      Intrinsics.checkNotNullParameter(form, "form");
      okhttp3.MultipartBody.Builder multipartBody = new okhttp3.MultipartBody.Builder((String)null, 1, (DefaultConstructorMarker)null);
      boolean var7;
      if (type != null) {
         boolean var6 = false;
         var7 = false;
         int var9 = false;
         multipartBody.setType(MediaType.Companion.get(type));
      }

      int $i$f$forEach = false;
      var7 = false;
      Iterator var8 = form.entrySet().iterator();

      while(var8.hasNext()) {
         Entry element$iv = (Entry)var8.next();
         int var11 = false;
         Object value = element$iv.getValue();
         if (value instanceof Map) {
            Map var13 = (Map)value;
            String var14 = "fileName";
            boolean var15 = false;
            Object file = var13.get(var14);
            if (file == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }

            String fileName = (String)file;
            var13 = (Map)value;
            var14 = "file";
            var15 = false;
            file = var13.get(var14);
            Map var18 = (Map)value;
            String var19 = "contentType";
            boolean var20 = false;
            Object var24 = var18.get(var19);
            var14 = var24 instanceof String ? (String)var24 : null;
            MediaType mediaType = var14 == null ? null : MediaType.Companion.get(var14);
            RequestBody var10000;
            if (file instanceof File) {
               var10000 = RequestBody.Companion.create((File)file, mediaType);
            } else if (file instanceof byte[]) {
               var10000 = okhttp3.RequestBody.Companion.create$default(RequestBody.Companion, (byte[])file, mediaType, 0, 0, 6, (Object)null);
            } else if (file instanceof String) {
               var10000 = RequestBody.Companion.create((String)file, mediaType);
            } else {
               okhttp3.RequestBody.Companion var25 = RequestBody.Companion;
               String var26 = GsonExtensionsKt.getGSON().toJson(file);
               Intrinsics.checkNotNullExpressionValue(var26, "GSON.toJson(file)");
               var10000 = var25.create(var26, mediaType);
            }

            RequestBody requestBody = var10000;
            multipartBody.addFormDataPart((String)element$iv.getKey(), fileName, requestBody);
         } else {
            multipartBody.addFormDataPart((String)element$iv.getKey(), element$iv.getValue().toString());
         }
      }

      $this$postMultipart.post((RequestBody)multipartBody.build());
   }

   public static final void postJson(@NotNull Builder $this$postJson, @Nullable String json) {
      Intrinsics.checkNotNullParameter($this$postJson, "<this>");
      if (json != null) {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         RequestBody requestBody = RequestBody.Companion.create(json, MediaType.Companion.get("application/json; charset=UTF-8"));
         $this$postJson.post(requestBody);
      }

   }
}
