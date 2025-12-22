package com.htmake.reader.api.controller;

import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.User;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.VertExtKt;
import io.legado.app.data.entities.Book;
import io.legado.app.exception.TocEmptyException;
import io.legado.app.model.localBook.LocalBook;
import io.legado.app.utils.FileUtils;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KLogger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J/\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"},
   d2 = {"Lcom/htmake/reader/api/controller/FileController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "checkAccess", "Lcom/htmake/reader/api/ReturnData;", "context", "Lio/vertx/ext/web/RoutingContext;", "isSave", "", "isDelete", "(Lio/vertx/ext/web/RoutingContext;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMulti", "download", "", "get", "importPreview", "list", "mkdir", "parse", "restore", "save", "upload", "reader-pro"}
)
public final class FileController extends BaseController {
   public FileController(@NotNull CoroutineContext coroutineContext) {
      Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
      super(coroutineContext);
   }

   @Nullable
   public final Object checkAccess(@NotNull RoutingContext context, boolean isSave, boolean isDelete, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label114: {
         if (var4 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var4;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label114;
            }
         }

         $continuation = new ContinuationImpl(var4) {
            Object L$0;
            Object L$1;
            Object L$2;
            boolean Z$0;
            boolean Z$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.checkAccess((RoutingContext)null, false, false, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      ReturnData returnData;
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).L$2 = returnData;
         ((<undefinedtype>)$continuation).Z$0 = isSave;
         ((<undefinedtype>)$continuation).Z$1 = isDelete;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAuth(context, (Continuation)$continuation);
         if (var10000 == var12) {
            return var12;
         }
         break;
      case 1:
         isDelete = ((<undefinedtype>)$continuation).Z$1;
         isSave = ((<undefinedtype>)$continuation).Z$0;
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$2;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (FileController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!(Boolean)var10000) {
         return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
      } else {
         context.put("__FILE_HOME__", (Object)null);
         String home = null;
         String var7;
         if (context.request().method() == HttpMethod.POST) {
            if (context.fileUploads() != null && !context.fileUploads().isEmpty()) {
               var7 = context.request().getParam("home");
               home = var7 == null ? "" : var7;
            } else {
               var7 = context.getBodyAsJson().getString("home");
               home = var7 == null ? "" : var7;
            }
         } else {
            List var8 = context.queryParam("home");
            Intrinsics.checkNotNullExpressionValue(var8, "context.queryParam(\"home\")");
            var7 = (String)CollectionsKt.firstOrNull(var8);
            home = var7 == null ? "" : var7;
         }

         User userInfo;
         switch(home.hashCode()) {
         case -1571867763:
            if (!home.equals("__LOCAL_STORE__")) {
               return returnData.setErrorMsg("非法访问");
            }

            if (this.getAppConfig().getSecure()) {
               userInfo = (User)context.get("userInfo");
               if (userInfo == null) {
                  return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
               }

               if (!userInfo.getEnable_local_store()) {
                  return returnData.setErrorMsg("未开启本地书仓功能");
               }
            }

            if ((isSave || isDelete) && !this.checkManagerAuth(context)) {
               return ReturnData.setData$default(returnData, "NEED_SECURE_KEY", (String)null, 2, (Object)null).setErrorMsg("请输入管理密码");
            }

            String[] var15 = new String[]{"storage", "localStore"};
            context.put("__FILE_HOME__", ExtKt.toDir$default(ExtKt.getWorkDir(var15), false, 1, (Object)null));
            break;
         case -1386618657:
            if (!home.equals("__HOME__")) {
               return returnData.setErrorMsg("非法访问");
            }

            String userNameSpace = this.getUserNameSpace(context);
            String[] var9 = new String[]{"storage", "data", userNameSpace};
            context.put("__FILE_HOME__", ExtKt.toDir$default(ExtKt.getWorkDir(var9), false, 1, (Object)null));
            break;
         case -1330162107:
            if (!home.equals("__WEBDAV__")) {
               return returnData.setErrorMsg("非法访问");
            }

            if (this.getAppConfig().getSecure()) {
               userInfo = (User)context.get("userInfo");
               if (userInfo == null) {
                  return ReturnData.setData$default(returnData, "NEED_LOGIN", (String)null, 2, (Object)null).setErrorMsg("请登录后使用");
               }

               if (!userInfo.getEnable_webdav()) {
                  return returnData.setErrorMsg("未开启webdav功能");
               }
            }

            context.put("__FILE_HOME__", ExtKt.toDir$default(this.getUserWebdavHome(context), false, 1, (Object)null));
            break;
         case -220135525:
            if (home.equals("__STORAGE__")) {
               if (!this.checkManagerAuth(context)) {
                  return ReturnData.setData$default(returnData, "NEED_SECURE_KEY", (String)null, 2, (Object)null).setErrorMsg("请输入管理密码");
               }

               context.put("__FILE_HOME__", ExtKt.toDir$default(ExtKt.getWorkDir("storage"), false, 1, (Object)null));
               break;
            }

            return returnData.setErrorMsg("非法访问");
         default:
            return returnData.setErrorMsg("非法访问");
         }

         FileControllerKt.access$getLogger$p().info("context.__FILE_HOME__ {}", context.get("__FILE_HOME__"));
         return null;
      }
   }

   // $FF: synthetic method
   public static Object checkAccess$default(FileController var0, RoutingContext var1, boolean var2, boolean var3, Continuation var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = false;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      return var0.checkAccess(var1, var2, var3, var4);
   }

   @Nullable
   public final Object list(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
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
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.list((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var21 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, false, false, (Continuation)$continuation, 6, (Object)null);
         if (var10000 == var21) {
            return var21;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         String path = null;
         String home;
         if (context.request().method() == HttpMethod.POST) {
            home = context.getBodyAsJson().getString("path");
            path = home == null ? "" : home;
         } else {
            List var7 = context.queryParam("path");
            Intrinsics.checkNotNullExpressionValue(var7, "context.queryParam(\"path\")");
            home = (String)CollectionsKt.firstOrNull(var7);
            path = home == null ? "" : home;
         }

         CharSequence var22 = (CharSequence)path;
         boolean var23 = false;
         if (var22.length() == 0) {
            path = "/";
         }

         home = null;
         Object home = context.get("__FILE_HOME__");
         if (home == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            path = ExtKt.toDir(path, true);
            File file = new File(Intrinsics.stringPlus((String)home, path));
            FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
            if (!file.exists()) {
               if (!Intrinsics.areEqual(path, "/")) {
                  return returnData.setErrorMsg("路径不存在");
               }

               file.mkdirs();
            }

            if (!file.isDirectory()) {
               return returnData.setErrorMsg("路径不是目录");
            } else {
               Object fileList = null;
               boolean var9 = false;
               fileList = new ArrayList();
               File[] var26 = file.listFiles();
               Intrinsics.checkNotNullExpressionValue(var26, "file.listFiles()");
               Object[] $this$forEach$iv = (Object[])var26;
               int $i$f$forEach = false;
               Object[] var11 = $this$forEach$iv;
               int var12 = $this$forEach$iv.length;

               for(int var13 = 0; var13 < var12; ++var13) {
                  Object element$iv = var11[var13];
                  File it = (File)element$iv;
                  int var16 = false;
                  String var17 = it.getName();
                  Intrinsics.checkNotNullExpressionValue(var17, "it.name");
                  if (!StringsKt.startsWith$default(var17, ".", false, 2, (Object)null)) {
                     Pair[] var28 = new Pair[]{TuplesKt.to("name", it.getName()), TuplesKt.to("size", Boxing.boxLong(it.length())), null, null, null};
                     String var18 = it.toString();
                     Intrinsics.checkNotNullExpressionValue(var18, "it.toString()");
                     var28[2] = TuplesKt.to("path", StringsKt.replace$default(var18, (String)home, "", false, 4, (Object)null));
                     var28[3] = TuplesKt.to("lastModified", Boxing.boxLong(it.lastModified()));
                     var28[4] = TuplesKt.to("isDirectory", Boxing.boxBoolean(it.isDirectory()));
                     fileList.add(MapsKt.mapOf(var28));
                  }
               }

               return ReturnData.setData$default(returnData, fileList, (String)null, 2, (Object)null);
            }
         }
      }
   }

   @Nullable
   public final Object upload(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label78: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label78;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.upload((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var21 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      ReturnData returnData;
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         returnData = new ReturnData();
         if (context.fileUploads() == null || context.fileUploads().isEmpty()) {
            return returnData.setErrorMsg("请上传文件");
         }

         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).L$1 = returnData;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, true, false, (Continuation)$continuation, 4, (Object)null);
         if (var10000 == var21) {
            return var21;
         }
         break;
      case 1:
         returnData = (ReturnData)((<undefinedtype>)$continuation).L$1;
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         Object path = null;
         path = context.request().getParam("path");
         CharSequence fileList = (CharSequence)path;
         boolean var7 = false;
         boolean var8 = false;
         if (fileList == null || fileList.length() == 0) {
            path = "/";
         }

         Intrinsics.checkNotNullExpressionValue(path, "path");
         path = ExtKt.toDir(path, true);
         fileList = null;
         var7 = false;
         Object fileList = new ArrayList();
         Object home = null;
         home = context.get("__FILE_HOME__");
         if (home == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            Set var24 = context.fileUploads();
            Intrinsics.checkNotNullExpressionValue(var24, "context.fileUploads()");
            Iterable $this$forEach$iv = (Iterable)var24;
            int $i$f$forEach = false;
            Iterator var10 = $this$forEach$iv.iterator();

            while(var10.hasNext()) {
               Object element$iv = var10.next();
               FileUpload it = (FileUpload)element$iv;
               int var13 = false;
               File file = new File(it.uploadedFileName());
               KLogger var27 = FileControllerKt.access$getLogger$p();
               Object[] var15 = new Object[]{it.uploadedFileName(), it.fileName(), file};
               var27.info("uploadFile: {} {} {}", var15);
               if (file.exists()) {
                  String fileName = it.fileName();
                  File newFile = new File(home + path + File.separator + fileName);
                  if (!newFile.getParentFile().exists()) {
                     newFile.getParentFile().mkdirs();
                  }

                  if (newFile.exists()) {
                     newFile.delete();
                  }

                  FileControllerKt.access$getLogger$p().info("moveTo: {}", newFile);
                  if (FilesKt.copyRecursively$default(file, newFile, false, (Function2)null, 6, (Object)null)) {
                     Pair[] var17 = new Pair[]{TuplesKt.to("name", newFile.getName()), TuplesKt.to("size", Boxing.boxLong(newFile.length())), null, null, null};
                     String var18 = newFile.toString();
                     Intrinsics.checkNotNullExpressionValue(var18, "newFile.toString()");
                     var17[2] = TuplesKt.to("path", StringsKt.replace$default(var18, (String)home, "", false, 4, (Object)null));
                     var17[3] = TuplesKt.to("lastModified", Boxing.boxLong(newFile.lastModified()));
                     var17[4] = TuplesKt.to("isDirectory", Boxing.boxBoolean(newFile.isDirectory()));
                     fileList.add(MapsKt.mapOf(var17));
                  }

                  FilesKt.deleteRecursively(file);
               }
            }

            return ReturnData.setData$default(returnData, fileList, (String)null, 2, (Object)null);
         }
      }
   }

   @Nullable
   public final Object download(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) {
      Object $continuation;
      label68: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label68;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.download((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, false, false, (Continuation)$continuation, 6, (Object)null);
         if (var10000 == var13) {
            return var13;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         VertExtKt.success(context, checkResult);
         return Unit.INSTANCE;
      } else {
         ReturnData returnData = new ReturnData();
         String path = null;
         boolean var6 = false;
         String home;
         int stream;
         if (context.request().method() == HttpMethod.POST) {
            home = context.getBodyAsJson().getString("path");
            path = home == null ? "" : home;
            Integer var15 = context.getBodyAsJson().getInteger("stream", Boxing.boxInt(0));
            Intrinsics.checkNotNullExpressionValue(var15, "context.bodyAsJson.getInteger(\"stream\", 0)");
            stream = ((Number)var15).intValue();
         } else {
            List var8 = context.queryParam("path");
            Intrinsics.checkNotNullExpressionValue(var8, "context.queryParam(\"path\")");
            home = (String)CollectionsKt.firstOrNull(var8);
            path = home == null ? "" : home;
            var8 = context.queryParam("stream");
            Intrinsics.checkNotNullExpressionValue(var8, "context.queryParam(\"stream\")");
            home = (String)CollectionsKt.firstOrNull(var8);
            int var20;
            if (home == null) {
               var20 = 0;
            } else {
               boolean var10 = false;
               Integer var17 = Boxing.boxInt(Integer.parseInt(home));
               var20 = var17 == null ? 0 : var17;
            }

            stream = var20;
         }

         CharSequence var16 = (CharSequence)path;
         boolean var18 = false;
         if (var16.length() == 0) {
            VertExtKt.success(context, returnData.setErrorMsg("参数错误"));
            return Unit.INSTANCE;
         } else {
            home = (String)context.get("__FILE_HOME__");
            if (home == null) {
               VertExtKt.success(context, returnData.setErrorMsg("参数错误"));
               return Unit.INSTANCE;
            } else {
               path = ExtKt.toDir(path, true);
               File file = new File(Intrinsics.stringPlus(home, path));
               FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
               if (!file.exists()) {
                  VertExtKt.success(context, returnData.setErrorMsg("路径不存在"));
                  return Unit.INSTANCE;
               } else {
                  HttpServerResponse response = context.response().putHeader("Cache-Control", "86400");
                  if (stream <= 0) {
                     response.putHeader("Content-Disposition", Intrinsics.stringPlus("attachment; filename=", URLEncoder.encode(file.getName(), "UTF-8")));
                  }

                  response.sendFile(file.toString());
                  return Unit.INSTANCE;
               }
            }
         }
      }
   }

   @Nullable
   public final Object get(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label56: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label56;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.get((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, false, false, (Continuation)$continuation, 6, (Object)null);
         if (var10000 == var10) {
            return var10;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         String path = null;
         String home;
         if (context.request().method() == HttpMethod.POST) {
            home = context.getBodyAsJson().getString("path");
            path = home == null ? "" : home;
         } else {
            List var7 = context.queryParam("path");
            Intrinsics.checkNotNullExpressionValue(var7, "context.queryParam(\"path\")");
            home = (String)CollectionsKt.firstOrNull(var7);
            path = home == null ? "" : home;
         }

         CharSequence var11 = (CharSequence)path;
         boolean var12 = false;
         if (var11.length() == 0) {
            return returnData.setErrorMsg("参数错误");
         } else {
            home = (String)context.get("__FILE_HOME__");
            if (home == null) {
               return returnData.setErrorMsg("参数错误");
            } else {
               path = ExtKt.toDir(path, true);
               File file = new File(Intrinsics.stringPlus(home, path));
               FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
               return !file.exists() ? returnData.setErrorMsg("路径不存在") : ReturnData.setData$default(returnData, FilesKt.readText$default(file, (Charset)null, 1, (Object)null), (String)null, 2, (Object)null);
            }
         }
      }
   }

   @Nullable
   public final Object save(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label47: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label47;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.save((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, true, false, (Continuation)$continuation, 4, (Object)null);
         if (var10000 == var11) {
            return var11;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         String content = context.getBodyAsJson().getString("path", "");
         String path = content == null ? "" : content;
         String home = context.getBodyAsJson().getString("content", "");
         content = home == null ? "" : home;
         CharSequence var12 = (CharSequence)path;
         boolean var8 = false;
         if (var12.length() == 0) {
            return returnData.setErrorMsg("参数错误");
         } else {
            home = (String)context.get("__FILE_HOME__");
            if (home == null) {
               return returnData.setErrorMsg("参数错误");
            } else {
               path = ExtKt.toDir(path, true);
               File file = FileUtils.INSTANCE.createFileWithReplace(Intrinsics.stringPlus(home, path));
               FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
               FilesKt.writeText$default(file, content, (Charset)null, 2, (Object)null);
               return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
            }
         }
      }
   }

   @Nullable
   public final Object mkdir(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label62: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label62;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.mkdir((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, true, false, (Continuation)$continuation, 4, (Object)null);
         if (var10000 == var11) {
            return var11;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         String name = context.getBodyAsJson().getString("path", "");
         String path = name == null ? "" : name;
         CharSequence var12 = (CharSequence)path;
         boolean var7 = false;
         if (var12.length() == 0) {
            return returnData.setErrorMsg("参数错误");
         } else {
            String home = context.getBodyAsJson().getString("name", "");
            name = home == null ? "" : home;
            CharSequence var14 = (CharSequence)name;
            boolean var8 = false;
            if (var14.length() != 0 && !StringsKt.startsWith$default(name, ".", false, 2, (Object)null)) {
               home = (String)context.get("__FILE_HOME__");
               if (home == null) {
                  return returnData.setErrorMsg("参数错误");
               } else {
                  path = ExtKt.toDir(path, true);
                  File file = new File(home + path + File.separator + name);
                  FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
                  if (file.exists()) {
                     return returnData.setErrorMsg("路径已存在");
                  } else {
                     file.mkdirs();
                     return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
                  }
               }
            } else {
               return returnData.setErrorMsg("参数错误");
            }
         }
      }
   }

   @Nullable
   public final Object delete(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label56: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label56;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.delete((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAccess(context, false, true, (Continuation)$continuation);
         if (var10000 == var10) {
            return var10;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         String path = null;
         String home;
         if (context.request().method() == HttpMethod.POST) {
            home = context.getBodyAsJson().getString("path");
            path = home == null ? "" : home;
         } else {
            List var7 = context.queryParam("path");
            Intrinsics.checkNotNullExpressionValue(var7, "context.queryParam(\"path\")");
            home = (String)CollectionsKt.firstOrNull(var7);
            path = home == null ? "" : home;
         }

         CharSequence var11 = (CharSequence)path;
         boolean var12 = false;
         if (var11.length() == 0) {
            return returnData.setErrorMsg("参数错误");
         } else {
            home = (String)context.get("__FILE_HOME__");
            if (home == null) {
               return returnData.setErrorMsg("参数错误");
            } else {
               path = ExtKt.toDir(path, true);
               File file = new File(Intrinsics.stringPlus(home, path));
               FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
               if (!file.exists()) {
                  return returnData.setErrorMsg("路径不存在");
               } else {
                  FilesKt.deleteRecursively(file);
                  return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
               }
            }
         }
      }
   }

   @Nullable
   public final Object deleteMulti(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label53: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label53;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.deleteMulti((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var18 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = this.checkAccess(context, false, true, (Continuation)$continuation);
         if (var10000 == var18) {
            return var18;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         JsonArray path = context.getBodyAsJson().getJsonArray("path");
         if (path == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            Object home = null;
            home = context.get("__FILE_HOME__");
            if (home == null) {
               return returnData.setErrorMsg("参数错误");
            } else {
               Iterable $this$forEach$iv = (Iterable)path;
               int $i$f$forEach = false;
               Iterator var9 = $this$forEach$iv.iterator();

               while(var9.hasNext()) {
                  Object element$iv = var9.next();
                  int var12 = false;
                  String var13 = (String)element$iv;
                  String filePath = var13 == null ? "" : var13;
                  CharSequence var19 = (CharSequence)filePath;
                  boolean var15 = false;
                  if (var19.length() > 0) {
                     File file = new File(Intrinsics.stringPlus((String)home, ExtKt.toDir(filePath, true)));
                     FilesKt.deleteRecursively(file);
                  }
               }

               return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
            }
         }
      }
   }

   @Nullable
   public final Object importPreview(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label84: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label84;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.importPreview((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var28 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, false, false, (Continuation)$continuation, 6, (Object)null);
         if (var10000 == var28) {
            return var28;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (FileController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         JsonArray paths = context.getBodyAsJson().getJsonArray("path");
         if (paths == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            Object home = null;
            home = context.get("__FILE_HOME__");
            if (home == null) {
               return returnData.setErrorMsg("参数错误");
            } else {
               Object fileList = null;
               boolean var8 = false;
               fileList = new ArrayList();
               Object userNameSpace = null;
               userNameSpace = this.getUserNameSpace(context);
               Iterable $this$forEach$iv = (Iterable)paths;
               int $i$f$forEach = false;
               Iterator var11 = $this$forEach$iv.iterator();

               while(var11.hasNext()) {
                  Object element$iv = var11.next();
                  int var14 = false;
                  String var15 = (String)element$iv;
                  String path = var15 == null ? "" : var15;
                  CharSequence var31 = (CharSequence)path;
                  boolean var17 = false;
                  if (var31.length() > 0) {
                     path = Intrinsics.stringPlus((String)home, path);
                     File file = new File(path);
                     FileControllerKt.access$getLogger$p().info("localFile: {} {}", path, file);
                     if (file.exists() && !file.isDirectory()) {
                        String fileName = file.getName();
                        BaseController var33 = (BaseController)this;
                        Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
                        String ext = BaseController.getFileExt$default(var33, fileName, (String)null, 2, (Object)null);
                        if (!Intrinsics.areEqual(ext, "txt") && !Intrinsics.areEqual(ext, "epub") && !Intrinsics.areEqual(ext, "umd") && !Intrinsics.areEqual(ext, "cbz") && !Intrinsics.areEqual(ext, "pdf")) {
                           return returnData.setErrorMsg("不支持导入" + ext + "格式的书籍文件");
                        }

                        String rootDir = ExtKt.getWorkDir$default((String)null, 1, (Object)null);
                        String relativePath = File.separator;
                        Intrinsics.checkNotNullExpressionValue(relativePath, "separator");
                        if (!StringsKt.endsWith$default(rootDir, relativePath, false, 2, (Object)null)) {
                           rootDir = Intrinsics.stringPlus(rootDir, File.separator);
                        }

                        relativePath = path;
                        FileControllerKt.access$getLogger$p().info("rootDir: {} path: {}", rootDir, path);
                        if (StringsKt.startsWith$default(path, rootDir, false, 2, (Object)null)) {
                           relativePath = StringsKt.replaceFirst$default(path, rootDir, "", false, 4, (Object)null);
                        }

                        FileControllerKt.access$getLogger$p().info("relative path: {}", relativePath);
                        String url = StringsKt.replace$default(relativePath, "\\", "/", false, 4, (Object)null);
                        Book book = Book.Companion.initLocalBook(url, relativePath, rootDir);
                        book.setUserNameSpace(userNameSpace);
                        FileControllerKt.access$getLogger$p().info("book {}", book);

                        Pair[] var24;
                        try {
                           ArrayList chapters = LocalBook.INSTANCE.getChapterList(book);
                           var24 = new Pair[]{TuplesKt.to("book", book), TuplesKt.to("chapters", chapters)};
                           fileList.add(MapsKt.mapOf(var24));
                        } catch (TocEmptyException var29) {
                           var24 = new Pair[]{TuplesKt.to("book", book), null};
                           boolean var25 = false;
                           var24[1] = TuplesKt.to("chapters", new ArrayList());
                           fileList.add(MapsKt.mapOf(var24));
                        }
                     }
                  }
               }

               return ReturnData.setData$default(returnData, fileList, (String)null, 2, (Object)null);
            }
         }
      }
   }

   @Nullable
   public final Object restore(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
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
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.restore((RoutingContext)null, (Continuation)this);
            }
         };
      }

      ReturnData returnData;
      Object var10000;
      label79: {
         Object $result = ((<undefinedtype>)$continuation).result;
         Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch(((<undefinedtype>)$continuation).label) {
         case 0:
            ResultKt.throwOnFailure($result);
            ((<undefinedtype>)$continuation).L$0 = this;
            ((<undefinedtype>)$continuation).L$1 = context;
            ((<undefinedtype>)$continuation).label = 1;
            var10000 = checkAccess$default(this, context, false, false, (Continuation)$continuation, 6, (Object)null);
            if (var10000 == var13) {
               return var13;
            }
            break;
         case 1:
            context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
            this = (FileController)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break;
         case 2:
            returnData = (ReturnData)((<undefinedtype>)$continuation).L$0;
            ResultKt.throwOnFailure($result);
            var10000 = $result;
            break label79;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         ReturnData checkResult = (ReturnData)var10000;
         if (checkResult != null) {
            return checkResult;
         }

         returnData = new ReturnData();
         String path = null;
         String ext;
         if (context.request().method() == HttpMethod.POST) {
            ext = context.getBodyAsJson().getString("path");
            path = ext == null ? "" : ext;
         } else {
            List var7 = context.queryParam("path");
            Intrinsics.checkNotNullExpressionValue(var7, "context.queryParam(\"path\")");
            ext = (String)CollectionsKt.firstOrNull(var7);
            path = ext == null ? "" : ext;
         }

         CharSequence var14 = (CharSequence)path;
         boolean var15 = false;
         if (var14.length() == 0) {
            path = "/";
         }

         ext = BaseController.getFileExt$default((BaseController)this, path, (String)null, 2, (Object)null);
         if (!Intrinsics.areEqual(ext, "zip")) {
            return returnData.setErrorMsg("路径不是zip备份文件");
         }

         String home = (String)context.get("__FILE_HOME__");
         if (home == null) {
            return returnData.setErrorMsg("参数错误");
         }

         File file = new File(Intrinsics.stringPlus(home, path));
         FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
         if (!file.exists()) {
            return returnData.setErrorMsg("路径不存在");
         }

         BookController bookController = new BookController(this.getCoroutineContext());
         String var10 = file.toString();
         Intrinsics.checkNotNullExpressionValue(var10, "file.toString()");
         String var10002 = this.getUserNameSpace(context);
         ((<undefinedtype>)$continuation).L$0 = returnData;
         ((<undefinedtype>)$continuation).L$1 = null;
         ((<undefinedtype>)$continuation).label = 2;
         var10000 = bookController.syncFromWebdav(var10, var10002, (Continuation)$continuation);
         if (var10000 == var13) {
            return var13;
         }
      }

      if (!(Boolean)var10000) {
         return returnData.setErrorMsg("恢复失败");
      } else {
         return ReturnData.setData$default(returnData, "", (String)null, 2, (Object)null);
      }
   }

   @Nullable
   public final Object parse(@NotNull RoutingContext context, @NotNull Continuation<? super ReturnData> $completion) {
      Object $continuation;
      label119: {
         if (var2 instanceof <undefinedtype>) {
            $continuation = (<undefinedtype>)var2;
            if ((((<undefinedtype>)$continuation).label & Integer.MIN_VALUE) != 0) {
               ((<undefinedtype>)$continuation).label -= Integer.MIN_VALUE;
               break label119;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return FileController.this.parse((RoutingContext)null, (Continuation)this);
            }
         };
      }

      Object $result = ((<undefinedtype>)$continuation).result;
      Object var28 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Object var10000;
      switch(((<undefinedtype>)$continuation).label) {
      case 0:
         ResultKt.throwOnFailure($result);
         ((<undefinedtype>)$continuation).L$0 = this;
         ((<undefinedtype>)$continuation).L$1 = context;
         ((<undefinedtype>)$continuation).label = 1;
         var10000 = checkAccess$default(this, context, false, false, (Continuation)$continuation, 6, (Object)null);
         if (var10000 == var28) {
            return var28;
         }
         break;
      case 1:
         context = (RoutingContext)((<undefinedtype>)$continuation).L$1;
         this = (FileController)((<undefinedtype>)$continuation).L$0;
         ResultKt.throwOnFailure($result);
         var10000 = $result;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      ReturnData checkResult = (ReturnData)var10000;
      if (checkResult != null) {
         return checkResult;
      } else {
         ReturnData returnData = new ReturnData();
         Object home = null;
         home = context.get("__FILE_HOME__");
         if (home == null) {
            return returnData.setErrorMsg("参数错误");
         } else {
            Object path = null;
            int var7 = false;
            String var8;
            List fileList;
            int var29;
            if (context.request().method() == HttpMethod.POST) {
               var8 = context.getBodyAsJson().getString("path");
               path = var8 == null ? "" : var8;
               Integer var30 = context.getBodyAsJson().getInteger("import", Boxing.boxInt(0));
               Intrinsics.checkNotNullExpressionValue(var30, "context.bodyAsJson.getInteger(\"import\", 0)");
               var29 = ((Number)var30).intValue();
            } else {
               fileList = context.queryParam("path");
               Intrinsics.checkNotNullExpressionValue(fileList, "context.queryParam(\"path\")");
               var8 = (String)CollectionsKt.firstOrNull(fileList);
               path = var8 == null ? "" : var8;
               fileList = context.queryParam("import");
               Intrinsics.checkNotNullExpressionValue(fileList, "context.queryParam(\"import\")");
               var8 = (String)CollectionsKt.firstOrNull(fileList);
               int var39;
               if (var8 == null) {
                  var39 = 0;
               } else {
                  boolean var11 = false;
                  Integer var32 = Boxing.boxInt(Integer.parseInt(var8));
                  var39 = var32 == null ? 0 : var32;
               }

               var29 = var39;
            }

            CharSequence var31 = (CharSequence)path;
            boolean var34 = false;
            if (var31.length() == 0) {
               path = "/";
            }

            File file = new File(Intrinsics.stringPlus((String)home, path));
            FileControllerKt.access$getLogger$p().info("file: {} {}", path, file);
            if (!file.exists()) {
               return returnData.setErrorMsg("路径不存在");
            } else if (!file.isDirectory()) {
               return returnData.setErrorMsg("路径不是目录");
            } else {
               fileList = null;
               boolean var10 = false;
               Object fileList = new ArrayList();
               Object userNameSpace = null;
               userNameSpace = this.getUserNameSpace(context);
               Object rootDir = null;
               rootDir = ExtKt.getWorkDir$default((String)null, 1, (Object)null);
               String var12 = File.separator;
               Intrinsics.checkNotNullExpressionValue(var12, "separator");
               if (!StringsKt.endsWith$default(rootDir, var12, false, 2, (Object)null)) {
                  rootDir = Intrinsics.stringPlus(rootDir, File.separator);
               }

               BookController bookController = new BookController(this.getCoroutineContext());
               Iterable $this$forEach$iv = (Iterable)ExtKt.listFilesRecursively(file);
               int $i$f$forEach = false;
               Iterator var15 = $this$forEach$iv.iterator();

               while(var15.hasNext()) {
                  Object element$iv = var15.next();
                  File it = (File)element$iv;
                  int var18 = false;
                  String fileName = it.getName();
                  Intrinsics.checkNotNullExpressionValue(fileName, "it.name");
                  if (!StringsKt.startsWith$default(fileName, ".", false, 2, (Object)null) && it.isFile()) {
                     fileName = it.getName();
                     BaseController var40 = (BaseController)this;
                     Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
                     String ext = BaseController.getFileExt$default(var40, fileName, (String)null, 2, (Object)null);
                     switch(ext.hashCode()) {
                     case 98299:
                        if (!ext.equals("cbz")) {
                           continue;
                        }
                        break;
                     case 110834:
                        if (!ext.equals("pdf")) {
                           continue;
                        }
                        break;
                     case 115312:
                        if (!ext.equals("txt")) {
                           continue;
                        }
                        break;
                     case 115916:
                        if (!ext.equals("umd")) {
                           continue;
                        }
                        break;
                     case 3120248:
                        if (!ext.equals("epub")) {
                           continue;
                        }
                        break;
                     default:
                        continue;
                     }

                     String relativePath = it.getPath();
                     FileControllerKt.access$getLogger$p().debug("rootDir: {} path: {}", rootDir, path);
                     Intrinsics.checkNotNullExpressionValue(relativePath, "relativePath");
                     if (StringsKt.startsWith$default(relativePath, rootDir, false, 2, (Object)null)) {
                        Intrinsics.checkNotNullExpressionValue(relativePath, "relativePath");
                        relativePath = StringsKt.replaceFirst$default(relativePath, rootDir, "", false, 4, (Object)null);
                     }

                     FileControllerKt.access$getLogger$p().debug("relative path: {}", relativePath);
                     Intrinsics.checkNotNullExpressionValue(relativePath, "relativePath");
                     String url = StringsKt.replace$default(relativePath, "\\", "/", false, 4, (Object)null);
                     Book.Companion var41 = Book.Companion;
                     Intrinsics.checkNotNullExpressionValue(relativePath, "relativePath");
                     Book book = var41.initLocalBook(url, relativePath, rootDir);
                     book.setUserNameSpace(userNameSpace);
                     FileControllerKt.access$getLogger$p().debug("book {}", book);
                     if (var29 > 0) {
                        Pair result = bookController.saveBookToShelf(book, userNameSpace, context);
                        if (result.getSecond() == null && ((Book)result.getFirst()).isInShelf()) {
                           fileList.add(MapsKt.mapOf(TuplesKt.to("name", it.getName())));
                        }
                     } else {
                        Pair[] var42 = new Pair[]{TuplesKt.to("name", it.getName()), TuplesKt.to("size", Boxing.boxLong(it.length())), null, null, null};
                        String var25 = it.toString();
                        Intrinsics.checkNotNullExpressionValue(var25, "it.toString()");
                        var42[2] = TuplesKt.to("path", StringsKt.replaceFirst$default(var25, (String)home, "", false, 4, (Object)null));
                        var42[3] = TuplesKt.to("lastModified", Boxing.boxLong(it.lastModified()));
                        var42[4] = TuplesKt.to("book", book);
                        fileList.add(MapsKt.mapOf(var42));
                     }
                  }
               }

               return ReturnData.setData$default(returnData, fileList, (String)null, 2, (Object)null);
            }
         }
      }
   }
}
