package com.htmake.reader.api.controller;

import com.htmake.reader.api.ReturnData;
import com.htmake.reader.db.DB;
import com.htmake.reader.utils.ExtKt;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.lang.reflect.Array;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J%\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016¢\u0006\u0002\u0010\bJ%\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016¢\u0006\u0002\u0010\bJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0016J\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010\u0011\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0019\u0010\u001c\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH&J\b\u0010\u001f\u001a\u00020\u0019H&J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eH&J\u0019\u0010!\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ%\u0010\"\u001a\u00020#2\u0006\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0002\u0010&J\u0018\u0010'\u001a\u00020%2\u0006\u0010\u0011\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0019H\u0016J\u0019\u0010)\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0019\u0010*\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"},
   d2 = {"Lcom/htmake/reader/api/controller/CURD;", "T", "", "beforeAdd", "Lcom/htmake/reader/api/ReturnData;", "val1", "db", "Lcom/htmake/reader/db/DB;", "(Ljava/lang/Object;Lcom/htmake/reader/db/DB;)Lcom/htmake/reader/api/ReturnData;", "beforeDelete", "beforeSave", "checkUserAuth", "", "context", "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checker", "var1", "Lio/vertx/core/json/JsonObject;", "var2", "(Lio/vertx/core/json/JsonObject;Ljava/lang/Object;)Z", "convertToEntity", "(Lio/vertx/core/json/JsonObject;)Ljava/lang/Object;", "convertToEntityList", "", "", "(Ljava/lang/String;)[Ljava/lang/Object;", "delete", "deleteMulti", "getEntityClass", "Ljava/lang/Class;", "getTableName", "getUserNS", "list", "onCheckEnd", "", "var3", "Lio/vertx/core/json/JsonArray;", "(Ljava/lang/Object;ZLio/vertx/core/json/JsonArray;)V", "onList", "userNameSpace", "save", "saveMulti", "reader-pro"}
)
public interface CURD<T> {
   @NotNull
   String getTableName();

   T convertToEntity(@NotNull JsonObject var1);

   @NotNull
   T[] convertToEntityList(@NotNull String var1);

   @NotNull
   JsonArray onList(@NotNull JsonArray var1, @NotNull String userNameSpace);

   boolean checker(@NotNull JsonObject var1, T var2);

   void onCheckEnd(T var1, boolean var2, @NotNull JsonArray var3);

   @Nullable
   ReturnData beforeSave(T val1, @NotNull DB<T> db);

   @Nullable
   ReturnData beforeAdd(T val1, @NotNull DB<T> db);

   @Nullable
   ReturnData beforeDelete(T val1, @NotNull DB<T> db);

   @Nullable
   Object checkUserAuth(@NotNull RoutingContext context, @NotNull Continuation<? super Boolean> $completion);

   @NotNull
   String getUserNS(@NotNull RoutingContext context);

   @NotNull
   Class<T> getEntityClass();

   @Nullable
   Object list(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

   @Nullable
   Object save(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

   @Nullable
   Object saveMulti(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

   @Nullable
   Object delete(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

   @Nullable
   Object deleteMulti(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion);

   @Metadata(
      mv = {1, 5, 1},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static <T> T convertToEntity(@NotNull CURD<T> this, @NotNull JsonObject var1) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(var1, "var1");
         return var1.mapTo(var0.getEntityClass());
      }

      @NotNull
      public static <T> T[] convertToEntityList(@NotNull CURD<T> this, @NotNull String var1) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(var1, "var1");
         Class clazz = Array.newInstance(var0.getEntityClass(), 0).getClass();
         Object var4 = ExtKt.getGson().fromJson(var1, clazz);
         Intrinsics.checkNotNullExpressionValue(var4, "gson.fromJson(var1, clazz)");
         Object[] itemList = (Object[])var4;
         return itemList;
      }

      @NotNull
      public static <T> JsonArray onList(@NotNull CURD<T> this, @NotNull JsonArray var1, @NotNull String userNameSpace) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(var1, "var1");
         Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
         return var1;
      }

      public static <T> void onCheckEnd(@NotNull CURD<T> this, T var1, boolean var2, @NotNull JsonArray var3) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(var3, "var3");
      }

      @Nullable
      public static <T> ReturnData beforeSave(@NotNull CURD<T> this, T val1, @NotNull DB<T> db) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(db, "db");
         return null;
      }

