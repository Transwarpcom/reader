package io.legado.app.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Result.Companion;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0011\u0010\u0006\u001a\u00020\u0007\"\u0006\b\u0000\u0010\b\u0018\u0001H\u0086\b\u001a5\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\b\u0018\u00010\u000b0\n\"\u0006\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a5\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\b\u0018\u00010\u000b0\n\"\u0006\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a/\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\b0\n\"\u0006\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a/\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\b0\n\"\u0006\b\u0000\u0010\b\u0018\u0001*\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"},
   d2 = {"GSON", "Lcom/google/gson/Gson;", "getGSON", "()Lcom/google/gson/Gson;", "GSON$delegate", "Lkotlin/Lazy;", "genericType", "Ljava/lang/reflect/Type;", "T", "fromJsonArray", "Lkotlin/Result;", "", "inputStream", "Ljava/io/InputStream;", "(Lcom/google/gson/Gson;Ljava/io/InputStream;)Ljava/lang/Object;", "json", "", "(Lcom/google/gson/Gson;Ljava/lang/String;)Ljava/lang/Object;", "fromJsonObject", "writeToOutputStream", "", "out", "Ljava/io/OutputStream;", "any", "", "reader-pro"}
)
public final class GsonExtensionsKt {
   @NotNull
   private static final Lazy GSON$delegate;

   @NotNull
   public static final Gson getGSON() {
      Lazy var1 = GSON$delegate;
      Object var2 = null;
      boolean var4 = false;
      Object var0 = var1.getValue();
      Intrinsics.checkNotNullExpressionValue(var0, "<get-GSON>(...)");
      return (Gson)var0;
   }

   // $FF: synthetic method
   public static final <T> Type genericType() {
      int $i$f$genericType = false;
      Intrinsics.needClassReification();
      Type var1 = (new TypeToken<T>() {
      }).getType();
      Intrinsics.checkNotNullExpressionValue(var1, "object : TypeToken<T>() {}.type");
      return var1;
   }

   // $FF: synthetic method
   public static final <T> Object fromJsonObject(Gson $this$fromJsonObject, String json) {
      Intrinsics.checkNotNullParameter($this$fromJsonObject, "<this>");
      int $i$f$fromJsonObject = false;
      boolean var3 = false;

      Object var4;
      try {
         Companion var9 = Result.Companion;
         int var5 = false;
         int $i$f$genericType = false;
         Intrinsics.needClassReification();
         Type var12 = (new GsonExtensionsKt$fromJsonObject$lambda-0$$inlined$genericType$1()).getType();
         Intrinsics.checkNotNullExpressionValue(var12, "object : TypeToken<T>() {}.type");
         Object var10000 = $this$fromJsonObject.fromJson(json, (Type)var12);
         Intrinsics.reifiedOperationMarker(2, "T");
         Object var10 = (Object)var10000;
         $i$f$genericType = false;
         var4 = Result.constructor-impl(var10);
      } catch (Throwable var8) {
         Companion var6 = Result.Companion;
         boolean var7 = false;
         var4 = Result.constructor-impl(ResultKt.createFailure(var8));
      }

      return var4;
   }

   // $FF: synthetic method
   public static final <T> Object fromJsonArray(Gson $this$fromJsonArray, String json) {
      Intrinsics.checkNotNullParameter($this$fromJsonArray, "<this>");
      int $i$f$fromJsonArray = false;
      boolean var3 = false;

      Object var4;
      try {
         Companion var9 = Result.Companion;
         int var5 = false;
         Intrinsics.reifiedOperationMarker(4, "T");
         Object var11 = $this$fromJsonArray.fromJson(json, (Type)(new ParameterizedTypeImpl((Class)Object.class)));
         List var10 = var11 instanceof List ? (List)var11 : null;
         boolean var12 = false;
         var4 = Result.constructor-impl(var10);
      } catch (Throwable var8) {
         Companion var6 = Result.Companion;
         boolean var7 = false;
         var4 = Result.constructor-impl(ResultKt.createFailure(var8));
      }

      return var4;
   }

   // $FF: synthetic method
   public static final <T> Object fromJsonObject(Gson $this$fromJsonObject, InputStream inputStream) {
      Intrinsics.checkNotNullParameter($this$fromJsonObject, "<this>");
      int $i$f$fromJsonObject = false;
      boolean var3 = false;

      Object var4;
      boolean $i$f$genericType;
      try {
         Companion var10 = Result.Companion;
         int var5 = false;
         InputStreamReader reader = new InputStreamReader(inputStream);
         Reader var10001 = (Reader)reader;
         $i$f$genericType = false;
         Intrinsics.needClassReification();
         Type var8 = (new GsonExtensionsKt$fromJsonObject$lambda-2$$inlined$genericType$1()).getType();
         Intrinsics.checkNotNullExpressionValue(var8, "object : TypeToken<T>() {}.type");
         Object var10000 = $this$fromJsonObject.fromJson(var10001, (Type)var8);
         Intrinsics.reifiedOperationMarker(2, "T");
         Object var11 = (Object)var10000;
         boolean var13 = false;
         var4 = Result.constructor-impl(var11);
      } catch (Throwable var9) {
         Companion var6 = Result.Companion;
         $i$f$genericType = false;
         var4 = Result.constructor-impl(ResultKt.createFailure(var9));
      }

      return var4;
   }

   // $FF: synthetic method
   public static final <T> Object fromJsonArray(Gson $this$fromJsonArray, InputStream inputStream) {
      Intrinsics.checkNotNullParameter($this$fromJsonArray, "<this>");
      int $i$f$fromJsonArray = false;
      boolean var3 = false;

      Object var4;
      try {
         Companion var9 = Result.Companion;
         int var5 = false;
         InputStreamReader reader = new InputStreamReader(inputStream);
         Reader var10001 = (Reader)reader;
         Intrinsics.reifiedOperationMarker(4, "T");
         Object var13 = $this$fromJsonArray.fromJson(var10001, (Type)(new ParameterizedTypeImpl((Class)Object.class)));
         List var10 = var13 instanceof List ? (List)var13 : null;
         boolean var12 = false;
         var4 = Result.constructor-impl(var10);
      } catch (Throwable var8) {
         Companion var6 = Result.Companion;
         boolean var7 = false;
         var4 = Result.constructor-impl(ResultKt.createFailure(var8));
      }

      return var4;
   }

   public static final void writeToOutputStream(@NotNull Gson $this$writeToOutputStream, @NotNull OutputStream out, @NotNull Object any) {
      Intrinsics.checkNotNullParameter($this$writeToOutputStream, "<this>");
      Intrinsics.checkNotNullParameter(out, "out");
      Intrinsics.checkNotNullParameter(any, "any");
      JsonWriter writer = new JsonWriter((Writer)(new OutputStreamWriter(out, "UTF-8")));
      writer.setIndent("  ");
      if (any instanceof List) {
         writer.beginArray();
         Iterable $this$forEach$iv = (Iterable)any;
         int $i$f$forEach = false;
         Iterator var6 = $this$forEach$iv.iterator();

         while(var6.hasNext()) {
            Object element$iv = var6.next();
            int var9 = false;
            if (element$iv != null) {
               boolean var12 = false;
               boolean var13 = false;
               int var15 = false;
               $this$writeToOutputStream.toJson(element$iv, (Type)element$iv.getClass(), writer);
            }
         }

         writer.endArray();
      } else {
         $this$writeToOutputStream.toJson(any, (Type)any.getClass(), writer);
      }

      writer.close();
   }

   static {
      GSON$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }
}
