package com.htmake.reader.api.controller;

import com.htmake.reader.api.ReturnData;
import com.htmake.reader.db.DB;
import com.htmake.reader.utils.ExtKt;
import io.legado.app.data.entities.BookGroup;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0016J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0017H\u0016J\u0019\u0010\u001f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "},
   d2 = {"Lcom/htmake/reader/api/controller/BookGroupController;", "Lcom/htmake/reader/api/controller/BaseController;", "Lcom/htmake/reader/api/controller/CURD;", "Lio/legado/app/data/entities/BookGroup;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "beforeSave", "Lcom/htmake/reader/api/ReturnData;", "var1", "db", "Lcom/htmake/reader/db/DB;", "checkUserAuth", "", "context", "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checker", "Lio/vertx/core/json/JsonObject;", "var2", "getEntityClass", "Ljava/lang/Class;", "getTableName", "", "getUserNS", "onCheckEnd", "", "bookGroupList", "Lio/vertx/core/json/JsonArray;", "onList", "userNameSpace", "saveBookGroupOrder", "reader-pro"}
)
public final class BookGroupController extends BaseController implements CURD<BookGroup> {
   public BookGroupController(@NotNull CoroutineContext coroutineContext) {
      Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
      super(coroutineContext);
   }

   @NotNull
   public String getTableName() {
      return "bookGroup";
   }

   @NotNull
   public JsonArray onList(@NotNull JsonArray var1, @NotNull String userNameSpace) {
      Intrinsics.checkNotNullParameter(var1, "var1");
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      if (var1.size() == 0) {
         JsonArray var2 = ExtKt.asJsonArray("\n            [{\"groupId\":-1,\"groupName\":\"全部\",\"order\":-10,\"show\":true},{\"groupId\":-2,\"groupName\":\"本地\",\"order\":-9,\"show\":true},{\"groupId\":-3,\"groupName\":\"音频\",\"order\":-8,\"show\":true},{\"groupId\":-4,\"groupName\":\"未分组\",\"order\":-7,\"show\":true},{\"groupId\":-5,\"groupName\":\"更新错误\",\"order\":-6,\"show\":true}]\n            ");
         if (var2 != null) {
            this.saveUserStorage(userNameSpace, "bookGroup", var2);
            return var2;
         }
      }

      return var1;
   }

   public boolean checker(@NotNull JsonObject var1, @NotNull BookGroup var2) {
      Intrinsics.checkNotNullParameter(var1, "var1");
      Intrinsics.checkNotNullParameter(var2, "var2");
      return Long.valueOf(var2.getGroupId()).equals(var1.getLong("groupId"));
   }

   public void onCheckEnd(@NotNull BookGroup var1, boolean var2, @NotNull JsonArray bookGroupList) {
      Intrinsics.checkNotNullParameter(var1, "var1");
      Intrinsics.checkNotNullParameter(bookGroupList, "bookGroupList");
      if (!var2) {
         int maxOrder = 0;
         Iterable var7 = (Iterable)bookGroupList;
         boolean var8 = false;
         long var9 = 0L;

         long var22;
         for(Iterator var11 = var7.iterator(); var11.hasNext(); var9 += var22) {
            Object var12 = var11.next();
            int var14 = false;
            JsonObject var15 = ExtKt.asJsonObject(var12);
            long var10000;
            if (var15 == null) {
               var10000 = 0L;
            } else {
               Long var16 = var15.getLong("groupId", 0L);
               var10000 = var16 == null ? 0L : var16;
            }

            long id = var10000;
            JsonObject var26 = ExtKt.asJsonObject(var12);
            int var27;
            if (var26 == null) {
               var27 = 0;
            } else {
               Integer var19 = var26.getInteger("order", 0);
               var27 = var19 == null ? 0 : var19;
            }

            int order = var27;
            maxOrder = order > maxOrder ? order : maxOrder;
            var22 = id > 0L ? id : 0L;
         }

         long idsSum = var9;

         long id;
         for(id = 1L; (id & idsSum) != 0L; id <<= 1) {
         }

         var1.setGroupId(id);
         var1.setOrder(maxOrder + 1);
      }

   }

   @Nullable
   public ReturnData beforeSave(@NotNull BookGroup var1, @NotNull DB<BookGroup> db) {
      Intrinsics.checkNotNullParameter(var1, "var1");
      Intrinsics.checkNotNullParameter(db, "db");
      ReturnData returnData = new ReturnData();
      CharSequence var4 = (CharSequence)var1.getGroupName();
      boolean var5 = false;
      return var4.length() == 0 ? returnData.setErrorMsg("分组名称不能为空") : null;
   }