      @Nullable
      public static <T> ReturnData beforeAdd(@NotNull CURD<T> this, T val1, @NotNull DB<T> db) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(db, "db");
         return null;
      }

      @Nullable
      public static <T> ReturnData beforeDelete(@NotNull CURD<T> this, T val1, @NotNull DB<T> db) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(db, "db");
         return null;
      }

      @Nullable
      public static <T> Object list(@NotNull CURD<T> this, @NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
         Object $continuation;
         label24: {
            if (var2 instanceof <undefinedtype>) {
               $continuation = (<undefinedtype>)var2;
               if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
                  ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
                  break label24;
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
                  return CURD.DefaultImpls.list((CURD)null, (RoutingContext)null, (Continuation)this);
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
            ((<undefinedtype>)$continuation).L$0 = var0;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = var0.checkUserAuth(context, (Continuation)$continuation);
            if (var10000 == var9) {
               return var9;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            var0 = (CURD)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         } else {
            String userNameSpace = var0.getUserNS(context);
            JsonArray list = DB.Companion.table$default(DB.Companion, userNameSpace, var0.getTableName(), (String)null, 4, (Object)null).readAll();
            list = var0.onList(list, userNameSpace);
            List var6 = list.getList();
            Intrinsics.checkNotNullExpressionValue(var6, "list.getList()");
            return ReturnData.setData$default(returnData, var6, (String)null, 2, (Object)null);
         }
      }

      @Nullable
      public static <T> Object save(@NotNull CURD<T> this, @NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
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
                  return CURD.DefaultImpls.save((CURD)null, (RoutingContext)null, (Continuation)this);
               }
            };
         }

         Object $result = ((<undefinedtype>)$continuation).result;
         Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         ReturnData returnData;
         Object var10000;
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = var0;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = var0.checkUserAuth(context, (Continuation)$continuation);
            if (var10000 == var10) {
               return var10;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            var0 = (CURD)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         } else {
            JsonObject var5 = context.getBodyAsJson();
            Intrinsics.checkNotNullExpressionValue(var5, "context.bodyAsJson");
            Object entity = var0.convertToEntity(var5);
            String userNameSpace = var0.getUserNS(context);
            DB db = DB.Companion.table$default(DB.Companion, userNameSpace, var0.getTableName(), (String)null, 4, (Object)null);
            ReturnData result = var0.beforeSave(entity, db);
            if (result != null) {
               return result;
            } else {
               db.save(entity, (Function3)(new Function3<T, Boolean, JsonArray, Unit>(var0) {
                  public final void invoke(T p0, boolean p1, @NotNull JsonArray p2) {
                     Intrinsics.checkNotNullParameter(p2, "p2");
                     ((CURD)this.receiver).onCheckEnd(p0, p1, p2);
                  }
               }), (Function2)(new Function2<JsonObject, T, Boolean>() {
                  public final boolean invoke(@NotNull JsonObject jsonObject, T value) {
                     Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                     return var0.checker(jsonObject, value);
                  }
               }));
               return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
            }
         }
      }

      @Nullable
      public static <T> Object saveMulti(@NotNull CURD<T> this, @NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
         Object $continuation;
         label42: {
            if (var2 instanceof <undefinedtype>) {
               $continuation = (<undefinedtype>)var2;
               if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
                  ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
                  break label42;
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
                  return CURD.DefaultImpls.saveMulti((CURD)null, (RoutingContext)null, (Continuation)this);
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
            ((<undefinedtype>)$continuation).L$0 = var0;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = var0.checkUserAuth(context, (Continuation)$continuation);
            if (var10000 == var14) {
               return var14;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            var0 = (CURD)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         } else {
            String userNameSpace = context.getBodyAsString();
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsString");
            Object[] itemList = var0.convertToEntityList(userNameSpace);
            boolean var6 = false;
            if (itemList.length == 0) {
               return returnData.setErrorMsg("参数错误");
            } else {
               userNameSpace = var0.getUserNS(context);
               DB db = DB.Companion.table$default(DB.Companion, userNameSpace, var0.getTableName(), (String)null, 4, (Object)null);
               Object[] var7 = itemList;
               int var8 = 0;
               int var9 = itemList.length;

               ReturnData result;
               do {
                  if (var8 >= var9) {
                     db.saveMulti(itemList, (Function3)(new Function3<T, Boolean, JsonArray, Unit>(var0) {
                        public final void invoke(T p0, boolean p1, @NotNull JsonArray p2) {
                           Intrinsics.checkNotNullParameter(p2, "p2");
                           ((CURD)this.receiver).onCheckEnd(p0, p1, p2);
                        }
                     }), (Function2)(new Function2<JsonObject, T, Boolean>() {
                        public final boolean invoke(@NotNull JsonObject jsonObject, T value) {
                           Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
                           return var0.checker(jsonObject, value);
                        }
                     }));
                     return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
                  }

                  Object item = var7[var8];
                  ++var8;
                  result = var0.beforeSave(item, db);
               } while(result == null);

               return result;
            }
         }
      }

      @Nullable
      public static <T> Object delete(@NotNull CURD<T> this, @NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
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
                  return CURD.DefaultImpls.delete((CURD)null, (RoutingContext)null, (Continuation)this);
               }
            };
         }

         Object $result = ((<undefinedtype>)$continuation).result;
         Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         ReturnData returnData;
         Object var10000;
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            returnData = new ReturnData();
            ((<undefinedtype>)$continuation).L$0 = var0;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = var0.checkUserAuth(context, (Continuation)$continuation);
            if (var10000 == var10) {
               return var10;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            var0 = (CURD)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         } else {
            JsonObject var5 = context.getBodyAsJson();
            Intrinsics.checkNotNullExpressionValue(var5, "context.bodyAsJson");
            final Object entity = var0.convertToEntity(var5);
            String userNameSpace = var0.getUserNS(context);
            DB db = DB.Companion.table$default(DB.Companion, userNameSpace, var0.getTableName(), (String)null, 4, (Object)null);
            ReturnData result = var0.beforeDelete(entity, db);
            if (result != null) {
               return result;
            } else {
               db.delete((Function1)(new Function1<JsonObject, Boolean>() {
                  public final boolean invoke(@NotNull JsonObject it) {
                     Intrinsics.checkNotNullParameter(it, "it");
                     return var0.checker(it, entity);
                  }
               }));
               return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
            }
         }
      }

      @Nullable
      public static <T> Object deleteMulti(@NotNull CURD<T> this, @NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
         Object $continuation;
         label42: {
            if (var2 instanceof <undefinedtype>) {
               $continuation = (<undefinedtype>)var2;
               if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
                  ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
                  break label42;
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
                  return CURD.DefaultImpls.deleteMulti((CURD)null, (RoutingContext)null, (Continuation)this);
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
            ((<undefinedtype>)$continuation).L$0 = var0;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).L$2 = returnData;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = var0.checkUserAuth(context, (Continuation)$continuation);
            if (var10000 == var14) {
               return var14;
            }
            break;
         case 1:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            var0 = (CURD)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!(Boolean)var10000) {
            return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
         } else {
            final ObjectRef itemList = new ObjectRef();
            String userNameSpace = context.getBodyAsString();
            Intrinsics.checkNotNullExpressionValue(userNameSpace, "context.bodyAsString");
            itemList.element = var0.convertToEntityList(userNameSpace);
            Object[] var15 = (Object[])itemList.element;
            boolean var6 = false;
            if (var15.length == 0) {
               return returnData.setErrorMsg("参数错误");
            } else {
               userNameSpace = var0.getUserNS(context);
               DB db = DB.Companion.table$default(DB.Companion, userNameSpace, var0.getTableName(), (String)null, 4, (Object)null);
               Object[] var7 = (Object[])itemList.element;
               int var8 = 0;
               int var9 = var7.length;

               ReturnData result;
               do {
                  if (var8 >= var9) {
                     db.delete((Function1)(new Function1<JsonObject, Boolean>() {
                        public final boolean invoke(@NotNull JsonObject it) {
                           Intrinsics.checkNotNullParameter(it, "it");
                           int var2 = 0;
                           int var3 = ((Object[])itemList.element).length;
                           if (var2 < var3) {
                              do {
                                 int k = var2++;
                                 if (var0.checker(it, ((Object[])itemList.element)[k])) {
                                    return true;
                                 }
                              } while(var2 < var3);
                           }

                           return false;
                        }
                     }));
                     return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
                  }

                  Object item = var7[var8];
                  ++var8;
                  result = var0.beforeDelete(item, db);
               } while(result == null);

               return result;
            }
         }
      }
   }
}
