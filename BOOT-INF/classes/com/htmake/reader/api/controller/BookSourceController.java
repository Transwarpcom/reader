package com.htmake.reader.api.controller;

import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.SpringContextUtils;
import com.htmake.reader.utils.VertExtKt;
import io.legado.app.data.entities.BookSource;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.slf4j.MDCContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\u0011\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ&\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0019\u0010\u0019\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u0014J\u0019\u0010\u001b\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0016\u001a\u00020\u0014J4\u0010\u001d\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0016\u001a\u00020\u00142\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001f2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001fJ\u0019\u0010!\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\"\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010#\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0016\u0010#\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u0018J\u0019\u0010%\u001a\u00020&2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ \u0010'\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00142\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010$\u001a\u00020\u0018J\u0019\u0010*\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ#\u0010+\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010)H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010-R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."},
   d2 = {"Lcom/htmake/reader/api/controller/BookSourceController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "webClient", "Lio/vertx/ext/web/client/WebClient;", "canEditBookSource", "", "context", "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllBookSources", "Lcom/htmake/reader/api/ReturnData;", "deleteBookSource", "deleteBookSources", "deleteBookSourcesFile", "deleteUserBookSource", "generateBookSourceMap", "", "", "", "userNameSpace", "bookSourceList", "Lio/vertx/core/json/JsonArray;", "getBookSource", "getBookSourceMap", "getBookSources", "getUserBookSourceJson", "getUserBookSourceJsonOpt", "fields", "", "checkNotEmpty", "readSourceFile", "saveBookSource", "saveBookSources", "bookSourceJsonArray", "saveFromRemoteSource", "", "saveUserBookSources", "userInfo", "Lcom/htmake/reader/entity/User;", "setAsDefaultBookSources", "updateRemoteSourceSub", "user", "(Ljava/lang/String;Lcom/htmake/reader/entity/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reader-pro"}
)
public final class BookSourceController extends BaseController {
   @NotNull
   private WebClient webClient;

   public BookSourceController(@NotNull CoroutineContext coroutineContext) {
      Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
      super(coroutineContext);
      Object var2 = SpringContextUtils.getBean("webClient", WebClient.class);
      Intrinsics.checkNotNullExpressionValue(var2, "getBean(\"webClient\", WebClient::class.java)");
      this.webClient = (WebClient)var2;
   }

   @Nullable
   public final JsonArray getUserBookSourceJsonOpt(@NotNull String userNameSpace, @Nullable Set<String> fields, @Nullable Set<String> checkNotEmpty) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      String[] var5 = new String[]{"data", userNameSpace, "bookSource"};
      File bookSourceFile = ExtKt.getStorageFile$default(var5, (String)null, 2, (Object)null);
      if (!bookSourceFile.exists()) {
         var5 = new String[]{"data", "default", "bookSource"};
         bookSourceFile = ExtKt.getStorageFile$default(var5, (String)null, 2, (Object)null);
      }

      JsonArray bookSourceList = ExtKt.parseJsonStringList$default(bookSourceFile, fields, (Set)null, 0, 0, checkNotEmpty, (Function1)null, 92, (Object)null);
      return bookSourceList;
   }

   // $FF: synthetic method
   public static JsonArray getUserBookSourceJsonOpt$default(BookSourceController var0, String var1, Set var2, Set var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = null;
      }

      if ((var4 & 4) != 0) {
         var3 = null;
      }

      return var0.getUserBookSourceJsonOpt(var1, var2, var3);
   }

   @Nullable
   public final JsonArray getUserBookSourceJson(@NotNull String userNameSpace) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      String[] var3 = new String[]{"bookSource"};
      JsonArray bookSourceList = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var3));
      if (bookSourceList == null && !userNameSpace.equals("default")) {
         String[] var4 = new String[]{"bookSource"};
         JsonArray systemBookSourceList = ExtKt.asJsonArray(this.getUserStorage("default", var4));
         if (systemBookSourceList != null) {
            bookSourceList = systemBookSourceList;
         }
      }

      return bookSourceList;
   }

   @Nullable
   public final Object canEditBookSource(@NotNull RoutingContext context, @NotNull Continuation<? super Boolean> $completion) {
      if (!this.getAppConfig().getSecure()) {
         return Boxing.boxBoolean(true);
      } else {
         User userInfo = (User)context.get("userInfo");
         return userInfo == null ? Boxing.boxBoolean(false) : Boxing.boxBoolean(userInfo.getEnable_book_source());
      }
   }

   @Nullable
   public final Object saveBookSource(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label70: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label70;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.saveBookSource((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label74: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var14) {
               return var14;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label74;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         }

         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = this.canEditBookSource(context, (Continuation)$continuation);
         if (var10000 == var14) {
            return var14;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         BookSource.Companion var20 = BookSource.Companion;
         String userNameSpace = context.getBodyAsString();
         Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsString");
         Object var15 = var20.fromJson-IoAF18A(userNameSpace);
         boolean var6 = false;
         BookSource bookSource = (BookSource)(Result.isFailure-impl(var15) ? null : var15);
         if (bookSource == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            userNameSpace = this.getUserNameSpace(context);
            JsonArray bookSourceList = this.getUserBookSourceJson(userNameSpace);
            if (bookSourceList == null) {
               bookSourceList = new JsonArray();
            }

            boolean var8 = false;
            Map urlMap = (Map)(new LinkedHashMap());
            int existIndex = 0;
            int var9 = bookSourceList.size();
            if (existIndex < var9) {
               do {
                  int i = existIndex++;
                  String var11 = bookSourceList.getJsonObject(i).getString("bookSourceUrl");
                  Intrinsics.checkNotNullExpressionValue(var11, "bookSourceList.getJsonObject(i).getString(\"bookSourceUrl\")");
                  urlMap.put(var11, Boxing.boxInt(i));
               } while(existIndex < var9);
            }

            existIndex = ((Number)urlMap.getOrDefault(bookSource.getBookSourceUrl(), Boxing.boxInt(-1))).intValue();
            if (existIndex >= 0) {
               List sourceList = bookSourceList.getList();
               sourceList.set(existIndex, JsonObject.mapFrom(bookSource));
               bookSourceList = new JsonArray(sourceList);
            } else {
               User userInfo = (User)context.get("userInfo");
               if (userInfo != null && bookSourceList.size() >= userInfo.getBook_source_limit()) {
                  return returnData.setErrorMsg("你已达到书源数上限，请联系管理员");
               }

               bookSourceList.add(JsonObject.mapFrom(bookSource));
            }

            this.saveUserStorage(userNameSpace, "bookSource", bookSourceList);
            this.generateBookSourceMap(userNameSpace, bookSourceList);
            return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public final Object saveBookSources(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label43: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label43;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.saveBookSources((RoutingContext)null, (Continuation)((Continuation)this));
            }
         };
      }

      Object var10000;
      ReturnData returnData;
      label47: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var7) {
               return var7;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label47;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         }

         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = this.canEditBookSource(context, (Continuation)$continuation);
         if (var10000 == var7) {
            return var7;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         JsonArray bookSourceJsonArray = context.getBodyAsJsonArray();
         if (bookSourceJsonArray == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            return this.saveBookSources(context, bookSourceJsonArray);
         }
      }
   }

   @NotNull
   public final ReturnData saveBookSources(@NotNull RoutingContext context, @NotNull JsonArray bookSourceJsonArray) {
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(bookSourceJsonArray, "bookSourceJsonArray");
      return this.saveUserBookSources(this.getUserNameSpace(context), (User)context.get("userInfo"), bookSourceJsonArray);
   }

   @NotNull
   public final ReturnData saveUserBookSources(@NotNull String userNameSpace, @Nullable User userInfo, @NotNull JsonArray bookSourceJsonArray) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      Intrinsics.checkNotNullParameter(bookSourceJsonArray, "bookSourceJsonArray");
      ReturnData returnData = new ReturnData();
      JsonArray bookSourceList = this.getUserBookSourceJson(userNameSpace);
      if (bookSourceList == null) {
         bookSourceList = new JsonArray();
      }

      boolean isOverLimit = false;
      Map urlMap = (Map)(new LinkedHashMap());
      int var18 = 0;
      int addCnt = bookSourceList.size();
      int maxIndex;
      if (var18 < addCnt) {
         do {
            maxIndex = var18++;
            String var10 = bookSourceList.getJsonObject(maxIndex).getString("bookSourceUrl");
            Intrinsics.checkNotNullExpressionValue(var10, "bookSourceList.getJsonObject(i).getString(\"bookSourceUrl\")");
            urlMap.put(var10, maxIndex);
         } while(var18 < addCnt);
      }

      isOverLimit = false;
      addCnt = 0;
      maxIndex = bookSourceList.size() - 1;
      boolean var11 = false;
      Set updateIndex = (Set)(new LinkedHashSet());
      int var20 = 0;
      int var12 = bookSourceJsonArray.size();
      if (var20 < var12) {
         do {
            int k = var20++;

            BookSource var15;
            try {
               BookSource.Companion var10000 = BookSource.Companion;
               String var22 = bookSourceJsonArray.getJsonObject(k).toString();
               Intrinsics.checkNotNullExpressionValue(var22, "bookSourceJsonArray.getJsonObject(k).toString()");
               Object var23 = var10000.fromJson-IoAF18A(var22);
               boolean var16 = false;
               var15 = (BookSource)(Result.isFailure-impl(var23) ? null : var23);
            } catch (Exception var17) {
               var15 = (BookSource)null;
            }

            if (var15 != null) {
               int existIndex = ((Number)urlMap.getOrDefault(var15.getBookSourceUrl(), -1)).intValue();
               if (existIndex >= 0) {
                  bookSourceList.set(existIndex, JsonObject.mapFrom(var15));
                  if (existIndex <= maxIndex) {
                     updateIndex.add(existIndex);
                  }
               } else {
                  if (userInfo != null && bookSourceList.size() >= userInfo.getBook_source_limit()) {
                     isOverLimit = true;
                     break;
                  }

                  ++addCnt;
                  bookSourceList.add(JsonObject.mapFrom(var15));
                  urlMap.put(var15.getBookSourceUrl(), bookSourceList.size() - 1);
               }
            }
         } while(var20 < var12);
      }

      this.saveUserStorage(userNameSpace, "bookSource", bookSourceList);
      this.generateBookSourceMap(userNameSpace, bookSourceList);
      String tip = "新增" + addCnt + "条书源，更新" + updateIndex.size() + "条书源";
      return isOverLimit ? returnData.setErrorMsg(Intrinsics.stringPlus(tip, "。你已达到书源数上限，请联系管理员")) : returnData.setData("", tip);
   }

   @Nullable
   public final Object getBookSource(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label51: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label51;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.getBookSource((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      ReturnData returnData;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         if (this.checkAuth(context, (Continuation)$continuation) == var13) {
            return var13;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      String bookSourceUrl = null;
      String userNameSpace;
      if (context.request().method() == HttpMethod.POST) {
         userNameSpace = context.getBodyAsJson().getString("bookSourceUrl");
         Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsJson.getString(\"bookSourceUrl\")");
         bookSourceUrl = userNameSpace;
      } else {
         List var6 = context.queryParam("bookSourceUrl");
         Intrinsics.checkNotNullExpressionValue(var6, "context.queryParam(\"bookSourceUrl\")");
         userNameSpace = (String)CollectionsKt.firstOrNull(var6);
         bookSourceUrl = userNameSpace == null ? "" : userNameSpace;
      }

      CharSequence var14 = (CharSequence)bookSourceUrl;
      boolean var15 = false;
      boolean var7 = false;
      if (var14.length() == 0) {
         return returnData.setErrorMsg("书源链接不能为空");
      } else {
         userNameSpace = this.getUserNameSpace(context);
         Map urlMap = this.getBookSourceMap(userNameSpace);
         int existIndex = ((Number)urlMap.getOrDefault(bookSourceUrl, Boxing.boxInt(-1))).intValue();
         if (existIndex < 0) {
            return returnData.setErrorMsg("书源信息不存在");
         } else {
            String[] var9 = new String[]{"data", userNameSpace, "bookSource"};
            File bookSourceFile = ExtKt.getStorageFile$default(var9, (String)null, 2, (Object)null);
            if (!bookSourceFile.exists()) {
               var9 = new String[]{"data", "default", "bookSource"};
               bookSourceFile = ExtKt.getStorageFile$default(var9, (String)null, 2, (Object)null);
            }

            JsonArray bookSourceList = ExtKt.parseJsonStringList$default(bookSourceFile, (Set)null, (Set)null, existIndex, existIndex, (Set)null, (Function1)null, 102, (Object)null);
            if (bookSourceList == null) {
               return returnData.setErrorMsg("书源信息不存在");
            } else {
               Map var10 = (new JsonObject(bookSourceList.getString(0))).getMap();
               Intrinsics.checkNotNullExpressionValue(var10, "JsonObject(bookSourceList.getString(0)).map");
               return ReturnData.setData$default(returnData, var10, (String)null, 2, (Object)null);
            }
         }
      }
   }

   @Nullable
   public final Object getBookSources(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label58: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label58;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.getBookSources((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var21 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      ReturnData returnData;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         if (this.checkAuth(context, (Continuation)$continuation) == var21) {
            return var21;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      boolean var4 = false;
      boolean $i$f$map;
      int simple;
      String userNameSpace;
      if (context.request().method() == HttpMethod.POST) {
         Integer var5 = context.getBodyAsJson().getInteger("simple", Boxing.boxInt(0));
         Intrinsics.checkNotNullExpressionValue(var5, "context.bodyAsJson.getInteger(\"simple\", 0)");
         simple = ((Number)var5).intValue();
      } else {
         List var6 = context.queryParam("simple");
         Intrinsics.checkNotNullExpressionValue(var6, "context.queryParam(\"simple\")");
         userNameSpace = (String)CollectionsKt.firstOrNull(var6);
         int var10000;
         if (userNameSpace == null) {
            var10000 = 0;
         } else {
            $i$f$map = false;
            Integer var24 = Boxing.boxInt(Integer.parseInt(userNameSpace));
            var10000 = var24 == null ? 0 : var24;
         }

         simple = var10000;
      }

      userNameSpace = this.getUserNameSpace(context);
      Set var10002;
      if (simple > 0) {
         String[] var7 = new String[]{"bookSourceGroup", "bookSourceName", "bookSourceUrl"};
         var10002 = SetsKt.setOf(var7);
      } else {
         var10002 = null;
      }

      JsonArray bookSourceList = this.getUserBookSourceJsonOpt(userNameSpace, var10002, simple > 0 ? SetsKt.setOf("exploreUrl") : null);
      if (bookSourceList != null) {
         List var27 = bookSourceList.getList();
         Intrinsics.checkNotNullExpressionValue(var27, "bookSourceList.list");
         Iterable $this$map$iv = (Iterable)var27;
         $i$f$map = false;
         Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
         int $i$f$mapTo = false;
         Iterator var12 = $this$map$iv.iterator();

         while(var12.hasNext()) {
            Object item$iv$iv = var12.next();
            int var15 = false;
            JsonObject var29 = new JsonObject;
            if (item$iv$iv == null) {
               throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }

            var29.<init>((String)item$iv$iv);
            Map var18 = var29.getMap();
            destination$iv$iv.add(var18);
         }

         List var17 = (List)destination$iv$iv;
         return ReturnData.setData$default(returnData, var17, (String)null, 2, (Object)null);
      } else {
         boolean var26 = false;
         return ReturnData.setData$default(returnData, new ArrayList(), (String)null, 2, (Object)null);
      }
   }

   @Nullable
   public final Object deleteBookSource(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label52: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label52;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.deleteBookSource((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label55: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var11) {
               return var11;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label55;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         }

         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = this.canEditBookSource(context, (Continuation)$continuation);
         if (var10000 == var11) {
            return var11;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         BookSource.Companion var14 = BookSource.Companion;
         String userNameSpace = context.getBodyAsString();
         Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsString");
         Object var12 = var14.fromJson-IoAF18A(userNameSpace);
         boolean var6 = false;
         BookSource bookSource = (BookSource)(Result.isFailure-impl(var12) ? null : var12);
         if (bookSource == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            userNameSpace = this.getUserNameSpace(context);
            JsonArray bookSourceList = this.getUserBookSourceJson(userNameSpace);
            if (bookSourceList == null) {
               bookSourceList = new JsonArray();
            }

            Map urlMap = this.getBookSourceMap(userNameSpace);
            int existIndex = ((Number)urlMap.getOrDefault(bookSource.getBookSourceUrl(), Boxing.boxInt(-1))).intValue();
            if (existIndex >= 0) {
               bookSourceList.remove(existIndex);
            }

            this.saveUserStorage(userNameSpace, "bookSource", bookSourceList);
            this.generateBookSourceMap(userNameSpace, bookSourceList);
            return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public final Object deleteBookSources(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label80: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label80;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.deleteBookSources((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label84: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var18 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var18) {
               return var18;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label84;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         }

         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = this.canEditBookSource(context, (Continuation)$continuation);
         if (var10000 == var18) {
            return var18;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         JsonArray bookSourceJsonArray = context.getBodyAsJsonArray();
         String userNameSpace = this.getUserNameSpace(context);
         JsonArray bookSourceList = this.getUserBookSourceJson(userNameSpace);
         if (bookSourceList == null) {
            bookSourceList = new JsonArray();
         }

         int var7 = 0;
         int var8 = bookSourceJsonArray.size();
         if (var7 < var8) {
            do {
               int k = var7++;
               String bookSourceUrl = bookSourceJsonArray.getJsonObject(k).getString("bookSourceUrl");
               CharSequence var11 = (CharSequence)bookSourceUrl;
               boolean var12 = false;
               boolean var13 = false;
               if (var11 != null && var11.length() != 0) {
                  int existIndex = -1;
                  int var20 = 0;
                  int var21 = bookSourceList.size();
                  if (var20 < var21) {
                     do {
                        int i = var20++;
                        String _bookSourceUrl = bookSourceList.getJsonObject(i).getString("bookSourceUrl");
                        if (bookSourceUrl.equals(_bookSourceUrl)) {
                           existIndex = i;
                           break;
                        }
                     } while(var20 < var21);
                  }

                  if (existIndex >= 0) {
                     bookSourceList.remove(existIndex);
                  }
               }
            } while(var7 < var8);
         }

         this.saveUserStorage(userNameSpace, "bookSource", bookSourceList);
         this.generateBookSourceMap(userNameSpace, bookSourceList);
         return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
      }
   }

   @Nullable
   public final Object deleteAllBookSources(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label39: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label39;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.deleteAllBookSources((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object var10000;
      ReturnData returnData;
      label43: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var7) {
               return var7;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label43;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         }

         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = this.canEditBookSource(context, (Continuation)$continuation);
         if (var10000 == var7) {
            return var7;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         String userNameSpace = this.getUserNameSpace(context);
         this.saveUserStorage(userNameSpace, "bookSource", new JsonArray());
         this.generateBookSourceMap(userNameSpace, new JsonArray());
         return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
      }
   }

   @Nullable
   public final Object setAsDefaultBookSources(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label32: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label32;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.setAsDefaultBookSources((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      ReturnData returnData;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAuth(context, (Continuation)$continuation);
         if (var10000 == var9) {
            return var9;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!(Boolean)var10000) {
         return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
      } else if (!this.checkManagerAuth(context)) {
         return ReturnData.setData$default(returnData, "NEED_SECURE_KEY", (String)null, 2, (Object)null).setErrorMsg("请输入管理密码");
      } else {
         String username = context.getBodyAsJson().getString("username");
         Intrinsics.checkNotNullExpressionValue(username, "username");
         String[] var6 = new String[]{"bookSource"};
         JsonArray bookSourceList = ExtKt.asJsonArray(this.getUserStorage(username, var6));
         if (bookSourceList == null) {
            return returnData.setErrorMsg("用户书源不存在");
         } else {
            List var10 = bookSourceList.getList();
            Intrinsics.checkNotNullExpressionValue(var10, "bookSourceList.getList()");
            this.saveUserStorage("default", "bookSource", var10);
            this.generateBookSourceMap("default", bookSourceList);
            return ReturnData.setData$default(returnData, "设置默认书源成功", (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public final Object readSourceFile(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      ReturnData returnData = new ReturnData();
      if (context.fileUploads() != null && !context.fileUploads().isEmpty()) {
         Object sourceList = null;
         sourceList = new JsonArray();
         Set var5 = context.fileUploads();
         Intrinsics.checkNotNullExpressionValue(var5, "context.fileUploads()");
         Iterable $this$forEach$iv = (Iterable)var5;
         int $i$f$forEach = false;
         Iterator var7 = $this$forEach$iv.iterator();

         while(var7.hasNext()) {
            Object element$iv = var7.next();
            FileUpload it = (FileUpload)element$iv;
            int var10 = false;
            File file = new File(it.uploadedFileName());
            if (file.exists()) {
               sourceList.add(FilesKt.readText$default(file, (Charset)null, 1, (Object)null));
               file.delete();
            }
         }

         List var13 = sourceList.getList();
         Intrinsics.checkNotNullExpressionValue(var13, "sourceList.getList()");
         return ReturnData.setData$default(returnData, var13, (String)null, 2, (Object)null);
      } else {
         return returnData.setErrorMsg("请上传文件");
      }
   }

   @Nullable
   public final Object saveFromRemoteSource(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) {
      Object $continuation;
      label52: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label52;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.saveFromRemoteSource((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      final ReturnData returnData;
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAuth(context, (Continuation)$continuation);
         if (var10000 == var10) {
            return var10;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!(Boolean)var10000) {
         VertExtKt.success(context, ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用"));
         return Unit.INSTANCE;
      } else {
         final ObjectRef url = new ObjectRef();
         String var5;
         if (context.request().method() == HttpMethod.POST) {
            var5 = context.getBodyAsJson().getString("url");
            url.element = var5 == null ? "" : var5;
         } else {
            List var6 = context.queryParam("url");
            Intrinsics.checkNotNullExpressionValue(var6, "context.queryParam(\"url\")");
            var5 = (String)CollectionsKt.firstOrNull(var6);
            url.element = var5 == null ? "" : var5;
         }

         CharSequence var11 = (CharSequence)url.element;
         boolean var12 = false;
         boolean var7 = false;
         if (var11 == null || var11.length() == 0) {
            VertExtKt.success(context, returnData.setErrorMsg("请输入远程书源链接"));
            return Unit.INSTANCE;
         } else {
            BuildersKt.launch$default((CoroutineScope)this, (new MDCContext((Map)null, 1, (DefaultConstructorMarker)null)).plus((CoroutineContext)Dispatchers.getIO()), (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
               int label;

               @Nullable
               public final Object invokeSuspend(@NotNull Object $result) {
                  Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  switch(this.label) {
                  case 0:
                     ResultKt.throwOnFailure(var1);
                     BookSourceController.this.webClient.getAbs((String)url.element).timeout(3000L).send(<undefinedtype>::invokeSuspend$lambda-0);
                     return Unit.INSTANCE;
                  default:
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }
               }

               @NotNull
               public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                  return (Continuation)(new <anonymous constructor>($completion));
               }

               @Nullable
               public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
                  return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
               }

               private static final void invokeSuspend$lambda_0/* $FF was: invokeSuspend$lambda-0*/(RoutingContext $context, BookSourceController this$0, ReturnData $returnData, AsyncResult it) {
                  HttpResponse var5 = (HttpResponse)it.result();
                  JsonArray body = var5 == null ? null : var5.bodyAsJsonArray();
                  if (body != null) {
                     VertExtKt.success($context, this$0.saveBookSources($context, body));
                  } else {
                     VertExtKt.success($context, $returnData.setErrorMsg("远程书源链接错误"));
                  }

               }
            }), 2, (Object)null);
            return Unit.INSTANCE;
         }
      }
   }

   @Nullable
   public final Object updateRemoteSourceSub(@NotNull String userNameSpace, @Nullable User user, @NotNull Continuation<? super Unit> $completion) {
      Object $continuation;
      label58: {
         if (var3 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var3;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label58;
            }
         }

         $continuation = new ContinuationImpl(var3) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int I$0;
            int I$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.updateRemoteSourceSub((String)null, (User)null, (Continuation)this);
            }
         };
      }

      label52: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var15 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         final ObjectRef remoteBookSourceList;
         int var5;
         int var6;
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            remoteBookSourceList = new ObjectRef();
            String[] var17 = new String[]{"remoteBookSourceSub"};
            JsonArray var16 = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var17));
            if (var16 == null) {
               return Unit.INSTANCE;
            }

            remoteBookSourceList.element = var16;
            var5 = 0;
            var6 = ((JsonArray)remoteBookSourceList.element).size();
            if (var5 >= var6) {
               break label52;
            }
            break;
         case 1:
            var6 = ((<undefinedtype>)$continuation).I$1;
            var5 = ((<undefinedtype>)$continuation).I$0;
            remoteBookSourceList = (ObjectRef)((<undefinedtype>)$continuation).L$3;
            user = (User)((<undefinedtype>)$continuation).L$2;
            userNameSpace = (String)((<undefinedtype>)$continuation).L$1;
            this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            if (var5 >= var6) {
               break label52;
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         do {
            final int i = var5++;
            final ObjectRef remoteBookSource = new ObjectRef();
            remoteBookSource.element = ((JsonArray)remoteBookSourceList.element).getJsonObject(i);
            final ObjectRef url = new ObjectRef();
            url.element = ((JsonObject)remoteBookSource.element).getString("link");
            CharSequence var10 = (CharSequence)url.element;
            boolean var11 = false;
            boolean var12 = false;
            if (!(var10 != null ? var10.length() == 0 : true)) {
               Function1 var10000 = (Function1)(new Function1<Handler<AsyncResult<Boolean>>, Unit>() {
                  public final void invoke(@NotNull Handler<AsyncResult<Boolean>> handler) {
                     Intrinsics.checkNotNullParameter(handler, "handler");
                     BookSourceController.this.webClient.getAbs((String)url.element).timeout(3000L).send(<undefinedtype>::invoke$lambda-0);
                  }

                  private static final void invoke$lambda_0/* $FF was: invoke$lambda-0*/(ObjectRef $url, BookSourceController this$0, String $userNameSpace, User $user, ObjectRef $remoteBookSourceList, int $i, ObjectRef $remoteBookSource, Handler $handler, AsyncResult it) {
                     Intrinsics.checkNotNullParameter($url, "$url");
                     Intrinsics.checkNotNullParameter(this$0, "this$0");
                     Intrinsics.checkNotNullParameter($userNameSpace, "$userNameSpace");
                     Intrinsics.checkNotNullParameter($remoteBookSourceList, "$remoteBookSourceList");
                     Intrinsics.checkNotNullParameter($remoteBookSource, "$remoteBookSource");
                     Intrinsics.checkNotNullParameter($handler, "$handler");
                     HttpResponse var10 = (HttpResponse)it.result();
                     JsonArray body = var10 == null ? null : var10.bodyAsJsonArray();
                     if (body != null) {
                        try {
                           BookSourceControllerKt.access$getLogger$p().info("updateRemoteSourceSub link={}, result={}", $url.element, this$0.saveUserBookSources($userNameSpace, $user, body).getErrorMsg());
                           JsonArray var12 = ((JsonArray)$remoteBookSourceList.element).set($i, ((JsonObject)$remoteBookSource.element).put("lastSyncTime", System.currentTimeMillis()));
                           Intrinsics.checkNotNullExpressionValue(var12, "remoteBookSourceList.set(i, remoteBookSource.put(\"lastSyncTime\", System.currentTimeMillis()))");
                           this$0.saveUserStorage($userNameSpace, "remoteBookSourceSub", var12);
                        } catch (Exception var11) {
                           BookSourceControllerKt.access$getLogger$p().error((Throwable)var11, (Function0)null.INSTANCE);
                        }
                     }

                     $handler.handle(Future.succeededFuture(true));
                  }
               });
               ((<undefinedtype>)$continuation).L$0 = this;
               ((<undefinedtype>)$continuation).L$1 = userNameSpace;
               ((<undefinedtype>)$continuation).L$2 = user;
               ((<undefinedtype>)$continuation).L$3 = remoteBookSourceList;
               ((<undefinedtype>)$continuation).I$0 = var5;
               ((<undefinedtype>)$continuation).I$1 = var6;
               ((<undefinedtype>)$continuation).label = 1;
               if (VertxCoroutineKt.awaitResult(var10000, (Continuation)$continuation) == var15) {
                  return var15;
               }
            }
         } while(var5 < var6);
      }

      generateBookSourceMap$default(this, userNameSpace, (JsonArray)null, 2, (Object)null);
      return Unit.INSTANCE;
   }

   @Nullable
   public final Object deleteUserBookSource(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label39: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label39;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.deleteUserBookSource((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      ReturnData returnData;
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAuth(context, (Continuation)$continuation);
         if (var10000 == var14) {
            return var14;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!(Boolean)var10000) {
         return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
      } else if (!this.checkManagerAuth(context)) {
         return ReturnData.setData$default(returnData, "NEED_SECURE_KEY", (String)null, 2, (Object)null).setErrorMsg("请输入管理密码");
      } else {
         JsonArray userJsonArray = context.getBodyAsJsonArray();
         int var5 = 0;
         int var6 = userJsonArray.size();
         if (var5 < var6) {
            do {
               int i = var5++;
               String username = userJsonArray.getString(i);
               String[] var10 = new String[]{"storage", "data", null, null};
               Intrinsics.checkNotNullExpressionValue(username, "username");
               var10[2] = username;
               var10[3] = "bookSource.json";
               File userBookSourceFile = new File(ExtKt.getWorkDir(var10));
               if (userBookSourceFile.exists()) {
                  ExtKt.deleteRecursively(userBookSourceFile);
               }
            } while(var5 < var6);
         }

         return ReturnData.setData$default(returnData, "删除书源成功", (String)null, 2, (Object)null);
      }
   }

   @Nullable
   public final Object deleteBookSourcesFile(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label28: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label28;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return BookSourceController.this.deleteBookSourcesFile((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      ReturnData returnData;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAuth(context, (Continuation)$continuation);
         if (var10000 == var9) {
            return var9;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (BookSourceController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!(Boolean)var10000) {
         return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
      } else {
         String userNameSpace = this.getUserNameSpace(context);
         String[] var6 = new String[]{"storage", "data", userNameSpace, "bookSource.json"};
         File userBookSourceFile = new File(ExtKt.getWorkDir(var6));
         if (userBookSourceFile.exists()) {
            ExtKt.deleteRecursively(userBookSourceFile);
         }

         return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
      }
   }

   @NotNull
   public final Map<String, Integer> generateBookSourceMap(@NotNull String userNameSpace, @Nullable JsonArray bookSourceList) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      JsonArray bookSourceJsonArray = bookSourceList != null ? bookSourceList : this.getUserBookSourceJson(userNameSpace);
      if (bookSourceJsonArray == null) {
         bookSourceJsonArray = new JsonArray();
      }

      boolean var5 = false;
      Map urlMap = (Map)(new LinkedHashMap());
      boolean var6 = false;
      List exploreList = (List)(new ArrayList());
      int var13 = 0;
      int var7 = bookSourceJsonArray.size();
      if (var13 < var7) {
         do {
            int i = var13++;
            String var9 = bookSourceJsonArray.getJsonObject(i).getString("bookSourceUrl");
            Intrinsics.checkNotNullExpressionValue(var9, "bookSourceJsonArray.getJsonObject(i).getString(\"bookSourceUrl\")");
            urlMap.put(var9, i);
            CharSequence var14 = (CharSequence)bookSourceJsonArray.getJsonObject(i).getString("exploreUrl");
            boolean var10 = false;
            boolean var11 = false;
            if (var14 != null && var14.length() != 0) {
               Pair[] var15 = new Pair[]{TuplesKt.to("bookSourceUrl", bookSourceJsonArray.getJsonObject(i).getString("bookSourceUrl")), TuplesKt.to("bookSourceGroup", bookSourceJsonArray.getJsonObject(i).getString("bookSourceGroup")), TuplesKt.to("bookSourceName", bookSourceJsonArray.getJsonObject(i).getString("bookSourceName"))};
               exploreList.add(MapsKt.mutableMapOf(var15));
            }
         } while(var13 < var7);
      }

      this.saveUserStorage(userNameSpace, "bookSourceMap", urlMap);
      this.saveUserStorage(userNameSpace, "bookSourceExploreList", exploreList);
      return urlMap;
   }

   // $FF: synthetic method
   public static Map generateBookSourceMap$default(BookSourceController var0, String var1, JsonArray var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      return var0.generateBookSourceMap(var1, var2);
   }

   @NotNull
   public final Map<String, Integer> getBookSourceMap(@NotNull String userNameSpace) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      String[] var3 = new String[]{"data", userNameSpace, "bookSource"};
      String var10000;
      if (ExtKt.getStorageFile$default(var3, (String)null, 2, (Object)null).exists()) {
         var3 = new String[]{"bookSourceMap"};
         var10000 = this.getUserStorage(userNameSpace, var3);
      } else {
         var3 = new String[]{"bookSourceMap"};
         var10000 = this.getUserStorage("default", var3);
      }

      String content = var10000;
      CharSequence var6 = (CharSequence)content;
      boolean var4 = false;
      boolean var5 = false;
      if (var6 != null && var6.length() != 0) {
         JsonObject var7 = ExtKt.asJsonObject(content);
         Map var8 = var7 == null ? null : var7.getMap();
         if (var8 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Int>");
         } else {
            return TypeIntrinsics.asMutableMap(var8);
         }
      } else {
         var3 = new String[]{"data", userNameSpace, "bookSource"};
         return ExtKt.getStorageFile$default(var3, (String)null, 2, (Object)null).exists() ? generateBookSourceMap$default(this, userNameSpace, (JsonArray)null, 2, (Object)null) : generateBookSourceMap$default(this, "default", (JsonArray)null, 2, (Object)null);
      }
   }
}