   @Nullable
   public Object checkUserAuth(@NotNull RoutingContext context, @NotNull Continuation<? super Boolean> $completion) {
      return this.checkAuth(context, $completion);
   }

   @NotNull
   public String getUserNS(@NotNull RoutingContext context) {
      Intrinsics.checkNotNullParameter(context, "context");
      return this.getUserNameSpace(context);
   }

   @NotNull
   public Class<BookGroup> getEntityClass() {
      return BookGroup.class;
   }

   @Nullable
   public final Object saveBookGroupOrder(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label60: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label60;
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
               return BookGroupController.this.saveBookGroupOrder((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var17 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
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
         if (var10000 == var17) {
            return var17;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (BookGroupController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!(Boolean)var10000) {
         return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
      } else {
         JsonArray bookGroupOrder = context.getBodyAsJson().getJsonArray("order", (JsonArray)null);
         if (bookGroupOrder == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            String userNameSpace = this.getUserNameSpace(context);
            String[] var7 = new String[]{"bookGroup"};
            JsonArray bookGroupList = ExtKt.asJsonArray(this.getUserStorage(userNameSpace, var7));
            if (bookGroupList == null) {
               bookGroupList = new JsonArray();
            }

            boolean var8 = false;
            Map orderMap = (Map)(new LinkedHashMap());
            int var19 = 0;
            int var9 = bookGroupOrder.size();
            int i;
            if (var19 < var9) {
               do {
                  i = var19++;
                  Long var11 = bookGroupOrder.getJsonObject(i).getLong("groupId");
                  Intrinsics.checkNotNullExpressionValue(var11, "bookGroupOrder.getJsonObject(i).getLong(\"groupId\")");
                  Integer var21 = bookGroupOrder.getJsonObject(i).getInteger("order");
                  Intrinsics.checkNotNullExpressionValue(var21, "bookGroupOrder.getJsonObject(i).getInteger(\"order\")");
                  orderMap.put(var11, var21);
               } while(var19 < var9);
            }

            List groupList = bookGroupList.getList();
            var9 = 0;
            i = bookGroupList.size();
            if (var9 < i) {
               do {
                  int i = var9++;
                  BookGroup bookGroup = (BookGroup)bookGroupList.getJsonObject(i).mapTo(BookGroup.class);
                  if (orderMap.containsKey(Boxing.boxLong(bookGroup.getGroupId()))) {
                     Object var14 = orderMap.get(Boxing.boxLong(bookGroup.getGroupId()));
                     Integer var13 = var14 instanceof Integer ? (Integer)var14 : null;
                     bookGroup.setOrder(var13 == null ? bookGroup.getOrder() : var13);
                     groupList.set(i, JsonObject.mapFrom(bookGroup));
                  }
               } while(var9 < i);
            }

            bookGroupList = new JsonArray(groupList);
            this.saveUserStorage(userNameSpace, "bookGroup", bookGroupList);
            return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public ReturnData beforeAdd(@NotNull BookGroup val1, @NotNull DB<BookGroup> db) {
      return CURD.DefaultImpls.beforeAdd(this, val1, db);
   }

   @Nullable
   public ReturnData beforeDelete(@NotNull BookGroup val1, @NotNull DB<BookGroup> db) {
      return CURD.DefaultImpls.beforeDelete(this, val1, db);
   }

   @NotNull
   public BookGroup convertToEntity(@NotNull JsonObject var1) {
      return (BookGroup)CURD.DefaultImpls.convertToEntity(this, var1);
   }

   @NotNull
   public BookGroup[] convertToEntityList(@NotNull String var1) {
      return (BookGroup[])CURD.DefaultImpls.convertToEntityList(this, var1);
   }

   @Nullable
   public Object delete(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      return CURD.DefaultImpls.delete(this, context, $completion);
   }

   @Nullable
   public Object deleteMulti(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      return CURD.DefaultImpls.deleteMulti(this, context, $completion);
   }

   @Nullable
   public Object list(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      return CURD.DefaultImpls.list(this, context, $completion);
   }

   @Nullable
   public Object save(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      return CURD.DefaultImpls.save(this, context, $completion);
   }

   @Nullable
   public Object saveMulti(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      return CURD.DefaultImpls.saveMulti(this, context, $completion);
   }
}
