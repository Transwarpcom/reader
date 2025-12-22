package com.htmake.reader.db;

import com.htmake.reader.utils.ExtKt;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0016J7\u0010\r\u001a\u0004\u0018\u00018\u0000\"\b\b\u0001\u0010\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u0002H\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016JQ\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u00002 \u0010\u0018\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u00192\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u001aH\u0016¢\u0006\u0002\u0010\u001bJW\u0010\u001c\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u001d2 \u0010\u0018\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u00192\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u001aH\u0016¢\u0006\u0002\u0010\u001e¨\u0006\u001f"},
   d2 = {"Lcom/htmake/reader/db/SQLTable;", "T", "Lcom/htmake/reader/db/DB;", "userNameSpace", "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "delete", "", "checker", "Lkotlin/Function1;", "Lio/vertx/core/json/JsonObject;", "", "findBy", "P", "", "field", "value", "clazz", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "readAll", "Lio/vertx/core/json/JsonArray;", "save", "onCheckEnd", "Lkotlin/Function3;", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;)V", "saveMulti", "", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;)V", "reader-pro"}
)
public final class SQLTable<T> extends DB<T> {
   public SQLTable(@NotNull String userNameSpace, @NotNull String name) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      Intrinsics.checkNotNullParameter(name, "name");
      super(userNameSpace, name);
   }

   @NotNull
   public JsonArray readAll() {
      String[] var2 = new String[]{"data", this.getUserNameSpace(), this.getName()};
      JsonArray dataList = ExtKt.asJsonArray(ExtKt.getStorage$default(var2, (String)null, 2, (Object)null));
      if (dataList == null) {
         dataList = new JsonArray();
      }

      this.setCachedValue(dataList);
      return dataList;
   }

   @Nullable
   public <P> T findBy(@NotNull String field, @NotNull P value, @NotNull Class<T> clazz) {
      Intrinsics.checkNotNullParameter(field, "field");
      Intrinsics.checkNotNullParameter(value, "value");
      Intrinsics.checkNotNullParameter(clazz, "clazz");
      JsonArray dataList = this.readAll();
      int var5 = 0;
      int var6 = dataList.size();
      if (var5 < var6) {
         do {
            int i = var5++;
            Object objValue = dataList.getJsonObject(i).getValue(field);
            if (value.equals(objValue)) {
               return dataList.getJsonObject(i).mapTo(clazz);
            }
         } while(var5 < var6);
      }

      return null;
   }

   public void save(T value, @Nullable Function3<? super T, ? super Boolean, ? super JsonArray, Unit> onCheckEnd, @NotNull Function2<? super JsonObject, ? super T, Boolean> checker) {
      Intrinsics.checkNotNullParameter(checker, "checker");
      JsonArray dataList = this.readAll();
      int existIndex = -1;
      int var6 = 0;
      int var7 = dataList.size();
      if (var6 < var7) {
         do {
            int i = var6++;
            JsonObject var9 = dataList.getJsonObject(i);
            Intrinsics.checkNotNullExpressionValue(var9, "dataList.getJsonObject(i)");
            if ((Boolean)checker.invoke(var9, value)) {
               existIndex = i;
               break;
            }
         } while(var6 < var7);
      }

      if (onCheckEnd != null) {
         onCheckEnd.invoke(value, existIndex >= 0, dataList);
      }

      if (existIndex >= 0) {
         List list = dataList.getList();
         list.set(existIndex, JsonObject.mapFrom(value));
         dataList = new JsonArray(list);
      } else {
         dataList.add(JsonObject.mapFrom(value));
      }

      this.setCachedValue(dataList);
      this.save();
   }

   public void saveMulti(@NotNull T[] value, @Nullable Function3<? super T, ? super Boolean, ? super JsonArray, Unit> onCheckEnd, @NotNull Function2<? super JsonObject, ? super T, Boolean> checker) {
      Intrinsics.checkNotNullParameter(value, "value");
      Intrinsics.checkNotNullParameter(checker, "checker");
      JsonArray dataList = this.readAll();
      int existIndex = -1;
      Object[] var6 = value;
      int var7 = 0;
      int var8 = value.length;

      while(var7 < var8) {
         Object j = var6[var7];
         ++var7;
         int var10 = 0;
         int var11 = dataList.size();
         if (var10 < var11) {
            do {
               int i = var10++;
               JsonObject var13 = dataList.getJsonObject(i);
               Intrinsics.checkNotNullExpressionValue(var13, "dataList.getJsonObject(i)");
               if ((Boolean)checker.invoke(var13, j)) {
                  existIndex = i;
                  break;
               }
            } while(var10 < var11);
         }

         if (onCheckEnd != null) {
            onCheckEnd.invoke(j, existIndex >= 0, dataList);
         }

         if (existIndex >= 0) {
            List list = dataList.getList();
            list.set(existIndex, JsonObject.mapFrom(j));
            dataList = new JsonArray(list);
         } else {
            dataList.add(JsonObject.mapFrom(j));
         }
      }

      this.setCachedValue(dataList);
      this.save();
   }

   public void delete(@NotNull Function1<? super JsonObject, Boolean> checker) {
      Intrinsics.checkNotNullParameter(checker, "checker");
      Object dataList = null;
      dataList = this.readAll();
      boolean var4 = false;
      List removeIndexList = (List)(new ArrayList());
      int var10 = 0;
      int var5 = dataList.size();
      if (var10 < var5) {
         do {
            int i = var10++;
            JsonObject var7 = dataList.getJsonObject(i);
            Intrinsics.checkNotNullExpressionValue(var7, "dataList.getJsonObject(i)");
            if ((Boolean)checker.invoke(var7)) {
               removeIndexList.add(i);
            }
         } while(var10 < var5);
      }

      if (removeIndexList.size() > 0) {
         Iterable $this$forEach$iv = (Iterable)removeIndexList;
         int $i$f$forEach = false;
         Iterator var13 = $this$forEach$iv.iterator();

         while(var13.hasNext()) {
            Object element$iv = var13.next();
            int it = ((Number)element$iv).intValue();
            int var9 = false;
            dataList.remove(it);
         }
      }

      this.setCachedValue(dataList);
      this.save();
   }

   public void save() {
      String[] var1 = new String[]{"data", this.getUserNameSpace(), this.getName()};
      ExtKt.saveStorage$default(var1, this.getCachedValue(), false, (String)null, 12, (Object)null);
   }
}
