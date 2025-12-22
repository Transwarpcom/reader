package com.htmake.reader.verticle;

import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.VertExtKt;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.kotlin.coroutines.CoroutineVerticle;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.slf4j.MDCContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H&J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u0015\u001a\u00060\u001aj\u0002`\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\u0011\u0010\u001d\u001a\u00020\u0012H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u0012H\u0016J6\u0010 \u001a\u00020\u0012*\u00020!2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0012\u0004\u0018\u00010%0#ø\u0001\u0000¢\u0006\u0002\u0010&J6\u0010'\u001a\u00020\u0012*\u00020!2\"\u0010\"\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0012\u0004\u0018\u00010%0#ø\u0001\u0000¢\u0006\u0002\u0010&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006("},
   d2 = {"Lcom/htmake/reader/verticle/RestVerticle;", "Lio/vertx/kotlin/coroutines/CoroutineVerticle;", "()V", "port", "", "getPort", "()I", "setPort", "(I)V", "router", "Lio/vertx/ext/web/Router;", "getRouter", "()Lio/vertx/ext/web/Router;", "setRouter", "(Lio/vertx/ext/web/Router;)V", "getContextPath", "", "initRouter", "", "(Lio/vertx/ext/web/Router;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onException", "error", "", "onHandlerError", "ctx", "Lio/vertx/ext/web/RoutingContext;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onStartError", "start", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "started", "coroutineHandler", "Lio/vertx/ext/web/Route;", "fn", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lio/vertx/ext/web/Route;Lkotlin/jvm/functions/Function2;)V", "coroutineHandlerWithoutRes", "reader-pro"}
)
public abstract class RestVerticle extends CoroutineVerticle {
   protected Router router;
   private int port = 8080;

   @NotNull
   protected final Router getRouter() {
      Router var1 = this.router;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("router");
         throw null;
      }
   }

   protected final void setRouter(@NotNull Router <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.router = var1;
   }

   public int getPort() {
      return this.port;
   }

   public void setPort(int <set-?>) {
      this.port = var1;
   }

   @Nullable
   protected Object start(@NotNull Continuation<? super Unit> $completion) {
      return start$suspendImpl(this, $completion);
   }

   // $FF: synthetic method
   static Object start$suspendImpl(final RestVerticle var0, Continuation var1) {
      Object $continuation;
      label41: {
         if (var1 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var1;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label41;
            }
         }

         $continuation = new ContinuationImpl(var1) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return RestVerticle.start$suspendImpl(var0, (Continuation)this);
            }
         };
      }

      label35: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            ((<undefinedtype>)$continuation).L$0 = var0;
            ((<undefinedtype>)$continuation).label = 1;
            if (var0.start((Continuation)$continuation) == var9) {
               return var9;
            }
            break;
         case 1:
            var0 = (RestVerticle)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            break;
         case 2:
            var0 = (RestVerticle)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            break label35;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         Router var2 = Router.router(var0.getVertx());
         Intrinsics.checkNotNullExpressionValue(var2, "router(vertx)");
         var0.setRouter(var2);
         String cookieName = "reader.session";
         Route var3 = var0.getRouter().route();
         Intrinsics.checkNotNullExpressionValue(var3, "router.route()");
         Route var10000 = var3;
         SessionHandler var11 = SessionHandler.create((SessionStore)LocalSessionStore.create(var0.getVertx())).setSessionCookieName(cookieName).setSessionTimeout(604800000L).setSessionCookiePath("/");
         Intrinsics.checkNotNullExpressionValue(var11, "create(LocalSessionStore.create(vertx))\n                            .setSessionCookieName(cookieName)\n                            .setSessionTimeout(7L * 86400 * 1000)\n                            .setSessionCookiePath(\"/\")");
         VertExtKt.globalHandler(var10000, (Handler)var11);
         var3 = var0.getRouter().route();
         Intrinsics.checkNotNullExpressionValue(var3, "router.route()");
         VertExtKt.globalHandler(var3, RestVerticle::start$lambda-1);
         var3 = var0.getRouter().route();
         Intrinsics.checkNotNullExpressionValue(var3, "router.route()");
         VertExtKt.globalHandler(var3, RestVerticle::start$lambda-3);
         var3 = var0.getRouter().route();
         Intrinsics.checkNotNullExpressionValue(var3, "router.route()");
         var10000 = var3;
         BodyHandler var12 = BodyHandler.create();
         Intrinsics.checkNotNullExpressionValue(var12, "create()");
         VertExtKt.globalHandler(var10000, (Handler)var12);
         var3 = var0.getRouter().route();
         Intrinsics.checkNotNullExpressionValue(var3, "router.route()");
         var10000 = var3;
         LoggerHandler var13 = LoggerHandler.create(LoggerFormat.DEFAULT);
         Intrinsics.checkNotNullExpressionValue(var13, "create(LoggerFormat.DEFAULT)");
         VertExtKt.globalHandler(var10000, (Handler)var13);
         var3 = var0.getRouter().route("/reader3/*");
         Intrinsics.checkNotNullExpressionValue(var3, "router.route(\"/reader3/*\")");
         VertExtKt.globalHandler(var3, RestVerticle::start$lambda-4);
         var3 = var0.getRouter().get("/health");
         Intrinsics.checkNotNullExpressionValue(var3, "router.get(\"/health\")");
         VertExtKt.globalHandler(var3, RestVerticle::start$lambda-5);
         Router var10001 = var0.getRouter();
         ((<undefinedtype>)$continuation).L$0 = var0;
         ((<undefinedtype>)$continuation).label = 2;
         if (var0.initRouter(var10001, (Continuation)$continuation) == var9) {
            return var9;
         }
      }

      var0.getRouter().route().last().failureHandler(RestVerticle::start$lambda-6);
      String contextPath = var0.getContextPath();
      Router mainRouter = null;
      CharSequence var5 = (CharSequence)contextPath;
      boolean var6 = false;
      if (var5.length() > 0) {
         Router var14 = Router.router(var0.getVertx());
         Intrinsics.checkNotNullExpressionValue(var14, "router(vertx)");
         mainRouter = var14;
         var14.mountSubRouter(ExtKt.toDir(contextPath, true), var0.getRouter());
      } else {
         mainRouter = var0.getRouter();
      }

      RestVerticleKt.access$getLogger$p().info("port: {}", Boxing.boxInt(var0.getPort()));
      var0.getVertx().createHttpServer().requestHandler((Handler)mainRouter).exceptionHandler(RestVerticle::start$lambda-7).listen(var0.getPort(), RestVerticle::start$lambda-8);
      return Unit.INSTANCE;
   }

   @Nullable
   public abstract Object initRouter(@NotNull Router router, @NotNull Continuation<? super Unit> $completion);

   @NotNull
   public abstract String getContextPath();

   public void onException(@NotNull Throwable error) {
      Intrinsics.checkNotNullParameter(error, "error");
      RestVerticleKt.access$getLogger$p().error("vertx exception: {}", error);
   }

   public void onStartError() {
   }

   public void started() {
   }

   public void onHandlerError(@NotNull RoutingContext ctx, @NotNull Exception error) {
      Intrinsics.checkNotNullParameter(ctx, "ctx");
      Intrinsics.checkNotNullParameter(error, "error");
      RestVerticleKt.access$getLogger$p().error("Error: {}", (Throwable)error);
      VertExtKt.error(ctx, (Throwable)error);
   }

   public final void coroutineHandler(@NotNull Route $this$coroutineHandler, @NotNull Function2<? super RoutingContext, ? super Continuation<Object>, ? extends Object> fn) {
      Intrinsics.checkNotNullParameter($this$coroutineHandler, "<this>");
      Intrinsics.checkNotNullParameter(fn, "fn");
      VertExtKt.globalHandler($this$coroutineHandler, RestVerticle::coroutineHandler$lambda-10);
   }

   public final void coroutineHandlerWithoutRes(@NotNull Route $this$coroutineHandlerWithoutRes, @NotNull Function2<? super RoutingContext, ? super Continuation<Object>, ? extends Object> fn) {
      Intrinsics.checkNotNullParameter($this$coroutineHandlerWithoutRes, "<this>");
      Intrinsics.checkNotNullParameter(fn, "fn");
      VertExtKt.globalHandler($this$coroutineHandlerWithoutRes, RestVerticle::coroutineHandlerWithoutRes$lambda-12);
   }

   private static final void start$lambda_1$lambda_0/* $FF was: start$lambda-1$lambda-0*/(RoutingContext $it, String $cookieName, Void $noName_0) {
      Intrinsics.checkNotNullParameter($cookieName, "$cookieName");
      Cookie cookie = $it.getCookie($cookieName);
      if (cookie != null) {
         cookie.setMaxAge(172800000L);
         cookie.setPath("/");
      }

   }

   private static final void start$lambda_1/* $FF was: start$lambda-1*/(String $cookieName, RoutingContext it) {
      Intrinsics.checkNotNullParameter($cookieName, "$cookieName");
      it.addHeadersEndHandler(RestVerticle::start$lambda-1$lambda-0);
      it.next();
   }

   private static final void start$lambda_3$lambda_2/* $FF was: start$lambda-3$lambda-2*/(RoutingContext $it, Void $noName_0) {
      String origin = $it.request().getHeader("Origin");
      if (origin != null) {
         CharSequence var3 = (CharSequence)origin;
         boolean var4 = false;
         if (var3.length() > 0) {
            HttpServerResponse res = $it.response();
            res.putHeader("Access-Control-Allow-Origin", origin);
            res.putHeader("Access-Control-Allow-Credentials", "true");
            res.putHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE");
            res.putHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, If-Match, If-Modified-Since, If-None-Match, If-Unmodified-Since, X-Requested-With");
         }
      }

   }

   private static final void start$lambda_3/* $FF was: start$lambda-3*/(String $cookieName, RoutingContext it) {
      Intrinsics.checkNotNullParameter($cookieName, "$cookieName");
      it.addHeadersEndHandler(RestVerticle::start$lambda-3$lambda-2);
      String origin = it.request().getHeader("Origin");
      if (origin != null) {
         CharSequence var3 = (CharSequence)origin;
         boolean var4 = false;
         if (var3.length() > 0 && it.request().method() == HttpMethod.OPTIONS) {
            it.removeCookie($cookieName);
            Intrinsics.checkNotNullExpressionValue(it, "it");
            VertExtKt.success(it, "");
            return;
         }
      }

      it.next();
   }

   private static final void start$lambda_4/* $FF was: start$lambda-4*/(RoutingContext it) {
      String rawMethod = it.request().rawMethod();
      RestVerticleKt.access$getLogger$p().info("{} {}", rawMethod, URLDecoder.decode(it.request().absoluteURI(), "UTF-8"));
      if (!rawMethod.equals("PUT") && (it.fileUploads() == null || it.fileUploads().isEmpty()) && it.getBodyAsString() != null && it.getBodyAsString().length() > 0 && it.getBodyAsString().length() < 1000) {
         RestVerticleKt.access$getLogger$p().info("Request body: {}", it.getBodyAsString());
      }

      it.next();
   }

   private static final void start$lambda_5/* $FF was: start$lambda-5*/(RoutingContext it) {
      Intrinsics.checkNotNullExpressionValue(it, "it");
      VertExtKt.success(it, "ok!");
   }

   private static final void start$lambda_6/* $FF was: start$lambda-6*/(RoutingContext ctx) {
      Intrinsics.checkNotNullExpressionValue(ctx, "ctx");
      Throwable var1 = ctx.failure();
      Intrinsics.checkNotNullExpressionValue(var1, "ctx.failure()");
      VertExtKt.error(ctx, var1);
   }

   private static final void start$lambda_7/* $FF was: start$lambda-7*/(RestVerticle this$0, Throwable error) {
      Intrinsics.checkNotNullParameter(this$0, "this$0");
      Intrinsics.checkNotNullExpressionValue(error, "error");
      this$0.onException(error);
   }

   private static final void start$lambda_8/* $FF was: start$lambda-8*/(RestVerticle this$0, AsyncResult res) {
      Intrinsics.checkNotNullParameter(this$0, "this$0");
      if (res.succeeded()) {
         RestVerticleKt.access$getLogger$p().info("Server running at: http://localhost:{}", this$0.getPort());
         RestVerticleKt.access$getLogger$p().info("Web reader running at: http://localhost:{}", this$0.getPort());
         String var2 = "ReaderApplication Started";
         boolean var3 = false;
         System.out.println(var2);
         this$0.started();
      } else {
         this$0.onStartError();
      }

   }

   private static final void coroutineHandler$lambda_10$lambda_9/* $FF was: coroutineHandler$lambda-10$lambda-9*/(ObjectRef $job, Void it) {
      Intrinsics.checkNotNullParameter($job, "$job");
      RestVerticleKt.access$getLogger$p().info("客户端已断开链接，终止运行");
      Job var2 = (Job)$job.element;
      if (var2 != null) {
         DefaultImpls.cancel$default(var2, (CancellationException)null, 1, (Object)null);
      }

   }

   private static final void coroutineHandler$lambda_10/* $FF was: coroutineHandler$lambda-10*/(RestVerticle this$0, Function2 $fn, RoutingContext ctx) {
      Intrinsics.checkNotNullParameter(this$0, "this$0");
      Intrinsics.checkNotNullParameter($fn, "$fn");
      ObjectRef job = new ObjectRef();
      ctx.request().connection().closeHandler(RestVerticle::coroutineHandler$lambda-10$lambda-9);
      job.element = BuildersKt.launch$default((CoroutineScope)this$0, (new MDCContext((Map)null, 1, (DefaultConstructorMarker)null)).plus((CoroutineContext)Dispatchers.getIO()), (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
         Object L$0;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            // $FF: Couldn't be decompiled
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 2, (Object)null);
   }

   private static final void coroutineHandlerWithoutRes$lambda_12$lambda_11/* $FF was: coroutineHandlerWithoutRes$lambda-12$lambda-11*/(ObjectRef $job, Void it) {
      Intrinsics.checkNotNullParameter($job, "$job");
      RestVerticleKt.access$getLogger$p().info("客户端已断开链接，终止运行");
      Job var2 = (Job)$job.element;
      if (var2 != null) {
         DefaultImpls.cancel$default(var2, (CancellationException)null, 1, (Object)null);
      }

   }

   private static final void coroutineHandlerWithoutRes$lambda_12/* $FF was: coroutineHandlerWithoutRes$lambda-12*/(RestVerticle this$0, Function2 $fn, RoutingContext ctx) {
      Intrinsics.checkNotNullParameter(this$0, "this$0");
      Intrinsics.checkNotNullParameter($fn, "$fn");
      ObjectRef job = new ObjectRef();
      ctx.request().connection().closeHandler(RestVerticle::coroutineHandlerWithoutRes$lambda-12$lambda-11);
      job.element = BuildersKt.launch$default((CoroutineScope)this$0, (new MDCContext((Map)null, 1, (DefaultConstructorMarker)null)).plus((CoroutineContext)Dispatchers.getIO()), (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            // $FF: Couldn't be decompiled
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 2, (Object)null);
   }
}
