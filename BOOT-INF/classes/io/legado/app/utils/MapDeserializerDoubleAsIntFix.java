package io.legado.app.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0005J0\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\b¨\u0006\u000f"},
   d2 = {"Lio/legado/app/utils/MapDeserializerDoubleAsIntFix;", "Lcom/google/gson/JsonDeserializer;", "", "", "", "()V", "deserialize", "jsonElement", "Lcom/google/gson/JsonElement;", "type", "Ljava/lang/reflect/Type;", "jsonDeserializationContext", "Lcom/google/gson/JsonDeserializationContext;", "read", "json", "reader-pro"}
)
public final class MapDeserializerDoubleAsIntFix implements JsonDeserializer<Map<String, ? extends Object>> {
   @Nullable
   public Map<String, Object> deserialize(@NotNull JsonElement jsonElement, @NotNull Type type, @NotNull JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
      Intrinsics.checkNotNullParameter(jsonElement, "jsonElement");
      Intrinsics.checkNotNullParameter(type, "type");
      Intrinsics.checkNotNullParameter(jsonDeserializationContext, "jsonDeserializationContext");
      Object var4 = this.read(jsonElement);
      return var4 instanceof Map ? (Map)var4 : null;
   }

   @Nullable
   public final Object read(@NotNull JsonElement json) {
      Intrinsics.checkNotNullParameter(json, "json");
      if (json.isJsonArray()) {
         List list = (List)(new ArrayList());
         JsonArray arr = json.getAsJsonArray();
         Iterator var19 = arr.iterator();

         while(var19.hasNext()) {
            JsonElement anArr = (JsonElement)var19.next();
            Intrinsics.checkNotNullExpressionValue(anArr, "anArr");
            list.add(this.read(anArr));
         }

         return list;
      } else if (!json.isJsonObject()) {
         if (json.isJsonPrimitive()) {
            JsonPrimitive prim = json.getAsJsonPrimitive();
            if (prim.isBoolean()) {
               return prim.getAsBoolean();
            }

            if (prim.isString()) {
               return prim.getAsString();
            }

            if (prim.isNumber()) {
               Number var17 = prim.getAsNumber();
               Intrinsics.checkNotNullExpressionValue(var17, "prim.asNumber");
               Number num = var17;
               double var18 = var17.doubleValue();
               boolean var21 = false;
               return Math.ceil(var18) == (double)num.longValue() ? num.longValue() : num.doubleValue();
            }
         }

         return null;
      } else {
         Map map = (Map)(new LinkedTreeMap());
         JsonObject obj = json.getAsJsonObject();
         Set entitySet = obj.entrySet();
         Iterator var5 = entitySet.iterator();

         while(var5.hasNext()) {
            Entry var6 = (Entry)var5.next();
            Intrinsics.checkNotNullExpressionValue(var6, "entitySet");
            boolean var9 = false;
            String key = (String)var6.getKey();
            boolean var10 = false;
            JsonElement value = (JsonElement)var6.getValue();
            Intrinsics.checkNotNullExpressionValue(key, "key");
            Intrinsics.checkNotNullExpressionValue(value, "value");
            Object var11 = this.read(value);
            boolean var12 = false;
            map.put(key, var11);
         }

         return map;
      }
   }
}
