package com.htmake.reader.api.controller;

import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import io.legado.app.data.entities.RssArticle;
import io.legado.app.data.entities.RssSource;
import io.legado.app.model.Debug;
import io.legado.app.model.DebugLog;
import io.legado.app.model.rss.Rss;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011J\u0019\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"},
   d2 = {"Lcom/htmake/reader/api/controller/RssSourceController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "canEditRssSource", "", "context", "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRssSource", "Lcom/htmake/reader/api/ReturnData;", "getRssArticles", "getRssContent", "getRssSourceByURL", "Lio/legado/app/data/entities/RssSource;", "url", "", "userNameSpace", "getRssSources", "saveRssSource", "saveRssSources", "reader-pro"}
)
public final class RssSourceController extends BaseController {
   public RssSourceController(@NotNull CoroutineContext coroutineContext) {
      Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
      super(coroutineContext);
   }

   @Nullable
   public final Object getRssSources(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
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
               return RssSourceController.this.getRssSources((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
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
         if (var10000 == var9) {
            return var9;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
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
         String[] var6 = new String[]{"rssSources"};
         JsonArray list = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var6));
         if (list != null) {
            List var11 = list.getList();
            Intrinsics.checkNotNullExpressionValue(var11, "list.getList()");
            return ReturnData.setData$default(returnData, var11, (String)null, 2, (Object)null);
         } else {
            boolean var10 = false;
            return ReturnData.setData$default(returnData, new ArrayList(), (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public final Object canEditRssSource(@NotNull RoutingContext context, @NotNull Continuation<? super Boolean> $completion) {
      if (!this.getAppConfig().getSecure()) {
         return Boxing.boxBoolean(true);
      } else {
         User userInfo = (User)context.get("userInfo");
         return userInfo == null ? Boxing.boxBoolean(false) : Boxing.boxBoolean(userInfo.getEnable_book_source());
      }
   }

   @Nullable
   public final Object saveRssSource(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label90: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label90;
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
               return RssSourceController.this.saveRssSource((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label93: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var16 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var16) {
               return var16;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label93;
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
         var10000 = this.canEditRssSource(context, (Continuation)$continuation);
         if (var10000 == var16) {
            return var16;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         RssSource.Companion var23 = RssSource.Companion;
         String userNameSpace = context.getBodyAsString();
         Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsString");
         Object var17 = var23.fromJson-IoAF18A(userNameSpace);
         boolean var6 = false;
         RssSource rssSource = (RssSource)(Result.isFailure-impl(var17) ? null : var17);
         if (rssSource == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            CharSequence var18 = (CharSequence)rssSource.getSourceUrl();
            var6 = false;
            if (var18.length() == 0) {
               return returnData.setErrorMsg("RSS链接不能为空");
            } else {
               var18 = (CharSequence)rssSource.getSourceName();
               var6 = false;
               if (var18.length() == 0) {
                  return returnData.setErrorMsg("RSS名称不能为空");
               } else {
                  userNameSpace = this.getUserNameSpace(context);
                  String[] var7 = new String[]{"rssSources"};
                  JsonArray rssSourceList = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var7));
                  if (rssSourceList == null) {
                     rssSourceList = new JsonArray();
                  }

                  int existIndex = -1;
                  int var8 = 0;
                  int var9 = rssSourceList.size();
                  if (var8 < var9) {
                     do {
                        int i = var8++;
                        var23 = RssSource.Companion;
                        String var12 = rssSourceList.getJsonObject(i).toString();
                        Intrinsics.checkNotNullExpressionValue(var12, "rssSourceList.getJsonObject(i).toString()");
                        Object var22 = var23.fromJson-IoAF18A(var12);
                        boolean var13 = false;
                        RssSource _rssSource = (RssSource)(Result.isFailure-impl(var22) ? null : var22);
                        if (_rssSource != null && _rssSource.getSourceUrl().equals(rssSource.getSourceUrl())) {
                           existIndex = i;
                           break;
                        }
                     } while(var8 < var9);
                  }

                  if (existIndex >= 0) {
                     List list = rssSourceList.getList();
                     list.set(existIndex, JsonObject.mapFrom(rssSource));
                     rssSourceList = new JsonArray(list);
                  } else {
                     rssSourceList.add(JsonObject.mapFrom(rssSource));
                  }

                  this.saveUserStorage(userNameSpace, "rssSources", rssSourceList);
                  return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
               }
            }
         }
      }
   }

   @Nullable
   public final Object saveRssSources(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label106: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label106;
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
               return RssSourceController.this.saveRssSources((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label110: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var20 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var20) {
               return var20;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label110;
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
         var10000 = this.canEditRssSource(context, (Continuation)$continuation);
         if (var10000 == var20) {
            return var20;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         JsonArray rssSourceJsonArray = context.getBodyAsJsonArray();
         if (rssSourceJsonArray == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            String userNameSpace = this.getUserNameSpace(context);
            String[] var7 = new String[]{"rssSources"};
            JsonArray rssSourceList = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var7));
            if (rssSourceList == null) {
               rssSourceList = new JsonArray();
            }

            int var21 = 0;
            int var8 = rssSourceJsonArray.size();
            if (var21 < var8) {
               do {
                  int k = var21++;
                  RssSource.Companion var28 = RssSource.Companion;
                  String var11 = rssSourceJsonArray.getJsonObject(k).toString();
                  Intrinsics.checkNotNullExpressionValue(var11, "rssSourceJsonArray.getJsonObject(k).toString()");
                  Object var22 = var28.fromJson-IoAF18A(var11);
                  boolean var12 = false;
                  RssSource rssSource = (RssSource)(Result.isFailure-impl(var22) ? null : var22);
                  if (rssSource != null) {
                     CharSequence var23 = (CharSequence)rssSource.getSourceUrl();
                     var12 = false;
                     if (var23.length() != 0) {
                        var23 = (CharSequence)rssSource.getSourceName();
                        var12 = false;
                        if (var23.length() != 0) {
                           int existIndex = -1;
                           int var25 = 0;
                           int var13 = rssSourceList.size();
                           if (var25 < var13) {
                              do {
                                 int i = var25++;
                                 var28 = RssSource.Companion;
                                 String var16 = rssSourceList.getJsonObject(i).toString();
                                 Intrinsics.checkNotNullExpressionValue(var16, "rssSourceList.getJsonObject(i).toString()");
                                 Object var27 = var28.fromJson-IoAF18A(var16);
                                 boolean var17 = false;
                                 RssSource _rssSource = (RssSource)(Result.isFailure-impl(var27) ? null : var27);
                                 if (_rssSource != null && _rssSource.getSourceUrl().equals(rssSource.getSourceUrl())) {
                                    existIndex = i;
                                    break;
                                 }
                              } while(var25 < var13);
                           }

                           if (existIndex >= 0) {
                              List list = rssSourceList.getList();
                              list.set(existIndex, JsonObject.mapFrom(rssSource));
                              rssSourceList = new JsonArray(list);
                           } else {
                              rssSourceList.add(JsonObject.mapFrom(rssSource));
                           }
                        }
                     }
                  }
               } while(var21 < var8);
            }

            this.saveUserStorage(userNameSpace, "rssSources", rssSourceList);
            return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public final Object deleteRssSource(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label75: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label75;
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
               return RssSourceController.this.deleteRssSource((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label79: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var16 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = this.checkAuth(context, (Continuation)$continuation);
            if (var10000 == var16) {
               return var16;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label79;
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
         var10000 = this.canEditRssSource(context, (Continuation)$continuation);
         if (var10000 == var16) {
            return var16;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("权限不足");
      } else {
         RssSource.Companion var21 = RssSource.Companion;
         String userNameSpace = context.getBodyAsString();
         Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsString");
         Object var17 = var21.fromJson-IoAF18A(userNameSpace);
         boolean var6 = false;
         RssSource rssSource = (RssSource)(Result.isFailure-impl(var17) ? null : var17);
         if (rssSource == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            userNameSpace = this.getUserNameSpace(context);
            String[] var7 = new String[]{"rssSources"};
            JsonArray rssSourceList = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var7));
            if (rssSourceList == null) {
               rssSourceList = new JsonArray();
            }

            int existIndex = -1;
            int var8 = 0;
            int var9 = rssSourceList.size();
            if (var8 < var9) {
               do {
                  int i = var8++;
                  var21 = RssSource.Companion;
                  String var12 = rssSourceList.getJsonObject(i).toString();
                  Intrinsics.checkNotNullExpressionValue(var12, "rssSourceList.getJsonObject(i).toString()");
                  Object var20 = var21.fromJson-IoAF18A(var12);
                  boolean var13 = false;
                  RssSource _rssSource = (RssSource)(Result.isFailure-impl(var20) ? null : var20);
                  if (_rssSource != null && _rssSource.getSourceUrl().equals(rssSource.getSourceUrl())) {
                     existIndex = i;
                     break;
                  }
               } while(var8 < var9);
            }

            if (existIndex >= 0) {
               rssSourceList.remove(existIndex);
            }

            this.saveUserStorage(userNameSpace, "rssSources", rssSourceList);
            return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public final RssSource getRssSourceByURL(@NotNull String url, @NotNull String userNameSpace) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      CharSequence var3 = (CharSequence)url;
      boolean var4 = false;
      if (var3.length() == 0) {
         return null;
      } else {
         String[] var11 = new String[]{"rssSources"};
         JsonArray list = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var11));
         if (list == null) {
            return null;
         } else {
            int var12 = 0;
            int var5 = list.size();
            if (var12 < var5) {
               do {
                  int i = var12++;
                  RssSource.Companion var10000 = RssSource.Companion;
                  String var8 = list.getJsonObject(i).toString();
                  Intrinsics.checkNotNullExpressionValue(var8, "list.getJsonObject(i).toString()");
                  Object var13 = var10000.fromJson-IoAF18A(var8);
                  boolean var9 = false;
                  RssSource _rssSource = (RssSource)(Result.isFailure-impl(var13) ? null : var13);
                  if (_rssSource != null && _rssSource.getSourceUrl().equals(url)) {
                     return _rssSource;
                  }
               } while(var12 < var5);
            }

            return null;
         }
      }
   }

   @Nullable
   public final Object getRssArticles(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label81: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label81;
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
               return RssSourceController.this.getRssArticles((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label84: {
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
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label84;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         }

         String sourceUrl = null;
         String sortName = null;
         String sortUrl = null;
         boolean var7 = false;
         String userNameSpace;
         int page;
         if (context.request().method() == HttpMethod.POST) {
            userNameSpace = context.getBodyAsJson().getString("sourceUrl");
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsJson.getString(\"sourceUrl\")");
            sourceUrl = userNameSpace;
            userNameSpace = context.getBodyAsJson().getString("sortName", "");
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsJson.getString(\"sortName\", \"\")");
            sortName = userNameSpace;
            userNameSpace = context.getBodyAsJson().getString("sortUrl", "");
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsJson.getString(\"sortUrl\", \"\")");
            sortUrl = userNameSpace;
            Integer var16 = context.getBodyAsJson().getInteger("page", Boxing.boxInt(1));
            Intrinsics.checkNotNullExpressionValue(var16, "context.bodyAsJson.getInteger(\"page\", 1)");
            page = ((Number)var16).intValue();
         } else {
            List var9 = context.queryParam("sourceUrl");
            Intrinsics.checkNotNullExpressionValue(var9, "context.queryParam(\"sourceUrl\")");
            userNameSpace = (String)CollectionsKt.firstOrNull(var9);
            sourceUrl = userNameSpace == null ? "" : userNameSpace;
            var9 = context.queryParam("sortName");
            Intrinsics.checkNotNullExpressionValue(var9, "context.queryParam(\"sortName\")");
            userNameSpace = (String)CollectionsKt.firstOrNull(var9);
            sortName = userNameSpace == null ? "" : userNameSpace;
            var9 = context.queryParam("sortUrl");
            Intrinsics.checkNotNullExpressionValue(var9, "context.queryParam(\"sortUrl\")");
            userNameSpace = (String)CollectionsKt.firstOrNull(var9);
            sortUrl = userNameSpace == null ? "" : userNameSpace;
            var9 = context.queryParam("page");
            Intrinsics.checkNotNullExpressionValue(var9, "context.queryParam(\"page\")");
            userNameSpace = (String)CollectionsKt.firstOrNull(var9);
            int var21;
            if (userNameSpace == null) {
               var21 = 1;
            } else {
               boolean var11 = false;
               Integer var17 = Boxing.boxInt(Integer.parseInt(userNameSpace));
               var21 = var17 == null ? 1 : var17;
            }

            page = var21;
         }

         CharSequence var18 = (CharSequence)sourceUrl;
         boolean var19 = false;
         if (var18.length() == 0) {
            return returnData.setErrorMsg("RSS源链接不能为空");
         }

         var18 = (CharSequence)sortUrl;
         var19 = false;
         if (var18.length() == 0) {
            sortUrl = sourceUrl;
         }

         userNameSpace = this.getUserNameSpace(context);
         RssSource rssSource = this.getRssSourceByURL(sourceUrl, userNameSpace);
         if (rssSource == null) {
            return returnData.setErrorMsg("RSS源不存在");
         }

         Rss var22 = Rss.INSTANCE;
         DebugLog var10005 = (DebugLog)Debug.INSTANCE;
         ((<undefinedtype>)$continuation).L$0 = returnData;
         ((<undefinedtype>)$continuation).L$1 = null;
         ((<undefinedtype>)$continuation).L$2 = null;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = var22.getArticles(sortName, sortUrl, rssSource, page, var10005, (Continuation)$continuation);
         if (var10000 == var14) {
            return var14;
         }
      }

      Pair rssArtcles = (Pair)var10000;
      return ReturnData.setData$default(returnData, rssArtcles, (String)null, 2, (Object)null);
   }

   @Nullable
   public final Object getRssContent(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label104: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label104;
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
               return RssSourceController.this.getRssContent((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      String content;
      Object var10000;
      label107: {
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
            this = (RssSourceController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label107;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         }

         String sourceUrl = null;
         String link = null;
         String origin = null;
         String userNameSpace;
         if (context.request().method() == HttpMethod.POST) {
            userNameSpace = context.getBodyAsJson().getString("sourceUrl");
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsJson.getString(\"sourceUrl\")");
            sourceUrl = userNameSpace;
            userNameSpace = context.getBodyAsJson().getString("link");
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsJson.getString(\"link\")");
            link = userNameSpace;
            userNameSpace = context.getBodyAsJson().getString("origin");
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsJson.getString(\"origin\")");
            origin = userNameSpace;
         } else {
            List var8 = context.queryParam("sourceUrl");
            Intrinsics.checkNotNullExpressionValue(var8, "context.queryParam(\"sourceUrl\")");
            userNameSpace = (String)CollectionsKt.firstOrNull(var8);
            sourceUrl = userNameSpace == null ? "" : userNameSpace;
            var8 = context.queryParam("link");
            Intrinsics.checkNotNullExpressionValue(var8, "context.queryParam(\"link\")");
            userNameSpace = (String)CollectionsKt.firstOrNull(var8);
            link = userNameSpace == null ? "" : userNameSpace;
            var8 = context.queryParam("origin");
            Intrinsics.checkNotNullExpressionValue(var8, "context.queryParam(\"origin\")");
            userNameSpace = (String)CollectionsKt.firstOrNull(var8);
            origin = userNameSpace == null ? "" : userNameSpace;
         }

         CharSequence var15 = (CharSequence)sourceUrl;
         boolean var16 = false;
         if (var15.length() == 0) {
            return returnData.setErrorMsg("RSS链接不能为空");
         }

         var15 = (CharSequence)link;
         var16 = false;
         if (var15.length() == 0) {
            return returnData.setErrorMsg("RSS文章链接不能为空");
         }

         var15 = (CharSequence)origin;
         var16 = false;
         if (var15.length() == 0) {
            return returnData.setErrorMsg("RSS文章来源不能为空");
         }

         userNameSpace = this.getUserNameSpace(context);
         RssSource rssSource = this.getRssSourceByURL(sourceUrl, userNameSpace);
         if (rssSource == null) {
            return returnData.setErrorMsg("RSS源不存在");
         }

         RssArticle rssArticle = new RssArticle(origin, (String)null, (String)null, 0L, link, (String)null, (String)null, (String)null, (String)null, false, (String)null, 2030, (DefaultConstructorMarker)null);
         content = "";
         if (rssSource.getRuleContent() == null) {
            return ReturnData.setData$default(returnData, content, (String)null, 2, (Object)null);
         }

         Rss var18 = Rss.INSTANCE;
         String var11 = rssSource.getRuleContent();
         if (var11 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
         }

         DebugLog var10004 = (DebugLog)Debug.INSTANCE;
         ((<undefinedtype>)$continuation).L$0 = returnData;
         ((<undefinedtype>)$continuation).L$1 = null;
         ((<undefinedtype>)$continuation).L$2 = null;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = var18.getContent(rssArticle, var11, rssSource, var10004, (Continuation)$continuation);
         if (var10000 == var14) {
            return var14;
         }
      }

      content = (String)var10000;
      return ReturnData.setData$default(returnData, content, (String)null, 2, (Object)null);
   }
}
