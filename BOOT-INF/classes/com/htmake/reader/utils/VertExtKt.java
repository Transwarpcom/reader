package com.htmake.reader.utils;

import com.htmake.reader.entity.BasicError;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;
import java.net.URLDecoder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.MDC;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u000b¨\u0006\f"},
   d2 = {"error", "", "Lio/vertx/ext/web/RoutingContext;", "throwable", "", "globalHandler", "Lio/vertx/ext/web/Route;", "handler", "Lio/vertx/core/Handler;", "success", "any", "", "reader-pro"}
)
public final class VertExtKt {
   public static final void success(@NotNull RoutingContext $this$success, @Nullable Object any) {
      Intrinsics.checkNotNullParameter($this$success, "<this>");
      String var10000;
      if (any instanceof JsonObject) {
         var10000 = ((JsonObject)any).toString();
      } else {
         String var3 = ExtKt.getGson().toJson(any);
         Intrinsics.checkNotNullExpressionValue(var3, "{\n        gson.toJson(any)\n    }");
         var10000 = var3;
      }

      String toJson = var10000;
      $this$success.response().putHeader("content-type", "application/json; charset=utf-8").end(toJson);
   }

   public static final void error(@NotNull RoutingContext $this$error, @NotNull Throwable throwable) {
      Intrinsics.checkNotNullParameter($this$error, "<this>");
      Intrinsics.checkNotNullParameter(throwable, "throwable");
      String path = URLDecoder.decode($this$error.request().absoluteURI(), "UTF-8");
      String var10003 = throwable.toString();
      String var10004 = String.valueOf(throwable.getMessage());
      Intrinsics.checkNotNullExpressionValue(path, "path");
      BasicError basicError = new BasicError("Internal Server Error", var10003, var10004, path, 500, System.currentTimeMillis());
      final String errorJson = ExtKt.getGson().toJson(basicError);
      ExtKt.getLogger().error("Internal Server Error", throwable);
      ExtKt.getLogger().error((Function0)(new Function0<Object>() {
         @Nullable
         public final Object invoke() {
            return errorJson;
         }
      }));
      $this$error.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(500).end(errorJson);
   }

   public static final void globalHandler(@NotNull Route $this$globalHandler, @NotNull Handler<RoutingContext> handler) {
      Intrinsics.checkNotNullParameter($this$globalHandler, "<this>");
      Intrinsics.checkNotNullParameter(handler, "handler");
      $this$globalHandler.handler(VertExtKt::globalHandler$lambda-0);
   }

   private static final void globalHandler$lambda_0/* $FF was: globalHandler$lambda-0*/(Handler $handler, RoutingContext ctx) {
      Intrinsics.checkNotNullParameter($handler, "$handler");
      String traceId = (String)ctx.get("traceId");
      CharSequence var3 = (CharSequence)traceId;
      boolean var4 = false;
      boolean var5 = false;
      if (var3 == null || var3.length() == 0) {
         traceId = ExtKt.getTraceId();
      }

      MDC.put("traceId", traceId);
      ctx.put("traceId", traceId);
      $handler.handle(ctx);
   }
}
